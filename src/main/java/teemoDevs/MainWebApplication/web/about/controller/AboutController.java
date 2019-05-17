package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return VIEW_PATH + "teemodevs/home";
    }

    /** Projects **/
    @GetMapping("/projects")
    public String aboutProjects() {
        return VIEW_PATH + "projects/home";
    }

    /** Developers **/
    @GetMapping("/developers")
    public String aboutDevelopers(Model model) {
        model.addAttribute("developerList", developerService.findAll());
        return VIEW_PATH + "developers/home";
    }

    @GetMapping("/developers/add")
    public String addDevelopers(Model model) {
        model.addAttribute("developerList", developerService.findAll());
        return VIEW_PATH + "developers/home";
    }

    @PostMapping("/developers/add")
    public String addDevelopersPost(@RequestBody List<Developer> developerList) {
        developerService.deleteAll();
        for (Developer developer : developerList) {

            // 이 작업을 해주지 않으면 Article에서 참조하는 Developer_id의 값이 null이 됨
            for (Article article : developer.getArticleList())
                if ( article.getDeveloper() != developer )
                    article.setDeveloper(developer);

            developerService.save(developer);
        }

        return "redirect:/developers";
    }
}
