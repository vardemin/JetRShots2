package vardemin.com.jetrshots2.di.component;


import dagger.Component;
import vardemin.com.jetrshots2.di.module.CommentsModule;
import vardemin.com.jetrshots2.di.scope.FragmentScope;
import vardemin.com.jetrshots2.ui.fragment.CommentsFragment;

@Component(dependencies = MainComponent.class, modules = CommentsModule.class)
@FragmentScope
public interface CommentsComponent {
    void inject(CommentsFragment commentsFragment);
}
