package platform.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import platform.util.Utils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;


@Entity
public class CodeSnippet {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id;

    @Column
    @NotBlank
    private String code;

    @Column(name = "createdAt")
    @CreationTimestamp
    @JsonFormat(pattern = Utils.DATE_TIME_FORMAT)
    private LocalDateTime date;

    @Column
    private long time;
    @Column
    private long views;

    @Column
    @JsonIgnore
    private boolean isViewsRestricted;

    @Column
    @JsonIgnore
    private boolean isTimeRestricted;

    //Helper field to retain expiration time of a snippet
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime expires;

    public CodeSnippet(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void updateTime(){
        time = expires.toEpochSecond(ZoneOffset.UTC) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public void setExpires(LocalDateTime expires){
        this.expires = expires;
    }

    public LocalDateTime getExpires(){
        return expires;
    }

    public void setRestrictions(){
        if(time > 0 && views > 0){
            expires = LocalDateTime.now().plusSeconds(time);
            isTimeRestricted = true;
            isViewsRestricted = true;
        }else if(time > 0){
            expires = LocalDateTime.now().plusSeconds(time);
            isTimeRestricted = true;
        }else if(views > 0){
            isViewsRestricted = true;
        }
    }

    public boolean isDeletionRequiredAfterRestrictionsUpdate(){
        boolean requiresDeletion = false;
        if(isViewsRestricted && isTimeRestricted){
            updateTime();
            views--;
            if(time <= 0 || views <= 0 ) {
                requiresDeletion = true;
            }
        } else if(isViewsRestricted){
            views--;
            if(views <= 0 ) {
                requiresDeletion = true;
            }
        }else if(isTimeRestricted){
            updateTime();
            if(time <= 0 ) {
                requiresDeletion = true;
            }
        }
        return requiresDeletion;
    }

    @JsonIgnore
    public boolean isExpired(){
        if(null == expires) return false;
        return LocalDateTime.now().isAfter(expires);
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public boolean isViewsRestricted() {
        return isViewsRestricted;
    }

    public void setViewsRestricted(boolean viewsRestricted) {
        isViewsRestricted = viewsRestricted;
    }

    public boolean isTimeRestricted() {
        return isTimeRestricted;
    }

    public void setTimeRestricted(boolean timeRestricted) {
        isTimeRestricted = timeRestricted;
    }

    @Override
    public String toString() {
        return "CodeSnippet{" +
                "code='" + code + '\'' +
                ", time=" + time +
                ", views=" + views +
                '}';
    }
}
