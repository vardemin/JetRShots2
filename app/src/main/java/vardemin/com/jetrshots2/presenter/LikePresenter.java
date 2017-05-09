package vardemin.com.jetrshots2.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.contract.LikesContract;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.models.User;

public class LikePresenter implements LikesContract.Presenter {

    private LikesContract.View view;

    private String username;
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public LikePresenter(String username) {
        App.getApplicationComponent().inject(this);
        this.username = username;
        load();
    }

    @Override
    public void onAttach(LikesContract.View view) {
        this.view = view;
        view.initialize(localDataRepository.getUserLikes(username));
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view!=null;
    }

    @Override
    public void load() {
        remoteDataRepository.getUserLikes(username,localDataRepository.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likes -> {
                    User user = localDataRepository.getUser(username);
                    for (Like like: likes) {
                        like.setUser(user);
                    }
                    localDataRepository.save(likes);
                });
    }
}
