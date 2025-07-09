package io.github.bokalebsson;

public class AppUser {

    // Attributes:
    private String username;
    private String password;
    private AppRole role;

    // Constructor:
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

    // Operations:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("-- User Information --").append("\n");
        sb.append("Username: ").append(username).append("\n");
        sb.append("Role: ").append(role).append("\n");
        sb.append("---------------------------").append("\n");
        sb.append("\n");
        return sb.toString();
    }

}
