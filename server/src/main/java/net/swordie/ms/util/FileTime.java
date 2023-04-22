package net.swordie.ms.util;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "filetimes")
public class FileTime implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int lowDateTime;
	private int highDateTime;
	@Transient
	private boolean isConvertedForClient;

	public long getLongValue() {
		return getLowDateTime() + (long) getHighDateTime() << 32;
	}

	public boolean isConvertedForClient() {
		return isConvertedForClient;
	}

	public void setConvertedForClient(boolean convertedForClient) {
		isConvertedForClient = convertedForClient;
	}

    public enum Type {
		// Mushy
		MAX_TIME(35120710, -1157267456),
		ZERO_TIME(21968699, -35635200),
		FT_UT_OFFSET(116444592000000000L),
		QUEST_TIME(27111903),
		PLAIN_ZERO(0);

		private long val;

		Type(long val) {
			this.val = val;
		}

		Type(int lowPart, int highPart) {
			val = lowPart + ((long) highPart << 32);
		}

		public long getVal() {
			return val;
		}
	}

	public FileTime(int lowDateTime, int highDateTime) {
		this.lowDateTime = lowDateTime;
		this.highDateTime = highDateTime;
		if (FileTime.fromType(Type.MAX_TIME).equals(this) || FileTime.fromType(Type.ZERO_TIME).equals(this)) {
			isConvertedForClient = true;
		}
	}

	public FileTime() {
	}

	public FileTime(long time, boolean isConvertedForClient) {
		this(time);
		this.isConvertedForClient = isConvertedForClient;
	}

	/**
	 * Creates a new copy of this FileTime
	 * @return the new copy
	 */
	public FileTime deepCopy() {
		return new FileTime(getLowDateTime(), getHighDateTime());
	}

	public static FileTime fromType(Type type) {
		return new FileTime(type.getVal(), true);
	}

	/**
	 * Creates a new FileTime from a given long, by splitting up the long into a low and high part
	 * @param time the long the FileTime should be created from
	 */
	public FileTime(long time) {
		lowDateTime = (int) time;
		highDateTime = (int) (time >>> 32);
	}

	public int getLowDateTime() {
		return lowDateTime;
	}

	public int getHighDateTime() {
		return highDateTime;
	}

	/**
	 * Creates a new FileTime from the current time (System.currentTimeMillis()). Ensures the date is correctly c
	 * alculated for the client.
	 * @return FileTime corresponding to the current time
	 */
	public static FileTime currentTime() {
		return fromEpochMillis(System.currentTimeMillis());
	}

	/**
	 * Creates a new FileTime from a given time (millis since epoch).
	 * @param time millis since epoch that this FileTime should correspond to
	 * @return FileTime corresponding to the given time
	 */
	public static FileTime fromEpochMillis(long time) {
		return fromLong(time);
	}

	/**
	 * Converts this FileTime (storing epoch millis) to a format that the client expects.
	 * @return formatted FileTime
	 */
	public FileTime toClientFormat() {
		FileTime ft = fromLong((long) (toLong() - 116444736000000000L) * 100000L);
		ft.setConvertedForClient(true);
		return ft;
	}

	/**
	 * Creates a new FileTime from a given date. Ensures the date is correctly calculated for the client.
	 * @param localDateTime date that this FileTime should correspond to
	 * @return FileTime corresponding to the given date
	 */
	public static FileTime fromDate(LocalDateTime localDateTime) {
		return fromEpochMillis(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
	}

	/**
	 * Returns this FileTime as an Instant.
	 * @return the Instant corresponding to this FileTime
	 */
	public Instant toInstant() {
		return toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant();
	}

	/**
	 * Returns the millis since epoch that this FileTime corresponds to.
	 * @return millis since epoch
	 */
	public long toMillis() {
		if (isConvertedForClient()) {
			return (toLong() - 116444736000000000L) / 10000L;
		}
		return toLong();
	}

	/**
	 * Returns the LocalDateTime that this FileTime corresponds to.
	 * @return corresponding date and time
	 */
	public LocalDateTime toLocalDateTime() {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(toMillis()), ZoneId.systemDefault());
	}

	/**
	 * Creates a new FileTime from a given long, by splitting up the long into a low and high part
	 * @param value the long the FileTime should be created from
	 * @return FileTime from the given log
	 */
	public static FileTime fromLong(long value) {
		return new FileTime((int) value, (int) (value >>> 32));
	}

	/**
	 * Encodes this FileTime to a packet
	 * @param outPacket the packet to encode to
	 */
	public void encode(OutPacket outPacket) {
		FileTime adjustedFt;
		if (isMinTime() || isMaxTime()) {
			adjustedFt = this;
		} else {
			adjustedFt = fromDate(toLocalDateTime().plusHours(2));
		}
		if (!isConvertedForClient()) {
			// https://stackoverflow.com/questions/46201834/how-to-convert-milli-seconds-time-to-filetime-in-c
			outPacket.encodeLong(adjustedFt.toLong() * 10000L + 116444736000000000L);
		} else {
			outPacket.encodeInt(adjustedFt.getHighDateTime());
			outPacket.encodeInt(adjustedFt.getLowDateTime());
		}
	}

	/**
	 * Returns this FileTime as a long, by adding up the high and low part
	 * @return addition of the low and high part
	 */
	public long toLong() {
		return (getLowDateTime() & 0xFFFFFFFFL) | ((long) getHighDateTime() << 32);
	}

	/**
	 * Returns true if this FileTime's time is before the current time.
	 * @return expiredness
	 */
	public boolean isExpired() {
		return !isPermanent() && toMillis() < System.currentTimeMillis();
	}

	private boolean isPermanent() {
		return equals(FileTime.fromType(Type.MAX_TIME)) || equals(new FileTime(21968699, -35635200));
	}

	/**
	 * Checks if this FileTime is before a given date.
	 * @param localDateTime the given date
	 * @return if this FileTime is before the given date
	 */
	public boolean isBefore(LocalDateTime localDateTime) {
		return toLocalDateTime().isBefore(localDateTime);
	}

	/**
	 * Checks if this FileTime is before a given FileTime.
	 * @param fileTime the given date
	 * @return if this FileTime is before the given FileTime
	 */
	public boolean isBefore(FileTime fileTime) {
		return toLocalDateTime().isBefore(fileTime.toLocalDateTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileTime fileTime = (FileTime) o;
		return lowDateTime == fileTime.lowDateTime &&
				highDateTime == fileTime.highDateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lowDateTime, highDateTime);
	}

	@Override
	public String toString() {
		return "FileTime{" +
				"lowDateTime=" + lowDateTime +
				", highDateTime=" + highDateTime +
				'}';
	}

	public boolean isMaxTime() {
		return equals(FileTime.fromType(Type.MAX_TIME));
	}

	public boolean isMinTime() {
		return equals(FileTime.fromType(Type.ZERO_TIME));
	}

	public String toSqlFormat() {
		if (isMaxTime()) {
			return "9999-01-01 00:00:01.000";
		} else if (isMinTime()) {
			return "1970-01-01 00:00:01.000";
		}
		LocalDateTime ldt = toLocalDateTime();
		return String.format("%04d-%02d-%02d %02d:%02d:%02d.%03d", ldt.getYear(), ldt.getMonthValue(),
				ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond(), 0);
	}

	/**
	 * Returns the current date as YY/MM/DD format.
	 * @return created string
	 */
	public String toYYMMDD() {
		if (isMaxTime()) {
			return "99/01/01";
		} else if (isMinTime()) {
			return "70/01/01";
		}
		LocalDateTime ldt = toLocalDateTime();
		return String.format("%02d/%02d/%02d", ldt.getYear() % 100, ldt.getMonthValue(), ldt.getDayOfMonth());
	}

	/**
	 * Returns the current date+time as YYMMDDHHMMSS format.
	 * @return created string
	 */
	public String toYYMMDDHHMMSS() {
		if (isMaxTime()) {
			return "990101010101";
		} else if (isMinTime()) {
			return "700101010101";
		}
		LocalDateTime ldt = toLocalDateTime();
		return String.format("%02d%02d%02d%02d%02d%02d", ldt.getYear() % 100, ldt.getMonthValue(), ldt.getDayOfMonth(),
				ldt.getHour(), ldt.getMinute(), ldt.getSecond());
	}
}
