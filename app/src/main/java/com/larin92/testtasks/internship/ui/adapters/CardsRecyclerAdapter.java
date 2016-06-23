package com.larin92.testtasks.internship.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larin92.testtasks.internship.R;
import com.larin92.testtasks.internship.data.model.CardModel;
import com.larin92.testtasks.internship.ui.fragments.NavigationFragment;

import java.util.List;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<Holder> {

    private final NavigationFragment.OnListFragmentInteractionListener mListener;
    private Context mContext;
    private List<CardModel> mCardModelList;

    public CardsRecyclerAdapter(Context context, List<CardModel> cardModelList,
                                NavigationFragment.OnListFragmentInteractionListener listener) {
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

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CardModel cardModel = mCardModelList.get(position);
        holder.setHolderData(cardModel);
    }

    public void addCards(List<CardModel> list) {
        mCardModelList = null;
        mCardModelList = list;
    }
}