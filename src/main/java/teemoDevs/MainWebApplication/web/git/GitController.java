package teemoDevs.MainWebApplication.web.git;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/git")
public class GitController {

    private final static String VIEW_PATH = "content/git/";

    @GetMapping("")
    public String teemoDevsGit() {
        return VIEW_PATH + "teemoDevs";
    }

    @GetMapping("/yechanpark")
    public String yechanparkGit() {
        return VIEW_PATH + "yechanpark";
    }

    @GetMapping("/ehdrylang")
    public String ehdrylangGit() {
        return VIEW_PATH + "ehdrylang";
    }

    @GetMapping("/developer-do")
    public String developerDoGit() {
        return VIEW_PATH + "developer_do";
    }

}
