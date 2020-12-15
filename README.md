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
normally instead of being terminated due to a repeated instruction.

## Day 9
Not too difficult today ... The XMAS class contains a method '''isValid''' that checks the validity of the value at a
given offset of the numbers list. The XMAS class is constructed with a preamble size to make it generic.
To find if two numbers in the preamble sum to the value, I simply used two nested loops, where the outer loop start at 
the given offset - preamble-size, and the inner loop starts at the offset of the outer loop + 1. To solve part 1, 
simply loop over the numbers and break the loop as soon as '''isValid(numbers, offset)''' returns '''false'''.

Part 2 has been solved using recursion, using a '''boolean[]''' to keep track of the selected numbers. The recursive 
search should stop as soon as the sum of the selected values is equal to or greater than the provided invalid number. 
When the sum of selected numbers add up to the provided invalid number, create a list fro the selected number, sort 
them in natural order, and return the sum of the first, and the last number in the list.

## Day 10
Started with an '''Adapter''' class, which can '''stack''' on top of another Adapter instance (ensuring the distance
between the two in jolts > 0 and <= 3). An '''AdapterMatcher''' reads the input and creates a stack of adapters. For 
counting differences of 1 and 3, just walk down the chain. That will do for part 1.
For part two, a simple recursive function would be able to calculate the number of different solutions ... if it would 
not be that much adapters you're using. So, a smarter approach is required, for instance by removing all adapters from 
the stack that cannot be removed anyway, and split the list into sublist which contain alternatives (so split into 
continuous sequences of adapters that enclose adapters that might be removed). 

For example, the list [(0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)], can be split into [[4, 5, 6, 7], 
[10, 11, 12]]. Then simply calculate the number of combinations of each individual sublist and multiply all calculated 
values. This approach really solves the issue in only a fraction of a second.  

## Day 11
Created a '''Seats''' class, which uses a '''Grid''' for the representation of the waiting area. A '''next(...)''' 
method created a new '''Seats''' instance with updated seats, based on the number of taken adjacent seats. The
'''GridUtils''' contains methods to count taken seats in the '''Grid''' and methods to check for equality.
For part 1, keep calling next, until te new instance equals te current one. For part 2, I updated the '''next(...)'''
method, to accept a strategy (lambda) that returns the number of taken seats, and I added an '''occupiedAround(...)''' 
(for part 1) and '''occupiedInSight(...)''' (for part 2) methods as strategy. For part 2, just run the algorithm using 
the second strategy, and you're done.

## Day 12
By reusing the Point utility class, this day is quite simple. I created a '''Ship''' with a location ('''Point''')
and a direction (in degrees). The puzzle input translates into a '''List<CourseDirective>''' that is fed to the 
'''Ship''' (the ship implements '''Consumer<CourseDirective>''''. Finaly the Manhatten distance can be calculated from 
the ship's location relative to '''Point.ORIGIN'''.
For part two, I created a '''WayPoint''' that consumes the course directives to update itself. Any FORWARD directive
if handled by the '''Ship''' by adding the '''WayPoint''' to the ship's location for the specified number of times.

## Day 13
Interesting challenge today ... part one is quite straight forward. I used '''org.javatuples.Pair'''' to create pairs 
of bus-id and waiting time, and then searched for the bus with the minimum waiting time.
The second challenge of the day (part 2) was really challenging to me. The initial algorithm, able to solve the
sample exercises of course wasn't able to crack the problem using the puzzle input. The numbers were too high, and
the algorithm just needed much time (and I didn't want to wait). The initial idea was to take steps equal tho the 
highest bus id. Then I realized, that as soon as the second valid bus-id has been found, the step size could be 
adjusted to the Least Common Multiple of the current step size, and the newly found bus-id. This increased step size
each time I found a next match, and the algorithm was blazingly fast. Beware, don't update the step sie immediately, 
for you might jump over the next valid value.

## Day 14
Created a simple '''Instruction''' class and a '''Compiler''' to translate the puzzle imput into instructions. The 
instructions operate on a '''Memory''' interface. A '''MemoryDecoder''' implements the '''Memory''' interface, and uses
a '''HashMap<Long,Long>''' as storage for the values (I started with an array, but that didn't work for part2).
The '''Memory.set(long offset, long value)''' takes care of the masking of the values using simple bit operations 
before storing them.
For part 2, I implemented a '''MemoryDecoderV2''' with a set implementation that first creates a '''List<Long>''' of 
addresses to be changed, after which it stores the provided value at all addresses in the list.

## Day 15
Today a straight forward puzzle. I created a '''Numbers''' class that implements the '''Supplier<Long>''' interface. I 
choose to work with long values, as I expect part 2 will be about big numbers.It also means that a '''List<Long>''' is
probably not a proper way to capture historical info. So '''Numbers''' uses '''Map<Long>''' to record historical info.  