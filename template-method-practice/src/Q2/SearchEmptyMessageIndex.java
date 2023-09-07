package Q2;

public class SearchEmptyMessageIndex extends Template<Integer>{

    @Override
    protected Integer getDefaultT() {
        return 0;
    }

    @Override
    protected boolean isSearchEnd(Integer result) {
        return result > 0;
    }

    @Override
    protected Integer updateSearchResult(Integer result, String message, int index) {
        if(message.isEmpty()) result = index;
        return null;
    }
}
