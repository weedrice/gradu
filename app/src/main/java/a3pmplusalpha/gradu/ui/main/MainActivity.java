package a3pmplusalpha.gradu.ui;

import android.os.Bundle;

import a3pmplusalpha.gradu.BR;
import a3pmplusalpha.gradu.R;
import a3pmplusalpha.gradu.databinding.ActivityHomeBinding;
import a3pmplusalpha.gradu.ui.base.BaseActivity;
import a3pmplusalpha.gradu.util.ViewModelFactory;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {
    private static final int HOME_CONTAINER_ID = R.id.fl_home_container;

    ViewModelFactory factory;
    private HomeViewModel viewModel;
    private Fragment currentFragment;
    private ActivityHomeBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public int getBindingVariable() {
        return BR.homeViewModel;
    }

    @Override
    public HomeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getDataBinding();

        if(savedInstanceState == null) {
            currentFragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(HOME_CONTAINER_ID, currentFragment)
                    .commit();
            binding.llBottomNavigation.ivHome.setImageResource(R.drawable.img_home_clicked);
        }
    }
}
