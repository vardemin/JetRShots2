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
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.util.DateUtil;

public class CommentsListAdapter extends RealmRecyclerViewAdapter<Comment, CommentsListAdapter.CommentsListViewHolder> {


    public CommentsListAdapter(@Nullable RealmResults<Comment> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public CommentsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, null);
        int width = parent.getMeasuredWidth();
        view.setMinimumWidth(width);
        CommentsListViewHolder result = new CommentsListViewHolder(view);
        return result;
    }

    @Override
    public void onBindViewHolder(CommentsListViewHolder holder, int position) {
        Comment comment = getItem(position);
        if(comment==null)
            return;
        Picasso.with(holder.avatar.getContext())
                .load(comment.getUser().getAvatar_url())
                .error(R.drawable.ic_placeholder)
                .into(holder.avatar);
        holder.username.setText(comment.getUser().getName());
        holder.text.setText(comment.getBody());
        holder.date.setText(DateUtil.dateToString(comment.getCommentedAt()));
    }

    public static class CommentsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comment_avatar)
        ImageView avatar;
        @BindView(R.id.comment_username)
        TextView username;
        @BindView(R.id.comment_text)
        TextView text;
        @BindView(R.id.comment_date)
        TextView date;

        public CommentsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
