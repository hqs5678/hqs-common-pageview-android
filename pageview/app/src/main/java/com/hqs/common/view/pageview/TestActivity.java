package com.hqs.common.view.pageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hqs.common.view.QPageView;

public class TestActivity extends AppCompatActivity {


    private QPageView pageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        pageView = (QPageView) findViewById(R.id.pageView);
        pageView.setAdapter(new RecyclerView.Adapter<TestViewHolder>() {
            @Override
            public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(TestViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }


    class TestViewHolder extends RecyclerView.ViewHolder {

        public TestViewHolder(View itemView) {
            super(itemView);
        }
    }
}
