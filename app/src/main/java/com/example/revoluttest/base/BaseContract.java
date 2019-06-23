package com.example.revoluttest.base;

import io.reactivex.disposables.Disposable;

public interface BaseContract {
    interface Presenter {
        void subscribe(View view);

        void onSubscribe();

        void unsubscribe();

        View getView();

        void addDisposable(Disposable disposable);
    }

    interface View {
        void setPresenter(Presenter presenter);

        Presenter getPresenter();
    }
}
