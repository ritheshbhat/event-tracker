package com.sample.rest.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = "utaId")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String zipcode;
    private String state;
    private String country;
//    private Timestamp created_on;
//    private Timestamp last_updated;
    private String utaId;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;


}

