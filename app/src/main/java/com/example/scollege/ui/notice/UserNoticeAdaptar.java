package com.example.scollege.ui.notice;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.scollege.NoticeData;
import com.example.scollege.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserNoticeAdaptar  extends RecyclerView.Adapter<UserNoticeAdaptar.UserNoticeViewAdapter> {
    private Context context;
    private ArrayList<UserNoticeData> list;
    public UserNoticeAdaptar(Context context, ArrayList<UserNoticeData> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public UserNoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_layout,parent,false);
        return new UserNoticeViewAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserNoticeViewAdapter holder, int position) {
        UserNoticeData currentItem = list.get(position);
        holder.deleteuserNoticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
        try {
            if(currentItem.getImage()!=null)
                Glide.with(context).load(currentItem.getImage()).into(holder.deleteuserNoticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class UserNoticeViewAdapter extends RecyclerView.ViewHolder {
        private TextView deleteuserNoticeTitle;
        private TextView date;
        private TextView time;
        private ImageView deleteuserNoticeImage;
        public UserNoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            deleteuserNoticeTitle =itemView.findViewById(R.id.deleteuserNoticeTitle);
            deleteuserNoticeImage =itemView.findViewById(R.id.deleteuserNoticeImage);
            date =itemView.findViewById(R.id.date);
            time =itemView.findViewById(R.id.time);
        }
    }
}