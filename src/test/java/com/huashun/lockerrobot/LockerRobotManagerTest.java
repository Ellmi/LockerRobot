package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;
import static com.huashun.lockerrobot.SizeType.S;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerRobotManagerTest {

    @Test
    public void should_return_s_locker_type_ticket_when_store_small_bag_given_robot_manage_1_free_s_locker_and_1_primaryLockerRobot_and_1_superLockerRobot() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(new Locker(S, 1)));

        Ticket ticket = lockerRobotManager.store(new Bag(S));

        assertEquals(S, ticket.getLockerSizeType());
    }

    @Test
    public void should_throw_LockerIsFullException_when_store_small_bag_given_robot_manage_1_full_s_locker_and_1_primaryLockerRobot_and_1_superLockerRobot() {
        Locker locker = new Locker(S, 1);
        locker.store(new Bag(S));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(locker));


        assertThrows(LockerIsFullException.class, () -> lockerRobotManager.store(new Bag(S)));
    }

}
