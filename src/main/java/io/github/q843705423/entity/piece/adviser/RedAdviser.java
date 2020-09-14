package io.github.q843705423.entity.piece.adviser;

public class RedAdviser extends Adviser {
    @Override
    public String englishName() {
        return "A";
    }

    @Override
    public String chinaName() {
        return "仕";
    }


    @Override
    public boolean isRed() {
        return true;
    }
}
