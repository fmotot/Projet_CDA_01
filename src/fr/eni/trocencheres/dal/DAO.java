package fr.eni.trocencheres.dal;

import java.util.List;

import fr.eni.trocencheres.BusinessException;
/**
 * 
 * @author jeanr
 *
 * @param <T>
 */
public interface DAO<T> {

	public List<T> getAll() throws BusinessException;

	public T insertOne(T entity) throws BusinessException;

	public T updateOne(T entity)throws BusinessException;

	public T getOne(T entity)throws BusinessException;

	public T deleteOne(T entity)throws BusinessException;

}
