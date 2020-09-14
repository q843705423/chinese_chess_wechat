package io.github.q843705423.entity.piece.gun;

public class RedGun extends Gun {
    @Override
    public String englishName() {
        return "C";
    }

    @Override
    public String chinaName() {
        return "炮";
    }


    @Override
    public boolean isRed() {
        return true;
    }
}
