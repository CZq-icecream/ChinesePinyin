package com.czq.chinesepinyin.entity;

/**
 * @date 2020.4.10
 */
public enum DailyGoal {
    Casual(10),
    Regular(20),
    Serious(30),
    Insane(50);

    private int xp;
    DailyGoal(int xp) {
        this.xp = xp;
    }

    @Override
    public String toString() {
        return "DailyGoal{" +
                "xp=" + xp +
                '}';
    }

    public int getXP(){
        return this.xp;
    }

    public static DailyGoal getDailyGoal(final Integer integer){
        DailyGoal[] values = DailyGoal.values();
        for (int i = 0; i < values.length; i++) {
            if (integer == values[i].xp) {
                return values[i];
            }
        }
        return null;
    }
}
