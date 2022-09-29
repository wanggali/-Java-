# CSS学习

* 选择器

  * 标签选择器
  * 类选择器
  * id选择器
  * **id>class>标签**

* 层次选择器

  * 后代选择器

  * 子选择器

    ```css
    body>p{
        
    }
    ```

  * 相邻兄弟选择器(只有一个，在它的下面)

    ```css
    .active+p{
        
    }
    ```

  * 通用选择器

    ```css
    .active~p{
        
    }
    ```

* 伪类选择器

  ```css
  ul li:first-child{
      //选择第一个
  }
  ul li:last-child{
      //选择第一个
  }
  //选择父类中第一个元素
  p:nth-child(1){
      //选中当前p元素父级元素的第一个（body），并且是当前元素菜生效
  }
  p:nth-of-type(1){
      //选中当前p元素父级元素的第一个（body）,类型类型
  }
  ```

* 属性选择器

  ```css
  a[id]{
      //存在id的属性
  }
  a[id=first]{
      //属性名=属性值（正则）
  }
  a[class*="links"]{
      // *=是以links开头的所有选择器
  }
  a[hrep^=http]{
      //选中hrep以http开头的元素
  } 
  a[hrep$=com]{
      //选中hrep以http结尾的元素
  }  
  ```

* 字体

  * font
  * font-size
  * font-weight
  * font-height

* 文本样式

  * color
    * rgb()
    * rgba() a透明度（0-1）
  * text-align
    * center
    * right
    * left
  * text-indent首航缩进
  * line-height行高
  * text-decoration下中上划线(超链接去下划线)
  * vartical-align水平对齐方式，参照物（两个之间）
  * text-shadow文本阴影（阴影颜色，水平偏移，垂直偏移，阴影半径）

* 列表

  * list-style
    * none去掉圆点
    * circle空心圆
    * decimal有序列表
    * square正方形

* 背景

  * background-img：url（）
  * background-repeat ：repeat-x 水平放置
  * background-repeat：repeat-y 垂直放置
  * background-repeat：no-repeat放置默认位置一个
  * 渐变
    * 开源项目：grabient
    * https://www.grabient.com/

* 盒子模型

  * margin外边距
  * boder边框
  * padding内边距
  * boder-redis圆角边框
  * 盒子阴影 box-shadow

* 浮动

  * 块级元素
  
  * 行内元素
  
  * block块元素
  
  * inline-block行内块元素
  
  * float浮动
  
  * clear
    * right右侧不允许浮动
    * left左侧不允许浮动
    * both两侧不允许浮动
    
  * overflow
  
    * 滚动条
  
    * hidden隐藏
  
    * scoll滚动条
  
    * 父级边框塌陷问题
  
      * 浮动元素后面增加div
  
      * 设置父元素高度
  
      * overflow
  
      * 父类中添加一个伪类(推荐使用)
  
        ```css
        #father:after{
            content:''
            display:block
            clear:both
        }
        ```
  
  * 定位
  
    * 相对定位
      * relative
      * top，left，right，bottom
      * 原来的位置会被保留
    * 绝对定位
      * absolute
      * 没有父级元素的定位的前提下，相对于浏览器定位
    * 固定定位
      * fixed
    * z-index
      * 层级
      * 最低是0，最高999
