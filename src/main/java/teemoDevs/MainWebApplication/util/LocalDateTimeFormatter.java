package teemoDevs.MainWebApplication.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime을 DB에 그대로 저장한 이후 사용하지 않음.
 * 이후 포맷 변경 시 참고를 위해 남겨놓음
 */
public class LocalDateTimeFormatter {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 'yyyy-MM-dd HH:mm:ss' 포맷의 LocalDateTime 객체로 변환
     */
    public static LocalDateTime format(LocalDateTime unFormattedLocalDateTime) {
        return LocalDateTime.parse(
                unFormattedLocalDateTime.format(DateTimeFormatter.ofPattern(pattern))
                , DateTimeFormatter.ofPattern(pattern)
        );
    }
}
