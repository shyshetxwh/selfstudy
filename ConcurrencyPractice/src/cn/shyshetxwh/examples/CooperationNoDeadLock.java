package cn.shyshetxwh.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * FileName: CooperationNoDeadLock
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 8:21
 */
public class CooperationNoDeadLock {

    @ThreadSafe
    class Taxi {
        @GuardedBy("this")
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public void setLocation(Point location) {
            boolean reached;
            synchronized (this) {
                this.location = location;
                reached = location.equals(destination);
            }
            if (reached)
                dispatcher.notifyAvailable(this);
        }
    }

    @ThreadSafe
    class Dispatcher {
        @GuardedBy("this")
        private final Set<Taxi> taxis;
        @GuardedBy("this")
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi t) {
            availableTaxis.add(t);
        }

        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                copy = new HashSet<>(taxis);
            }
            Image image = new Image();
            for (Taxi t : copy) {
                image.drawMarker(t.getLocation());
            }
            return image;

        }

    }


    class Image {
        public void drawMarker(Point p) {
        }
    }
}
