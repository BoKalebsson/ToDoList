package io.github.bokalebsson.model;

import io.github.bokalebsson.AppRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserTest {

    // Group: Constructor tests

    @Test
    // Checks constructor sets fields properly with valid inputs
    public void constructorSetsFields() {
        // Arrange
        String username = "user123";
        String password = "pass123";
        AppRole role = AppRole.ROLE_APP_USER;

        // Act
        AppUser user = new AppUser(username, password, role);

        // Assert
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    // Throws if username is null or empty
    public void constructorInvalidUsername() {
        // Arrange
        String password = "pass123";
        AppRole role = AppRole.ROLE_APP_USER;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AppUser(null, password, role));
        assertThrows(IllegalArgumentException.class, () -> new AppUser("  ", password, role));
    }

    @Test
    // Throws if password is null or empty
    public void constructorInvalidPassword() {
        // Arrange
        String username = "user123";
        AppRole role = AppRole.ROLE_APP_USER;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AppUser(username, null, role));
        assertThrows(IllegalArgumentException.class, () -> new AppUser(username, "  ", role));
    }

    @Test
    // Throws if role is null
    public void constructorInvalidRole() {
        // Arrange
        String username = "user123";
        String password = "pass123";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AppUser(username, password, null));
    }

    // Group: Setter and getter tests

    @Test
    // Setters update fields correctly
    public void setAndGetFields() {
        // Arrange
        AppUser user = new AppUser("user1", "pass1", AppRole.ROLE_APP_ADMIN);

        // Act
        user.setUsername("newUser");
        user.setPassword("newPass");
        user.setRole(AppRole.ROLE_APP_USER);

        // Assert
        assertEquals("newUser", user.getUsername());
        assertEquals("newPass", user.getPassword());
        assertEquals(AppRole.ROLE_APP_USER, user.getRole());
    }

    @Test
    // setUsername throws on null or empty
    public void setUsernameInvalid() {
        // Arrange
        AppUser user = new AppUser("user", "pass", AppRole.ROLE_APP_USER);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> user.setUsername(null));
        assertThrows(IllegalArgumentException.class, () -> user.setUsername("  "));
    }

    @Test
    // setPassword throws on null or empty
    public void setPasswordInvalid() {
        // Arrange
        AppUser user = new AppUser("user", "pass", AppRole.ROLE_APP_USER);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(null));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("  "));
    }

    @Test
    // setRole throws on null
    public void setRoleInvalid() {
        // Arrange
        AppUser user = new AppUser("user", "pass", AppRole.ROLE_APP_USER);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> user.setRole(null));
    }

    // Group: toString tests

    @Test
    // toString excludes password but includes username and role
    public void toStringNoPassword() {
        // Arrange
        AppUser user = new AppUser("user", "secretPass", AppRole.ROLE_APP_USER);

        // Act
        String result = user.toString();

        // Assert
        assertTrue(result.contains("user"));
        assertTrue(result.contains("ROLE_APP_USER"));
        assertFalse(result.contains("secretPass"));
    }

    // Group: equals and hashCode tests

    @Test
    // equals and hashCode are equal for users with same fields (except password ignored)
    public void equalsHashCodeSame() {
        // Arrange
        AppUser user1 = new AppUser("user", "pass", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("user", "pass", AppRole.ROLE_APP_USER);

        // Act & Assert
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    // equals returns false for different users
    public void equalsDifferentUsers() {
        // Arrange
        AppUser user1 = new AppUser("user1", "pass1", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("user2", "pass2", AppRole.ROLE_APP_ADMIN);

        // Act & Assert
        assertNotEquals(user1, user2);
    }

    @Test
    // equals returns false for different class
    public void equalsDifferentClass() {
        // Arrange
        AppUser user = new AppUser("user", "pass", AppRole.ROLE_APP_USER);
        Object other = new Object();

        // Act & Assert
        assertNotEquals(user, other);
    }

    @Test
    // equals returns false for null
    public void equalsNull() {
        // Arrange
        AppUser user = new AppUser("user", "pass", AppRole.ROLE_APP_USER);

        // Act & Assert
        assertNotEquals(null, user);
    }
}