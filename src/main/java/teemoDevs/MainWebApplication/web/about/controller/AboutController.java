package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import teemoDevs.MainWebApplication.web.about.service.DeveloperService;

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
}
