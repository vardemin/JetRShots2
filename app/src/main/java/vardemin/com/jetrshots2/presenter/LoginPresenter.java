package vardemin.com.jetrshots2.presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.contract.LoginContract;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;
import vardemin.com.jetrshots2.util.Constant;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public LoginPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public void onAttach(LoginContract.View view) {
        this.view = view;
        if (!localDataRepository.getToken().equals(""))
            view.onAuthorized();
        else view.setLoading(false);
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
    public void login(String code) {
        remoteDataRepository.authorize(Constant.AUTH_URL+"token",Constant.CLIENT_ID,Constant.CLIENT_SECRET, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( accessToken -> {
                   localDataRepository.cacheToken(accessToken.getAccessToken());
                    view.setLoading(false);
                    view.onAuthorized();
                }, throwable -> {
                    view.showError(throwable.getLocalizedMessage());
                });
    }
}
