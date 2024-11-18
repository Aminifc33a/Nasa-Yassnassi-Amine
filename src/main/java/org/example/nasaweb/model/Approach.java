package org.example.nasaweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Approaches")
public class Approach {
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