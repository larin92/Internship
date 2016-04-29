package com.larin92.testtasks.internship;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.larin92.testtasks.internship.adapters.ItemImageRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemFragment extends Fragment {

    static Context mContext;
    static CardModel mCardModel = null;
    @Bind(R.id.recycler_images)
    RecyclerView mRecycler;
    @Bind(R.id.item_category)
    TextView mCategory;
    @Bind(R.id.item_status)
    TextView mStatus;
    @Bind(R.id.item_creation)
    TextView mCreation;
    @Bind(R.id.item_registration)
    TextView mRegistration;
    @Bind(R.id.item_solveTo)
    TextView mResolveTo;
    @Bind(R.id.item_responsible)
    TextView mResponsible;
    @Bind(R.id.item_description)
    TextView mDescription;
    List<String> mUrls = null;

    public ItemFragment() {
        // Required empty public constructor
    }

    public static ItemFragment newInstance(CardModel cardModel, Context context) {
        mContext = context;
        mCardModel = cardModel;
        return new ItemFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment, container, false);
        ButterKnife.bind(this, view);

        receiveAndSetData();

        return view;
    }

    private void receiveAndSetData() {
        if (mCardModel != null) {
            if (mCardModel.getmUrls() != null)
                mUrls = mCardModel.mUrls;
            switch (mCardModel.getmCategory()) {
                case 0:
                    mCategory.setText(mContext.getResources().getString(R.string.mockCategory0));
                    break;
                case 1:
                    mCategory.setText(mContext.getResources().getString(R.string.mockCategory1));
                    break;
                case 2:
                    mCategory.setText(mContext.getResources().getString(R.string.mockCategory2));
                    break;
                default:
                    mCategory.setText(mContext.getResources().getString(R.string.statusWut));
            }
            String status;
            switch (mCardModel.getmStatus()) {
                case 0:
                    status = getResources().getString(R.string.status0);
                    break;
                case 1:
                    status = getResources().getString(R.string.status1);
                    break;
                case 2:
                    status = getResources().getString(R.string.status2);
                    break;
                default:
                    status = getResources().getString(R.string.statusWut);
            }
            mStatus.setText(status);

            if (mCardModel.getmDateCreated() != null)
                mCreation.setText(mCardModel.getmDateCreated());
            if (mCardModel.getmDateRegistered() != null)
                mRegistration.setText(mCardModel.getmDateRegistered());
            if (mCardModel.getmDateResolveTo() != null)
                mResolveTo.setText(mCardModel.getmDateResolveTo());
            if (mCardModel.getmResponsible() != null)
                mResponsible.setText(mCardModel.getmResponsible());
            if (mCardModel.getmDescription() != null)
                mDescription.setText(mCardModel.getmDescription());
        }
        populateRecycler();
    }

    //populate our recyclerView
    protected void populateRecycler() {
        if (mUrls == null) {
            mUrls = new ArrayList<>(Arrays.
                    asList(getResources().
                            getStringArray(R.array.urls0)));
        }

        mRecycler.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        ItemImageRecyclerAdapter adapter = new ItemImageRecyclerAdapter(mContext, mUrls);
        mRecycler.setAdapter(adapter);
    }
}
