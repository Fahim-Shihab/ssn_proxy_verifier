package com.ibas.safetynet.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibas.safetynet.data.model.NidInfo;
import com.ibas.safetynet.data.payload.Nid.*;
import com.ibas.safetynet.data.repository.NidInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class NidService {
    private final NidInfoRepository nidInfoRepository;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    @Transactional(readOnly = true)
    public NidInfoResponse getNidInfo(String nid, LocalDate dob) {

        NidInfoResponse nidInfoResponse = null;

        try {
            NidInfo nidInfo = nidInfoRepository.findByNidAndDob(nid, dob).orElse(null);
            if (nidInfo != null) {
                NidInfoDto nidInfoDto = new NidInfoDto();

                nidInfoDto.setBloodGroup(nidInfo.getBloodGroup());
                nidInfoDto.setFather(nidInfo.getFatherName());
                nidInfoDto.setGender(nidInfo.getGender());
                nidInfoDto.setMother(nidInfo.getMotherName());
                nidInfoDto.setName(nidInfo.getNameBn());
                nidInfoDto.setNameEn(nidInfo.getNameEn());
                nidInfoDto.setNationalId(nidInfo.getSmartId());
                nidInfoDto.setNidFather(nidInfo.getFatherNid());
                nidInfoDto.setNidMother(nidInfo.getMotherNid());
                nidInfoDto.setPhoto(nidInfo.getPhoto());
                nidInfoDto.setPin(nidInfo.getNid());
                nidInfoDto.setReligion(nidInfo.getReligion());

                if (nidInfo.getPermanentAddress() != null) {
                    try {
                        AddressDto addressDto = gson.fromJson(nidInfo.getPermanentAddress(), AddressDto.class);
                        nidInfoDto.setPermanentAddress(addressDto);
                    } catch (Exception e) {
                        log.error("Error trying to convert permanentAddress to dto\n{}",e.getMessage());
                        nidInfoDto.setPermanentAddress(null);
                    }
                }

                if (nidInfo.getPresentAddress() != null) {
                    try {
                        AddressDto addressDto = gson.fromJson(nidInfo.getPresentAddress(), AddressDto.class);
                        nidInfoDto.setPresentAddress(addressDto);
                    } catch (Exception e) {
                        log.error("Error trying to convert presentAddress to dto\n{}",e.getMessage());
                        nidInfoDto.setPresentAddress(null);
                    }
                }

                nidInfoResponse = new NidInfoResponse(HttpStatus.OK.getReasonPhrase(),HttpStatus.OK.value()+"",
                        new NidSuccessInfo(nidInfoDto), null);

                return nidInfoResponse;
            } else {
                return new NidInfoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()+"",
                        null, new NidFailureInfo(HttpStatus.NOT_FOUND.getReasonPhrase()));
            }
        } catch (Exception e) {
            log.error("Error retrieving NID info\n{}", e.getMessage());
            return new NidInfoResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()+"", null,
                    new NidFailureInfo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }
    }
}
