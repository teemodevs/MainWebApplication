package teemoDevs.MainWebApplication.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"", "/home"})
    public String home1() {
        return "content/home/home1";
    }

    @GetMapping("/home/home2")
    public String home2() {
        return "content/home/home2";
    }

    @GetMapping("/home/home3")
    public String home3() {
        return "content/home/home3";
    }

    @GetMapping("/home/home4")
    public String home4() {
        return "content/home/home4";
    }
}
