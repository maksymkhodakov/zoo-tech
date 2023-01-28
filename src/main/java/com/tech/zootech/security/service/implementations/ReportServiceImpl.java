package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.ReportDto;
import com.tech.zootech.security.domain.Report;
import com.tech.zootech.security.exceptions.ReportNotFound;
import com.tech.zootech.security.repo.ReportRepository;
import com.tech.zootech.security.service.ReportService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    public static final String REPORT_NOT_FOUND = "Report not found";
    private final ReportRepository reportRepository;

    @Override
    public List<ReportDto> getAll() {
        return reportRepository.findAll()
                .stream()
                .map(ReportDto::new)
                .toList();
    }

    @Override
    public ReportDto getById(Long id) {
        final var report = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFound(REPORT_NOT_FOUND));
        return new ReportDto(report);
    }

    @Override
    @Transactional
    public void create(ReportDto reportDto) {
        final var report = new Report(reportDto);
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void update(Long idReportToUpdate, ReportDto reportDto) {
        final var report = reportRepository.findById(idReportToUpdate)
                .orElseThrow(() -> new ReportNotFound(REPORT_NOT_FOUND));
        report.setType(reportDto.getType());
        report.setName(reportDto.getName());
    }
}
