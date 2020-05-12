package fr.eni.trocencheres.dal;

import java.util.List;

public interface DAO<T> {

	public List<T> getAll();

	public T insertOne(T entity);

	public T updateOne(T entity);

	public T getOne(T entity);

	public T deleteOne(T entity);

}
