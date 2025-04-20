package net.swordie.ms.client.character.union;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "unions")
public class Union {
    public static final int MAX_PRESETS = 5;
    public static final int MAX_STATS = 8;
    private static final int DEFAULT_PRESETS = 2;
    @Transient
    private Account account;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "unionid")
    private List<UnionBoard> unionBoards = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int unionCoin;
    private int unionRank;
    private int presets;

    public Union() {
    }

    public Union(int presets, int unionRank) {
        this.presets = presets;
        this.unionRank = unionRank;
        for (int i = 0; i < DEFAULT_PRESETS; i++) {
            addPreset();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void addPreset() {
        if (getPresets() < MAX_PRESETS) {
            setPresets(getPresets() + 1);
            unionBoards.add(new UnionBoard());
        }
    }

    public UnionBoard getBoardByPreset(int preset) {
        if (hasPresetUnlocked(preset)) {
            return getUnionBoards().get(preset);
        }
        return null;
    }

    public List<UnionBoard> getUnionBoards() {
        return unionBoards;
    }

    public void setUnionBoards(List<UnionBoard> unionBoards) {
        this.unionBoards = unionBoards;
    }

    public int getUnionCoin() {
        return unionCoin;
    }

    public void setUnionCoin(int unionCoin) {
        this.unionCoin = unionCoin;
    }

    public void addUnionCoin(int amount) {
        setUnionCoin(getUnionCoin() + amount);
    }

    public int getUnionRank() {
        return unionRank;
    }

    public void setUnionRank(int unionRank) {
        this.unionRank = unionRank;
    }

    public int getPresets() {
        return presets;
    }

    public void setPresets(int presets) {
        this.presets = presets;
    }

    public boolean hasPresetUnlocked(int preset) {
        return preset >= 0 && preset < getPresets();
    }

    public Set<Char> getEligibleUnionChars() {
        // Only take 3rd job+ characters that are at least level 60.
        return account.getCharacters().stream()
                .filter(chr -> chr.getLevel() >= 60 && chr.getJob() / 10 >= 1)
                .collect(Collectors.toSet());
    }

    public Set<UnionMember> getActiveUnionChars(int preset) {
        // Get eligible characters that have a grid position of >= 0.
        if (hasPresetUnlocked(preset)) {
            return getBoardByPreset(preset).getActiveMembers();
        }
        return null;
    }

    public void setCharPosForPreset(int preset, Char chr, int rotation, int grid) {
        UnionBoard ub = getBoardByPreset(preset);
        if (ub != null) {
            ub.setCharGridPos(chr, rotation, grid);
        }
    }

}
