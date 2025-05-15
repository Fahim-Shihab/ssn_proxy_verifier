package com.ibas.safetynet.data.controller;

import com.ibas.safetynet.data.payload.MfsAccOwnerInfoDto;
import com.ibas.safetynet.data.service.MfsAccOwnerInfoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saftynet")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MfsController {
    private final MfsAccOwnerInfoService mfsAccOwnerInfoService;

    @GetMapping(path = "/MFSInfo")
    @ResponseBody
    public ResponseEntity<?> getMfsData(
            @RequestParam @NotBlank @Size(min = 4, max = 5, message = "MFS name must be 4-5 letters") String mfs,
            @RequestParam @NotBlank @Size(min = 11, max = 11, message = "Mobile Number must be 11-digits") String mobile,
            @RequestParam @NotBlank @Size(min = 10, max = 17, message = "Invalid nid") String nid
    ) {
        return ResponseEntity.ofNullable(mfsAccOwnerInfoService.getMfsOwnerInfoResponse(mfs, mobile, nid));
    }

    @PostMapping(path = "/MFSInfo/save")
    @ResponseBody
    public ResponseEntity<?> saveMfsData(@Valid @RequestBody MfsAccOwnerInfoDto request) {
        return ResponseEntity.ofNullable(mfsAccOwnerInfoService.saveMfsInfo(request));
    }
}
