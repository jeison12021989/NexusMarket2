package application.domain.models;

/**
 * Read-only / operational follow-up profile.
 */
public class Supervisor extends User {

    public Supervisor(Long identifier, String name, String email, String document) {
        super(identifier, name, email, document);
    }

    @Override
    public String showRole() {
        return "Supervisor";
    }

    public void consultInformation() {
        System.out.println("Supervisor " + getName() + " is performing a consultation.");
    }
}
