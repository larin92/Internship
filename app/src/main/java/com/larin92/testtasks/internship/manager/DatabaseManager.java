package com.larin92.testtasks.internship.manager;

import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.model.CardModel;
import com.larin92.testtasks.internship.model.json.Model;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import timber.log.Timber;

public class DatabaseManager implements Manager {

    private static volatile Realm sRealm;

    static {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(App.getContext())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        sRealm = Realm.getDefaultInstance();
    }

    @Override
    public void clear() {
        if (sRealm != null && !sRealm.isClosed()) {
            sRealm.close();
        }
    }

    public CardModel findById(int id) {
        return sRealm.where(CardModel.class)
                .equalTo(CardModel.ID, id)
                .findFirst();
    }

    public List<CardModel> getQuery(String query) {
        Timber.d("QUERY(database):" + query);
        if (CardModel.sQueryInWork.equals(query)) {
            return getInWork();
        }

        else if (CardModel.sQueryDone.equals(query)) {
            return getDone();
        }

        else if (CardModel.sQueryWaiting.equals(query)) {
            return getWaiting();
        }

        throw new IllegalArgumentException("query isn't matching known statuses");
    }

    public List<CardModel> getInWork() {
        return sRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(1)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(2)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(3)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(4)).or()
                .findAll();
    }

    public List<CardModel> getDone() {
        return sRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.sStateDone.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.sStateDone.get(1)).or()
                .findAll();
    }

    public List<CardModel> getWaiting() {
        return sRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.sStateWaiting.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.sStateWaiting.get(1)).or()
                .equalTo(CardModel.STATE, CardModel.sStateWaiting.get(2)).or()
                .findAll();
    }

    public int getCount(String query) {
        if (CardModel.sQueryInWork.equals(query)) {
            return (int)getInWorkCount();
        }

        else if (CardModel.sQueryDone.equals(query)) {
            return (int)getDoneCount();
        }

        else if (CardModel.sQueryWaiting.equals(query)) {
            return (int)getWaitingCount();
        }

        throw new IllegalArgumentException("query isn't matching known statuses");
    }

    public long getInWorkCount() {
        return sRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(1)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(2)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(3)).or()
                .equalTo(CardModel.STATE, CardModel.sStateInWork.get(4)).or()
                .count();
    }

    public long getDoneCount() {
        return sRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.sStateDone.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.sStateDone.get(1)).or()
                .count();
    }

    public long getWaitingCount() {
        return sRealm.where(CardModel.class)
                .equalTo(CardModel.STATE, CardModel.sStateWaiting.get(0)).or()
                .equalTo(CardModel.STATE, CardModel.sStateWaiting.get(1)).or()
                .equalTo(CardModel.STATE, CardModel.sStateWaiting.get(2)).or()
                .count();
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
                    .setStatusName(model.getState().getName())
                    .setTitle(model.getTitle())
                    .setImages(model.getFilesList())
                    .setResponsible(model.getPerformers())
                    .build();
            sRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(cardModel);
                }
            });
        }
    }

    public <T extends RealmObject> void add(T object) {
        sRealm.beginTransaction();
        sRealm.copyToRealmOrUpdate(object);
        sRealm.commitTransaction();
    }

    public void deleteAll() {
        sRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                sRealm.deleteAll();
            }
        });
    }
}
