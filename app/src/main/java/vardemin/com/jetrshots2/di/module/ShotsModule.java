package vardemin.com.jetrshots2.di.module;

import dagger.Module;
import dagger.Provides;
import vardemin.com.jetrshots2.di.scope.FragmentScope;
import vardemin.com.jetrshots2.presenter.ShotsPresenter;

@Module
public class ShotsModule {
    @Provides
    @FragmentScope
    public ShotsPresenter provideShotsPresenter() {
        return new ShotsPresenter();
    }
}
