package com.pack.java.generics;

public abstract class Player {

    private final String name;
    private final TYPE type;
    
    public Player(String name,
                  TYPE type) {
        super();
        this.name = name;
        this.type = type;
    }

    enum TYPE {
        FOOTBALL, CRICKET;
    }
    
    public String getName() {
        return name;
    }

    public TYPE getType() {
        return type;
    }
}
