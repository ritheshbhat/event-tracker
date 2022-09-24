package com.sample.rest.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Time;

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
}
