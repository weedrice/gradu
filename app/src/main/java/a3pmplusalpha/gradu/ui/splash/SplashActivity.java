package a3pmplusalpha.gradu.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import a3pmplusalpha.gradu.model.repository.Local.preference.SettingsPreference;
import a3pmplusalpha.gradu.ui.login.LoginActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;

        //When SharedPreference Exists.
        if(SettingsPreference.getId(SplashActivity.this).length() == 0) {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.putExtra("ID",
                    SettingsPreference.getId(SplashActivity.this).toString());
            if(SettingsPreference.getPw(SplashActivity.this).length() == 0) {
                intent.putExtra("PW",
                        SettingsPreference.getPw(SplashActivity.this).toString());
            }
            startActivity(intent);
            this.finish();
        }
        //When SharedPreference doesn't Exists.
        else {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
