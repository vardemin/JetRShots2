package vardemin.com.jetrshots2.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.ui.fragment.FollowersFragment;
import vardemin.com.jetrshots2.ui.fragment.LikesFragment;

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 2;

    private final int[] titles = {R.string.profile_likes, R.string.profile_followers};

    private Context context;

    public ProfilePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        switch (position) {
            case 0:
                fragment = Fragment.instantiate(context, LikesFragment.class.getName());
                break;
            case 1:
                fragment = Fragment.instantiate(context, FollowersFragment.class.getName());
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(titles[position]);
    }
}
