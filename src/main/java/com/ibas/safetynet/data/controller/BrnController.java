package com.ibas.safetynet.data.controller;

import com.ibas.safetynet.data.payload.brn.BrnInfoDto;
import com.ibas.safetynet.data.service.BrnService;
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
public class BrnController {
    private final BrnService brnService;

    @GetMapping(path = "/BRNInfo")
    @ResponseBody
    public ResponseEntity<?> getBrnData(
            @RequestParam @NotBlank @Size(min = 17, max = 17) String ubrn,
            @RequestParam @Past LocalDate dob) {
        return ResponseEntity.ofNullable(brnService.getBrnInfoDto(ubrn, dob));
    }

    @PostMapping(path = "/BRNInfo/save")
    @ResponseBody
    public ResponseEntity<?> saveBrnData(@Valid @RequestBody BrnInfoDto request) {
        return ResponseEntity.ofNullable(brnService.saveBrnInfoAsync(request));
    }
}
