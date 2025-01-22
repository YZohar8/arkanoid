// 211970389 Yonatan Zohar
package Game.objects;

import Game.animtion.GameLevel;
import Game.interfaces.Collidable;
import Game.interfaces.HitListener;
import Game.interfaces.HitNotifier;
import Game.interfaces.Sprite;
import biuoop.DrawSurface;
import geometric.objects.Ball;
import geometric.objects.Point;
import geometric.objects.Rectangle;
import geometric.objects.Velocity;
import global.Global;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block object in a game.
 * It implements both the Collidable and Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // define fields
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor function for Block withe random color.
     *
     * @param upperLeft The upper left point of the block
     * @param width     The width of the block
     * @param height    The height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = Global.createRandomColor();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor function for Block.
     *
     * @param upperLeft The upper left point of the block
     * @param width     The width of the block
     * @param height    The height of the block
     * @param color     The color of the block
     */
    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(),
                currentVelocity.getDy());
        // Checks if the hit point is on one of the lines
        if ((this.rectangle.getUpLine().isPointOnLine(collisionPoint))) {
            newVelocity.setDy(currentVelocity.getDy() * -1);
        } else if (this.rectangle.getDownLine().isPointOnLine(collisionPoint)) {
            newVelocity.setDy(currentVelocity.getDy() * -1);
        } else if ((this.rectangle.getRightLine().isPointOnLine(collisionPoint)
        )) {
            newVelocity.setDx(currentVelocity.getDx() * -1);
        } else if (this.rectangle.getLeftLine().isPointOnLine(collisionPoint)) {
            newVelocity.setDx(currentVelocity.getDx() * -1);
        }
        // return the new velocity after that change
        return newVelocity;
    }

    /**
     * Handles a collision between the Collidable object and another object.
     * and check with listeners if need to remove or the ball or
     * the Collidable object.
     * @param hitter the hit ball
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the current velocity of the object colliding
     *                       with the Collidable object.
     * @return the new velocity of the object after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity newVelocity = hit(collisionPoint, currentVelocity);
        this.notifyHit(hitter);
        // return the new velocity after that change
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        // draw the rectangle
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        // draw the rectangle Line
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        // do nothing
    }

    /**
     * the function remove the block from game.
     * @param game game object that this block connected to game.
     */
    public void removeFromGame(GameLevel game) {
        game.fixTheBallsMove();
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered HitListeners about a
     * hit event with the specified ball.
     *
     * @param hitter the ball that caused the hit event
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
