package com.gali.box;


/**
 *      这个类是我们描述的，未来会提供给其他的用户使用
 *
 *      目的：设计一个新的容器，解决原有数组长度固定的问题
 *              看起来长度是可变的
 *
 *      做事：存储，获取，删除         方法
 *
 *      我们需要有一个真实的容器，在底层存数据     是数组-----是属性
 *
 *      类和类的关系有三种体现
 *      A is-a B    继承/实现
 *      A has-a B   B作为A的属性
 *      A use-a B   B作为A方法的一部分(参数，内部new)
 *
 */
public class ArrayBox<T> extends AbstractBox<T>{

    //静态常量
    private static final int DEFAULT_CAPACITY = 10;

    //设计一个属性，是一个数组，存储真实数据用
    private Object[] elementDate;
    //设计一个属性，用来记录元素该存储的位置               0 1 2
    //设计一个属性，用来记录底层数组真实存储的有效元素个数   1 2 3
    private int size = 0;//记录有效元素的个数


    //构造方法------就是创建当前类的对象(同时我想要做点别的事情)
    public ArrayBox(){
        elementDate = new Object[DEFAULT_CAPACITY];//可读性
    }
    public ArrayBox(int capacity){
        elementDate = new Object[capacity];//可读性
    }


    //获取有效元素的个数
    public int size(){
        return size;
    }
    //获取底层真实数组的长度
    private int length(){
        return elementDate.length;
    }


    //设计一个方法，可以添加元素
    //  是否需要提供一些条件-----------参数     需要存储的那个数据
    //  是否需要留下一个结果-----------返回值    是否存储成功
    public boolean add(T element){
        //要做一个严谨的判断
        //确保数组一定有小格子
        this.ensureCapacityInternal(size+1);
        //用户给我们提供了一个element元素
        //我们需要将element元素存到我们自己的内部数组里
        elementDate[size++] = element;
        //可以告知存储成功
        return true;
    }
    //设计一个add重载，element，index
    public void add(int index,T element){
        //1.确保有容量
        //2.从size----index倒叙，向后移动覆盖，前面index位置空出来
        //3.element存入index位置
    }

    //设计一个方法，可以获取元素
    //  是否需要提供条件    参数      位置--1(索引)
    //  是否需要留下结果    返回值    找到的那个元素--int
    public T get(int index){
        //先做一个index范围的检测---找小弟
        this.rangeCheck(index);
        //按照index位置，找寻数组中真实的数据
        return (T)elementDate[index];
    }

    //设计一个方法，可以删除元素
    //      10  add-6   10,20,30,40,50,60,0,0,0,0
    //                  10,20,30,40,50,60
    //                  从给定的位置开始，到size结束，后面的元素前移覆盖
    //                  index--2    size--6
    //                  10,20,40,50,60,0        size减少一个，人为的将最后的60改为0
    //  参数--index  返回值--删掉的那个旧元素
    public T remove(int index){
        //1.调用检测index范围的方法
        this.rangeCheck(index);
        //2.获取index位置的那个旧元素
        T oldValue = (T)elementDate[index];
        //3.元素前移覆盖，(index)---(size-1)
        for(int i=index;i<size-1;i++){          //2---4
            elementDate[i] = elementDate[i+1];  //40-30  50-40  60-50
        }
        //4.有效元素的个数减少一个记录  --size
        //5.人为的将原来size-1那个位置的元素变成0
        elementDate[--size] = null;
        //6.将旧元素返回
        return oldValue;
    }

    //===========================================

    //设计一个小弟方法  小A，为add服务，确保数组内部有小格子/容量
    //  是否需要条件      最少需要多大容量?   int
    //  是否需要结果      void
    private void ensureCapacityInternal(int minCapacity){
        //需要用最小容量，跟现在elementDate数组的容量比
        if(minCapacity - elementDate.length > 0){
            //容量不够了，想办法让它够      先算算
            this.grow(minCapacity);
        }
    }

    //设计一个小弟方法  小B，为ensureCapacityInternal方法服务
    //经过一个周密详细的计算   ，创建的新数组要多长比较合适
    //  需要的最小容量作为参数
    private void grow(int minCapacity){
        //小B自认为是一个数学奇才，先不管你需要多少
        //按照自己的经验，先算算(自负)
        //1.获取旧数组的容量
        int oldCapacity = elementDate.length;
        //2.按照小B同学的经验，计算------>1.5倍
        int newCapacity = oldCapacity + (oldCapacity>>1);
        //经过计算后再跟所需最小容量再比一下
        if(newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        //真的需要去创建新数组，真的需要将旧数组的元素搬运过来
        elementDate = this.copyOf(elementDate,newCapacity);
    }

    //设计一个方法，小C 负责在grow方法的之后，真的去做"力气活"
    //去创建新数组，真的需要将旧数组的元素搬运过来
    //      参数?     旧数组，newCapacity
    //      返回值?    全新的数组(带着旧数组的元素，新的空格)
    private Object[] copyOf(Object[] oldArray,int newCapacity){
        //1.根据计算出来新的长度，去创建新数组
        Object[] newArray = new Object[newCapacity];
        //2.将旧数组内的元素挨个访问一遍，取出来放入新数组的对应位置
        for(int i=0 ; i<oldArray.length ; i++){
            newArray[i] = oldArray[i];
        }
        //3.将新数组返回
        return newArray;
    }

    //设计一个方法，小D 负责检测index是否合法
    //  参数？ index
    //  void    自定义的异常说明这个index不合法      更严重
    private void rangeCheck(int index){
        //  10  add-6       index 0 9     0-5 0-9  7
        if(index<0 || index>=size){
            //index就是不合法的索引位置
            throw new BoxIndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
    }


}
