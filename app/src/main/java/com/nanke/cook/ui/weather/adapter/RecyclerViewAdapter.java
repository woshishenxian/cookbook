package com.nanke.cook.ui.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Data;
import com.nanke.cook.entity.weather.Weather;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Recycler adapter
 *
 * @author Qiushui
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final Context context;
    private Data data;


    public RecyclerViewAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.activity_weather_header, parent, false));
        }
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.activity_weather_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(0 == position){
            HeaderHolder itemHolder = (HeaderHolder) holder;

            itemHolder.temperature.setText(data.getRealtime().getWeather().getTemperature());
            itemHolder.wind.setText(data.getRealtime().getWind().getPower()+
                    data.getRealtime().getWind().getDirect());
            itemHolder.humidity.setText(data.getRealtime().getWeather().getHumidity());
//

        }else{
            ItemHolder itemHolder = (ItemHolder) holder;
            Weather weather = data.getWeather().get(position-1);
            itemHolder.week.setText(weather.getWeek());
            Picasso.with(context).load(weather.getWeatherImg(Integer.parseInt(weather.getInfo().getDay().get(0)))).into(itemHolder.weatherImg);
            itemHolder.temperature.setText(weather.getInfo().getDay().get(2)+"°/"+weather.getInfo().getNight().get(2)+"°");


        }
    }

    @Override
    public int getItemCount() {
        return data.getWeather().size() +1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }

    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.week)
        TextView week;
        @InjectView(R.id.temperature)
        TextView temperature;
        @InjectView(R.id.weather_img)
        ImageView weatherImg;



        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);

        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.temperature)
        TextView temperature;
        @InjectView(R.id.wind)
        TextView wind;
        @InjectView(R.id.humidity)
        TextView humidity;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
