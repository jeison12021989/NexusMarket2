package application.domain.models;

/**
 * Base identity for every participant of the Marketplace (Buyer, Seller,
 * Administrator, Logistics Operator, Supervisor). Each user has exactly
 * one role and a unique identifier, email and document.
 */
public abstract class User {

    private final Long identifier;
    private String name;
    private String email;
    private String document;
    private UserStatus status;

    protected User(Long identifier, String name, String email, String document) {
        this.identifier = identifier;
        this.name = name;
        this.email = email;
        this.document = document;
        this.status = UserStatus.ACTIVE;
    }

    public abstract String showRole();

    public void showInformation() {
        System.out.println("User: " + name);
        System.out.println("Email: " + email);
        System.out.println("Document: " + document);
        System.out.println("Status: " + status.getLabel());
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
