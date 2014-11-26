package model;

import java.util.HashSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tag implements Comparable<Tag>
{
	private StringProperty name;
	private HashSet<Game> games;
	private StringProperty gameNames;
	
	public Tag(String name)
	{
		this.name = new SimpleStringProperty(name);
		this.games = new HashSet<Game>();
		this.gameNames = new SimpleStringProperty("");
	}
	
	public StringProperty nameProperty() 
	{
		return name;
	}
	
	public StringProperty gameNamesProperty()
	{
		return gameNames;
	}

	public void addGame(Game g)
	{
		games.add(g);
		String temp = gameNames.get();
		if(!temp.equals(""))
			temp+=", "+g.nameProperty().get();
		else temp +=g.nameProperty().get();
		gameNames.set(temp);
	}
	
	public HashSet<Game> getGames()
	{
		return games;
	}
	
	@Override
	public int compareTo(Tag t)
	{
		return this.name.get().compareTo(t.nameProperty().get());
	}

	@Override
	public boolean equals(Object o)
	{
		if(o==null)
			return false;
		return this.name.get().equals(((Tag)o).nameProperty().get());
	}
}
