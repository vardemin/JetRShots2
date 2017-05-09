package vardemin.com.jetrshots2.models;

import java.util.Date;
import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Shot extends RealmObject {
    @PrimaryKey
    private int id;
    private User user;
    private String title;
    private String description;
    private ShotImage images;
    private int likes_count;
    private int comments_count;
    private boolean liked;
    private Date updated_at;

    public Shot() {}

    public Shot(int id, User user, String title, String description, ShotImage images, int likes_count, int comments_count, Date updated_at) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.images = images;
        this.likes_count = likes_count;
        this.comments_count = comments_count;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description!=null ? description.replaceAll("\\<.*?>",""): null;
    }


    public int getLikes_count() {
        return likes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public boolean getLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public ShotImage getImages() {
        return images;
    }

    public String getImageUrl() {
        return images.getBestImageUrl();
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

}
