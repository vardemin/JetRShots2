package vardemin.com.jetrshots2.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Like extends RealmObject {
    @PrimaryKey
    private int id;
    private Shot shot;
    private User user;
    private Date created_at;

    public Like() {}

    public Like(int id, Shot shot, User user, Date created_at) {
        this.id = id;
        this.shot = shot;
        this.user = user;
        this.created_at = created_at;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
