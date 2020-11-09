package de.oth.regensburg.projektstudium.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Building {
    private @Id
    @GeneratedValue
    Long id;
    private String shortName;
    private String fullName;
    private String description;
    private String address;
    @OneToMany
    private Collection<Person> people = new HashSet<>();

    public Building() {
    }

    public Building(String shortName, String fullName, String description, String address, Collection<Person> people) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.description = description;
        this.address = address;
        this.people = people;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Person> getEmployees() {
        return people;
    }

    public void setEmployees(Collection<Person> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return id.equals(building.id) &&
                Objects.equals(shortName, building.shortName) &&
                Objects.equals(fullName, building.fullName) &&
                Objects.equals(description, building.description) &&
                Objects.equals(address, building.address) &&
                Objects.equals(people, building.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortName, fullName, description, address, people);
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}