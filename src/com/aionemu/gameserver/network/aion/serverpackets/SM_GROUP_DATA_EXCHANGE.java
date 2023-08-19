/*
 * This file is part of Encom. **ENCOM FUCK OTHER SVN**
 *
 *  Encom is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Encom is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with Encom.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_GROUP_DATA_EXCHANGE extends AionServerPacket
{
	private byte[] byteData;
	private int action;
	private int unk2;

	public SM_GROUP_DATA_EXCHANGE(byte[] byteData, int action, int unk2) {
		this.action = action;
		this.byteData = byteData;
		this.unk2 = unk2;
	}

	public SM_GROUP_DATA_EXCHANGE(byte[] byteData) {
		this.action = 1;
		this.byteData = byteData;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeC(action); // action

		if (action != 1) {
			writeC(unk2); // unk
        }
		writeD(byteData.length);
		writeB(byteData);
	}
}