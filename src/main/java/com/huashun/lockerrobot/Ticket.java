package com.huashun.lockerrobot;

public class Ticket {
    private SizeType lockerSizeType;

    public Ticket(SizeType lockerSizeType) {
        this.lockerSizeType = lockerSizeType;
    }

    public SizeType getLockerSizeType() {
        return lockerSizeType;
    }
}
