package com.example.simonsays;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Player
{
    @Element
    private String name,score;

    public Player() {
    }

    public Player(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
