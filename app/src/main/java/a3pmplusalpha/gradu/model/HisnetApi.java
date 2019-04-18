package a3pmplusalpha.gradu.model;

import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class HisnetApi {
    private static HisnetApi INSTANCE;

    private String baseUrl = "https://hisnet.handong.edu/";
    public API api;

    private HisnetApi(Context context) {
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        OkHttpClient okClient = new OkHttpClient().newBuilder()
                .cookieJar(cookieJar)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .build();

        api = retrofit.create(API.class);
    }

    public static HisnetApi getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new HisnetApi(context);
        }

        return INSTANCE;
    }

    public interface API {
        @POST("haksa/record/HREC110M.php")
        Call<ResponseBody> getInfo();

        @POST("haksa/hakjuk/HHAK110M.php")
        Call<ResponseBody> getAccountInfo();

        @FormUrlEncoded
        @POST("login/_login.php")
        Call<ResponseBody> login(
                @Field("id")
                String id,
                @Field("password")
                String password,
                @Field("Language")
                String language
        );
    }

}