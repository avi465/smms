package com.avi;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Functions {
//    this method return the no of rows/tuples in any file
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

//    this method return the whole file data of any file
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

//    this method return the data of any column of any file
    public static String[] getColumnData(String fileName,int columnIndex){
        String[] columnData = new String[Functions.recordCount(fileName)];
        try {
            int i = 0;
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                String[] token = data.split(",");
                columnData[i] = token[columnIndex];
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println(fileName + "not found");
            exception.printStackTrace();
        }
        return columnData;
    }

//    this method search the key in any file and return the correponding key data
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
