package org.example;

import org.example.entity.User;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        logger.info("Application started.");

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);
            logger.info("User selected option: {}", choice);

            switch (choice) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    readUser(scanner);
                    break;
                case 3:
                    updateUser(scanner);
                    break;
                case 4:
                    deleteUser(scanner);
                    break;
                case 5:
                    listUsers();
                    break;
                case 0:
                    exitApplication(scanner);
                    return;
                default:
                    logger.warn("Invalid option selected: {}", choice);
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Create User");
        System.out.println("2. Read User");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. List Users");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void createUser(Scanner scanner) {
        User user = new User();
        scanner.nextLine();

        System.out.print("Enter name: ");
        user.setName(scanner.nextLine());
        logger.info("User created with name: {}", user.getName());

        System.out.print("Enter email: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Enter age: ");
        user.setAge(scanner.nextInt());

        userService.saveUser(user);
        logger.info("User saved: {}", user);
    }

    private static void readUser(Scanner scanner) {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        User fetchedUser = userService.getUser(id);
        logger.info("Fetched user: {}", fetchedUser);
        System.out.println(fetchedUser);
    }

    private static void updateUser(Scanner scanner) {
        System.out.print("Enter user ID to update: ");
        int updateId = scanner.nextInt();
        User updateUser = userService.getUser(updateId);

        if (updateUser != null) {
            logger.info("Updating user with ID: {}", updateId);

            System.out.print("Enter new name: ");
            updateUser.setName(scanner.next());
            logger.info("Updated name to: {}", updateUser.getName());

            System.out.print("Enter new email: ");
            updateUser.setEmail(scanner.next());

            logger.info("Updated email to: {}", updateUser.getEmail());

            System.out.print("Enter new age: ");
            updateUser.setAge(scanner.nextInt());
            userService.updateUser(updateUser);
            logger.info("User updated successfully: {}", updateUser);
        } else {
            logger.warn("User not found for update attempt with ID: {}", updateId);
            System.out.println("User not found!");
        }
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("Enter user ID to delete: ");
        int deleteId = scanner.nextInt();
        logger.info("Deleting user with ID: {}", deleteId);
        userService.deleteUser(deleteId);
    }

    private static void listUsers() {
        logger.info("Listing all users.");
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
        logger.info("Displayed {} users.", users.size());
    }

    private static void exitApplication(Scanner scanner) {
        logger.info("Exiting application.");
        scanner.close();
    }
}
