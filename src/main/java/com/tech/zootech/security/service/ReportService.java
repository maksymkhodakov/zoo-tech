package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.ReportDto;

import java.util.List;

public interface ReportService {
    List<ReportDto> getAll();
    ReportDto getById(Long id);
    void create(ReportDto reportDto);
    void update(Long idReportToUpdate, ReportDto reportDto);
}
