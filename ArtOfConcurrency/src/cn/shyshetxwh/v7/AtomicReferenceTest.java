package cn.shyshetxwh.v7;

import java.util.concurrent.atomic.AtomicReference;

/**
 * FileName: AtomicReferenceTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/12 0012 21:44
 */
public class AtomicReferenceTest {
    public static AtomicReference<User> userRef = new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("conan", 15);
        userRef.set(user);
        User updateUser = new User("Shinichi", 17);
        userRef.compareAndSet(user, updateUser);
        System.out.println(userRef.get().getName());
        System.out.println(userRef.get().getAge());
    }
}
