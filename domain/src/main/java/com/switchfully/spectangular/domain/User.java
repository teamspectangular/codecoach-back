package com.switchfully.spectangular.domain;

import com.switchfully.spectangular.exceptions.InvalidEmailException;
import com.switchfully.spectangular.exceptions.InvalidPasswordException;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app_user")
public class User {
    public static final int MAX_COACH_TOPICS = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String encryptedPassword;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "availability")
    private String availability;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "img_url")
    private String imageUrl;


    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_topics", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_name"))
    private List<Topic> topicList;

    @Column(name = "reset_token")
    private String resetToken;


    public User() {
    }

    public User(String firstName, String lastName, String profileName, String email, String encryptedPassword,
                Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileName = profileName;
        this.email = validEmail(email);
        this.encryptedPassword = encryptedPassword;
        this.role = role;
    }

    public String validEmail(String emailAddress) {
        if (!EmailValidator.getInstance().isValid(emailAddress)) {
            throw new InvalidEmailException("user has invalid email address");
        }
        return emailAddress;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Role getRole() {
        return role;
    }

    public String getAvailability() {
        return availability;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public User setAvailability(String availability) {
        this.availability = availability;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public User setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public User setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public User setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setProfileName(String profileName) {
        this.profileName = profileName;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void becomeCoach() {
        this.role = Role.COACH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
