package util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import util.MiscUtilities;

public class HibernateUtils
{
	private static SessionFactory sessionFactory;
	
	public static void init()
	{
		try
		{
			Configuration configuration = new Configuration();
			configuration.configure("/util/database/hibernate.cfg.xml");
			ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			setSessionFactory(configuration.buildSessionFactory(serviceRegistry));
		}
		catch (Exception e)
		{
			MiscUtilities.log("Error configuring database connection. In init() function of HibernateUtils.java: " + e.toString());
			e.printStackTrace();
		}
	}
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	private static void setSessionFactory(SessionFactory sessionFactory)
	{
		HibernateUtils.sessionFactory = sessionFactory;
	}
	
	//Needs to be implemented properly
	public static void getData()
	{
	    try
	    {
			Connection conn = DriverManager.getConnection("jdbc:derby:resources/data/database/IPLManagerDatabase", "root", "");
			Statement st = conn.createStatement();

			ResultSet mrs = conn.getMetaData().getTables(null, null, null, new String[] { "TABLE" });
			while (mrs.next())
			{
			  String tableName = mrs.getString(3);
			  System.out.println("\n\n\n\nTable Name: "+ tableName);

			  ResultSet rs = st.executeQuery("select * from " + tableName);
			  ResultSetMetaData metadata = rs.getMetaData();
			  while (rs.next())
			  {
			    System.out.println(" Row:");
			    for (int i = 0; i < metadata.getColumnCount(); i++)
			    {
			      System.out.println("    Column Name: "+ metadata.getColumnLabel(i + 1)+ ",  ");
			      System.out.println("    Column Type: "+ metadata.getColumnTypeName(i + 1)+ ":  ");
			      Object value = rs.getObject(i + 1);
			      System.out.println("    Column Value: "+value+"\n");
			    }
			  }
			}
		}
	    catch (Exception e)
	    {
			MiscUtilities.log("Exception in HibernateUtils.java getData() function: " + e.toString());
			e.printStackTrace();
		}
	  }
}
