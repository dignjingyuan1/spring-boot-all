package com.flow.snow.snow.learn;

import com.rabbitmq.tools.json.JSONUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReadAndWrite {

    private final static String FILE_PATH = "/Users/dingjingyuan/workspace/personal/file.txt";

    public void readText() throws IOException {
        // 普通读取文件
        System.out.println("________普通读取文件内容________");
        File file = new File(FILE_PATH);
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buff = new BufferedReader(inputReader);
        String data = null;
        while (null != (data = buff.readLine())){
            System.out.println(data);
        }

        System.out.println("________jdk1.8读取文件内容________");
        Files.lines(Paths.get(FILE_PATH)).forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        FileReadAndWrite fileReadAndWrite = new FileReadAndWrite();
        fileReadAndWrite.readText();
    }
}
