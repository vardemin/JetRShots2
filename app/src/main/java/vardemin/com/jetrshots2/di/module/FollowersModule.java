package vardemin.com.jetrshots2.di.module;

import dagger.Module;
import dagger.Provides;
import vardemin.com.jetrshots2.di.scope.SubFragmentScope;
import vardemin.com.jetrshots2.presenter.FollowerPresenter;

@Module
public class FollowersModule {
    @Provides
    @SubFragmentScope
    public FollowerPresenter provideFollowerPresenter(String username) {
        return new FollowerPresenter(username);
    }
}
