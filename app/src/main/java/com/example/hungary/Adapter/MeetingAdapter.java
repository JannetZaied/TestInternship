package com.example.hungary.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hungary.CustomImageView;
import com.example.hungary.R;
import com.example.hungary.data.Meetings;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MyViewHolders> {
    private Context context;
    public List<Meetings> listM;


    public class MyViewHolders extends RecyclerView.ViewHolder {

        public CustomImageView thumbnail;
        public TextView desc;


        public MyViewHolders(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.image);
            desc = view.findViewById(R.id.description);
        }
    }


    public MeetingAdapter(Context context, List<Meetings> coursList) {
        this.context = context;
        this.listM = coursList;
    }

    @Override
    public MyViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new MyViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolders holder, final int position) {
        final Meetings m = listM.get(position);
        //here we will tell each data to link with it's view w fazit el cropping here
        Glide.with(context.getApplicationContext())
                .load(m.getTumbnail())
                .into(holder.thumbnail);
        String title = m.getName() + " - "+ m.getSubtitle();
        holder.desc.setText(title);

        }




    public int ConvertToInt(String numb) {
        return Integer.parseInt(numb);
    }

    @Override
    public int getItemCount() {
        Log.d("list size", String.valueOf(listM.size()));
        return listM.size();
    }


}