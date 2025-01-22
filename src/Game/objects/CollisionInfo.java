// 211970389 Yonatan Zohar
package Game.objects;

import Game.interfaces.Collidable;
import geometric.objects.Point;

/**
 * This class represents information about a collision that occurred between two objects.
 */
public class CollisionInfo {
    // define fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor for CollisionInfo objects.
     *
     * @param collisionPoint the point at which the collision occurred
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurred.
     *
     * @return the point at which the collision occurred
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
