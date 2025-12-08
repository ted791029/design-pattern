package com.ted.app;

import com.ted.app.databases.ProtectionPassWordRealDatabaseProxy;
import com.ted.app.databases.RealDatabase;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        String password = "1qaz2wsx";
        String password = "test";
        Client client = new Client(new ProtectionPassWordRealDatabaseProxy(new RealDatabase(), password));
        client.start();
    }
}
