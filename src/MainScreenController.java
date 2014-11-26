import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import model.Folder;
import model.FolderType;
import model.Game;
import model.Tag;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainScreenController
{
	@FXML
	private Button editTagButton;
	@FXML
	private Button createTagButton;
	@FXML
	private Button editFolderButton;
	@FXML
	private Button createFolderButton;
	@FXML
	private Button deleteTagButton;
	@FXML
	private Button deleteFolderButton;
	@FXML
	private MenuItem close;
	@FXML
	private TableView<Folder> folderTable;
	@FXML
	private TableView<Tag> tagTable;
	@FXML
	private TableColumn<Folder, String> folderName;
	@FXML
	private TableColumn<Folder, String> folderType;
	@FXML
	private TableColumn<Tag, String> tagName;
	@FXML
	private TableColumn<Tag, String> games;
	@FXML
	private TableView<Game> gameTable;
	@FXML
	private TableColumn<Game, String> gameNameColumn;
	@FXML
	private TableColumn<Game, String> primaryTag;
	@FXML
	private TableColumn<Game, String> secondaryTag;
	private Runner main;
	private ArrayList<Folder> folders;
	private ArrayList<Tag> tags;
	private ArrayList<Game> gameList;

	@FXML
	void initialize()
	{
		close.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				main.close();
			}
		});
		createFolderButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					main.showFolder(new Folder("", FolderType.TAG));
				} catch(IOException e)
				{
					e.printStackTrace();
				}
				setFolders(main.getFolders());
			}
		});
		editFolderButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					Folder select = folderTable.getSelectionModel().getSelectedItem();
					if(select != null)
						main.showFolder(new Folder("", FolderType.TAG));
					else event.consume();
				} catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		createTagButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				try
				{
					main.showTag(new Tag(""));
				} catch(IOException e)
				{
					e.printStackTrace();
				}
				setTags(main.getTags());
			}
		});
		editTagButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					Tag select = tagTable.getSelectionModel().getSelectedItem();
					if(select != null)
						main.showTag(select);
					else event.consume();
				} catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		deleteTagButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Tag select = tagTable.getSelectionModel().getSelectedItem();
				if(select != null)
					tagTable.getItems().remove(select);
				else event.consume();
			}
		});
		folderName
				.setCellValueFactory(new PropertyValueFactory<Folder, String>(
						"name"));
		folderType
				.setCellValueFactory(new PropertyValueFactory<Folder, String>(
						"type"));
		tagName.setCellValueFactory(new PropertyValueFactory<Tag, String>(
				"name"));
		games.setCellValueFactory(new PropertyValueFactory<Tag, String>(
				"gameNames"));
		gameNameColumn
				.setCellValueFactory(new PropertyValueFactory<Game, String>(
						"name"));
		primaryTag.setCellValueFactory(new PropertyValueFactory<Game, String>(
				"primaryTag"));
		secondaryTag
				.setCellValueFactory(new PropertyValueFactory<Game, String>(
						"secondaryTag"));
	}

	public void setMain(Runner main)
	{
		this.main = main;
	}

	public void setFolders(ArrayList<Folder> folders)
	{
		this.folders = folders;
		ArrayList<String> toShow = new ArrayList<String>();
		for(Folder f: folders)
		{
			toShow.add(f.nameProperty().get());
		}
		folderTable.setItems(FXCollections.observableArrayList(folders));
	}

	public void setTags(ArrayList<Tag> tags)
	{
		this.tags = tags;
		HashSet<String> toShow = new HashSet<String>();
		for(Tag t: tags)
		{
			toShow.add(t.nameProperty().get());
		}
		tagTable.setItems(FXCollections.observableArrayList(tags));
	}

	public void setGamesList(ArrayList<Game> list)
	{
		this.gameList = list;
		gameTable.setItems(FXCollections.observableArrayList(gameList));
	}
}