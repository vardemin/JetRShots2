package vardemin.com.jetrshots2.events;

import android.os.Bundle;

/**
 * Transition Event
 */
public class TransitionEvent {
    private final TransitionType transitionType;
    private final Bundle data;

    public TransitionEvent(TransitionType transitionType, Bundle data) {
        this.transitionType = transitionType;
        this.data = data;
    }
    public TransitionType getTransitionType() {
        return transitionType;
    }

    public Bundle getData() {
        return data;
    }

    public enum TransitionType {
        /**
         * NAVIGATE TO PROFILE
         */
        PROFILE,
        /**
         * NAVIGATE TO COMMENTS
         */
        COMMENT,
        /**
         * EXIT
         */
        EXIT
    }
}

