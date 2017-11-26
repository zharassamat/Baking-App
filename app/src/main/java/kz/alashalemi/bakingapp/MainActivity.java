package kz.alashalemi.bakingapp;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import kz.alashalemi.bakingapp.adapter.FoodAdapter;
import kz.alashalemi.bakingapp.data.BakingContract;
import kz.alashalemi.bakingapp.model.Food;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FoodAdapter.ListItemClickListener{


    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private FoodAdapter mAdapter;

    private RecyclerView mFoodListView;

    private ArrayList<Food> mFoodList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFoodListView = (RecyclerView) findViewById(R.id.rv_food_list);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns());

        mFoodListView.setLayoutManager(layoutManager);

        mFoodListView.setHasFixedSize(true);

        mAdapter = new FoodAdapter(mFoodList, this);

        mFoodListView.setAdapter(mAdapter);


        loadData();

       /* ContentValues contentValues = new ContentValues();
        contentValues.put(BakingContract.BakingEntry.COLUMN_FOOD_ID, "2");
        contentValues.put(BakingContract.BakingEntry.COLUMN_FOOD_IMAGE, "no image");
        contentValues.put(BakingContract.BakingEntry.COLUMN_FOOD_NAME, "kuyrdak");
        contentValues.put(BakingContract.BakingEntry.COLUMN_FOOD_SERVINGS, "no servings");

        Uri uri = getContentResolver().insert(BakingContract.BakingEntry.CONTENT_URI,contentValues);

        if(uri != null) {
            Toast.makeText(this,uri.toString(), Toast.LENGTH_LONG).show();
        }*/
    }

    private void loadData() {
        BakingApp.getAPI().loadBakerList().enqueue(new Callback<ArrayList<Food>>() {
            @Override
            public void onResponse(Call<ArrayList<Food>> call, Response<ArrayList<Food>> response) {
                if (response.body() != null) {
                    try {

                        mFoodList.clear();
                        mFoodList.addAll(response.body());
                        mAdapter.setFoodData(mFoodList);
                        Log.e("sj", response.body().get(0).getName() + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                        showErrorMessage(e.getMessage());
                    }
                } else {
                    showErrorMessage("is null");
                }

                mLoadingIndicator.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ArrayList<Food>> call, Throwable t) {
                showErrorMessage(t.getMessage());
                mLoadingIndicator.setVisibility(View.INVISIBLE);
            }
        });

    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }


    private void showErrorMessage(String msg) {
        Log.e("sj",msg);
    }

    @Override
    public void onListItemClick(int position) {

    }
}
