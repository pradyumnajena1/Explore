package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Elevator {
  private Direction direction;
  private Status status;
  private int currentFloor;
  private SortedSet<Integer> targetFloors;
  private List<ElevatorButton> buttons;

  public Elevator(int currentFloor) {
    this.currentFloor = currentFloor;
    this.status = Status.STOPPED;
    this.direction = Direction.STOP;
    this.targetFloors = new TreeSet<>();
  }

  public void start() {



    status = Status.SERVING;
    while (targetFloors.size() > 0) {

      if (direction == Direction.UP) {
        SortedSet<Integer> remaining = targetFloors.tailSet(currentFloor);
        if (remaining.isEmpty()) {
          direction = targetFloors.isEmpty() ? Direction.STOP : Direction.DOWN;
          status = targetFloors.isEmpty() ? Status.STOPPED : Status.SERVING;
        } else {
          Integer next = remaining.first();
          System.out.println("going from " + currentFloor + " to " + next);
          currentFloor = next;
          targetFloors.remove(next);
        }
      } else if (direction == Direction.DOWN) {
        SortedSet<Integer> remaining = targetFloors.headSet(currentFloor);
        if (remaining.isEmpty()) {
          direction = targetFloors.isEmpty() ? Direction.STOP : Direction.UP;
          status = targetFloors.isEmpty() ? Status.STOPPED : Status.SERVING;
        } else {
          Integer next = remaining.first();
          System.out.println("going from " + currentFloor + " to " + next);
          currentFloor = next;
          targetFloors.remove(next);
        }
      }
    }
  }

  public void stop() {
    status = Status.STOPPED;
  }

  public void addTargetFloor(int targetFloor) {
    targetFloors.add(targetFloor);
  }

  private static enum Status {
    SERVING,
    STOPPED,
  }

  private static enum Direction {
    UP,
    DOWN,
    STOP;
  }

  private static class ElevatorButton {
    private final int floor;
    private final Elevator host;

    private ElevatorButton(int floor, Elevator elevator) {
      this.floor = floor;
      this.host = elevator;
    }

    public void pressed() {
      host.targetFloors.add(floor);
    }
  }
}
