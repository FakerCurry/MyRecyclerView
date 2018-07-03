package com.sjw.myrecycleview.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sjw.myrecycleview.R;
import com.sjw.myrecycleview.bean.TestBean;
import com.sjw.myrecycleview.customrefrensh.BasicRecyViewHolder;
import com.sjw.myrecycleview.customrefrensh.HFSingleTypeRecyAdapter;


/**
 *  测试
 */
public class TestSingleFHFSingleTypeRecyAdapter extends HFSingleTypeRecyAdapter<TestBean, TestSingleFHFSingleTypeRecyAdapter.RecyViewHolder> {


    public TestSingleFHFSingleTypeRecyAdapter(int resId) {
        super(resId);
    }

    @Override
    public RecyViewHolder buildViewHolder(View itemView) {
        return new RecyViewHolder(itemView);
    }

    @Override
    public void bindDataToHolder(RecyViewHolder holder, TestBean s, int position) {
        holder.text.setText(s.getTitle());
        holder.btn.setText("按钮 "+position);
        holder.btn.setTag(position);
    }

    public static class RecyViewHolder extends BasicRecyViewHolder {

        TextView text;
        Button btn;

        public RecyViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.item_title);
            btn= (Button) itemView.findViewById(R.id.item_btn);
        }

    }
}
