package org.example.nasaweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Asteroids")
public class Asteroid {
    public Asteroid(long id, String name, BigDecimal absoluteMagnitude, BigDecimal diameter, Boolean isPotentiallyHazardous) {
        this.id = id;
        this.name = name;
        this.absoluteMagnitude = absoluteMagnitude;
        this.diameterKmAverage = diameter;
        this.isPotentiallyHazardous = isPotentiallyHazardous;
    }
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "absolute_magnitude", precision = 5, scale = 2)
    private BigDecimal absoluteMagnitude;

    @Column(name = "diameter_km_average", precision = 10, scale = 2)
    private BigDecimal diameterKmAverage;

    @NotNull
    @Column(name = "is_potentially_hazardous", nullable = false)
    private Boolean isPotentiallyHazardous = false;

    @OneToMany(mappedBy = "asteroid", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Approach> approaches = new ArrayList<>();


}