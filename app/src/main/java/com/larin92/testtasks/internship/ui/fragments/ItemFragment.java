package com.larin92.testtasks.internship.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.contract.ItemContract;
import com.larin92.testtasks.internship.model.CardModel;
import com.larin92.testtasks.internship.presenter.ItemPresenter;
import com.larin92.testtasks.internship.ui.adapters.ItemImageRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ItemFragment extends Fragment implements ItemContract.View {

    private static Context sContext;
    private ItemPresenter mItemPresenter;
    private List<String> mUrls = new ArrayList<>();
    private Unbinder mUnbinder;

    @BindView(R.id.recycler_images)
    RecyclerView mRecycler;
    @BindView(R.id.item_category)
    TextView mCategory;
    @BindView(R.id.item_status)
    TextView mStatus;
    @BindView(R.id.item_creation)
    TextView mCreation;
    @BindView(R.id.item_registration)
    TextView mRegistration;
    @BindView(R.id.item_solveTo)
    TextView mResolveTo;
    @BindView(R.id.item_responsible)
    TextView mResponsible;
    @BindView(R.id.item_description)
    TextView mDescription;

    @OnClick({R.id.item_category,
            R.id.item_status,
            R.id.item_creation,
            R.id.item_registration,
            R.id.item_solveTo,
            R.id.item_responsible,
            R.id.item_description,
            R.id.item_creation_header,
            R.id.item_registration_header,
            R.id.item_solveto_header
    })
    public void onClickToast(View view) {
        Toast.makeText(getContext(), view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    public ItemFragment() {
        // Required empty public constructor
    }

    public static ItemFragment newInstance(Context context) {
        sContext = context;
        return new ItemFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mItemPresenter = new ItemPresenter();
        mItemPresenter.attachView(this);
        mItemPresenter.receiveData(getActivity().getIntent());

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mItemPresenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setData(CardModel cardModel) {
        if (cardModel != null) {
            if (cardModel.getImages() != null && cardModel.getImages().size() > 0) {
                for (int i = 0; i < cardModel.getImages().size(); i++)
                    mUrls.add(cardModel.getImages().get(i).getUrl());
            }
            mCategory.setText(cardModel.getCategory());

            String status = getResources().getString(R.string.statusWut);
            if (CardModel.STATE_INWORK.contains(cardModel.getStatus())) {
                status = getResources().getString(R.string.status0);
            }
            if (CardModel.STATE_DONE.contains(cardModel.getStatus())) {
                status = getResources().getString(R.string.status1);
            }
            if (CardModel.STATE_WAITING.contains(cardModel.getStatus())) {
                status = getResources().getString(R.string.status2);
            }
            mStatus.setText(status);

            if (cardModel.getDateCreated() != null) {
                mCreation.setText(cardModel.getDateCreated());
            }
            if (cardModel.getDateRegistered() != null) {
                mRegistration.setText(cardModel.getDateRegistered());
            }
            if (cardModel.getDateResolveTo() != null) {
                mResolveTo.setText(cardModel.getDateResolveTo());
            }

            if (cardModel.getResponsible() != null) {
                mResponsible.setText(cardModel.getResponsible());
            }
            if (cardModel.getDescription() != null) {
                mDescription.setText(cardModel.getDescription());
            }
        }
        populateRecycler();
    }

    private void populateRecycler() {
        if (mUrls == null) {
            return;
        }
        if (mUrls.size() == 0) {
            return;
        }
        mRecycler.setLayoutManager(new LinearLayoutManager(sContext, RecyclerView.HORIZONTAL, false));
        ItemImageRecyclerAdapter adapter = new ItemImageRecyclerAdapter(sContext, mUrls);
        mRecycler.setAdapter(adapter);
    }
}
