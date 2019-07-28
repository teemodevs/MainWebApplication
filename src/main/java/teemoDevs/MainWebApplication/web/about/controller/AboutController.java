package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import teemoDevs.MainWebApplication.web.about.model.Article;
import teemoDevs.MainWebApplication.web.about.model.Developer;
import teemoDevs.MainWebApplication.web.about.service.DeveloperService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/about")
public class AboutController {

    private final static String VIEW_PATH = "content/about/";

    @Autowired
    private DeveloperService developerService;

    @GetMapping("")
    public String aboutTeemoDevs() {
        return VIEW_PATH + "teemodevs/home";
    }

    /** Projects 홈 **/
    @GetMapping("/projects")
    public String aboutProjects() {
        return VIEW_PATH + "projects/home";
    }

    /** Developers 홈 **/
    @GetMapping("/developers")
    public String aboutDevelopers(Model model) {
        model.addAttribute("developerList", developerService.findAll());
        return VIEW_PATH + "developers/home";
    }

    /**
     * 개발자 추가 폼 입력 페이지로 이동
     * */
    @GetMapping("/developers/add")
    public String addDevelopers(Model model) {
        // 객체를 만들어서 넘겨주지 않고 thymeleaf에서 th:object 내의 th:field를 선언하면
        // org.thymeleaf.spring5.processor.SpringInputGeneralFieldTagProcessor 이 발생
        Developer developer = new Developer();
        model.addAttribute("developer", developer);
        return VIEW_PATH + "developers/registerForm";
    }

    /**
     * 개발자 추가
     * */
    // @RequestBody : multipart/form-data는 json 포맷으로 들어오지 않으므로 사용 불가. @ModelAttribute를 써야 함
    @PostMapping(path = "/developers/add", consumes = {"multipart/form-data"})
    public String addDevelopersPost(@ModelAttribute Developer developer, HttpServletRequest request) {

        /** 이미지 업로드 시작 **/
        // 실제 파일명(확장자명 포함) : "teemo.jpg"
        String imageFilename = developer.getProductImage().getOriginalFilename();

        // 루트 디렉토리를 가져옴 : 배포 시에 사용 경로가 모두 다르기 때문
        String PATH = request.getSession().getServletContext().getRealPath("/");

        File directory = new File(PATH);
        System.out.println(directory.toString());

        if (! directory.exists()) {
            // make the entire directory path including parents
            directory.mkdirs();
        }

        try {
            developer.getProductImage().transferTo(new File(PATH + imageFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 이미지 업로드 끝 **/

        developer.setImageURI(imageFilename);

        // 이 작업을 해주지 않으면 ARTICLE 테이블의 DEVELOPER_ID의 값이 null이 됨
        for (Article article : developer.getArticleList())
            if ( article.getDeveloper() != developer )
                article.setDeveloper(developer);

        developerService.save(developer);

        return "redirect:/about/developers";
    }
}
