package Q2;

public class SearchLongestMessage extends Template<String>{
    @Override
    protected String getDefaultT() {
        return "";
    }

    @Override
    protected String updateSearchResult(String result, String message, int index) {
        if(message.length() > result.length()) result = message;
        return result;
    }
}