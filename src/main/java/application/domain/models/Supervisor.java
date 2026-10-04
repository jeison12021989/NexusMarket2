package application.domain.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Read-only / operational follow-up profile (Participant: Supervisor).
 */
@Entity
@DiscriminatorValue("SUPERVISOR")
public class Supervisor extends User {

    protected Supervisor() {
        super();
    }

    public Supervisor(String name, String email, String document) {
        super(name, email, document);
    }

    @Override
    public String showRole() {
        return "Supervisor";
    }

    public void consultInformation() {
        System.out.println("Supervisor " + getName() + " is performing a consultation.");
    }
}
