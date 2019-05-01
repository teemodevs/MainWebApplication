package teemoDevs.MainWebApplication.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final static String VIEW_PATH = "content/home/";

    @GetMapping(value = {"", "/home"})
    public String home1() {
        return VIEW_PATH + "home1";
    }

    @GetMapping("/home/home2")
    public String home2() {
        return VIEW_PATH + "home2";
    }

    @GetMapping("/home/home3")
    public String home3() {
        return VIEW_PATH + "home3";
    }

    @GetMapping("/home/home4")
    public String home4() {
        return VIEW_PATH + "home4";
    }
}
