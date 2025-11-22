import com.ted.app.RPG;
import com.ted.app.Troop;
import com.ted.app.role.Hero;
import com.ted.app.role.Role;
import com.ted.app.role.ai.AI;
import com.ted.app.strategy.action.skill.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void cheerup() throws Exception {
        String inPath = "src/test/resources/cheerup.in";
        String outPath = "src/test/resources/cheerup.out";
        content(inPath, outPath);
    }

    @Test
    public void curse() throws Exception {
        String inPath = "src/test/resources/curse.in";
        String outPath = "src/test/resources/curse.out";
        content(inPath, outPath);
    }

    @Test
    public void onePunch() throws Exception {
        String inPath = "src/test/resources/one-punch.in";
        String outPath = "src/test/resources/one-punch.out";
        content(inPath, outPath);
    }

    @Test
    public void onlyBasicAttack() throws Exception {
        String inPath = "src/test/resources/only-basic-attack.in";
        String outPath = "src/test/resources/only-basic-attack.out";
        content(inPath, outPath);
    }

    @Test
    public void petrochemical() throws Exception {
        String inPath = "src/test/resources/petrochemical.in";
        String outPath = "src/test/resources/petrochemical.out";
        content(inPath, outPath);
    }

    @Test
    public void poison() throws Exception {
        String inPath = "src/test/resources/poison.in";
        String outPath = "src/test/resources/poison.out";
        content(inPath, outPath);
    }

    @Test
    public void selfExplosion() throws Exception {
        String inPath = "src/test/resources/self-explosion.in";
        String outPath = "src/test/resources/self-explosion.out";
        content(inPath, outPath);
    }

    @Test
    public void selfHealing() throws Exception {
        String inPath = "src/test/resources/self-healing.in";
        String outPath = "src/test/resources/self-healing.out";
        content(inPath, outPath);
    }

    @Test
    public void summon() throws Exception {
        String inPath = "src/test/resources/summon.in";
        String outPath = "src/test/resources/summon.out";
        content(inPath, outPath);
    }

    @Test
    public void waterball() throws Exception {
        String inPath = "src/test/resources/waterball-and-fireball-1v2.in";
        String outPath = "src/test/resources/waterball-and-fireball-1v2.out";
        content(inPath, outPath);
    }

    private void content(String inPath, String outPath) throws Exception {
        // 用於收集本次測試輸出
        final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();

        try {
            // 讀取輸入檔案並建立 RPG
            RPG rpg = new RPG();
            String injected = loadTroopsAndInjectInput(java.nio.file.Paths.get(inPath), rpg);

            // 建立模擬輸入流（每次測試都重新建立新的）
            ByteArrayInputStream fakeIn = new ByteArrayInputStream(injected.getBytes(StandardCharsets.UTF_8));
            System.setIn(fakeIn);

            // 建立模擬輸出流（注意：不要用 try-with-resources，避免被 close）
            PrintStream fakeOut = new PrintStream(outputBuffer, true, "UTF-8");
            System.setOut(fakeOut);

            // 設定角色
            rpg.setHero(rpg.getTroops().get(0).getRoles().get(0));
            rpg.setTroop2(rpg.getTroops().get(1));

            // 執行遊戲
            rpg.start();

            // flush 所有輸出
            fakeOut.flush();
            outputBuffer.flush();

        } finally {

        }

        // 取得實際輸出
        String actualOutput = new String(outputBuffer.toByteArray(), StandardCharsets.UTF_8).trim();

        // 讀取期望輸出
        String expectedOutput = new String(
                java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(outPath)),
                StandardCharsets.UTF_8
        ).trim();

        // 正規化換行符號
        String normalizedActual = actualOutput.replace("\r\n", "\n");
        String normalizedExpected = expectedOutput.replace("\r\n", "\n");

        // 驗證結果
        assertEquals(normalizedExpected, normalizedActual);
    }



    private String loadTroopsAndInjectInput(Path file, RPG rpg) throws Exception {
        // ... (此方法保持不變，因為邏輯正確)
        List<Troop> troops = new ArrayList<>();
        Troop currentTroop = null;
        List<String> userInputLines = new ArrayList<>();

        List<String> lines = Files.readAllLines(file);
        boolean inTroop = false;

        for (String raw : lines) {
            String line = raw.trim();

            if (line.startsWith("#軍隊-") && line.endsWith("-開始")) {
                String id = line.substring(4, line.length() - 3);
                currentTroop = new Troop(id, rpg);
                troops.add(currentTroop);
                inTroop = true;
                continue;
            }

            if (line.startsWith("#軍隊-") && line.endsWith("-結束")) {
                inTroop = false;
                currentTroop = null;
                continue;
            }

            if (inTroop) {
                // 解析角色
                String[] parts = line.split("\\s+");
                String name = parts[0];
                int hp = Integer.parseInt(parts[1]);
                int mp = Integer.parseInt(parts[2]);
                int str = Integer.parseInt(parts[3]);

                List<Skill> skills = new ArrayList<>();
                for (int i = 4; i < parts.length; i++) {
                    Skill skill = null;
                    switch (parts[i]) {
                        case "水球":
                            skill = new WaterBall();
                            break;
                        case "火球":
                            skill = new FireBall();
                            break;
                        case "自我治療" :
                            skill = new SelfHealing();
                            break;
                        case "石化":
                            skill = new Petrochemical();
                            break;
                        case "下毒" :
                            skill = new Poison();
                            break;
                        case "召喚":
                            skill = new Summon();
                            break;
                        case "自爆":
                            skill = new SelfExplosion();
                            break;
                        case "鼓舞":
                            skill = new CheerUp();
                            break;
                        case "詛咒":
                            skill = new Curse();
                            break;
                        case "一拳攻擊":
                            skill = new OnePunch();
                            break;
                    };
                    if (skill != null) skills.add(skill);
                }

                Role role;
                if (name.equals("英雄")) {
                    role = new Hero(hp, name, mp, rpg, str, skills, currentTroop);
                } else {
                    role = new AI(hp, name, mp, rpg, str, skills, currentTroop);
                }

                currentTroop.add(role);
            } else {
                if (!line.isEmpty()) {
                    userInputLines.add(line);
                }
            }
        }

        // 設定軍隊
        rpg.setTroops(troops);

        // 注入 System.in 作為遊戲輸入
        String injected = String.join("\n", userInputLines) + "\n";
        return injected;
    }
}