package cli;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@PropertySource("classpath:application.properties")
@Slf4j
public class Dao {
    private DataSource con;

    public Dao(DataSource con)
    {
        this.con = con;
    }

    // 코드 분리작업
    public void createTable() throws SQLException
    {
        Statement statement = con.getConnection().createStatement();

        statement.execute("CREATE TABLE member(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username varchar(255)," +
                "password varchar(255)" +
                ")");

    }


    public void insert() throws SQLException {
        Statement statement = con.getConnection().createStatement();

        statement.executeUpdate("INSERT INTO member(username, password) VALUES('ahn', '1234')");
//        throw new RuntimeException("db Error");
    }

    public void print()
    {
        // 자바 1.7부터 autoCloserable interface 상속하는것은 리소스반환 (try-with resource) multi-catch Exception도 기재할 것
        try{
            Statement statement = con.getConnection().createStatement();
            ResultSet rs  = statement.executeQuery("SELECT id, username, password FROM member");

            while(rs.next())
            {
//                int id = rs.getInt("id");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
                MemberVO member = new MemberVO(rs);

                log.info(member.toString());
                System.out.println(member.toString());
            }

        } catch (SQLException clE ) {
            clE.printStackTrace();
        }
        log.info("Hello World!!");

    }

}
