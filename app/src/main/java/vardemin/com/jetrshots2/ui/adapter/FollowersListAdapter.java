package vardemin.com.jetrshots2.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.models.Follower;

public class FollowersListAdapter extends RealmRecyclerViewAdapter<Follower, FollowersListAdapter.FollowersListViewHolder> {


    public FollowersListAdapter(@Nullable RealmResults<Follower> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public FollowersListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item, null);
        int width = parent.getMeasuredWidth();
        view.setMinimumWidth(width);
        FollowersListViewHolder result = new FollowersListViewHolder(view);
        return result;
    }

    @Override
    public void onBindViewHolder(FollowersListViewHolder holder, int position) {
        Follower follower = getItem(position);
        if (follower==null)
            return;
        Picasso.with(holder.avatar.getContext()).load(follower.getFollower().getAvatar_url()).error(R.drawable.ic_placeholder).into(holder.avatar);
        holder.username.setText(follower.getFollower().getName());
        holder.likes_count.setText(String.valueOf(follower.getFollower().getLikes_count()));
    }

    public static class FollowersListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.follower_avatar)
        ImageView avatar;
        @BindView(R.id.follower_name)
        TextView username;
        @BindView(R.id.follower_likes_count)
        TextView likes_count;

        public FollowersListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
