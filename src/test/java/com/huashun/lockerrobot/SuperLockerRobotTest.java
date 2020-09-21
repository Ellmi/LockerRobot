package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void should_throw_LockerIsFullException_when_store_bag_given_robot_manage_2_l_lockers_which_all_full() {
        Locker firstLocker = new Locker(L, 1);
        firstLocker.store(new Bag(L));
        Locker secondLocker = new Locker(L, 1);
        secondLocker.store(new Bag(L));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(List.of(firstLocker, secondLocker));

        assertThrows(LockerIsFullException.class, () -> superLockerRobot.store(new Bag(L)));

    }

    @Test
    public void should_return_correct_bag_when_fetch_bag_given_valid_l_ticket() {
        Locker locker = new Locker(L, 1);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(List.of(locker));
        Bag storedBag = new Bag(L);
        Ticket ticket = superLockerRobot.store(storedBag);

        Bag fetchedBag = superLockerRobot.fetchBagBy(ticket);

        assertEquals(L, ticket.getLockerSizeType());
        assertSame(storedBag, fetchedBag);

    }
}
