package com.sia.gentest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@NoArgsConstructor

@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(nullable = false)
    @NotNull(message = "firstName cannot be empty")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "age cannot be empty")
    private int age;

    @Column(nullable = true)
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
