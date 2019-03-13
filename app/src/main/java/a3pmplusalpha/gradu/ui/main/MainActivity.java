package a3pmplusalpha.gradu.ui.main;

import android.os.Bundle;
import android.util.Log;

import a3pmplusalpha.gradu.R;
import a3pmplusalpha.gradu.databinding.ActivityHomeBinding;
import a3pmplusalpha.gradu.ui.base.BaseActivity;
import a3pmplusalpha.gradu.ui.main.home.HomeFragment;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity<ActivityHomeBinding, MainContract.Presenter> {
    private static final int HOME_CONTAINER_ID = R.id.fl_home_container;
    private Fragment currentFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public MainContract.Presenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String html = getIntent().getExtras().getString("html");

        currentFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(HOME_CONTAINER_ID, currentFragment)
                .commit();

        binding.llBottomNavigation.ivHome.setImageResource(R.drawable.img_home_clicked);
    }
}
