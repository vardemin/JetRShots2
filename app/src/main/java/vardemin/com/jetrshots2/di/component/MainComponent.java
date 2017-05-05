package vardemin.com.jetrshots2.di.component;

import dagger.Component;
import vardemin.com.jetrshots2.di.module.MainModule;
import vardemin.com.jetrshots2.di.scope.ScreenScope;
import vardemin.com.jetrshots2.ui.activity.MainActivity;

@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
@ScreenScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
