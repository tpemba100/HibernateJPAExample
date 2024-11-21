# User Management Application

This application is a basic **CRUD** (Create, Read, Update, Delete) system for managing **User** data in a database using **Hibernate ORM**. It allows you to perform user management operations such as adding, retrieving, updating, and deleting users stored in a relational database.

---

## **Features**

- **Create Users**: Add multiple users to the database with predefined data.
- **Read Users**: Retrieve user details by user ID.
- **Update Users**: Modify user information (e.g., email, full name, etc.).
- **Delete Users**: Remove a user from the database by ID.

---

## **Technologies Used**

- **Hibernate ORM**: Object-relational mapping framework for Java.
- **JPA (Java Persistence API)**: Used for entity mapping and database interaction.
- **SQL Database**: A relational database to store user data (e.g., MySQL, PostgreSQL).

---

## **How It Works**

### **1. User Entity (Model)**

The `User` class represents a user entity mapped to a **USER** table in the database. It uses Hibernate annotations to define the entity and the primary key:

- **Annotations**:
  - `@Entity`: Marks the class as a persistent entity.
  - `@Table(name = "USER")`: Specifies the table name.
  - `@Id`: Defines the primary key.
  - `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Auto-generates the primary key value (auto-increment).

- **Fields**:
  - `id`: The user’s unique identifier (primary key).
  - `fullName`, `email`, `password`, `age`, `salary`, `city`: User details.

- **Constructors**:
  - Default constructor (required by JPA).
  - Parameterized constructor for initializing a `User` with all fields.

---

### **2. UserController (Controller)**

The `UserController` class is responsible for performing CRUD operations using Hibernate's **Session** and **Transaction** mechanisms:

- **CRUD Operations**:
  - **addUser**: Adds multiple users to the database.
  - **findUser**: Retrieves a user by their `userId` and prints their details.
  - **updateUser**: Updates an existing user’s information by their `userId`.
  - **deleteUser**: Deletes a user from the database by their `userId`.

- **Session Management**:
  - **SessionFactory**: Configures and creates a session to interact with the database.
  - **Session**: Used to execute database operations like save, find, update, and delete.
  - **Transaction**: Ensures operations are executed atomically.

---

## **Example Usage**

1. **Add Users**:
   - Add multiple users by calling `addUser()` in the `UserController`.
   
2. **Find a User**:
   - Find a user by calling `findUser()` with a user ID.
   
3. **Update a User**:
   - Update a user by calling `updateUser()` with a user ID.
   
4. **Delete a User**:
   - Delete a user by calling `deleteUser()` with a user ID.

---

## **Setup & Requirements**

1. **Database Configuration**: 
   - Make sure your database is set up and connected properly. You may need to configure the `hibernate.cfg.xml` file with database credentials (username, password, URL).
   
2. **JDK 11+**: The application is built with Java.

3. **Hibernate Dependencies**: 
   - The required Hibernate and JPA dependencies should be included in your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

---
