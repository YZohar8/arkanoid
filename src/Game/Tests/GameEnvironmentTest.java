// 211970389 Yonatan Zohar
package Game.Tests;

import java.awt.geom.Line2D;
import Game.objects.Block;
import Game.objects.GameEnvironment;
import biuoop.DrawSurface;
import biuoop.GUI;
import geometric.objects.Ball;
import geometric.objects.Line;
import geometric.objects.Point;
import geometric.objects.Velocity;
import global.Global;
import java.awt.Color;
import java.util.Random;

public class GameEnvironmentTest {
    public static void main(String[] args) {
        Random rand = new Random();
        // create a GUI window with a size of 200x200 pixels
        GUI gui = new GUI("title", 500, 500);
        // create a sleeper to pause the animation
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        /* create a ball with radius 30 and black color at the given
           start coordinates and set the velocity */
        GameEnvironment gameEnvironment = new GameEnvironment();
        geometric.objects.Point p1 = new Point(50, 50);
        Ball ball = new Ball(p1, 5, java.awt.Color.BLACK, gameEnvironment);
        ball.setVelocity(5, 3);
        // set the borders of the window in which the ball is drawn
        gameEnvironment.addCollidable(new Block(new geometric.objects.Point(0, 0), 500, 15, Color.black));
        gameEnvironment.addCollidable(new Block(new geometric.objects.Point(0, 0), 15, 500, Color.black));
        gameEnvironment.addCollidable(new Block(new geometric.objects.Point(485, 0), 15, 500, Color.black));
        gameEnvironment.addCollidable(new Block(new geometric.objects.Point(0, 485), 500, 15, Color.black));
        ball.setWindowStart(0, 0);
        ball.setWindowEnd(500, 500);
        double x;
        double y;
        while (gameEnvironment.sizeOfCollision() < 40) {
            x = rand.nextDouble(485) + 15;
            y = rand.nextDouble(485) + 15;
            gameEnvironment.addCollidable(new Block(new geometric.objects.Point(x, y), 35, 10,
                    Global.createRandomColor()));
        }



        int count = 0;
        // infinite loop to continuously move and redraw the ball
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < gameEnvironment.sizeOfCollision(); i++) {
                Block b = (Block) gameEnvironment.getCollision(i);
                b.drawOn(d);
            }
            Point back = new Point(ball.getX(), ball.getY());
            Velocity v = new Velocity(ball.getVelocity().getDx(), ball.getVelocity().getDy());
            ball.timePassed();
            // check
            Point afther = new Point(ball.getX(), ball.getY());
            Line l1 = new Line(back, afther);
            for (int i = 4; i < gameEnvironment.sizeOfCollision(); i++) {
                Block b = (Block) gameEnvironment.getCollision(i);
                if (b.getCollisionRectangle().pointContained(afther)) {
                    if (b.getCollisionRectangle().getDownLine().intersectionWith(l1) != null && ball.getVelocity().getDy() < 0) {
                        b.getCollisionRectangle().getDownLine().intersectionWith(l1);
                        ball.setVelocity(v);
                        ball.setCenter(back);
                        ball.timePassed();
                    }
                    if (b.getCollisionRectangle().getUpLine().intersectionWith(l1) != null && ball.getVelocity().getDy() > 0) {
                        b.getCollisionRectangle().getUpLine().intersectionWith(l1);
                        ball.setVelocity(v);
                        ball.setCenter(back);
                        ball.timePassed();
                    }
                    if (b.getCollisionRectangle().getRightLine().intersectionWith(l1) != null && ball.getVelocity().getDx() < 0) {
                        b.getCollisionRectangle().getRightLine().intersectionWith(l1);
                        ball.setVelocity(v);
                        ball.setCenter(back);
                        ball.timePassed();
                    }
                    if (b.getCollisionRectangle().getLeftLine().intersectionWith(l1) != null && ball.getVelocity().getDx() > 0) {
                        b.getCollisionRectangle().getLeftLine().intersectionWith(l1);
                        ball.setVelocity(v);
                        ball.setCenter(back);
                        ball.timePassed();
                    }
                }

            }
                // draw the ball on the draw surface and shoe it
                ball.drawOn(d);
                gui.show(d);
                // wait for 50 milliseconds to slow down the animation
                sleeper.sleepFor(Global.TIME_WAIT);
            }
        }

    }
