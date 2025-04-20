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
package net.swordie.ms.connection.netty;

import net.swordie.ms.connection.InPacket;

/**
 * Represents a packet handler for a received MapleStory net.swordie.ms.connection.packet.
 * 
 * @author Zygon
 * @param <E> specific type of NettyClient -> more than likely the inheritance
 */
public interface PacketHandler<E extends NettyClient> {
    
    boolean validateState(E c);
    
    void handle(E c, InPacket r);
}
