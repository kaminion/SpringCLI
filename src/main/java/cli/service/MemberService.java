package cli.service;

import cli.dao.MemberDao;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@AllArgsConstructor
public class MemberService {
    private MemberDao memberDao;

    // Transaction 을 여기에 붙이는게 편할수도있다.
    @Transactional
    public void insert(String username, String password) throws SQLException
    {
        // 비즈니스 로직

        memberDao.insert(username, password);
    }

    public void print() throws SQLException
    {
        memberDao.templatePrint();
    }

}
