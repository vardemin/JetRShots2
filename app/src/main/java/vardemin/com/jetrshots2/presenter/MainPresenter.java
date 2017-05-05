package vardemin.com.jetrshots2.presenter;

import javax.inject.Inject;

import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.contract.MainContract;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    @Inject
    ILocalDataRepository localDataRepository;

    public MainPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public void onAttach(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view=null;
    }

    @Override
    public boolean isViewAttached() {
        return view!=null;
    }

    @Override
    public void logout() {
        localDataRepository.cacheToken("");
        if(isViewAttached())
            view.exitToLogin();
    }
}
