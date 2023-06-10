package ba.unsa.etf.apigateway.model;

import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String bio;
    private UUID profileLinks;

    private String role;


    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public UUID getProfileLinks() {
        return profileLinks;
    }

    public void setProfileLinks(final UUID profileLinks) {
        this.profileLinks = profileLinks;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

}
