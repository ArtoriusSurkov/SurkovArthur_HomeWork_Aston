package org.example;

import org.example.entity.User;
import org.example.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. List Users");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    User user = new User();
                    System.out.print("Enter name: ");
                    user.setName(scanner.nextLine());
                    System.out.print("Enter email: ");
                    user.setEmail(scanner.nextLine());
                    System.out.print("Enter age: ");
                    user.setAge(scanner.nextInt());
                    userService.saveUser(user);
                    break;
                case 2:
                    System.out.print("Enter user ID: ");
                    Long id = scanner.nextLong();
                    User fetchedUser = userService.getUser(id);
                    System.out.println(fetchedUser);
                    break;
                case 3:
                    System.out.print("Enter user ID to update: ");
                    Long updateId = scanner.nextLong();
                    User updateUser = userService.getUser(updateId);
                    if (updateUser != null) {
                        System.out.print("Enter new name: ");
                        updateUser.setName(scanner.next());
                        System.out.print("Enter new email: ");
                        updateUser.setEmail(scanner.next());
                        System.out.print("Enter new age: ");
                        updateUser.setAge(scanner.nextInt());
                        userService.updateUser(updateUser);
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter user ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    userService.deleteUser(deleteId);
                    break;
                case 5:
                    List<User> users = userService.getAllUsers();
                    for (User u : users) {
                        System.out.println(u);
                    }
                    break;
                case 0:
                    userService.close();
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}