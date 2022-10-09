package com.sample.rest.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
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
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Event> events=new HashSet<>();





}
