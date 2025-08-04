package io.github.bokalebsson.model;

import java.util.Objects;

public class AppUser {

    // Attributes:
    private String username;
    private String password;
    private AppRole role;

    // Constructor:

    //Required for JSON deserialization (Jackson), Do not use this manually.
    AppUser() {
    }

    public AppUser(String username, String password, AppRole role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    // Getters:
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AppRole getRole() {
        return role;
    }

    // Setters:
    public void setUsername(String username) {
        if(username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.password = password;
    }

    public void setRole(AppRole role) {
        if (role == null){
            throw new IllegalArgumentException("Role cannot be null.");
        }
        this.role = role;
    }

    public static final AppUser GUEST = new AppUser("guest", "guest", AppRole.ROLE_APP_GUEST);

    // Operations:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- User Information --").append("\n");
        sb.append("Username: ").append(username).append("\n");
        sb.append("Role: ").append(role).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {

        // Step 1: Check if both references point to the same object in memory.
        if (this == o) return true;

        // Step 2: If the other object is null, they cannot be equal.
        if (o == null) return false;

        // Step 3: Check if the other object is of type AppUser.
        if (!(o instanceof AppUser)) return false;

        // Step 4: Cast the object to AppUser so we can access its fields.
        AppUser other = (AppUser) o;

        // Step 5: Compare fields that define identity (excluding password).
        return this.username.equals(other.username) && this.role == other.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username, this.role);
    }

}
