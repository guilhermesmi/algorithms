package algorithms.series

class LucasSeriesGroovyed {

    public int l(int n){
        Closure lucas
       lucas = { int i ->
           assert (i >= 0);
           i == 0 ? 2 : i == 1 ? 1 : lucas(i - 2) + lucas(i - 1)
       }.memoize()
       lucas(n)
    }
}
