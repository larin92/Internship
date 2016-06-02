package com.larin92.testtasks.internship.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.contract.NavigationContract;
import com.larin92.testtasks.internship.data.Api;
import com.larin92.testtasks.internship.data.Database;
import com.larin92.testtasks.internship.data.model.CardModel;
import com.larin92.testtasks.internship.data.model.json.Model;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class NavigationPresenter implements NavigationContract.Presenter {

    private static final String TAG = "NavigationPresenter";
    private CompositeSubscription mSubscription;
    private NavigationContract.View mView;
    private volatile int mOffset = 0;
    private final String mQuery;

    public NavigationPresenter(int tab) {
        switch (tab) {
            case 0:
                mQuery = CardModel.QUERY_INWORK;
                break;
            case 1:
                mQuery = CardModel.QUERY_DONE;
                break;
            case 2:
                mQuery = CardModel.QUERY_WAITING;
                break;
            default:
                mQuery = CardModel.QUERY_DONE;
                break;
        }
    }

    @Override
    public void attachView(NavigationContract.View view) {
        mSubscription = new CompositeSubscription();
        mOffset = Database.please().getQuery(mQuery).size();
        Log.v(TAG, "Query is: " + mQuery + ". Offset is: " + String.valueOf(mOffset));
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void receiveData() {
        mView.showRefresh();
        if (!isOnline()) {
            Toast.makeText(mView.getViewContext(), R.string.sorry, Toast.LENGTH_SHORT).show();
            mView.hideRefresh();
            return;
        }
        Log.v(TAG, "receiveData");
        mSubscription.add(Api.please().getBatch(mQuery, mOffset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Model>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideRefresh();
                        Log.v(TAG, "receiveData onCompleted");
                        showBackup();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error: " + e);
                        mView.hideRefresh();
                    }

                    @Override
                    public void onNext(List<Model> models) {
                        Log.v(TAG, "receiveData onNext");
                        Database.please().responseAdaption(models);
                        mOffset += models.size();
                        Log.v(TAG, "Query is: " + mQuery + ". Offset is: " + String.valueOf(mOffset));
                    }
                }));
    }

    @Override
    public void update() {
        mView.showRefresh();
        if (!isOnline()) {
            Toast.makeText(mView.getViewContext(), R.string.sorry, Toast.LENGTH_SHORT).show();
            mView.hideRefresh();
            return;
        }
        Log.v(TAG, "update");
        mSubscription.add(Api.please().update(mQuery, mOffset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Model>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideRefresh();
                        Log.v(TAG, "update onCompleted");
                        showBackup();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error: " + e);
                        mView.hideRefresh();
                    }

                    @Override
                    public void onNext(List<Model> models) {
                        Log.v(TAG, "update onNext");
                        Database.please().responseAdaption(models);
                        mOffset = models.size();
                        Log.v(TAG, "Query is: " + mQuery + ". Offset is: " + String.valueOf(mOffset));
                    }
                }));
    }

    @Override
    public void showBackup() {
        int size = Database.please().getQuery(mQuery).size();
        if (size == 0) {
            Log.v(TAG, "showBackup():Q isEmpty)");
            receiveData();
            return;
        }

        if (mView.getItemCount() == 0) {
            Log.v(TAG, "showbackup() setdata");
            mView.setData(Database.please().getQuery(mQuery));
        }

        if (mView.getItemCount() < size) {
            Log.v(TAG, "showbackup() notify");
            mView.notifyAdapter(Database.please().getQuery(mQuery));
        }
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager cm;
        cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public int getOffset() {
        return mOffset;
    }
}
