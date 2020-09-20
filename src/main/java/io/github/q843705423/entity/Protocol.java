package io.github.q843705423.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Protocol {

    private String piecePlacementData;
    private String activeColor;
    //换位可行性
    private String castlingAvailability;

    private String enPassantTargetSquare;
    private List<String> moveList = new ArrayList<>();
    private String banEn;

    public List<String> getMoveList() {
        return moveList;
    }

    private int eatTurn;
    private int allTurn;

    public Protocol() {

        piecePlacementData = "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR";
        activeColor = "w";
    }

    /**
     * position fen 5k3/9/9/9/9/9/9/1N7/9/3K5 b - - 0 1 moves f9f8 b2d3 f8f9 d3b2 f9f8
     * position fen rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w 0 1 moves c3c4 g6g5
     *
     * @param fen fen
     */
    public Protocol(String fen) {
        if (fen.startsWith("position startpos")) {
            fen = fen.replace("position startpos", "position fen rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w 0 1 - -");
        } else if (!fen.startsWith("position fen")) {
            fen = "position fen " + fen;

        }
        String[] split = fen.split(" ");
        piecePlacementData = split[2];
        activeColor = split[3];
        castlingAvailability = "-";
        enPassantTargetSquare = "-";
        if (split.length > 8) {
            for (int i = 9; i < split.length; i++) {
                moveList.add(split[i]);
            }
        }
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
        setBan();

    }

    public void setBan() {
        List<String> moveList = this.getMoveList();
        if (moveList.size() >= 8) {
            String s = moveList.get(moveList.size() - 4);
            String s1 = moveList.get(moveList.size() - 8);
            if (s.equals(s1)) {
                this.banEn = s;
            }

        }
    }

    public void changeColor() {
        activeColor = "b".equals(activeColor) ? "w" : "b";
    }

    public boolean isRed() {
        boolean b = !"b".equals(activeColor);
        for (int i = 0; i < moveList.size(); i++) {
            b = !b;
        }
        return b;

    }
}
