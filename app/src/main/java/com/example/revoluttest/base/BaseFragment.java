package com.example.revoluttest.base;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment implements BaseContract.View {
    private BaseContract.Presenter presenter;

    @Override
    public BaseContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
