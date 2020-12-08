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

## Day 4
Created a Passport class with a '''fields()''' method, which returns a map of fields. This allows simple validation of 
completeness using a set later on. A Batch class translates the puzzle input in a list of passports. 
A PassportValidator checks for a passport if the key-set of the fields-map contains all required fields 
(using '''Set.containsAll(REQUIRED)'''). That's all required for part 1.
For part 2, an EnhancedPassportValidator is being used, which contains attribute specific validator methods, also 
listed in a map (using the attribute name as the key). This validator can loop over all keys i the field-map of a 
passport and use the appropriate validator-method to check the field-value.

## Day 5
It was pretty straight forward today. Created a BoardingPassDecoder (decodes the letter code into a Point which 
references a seat row and column) and a SeatIdDecoder (which decodes a Point into a seat ID). Using these it's simple
to create a Set<Point> of seats in use. With this you can solve part 1.
Given the set of used seats you can create a set of empty seats (Set<Point> with X,Y coordinates in the range
(0..7, 0..127) that are missing in the used seats set). Then browse the unused seats set, filter out all in the first 
and last row, check is prev and next id are in use, and pick the first available. I love streams...

## Day 6
I created a '''GroupAnswers''' class to hold the answer lines of each group (just a '''List<String>'''), with a method 
'''yesCount()''' to count number of the distinct letters in the group (part 1). Parsing the puzzle input is the hardest part 
in this and even that is straight forward. For part 2 an '''allYesses()''' method was added, that uses a 
'''Set<Character>''' to determine which letters are in all forms per group. The set size after processing all entries
of the group is the number of all yesses.

## Day 7
A '''Bags.loadBags(List<String> lines)''' method returns a '''Map<String,Bag>''' (puzzle input). The '''Bag''' class 
provides a '''contains(Bag bag)''' method which returns true if a bag (or any of its contained bags)
contains the specified bag. That's enough to solve part 1. Another method returns the number of bags inside the bag. 
The result is remembered as it probably takes too long to calculate it every time again. That method solves part 2.

## Day 8
It might be a bit of overkill, but maybe it pays off in one of the upcoming days (over the past years, each year 
had several puzzles based on the idea of a programmable device. I created some classes for an '''Instruction''' 
(abstract base class), a '''Compiler''', and a '''Processor''' (an interface with an instruction pointer and an 
accumulator), and build a '''HandHeldGameConsole'''.

The '''Processor''' can log it's state and executing instructions, and can terminate (configurable) when an instruction
is about to execute for a second time. These are the required ingredients for part 1.

For part 2, just loop over the program and replace the next nop or jmp instruction, until the program terminates 
normally instead of being terminated due to an repeated instruction.
