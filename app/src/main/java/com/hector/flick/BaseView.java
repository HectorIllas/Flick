package com.hector.flick;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
