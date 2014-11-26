import java.util.ArrayList;
import java.util.HashSet;
import model.Folder;
import model.FolderType;
import model.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FolderEditScreenController
{
	@FXML
	private Button cancelButton;
	@FXML
	private TextField folderNameField;
	@FXML
	private ComboBox<String> tagSelect;
	@FXML
	private Button saveButton;
	@FXML
	private Label typeOfTagText;
	@FXML
	private RadioButton dynamic;
	@FXML
	private RadioButton tag;
	private Runner main;
	private ArrayList<Tag> tags;
	private Folder currentFolder;

	@FXML
	void initialize()
	{
		tag.selectedProperty().set(true);
		currentFolder.setType(FolderType.TAG);
		saveButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				Node source = (Node) arg0.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			}
		});
		cancelButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				Node source = (Node) arg0.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			}
		});
		dynamic.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				tag.selectedProperty().set(false);
				currentFolder.setType(FolderType.DYNAMIC);
			}
		});
		tag.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				dynamic.selectedProperty().set(false);
				currentFolder.setType(FolderType.TAG);
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

	public ArrayList<Tag> getTags()
	{
		return tags;
	}

	public void setTags(ArrayList<Tag> tags)
	{
		this.tags = tags;
		HashSet<String> strings = new HashSet<String>();
		for(Tag t: tags)
		{
			strings.add(t.nameProperty().get());
		}
		ObservableList<String> menu = FXCollections
				.observableArrayList(strings);
		tagSelect.setItems(menu);
	}

	public void setFolder(Folder currentFolder)
	{
		this.currentFolder = currentFolder;
		if(currentFolder.getType() == FolderType.TAG
				|| currentFolder.getType() == FolderType.NONE)
		{
			tag.selectedProperty().set(true);
			dynamic.selectedProperty().set(false);
		}
		else
		{
			dynamic.selectedProperty().set(true);
			tag.selectedProperty().set(false);
		}
	}
}
