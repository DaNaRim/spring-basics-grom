package com.lesson3;

import org.springframework.beans.factory.annotation.Autowired;

public class PasswordReminder {

//    @Autowired
    private DbConnector dbConnector;

    @Autowired
    public PasswordReminder(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void sendPassword() {
        //logic
    }

//    @Autowired
//    public void setDbConnector(DbConnector dbConnector) {
//        this.dbConnector = dbConnector;
//    }
}
