package com.larin92.testtasks.internship.presenter;

import android.content.Intent;

import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.contract.ItemContract;
import com.larin92.testtasks.internship.manager.DatabaseManager;
import com.larin92.testtasks.internship.data.model.CardModel;

public class ItemPresenter implements ItemContract.Presenter {

    private ItemContract.View mView;

    @Override
    public void attachView(ItemContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void receiveData(Intent intent) {
        int id = intent.getIntExtra(CardModel.ID, 0);
        CardModel cardModel = App.getDatabaseManager().findById(id);
        mView.setData(cardModel);
    }
}
