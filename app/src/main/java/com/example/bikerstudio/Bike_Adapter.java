package com.example.bikerstudio;


import static com.example.bikerstudio.OrderAdapter.mListener1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Bike_Adapter extends RecyclerView.Adapter<Bike_Adapter.MyViewHolder> {

    private Context context;
    List<BikeViewModel> bikeViewModelList;
    public static Bike_Adapter.OnItemClickListener mListener;

    public static Bike_Adapter.OnItemClickListener4 mlistener4;
    public static Bike_Adapter.OnItemClickListener5 mlistener5;

    public void setOnItemClickListener5( Bike_Adapter.OnItemClickListener5 listener5) {


        mlistener5 = listener5;
    }




    public interface OnItemClickListener{
        void onItemClick(int position);

    }
    public interface OnItemClickListener4{

        void onItemClick4(int position);
    }

    public interface OnItemClickListener5{

        void onItemClick5(int position);
    }

    public void setOnItemClickListener4(Bike_Adapter.OnItemClickListener4 listener4){


        mlistener4 = listener4;



    }

    public void setOnItemClickListener(Bike_Adapter.OnItemClickListener listener){

        mListener = listener;




    }




    public Bike_Adapter(Context context, List<BikeViewModel> bikeViewModelList) {
        this.context = context;
        this.bikeViewModelList = bikeViewModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view,parent,false);


        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {






        holder.bike_num.setText("Bike No: "+bikeViewModelList.get(position).getBike_num());
        holder.bike_model.setText(bikeViewModelList.get(position).getBike_model());
        holder.year.setText("Year: "+bikeViewModelList.get(position).getYear());
        holder.bike_name.setText(bikeViewModelList.get(position).getBike_name());
//        holder.ins_no.setText("Insurance No: "+bikeViewModelList.get(position).getIns_no());
//        holder.ins_f_date.setText("Ins From Date: "+bikeViewModelList.get(position).getIns_f_date());
//        holder.ins_to_date.setText("Ins to Date: "+bikeViewModelList.get(position).getIns_to_date());
        holder.kms.setText("Running: "+bikeViewModelList.get(position).getKms());
        holder.version.setText("Version: "+bikeViewModelList.get(position).getVersion());



        




    }

    @Override
    public int getItemCount() {
        return bikeViewModelList.size();
    }



    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bike_num,bike_model,year,bike_name,ins_no,ins_f_date,ins_to_date,kms,version,btn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);




         bike_num =itemView.findViewById(R.id.bike_num);
         bike_model =itemView.findViewById(R.id.bike_model);
         year =itemView.findViewById(R.id.year);
         bike_name =itemView.findViewById(R.id.bike_name);
//         ins_no =itemView.findViewById(R.id.ins_no);
//         ins_f_date=itemView.findViewById(R.id.ins_f_date);
//         ins_to_date =itemView.findViewById(R.id.ins_to_date);
         kms=itemView.findViewById(R.id.kms);
         version=itemView.findViewById(R.id.version);
         btn = itemView.findViewById(R.id.edit_btn);



            if (bikeViewModelList.get(0).getVname() != null)
            {
                btn.setVisibility(View.GONE);
            }

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if (mlistener4 != null) {
                     int position = getAdapterPosition();
                     if (position != RecyclerView.NO_POSITION) {
                         mlistener4.onItemClick4(position);

                     }

                 }


                 if (mlistener5 != null) {
                     int position = getAdapterPosition();
                     if (position != RecyclerView.NO_POSITION) {
                         mlistener5.onItemClick5(position);

                     }

                 }
             }
         });




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

