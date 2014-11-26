import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
	private HashSet<Game> games;
	private ArrayList<Tag> tags;
	private HashSet<Folder> folders;

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

	public void showFolder() throws IOException
	{
		FXMLLoader load = new FXMLLoader();
		load.setLocation(Runner.class.getResource("view/FolderEditScreen.fxml"));
		AnchorPane main = (AnchorPane) load.load();
		FolderEditScreenController control = load.getController();
		control.setMain(this);
		control.setTags(tags);
		Scene scene = new Scene(main);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.show();
	}

	public void showTag(Tag t) throws IOException
	{
		FXMLLoader load = new FXMLLoader();
		load.setLocation(Runner.class.getResource("view/TagEditScreen.fxml"));
		AnchorPane main = (AnchorPane) load.load();
		TagEditScreenController control = load.getController();
		control.setMain(this);
		control.setListOfGames(games);
		control.setCurrentTag(t);
		Scene scene = new Scene(main);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.show();
	}

	public void loadData() throws FileNotFoundException
	{
		games = new HashSet<Game>();
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
			for(int i = 0; i<tags.size(); i++)
			{
				if(tags.get(i).equals(tag))
					tags.get(i).addGame(game);
			}		
		}
		file.close();
		folders = new HashSet<Folder>();
		file = new Scanner(new File("Folders.txt"));
		while(file.hasNext())
		{
			String line = file.nextLine();
			Folder folder = new Folder(line, FolderType.TAG);
			folders.add(folder);
		}
		file.close();
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