package org.csstudio.saverestore.git.ui;

import java.util.Optional;

import org.csstudio.saverestore.git.Credentials;
import org.csstudio.saverestore.ui.util.FXCanvasMaker;
import org.csstudio.saverestore.ui.util.InputValidator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * <code>UsernameAndPasswordDialog</code> is a dialog which provides means to enter a username and password.
 * It also provides a checkbox in order to hint the receiver whether to remember the credentials for later or not.
 *
 * @author <a href="mailto:jaka.bobnar@cosylab.com">Jaka Bobnar</a>
 *
 */
public class UsernameAndPasswordDialog extends TitleAreaDialog {

    private Button okButton;
    private PasswordField password;
    private TextField username;
    private CheckBox remember;
    private Credentials value;
    private InputValidator<String> validator = e -> (e == null || e.trim().isEmpty()) ? "Invalid" : null;
    private String currentUsername;

    /**
     * Constructs a new dialog and sets the predefined username. If no username is provided, system user
     * is used.
     *
     * @param shell the parent shell
     * @param username predefined username
     */
    public UsernameAndPasswordDialog(Shell shell, String username) {
        super(shell);
        this.currentUsername = username == null ? System.getProperty("user.name") : username;
        setBlockOnOpen(true);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            value = new Credentials(username.getText(), password.getText().toCharArray(), remember.isSelected());
        } else {
            value = null;
        }
        super.buttonPressed(buttonId);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Authentication");
    }

    /**
     * Open the dialog, wait for it to close and then return the value.
     *
     * @return the entered credentials or an empty object if cancel was pressed
     */
    public Optional<Credentials> openAndWat() {
        if (open() == IDialogConstants.OK_ID) {
            return Optional.ofNullable(value);
        } else {
            return Optional.empty();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        ((GridLayout) parent.getLayout()).numColumns++;
        new FXCanvasMaker() {
            @Override
            protected Scene createFxScene() {
                okButton = new Button(IDialogConstants.OK_LABEL);
                okButton.setOnAction(e -> buttonPressed(IDialogConstants.OK_ID));
                Button cancelButton = new Button(IDialogConstants.CANCEL_LABEL);
                cancelButton.setOnAction(e -> buttonPressed(IDialogConstants.CANCEL_ID));
                okButton.setPrefWidth(60);
                cancelButton.setPrefWidth(60);

                GridPane pane = new GridPane();
                pane.setHgap(10);
                GridPane.setHgrow(okButton, Priority.ALWAYS);
                GridPane.setHalignment(okButton, HPos.RIGHT);
                pane.add(okButton, 0, 0);
                pane.add(cancelButton, 1, 0);
                return new Scene(pane);
            }
        }.createPartControl(parent);
        validateInput();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        new FXCanvasMaker() {
            @Override
            protected Scene createFxScene() {
                GridPane pane = new GridPane();
                pane.setPadding(new Insets(10,10,10,10));
                pane.setHgap(3);
                pane.setVgap(5);
                username = new TextField();
                username.setMaxWidth(Double.MAX_VALUE);
                if (currentUsername != null) {
                    username.setText(currentUsername);
                    username.selectAll();
                }
                password = new PasswordField();
                username.setOnAction(e->password.requestFocus());
                password.setOnAction(e->{
                    if (!okButton.isDisable()) {
                        buttonPressed(IDialogConstants.OK_ID);
                    }
                });
                password.textProperty().addListener((a,o,n)->validateInput());
                username.textProperty().addListener((a,o,n)->validateInput());
                password.setMaxWidth(Double.MAX_VALUE);
                Label uLabel = new Label("Username:");
                Label pLabel = new Label("Password:");
                GridPane.setHgrow(username, Priority.ALWAYS);
                GridPane.setHgrow(password, Priority.ALWAYS);
                GridPane.setFillWidth(username, true);
                GridPane.setFillWidth(password, true);
                pane.add(uLabel, 0,0);
                pane.add(pLabel, 0,1);
                pane.add(username, 1, 0);
                pane.add(password, 1, 1);
                remember = new CheckBox("Remember password for later use");
                pane.add(remember, 0, 2, 2, 1);
                pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
                Color c = parent.getBackground();
                String cl = Integer.toHexString(c.getRed()) + Integer.toHexString(c.getGreen()) + Integer.toHexString(c.getBlue());
                pane.setStyle("-fx-background-color: #"+cl + ";");
                pane.setPrefWidth(540);
                return new Scene(pane);
            }
        }.createPartControl(composite);

        setTitle("Authentication");
        setMessage("Please, provide the username and password to access Save and Restore git repository");
        return composite;
    }

    private void validateInput() {
        String s = validator.validate(username.getText());
        if (s == null) {
            s = validator.validate(password.getText());
        }
        if (okButton != null) {
            okButton.setDisable(s != null);
        }
    }
}
