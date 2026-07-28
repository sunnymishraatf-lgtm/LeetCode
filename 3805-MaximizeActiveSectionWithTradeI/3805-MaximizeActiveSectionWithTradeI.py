# Last updated: 28/07/2026, 20:22:04
class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        n = len(s)
        ones = s.count('1')
        
        # Parse zero groups (contiguous '0's)
        zero_lengths = []
        i = 0
        while i < n:
            if s[i] == '0':
                length = 0
                while i < n and s[i] == '0':
                    length += 1
                    i += 1
                zero_lengths.append(length)
            else:
                i += 1
        
        # Find max sum of adjacent zero group lengths
        max_gain = 0
        for i in range(len(zero_lengths) - 1):
            max_gain = max(max_gain, zero_lengths[i] + zero_lengths[i + 1])
        
        return ones + max_gain