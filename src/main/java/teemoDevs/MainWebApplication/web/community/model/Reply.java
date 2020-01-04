package teemoDevs.MainWebApplication.web.community.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import teemoDevs.MainWebApplication.util.LocalDateTimeFormatter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reply {
    @Id
    @GeneratedValue
    @Column(name = "REPLY_ID")
    private Long id;        // 댓글 id

    private String author;  // 작성한 유저 이름

    @Column(length = 40000)
    private String content; // 댓글 내용

    private LocalDateTime addDate;        // 작성시간
    private LocalDateTime lastModifyDate; // 최종 수정 시간

    /**
     * {@link JoinColumn}        : name 값은 현재 클래스가 DB ROW에 저장될 때, 상대 클래스에 대한 외래키 값을 포함하는 컬럼명
     * 상대 클래스에 대한 외래키 값을 포함하는 컬럼명이기 때문에 상대 클래스의 정보가 기술되어야 한다.
     * {@link ManyToOne}         : 현재 클래스(Reply)와 상대 클래스(Baord)가 N:1일 때 선언.
     * {@link JsonBackReference} : JSON Serialize 시 상대 클래스(Board)의 정보를 Serialize 하지 않는다.
     * (참고 : {@link JsonManagedReference} )
     */
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    @JsonBackReference
    private Board board;

    // 부모 댓글
    @ManyToOne
    @JsonBackReference
    private Reply parent;

    // 자식 댓글 리스트
    @OneToMany(
            mappedBy = "parent",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JsonManagedReference
    private List<Reply> children = new ArrayList<>();

    /**
     * 최초 생성 시간 설정.
     */
    public Reply setAddDate(LocalDateTime localDateTime) {
        this.addDate = LocalDateTimeFormatter.format(localDateTime);
        this.setLastModifyDate(localDateTime);
        return this;
    }

    /**
     * 최종 수정 시간 설정. 클래스 외부, 내부 모두 사용됨
     */
    public Reply setLastModifyDate(LocalDateTime localDateTime) {
        this.lastModifyDate = LocalDateTimeFormatter.format(localDateTime);
        return this;
    }

}
