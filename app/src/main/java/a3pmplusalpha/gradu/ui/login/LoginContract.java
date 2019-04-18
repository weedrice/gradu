package a3pmplusalpha.gradu.ui.login;

import a3pmplusalpha.gradu.ui.base.BasePresenter;
import a3pmplusalpha.gradu.ui.base.BaseView;

public interface LoginContract {
    interface View extends BaseView {
        void startLogin();
        void loginFailure();
        void loginSuccess(String userHtml, String classHtml);
        void changeIdDrawable(boolean state);
        void changeLoginDrawable(boolean state);
    }

    interface Presenter extends BasePresenter {
        void setSaveId();
        void setAlwaysLogin();
        void logIn(String id, String pw);
    }
}
