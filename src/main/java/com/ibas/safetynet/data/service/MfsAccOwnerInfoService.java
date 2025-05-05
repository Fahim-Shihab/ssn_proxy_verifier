package com.ibas.safetynet.data.service;

import com.ibas.safetynet.common.MFS;
import com.ibas.safetynet.data.model.MfsAccOwnerInfo;
import com.ibas.safetynet.data.payload.MfsOwnerInfoResponse;
import com.ibas.safetynet.data.repository.MfsAccOwnerInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MfsAccOwnerInfoService {
    private final MfsAccOwnerInfoRepository mfsAccOwnerInfoRepository;

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
}
