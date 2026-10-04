package application.service;

import application.domain.models.Report;
import application.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public boolean generateReport(String name) {
        Report report = new Report(name);
        boolean generated = report.generate();
        reportRepository.save(report);
        return generated;
    }
}
