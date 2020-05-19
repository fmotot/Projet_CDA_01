package fr.test;

import java.security.MessageDigest;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bll.BLLFactory;
import fr.eni.trocencheres.bll.UtilisateurManager;
import fr.eni.trocencheres.bo.Utilisateur;

public class TestUtilisateurManagerImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();
		Utilisateur utilisateur = null;
		Utilisateur utilisateur2 = null;
		Utilisateur utilisateur3 = null;
		Utilisateur utilisateur4 = null;
		
		try {
			utilisateur = utilisateurManager.creerCompteUtilisateur("01 23 45 67 89", "35000", "monPseudo", "bob", "TheSponge", "bob@sponge.fr", "34 la rue", "la grande ville", "123456", null);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.getListeCodesErreur());
		}
		
		System.out.println(utilisateur);
		
		
		try {
			utilisateur2 = utilisateurManager.creerCompteUtilisateur("01 23 45 67 88", "35000", "monPseudo", null, "TheSponge", "bob@sponge.fr", "34 la rue", "la grande ville", "123456", null);
		} catch (BusinessException e) {
			System.err.println("nom vide");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println(utilisateur2);
		
		try {
			utilisateur2 = utilisateurManager.creerCompteUtilisateur("01 23 45 67 889", "35000", "monPseudo", "", "TheSponge", "bob@sponge.fr", "34 la rue", "la grande ville", "123456", null);
		} catch (BusinessException e) {
			System.err.println("nom vide");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println(utilisateur2);
		
		try {
			utilisateur2 = utilisateurManager.creerCompteUtilisateur("01 23 45 67 889", "35000555555555555", null, "bob", "TheSponge", "bob@sponge.fr", "34 la rue", "la grande ville", "123456", null);
		} catch (BusinessException e) {
			System.err.println("pseudo vide");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println(utilisateur2);
		
		try {
			utilisateur2 = utilisateurManager.creerCompteUtilisateur("01 23 45 67 89", "35000", "moi", "moi", "moi", "moi@moche.et.mechant", "34 la rue", "la grande ville", "123456", null);
		} catch (BusinessException e) {
			System.err.println("email pas bon");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println(utilisateur2);
		
		try {
			utilisateur2 = utilisateurManager.creerCompteUtilisateur("01 23 45 67 88", "35000", "moi", "moi", "moi", "moi@moche.et.mechant.gru", "34 la rue", "la grande ville", "123456", null);
		} catch (BusinessException e) {
			System.err.println("email pas bon");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println(utilisateur2);
		
		try {
			utilisateur3 = utilisateurManager.login("bob@sponge.f", "123");
		} catch (BusinessException e) {
			System.err.println("connexion erreur mdp");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println("connexion : " + utilisateur3);
		
		try {
			utilisateur4 = utilisateurManager.login("moa", "123456");
		} catch (BusinessException e) {
			System.err.println("connexion ok");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println("connexion : " + utilisateur4);
		
		try {
			utilisateur3 = utilisateurManager.login("moi", "123");
		} catch (BusinessException e) {
			System.err.println("connexion erreur mdp");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println("connexion : " + utilisateur3);
		
		try {
			utilisateur4 = utilisateurManager.login("moi", "123456");
		} catch (BusinessException e) {
			System.err.println("connexion ok");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println("connexion : " + utilisateur4);
		
		try {
			utilisateur3 = utilisateurManager.login("bob@sponge.fr", "123");
		} catch (BusinessException e) {
			System.err.println("connexion erreur mdp");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println("connexion : " + utilisateur3);
		
		try {
			utilisateur4 = utilisateurManager.login("bob@sponge.fr", "123456");
		} catch (BusinessException e) {
			System.err.println("connexion ok");
			System.err.println(e.getListeCodesErreur());
		}
		System.out.println("connexion : " + utilisateur4);
	}

}
