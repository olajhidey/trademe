package com.jonetech.trademe.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonetech.trademe.R;
import com.jonetech.trademe.model.Creditor;

import java.util.List;

public class CreditorListAdapter extends RecyclerView.Adapter<CreditorListAdapter.CreditorViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Creditor> creditors;
    private static ClickListener sClickListener;

    public CreditorListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CreditorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);


        return new CreditorViewHolder(itemView);
    }

    public Creditor getWordAtPosition(int position) {
        return creditors.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditorViewHolder holder, int position) {

        if (creditors != null) {
            Creditor current = creditors.get(position);

            holder.creditorItemView.setText(current.getName());
        } else {
            holder.creditorItemView.setText("No creditor");
        }

    }

    @Override
    public int getItemCount() {

        if (creditors != null) {
            return creditors.size();
        } else {
            return 0;
        }

    }

    public void setCreditors(List<Creditor> creditorList){
        creditors = creditorList;
        notifyDataSetChanged();
    }

    public class CreditorViewHolder extends RecyclerView.ViewHolder {

        public final TextView creditorItemView;

        public CreditorViewHolder(View itemView) {
            super(itemView);

            creditorItemView = itemView.findViewById(R.id.customer_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }

    public void setOnClickListener(ClickListener listener){
        CreditorListAdapter.sClickListener = listener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);

    }
}
