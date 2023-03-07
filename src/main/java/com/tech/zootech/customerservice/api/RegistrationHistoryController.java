package com.tech.zootech.customerservice.api;

import com.tech.zootech.customerservice.domain.dto.DownloadFileDTO;
import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;
import com.tech.zootech.customerservice.service.RegistrationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration-history")
@RequiredArgsConstructor
public class RegistrationHistoryController {
    private final RegistrationHistoryService registrationHistoryService;
    @GetMapping("/customer/{id}")
    public ResponseEntity<RegistrationHistoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registrationHistoryService.getByCustomerId(id));
    }

    @PostMapping("/download/{reportHistoryId}")
    public ResponseEntity<Resource> downloadHistory(@PathVariable("reportHistoryId") Long id) {
        final DownloadFileDTO downloadFileDTO = registrationHistoryService.downloadFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(downloadFileDTO.getContentType()))
                .header("Content-Disposition", downloadFileDTO.getContentDispositionHeader())
                .body(downloadFileDTO.getResource());
    }
}
