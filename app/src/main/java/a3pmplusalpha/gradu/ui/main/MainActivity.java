package a3pmplusalpha.gradu.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import a3pmplusalpha.gradu.R;
import a3pmplusalpha.gradu.databinding.ActivityHomeBinding;
import a3pmplusalpha.gradu.ui.base.BaseActivity;
import a3pmplusalpha.gradu.ui.main.home.HomeFragment;

public class MainActivity extends BaseActivity<ActivityHomeBinding, MainContract.Presenter> implements MainContract.View {
    static final String welcomeMsg = "Welcome, ";

    private static final int HOME_CONTAINER_ID = R.id.fl_home_container;
    private Fragment currentFragment;

    @Override
    public void setUserName(String name) {
        binding.includeLayoutHeader.tvWelcome.setText(welcomeMsg + name);
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public MainContract.Presenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userHtml = getIntent().getExtras().getString("userHtml");
        String classHtml = getIntent().getExtras().getString("classHtml");
        presenter.loadInfo(userHtml, classHtml);

        currentFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(HOME_CONTAINER_ID, currentFragment)
                .commit();

        binding.includeBottomNavigation.ivHome.setImageResource(R.drawable.img_home_clicked);
    }
}
