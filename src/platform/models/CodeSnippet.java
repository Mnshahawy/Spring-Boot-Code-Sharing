package platform.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private enum RESTRICTIONS {
        NONE,
        TIME_RESTRICTED,
        VIEWS_RESTRICTED,
        TIME_AND_VIEWS_RESTRICTED
    }
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private boolean isLastView;

    //Helper field to retain expiration time of a snippet
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime expires;

    /**
        Since the Snippet can be time-restricted or views-restricted or both,
        it's better to have a redundant field to identify the type of restriction
        This will help to optimize our queries and the process to update restrictions
        I will be using the following codes:
        No restriction = 0
        time-restricted = 1
        views-restricted = 2
        time-and-views-restricted = 3
        These are the ordinal values of the RESTRICTIONS enum
     */
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int restricted;

    public CodeSnippet(){
        //this.id = UUID.randomUUID().toString();
    }

    public CodeSnippet(String code){
        //this();
        this.code = code;
    }

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

    public void setRestricted(){
        RESTRICTIONS restrictions = RESTRICTIONS.NONE;
        if(time > 0 && views > 0){
            expires = LocalDateTime.now().plusSeconds(time);
            restrictions = RESTRICTIONS.TIME_AND_VIEWS_RESTRICTED;
        }else if(time > 0){
            expires = LocalDateTime.now().plusSeconds(time);
            restrictions = RESTRICTIONS.TIME_RESTRICTED;
        }else if(views > 0){
            restrictions = RESTRICTIONS.VIEWS_RESTRICTED;
        }
        this.restricted = restrictions.ordinal();
    }

    public int getRestricted() {
        return restricted;
    }

    public boolean updateRestricted(){
        RESTRICTIONS restrictions = RESTRICTIONS.values()[restricted];
        boolean requiresDeletion = false;
        switch (restrictions){
            case TIME_RESTRICTED:
                updateTime();
                if(time <= 0 ) {
                    requiresDeletion = true;
                }
                break;
            case VIEWS_RESTRICTED:
                views--;
                if(views <= 0 ) {
                    requiresDeletion = true;
                }
                break;
            case TIME_AND_VIEWS_RESTRICTED:
                updateTime();
                views--;
                if(time <= 0 || views <= 0 ) {
                    requiresDeletion = true;
                }
                break;
        }
        return requiresDeletion;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isLastView() {
        return isLastView;
    }

    public void setLastView(boolean lastView) {
        isLastView = lastView;
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
