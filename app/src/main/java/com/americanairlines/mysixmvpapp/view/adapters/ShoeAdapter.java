package com.americanairlines.mysixmvpapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.americanairlines.mysixmvpapp.R;
import com.americanairlines.mysixmvpapp.model.Shoe;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

public class ShoeAdapter extends BaseAdapter {

    private List<Shoe> shoeList;

    public ShoeAdapter(List<Shoe> shoeList) {
        this.shoeList = shoeList;
    }

    @Override
    public int getCount() {
        return shoeList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoes, parent, false);

        TextView shoeModel = v.findViewById(R.id.tv_shoe_model);
        TextView shoeSize = v.findViewById(R.id.tv_shoe_size);
        TextView shoePrice = v.findViewById(R.id.tv_shoe_price);
        ImageView shoeImage = v.findViewById(R.id.im_shoe_image);

        shoeModel.setText(shoeList.get(position).getShoeModel());
        shoeSize.setText(String.valueOf(shoeList.get(position).getShoeSize()));
        shoePrice.setText(String.format("$%.2f", shoeList.get(position).getShoePrice()));

        Glide.with(v.getContext())
                .load(shoeList.get(position).getImageID())
                .into(shoeImage);

        return v;
    }
}
