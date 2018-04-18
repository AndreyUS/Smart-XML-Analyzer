package com.andrewusanin.writer;


public class ConsoleMessageWriter implements MessageWriter {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

}
