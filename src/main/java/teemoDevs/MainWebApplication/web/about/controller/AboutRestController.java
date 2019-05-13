package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teemoDevs.MainWebApplication.web.about.model.Developer;
import teemoDevs.MainWebApplication.web.about.service.DeveloperService;

import java.util.List;

@RestController
@RequestMapping("/about")
public class AboutRestController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/developers/rest/add")
    public void developersRegisterAPI(@RequestBody List<Developer> developerList) {
        for (Developer developer : developerList)
            developerService.save(developer);
    }

}
