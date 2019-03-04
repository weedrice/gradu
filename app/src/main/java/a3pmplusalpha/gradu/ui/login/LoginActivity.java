package a3pmplusalpha.gradu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import a3pmplusalpha.gradu.BR;
import a3pmplusalpha.gradu.R;
import a3pmplusalpha.gradu.databinding.ActivityLoginBinding;
import a3pmplusalpha.gradu.ui.HomeActivity;
import a3pmplusalpha.gradu.ui.base.BaseActivity;
import a3pmplusalpha.gradu.util.DummyClass;
import a3pmplusalpha.gradu.util.ViewModelFactory;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    ViewModelFactory factory;
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public LoginViewModel getViewModel() {
        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return loginViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binding = getDataBinding();

        loginViewModel.getOnClickLogin().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                onLoginClicked();
            }
        });
    }

    public void onLoginClicked() {
        String id = binding.etLoginId.getText().toString();
        String pw = binding.etLoginPw.getText().toString();
        if(DummyClass.checkId(id, pw)) {
            hideKeyboard();
            startHomeActivity();
        } else {
            Toast.makeText(this, getString(R.string.invalid_id_pw), Toast.LENGTH_LONG).show();
        }
    }

    public void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
