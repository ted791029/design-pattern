# `SRS.txt` 物件導向分析（依 `SKILL.md` SOP）

## 第一步：逐句拆解需求（關鍵句）

| 句子編號 | 需求句（摘要） |
|---|---|
| S1 | 系統是單字學習系統，支援管理、查詢、批次新增、批次刪除、複習。 |
| S2 | `Word` 具備 `Name`、`Description`、`Definitions`；`Definition` 由 `PoS + explanation` 組成。 |
| S3 | CLI 畫面由 `Breadcrumbs`、`Messenger`、`Menu`、`Prompt` 組成（`Messenger` 可選）。 |
| S4 | 指令不分大小寫；`/ESC` 可全域退出；部分場景有 `/B` 返回上一頁。 |
| S5 | `Search words`：輸入單字後查詢，成功可加入 repository，失敗提示找不到。 |
| S6 | `Add new words`：逗號分隔輸入，依序查詢，成功加入、失敗列出。 |
| S7 | `Delete words`：逗號分隔輸入，從 repository 刪除，成功/失敗分別列出。 |
| S8 | `Review words`：可開始測驗，抽最多 10 題，依定義提示作答並累計分數。 |
| S9 | 出題時單字挖空 70%（無條件捨去），`definition` 作為提示。 |
| S10 | 查詢技術可抽換，預設本地 JSON，未來可擴充爬蟲。 |
| S11 | 各 `Scene` 有生命週期策略（永不清空/離開清空/距離清空）。 |
| S12 | 框架目標：降低重複、提升維護性，擴充場景圖時不改既有框架。 |

---

## 第二步：點的萃取（實體、類別、屬性）

| 來源句 | 詞彙 | 判定結果 | 類型 (Class/Object/Attribute) | 歸屬實體 | 說明 |
|---|---|---|---|---|---|
| S1 | Vocabulary Learning System | 開新類別 | Class | - | 系統邊界 |
| S1 | Manage/Search/Add/Delete/Review | 開新類別 | Class | - | 功能場景 |
| S2 | Word | 開新類別 | Class | - | 核心領域實體 |
| S2 | Name | 開新屬性 | Attribute | Word | 單字名稱 |
| S2 | Description | 開新屬性 | Attribute | Word | 單字描述 |
| S2 | Definition | 開新類別 | Class | - | 詞性定義 |
| S2 | Part of Speech | 開新類別 | Class | - | 詞性 |
| S2 | abbr/fullName | 開新屬性 | Attribute | PartOfSpeech | 詞性縮寫與全名 |
| S3 | Scene | 開新類別 | Class | - | CLI 場景 |
| S3 | Breadcrumbs/Messenger/Menu/Prompt | 開新類別 | Class | Scene | 畫面組件 |
| S3 | MenuOption | 開新類別 | Class | Menu | 選單項目 |
| S4 | Command | 開新類別 | Class | - | 使用者輸入命令 |
| S4 | `/ESC`、`/B` | 開新物件 | Object | Command | 特殊命令值 |
| S5~S7 | WordRepository | 開新類別 | Class | - | 單字庫 |
| S8 | ReviewSession | 開新類別 | Class | - | 一次複習流程 |
| S8 | point/latestPoint/latestNumOfQuestions | 開新屬性 | Attribute | ReviewSession | 成績狀態 |
| S8~S9 | Question | 開新類別 | Class | - | 單題 |
| S9 | wordBlank/questionNumber | 開新屬性 | Attribute | Question | 題面與題號 |
| S10 | DictionaryQueryTechnique | 開新類別 | Class | - | 查詢能力抽象 |
| S10 | LocalJsonQuery/CrawlerQuery | 開新類別 | Class | DictionaryQueryTechnique | 可替換實作 |
| S11 | SceneLifecyclePolicy | 開新類別 | Class | - | 生命週期策略 |
| S11 | NeverClear/ClearOnExit/DistanceBased | 開新類別 | Class | SceneLifecyclePolicy | 策略類型 |
| S11 | distance D | 開新屬性 | Attribute | DistanceBasedPolicy | 策略參數 |
| S12 | SceneNavigationGraph | 開新類別 | Class | - | 場景圖 |

---

## 第三步：實體職責卡片

## 實體：`Word` (Class)

### 職責 (Responsibility)
保存單字本體與語義資料，提供查詢、顯示、出題所需資料來源。

### 屬性 (Attributes)
- `name: string`
- `description: string`
- `definitions: List<Definition>`

### 行為 (Behaviors)
- 提供可顯示的摘要資訊
- 支援與答案比對

### 互動 (Interactions)
- 與 `WordRepository` 協作完成存取
- 與 `Question` 協作完成題目呈現

### 關係 (Relationships)
- `Has-A` -> `Definition`（1..*）

## 實體：`Definition` (Class)

### 職責 (Responsibility)
封裝單字在某詞性下的定義解釋。

### 屬性 (Attributes)
- `partOfSpeech: PartOfSpeech`
- `explanation: string`

### 行為 (Behaviors)
- 轉換為輸出行：`$pos - $explanation`

### 互動 (Interactions)
- 與 `Word` 組成完整語義
- 與 `Question` 提供提示

### 關係 (Relationships)
- `Uses-A` -> `PartOfSpeech`
- `Belongs-To` -> `Word`

## 實體：`PartOfSpeech` (Class)

### 職責 (Responsibility)
管理詞性的縮寫與全名。

### 屬性 (Attributes)
- `abbr: string`
- `fullName: string`

### 行為 (Behaviors)
- 提供詞性標記資訊

### 互動 (Interactions)
- 與 `Definition` 協作表達語義分類

### 關係 (Relationships)
- `Referenced-By` -> `Definition`

## 實體：`WordRepository` (Class)

### 職責 (Responsibility)
保存使用者單字庫，支援新增、刪除、查找、列舉。

### 屬性 (Attributes)
- `words: Collection<Word>`

### 行為 (Behaviors)
- `addWord(word)`
- `deleteWord(name)`
- `findWord(name)`
- `listWords()`

### 互動 (Interactions)
- 與 `Search/Add/Delete` 場景協作資料異動
- 與 `ReviewSession` 協作抽題

### 關係 (Relationships)
- `Has-A` -> `Word`（0..*）

## 實體：`DictionaryQueryTechnique` (Class)

### 職責 (Responsibility)
提供可替換的單字查詢能力（技術抽象）。

### 屬性 (Attributes)
- （抽象能力，無固定領域屬性）

### 行為 (Behaviors)
- `queryWord(name)` -> `Word | NotFound`

### 互動 (Interactions)
- 被 `Search/Add` 場景呼叫
- 與資料來源（JSON/爬蟲）協作

### 關係 (Relationships)
- `Is-A` <- `LocalJsonQuery`
- `Is-A` <- `CrawlerQuery`

## 實體：`Scene` (Class)

### 職責 (Responsibility)
承載 CLI 單一場景的畫面、輸入、導覽、狀態。

### 屬性 (Attributes)
- `breadcrumbs`
- `menu`
- `prompt`
- `messenger?`
- `state`

### 行為 (Behaviors)
- 顯示畫面
- 解析輸入命令
- 導覽上下層場景
- 套用生命週期策略清理狀態

### 互動 (Interactions)
- 與 `Command` 協作判讀輸入
- 與 `SceneNavigationGraph` 協作轉場
- 與 `SceneLifecyclePolicy` 協作狀態管理

### 關係 (Relationships)
- `Has-A` -> `Menu`、`Prompt`、`Breadcrumbs`、`Messenger`
- `Uses-A` -> `SceneLifecyclePolicy`
- `Association` -> `Scene`（上下層導覽）

## 實體：`ReviewSession` (Class)

### 職責 (Responsibility)
封裝一次複習考試流程（題組、答題、計分）。

### 屬性 (Attributes)
- `point: int`
- `questionSet: List<Question>`
- `remainingQuestions: int`
- `latestPoint: int`
- `latestNumOfQuestions: int`

### 行為 (Behaviors)
- 初始化題組（最多 10 題）
- 判斷答案正確性
- 更新分數

### 互動 (Interactions)
- 與 `WordRepository` 協作抽題
- 與 `Question` 逐題互動

### 關係 (Relationships)
- `Has-A` -> `Question`（1..10）
- `Uses-A` -> `WordRepository`

---

## 第四步：線的萃取（關係分類）

| 實體 A | 關係類型 | 實體 B | A 端基數 | B 端基數 | 來源（定義/行為） | 說明 |
|---|---|---|---|---|---|---|
| Word | Association (`Has-A`) | Definition | 1 | 1..* | 定義 | 每個單字至少一個定義 |
| Definition | Association (`Uses-A`) | PartOfSpeech | 1 | 1 | 定義 | 每筆定義對應一個詞性 |
| WordRepository | Association (`Has-A`) | Word | 1 | 0..* | 定義/行為 | 單字庫持有多個單字 |
| ReviewSession | Association (`Has-A`) | Question | 1 | 1..10 | 行為 | 一次測驗題組 |
| ReviewSession | Dependency (`Uses-A`) | WordRepository | 1 | 1 | 行為 | 題目來源 |
| Scene | Association | SceneLifecyclePolicy | 1 | 1 | 定義 | 每個場景綁定一策略 |
| DistanceBasedPolicy | Generalization (`Is-A`) | SceneLifecyclePolicy | * | 1 | 定義 | 生命週期子類型 |
| NeverClearPolicy | Generalization (`Is-A`) | SceneLifecyclePolicy | * | 1 | 定義 | 生命週期子類型 |
| ClearOnExitPolicy | Generalization (`Is-A`) | SceneLifecyclePolicy | * | 1 | 定義 | 生命週期子類型 |
| LocalJsonQuery | Generalization (`Is-A`) | DictionaryQueryTechnique | * | 1 | 定義 | 查詢實作 |
| CrawlerQuery | Generalization (`Is-A`) | DictionaryQueryTechnique | * | 1 | 定義 | 查詢實作 |
| Search/Add Scene | Dependency (`Uses-A`) | DictionaryQueryTechnique | 1 | 1 | 行為 | 查詢單字定義 |
| Scene | Association | Scene（Navigation） | 1 | 0..* | 定義/行為 | 導覽上下層關係 |
| Menu | Association (`Has-A`) | MenuOption | 1 | 1..* | 定義 | 選單組成 |

### 關聯類別（Association Class）

| 關聯類別名稱 | 連接的關聯 | 屬性 | 行為 |
|---|---|---|---|
| `SceneTransition` | `Scene <-> Scene` | `command`, `targetScene`, `distance` | 驗證導覽命令並轉場 |
| `LexicalSense`（可由 `Definition` 承載） | `Word <-> PartOfSpeech` | `explanation` | 詞性定義輸出 |

---

## 第五步：最終 OOA 分析文件彙整

1. **需求摘要**  
   本系統是以 CLI 場景驅動的單字學習應用，核心領域為 `Word` 與其語義資料，並結合可替換查詢技術、場景導覽圖與可配置生命週期策略，達成低重複、可擴充、可維護的框架化目標。

2. **實體清單**  
   `VocabularyLearningSystem`, `Scene`, `SceneNavigationGraph`, `SceneLifecyclePolicy`(+子類型), `WordRepository`, `Word`, `Definition`, `PartOfSpeech`, `ReviewSession`, `Question`, `DictionaryQueryTechnique`(+`LocalJsonQuery`/`CrawlerQuery`), `Menu`, `MenuOption`, `Breadcrumbs`, `Messenger`, `Prompt`, `Command`。

3. **實體職責卡片**  
   已於第三步完整列出。

4. **關係清單**  
   已於第四步完整列出（含 `Generalization / Association / Dependency` 與基數）。

5. **關聯類別**  
   `SceneTransition`、`LexicalSense`（或以 `Definition` 承載）。

---

## 模糊點（待確認）

- `A113 Delete words` 有一段描述像是「查詢成功加入單字庫」，語意與刪除案例衝突。  
- `A113` 的 `$currentWords` 說明有疑似誤植。  
- Root 場景是否應提供 `/B`（通常根場景無上一頁）。  
