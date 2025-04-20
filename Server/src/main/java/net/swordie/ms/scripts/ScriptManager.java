package net.swordie.ms.scripts;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.field.Clock;
import net.swordie.ms.world.field.Field;

import java.util.Observer;
import java.util.concurrent.ScheduledFuture;

/**
 * Script manager {@code interface} used in all scripts.
 *
 * @author NullByte
 */
public interface ScriptManager extends Observer {

	/**
	 * Starts script from script name and type.
	 * @param scriptName
	 * @param scriptType
	 */
	void startScript(String scriptName, String scriptType);

	/**
	 * The character instance, can be null if none is implied.<br>
	 * Example: "sm.getChr()"
	 *
	 * @return The {@link Char} linked with the current script.
	 */
	Char getChr();

	/**
	 * The field instance, cannot be null, it's either set or as the {@link Char}'s field.<br>
	 * Example: "sm.getChar()"
	 *
	 * @return The {@link Field} linked with the current script.
	 */
	Field getField();

	/**
	 * This is the method to use to determine whether or not a script has a {@link Char}.<br>
	 * Example: "if(sm.isField()) {}"
	 *
	 * @return True if the script is field related and has no character.
	 */
	boolean isField();

	/**
	 * Sends a message box with previous / next buttons enabled.<br>
	 * Example: "sm.sendSay("Hello character!")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	int sendSay(String text);

	/**
	 * Sends a message box with the next button enabled only.<br>
	 * Example: "sm.sendNext("Click next!")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	int sendNext(String text);

	/**
	 * Sends a message box with the previous button enabled only.<br>
	 * Example: "sm.sendPrev("Click previous!")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	int sendPrev(String text);

	/**
	 * Sends a message box with the ok button enabled only.<br>
	 * Example: "sm.sendSayOkay("Click ok to continue.")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	int sendSayOkay(String text);

	/**
	 * Sends a message box with the defined image from wz.
	 * Example: unknown, ask Sjonnie for help here.
	 *
	 * @param image
	 * 		The image to display in the message box.
	 */
	int sendSayImage(String image);

	/**
	 * Sends a message box with the defined images from wz.
	 * Example: unknown, ask Sjonnie for help here.
	 *
	 * @param images
	 * 		The images to display in the message box.
	 */
	int sendSayImage(String[] images);

	/**
	 * Sends a message box with yes / no buttons.<br>
	 * Example: "sm.sendAskYesNo("Please click yes or no.")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	boolean sendAskYesNo(String text);

	/**
	 * Sends a message box with accept / decline buttons.<br>
	 * Example: "sm.sendAskAccept("Do you want to accept my quest?")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	boolean sendAskAccept(String text);

	/**
	 * Sends a message box asking a user for some text.<br>
	 * Example: "sm.sendAskText("Please input some text.", "Hello World!", 1, 20)"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 * @param defaultText
	 * 		The default text shown in the box.
	 * @param minLength
	 * 		The minimum length of the text. "Hi" would be a length of 2. "Hello" would be 5.
	 * @param maxLength
	 * 		The maximum length of the text. "Hi" would be a length of 2.
	 */
	String sendAskText(String text, String defaultText, short minLength, short maxLength);

	/**
	 * Sends a message box asking a user for a number.
	 * Example: "sm.sendAskNumber("Select a number between 1 and 10", 1, 1, 10)"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 * @param defaultNum
	 * 		The default number shown.
	 * @param min
	 * 		The minimum number.
	 * @param max
	 * 		The maximum number.
	 */
	int sendAskNumber(String text, int defaultNum, int min, int max);

	/**
	 * Sends a chat window for a quiz.
	 * Example: "sm.sendInitialQuiz(0, "Quest Title", "What is 1 + 1", 1, 1, 30)"
	 *
	 * @param type
	 * 		The type (0 for question, 1 for nothing)
	 * @param title
	 * 		The title of the quiz.
	 * @param problem
	 * 		The question of the quiz.
	 * @param hint
	 * 		The hint of the quiz.
	 * @param min
	 * 		The minimum length of the answer.
	 * @param max
	 * 		The maximum length of the answer.
	 * @param time
	 * 		The time allowed to answer the question, in seconds.
	 */
	int sendInitialQuiz(byte type, String title, String problem, String hint, int min, int max, int time);

	/**
	 * Sends a chat window for an initial speed quiz.
	 * Example: "sm.sendInitialSpeedQuiz(0, 0, 10, 2, 1, 10)"
	 *
	 * @param type
	 * 		The type (0 for question, 1 for nothing).
	 * @param quizType
	 * 		The type of quiz (experiment with this).
	 * @param answer
	 * 		The correct answer.
	 * @param correctAnswers
	 * 		Current amount of correct answers.
	 * @param remaining
	 * 		The remaining amount of questions.
	 * @param time
	 * 		The remaining amount of time, in seconds.
	 */
	int sendInitialSpeedQuiz(byte type, int quizType, int answer, int correctAnswers, int remaining, int time);

	/**
	 * Sends an IC quiz.
	 * Example: "sm.sendICQuiz(0, "What is 1 + 1", "It isn't 3", 30)"
	 *
	 * @param type
	 * 		The type (0 for question, 1 for nothing).
	 * @param text
	 * 		The question for the quiz.
	 * @param hintText
	 * 		The hint of the quiz.
	 * @param time
	 * 		The remaining amount of time, in seconds.
	 */
	int sendICQuiz(byte type, String text, String hintText, int time);

	/**
	 * Sends a chat window with Avatar options (different hairstyles, eyestyles, things like that)
	 * Example: "sm.sendAskAvatar("Choose your hair here!", false, false)"
	 *
	 * @param text
	 * 		The text to display.
	 * @param angelicBuster
	 * 		Whether or not the avatar should be in its dress up form.
	 * @param zeroBeta
	 * 		Whether or not the avatar should be in its beta form.
	 * @param options
	 * 		A list of hair/eye options available to choose
	 */
	int sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta, int... options);

	/**
	 * Sends a slide window to the client with a given dlgType.
	 * Example: "sm.sendAskSlideMenu(0)" (for the dimensional portal)
	 * @param dlgType the dialogue type.
	 */
	int sendAskSlideMenu(int dlgType);

	// Start of param methods ------------------------------------------------------------------------------------------

	/**
	 * Sets the player as the speaker instead of an npc.
	 */
	void setPlayerAsSpeaker();

	/**
	 * Sets the current chat type to be a brown box at the bottom of the screen.
	 */
	void setBoxChat();


	// Start helper methods for scripts --------------------------------------------------------------------------------

	/**
	 * Fully disposes the script.
	 * Example: "sm.dispose()"
	 */
	void dispose();



	// Character Stat-related methods ----------------------------------------------------------------------------------

	/**
	 * Gets the {@link Char}'s job.
	 */
	String getJob();

	/**
	 * Sets the {@link Char} to the specified job.
	 * Example: "sm.setJob(112)"
	 *
	 * @param jobID
	 * 		The id of the job.
	 */
	void setJob(short jobID);

	/**
	 * Add Skill Points to {@link Char}.
	 * Example: "sm.addSP(3)"
	 *
	 * @param amount
	 * 		The amount of Skill Points added.
	 */
	void addSP(int amount, boolean jobAdv);

	/**
	 * Sets the Skill Points of {@link Char} to the specified amount.
	 * Example: "sm.setSP(15)"
	 *
	 * @param amount
	 * 		The amount to which the Skill Points will be set.
	 */
	void setSP(int amount);

	/**
	 * Add Ability Points to {@link Char}.
	 * Example: "sm.addAP(5)"
	 *
	 * @param amount
	 * 		The amount of Ability Points added
	 */
	void addAP(int amount);

	/**
	 * Sets the Ability Points of {@link Char} to the specified amount.
	 * Example: "sm.setAP(15)"
	 *
	 * @param amount
	 * 		The amount to which the Ability Points will be set.
	 */
	void setAP(int amount);


	/**
	 * Resets the Ability Points of {@link Char}.
	 * Example: "sm.resetAp(false)"
	 * @param hpMp
	 *		Whether or not to reset the hp and mp also.
	 */
	void resetAP(boolean hpMp);

	/**
	 * Resets the Ability Points of {@link Char} and adds it to the job's main stat.
	 * @param hpMp
	 * 		Determines resetting hp and mp as well.
	 * @param jobId
	 * 		Job of the main stat to add the total Ability Points to.
	 */
	void resetAP(boolean hpMp, short jobId);

	/**
	 * Sets the STR of {@link Char} to the specified amount.
	 * Example: "sm.setSTR(15)"
	 *
	 * @param amount
	 * 		The amount to which the STR will be set.
	 */
	void setSTR(short amount);

	/**
	 * Sets the INT of {@link Char} to the specified amount.
	 * Example: "sm.setINT(15)"
	 *
	 * @param amount
	 * 		The amount to which the INT will be set.
	 */
	void setINT(short amount);

	/**
	 * Sets the DEX of {@link Char} to the specified amount.
	 * Example: "sm.setDEX(15)"
	 *
	 * @param amount
	 * 		The amount to which the DEX will be set.
	 */
	void setDEX(short amount);

	/**
	 * Sets the LUK of {@link Char} to the specified amount.
	 * Example: "sm.setLUK(15)"
	 *
	 * @param amount
	 * 		The amount to which the LUK will be set.
	 */
	void setLUK(short amount);

	/**
	 * Sets the Max HP of {@link Char} to the specified amount.
	 * Example: "sm.setMaxHP(15)"
	 *
	 * @param amount
	 * 		The amount to which the Max HP will be set.
	 */
	void setMaxHP(int amount);

	/**
	 * Sets the Max MP of {@link Char} to the specified amount.
	 * Example: "sm.setMaxMP(15)"
	 *
	 * @param amount
	 * 		The amount to which the Max MP will be set.
	 */
	void setMaxMP(int amount);


	/**
	 * A Combined method, that sets the {@link Char} JobID to the specified job ID
	 * as well as add 5 AP  and  3 SP.
	 * Example: "sm.jobAdvance(112)"
	 *
	 * @param jobID
	 * 		The id of the Job.
	 */
	void jobAdvance(short jobID);

	/**
	 * Gives the {@link Char} the specified Exp.
	 * Example: "sm.giveExp(5000)"
	 *
	 * @param expGiven
	 * 		The amount of Exp given
	 */
	void giveExp(long expGiven);

	/**
	 * Gives the {@link Char} the specified Exp, however no Exp Message is given
	 * Example: "sm.giveExp(5000)"
	 *
	 * @param expGiven
	 * 		The amount of Exp given
	 */
	void giveExpNoMsg(long expGiven);


	/**
	 * Changes a part of the characterlook of a Char.
	 * @param look the id of the look (skin/eyes/hair) to change
	 */
	void changeCharacterLook(int look);

	/**
	 * Adds the specified skill to the Char.
	 *
	 * @param skillId
	 * 		The id of the skill
	 * @param slv
	 * 		The skill level of the skill
	 */
	void giveSkill(int skillId, int slv, int maxLvl);

	/**
	 * Removes the specified skill to the Char.
	 *
	 * @param skillId
	 * 		The id of the skill.
	 */
	void removeSkill(int skillId);

	/**
	 * Adds a given amount of levels to the Char. Also includes adding AP/SP.
	 * @param level the amount of levels to give.
	 */
	void addLevel(int level);



	// Field-related methods -------------------------------------------------------------------------------------------

	/**
	 * Warps the {@link Char} to the specified field.
	 * Example: "sm.warp(100)"
	 *
	 * @param fieldID
	 * 		The id of the field to warp the {@link Char} to.
	 */
	void warp(int fieldID);

	/**
	 * Warps the linked {@link Char} to the specified field at the specified portal.
	 * Example: "sm.warp(100, 10)"
	 *
	 * @param fieldID
	 * 		The id of the field.
	 * @param portalID
	 * 		The id of the portal.
	 */
	void warp(int fieldID, int portalID);

	/**
	 * Changes the channel and warps the given Char to the given field.
	 * @param channel the channel to change to
	 * @param fieldID the field id to warp to
	 */
	void changeChannelAndWarp(int channel, int fieldID);

	/**
	 * Teleports {@link Char} to the portal ID specified.
	 * Example: "sm.teleportToPortal(0)"
	 *
	 * @param portalId
	 * 		The id of the Portal
	 */
	void teleportToPortal(int portalId);

	Drop getDropInRect(int itemID, int rectRange);
	/**
	 * Gets the id of the linked {@link Field}
	 * Example: "sm.getFieldID()"
	 *
	 * @return The id of the linked {@link Field}.
	 */
	int getFieldID();

		/**
	 * Warp {@link Char} into the instanced field of the specified id.
	 * Example: "sm.warpInstanceIn(100000000)"
	 *
	 * @param id
	 * 		The id of the field to be instanced
	 */
	void warpInstanceIn(int id);

	/**
	 * Warp {@link Char} out of the instanced field into a non-instanced field of the specified id.
	 * Example: "sm.warpInstanceOut(100000000)"
	 *
	 * @param id
	 * 		The id of the non-instanced field to be warped into
	 */
	void warpInstanceOut(int id);

	/**
	 * Gets the ID of the current return {@link Field}.
	 * Example: "sm.getReturnField()"
	 *
	 * @return The ID of the linked return {@link Field}.
	 */
	int getReturnField();

	/**
	 * Sets the return {@link Field} to the {@link Field} linked to the {@link ScriptManager}.
	 * Example: "sm.setReturnField()"
	 */
	void setReturnField();

	/**
	 * Sets the return {@link Field}.
	 * Example: "sm.setReturnField(100)"
	 *
	 * @param fieldID
	 * 		The id of the {@link Field}.
	 */
	void setReturnField(int fieldID);

	/**
	 * Determines if there are mobs present in the {@link Field} linked to the {@link
	 * ScriptManager}.
	 * Example: "if(sm.hasMobsInField()) {}"
	 *
	 * @return True if there are mobs in the linked {@link Field}.
	 */
	boolean hasMobsInField();

	/**
	 * Waits for a mob to die on the char's current field.
	 * @return the mob that died
	 */
	Mob waitForMobDeath();

	/**
	 * Waits for a mob with a given ID to die on the char's current field.
	 * @param possibleMobs the ids the mob can have. If multiple ids are given, this will return when there is a single match
	 * @return the mob that died
	 */
	Mob waitForMobDeath(int... possibleMobs);

	/**
	 * Determines if there are mobs present in a defined {@link Field}.
	 * Example: "if(sm.hasMobsInField(100)) {}"
	 *
	 * @param fieldID
	 * 		The id of the {@link Field} to receive info from.
	 *
	 * @return True if there are mobs in the {@link Field}.
	 */
	boolean hasMobsInField(int fieldID);

	/**
	 * Gets the number of mobs in the {@link Field} linked to the {@link ScriptManager}
	 * Example: "sm.getAmountOfMobsInField()"
	 *
	 * @return The number of mobs in the linked {@link Field}.
	 */
	int getAmountOfMobsInField();

	/**
	 * Gets the number of mobs from a selected {@link Field}.
	 * Example: "sm.getAmountOfMobsInField(100)"
	 *
	 * @param fieldID
	 * 		The fieldID of the {@link Field} to get the number of mobs in.
	 *
	 * @return The number of mobs in the selected {@link Field}.
	 */
	int getAmountOfMobsInField(int fieldID);

	/**
	 * Shows an Effect from the directory specified.
	 *
	 * @param dir
	 * 		directory towards the effect
	 * @param delay
	 * 		delay (ms) till the effect gets displayed
	 */
	void showFieldEffect(String dir, int delay);

	/**
	 * Shows an effect from the object name specified
	 * @param objectEffectName
	 * 		Name of the object's effect
	 */

	void showObjectFieldEffect(String objectEffectName);

	/**
	 * Drops the specified Item on the field at the given position (x, y).
	 *
	 * @param itemId
	 * 		The Id of the Item to be spawned.
	 * @param x
	 * 		The X-coordinate of the Position.
	 * @param y
	 * 		The Y-Coordinate of the Position.
	 */
	void dropItem(int itemId, int x, int y);


	/**
	 * Teleport the player to a Position in the current field.
	 *
	 * @param position
	 * 		The position to be teleported to.
	 */
	void teleportInField(Position position);

	/**
	 * Teleport the player to a Position in the current field.
	 *
	 * @param x
	 * 		The position's x coordinate to be teleported to.
	 * @param y
	 * 		The position's y coordinate to be teleported to.
	 */
	void teleportInField(int x, int y);



	// Life-related methods --------------------------------------------------------------------------------------------


	// NPC methods

	/**
	 * Spawns an NPC on the {@link Char} field, of the specified template id, and with the specified x and y co-ordinates.
	 * Example: "sm.spawnNpc(1000000)"
	 *
	 * @param npcId
	 * 		The id of the NPC to be spawned.
	 * @param x
	 * 		The x co-ordinate in the Field for the NPC to be spawned at.
	 * @param y
	 * 		The y co-ordinate in the Field for the NPC to be spawned at.
	 */
	void spawnNpc(int npcId, int x, int y);

	/**
	 * Spawns an NPC on the {@link Char} field, of the specified template id, with the specified x and y co-ordinates and a boolean if the npc should be flipped or not.
	 * Example: "sm.spawnNpc(1000000, 10, 10, true)"
	 *
	 * @param npcId
	 * 		The id of the NPC to be spawned.
	 * @param x
	 * 		The x co-ordinate in the Field for the NPC to be spawned at.
	 * @param y
	 * 		The y co-ordinate in the Field for the NPC to be spawned at.
	 *
	 * @param flip
	 * 		If the npc should be flipped
	 */
	void spawnNpc(int npcId, int x, int y, boolean flip);
	/**
	 * Removes the NPC on the {@link Char} field, with the specified template id.
	 * Example: "sm.spawnNpc(1000000)"
	 *
	 * @param npcId
	 * 		The id of the NPC to be spawned.
	 */
	void removeNpc(int npcId);

	/**
	 * Opens a dialogue with the npc specified.
	 * Example: "sm.openNpc(1000000)"
	 *
	 * @param npcId
	 * 		The id of the NPC's dialogue to be opened
	 */
	void openNpc(int npcId);

	/**
	 * Opens a shop defined by the shop ID.
	 * Example: "sm.openShop(100)"
	 *
	 * @param shopID
	 * 		The shop ID to define which shop to open.
	 */
	void openShop(int shopID);

	/**
	 * Opens a trunk (storage) with a given NPC.
	 * Example: "sm.openTrunk(100)"
	 *
	 * @param npcTemplateID
	 * 		The npc template ID to open the trunk with.
	 */
	void openTrunk(int npcTemplateID);

	/**
	 * Sets the current speaker template id for npc chat.
	 * Example: "sm.setSpeakerID(1010100)"
	 * @param templateID the speaker's template id
	 */
	void setSpeakerID(int templateID);
        
        /**
	 * Sets the current speaker type for npc chat.
	 * Example: "sm.setSpeakerType(4)"
         * Default type for speaker type is 4
	 * @param speakerType the speaker type
	 */
	void setSpeakerType(byte speakerType);
        
        
	/**
	 * Hides the specified Npc (specified by Template id)
	 *
	 * @param npcTemplateId Template Id of Npc to be hidden
	 * @param hideTemplate true to hide. false to show
	 * @param hideNameTag true to hide. false to show
	 */
	void hideNpcByTemplateId(int npcTemplateId, boolean hideTemplate, boolean hideNameTag);

	/**
	 * Hides the specified Npc (specified by Object id)
	 *
	 * @param npcObjId Object Id of Npc to be hidden
	 * @param hideTemplate true to hide. false to show
	 * @param hideNameTag true to hide. false to show
	 */
	void hideNpcByObjectId(int npcObjId, boolean hideTemplate, boolean hideNameTag);

	/**
	 * Forces the specified Npc (Template Id) to move in the specified direction
	 *
	 * @param npcTemplateId Template Id of the Npc to be moved
	 * @param left if true, Npc moves to the left. if false, Npc moves to the right
	 * @param distance distance traveled by the Npc
	 * @param speed speed at which the Npc travels (a number between 10 and 300)
	 */
	void moveNpcByTemplateId(int npcTemplateId, boolean left, int distance, int speed);

	/**
	 * Forces the specified Npc (Object Id) to move in the specified direction
	 *
	 * @param npcObjId Object Id of the Npc to be moved
	 * @param left if true, Npc moves to the left. if false, Npc moves to the right
	 * @param distance distance traveled by the Npc
	 * @param speed speed at which the Npc travels (a number between 10 and 300)
	 */
	void moveNpcByObjectId(int npcObjId, boolean left, int distance, int speed);

	/**
	 * Forces the specified Npc (Template Id) to flip
	 *
	 * @param npcTemplateId Template Id of the Npc to be flipped
	 * @param left if true, Npc will face left. if false, Npc will face right
	 */
	void flipNpcByTemplateId(int npcTemplateId, boolean left);

	/**
	 * Forces the specified Npc (Object Id) to flip
	 *
	 * @param npcObjId Object Id of the Npc to be flipped
	 * @param left if true, Npc will face left. if false, Npc will face right
	 */
	void flipNpcByObjectId(int npcObjId, boolean left);

	/**
	 * Forces the Npc to use do the specified special action.
	 *
	 * @param npcTemplateId Template Id of the Npc to do the Special Action
	 * @param effectName Effect name to be shown
	 * @param duration if duration > 0. The Effect will repeat for duration (ms)
	 */
	void showNpcSpecialActionByTemplateId(int npcTemplateId, String effectName, int duration);

	/**
	 * Forces the Npc to use do the specified special action.
	 *
	 * @param npcObjId Object Id of the Npc to do the Special Action
	 * @param effectName Effect name to be shown
	 * @param duration if duration > 0. The Effect will repeat for duration (ms)
	 */
	void showNpcSpecialActionByObjectId(int npcObjId, String effectName, int duration);



	// Mob methods

	/**
	 * Spawns a mob on the {@link Char} linked to the {@link ScriptManager}.
	 * Example: "sm.spawnMobOnChar(100)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 */
	void spawnMobOnChar(int mobID);

	/**
	 * Spawns a mob on the {@link Char} linked to the {@link ScriptManager}.
	 * Example: "sm.spawnMobOnChar(100, false)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 */
	void spawnMobOnChar(int mobID, boolean respawnable);

	/**
	 * Spawns a defined mob.
	 * Example: "sm.spawnMob(100)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 */
	void spawnMob(int mobID);

	/**
	 * Spawns a defined mob.
	 * Example: "sm.spawnMob(100, false)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 */
	void spawnMob(int mobID, boolean respawnable);

	/**
	 * Spawns a defined mob at an x/y location.
	 * Example: "sm.spawnMob(100, 0, 0, false)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param x
	 * 		The x coordinate of the mob.
	 * @param y
	 * 		The y coordinate of the mob.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 */
	void spawnMob(int mobID, int x, int y, boolean respawnable);

	/**
	 * Spawns a defined mob at an x/y location.
	 * Example: "sm.spawnMob(100100, 0, 0, false, 1000)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param x
	 * 		The x coordinate of the mob.
	 * @param y
	 * 		The y coordinate of the mob.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 * @param hp
	 * 		The hp that the mob should start out with
	 */
	void spawnMob(int mobID, int x, int y, boolean respawnable, long hp);

	/**
	 * Removes the specified mob from the {@link Char}'s field.
	 *
	 * @param id
	 * 		The Object Id of the monster.
	 */
	void removeMobByObjId(int id);

	/**
	 * Removes the specified mob(s) from the {@link Char}'s field.
	 *
	 * @param id
	 * 		The template id of the monster(s).
	 */
	void removeMobByTemplateId(int id);

	/**
	 * Shows the {@link Char}'s HP with the pre-defined template.
	 * Example: "sm.showHP()"
	 */
	void showHP();

	/**
	 * Shows the {@link Char}'s HP with the defined template.
	 * Example: "sm.showHP(100)"
	 *
	 * @param templateID
	 * 		The template to show the HP with.
	 */
	void showHP(int templateID);



	// Reactor methods

	/**
	 * Removes the reactor in the {@link Field}.
	 * Example: "sm.removeReactor()"
	 */
	void removeReactor();

	/**
	 * Spawns a reactor in {@link Char} field.
	 *
	 * @param reactorId
	 * 		The Id of the Reactor spawned.
	 * @param x
	 * 		The X-coordinate of the Position.
	 * @param y
	 * 		The Y-coordinate of the Position.
	 */
	void spawnReactor(int reactorId, int x, int y);

	/**
	 * Returns whether or not there is a Reactor on the {@link Char} field.
	 *
	 * @return
	 * 		Returns a boolean, true if there is 1 or more reactor(s) on the field.
	 */
	boolean hasReactors();

	/**
	 * Returns the quantity of Reactors in {@link Char} field.
	 *
	 * @return
	 * 		Returns the quantity of reactors in the field.
	 */
	int getReactorQuantity();



	// Party-related methods -------------------------------------------------------------------------------------------

	/**
	 * Gets the {@link Char} linked to the {@link ScriptManager}'s {@link Party}.
	 * Example: "sm.getParty()"
	 *
	 * @return The {@link Party} of the linked {@link Char}.
	 */
	Party getParty();

	/**
	 * Gets the {@link Char} linked's Party size
	 *
	 * @return The amount of members in {@link Char} linked's Party
	 */
	int getPartySize();

	/**
	 * Checks if the {@link Char} linked to the {@link ScriptManager} is the {@link Party} leader.
	 * Example: "if(sm.isPartyLeader()) {}"
	 *
	 * @return True if the linked {@link Char} is the {@link Party} leader.
	 */
	boolean isPartyLeader();

	/**
	 * Checks whether or not the whole party is in the same map, and in the same channel.
	 * Also checks if the {@link Char} linked to the {@link ScriptManager} is the leader.
	 * Example: "if(sm.checkParty()) {}"
	 *
	 * @param event the boss to check the cooldown for. May be null.
	 * @param level the required level to enter the boss
	 *
	 * @return True if the entire party is in the same location/channel and the leader is the linked
	 * {@link Char}.
	 */

	boolean checkParty(EventType event, int level);


	// Guild-related methods -------------------------------------------------------------------------------------------

	/**
	 * Shows a window to the linked {@link Char} for creating a guild.
	 * Example: "sm.showGuildCreateWindow()"
	 */
	void showGuildCreateWindow();

	/**
	 * Checks if an alliance name is valid.
	 * Example: "sm.checkAllianceName("Swordie")"
	 */
	boolean checkAllianceName(String name);



	// Chat-related methods --------------------------------------------------------------------------------------------

	/**
	 * Redirected to {@link #chatRed(String)}
	 * Example: "sm.chat("Hello User.")"
	 *
	 * @param text
	 * 		The text sent to the linked {@link Char}.
	 *
	 * @see #chatRed(String)
	 */
	void chat(String text);

	/**
	 * Sends a red message in the main chat box.
	 * Example: "sm.chatRed("Hello User.")"
	 *
	 * @param text
	 * 		The text sent to the linked {@link Char}.
	 */
	void chatRed(String text);

	/**
	 * Sends a blue message in the main chat box.
	 * Example: "sm.chatBlue("Hello User.")"
	 *
	 * @param text
	 * 		The text sent to the linked {@link Char}.
	 */
	void chatBlue(String text);

	/**
	 * Sends a script message with the specified text
	 * Example: "sm.chatScript("Hello User.")"
	 *
	 * @param text
	 * 		The text send to the linked {@link Char}.
	 */
	void chatScript(String text);



	// Inventory-related methods ---------------------------------------------------------------------------------------

	/**
	 * Gives the linked {@link Char}'s nx.
	 * @param amount
	 */
	void giveNX(int amount);

	/**
	 * Changes the linked {@link Char}'s mesos.
	 * Example: "sm.giveMesos(100000)"
	 *
	 * @param mesos
	 * 		The amount of mesos to change the {@link Char}'s mesos by.
	 */
	void giveMesos(long mesos);

	/**
	 * Changes the linked {@link Char}'s mesos.
	 * Example: "sm.deductMesos(100000)"
	 *
	 * @param mesos
	 * 		The amount of mesos to change the {@link Char}'s mesos by.
	 */
	void deductMesos(long mesos);

	/**
	 * Gets the mesos of the linked {@link Char}.
	 * Example: "sm.getMesos()"
	 *
	 * @return The mesos of the linked {@link Char}.
	 */
	long getMesos();

	/**
	 * Gives the linked {@link Char} the defined item.
	 * Example: "sm.giveItem(100)"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 */
	void giveItem(int itemID);

	/**
	 * Gives the linked {@link Char} the specified quantity of the defined item.
	 * Example: "sm.giveItem(100, 1)"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 * @param quantity
	 * 		The quantity of the item.
	 */
	void giveItem(int itemID, int quantity);

	/**
	 * Gives an item to the player, and equips it if the item is an equip. Will put the old equip (if there was any)
	 * back into the player's inventory.
	 * Example "sm.giveAndEquip(1302000)"
	 * @param id
	 * 		The ID of the item.
	 */
	void giveAndEquip(int id);

	/**
	 * Determines if the linked {@link Char} has the specified item.
	 * Example: "if(sm.hasItem(100)) {}"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 *
	 * @return True if the link {@link Char} has the item.
	 */
	boolean hasItem(int itemID);

	/**
	 * Determines if the linked {@link Char} has the specified item equipped.
	 *
	 * @param itemID
	 * 		The ID of the item.
	 *
	 * @return True if the character has the item equipped.
	 */
	boolean isEquipped(int itemID);

	/**
	 * Determines if the linked {@link Char} has the specified item with the specified quantity.
	 * Example: "if(sm.hasItem(100, 3)) {}"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 * @param quantity
	 * 		The quantity of the item.
	 *
	 * @return True if the link {@link Char} has the item with the specified quantity.
	 */
	boolean hasItem(int itemID, int quantity);

	/**
	 * Consumes 1 of a specified item.
	 * Example: "sm.consumeItem(100)"
	 *
	 * @param itemID
	 * 		The ID of the consumed item.
	 */
	void consumeItem(int itemID);

	/**
	 * Consumes a specified item with a specified quantity.
	 * Example: "sm.consumeItem(100, 1)"
	 *
	 * @param itemID
	 * 		The ID of the consumed item.
	 * @param quantity
	 * 		The quantity of the amount of items consumed.
	 */
	void consumeItem(int itemID, int quantity);

	/**
	 * Gives the CTS to the linked {@link Char} of the specified item id
	 *
	 * @param id
	 * 		The ID of the item
	 */
	void useItem(int id);

	/**
	 * Returns the amount of items the linked {@link Char} has of the specified id.
	 * Example: "if(getQuantityOfitem(100) > 50) {}"
	 *
	 * @param id
	 * 		The ID of the item.
	 *
	 * @return Quantity of specified id in the link {@link Char} inventory.
	 */
	int getQuantityOfItem(int id);

	/**
	 * Determines if the linked {@link Char} can hold a specific item.
	 * Example: "if sm.canHold(100):"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 *
	 * @return True if the linked {@link Char} can hold the item.
	 */
	boolean canHold(int itemID);/**

	 * Determines if the linked {@link Char} can hold a specific item.
	 * Example: "if(sm.canHold(1302000, 300)) {}"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 * @param quantity
	 * 		The quantity of the item
	 *
	 * @return True if the linked {@link Char} can hold the item.
	 */
	boolean canHold(int itemID, int quantity);
	/**
	 * Returns the amount of empty slots {@link Char} has in the specified Inventory Type.
	 * Example: "sm.getEmptyInventorySlots(InvType.EQUIP)"
	 *
	 * @param invType
	 * 		The Inventory Type to be used.
	 * @return
	 * 		The amount of empty slots in the specified Inventory Type.
	 */
	int getEmptyInventorySlots(InvType invType);

	void giveGift(int itemId);

	// Quest-related methods -------------------------------------------------------------------------------------------

	/**
	 * Completes a quest, and gives the rewards to the Char. Only works if the Char currently has the quest, and has
	 * the requirements to complete it.
	 * Example: "sm.completeQuest(10001)"
	 *
	 * @param questID
	 * 		The ID of the quest.
	 */
	void completeQuest(int questID);

	/**
	 * Completes a quest, and gives the rewards to the Char. Does not check for anything.
	 * Example: "sm.completeQuestNoCheck(10001)"
	 *
	 * @param questID
	 * 		The ID of the quest
	 */
	void completeQuestNoCheck(int questID);

	/**
	 * Completes a quest without giving quest rewards.
	 * Example: "sm.completeQuestNoRewards(10001)"
	 *
	 * @param questID
	 * 		The ID of the quest.
	 */
	void completeQuestNoRewards(int questID);

	/**
	 * Starts a quest without checking quest requirements.
	 * Example: "sm.startQuestNoCheck(100)"
	 *
	 * @param questID
	 * 		The ID of the quest.
	 */
	void startQuestNoCheck(int questID);

	/**
	 * Starts a quest and checks the quest requirements.
	 * Example: "sm.startQuest(100)"
	 *
	 * @param id
	 * 		The id of the quest.
	 */
	void startQuest(int id);

	/**
	 * Returns whether or not {@link Char} has the specified quest in progress.
	 * Example: "if sm.hasQuest(100):"
	 *
	 * @param id
	 * 		The id of the quest.
	 * @return
	 * 		True if {@link Char} has the quest in progress. False if the quest is done or not started.
	 */
	boolean hasQuest(int id);

	/**
	 * Returns whether or not {@link Char} has completed the specified quest.
	 * Example: "if sm.hasQuestCompleted(100):"
	 *
	 * @param id
	 * 		The id of the quest.
	 * @return
	 * 		True if {@link Char} has the quest completed. False if the quest isn't started or in progress.
	 */
	boolean hasQuestCompleted(int id);



	// Boss-related methods --------------------------------------------------------------------------------------------

	/**
	 * Sets the {@link Char}'s Instance Death Count to the specified amount.
	 *
	 * @param deathCount
	 * 		The Death Count set
	 */
	void setDeathCount(int deathCount);

	/**
	 * Creates Obstacle Atoms randomly within the map from the top position, it dies when hitting a platform.
	 *
	 * @param oae
	 * 		The Obstacle Atom, from this we grab the Atom Type and the Atom HitBox.
	 * @param key
	 * 		The Key is given when the Obstacle Atom Collides with something. as to allow a way to differentiate Atoms.
	 * @param damage
	 * 		The amount of damage done to the player in HP%.
	 * @param velocity
	 * 		The speed at which the Atom falls down.
	 * @param angle
	 * 		The angle at the Atom will travel
	 * @param amount
	 * 		The amount of atoms spawned in 1 method.
	 * @param proc
	 * 		The chance (out of 100) of creating an atom. As to randomise the creation.
	 */
	void createObstacleAtom(ObtacleAtomEnum oae, int key, int damage, int velocity, int angle, int amount, int proc);



	// Character Temporary Stat-related methods ------------------------------------------------------------------------

	/**
	 * Gives the CTS to the linked {@link Char} with specified CTS, nOption, rOption, time
	 *
	 * @param cts
	 * 		The CTS of the buff to be given
	 * @param nOption
	 * 		The nOption of the buff to be given
	 * @param rOption
	 * 		The rOption of the buff to be given
	 * @param time
	 * 		The tOption of the buff to be given
	 */
	void giveCTS(CharacterTemporaryStat cts, int nOption, int rOption, int time);

	/**
	 * Removes a CTS from the linked {@link Char} specified by CTS
	 *
	 * @param cts
	 * 		The CTS to be removed
	 */
	void removeCTS(CharacterTemporaryStat cts);

	/**
	 * Removes a whole buff from the linked {@link Char} specified by the Skill ID
	 * @param skillId
	 * 		The ID of the Skill
	 */
	void removeBuffBySkill(int skillId);

	/**
	 * Return boolean if the linked {@link Char} has the specified CTS
	 *
	 * @param cts
	 * 		CTS to be checked
	 *
	 * @return
	 * 		return true if the linked {@link Char} has the specified CTS
	 */
	boolean hasCTS(CharacterTemporaryStat cts);

	/**
	 * Return nOption from the linked {@link Char} based on the specified CTS
	 *
	 * @param cts
	 * 		CTS to return the nOption from
	 *
	 * @return
	 * 		return nOption from the CTS specified
	 */
	int getnOptionByCTS(CharacterTemporaryStat cts);

	/**
	 * Makes the {@link Char} ride a mount with the specified id.
	 *
	 * @param mountID
	 * 		The id of the Mount
	 */
	void rideVehicle(int mountID);



	// InGameDirectionEvent methods ------------------------------------------------------------------------------------

	/**
	 * Moves the Client's camera at the given speed towards the given position.
	 *
	 * @param back
	 * 		if true, it will move the Camera to the original position.
	 * 		if false, it will move the Camera to the given position, at the given speed.
	 * @param speed
	 * 		speed of the camera,  in Pixel per second.
	 * @param x x Position
	 * @param y y Position
	 * 		Position for the Camera to move to.
	 */
	int moveCamera(boolean back, int speed, int x, int y);

	/**
	 * Zooms the Camera in at the given position.
	 *
	 * Scale: 1000 is normal.
	 * Higher = Zoom In
	 * Lower = Zoom Out
	 *
	 * @param inZoomDuration Time the Zooming takes
	 * @param scale Zoom Scale
	 * @param x x Position
	 * @param y y Position
	 * 		Screen Center Position
	 */
	int zoomCamera(int inZoomDuration, int scale, int x, int y);

	/**
	 * Resets the Camera from a still position to following the User again.
	 *
	 */
	void resetCamera();

	/**
	 * sends a delay to the client, after the delay it'll send a response with which you can run action function in the script
	 *
	 * @param delay delay in milliseconds
	 */
	int sendDelay(int delay);

	/**
	 * Does a specified In Game Direction Event and sends a delay 'sendDelay'
	 *
	 * @param delay delay in milliseconds
	 * @param methodName method to be ran
	 * @param args arguments used in the specified method
	 */
	void doEventAndSendDelay(int delay, String methodName, Object...args);

	/**
	 * Forces the User to move in the specified direction for the specified distance
	 *
	 * @param left Direction the player moves in. True = Left. False = Right
	 * @param distance Distance to move
	 */
	void forcedMove(boolean left, int distance);

	/**
	 * Forces the User to flip
	 *
	 * @param left True = flip Left. False = flip Right
	 */
	void forcedFlip(boolean left);

	/**
	 * Forces the User to do an action/stance
	 *
	 * @param type The Action Done, depending on Type
	 * @param duration The duration the action is held
	 */
	void forcedAction(int type, int duration);

	/**
	 * Forces an action by the user, such as jumping
	 *
	 * @param type type of action used. refer to: {@link net.swordie.ms.enums.ForcedInputType}
	 */
	void forcedInput(int type);

	/**
	 * Hides the user
	 *
	 * @param hide true: hide.  false: show
	 */
	void hideUser(boolean hide);


	/**
	 * Shows an Effect from the given path for 'duration' duration at 'position' position. Other variables are not used atm
	 *
	 * @param path path to the Effect
	 * @param duration Effect duration
	 * @param x y Position
	 * @param y x Position
	 * 		Position for the Effect
	 * @param z
	 * @param npcIdForExtend
	 * @param onUser if true, the effect will follow the player | if false, the effect will stay static
	 * @param idk2
	 */
	void showEffect(String path, int duration, int x, int y, int z, int npcIdForExtend, boolean onUser, int idk2);

	// Clock methods ---------------------------------------------------------------------------------------------------

	/**
	 * Shows a timer on top with precision in milliseconds. Will automatically remove itself after the timer has
	 * expired.
	 * @param seconds the amount of seconds the stopwatch should start out with
	 */
	Clock createStopWatch(int seconds);

	/**
	 * Shows a timer on top with precision in minutes/seconds. Will automatically remove itself after the timer has
	 * expired.
	 * @param seconds the amount of seconds the stopwatch should start out with
	 */
	Clock createClock(int seconds);

	/**
	 * Shows a timer on top with precision in hour/minutes/seconds. Will automatically remove itself after the timer
	 * has expired.
	 * @param hours the amount of hours the stopwatch should start out with
	 * @param minutes the amount of minutes the stopwatch should start out with
	 * @param seconds the amount of seconds the stopwatch should start out with
	 */
	void createClock(int hours, int minutes, int seconds);

	// Other methods ---------------------------------------------------------------------------------------------------

	/**
	 * Adds a damage skin to the {@link Char} linked to the {@link ScriptManager}.
	 * Example: "sm.addDamageSkin(100)"
	 *
	 * @param skinID
	 * 		The ID of the damage skin.
	 *
	 * @return True if the damage skin was successfully applied.
	 */
	boolean addDamageSkin(int skinID);


	/**
	 * Opens a ui with a given ID.
	 * Look at enums/UIType for all ID's.
	 * Add "from net.swordie.ms.enums import UIType" at the top of the npc script to use this.
	 * Example: "sm.openUI(UIType.EQUIP)"
	 *
	 * @param uiID
	 * 		The ui id to show.
	**/
	void openUI(UIType uiID);

	/**
	* Opens the Dimensional mirror.
	* Example: "sm.openDimensionalMirror"
	*/
	void openDimensionalMirror();
	/**
	 * Closes a ui with a given UIType.
	 * @param uiID
	 */
	void closeUI(UIType uiID);

	/**
	 * Shows the {@link Char} the "Clear Stage with Exp" window, as well as giving the specified Exp.
	 *
	 * @param expGiven
	 * 		The amount of Exp given to the {@link Char}
	 */
	void showClearStageExpWindow(long expGiven);

	/**
	 * Returns a random integer below the number specified.
	 *
	 * @param upBound
	 * 		The number above the maximum random number
	 * @return
	 * 		Returns a random integer below the number specified.
	 */
	int getRandomIntBelow(int upBound);

	/**
	 * Grabs an effect from the WzFiles and displays it.
	 *
	 * @param dir
	 * 		The Directory to the Effect.
	 *
	 * @param placement
	 * 		The place where the effect is, goes in types (0 is on character, 3 is on character's feet, 4 is middle of screen)
	 *
	 * @param delay
	 * 		The delay in ms before the effect shows
	 */
	void showEffect(String dir, int placement, int delay);

	/**
	 * Gives the user a weather notice with specified variables
	 *
	 *
	 * @param text
	 * 		Text in the weather notice
	 * @param type
	 * 		Type of weather notice
	 * @param duration
	 * 		Duration the weather notice stays on the screen
	 */
	void showWeatherNotice(String text, WeatherEffNoticeType type, int duration);

	/**
	 * Calls a given method for the entire party. If a party member is offline, gets it from the database, and applies
	 * the method on it, and saves it back to the database.
	 * Note: the arguments of the
	 *
	 * @param methodName
	 * 		The name of the method that should be invoked
	 * @param args
	 * 		The arguments of the method
	 */
	void invokeForParty(String methodName, Object... args);

	/**
	 * Invokes a method after a given delay.
	 *
	 * @param delay
	 * 		The delay (in ms) after which the method should be invoked.
	 * @param methodName
	 * 		The name of the method that should be invoked
	 * @param args
	 * 		The arguments that the method should have
	 * @return the resulting ScheduledFuture
	 */
	ScheduledFuture invokeAfterDelay(long delay, String methodName, Object...args);

	/**
	 * Invokes the method after a given delay, after which it will keep invoking the method each
	 * <code>delayBetweenExecutions</code> milliseconds, until it has been invoked <code>executes</code> times.
	 * If <code>executes</code> is 0, will continue until the end of the main process.
	 *
	 * @param initialDelay
	 * 		The initial delay (in ms) after which the method should be invoked.
	 * @param delayBetweenExecutions
	 * 		The amount of time (in ms) between two invokes
	 * @param executes
	 * 		The amount of times that the method should be invoked (0 if no limit)
	 * @param methodName
	 * 		The name of the method that should be invoked*
	 * @param args
	 * 		The arguments that the method should have
	 * @return
	 * 		the resulting ScheduledFuture
	 */
	ScheduledFuture invokeAtFixedRate(long initialDelay, long delayBetweenExecutions,
									  int executes, String methodName, Object...args);

	int playVideoByScript(String videoPath);

	void addStorageSlots(byte amount);

	void addInventorySlotsByInvType(byte amount, byte type);

	int getSlotsLeftToAddByInvType(byte type);


	/**
	 * Opens a nodestone from a given nodestone id.
	 * @param id the item id
	 */
	void openNodestone(int id);

	/**
	 * Returns the server's name.
	 *
	 * @return
	 * 		The server's name defined in GameConstants.
	 */
	String getServerName();

	/**
	 * Warps the character to the hub.
	 */
	void warpToHub();

	/**
	 * Begins separate job handling for starting characters.
	 */
	void doJobStart();

	/**
	 * Ends separate job handling for starting characters.
	 */
	void doJobEnd();
}
