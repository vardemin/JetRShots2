package vardemin.com.jetrshots2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.contract.MainContract;
import vardemin.com.jetrshots2.di.component.DaggerMainComponent;
import vardemin.com.jetrshots2.di.component.MainComponent;
import vardemin.com.jetrshots2.di.module.MainModule;
import vardemin.com.jetrshots2.events.TransitionEvent;
import vardemin.com.jetrshots2.presenter.MainPresenter;
import vardemin.com.jetrshots2.ui.fragment.CommentsFragment;
import vardemin.com.jetrshots2.ui.fragment.ProfileFragment;
import vardemin.com.jetrshots2.ui.fragment.ShotsFragment;

import static vardemin.com.jetrshots2.util.Constant.COMMENT_KEY;
import static vardemin.com.jetrshots2.util.Constant.PROFILE_KEY;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;


    private MainComponent mainComponent;
    public MainActivity() {
        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .mainModule(new MainModule())
                .build();
        mainComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setLoading(boolean state) {

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void exitToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    /**
     * Called on Transition EVENT
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleTransition(TransitionEvent event) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        String tag;
        switch (event.getTransitionType()){
            case PROFILE:
                String username = event.getData().getString(PROFILE_KEY);
                tag = PROFILE_KEY.concat(username);
                fragment = fragmentManager.findFragmentByTag(tag);
                if(fragment==null)
                    fragment = ProfileFragment.newInstance(username);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_frame, fragment, tag);
                transaction.addToBackStack(tag);
                transaction.commit();
                break;
            case COMMENT:
                int shotId = event.getData().getInt(COMMENT_KEY);
                tag = COMMENT_KEY.concat(String.valueOf(shotId));
                fragment = fragmentManager.findFragmentByTag(tag);
                if(fragment==null)
                    fragment = CommentsFragment.newInstance(shotId);
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_frame, fragment, tag);
                transaction.addToBackStack(tag);
                transaction.commit();
                break;
            case EXIT:
                presenter.logout();
                break;
        }
    }
}
