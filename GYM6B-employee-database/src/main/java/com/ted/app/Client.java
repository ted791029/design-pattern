package com.ted.app;

import com.ted.app.util.ScannerUtil;

import java.io.IOException;

public class Client {
    private Database database;

    public Client(Database database) {
        this.database = database;
    }

    public void start() throws IOException {
        while (true){
            int id  = ScannerUtil.getInputInteger("請輸入員工ID", 1, 9999999);
            Employee employee = database.getEmployeeById(id);
            System.out.println(employee);
            System.out.println("下屬為: " + employee.getSubordinates());
            System.out.println("==============================================================");
        }
    }
}
