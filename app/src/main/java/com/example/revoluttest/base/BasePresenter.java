package com.example.revoluttest.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter implements BaseContract.Presenter{

    private CompositeDisposable disposables;
    private BaseContract.View view;

    @Override
    public void subscribe(BaseContract.View view) {
        this.view = view;
        this.disposables = new CompositeDisposable();
        onSubscribe();
    }

    @Override
    public BaseContract.View getView() {
        return view;
    }

    @Override
    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void unsubscribe() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
