package teemoDevs.MainWebApplication.web.git;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/git")
public class GitController {

    private final static String VIEW_PATH = "content/git/";

    @GetMapping("")
    public String git1() {
        return VIEW_PATH + "git1";
    }

    @GetMapping("/git2")
    public String git2() {
        return VIEW_PATH + "git2";
    }

    @GetMapping("/git3")
    public String git3() {
        return VIEW_PATH + "git3";
    }

    @GetMapping("/git4")
    public String git4() {
        return VIEW_PATH + "git4";
    }

}
