package teemoDevs.MainWebApplication.web.community.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * {@link org.springframework.data.domain.PageRequest}를 커스터마이징하는 클래스
 */
public class CustomPageRequest {

    private int page;
    private int size;

    private Sort.Direction direction;


    public CustomPageRequest setPage(int page) {
        this.page = (page <= 0) ? 1 : page;
        return this;
    }

    public CustomPageRequest setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;

        this.size = (size > MAX_SIZE) ? DEFAULT_SIZE : size;
        return this;
    }

    public CustomPageRequest setDirection(Sort.Direction direction) {
        this.direction = direction;
        return this;
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size, direction, "addDate");
    }
}
