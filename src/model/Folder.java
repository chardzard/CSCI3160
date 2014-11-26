package model;

import java.util.HashSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Folder implements Comparable<Folder>
{
	private StringProperty name;
	private HashSet<Game> games;
	private FolderType type;
	
	public Folder(String name, FolderType type)
	{
		this.name = new SimpleStringProperty(name);
		games = new HashSet<Game>();
		this.type = type;
	}
	
	public StringProperty nameProperty()
	{
		return name;
	}
	
	public void addGame(Game g)
	{
		games.add(g);
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return this.name.get().equals(((Folder)o).nameProperty().get()) && this.type == ((Folder)o).getType();
	}
	
	public void setType(FolderType type)
	{
		this.type = type;
	}
	
	public FolderType getType()
	{
		return type;
	}

	@Override
	public int compareTo(Folder o)
	{
		return this.name.get().compareTo(((Folder)o).nameProperty().get());
	}
}