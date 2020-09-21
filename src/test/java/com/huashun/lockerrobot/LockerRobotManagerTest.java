package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;
import static com.huashun.lockerrobot.SizeType.M;
import static com.huashun.lockerrobot.SizeType.S;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LockerRobotManagerTest {

    @Test
    public void should_return_s_locker_type_ticket_when_store_small_bag_given_robot_manage_1_free_s_locker_and_1_primaryLockerRobot_and_1_superLockerRobot() {
        LockerRobotManager lockerRobotManager = buildLockerRobotManager();

        Ticket ticket = lockerRobotManager.store(new Bag(S));

        assertEquals(S, ticket.getLockerSizeType());
    }

    private LockerRobotManager buildLockerRobotManager() {
        return new LockerRobotManager(List.of(new Locker(S, 1), new PrimaryLockerRobot(List.of(new Locker(M, 1))), new SuperLockerRobot(List.of(new Locker(L, 1)))));
    }

    @Test
    public void should_throw_LockerIsFullException_when_store_small_bag_given_robot_manage_1_full_s_locker_and_1_primaryLockerRobot_and_1_superLockerRobot() {

        LockerRobotManager lockerRobotManager = buildLockerRobotManager();
        lockerRobotManager.store(new Bag(S));

        assertThrows(LockerIsFullException.class, () -> lockerRobotManager.store(new Bag(S)));
    }

    @Test
    public void should_return_m_locker_type_ticket_when_store_medium_bag_given_robot_manage_1_s_locker_and_1_free_primaryLockerRobot_and_1_superLockerRobot() {
        LockerRobotManager lockerRobotManager = buildLockerRobotManager();

        Ticket ticket = lockerRobotManager.store(new Bag(M));

        assertEquals(M, ticket.getLockerSizeType());
    }

    @Test
    public void should_throw_LockerIsFullException_when_store_medium_bag_given_robot_manage_1_s_locker_and_1_full_primaryLockerRobot_and_1_superLockerRobot() {

        LockerRobotManager lockerRobotManager = buildLockerRobotManager();
        lockerRobotManager.store(new Bag(M));

        assertThrows(LockerIsFullException.class, () -> lockerRobotManager.store(new Bag(M)));
    }

    @Test
    public void should_return_l_locker_type_ticket_when_store_large_bag_given_robot_manage_1_s_locker_and_1_primaryLockerRobot_and_1_free_superLockerRobot() {
        LockerRobotManager lockerRobotManager = buildLockerRobotManager();

        Ticket ticket = lockerRobotManager.store(new Bag(L));

        assertEquals(L, ticket.getLockerSizeType());
    }

    @Test
    public void should_throw_LockerIsFullException_when_store_large_bag_given_robot_manage_1_s_locker_and_1primaryLockerRobot_and_1_full_superLockerRobot() {

        LockerRobotManager lockerRobotManager = buildLockerRobotManager();
        lockerRobotManager.store(new Bag(L));

        assertThrows(LockerIsFullException.class, () -> lockerRobotManager.store(new Bag(L)));
    }

    @Test
    public void should_return_correct_bag_when_fetch_bag_given_valid_s_ticket() {
        LockerRobotManager lockerRobotManager = buildLockerRobotManager();
        Bag storedBag = new Bag(S);
        Ticket ticket = lockerRobotManager.store(storedBag);

        Bag fetchedBag = lockerRobotManager.fetchBagBy(ticket);

        assertSame(storedBag, fetchedBag);
    }
}
