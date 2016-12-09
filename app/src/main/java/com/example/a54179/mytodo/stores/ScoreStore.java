package com.example.a54179.mytodo.stores;

import com.example.a54179.mytodo.actions.Action;
import com.example.a54179.mytodo.actions.ScoreActions;
import com.example.a54179.mytodo.dispatcher.Dispatcher;
import com.squareup.otto.Subscribe;

/**
 * Created by 54179 on 2016/12/9.
 */

public class ScoreStore extends Store {
    private static ScoreStore instance;
    private final long step = 100;
    private long score = 0;

    public ScoreStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static ScoreStore get(Dispatcher dispatcher) {
        if(instance == null) {
            instance = new ScoreStore(dispatcher);
        }
        return instance;
    }

    public void setScore(long val) {
        score = val;
    }

    public long getScore() {
        return score;
    }

    private void increment() {
        score += step;
    }

    private void decrement() {
        score -= step;
        if(score < 0) score = 0;
    }

    @Override
    @Subscribe
    @SuppressWarnings("unchecked")
    public void onAction(Action action) {
        switch (action.getType()) {
            case ScoreActions.SCORE_INC:
                increment();
                break;
            case ScoreActions.SCORE_DEC:
                decrement();
                break;
        }
        emitStoreChange();
    }

    @Override
    StoreChangeEvent changeEvent() {
        return new ScoreStoreChangeEvent();
    }

    public class ScoreStoreChangeEvent implements StoreChangeEvent {}
}
