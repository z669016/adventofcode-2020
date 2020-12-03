# Advent of code 2020

## Convenience methods and classes
I have a small library with some convenience methods used for other AoC exercises. Like the ```ResourceLines``` class 
to read a resource file and transform the content into a ```List<String>```, or the CSV class to read a resource 
file containing comma separated values and returning a List of these values, optionally after transformation from 
```String``` to ```Integer```.

Also uses the algorithms library, which contains generic classes for addressing classic compute problems (from the book 
**Classic Computer Science Problems In Java** (c) Manning.com - 2020) 

It was never my intention to create the shortest program possible. I did try to create clear and simple implementations.

## Day 1
Add all values to a set, and iterate over it. If (2020 - iterated-value) is in the set, then you have found your pair.
Works similar with three values (double loop).

## Day 2
Created a Password class to extract a password from a line of the puzzle input. Created a PasswordPolicy interface to 
validate password policy and two implementations of that interface with a static factory method. Found the answer by 
streaming the input, filtering the valid passwords according to the policy, and counting the stream elements.

## Day 3
Started with a class called TreeArea, that is build from the map (puzzle input). The TreeArea has a member called 
'''grid''' that holds the map and can be used to find if there is a tree at a specific location. Replication of the map
isn't needed, for the map is repetitive so, by using '''x % grid[0].length''' you can get the location of any tree.
Then I created a Walker class to walk though the forest and count trees given a certain slope.
This is all you need for part 1. For part 2, just use a list of slopes, map slopes into tree-counts and reduce the 
stream of long values.

