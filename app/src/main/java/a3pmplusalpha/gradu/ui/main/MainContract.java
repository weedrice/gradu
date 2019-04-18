package a3pmplusalpha.gradu.ui.main;

import a3pmplusalpha.gradu.ui.base.BasePresenter;
import a3pmplusalpha.gradu.ui.base.BaseView;

public interface MainContract {
    interface View extends BaseView {
        void setUserName(String name);
    }

    interface Presenter extends BasePresenter {
        void loadInfo(String userHtml, String classHtml);
    }
}
