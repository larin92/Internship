package com.larin92.testtasks.internship.contract;

import android.content.Intent;

import com.larin92.testtasks.internship.model.CardModel;

public class ItemContract {

    public interface Presenter extends BasePresenter<View> {

        void receiveData(Intent intent);
    }

    public interface View extends BaseView {

        void setData(CardModel cardModel);
    }
}
