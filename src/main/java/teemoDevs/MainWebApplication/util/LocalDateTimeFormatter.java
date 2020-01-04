package teemoDevs.MainWebApplication.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
