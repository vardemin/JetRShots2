package vardemin.com.jetrshots2.presenter;

public interface BasePresenter<T> {
    void onAttach(T view);
    void onDetach();
    boolean isViewAttached();
}
