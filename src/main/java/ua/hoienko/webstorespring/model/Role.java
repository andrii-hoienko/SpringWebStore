package ua.hoienko.webstorespring.model;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String toString() {
        return this.name().replace("ROLE_", "");
    }
}
