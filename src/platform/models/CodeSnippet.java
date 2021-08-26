package platform.models;

import platform.util.Utils;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

public class CodeSnippet {

    @NotBlank
    private String code;

    private String date;

    public CodeSnippet(String code, String date){
        this.code = code;
        this.date = Objects.requireNonNullElseGet(date, () -> Utils.getFormattedDateTime(LocalDateTime.now()));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void updateDate(){
        this.date = Utils.getFormattedDateTime(LocalDateTime.now());
    }
}
