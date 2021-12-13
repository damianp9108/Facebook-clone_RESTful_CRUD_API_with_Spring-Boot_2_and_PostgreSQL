package facebookapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private int postID;

    @ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.ALL,
    @JoinColumn(name = "userId", referencedColumnName = "userID")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(name = "description")
    private String description;

    @Column(name = "postImgURL")
    private String postImgURL;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

}
