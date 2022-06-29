package com.adventofcode.day4;

import java.util.List;

public class BingoCard {
    public static final int ROWS = 5;
    public static final int COLS = 5;
    private List<List<Integer>> board;

    public BingoCard(List<List<Integer>> board){
        this.board = board;
    }

    public boolean addNumber(Integer num){
        boolean isWinner = false;
        for(int i = 0; i < ROWS; i++){
            List<Integer> bingoRow = board.get(i);
            for(int j = 0; j < COLS; j++){
                if(bingoRow.get(j) == num){
                    bingoRow.set(j,-(bingoRow.get(j)));
                    isWinner = checkWinner(i,j);
                }
            }
        }
        return isWinner;
    }

    private boolean checkWinner(int row, int column) {
        boolean columnWinner = true;
        boolean rowWinner = true;

        //check row
        for(int i = 0; i < 5; i++){
            Integer num = board.get(row).get(i);
            if(num > 0) rowWinner = false;
        }

        //check column
        for(int i = 0; i < 5; i++){
            Integer num = board.get(i).get(column);
            if(num > 0) columnWinner = false;
        }

        return columnWinner || rowWinner;
    }

    public Integer calculateWinningScore(Integer winningNumber) {
        int sum = 0;
        for(List<Integer> row: board){
            for(Integer num : row){
                if(num > 0)
                    sum +=num;
            }
        }
        return sum * winningNumber;
    }
}
