package teemoDevs.MainWebApplication.web.git;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.extras.springsecurity5.auth.AuthUtils;

@Controller
@RequestMapping("/git")
public class GitController {

    private final static String VIEW_PATH = "content/git/";

    @GetMapping("")
    public String git1() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated());
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getDetails());
        System.out.println(auth.getCredentials());
        System.out.println(auth.getAuthorities());

        Object authenticationProperty = AuthUtils.getAuthenticationProperty(auth, "principal.attributes['authorities']");
        System.out.println(authenticationProperty.toString());

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
