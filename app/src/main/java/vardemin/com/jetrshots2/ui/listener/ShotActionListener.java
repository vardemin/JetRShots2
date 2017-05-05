package vardemin.com.jetrshots2.ui.listener;

import vardemin.com.jetrshots2.models.Shot;
import vardemin.com.jetrshots2.models.User;

public interface ShotActionListener {
    void onUser(String username);
    void onLike(int shot);
    void onComment(int shot);
}
