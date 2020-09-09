package io.github.q843705423.entity.piece.common;

import io.github.q843705423.entity.piece.*;
import io.github.q843705423.entity.piece.house.BlackHouse;
import io.github.q843705423.entity.piece.house.RedHouse;
import io.github.q843705423.entity.piece.kind.BlackKind;
import io.github.q843705423.entity.piece.kind.RedKind;
import io.github.q843705423.entity.piece.soldier.BlackSoldier;
import io.github.q843705423.entity.piece.soldier.RedSoldier;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    public static Map<Integer, Piece> index2Bing = new HashMap<>(41);

    static {
        //1->卒
        BlackSoldier blackSoldier = new BlackSoldier();
        index2Bing.put(0, blackSoldier);
        index2Bing.put(1, blackSoldier);
        index2Bing.put(2, blackSoldier);
        index2Bing.put(3, blackSoldier);
        index2Bing.put(4, blackSoldier);

        RedSoldier redSoldier = new RedSoldier();
        index2Bing.put(16, redSoldier);
        index2Bing.put(17, redSoldier);
        index2Bing.put(18, redSoldier);
        index2Bing.put(19, redSoldier);
        index2Bing.put(20, redSoldier);
        //2->将
        index2Bing.put(5, new BlackKind());
        index2Bing.put(21, new RedKind());

        //3->马
        index2Bing.put(6, new BlackHouse());
        index2Bing.put(7, new BlackHouse());

        index2Bing.put(22, new RedHouse());
        index2Bing.put(23, new RedHouse());

        //4->炮
        index2Bing.put(8, new BlackGun());
        index2Bing.put(9, new BlackGun());

        index2Bing.put(24, new RedGun());
        index2Bing.put(25, new RedGun());

        //5->车
        index2Bing.put(10, new BlackCar());
        index2Bing.put(11, new BlackCar());

        index2Bing.put(26, new RedCar());
        index2Bing.put(27, new RedCar());

        //6 相
        index2Bing.put(12, new BlackMinister());
        index2Bing.put(13, new BlackMinister());

        index2Bing.put(28, new RedMinister());
        index2Bing.put(29, new RedMinister());
        //7 士
        BlackAdviser blackAdviser = new BlackAdviser();
        index2Bing.put(14, blackAdviser);
        index2Bing.put(15, blackAdviser);

        RedAdviser redAdviser = new RedAdviser();
        index2Bing.put(30, redAdviser);
        index2Bing.put(31, redAdviser);
    }

}
