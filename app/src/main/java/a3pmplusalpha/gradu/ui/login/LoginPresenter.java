package a3pmplusalpha.gradu.ui.login;

import android.app.Activity;

import a3pmplusalpha.gradu.model.HisnetApi;
import a3pmplusalpha.gradu.model.repository.Local.preference.SettingsPreference;
import androidx.databinding.ObservableBoolean;
import io.reactivex.disposables.CompositeDisposable;

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
        SettingsPreference.saveId((Activity)view, id);
    }

    private void savePwPref(String pw) {
        SettingsPreference.savePw((Activity)view, pw);
    }

    @Override
    public void logIn(String id, String pw) {
        // TODO: Login Process 처리하기
        /*setIsLoading(true);
        compositeDisposable.add(
                HisnetApi.getInstance().api.login(id, pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    setIsLoading(false);
                    String result = response.body();
                    Log.d("Presenter", result);
                }, throwable -> {
                    setIsLoading(false);
                    throwable.getStackTrace();
                })
        );*/
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
