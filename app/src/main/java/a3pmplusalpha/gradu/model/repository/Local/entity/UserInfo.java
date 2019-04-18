package a3pmplusalpha.gradu.model.repository.Local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class UserInfo implements Parcelable {
    private String name;
    private String scholarship;
    private String major1;
    private String major2;
    private boolean maxCredit;
    private String stunum;
    private String semester;

    public UserInfo() { }

    public UserInfo(String name, String scholarship, String major1,
                    String major2, boolean maxCredit, String stunum,
                    String semester, HashMap<String, ClassInfo> classInfo) {
        this.name = name;
        this.scholarship = scholarship;
        this.major1 = major1;
        this.major2 = major2;
        this.maxCredit = maxCredit;
        this.stunum = stunum;
        this.semester = semester;
    }

    private UserInfo(Parcel in) {
        this.name = in.readString();
        this.scholarship = in.readString();
        this.major1 = in.readString();
        this.major2 = in.readString();
        this.maxCredit = Boolean.valueOf(in.readString());
        this.stunum = in.readString();
        this.semester = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScholarship() {
        return scholarship;
    }

    public void setScholarship(String scholarship) {
        this.scholarship = scholarship;
    }

    public String getMajor1() {
        return major1;
    }

    public void setMajor1(String major1) {
        this.major1 = major1;
    }

    public String getMajor2() {
        return major2;
    }

    public void setMajor2(String major2) {
        this.major2 = major2;
    }

    public boolean isMaxCredit() {
        return maxCredit;
    }

    public void setMaxCredit(boolean maxCredit) {
        this.maxCredit = maxCredit;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        @Override
        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(scholarship);
        parcel.writeString(major1);
        parcel.writeString(major2);
        parcel.writeString(String.valueOf(maxCredit));
        parcel.writeString(stunum);
        parcel.writeString(semester);
    }
}
