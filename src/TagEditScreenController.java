import java.util.HashSet;
import model.Game;
import model.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TagEditScreenController
{
	@FXML
	private TableView<Game> gameTable;
	@FXML
	private TableColumn<Game, String> gameTitleColumn;
	@FXML
	private TableColumn<Game, Boolean> selectColumn;
	@FXML
	private TextField tagNameField;
	@FXML
	private Button save;
	@FXML
	private Button cancel;
	private Runner main;
	private HashSet<Game> listOfGames;
	private Tag currentTag;

	@FXML
	void initialize()
	{
		gameTitleColumn
				.setCellValueFactory(new PropertyValueFactory<Game, String>(
						"name"));
		selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
		selectColumn
				.setCellFactory(new Callback<TableColumn<Game, Boolean>, TableCell<Game, Boolean>>()
				{
					public TableCell<Game, Boolean> call(
							TableColumn<Game, Boolean> p)
					{
						return new CheckBoxTableCell<Game, Boolean>();
					}
				});
		selectColumn.setEditable(true);
		gameTable.setEditable(true);
		save.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				Node  source = (Node)  arg0.getSource(); 
			    Stage stage  = (Stage) source.getScene().getWindow();
			    stage.close();
			}
		});
		cancel.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				Node  source = (Node)  arg0.getSource(); 
			    Stage stage  = (Stage) source.getScene().getWindow();
			    stage.close();
			}
		});
	}

	public void setMain(Runner main)
	{
		this.main = main;
	}

	public Runner getMain()
	{
		return main;
	}
	
	public HashSet<Game> getListOfGames()
	{
		return listOfGames;
	}

	public void setListOfGames(HashSet<Game> listOfGames)
	{
		this.listOfGames = listOfGames;
		ObservableList<Game> table = FXCollections
				.observableArrayList(listOfGames);
		gameTable.setItems(table);
	}
	
	public void setCurrentTag(Tag t)
	{
		currentTag = t;
		tagNameField.setText(currentTag.nameProperty().get());
		for(Game g: currentTag.getGames())
		{
			for(int i = 0; i<gameTable.getItems().size(); i++)
			{
				if(gameTable.getItems().get(i).equals(g))
				{
					gameTable.getItems().get(i).selectProperty().set(true);
				}
			}
		}
	}
}
