# Implementing the Luhn algorithm in Java 8 and 9 using infinite streams

 implementing the Luhn algorithm to calculate a simple checksum.
 
 A short summary of the Luhn algorithm:
 
 - Given a non-negative, integer number of arbitrary length.
 - From right to left, double every second digit. If the resulting number has two digits, take the square sum (so we have one digit again).
 - Sum up all the resulting digits.
 - If the sum modulo 10 is zero, the original number is valid according to the Luhn algorithm.
 
 ## How it works
 
 `IntStream.iterate()` generates an endless (but lazily calculated) stream of numbers `1,2,1,2,1,2, ...` – 
 this is the factor we'll multiply each digit with.
 
 In the Java 8 example, we wrap the number in a `StringBuilder` since this class offers a `reverse()` method. 
 This way we can walk through the `chars()` stream of digits forward:
 
 The first `map()` converts each digit char to its number value. 
 The second `map()` multiplies this number value with the next factor value from the infinite `IntStream`
 `reduce()` calculates the square sum for all of these numbers (even for the ones with only one digit). 

Meanwhile, Java 9 introduced `takeWhile()`. 
We can use this stream operation to get rid of the `StringBuilder` and the reversing of the characters. 
Instead, we create a second infinite stream from the number parameter and cut the stream with a suitable lambda predicate.

The `chars()` stream from the Java 8 example stopped after the last character of the parameter string. 
In the Java 9 example, `LongStream.iterate()` divides the parameter by 10 endlessly, e.g.

```
8763, 876, 87, 8, 0, 0, 0, ...
```
We have to consider the stream's values only as long as they are greater than zero. 
`takeWhile()` does exactly this and yields the following finite stream:

```
8763, 876, 87, 8
```

Now we map() this stream to a stream of its rightmost digits (mod 10):

```
3, 6, 7, 8
```

The final operations (doubling of each second digit and adding the square sums) are the same as in the Java 8 example.

## Required Software

Tested using:

- Java 9
- Gradle 4.0
