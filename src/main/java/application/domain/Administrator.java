package application.domain.models;

/**
 * Responsible for onboarding sellers and warehouses, and for consulting
 * administrative reports.
 */
public class Administrator extends User {

    public Administrator(Long identifier, String name, String email, String document) {
        super(identifier, name, email, document);
    }

    @Override
    public String showRole() {
        return "Administrator";
    }

    public void registerSeller(Seller seller) {
        System.out.println("Seller " + seller.getName() + " registered successfully.");
    }

    public boolean generateReport(Report report) {
        System.out.println("Requested report: " + report.getName());
        return report.generate();
    }
}
