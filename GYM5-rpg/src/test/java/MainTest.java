import com.ted.app.RPG;
import com.ted.app.Troop;
import com.ted.app.role.Hero;
import com.ted.app.role.Role;
import com.ted.app.role.ai.AI;
import com.ted.app.strategy.action.skill.*;
import com.ted.app.strategy.aiChoose.ChooseBySeed;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testBattleScenario() throws Exception {
        // 讀取 input.txt
        File inputFile = new File("src/test/resources/waterball-and-fireball-1v2.in");
        File expectedFile = new File("src/test/resources/waterball-and-fireball-1v2.out");

        // 建立 RPG 遊戲
        RPG rpg = new RPG();
        // 註：您的 loadTroopsAndInjectInput 方法同時設定了 System.in
        loadTroopsAndInjectInput(inputFile.toPath(), rpg);
        rpg.setHero(rpg.getTroops().get(0).getRoles().get(0));
        rpg.setTroop2(rpg.getTroops().get(1));

        // 儲存原始的 System.out 和 System.in
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 建立新的 PrintStream，將輸出導向 outputStream，設置 auto-flush 為 true
        PrintStream newPrintStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
        System.setOut(newPrintStream);

        // 💡 原始的 System.in 已經在 loadTroopsAndInjectInput 中被替換，
        //    通常測試結束後應該恢復，但由於測試是獨立運行的，這裡先忽略恢復 System.in。

        try {
            // 開始遊戲
            rpg.start();
        } finally {
            // 步驟 1: 強制 flush 緩衝區，確保所有資料寫入 outputStream
            // 呼叫 System.out.flush() 實際上是呼叫 newPrintStream.flush()
            System.out.flush();

            // 步驟 2: (可選) 確保 outputStream 自身是完整的。
            outputStream.flush();

            // 步驟 3: 恢復原始的 System.out
            System.setOut(originalOut);

            // 步驟 4: 關閉我們建立的新 PrintStream
            newPrintStream.close();
        }

        // 取得輸出
        // 使用 outputStream.toString(StandardCharsets.UTF_8) 更明確指定編碼
        String actualOutput = outputStream.toString(StandardCharsets.UTF_8).trim();
        System.out.println();
        // 請確保您的 RPG.start() 方法有正確輸出內容到 System.out

        // 讀取 expected.txt
        String expectedOutput = Files.readString(expectedFile.toPath(), StandardCharsets.UTF_8).trim();

        String normalizedActual = actualOutput.replace("\r\n", "\n").trim();
        String normalizedExpected = expectedOutput.replace("\r\n", "\n").trim();
        assertEquals(normalizedExpected, normalizedActual);;
    }

    public void loadTroopsAndInjectInput(Path file, RPG rpg) throws Exception {
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
                    Skill skill = switch (parts[i]) {
                        case "水球" -> new WaterBall();
                        case "火球" -> new FireBall();
                        case "自我治療" -> new SelfHealing();
                        case "石化" -> new Petrochemical();
                        case "下毒" -> new Poison();
                        case "召喚" -> new Summon();
                        case "自爆" -> new SelfExplosion();
                        case "鼓舞" -> new CheerUp();
                        case "詛咒" -> new Curse();
                        case "一拳攻擊" -> new OnePunch();
                        default -> null;
                    };
                    if (skill != null) skills.add(skill);
                }

                Role role;
                if (name.equals("英雄")) {
                    role = new Hero(hp, name, mp, rpg, str, skills, currentTroop);
                } else {
                    role = new AI(hp, name, mp, rpg, str, skills, currentTroop, new ChooseBySeed());
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
        System.setIn(new ByteArrayInputStream(injected.getBytes(StandardCharsets.UTF_8)));
    }
}