package io.github.q843705423.entity.piece.adviser;

public class BlackAdviser extends Adviser {
    @Override
    public String englishName() {
        return "a";
    }

    @Override
    public String chinaName() {
        return "士";
    }


    @Override
    public boolean isRed() {
        return false;
    }

}
