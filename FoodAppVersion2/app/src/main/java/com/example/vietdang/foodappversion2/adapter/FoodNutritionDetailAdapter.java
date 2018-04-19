package com.example.vietdang.foodappversion2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.detaifood.NutritionItemsView;
import com.example.vietdang.foodappversion2.model.detaifood.NutritionTypeView;
import com.example.vietdang.foodappversion2.model.detaifood.NutrtionTypeHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by vietdang on 10/22/2017.
 */

public class FoodNutritionDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<NutrtionTypeHolder>items= Collections.emptyList();

    public FoodNutritionDetailAdapter(List<NutrtionTypeHolder> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        switch (viewType){
            case NutrtionTypeHolder.TYPE_GROUP:{
                View itemView=inflater.inflate(R.layout.item_group_nutrition,parent,false);
                return new NutritionTypeViewHolder(itemView);
            }
            case NutrtionTypeHolder.TYPE_ITEM:{
                View itemView=inflater.inflate(R.layout.item_nutrition_detailfood,parent,false);
                return new NutritionFoodViewHolder(itemView);
            }
            default:throw  new IllegalStateException("ko biet truyen view type gi");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        switch (viewType){
            case NutrtionTypeHolder.TYPE_GROUP:{
                NutritionTypeView nutritionTypeView= (NutritionTypeView) items.get(position);
                NutritionTypeViewHolder nutritionTypeViewHolder= (NutritionTypeViewHolder) holder;
                nutritionTypeViewHolder.txtNutriontionType.setText(nutritionTypeView.getNutritionType());
                break;
            }
            case NutrtionTypeHolder.TYPE_ITEM:{
                NutritionItemsView nutritionItemsView= (NutritionItemsView) items.get(position);
                NutritionFoodViewHolder nutritionFoodViewHolder= (NutritionFoodViewHolder) holder;
                nutritionFoodViewHolder.txtNutritionName.setText(nutritionItemsView.getFoodDetail().getNutritionName());
                nutritionFoodViewHolder.txtNutritionUnit.setText(nutritionItemsView.getFoodDetail().getNutritionUnit());
                nutritionFoodViewHolder.txtNutritionValues.setText(String.valueOf(nutritionItemsView.getFoodDetail().getNutritionValue()));
                break;
            }
            default:throw  new IllegalStateException("ko biet truyen view type gi");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    private  static class NutritionTypeViewHolder extends RecyclerView.ViewHolder{
        TextView txtNutriontionType;
        public NutritionTypeViewHolder(View itemView) {
            super(itemView);
            txtNutriontionType=(TextView)itemView.findViewById(R.id.txt_group_nutrition_header);
        }

    }
    private static class NutritionFoodViewHolder extends RecyclerView.ViewHolder{
        TextView txtNutritionName,txtNutritionUnit,txtNutritionValues;
        public NutritionFoodViewHolder(View itemView) {
            super(itemView);
            txtNutritionName=(TextView)itemView.findViewById(R.id.txt_tpdd_nutrition_detail);
            txtNutritionUnit=(TextView)itemView.findViewById(R.id.txt_dv_nutrition_detail);
            txtNutritionValues=(TextView)itemView.findViewById(R.id.txt_hl_nutrition_detail);
        }
    }
}
