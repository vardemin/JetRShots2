package vardemin.com.jetrshots2.contract;

import vardemin.com.jetrshots2.presenter.BasePresenter;

public interface LoginContract {

    interface View {
        void setLoading(boolean state);
        void login();
        void onAuthorized();
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String code);
    }
}
