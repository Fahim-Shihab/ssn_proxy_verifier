package com.ibas.safetynet.data.controller;

import com.ibas.safetynet.data.payload.BrnInfoDto;
import com.ibas.safetynet.data.service.BrnService;
import com.ibas.safetynet.data.service.MfsAccOwnerInfoService;
import com.ibas.safetynet.data.service.NidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/saftynet")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DataController {
    private final BrnService brnService;
    private final NidService nidService;
    private final MfsAccOwnerInfoService mfsAccOwnerInfoService;

    @GetMapping(path = "/BRNInfo")
    @ResponseBody
    public ResponseEntity<?> getBrnData(
            @RequestParam String ubrn,
            @RequestParam LocalDate dob) {
        if (ubrn != null && !ubrn.trim().isEmpty() && dob != null) {
            return ResponseEntity.ofNullable(brnService.getBrnInfoDto(ubrn, dob));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/NIDinfo")
    @ResponseBody
    public ResponseEntity<?> getNidData(
            @RequestParam String nid,
            @RequestParam LocalDate dob) {
        if (nid != null && !nid.trim().isEmpty() && dob != null) {
            return ResponseEntity.ofNullable(nidService.getNidInfo(nid, dob));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/MFSInfo")
    @ResponseBody
    public ResponseEntity<?> getMfsData(
            @RequestParam String mfs,
            @RequestParam String mobile,
            @RequestParam String nid
    ) {
        if (mfs != null && !mfs.trim().isEmpty()
                && mobile != null && !mobile.trim().isEmpty()
                && nid != null && !nid.trim().isEmpty()) {
            return ResponseEntity.ofNullable(mfsAccOwnerInfoService.getMfsOwnerInfoResponse(mfs, mobile, nid));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
