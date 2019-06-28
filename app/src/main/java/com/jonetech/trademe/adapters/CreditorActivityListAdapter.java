package com.jonetech.trademe.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonetech.trademe.R;
import com.jonetech.trademe.model.CreditActivity;

import java.util.List;

public class CreditorActivityListAdapter extends RecyclerView.Adapter<CreditorActivityListAdapter.CreditActivityViewHolder> {

    private LayoutInflater layoutInflater;
    private List<CreditActivity> fCreditActivities;

    public CreditorActivityListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CreditActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View itemView = layoutInflater.inflate(R.layout.customer_activity,parent, false);

        return new CreditActivityViewHolder(itemView);
    }

    public CreditActivity getWordAtPosition(int position){ return fCreditActivities.get(position);}

    @Override
    public void onBindViewHolder(@NonNull CreditActivityViewHolder holder, int position) {

        if(fCreditActivities != null){
            CreditActivity current = fCreditActivities.get(position);

            holder.creditTitle.setText(current.getTitle());
            holder.creditAmount.setText(String.valueOf(current.getNew_bal()));
        }

    }

    @Override
    public int getItemCount() {

        if(fCreditActivities != null){
            return fCreditActivities.size();
        }else{
            return 0;
        }
    }

   public void setActivity(List<CreditActivity> activity){
        fCreditActivities = activity;
        notifyDataSetChanged();
    }

    public class CreditActivityViewHolder extends RecyclerView.ViewHolder {

        public final TextView creditTitle;
        public final TextView creditAmount;


        public CreditActivityViewHolder(View itemView) {
            super(itemView);

            creditTitle = itemView.findViewById(R.id.activity_name);
            creditAmount = itemView.findViewById(R.id.amount);

        }
    }

    public interface ClickListener{
        void onClickItem(View v, int positon);
    }
}
