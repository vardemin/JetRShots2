package vardemin.com.jetrshots2.data.remote;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Retrofit;
import vardemin.com.jetrshots2.models.AccessToken;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.models.User;

public class RemoteDataRepository implements IRemoteDataRepository {
    /**
     * Retrofit instance
     */
    private Retrofit retrofit;

    public RemoteDataRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<AccessToken> authorize(@NonNull String url, @NonNull String clientId, @NonNull String clientSecret, @NonNull String code) {
        return retrofit.create(ServiceApi.class).authorizeClient(url, clientId, clientSecret, code);
    }

    @Override
    public Observable<List<Shot>> getRecentShots(@NonNull String access_token) {
        return retrofit.create(ServiceApi.class).getShots(access_token,"1","50", "recent", "attachments+debuts+playoffs+rebounds+teams");
    }

    @Override
    public Observable<User> getUser(@NonNull String username, @NonNull String access_token) {
        return retrofit.create(ServiceApi.class).getSingleUser(username, access_token);
    }

    @Override
    public Observable<List<Like>> getUserLikes(@NonNull String username, @NonNull String access_token) {
        return retrofit.create(ServiceApi.class).getUserLikes(username, access_token);
    }

    @Override
    public Observable<List<Follower>> getUserFollowers(@NonNull String username, @NonNull String access_token) {
        return retrofit.create(ServiceApi.class).getUserFollowers(username, access_token);
    }

    @Override
    public Observable<List<Comment>> getShotComments(@NonNull String shotId, @NonNull String access_token) {
        return retrofit.create(ServiceApi.class).getShotComments(shotId,access_token);
    }

    @Override
    public Observable<Comment> postCommentToShot(@NonNull String shotId, @NonNull String text, @NonNull String access_token) {
        return retrofit.create(ServiceApi.class).postCommentToShot(shotId, text, access_token);
    }

    @Override
    public void likeShot(@NonNull String shotId, @NonNull String access_token) {
        retrofit.create(ServiceApi.class).likeShot(shotId, access_token);
    }
}
