package com.hqs.common.view.pageview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hqs.common.utils.ActivityUtil;
import com.hqs.common.utils.DensityUtils;
import com.hqs.common.utils.ScreenUtils;
import com.hqs.common.view.QPageView;

public class TestActivity extends AppCompatActivity {


    private QPageView pageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ActivityUtil.hideActionBar(this);

        pageView = (QPageView) findViewById(R.id.pageView);
        pageView.setSeparatorColor(Color.rgb(15, 9, 7));
        pageView.setSeparatorWidth(DensityUtils.dp2px(this, 22));

        pageView.setAdapter(new RecyclerView.Adapter<TestViewHolder>() {
            @Override
            public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.view_holder_test, null);

                return new TestViewHolder(view);
            }

            @Override
            public void onBindViewHolder(TestViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });
    }


    class TestViewHolder extends RecyclerView.ViewHolder {

        ListView lv;
        ImageView back;

        public TestViewHolder(View itemView) {
            super(itemView);
            lv = itemView.findViewById(R.id.list);

            itemView.setBackgroundColor(Color.WHITE);

            back = itemView.findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TestActivity.this.finish();
                }
            });

            lv.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return 30;
                }

                @Override
                public Object getItem(int i) {
                    return null;
                }

                @Override
                public long getItemId(int i) {
                    return 0;
                }

                @Override
                public View getView(int i, View view, ViewGroup viewGroup) {

                    if (view == null){
                        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
                        view = layoutInflater.inflate(R.layout.list_item, null);
                    }

                    TextView tv = view.findViewById(R.id.text);
                    tv.setText("list-" + i);
                    return view;
                }
            });

        }
    }
}
