package com.oskm.lang;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sungkyu.eo on 2015-05-04.
 */
public class PatternFindTest {

    private static final int LOOP_COUNT = 100000000;

    @Test
    public void usingStringLoop() {

        String data = "XDffxf021tYk";

        String[] CHN_TOKENS_FIRST_STR = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        long startTime = System.currentTimeMillis();
        boolean b = false;
        for (int i = 0; i < LOOP_COUNT; i++) {

            for (String token : CHN_TOKENS_FIRST_STR) {
                if (data.startsWith(token)) {
                    b = true;
                }
            }
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    public void usingString_Matches() {

        String data = "XDffxf021tYk";

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < LOOP_COUNT; i++) {

            data.matches("^[A-Z].*$");
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    public void usingPatternMatcher() {

        String data = "XDffxf021tYk";

        long startTime = System.currentTimeMillis();

        Pattern p = Pattern.compile("^[A-Z].*$");
        Matcher m = p.matcher(data);

        for (int i = 0; i < LOOP_COUNT; i++) {
            m.matches();
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
