﻿package test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import dao.DAOException;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;


public abstract class AbstractCatalogueTest {

	protected I_Catalogue cat;
	@Test
	public void testConstructeurCatalogue() {
		assertNotNull("créer catalogue", cat);
	}

	@Test
	public void testAddProduitIProduit_null() throws DAOException {
		I_Produit p1 = null;
		assertFalse("ajout produit null", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_unProduit() throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 1);
		assertTrue("ajout un produit", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_deuxProduits() throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		assertTrue("ajout deux produits", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitConsecutif() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit consécutif", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitNonConsecutif() throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit non consécutif", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomConsecutif() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Treets", 15, 2);
		assertFalse("ajout deux produits avec même nom consécutif", cat.addProduit(p3));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomNonConsecutif() throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Mars", 15, 2);
		assertFalse("ajout deux produits avec même nom non consecutif", cat.addProduit(p3));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomEspacesAuDebut() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit(" Mars", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des espaces au début", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomTabulationsAuDebut()throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("	Mars", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des tabulations au début", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomEspacesALaFin() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Mars ", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des espaces à la fin", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomTabulationsALaFin() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Mars	", 15, 2);
		assertFalse("ajout deux produits avec même nom mais un avec des tabulations à la fin", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_stockNegatif() throws DAOException{
		I_Produit p1 = createProduit("Raider", 10, -1);
		assertFalse("ajout produit avec un stock n€gatif", cat.addProduit(p1));
	}

	@Test
	public void testAddProduitIProduit_stockNul() throws DAOException{
		I_Produit p1 = createProduit("Snickers", 1, 0);
		assertTrue("ajout produit avec un stock nul", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_prixNul()throws DAOException {
		I_Produit p1 = createProduit("Lion", 0, 3);
		assertFalse("ajout produit avec un prix nul", cat.addProduit(p1));
	}	
		
	@Test
	public void testAddProduitIProduit_prixNegatif() throws DAOException{
		I_Produit p1 = createProduit("Bounty", -5, 4);
		assertFalse("ajout produit avec un prix n€gatif", cat.addProduit(p1));
	}	

	
	@Test
	public void testAddProduitStringDoubleInt_unProduit()throws DAOException {
		assertTrue("ajout un produit", cat.addProduit("Mars", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxProduit() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertTrue("ajout deux produits", cat.addProduit("Treets", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomConsecutif() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit consécutif", cat.addProduit("Treets", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomNonConsecutif() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit non consécutif", cat.addProduit("Mars", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomEspacesAuDebut() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec espaces au début", cat.addProduit(" Mars", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomTabulationsAuDebut()throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec tabulations au début", cat.addProduit("	Mars", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomEspacesALaFin() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec espaces à la fin", cat.addProduit("Mars ", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisProduitMemeNomTabulationsALaFin() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertFalse("ajout deux fois même produit mais un avec tabulations à la fin", cat.addProduit("Mars	", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_stockNegatif() throws DAOException{
		assertFalse("ajout produit avec stock n€gatif", cat.addProduit("Raider", 10, -1));
	}

	@Test
	public void testAddProduitStringDoubleInt_stockNul() throws DAOException{
		assertTrue("ajout produit avec stock nul", cat.addProduit("Snickers", 1, 0));
	}

	@Test
	public void testAddProduitStringDoubleInt_prixNul() throws DAOException{
		assertFalse("ajout produit avec prix nul", cat.addProduit("Lion", 0, 3));
	}	
	
	@Test
	public void testAddProduitStringDoubleInt_prixNegatif() throws DAOException{
		assertFalse("ajout produit avec prix n€gatif", cat.addProduit("Bounty", -5, 4));
	}	

	@Test
	public void testAddProduits_null() {
		List<I_Produit> liste = null;
		assertEquals("ajout liste null", 0, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_vide() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		assertEquals("ajout liste vide", 0, cat.addProduits(liste));
	}	

	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueVide() {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec deux produits dans un catalogue vide",2, cat.addProduits(liste));
	}	
	
	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueDejaRempli() throws DAOException{
		I_Produit p0 = createProduit("Twix", 10, 6);
		cat.addProduit(p0);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec deux produits dans un catalogue plein",2, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecUnSeulDoublon() throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec un seul des produits d€j€ dans le catalogue",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecCertainsDoublons()throws DAOException {
		I_Produit p1 = createProduit("Twix", 10, 4);
		I_Produit p2 = createProduit("Bounty", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec plusieurs produits d€j€ dans le catalogue",2, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecQueDesDoublons() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste avec tous les produits d€j€ dans le catalogue",0, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_produitsAvecNomsDoublons() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Treets", 10, 6);
		liste.add(p3);
		liste.add(p4);
		assertEquals("ajout liste produits dont tous les noms dans le catalogue",0, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsEspacesAuDebut() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit(" Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit espaces au début du nom",3, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsTabulationsAuDebut() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("	Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit tabulations au début du nom",3, cat.addProduits(liste));
	}	
	
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsEspacesALaFin() throws DAOException {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars ", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit espace à la fin du nom",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_produitsAvecDoublonsNomProduitsTabulationsALaFin() throws DAOException{
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars ", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		liste.add(p3);
		liste.add(p4);
		liste.add(p5);
		liste.add(p6);
		liste.add(p2);
		liste.add(p4);
		assertEquals("ajout liste avec produit tabulation à la fin du nom",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_avecStocksNegatifs() throws DAOException{
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 15, 2);
		I_Produit p2 = createProduit("Kit Kat", 8, -3);
		I_Produit p3 = createProduit("Lion", 4, 6);
		liste.add(p1);
		liste.add(p2);
		liste.add(p3);
		assertEquals("ajout liste produit avec stock n€gatif",2, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_avecStocksNull() throws DAOException{
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 15, 2);
		I_Produit p2 = createProduit("Snickers", 1, 0);
		I_Produit p3 = createProduit("Lion", 4, 6);
		liste.add(p1);
		liste.add(p2);
		liste.add(p3);
		assertEquals("ajout liste produit avec stock nul",3, cat.addProduits(liste));
	}
	
	@Test
	public void testAddProduits_avecPrixNul()throws DAOException {
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Nuts", 0, 1);
		liste.add(p1);
		assertEquals("ajout liste produit avec prix nul",0, cat.addProduits(liste));
	}
		
	@Test
	public void testAddProduits_avecPrixNegatif() throws DAOException{
		List<I_Produit> liste = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Topset", -8, 3);
		I_Produit p2 = createProduit("Nuts", 4, 6);
		liste.add(p1);
		liste.add(p2);
		assertEquals("ajout liste produit avec prix n€gatif",1, cat.addProduits(liste));
	}

	@Test
	public void testRemoveProduit_existe()throws DAOException {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertTrue("suppression produit existant", cat.removeProduit("Mars"));
	}	
	
	@Test
	public void testRemoveProduit_existePas() throws DAOException{
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("suppression produit qui n'existe pas", cat.removeProduit("Lion"));
	}
		
	@Test
	public void testRemoveProduit_null() throws DAOException{
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("suppression avec un nom null", cat.removeProduit(null));
	}	
		
	@Test
	public void testAcheterProduit_existePas() throws DAOException{
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter produit qui n'existe pas", cat.acheterStock("Nuts", 3));
	}
	
	@Test
	public void testAcheterProduit_existe() throws DAOException{
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertTrue("acheter produit qui existe", cat.acheterStock("Raider", 3));
	}
		
	@Test
	public void testAcheterProduit_quantiteNegative() throws DAOException{
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter quantité n€gative", cat.acheterStock("Mars", -4));
	}	
		
	@Test
	public void testAcheterProduit_quantiteNulle()throws DAOException {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter quantité nulle", cat.acheterStock("Treets", 0));
	}	

	@Test
	public void testVendreProduit_existePas() throws DAOException{
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit qui n'existe pas", cat.vendreStock("Nuts", 3));
	}	
	
	@Test
	public void testVendreProduit_existe() throws DAOException{
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertTrue("vendre produit qui existe", cat.vendreStock("Raider", 1));
	}

	@Test
	public void testVendreProduit_quantiteNegative() throws DAOException{
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre quantité négative", cat.vendreStock("Mars", -4));
	}	
	
	@Test
	public void testVendreProduit_quantiteNulle() throws DAOException{
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre quantité nulle", cat.vendreStock("Treets", 0));
	}
	
	@Test
	public void testVendreProduit_stockNul() throws DAOException{
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit sans stock", cat.vendreStock("Treets", 4));
	}
	
	@Test
	public void testVendreProduit_stockInsuffisant() throws DAOException{
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit stock insuffisant", cat.vendreStock("Raider", 10));
	}
	
	@Test
	public void testGetNomProduits_vide() throws DAOException{
		String[] tab0 = new String[0];
		assertArrayEquals("récupère nom produits catalogue vide", tab0, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduit()throws DAOException {
		String[] tab = {"Mars"};
		cat.addProduit("Mars", 10, 1);
		assertArrayEquals("récupère nom produits avec un seul produit", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesAuDebut() throws DAOException{
		String[] tab = {"Mars"};
		cat.addProduit(" Mars", 10, 1);
		assertArrayEquals("récupère nom produit avec espace debut ; les espaces au début ne doivent pas être stockés", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesALaFin()throws DAOException {
		String[] tab = {"Mars"};
		cat.addProduit("Mars ", 10, 1);
		assertArrayEquals("récupère nom produit avec espace fin ; les espaces à la fin ne doivent pas être stockés", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomEspacesAuMilieu() throws DAOException{
		String[] tab = {"Kit Kat"};
		cat.addProduit("Kit Kat", 10, 1);
		assertArrayEquals("récupère nom produit avec des espace au milieu", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsAuDebut() throws DAOException{
		String[] tab = {"Mars"};
		cat.addProduit("	Mars", 10, 1);
		assertArrayEquals("récupère nom produit avec tabulation debut ; les tabulations au début ne doivent pas être stockés", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsALaFin() throws DAOException{
		String[] tab = {"Mars"};
		cat.addProduit("Mars	", 10, 1);
		assertArrayEquals("récupère nom produit avec tabulation fin ; les tabulations à la fin ne doivent pas être stockés", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduitAvecNomTabulationsAuMilieu() throws DAOException{
		String[] tab = {"Kit Kat"};
		cat.addProduit("Kit	Kat", 10, 1);
		assertArrayEquals("récupère nom produit avec des tabulations au milieu ; les tabulations au milieu doivent être remplac€es par des espaces", tab, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_deuxProduits() throws DAOException{
		String[] tab = {"Mars", "Treets"};
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		assertArrayEquals("récupère nom de deux produits", tab, cat.getNomProduits());
	}
		
	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAlphabetique()throws DAOException {
		String[] tab = {"Mars", "Raider", "Treets"};
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Raider", 12, 2);
		cat.addProduit("Treets", 10, 1);
		assertArrayEquals("récupère nom de plusieurs produits ajoutés dans ordre alphab€tique", tab, cat.getNomProduits());
	}	
		
	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAleatoire() throws DAOException{
		String[] tab = {"Bounty", "Mars", "Raider", "Treets"};
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		cat.addProduit("Bounty", 12, 2);
		assertArrayEquals("récupère nom de plusieurs produits ajoutés dans ordre al€atoire (doivent être retourn€s dans l'ordre alphab€tique)", tab, cat.getNomProduits());
	}
	
	@Test
	public void testMontantTotalTTC_catalogueVide() {
		assertEquals("montant TTC catalogue vide",0,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testMontantTotalTTC_pasDeStock() throws DAOException{
		cat.addProduit("Nuts", 1, 0);
		assertEquals("montant TTC sans stock",0,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testMontantTotalTTC_sansVirgule() throws DAOException{
		cat.addProduit("Mars", 100, 4);
		cat.addProduit("Raider", 20, 5);
		assertEquals("montant TTC sans virgule ",600,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_UnChiffreApresLaVirgule()throws DAOException {
		cat.addProduit("Mars", 10, 6);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 12);
		assertEquals("montant TTC avec virgule ; 1 chiffre",134.4,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_DeuxChiffresApresLaVirgule()throws DAOException {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.6, 1);
		assertEquals("montant TTC avec virgule ; 2 chiffres",135.12,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiInferieur_TroisChiffresApresLaVirgule()throws DAOException {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.66, 1);
		assertEquals("montant TTC avec virgule ; 135.192 doit être arrondi à 135.19",135.19,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule()throws DAOException {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.69, 1);
		assertEquals("montant TTC avec virgule ; 135.228 doit être arrondi à 135.23",135.23,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule_IlNeFautPasArrondirLePrixDuStockUnitaireMaisLePrixDuStockTotal() throws DAOException{
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.67, 1);
		cat.addProduit("Nuts", 12.67, 1);
		assertEquals("c'est le montant total TTC qu'il faut arrondir, pas les prix TTC des diff€rents produits",150.41,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testToString_CatalogueVide() {
		String resultatAttendu = "\n" +
								 "Montant total TTC du stock : 0,00 €";
		assertEquals("toString catalogue vide", resultatAttendu, cat.toString());
	}
	
	@Test	
	public void testToString_CatalogueAvecDesProduits_TotalAvecAucunChiffreApresVirgule()throws DAOException {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 120,00 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		assertEquals("toString catalogue sans virgule", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_AvecDesEspaceDansLesNomsDesProduit() throws DAOException{
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
				 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
				 "Kit Kat - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
				 "\n" +
				 "Montant total TTC du stock : 120,00 €";
		cat.addProduit("Mars ", 10, 5);
		cat.addProduit(" Treets", 10, 4);
		cat.addProduit("Kit Kat", 1, 10);
		assertEquals("toString avec des espaces dans les noms des produits", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_AvecDesTabulationsDansLesNomsDesProduit() throws DAOException{
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
				 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
				 "Kit Kat - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
				 "\n" +
				 "Montant total TTC du stock : 120,00 €";
		cat.addProduit("Mars	", 10, 5);
		cat.addProduit("	Treets", 10, 4);
		cat.addProduit("Kit	Kat", 1, 10);
		assertEquals("toString avec des tabulations dans les noms des produits", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecUnChiffreApresVirgule() throws DAOException{
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,45 € - prix TTC : 12,54 € - quantité en stock : 5" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 182,70 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.45, 5);
		assertEquals("toString catalogue avec un total d'un chiffre après la virgule", resultatAttendu, cat.toString());
	}
		
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecDeuxChiffresApresVirgule() throws DAOException{
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,40 € - prix TTC : 12,48 € - quantité en stock : 1" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 132,48 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.4, 1);
		assertEquals("toString catalogue avec un total de deux chiffres après virgule", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiInferieur() throws DAOException{
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 1" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 132,56 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.47, 1);
		assertEquals("on affiche que deux chiffres après la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calculé avec les prix unitaires TTC non arrondis",resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiSuperieur() throws DAOException{
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 2" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 145,13 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.47, 2);
		assertEquals("on affiche que deux chiffres après la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calculé avec les prix unitaires TTC non arrondis",resultatAttendu, cat.toString());
	}
	
	@Test
	public void testClear() throws DAOException {
		String resultatAttendu = "\nMontant total TTC du stock : 0,00 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.47, 2);
		cat.clear();
		
		assertEquals("On affiche une liste vide vide produits",resultatAttendu, cat.toString());
	}

	private I_Produit createProduit(String nom, double prixHT, int quantite) {
		try {
			return new Produit(nom,prixHT,quantite);
		}
		catch (Exception e) { return null; }
	}		
		
	
}
