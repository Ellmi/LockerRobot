package com.huashun.lockerrobot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;
import static com.huashun.lockerrobot.SizeType.S;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockerRobotManagerTest {

    @Test
    public void should_return_s_locker_type_ticket_when_store_small_bag_given_robot_manage_1_free_s_locker_and_1_primaryLockerRobot_and_1_superLockerRobot() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager(List.of(new Locker(S, 1)));

        Ticket ticket = lockerRobotManager.store(new Bag(S));

        assertEquals(S, ticket.getLockerSizeType());
    }

}
