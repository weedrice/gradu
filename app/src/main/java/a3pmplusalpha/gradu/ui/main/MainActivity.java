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

    private enum Fragments {
        HOME_FRAGMENT(0),
        STATUS_FRAGMENT(1),
        SIMULATION_FRAGMENT(2),
        MYPAGE_FRAGMENT(3);

        private int FLAG;

        Fragments(int FLAG) {
            this.FLAG = FLAG;
        }

        public int getFLAG() {
            return FLAG;
        }
    }
    private static Fragments FRAGMENT_FLAG;
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

        startHomeFragment();

        initView();
    }

    private void initView() {
        binding.includeBottomNavigation.ivHome.setOnClickListener(__ -> startHomeFragment());
        binding.includeBottomNavigation.ivMyPage.setOnClickListener(__ -> startStatusFragment());
        binding.includeBottomNavigation.ivSimulation.setOnClickListener(__ -> startSimulationFragment());
        binding.includeBottomNavigation.ivMyPage.setOnClickListener(__ -> startMyPageFragment());
    }

    private void setBottomNavigation() {
        switch(FRAGMENT_FLAG) {
            case HOME_FRAGMENT:
                binding.includeBottomNavigation.ivHome.setImageResource(R.drawable.img_home);
            case MYPAGE_FRAGMENT:
                binding.includeBottomNavigation.ivMyPage.setImageResource(R.drawable.img_mypage);
                break;
            case STATUS_FRAGMENT:
                binding.includeBottomNavigation.ivStatus.setImageResource(R.drawable.img_status);
                break;
            case SIMULATION_FRAGMENT:
                binding.includeBottomNavigation.ivSimulation.setImageResource(R.drawable.img_simulation);
                break;
        }
    }

    private void startHomeFragment() {
        if(!(currentFragment instanceof HomeFragment)) {
            //TODO: HomeFragment 시작
            currentFragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(HOME_CONTAINER_ID, currentFragment)
                    .commit();

            setBottomNavigation();
            binding.includeBottomNavigation.ivHome.setImageResource(R.drawable.img_home_clicked);
            FRAGMENT_FLAG = Fragments.HOME_FRAGMENT;

            Bundle bundle = new Bundle();
            bundle.putParcelable("userInfo", presenter.getUserInfo());
            bundle.putSerializable("classInfo", presenter.getClassInfo());
        }
    }

    private void startStatusFragment() {
        //TODO: StatusFragment 시작
        /*if(!(currentFragment instanceof StatusFragment)) {
            currentFragment = new StatusFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(HOME_CONTAINER_ID, currentFragment)
                    .commit();

            setBottommNavigation();
            binding.includeBottomNavigation.ivStatus.setImageResource(R.drawable.img_status_clicked);
            FRAGMENT_FLAG = Fragments.STATUS_FRAGMENT;
        }*/
    }

    private void startSimulationFragment() {
        //TODO: SimulationFragment 시작
        /*if(!(currentFragment instanceof StatusFragment)) {
            binding.includeBottomNavigation.ivHome.setImageResource(R.drawable.img_home);
        }*/
    }

    private void startMyPageFragment() {
        //TODO: MyPageFragment 시작
        /*if(!(currentFragment instanceof StatusFragment)) {
            binding.includeBottomNavigation.ivHome.setImageResource(R.drawable.img_home);
        }*/
    }
}
