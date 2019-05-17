package teemoDevs.MainWebApplication.web.about.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import teemoDevs.MainWebApplication.web.about.model.Developer;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeveloperCreationDto {
    private List<Developer> developerList;

    public void addDeveloper(Developer developer) {
        this.developerList.add(developer);
    }
}
