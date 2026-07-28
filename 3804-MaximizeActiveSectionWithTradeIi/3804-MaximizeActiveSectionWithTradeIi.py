# Last updated: 28/07/2026, 20:22:12
class Solution:
    def maxActiveSectionsAfterTrade(self, s: str, queries: List[List[int]]) -> List[int]:
        import bisect

class Solution:
    def maxActiveSectionsAfterTrade(self, s: str, queries: list[list[int]]) -> list[int]:
        n = len(s)
        ones_full = s.count('1')
        
        # Parse zero groups: list of [start, end, length]
        zero_groups = []
        i = 0
        while i < n:
            if s[i] == '0':
                start = i
                while i < n and s[i] == '0':
                    i += 1
                end = i - 1
                zero_groups.append([start, end, end - start + 1])
            else:
                i += 1
        
        k = len(zero_groups)
        
        # zeroGroupIndex[p] = index of zero group containing p, or -1
        zeroGroupIndex = [-1] * n
        for idx, zg in enumerate(zero_groups):
            for p in range(zg[0], zg[1] + 1):
                zeroGroupIndex[p] = idx
        
        # zeroMergeLengths[i] = Z_i.length + Z_{i+1}.length
        zm = max(0, k - 1)
        zeroMergeLengths = []
        for i in range(zm):
            zeroMergeLengths.append(zero_groups[i][2] + zero_groups[i + 1][2])
        
        # Sparse table for range max query
        if zm > 0:
            LOG = (zm).bit_length()
            st = [zeroMergeLengths[:]]
            for j in range(1, LOG):
                prev = st[j-1]
                step = 1 << (j - 1)
                curr = [max(prev[i], prev[i + step]) for i in range(zm - (1 << j) + 1)]
                st.append(curr)
            
            def query_st(l, r):
                if l > r or l < 0 or r >= zm:
                    return -10**18
                length = r - l + 1
                j = length.bit_length() - 1
                return max(st[j][l], st[j][r - (1 << j) + 1])
        else:
            def query_st(l, r):
                return -10**18
        
        z_starts = [zg[0] for zg in zero_groups]
        z_ends = [zg[1] for zg in zero_groups]
        
        answers = []
        for l, r in queries:
            max_gain = 0
            zl = zeroGroupIndex[l]
            zr = zeroGroupIndex[r]
            
            # Special case: l and r in adjacent zero groups
            if zl >= 0 and zr >= 0 and zl + 1 == zr:
                left_partial = zero_groups[zl][1] - l + 1
                right_partial = r - zero_groups[zr][0] + 1
                max_gain = max(max_gain, left_partial + right_partial)
            
            # Inner pairs: both zero groups fully in [l,r]
            first_inner = bisect.bisect_left(z_starts, l)
            last_inner = bisect.bisect_right(z_ends, r) - 2
            
            if first_inner <= last_inner and 0 <= first_inner < zm and last_inner >= 0:
                max_gain = max(max_gain, query_st(first_inner, min(last_inner, zm - 1)))
            
            # Left boundary: s[l]='0', pair with Z_i containing l
            if zl >= 0 and zl < zm and zero_groups[zl + 1][0] <= r:
                if not (zr >= 0 and zl + 1 == zr):  # not special case
                    left_c = zero_groups[zl][1] - l + 1
                    right_c = min(zero_groups[zl + 1][1], r) - zero_groups[zl + 1][0] + 1
                    max_gain = max(max_gain, left_c + right_c)
            
            # Right boundary: s[r]='0', pair with Z_{i+1} containing r
            if zr >= 0:
                i = zr - 1
                if 0 <= i < zm and zero_groups[i][1] >= l:
                    if not (zl >= 0 and zl + 1 == zr):  # not special case
                        left_c = zero_groups[i][1] - max(zero_groups[i][0], l) + 1
                        right_c = r - zero_groups[i + 1][0] + 1
                        max_gain = max(max_gain, left_c + right_c)
            
            answers.append(ones_full + max_gain)
        
        return answers