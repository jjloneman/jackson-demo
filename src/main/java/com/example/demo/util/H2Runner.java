package com.example.demo.util;

import org.h2.tools.Shell;

import java.sql.SQLException;

public class H2Runner extends Thread {

    public void run() {
        try {
            Shell shell = new Shell();
            shell.runTool("-url", "jdbc:h2:mem:mydb2",
                    "-driver", "org.h2.Driver",
                    "-user", "sa",
                    "-password", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void terminate() {
        this.interrupt();
    }
}
