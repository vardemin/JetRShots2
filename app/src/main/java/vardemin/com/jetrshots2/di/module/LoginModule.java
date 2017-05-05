package vardemin.com.jetrshots2.di.module;

import dagger.Module;
import dagger.Provides;
import vardemin.com.jetrshots2.di.scope.ScreenScope;
import vardemin.com.jetrshots2.presenter.LoginPresenter;

@Module
public class LoginModule {
    @Provides
    @ScreenScope
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
}
