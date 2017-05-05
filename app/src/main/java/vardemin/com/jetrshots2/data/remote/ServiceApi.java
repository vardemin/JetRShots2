package vardemin.com.jetrshots2.data.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import vardemin.com.jetrshots2.models.AccessToken;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.models.User;

public interface ServiceApi {
    @POST
    Observable<AccessToken> authorizeClient (@Url String url,
                                             @Query("client_id") String client_id,
                                             @Query("client_secret") String client_secret,
                                             @Query("code") String code);

    @GET("shots")
    Observable<List<Shot>> getShots(@Query("access_token") String token, @Query("page") String page,
                                    @Query("per_page") String per_page, @Query("sort") String sort,
                                    @Query("list") String list);

    @GET("users/{user}")
    Observable<User> getSingleUser(@Path("user") String user, @Query("access_token") String token);

    @GET("users/{user}/likes")
    Observable<List<Like>> getUserLikes(@Path("user") String user, @Query("access_token") String token);

    @GET("users/{user}/followers")
    Observable<List<Follower>> getUserFollowers(@Path("user") String user, @Query("access_token") String token);

    @GET("shots/{shot}/comments")
    Observable<List<Comment>> getShotComments(@Path("shot") String shot, @Query("access_token") String token);

    @POST("shots/{shot}/comments")
    Observable<Comment> postCommentToShot(@Path("shot") String shot, @Query("body") String text, @Query("access_token") String token);

    @POST("shots/{shot}/like")
    Call<String> likeShot(@Path("shot") String shotId, @Query("access_token") String token);
}
