package a3pmplusalpha.gradu.model.repository.Local.entity;

public class UserInfo {
    String name;
    String scholarship;
    String major1;
    String major2;
    boolean maxCredit;
    String stunum;
    String semester;


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

    public boolean isMaxCredit() {
        return maxCredit;
    }

    public void setMaxCredit(boolean maxCredit) {
        this.maxCredit = maxCredit;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", scholarship='" + scholarship + '\'' +
                ", major1='" + major1 + '\'' +
                ", major2='" + major2 + '\'' +
                ", maxCredit=" + maxCredit +
                ", stunum='" + stunum + '\'' +
                ", semester='" + semester + '\'' +
                '}';
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
}
