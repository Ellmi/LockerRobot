package com.huashun.lockerrobot;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.M;

public class PrimaryLockerRobot {
    private List<Locker> managedLockers;

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket store(Bag bag) {
        return new Ticket(M);
    }
}
