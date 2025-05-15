package com.ibas.safetynet.data.service;

import com.ibas.safetynet.common.GenericResponse;
import com.ibas.safetynet.data.model.BrnInfo;
import com.ibas.safetynet.data.payload.BrnInfoDto;
import com.ibas.safetynet.data.repository.BrnInfoRepository;
import com.ibas.safetynet.kafka.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrnService {
    private final BrnInfoRepository brnInfoRepo;
    private final KafkaService kafkaService;

    @Transactional(readOnly = true)
    public BrnInfoDto getBrnInfoDto(String ubrn, LocalDate dob) {
        try {
            BrnInfo brnInfo = brnInfoRepo.findBrnInfoByUbrnAndDob(ubrn, dob).orElse(null);
            if (brnInfo != null) {
                BrnInfoDto brnInfoDto = new BrnInfoDto();
                BeanUtils.copyProperties(brnInfo, brnInfoDto);
                return brnInfoDto;
            } else {
                return new BrnInfoDto();
            }
        } catch (Exception e) {
            log.error("Error retrieving BRN info\n{}", e.getMessage());
            return null;
        }
    }

    public GenericResponse saveBrnInfo(BrnInfoDto dto) {
        try {
            kafkaService.publishBrnMessage(dto);
            return new GenericResponse(HttpStatus.OK.value(), "Success");
        } catch (Exception e) {
            log.error("Error saving BRN info\n{}", e.getMessage());
        }

        return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
    }
}
