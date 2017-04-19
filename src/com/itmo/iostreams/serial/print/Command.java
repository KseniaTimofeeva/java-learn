package com.itmo.iostreams.serial.print;

/**
 * Created by ksenia on 18.04.2017.
 */
public interface Command {
    int getCode();

    <T extends Command> T getCommand();

    void setAnswer(Object answer);

    Object getAnswer();
}
