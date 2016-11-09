package com.nanke.cook.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vince on 16/11/7.
 */

public class DailyAdapter extends BaseAdapter {

    private Context context;
    private List<Food> foods;

    public DailyAdapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = new ArrayList<>(foods);
    }


    public void addAll(List<Food> collection) {
        foods.addAll(collection);
        notifyDataSetChanged();
    }


    public void clear() {
        foods.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return foods.isEmpty();
    }

    public void remove(int index) {
        if (index > -1 && index < foods.size()) {
            foods.remove(index);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_daily_item, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Food food = foods.get(position);
        holder.titleView.setText(food.getName());
        Picasso.with(context).load(food.getImgUrl()).into(holder.portraitView);
        holder.position = position;

        return convertView;
    }


    class ViewHolder implements View.OnClickListener{
        ImageView portraitView;
        TextView titleView;
        TextView favorite;

        int position;

        public ViewHolder(View convertView) {
            this.portraitView = (ImageView) convertView.findViewById(R.id.portrait);
            this.titleView = (TextView) convertView.findViewById(R.id.title);
//            this.favorite = (TextView) convertView.findViewById(R.id.favorite);

//            favorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onCollectItemClickListener !=null)
            onCollectItemClickListener.onItemClick(foods.get(position));
        }
    }

    private OnCollectItemClickListener onCollectItemClickListener;

    public void setOnCollectItemClickListener(
            OnCollectItemClickListener onCollectItemClickListener) {
        this.onCollectItemClickListener = onCollectItemClickListener;
    }

    public interface OnCollectItemClickListener {
        void onItemClick(Food food);
    }
}
