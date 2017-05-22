package application;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainController implements Initializable {

	private ObservableList<Menu> list, table;
	private MenuBook menuBook;
	private ConsoleUI consoleUI;
	int total,item = 0;

	@FXML private TableView<Menu> confirmTableView;
	@FXML private TableColumn<Menu, Integer> menuTableColumn1, menuTableColumn3, menuTableColumn4;
	@FXML private TableColumn<Menu, String> menuTableColumn2;
	
	@FXML private TableView<Menu> statusTableView;
	@FXML private TableColumn<Menu, Integer> statusTableColumn1, statusTableColumn3, statusTableColumn4, statusTableColumn5;
	@FXML private TableColumn<Menu, String> statusTableColumn2;
	
	@FXML private Button clear, confirm, checkbill;
	@FXML private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;;
	@FXML private Label totalLabel, itemLabel;

	public MainController() {
		try {
			menuBook = new MenuBook( "EngMenu.csv" );
			consoleUI = new ConsoleUI( menuBook );
			menu();
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}

	private void menu() {
		list = FXCollections.observableArrayList( consoleUI.getOrderList() );
		table = list;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCell();
	}
	
	public void setCell(){
		menuTableColumn1.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("menuID"));
		menuTableColumn2.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuName"));
		menuTableColumn3.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("menuAmount"));
		menuTableColumn4.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("menuCost"));
		confirmTableView.setItems(table);
	}

	@FXML private HBox hBox;

	@FXML private TabPane tabPane;

	@FXML private AnchorPane confirmPane;

	@FXML private AnchorPane confirmTablePane;

	@FXML private AnchorPane totalPane;

	public void confirm(ActionEvent event){

	}

	public void clear(ActionEvent event){
		consoleUI.clearOrderList();
		updateDisplay();
		item = 0;
		setItem(item);
		total = 0;
		setTotal(total);
	}

	public void updateDisplay() {
		menu();
		setCell();
	}

	public void clickMenu(ActionEvent event){
		int index = 0;
		if( isButton(event, button1) ) { }
		else if( isButton(event, button2) ) index = 1;
		else if( isButton(event, button3) ) index = 2;
		else if( isButton(event, button4) ) index = 3;
		else if( isButton(event, button5) ) index = 4;
		else if( isButton(event, button6) ) index = 5;
		else if( isButton(event, button7) ) index = 6;
		else if( isButton(event, button8) ) index = 7;
		else if( isButton(event, button9) ) index = 8;
		else if( isButton(event, button10) ) index = 9;
		addOrder(index);
		totalLabel(callCost(index));
		updateDisplay();
		item++;
		setItem(item);
	}

	public boolean isButton(ActionEvent event, Button button){
		Object choosenButton = event.getSource();
		return choosenButton.equals( button ) ;
	}
	
	public void addOrder(int index){
		consoleUI.AddToOrderList( menuBook.getAllMenuList().get( index ) );
	}
	
	public int callCost(int index){
		return menuBook.getAllMenuList().get(index).getMenuCost();
	}

	public void totalLabel(int cost){
		total += cost;
		setTotal(total);
	}
	
	public void setTotal(int total){
		totalLabel.setText("TOTAL: " + total);
	}
	
	public void setItem(int item){
		itemLabel.setText("ITEM: " + item);
	}

	public void checkbill(ActionEvent event){
		
	}
	@FXML private ProgressIndicator progress;
	
	@FXML private JFXButton test;

	@FXML private Tab menuTab;

	@FXML private Label menuName;
}
