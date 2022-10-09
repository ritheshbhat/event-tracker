package com.sample.rest.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

}
