package Q2;

public class CountNumberOfWaterballs extends Template<Integer> {

    @Override
    protected Integer getDefaultT() {
        return 0;
    }

    @Override
    protected Integer updateSearchResult(Integer result, String message, int index) {
        if ("Waterball".equals(message)) result++;
        return result;
    }
}