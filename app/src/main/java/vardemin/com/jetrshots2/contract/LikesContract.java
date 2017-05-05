package vardemin.com.jetrshots2.contract;

import io.realm.RealmResults;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.presenter.BasePresenter;

/**
 * Contract pattern for Likes
 */
public interface LikesContract {
    /**
     * Likes View
     */
    interface View {
        /**
         * Init View
         * @param data data
         */
        void initialize(RealmResults<Like> data);

        /**
         * Show error
         * @param errorMessage error
         */
        void showError(String errorMessage);
    }

    /**
     * Likes Presenter
     */
    interface Presenter extends BasePresenter<View> {
        /**
         * Load data
         */
        void load();
    }
}
