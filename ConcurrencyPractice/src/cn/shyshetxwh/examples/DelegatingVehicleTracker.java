package cn.shyshetxwh.examples;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FileName: DelegatingVehicleTracker
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 19:35
 */
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    //返回实时拷贝
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
        //可以返回静态拷贝
        //return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException();
        }
    }
}
