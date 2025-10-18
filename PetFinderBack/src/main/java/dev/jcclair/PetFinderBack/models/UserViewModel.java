package dev.jcclair.PetFinderBack.models;

public class UserViewModel {
    private String email;
    private String password;

    public UserViewModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserViewModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
