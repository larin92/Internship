package com.larin92.testtasks.internship.data;

import com.larin92.testtasks.internship.App;
import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.data.model.CardModel;
import com.larin92.testtasks.internship.data.model.ImageModel;
import com.larin92.testtasks.internship.data.model.json.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
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

            String performers = "";
            if (model.getPerformers() != null)
                for (int j = 0; j < model.getPerformers().size(); j++) {
                    if (j > 0)
                        performers += ", ";
                    performers += model.getPerformers().get(j).getOrganization();
                }

            RealmList<ImageModel> images = new RealmList<>();
            if (model.getFiles() != null)
                for (int j = 0; j < model.getFiles().size(); j++) {
                    images.add(new ImageModel(model.getFiles().get(j).getFilename()));
                }

            String address = "";
            if (model.getGeoAddress() != null)
                address = model.getGeoAddress().getAddress();

            String daysLeft = "";
            if (model.getDeadline() != null && model.getStartDate() != null) {
                int days = (model.getDeadline() - model.getStartDate()) / (24 * 60 * 60 * 1000);
                daysLeft = String.valueOf(days);
            }

            final CardModel cardModel = CardModel.newBuilder()
                    .setID(model.getId())
                    .setCategory(model.getTitle())
                    .setDescription(model.getBody())
                    .setAddress(address)
                    .setDateCreated(format(model.getCreatedDate()))
                    .setDateRegistered(format(model.getStartDate()))
                    .setDateResolveTo(format(model.getDeadline()))
                    .setDaysLeft(daysLeft)
                    .setLikes(model.getLikesCounter())
                    .setStatus(model.getState().getId())
                    .setTitle(model.getTitle())
                    .setImages(images)
                    .setResponsible(performers)
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

    private String format(Integer dateInt) {
        if (dateInt == null)
            return "";
        DateFormat formatter = new SimpleDateFormat(App.getContext()
                .getString(R.string.date_pattern),
                Locale.getDefault());
        Date date = new Date(dateInt);

        return formatter.format(date);
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
