package teemoDevs.MainWebApplication.web.about.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teemoDevs.MainWebApplication.web.about.service.DeveloperService;

@RestController
@RequestMapping("/about")
public class AboutRestController {

    @Autowired
    private DeveloperService developerService;

    @DeleteMapping("/developers/delete/{developerId}")
    public ResponseEntity<?> deleteDeveloper(@PathVariable Long developerId) {
        developerService.deleteById(developerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
