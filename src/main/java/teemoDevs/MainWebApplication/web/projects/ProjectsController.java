package teemoDevs.MainWebApplication.web.projects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private final static String VIEW_PATH = "content/projects/";

    @GetMapping("")
    public String projects1() {
        return VIEW_PATH + "projects1";
    }

    @GetMapping("/projects2")
    public String projects2() {
        return VIEW_PATH + "projects2";
    }

    @GetMapping("/projects3")
    public String projects3() {
        return VIEW_PATH + "projects3";
    }

    @GetMapping("/projects4")
    public String projects4() {
        return VIEW_PATH + "projects4";
    }
}
