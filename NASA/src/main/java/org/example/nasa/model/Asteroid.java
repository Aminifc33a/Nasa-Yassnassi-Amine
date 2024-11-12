package org.example.nasa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="asteroid")
public class Asteroid {
    @Id
    private int id;
    private String name;
    @Column(name = "absolute_magnitude")
    private double magnitude;
    @Column(name = "diameter_km_average")
    private double diameter;
    @Column(name = "is_potentially_hazardous")
    private boolean isPotentiallyHazardous;
    @OneToMany(mappedBy = "asteroid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Approach> aproaches;
}