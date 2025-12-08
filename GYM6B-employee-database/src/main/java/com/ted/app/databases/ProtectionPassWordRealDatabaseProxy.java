package com.ted.app.databases;

import com.ted.app.Database;
import com.ted.app.Employee;

public class ProtectionPassWordRealDatabaseProxy implements Database {
    private Database database;

    private String password;

    public ProtectionPassWordRealDatabaseProxy(Database database, String password) {
        this.password = password;
        this.database = database;
    }

    @Override
    public Employee getEmployeeById(int id) {

        if(password != "1qaz2wsx"){
            throw new RuntimeException("密碼錯誤");
        }

        return database.getEmployeeById(id);
    }
}
