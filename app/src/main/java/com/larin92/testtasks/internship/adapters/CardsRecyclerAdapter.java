package com.larin92.testtasks.internship.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larin92.testtasks.internship.CardModel;
import com.larin92.testtasks.internship.CardsFragment;
import com.larin92.testtasks.internship.R;

import java.util.List;

/**
 * Created by larin92 on 18.04.2016.
 */
public class CardsRecyclerAdapter extends RecyclerView.Adapter<Holder> {

    static final String TAG = "CardsRecyclerAdapter";
    private final CardsFragment.OnListFragmentInteractionListener mListener;
    private Context mContext;
    private List<CardModel> mCardModelList;

    public CardsRecyclerAdapter(Context context, List<CardModel> cardModelList,
                                CardsFragment.OnListFragmentInteractionListener listener) {
        mContext = context;
        mCardModelList = cardModelList;
        mListener = listener;
    }

    // get the array size
    @Override
    public int getItemCount() {
        return mCardModelList.size();
    }

    // create new view
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        return new Holder(v, mContext, mListener);
    }

    //
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CardModel cardModel = mCardModelList.get(position);
        holder.setHolderData(cardModel);
    }
}