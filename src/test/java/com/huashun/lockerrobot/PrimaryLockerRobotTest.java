package com.huashun.lockerrobot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.M;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimaryLockerRobotTest {

    @Test
    public void should_return_m_locker_type_ticket_when_store_bag_given_robot_manage_1_m_locker_with_free_capacity() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(new Locker(M, 1)));

        Ticket ticket = primaryLockerRobot.store(new Bag(M));

        assertEquals(M, ticket.getLockerSizeType());
    }
}
