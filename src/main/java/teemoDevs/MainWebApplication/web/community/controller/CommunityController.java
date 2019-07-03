package teemoDevs.MainWebApplication.web.community.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import teemoDevs.MainWebApplication.web.community.model.Board;
import teemoDevs.MainWebApplication.web.community.model.CustomPageRequest;
import teemoDevs.MainWebApplication.web.community.service.BoardService;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/community")
public class CommunityController {

    private final static String VIEW_PATH = "content/community/";

    @Autowired
    private BoardService boardService;

    /**
     * 자유게시판
     * */
    @GetMapping("")
    public String communityFreeBoard(Model model, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        CustomPageRequest customPageRequest
                = new CustomPageRequest()
                        .setPage(pageNumber)
                        .setSize(pageSize)
                        .setDirection(Sort.Direction.DESC);

        model.addAttribute("boardList", boardService.findAll(customPageRequest.of()));

        // 보여줄 페이징 번호 최대 갯수
        model.addAttribute("pageBarSize", 5);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String value = objectMapper.writeValueAsString(boardService.findAll(customPageRequest.of()));
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return VIEW_PATH + "freeboard/home";
    }

    @GetMapping("/freeBoard/add")
    public String addBoard(Model model, Authentication authentication) {

        if (authentication == null)
            return "redirect:/oauth2/authorization/teemo";

        Board board = new Board();
        model.addAttribute("board", board);
        return VIEW_PATH + "freeboard/registerForm";
    }

    @PostMapping("/freeBoard/add")
    public String addBoardPost(@ModelAttribute Board board) {
        board.setAddDate(new Date());
        boardService.save(board);
        return "redirect:/community";
    }

}
