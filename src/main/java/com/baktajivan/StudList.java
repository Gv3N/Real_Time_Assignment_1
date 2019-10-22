package com.baktajivan;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class StudList {

    public static RegexMatch regex = new RegexMatch();
    public static String[][] studList = new String[40][2];

    private static int a = 0;
    private static int b =0;

    public static void main(String [] args){
        new StudList().Main();
        //for loop haris missing here....
    }

    private void Main(){
        regex = new RegexMatch();

        try{
            String gitLink = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";
            Document doc = Jsoup.connect(gitLink).get();
            Elements links = doc.getElementsByClass("markdown-body").select("td");

            for (Element link : links){

                if (link.text().length() == 6 && regex.regexMatch("[0-9]", link.text()) && a < studList.length){
                    studList[a][0] = link.text();
                    a++;
                }//if matric no
                else if (regex.regexMatch("[a-zA-Z]",link.text()) && b < studList.length){
                    studList[b][1] = link.text();
                    b++;
                }//else if name
            }//for
        }//try
        catch (IOException e) {
            e.printStackTrace();
        }//catch error
    }//main

}//end class
