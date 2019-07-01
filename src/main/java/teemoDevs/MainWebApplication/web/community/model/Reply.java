package teemoDevs.MainWebApplication.web.community.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Reply {
    @Id
    @GeneratedValue
    @Column(name="REPLY_ID")
    private Long id;

    private String username; // 작성한 유저 이름
    private String content;  // 댓글 내용

    /**
     * {@link JoinColumn}의 name 값은 현재 클래스가 DB ROW에 저장될 때, 상대 클래스에 대한 외래키 값을 포함하는 컬럼명
     * 상대 클래스에 대한 외래키 값을 포함하는 컬럼명이기 때문에 상대 클래스의 정보가 기술되어야 한다.
     * */
    @ManyToOne
    @JoinColumn(name="BOARD_ID")
    private Board board;

    public void setBoard(Board board) {
        this.board = board;

        // 무한루프에 빠지지 않도록 체크
        if ( !board.getReplyList().contains(this) )
            board.getReplyList().add(this);
    }
}
