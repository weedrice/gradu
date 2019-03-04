package a3pmplusalpha.gradu.util;

public class DummyClass {
    private static String dummyId = "21100440";
    private static String dummyPw = "abc123";

    public static boolean checkId(String id, String pw) {
        if (dummyId.equals(id) && dummyPw.equals(pw)) {
            return true;
        } else {
            return false;
        }
    }
}
