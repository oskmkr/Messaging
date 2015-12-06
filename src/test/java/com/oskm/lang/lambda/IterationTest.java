/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.lang.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by sungkyu.eo on 2015-02-13.
 */

/**
 * java 8 new syntax test
 * lambda expression
 * Predicate Interface
 * :: expression
 * Stream API
 */
public class IterationTest {

    private static final Logger LOG = LoggerFactory.getLogger(IterationTest.class);

    /**
     * @reference http://jinson.tistory.com/208
     * @reference http://jinson.tistory.com/entry/%ED%95%9C%EA%B8%80%ED%99%94-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-5-%EB%9E%8C%EB%8B%A4%EA%B0%80-%ED%95%84%EC%9A%94%ED%95%9C-%EC%9D%B4%EC%9C%A0-2
     */
    @Test
    public void iteration() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        for (int each : numberList) {
            LOG.debug("# " + each);
        }

        numberList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer each) {
                LOG.debug("# " + each);
            }
        });

        numberList.forEach((Integer each) -> LOG.debug("# " + each));

        numberList.forEach(each -> LOG.debug("# " + each));


        Map<String, String> map = new HashMap<>();

        Collections.sort(numberList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                }

                return 0;
            }
        });

        numberList.forEach((Integer each) -> {
            LOG.debug("# " + each);
            LOG.debug("# " + each);
        });

        /**
         * (역주: 에타 확장(또는 더 일반적으로 에타 변환)이란 어떤 함수 f가 있을 때,
         * (자바 람다식으로 표현하자면) (x) -> f(x)나 f가 같다는 것입니다. 매개변수를 붙이면 에타 확장, 매개변수를 때어내면 에타 축소(reduction)이라 합니다.
         * 다만, f안에서 x가 자유변수(free variable)여야 합니다.
         * 에타 확장이 실제 위의 forEach에서 어떤 일을 하냐 하면, 바로 그 앞의 식과 같은 일을 합니다.
         */
        numberList.forEach(x -> System.out.println(x));
        numberList.forEach(System.out::println);
    }

    @Test
    public void iteration2() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        int sum = sumAll(numberList);

        LOG.debug("# sum all : " + sum);

        int sumOddNumber = sumOddNumber(numberList);

        LOG.debug("# sum odd number : " + sumOddNumber);

        numberList.forEach(x -> {
            if (x % 2 == 0) {
                LOG.debug("# even number : " + x);
            }
            ;
        });

        // sum of even number
        int sumOfEvenNumber = sum(numberList, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        });

        LOG.debug("# sum of even number : " + sumOfEvenNumber);
        LOG.debug("# sum of even number (using lambda expression) : " + sum(numberList, x -> x % 2 == 0));

        // sum of odd number
        int sumOfOddNumber = sum(numberList, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 != 0;
            }
        });

        LOG.debug("# sum of odd number : " + sumOfOddNumber);
        LOG.debug("# sum of even number (using lambda expression) : " + sum(numberList, x -> x % 2 != 0));

        // sum of all number
        int sumOfAllNumber = sum(numberList, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return true;
            }
        });

        LOG.debug("# sum of all number : " + sumOfAllNumber);
        LOG.debug("# sum of even number (using lambda expression) : " + sum(numberList, x -> true));
    }

    @Test
    public void interationByLaziness() {

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6);

        //numberList.forEach(System.out::println);


        // 1. former
        for (int each : numberList) {
            if (each % 2 == 0) {
                if (each * 2 > 5) {
                    LOG.debug("# value : " + each);
                    break;
                }
            }
        }

        // 2. method extraction
        for (int each : numberList) {
            if (isEven(each)) {
                if (isGreaterThan5(doubleIt(each))) {
                    LOG.debug("# value : " + each);
                    break;
                }
            }
        }

        // 3. stream
        Optional<Integer> optional = numberList.stream().filter(x -> isEven(x)).map(x -> doubleIt(x)).filter(x -> isGreaterThan5(x)).findFirst();

        LOG.debug("# optional : " + optional);

        //LOG.debug(String.valueOf(numberList.stream().filter(x -> x % 2 == 0).map(x -> x * 2).filter(x -> x > 5).findAny()));
    }

    private boolean isEven(int number) {
        LOG.debug("[isEven, " + number + "]");
        return number % 2 == 0;
    }

    private int doubleIt(int number) {
        LOG.debug("[doubleIt, " + number + "]");
        return number * 2;
    }

    private boolean isGreaterThan5(int number) {
        LOG.debug("[isGreaterThan5, " + number + "]");
        return number > 5;
    }

    /**
     * @reference http://insukcho.blogspot.kr/2014/07/jdk-8-33-stream-api.html
     */
    @Test
    public void interationByStreamAPI() {

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        LOG.debug("# stream " +
                numberList.stream().filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer x) {
                        return x % 2 == 0;
                    }
                }).map(new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) {
                        return integer;
                    }
                }));


    }

    private int sumAll(List<Integer> numberList) {
        int sum = 0;
        for (int each : numberList) {
            sum += each;
        }
        return sum;
    }

    private int sumOddNumber(List<Integer> numberList) {
        int sum = 0;
        for (int each : numberList) {
            if (!(each % 2 == 0)) {
                sum += each;
            }

        }
        return sum;
    }

    private int sum(List<Integer> numberList, Predicate<Integer> p) {
        int sum = 0;
        for (int each : numberList) {

            if (p.test(each)) {
                sum += each;
            }
        }
        return sum;
    }
}
