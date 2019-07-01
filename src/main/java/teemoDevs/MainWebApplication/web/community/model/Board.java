package teemoDevs.MainWebApplication.web.community.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue
    @Column(name="BOARD_ID")
    private Integer id;

    private String subject; // 제목

    @Column(length = 40000)
    private String content; // 내용

    private Date addDate;        // 작성시간
    private Date lastModifyDate; // 최종 수정 시간

    @OneToMany(mappedBy = "board", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Reply> replyList = new ArrayList<>();

    public void addReply(Reply reply) {
        this.replyList.add(reply);

        // 무한루프에 빠지지 않도록 체크
        if ( reply.getBoard() != this )
            reply.setBoard(this);
    }
}
