package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teemoDevs.MainWebApplication.web.about.model.Article;
import teemoDevs.MainWebApplication.web.about.model.Developer;
import teemoDevs.MainWebApplication.web.about.service.DeveloperService;

import java.util.List;

@RestController
@RequestMapping("/about")
public class AboutRestController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/developers/rest/add")
    public void developersRegisterAPI(@RequestBody List<Developer> developerList) {
        for (Developer developer : developerList) {

            // 이 작업을 해주지 않으면 Article에서 참조하는 Developer_id의 값이 null이 됨
            for (Article article : developer.getArticleList())
                if ( article.getDeveloper() != developer )
                    article.setDeveloper(developer);

            developerService.save(developer);
        }
    }

}
