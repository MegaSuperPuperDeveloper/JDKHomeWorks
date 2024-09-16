package org.example;

// Создать справочник сотрудников
// Необходимо:
// Создать класс справочник сотрудников, который содержит внутри
// коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
// Табельный номер
// Номер телефона
// Имя
// Стаж
// Добавить метод, который ищет сотрудника по стажу (может быть список)
// Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
// Добавить метод, который ищет сотрудника по табельному номеру
// Добавить метод добавления нового сотрудника в справочник


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomeWork4 {
    private ArrayList<String> info = new ArrayList<>();
    private static final HashMap<Integer, ArrayList<String>> listOfStaff = new HashMap<>();


    public HomeWork4(Integer reportCardNumber, String phoneNumber, String name, String experience) {
        info.add(String.valueOf(reportCardNumber));
        info.add(phoneNumber);
        info.add(name);
        info.add(experience);
        listOfStaff.put(reportCardNumber, info);
    }

    public static ArrayList<String> getStaffByReportCardNumber(Integer reportCardNumber) {
        return listOfStaff.get(reportCardNumber);
    }

    public static ArrayList<String> getPhoneNumberByName(String name) {
        ArrayList<String> phoneNumbers = new ArrayList<>();
        for (Integer L : listOfStaff.keySet()) {
            if (Objects.equals(listOfStaff.get(L).get(2), name)) {
                phoneNumbers.add(listOfStaff.get(L).get(1));
            }
        }
        return phoneNumbers;
    }

    public static ArrayList<String> getStaffByExperience(String experience) {
        ArrayList<String> staffs = new ArrayList<>();
        for (Integer L : listOfStaff.keySet()) {
            if (Objects.equals(listOfStaff.get(L).get(3), experience)) {
                staffs.add(String.valueOf(listOfStaff.get(L)));
            }
        }
        return staffs;
    }

}