package com.ted.app;

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
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

class TestcaseRunnerTest {

    private static final Path TESTCASE_DIR = Path.of("src", "test", "resources", "testcases");
    private static final int MAX_DIFF_LINES_PER_CASE = 20;

    @Test
    void runGoldenTestcases() throws IOException {
        List<String> names = testcaseNames();
        List<String> mismatchReports = new ArrayList<>();
        for (String testcaseName : names) {
            String input = Files.readString(TESTCASE_DIR.resolve(testcaseName + ".in"), StandardCharsets.UTF_8);
            String expectedOutput = normalize(
                    Files.readString(TESTCASE_DIR.resolve(testcaseName + ".out"), StandardCharsets.UTF_8));
            String actualOutput = normalize(runSystem(input));

            String diff = buildDiffReport(expectedOutput, actualOutput, MAX_DIFF_LINES_PER_CASE);
            if (!diff.isEmpty()) {
                mismatchReports.add("=== " + testcaseName + " ===" + System.lineSeparator() + diff);
            }
        }

        int total = names.size();
        int failed = mismatchReports.size();
        int passed = total - failed;
        String summary = "Golden testcase summary: total=" + total + ", passed=" + passed + ", failed=" + failed;

        if (!mismatchReports.isEmpty()) {
            String lineSeparator = System.lineSeparator();
            String report = String.join(lineSeparator + lineSeparator, mismatchReports);
            System.err.println(summary);
            System.err.println("Golden testcase mismatches:" + lineSeparator + report);
            return;
        }

        System.out.println(summary);
    }

    private static List<String> testcaseNames() throws IOException {
        try (Stream<Path> paths = Files.list(TESTCASE_DIR)) {
            return paths
                    .filter(path -> path.getFileName().toString().endsWith(".in"))
                    .map(path -> path.getFileName().toString().replace(".in", ""))
                    .sorted()
                    .toList();
        }
    }

    private static String runSystem(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(inputStream);
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

            SceneManager manager = new SceneManager();
            new AppContext(
                new Scanner(System.in, StandardCharsets.UTF_8),
                manager,
                new WordRepository(),
                new LocalJsonDictionaryAdapter(new LocalJsonDictionary(
                        Path.of("src", "main", "resources", "dictionary.json"))),
                new Random(0)
            );
            manager.register(SceneId.HOME, new HomeSceneFactory());
            manager.register(SceneId.MANAGE, new ManageWordsSceneFactory());
            manager.register(SceneId.SEARCH, new SearchWordsSceneFactory());
            manager.register(SceneId.SEARCH_ADD_CONFIRM, new SearchAddConfirmSceneFactory());
            manager.register(SceneId.ADD_WORDS, new AddWordsSceneFactory());
            manager.register(SceneId.DELETE_WORDS, new DeleteWordsSceneFactory());
            manager.register(SceneId.REVIEW, new ReviewWordsSceneFactory());
            manager.register(SceneId.QUESTION, new QuestionSceneFactory());
            manager.registerDefaultMenuOption(DefaultMenuOptionKey.PREVIOUS_PAGE, new PreviousPageDefaultMenuOptionFactory());
            manager.registerDefaultMenuOption(DefaultMenuOptionKey.EXIT, new ExitDefaultMenuOptionFactory());
            manager.link(SceneId.HOME, SceneId.MANAGE);
            manager.link(SceneId.HOME, SceneId.REVIEW);
            manager.link(SceneId.MANAGE, SceneId.SEARCH);
            manager.link(SceneId.MANAGE, SceneId.ADD_WORDS);
            manager.link(SceneId.MANAGE, SceneId.DELETE_WORDS);

            new VocabularyLearningSystem().start(manager);
            return outputStream.toString(StandardCharsets.UTF_8);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    private static String normalize(String text) {
        String lf = text.replace("\r\n", "\n").replace("\r", "\n");
        String[] lines = lf.split("\n", -1);
        StringBuilder normalized = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i > 0) {
                normalized.append('\n');
            }
            normalized.append(lines[i].stripTrailing());
        }
        return normalized.toString().stripTrailing();
    }

    private static String buildDiffReport(String expectedOutput, String actualOutput, int maxLines) {
        String[] expectedLines = expectedOutput.split("\n", -1);
        String[] actualLines = actualOutput.split("\n", -1);
        int max = Math.max(expectedLines.length, actualLines.length);

        StringBuilder report = new StringBuilder();
        int mismatchCount = 0;
        for (int i = 0; i < max; i++) {
            String expected = i < expectedLines.length ? expectedLines[i] : "<EOF>";
            String actual = i < actualLines.length ? actualLines[i] : "<EOF>";
            if (expected.equals(actual)) {
                continue;
            }
            mismatchCount++;
            if (mismatchCount <= maxLines) {
                report.append("line ").append(i + 1).append(System.lineSeparator())
                        .append("  expected: ").append(expected).append(System.lineSeparator())
                        .append("  actual  : ").append(actual).append(System.lineSeparator());
            }
        }

        if (mismatchCount == 0) {
            return "";
        }
        if (mismatchCount > maxLines) {
            report.append("... ")
                    .append(mismatchCount - maxLines)
                    .append(" more mismatched line(s) omitted");
        }
        return report.toString().stripTrailing();
    }
}
