package io.github.q843705423;

import io.github.q843705423.entity.BaseInfo;

public interface Eval {

    /**
     * 基础价值
     *
     * @return s
     */
    int basePrice(BaseInfo baseInfo);

    /**
     * 移动价值
     *
     * @return int
     */
    int movePrice(BaseInfo baseInfo);


    /**
     * 攻击价值
     *
     * @return int
     */
    int attackPrice(BaseInfo baseInfo);

    /**
     * 保护价值
     */
    int defendPrice(BaseInfo baseInfo);

    int[] moveList(BaseInfo baseInfo);

    int[] eatList(BaseInfo baseInfo);
}
