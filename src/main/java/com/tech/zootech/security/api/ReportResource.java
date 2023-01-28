package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.ReportDto;
import com.tech.zootech.security.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/reports")
@AllArgsConstructor
public class ReportResource {
    private final ReportService reportService;

    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> getAll() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ReportDto> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getById(id));
    }

    @PutMapping("/create")
    public ResponseEntity<Void> create(@RequestBody ReportDto reportDto) {
        reportService.create(reportDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ReportDto reportDto) {
        reportService.update(id, reportDto);
        return ResponseEntity.ok().build();
    }
}
