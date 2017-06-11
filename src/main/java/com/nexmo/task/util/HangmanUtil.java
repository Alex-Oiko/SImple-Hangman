package com.nexmo.task.util;


import java.util.stream.IntStream;

public class HangmanUtil {

    public static String getState(Character letter, String character, String state){
        StringBuilder builder = new StringBuilder(state);
        IntStream.range(0, character.length())
                .filter(i -> character.charAt(i) == letter)
                .forEach(i -> builder.setCharAt(i,letter));

        return builder.toString();
    }
}
