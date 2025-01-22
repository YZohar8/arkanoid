// 211970389 Yonatan Zohar
package Game.objects;

/**
 * The Counter class represents a simple counter that can
 * be incremented or decremented.
 */
public class Counter {
    // define fields
    private int counter;

    /**
     * Constructs a Counter object with an initial count of 0.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Constructs a Counter object with the specified initial count.
     *
     * @param counter the initial count for the Counter object
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Increases the counter by 1.
     */
    public void increase() {
        this.counter++;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Decreases the counter by 1.
     */
    public void decrease() {
        this.counter--;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.counter;
    }
}
