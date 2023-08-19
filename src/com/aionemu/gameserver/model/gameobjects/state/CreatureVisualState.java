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
package com.aionemu.gameserver.model.gameobjects.state;

/**
 * @author Sweetkr
 */
public enum CreatureVisualState {
	VISIBLE(0), // Normal
	HIDE1(1), // Hide I
	HIDE2(2), // Hide II
	HIDE3(3), // Hide by Artifact?
	HIDE5(5), //No idea :D
	HIDE10(10), // Hide from Npc?
	HIDE13(13), // Hide from Npc?
	HIDE20(20), // Hide from Npc?
	BLINKING(64); // Blinking when entering to zone

	private int id;

	private CreatureVisualState(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}