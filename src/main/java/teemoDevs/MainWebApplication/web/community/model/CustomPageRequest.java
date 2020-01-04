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

    /**
     * 쿼리할 페이지 세팅
     */
    public CustomPageRequest setPage(int page) {
        this.page = (page <= 0) ? 1 : page;
        return this;
    }

    /**
     * 페이지 당 쿼리되는 갯수 세팅
     */
    public CustomPageRequest setSize(int size) {
        int DEFAULT_SIZE = 5;   // 페이지 기본 사이즈
        int MAX_SIZE = 10;      // 페이지 최대 사이즈
        int MIN_SIZE = 1;       // 페이지 최소 사이즈

        this.size = (size > MAX_SIZE) || (size < MIN_SIZE) ? DEFAULT_SIZE : size;
        return this;
    }

    /**
     * 쿼리 시 오름차순, 내림차순 세팅
     */
    public CustomPageRequest setDirection(Sort.Direction direction) {
        this.direction = direction;
        return this;
    }

    /**
     * 쿼리 시 페이징을 위한 객체를 리턴
     */
    public PageRequest of() {
        String[] properties = { "addDate" }; // 정렬 시 기준이 되는 필드명 리스트
        return PageRequest.of(page - 1, size, direction, properties);
    }
}
