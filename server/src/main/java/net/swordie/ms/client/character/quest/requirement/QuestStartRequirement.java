package net.swordie.ms.client.character.quest.requirement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.DatSerializable;

/**
 * Created on 3/2/2018.
 */
public interface QuestStartRequirement extends DatSerializable {

    /**
     * Returns whether or not a given {@link Char} has a requirement.
     * @param chr The Char that should be checked.
     * @return Whether or not the given Char has the requirements for this requirement.
     */
    boolean hasRequirements(Char chr);
}
