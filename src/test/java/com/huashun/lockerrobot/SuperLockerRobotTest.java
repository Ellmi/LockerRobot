package com.huashun.lockerrobot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;
import static com.huashun.lockerrobot.SizeType.M;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperLockerRobotTest {
    @Test
    public void should_return_l_locker_type_ticket_when_store_bag_given_robot_manage_1_l_locker_with_free_capacity() {
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(List.of(new Locker(M, 1)));

        Ticket ticket = superLockerRobot.store(new Bag(L));

        assertEquals(L, ticket.getLockerSizeType());
    }
}
