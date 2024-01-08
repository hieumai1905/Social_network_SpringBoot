package com.example.socialnetwork.models;

import com.example.socialnetwork.models.enums.RelationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "relations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id", nullable = false)
    private Long relationId;

    @Column(name = "type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private RelationType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_target_id")
    private User userTarget;

    public Relation(RelationType type, User user, User userTarget) {
        this.type = type;
        this.user = user;
        this.userTarget = userTarget;
    }
}
