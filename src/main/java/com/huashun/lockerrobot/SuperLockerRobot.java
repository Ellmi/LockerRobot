package com.huashun.lockerrobot;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;

public class SuperLockerRobot {
    private List<Locker> managedLockers;

    public SuperLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket store(Bag bag) {
        return new Ticket(L);
    }
}
