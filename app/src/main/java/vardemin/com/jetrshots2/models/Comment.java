package vardemin.com.jetrshots2.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Comment extends RealmObject {
    @PrimaryKey
    private int id;
    private User user;
    private Shot shot;
    private Date updated_at;
    private String body;

    public Comment() {}

    public Comment(int id, User user, Shot shot, Date commentedAt, String body){
        this.id = id;
        this.user = user;
        this.shot = shot;
        this.updated_at = commentedAt;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Shot getShot() {
        return shot;
    }

    public Date getCommentedAt() {
        return updated_at;
    }

    public String getBody() {
        return body;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }
}
