package a3pmplusalpha.gradu.util;

import a3pmplusalpha.gradu.ui.HomeViewModel;
import a3pmplusalpha.gradu.ui.login.LoginViewModel;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public ViewModelFactory() {

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel();
        } else if(modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel();
        }
        throw new IllegalArgumentException(modelClass.getName());
    }
}
