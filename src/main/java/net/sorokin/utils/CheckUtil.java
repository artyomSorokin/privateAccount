package net.sorokin.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

    private  Pattern pattern;
    private  Matcher matcher;

    private final String EMAIL_PATTERN =
            "^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)" +
                    "*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)" +
                    "*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";

    private static final String PASSWORD_PATTERN =
            "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}";


    public boolean checkEmail(String hex) {
        final String LOWERCASE_EMAIL = hex.toLowerCase();
        System.out.println("in checkMail");
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(LOWERCASE_EMAIL);
        boolean b = matcher.matches();
        System.out.println(b);
        return b;

    }



    public boolean checkPassword(final String password){
        System.out.println("in checkPass");
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        boolean b = matcher.matches();
        System.out.println(b);
        return b;
    }

    public static void main(String[] args) {
        new CheckUtil().checkEmail("eLKrHorrid24@gmail.net");
    }
}
