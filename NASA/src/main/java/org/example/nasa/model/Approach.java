package org.example.nasa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "approach")
public class Approach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approach_date")
    private String approachDate;

    private double velocity;
    private double distance;
    @Column(name = "orbiting_body")
    private String orbitingBody;

    @ManyToOne
    @JoinColumn(name = "asteroid_id", nullable = false)
    private Asteroid asteroid;  // Relación con la tabla de asteroides
}
