package vardemin.com.jetrshots2.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.contract.FollowersContract;
import vardemin.com.jetrshots2.di.component.DaggerFollowersComponent;
import vardemin.com.jetrshots2.di.component.FollowersComponent;
import vardemin.com.jetrshots2.di.component.ProfileComponent;
import vardemin.com.jetrshots2.di.module.FollowersModule;
import vardemin.com.jetrshots2.models.Follower;
import vardemin.com.jetrshots2.presenter.FollowerPresenter;
import vardemin.com.jetrshots2.ui.adapter.FollowersListAdapter;

public class FollowersFragment extends Fragment implements FollowersContract.View {
    private static final String key = "USER_ID";

    @Inject
    FollowerPresenter presenter;

    private FollowersComponent followersComponent;

    @BindView(R.id.followers_rv)
    RecyclerView recyclerView;

    private FollowersListAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        followersComponent = DaggerFollowersComponent.builder()
                .profileComponent(((ProfileFragment) getParentFragment()).getProfileComponent())
                .followersModule(new FollowersModule())
                .build();
        followersComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.followers_list, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onAttach(this);
    }

    @Override
    public  void onStop() {
        super.onStop();
        presenter.onDetach();
    }

    @Override
    public void initialize(RealmResults<Follower> data) {
        adapter = new FollowersListAdapter(data, true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {

    }

    public FollowersComponent getFollowersComponent() {
        return followersComponent;
    }
}
