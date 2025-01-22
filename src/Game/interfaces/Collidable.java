// 211970389 Yonatan Zohar
package Game.interfaces;

import geometric.objects.Point;
import geometric.objects.Rectangle;
import geometric.objects.Velocity;

/**
 * The Collidable interface represents objects in the game that can
 * be collided with.
 * Implementing classes must provide a collision rectangle and a method
 * for handling collisions.
 */
public interface Collidable {

    /**
     * Returns the collision rectangle of the Collidable object.
     * @return the collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles a collision between the Collidable object and another object.
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the current velocity of the object colliding
     *                       with the Collidable object.
     * @return the new velocity of the object after the collision.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
