package com.larin92.testtasks.task1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.mHolder> {
    private Context mContext;
    protected List<String> mImages;

    public RecyclerAdapter(Context context, List<String> images) {
        mContext = context;
        mImages = images;
    }

    // get the array size
    @Override
    public int getItemCount() {
        return mImages.size();
    }

    protected class mHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image) ImageView mImageView;

        public mHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    // create new view
    @Override
    public RecyclerAdapter.mHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, false);
        return new mHolder(v);
    }

    // set the image in the newborn view
    @Override
    public void onBindViewHolder(mHolder holder, int position) {
        // get the image from URL
        Glide
                .with(mContext)
                .load(mImages.get(position))
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(holder.mImageView);
    }
}