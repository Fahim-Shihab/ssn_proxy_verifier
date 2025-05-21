package com.ibas.safetynet.data.dao;

import com.ibas.safetynet.data.model.MfsAccOwnerInfo;
import com.ibas.safetynet.data.payload.brn.BrnInfoSaveDto;
import com.ibas.safetynet.data.payload.mfs.MfsAccOwnerInfoSaveDto;
import com.ibas.safetynet.data.payload.nid.NidInfoSaveDto;
import com.ibas.safetynet.data.repository.MfsAccOwnerInfoRepository;
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
public class MfsAccOwnerInfoDao {
    private final MfsAccOwnerInfoRepository mfsAccOwnerInfoRepo;

    @Transactional
    public void saveMfsAccOwnerInfo(MfsAccOwnerInfoSaveDto dto) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<MfsAccOwnerInfoSaveDto>> violations = validator.validate(dto);
            if (violations.isEmpty()) {
                if (dto.getMfsId() != null && (dto.getMfsId() == 1 || dto.getMfsId() == 2 ||
                        dto.getMfsId() == 4 || dto.getMfsId() == 5 || dto.getMfsId() == 7)
                        && (dto.getMobileNumber() != null && dto.getMobileNumber().length() == 11)
                        && dto.getNid() != null && (dto.getNid().trim().length() == 10 || dto.getNid().trim().length() == 17)) {
                    MfsAccOwnerInfo mfsAccOwnerInfo = mfsAccOwnerInfoRepo
                            .findByMfsIdAndMobileNumber(dto.getMfsId(), dto.getMobileNumber())
                            .orElse(new MfsAccOwnerInfo());

                    if (mfsAccOwnerInfo.getId() != null) {
                        if (mfsAccOwnerInfo.getNid().equals(dto.getNid())) {
                            if (mfsAccOwnerInfo.isMobileNumberOwnedByNid() == dto.getMobileNumberOwnedByNid()) {
                                // Duplicate message, no need for update
                                return;
                            }
                        }
                    }
                    mfsAccOwnerInfo.setMfsId(dto.getMfsId());
                    mfsAccOwnerInfo.setMobileNumber(dto.getMobileNumber().trim());
                    mfsAccOwnerInfo.setMobileNumberOwnedByNid(dto.getMobileNumberOwnedByNid());
                    mfsAccOwnerInfo.setNid(dto.getNid().trim());

                    mfsAccOwnerInfo.setReceivedAt(dto.getReceivedAt());
                    mfsAccOwnerInfo.setSentBy(dto.getSentBy());
                    mfsAccOwnerInfo.setSavedAt(LocalDateTime.now());

                    mfsAccOwnerInfoRepo.save(mfsAccOwnerInfo);
                }
            } else {
                StringBuilder violationMessage = new StringBuilder();
                for (ConstraintViolation<MfsAccOwnerInfoSaveDto> violation : violations) {
                    violationMessage.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append(". ");
                }
                violationMessage = new StringBuilder(violationMessage.toString().trim());
                log.info("For nid:{}, MFS save violation message: {}", dto.getNid(), violationMessage);
            }
        } catch (Exception e) {
            log.error("Error trying to save MFS info from kafka\n{}", e.toString());
        }
    }
}
