package com.mycompany.mavenproject2;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            // ================= REGISTRATION =================
            Login login = new Login();
            System.out.println("===== USER REGISTRATION =====");
            System.out.print("Enter Username: ");
            String username = input.nextLine();
            System.out.print("Enter Password: ");
            String password = input.nextLine();
            System.out.print("Enter Phone Number: ");
            String phoneNumber = input.nextLine();

            String registrationMessage = login.registerUser(username, password, phoneNumber);
            System.out.println(registrationMessage);

            if (!registrationMessage.equals("User registered successfully.")) {
                return;
            }

            // ================= LOGIN =================
            System.out.println("\n===== USER LOGIN =====");
            System.out.print("Enter Username: ");
            String loginUsername = input.nextLine();
            System.out.print("Enter Password: ");
            String loginPassword = input.nextLine();

            boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginSuccess));

            if (!loginSuccess) {
                return;
            }

            // ================= QUICKCHAT APP =================
            System.out.println("\nWelcome to QuickChat.");
            System.out.print("\nHow many messages would you like to send? ");

            int maxMessages = 0;
            try {
                maxMessages = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Exiting.");
                return;
            }

            int menuChoice;

            do {
                System.out.println("\n--- QuickChat Menu ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                try {
                    menuChoice = Integer.parseInt(input.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    menuChoice = 0;
                    continue;
                }

                switch (menuChoice) {

                    case 1 -> {
                        int messagesSentThisSession = 0;

                        while (messagesSentThisSession < maxMessages) {
                            System.out.println("\n--- Message "
                                    + (messagesSentThisSession + 1)
                                    + " of " + maxMessages + " ---");

                            System.out.print("Enter recipient cell number: ");
                            String recipient = input.nextLine().trim();

                            String messageText = "";
                            while (true) {
                                System.out.print("Enter message (max 250 chars): ");
                                messageText = input.nextLine();
                                String lengthCheck = Message.validateMessageLength(messageText);
                                if (lengthCheck.equals("Message ready to send.")) {
                                    System.out.println("Message ready.");
                                    break;
                                } else {
                                    System.out.println(lengthCheck
                                            + " Please shorten your message.");
                                }
                            }

                            Message msg = new Message(recipient, messageText);

                            System.out.println(msg.checkRecipientCell());
                            System.out.println("Message ID   : " + msg.getMessageID());
                            System.out.println("Message Hash : " + msg.getMessageHash());

                            System.out.println("\nWhat would you like to do?");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            System.out.print("Choose: ");

                            int sendChoice = 0;
                            try {
                                sendChoice = Integer.parseInt(input.nextLine().trim());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid choice.");
                            }

                            System.out.println(msg.sentMessage(sendChoice));

                            System.out.println("\n--- Message Details ---");
                            System.out.println("Message ID   : " + msg.getMessageID());
                            System.out.println("Message Hash : " + msg.getMessageHash());
                            System.out.println("Recipient    : " + msg.getRecipient());
                            System.out.println("Message      : " + msg.getMessageText());

                            messagesSentThisSession++;
                        }

                        System.out.println("\nTotal messages sent: "
                                + Message.returnTotalMessages());
                    }

                    case 2 -> System.out.println(Message.printMessages());

                    case 3 -> System.out.println("Goodbye!");

                    default -> System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }

            } while (menuChoice != 3);

        }
    }
}