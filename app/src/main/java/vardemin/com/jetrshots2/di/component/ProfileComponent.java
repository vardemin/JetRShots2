package vardemin.com.jetrshots2.di.component;

import dagger.Component;
import vardemin.com.jetrshots2.di.module.ProfileModule;
import vardemin.com.jetrshots2.di.scope.FragmentScope;
import vardemin.com.jetrshots2.ui.fragment.ProfileFragment;

@Component(dependencies = MainComponent.class, modules = ProfileModule.class)
@FragmentScope
public interface ProfileComponent {
    String username();
    void inject(ProfileFragment profileFragment);
}
