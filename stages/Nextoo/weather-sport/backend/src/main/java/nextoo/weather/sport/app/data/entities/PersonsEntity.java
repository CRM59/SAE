package nextoo.weather.sport.app.data.entities;

import jakarta.persistence.*;
import nextoo.weather.sport.app.models.Person;

import java.util.Objects;

@Entity
@Table(name = "persons")
public class PersonsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personsSeq")
    @SequenceGenerator(name = "personsSeq", sequenceName = "persons_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonsEntity that = (PersonsEntity) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }


    public Person toPerson() {
        return new Person(
                firstName,
                lastName
        );
    }
}
