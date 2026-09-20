package application.service;

import application.domain.models.Report;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public boolean generateReport(String name) {
        return new Report(name).generate();
    }
}
