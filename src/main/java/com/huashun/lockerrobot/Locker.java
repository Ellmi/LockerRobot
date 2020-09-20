package com.huashun.lockerrobot;

public class Locker {
    private SizeType sizeType;
    private int capacity;

    public Locker(SizeType sizeType, int capacity) {
        this.sizeType = sizeType;
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if (capacity == 0) throw new LockerIsFullException();
        capacity--;
        return new Ticket(sizeType);
    }
}
