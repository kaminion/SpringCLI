package cli;

import cli.config.AppConfig;
import cli.dao.Dao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        Dao dao = context.getBean(Dao.class);
        dao.createTable();

        try{
            dao.insert();
        }catch(SQLException e)
        {
            dao.print();
        }
        dao.print();

        context.close();

    }
}
