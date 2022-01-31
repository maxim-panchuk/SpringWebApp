package org.panchuk.app.service;

import org.panchuk.app.entity.Audit;
import org.springframework.stereotype.Service;

@Service
public interface AuditService {
    void addAction (Audit audit);
}
