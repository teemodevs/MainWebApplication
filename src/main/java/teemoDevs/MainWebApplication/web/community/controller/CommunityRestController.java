package teemoDevs.MainWebApplication.web.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teemoDevs.MainWebApplication.web.community.service.BoardService;

/**
 * 커뮤니티 REST 컨트롤러
 * @author yechanpark
 * */
@RestController
@RequestMapping("/community")
public class CommunityRestController {

    @Autowired
    private BoardService boardService;

}
