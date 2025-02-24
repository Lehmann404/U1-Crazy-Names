import becker.robots.*;


/**
 * @author Isaac Lehmann
 * @version 2025-02-10
 */

public class RobotTask {

    /**
     * Runs the main task of creating a city and initializing robots
     * to spell out "ISAAC".
     */
    public void run() {
        City waterloo = new City(6, 29);
        //Created robots in an array for each letter
        Robot[] robots = new Robot[5];
        // create the new robot in 6 space intervals (i*6) so they are equidistant and make robots invisible.
        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Robot(waterloo, 5, i * 6, Direction.EAST, 100);
            robots[i].setTransparency(1);
        }

        //using java's "thread" feature to have multiple letters written at one time, and creating them as an array
        //in order to start them in a loop.
        Thread[] threads = {
                new Thread(() -> makeLetter(robots[0], 'I')),
                new Thread(() -> makeLetter(robots[1], 'S')),
                new Thread(() -> makeLetter(robots[2], 'A')),
                new Thread(() -> makeLetter(robots[3], 'A')),
                new Thread(() -> makeLetter(robots[4], 'C'))
        };
        //starting the threads.
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    /**
     * Directs the robot to perform tasks based on the given direction, magnitude,
     * and whether to drop items.
     *
     * @param robot     The robot carrying out the task.
     * @param direction The direction in which the robot moves.
     * @param magnitude The number of steps the robot moves.
     * @param dropItems Whether the robot drops items along its path.
     */
    private void performTask(Robot robot, Direction direction, int magnitude, boolean dropItems) {
        switchDirection(robot, direction);
        for (int i = 0; i < magnitude; i++) {
            if (dropItems) {
                robot.putThing();
            }
            robot.move();
        }
    }

    /**
     * Directs the robot to create a specified letter by performing a sequence of tasks.
     *
     * @param robot  The robot constructing the letter.
     * @param letter The letter to be constructed.
     */
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

    /**
     * Adjusts the direction of the robot until it matches the specified direction.
     *
     * @param robot     The robot that needs to change its direction.
     * @param direction The desired direction for the robot.
     */
    private void switchDirection(Robot robot, Direction direction) {
        while (robot.getDirection() != direction) {
            robot.turnLeft();
        }
    }
}