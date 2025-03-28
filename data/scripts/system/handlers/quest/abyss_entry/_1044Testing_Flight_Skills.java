/*
 * This file is part of Encom.
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
package quest.abyss_entry;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;

public class _1044Testing_Flight_Skills extends QuestHandler {

	private final static int questId = 1044;
	private String[] rings = {"ELTNEN_AIR_BOOSTER_1", "ELTNEN_AIR_BOOSTER_2", "ELTNEN_AIR_BOOSTER_3", "ELTNEN_AIR_BOOSTER_4", "ELTNEN_AIR_BOOSTER_5", "ELTNEN_AIR_BOOSTER_6"};
	public _1044Testing_Flight_Skills() {
		super(questId);
	}
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env, 1922);
	}
	
	@Override
	public void register() {
		qe.registerOnLevelUp(questId);
		qe.registerQuestNpc(203901).addOnTalkEvent(questId);
		qe.registerQuestNpc(203930).addOnTalkEvent(questId);
		for (String ring: rings) {
			qe.registerOnPassFlyingRings(ring, questId);
		}
		qe.registerOnQuestTimerEnd(questId);
		qe.registerOnDie(questId);
		qe.registerOnEnterWorld(questId);
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		int targetId = env.getTargetId();
		QuestDialog dialog = env.getDialog();
		if (qs == null) {
			return false;
		}
		int var = qs.getQuestVarById(0);
		if (qs.getStatus() == QuestStatus.START) {
			switch (targetId) {
				case 203901: { //Telemachus.
					switch (dialog) {
						case START_DIALOG: {
							if (var == 0) {
								return sendQuestDialog(env, 1011);
							}
						} case STEP_TO_1: {
							return defaultCloseDialog(env, 0, 1);
						}
					}
					break;
				} case 203930: { //Daedalus.
					switch (dialog) {
						case START_DIALOG: {
							if (var == 1) {
								return sendQuestDialog(env, 1352);
							} else if (var == 8) {
								return sendQuestDialog(env, 3057);
							} else if (var == 9) {
								return sendQuestDialog(env, 1693);
							}
						} case SELECT_ACTION_1354: {
							if (var == 1 || var == 8) {
								playQuestMovie(env, 40);
								return sendQuestDialog(env, 1354);
							}
						} case STEP_TO_2: {
							if (var == 1) {
								QuestService.questTimerStart(env, 300); //5 Minutes.
								updateQuestStatus(env);
								return defaultCloseDialog(env, 1, 2);
							} else if (var == 8) {
								playQuestMovie(env, 40);
								QuestService.questTimerStart(env, 300); //5 Minutes.
								return defaultCloseDialog(env, 8, 2);
							}
						} case SET_REWARD: {
							if (var == 9) {
								qs.setStatus(QuestStatus.REWARD);
								updateQuestStatus(env);
				                return closeDialogWindow(env);
							}
						}
						default:
							break;
					}
				}
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
			if (targetId == 203901) { //Telemachus.
				if (dialog == QuestDialog.USE_OBJECT) {
					return sendQuestDialog(env, 10002);
				} else {
					return sendQuestEndDialog(env);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean onPassFlyingRingEvent(QuestEnv env, String flyingRing) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			if (rings[0].equals(flyingRing)) {
				changeQuestStep(env, 2, 3, false);
				return true;
			} else if (rings[1].equals(flyingRing)) {
				changeQuestStep(env, 3, 4, false);
				return true;
			} else if (rings[2].equals(flyingRing)) {
				changeQuestStep(env, 4, 5, false);
				return true;
			} else if (rings[3].equals(flyingRing)) {
				changeQuestStep(env, 5, 6, false);
				return true;
			} else if (rings[4].equals(flyingRing)) {
				changeQuestStep(env, 6, 7, false);
				return true;
			} else if (rings[5].equals(flyingRing)) {
				changeQuestStep(env, 7, 9, false);
				QuestService.questTimerEnd(env);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onQuestTimerEndEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var > 1 && var < 7) {
				changeQuestStep(env, var, 8, false);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onDieEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var > 1 && var < 7) {
				QuestService.questTimerEnd(env);
				return this.onQuestTimerEndEvent(env);
			}
		}
		return false;
	}
	
	@Override
	public boolean onEnterWorldEvent(QuestEnv env) {
		Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getStatus() == QuestStatus.START) {
			int var = qs.getQuestVarById(0);
			if (var > 1 && var < 7) {
				QuestService.questTimerEnd(env);
				return this.onQuestTimerEndEvent(env);
			}
		}
		return false;
	}
}