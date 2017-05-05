package vardemin.com.jetrshots2.di.component;

import dagger.Component;
import vardemin.com.jetrshots2.di.module.ShotsModule;
import vardemin.com.jetrshots2.di.scope.FragmentScope;
import vardemin.com.jetrshots2.ui.fragment.ShotsFragment;

@Component(dependencies = MainComponent.class, modules = ShotsModule.class)
@FragmentScope
public interface ShotsComponent {
    void inject(ShotsFragment shotsFragment);
}
