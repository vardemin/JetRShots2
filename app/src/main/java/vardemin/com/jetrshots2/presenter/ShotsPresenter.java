package vardemin.com.jetrshots2.presenter;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.contract.ShotsContract;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;
import vardemin.com.jetrshots2.events.TransitionEvent;
import vardemin.com.jetrshots2.models.Shot;

import static vardemin.com.jetrshots2.util.Constant.COMMENT_KEY;
import static vardemin.com.jetrshots2.util.Constant.PROFILE_KEY;

public class ShotsPresenter implements ShotsContract.Presenter {

    private ShotsContract.View view;
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public ShotsPresenter() {
        App.getApplicationComponent().inject(this);
        load();
    }

    @Override
    public void onAttach(ShotsContract.View view) {
        this.view = view;
        view.initialize(localDataRepository.getShots());
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
        remoteDataRepository.getRecentShots(localDataRepository.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shots -> {
                    if(isViewAttached())
                        view.setLoading(false);
                   localDataRepository.save(shots);
                });
    }

    @Override
    public void likeClick(int shot) {
        Shot likingShot = localDataRepository.getShot(shot);
        if(!likingShot.getLiked()) {
            remoteDataRepository.likeShot(String.valueOf(shot), localDataRepository.getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(like -> {
                        like.setShot(likingShot);
                        localDataRepository.save(like);
                        localDataRepository.likeShot(shot,true);
                    });
        }
        else {
            remoteDataRepository.unlikeShot(String.valueOf(shot), localDataRepository.getToken());
            localDataRepository.likeShot(shot,false);
        }
        //localDataRepository.likeShot(shot);

    }

    @Override
    public void userClick(String username) {
        Bundle bundle = new Bundle();
        bundle.putString(PROFILE_KEY,username);
        EventBus.getDefault().post(new TransitionEvent(TransitionEvent.TransitionType.PROFILE, bundle));
    }

    @Override
    public void commentClick(int shot) {
        Bundle bundle = new Bundle();
        bundle.putInt(COMMENT_KEY, shot);
        EventBus.getDefault().post(new TransitionEvent(TransitionEvent.TransitionType.COMMENT, bundle));
    }

    @Override
    public void exit() {
        EventBus.getDefault().post(new TransitionEvent(TransitionEvent.TransitionType.EXIT, null));
    }
}
