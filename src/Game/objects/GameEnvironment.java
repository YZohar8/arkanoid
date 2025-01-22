// 211970389 Yonatan Zohar
package Game.objects;

import Game.interfaces.Collidable;
import geometric.objects.Line;
import geometric.objects.Point;
import java.util.ArrayList;


/**
 *  The GameEnvironment class represents the environment of the game.
 *  It stores a collection of collidables and provides methods for managing and
 *  interacting with them.
 */
public class GameEnvironment {
    // define fields
    private java.util.List<Collidable> listCollidable;

    /**
     * Constructor for GameEnvironment class.
     * Initializes the list of collidables.
     */
    public GameEnvironment() {
        listCollidable = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the list of collidables.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        listCollidable.add(c);
    }

    /**
     * Checks if a given line trajectory will collide with any of the
     * collidables in the environment, and returns information about the
     * closest collision point.
     *
     * @param trajectory The line trajectory to check.
     * @return A CollisionInfo object containing information about the closest
     * collision point, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // if is not coolidable object in the list
        if (listCollidable.size() == 0) {
            return null;
        }
        // define variables
        Collidable c = null;
        Point collisionPoint = null, pointTmp;
        double distance = 0, distanceTmp;
        boolean flag = true;

        /* Goes through the whole list and checks on each element what is the
         closest intersection it has with the line */

        for (int i = 0; i < listCollidable.size(); i++) {
            pointTmp = trajectory.closestIntersectionToStartOfLine(
                    listCollidable.get(i).getCollisionRectangle());

            /* If the intersection point is the closest to anything that
             was before, keep it */

            if (pointTmp != null) {
                distanceTmp = pointTmp.distance(trajectory.start());
                if ((distanceTmp <= distance) || (flag)) {
                    c = listCollidable.get(i);
                    distance = distanceTmp;
                    collisionPoint = pointTmp;
                    flag = false;
                }
                /* If the ball is already inside the rectangle, you will find
                 the closest point to take it out of the rectangle */
            } else if (listCollidable.get(i).getCollisionRectangle()
                    .pointContained(trajectory.start())) {
                pointTmp = listCollidable.get(i).getCollisionRectangle().
                        closePointOnWallsToPointContained(trajectory.start());
                distanceTmp = pointTmp.distance(trajectory.start());
                if ((distanceTmp <= distance) || (flag)) {
                    c = listCollidable.get(i);
                    distance = distanceTmp;
                    collisionPoint = pointTmp;
                    flag = false;
                }
            }
        }
        // if not found insertion Point
        if (flag) {
            return null;
        }
        return new CollisionInfo(collisionPoint, c);
    }

    /**
     * Returns the number of collidables in the environment.
     *
     * @return The number of collidables in the environment.
     */
    public int sizeOfCollision() {
        return listCollidable.size();
    }

    /**
     * Returns the collidable object at the specified index in
     * the list of collidables.
     *
     * @param index The index of the collidable object to return.
     * @return The collidable object at the specified index.
     */
    public Collidable getCollision(int index) {
        return listCollidable.get(index);
    }

    /**
     * Removes the given collidable object from the list of collidables.
     *
     * @param c The collidable object to remove.
     */
    public void removeCollision(Collidable c) {
        listCollidable.remove(c);
    }

}

