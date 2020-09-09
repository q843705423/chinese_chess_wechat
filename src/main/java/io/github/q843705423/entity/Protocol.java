package io.github.q843705423.entity;

import lombok.Data;

@Data
public class Protocol {

    private String piecePlacementData;
    private String activeColor;
    //换位可行性
    private String castlingAvailability;

    private String enPassantTargetSquare;

    private int eatTurn;
    private int allTurn;

    public Protocol(String fen) {
        String[] split = fen.split(" ");
        piecePlacementData = split[0];
        activeColor = split[1];
        castlingAvailability = "-";
        enPassantTargetSquare = "-";
        try {
            eatTurn = Integer.parseInt(split[4]);
        } catch (NumberFormatException e) {
            eatTurn = 0;
        }
        try {
            int allTurn = Integer.parseInt(split[5]);
        } catch (NumberFormatException e) {
            allTurn = 0;
        }
    }


    public boolean isRed() {
        return !"b".equals(activeColor);

    }
}
