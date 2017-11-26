package kz.alashalemi.bakingapp;

import android.app.Application;
import android.content.Context;

import java.io.IOException;

import kz.alashalemi.bakingapp.utility.BakingApi;
import kz.alashalemi.bakingapp.utility.NetworkUtils;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zharas on 11/18/17.
 */

public class BakingApp extends Application {

    private static BakingApi api;

    public static BakingApp get(Context context) {
        return (BakingApp) context.getApplicationContext();
    }

    public static BakingApi getAPI() {
        if (api == null) {
            // We need client for debugging
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            OkHttpClient.Builder httpClient =
                    new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    return chain.proceed(original);
                }
            });

            api = new Retrofit.Builder()
                    .baseUrl(NetworkUtils.BAKING_BASE_URL)
                    .client(client)
                    .client(httpClient.build())
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(BakingApi.class);
        }
        return api;
    }

}
