package vardemin.com.jetrshots2.di.component;

import dagger.Component;
import vardemin.com.jetrshots2.di.module.LoginModule;
import vardemin.com.jetrshots2.di.scope.ScreenScope;
import vardemin.com.jetrshots2.ui.activity.LoginActivity;

@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
@ScreenScope
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
