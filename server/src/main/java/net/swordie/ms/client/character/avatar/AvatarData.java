package net.swordie.ms.client.character.avatar;

import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.JobConstants;

import javax.persistence.*;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "avatardata")
public class AvatarData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "characterStat")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CharacterStat characterStat;
    @JoinColumn(name = "avatarLook")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AvatarLook avatarLook;
    @JoinColumn(name = "zeroAvatarLook")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AvatarLook zeroAvatarLook;

    public AvatarLook getAvatarLook() {
        return avatarLook;
    }

    public CharacterStat getCharacterStat() {
        return characterStat;
    }

    public AvatarLook getZeroAvatarLook() {
        return zeroAvatarLook;
    }

    public void setZeroAvatarLook(AvatarLook zeroAvatarLook) {
        this.zeroAvatarLook = zeroAvatarLook;
    }

    public void encode(OutPacket outPacket) {
        characterStat.encode(outPacket);
        // sub_92A050
        outPacket.encodeInt(0); // new 196
        outPacket.encodeLong(0); // new 199
        // ~sub_92A050
        avatarLook.encode(outPacket);
        if (JobConstants.isZero(getCharacterStat().getJob())) {
            zeroAvatarLook.encode(outPacket);
        }
    }

    public void setCharacterStat(CharacterStat characterStat) {
        this.characterStat = characterStat;
    }

    public void setAvatarLook(AvatarLook avatarLook) {
        this.avatarLook = avatarLook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AvatarLook getAvatarLook(boolean zeroBetaState) {
        return zeroBetaState ? getZeroAvatarLook() : getAvatarLook();
    }
}
