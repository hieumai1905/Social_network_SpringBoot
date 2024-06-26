package com.example.socialnetwork.models;

import com.example.socialnetwork.models.key.UserTagId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_tags")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserTag {
    @EmbeddedId
    private UserTagId userTagId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
