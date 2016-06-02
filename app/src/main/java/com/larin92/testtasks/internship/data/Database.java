package com.larin92.testtasks.internship.data;

import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.data.model.CardModel;
import com.larin92.testtasks.internship.data.model.json.Model;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class Database {

    private static volatile Database mDatabase = null;
    private static volatile Realm mRealm = null;

    public static Database please() {
        if (mDatabase != null)
            return mDatabase;
        mDatabase = new Database();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(App.getContext())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        mRealm = Realm.getDefaultInstance();
        return mDatabase;
    }

    public CardModel findById(int id) {
        return mRealm.where(CardModel.class)
                .equalTo(CardModel.ID, id)
                .findFirst();
    }

    public List<CardModel> getQuery(String query) {
        if (query.equals(CardModel.QUERY_INWORK))
            return mDatabase.getInWork();

        if (query.equals(CardModel.QUERY_DONE))
            return mDatabase.getDone();

        if (query.equals(CardModel.QUERY_WAITING))
            return mDatabase.getWaiting();

        return mDatabase.getDone();
    }

    public List<CardModel> getInWork() {
        return mRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.STATE_INWORK.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.STATE_INWORK.get(1)).or()
                .equalTo(CardModel.STATE, CardModel.STATE_INWORK.get(2)).or()
                .equalTo(CardModel.STATE, CardModel.STATE_INWORK.get(3)).or()
                .equalTo(CardModel.STATE, CardModel.STATE_INWORK.get(4)).or()
                .findAll();
    }

    public List<CardModel> getDone() {
        return mRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.STATE_DONE.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.STATE_DONE.get(1)).or()
                .findAll();
    }

    public List<CardModel> getWaiting() {
        return mRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.STATE_WAITING.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.STATE_WAITING.get(1)).or()
                .equalTo(CardModel.STATE, CardModel.STATE_WAITING.get(2)).or()
                .findAll();
    }

    public void responseAdaption(List<Model> response) {
        for (int i = 0; i < response.size(); i++) {
            Model model = response.get(i);

            final CardModel cardModel = CardModel.newBuilder()
                    .setID(model.getId())
                    .setCategory(model.getTitle())
                    .setDescription(model.getBody())
                    .setAddress(model.getAddress())
                    .setDateCreated(model.getCreatedDate())
                    .setDateRegistered(model.getStartDate())
                    .setDateResolveTo(model.getDeadline())
                    .setDaysLeft(model.getDaysLeft())
                    .setLikes(model.getLikesCounter())
                    .setStatus(model.getState().getId())
                    .setTitle(model.getTitle())
                    .setImages(model.getFilesList())
                    .setResponsible(model.getPerformers())
                    .build();
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(cardModel);
                }
            });
        }
    }

    public <T extends RealmObject> void add(T object) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(object);
        mRealm.commitTransaction();
    }

    public void deleteAll() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.deleteAll();
            }
        });
    }
}
