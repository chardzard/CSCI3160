package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Game implements Comparable<Game>
{
	private StringProperty name;
	private StringProperty primaryTag;
	private StringProperty secondaryTag;
	private BooleanProperty select;
	
	public Game(String name, String primaryTag, String secondaryTag, Boolean select)
	{
		this.name = new SimpleStringProperty(name);
		this.primaryTag = new SimpleStringProperty(primaryTag);
		this.secondaryTag = new SimpleStringProperty(secondaryTag);
		this.select = new SimpleBooleanProperty(select);
	}

	public StringProperty nameProperty()
	{
		return name;
	}
	
	public StringProperty primaryTagProperty()
	{
		return primaryTag;
	}
	
	public StringProperty secondaryTagProperty()
	{
		return secondaryTag;
	}
	
	public BooleanProperty selectProperty()
	{
		return select;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return this.name.get().equals(((Game)o).nameProperty().get());
	}

	@Override
	public int compareTo(Game o)
	{
		return this.name.get().compareTo(((Game)o).nameProperty().get());
	}
}
