//: annotations/database/Uniqueness.java
// Sample of nested annotations
package v20annotation.v20database;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
} ///:~
