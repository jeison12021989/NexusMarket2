package application.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Domain: Administrative reporting. Consolidates administrative
 * information for consultation (OBJ-12).
 */
@Entity
public class Report implements Generable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime generatedAt;

    protected Report() {
        // required by JPA
    }

    public Report(String name) {
        this.name = name;
    }

    @Override
    public boolean generate() {
        this.generatedAt = LocalDateTime.now();
        System.out.println("Generating report: " + name);
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
