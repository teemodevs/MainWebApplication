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

}
