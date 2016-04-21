package com.larin92.testtasks.internship.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.larin92.testtasks.internship.CardModel;
import com.larin92.testtasks.internship.CardsFragment;
import com.larin92.testtasks.internship.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by larin92 on 21.04.2016.
 */
public class Holder extends RecyclerView.ViewHolder {
    @Bind(R.id.card) CardView mCardView;
    @Bind(R.id.card_category) ImageView mCategory;
    @Bind(R.id.like_count) TextView mLikeCount;
    @Bind(R.id.card_description) TextView mDescription;
    @Bind(R.id.card_address) TextView mAddress;
    @Bind(R.id.card_date) TextView mDate;
    @Bind(R.id.card_days_left) TextView mDaysLeft;
    private Context mContext;
    private CardModel mCardModel;
    private CardsFragment.OnListFragmentInteractionListener mListener;

    public Holder(View v, Context context,
                  CardsFragment.OnListFragmentInteractionListener listener) {
        super(v);
        ButterKnife.bind(this, v);
        mContext = context;
        mListener = listener;
    }

    public void setHolderData(int position, CardModel cardModel) {
        mCardModel = cardModel;  //not necessary, but makes it more readable
        switch (mCardModel.getmCategory()) {
            case "Black Hole":
                mCategory.setImageResource(R.drawable.ic_business);
                break;
            case "Neutron Star":
                mCategory.setImageResource(R.drawable.ic_cogwheel);
                break;
            case "Supernova Explosion":
                mCategory.setImageResource(R.drawable.ic_construction);
                break;
            default:
                mCategory.setImageResource(R.drawable.ic_trash);
        }

        mLikeCount.setText(String.valueOf(mCardModel.getmLikes()));

        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        if (dpWidth>=600)
            dpWidth*=2; //makes sense for large displays

        String description = mCardModel.getmDescription();
        if (description.length() > dpWidth/13*2) {
            description = description.subSequence(0, (int)dpWidth/13*2) + "...";
        }
        mDescription.setText(description);

        String address = mCardModel.getmAddress();
        if (address.length() > dpWidth/13) {
            address = address.subSequence(0, (int)dpWidth/13) + "...";
        }
        mAddress.setText(address);

        if (mCardModel.getmStatus() == 1)
            mDate.setText(mCardModel.getmDateSolveTo());
        else
            mDate.setText(mCardModel.getmDateCreated());
        if (mCardModel.getmStatus() == 0) {
            String daysLeft = mCardModel.getmDaysLeft() + " " + mContext.getResources().getString(R.string.days);
            mDaysLeft.setText(daysLeft);
        }

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mCardModel);
                }
            }
        });
    }
}
