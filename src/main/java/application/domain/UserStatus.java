package application.domain.models;

public enum UserStatus {
    ACTIVE("Active"),
    BLOCKED("Blocked"),
    INACTIVE("Inactive");

    private final String label;

    UserStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
