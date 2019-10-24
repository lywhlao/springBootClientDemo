package com.example.demo;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: laojiaqi
 * @Date: 2019-09-25 21:42
 * @Description:
 */
public class RegexTest {
    String target="\"Ganymede,\" he continued, \"is the largest moon in the Solar System.\"";

    /**
     * 贪婪，默认
     */
    @Test
    public void greedy(){
        Pattern pattern = Pattern.compile("\".*\"");
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            String group = matcher.group(0);
            System.out.println(group);
        }
    }

    /**
     * 懒惰
     */
    @Test
    public void lazy(){
        Pattern pattern = Pattern.compile("\".*?\"");
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            String group = matcher.group(0);
            System.out.println(group);
        }
    }

    /**
     * 占有型
     */
    @Test
    public void process(){
        String target="\"Ganymede,\" he continued, \"is the largest moon in the Solar System.\"";
        Pattern pattern = Pattern.compile("\".*+\"");
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            String group = matcher.group(0);
            System.out.println(group);
        }
    }


    /**
     * 占有型2
     *
     */
    @Test
    public void process2(){
        String target="\"Ganymede,\" he continued, \"is the largest moon in the Solar System.\"";
        Pattern pattern = Pattern.compile("\"[^\"]*+\"");
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            String group = matcher.group(0);
            System.out.println(group);
        }
    }


    @Test
    public void process3(){
        String input = "one two three four four four five six";
        Pattern pattern = Pattern.compile("^one two (.*+)four.*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String group = matcher.group(0);
            System.out.println(group);
        }

    }

}
