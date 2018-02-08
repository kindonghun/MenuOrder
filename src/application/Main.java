package application;
	
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	private final ObservableList<Order> data = FXCollections.observableArrayList();
	private static Stage stage;
	AnchorPane OrderViewPane;
	AnchorPane PaymentViewPane;
	@Override
	public void start(Stage Stage) throws IOException {
		stage= Stage;
//		initPaymentView();
		initOrderView();
		
		Stage.show();
		
	}
	public void AddMenu(String MenuName, String Price) {
	data.add(new Order(MenuName, Price));
		
	}
	public void initOrderView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PersonOverview.fxml"));

			OrderViewPane= (AnchorPane)loader.load();
			stage.setScene(new Scene(OrderViewPane));
			stage.setTitle("주문프로그램");
			Controller con = loader.getController();
			con.setData(data);
			con.setMainApp(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ShowPaymentView() {
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("payment.fxml"));
		
		PaymentViewPane = (AnchorPane)loader.load();
		OrderViewPane.getChildren().add(PaymentViewPane);
		
		PaymentController con = loader.getController();
		con.setData(data);
		con.setMainApp(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ShowOrderView() {
		OrderViewPane.getChildren().remove(PaymentViewPane);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
