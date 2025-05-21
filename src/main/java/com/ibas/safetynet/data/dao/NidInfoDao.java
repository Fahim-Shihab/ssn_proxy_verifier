package com.ibas.safetynet.data.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibas.safetynet.data.model.NidInfo;
import com.ibas.safetynet.data.payload.brn.BrnInfoSaveDto;
import com.ibas.safetynet.data.payload.nid.AddressDto;
import com.ibas.safetynet.data.payload.nid.NidInfoSaveDto;
import com.ibas.safetynet.data.repository.NidInfoRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NidInfoDao {
    private final NidInfoRepository nidInfoRepo;
    private final GsonBuilder builder = new GsonBuilder();
    private final Gson gson = builder.create();

    @Transactional
    public void saveNidInfo(NidInfoSaveDto dto) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<NidInfoSaveDto>> violations = validator.validate(dto);
            if (violations.isEmpty()) {
                if (
                        (dto.getNationalId() != null && (dto.getNationalId().length() == 10 || dto.getNationalId().length() == 17))
                                && (dto.getPin() != null && (dto.getPin().length() == 10 || dto.getPin().length() == 17))
                ) {
                    NidInfo nidInfo = nidInfoRepo.findByNidAndDob(dto.getNationalId(), dto.getDob()).orElse(new NidInfo());
                    if (nidInfo.getId() == null) {
                        nidInfo = nidInfoRepo.findByNidAndDob(dto.getPin(), dto.getDob()).orElse(new NidInfo());
                    }
                    if (dto.getNationalId().length() == 10) {
                        nidInfo.setSmartId(dto.getNationalId());
                        nidInfo.setNid(dto.getPin());
                    } else {
                        nidInfo.setSmartId(dto.getPin());
                        nidInfo.setNid(dto.getNationalId());
                    }
                    nidInfo.setDob(dto.getDob());
                    nidInfo.setNameBn(dto.getName());
                    nidInfo.setNameEn(dto.getNameEn());
                    nidInfo.setGender(dto.getGender());
                    nidInfo.setReligion(dto.getReligion());
                    nidInfo.setBloodGroup(dto.getBloodGroup());
                    nidInfo.setFatherName(dto.getFather());
                    nidInfo.setMotherName(dto.getMother());
                    nidInfo.setFatherNid(dto.getNidFather());
                    nidInfo.setMotherNid(dto.getNidMother());
                    nidInfo.setPhoto(dto.getPhoto());
                    if (dto.getPermanentAddress() != null) {
                        try {
                            nidInfo.setPermanentAddress(dto.getPermanentAddress());
                        } catch (Exception e) {
                            log.error("Error trying to convert permanent address to json-string during nid save\n{}", e.toString());
                        }
                    }
                    if (dto.getPresentAddress() != null) {
                        try {
                            nidInfo.setPresentAddress(dto.getPresentAddress());
                        } catch (Exception e) {
                            log.error("Error trying to convert present address to json-string during nid save\n{}", e.toString());
                        }
                    }

                    nidInfo.setReceivedAt(dto.getReceivedAt());
                    nidInfo.setSentBy(dto.getSentBy());
                    nidInfo.setSavedAt(LocalDateTime.now());

                    nidInfoRepo.save(nidInfo);
                }
            } else {
                StringBuilder violationMessage = new StringBuilder();
                for (ConstraintViolation<NidInfoSaveDto> violation : violations) {
                    violationMessage.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append(". ");
                }
                violationMessage = new StringBuilder(violationMessage.toString().trim());
                log.info("For nationalId:{}, nid save violation message: {}", dto.getNationalId(), violationMessage);
            }
        } catch (Exception e) {
            log.error("Error trying to save NID info from kafka\n{}", e.toString());
        }
    }
}
