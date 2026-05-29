package com.ted.app.managewords;

import com.ted.app.core.DictionaryQueryTechnique;
import com.ted.app.core.Word;

public class LocalJsonDictionaryAdapter implements DictionaryQueryTechnique {

    private LocalJsonDictionary localJsonDictionary;

    public LocalJsonDictionary getLocalJsonDictionary() {
        return localJsonDictionary;
    }

    public LocalJsonDictionaryAdapter(LocalJsonDictionary localJsonDictionary) {
            this.localJsonDictionary = localJsonDictionary;
        }

    @Override
        public Word queryWord(String name) {
            return localJsonDictionary.queryWord(name);
        }

    public void setLocalJsonDictionary(LocalJsonDictionary localJsonDictionary) {
        this.localJsonDictionary = localJsonDictionary;
    }
}
