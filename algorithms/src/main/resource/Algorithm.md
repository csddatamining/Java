##算法笔记
###复杂度分析
大O复杂度表示法：表示代码时间随数据规模增长的变化趋势，也叫渐进时间复杂度（asymptotic time complexity），简称时间复杂度。  
时间复杂度分析
- 只关注循环执行次数最多的一段代码
- 加法法则：总复杂度等于量级最大的那段代码的复杂度
- 乘法法则：嵌套代码的复杂度等于嵌套内外代码复杂度的乘积  

常见的复杂度：
- 常量阶O(1)：一般情况下，只要算法中不存在循环语句、递归语句，即使有成千上万行代码，时间复杂度也是O(1)
- 对数阶O(log<sup>n</sup>)：例
```
 i=1;
 while (i <= n)  {
   i = i * 2;
 }
```

- 线性对数阶O(nlog<sup>n</sup>)：归并排序、快速排序
- 线性阶O(n)
- 平方阶O(n<sup>2</sup>)
- 指数阶O(2<sup>n</sup>)
- 阶乘阶O(n!)

### 空间复杂度分析
空间复杂度就是渐进空间复杂度(asymptotic)，表示算法的存储空间与数据规模之间的增长关系

### 最好、最坏情况时间复杂度

