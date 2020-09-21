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
        }
        return null;
    }
}
