package com.ethioclicks.skilledApp.security.utils;

public class Util {
    public static String arrayToString(String[] questionsAndAnswers) {
        return (questionsAndAnswers!=null && questionsAndAnswers.length>0) ? String.join("|", questionsAndAnswers):"";
    }

    public static String[] stringToArray(String questionsAndAnswers) {
        String[] emptyArray = {};
        return (questionsAndAnswers!=null && !questionsAndAnswers.isEmpty()) ? questionsAndAnswers.split("\\|"):emptyArray;
    }
}
