package vardemin.com.jetrshots2.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.contract.ShotsContract;
import vardemin.com.jetrshots2.di.component.DaggerShotsComponent;
import vardemin.com.jetrshots2.di.component.ShotsComponent;
import vardemin.com.jetrshots2.di.module.ShotsModule;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.presenter.ShotsPresenter;
import vardemin.com.jetrshots2.ui.activity.MainActivity;
import vardemin.com.jetrshots2.ui.adapter.ShotsListAdapter;
import vardemin.com.jetrshots2.util.NetworkUtil;


public class ShotsFragment extends Fragment implements ShotsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ShotsPresenter presenter;

    private ShotsComponent shotsComponent;
    private ShotsListAdapter adapter;

    @BindView(R.id.shots_list_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.shots_list_refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.shots_toolbar)
    Toolbar toolbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        shotsComponent = DaggerShotsComponent.builder()
                .mainComponent(((MainActivity)getActivity()).getMainComponent())
                .shotsModule(new ShotsModule())
                .build();
        shotsComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shots_list, container, false);
        ButterKnife.bind(this, rootView);
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
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
        adapter.removeListener();
    }

    @Override
    public void initialize(RealmResults<Shot> data) {
        adapter = new ShotsListAdapter(data, true);
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void loadMore() {
        if(!NetworkUtil.isNetworkConnected(getActivity())) {
            Toast.makeText(getActivity(),R.string.error_network,Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.load();
    }

    @Override
    public void setLoading(boolean state) {
        refreshLayout.setRefreshing(state);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUser(String username) {
        presenter.userClick(username);
    }

    @Override
    public void onLike(int shot) {
        presenter.likeClick(shot);
    }

    @Override
    public void onComment(int shot) {
        presenter.commentClick(shot);
    }

    @Override
    public void onRefresh() {
        loadMore();
    }

    public ShotsComponent getShotsComponent() {
        return shotsComponent;
    }

    @OnClick(R.id.shots_exit_btn)
    public void exitClick() {
        presenter.exit();
    }
}
