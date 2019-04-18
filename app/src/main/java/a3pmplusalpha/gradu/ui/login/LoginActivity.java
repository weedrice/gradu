package a3pmplusalpha.gradu.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import a3pmplusalpha.gradu.R;
import a3pmplusalpha.gradu.databinding.ActivityLoginBinding;
import a3pmplusalpha.gradu.model.repository.Local.preference.GraduPreference;
import a3pmplusalpha.gradu.ui.base.BaseActivity;
import a3pmplusalpha.gradu.ui.main.MainActivity;
import androidx.annotation.Nullable;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginContract.Presenter> implements LoginContract.View {
    @Override
    public LoginContract.Presenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
    }

    private void initView(Bundle bundle) {
        String id = getIntent().getExtras().getString("ID");
        if(id.length() > 0) {
            binding.etLoginId.setText(id);
            binding.ivSaveId.setImageResource(R.drawable.btn_save_id_clicked);
            String password = getIntent().getExtras().getString("PW");
            if(password.length() > 0) {
                binding.etLoginPw.setText(password);
                binding.ivAlwaysLogin.setImageResource(R.drawable.btn_always_login_clicked);
            }
        }

        binding.ivSaveId.setOnClickListener(__ -> saveId());
        binding.ivAlwaysLogin.setOnClickListener(__ -> alwaysLogin());
        binding.ivLogin.setOnClickListener(__ -> startLogin());
    }

    @Override
    public void loginFailure() {
        showToast(getString(R.string.login_failure));
    }

    @Override
    public void startLogin() {
        String id = binding.etLoginId.getText().toString();
        String pw = binding.etLoginPw.getText().toString();

        if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pw)) {
            presenter.logIn(id, pw);
        } else {
            showToast(getString(R.string.invalid_id_pw));
        }
    }

    @Override
    public void loginSuccess(String userHtml, String classHtml) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("userHtml", userHtml);
        intent.putExtra("classHtml", classHtml);
        startActivity(intent);
        finish();
    }

    public void saveId() {
        presenter.setSaveId();
    }

    public void alwaysLogin() {
        presenter.setAlwaysLogin();
    }

    @Override
    public void changeIdDrawable(boolean state) {
        if(state) {
            binding.ivSaveId.setImageResource(R.drawable.btn_save_id_clicked);
        } else {
            binding.ivSaveId.setImageResource(R.drawable.btn_save_id);
        }
    }

    @Override
    public void changeLoginDrawable(boolean state) {
        if(state) {
            binding.ivAlwaysLogin.setImageResource(R.drawable.btn_always_login_clicked);
        } else {
            binding.ivAlwaysLogin.setImageResource(R.drawable.btn_always_login);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
