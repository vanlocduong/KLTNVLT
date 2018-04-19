package com.example.vietdang.foodappversion2.view.searchfood;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.searchfood.FoodSearch;
import com.example.vietdang.foodappversion2.presenter.foodsearch.FoodSearchPre;
import com.example.vietdang.foodappversion2.presenter.foodsearch.IFoodSearchPre;
import com.example.vietdang.foodappversion2.view.detailfood.DetailFoodFragment;

import java.util.List;

public class SearchFoodActivity extends AppCompatActivity implements ISearchFoodView {

    //view
    private LinearLayout layoutSearchfood;
    private EditText edtSearch;
    private ImageView imgSearch;
    private RecyclerView recyclerView;

    private SimpleItemRecyclerViewAdapter mAdapter;
    private List<FoodSearch> mFoods;
    //
    private IFoodSearchPre mIFoodHistoryPre;

    //
    private FragmentManager fmgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        layoutSearchfood = (LinearLayout) findViewById(R.id.layout_searchfood);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSearchActivity);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Search Food");
        setUpRecyclerView();
        mIFoodHistoryPre = new FoodSearchPre(this);
        edtSearch = (EditText) findViewById(R.id.floating_searchfood_view);
        imgSearch = (ImageView) findViewById(R.id.img_search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIFoodHistoryPre.onLoadFoodHistorySuccess(edtSearch.getText().toString());
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_searchfood);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showData(List<FoodSearch> foodSearchList) {
        mAdapter = new SimpleItemRecyclerViewAdapter(foodSearchList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorLoadData(String message) {
        ShowSnackbar(message);
    }

    public void ShowSnackbar(String title) {
        Snackbar snackbar = Snackbar.make(layoutSearchfood, title, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        private List<FoodSearch> mValues;
//        private CustomFilter mFilter;

        public SimpleItemRecyclerViewAdapter(List<FoodSearch> items) {
            mValues = items;
//            mFilter = new CustomFilter(SimpleItemRecyclerViewAdapter.this);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_searchfood, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            FoodSearch current = mValues.get(position);
            holder.setData(current, position);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView title, description;
            ImageView imgThumb;
            int position;
            FoodSearch current;
            CardView cardView;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.txtname_searchfood);
                description = (TextView) itemView.findViewById(R.id.tv_motasearchfood);
                imgThumb = (ImageView) itemView.findViewById(R.id.img_item_searchfood);
                cardView = (CardView) itemView.findViewById(R.id.cardviewfood);
            }

            public void setData(final FoodSearch current, int position) {
                this.title.setText(current.getFoodName());
                this.description.setText(current.getFoodDescription());
                Glide.with(SearchFoodActivity.this).load(current.getFoodImages())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgThumb);
                this.position = position;
                this.current = current;

                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerView.setVisibility(View.INVISIBLE);
                        changeFragment(current);
                    }
                });

            }
        }

        private void changeFragment(FoodSearch foodSearch) {
            Fragment fragment = new DetailFoodFragment();
            FragmentManager fmgr = getSupportFragmentManager();
            FragmentTransaction ft = fmgr.beginTransaction();
            ft.add(R.id.activity_search, fragment);
            ft.addToBackStack(fragment.getClass().getSimpleName());
            //guu thong tin thuc pham qua cho detail food
            Bundle bundle = new Bundle();
            bundle.putString("FoodName", foodSearch.getFoodName());
            bundle.putString("FoodDes", foodSearch.getFoodDescription());
            bundle.putString("FoodImg", foodSearch.getFoodImages());
            bundle.putInt("FoodId", foodSearch.getFoodId());
            fragment.setArguments(bundle);
            ft.commit();
        }
    }
}
