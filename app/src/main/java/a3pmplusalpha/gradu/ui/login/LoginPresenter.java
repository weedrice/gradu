package a3pmplusalpha.gradu.ui.login;

import a3pmplusalpha.gradu.model.HisnetApi;
import a3pmplusalpha.gradu.model.repository.Local.preference.GraduPreference;
import androidx.databinding.ObservableBoolean;
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

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        client = HisnetApi.getInstance((LoginActivity)view);
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    @Override
    public void setSaveId() {
        if(isSaveId && isAlwaysLogin) {
            setAlwaysLogin();
        }
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

    private void clearIdPref() {
        GraduPreference.getSharedPreferences((LoginActivity)view)
                .clearPrefString(GraduPreference.PREF_NAME_ID);
    }

    private void savePwPref(String pw) {
        GraduPreference.getSharedPreferences((LoginActivity)view)
                .putPrefString(GraduPreference.PREF_NAME_PASSWORD, pw);
    }

    private void clearPwPref() {
        GraduPreference.getSharedPreferences((LoginActivity)view)
                .clearPrefString(GraduPreference.PREF_NAME_PASSWORD);
    }

    private void controlPrefs(String id, String pw) {
        if(isAlwaysLogin)
            savePwPref(pw);
        if(isSaveId) {
            saveIdPref(id);
            clearPwPref();
        } else {
            clearIdPref();
            clearPwPref();
        }
    }

    @Override
    public void logIn(String id, String pw) {
        setIsLoading(true);
        client.api.login(id, pw, "Korean").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(!response.body().string().contains("alert")) {
                        controlPrefs(id, pw);
                        getUserHtml();
                    } else {
                        view.loginFailure();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    setIsLoading(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                setIsLoading(false);
            }
        });
    }

    private void getUserHtml() {
        client.api.getAccountInfo().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getClassHtml(response.body().string());
                    setIsLoading(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    setIsLoading(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                setIsLoading(false);
            }
        });
    }

    private void getClassHtml(String userHtml) {
        client.api.getInfo().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    view.loginSuccess(userHtml, response.body().string());
                    setIsLoading(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    setIsLoading(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                setIsLoading(false);
            }
        });
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
