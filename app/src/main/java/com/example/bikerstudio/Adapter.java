package com.example.bikerstudio;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<BookingModel> bookingModelList;

    public static OnItemClickListener mListener;




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }


    public Adapter(Context context, List<BookingModel> bookingModelList) {
        this.context = context;
        this.bookingModelList = bookingModelList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater =LayoutInflater.from(context);
        v= inflater.inflate(R.layout.booking, parent,false);

        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



//            holder.id.setText(bookingModelList.get(position).getId());
            holder.name.setText(bookingModelList.get(position).getName());
            holder.dis.setText(bookingModelList.get(position).getDis());
            holder.price.setText(bookingModelList.get(position).getPrice());






    }

    @Override
    public int getItemCount() {
        return bookingModelList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dis,name,price;
        Button add_btn;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            dis= itemView.findViewById(R.id.description);
            add_btn = itemView.findViewById(R.id.add);



            add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    add_btn.setVisibility(View.INVISIBLE);

                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }

                }
            });




        }
    }

}

