package Q2;

public abstract class Template<T> {
    public T search(String[] messages) {
        T result = getDefaultT();
        for (int index = 0; index < messages.length; index++) {
            result = updateSearchResult(result, messages[index], index);
            if(isSearchEnd(result)){
                break;
            }
        }
        return result;
    }

    protected T getDefaultT() {
        return null;
    }

    protected boolean isSearchEnd(T result) {
        return false;
    }

    protected abstract T updateSearchResult(T result, String message, int index);
}
