package teemoDevs.MainWebApplication.web.community.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String author;  // 작성자

    @Column(length = 40000)
    private String content; // 내용

    private Date addDate;        // 작성시간
    private Date lastModifyDate; // 최종 수정 시간

    /**
     * {@link OneToMany}            : 현재 클래스(Board)와 상대 클래스(Reply)가 1:N일 때 선언.
     *                                mappedBy="board"는 상대 클래스(Reply)에서 현재 클래스(Board)를 참조하는 변수의 이름.
     * {@link JsonManagedReference} : JSON Serialize 시 상대 클래스(Reply)의 정보를 Serialize한다.
     *                                상대 클래스(Reply)에서는 board 변수에 {@link JsonBackReference} 를 붙여 현재 클래스(Board)의 정보를 Serialize하지 않게 해야 한다.
     *                                둘 다 Infinite Recursion 오류를 해결하는데 사용한다.
     * */
    @OneToMany(mappedBy = "board", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private List<Reply> replyList = new ArrayList<>();
}
