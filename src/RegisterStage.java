import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterStage {

	public RegisterStage() {

	}

	@SuppressWarnings("unchecked")
	public void buildStage(Stage primaryStage) {

		primaryStage.setTitle("PMR");

		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,50,50,50));

		//Adding HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(20,20,20,30));


		//Adding GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		//Implementing Nodes for GridPane
		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Label lblPassword = new Label("Password");
		final PasswordField pf = new PasswordField();
		Label lblEmail = new Label("Email");
		final TextField txtEmail = new TextField();
		Label lblPhone = new Label("Phone Number");
		final TextField txtPhone = new TextField();

		Button btnRegister = new Button("       Create Account       ");
		Button btnBack = new Button("Back");
		final Label lblMessage = new Label();


		//Adding Nodes to GridPane layout
		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(lblEmail, 0, 2);
		gridPane.add(lblPhone, 0, 3);
		gridPane.add(pf, 1, 1);
		gridPane.add(txtEmail, 1, 2);
		gridPane.add(txtPhone, 1, 3);
		gridPane.add(btnBack, 0, 4);
		gridPane.add(btnRegister, 1, 4);
		gridPane.add(lblMessage, 1, 2);


		//Reflection for gridPane
		//		Reflection r = new Reflection();
		//		r.setFraction(0.5f);
		//		gridPane.setEffect(r);

		//DropShadow effect 
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);

		//Adding text and DropShadow effect to it
		Text text = new Text("    PMR");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);

		//Adding text to HBox
		hb.getChildren().add(text);

		//Add ID's to Nodes
		bp.setId("bp");
		gridPane.setId("root");
		btnRegister.setId("btnRegister");
		btnBack.setId("btnBack");
		text.setId("text");

		//Action for btnBack
		btnBack.setOnAction(new EventHandler() {
			public void handle(Event event) {

				Stage stage = new Stage();
				LoginStage pmrstage = new LoginStage();
				pmrstage.loginStage(stage);
				primaryStage.close();					

				txtUserName.setText("");
				pf.setText("");
			}
		});

		//		//Action for btnRegister
		btnRegister.setOnAction(new EventHandler() {
			public void handle(Event event) {

				//validate user name input

				txtPhone.setText(txtPhone.getText().replaceAll("[^\\d]", ""));

				boolean username_valid = (txtUserName.getText().length() > 3) ? true : false; 
				boolean pf_valid = (pf.getText().length() > 3) ? true : false; 
				boolean email_valid = (txtEmail.getText().length() > 5 && txtEmail.getText().contains("@") && txtEmail.getText().contains(".")) ? true : false; 
				boolean phone_valid = (txtPhone.getText().length() == 10) ? true : false;
				
				boolean user_already_exists = false;
				boolean email_already_exists = false;
				boolean phone_already_exists = false;


				Connection user_connection = null;
				ResultSet user_resultSet = null;
				Statement user_statement = null;
				
				Connection email_connection = null;
				ResultSet email_resultSet = null;
				Statement email_statement = null;
				
				Connection phone_connection = null;
				ResultSet phone_resultSet = null;
				Statement phone_statement = null;
				
				try{
					String url = "jdbc:sqlite:../server/db/pmr.db";
					user_connection = DriverManager.getConnection(url);
					email_connection = DriverManager.getConnection(url);
					phone_connection = DriverManager.getConnection(url);

					String user_sql = "Select * from User WHERE Username='" + txtUserName.getText() + "';"; 
					String email_sql = "Select * from User WHERE Email='" + txtEmail.getText() + "';"; 
					String phone_sql = "Select * from User WHERE PhoneNumber='" + txtPhone.getText() + "';"; 

					System.out.println(user_sql);
					System.out.println(email_sql);
					System.out.println(phone_sql);

					user_statement = user_connection.createStatement();
					email_statement = email_connection.createStatement();
					phone_statement = phone_connection.createStatement();
					
					user_resultSet = user_statement.executeQuery(user_sql);
					email_resultSet = email_statement.executeQuery(email_sql);
					phone_resultSet = phone_statement.executeQuery(phone_sql);
					
					if(user_resultSet.next()){
						user_already_exists = true;
					}
					
					if(email_resultSet.next()){
						email_already_exists = true;
					}
					
					if(phone_resultSet.next()){
						phone_already_exists = true;
					}

				} catch (SQLException e){
					System.out.println(e.getMessage());
				} finally {
					try{
						if (user_connection != null){
							user_resultSet.close();
							user_statement.close();
							user_connection.close();
						}
						if (email_connection != null){
							email_resultSet.close();
							email_statement.close();
							email_connection.close();
						}
						if (phone_connection != null){
							phone_resultSet.close();
							phone_statement.close();
							phone_connection.close();
						}
					} catch (SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}

				if (!username_valid) {
					JOptionPane.showMessageDialog(null, "Username must be at least 4 characters long.");
					lblUserName.setText("Invalid User");
					lblUserName.setId("invalid");
				} else if (!pf_valid) {
					JOptionPane.showMessageDialog(null, "Password must be at least 4 characters long.");
					lblPassword.setText("Invalid Pass");
					lblPassword.setId("invalid");

					lblUserName.setText("Username");
					lblUserName.setId("");
				} else if (!email_valid) {
					JOptionPane.showMessageDialog(null, "Please enter a valid email.");
					lblEmail.setText("Invalid Email");
					lblEmail.setId("invalid");

					lblUserName.setText("Username");
					lblUserName.setId("");
					lblPassword.setText("Password");
					lblPassword.setId("");
				} else if (!phone_valid) {
					JOptionPane.showMessageDialog(null, "Please enter a 10 digit phone number.");
					lblPhone.setText("Invalid Phone  ");
					lblPhone.setId("invalid");

					lblUserName.setText("Username");
					lblUserName.setId("");
					lblPassword.setText("Password");
					lblPassword.setId("");
					lblEmail.setText("Email");
					lblEmail.setId("");
				} else if (user_already_exists) {
					JOptionPane.showMessageDialog(null, "This username is already registered.");
				} else if (email_already_exists) {
					JOptionPane.showMessageDialog(null, "This email is already associated with an account.");
				} else if (phone_already_exists) {
					JOptionPane.showMessageDialog(null, "This phone number is already associated with an account.");
				} else {
					main.currentUser = txtUserName.getText();
					Connection connection = null;
					try{
						String url = "jdbc:sqlite:../server/db/pmr.db";
						connection = DriverManager.getConnection(url);
						String sql = "INSERT INTO User(Username, Password, Email, PhoneNumber, Keywords) VALUES(?,?,?,?,?)";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, txtUserName.getText());
						preparedStatement.setString(2, pf.getText());
						preparedStatement.setString(3, txtEmail.getText());
						preparedStatement.setString(4, txtPhone.getText());
						preparedStatement.setString(5, "");
						preparedStatement.executeUpdate();
						System.out.println("Connection successful");
					} catch (SQLException e){
						System.out.println(e.getMessage());
					} finally {
						try{
							if (connection != null){
								connection.close();
							}
						} catch (SQLException ex) {
							System.out.println(ex.getMessage());
						}
					}

					System.out.println("account created");
					Stage stage = new Stage();
					PMRStage pmrstage = new PMRStage();
					pmrstage.buildStage(stage);
					primaryStage.close();					


				}


			}
		});

		//Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
		bp.setCenter(gridPane);  

		//Adding BorderPane to the scene and loading CSS
		Scene scene = new Scene(bp);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.titleProperty().bind(
				scene.widthProperty().asString().
				concat(" : ").
				concat(scene.heightProperty().asString()));
		//primaryStage.setResizable(false);
		primaryStage.show();


	}

}