package com.example.socialnetwork.models;

import com.example.socialnetwork.models.enums.Gender;
import com.example.socialnetwork.models.enums.RoleUser;
import com.example.socialnetwork.models.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;

    private String avatar;

    private String coverPhoto;

    @Column(name = "country")
    private String country;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleUser userRole;

    @Column(name = "about_me", length = 1000)
    private String aboutMe;

    @Column(name = "register_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.PostInteract> postInteracts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.CommentReply> commentReplies;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.Conversation> conversations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.UserHobbies> userHobbies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.UserTag> userTags;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.Album> albums;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.example.socialnetwork.models.Participant> participants;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Relation> relations;

    @PrePersist
    public void prePersist() {
        this.avatar = "/files-upload/images/default-avatar.png";
        this.coverPhoto = "/files-upload/images/default-cover-photo.jpg";
        this.registerAt = new Date();
        this.userRole = RoleUser.ROLE_USER;
        this.status = UserStatus.INACTIVE;
    }
}
