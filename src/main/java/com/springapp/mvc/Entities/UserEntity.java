package com.springapp.mvc.Entities;




import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name = "SSO_ID", unique = true, nullable = false, length = 30)
    private String ssoId;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 30)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "STATE", nullable = false, length = 100)
    private String state = State.ACTIVE.getState();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ROLES_ID", referencedColumnName = "ID")
    UserRoles role;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }


    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserEntity))
            return false;
        UserEntity other = (UserEntity) obj;
        if (id != other.id)
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", state=" + state + ", userRoles=" + role + "]";
    }


}
