package vardemin.com.jetrshots2.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;
import vardemin.com.jetrshots2.di.module.ApplicationModule;
import vardemin.com.jetrshots2.di.module.DataModule;
import vardemin.com.jetrshots2.di.module.NetModule;
import vardemin.com.jetrshots2.presenter.CommentPresenter;
import vardemin.com.jetrshots2.presenter.FollowerPresenter;
import vardemin.com.jetrshots2.presenter.LikePresenter;
import vardemin.com.jetrshots2.presenter.LoginPresenter;
import vardemin.com.jetrshots2.presenter.MainPresenter;
import vardemin.com.jetrshots2.presenter.ProfilePresenter;
import vardemin.com.jetrshots2.presenter.ShotsPresenter;

@Component(modules = {ApplicationModule.class, NetModule.class, DataModule.class})
@Singleton
public interface ApplicationComponent {
    Context context();
    ILocalDataRepository localDataRepository();
    IRemoteDataRepository remoteDataRepository();
    Retrofit retrofit();

    void inject(MainPresenter mainPresenter);
    void inject(LoginPresenter loginPresenter);
    void inject(ShotsPresenter shotsPresenter);
    void inject(CommentPresenter commentPresenter);
    void inject(ProfilePresenter profilePresenter);
    void inject(LikePresenter likePresenter);
    void inject(FollowerPresenter followerPresenter);
}
