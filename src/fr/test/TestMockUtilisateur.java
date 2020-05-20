package fr.test;

import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.dal.UtilisateurDAO;

public class TestMockUtilisateur implements UtilisateurDAO{
	
	List<Utilisateur> liste = new ArrayList<Utilisateur>();
	
	@Override
	public List<Utilisateur> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur insertOne(Utilisateur entity) {
		liste.add(entity);
		
		return entity;
	}

	@Override
	public Utilisateur updateOne(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getOne(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur deleteOne(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTelephoneExist(String telephone) {
		boolean exist = false;
		for (Utilisateur utilisateur : liste) {
			if (utilisateur.getTelephone().equals(telephone)) {
				exist = true;
				break;
			}
		}
		
		return exist;
	}

	@Override
	public Utilisateur selectByLogin(String login) {
		Utilisateur user = null;
		for (Utilisateur utilisateur : liste) {
			if (utilisateur.getPseudo().equals(login) || utilisateur.getEmail().equals(login)) {
				user = utilisateur;
				break;
			}
		}
		
		return user;
	}

	@Override
	public boolean isPseudoExist(String pseudo) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailExist(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

}
