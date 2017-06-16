package com.sgcc.greendaodemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

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
    private List<User> list;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();

        userDao = getUserDao();
        user = new User();
        user.set_id(null);
        user.setAuto_login(true);
        user.setName("miao_wenlong");
        user.setPwd("123456");
        user.setRem_pwd(true);
        userDao.insert(user);
        user = new User(null,"shi_shenyan","123456",false,false);
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

        layRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list = getUserDao().queryBuilder().list();
                adapter.notifyDataSetChanged();
                layRefresh.setRefreshing(false);
            }
        });
    }

    private void setData() {
       /* Cursor cursor = ((MyApp) this.getApplicationContext()).getDb().query(getUserDao().getTablename(),
                getUserDao().getAllColumns(), null, null, null, null, null);*/
        list = getUserDao().queryBuilder().list();
        Log.e(TAG, list.toString());

        adapter = new UserAdapter();
        rvUser.setAdapter(adapter);
        rvUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

    }

    class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getContext()).
                    inflate(R.layout.item_user, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(list.get(position).getName());
            holder.checkBox.setChecked(list.get(position).getRem_pwd());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            CheckBox checkBox;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv_username);
                checkBox = (CheckBox) itemView.findViewById(R.id.cb_pwd);
            }
        }
    }

    @OnClick({R.id.fBtn, R.id.btn_add, R.id.btn_clear, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                user.set_id(SystemClock.currentThreadTimeMillis()%100);
                userDao.insert(user);
                list.add(user);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_clear:
                userDao.deleteAll();
                list.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.button:
                user.set_id(SystemClock.currentThreadTimeMillis()%100);
                user.setName("caiji");
                userDao.update(user);
                list = getUserDao().queryBuilder().list();
                adapter.notifyDataSetChanged();
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
