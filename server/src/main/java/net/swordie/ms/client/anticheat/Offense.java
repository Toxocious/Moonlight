package net.swordie.ms.client.anticheat;

import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

@Entity
@Table(name = "offenses")
public class Offense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int charID;
    private int accountID;
    private String msg;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Convert(converter = FileTimeConverter.class)
    private FileTime issueDate;
    @Column(name = "issuer_char_id")
    private int issuerCharID;

    public Offense() {
    }

    public Offense(int charID, int accountID, String msg, Type type, FileTime issueDate, int issuerCharID) {
        this.charID = charID;
        this.accountID = accountID;
        this.msg = msg;
        this.type = type;
        this.issueDate = issueDate;
        this.issuerCharID = issuerCharID;
    }

    public Offense withMsg(String msg) {
        setMsg(msg);
        return this;
    }

    public Offense withType(Type type) {
        setType(type);
        return this;
    }

    public Offense withIssuerID(int issuerCharID) {
        setIssuerCharID(issuerCharID);
        return this;
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FileTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(FileTime issueDate) {
        this.issueDate = issueDate;
    }

    public int getIssuerCharID() {
        return issuerCharID;
    }

    public void setIssuerCharID(int issuerCharID) {
        this.issuerCharID = issuerCharID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public enum Type {
        Warning(0), // Mostly for logging
        Editing(5), // Any sort of client editing, like wz/packet
        Gameplay(2), // Stuff like KSing
        Behaviour(1), // Being annoying
        Manual(5), // Manual offense (by doing !ban or something)
        ;

        private int points;

        Type(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    }

    @Override
    public String toString() {
        return "Offense{" +
                "charID=" + charID +
                ", accountID=" + accountID +
                ", msg='" + msg + '\'' +
                ", type=" + type +
                ", issueDate=" + issueDate +
                ", issuerCharID=" + issuerCharID +
                '}';
    }
}
