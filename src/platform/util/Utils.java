package platform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static final String TEST_CODE = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static String getFormattedDateTime(String isoDate){
        LocalDateTime dateTime = LocalDateTime.parse(isoDate);
        return dateTime.format(dtFormatter);
    }

    public static String getFormattedDateTime(LocalDateTime dateTime){
        return dateTime.format(dtFormatter);
    }
}
