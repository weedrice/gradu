package a3pmplusalpha.gradu.data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface HisnetApi {
    @POST("./login.php")
    Call<Account> login(
            @Field("id") String id,
            @Field("password") String password
    );

}
