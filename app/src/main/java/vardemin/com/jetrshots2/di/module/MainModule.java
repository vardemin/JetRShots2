package vardemin.com.jetrshots2.di.module;

import dagger.Module;
import dagger.Provides;
import vardemin.com.jetrshots2.di.scope.ScreenScope;
import vardemin.com.jetrshots2.presenter.MainPresenter;

@Module
public class MainModule {
    @Provides
    @ScreenScope
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
