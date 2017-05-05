package vardemin.com.jetrshots2.di.module;

import dagger.Module;
import dagger.Provides;
import vardemin.com.jetrshots2.di.scope.FragmentScope;
import vardemin.com.jetrshots2.presenter.ProfilePresenter;

@Module
public class ProfileModule {
    private String username;
    public ProfileModule(String username) {
        this.username = username;
    }
    @Provides
    @FragmentScope
    public ProfilePresenter provideProfilePresenter() {
        return new ProfilePresenter(username);
    }
    @Provides
    @FragmentScope
    public String provideUsername() {
        return username;
    }
}
