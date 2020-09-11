package gamesetting;

/**
 * Counter class.
 * The class role is to enable: increasing, decreasing and get the value of a counter.
 */
public class Counter {

    private int num = 0;

    /**
     * The function role is to increase some counter.
     * @param number represents the number that we want to add.
     */
    public void increase(int number) {
        this.num = this.num + number;
    }

    /**
     * The function role is to decrease some counter.
     * @param number represents the number that we want to add.
     */
    public void decrease(int number) {
        this.num = this.num - number;
    }

    /**
     * Get the counter value.
     * @return the counter value.
     */
    public int getValue() {
        return this.num;
    }
}
