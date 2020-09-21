package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.M;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimaryLockerRobotTest {

    @Test
    public void should_return_m_locker_type_ticket_when_store_bag_given_robot_manage_1_m_locker_with_free_capacity() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(new Locker(M, 1)));

        Ticket ticket = primaryLockerRobot.store(new Bag(M));

        assertEquals(M, ticket.getLockerSizeType());
    }

    @Test
    public void should_return_m_locker_type_ticket_and_store_into_first_locker_when_store_bag_given_robot_manage_2_m_lockers_which_all_have_free_capacity() {
        Locker firstLocker = new Locker(M, 1);
        Locker secondLocker = new Locker(M, 1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(firstLocker, secondLocker));

        Bag storedBag = new Bag(M);
        Ticket ticket = primaryLockerRobot.store(storedBag);

        assertEquals(M, ticket.getLockerSizeType());
        assertSame(storedBag, firstLocker.fetchBagBy(ticket));
    }

    @Test
    public void should_return_m_locker_type_ticket_and_store_into_second_locker_when_store_bag_given_robot_manage_2_m_lockers_which_first_is_full_but_second_have_free_capacity() {
        Locker firstLocker = new Locker(M, 1);
        Locker secondLocker = new Locker(M, 1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(firstLocker, secondLocker));
        primaryLockerRobot.store(new Bag(M));

        Bag storedBag = new Bag(M);
        Ticket ticket = primaryLockerRobot.store(storedBag);

        assertEquals(M, ticket.getLockerSizeType());
        assertSame(storedBag, secondLocker.fetchBagBy(ticket));
    }

    @Test
    public void should_throw_LockerIsFullException_when_store_bag_given_robot_manage_2_m_lockers_which_all_full() {
        Locker firstLocker = new Locker(M, 1);
        Locker secondLocker = new Locker(M, 1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(firstLocker, secondLocker));
        primaryLockerRobot.store(new Bag(M));
        primaryLockerRobot.store(new Bag(M));

        assertThrows(LockerIsFullException.class, () -> primaryLockerRobot.store(new Bag(M)));

    }


    @Test
    public void should_return_correct_bag_when_fetch_bag_given_valid_m_ticket() {
        Locker locker = new Locker(M, 1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(List.of(locker));
        Bag storedBag = new Bag(M);
        Ticket ticket = primaryLockerRobot.store(storedBag);

        Bag fetchedBag = primaryLockerRobot.fetchBagBy(ticket);

        assertEquals(M, ticket.getLockerSizeType());
        assertSame(storedBag, fetchedBag);

    }
}
