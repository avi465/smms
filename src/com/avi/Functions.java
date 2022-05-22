package com.avi;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Functions {
    public static int recordCount(String fileName){
        int count = 0;
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                count++;
            }
            myReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println(fileName + "not found");
            exception.printStackTrace();
        }
        return count;
    }

    public static String[][] fileData(String fileName, int noOfColumns){
        int rowCount = Functions.recordCount(fileName);
        String[][] fileData = new String[rowCount][noOfColumns];
        try {
            int i = 0;
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                String[] token = data.split(",");
                for (int j=0;j<noOfColumns;j++){
                    fileData[i][j] = token[j];
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println(fileName + "not found");
            exception.printStackTrace();
        }
        return fileData;
    }

    public static String search(String fileName,int keyDataIndex,String key){
        String searchedData = "";
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                String[] token = data.split(",");
                if (key.equals(token[keyDataIndex])){
                    searchedData = data;
                }
            }
            myReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println(fileName + "not found");
            exception.printStackTrace();
        }
        return searchedData;
    }
}
