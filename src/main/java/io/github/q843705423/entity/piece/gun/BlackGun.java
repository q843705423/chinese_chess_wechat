package io.github.q843705423.entity.piece.gun;

public class BlackGun extends Gun {
    @Override
    public String englishName() {
        return "c";
    }

    @Override
    public String chinaName() {
        return "砲";
    }


    @Override
    public boolean isRed() {
        return false;
    }
}
