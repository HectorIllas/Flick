package com.hector.flickapp;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
