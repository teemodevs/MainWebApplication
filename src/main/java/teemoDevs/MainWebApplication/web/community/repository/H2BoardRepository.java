package teemoDevs.MainWebApplication.web.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import teemoDevs.MainWebApplication.web.community.model.Board;

/**
 * {@link JpaRepository}는 {@link PagingAndSortingRepository} 인터페이스를 상속받는다.
 * H2 DB를 사용할 때는 이 인터페이스를 사용한다.
 */
@Repository
public interface H2BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findAll(Pageable pageable);
}
