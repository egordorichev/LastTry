package org.egordorichev.lasttry.world.environment;

public class Event {
    public static Event bloodMoon = new BloomMoon();
    public static Event rain = new Rain();

    /**
     * Event's name
     */
    private String name;

    /**
     * If event is happening
     */
    private boolean happening = false;

    public Event(String name) {
        this.name = name;
    }

    public boolean start() {
        if (this.happening) {
            return false;
        }

        if (this.canHappen()) {
            this.happening = true;
            this.onStart();
            return true;
        }

        return false;
    }

    public void update(int dt) {

    }

    public void end() {
        if (!this.happening) {
            return;
        }

        this.happening = false;
        this.onEnd();
    }

    protected void onStart() {

    }

    protected void onEnd() {

    }

    public boolean isHappening() {
        return this.happening;
    }

    public boolean canHappen() {
        return false;
    }

    public String getName() {
        return this.name;
    }
}