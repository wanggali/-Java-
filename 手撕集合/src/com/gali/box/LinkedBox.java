package com.gali.box;

//这是我们自己描述的一个新的容器
//当然，这里面需要设计几个常用方法  存，取，删，有效个数
public class LinkedBox<T> extends AbstractBox<T>{

    //设计两个属性(理解为以前我们设计ArrayBox中的那个elementDate，记录真实数据的位置)
    private Node<T> first;
    private Node<T> last;
    //设计一个属性，用来记录有效元素的个数
    private int size = 0;

    //设计一个方法，用来存储元素
    public boolean add(T element){
        //调用小A方法，帮我们将element存入链表结构
        this.linkLast(element);
        //告知添加成功
        return true;
    }

    //设计一个方法，存储元素，向指定位置
    @Override
    public void add(int index, T element){

    }

    //设计一个方法，用来获取元素
    public T get(int index){
        //检测index是否合法
        this.rangeCheck(index);
        //找寻index对应位置的那个Node对象----重要
        Node<T> targetNode = this.node(index);
        //将targetNode对象中的item数据，返回
        return targetNode.item;
    }

    //设计一个方法，用来删除元素
    public T remove(int index){
        //检测index范围是否合法
        this.rangeCheck(index);
        //找到index位置的那个Node
        Node<T> targetNode = this.node(index);
        //设计一个小弟方法  小D
        //负责删除当前的这个targetNode对象，让他帮忙返回oldValue
        T oldValue = this.unlink(targetNode);
        //将oldValue值返回
        return oldValue;
    }

    //设计一个方法，获取有效元素的个数
    @Override
    public int size(){
        return size;
    }

    //================================================

    //设计一个小弟方法  小A
    //  负责将一个新的元素，存储在一个新的Node对象里
    //  新的Node对象，需要挂在链表结构的尾端
    //  参数----element元素
    //  返回值---void      连接在尾端
    private void linkLast(T element){
        //获取链表的尾节点对象
        Node<T> l = last;
        //需要自己创建一个新的Node对象,同时还做了一个关联(前一个)
        Node<T> newNode = new Node(l,element,null);
        //将newNode对象设置为尾节点
        last = newNode;
        //判断原来的last是否有
        if(l == null){//证明原来没有尾节点对象，这个linkedbox从来没有使用过，空的
            first = newNode;
        }else{//原来是有尾节点对象的，以前使用过
            l.next = newNode;
        }
        //有效元素个数增加一个
        size++;
    }

    //设计一个小弟    小B  负责检测index是否合法
    private void rangeCheck(int index){
        if(index<0 || index>=size){
            throw new BoxIndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
    }

    //设计一个小弟    小C  负责找寻给定index位置的那个Node对象
    //  参数      index
    //  返回值    Node
    private Node<T> node(int index){
        Node<T> targetNode;//用来存储找到的那个目标Node
        //判断index对应的位置，是在前半部分，还是在后半部分
        if(index < (size>>1)){//从前向后搜索
            targetNode = first;
            for(int i=0;i<index;i++){
                targetNode = targetNode.next;
            }
        }else{//从后向前搜索
            targetNode = last;
            for(int i=size-1;i>index;i--){
                targetNode = targetNode.prev;
            }
        }
        //最后将targetNode返回
        return targetNode;
    }

    //设计一个小弟方法  小D
    //负责删除当前的这个targetNode对象，让他帮忙返回oldValue
    //  参数?     Node targetNode
    //  返回值?   int oldValue
    private T unlink(Node<T> targetNode){
        //获取当前targetNode对象中的item数据
        T oldValue = targetNode.item;
        //获取当前targetNode对象的前一个Node对象
        Node<T> prev = targetNode.prev;
        //获取当前targetNode对象的下一个Node对象
        Node<T> next = targetNode.next;
        //删除当前的node
        //打断前半部分的连接
        if(prev == null){//当前的node就是第一个
            first = next;
        }else{
            prev.next = next;
            targetNode.prev = null;
        }
        //打断后半部分的连接
        if(next == null){//当前的node就是最后一个
            last = prev;
        }else{
            next.prev = prev;
            targetNode.next = null;
        }
        //千万别忘了，让size记录的有效个数减少一个
        size--;
        //最后将oldValue返回
        return oldValue;
    }

    //=================================================

    //内部类，将一个类放在另一个类的内部(属性/方法并列---全局，属性/方法的内部---局部)
    //Node是LinkedBox的全局内部类
    //  省略了一个java类文件
    //  只有当前的LinkedBox类使用，别人用不到
    //  隐藏了底层存储的机制

    //是一个非常简单的对象
    //只负责存储真实的数据，还有下一个对象，上一个对象的地址(Node)

    private static class Node<T> {

        Node prev;//(绿色)previous 上一个Node对象的地址引用
        T item;//(紫色)当前Node里面存储的真实数据---int(T)
        Node next;//(蓝色)下一个Node对象的地址引用

        //为了创建Node对象的同时，给里面的属性赋值方便
        //自己描述一个带参数的构造方法
        public Node(Node prev,T item,Node next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }


}
