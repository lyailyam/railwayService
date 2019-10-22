package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "railways-test", catalog = "")
@IdClass(UserEntityPK.class)
public class UserEntity {
    private int id;
    private String email;
    private String firstname;
    private String surname;
    private Byte activated;
    private String nationalId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = 255)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 255)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "activated", nullable = true)
    public Byte getActivated() {
        return activated;
    }

    public void setActivated(Byte activated) {
        this.activated = activated;
    }

    @Basic
    @Column(name = "national_id", nullable = true, length = 45)
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (activated != null ? !activated.equals(that.activated) : that.activated != null) return false;
        if (nationalId != null ? !nationalId.equals(that.nationalId) : that.nationalId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (activated != null ? activated.hashCode() : 0);
        result = 31 * result + (nationalId != null ? nationalId.hashCode() : 0);
        return result;
    }
}
