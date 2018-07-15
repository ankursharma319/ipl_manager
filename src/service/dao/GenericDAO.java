package service.dao;

import java.util.List;

public interface GenericDAO<T>
{
	public List<T> getAll();
}