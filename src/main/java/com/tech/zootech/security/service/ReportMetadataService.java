package com.tech.zootech.security.service;

import com.tech.zootech.security.domain.Report;
import com.tech.zootech.security.repo.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportMetadataService implements IMetadata<Report> {
    private final ReportRepository reportRepository;

    @Override
    public List<Report> extractData() {
        return reportRepository.findAll()
                .stream()
                .peek(it -> it.setId(null))
                .toList();
    }

    @Override
    public void uploadData(Report data) {
        reportRepository.save(data);
    }
}
