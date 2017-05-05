package vardemin.com.jetrshots2.contract;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.models.User;
import vardemin.com.jetrshots2.presenter.BasePresenter;

public interface ProfileContract {

    interface View {
        void initialize(User user);
    }

    interface Presenter extends BasePresenter<View> {
        void load();
    }
}
