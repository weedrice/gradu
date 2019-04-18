package a3pmplusalpha.gradu.util;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

import a3pmplusalpha.gradu.model.repository.Local.entity.ClassInfo;
import a3pmplusalpha.gradu.model.repository.Local.entity.UserInfo;

public class HtmlParse {
    public static UserInfo getAccountInformation(String html) {
        Document doc = Jsoup.parse(html);
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

        return userInfo;
    }

    public static HashMap<String, ArrayList<ClassInfo>> getClassInfomation(String html) {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div[id^=div_2]");

        //연도별 과목을 담기 위한 map
        HashMap<String, ArrayList<ClassInfo>> yearMap = new HashMap<>();
        for(Element table : elements) {
            Elements contents = table.select("td");

            ArrayList<ClassInfo> classes = new ArrayList<>();
            String year = contents.get(0).text().split(" ")[0];
            for(int i=9;i<contents.size();i += 8) {
                //한과목의 정보를 담는 arrayList
                ClassInfo classInfo = new ClassInfo();
                classInfo.setCode(contents.get(i).text());
                classInfo.setName(contents.get(i+1).text());
                classInfo.setDescription(contents.get(i+2).text());
                classInfo.setCredit(contents.get(i+3).text());
                classInfo.setGrade(contents.get(i+4).text());
                classInfo.setScore(contents.get(i+5).text());

                classes.add(classInfo);
            }

            yearMap.put(year, classes);
        }

        return yearMap;
    }
}
