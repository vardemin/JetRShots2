package vardemin.com.jetrshots2.presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.contract.FollowersContract;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.models.User;

public class FollowerPresenter implements FollowersContract.Presenter {

    private FollowersContract.View view;

    private String username;
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public FollowerPresenter(String username) {
        App.getApplicationComponent().inject(this);
        this.username = username;
        load();
    }

    @Override
    public void onAttach(FollowersContract.View view) {
        this.view = view;
        view.initialize(localDataRepository.getUserFollowers(username));
    }

    @Override
    public void onDetach() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view!=null;
    }

    @Override
    public void load() {
        remoteDataRepository.getUserFollowers(username,localDataRepository.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(followers -> {
                    User user = localDataRepository.getUser(username);
                    for (Follower follower: followers) {
                        follower.setUser(user);
                    }
                    localDataRepository.save(followers);
                });
    }
}
