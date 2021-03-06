package com.larin92.testtasks.internship.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.larin92.testtasks.internship.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ItemImageRecyclerAdapter extends RecyclerView.Adapter<ItemImageRecyclerAdapter.Holder> {
    private Context mContext;
    private List<String> mImages;

    public ItemImageRecyclerAdapter(Context context, List<String> images) {
        mContext = context;
        mImages = images;
    }

    // get the array size
    @Override
    public int getItemCount() {
        return mImages.size();
    }

    // create new view
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_image_recycler, parent, false);
        return new Holder(v);
    }

    // set the image in the newborn view
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // get the image from URL
        Glide
                .with(mContext)
                .load(mImages.get(position))
                .placeholder(R.drawable.placeholder)
                .crossFade()
                .into(holder.mImageView);
    }

    protected class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView mImageView;

        @OnClick(R.id.image)
        public void onClickToast(View view) {
            Toast.makeText(mContext, view.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        }

        public Holder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}