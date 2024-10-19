package handlers;

import java.util.*;
import models.Administrator;
import models.Doctor;
import models.Pharmacist;
import models.User;

public class StaffManagement {
    private List<User> staff;
    private final String staffFile = "src/data/Staff_List.csv";  // File to load staff from

    public StaffManagement() {
        this.staff = new ArrayList<>();
        loadStaff();
    }

    // Load staff from CSV
    public void loadStaff() {
        List<String[]> data = CSVHandler.readCSV(staffFile);
        System.out.println(data.size());
        for (String[] row : data) {
            String id = row[0];                // Staff ID
            String name = row[1];              // Staff Name
            String role = row[2];              // Role (Doctor, Pharmacist, Administrator)
            String password = "password";          // Staff Password
            User staffMember = null;

            switch (role) {
                case "Doctor":
                    staffMember = new Doctor(id, name, password);
                    break;
                case "Pharmacist":
                    staffMember = new Pharmacist(id, name, password);
                    break;
                case "Administrator":
                    staffMember = new Administrator(id, name, password);
                    break;
            }

            if (staffMember != null) {
                staff.add(staffMember);
            }
        }
    }

    public User findStaffById(String id) {
        for (User user : staff) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
    public void addStaff(User newStaff) {
        staff.add(newStaff);
    }

    public void removeStaff(String staffId) {
        staff.removeIf(user -> user.getId().equals(staffId));
    }

    public void displayStaff() {
        System.out.println("Hospital Staff List:");
        for (User user : staff) {
            System.out.println("ID: " + user.getId() + " | Name: " + user.getName() + " | Role: " + user.getRole());
        }
    }
}
