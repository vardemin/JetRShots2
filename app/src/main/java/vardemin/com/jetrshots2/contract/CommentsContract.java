package vardemin.com.jetrshots2.contract;

import io.realm.RealmResults;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.presenter.BasePresenter;

/**
 * Contract pattern for Contacts
 */
public interface CommentsContract {

    /**
     * Comments Viwe
     */
    interface View {
        /**
         * show loading
         * @param state loading state
         */
        void setLoading(boolean state);

        /**
         * Init view
         * @param data data
         */
        void initialize(RealmResults<Comment> data);

        /**
         * Called on send
         * @param text text to send
         */
        void sendComment(String text);

        /**
         * Show error
         * @param errorMessage error
         */
        void showError(String errorMessage);
    }

    /**
     * Comments Presenter
     */
    interface Presenter extends BasePresenter<View> {
        /**
         * Load data
         */
        void load();

        /**
         * Called on send view
         * @param text text to send
         */
        void sendClick(String text);
    }
}
