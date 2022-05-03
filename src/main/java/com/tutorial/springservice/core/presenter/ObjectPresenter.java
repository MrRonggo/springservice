package com.tutorial.springservice.core.presenter;

public interface ObjectPresenter<T> {
    void present(T response);
}
