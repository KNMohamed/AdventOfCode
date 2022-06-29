package com.adventofcode.day4;

import java.util.List;

public class BingoCard {
    public List<List<Integer>> board;
    public State state;

    public BingoCard(List<List<Integer>> board){
        this.board = board;
        this.state = State.UNSOLVED;
    }
}
