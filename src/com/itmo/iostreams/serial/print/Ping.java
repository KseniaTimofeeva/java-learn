package com.itmo.iostreams.serial.print;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by ksenia on 18.04.2017.
 */
public class Ping implements Command, Externalizable {

    private int code;

    public Ping() {
        code = 1;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public Ping getCommand() {
        return this;
    }
}
