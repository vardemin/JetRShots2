package vardemin.com.jetrshots2.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.models.User;


public class LocalDataRepository implements ILocalDataRepository {
    @NonNull
    private final Context context;

    @Inject
    public Realm realm;

    private static final String APP_PREFERENCES = "jetrshots_pref";
    private static final String APP_PREFERENCES_TOKEN = "access_token";
    private static final String APP_PREFERENCES_CLIENT_ID = "client_id";

    private SharedPreferences preferences;

    public LocalDataRepository(@NonNull Context context, Realm realm) {
        this.context = context;
        preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        this.realm = realm;
    }

    @Override
    public void save(List list) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
    }

    @Override
    public void save(RealmModel object) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }

    @Override
    public void delete(RealmObject object) {
        realm.beginTransaction();
        object.deleteFromRealm();
        realm.commitTransaction();
    }


    @Override
    public void cacheToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(APP_PREFERENCES_TOKEN, token);
        editor.apply();
    }

    @Override
    public String getToken() {
        return preferences.getString(APP_PREFERENCES_TOKEN, "");
    }

    @Override
    public RealmResults<Shot> getShots() {
        return realm.where(Shot.class).findAll().sort("updated_at", Sort.DESCENDING);
    }

    @Override
    public Shot getShot(int shotId) {
        return realm.where(Shot.class).equalTo("id", shotId).findFirst();
    }


    @Override
    public User getUser(String username) {
        return realm.where(User.class).equalTo("username", username).findFirst();
    }

    @Override
    public RealmResults<Follower> getUserFollowers(String username) {
        return realm.where(Follower.class).equalTo("user.username",username).findAll();
    }

    @Override
    public RealmResults<Like> getUserLikes(String username) {
        return realm.where(Like.class).equalTo("user.username", username).findAll();
    }

    @Override
    public RealmResults<Comment> getShotComments(int shotId) {
        return realm.where(Comment.class).equalTo("shot.id", shotId).findAll();
    }

    @Override
    public void likeShot(int shotId, boolean state) {
        realm.beginTransaction();
        Shot shot= realm.where(Shot.class).equalTo("id",shotId).findFirst();
        shot.setLiked(state);
        shot.setLikes_count(state?shot.getLikes_count()+1:shot.getLikes_count()-1);
        realm.commitTransaction();
    }

    @Override
    public void close() {
        realm.close();
    }
}
