package com.example.engineeraitest.presentation.screen.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.engineeraitest.R;
import com.example.engineeraitest.app.di.GlideApp;
import com.example.engineeraitest.domain.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private static final int ITEMS_ROW_COUNT = 2;

    private List<User> mUserList = new ArrayList<>();

    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void addUserList(List<User> userList) {
        mUserList.addAll(userList);
        notifyItemRangeChanged(mUserList.size() - userList.size(), userList.size());
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_userName)
        TextView mUserNameTextView;
        @BindView(R.id.iv_userImage)
        ImageView mUserImageView;
        @BindView(R.id.iv_oddItem)
        ImageView mOddItemImageView;
        @BindView(R.id.rv_items)
        RecyclerView mItemsRecyclerView;

        UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(User user) {

            GlideApp.with(itemView.getContext())
                    .load(user.getImage())
                    .placeholder(R.color.color_placeholder)
                    .error(R.color.color_placeholder)
                    .apply(RequestOptions.circleCropTransform())
                    .into(mUserImageView);

            mUserNameTextView.setText(user.getName());

            if (user.getItems().size() % 2 == 0) {
                mOddItemImageView.setVisibility(View.GONE);
                setItems(user.getItems());
            } else {
                mOddItemImageView.setVisibility(View.VISIBLE);

                GlideApp.with(itemView.getContext())
                        .load(user.getItems().get(0))
                        .placeholder(R.color.color_placeholder)
                        .error(R.color.color_placeholder)
                        .into(mOddItemImageView);
                setItems(user.getItems().subList(1, user.getItems().size()));
            }
        }

        private void setItems(List<String> items) {
            mItemsRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), ITEMS_ROW_COUNT));
            ItemAdapter itemAdapter = new ItemAdapter();
            mItemsRecyclerView.setAdapter(itemAdapter);
            itemAdapter.setItemList(items);
        }
    }
}
