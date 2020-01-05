package teemoDevs.MainWebApplication.web.community.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 커뮤니티 게시글 Model
 *
 * @author yechanpark
 */
@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Integer id;

    private String subject; // 제목
    private String author;  // 작성자

    /**
     * Java에서 String 타입 저장 시 DB에 저장되는 필드의 타입
     * Default : MySQL - VARCHAR(255), H2 - VARCHAR(255)
     * {@link Lob} : MySQL - Longtext,  H2 - CLOB
     * {@link Column}(columnDefinition = "text") : MySQL - TEXT, H2 : TEXT
     *
     * 더 큰 문자열을 저장하려면 Annotation을 사용해 별도의 타입을 지정해야 함
     */
    @Column(columnDefinition = "TEXT") // 또는 @Lob
    private String content; // 내용

    /**
     * Java에서 LocalDateTime 타입 저장 시 DB에 저장되는 필드의 타입
     * Default : MySQL - datetime(6), H2 - TIMESTAMP
     */
    private LocalDateTime addDate;        // 작성시간
    private LocalDateTime lastModifyDate; // 최종 수정 시간

    /**
     * {@link OneToMany}            : 현재 클래스(Board)와 상대 클래스(Reply)가 1:N일 때 선언.
     * mappedBy="board"는 상대 클래스(Reply)에서 현재 클래스(Board)를 참조하는 변수의 이름.
     * {@link JsonManagedReference} : JSON Serialize 시 상대 클래스(Reply)의 정보를 Serialize한다.
     * 상대 클래스(Reply)에서는 board 변수에 {@link JsonBackReference} 를 붙여 현재 클래스(Board)의 정보를 Serialize하지 않게 해야 한다.
     * 둘 다 Infinite Recursion 오류를 해결하는데 사용한다.
     * {@link CascadeType.PERSIST} : Board 저장 시 Reply도 같이 저장
     * {@link CascadeType.REMOVE} : Board 삭제 시 Reply도 같이 삭제
     * {@link CascadeType.MERGE} : Board 업데이트 시 Reply도 같이 업데이트
     */
    @OneToMany(
            mappedBy = "board",
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE },
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Reply> replyList = new ArrayList<>();

    public Board setAddDate(LocalDateTime localDateTime) {
        this.addDate = localDateTime;
        this.setLastModifyDate(localDateTime);
        return this;
    }

    public Board setLastModifyDate(LocalDateTime localDateTime) {
        this.lastModifyDate = localDateTime;
        return this;
    }

}
