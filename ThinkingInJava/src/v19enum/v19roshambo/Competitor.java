//: enumerated/Competitor.java
// Switching one enum on another.
package v19enum.v19roshambo;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
} ///:~
