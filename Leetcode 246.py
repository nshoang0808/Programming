class Solution(object):
    def nthUglyNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        i2 = 1
        i3 = 1
        i5 = 1
        uglies = [0 , 1]
        for i in range(2, n+1):
            ft_2 = uglies[i2] *2
            ft_3 = uglies[i3] *3
            ft_5 = uglies[i5] *5
            x = 0
            if ft_2 > ft_3:
                if ft_3 > ft_5:
                    x = ft_5
                    i5 = i5 + 1
                else:
                    x = ft_3
                    i3 = i3 + 1
            elif ft_2 < ft_5:
                x = ft_2
                i2 = i2 + 1
            else:
                x = ft_5
                i5 = i5 + 1
            uglies.append(x)
            print x
        return uglies[n]