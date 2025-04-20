package net.swordie.ms.client.character.cards;

import net.swordie.ms.constants.ItemConstants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 5/3/2018.
 */
@Entity
@Table(name = "monsterbookinfos")
public class MonsterBookInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ElementCollection
    @CollectionTable(name = "monsterbookcards", joinColumns = @JoinColumn(name = "bookID"))
    @Column(name = "cardID")
    private Set<Integer> cards = new HashSet<>();
    private int setID;
    private int coverID;

    public MonsterBookInfo() {
        setID = -1;
        coverID = -1;
    }

    public Set<Integer> getCards() {
        return cards;
    }

    public void setCards(Set<Integer> cards) {
        this.cards = cards;
    }

    public int getSetID() {
        return setID;
    }

    public void setSetID(int setID) {
        this.setID = setID;
    }

    public int getCoverID() {
        return coverID;
    }

    public void setCoverID(int coverID) {
        this.coverID = coverID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasCard(int cardID) {
        return getCards().contains(cardID % ItemConstants.MOB_CARD_BASE_ID);
    }

    public void addCard(int itemID) {
        getCards().add(itemID % ItemConstants.MOB_CARD_BASE_ID);
    }
}
