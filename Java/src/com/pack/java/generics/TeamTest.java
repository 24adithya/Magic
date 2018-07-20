package com.pack.java.generics;

public class TeamTest {

    public static void main(String[] args) {
        
        FootballPlayer ronaldo = new FootballPlayer("Cristiano Ronaldo", Player.TYPE.FOOTBALL);
        FootballPlayer messi = new FootballPlayer("Lionel Messi", Player.TYPE.FOOTBALL);
        
        CricketPlayer dhoni = new CricketPlayer("MS Dhoni", Player.TYPE.CRICKET);
        
        Team<FootballPlayer> footballTeam = new Team<>("Dream Team", 1);
        footballTeam.addPlayer(ronaldo);
        footballTeam.addPlayer(messi);
    }
}
