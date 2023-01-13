package com.forensic.mart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.forensic.mart.Activities.Edit_user_profile;
import com.forensic.mart.BeanFiles.Education_bean;
import com.forensic.mart.R;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Edit_education_adapter extends RecyclerView.Adapter<Edit_education_adapter.InnerClassViewHolder> {
    ArrayList<Education_bean.UserBean> aryList;
    static Context ctx;
    Edit_user_profile edit_user_profile;
    public Edit_education_adapter(ArrayList<Education_bean.UserBean> data, Edit_user_profile edit_user_profile) {
        aryList = data;
        ctx = edit_user_profile;
    }

    @NonNull
    @Override
    public Edit_education_adapter.InnerClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.edit_user_profile_skill, parent, false);
        return new Edit_education_adapter.InnerClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassViewHolder holder, int position) {
        Education_bean.UserBean user = aryList.get(position);
        holder.level.setHint("Education Level");
        holder.from.setHint("Starting Date");
        holder.to.setHint("Passing Date");
        holder.school.setHint("Institute Name");
        holder.level.setHelperText("Enter/Change your Education Level here");
        holder.from.setHelperText("Enter/Change your Starting Date Here\n Eg. YYYY/MM/DD");
        holder.to.setHelperText("Enter/Change your Passing Date Here\n Eg. YYYY/MM/DD");
        holder.school.setHelperText("Enter/Change your Institute Name Here");
        holder.level_editText.setText(user.getDegree());
        holder.from_editText.setText(user.getFromDate());
        holder.to_editText.setText(user.getToDate() );
        holder.school_editText.setText(user.getSchool());
        holder.update.setOnClickListener(v->{
            holder.update.setText("Updating");
            holder.update.setClickable(false);
            SetDataToDatabase con = Conn.doConnect();
            con.EditProfile_update_edu("update_edu", user.getId(), holder.school_editText.getText().toString(), holder.from_editText.getText().toString(), holder.to_editText.getText().toString(),
                    holder.level_editText.getText().toString(), "", new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            //edit_user_profile.fetchData();
                            ((Edit_user_profile)ctx).fetchData();
                            holder.update.setText("Update");
                            holder.update.setClickable(true);
                            Toast.makeText(Edit_education_adapter.ctx, ""+serverResponse.msg, Toast.LENGTH_SHORT).show();

                        }
                        @Override
                        public void failure(RetrofitError error) {
                            holder.update.setText("Update");
                            holder.update.setClickable(true);
                            if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                                Toast.makeText(Edit_education_adapter.ctx, "No Internet connection found.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Edit_education_adapter.ctx, error.toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        });

        holder.delete.setOnClickListener(v->{
            holder.delete.setText("Deleting");
            holder.delete.setClickable(false);
            SetDataToDatabase con = Conn.doConnect();
            con.EditProfile_remove_edu("remove_edu", user.getId(), new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            ((Edit_user_profile)ctx).fetchData();
                            holder.delete.setText("Delete");
                            holder.delete.setClickable(true);
                            Toast.makeText(Edit_education_adapter.ctx, ""+serverResponse.msg, Toast.LENGTH_SHORT).show();

                        }
                        @Override
                        public void failure(RetrofitError error) {
                            holder.delete.setText("Delete");
                            holder.delete.setClickable(true);
                            if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                                Toast.makeText(Edit_education_adapter.ctx, "No Internet connection found.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Edit_education_adapter.ctx, error.toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return aryList.size();
    }

    public static class InnerClassViewHolder extends RecyclerView.ViewHolder {
       TextInputLayout level,from,to,school;
       TextInputEditText level_editText,from_editText,to_editText,school_editText;
       Button update,delete;
        public InnerClassViewHolder(@NonNull View itemView) {
            super(itemView);

            level = itemView.findViewById(R.id.edit_level_user_profile_skill);
            to = itemView.findViewById(R.id.edit_end_user_profile_skill);
            from = itemView.findViewById(R.id.edit_start_user_profile_skill);
            school = itemView.findViewById(R.id.edit_other_user_profile_skill);
            level_editText = itemView.findViewById(R.id.edit_level_user_profile_skill_editable);
            from_editText = itemView.findViewById(R.id.edit_start_user_profile_skill_editable);
            to_editText = itemView.findViewById(R.id.edit_end_user_profile_skill_editable);
            school_editText = itemView.findViewById(R.id.edit_other_user_profile_skill_editable);
            update = itemView.findViewById(R.id.btn_update_edit_user_profile_skill);
            delete = itemView.findViewById(R.id.btn_delete_edit_user_profile_skill);
        }
    }
}
