package org.example.nasaweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Approaches")
public class Approach {
    public Approach(Asteroid asteroid, LocalDate approachDate, BigDecimal velocity, BigDecimal distance, String orbitingBody) {
        this.asteroid = asteroid;
        this.approachDate = approachDate;
        this.velocity = velocity;
        this.distance = distance;
        this.orbitingBody = orbitingBody;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "asteroid_id", nullable = false)
    private Asteroid asteroid;

    @NotNull
    @Column(name = "approach_date", nullable = false)
    private LocalDate approachDate;

    @Column(name = "velocity", precision = 15, scale = 8)
    private BigDecimal velocity;

    @Column(name = "distance", precision = 15, scale = 8)
    private BigDecimal distance;

    @Size(max = 255)
    @Column(name = "orbiting_body")
    private String orbitingBody;

}