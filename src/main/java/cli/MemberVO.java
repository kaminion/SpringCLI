package cli;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberVO {
    // NonNull은 얠 위한 생성자 RequiredArgsConstructor에만 적용
    private int id;
    @NonNull private String username;
    @NonNull private String password;

    public MemberVO(ResultSet rs)
    {
        try{
            this.id = rs.getInt("id");
            this.username = rs.getString("username");
            this.password = rs.getString("password");
        }catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }

    }

}
