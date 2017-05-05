package vardemin.com.jetrshots2.presenter;


import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.contract.ProfileContract;
import vardemin.com.jetrshots2.contract.ProfileContract.Presenter;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;

public class ProfilePresenter implements Presenter {
    private ProfileContract.View view;

    private String username;
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public ProfilePresenter(String username) {
        App.getApplicationComponent().inject(this);
        this.username = username;
        load();
    }

    @Override
    public void onAttach(ProfileContract.View view) {
        this.view = view;
        Log.d("PRESENTER USER ID", username);
        view.initialize(localDataRepository.getUser(username));
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
        remoteDataRepository.getUser(username,localDataRepository.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                   localDataRepository.save(user);
                });
    }
}
