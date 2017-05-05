package vardemin.com.jetrshots2.contract;


import vardemin.com.jetrshots2.presenter.BasePresenter;

/**
 * Main Screen Contract
 */
public interface MainContract {
    /**
     * Main Screen View
     */
    interface View {
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

        /**
         * View exit to login screen
         */
        void exitToLogin();
    }

    /**
     * Main Screen Presenter
     */
    interface Presenter extends BasePresenter<View> {
        /**
         * Handle logout
         */
        void logout();
    }
}
