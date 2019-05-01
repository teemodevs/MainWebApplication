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
        Developer developer = new Developer();
        developer.setName("박예찬");
        developer.setNickname("yechanpark");
        developer.setImageURI("/img/developers/developer_yechanpark.jpg");

        Article article1 = new Article();
        article1.setSubject("아티클1 서브젝트");
        article1.setContents("아티클1 컨텐트");
        article1.setDeveloper(developer);

        Article article2 = new Article();
        article2.setSubject("아티클2 서브젝트");
        article2.setContents("아티클2 컨텐트");
        article2.setDeveloper(developer);

        Article article3 = new Article();
        article3.setSubject("아티클3 서브젝트");
        article3.setContents("아티클3 컨텐트");
        article3.setDeveloper(developer);

        developer.addArticle(article1);
        developer.addArticle(article2);
        developer.addArticle(article3);

        developerService.save(developer);

        List<Developer> developerList = developerService.findAll();
        model.addAttribute("developerList", developerList);
        return VIEW_PATH + "about_developers";
    }
}
