package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import teemoDevs.MainWebApplication.web.about.model.Article;
import teemoDevs.MainWebApplication.web.about.model.Developer;
import teemoDevs.MainWebApplication.web.about.service.DeveloperService;

import java.util.List;

@Controller
@RequestMapping("/about")
public class AboutController {

    private final static String VIEW_PATH = "content/about/";

    @Autowired
    private DeveloperService developerService;

    @GetMapping("")
    public String aboutTeemoDevs() {
        return VIEW_PATH + "about_teemodevs";
    }

    @GetMapping("/aboutProjects")
    public String aboutProjects() {
        return VIEW_PATH + "about_projects";
    }

    @GetMapping("/aboutDevelopers")
    public String aboutDevelopers(Model model) {
        Developer developer1 = new Developer();
        developer1.setName("박예찬");
        developer1.setNickname("yechanpark");
        developer1.setImageURI("developer_yechanpark.jpg");

        Article article1 = new Article();
        article1.setSubject("아티클1 서브젝트");
        article1.addContent("아티클1 컨텐트1");
        article1.addContent("아티클1 컨텐트2");
        article1.addContent("아티클1 컨텐트3");
        article1.setDeveloper(developer1);

        Article article2 = new Article();
        article2.setSubject("아티클2 서브젝트");
        article2.addContent("아티클2 컨텐트1");
        article2.addContent("아티클2 컨텐트2");
        article2.addContent("아티클2 컨텐트3");
        article2.setDeveloper(developer1);

        Article article3 = new Article();
        article3.setSubject("아티클3 서브젝트");
        article3.addContent("아티클3 컨텐트1");
        article3.addContent("아티클3 컨텐트2");
        article3.setDeveloper(developer1);


        Developer developer2 = new Developer();
        developer2.setName("정동교");
        developer2.setNickname("ehdrylang");
        developer2.setImageURI("developer_ehdrylang.jpg");

        Article article4 = new Article();
        article4.setSubject("아티클4 서브젝트");
        article4.addContent("아티클4 컨텐트1");
        article4.addContent("아티클4 컨텐트2");
        article4.setDeveloper(developer2);

        Article article5 = new Article();
        article5.setSubject("아티클5 서브젝트");
        article5.addContent("아티클5 컨텐트1");
        article5.addContent("아티클5 컨텐트2");
        article5.addContent("아티클5 컨텐트3");
        article5.addContent("아티클5 컨텐트4");
        article5.setDeveloper(developer2);

        Article article6 = new Article();
        article6.setSubject("아티클6 서브젝트");
        article6.addContent("아티클6 컨텐트1");
        article6.addContent("아티클6 컨텐트2");
        article6.addContent("아티클6 컨텐트3");
        article6.addContent("아티클6 컨텐트4");
        article6.setDeveloper(developer2);


        /**
         * Article.setDeveloper 내부에서 이미 Developer 객체에 Article을 추가하므로 아래 코드는 실행할 필요 없음
         * 아래 코드를 수행하면 Developer에 Article이 중복으로 2번 저장됨
         * developer.addArticle(article1);
         * developer.addArticle(article2);
         * developer.addArticle(article3);
         * */

        developerService.save(developer1);
        developerService.save(developer2);

        List<Developer> developerList = developerService.findAll();

        model.addAttribute("developerList", developerList);
        return VIEW_PATH + "about_developers";
    }
}
