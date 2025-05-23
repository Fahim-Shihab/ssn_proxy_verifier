package com.ibas.safetynet.data.service;

import com.ibas.safetynet.common.GenericResponse;
import com.ibas.safetynet.common.MFS;
import com.ibas.safetynet.common.Utility;
import com.ibas.safetynet.data.model.MfsAccOwnerInfo;
import com.ibas.safetynet.data.payload.mfs.MfsAccOwnerInfoDto;
import com.ibas.safetynet.data.payload.mfs.MfsAccOwnerInfoSaveDto;
import com.ibas.safetynet.data.payload.mfs.MfsOwnerInfoResponse;
import com.ibas.safetynet.data.repository.MfsAccOwnerInfoRepository;
import com.ibas.safetynet.kafka.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class MfsAccOwnerInfoService {
    private final MfsAccOwnerInfoRepository mfsAccOwnerInfoRepository;
    private final KafkaService kafkaService;

    public MfsOwnerInfoResponse getMfsOwnerInfoResponse(String mfsName, String mobileNumber, String nid) {
        try {
            MFS mfs = MFS.getByName(mfsName);
            if (mfs != null) {
                MfsAccOwnerInfo mfsAccOwnerInfo = mfsAccOwnerInfoRepository
                        .findByMfsIdAndMobileNumber(mfs.getValue(), mobileNumber).orElse(null);
                if (mfsAccOwnerInfo != null) {
                    MfsOwnerInfoResponse response = new MfsOwnerInfoResponse();
                    response.setAccountExists(true);
                    response.setNIDMatched(mfsAccOwnerInfo.getNid().equals(nid));
                    response.setNIDMobileMatched(mfsAccOwnerInfo.isMobileNumberOwnedByNid());

                    return response;
                } else {
                    return new MfsOwnerInfoResponse(false, false, false, "No record found for this mobile number");
                }
            } else {
                return new MfsOwnerInfoResponse(false, false, false, "Wrong MFS");
            }

        } catch (Exception e) {
            log.error("Error trying to find MFS Owner info\n{}",e.getMessage());
            return new MfsOwnerInfoResponse(null, null, null, "Internal Server Error");
        }
    }

    public GenericResponse saveMfsInfoAsync(MfsAccOwnerInfoDto dto) {
        try {
            MfsAccOwnerInfoSaveDto saveDto = new MfsAccOwnerInfoSaveDto();
            BeanUtils.copyProperties(dto, saveDto);
            saveDto.setReceivedAt(LocalDateTime.now());
            saveDto.setSentBy(Utility.getAuthenticatedUserId());
            kafkaService.publishMfsMessage(saveDto);
            return new GenericResponse(HttpStatus.OK.value(), "Success");
        } catch (Exception e) {
            log.error("Error saving BRN info\n{}", e.getMessage());
        }

        return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
    }
}
