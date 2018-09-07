package com.pack.java.generics;

import java.util.List;

public class Team<T extends Player> implements Comparable<Team<T>> {

    private String name;
    private Integer rank;
    private List<T> players;
    
    public Team(String name,
                Integer rank) {
        super();
        this.name = name;
        this.rank = rank;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public List<T> getPlayers() {
        return players;
    }
    public void setPlayers(List<T> players) {
        this.players = players;
    }
    public void addPlayer(T player) {
        this.players.add(player);
    }

    @Override
    public int compareTo(Team<T> team) {
        int value = 0;
        if(this.getRank().intValue() > team.getRank().intValue()) {
            value = 1;
        } else if(this.getRank().intValue() < team.getRank().intValue()) {
            value = -1;
        } 
        return value;
    }
}
