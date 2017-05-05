package vardemin.com.jetrshots2.contract;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.models.User;
import vardemin.com.jetrshots2.presenter.BasePresenter;

public interface FollowersContract {

    interface View {
        void initialize(RealmResults<Follower> data);
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {
        void load();
    }
}
