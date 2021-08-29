package platform.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import platform.util.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Entity
public class CodeSnippet {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column
    @NotBlank
    private String code;

    @Column(name = "createdAt")
    @CreationTimestamp
    @JsonFormat(pattern = Utils.DATE_TIME_FORMAT)
    private LocalDateTime date;


    public CodeSnippet(){}
    public CodeSnippet(String code){
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getFormattedDateTime(){
        return date.format(Utils.DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "CodeSnippet{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}
