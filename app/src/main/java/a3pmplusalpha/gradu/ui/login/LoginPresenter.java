package a3pmplusalpha.gradu.ui.login;

import android.util.Log;

import java.util.HashSet;

import a3pmplusalpha.gradu.model.AddCookieIntercepter;
import a3pmplusalpha.gradu.model.HisnetApi;
import a3pmplusalpha.gradu.model.ReceivedCookieIntercepter;
import a3pmplusalpha.gradu.model.repository.Local.preference.GraduPreference;
import androidx.databinding.ObservableBoolean;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private ObservableBoolean isLoading = new ObservableBoolean();
    private boolean isSaveId;
    private boolean isAlwaysLogin;
    private HisnetApi client;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        client = HisnetApi.getInstance();
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    @Override
    public void setSaveId() {
        isSaveId = !isSaveId;
        view.changeIdDrawable(isSaveId);
    }

    @Override
    public void setAlwaysLogin() {
        isAlwaysLogin = !isAlwaysLogin;
        view.changeLoginDrawable(isAlwaysLogin);
    }

    private void saveIdPref(String id) {
        GraduPreference.getSharedPreferences((LoginActivity)view)
                .putPrefString(GraduPreference.PREF_NAME_ID, id);
    }

    private void savePwPref(String pw) {
        GraduPreference.getSharedPreferences((LoginActivity)view)
                .putPrefString(GraduPreference.PREF_NAME_PASSWORD, pw);
    }

    @Override
    public void logIn(String id, String pw) {
        // TODO: Login Process 처리하기
        setIsLoading(true);
        client.api.login(id, pw).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(!response.body().string().contains("alert")) {
                        Log.d("Response: ", response.body().string());
                        //TODO: Login 성공 시 Data 받아오는 과정 추가
                        setCookies();
                        setIsLoading(false);
                    } else {
                        client.clearClient();
                        view.loginFailure();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setCookies() {
        ReceivedCookieIntercepter receivedCookieIntercepter
                = new ReceivedCookieIntercepter((LoginActivity)view);
        AddCookieIntercepter addCookieIntercepter
                = new AddCookieIntercepter((LoginActivity)view);
        OkHttpClient okClient = new OkHttpClient().newBuilder()
                .addInterceptor(addCookieIntercepter)
                .addNetworkInterceptor(receivedCookieIntercepter)
                .build();
        client.setClient(okClient);
        Log.d("LP", "Set new Client");
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
