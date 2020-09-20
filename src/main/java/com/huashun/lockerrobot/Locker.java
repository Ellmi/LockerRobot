package com.huashun.lockerrobot;

import static com.huashun.lockerrobot.SizeType.S;

public class Locker {
    private SizeType sizeType;
    private int capacity;

    public Locker(SizeType sizeType, int capacity) {
        this.sizeType = sizeType;
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        return new Ticket(S);
    }
}
