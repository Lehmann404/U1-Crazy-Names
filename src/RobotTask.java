import becker.robots.*;

/**
 * programmer: Isaac Lehmann
 * date: 2025-02-10
 * description:
 */
public class RobotTask {

    public void run() {
        City waterloo = new City(6, 29);
        Robot[] robots = new Robot[5];
        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Robot(waterloo, 5, i * 6, Direction.EAST, 100);
            robots[i].setTransparency(1);
        }

        Thread[] threads = {
                new Thread(() -> makeLetter(robots[0], 'I')),
                new Thread(() -> makeLetter(robots[1], 'S')),
                new Thread(() -> makeLetter(robots[2], 'A')),
                new Thread(() -> makeLetter(robots[3], 'A')),
                new Thread(() -> makeLetter(robots[4], 'C'))
        };
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }


    private void performTask(Robot robot, Direction direction, int magnitude, boolean dropItems) {
        switchDirection(robot, direction);
        for (int i = 0; i < magnitude; i++) {
            if (dropItems) {
                robot.putThing();
            }
            robot.move();
        }
    }


    private void makeLetter(Robot robot, Character letter) {
        switch (letter) {
            case 'I' -> {
                performTask(robot, Direction.EAST, 5, true);
                performTask(robot, Direction.WEST, 3, false);
                performTask(robot, Direction.NORTH, 5, true);
                performTask(robot, Direction.WEST, 2, true);
                performTask(robot, Direction.EAST, 5, true);
            }
            case 'S' -> {
                performTask(robot, Direction.EAST, 4, true);
                performTask(robot, Direction.NORTH, 3, true);
                performTask(robot, Direction.WEST, 4, true);
                performTask(robot, Direction.NORTH, 2, true);
                performTask(robot, Direction.EAST, 5, true);

            }
            case 'A' -> {
                performTask(robot, Direction.NORTH, 5, true);
                performTask(robot, Direction.EAST, 4, true);
                performTask(robot, Direction.SOUTH, 2, true);
                performTask(robot, Direction.WEST, 4, true);
                performTask(robot, Direction.EAST, 4, false);
                performTask(robot, Direction.SOUTH, 4, true);
            }
            case 'C' -> {
                performTask(robot, Direction.EAST, 5, true);
                performTask(robot, Direction.WEST, 5, false);
                performTask(robot, Direction.NORTH, 5, true);
                performTask(robot, Direction.EAST, 5, true);
            }
        }
    }

    private void switchDirection(Robot robot, Direction direction) {
        while (robot.getDirection() != direction) {
            robot.turnLeft();
        }
    }
}