package com.wcq.utils;

import org.apache.poi.hwpf.HWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordReader {
    public static void main(String[] args) {
        String file = "H:/templete.doc";
        read(file);
    }

    public static void read(String fileName){
        File file = new File(fileName);
        try {
            FileInputStream in = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(in);
            doc.getRange();
//            String content = word.getText().replaceAll("\\{param1\\}}", "123");



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
    }

    }
}
