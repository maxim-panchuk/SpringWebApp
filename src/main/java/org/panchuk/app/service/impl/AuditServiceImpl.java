package org.panchuk.app.service.impl;

import org.panchuk.app.entity.Audit;
import org.panchuk.app.repository.AuditRepository;
import org.panchuk.app.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl (AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
    @Override
    public void addAction(Audit audit) {
        auditRepository.saveAndFlush(audit);
    }
}
