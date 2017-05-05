package vardemin.com.jetrshots2.presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.contract.CommentsContract;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;
import vardemin.com.jetrshots2.models.Comment;
import vardemin.com.jetrshots2.models.Shot;

public class CommentPresenter implements CommentsContract.Presenter {

    private CommentsContract.View view;
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;
    private int shotId;

    public CommentPresenter(int shotId) {
        App.getApplicationComponent().inject(this);
        this.shotId = shotId;
        requestComments();
    }

    @Override
    public void onAttach(CommentsContract.View view) {
        this.view = view;
        view.initialize(localDataRepository.getShotComments(shotId));
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view!=null;
    }

    @Override
    public void load() {
        requestComments();
    }

    @Override
    public void sendClick(String text) {
        if (isViewAttached())
            view.setLoading(true);
        remoteDataRepository.postCommentToShot(String.valueOf(shotId),text,localDataRepository.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comment -> {
                   localDataRepository.save(comment);
                    if(isViewAttached())
                        view.setLoading(false);
                }, throwable -> {
                    if (throwable.getLocalizedMessage().contains("403"))
                        view.showError("NO PERMISSON");
                    else view.showError(throwable.getLocalizedMessage());
                });
    }

    private void requestComments() {
        remoteDataRepository.getShotComments(String.valueOf(shotId),localDataRepository.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {
                    Shot shot = localDataRepository.getShot(shotId);
                    for (Comment comment: comments) {
                        comment.setShot(shot);
                    }
                    localDataRepository.save(comments);
                });
    }


}
