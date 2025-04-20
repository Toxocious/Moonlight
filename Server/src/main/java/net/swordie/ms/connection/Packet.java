/*
    This file is part of Desu: MapleStory v62 Server Emulator
    Copyright (C) 2014  Zygon <watchmystarz@hotmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.swordie.ms.connection;

import net.swordie.ms.util.Util;

/**
 * Represents a packet to be sent over a TCP socket for MapleStory.
 * Very simply, it is an abstraction of raw data that applies some extra 
 * functionality because it is a MapleStory packet.
 *
 * @author Zygon
 */
public class Packet implements Cloneable {

    private byte[] data;

    public Packet(byte[] data) {
        this.data = new byte[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    public int getLength() {
        if (data != null) {
            return data.length;
        }
        return 0;
    }

    public int getHeader() {
        if (data.length < 2) {
            return 0xFFFF;
        }
        return ((data[0] & 0xFF) + ((data[1] & 0xFF) << 8));
    }

    public void setData(byte[] nD) {
        data = nD;
    }

    public byte[] getData() {
        return data;
    }
    
    @Override
    public String toString() {
        if (data == null) return "";
        return "[Pck] | " + Util.readableByteArray(data);
    }
    
    @Override
    public Packet clone() {
        return new Packet(data);
    }

    public void release(){}

}
