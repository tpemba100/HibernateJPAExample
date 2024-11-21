package controller;

import jakarta.persistence.TypedQuery;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserController {

    // Main method - Entry point of the program
    public static void main(String[] args) {
        // 1. Create a SessionFactory object from Hibernate configuration file
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        // 2. Create a Session object for interacting with the database
        Session session = factory.openSession();

        // Uncomment below lines to call different methods based on the action needed
        // addUser(factory, session);
//         findUser(factory, session, 2);  // Find user with ID 2
        // updateUser(session, 3);         // Update user with ID 3
        // deleteUser(session, 4);         // Delete user with ID 4
//        findUserHql(factory,session);
//        getRecordById(factory,session);
//        getRecords(session);
//        getMaxSalary(session);
        getMaxSalaryGroupBy(session);
    }


    //using HQL(hibernate query language): Method to find a user by their ID
    public static void findUserHql(SessionFactory factory,Session session) {
        String hqlFrom = "FROM User"; // Example of HQL to get all records of user class
        String hqlSelect = "SELECT u FROM User u";
//        TypedQuery<User> query = session.createQuery(hqlFrom, User.class);
        TypedQuery<User> query = session.createQuery(hqlSelect, User.class);
        List<User> results = query.getResultList();
        System.out.printf("%s%13s%17s%34s%n","|User Id","|Full name","|Email","|Password");
        for (User u:results) {
            System.out.printf(" %-10d %-20s %-30s %s %n", u.getId(), u.getFullName(), u.getEmail(), u.getPassword());
        }
    }
    //HQL: WHERE Clause and Order by Clause
    public static void getRecordById(SessionFactory factory, Session session) {
        String hql = "FROM User u WHERE u.id > 2 ORDER BY u.salary DESC";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        List<User> results = query.getResultList();
        System.out.printf("%s%13s%17s%34s%21s%n", "|User Id", "|Full name", "|Email", "|Password", "|Salary");
        for (User u : results) {
            System.out.printf(" %-10d %-20s %-30s %-23s %s %n", u.getId(), u.getFullName(), u.getEmail(), u.getPassword(), u.getSalary());
        }
    }
//    HQL: Multiple SELECT Expressions
    public static void getRecords (Session session) {
        TypedQuery<Object[]> query = session.createQuery(
                "SELECT U.salary, U.fullName FROM User AS U", Object[].class);
        List<Object[]> results = query.getResultList();
        System.out.printf("%s%13s%n","Salary","City");
        for (Object[] a : results) {
            System.out.printf("%-16s%s%n",a[0],a[1]);
        }
    }
//    Aggregate Function: max, count
    public static void getMaxSalary(Session session) {
        String hql = "SELECT max(U.salary) FROM User U";
        TypedQuery<Object> query = session.createQuery(hql,Object.class);
        Object result = query.getSingleResult();
        System.out.printf("%s%s","Maximum Salary:",result);

//        String hqlCount = "SELECT COUNT(*) FROM User U";
//        List<Object> results = session.createQuery(hqlCount,Object.class).getResultList();
//        System.out.println("Count:"+results);
    }

//    GROUP BY Clause and Aggregate function
public static void getMaxSalaryGroupBy(Session session) {
    String hql = "SELECT SUM(U.salary), U.city FROM User U GROUP BY U.city";
    TypedQuery<Object[]> query = session.createQuery(hql);
    List<Object[]> result = query.getResultList();
    for (Object[] o : result) {
        System.out.println("Total salary " + o[0] + " | city: " + o[1]);
    }
}


    // Method to add multiple users to the database
    public static void addUser(SessionFactory factory, Session session) {
        // 1. Begin a transaction (required for database operations)
        Transaction transaction = session.beginTransaction();

        // 2. Create new User objects
        User uOne = new User();
        uOne.setEmail("haseeb@gmail.com");
        uOne.setFullName("Moh Haseeb");
        uOne.setPassword("has123");
        uOne.setSalary(2000.69);
        uOne.setAge(20);
        uOne.setCity("NYC");

        User uTwo = new User();
        uTwo.setEmail("James@gmail.com");
        uTwo.setFullName("James Santana");
        uTwo.setPassword("James123");
        uTwo.setSalary(2060.69);
        uTwo.setAge(25);
        uTwo.setCity("Dallas");

        // 3. Creating users using the constructor
        User uFour = new User("Christ", "christ@gmail.com", "147852", 35, 35000.3, "NJ");
        User uFive = new User("Sid", "Sid", "s258", 29, 4000.36, "LA");

        // 4. Persisting (saving) the User objects into the database
        session.persist(uOne);
        session.persist(uTwo);
        session.persist(uFour);
        session.persist(uFive);

        // 5. Commit the transaction to save the data to the database
        transaction.commit();

        // 6. Output message to confirm successful saving
        System.out.println("Successfully saved");

        // 7. Close the Session and SessionFactory to release resources
        factory.close();
        session.close();
    }

    // Method to find a user by their ID
    public static void findUser(SessionFactory factory, Session session, int userId) {
        // 1. Begin a transaction
        Transaction tx = session.beginTransaction();

        // 2. Retrieve a User object using the session's get() method, specifying the class and userId
        User u = session.get(User.class, userId);

        // 3. Display the user's details
        if (u != null) {
            System.out.println("FullName: " + u.getFullName());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Password: " + u.getPassword());
        } else {
            System.out.println("User not found");
        }

        // 4. Commit the transaction to complete the operation
        tx.commit();

        // 5. Close the session and session factory
        factory.close();
        session.close();
    }

    // Method to update a user’s information
    public static void updateUser(Session session, int userId) {
        // 1. Begin a transaction
        Transaction tx = session.beginTransaction();

        // 2. Create a User object and set its ID and updated fields
        User u = new User();
        u.setId(userId); // Setting the ID of the user to be updated
        u.setEmail("mhaseeb@perscholas");
        u.setFullName("M Haseeb");
        u.setPassword("123456");

        // 3. Merge the updated user object to apply the changes in the database
        session.merge(u);  // merge() is used instead of update() for partial updates

        // 4. Commit the transaction to save changes
        session.getTransaction().commit();

        // 5. Close the session
        session.close();
    }

    // Method to delete a user from the database
    public static void deleteUser(Session session, int userId) {
        // 1. Begin a transaction
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Transaction tx = session.beginTransaction();

        // 2. Create a User object with the given ID to be deleted
        User u = new User();
        u.setId(userId);

        // 3. Remove the user from the database
        session.remove(u);

        // 4. Commit the transaction to delete the user
        tx.commit();

        // 5. Close the session and session factory
        session.close();
        factory.close();
    }
}
