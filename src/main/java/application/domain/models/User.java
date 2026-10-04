package application.domain.models;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * Domain 1 - User Administration.
 * Base identity for every participant of the Marketplace (Buyer, Seller,
 * Administrator, Logistics Operator, Supervisor). Each user has exactly
 * one role (RG-02) and a unique identifier, email and document (Critical
 * Validation - Users). Persisted with single-table inheritance.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifier;

    private String name;

    private String email;

    private String document;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    protected User() {
        // required by JPA
    }

    protected User(String name, String email, String document) {
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
