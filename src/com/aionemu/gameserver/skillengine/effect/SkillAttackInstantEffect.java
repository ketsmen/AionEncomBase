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
package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.skillengine.action.DamageType;
import com.aionemu.gameserver.skillengine.change.Func;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SkillAttackInstantEffect")
public class SkillAttackInstantEffect extends DamageEffect {

	@XmlAttribute
	protected int rnddmg;// TODO should be enum and different types of random damage behaviour
	@XmlAttribute
	protected boolean cannotmiss;

	/**
	 * @return the rnddmg
	 */
	public int getRnddmg() {
		return rnddmg;
	}

	@Override
	public Func getMode() {
		return mode;
	}

	@Override
	public void calculate(Effect effect) {
		super.calculate(effect, DamageType.PHYSICAL);
	}

	/**
	 * @return the cannotmiss
	 */
	public boolean isCannotmiss() {
		return cannotmiss;
	}
}