package application.service;

import application.domain.models.Report;

/**
 * Administrative reporting (OBJ-12).
 */
public interface ReportService {

    boolean generateReport(String name);
}
