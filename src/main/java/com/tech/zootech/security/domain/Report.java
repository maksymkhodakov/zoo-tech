package com.tech.zootech.security.domain;

import com.tech.zootech.security.domain.interfaces.IReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report extends AbstractEntity implements IReport {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public Report(IReport iReport) {
        this.id = iReport.getId();
        this.created = iReport.getCreated();
        this.updated = iReport.getUpdated();
        this.name = iReport.getName();
        this.type = iReport.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Report report = (Report) o;
        return id != null && Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
