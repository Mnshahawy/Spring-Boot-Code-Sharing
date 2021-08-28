package platform.models;

import platform.util.Utils;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

public class CodeSnippet {
    //private Long id;

    @NotBlank
    private String code;
    private String date;

    public CodeSnippet(String code, String date){
        this.code = code;
        this.date = Objects.requireNonNullElseGet(date, () -> Utils.getFormattedDateTime(LocalDateTime.now()));
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

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

    @Override
    public String toString() {
        return "CodeSnippet{" +
                //"id=" + id +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}
