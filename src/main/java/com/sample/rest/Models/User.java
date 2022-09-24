package com.sample.rest.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;



@Entity

@Table(name = "users")
public class User {
//    private @Id @GeneratedValue long id;
    private @NotBlank String email;
    private @NotBlank String password;
    private @NotBlank boolean loggedIn;


    public User() {
    }
    public User(@NotBlank String email,
                @NotBlank String password) {
        this.email = email;
        this.password = password;
        this.loggedIn = false;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String zipcode;

    @Column
    private String utaId;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUtaId() {
        return utaId;
    }

    public void setUtaId(String utaId) {
        this.utaId = utaId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, email, password,
                loggedIn);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }




}

