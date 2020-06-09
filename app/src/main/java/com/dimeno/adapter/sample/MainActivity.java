package com.dimeno.adapter.sample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dimeno.adapter.callback.OnItemClickCallback;
import com.dimeno.adapter.callback.OnItemLongClickCallback;
import com.dimeno.adapter.sample.adapter.UserAdapter;
import com.dimeno.adapter.sample.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private UserAdapter mAdapter;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#33888888"));
        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = parent.getChildAt(i);
                    if (childAt != null) {
                        int left = childAt.getLeft();
                        int right = childAt.getRight();
                        int top = childAt.getBottom();
                        int bottom = top + 1;
                        c.drawRect(new Rect(left, top, right, bottom), mPaint);
                    }
                }
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, 1);
            }
        });
        init();
    }

    private void init() {
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new UserEntity("张三", i + 1));
        }
        mAdapter = new UserAdapter(list);
        mAdapter.setOnClickCallback(new OnItemClickCallback() {
            @Override
            public void onItemClick(View itemView, int position) {
                UserEntity entity = mAdapter.getDatas().get(position);
                Toast.makeText(itemView.getContext(), "click -> name : " + entity.name + " age : " + entity.age, Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnLongClickCallback(new OnItemLongClickCallback() {
            @Override
            public void onItemLongClick(View itemView, int position) {
                UserEntity entity = mAdapter.getDatas().get(position);
                Toast.makeText(itemView.getContext(), "long click -> name : " + entity.name + " age : " + entity.age, Toast.LENGTH_SHORT).show();
            }
        });
        mRecycler.setAdapter(mAdapter);
    }
}