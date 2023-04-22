package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.UserLocal;

/**
 * Created on 2/10/2018.
 */
public class LarknessManager {
	private Char chr;
	private int gauge;
	private LarknessType larknessType;
	public static final int maxLarkness = 10000;
	public static final int minLarkness = 0;

	public LarknessManager(Char chr) {
		this.chr = chr;
		this.gauge = maxLarkness / 2; // Half way
		this.larknessType = LarknessType.Dark;
	}

	public int getGauge() {
		return gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = (gauge > maxLarkness ? maxLarkness : (gauge < minLarkness ? minLarkness : gauge)); // Set boundaries  0 ~ 10,000
	}

	public void addGauge(int addedValue) {
		setGauge(getGauge() + addedValue);
	}

	public LarknessType getLarknessType() {
		return larknessType;
	}

	public void setLarknessType(LarknessType larknessType) {
		this.larknessType = larknessType;
	}

	public boolean isDark() {
		return getLarknessType() == LarknessType.Dark;
	}

	public boolean isEquilibrium() {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		return tsm.hasStat(CharacterTemporaryStat.Larkness) && tsm.getOption(CharacterTemporaryStat.Larkness).rOption == Luminous.EQUILIBRIUM2;
	}

	public enum LarknessType {
		None(0),
		Dark(1),
		Light(2),
		;
		private byte val;

		LarknessType(int val) {
			this.val = (byte) val;
		}

		public byte getVal() {
			return val;
		}
	}

	public void encode(OutPacket outPacket) {
		outPacket.encodeInt(Luminous.ECLIPSE); 	// rLarkness Dark
		outPacket.encodeInt(0);				// tLarkness Dark
		outPacket.encodeInt(Luminous.SUNFIRE); 	// rLarkness Light
		outPacket.encodeInt(0); 				// tLarkness Light

		outPacket.encodeInt(getLarknessType() == LarknessType.Dark ? getGauge() : -1);
		outPacket.encodeInt(getLarknessType() == LarknessType.Light ? getGauge() : -1);
		outPacket.encodeInt(isEquilibrium() ? 1 : 0);
	}

	/**
	 * If Dark,  Gauge goes up 		(up to 10,000)
	 * If Light, Gauge goes down	(down to 0)
	 * @param gaugeChange
	 */
	public void changeGauge(int gaugeChange) {
		addGauge(isDark() ? gaugeChange : -gaugeChange);
		if (getGauge() <= minLarkness || getGauge() >= maxLarkness) {
			if (chr.hasSkill(Luminous.EQUILIBRIUM)) {
				goEquilibriumMode();
				return;
			} else {
				changeMode();
			}
		}
		updateInfo();
	}

	public void goEquilibriumMode() {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		Option o = new Option();
		o.nOption = 2;
		o.rOption = Luminous.EQUILIBRIUM2;
		o.tOption = 10 + ((Luminous) chr.getJobHandler()).getMoreEquilibriumTime();
		tsm.putCharacterStatValue(CharacterTemporaryStat.Larkness, o);
		tsm.sendSetStatPacket();
		updateInfo();
		chr.resetSkillCoolTime(Luminous.DEATH_SCYTHE);
		chr.resetSkillCoolTime(Luminous.ENDER);
	}

	public void changeMode() {
		setLarknessType(isDark() ? LarknessType.Light : LarknessType.Dark);
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		Option o = new Option();
		o.nOption = 1;
		o.rOption = isDark() ? Luminous.ECLIPSE : Luminous.SUNFIRE;
		tsm.putCharacterStatValue(CharacterTemporaryStat.Larkness, o);
		tsm.sendSetStatPacket();
		updateInfo();
	}

	/**
	 * Sends a packet to update the client's larkness state
	 */
	public void updateInfo() {
		chr.write(UserLocal.incLarknessResponse(this));
	}
}