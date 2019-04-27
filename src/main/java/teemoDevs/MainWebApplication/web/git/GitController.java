package teemoDevs.MainWebApplication.web.git;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/git")
public class GitController {
    @GetMapping("")
    public String git1() {
        return "content/git/git1";
    }

    @GetMapping("/git2")
    public String git2() {
        return "content/git/git2";
    }

    @GetMapping("/git3")
    public String git3() {
        return "content/git/git3";
    }

    @GetMapping("/git4")
    public String git4() {
        return "content/git/git4";
    }

}
