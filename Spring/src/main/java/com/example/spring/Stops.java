package com.example.spring;

import org.apache.tomcat.jni.Buffer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;


public class Stops {



    InputStream input = new URL("http://www.wienerlinien.at/ogd_realtime/doku/ogd/wienerlinien-ogd-haltepunkte.csv").openStream();

  //  public static void main(String[] args){
   //     SpringApplication.run(Stops.class, args);
    //}


    public Stops() throws IOException {
        Reader inputreader = new InputStreamReader(input);

        String resultRow = null;


        BufferedReader br = new BufferedReader(inputreader);
        String line;
        for(int i = 0;(line = br.readLine()) != null; i++) {
            String[] values = line.split(",");
            if(values[i].equals("123")) {
                resultRow = values[2];
                break;
            }
        }
        br.close();
        System.out.println(resultRow);
    }


}

