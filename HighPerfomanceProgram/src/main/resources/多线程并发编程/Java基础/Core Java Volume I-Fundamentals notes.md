#第四章 对象与类
##4.1 面向对象程序概述
面向对象程序设计OOP，当前主流程序设计泛型  
《算法+数据结构=程序》
###4.1.1 类
由类构造对象的过程称为创建类的实例（instance）  
封装（encapsulation），有时称为数据隐藏  
对象中的数据称为实例域（instance field），操纵数据的过程称为方法（method）  
通过扩展一个类来建立另一个类的过程称为继承（inheritance）  
###4.1.2 对象
三个特性：
- 对象的行为（behavior）
- 对象的状态（state）
- 对象标识（identity）
###4.1.3 识别类
传统过程化程序设计从顶部main函数开始。面向对象程序设计没有所谓顶部。  
OOP初学者可从设计类开始，再往每个类中加方法，即分析问题找名词，方法对应动词
###4.1.4 类之间的关系
- 依赖（dependence) "use-a"，尽可能减少依赖（高内聚低耦合）
- 聚合（aggregation）"has-a"
- 继承（inheritance）"is-a"

##4.2 使用预定义类
示例Date类，如何构造对象，如何调用类的方法
###4.2.1 对象与对象变量
Java中使用构造器（constructor）构造新实例，构造器的名字应与类名相同    
构造Date对象，构造器前加new操作符：new Date()  
Java中，任何对象变量的值都是对存储在另一个地方的一个对象的引用。new 操作符的返回值也是一个引用  
局部变量不会自动地初始化为null，而必须通过调用new或者将他们设置为null进行初始化。  
###4.2.2 Java类库中的LocalDate类
Date类的实例有个状态，即特定的时间点。  
时间是用距离一个固定时间点的毫秒数（可正可负）来表示的，这个点即所谓的纪元（epoch），它是UTC时间1970年1月1日00:00:00  
UTC是Coordinated Universal Time的缩写，与大家熟悉的GMT时间（Greenwich Mean Time,格林尼治时间）一样，是一种具有实践意义的科学标准时间。
###4.2.3 更改器方法与访问器方法
更改器方法（mutator method）  
只访问对象而不修改对象的方法称为访问其方法（accessor method）

##4.3 用户自定义类
Java中最简单的类定义形式：
```
class ClassName{
    field1
    field2
    ...
    constructor1
    constructor2
    ...
    method1
    method2
    ...
}
```
构造器总是伴随着new操作符的执行被调用
- 构造器与类重名
- 每个类可以有一个以上构造器
- 构造器有0个、1个、或多个参数
- 构造器没有返回值
- 构造器总是伴随着new操作符一起调用

final修饰符大都应用于基本（primitive）类型域，或不可变（immutable）类的域（如果类中的每个方法都不会改变其对象，这种类就是不可变对象）

   