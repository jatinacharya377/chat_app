package com.chat.myapplication.di;

import com.chat.myapplication.api.ChatAPI;
import com.chat.myapplication.api.ChatService;
import com.chat.myapplication.repository.AuthRepo;
import com.chat.myapplication.repository.ProfileRepo;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    public AuthRepo provideAuthRepo() {

        return new AuthRepo();
    }

    @Provides
    public ChatAPI provideChatAPI() {

        String baseUrl = "https://testa2.aisle.co/V1/";
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
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

    @Provides
    public ChatService provideChatService() {

        return new ChatService();
    }

    @Provides
    public ProfileRepo provideProfileRepo() {

        return new ProfileRepo();
    }
}
