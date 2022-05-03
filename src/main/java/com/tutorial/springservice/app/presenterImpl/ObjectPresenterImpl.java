package com.tutorial.springservice.app.presenterImpl;

import com.tutorial.springservice.core.presenter.ObjectPresenter;
import lombok.Getter;

@Getter
public class ObjectPresenterImpl<T> implements ObjectPresenter<T> {
    private T response;

    @Override
    public void present(T response) {
        this.response = response;
    }
}
