package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int column = 0;

        if (messageLen % rows == 0) {
            column = messageLen / rows;
        }
        else {
            column = (messageLen + rows) / rows;
        }

        if (messageLen == 0) {
            return 1;
        }

        return column;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] encryptArray = new String[rows][determineColumns(message.length(), rows)];
        int looped = 0;
        for (int row = 0; row < encryptArray.length; row++) {
            for (int column = 0; column < encryptArray[row].length; column++) {
                if (looped < message.length()) {
                    encryptArray[row][column] = message.substring(looped, looped + 1);
                }
                else {
                    encryptArray[row][column] = "=";
                }
                looped++;
            }

        }
        return encryptArray;
    }

    public static String encryptMessage(String message, int rows) {
        String encryptedString = "";
        String[][] encryptedArray = generateEncryptArray(message, rows);

        for (int column = encryptedArray[0].length - 1; column >= 0; column--) {
            for (int row = 0; row < rows; row++) {
                encryptedString += encryptedArray[row][column];
            }
        }

        return encryptedString;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        if (encryptedMessage.length() % rows != 0) {
            return "";
        }


        String decryptedString = "";
        String[][] decryptedArray = generateEncryptArray(encryptedMessage, rows);

        int encryptedMessageIdx = 0;
        for (int column = decryptedArray[0].length - 1; column >= 0; column--) {
            for (int row = 0; row < rows; row++) {
                decryptedArray[row][column] = encryptedMessage.substring(encryptedMessageIdx, encryptedMessageIdx + 1);
                encryptedMessageIdx++;
            }
        }

        for (int row = 0; row < decryptedArray.length; row++) {
            for (int column = 0; column < decryptedArray[row].length; column++) {
                if (!decryptedArray[row][column].equals("=")) {
                    decryptedString += decryptedArray[row][column];
                }
            }
        }

        return decryptedString;
    }
}