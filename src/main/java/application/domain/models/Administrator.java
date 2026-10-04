package application.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Responsible for onboarding sellers and warehouses, and for consulting
 * administrative reports (OBJ-02, OBJ-04, OBJ-12).
 */
@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends User {

    protected Administrator() {
        super();
    }

    public Administrator(String name, String email, String document) {
        super(name, email, document);
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
