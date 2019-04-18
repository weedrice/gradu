package a3pmplusalpha.gradu.ui.main;

import java.util.ArrayList;
import java.util.HashMap;

import a3pmplusalpha.gradu.model.repository.Local.entity.ClassInfo;
import a3pmplusalpha.gradu.model.repository.Local.entity.UserInfo;
import a3pmplusalpha.gradu.util.HtmlParse;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private UserInfo userInfo;
    private HashMap<String, ArrayList<ClassInfo>> yearMap;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadInfo(String userHtml, String classHtml) {
        userInfo = HtmlParse.getAccountInformation(userHtml);
        yearMap = HtmlParse.getClassInfomation(classHtml);

        view.setUserName(userInfo.getName());
    }

    @Override
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public HashMap<String, ArrayList<ClassInfo>> getClassInfo() {
        return yearMap;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }
}
