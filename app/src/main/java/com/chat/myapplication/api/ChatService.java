package com.chat.myapplication.api;

import com.chat.myapplication.di.DaggerApiComponent;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatService {

    @Inject
    public ChatAPI api;

    public ChatAPI createChatAPI(String authToken) {

        String baseUrl = "https://testa2.aisle.co/V1/";
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", authToken)
                    .addHeader("Cookie", "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720")
                    .build();
            return chain.proceed(request);
        }).addInterceptor(logging).build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ChatAPI.class);
    }

    public void init() {

        DaggerApiComponent.create().inject(this);
    }
}
