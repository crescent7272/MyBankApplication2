package com.example.mybankapplication.cleancode.statementsScreen;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapplication.R;
import com.example.mybankapplication.util.Utils;

import java.util.List;

import static com.example.mybankapplication.util.Utils.formatCurrency;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.MyViewHolder> {

    private List<StatementList> statementList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        /*
        * "title": "Pagamento",
            "desc": "Conta de luz",
            "date": "2018-08-15",
            "value": -50
        *
        *
        * */
        public TextView title, desc, date, value;

        public MyViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title);
            desc  = view.findViewById(R.id.conta);
            date  = view.findViewById(R.id.date);
            value = view.findViewById(R.id.amount);
        }
    }


    public StatementAdapter(List<StatementList> statementList) {
        this.statementList = statementList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        StatementList statement = statementList.get(position);

        holder.title.setText(statement.getTitle());
        holder.desc.setText(statement.getDesc());
        holder.date.setText(Utils.formatDate(statement.getDate()));
        holder.value.setText("R$"+formatCurrency(statement.getValue()));
    }




    @Override
    public int getItemCount() {
        return statementList.size();
    }
}