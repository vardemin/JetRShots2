package vardemin.com.jetrshots2.di.module;

import dagger.Module;
import dagger.Provides;
import vardemin.com.jetrshots2.di.scope.SubFragmentScope;
import vardemin.com.jetrshots2.presenter.LikePresenter;

@Module
public class LikeModule {
    @Provides
    @SubFragmentScope
    public LikePresenter provideLikePresenter(String username) {
        return new LikePresenter(username);
    }
}
