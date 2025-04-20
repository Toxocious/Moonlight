package net.swordie.ms.loaders.containerclasses;

public class Cosmetic {
    public int Id;
    public String Name;

    public Cosmetic(int id, String name) {
        Id = id;
        Name = name;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
