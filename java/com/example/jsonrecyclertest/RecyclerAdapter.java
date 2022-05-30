package com.example.jsonrecyclertest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {

    private final ArrayList<Student> studentList;
    private final ArrayList<Student> studentListFull;

    Context context;


    public RecyclerAdapter(Context ct, ArrayList<Student> studentList) {
        this.studentList = studentList;
        context = ct;
        studentListFull = new ArrayList<>(studentList);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTxt;
        private final TextView ageTxt;
        private final TextView emailTxt;

        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.textView);
            ageTxt = itemView.findViewById(R.id.textView2);
            emailTxt = itemView.findViewById(R.id.textView3);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = studentList.get(position).getName();
        holder.nameTxt.setText(name);
        String ageTxt = studentList.get(position).getAge();
        holder.ageTxt.setText(ageTxt);
        String emailTxt = studentList.get(position).getEmail();
        holder.emailTxt.setText(emailTxt);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", name);
                intent.putExtra("age", ageTxt);
                intent.putExtra("email", emailTxt);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    @Override
    public Filter getFilter() {
        return studentFilter;
    }

    private final Filter studentFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Student> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(studentListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Student item : studentListFull) {
                    if(item.getName().toLowerCase().contains(filterPattern) ||
                    item.getAge().toLowerCase().contains(filterPattern) ||
                    item.getEmail().toLowerCase().contains(filterPattern)) {
                         filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            studentList.clear();
            studentList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
