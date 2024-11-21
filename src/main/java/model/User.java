// This is the package declaration where the User class is located
package model;

// Importing necessary JPA annotations for entity mapping
import jakarta.persistence.*;

// @Entity annotation tells Hibernate that this class is a persistent entity and will be mapped to a table in the database.
@Entity

// @Table annotation allows you to specify the name of the table in the database.
// In this case, the table name will be "USER".
@Table(name = "USER")
public class User {

    // @Column annotation specifies the name of the column in the database.
    // @Id is the primary key for this entity.
    // @GeneratedValue is used to automatically generate the primary key value for new entities.
    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This will use auto-increment for the primary key.
    private Integer id;

    // These fields represent user details such as full name, email, password, age, salary, and city.
    private String fullName;
    private String email;
    private String password;
    private int age;
    private double salary;
    private String city;

    // Constructor that allows you to create a User object with all fields populated.
    public User(String fullName, String email, String password, int age, double salary, String city) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.salary = salary;
        this.city = city;
    }

    // Default constructor, which is required by JPA for entity creation
    public User() {
    }

    // Getter and Setter methods for each field in the User entity.
    // These methods are used to retrieve and update the values of the entity's fields.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
