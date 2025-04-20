
package net.swordie.ms.client.character.commands;

public abstract class PlayerCommand implements ICommand {


    public PlayerCommand() {
    }

    private static char prefix = '@';

    public static char getPrefix() {
        return prefix;
    }
}