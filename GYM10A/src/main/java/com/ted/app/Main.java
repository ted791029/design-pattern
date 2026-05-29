package com.ted.app;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

import com.ted.app.core.AppContext;
import com.ted.app.core.DefaultMenuOptionKey;
import com.ted.app.core.SceneManager;
import com.ted.app.core.SceneId;
import com.ted.app.core.VocabularyLearningSystem;
import com.ted.app.core.WordRepository;
import com.ted.app.core.factories.ExitDefaultMenuOptionFactory;
import com.ted.app.core.factories.HomeSceneFactory;
import com.ted.app.core.factories.PreviousPageDefaultMenuOptionFactory;
import com.ted.app.managewords.LocalJsonDictionary;
import com.ted.app.managewords.LocalJsonDictionaryAdapter;
import com.ted.app.managewords.factories.AddWordsSceneFactory;
import com.ted.app.managewords.factories.DeleteWordsSceneFactory;
import com.ted.app.managewords.factories.ManageWordsSceneFactory;
import com.ted.app.managewords.factories.SearchAddConfirmSceneFactory;
import com.ted.app.managewords.factories.SearchWordsSceneFactory;
import com.ted.app.reviewwords.factories.QuestionSceneFactory;
import com.ted.app.reviewwords.factories.ReviewWordsSceneFactory;

public class Main {
        public static void main(String[] args) {
                SceneManager manager = new SceneManager();
                new AppContext(
                        new Scanner(System.in, StandardCharsets.UTF_8),
                        manager,
                        new WordRepository(),
                        new LocalJsonDictionaryAdapter(new LocalJsonDictionary(
                                        Path.of("src", "main", "resources", "dictionary.json"))),
                        new Random()
                );
                
                manager.register(SceneId.HOME, new HomeSceneFactory());
                manager.register(SceneId.MANAGE, new ManageWordsSceneFactory());
                manager.register(SceneId.SEARCH, new SearchWordsSceneFactory());
                manager.register(SceneId.SEARCH_ADD_CONFIRM, new SearchAddConfirmSceneFactory());
                manager.register(SceneId.ADD_WORDS, new AddWordsSceneFactory());
                manager.register(SceneId.DELETE_WORDS, new DeleteWordsSceneFactory());
                manager.register(SceneId.REVIEW, new ReviewWordsSceneFactory());
                manager.register(SceneId.QUESTION, new QuestionSceneFactory());

                manager.link(SceneId.HOME, SceneId.MANAGE);
                manager.link(SceneId.HOME, SceneId.REVIEW);
                manager.link(SceneId.MANAGE, SceneId.SEARCH);
                manager.link(SceneId.MANAGE, SceneId.ADD_WORDS);
                manager.link(SceneId.MANAGE, SceneId.DELETE_WORDS);
                
                manager.registerDefaultMenuOption(DefaultMenuOptionKey.PREVIOUS_PAGE, new PreviousPageDefaultMenuOptionFactory());
                manager.registerDefaultMenuOption(DefaultMenuOptionKey.EXIT, new ExitDefaultMenuOptionFactory());

                new VocabularyLearningSystem().start(manager);
        }
}
