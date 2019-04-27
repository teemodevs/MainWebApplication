package teemoDevs.MainWebApplication.web.projects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectsController {
    @GetMapping("")
    public String projects1() {
        return "content/projects/projects1";
    }

    @GetMapping("/projects2")
    public String projects2() {
        return "content/projects/projects2";
    }

    @GetMapping("/projects3")
    public String projects3() {
        return "content/projects/projects3";
    }

    @GetMapping("/projects4")
    public String projects4() {
        return "content/projects/projects4";
    }
}
