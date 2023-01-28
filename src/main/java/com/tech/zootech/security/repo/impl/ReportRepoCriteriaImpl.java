package com.tech.zootech.security.repo.impl;

import com.tech.zootech.security.domain.Report;
import com.tech.zootech.security.repo.ReportRepoCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ReportRepoCriteriaImpl implements ReportRepoCriteria {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Report> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Report> criteriaQuery = criteriaBuilder.createQuery(Report.class);
        Root<Report> root = criteriaQuery.from(Report.class);

        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Report getById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Report> criteriaQuery = criteriaBuilder.createQuery(Report.class);
        Root<Report> root = criteriaQuery.from(Report.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("id"), id));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
