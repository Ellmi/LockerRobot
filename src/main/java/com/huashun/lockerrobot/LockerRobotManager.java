package com.huashun.lockerrobot;

import java.util.List;

public class LockerRobotManager {
    private List<Storable> storableList;

    public LockerRobotManager(List<Storable> storableList) {
        this.storableList = storableList;
    }

    public Ticket store(Bag bag) {
        switch (bag.getSizeType()) {
            case S:
                return storableList.stream().filter(storable -> storable instanceof Locker).findFirst().get().store(bag);
            case M:
                return storableList.stream().filter(storable -> storable instanceof PrimaryLockerRobot).findFirst().get().store(bag);
            case L:
                return storableList.stream().filter(storable -> storable instanceof SuperLockerRobot).findFirst().get().store(bag);
            default:
                return null;
        }
    }

    public Bag fetchBagBy(Ticket ticket) {
        switch (ticket.getLockerSizeType()) {
            case S:
                return storableList.stream().filter(storable -> storable instanceof Locker).findFirst().get().fetchBagBy(ticket);
            case M:
                return storableList.stream().filter(storable -> storable instanceof PrimaryLockerRobot).findFirst().get().fetchBagBy(ticket);
            default:
                return null;
        }
    }
}
