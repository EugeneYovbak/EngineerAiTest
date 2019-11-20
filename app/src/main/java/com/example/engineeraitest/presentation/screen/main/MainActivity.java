package com.example.engineeraitest.presentation.screen.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.engineeraitest.R;
import com.example.engineeraitest.app.EngineerAiTestApp;
import com.example.engineeraitest.domain.model.User;
import com.example.engineeraitest.presentation.base.BaseActivity;
import com.example.engineeraitest.presentation.base.DataWrapper;
import com.example.engineeraitest.presentation.screen.main.adapter.UserAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private UserAdapter mAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_users)
    RecyclerView mUsersRecyclerView;

    @Inject
    MainViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EngineerAiTestApp.getDependencyGraph().initMainComponent().inject(this);
        setSupportActionBar(mToolbar);

        initUserList();

        mViewModel.getUsersData().observe(this, this::handleUsers);
        mViewModel.getUsers();
    }

    private void initUserList() {
        mAdapter = new UserAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mUsersRecyclerView.setLayoutManager(layoutManager);
        mUsersRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        if (!isInProgress()) mViewModel.getUsers();
                    }
                }
            }
        });

        mUsersRecyclerView.setAdapter(mAdapter);
    }

    private void handleUsers(DataWrapper<List<User>> dataWrapper) {
        switch (dataWrapper.getStatus()) {
            case LOADING:
                showProgress();
                break;
            case SUCCESS:
                hideProgress();
                mAdapter.addUserList(dataWrapper.getData());
                break;
            case ERROR:
                hideProgress();
                Toast.makeText(this, dataWrapper.getThrowable().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        EngineerAiTestApp.getDependencyGraph().releaseMainComponent();
        super.onDestroy();
    }
}
