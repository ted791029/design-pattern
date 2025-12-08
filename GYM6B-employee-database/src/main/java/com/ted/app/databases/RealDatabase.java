package com.ted.app.databases;

import com.ted.app.Database;
import com.ted.app.Employee;
import com.ted.app.employees.VirtualRealEmployeeProxy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RealDatabase implements Database {

    private final String FILE_PATH = "D:\\sourcetree\\design-pattern\\GYM6B-employee-database\\src\\main\\resources\\database.txt";

    private final String INDEX_FILE_PATH = "D:\\sourcetree\\design-pattern\\GYM6B-employee-database\\src\\main\\resources\\database_idx.bin";

    private final int INDEX_BATCH_SIZE = 1000; // 每批處理行數

    private List<Long> indexList;

    public RealDatabase() throws IOException {
        File file = new File(INDEX_FILE_PATH);

        if (!file.exists()) {
            initIndexFile();
        }

        loadIndex();

    }


    @Override
    public Employee getEmployeeById(int id) {
        //首行為Header
        int lineCount = id + 1;

        if (lineCount < 1 && lineCount > indexList.size()) {
            throw new RuntimeException("查無此員工");
        }

        String line;

        try {
            RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r");
            long pos = indexList.get(lineCount - 1);
            raf.seek(pos);
            line = raf.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (line == null) {
            throw new RuntimeException("查無此員工");
        }

        return toEmployee(line);
    }

    private void exportIndex(List<Long> positions) throws IOException {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(INDEX_FILE_PATH, true)))) {

            for (long pos : positions) {
                out.writeLong(pos);
            }

            out.flush();
        }
    }

    private void loadIndex() {
        List<Long> positions = new ArrayList<>();

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(INDEX_FILE_PATH)))) {

            while (true) {
                try {
                    long position = in.readLong();
                    positions.add(position);
                } catch (EOFException eof) {
                    // 讀到檔案結尾，正常結束
                    break;
                }
            }

        } catch (IOException e) {
            // 檔案不存在或讀取錯誤
            e.printStackTrace();
        }

        indexList = positions;

    }


    private void initIndexFile() throws IOException {
        System.out.printf("建立 DB 索引檔%n");

        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r")) {

            long position = 0;
            int lineCount = 1;
            List<Long> batchPositions = new ArrayList<>(INDEX_BATCH_SIZE);

            batchPositions.add(position);

            String line;
            while ((line = raf.readLine()) != null) {
                position = raf.getFilePointer();
                batchPositions.add(position);

                if (lineCount % INDEX_BATCH_SIZE == 0) {
                    // 每批寫出索引
                    exportIndex(batchPositions);
                    batchPositions.clear();
                    System.out.printf("已處理 %,d 行%n", lineCount);
                }
                lineCount++;
            }

            // 寫出最後一批
            if (!batchPositions.isEmpty()) {
                exportIndex(batchPositions);
            }

            System.out.printf("完成！總行數約 %,d 行%n", lineCount);
        }
    }

    private Employee toEmployee(String line) {
        String[] dataArr = line.split(" ");
        int id = Integer.parseInt(dataArr[0]);
        String name = dataArr[1];
        int age = Integer.parseInt(dataArr[2]);
        String subordinateIds = dataArr.length == 4 ? dataArr[3] : null;
        return new VirtualRealEmployeeProxy(age, this, id, name, subordinateIds);
    }


}
