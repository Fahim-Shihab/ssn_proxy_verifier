package com.ibas.safetynet.data.controller;

import com.ibas.safetynet.data.payload.Nid.NidInfoDto;
import com.ibas.safetynet.data.service.NidService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/saftynet")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NidController {
    private final NidService nidService;

    @GetMapping(path = "/NIDinfo")
    @ResponseBody
    public ResponseEntity<?> getNidData(
            @RequestParam @NotBlank @Size(min = 10, max = 17) String nid,
            @RequestParam @Past LocalDate dob) {
        return ResponseEntity.ofNullable(nidService.getNidInfo(nid, dob));
    }

    @PostMapping(path = "/NIDinfo/save")
    @ResponseBody
    public ResponseEntity<?> saveNidData(@Valid @RequestBody NidInfoDto request) {
        return ResponseEntity.ofNullable(nidService.saveNidInfo(request));
    }
}
