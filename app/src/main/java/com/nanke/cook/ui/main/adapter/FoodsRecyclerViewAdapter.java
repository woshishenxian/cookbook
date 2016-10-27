package com.nanke.cook.ui.main.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.BaseRecyclerViewAdapter;
import com.nanke.cook.R;
import com.nanke.cook.entity.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vince on 16/10/25.
 */

public class FoodsRecyclerViewAdapter extends BaseRecyclerViewAdapter<Food> {


    public FoodsRecyclerViewAdapter(List<Food> foods) {
        super(foods);
    }

    public FoodsRecyclerViewAdapter(List<Food> foods, Context context) {
        super(foods, context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_item,null);
        return new FoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vholder, int position) {
        super.onBindViewHolder(vholder, position);
        FoodsHolder holder = (FoodsHolder)vholder;
        Food food = list.get(position);
        holder.despView.setText(food.getDescription());
        holder.titleView.setText(food.getName());
        Picasso.with(holder.imageView.getContext()).load(food.getImgUrl()).into(holder.imageView);
    }



    @Override
    protected Animator[] getAnimators(View view) {
        if (view.getMeasuredHeight() <=0){
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 1.0f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 1.0f);
            return new ObjectAnimator[]{scaleX, scaleY};
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 1.0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 1.0f),
        };
    }



    public class FoodsHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleView;
        private TextView despView;

        public FoodsHolder(View itemView) {
            super(itemView);
            this.despView = (TextView) itemView.findViewById(R.id.despView);
            this.titleView = (TextView) itemView.findViewById(R.id.titleView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}
