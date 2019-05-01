package teemoDevs.MainWebApplication.web.about.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teemoDevs.MainWebApplication.web.about.model.Developer;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
