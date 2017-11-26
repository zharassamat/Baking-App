package kz.alashalemi.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kz.alashalemi.bakingapp.R;
import kz.alashalemi.bakingapp.model.Food;

/**
 * Created by zharas on 11/26/17.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final ListItemClickListener mOnClickListener;

    private ArrayList<Food> mFoodList;

    public interface ListItemClickListener {
        void onListItemClick(int position);
    }

    public FoodAdapter(ArrayList<Food> foodList, ListItemClickListener listener) {
        mFoodList = foodList;
        mOnClickListener = listener;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_food;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem,parent,shouldAttachToParentImmediately);

        FoodViewHolder viewHolder = new FoodViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {

        holder.tvFoodName.setText(mFoodList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(mFoodList == null) {
            return 0;
        }
        return mFoodList.size();
    }

    public void setFoodData(ArrayList<Food> foodData) {
        mFoodList = foodData;
        notifyDataSetChanged();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvFoodName;

        public FoodViewHolder(View itemView) {
            super(itemView);

            tvFoodName = (TextView) itemView.findViewById(R.id.tv_food_name);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
