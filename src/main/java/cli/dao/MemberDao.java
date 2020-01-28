package cli.dao;

import cli.entity.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@PropertySource("classpath:application.properties")
@Slf4j
public class MemberDao {
    private DataSource con;
    private JdbcTemplate jdbcTemplate;

//    public MemberDao(DataSource con)
//    {
//        this.con = con;
//    }
    public MemberDao(JdbcTemplate dataSource)
    {
        this.jdbcTemplate = dataSource;
    }

    // 코드 분리작업
    public void createTable() throws SQLException
    {
//        Statement statement = con.getConnection().createStatement();
//
//        statement.execute("CREATE TABLE member(" +
//                "id INT AUTO_INCREMENT PRIMARY KEY," +
//                "username varchar(255)," +
//                "password varchar(255)" +
//                ")");

        jdbcTemplate.execute("CREATE TABLE member(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username varchar(255)," +
                "password varchar(255)" +
                ")");

    }

//    메서드레벨에서도 선언가능
    @Transactional
    public void insert(String username, String password) throws SQLException {
       //Statement statement = DataSourceUtils.getConnection(con).createStatement();
       // statement.executeUpdate("INSERT INTO member(username, password) VALUES('ahn', '1234')");
        // JDBC 템플릿
        jdbcTemplate.update("INSERT INTO member(username, password) VALUES(?, ?)", username, password);

//        throw new RuntimeException("db Error");
    }

    public void print()
    {
        // 자바 1.7부터 autoCloserable interface 상속하는것은 리소스반환 (try-with resource) multi-catch Exception도 기재할 것
        try{
//            Statement statement = con.getConnection().createStatement(); 이건 다른트랜잭션을 만드는것
            Statement statement = DataSourceUtils.getConnection(con).createStatement(); // 이게 동일 트랜잭션사용임
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

    public void templatePrint()
    {
        List<MemberVO> list = jdbcTemplate.query("SELECt id, username, password from member",
                (RowMapper<MemberVO>)(resultSet, i)-> new MemberVO(resultSet));

        list.forEach(x -> log.info(">>MEMBER : " + x.getUsername()));

    }


}
