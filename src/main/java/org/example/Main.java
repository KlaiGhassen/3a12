package org.example;

import models.Students;
import services.StudentsService;

public class Main {
    public static void main(String[] args) {
        StudentsService ss = new StudentsService();
        Students s = new Students(1, 24, "iyadh", "hamemi");
        try {
            // ss.create(s);
            //ss.update(s);
            //   System.out.println(ss.getAll());
            ss.delete(1);
            System.out.println(ss.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}