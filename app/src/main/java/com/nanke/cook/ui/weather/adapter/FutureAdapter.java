package com.nanke.cook.ui.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.entity.weather.Weather;

import java.util.List;

/**
 * Created by vince on 16/11/4.
 */

public class FutureAdapter extends BaseAdapter {


    private Context context;
    private List<Weather> weathers;

    public FutureAdapter(Context context, List<Weather> weathers) {
        this.context = context;
        this.weathers = weathers;
    }


    @Override
    public int getCount() {
        return weathers.size();
    }

    @Override
    public Object getItem(int position) {
        return weathers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_weather_item,null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Weather weather = weathers.get(position);
        holder.week.setText(weather.getWeek());

        holder.temperature.setText(weather.getInfo().getDay().get(2)+"°/"+weather.getInfo().getNight().get(2)+"°");

        holder.weather_img.setImageResource(weather.getWeatherImg(Integer.parseInt(weather.getInfo().getDay().get(0))));

        return convertView;
    }

    public static class ViewHolder{
        TextView week,temperature;
        ImageView weather_img;

        public ViewHolder(View convertView) {
            this.week = (TextView) convertView.findViewById(R.id.week);
            this.temperature = (TextView) convertView.findViewById(R.id.temperature);
            this.weather_img = (ImageView) convertView.findViewById(R.id.weather_img);
        }
    }
}
