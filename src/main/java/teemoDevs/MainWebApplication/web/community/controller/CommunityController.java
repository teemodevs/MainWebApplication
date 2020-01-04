package teemoDevs.MainWebApplication.web.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teemoDevs.MainWebApplication.web.community.model.Board;
import teemoDevs.MainWebApplication.web.community.model.CustomPageRequest;
import teemoDevs.MainWebApplication.web.community.model.Reply;
import teemoDevs.MainWebApplication.web.community.service.BoardService;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * 커뮤니티 웹 컨트롤러
 * @author yechanpark
 * */
@Controller
@RequestMapping("/community")
public class CommunityController {

    private final static String VIEW_PATH = "content/community/";

    @Autowired
    private BoardService boardService;

    /**
     * 자유게시판 이동
     * */
    @GetMapping("")
    public String communityFreeBoard(Model model, @PageableDefault(size = 5, page = 1, sort = CustomPageRequest.sortProperty, direction = Sort.Direction.DESC) Pageable pageable) {
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
     * 자유게시판 게시글 추가 입력 폼 이동
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
     * 게시글 추가
     * */
    @PostMapping("/freeBoard/board/add")
    public String addBoardPost(@ModelAttribute Board board, Principal principal) {
        Reply reply1 = new Reply();
        reply1.setBoard(board);
        reply1.setAuthor("reply1 author");
        reply1.setContent("reply1 content");
        reply1.setAddDate(LocalDateTime.now());

        Reply reply1_1 = new Reply();
        reply1_1.setParent(reply1);
        reply1_1.setAuthor("reply1_1 author");
        reply1_1.setContent("reply1_1 content");
        reply1_1.setAddDate(LocalDateTime.now());
        reply1.getChildren().add(reply1_1);

        Reply reply1_2 = new Reply();
        reply1_2.setParent(reply1);
        reply1_2.setAuthor("reply1_2 author");
        reply1_2.setContent("reply1_2 content");
        reply1_2.setAddDate(LocalDateTime.now());
        reply1.getChildren().add(reply1_2);

        Reply reply1_1_1 = new Reply();
        reply1_1_1.setParent(reply1_1);
        reply1_1_1.setAuthor("reply1_1_1 author");
        reply1_1_1.setContent("reply1_1_1 content");
        reply1_1_1.setAddDate(LocalDateTime.now());
        reply1_1.getChildren().add(reply1_1_1);

        Reply reply2 = new Reply();
        reply2.setBoard(board);
        reply2.setAuthor("reply2 author");
        reply2.setContent("reply2 content");
        reply2.setAddDate(LocalDateTime.now());

        Reply reply1_1_2 = new Reply();
        reply1_1_2.setParent(reply1_1);
        reply1_1_2.setAuthor("reply1_1_2 author");
        reply1_1_2.setContent("reply1_1_2 content");
        reply1_1_2.setAddDate(LocalDateTime.now());
        reply1_1.getChildren().add(reply1_1_2);

        Reply reply2_1 = new Reply();
        reply2_1.setParent(reply2);
        reply2_1.setAuthor("reply2_1 author");
        reply2_1.setContent("reply2_1 content");
        reply2_1.setAddDate(LocalDateTime.now());
        reply2.getChildren().add(reply2_1);

        Reply reply2_2 = new Reply();
        reply2_2.setParent(reply2);
        reply2_2.setAuthor("reply2_2 author");
        reply2_2.setContent("reply2_2 content");
        reply2_2.setAddDate(LocalDateTime.now());
        reply2.getChildren().add(reply2_2);

        Reply reply2_1_1 = new Reply();
        reply2_1_1.setParent(reply2_1);
        reply2_1_1.setAuthor("reply2_1_1 author");
        reply2_1_1.setContent("reply2_1_1 content");
        reply2_1_1.setAddDate(LocalDateTime.now());
        reply2_1.getChildren().add(reply2_1_1);

        board.getReplyList().add(reply1);
        board.getReplyList().add(reply2);
        board.setAddDate(LocalDateTime.now());
        board.setAuthor(principal.getName());

        boardService.save(board);
        return "redirect:/community";
    }

    /**
     * 게시글에 추가할 최상위 부모 댓글 추가
     * */
    @PostMapping("/freeBoard/reply/add")
    public String addReplyPost(
            @RequestParam(value = "board.id") Board board,
            @RequestParam(value = "replyContent") String replyContent,
            Principal principal) {

        Reply reply = new Reply();
        reply.setBoard(board);
        reply.setAuthor(principal.getName());
        reply.setContent(replyContent);
        reply.setAddDate(LocalDateTime.now());

        board.getReplyList().add(reply);

        boardService.save(board);
        return "redirect:/community";
    }

}
