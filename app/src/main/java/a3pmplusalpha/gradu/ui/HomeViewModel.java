package a3pmplusalpha.gradu.ui;

import a3pmplusalpha.gradu.util.SingleLiveEvent;
import androidx.lifecycle.LiveData;

public class HomeViewModel {

    private SingleLiveEvent _onClickedHome = new SingleLiveEvent();
    LiveData onClickedHome = _onClickedHome;

    HomeViewModel() {

    }



}
