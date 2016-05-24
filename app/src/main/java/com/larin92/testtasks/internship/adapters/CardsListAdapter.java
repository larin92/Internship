package com.larin92.testtasks.internship.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.larin92.testtasks.internship.CardModel;
import com.larin92.testtasks.internship.CardsFragment;
import com.larin92.testtasks.internship.R;

import java.util.List;

/**
 * Created by larin92 on 21.04.2016.
 */
public class CardsListAdapter extends BaseAdapter {

    private final CardsFragment.OnListFragmentInteractionListener mListener;
    private Context mContext;
    private List<CardModel> mCardModelList;

    public CardsListAdapter(Context context, List<CardModel> cardModelList,
                            CardsFragment.OnListFragmentInteractionListener listener) {
        mContext = context;
        mCardModelList = cardModelList;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mCardModelList.size();
    }

    @Override
    public CardModel getItem(int position) {
        return mCardModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
            holder = new Holder(convertView, mContext, mListener);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        CardModel cardModel = mCardModelList.get(position);
        holder.setHolderData(cardModel);
        return convertView;
    }
}
