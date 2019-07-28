package teemoDevs.MainWebApplication.web.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import teemoDevs.MainWebApplication.web.community.model.Board;
import teemoDevs.MainWebApplication.web.community.model.CustomPageRequest;
import teemoDevs.MainWebApplication.web.community.model.Reply;
import teemoDevs.MainWebApplication.web.community.service.BoardService;

import java.security.Principal;
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
    public String communityFreeBoard(Model model, @PageableDefault(size = 5, page = 1, sort = "addDate", direction = Sort.Direction.DESC) Pageable pageable) {
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
        return VIEW_PATH + "freeboard/home";
    }

    /**
     * 게시판 추가 폼 입력 페이지 이동
     * */
    @GetMapping("/freeBoard/board/add")
    public String addBoard(Model model, Authentication authentication) {

        if (authentication == null)
            return "redirect:/oauth2/authorization/teemo";

        Board board = new Board();
        model.addAttribute("board", board);
        return VIEW_PATH + "freeboard/registerForm";
    }

    /**
     * 게시판 추가
     * */
    @PostMapping("/freeBoard/board/add")
    public String addBoardPost(@ModelAttribute Board board, Principal principal) {
        Reply reply1 = new Reply();
        reply1.setBoard(board);
        reply1.setAuthor("sampleUserName");
        reply1.setContent("sampleReplyContent");

        Reply reply2 = new Reply();
        reply2.setParent(reply1);
        reply2.setAuthor("reply2 author");
        reply2.setContent("reply2 content");
        reply1.getChildren().add(reply2);

        board.getReplyList().add(reply1);
        board.setAddDate(new Date());
        board.setAuthor(principal.getName());

        boardService.save(board);
        return "redirect:/community";
    }

}
