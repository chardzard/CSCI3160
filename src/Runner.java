import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Folder;
import model.FolderType;
import model.Game;
import model.Tag;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Runner extends Application
{
	private Stage primaryStage;
	private ArrayList<Game> games;
	private ArrayList<Tag> tags;
	private ArrayList<Folder> folders;

	public static void main(String[] args)
	{
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.primaryStage = primaryStage;
		loadData();
		showMain();
	}

	public void showMain() throws IOException
	{
		FXMLLoader load = new FXMLLoader();
		load.setLocation(Runner.class.getResource("view/MainScreen.fxml"));
		AnchorPane main = (AnchorPane) load.load();
		MainScreenController control = load.getController();
		control.setMain(this);
		control.setFolders(folders);
		control.setTags(tags);
		control.setGamesList(games);
		Scene scene = new Scene(main);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void showFolder(Folder f) throws IOException
	{
		int index = folders.indexOf(f);
		FXMLLoader load = new FXMLLoader();
		load.setLocation(Runner.class.getResource("view/FolderEditScreen.fxml"));
		AnchorPane main = (AnchorPane) load.load();
		FolderEditScreenController control = load.getController();
		control.setMain(this);
		control.setTags(tags);
		control.setFolder(f);
		Scene scene = new Scene(main);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.showAndWait();
		Folder change = control.getFolder();
		if(index != -1)
			folders.set(index, change);
		else if(change == null)
			return;
		else folders.add(change);
	}

	public void showTag(Tag t) throws IOException
	{
		int index = tags.indexOf(t);
		FXMLLoader load = new FXMLLoader();
		load.setLocation(Runner.class.getResource("view/TagEditScreen.fxml"));
		AnchorPane main = (AnchorPane) load.load();
		TagEditScreenController control = load.getController();
		control.setMain(this);
		for(int i = 0; i<games.size(); i++)
			games.get(i).selectProperty().set(false);
		control.setListOfGames(games);
		control.setCurrentTag(t);
		Scene scene = new Scene(main);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.showAndWait();
		Tag change = control.getCurrentTag();
		if(index != -1)
			tags.set(index, change);
		else if(change == null)
			return;
		else tags.add(change);
	}

	public void loadData() throws FileNotFoundException
	{
		games = new ArrayList<Game>();
		tags = new ArrayList<Tag>();
		Scanner file = new Scanner(new File("Games.txt"));
		while(file.hasNext())
		{
			String line = file.nextLine();
			String[] split = line.split("\t");
			Game game = new Game(split[0], split[1], null, false);
			Tag tag = new Tag(split[1]);
			games.add(game);
			if(!tags.contains(tag))
				tags.add(tag);
			for(int i = 0; i < tags.size(); i++)
			{
				if(tags.get(i).equals(tag))
					tags.get(i).addGame(game);
			}
		}
		file.close();
		folders = new ArrayList<Folder>();
		file = new Scanner(new File("Folders.txt"));
		while(file.hasNext())
		{
			String line = file.nextLine();
			Folder folder = new Folder(line, FolderType.TAG);
			folders.add(folder);
		}
		file.close();
	}

	public ArrayList<Tag> getTags()
	{
		return tags;
	}

	public ArrayList<Folder> getFolders()
	{
		return folders;
	}

	public ArrayList<Game> getGames()
	{
		return games;
	}

	public void close()
	{
		Platform.exit();
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
}