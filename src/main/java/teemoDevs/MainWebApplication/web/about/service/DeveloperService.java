package teemoDevs.MainWebApplication.web.about.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teemoDevs.MainWebApplication.web.about.model.Developer;
import teemoDevs.MainWebApplication.web.about.repository.DeveloperRepository;

import java.util.List;

@Service
public class DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;

    public void save(Developer developer) {
        developerRepository.save(developer);
    }

    public void deleteAll() {
        developerRepository.deleteAll();
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }
}
