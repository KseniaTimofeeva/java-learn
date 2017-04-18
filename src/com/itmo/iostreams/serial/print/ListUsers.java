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

    public ListUsers() {
        code = 2;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public ListUsers getCommand() {
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
