package vardemin.com.jetrshots2.contract;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.models.User;
import vardemin.com.jetrshots2.presenter.BasePresenter;
import vardemin.com.jetrshots2.ui.listener.ShotActionListener;

/**
 * Shots Contract
 */
public interface ShotsContract {
    /**
     * Shots View
     */
    interface View extends ShotActionListener {
        /**
         * Init View
         * @param data data
         */
        void initialize(RealmResults<Shot> data);

        /**
         * Call on load more action
         */
        void loadMore();

        /**
         * Show loading
         * @param state loading state
         */
        void setLoading(boolean state);

        /**
         * Show error
         * @param errorMessage error
         */
        void showError(String errorMessage);
    }

    /**
     * Shots presenter
     */
    interface Presenter extends BasePresenter<View> {
        /**
         * Load data
         */
        void load();

        /**
         * Called on view like click
         * @param shot shot to like
         */
        void likeClick(int shot);

        /**
         * Called on view user click
         * @param username of user clicked
         */
        void userClick(String username);

        /**
         * Called on view comment click
         * @param shot to comment
         */
        void commentClick(int shot);

        /**
         * Called on view exit action
         */
        void exit();
    }

}
