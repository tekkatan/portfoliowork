package nl.cubicated.crudapi;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CrudApiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(CrudApiApplication.class, args);
		initDatabaseUsingSpring(ctx.getBean(DataSource.class));
	}
	
	/*
	 * Init in-memory database with springboot
	 */
	private static void initDatabaseUsingSpring(DataSource ds) {
		try(Connection conn=ds.getConnection()){
			conn.createStatement().execute("insert into tutorials (id,title, description,published) "
					+ "values(1,'tut1','what you will learn',false)");
			System.out.println("Added default row in db");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
