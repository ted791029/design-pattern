package com.ted.app;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ted.app.handler.AttractiveHandler;
import com.ted.app.handler.Covid19Handler;
import com.ted.app.handler.SleepApneaSyndromeHandler;
import com.ted.app.lib.*;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class PrescriptionDemandFacade {

    private PrescriberSystem prescriberSystem;

    public static final String DATABASE_FILE_PATH = "D:\\sourcetree\\design-pattern\\GYM6-prescriber-system\\src\\main\\java\\com\\ted\\app\\file\\database\\";

    public static final String DISEASE_FILE_PATH = "D:\\sourcetree\\design-pattern\\GYM6-prescriber-system\\src\\main\\java\\com\\ted\\app\\file\\disease\\";

    public static final String EXPORT_FILE_PTAH = "D:\\sourcetree\\design-pattern\\GYM6-prescriber-system\\src\\main\\java\\com\\ted\\app\\file\\cases\\";
    public static final int UPDATE_DATABASE = 0x01;

    public static final int UPDATE_SUPPORTED_DISEASES = 0x01 << 1;

    public static final int PRESCRIPTION_DEMAND = 0x01 << 2;

    public PrescriptionDemandFacade() throws IOException {
        prescriberSystemInit();
    }

    private void export(Case c, String fileName, ExportType type) throws IOException {

        if (ExportType.CSV == type) {
            exportToCSV(c, fileName);
        } else if (ExportType.JSON == type) {
            exportToJson(c, fileName);
        }

    }

    private void exportToCSV(Case c, String fileName) throws IOException {
        String fullPath = EXPORT_FILE_PTAH + fileName + ".csv";
        try (
                Writer writer = new OutputStreamWriter(
                        new FileOutputStream(fullPath),
                        StandardCharsets.UTF_8
                );
                ICsvListWriter listWriter = new CsvListWriter(writer, CsvPreference.STANDARD_PREFERENCE)
        ) {
            final String[] header = new String[]{"symptoms", "name", "potentialDisease", "medicines", "usage", "caseTime"};

            listWriter.writeHeader(header);

            String symptomsStr = c.getSymptoms().stream().collect(Collectors.joining("、"));
            Prescription prescription = c.getPrescription();

            final List<Object> columns = Arrays.asList(
                    symptomsStr,
                    prescription.getName(),
                    prescription.getPotentialDisease(),
                    prescription.getMedicines(),
                    prescription.getUsage(),
                    c.getCaseTime().toString()
            );

            listWriter.write(columns);

        }

        System.out.println("數據已寫入 " + fileName);
    }

    private void exportToJson(Case c, String fileName) throws IOException {
        Gson gson = new Gson();

        String fullPath = EXPORT_FILE_PTAH + fileName + ".json";

        try (
                Writer writer = new OutputStreamWriter(
                        new FileOutputStream(fullPath),
                        StandardCharsets.UTF_8
                )
        ) {

            gson.toJson(c, writer);

        }

        System.out.println("數據已寫入 " + fileName);
    }

    private DiseasesHandler handlerInit() {
        return new Covid19Handler(
                new AttractiveHandler(
                        new SleepApneaSyndromeHandler(
                                null
                        )
                )
        );
    }

    private InputStream getResourceFile(String filePath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("找不到檔案: " + filePath);
        }

        return inputStream;
    }

    private List<Patient> jsonFormatToPatients(InputStream data) {
        try {
            Gson gson = new Gson();
            JsonElement element = JsonParser.parseReader(new InputStreamReader(data, StandardCharsets.UTF_8));
            // 這裡 TypeToken 必須使用 Patient 類別
            Type listType = TypeToken.getParameterized(List.class, Patient.class).getType();

            if (element.isJsonArray()) {
                // 這裡直接使用 listType 進行反序列化，結果為 List<Patient>
                return gson.fromJson(element, listType);
            } else if (element.isJsonObject()) {
                // 這裡直接反序列化為 Patient 物件
                Patient obj = gson.fromJson(element, Patient.class);
                return Collections.singletonList(obj);
            }

            return Collections.emptyList();
        } catch (Exception e) {
            throw new RuntimeException("讀取或解析 JSON 失敗", e);
        }
    }

    private void prescriberRun(Prescriber prescriber) {
        Thread prescriberThread = new Thread(prescriber);
        prescriberThread.start();
    }

    private void prescriberSystemInit() throws IOException {
        PatientDatabase patientDatabase = new PatientDatabase();
        BlockingQueue<PrescriptionDemandInfo> queue = new LinkedBlockingQueue<>();
        DiseasesHandler handler = handlerInit();
        Prescriber prescriber = new Prescriber(handler, queue);
        prescriberSystem = new PrescriberSystem(patientDatabase, prescriber, queue);
        updateDatabase("default_database.json");
        prescriberRun(prescriber);
    }

    public void request(PrescriptionDemandRequest request) throws IOException {

        if ((request.getUpdateAction() & UPDATE_DATABASE) == UPDATE_DATABASE) {
            String patientTableName = request.getPatientTableName();

            if (patientTableName == null) {
                throw new RuntimeException("無檔案名，無法更新Database");
            }

            updateDatabase(patientTableName);
        }

        if ((request.getUpdateAction() & UPDATE_SUPPORTED_DISEASES) == UPDATE_SUPPORTED_DISEASES) {
            String supportedDiseasesTable = request.getSupportedDiseasesTable();

            if (supportedDiseasesTable == null) {
                throw new RuntimeException("無檔案名，無法更新Diseases");
            }

            updateSupportDiseases(supportedDiseasesTable);
        }

        if ((request.getUpdateAction() & PRESCRIPTION_DEMAND) == PRESCRIPTION_DEMAND) {
            Case c = prescriberSystem.prescriptionDemand(request.getId(), request.getSymptoms());
            export(c, request.getExportFileName(), request.getExportType());
        }
    }

    private List<String> txtFormatToList(InputStream data) {

        List<String> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(data, StandardCharsets.UTF_8))) {
            String line;

            while ((line = reader.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException("讀取 TXT 失敗", e);
        }

        return result;
    }

    private void updateDatabase(String patientTableName) throws IOException {
        InputStream inputStream = getResourceFile(DATABASE_FILE_PATH + patientTableName);
        List<Patient> patients = jsonFormatToPatients(inputStream);
        prescriberSystem.updateDatabase(patients);
    }

    private void updateSupportDiseases(String supportedDiseasesTable) throws IOException {
        InputStream inputStream = getResourceFile(DISEASE_FILE_PATH + supportedDiseasesTable);
        List<String> supportedDiseases = txtFormatToList(inputStream);
        prescriberSystem.updateSupportDiseases(supportedDiseases);
    }
}
