package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

    private final static String VIEW_PATH = "content/about/";

    @GetMapping("")
    public String aboutTeemoDevs() {
        return VIEW_PATH + "teemodevs/home";
    }

    /** Projects 홈 **/
    @GetMapping("/projects")
    public String aboutProjects() {
        return VIEW_PATH + "projects/home";
    }

    /** Developers 홈 **/
    @GetMapping("/developers")
    public String aboutDevelopers() {
        return VIEW_PATH + "developers/home";
    }

}
