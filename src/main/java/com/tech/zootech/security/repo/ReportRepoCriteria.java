package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Report;

import java.util.List;

public interface ReportRepoCriteria {
    List<Report> getAll();
    Report getById(Long id);
}
