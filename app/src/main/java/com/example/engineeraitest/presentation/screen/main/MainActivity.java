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
import com.example.engineeraitest.presentation.screen.main.adapter.UserAdapter;
import com.example.engineeraitest.presentation.screen.main.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    private UserAdapter mAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_users)
    RecyclerView mUsersRecyclerView;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        EngineerAiTestApp.getDependencyGraph().initMainComponent().inject(this);
        mPresenter.onAttach(this);

        setSupportActionBar(mToolbar);
        initUserList();
        mPresenter.getUsers();
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
                        if (!isInProgress()) mPresenter.getUsers();
                    }
                }
            }
        });
        mUsersRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showLoadingIndicator() {
        showProgress();
    }

    @Override
    public void hideLoadingIndicator() {
        hideProgress();
    }

    @Override
    public void usersLoadSuccess(List<User> userList) {
        mAdapter.addUserList(userList);
    }

    @Override
    public void usersLoadError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        EngineerAiTestApp.getDependencyGraph().releaseMainComponent();
        super.onDestroy();
    }
}
