package com.larin92.testtasks.internship.contract;

import android.content.Context;

import com.larin92.testtasks.internship.data.model.CardModel;

import java.util.List;

public class NavigationContract {

    public interface Presenter extends BasePresenter<View> {

        void attachView(View view);

        void receiveData();

        void update();

        void showBackup();

        boolean isOnline();

        int getOffset();
    }

    public interface View extends BaseView {

        Context getViewContext();

        void setData(List<CardModel> list);

        void notifyAdapter(List<CardModel> list);

        int getItemCount();

        void showRefresh();

        void hideRefresh();
    }
}
