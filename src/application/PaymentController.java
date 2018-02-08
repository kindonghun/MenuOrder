package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PaymentController {
	private ObservableList<Order> data;
	private static Stage stage;
	private Main mainApp;
	@FXML
	private TableView<Order> table = new TableView<Order>();
	@FXML
	private TableColumn<Order, String> fc;
	@FXML
	private TableColumn<Order, String> sc;

	@FXML
	private TextField SumPrice;

	public void setData(ObservableList<Order> data) {
		this.data = data;
		table.setItems(data);
		SumPrice.setText(String.format("%d", CalcurateSumPrice()));
		// SumPrice.setText(String.format("%d",1000));
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void back() { //뒤로가기
		mainApp.ShowOrderView();
	}

	@FXML
	private void initialize() {
		table.setItems(data);
		fc.setCellValueFactory(cellData -> cellData.getValue().MenuName1Property());
		sc.setCellValueFactory(cellData -> cellData.getValue().PriceProperty());

	}

	@FXML
	public void PaymentPostulat() throws IOException { //주문완료 dialog
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(stage);
		dialog.setTitle("주문완료");
		Parent parent = FXMLLoader.load(getClass().getResource("paymentPostulat.fxml"));
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		// dialog.setResizable(false);
		
		dialog.show();
		
	}

	@FXML
	public void receipt() { //영수증출력
		String menuInvoice = new String();
		
		for(Order order : data) {
			menuInvoice +=String.format("%s, %s원\r\n",order.getMenuName1(),order.getPrice() ) ;
		}
		
		String message = "--------영수증--------\r\n"
						+"매장이름: Java		\r\n"
						+"매장위치: ITBank 11층\r\n"
						+"매장전화: 112		\r\n"
						+"==================\r\n"
						+"음식명	  금액	\r\n"
						+"==================\r\n"
						+menuInvoice+
						"==================\r\n"
						+String.format("부가세:%7.0f원\r\n",+Integer.parseInt(SumPrice.getText())*0.1)
						+String.format("총   액:%s\r\n",SumPrice.getText()+"원")
						+String.format("합   계:%s",SumPrice.getText()+"원");
	
		File file = new File("영수증.txt");
		FileWriter writer = null;

		try {
			// 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
			writer = new FileWriter(file, true);
			writer.write(message);
			writer.flush();
			
			table.setItems(data);
			fc.setCellValueFactory(cellData -> cellData.getValue().MenuName1Property());
			sc.setCellValueFactory(cellData -> cellData.getValue().PriceProperty());
			System.out.println("영수증이출력되었습니다");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private int CalcurateSumPrice() {
		int sum = 0;
		for (Order order : data) {
			if (order != null) {
				sum += Integer.parseInt(order.getPrice());
			}
		}
		return sum;
	}
}
