package com.ibas.safetynet.data.controller;

import com.ibas.safetynet.data.service.BrnService;
import com.ibas.safetynet.data.service.MfsAccOwnerInfoService;
import com.ibas.safetynet.data.service.NidService;
import jakarta.validation.constraints.*;
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
            @RequestParam @NotBlank @Size(min = 17, max = 17) String ubrn,
            @RequestParam @Past LocalDate dob) {
        return ResponseEntity.ofNullable(brnService.getBrnInfoDto(ubrn, dob));
    }

    @GetMapping(path = "/NIDinfo")
    @ResponseBody
    public ResponseEntity<?> getNidData(
            @RequestParam @NotBlank @Size(min = 10, max = 17) String nid,
            @RequestParam @Past LocalDate dob) {
        return ResponseEntity.ofNullable(nidService.getNidInfo(nid, dob));
    }

    @GetMapping(path = "/MFSInfo")
    @ResponseBody
    public ResponseEntity<?> getMfsData(
            @RequestParam @NotBlank @Size(min = 4, max = 5, message = "MFS name must be 4-5 letters") String mfs,
            @RequestParam @NotBlank @Size(min = 11, max = 11, message = "Mobile Number must be 11-digits") String mobile,
            @RequestParam @NotBlank @Size(min = 10, max = 17, message = "Wrong MFS") String nid
    ) {
        return ResponseEntity.ofNullable(mfsAccOwnerInfoService.getMfsOwnerInfoResponse(mfs, mobile, nid));
    }
}
