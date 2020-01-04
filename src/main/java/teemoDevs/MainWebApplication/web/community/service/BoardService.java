package teemoDevs.MainWebApplication.web.community.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import teemoDevs.MainWebApplication.web.community.model.Board;

public interface BoardService {
    void save(Board board);

    Page<Board> findAll(PageRequest pageRequest);
}
