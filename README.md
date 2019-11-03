# Sales Taxes

[![Build Status](https://travis-ci.org/acampolm/lm_test.svg?branch=master)](https://travis-ci.org/acampolm/lm_test)

This program is intended to calculate the taxes for a given list of products. this products will be consumed but standard input and the resulting bill will be represented by standard output. 

#example

```bash

lm_test$ cat input.txt
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
lm_test$ cat input.txt | java -cp ./target/test-0.1-SNAPSHOT.jar com.lm.test.Main
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83
```
