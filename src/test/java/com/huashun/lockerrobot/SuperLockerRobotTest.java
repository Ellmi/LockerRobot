package com.huashun.lockerrobot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperLockerRobotTest {
    @Test
    public void should_return_l_locker_type_ticket_when_store_bag_given_robot_manage_1_l_locker_with_free_capacity() {
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(List.of(new Locker(L, 1)));

        Ticket ticket = superLockerRobot.store(new Bag(L));

        assertEquals(L, ticket.getLockerSizeType());
    }

    @Test
    public void should_return_l_locker_type_ticket_and_store_bag_into_second_locker_when_store_bag_given_robot_manage_2_l_lockers_which_second_has_bigger_capacity_rate() {
        Locker firstLocker = new Locker(L, 6);
        firstLocker.store(new Bag(L));
        firstLocker.store(new Bag(L));
        firstLocker.store(new Bag(L));
        Locker secondLocker = new Locker(L, 2);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(List.of(firstLocker, secondLocker));

        Bag storedBag = new Bag(L);
        Ticket ticket = superLockerRobot.store(storedBag);

        assertEquals(L, ticket.getLockerSizeType());
        assertSame(storedBag, secondLocker.fetchBagBy(ticket));
    }
}
