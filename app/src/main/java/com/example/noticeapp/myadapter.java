package com.example.noticeapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

@SuppressWarnings("JavadocReference")
public class myadapter  extends RecyclerView.Adapter<myadapter.myviewholder>{

    ArrayList<model> nlist;

    public myadapter(ArrayList<model> nlist) {
        this.nlist = nlist;
    }




    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticelist,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.datetime.setText(nlist.get(position).getDate());
        holder.titlen.setText(nlist.get(position).getTitle());
        holder.des.setText(nlist.get(position).getDiscription());

        holder.titlen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.titlen.getContext(),FetchActivity.class);
                intent.putExtra("Title",nlist.get(position).getTitle());
                intent.putExtra("Discription",nlist.get(position).getDiscription());
                intent.putExtra("Date",nlist.get(position).getDate());
                intent.putExtra("File_Path",nlist.get(position).getFile_Path());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.titlen.getContext().startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return nlist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView datetime,titlen,des;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            datetime = itemView.findViewById(R.id.datetime);
            titlen = itemView.findViewById(R.id.titlen);
            des = itemView.findViewById(R.id.des);
        }

    }
}
