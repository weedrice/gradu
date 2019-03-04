package a3pmplusalpha.gradu.ui.login;

import android.util.Log;

import a3pmplusalpha.gradu.ui.base.BaseViewModel;
import a3pmplusalpha.gradu.util.SingleLiveEvent;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends BaseViewModel {
    private SingleLiveEvent _onClickLogin = new SingleLiveEvent();
    LiveData onClickLogin;
    LiveData getOnClickLogin() {
        return _onClickLogin;
    }

    private MutableLiveData<Boolean> _isSaveId = new MutableLiveData<Boolean>();
    LiveData<Boolean> isSaveId;
    LiveData<Boolean> getIsSaveId() {
        return _isSaveId;
    }

    public void onLoginClicked() {
        _onClickLogin.call();
    }

}
