package lecture2homework;

/* What are some ways to implement a singleton in Java 单例模式
*
* Singleton Design Pattern is a popular pattern to keep a global variable.
* It's a class with a getInstance() method, which is both public and static
* so that any class which needs a reference to this class can call this method
* and get the global reference.
*
* 单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

* 这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。

* 注意：

1、单例类只能有一个实例。
2、单例类必须自己创建自己的唯一实例。
3、单例类必须给所有其他对象提供这一实例。
*
* 应用实例：

1、一个班级只有一个班主任。
2、Windows 是多进程多线程的，在操作一个文件的时候，就不可避免地出现多个进程或线程同时操作一个文件的现象，所以所有文件的处理必须通过唯一的实例来进行。
3、一些设备管理器常常设计为单例模式，比如一个电脑有两台打印机，在输出的时候就要处理不能两台打印机打印同一个文件。
优点：

1、在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例（比如管理学院首页页面缓存）。
2、避免对资源的多重占用（比如写文件操作）。
缺点：没有接口，不能继承，与单一职责原则冲突，一个类应该只关心内部逻辑，而不关心外面怎么样来实例化。

使用场景：

1、要求生产唯一序列号。
2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。
*
* */

public class SingletonDesignPattern {
    public static class SingletonLazyI {
        /** Lazy initialization, non-thread-safe:
         * This is the classical version, but it's not thread-safe.
         * If more than one thread attempts to access instance at the same time,
         * more than one instance may be created.
         *
         * 懒汉式，线程不安全
         * 是否 Lazy 初始化：是
         *
         * 是否多线程安全：否
         *
         * 实现难度：易
         *
         * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
         * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
         *
         * 当程序需要这个实例的时候才去创建对象，就如同一个人懒的饿到不行了才去吃东西
         *
         * */
        private static SingletonLazyI instance;
        private SingletonLazyI () {}

        public static SingletonLazyI getInstance() {
            if (instance == null) {
                instance = new SingletonLazyI();
            }
            return instance;
        }
    }

    public static class SingletonLazyII {
        /** 懒汉式，线程安全
         是否 Lazy 初始化：是

         是否多线程安全：是

         实现难度：易

         描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
         优点：第一次调用才初始化，避免内存浪费。
         缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
         getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
         * */
        public static SingletonLazyII instance;
        private SingletonLazyII() {}

        public static synchronized SingletonLazyII getInstance() {
            if (instance == null) {
                instance = new SingletonLazyII();
            }
            return instance;
        }
    }

    public static class SingletonEager {
        /** Eager Instantiation 饿汉式
        饿汉就是类一旦加载，就把单例初始化完成，保证getInstance的时候，单例是已经存在的了。

         是否 Lazy 初始化：否

         是否多线程安全：是

         实现难度：易

         描述：这种方式比较常用，但容易产生垃圾对象。
         优点：没有加锁，执行效率会提高。
         缺点：类加载时就初始化，浪费内存。
         它基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。
         */
        private static SingletonEager instance = new SingletonEager();
        private SingletonEager () {}
        public static SingletonEager getInstance() {
            return instance;
        }
    }

    /* DCL: Double-checked locking
     * Nested Initialization
     * ...
     * */
}
