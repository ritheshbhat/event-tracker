package com.springboot.tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"}),


})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    private long utaId;
    private long zipcode;
    private String address;
    private long phNo;
    private byte[] barcode;




    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
       joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
       inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "uEvent",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<UserEvents> uEvent=new HashSet<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "uDepartment",cascade = CascadeType.REMOVE,orphanRemoval = true)
//    private Set<UserDepartments> uDepartment=new HashSet<>();



}
