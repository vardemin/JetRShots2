package vardemin.com.jetrshots2.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.models.Like;
import vardemin.com.jetrshots2.util.DateUtil;

public class LikesListAdapter extends RealmRecyclerViewAdapter<Like,LikesListAdapter.LikesListViewHolder>{

    public LikesListAdapter(RealmResults<Like> likes) {
        super(likes,true);
        setHasStableIds(true);
    }

    @Override
    public LikesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.like_item, null);
        int width = parent.getMeasuredWidth();
        view.setMinimumWidth(width);
        LikesListViewHolder result = new LikesListViewHolder(view);
        return result;
    }

    @Override
    public void onBindViewHolder(LikesListViewHolder holder, int position) {
        Like like = getItem(position);
        if (like==null)
            return;
        Log.d("LIKE ID: ", String.valueOf(like.getId()));
        Picasso.with(holder.avatar.getContext())
                .load(like
                .getUser().getAvatar_url())
                .error(R.drawable.ic_placeholder)
                .into(holder.avatar);
        holder.username.setText(like.getUser().getName());
        holder.date.setText(DateUtil.dateToString(like.getCreatedAt()));
        holder.title.setText(like.getShot().getTitle());
    }

    public static class LikesListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.like_avatar)
        ImageView avatar;
        @BindView(R.id.like_username)
        TextView username;
        @BindView(R.id.like_post_title)
        TextView title;
        @BindView(R.id.like_date)
        TextView date;

        public LikesListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
