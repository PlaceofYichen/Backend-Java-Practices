package mondaylectures.mylist.impl;

import mondaylectures.mylist.IArrayList;

public class ArrayListImpl implements IArrayList {

    private int[] data;
    private int size;
    public ArrayListImpl() {
        this(10);
    }

    public ArrayListImpl(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    /**
     * Time Complexity: O(1)
     * @return
     */
    @Override
    public int capacity() {
        return data.length;
    }

    /**
     * TC: O(1)
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * TC: O(1)
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * O(N)
     * @param element
     */
    @Override
    public void add(int element) {
//        if (size == data.length) {
//            // 数组已满
//            throw new IllegalArgumentException("This ArrayList is full.");
//        }
//        data[size] = element;
//        size++;
        add(size, element);
    }

    /**
     * O(N)
     * @param index
     * @param element
     */
    @Override
    public void add(int index, int element) {
//        if (size == data.length) {
//            // 数组已满
//            throw new IllegalArgumentException("This ArrayList is full.");
//        }
        if (index < 0 || index > size) {
            // index输入错误：小于0 或者 大于初始化容量
            throw new IllegalArgumentException("Input error: the input index is either smaller than 0 " +
                    "or the input index is larger than the initial capacity.");
        }
        // 扩容
        if (size == data.length) {
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    /**
     * O(N)
     * @param element
     * @return
     */
    @Override
    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * O(1)
     * @param index
     * @return
     */
    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            // index输入错误：小于0 或者 大于初始化容量
            throw new IllegalArgumentException("Input error: the input index is either smaller than 0 " +
                    "or the input index is larger than the initial capacity.");
        }
        return data[index];
    }

    /**
     * O(1)
     * @param index
     * @param element
     */
    @Override
    public void set(int index, int element) {
        if (index < 0 || index >= size) {
            // index输入错误：小于0 或者 大于初始化容量
            throw new IllegalArgumentException("Input error: the input index is either smaller than 0 " +
                    "or the input index is larger than the initial capacity.");
        }
        data[index] = element;
    }

    /**
     * O(N)
     * @param index
     * @return
     */
    @Override
    public int remove(int index) {
        if (index < 0 || index >= size) {
            // index输入错误：小于0 或者 大于初始化容量
            throw new IllegalArgumentException("Input error: the input index is either smaller than 0 " +
                    "or the input index is larger than the initial capacity.");
        }
        int res = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        // 缩容
        // Lazy操作。数组减少到1/4时才缩容
        if (data.length / 4 == size && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return res;
    }

    /**
     * O(N)
     * @param element
     */
    @Override
    public void removeElement(int element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                index = 1;
            }
        }
        if (index == -1) {
            // 元素不存在
            throw new IllegalArgumentException("The element does not exist.");
        }
        remove(index);
    }

    @Override
    public void print() {
        System.out.println("size : " + size);
        System.out.println("capacity : " + data.length);
        System.out.print("The list is : [ ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.print("]");
        System.out.println("\n-------------------------------");
    }

    @Override
    public void resize(int capacity) {
        int[] temp = new int[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
}
