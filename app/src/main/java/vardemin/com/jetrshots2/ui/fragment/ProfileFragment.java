package vardemin.com.jetrshots2.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.contract.ProfileContract;
import vardemin.com.jetrshots2.di.component.DaggerProfileComponent;
import vardemin.com.jetrshots2.di.component.ProfileComponent;
import vardemin.com.jetrshots2.di.module.ProfileModule;
import vardemin.com.jetrshots2.models.User;
import vardemin.com.jetrshots2.presenter.ProfilePresenter;
import vardemin.com.jetrshots2.ui.activity.MainActivity;
import vardemin.com.jetrshots2.ui.adapter.ProfilePagerAdapter;

import static vardemin.com.jetrshots2.util.Constant.PROFILE_KEY;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    @Inject
    ProfilePresenter presenter;

    private ProfileComponent profileComponent;

    private String username;

    @BindView(R.id.profile_viewpager)
    ViewPager viewPager;
    @BindView(R.id.profile_avatar)
    ImageView avatar;
    @BindView(R.id.profile_bio)
    TextView bio;
    @BindView(R.id.profile_username)
    TextView username_txt;
    @BindView(R.id.profile_likes_count)
    TextView likes_count;
    @BindView(R.id.profile_followers_count)
    TextView followers_count;
    @BindView(R.id.profile_toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_tabs)
    TabLayout tabLayout;

    public static ProfileFragment newInstance(String username) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(PROFILE_KEY, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.username = getArguments().getString(PROFILE_KEY);
        profileComponent = DaggerProfileComponent.builder()
                .mainComponent(((MainActivity)getActivity()).getMainComponent())
                .profileModule(new ProfileModule(username))
                .build();
        profileComponent.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_layout, container, false);
        ButterKnife.bind(this, rootView);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(null);
        viewPager.setAdapter(new ProfilePagerAdapter(getChildFragmentManager(), getActivity()));
        viewPager.setPageMargin(20);
        tabLayout.setupWithViewPager(viewPager);
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
    public void initialize(User user) {
        Picasso.with(getActivity()).load(user.getAvatar_url()).error(R.drawable.ic_placeholder).into(avatar);
        username_txt.setText(user.getName());
        bio.setText(user.getBio());
        likes_count.setText(String.valueOf(user.getLikes_count()));
        followers_count.setText(String.valueOf(user.getFollowers_count()));
    }

    public ProfileComponent getProfileComponent() {
        return profileComponent;
    }
}
