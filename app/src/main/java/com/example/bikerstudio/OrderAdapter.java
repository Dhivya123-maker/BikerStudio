package com.example.bikerstudio;

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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{
    private Context context;
    private List<OrderModel> orderModelList;

    public static OrderAdapter.OnItemClickListener mListener1;



    public interface OnItemClickListener{
        void onItemClick1(int position);


    }

    public void setOnItemClickListener(OrderAdapter.OnItemClickListener listener1){

        mListener1 = listener1;


    }


    public OrderAdapter(Context context, List<OrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
    }





    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater =LayoutInflater.from(context);
        v= inflater.inflate(R.layout.order, parent,false);

        return new OrderAdapter.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        holder.order_id.setText(orderModelList.get(position).getOrder_id());
        holder.w_order.setText(orderModelList.get(position).getW_order());
        holder.d_date.setText(orderModelList.get(position).getD_date());





    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView order_id, w_order,d_date,view;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            order_id = itemView.findViewById(R.id.order_id);
            w_order = itemView.findViewById(R.id.w_order);
            d_date = itemView.findViewById(R.id.d_date);
            view = itemView.findViewById(R.id.view);





            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    Intent i = new Intent(view.getContext(), Order_Cfrm.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    i.putExtra("o_id",orderModelList.get(position).getOrder_id());
                    view.getContext().startActivity(i);


                }
            });





        }
    }
}
