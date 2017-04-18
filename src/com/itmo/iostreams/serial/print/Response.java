package com.itmo.iostreams.serial.print;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * Created by ksenia on 19.04.2017.
 */
public class Response <T extends Command> implements Serializable {
    private T command;
    private Object answer;

    public Response() {
    }

    public Response(T command, Object answer) {
        this.command = command;
        this.answer = answer;
    }

    public T getCommand() {
        return command;
    }

    public Object getAnswer() {
        return answer;
    }


}
