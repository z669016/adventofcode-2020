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
Then I created a Walker class to walk through the forest and count trees given a certain slope.
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
had several puzzles based on the idea of a programmable device). I created some classes for an '''Instruction''' 
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
When the sum of selected numbers add up to the provided invalid number, create a list from the selected number, sort 
them in natural order, and return the sum of the first, and the last number in the list.

## Day 10
Started with an '''Adapter''' class, which can '''stack''' on top of another Adapter instance (ensuring the distance
between the two in jolts > 0 and <= 3). An '''AdapterMatcher''' reads the input and creates a stack of adapters. For 
counting differences of 1 and 3, just walk down the chain. That will do for part 1.
For part two, a simple recursive function would be able to calculate the number of different solutions ... if it would 
not be that many adapters you're using. So, a smarter approach is required, for instance by removing all adapters from 
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
'''Ship''' (the ship implements '''Consumer<CourseDirective>'''). Finally, the Manhattan distance can be calculated from 
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
Created a simple '''Instruction''' class and a '''Compiler''' to translate the puzzle input into instructions. The 
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

## Day 16
Again not too difficult, but it did require some work. I started with a '''Ticket''' class, a '''TicketFieldValidator''' 
class, and a '''PuzzleInput''' class. The '''PuzzleInput''' contains methods to extract from the puzzle input a list of 
ticket validators, a list of nearby tickets or just my ticked.
Part 1 means, stream all nearby tickets, stream their field-values, filter all fields that don't match any field
validator and sum the filtered values.
For part 2, I added a '''TicketValidator''' class that can validate a single ticket (a valid ticket contains only 
fields that match one or more field-validators). It also contains a '''fieldNames(List<Ticket> tickets)''' method that
determines the field-names. This method first creates a '''Set<String>''' for each field with a list of the field
validator names that are valid for that field. Then the array of sets is reduced by continuously removing fields from 
the possible names set, which occur only once in the array. That leaves an array of field-names. That array is used to 
filter the "departure" fields from my ticket required to calculate the answer. I made a mistake by using an '''int''' 
here as the multiplied value appeared too big, so I had to use a '''long'''.

## Day 17
Based on the Point3D class in my library, I created a Grid3D class. Not backed by a 3-dimensional array, but with a 
'''Map<Point3D,Character>''' to maintain the grid state. The '''Grid3D.evolve()''' method, creates a new instance of the grid
after extending it 1 point in to all directions (x, y, z). The Point3D contains a convenience method 
'''Point3D.directions()''' (returns a list of all points 1 position from the origin) and '''Point3D.adjacent()''' 
(return a list of directions  added to the current point) to get all neighbours of a point. The 
'''Grid3D.countNeighbours(Point3D,char)''' returns the number of adjacent points with a specific state. This will solve
part1. For part 2, I decided to clone and adjust Point3D into a Point4D amd Grid3D into Grid4D (just added the fourth 
dimension). Not the most beautiful approach, but it works and was straight forward. 

## Day 18
Started with an '''Operand''' (extends '''Supplier<Logn>'''), '''Operator''' (implements 
'''BiFunction<Long,Long,Long>'''), '''Expression''' (extends '''Operand''') and '''Value''' (extends '''Operand''')
classes, and a '''PlusOperator''', and '''TimesOperator''' (extending '''Operator'''). These classes together should 
be able to do the math. An '''ExpressionBuilder''' creates an '''Expression''' instance for each line in the puzzle 
input, using the '''Tokenizer''' to parse a line and return tokens for the symbols (OPEN, CLOSE, VALUE, OPERATOR).
The catch in the expression builder is to create the expression in the right order to ensure it evaluates from left to 
right, and not the other way around. This approach solved part 1.
For part 2 an '''ExpressionBuilderPlusPrecedence''' (extending '''ExpressionBuilder''') is used, that creates 
expressions for plus expressions (basically, it puts a plus expression between parentheses). Now the only difference 
between part 1 and part 2 is the expression builder used.

## Day 19
A nice puzzle today. The base classes '''Rule''' (implementing the '''Validator<String>''' interface) and its subclasses
'''ValueRule''', '''ListRule''', and '''ChoiceRule''' are the basis for solving the puzzle. The base class contains the 
factory method, and is used by the '''Rules''' class to create a map of all rules (useful for selecting a rule to 
evaluate). The helper class '''PuzzleInput''' again takes care of splitting the input file into rules and messages to 
be evaluated. A bit of work, but straight forward for part 1.
Part two requires some hacking on the design, although you should be able to solve it generically as well. I created 
specific instances of '''Rule8''', '''Rule11''', and '''Rule0''', that know their definition and use the length of 
their values and assume the length of a rule 42 value, and a rule 31 value are equal.

'''Rule11''' validation simply takes the centered sequence to be validated (length of a normal rule 11 value, which is 
a rule 42 value plus a rule 31 value). If that center matches the 'old' rule 11, it removes the center, and repeats the 
test from the start, until a tes fails (then the text doesn't match rule 11) or the text is empty (than it apparently 
matches all 'old' rule 11 checks).  

'''Rule0''' checks on the list of rule 8 and rule 11, by repeatedly splitting the text to be checked in a rule8 part,
and a rule 11 part. On the first iteration, the rule 8 part is 1 rule 42 value long, the second time 2, and so on, 
until the match was found on both parts.

It worked on part 2, but I won't say is well-designed.

## Day 20
Well, well, well ... a real jigsaw puzzle to solve ... I started with a '''Tile''' class to handle puzzle input and
match tile borders. Then I created a '''Puzzler''' class that would solve the puzzle just as you normally would.

The '''Puzzler''' class starts with creating a map with, for each tile, a list with possible neighbours. This 
immediately solves part 1, for all tiles with only two neighbours will be corners.

Part 2 is solved step by step ... first create the Tile grid that matches. Pick a corner, flip/rotate until it's on the 
top/left corner (i.e. one of the neighbours is below, and the other is at the right). From there. place all other 
pieces and flip/rotate where required. Then create an image from the Tile grid (remove the borders and glue the grid 
together), and flip/rotate until you've been able to count sea monsters. Then paint the sea monsters (replace sea 
monster '#' positions have an 'O'), and count the remaining '#' in the painted image.

All in all, it requires quite some code, but it works!

A possible faster solution would have been to first create a list wil all tiles flipped/rotated in all possible ways 
(which means 12 version of each tile). Then you could brute-force solve the puzzle by iterating over the list, placing 
a first tile on the top-left and trying to add tiles at the right and below... maybe I'll rewrite my solution.  

## Day 21
This is a puzzle for deduction. First create an overview with possible foods per possible allergen. From that, create 
a list of possible allergens per ingredient. This overview will contain allergens that are contained only in one 
ingredient. Then iterate over the sets, and when there are multiple ingredients that could contain an allergen, remove 
the ingredients that are specifically linked to one allergen. This basically solves part 2, and it enables you to compile
a list of ingredients that cannot contain an allergen. Solving part 1 is simply counting occurrences.

# Day 22
Not so difficult today ... A '''Player''' class represents a player, holds the cards for a player, and calculates the 
score. The '''Combat''' class takes two lists of card numbers and creates two players. The '''play()''' method runs the 
game by taking one card from each player, and follow the rules on winning/loosing. Straight forward and rather simple.

Part two requires a little bit more effort. I created a '''RecursiveCombat''' (that inherits from '''Combat''') which 
keeps a history (as a '''Set<String>''') and implements different rules in the '''play()''' method. There was a catch 
in the stop-condition for the recursion. I initially implemented it as "if either player one or player two, gets a set 
of cards that was in the game before, then stop", but it should have been "if player 1 and player 2, at the same time, 
have a set of cards they have had before, then stop" (I need to admit, as the rule is to prevent infinity, the second 
interpretation makes more sense). 

## Day 23
I've seen something similar once before ... using a List (either ArrayList or LinkedList) won't do it for you, as part 
two of this assignment will be something with a much bigger list and way more iterations. The solution is to use an 
array that contains the references for the next element in the list. On each iteration you need to update at most three 
values of the array (current needs to reference a new next value; the destination needs to reference the first element 
of the moved sublist; the last element of the moved sublist needs to reference the original value of the destination).

Getting a value of an element or an index only requires some searching for the first nine values (the puzzle init). For
the rest of the array, the index and the value are related to the array position.

## Day 24
A grid of hexagonal figures can be represented using a normal coordination system, if you abby to some specific rules:
* On rows with an even Y value, only use even X values like (0,0), (-2,0), and (4,0) 
* On rows with an uneven Y value, only use uneven X values like (1,1), (-1,-3), and (3,3)
* To move left or right on the same row (same Y value) increase (or decrease) X with 2
* to move up or down increase (or decrease) Y and X, i.e. from (0,0) to (1,1) means up right, and (-1,-1) is down left

Using this coordination system, I could reuse the '''Point''' class to navigate the tiles ('''HexagonalDirection'''):
* EAST (e) -> Point.add(2, 0)
* WEST (w) -> Point.add(-2, 0)
* NORTH EAST (ne) -> Point.add(1, 1)
* NORTH WEST (nw) -> Point.add(-1, 1)
* SOUTH EAST (se) -> Point.add(1, -1)
* SOUTH WEST (sw) -> Point.add(11, -1)

For part one, just walk the routes ('''TileVisitor'''), starting at '''Point.ORIGIN''' and flip the tile at the end of
the route. While walking the route add all the tiles on the route to a set. At the end, count the black tiles in 
the set.

For part two, I created a '''TileArt''' class with a '''next()''' method, to simulate a next day. The trick is, to 
ensure there is a complete ring of tiles surrounding any black tiles, before you do the transformation, as these tiles 
could flip as well during transformation (as they connect to at least one black one). So indeed, the figure grows during
the transformations.  

## Day 25
As usual a simple and straight forward puzzle, ad we all should enjoy Christmas today...