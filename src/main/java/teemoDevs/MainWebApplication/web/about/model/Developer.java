package teemoDevs.MainWebApplication.web.about.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Developer {
    @Id
    @GeneratedValue
    @Column(name="DEVELOPER_ID")
    private Long id;

    private String nickname; // 닉네임
    private String name;     // 이름(실명)
    private String imageURI; // 이미지 URI

    @Transient // db에 저장하지 않는다.
    private MultipartFile productImage;

    /**
     * mappedBy의 값은 상대 클래스에서 참조하는 현재 클래스의 변수명과 같아야 함
     * 상대 클래스에서 참조하는 현재 클래스의 변수명이 들어가기 때문에 현재 클래스의 정보가 들어가야 함
     * class Article {
     *      ...
     *      private Developer developer; // 여기의 developer
     * }
     *
     * {@link CascadeType.PERSIST}는 A를 저장할 때, 연관된 B의 리스트를 전부 저장하도록 지정
     * {@link CascadeType.REMOVE}는 A를 삭제할 때, 연관된 B의 리스트를 전부 삭제하도록 지정
     * */
    @OneToMany(mappedBy = "developer", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Article> articleList = new ArrayList<>();

    public void addArticle(Article article) {
        this.articleList.add(article);

        // 무한루프에 빠지지 않도록 체크
        if ( article.getDeveloper() != this )
            article.setDeveloper(this);
    }

}
