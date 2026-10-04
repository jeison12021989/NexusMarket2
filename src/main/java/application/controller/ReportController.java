package application.controller;

import application.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public boolean generate(@RequestBody Map<String, String> body) {
        return reportService.generateReport(body.get("name"));
    }
}
