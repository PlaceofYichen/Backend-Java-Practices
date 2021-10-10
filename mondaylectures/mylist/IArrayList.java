package mondaylectures.mylist;

public interface IArrayList {

    /**
     * 容量大小
     * @return
     */
    int capacity();

    /**
     * 实际占用大小
     * @return
     */
    int size();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 加入：末尾
     */
    void add(int element);

    /**
     * 加入：中间插入
     * @param index
     * @param element
     */
    void add(int index, int element);

    /**
     * 查找元素
     * @param element
     * @return
     */
    boolean contains(int element);

    /**
     * 找到元素
     * @param index
     * @return
     */
    int get(int index);

    /**
     * 修改元素
     * @param index
     * @param element
     */
    void set(int index, int element);

    /**
     * 根据index查找删除
     * @param index
     * @return
     */
    int remove(int index);

    /**
     * 找到元素进行删除
     * @param element
     * @return
     */
    void removeElement(int element);

    /**
     * 打印数据和结果
     */
    void print();

    /**
     * 改变capacity的大小
     * @param capacity
     */
    void resize(int capacity);
}