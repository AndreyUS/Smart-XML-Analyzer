package com.andrewusanin.writer;


public interface MessageWriter {

    /**
     * Writes message to source
     * @param message, the orignal message
     */
    void write(String message);
}
