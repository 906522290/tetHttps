package com.jshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestMain {

    public static void main(String[] args){
        String test = "@@22@@";
        String[] arrayTest = test.split("\\@",5);
        System.out.println(arrayTest.length);


        Scanner scanner = new Scanner(System.in);
        String gene1 = scanner.nextLine();

        String gene2 = scanner.nextLine();

        String[] str_string1 = gene1.split("\\d");
        String[] str_string2 = gene2.split("\\d");
        List newstr_string1 = new ArrayList<String>();
        List newstr_string2 = new ArrayList<String>();
        for(int i=0;i<str_string1.length;i++){
            if(!"".equals(str_string1[i].trim()))
                newstr_string1.add(str_string1[i]);
        }

        for(int i=0;i<str_string2.length;i++){
            if(!"".equals(str_string2[i].trim()))
                newstr_string2.add(str_string2[i]);
        }

        //得到数字
        String[] num_string1 = gene1.split("\\D");
        String[] num_string2= gene2.split("\\D");

        String str1="";
        String str2="";
        for(int i=0;i<num_string1.length;i++){
            int count = Integer.valueOf(num_string1[i]);
            String s = (String) newstr_string1.get(i);
            for(int j=0 ;j<count;j++){
                str1 = str1+s;
            }
        }

        for(int i=0;i<num_string2.length;i++){
            int count =Integer.valueOf(num_string2[i]);
            String s = (String) newstr_string2.get(i);
            for(int j=0 ;j<count;j++){
                str2 = str2+s;
            }
        }
        int loop = 0;
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)==str2.charAt(i)){
                loop = loop + 1;
            }
        }
        int fen =0;
        for(int i=0;i<num_string1.length;i++){
            fen = fen + Integer.valueOf(num_string1[i]);
        }
        System.out.println(loop+"/" +fen);
    }
}
