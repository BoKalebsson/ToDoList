package io.github.bokalebsson;

public class AppUser {

    // Attributes:
    private String username;
    private String password;
    private AppRole role;

    // Constructor:
    public AppUser(String username, String password, AppRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
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
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
