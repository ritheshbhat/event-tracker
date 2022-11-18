package com.springboot.tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "events"
)
public class Event {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long event_id ;
//    private String department;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private java.sql.Date eventDate;
    @Column(nullable = false)
    private String venue;
    @Column(nullable = false)
    private Time eventTime;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "uEvent",cascade = CascadeType.ALL)
    private Set<UserEvents> uEvent=new HashSet<>();


}
