package com.example.vietdang.foodappversion2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.foodhistory.FoodHistory;
import com.example.vietdang.foodappversion2.presenter.history.IFoodHistoryPre;

import java.util.List;

/**
 * Created by vietdang on 1/16/2018.
 */

public class RecyclerViewFoodHistoryAdapter extends RecyclerView.Adapter<RecyclerViewFoodHistoryAdapter.MyView> {
    private List<FoodHistory> list;
    private Context mContext;
    private IFoodHistoryPre foodHistoryPre;

    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener{
        private APIService mService= ApiUtil.getAPIService();
        private ImageView imgView;
        private TextView txt_name_history;
        private TextView txtXoa;
        private FoodHistory foodHistory;
        int fposition;

        public MyView(View view) {
            super(view);
            imgView = (ImageView) view.findViewById(R.id.item_img_food_dish);
            txt_name_history = (TextView) view.findViewById(R.id.txt_item_namefood_history);
            txtXoa=(TextView)view.findViewById(R.id.item_foodhistory_delete);
        }

        public void setData(FoodHistory foodHistory,int fposition) {
            this.foodHistory=foodHistory;
            this.fposition=fposition;
            txt_name_history.setText(foodHistory.getFoodName());
            Glide.with(mContext).load(foodHistory.getFoodImages())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgView);
        }

        public void setListener(){
            txtXoa.setOnClickListener(MyView.this);
        }

        @Override
        public void onClick(View v) {
            removeItem(fposition,foodHistory.getFoodHistoryId());
        }
    }
    public void removeItem(int position,int foodHistoryID) {
        list.remove(position);
        notifyItemRemoved(position);
        foodHistoryPre.DeleteFoodHistory(foodHistoryID);
    }

    public RecyclerViewFoodHistoryAdapter(Context context, List<FoodHistory> horizontalList, IFoodHistoryPre foodHistoryPre) {
        this.mContext = context;
        this.list = horizontalList;
        this.foodHistoryPre=foodHistoryPre;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_history_layout, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, int position) {
        holder.setData(list.get(position),position);
        holder.setListener();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
