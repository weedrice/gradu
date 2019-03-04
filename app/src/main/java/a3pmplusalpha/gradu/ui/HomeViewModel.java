package a3pmplusalpha.gradu.ui;

import a3pmplusalpha.gradu.ui.base.BaseViewModel;
import a3pmplusalpha.gradu.util.SingleLiveEvent;
import androidx.lifecycle.LiveData;

public class HomeViewModel extends BaseViewModel {

    private SingleLiveEvent _onClickedHome = new SingleLiveEvent();
    LiveData onClickedHome = _onClickedHome;

    public HomeViewModel() {

    }



}
