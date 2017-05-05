package vardemin.com.jetrshots2.ui.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.contract.CommentsContract;
import vardemin.com.jetrshots2.di.component.CommentsComponent;
import vardemin.com.jetrshots2.di.component.DaggerCommentsComponent;
import vardemin.com.jetrshots2.di.module.CommentsModule;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.presenter.CommentPresenter;
import vardemin.com.jetrshots2.ui.activity.MainActivity;
import vardemin.com.jetrshots2.ui.adapter.CommentsListAdapter;

import static vardemin.com.jetrshots2.util.Constant.COMMENT_KEY;

public class CommentsFragment extends Fragment implements CommentsContract.View {

    @Inject
    CommentPresenter presenter;

    private CommentsComponent commentsComponent;

    private ProgressDialog progressDialog;

    private int shotId;

    @BindView(R.id.comment_input)
    EditText input;
    @BindView(R.id.comments_rv)
    RecyclerView recyclerView;


    public static CommentsFragment newInstance(int shotId) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt(COMMENT_KEY, shotId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.shotId = getArguments().getInt(COMMENT_KEY);
        commentsComponent = DaggerCommentsComponent.builder()
                .mainComponent(((MainActivity)getActivity()).getMainComponent())
                .commentsModule(new CommentsModule(shotId))
                .build();
        commentsComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comments_layout, container, false);
        ButterKnife.bind(this, rootView);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
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
    public void setLoading(boolean state) {
        if (state && !progressDialog.isShowing())
            progressDialog.show();
        else {
                progressDialog.dismiss();
        }

    }

    @Override
    public void initialize(RealmResults<Comment> data) {
        CommentsListAdapter adapter = new CommentsListAdapter(data, true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void sendComment(String text) {
        presenter.sendClick(text);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_SHORT).show();
        setLoading(false);
    }

    @OnClick(R.id.comment_action_send)
    public void onSend() {
        if(input.getText().length()>0)
            sendComment(input.getText().toString());
    }

    public CommentsComponent getCommentsComponent() {
        return commentsComponent;
    }
}
