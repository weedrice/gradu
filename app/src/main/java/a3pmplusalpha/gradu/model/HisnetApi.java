package a3pmplusalpha.gradu.model;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class HisnetApi {
    private static HisnetApi INSTANCE;

    private String baseUrl = "https://hisnet.handong.edu/";
    public API api;

    private HisnetApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
        api = retrofit.create(API.class);
    }

    public static HisnetApi getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new HisnetApi();
        }

        return INSTANCE;
    }

    public void clearClient() {
        api = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build()
                .create(API.class);
    }

    public void setClient(OkHttpClient newClient) {
        api = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(newClient)
                .build()
                .create(API.class);
    }

    public interface API {

        @GET("haksa/record/HREC110M.php")
        Call<ResponseBody> getInfo();

        @FormUrlEncoded
        @POST("login/_login.php")
        Call<ResponseBody> login(
                @Field("id")
                String id,
                @Field("password")
                String password
        );
    }
}