package service.dao.impl;

import java.util.List;

import org.hibernate.Session;

import model.tournament.Tournament;
import service.dao.GenericDAO;
import util.database.HibernateUtils;

public class TournamentDAO implements GenericDAO<Tournament>
{
	private Session session;
	private void beginCommunication()
	{
		session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
	}
	
	private void closeCommunication()
	{
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public List<Tournament> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Tournament getFirst() {
		beginCommunication();
		Tournament t = (Tournament)session.get(Tournament.class, 1);
		closeCommunication();
		return t;
	}
	
	public void update(Tournament tournament) {
		beginCommunication();
		session.update(tournament);
		closeCommunication();
	}

}
