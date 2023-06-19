package org.example.entities;

public class User {
    private String email;
    private String password;
    private Boolean isLoggedIn = false;

    public User(String email,String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setIsLoggedIn(Boolean bool) {
        this.isLoggedIn = bool;
    }

    public Boolean getIsLoggedIn(){
        return this.isLoggedIn;
    }
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
