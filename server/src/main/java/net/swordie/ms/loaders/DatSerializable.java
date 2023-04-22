package net.swordie.ms.loaders;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/8/2018.
 */
public interface DatSerializable {

    void write(DataOutputStream dos) throws IOException ;

    DatSerializable load(DataInputStream dis) throws IOException ;
}
