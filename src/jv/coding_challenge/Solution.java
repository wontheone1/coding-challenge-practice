package jv.coding_challenge;

/**
 * Created by wseobseo on 15/12/2017.
 */
public class Solution {

    public static int solution(int N) {
        // write your code in Java SE 8
        System.out.println(Integer.toBinaryString(N));
        char[] binCharArr = Integer.toBinaryString(N).toCharArray();
        int longestBinGap = 0; int currentBinGap = 0;

        for (char c : binCharArr) {
            if (c == '1') {
                if (currentBinGap > longestBinGap) {
                    longestBinGap = currentBinGap;
                }
                currentBinGap = 0;
            } else {
                currentBinGap++;
            }
        }
        return longestBinGap;
    }
}

/*
n=6=110_2 and n=328=101001000_2
n=5=101_2, n=16=2**4 and n=1024=2**10
n=1162=10010001010_2 and n=5=101_2
n=51712=110010100000000_2 and n=20=10100_2
n=66561=10000010000000001_2
n=6291457=11000000000000000000001_2
n=805306373=110000000000000000000000000101_2
n=1610612737=1100000000000000000000000000001_2
 */
