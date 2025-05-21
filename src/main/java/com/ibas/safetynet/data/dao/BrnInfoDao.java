package com.ibas.safetynet.data.dao;

import com.ibas.safetynet.data.model.BrnInfo;
import com.ibas.safetynet.data.payload.brn.BrnInfoSaveDto;
import com.ibas.safetynet.data.payload.nid.NidInfoSaveDto;
import com.ibas.safetynet.data.repository.BrnInfoRepository;
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
public class BrnInfoDao {
    private final BrnInfoRepository brnInfoRepo;

    @Transactional
    public void saveBrnInfo(BrnInfoSaveDto dto) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<BrnInfoSaveDto>> violations = validator.validate(dto);
            if (violations.isEmpty()) {
                BrnInfo brnInfo = brnInfoRepo.findBrnInfoByUbrnAndDob(dto.getUbrn(), dto.getDob()).orElse(new BrnInfo());
                brnInfo.setUbrn(dto.getUbrn());
                brnInfo.setDob(dto.getDob());
                brnInfo.setPersonName(dto.getPersonName());
                brnInfo.setPersonNameEn(dto.getPersonNameEn());
                brnInfo.setFatherName(dto.getFatherName());
                brnInfo.setMotherName(dto.getMotherName());
                brnInfo.setFatherNameEn(dto.getFatherNameEn());
                brnInfo.setMotherNameEn(dto.getMotherNameEn());
                brnInfo.setFatherNationality(dto.getFatherNationality());
                brnInfo.setMotherNationality(dto.getMotherNationality());
                brnInfo.setFatherNationalityEn(dto.getFatherNationalityEn());
                brnInfo.setMotherNationalityEn(dto.getMotherNationalityEn());
                brnInfo.setSex(dto.getSex());
                brnInfo.setPlaceOfBirth(dto.getPlaceOfBirth());
                brnInfo.setPlaceOfBirthEn(dto.getPlaceOfBirthEn());

                brnInfo.setReceivedAt(dto.getReceivedAt());
                brnInfo.setSentBy(dto.getSentBy());
                brnInfo.setSavedAt(LocalDateTime.now());

                brnInfoRepo.save(brnInfo);
            } else {
                StringBuilder violationMessage = new StringBuilder();
                for (ConstraintViolation<BrnInfoSaveDto> violation : violations) {
                    violationMessage.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append(". ");
                }
                violationMessage = new StringBuilder(violationMessage.toString().trim());
                log.info("For brn:{}, BRN save violation message: {}", dto.getUbrn(), violationMessage);
            }
        } catch (Exception e) {
            log.error("Error during brn save from kafka\n{}", e.toString());
        }
    }
}
