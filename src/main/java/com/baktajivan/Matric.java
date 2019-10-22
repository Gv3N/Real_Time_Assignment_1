package com.baktajivan;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Matric {
    private static  RegexMatch regex = new RegexMatch();
    private final String gitLink = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
    static String [][] ars = new String[40][3];//chck later
    public static int a = 0,b = 0,z = 0;

    public static void main (String [] args){
    new Matric().Main();
    for (int x =0; x < ars.length; x++){
        if (ars[x][0] == null && ars [x][1] == null){
            ars[x][0] = " ";
            ars[x][1] = " ";
            ars[x][2] = " ";
        }//if
        else if (regex.regexMatch("^ ", ars[x][1])){
          ars[x][1] = ars[x][1].replaceAll("^ ", "");
        }//else if
    }//for
    }//main

    private void Main(){
        try {
            Document doc = Jsoup.connect(gitLink).get();
            String title = doc.title();
            Elements data = doc.getElementsByClass("js-timeline-item js-timeline-progressive-focus-container").select("c");

            for(Element link : data){
                String[] splitString = link.text().split(" ");
                for (int i=0;i <splitString.length;i++){
                    if (regex.regexMatch("[0-9]",splitString[i]) && splitString[i].length()<=13){
                        String[] splitText;
                        if(splitString[i].length()>6){
                            splitText = splitString[i].split(":");
                            for(int j=0;j<splitText.length;j++){
                                if (splitText[j].length()==6 && regex.regexMatch("[0-9]",splitText[j])){
                                    ars[a][0]=splitText[1];
                                    a++;
                                }//if j
                            }//for j
                        }//if splitString.length
                        else{ars[a][0]=splitString[i];
                        a++;
                        }//else
                }//if regex
                    else if (regex.regexMatch("https",splitString[i])){
                        String[] splitText;
                        if (regex.regexMatch("Link",splitString[i])){
                            splitText=splitString[i].split("ink:");
                            for (int k =0;k<splitText.length;k++){
                                if(splitText[k].length()>1){
                                    ars[b][2]=splitText[1];
                                    b++;
                                }//if
                            }//for
                        }//if
                        else {
                            ars[b][2]=splitString[i];
                            b++;
                        }//else
                    }//else if
            }//for link : data
                String[] splitName = link.text().split("Name");
                for(String a: splitName) {
                    String[] splitName2 = a.split(":");

                    for(String b: splitName2) {
                        String[] splitName3 = b.split("Link");

                        for(String c: splitName3) {
                            String[] splitName4 = c.split("Matric no");

                            for (String d: splitName4) {
                                String[] splitName5= d.split("link");

                                for(String e: splitName5) {
                                    if(regex.regexMatch("[a-zA-Z]",e)
                                            && !regex.regexMatch("[0-9]",e)
                                            && !regex.regexMatch("https",e)
                                            && !regex.regexMatch("//",e)
                                            && !regex.regexMatch("[Mm]atri[cx]",e))
                                    {
                                        ars[z][1] = e;
                                        z++;

                                    }
                                }
                            }
                        }

                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
