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

    @Override
    public boolean equals(Object o) {

        // Step 1: If the two objects are the same in memory, they are equal.
        if (this == o) {
            return true;
        }

        // Step 2: If the other object is null, it can never be equal.
        if (o == null) {
            return false;
        }

        // Step 3: If the other object is not an AppUser, they are not comparable.
        if (!(o instanceof AppUser)) {
            return false;
        }

        // Step 4: Type cast the object so we can access its fields.
        AppUser other = (AppUser) o;

        // Step 5: Compare the fields that define identity.
        // We compare username using .equals() (it's a String),
        // and role using == because AppRole is an enum.
        return this.username.equals(other.username) && this.role == other.role;
    }

}
