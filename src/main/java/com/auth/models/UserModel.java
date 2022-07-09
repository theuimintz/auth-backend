package com.auth.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {

    // region Properties

    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    private Long id;

    public Long getId() { return id; }

    
    @Column ( nullable = false, unique = true )
    private String username;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }


    @Column ( nullable = false )
    private String firstName;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    @Column ( nullable = false )
    private String lastName;

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Column ( nullable = false, unique = true )
    private String telNumber;

    public String getTelNumber() { return telNumber; }
    public void setTelNumber(String telNumber) { this.telNumber = telNumber; }

    @Column ( nullable = false, unique = true )
    private String email;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Column ( nullable = false )
    private String password;

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; } 

    // endregion
    
    // region Construction

    public UserModel() {
        username = "";
        firstName = "";
        lastName = "";
        telNumber = "";
        email = "";
        password = "";
    }

    public UserModel( String username, String firstName, String lastName, String telNumber, String email, String password ) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.email = email;
        this.password = password;
    }

    // endregion
    
}   
