# coding-challenge

Write a function that accepts 6 single digit arguments

Function output is a earliest time possible with the given 6 digits in time format as a string.

In case valid time cannot be assembled with the given 6 digits, return "NOT POSSIBLE"

The efficiency of the solution is not going to be considered as important point
so for this question it is ok to have high time/space complexity solution.

e.g.

solution(1, 2, 3, 4, 5, 6);
=>"12:34:56"

solution(8, 2, 4, 1, 2, 6);
=> "12:26:48"

solution(2, 3, 3, 2, 3, 4);
=> "22:33:34"

solution(0, 0, 0, 0, 0, 0);
=> "00:00:00"

solution(8, 8, 1, 3, 5, 8);
=> "18:38:58"

solution(8, 8, 8, 9, 9, 9);
=> "NOT POSSIBLE"
