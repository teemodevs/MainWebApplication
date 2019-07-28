package teemoDevs.MainWebApplication.web.community.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reply {
    @Id
    @GeneratedValue
    @Column(name="REPLY_ID")
    private Long id;

    private String author;  // 작성한 유저 이름
    private String content; // 댓글 내용

    /**
     * {@link JoinColumn}        : name 값은 현재 클래스가 DB ROW에 저장될 때, 상대 클래스에 대한 외래키 값을 포함하는 컬럼명
     *                             상대 클래스에 대한 외래키 값을 포함하는 컬럼명이기 때문에 상대 클래스의 정보가 기술되어야 한다.
     * {@link ManyToOne}         : 현재 클래스(Reply)와 상대 클래스(Baord)가 N:1일 때 선언.
     * {@link JsonBackReference} : JSON Serialize 시 상대 클래스(Board)의 정보를 Serialize 하지 않는다.
     *                             (참고 : {@link JsonManagedReference} )
     * */
    @ManyToOne
    @JoinColumn(name="BOARD_ID")
    @JsonBackReference
    private Board board;

    // 대댓글 리스트
    private List<Reply> replyList = new ArrayList<>();
}
