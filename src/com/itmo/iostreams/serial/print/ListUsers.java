package com.itmo.iostreams.serial.print;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by ksenia on 18.04.2017.
 */
public class ListUsers implements Command, Externalizable {
    private int code;

    private Object answer;

    public ListUsers() {
        code = 2;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    @Override
    public Object getAnswer() {
        return answer;
    }

    @Override
    public ListUsers getCommand() {
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(code);
        out.writeObject(answer);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        code = in.readInt();
        answer = in.readObject();
    }
}
