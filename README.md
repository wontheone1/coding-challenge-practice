# coding-challenge

Write a function:
class Solution { public int solution(int[] A); }
that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
Given A = [1, 2, 3], the function should return 4.
Given A = [−1, −3], the function should return 1.
Assume that:
N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
Complexity:
expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

solution
make an integer sequence array of size N (0, n +1)  n+2
i.e. [0,1,2,3,4,5,6,7]  O(N)
=>  [0,0,0,0,0,5,0,7] 
while iterating input array set the element of integer sequence array to 0 when it occurs from input array. O(N)
Return smallest non-zero element in the end O(N)

Total time complexity
3O(N)
= O(N)
