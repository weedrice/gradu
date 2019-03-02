package a3pmplusalpha.gradu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a3pmplusalpha.gradu.databinding.FragmentHomeBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    public static Fragment newInstnace() {
        Bundle args = new Bundle();
        Fragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private HomeViewModel homeViewModel = new HomeViewModel();
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}
