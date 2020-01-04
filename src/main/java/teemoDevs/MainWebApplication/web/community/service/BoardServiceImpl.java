package teemoDevs.MainWebApplication.web.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teemoDevs.MainWebApplication.web.community.model.Board;
import teemoDevs.MainWebApplication.web.community.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void save(Board board) {
        this.boardRepository.save(board);
    }

    public Page<Board> findAll(PageRequest pageRequest) {
        return this.boardRepository.findAll(pageRequest);
    }

}
