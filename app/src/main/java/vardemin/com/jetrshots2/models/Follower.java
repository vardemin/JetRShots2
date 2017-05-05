package vardemin.com.jetrshots2.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Follower extends RealmObject {
    @PrimaryKey
    private int id;
    private User user;
    private User follower;

    public Follower() {}

    public Follower(int id, User user, User follower) {
        this.id = id;
        this.user = user;
        this.follower = follower;
    }

    public User getUser() {
        return user;
    }

    public User getFollower() {
        return follower;
    }

    public int getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
