package a3pmplusalpha.gradu.ui.main;

import java.util.ArrayList;
import java.util.HashMap;

import a3pmplusalpha.gradu.model.repository.Local.entity.ClassInfo;
import a3pmplusalpha.gradu.model.repository.Local.entity.UserInfo;
import a3pmplusalpha.gradu.ui.base.BasePresenter;
import a3pmplusalpha.gradu.ui.base.BaseView;

public interface MainContract {
    interface View extends BaseView {
        void setUserName(String name);
    }

    interface Presenter extends BasePresenter {
        void loadInfo(String userHtml, String classHtml);
        UserInfo getUserInfo();
        HashMap<String, ArrayList<ClassInfo>> getClassInfo();
    }
}
