package vardemin.com.jetrshots2.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.ui.listener.ShotActionListener;

public class ShotsListAdapter extends RealmRecyclerViewAdapter<Shot,ShotsListAdapter.ShotsListViewHolder> {

    private ShotActionListener listener;

    public ShotsListAdapter(@Nullable OrderedRealmCollection<Shot> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    public void setListener(ShotActionListener listener) {
        this.listener = listener;
    }
    public void removeListener() {
        this.listener = null;
    }

    @Override
    public ShotsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item, null);
        int height = parent.getMeasuredHeight() / 2;
        view.setMinimumHeight(height);
        ShotsListViewHolder result = new ShotsListViewHolder(view);
        return result;
    }

    @Override
    public void onBindViewHolder(ShotsListViewHolder holder, int position) {
        Shot shot = getItem(position);
        if (shot==null)
            return;
        Picasso.with(holder.image.getContext())
                .load(shot
                        .getImageUrl())
                .error(R.drawable.ic_placeholder)
                .into(holder.image);
        Picasso.with(holder.avatar.getContext())
                .load(shot
                        .getUser().getAvatar_url())
                .error(R.drawable.ic_placeholder)
                .into(holder.avatar);
        holder.title.setText(shot.getTitle());
        holder.username.setText(shot.getUser().getName());
        holder.description.setText(shot.getDescription());
        holder.commentsCount.setText(String.valueOf(shot.getComments_count()));
        holder.likesCount.setText(String.valueOf(shot.getLikes_count()));

        holder.likeBtn.setImageResource(shot.isLiked()?R.drawable.ic_liked:R.drawable.ic_action_like);

        if(listener==null)
            return;

        View.OnClickListener clickListener = view -> {
            switch (view.getId()) {
                case R.id.shot_user_avatar:case R.id.shot_user_name:
                    listener.onUser(shot.getUser().getUsername());break;
                case R.id.shot_action_comment:
                    listener.onComment(shot.getId());break;
                case R.id.shot_action_like:
                    holder.likeBtn.setImageResource(shot.isLiked()?R.drawable.ic_liked:R.drawable.ic_action_like);
                    listener.onLike(shot.getId());break;
            }
        };

        holder.username.setOnClickListener(clickListener);
        holder.avatar.setOnClickListener(clickListener);
        holder.commentBtn.setOnClickListener(clickListener);
        holder.likeBtn.setOnClickListener(clickListener);

    }


    public static class ShotsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.shot_image)
        ImageView image;
        @BindView(R.id.shot_title)
        TextView title;
        @BindView(R.id.shot_user_avatar)
        ImageView avatar;
        @BindView(R.id.shot_user_name)
        TextView username;
        @BindView(R.id.shot_description)
        TextView description;
        @BindView(R.id.shot_comments_count)
        TextView commentsCount;
        @BindView(R.id.shot_likes_count)
        TextView likesCount;
        @BindView(R.id.shot_action_comment)
        ImageButton commentBtn;
        @BindView(R.id.shot_action_like)
        ImageButton likeBtn;

        public ShotsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
