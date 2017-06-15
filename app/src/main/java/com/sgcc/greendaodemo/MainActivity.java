package com.sgcc.greendaodemo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sgcc.greendaodemo.dto.User;
import com.sgcc.greendaodemo.dto.UserDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.button)
    Button button;
    private boolean showMenu = true;

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.lay_refresh)
    SwipeRefreshLayout layRefresh;
    @BindView(R.id.main_constrain)
    ConstraintLayout mainConstrain;
    @BindView(R.id.fBtn)
    FloatingActionButton fBtn;
    private UserDao userDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();

        userDao = getUserDao();
        user = new User();
        user.setAuto_login(true);
        user.setName("miao_wenlong");
        user.setPwd("123456");
        user.setRem_pwd(true);
        userDao.insert(user);
        userDao.insert(user);

    }

    private Activity getContext() {
        return this;
    }

    private UserDao getUserDao() {
        return ((MyApp) this.getApplicationContext()).getDaoSession().getUserDao();
    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUser.setHasFixedSize(true);
        rvUser.setLayoutManager(linearLayoutManager);
        setData();
    }

    private void setData() {
        Cursor cursor = ((MyApp) this.getApplicationContext()).getDb().query(getUserDao().getTablename(),
                getUserDao().getAllColumns(),null,null,null,null,null);
        List list = getUserDao().queryBuilder().list();
        Log.e(TAG, list.toString());
    }


    @OnClick({R.id.fBtn,R.id.btn_add, R.id.btn_clear, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                userDao.insert(user);
                break;
            case R.id.btn_clear:
                break;
            case R.id.button:
                break;
            case R.id.fBtn:
                btnAdd.setVisibility(showMenu ? View.VISIBLE : View.GONE);
                btnClear.setVisibility(showMenu ? View.VISIBLE : View.GONE);
                button.setVisibility(showMenu ? View.VISIBLE : View.GONE);
                showMenu = !showMenu;
                break;
        }
    }
}
