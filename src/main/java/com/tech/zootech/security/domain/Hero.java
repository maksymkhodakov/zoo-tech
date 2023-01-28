package com.tech.zootech.security.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Entity
@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "heroes")
public class Hero extends AbstractEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "city_name", nullable = false)
    private String city;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hero hero = (Hero) o;
        return id != null && Objects.equals(id, hero.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
