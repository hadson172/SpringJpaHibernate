package app.model;


import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Users")
public class User
{
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;


    @NotNull
    @Size(min = 4,max = 12)
    private String username;


    @Size(min = 4,max = 12)
    private String password;

    @Email
    private String email;


    private PhysicalDetails details;



    public User()
    {
        details = new PhysicalDetails();
    }

    public User(String firstName, String lastName, String username, String password, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        details = new PhysicalDetails();
    }


    public String getUsername()
    {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public PhysicalDetails getDetails() {
        return details;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    public void setDetails(PhysicalDetails details) {
        this.details = details;
    }
}
