package com.example.demo.controller;

import com.example.demo.dto.SkateDto;
import com.example.demo.service.RentalService;
import com.example.demo.service.SkateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skate")
@AllArgsConstructor
public class SkateController {

    private final SkateService skateService;
    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<SkateDto>> findAll() {
        List<SkateDto> skates = skateService.getAllSkates();
        return ResponseEntity.ok(skates);
    }

    @GetMapping("/{size}")
    public ResponseEntity<List<SkateDto>> findAllBySize(@PathVariable Double size) {
        List<SkateDto> skatesBySize = skateService.getSkateBySize(size);
        return ResponseEntity.ok(skatesBySize);
    }

    @GetMapping("/available")
    public ResponseEntity<List<SkateDto>> findAllAvailable() {
        List<SkateDto> availableSkates = skateService.getAvailableSkates();
        return ResponseEntity.ok(availableSkates);
    }

    @GetMapping("/available/{size}")
    public ResponseEntity<List<SkateDto>> findAvailableBySize(@PathVariable Double size) {
        List<SkateDto> availableSkates = skateService.getAvailableSkatesBySize(size);
        return ResponseEntity.ok(availableSkates);
    }

    @PostMapping("/{size}")
    public ResponseEntity<SkateDto> createSkate(@Valid @PathVariable Double size) {
        SkateDto skateDto = skateService.createSkate(size);
        return ResponseEntity.status(HttpStatus.CREATED).body(skateDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSkate(@Valid @RequestBody SkateDto skateDto) {
        skateService.deleteSkate(skateDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{licenceNumber}")
    public ResponseEntity<SkateDto> rentSkate(@Valid @RequestBody SkateDto skateDto, @PathVariable Integer licenceNumber) {
        SkateDto rentedSkate = rentalService.rentSkate(licenceNumber, skateDto);
        return ResponseEntity.ok(rentedSkate);
    }

    @PatchMapping
    public ResponseEntity<SkateDto> returnSkate(@Valid @RequestBody SkateDto skateDto) {
        SkateDto returnSkate = rentalService.returnSkate(skateDto);
        return ResponseEntity.ok(returnSkate);
    }

}
