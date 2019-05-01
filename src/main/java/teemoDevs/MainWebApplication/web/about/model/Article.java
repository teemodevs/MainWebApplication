package teemoDevs.MainWebApplication.web.about.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue
    @Column(name="ARTICLE_ID")
    private Long id;

    private String subject;
    private String contents;

    /**
     * @JoinColumn의 name 값은 현재 클래스가 DB ROW에 저장될 때, 상대 클래스에 대한 외래키 값을 포함하는 컬럼명
     * 상대 클래스에 대한 외래키 값을 포함하는 컬럼명이기 때문에 상대 클래스의 정보가 기술되어야 한다.
     * */
    @ManyToOne
    @JoinColumn(name="DEVELOPER_ID")
    private Developer developer;

    public void setDeveloper(Developer developer) {
        this.developer = developer;

        // 무한루프에 빠지지 않도록 체크
        if ( !developer.getArticleList().contains(this) )
            developer.getArticleList().add(this);
    }

}
