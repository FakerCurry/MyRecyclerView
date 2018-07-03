package com.sjw.myrecycleview.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.sjw.myrecycleview.R;
import com.sjw.myrecycleview.adapter.PullToRefreshAdapter;
import com.sjw.myrecycleview.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerHasAnimateActivity extends AppCompatActivity {
    private static final int PAGE_SIZE = 6;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PullToRefreshAdapter mAdapter;

    private int mNextRequestPage = 1;
    List<TestBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_has_animate);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        addHeadView();
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();

    }
    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(RecyclerHasAnimateActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RecyclerHasAnimateActivity.this, "change complete", Toast.LENGTH_LONG).show();
            }
        });
        mAdapter.addHeaderView(headView);
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    //    1，首先创建一个Handler对象

    Handler handler2 = new Handler();

    //    2，然后创建一个Runnable对象
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            mNextRequestPage = 1;
            mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
            beanList = new ArrayList<>();
            beanList.clear();
            for (int a = 0; a < PAGE_SIZE; a++) {
                TestBean bean = new TestBean();
                bean.setTitle(mNextRequestPage + "aa" + a);
                beanList.add(bean);

            }


            setData(true, beanList);
            mAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);

        }

    };


    private void refresh() {

        handler2.postDelayed(runnable2, 1000);

    }

//    1，首先创建一个Handler对象

    Handler handler = new Handler();

    //    2，然后创建一个Runnable对象
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mNextRequestPage++;

            List<TestBean> beanList2 = new ArrayList<>();
            for (int a = 0; a < PAGE_SIZE; a++) {
                TestBean bean = new TestBean();
                bean.setTitle(mNextRequestPage + "bb" + a);
                beanList2.add(bean);

            }
            setData(false, beanList2);
        }

    };

    private void loadMore() {


//        3，使用PostDelayed方法，两秒后调用此Runnable对象
        handler.postDelayed(runnable, 1000);


    }

    private void setData(boolean isRefresh, List data) {

        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

}
