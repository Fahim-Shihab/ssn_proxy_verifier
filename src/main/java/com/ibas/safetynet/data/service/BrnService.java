package com.ibas.safetynet.data.service;

import com.ibas.safetynet.data.model.BrnInfo;
import com.ibas.safetynet.data.payload.BrnInfoDto;
import com.ibas.safetynet.data.repository.BrnInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrnService {
    private final BrnInfoRepository brnInfoRepo;

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
}
