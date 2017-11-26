package kz.alashalemi.bakingapp.utility;

import java.util.ArrayList;

import kz.alashalemi.bakingapp.model.Food;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zharas on 11/18/17.
 */

public interface BakingApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Food>> loadBakerList();

}
