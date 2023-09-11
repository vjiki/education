static int binpow(int a, int n) {
    int res = 1;
    while (n != 0) {
    if ((n & 1) > 0) {
    res *= a;
    }
    a *= a;
    n >>= 1;
    }
    return res;
    }