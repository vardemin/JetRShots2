package vardemin.com.jetrshots2.models;

import io.realm.RealmObject;

public class ShotImage extends RealmObject {
    private String hidpi;
    private String normal;
    private String teaser;

    public ShotImage(){}

    public ShotImage(String hidpi, String normal, String teaser) {
        this.hidpi = hidpi;
        this.normal = normal;
        this.teaser = teaser;
    }

    public String getHidpi() {
        return hidpi;
    }

    public String getNormal() {
        return normal;
    }

    public String getTeaser() {
        return teaser;
    }

    public String getBestImageUrl() {
        return (hidpi!=null)? hidpi: normal;
    }
}
