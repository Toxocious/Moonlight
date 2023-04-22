package net.swordie.ms.client.character;

import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Sjonnie
 * Created on 9/30/2018.
 */
@Entity
@Table(name = "monster_collection_explorations")
public class MonsterCollectionExploration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int collectionKey;
    private String monsterKey;
    private int position;

    @Convert(converter = FileTimeConverter.class)
    private FileTime endDate;

    public MonsterCollectionExploration() {
    }

    public MonsterCollectionExploration(int sessionID, FileTime endDate) {
        this.collectionKey = sessionID;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCollectionKey() {
        return collectionKey;
    }

    public void setCollectionKey(int collectionKey) {
        this.collectionKey = collectionKey;
    }

    public FileTime getEndDate() {
        return endDate;
    }

    public void setEndDate(FileTime endDate) {
        this.endDate = endDate;
    }

    public String getMonsterKey() {
        return monsterKey;
    }

    public void setMonsterKey(String monsterKey) {
        this.monsterKey = monsterKey;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonsterCollectionExploration that = (MonsterCollectionExploration) o;
        return collectionKey == that.collectionKey;
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionKey);
    }

    public String getValue(boolean active) {
        LocalDateTime ldt = getEndDate().toLocalDateTime().minusHours(2); // so it's the same for the client
        return String.format("mobKey=%s;end=%s%s%s%s%s;state=%d", getMonsterKey(),
                Util.leftPaddedString(4, '0', String.valueOf(ldt.getYear())),
                Util.leftPaddedString(2, '0', String.valueOf(ldt.getMonthValue())),
                Util.leftPaddedString(2, '0', String.valueOf(ldt.getDayOfMonth())),
                Util.leftPaddedString(2, '0', String.valueOf(ldt.getHour())),
                Util.leftPaddedString(2, '0', String.valueOf(ldt.getMinute())),
                active ? 1 : 0);
    }
}
