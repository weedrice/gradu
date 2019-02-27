package a3pmplusalpha.gradu.ui;


import android.os.Bundle;

import a3pmplusalpha.gradu.R;
import a3pmplusalpha.gradu.databinding.ActivityHomeBinding;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {
    private static final int HOME_CONTAINER_ID = R.id.fl_home_container;
    private HomeViewModel viewModel;
    private Fragment currentFragment;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        if(savedInstanceState == null) {
            currentFragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(HOME_CONTAINER_ID, currentFragment)
                    .commit();
        }

        binding.setLifecycleOwner(this);
    }
}
