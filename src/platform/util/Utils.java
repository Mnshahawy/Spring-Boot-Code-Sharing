package platform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static final String TEST_CODE = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss a";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public static String getFormattedDateTime(String isoDate){
        LocalDateTime dateTime = LocalDateTime.parse(isoDate);
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    public static String getFormattedDateTime(LocalDateTime dateTime){
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
