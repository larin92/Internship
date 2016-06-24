package com.larin92.testtasks.internship.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.contract.NavigationContract;
import com.larin92.testtasks.internship.model.CardModel;
import com.larin92.testtasks.internship.model.json.Model;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class NavigationPresenter implements NavigationContract.Presenter {

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
        mOffset = App.getDatabaseManager().getQuery(mQuery).size();
        Timber.d("attachView. Query is: " + mQuery + ". Offset is: " + String.valueOf(mOffset));
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
        mSubscription.add(App.getApiManager().getBatch(mQuery, mOffset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Model>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideRefresh();
                        showBackup();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error: " + e);
                        mView.hideRefresh();
                    }

                    @Override
                    public void onNext(List<Model> models) {
                        App.getDatabaseManager().responseAdaption(models);
                        mOffset += models.size();
                        Timber.d("receiveData. Query is: " + mQuery + ". Offset is: " + String.valueOf(mOffset));
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
        mSubscription.add(App.getApiManager().update(mQuery, mOffset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Model>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideRefresh();
                        showBackup();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error: " + e);
                        mView.hideRefresh();
                    }

                    @Override
                    public void onNext(List<Model> models) {
                        App.getDatabaseManager().responseAdaption(models);
                        mOffset = models.size();
                        Timber.d("update. Query is: " + mQuery + ". Offset is: " + String.valueOf(mOffset));
                    }
                }));
    }

    @Override
    public void showBackup() {
        int size = App.getDatabaseManager().getQuery(mQuery).size();
        if (size == 0) {
            Timber.d("showBackup():Q isEmpty)");
            receiveData();
            return;
        }

        if (mView.getItemCount() == 0) {
            Timber.d("showbackup() setdata");
            mView.setData(App.getDatabaseManager().getQuery(mQuery));
        }

        if (mView.getItemCount() < size) {
            Timber.d("showbackup() notify");
            mView.notifyAdapter(App.getDatabaseManager().getQuery(mQuery));
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
