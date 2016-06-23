package com.larin92.testtasks.internship.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.data.model.CardModel;
import com.larin92.testtasks.internship.ui.fragments.NavigationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Holder extends RecyclerView.ViewHolder {
    @BindView(R.id.card)
    CardView mCardView;
    @BindView(R.id.card_category)
    ImageView mCategory;
    @BindView(R.id.like_count)
    TextView mLikeCount;
    @BindView(R.id.card_description)
    TextView mDescription;
    @BindView(R.id.card_address)
    TextView mAddress;
    @BindView(R.id.card_date)
    TextView mDate;
    @BindView(R.id.card_days_left)
    TextView mDaysLeft;
    private Context mContext;
    private NavigationFragment.OnListFragmentInteractionListener mListener;

    public Holder(View v, Context context,
                  NavigationFragment.OnListFragmentInteractionListener listener) {
        super(v);
        ButterKnife.bind(this, v);
        mContext = context;
        mListener = listener;
    }

    public void setHolderData(final CardModel cardModel) {

        mCategory.setImageResource(R.drawable.ic_paper);

        mLikeCount.setText(String.valueOf(cardModel.getLikes()));
        mDescription.setText(cardModel.getDescription());
        mAddress.setText(cardModel.getAddress());

        if (CardModel.STATE_DONE.contains(cardModel.getStatus())) {
            mDate.setText(cardModel.getDateResolveTo());
        } else {
            mDate.setText(cardModel.getDateCreated());
        }

        if (CardModel.STATE_INWORK.contains(cardModel.getStatus())) {
            String daysLeft = cardModel.getDaysLeft() + " " + mContext.getResources().getString(R.string.days);
            mDaysLeft.setText(daysLeft);
        }
        mDate.setText(cardModel.getDateCreated());

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(cardModel);
                }
            }
        });
    }
}
