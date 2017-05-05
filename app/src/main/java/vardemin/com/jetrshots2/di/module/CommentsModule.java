package vardemin.com.jetrshots2.di.module;

import dagger.Module;
import dagger.Provides;
import vardemin.com.jetrshots2.di.scope.FragmentScope;
import vardemin.com.jetrshots2.presenter.CommentPresenter;

@Module
public class CommentsModule {
    private int shotId;
    public CommentsModule(int shotId) {
        this.shotId = shotId;
    }

    @Provides
    @FragmentScope
    public CommentPresenter provideCommentPresenter() {
        return new CommentPresenter(shotId);
    }
}
