package vardemin.com.jetrshots2.di.component;

import dagger.Component;
import vardemin.com.jetrshots2.di.module.FollowersModule;
import vardemin.com.jetrshots2.di.scope.SubFragmentScope;
import vardemin.com.jetrshots2.ui.fragment.FollowersFragment;

@Component(dependencies = ProfileComponent.class, modules = FollowersModule.class)
@SubFragmentScope
public interface FollowersComponent {
    void inject(FollowersFragment followersFragment);
}
