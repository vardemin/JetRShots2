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
import vardemin.com.jetrshots2.contract.LikesContract;
import vardemin.com.jetrshots2.di.component.DaggerLikesComponent;
import vardemin.com.jetrshots2.di.component.LikesComponent;
import vardemin.com.jetrshots2.di.module.LikeModule;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.presenter.LikePresenter;
import vardemin.com.jetrshots2.ui.adapter.LikesListAdapter;

public class LikesFragment extends Fragment implements LikesContract.View {
    private static final String key = "USER_ID";

    @Inject
    LikePresenter presenter;

    private LikesComponent likesComponent;

    @BindView(R.id.likes_rv)
    RecyclerView recyclerView;

    private LikesListAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        likesComponent = DaggerLikesComponent.builder()
                .profileComponent(((ProfileFragment)getParentFragment()).getProfileComponent())
                .likeModule(new LikeModule())
                .build();
        likesComponent.inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.likes_list, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    public void initialize(RealmResults<Like> data) {
        adapter = new LikesListAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {

    }

    public LikesComponent getLikesComponent() {
        return likesComponent;
    }
}
