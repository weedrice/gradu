package a3pmplusalpha.gradu.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

import a3pmplusalpha.gradu.model.repository.Local.preference.GraduPreference;
import a3pmplusalpha.gradu.ui.login.LoginActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;

        //When SharedPreference Exists.
        String id = GraduPreference.getSharedPreferences(this)
                .getPrefString(GraduPreference.PREF_NAME_ID, "");

        String password = GraduPreference.getSharedPreferences(this)
                .getPrefString(GraduPreference.PREF_NAME_PASSWORD, "");

        if(id != null) {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.putExtra("ID",
                    id);
            if(password != null) {
                intent.putExtra("PW",
                        password);
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
