package vardemin.com.jetrshots2.di.component;

import dagger.Component;
import vardemin.com.jetrshots2.di.module.LikeModule;
import vardemin.com.jetrshots2.di.scope.SubFragmentScope;
import vardemin.com.jetrshots2.ui.fragment.LikesFragment;

@Component(dependencies = ProfileComponent.class, modules = LikeModule.class)
@SubFragmentScope
public interface LikesComponent {
    void inject(LikesFragment likesFragment);
}
