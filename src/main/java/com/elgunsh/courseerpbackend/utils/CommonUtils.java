package com.elgunsh.courseerpbackend.utils;


public class CommonUtils {

    @FunctionalInterface
    public interface Checker {
        boolean check();
    }

//    public static void throwIf(Checker checker, BaseException ex) {
//        if (checker.check()) {
//            throw ex;
//        }
//    }

}
