package a3pmplusalpha.gradu.util;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import a3pmplusalpha.gradu.model.repository.Local.entity.UserInfo;

public class HtmlParse {
    public static UserInfo getAccountInformation(String html) {
        Document doc = Jsoup.parse(html);
        Log.d("DOC CREATED", "GOOD");
        Elements elements = doc.select("tr[height$=28] td");

        UserInfo userInfo = new UserInfo();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).hasText()) {
                switch (elements.get(i).text()) {
                    case "성명":
                        userInfo.setName(elements.get(i + 1).text());
                        break;
                    case "학년 - 학번":
                        userInfo.setStunum(elements.get(i + 1).text().split("-")[1].trim());
                        break;
                    case "학부":
                        userInfo.setScholarship(elements.get(i + 1).text());
                        break;
                    case "전공":
                        String[] majors = elements.get(i + 1).text().split(" ");
                        userInfo.setMajor1(majors[0].substring(0, majors[0].length()-1));
                        userInfo.setMajor2(majors[1].trim());
                        break;
                    case "교육과정 구분":
                        String credit = elements.get(i + 1).text();
                        if (credit.contains("140")) {
                            userInfo.setMaxCredit(true);
                        } else {
                            userInfo.setMaxCredit(false);
                        }
                }
            }
        }

        Log.d("USERINFO", userInfo.toString());


        return userInfo;
    }
}
