package com.sia.gentest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "passengers")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;


    @Column(nullable=false)
    @NotNull(message="firstName cannot be empty")
    private String firstName;
    @Column(nullable=false)
    @NotNull(message="age cannot be empty")
    private int age;
    @Column(nullable=true)
    private boolean gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Passenger)) {
            return false;
        }
        return id != null && id.equals(((Passenger) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
