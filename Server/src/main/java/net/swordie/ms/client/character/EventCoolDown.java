package net.swordie.ms.client.character;

import javax.persistence.*;


@Entity
@Table(name = "eventscooltimes")
public class EventCoolDown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "eventType")
    private int eventType;
    @Column(name = "amountDone")
    private int amountDone;
    @Column(name = "nextresettime")
    private long nextResetTime;

    public int getId() {
        return id;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getAmountDone() {
        return amountDone;
    }

    public void setAmountDone(int amountDone) {
        this.amountDone = amountDone;
    }

    public long getNextResetTime() {
        return nextResetTime;
    }

    public void setNextResetTime(long nextResetTime) {
        this.nextResetTime = nextResetTime;
    }

    public EventCoolDown(int eventType, int amountDone, long nextResetTime) {
        this.eventType = eventType;
        this.amountDone = amountDone;
        this.nextResetTime = nextResetTime;
    }

    public EventCoolDown(){ }
}
