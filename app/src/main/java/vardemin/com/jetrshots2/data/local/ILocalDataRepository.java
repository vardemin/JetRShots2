package vardemin.com.jetrshots2.data.local;

import java.util.List;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.models.User;


public interface ILocalDataRepository<E extends RealmModel> {
    /**
     * Save RealmObject list
     * @param list of realmObjects to save
     */
    void save(List<E> list);

    /**
     * Save realm object
     * @param object to save
     */
    void save(E object);

    /**
     * Delete object from BD
     * @param object obj to remove
     */
    void delete(RealmObject object);

    /**
     * Save user token
     * @param token to save
     */
    void cacheToken(String token);

    /**
     * Get current token
     * @return user token
     */
    String getToken();

    RealmResults<Shot> getShots();
    Shot getShot(int shotId);

    /**
     * Get user by username
     * @param username username
     * @return User instance
     */
    User getUser(String username);

    /**
     * Get User followers by username
     * @param username username
     * @return list of followers
     */
    RealmResults<Follower> getUserFollowers(String username);

    /**
     * Get User likes by username
     * @param username username
     * @return list of likes
     */
    RealmResults<Like> getUserLikes(String username);

    /**
     * Get Shot comments by Id
     * @param shotId shot id
     * @return list of comments
     */
    RealmResults<Comment> getShotComments(int shotId);


    void likeShot(int shotId, boolean state);

    /**
     * Close DB connection (If no longer needed / app termination)
     */
    void close();
}
