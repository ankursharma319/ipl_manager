package service.dao.impl;

import java.util.List;

import model.team.Team;

import org.hibernate.Session;

import service.dao.GenericDAO;
import util.database.HibernateUtils;

public class TeamDAO implements GenericDAO<Team>
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
	
	public TeamDAO()
	{
		
	}

	@Override
	public List<Team> getAll()
	{		
		beginCommunication();
		List<Team> l = session.createQuery("from Team").list();
		closeCommunication();
		return l;
	}
}
