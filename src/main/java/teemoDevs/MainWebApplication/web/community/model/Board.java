package teemoDevs.MainWebApplication.web.community.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 커뮤니티 게시글 Model
 * @author yechanpark
 * */
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

    @Column(length = 100000)
    private String content; // 내용

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
    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JsonManagedReference
    private List<Reply> replyList = new ArrayList<>();

    public Board setAddDate(LocalDateTime localDateTime) {
        this.addDate = this.formattedLocalDateTime(localDateTime);
        this.setLastModifyDate(localDateTime);
        return this;
    }

    public String getAddDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(this.addDate);
    }

    public Board setLastModifyDate(LocalDateTime localDateTime) {
        this.lastModifyDate = this.formattedLocalDateTime(localDateTime);
        return this;
    }

    public String getLastModifyDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(this.lastModifyDate);
    }

    private LocalDateTime formattedLocalDateTime(LocalDateTime unFormattedLocalDateTime) {
        return LocalDateTime.parse(
                unFormattedLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }
}
