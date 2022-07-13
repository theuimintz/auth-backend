package com.auth.dtos;


public class UserDTO {

    private Long id;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    private ProfileImageDTO profileImage;

    public ProfileImageDTO getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(ProfileImageDTO image) {
        this.profileImage = image;
    }


    private String username;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    private String firstName;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
        

    private String lastName;

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    private String email;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    } 


    private String telNumber;

    public String getTelNumber() {
        return telNumber;
    }
    public void setTelNumber(String num) {
        this.telNumber = num;
    }


    public UserDTO(Long id, ProfileImageDTO image, String username, String firstName, String lastName, String email, String telNumber) {
        this.id = id;
        this.profileImage = image;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNumber = telNumber;
    }

}
