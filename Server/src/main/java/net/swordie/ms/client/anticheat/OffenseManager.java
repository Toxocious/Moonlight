package net.swordie.ms.client.anticheat;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.util.FileTime;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offense_managers")
public class OffenseManager {
    @Transient
    private static final Logger log = Logger.getLogger(DatabaseManager.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "manager_id")
    private Set<Offense> offenses;
    private int points;
    @Transient
    private Char chr;

    /**
     * Creates a new offense that was detected by the system (i.e., not manual)
     * @param type the type of offense
     * @param msg the description of the offense
     */
    public void addOffense(Offense.Type type, String msg) {
        addOffense(getDefaultOffsense()
                .withMsg(msg)
                .withType(type)
        );
    }

    /**
     * Creates a new offense that was detected by the system (i.e., not manual)
     * @param type the type of offense
     * @param msg the description of the offense
     * @param expected expected value
     * @param actual actual value
     */
    public void addOffense(Offense.Type type, String msg, int expected, int actual) {
        msg += String.format(" (expected %d, got %d)", expected, actual);
        addOffense(getDefaultOffsense()
                .withMsg(msg)
                .withType(type)
        );
    }

    /**
     * Adds an offense seen by a game master.
     * @param type the type of offense
     * @param msg the description of the offense
     * @param issuerCharID the game master's char id
     */
    public void addOffense(Offense.Type type, String msg, int issuerCharID) {
        addOffense(getDefaultOffsense()
                .withMsg(msg)
                .withType(type)
                .withIssuerID(issuerCharID)
        );
    }

    /**
     * Adds an offense seen by a game master, with type Manual.
     * @param msg the description of the offense
     * @param issuerCharID the game master's char id
     */
    public void addOffense(String msg, int issuerCharID) {
        addOffense(getDefaultOffsense()
                .withMsg(msg)
                .withIssuerID(issuerCharID)
        );
    }

    /**
     * Adds an offense found by the system with type Editing.
     * @param msg the description of the offense
     */
    public void addOffense(String msg) {
        addOffense(getDefaultOffsense()
                .withMsg(msg)
                .withType(Offense.Type.Editing));
    }

    /**
     * Adds an offense found by the system with type Editing.
     * @param msg the description of the offense
     */
    public void addOffense(String msg, int expected, int actual) {
        msg += String.format(" (expected %d, got %d)", expected, actual);
        addOffense(msg);
    }

    private Offense getDefaultOffsense() {
        int charId = 0;
        int accId = 0;
        if (chr != null) {
            charId = chr.getId();
            accId = chr.getUserId();
        }

        return new Offense(charId, accId, "", Offense.Type.Manual, FileTime.currentTime(), 0);
    }

    private void addOffense(Offense offense) {
        log.info("Added offense: " + offense);
        addPoints(offense.getType().getPoints());
        // TODO do something at certain point intervals (temp ban, mute, idk)
        getOffenses().add(offense);
    }

    public Set<Offense> getOffenses() {
        if (offenses == null) {
            offenses = new HashSet<>();
        }
        return offenses;
    }

    public void setOffenses(Set<Offense> offenses) {
        this.offenses = offenses;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        setPoints(getPoints() + points);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
