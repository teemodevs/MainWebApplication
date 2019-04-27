package teemoDevs.MainWebApplication.web.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {
    @GetMapping("")
    public String about1() {
        return "content/about/about1";
    }

    @GetMapping("/about2")
    public String about2() {
        return "content/about/about2";
    }

    @GetMapping("/about3")
    public String about3() {
        return "content/about/about3";
    }

    @GetMapping("/about4")
    public String about4() {
        return "content/about/about4";
    }
}
