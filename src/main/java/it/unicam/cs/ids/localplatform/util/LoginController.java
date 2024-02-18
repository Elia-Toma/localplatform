package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents a login controller.
 */
public class LoginController {
    private final PlatformManager platformManager;

    @FXML
    private TextField email;
    @FXML
    private PasswordField psw;
    private static User currentUser;

    public LoginController() {
        this.platformManager = Controller.getPlatformManager();
    }

    /**
     * This method is called when the user clicks the login button.
     *
     * @param event the event representing the action of the user
     * @throws IOException
     */
    @FXML
    private void Login(ActionEvent event) throws IOException {
        String emailValue = email.getText();
        String passwordValue = psw.getText();

        if (this.platformManager.getEmail().equals(emailValue))
            if (this.platformManager.getPassword().equals(passwordValue)) {
                currentUser = this.platformManager;
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/Platform_Manager.fxml"));
                Scene scene = new Scene(root, 800, 600);

                stage.setScene(scene);
                stage.show();
            }

        // Retrieve the corresponding user from the municipality's user list.
        User user = this.platformManager.getResidence().getUsers().stream()
                .filter(u -> u.getEmail().equals(emailValue) && u.getPassword().equals(passwordValue))
                .findFirst()
                .orElse(null);

        if (user != null) {
            currentUser = user;
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = null;

            // Check the user's class and open the corresponding fxml file.
            switch (user) {
                case Contributor contributor -> root = FXMLLoader.load(getClass().getResource("/Contributor.fxml"));
                case AuthorizedTourist authorizedTourist ->
                        root = FXMLLoader.load(getClass().getResource("/Auth_Tourist.fxml"));
                case Tourist tourist -> root = FXMLLoader.load(getClass().getResource("/Tourist.fxml"));
                case Animator animator -> root = FXMLLoader.load(getClass().getResource("/Animator.fxml"));
                case Curator curator -> root = FXMLLoader.load(getClass().getResource("/Curator.fxml"));
                case AuthorizedContributor authorizedContributor ->
                        root = FXMLLoader.load(getClass().getResource("/Auth_Contributor.fxml"));
                default -> {
                }
            }

            if (root != null) {
                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}