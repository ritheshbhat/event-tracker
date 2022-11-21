package com.springboot.tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "departments"
)
public class Department {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id ;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "department",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<Event> events=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "uDepartment",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<UserDepartments> uDepartment=new HashSet<>();






}
