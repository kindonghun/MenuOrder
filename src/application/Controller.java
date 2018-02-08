package application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	private TableView<Order> table = new TableView<Order>();
	@FXML
	private TableColumn<Order, String> fc;
	@FXML
	private TableColumn<Order, String> sc;
	
	final Button Button = new Button();
	
	private  ObservableList<Order> data;
	
	private Main mainApp; 
	
	@FXML
	private TextField SumPrice;
		
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void setData(ObservableList<Order> data) {
		this.data = data;
		table.setItems(data);
	}
	@FXML
	private void pg() {
		mainApp.AddMenu("±èÄ¡Âî°³", "7000");
		int oldsum =Integer.parseInt(SumPrice.getText());
		SumPrice.setText(String.format("%d",oldsum+7000));
	}
	@FXML
	private void pg1() {
		mainApp.AddMenu("µÈÀåÂî°³", "7000");
		int oldsum =Integer.parseInt(SumPrice.getText());
		SumPrice.setText(String.format("%d",oldsum+7000));
	}
	@FXML
	private void pg2() {
		mainApp.AddMenu("¼øµÎºÎÂî°³", "8000");
		int oldsum =Integer.parseInt(SumPrice.getText());
		SumPrice.setText(String.format("%d",oldsum+8000));
	}
	@FXML
	private void pg3() {
		mainApp.AddMenu("ºÎ´ëÂî°³", "10000");
		int oldsum =Integer.parseInt(SumPrice.getText());
		SumPrice.setText(String.format("%d",oldsum+10000));
	}
	@FXML
	private void pg4() {
		mainApp.AddMenu("¼³··ÅÁ", "7000");
		int oldsum =Integer.parseInt(SumPrice.getText());
		SumPrice.setText(String.format("%d",oldsum+7000));
	}

	@FXML
	private void delete() { // ÇÏ³ª¾¿»èÁ¦µÇ´Â±â´É
		Order selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
		int oldsum =Integer.parseInt(SumPrice.getText());
		int price =Integer.parseInt(selectedItem.getPrice());
		
		SumPrice.setText(String.format("%d",oldsum-price));
	}
	@FXML
	private void btpayment() {
		mainApp.ShowPaymentView();
	}
	@FXML
	private void initialize() {
		table.setItems(data);
		fc.setCellValueFactory(cellData -> cellData.getValue().MenuName1Property());
		sc.setCellValueFactory(cellData -> cellData.getValue().PriceProperty());

	}
	//////////////////////////////////////////////////////

}
