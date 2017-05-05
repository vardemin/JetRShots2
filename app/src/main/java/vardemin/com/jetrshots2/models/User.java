package vardemin.com.jetrshots2.models;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private String username;
    private String avatar_url;
    private String bio;
    private int likes_count;
    private int likes_received_count;
    private int followers_count;
    private int followings_count;

    public User() {}

    public User(int id, String name, String username, String avatar_url, String bio, int likes_count, int likes_received_count, int followers_count, int followings_count) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.avatar_url = avatar_url;
        this.bio = bio;
        this.likes_count = likes_count;
        this.likes_received_count = likes_received_count;
        this.followers_count = followers_count;
        this.followings_count = followings_count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getBio() {
        return bio;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public int getLikes_received_count() {
        return likes_received_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFollowings_count() {
        return followings_count;
    }

    public String getUsername() {
        return username;
    }


}
