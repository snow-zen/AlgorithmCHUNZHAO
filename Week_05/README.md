学习笔记

常见位运算操作：

`x ^ 0 = x`

`x ^ 0xffffffff = ~x`

`~0 = 0xffffffff`

`x ^ (~x) = 0xffffffff`

`x ^ x = 0`


位运算交换

```java
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

指定位置位运算

`(~0 << n) & x`将x最右边的n位清零

`(x >> n) & 1`获取x的第n位值

`x & (1 << n)`获取x的第n位的幂值

`x | (1 << n)`仅将第n位置为1

`x & (~(1 << n))`仅将第n位置为0

`x & ((1 << n) - 1)`将x最高位至第n位清零