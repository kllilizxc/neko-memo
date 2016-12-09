package com.example.a54179.mytodo.stores;

import com.example.a54179.mytodo.actions.Action;
import com.example.a54179.mytodo.dispatcher.Dispatcher;

/**
 * Created by 54179 on 2016/12/8.
 */

public abstract class Store {
    final Dispatcher dispatcher;

    public interface StoreChangeEvent {}

    abstract StoreChangeEvent changeEvent();

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    void emitStoreChange() {
        dispatcher.emitChange(changeEvent());
    }

    public abstract void onAction(Action action);
}
