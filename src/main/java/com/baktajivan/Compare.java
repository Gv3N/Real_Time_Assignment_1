package com.baktajivan;
import java.util.*;

public class Compare {
    private static int totalCommented = 0, totalNotCommented = 0, totalAnonCommented = 0;
    private static String[] matL = new String[45];
    private static String[] matC = new String[39];
    private static String[] report = new String[39];
    private static String[] Title = {"No.", "Matric", "Name", "Github Link"};
    static String[][] studS = new String[39][4], studU = new String[39][4], studNS = new String[39][4];
    private static Matric studC = new Matric();
    private static StudList studL = new StudList();

    public static void main(String[] args) {
        Matric.main(args);
        StudList.main(args);
        for (int i = 0; i < StudList.studList.length; i++) {
            matL[i] = StudList.studList[i][0];
        }

        for (int i = 0; i < Matric.ars.length; i++) {
            matC[i] = Matric.ars[i][0];
        }

        for (int i = 0; i < Matric.ars.length; i++) {
            if (Arrays.asList(matL).contains(Matric.ars[i][0]) && !Matric.ars[i][0].equals(" ")) {
                totalCommented = storeD(studS, totalCommented, i);
            } else if (!Arrays.asList(matL).contains(Matric.ars[i][0]) && !Matric.ars[i][0].equals(" ")) {
                totalAnonCommented = storeD(studU, totalAnonCommented, i);
            }
        }

        for (int i = 0; i < StudList.studList.length; i++) {
            if (!Arrays.asList(matC).contains(StudList.studList[i][0]) && StudList.studList[i][0] != null && !StudList.studList[i][0].equals("254660")) {
                studNS[0] = Title;
                studNS[totalNotCommented + 1][0] = String.valueOf(totalNotCommented + 1);
                studNS[totalNotCommented + 1][1] = StudList.studList[i][0];
                studNS[totalNotCommented + 1][2] = StudList.studList[i][1];
                studNS[totalNotCommented + 1][3] = "No Submission";
                totalNotCommented++;
            }
        }

        System.out.println("List of Student Submit");
        String[] rePrint = report(studS);
        for (int i = 0; i < report.length; i++) {
            if (rePrint[i] != null) {
                System.out.println(rePrint[i]);
            }
        }
        clear(rePrint);
        System.out.println("\nList of Student Not Submit");
        rePrint = report(studNS);
        for (int i = 0; i < report.length; i++) {
            if (rePrint[i] != null) {
                System.out.println(rePrint[i]);
            }
        }
        clear(rePrint);
        System.out.println("\nList of Unknown Submit");
        rePrint = report(studU);
        for (int i = 0; i < report.length; i++) {
            if (rePrint[i] != null) {
                System.out.println(rePrint[i]);
            }
        }
    }

    private static String[] report(String[][] list) {
        for (int i = 0; i < list.length; i++) {
            if (i == 1) {
                report[i] = String.format("|%5s|%8s|%39s|%40s|", "-----", "--------", "---------------------------------------", "----------------------------------------");
            } else if (i == 0 && list[i][0] != null) {
                report[i] = String.format("| %-4s| %-7s| %-38s| %-39s|", list[i][0], list[i][1], list[i][2], list[i][3]);
            } else if (list[i - 1][0] != null) {
                report[i] = String.format("| %-4s| %-7s| %-38s| %-39s|", list[i - 1][0], list[i - 1][1], list[i - 1][2], list[i - 1][3]);
            }
        }
        return report;
    }

    private static void clear(String[] s) {
        Arrays.fill(s, null);
    }

    private static int storeD(String[][] arrS, int total, int studentC) {
        arrS[0] = Title;
        arrS[total + 1][0] = String.valueOf(total + 1);
        if (arrS[total].length - 1 >= 0)
            System.arraycopy(Matric.ars[studentC], 0, arrS[total + 1], 1, arrS[total].length - 1);
        total++;
        return total;
    }
}
