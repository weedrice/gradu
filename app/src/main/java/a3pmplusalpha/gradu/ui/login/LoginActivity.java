package a3pmplusalpha.gradu.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import a3pmplusalpha.gradu.R;
import a3pmplusalpha.gradu.databinding.ActivityLoginBinding;
import a3pmplusalpha.gradu.ui.base.BaseActivity;
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
        if(bundle != null) {
            binding.etLoginId.setText(bundle.getString("ID"));
            binding.ivSaveId.setImageResource(R.drawable.btn_save_id_clicked);
            if(bundle.getString("PW") != null) {
                binding.etLoginPw.setText(bundle.getString("PW"));
                binding.ivAlwaysLogin.setImageResource(R.drawable.btn_always_login_clicked);
            }
        }

        binding.ivSaveId.setOnClickListener(__ -> saveId());
        binding.ivAlwaysLogin.setOnClickListener(__ -> alwaysLogin());
        binding.ivLogin.setOnClickListener(__ -> startLogin());
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
    public void saveId() {
        presenter.setSaveId();
    }

    @Override
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
}
