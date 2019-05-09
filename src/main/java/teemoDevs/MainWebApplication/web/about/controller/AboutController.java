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

    @GetMapping("/developers/register")
    public String developersRegister(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("developer", new Developer());
        return VIEW_PATH + "developers/registerDeveloperForm";
    }

    @PostMapping("/developers/register")
    public String developersRegisterPost(@RequestBody Developer developer,
                                         @RequestBody List<Article>  articleList) {

        for (Article article : articleList)
            article.setDeveloper(developer);

        developerService.save(developer);

        return "redirect:/about/developers";
    }

    @GetMapping("/developers/edit")
    public void developersEdit(Model model) {
        System.out.println("This is /developers/edit GET Controller");
    }

}
