package vardemin.com.jetrshots2.data.remote;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import vardemin.com.jetrshots2.models.AccessToken;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.models.User;

public interface IRemoteDataRepository {
    /**
     * Autorize user
     * @param url OAuth ur
     * @param clientId CLIENT ID returned by 1-st OAuth step
     * @param clientSecret CLIENT SECRET from App
     * @param code CODE returned after 1-st step OAuth
     * @return Access Token
     */
    Observable<AccessToken> authorize(@NonNull String url, @NonNull String clientId,
                                      @NonNull String clientSecret,
                                      @NonNull String code);

    /**
     * Get Recent Shots
     * @param access_token ACCESS TOKEN
     * @return List of Shots
     */
    Observable<List<Shot>> getRecentShots(@NonNull String access_token);

    /**
     * Get Single user by username
     * @param username username
     * @param access_token ACCESS_TOKEN
     * @return user
     */
    Observable<User> getUser(@NonNull String username, @NonNull String access_token);

    /**
     * Get Likes Received by User
     * @param username username
     * @param access_token ACCESS_TOKEN
     * @return list of Likes
     */
    Observable<List<Like>> getUserLikes(@NonNull String username, @NonNull String access_token);

    /**
     * Get User Followers
     * @param username username
     * @param access_token ACCESS TOKEN
     * @return list of Followers
     */
    Observable<List<Follower>> getUserFollowers(@NonNull String username, @NonNull String access_token);

    /**
     *
     * @param shotId
     * @param access_token
     * @return
     */
    Observable<List<Comment>> getShotComments(@NonNull String shotId, @NonNull String access_token);

    /**
     * Post Comment to Shot
     * @param shotId shot Id
     * @param text text to send
     * @param access_token ACCESS_TOKEN
     * @return Comment object
     */
    Observable<Comment> postCommentToShot(@NonNull String shotId, @NonNull String text,
                                          @NonNull String access_token);

    /**
     * Post to like shot
     * @param shotId shot id
     * @param access_token ACCESS TOKEN
     */
    void likeShot(@NonNull String shotId, @NonNull String access_token);
}
