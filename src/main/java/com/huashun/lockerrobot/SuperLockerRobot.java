package com.huashun.lockerrobot;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot {
    private List<Locker> managedLockers;

    public SuperLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket store(Bag bag) {
        return managedLockers.stream().max(Comparator.comparing(locker -> locker.getCapacityRate())).get().store(bag);
    }
}
