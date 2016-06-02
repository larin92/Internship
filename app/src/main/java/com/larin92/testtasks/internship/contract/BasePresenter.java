package com.larin92.testtasks.internship.contract;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
