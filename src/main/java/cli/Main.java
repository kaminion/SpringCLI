package cli;

import cli.config.AppConfig;
import cli.controller.MemberController;
import cli.dao.MemberDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        MemberDao dao = context.getBean(MemberDao.class);
        dao.createTable();

        System.out.println("====================사용자의 username password를 입력해주세요===================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("username : ");
        String username = scanner.nextLine();
        System.out.println("password : ");
        String password = scanner.nextLine();

        MemberController controller = context.getBean(MemberController.class);
        controller.insert(username, password);
        controller.print();

//        try{
//            dao.insert(username, password);
//        }catch(SQLException e)
//        {
////            dao.print();
//        }
//        dao.templatePrint();

        context.close();

    }
}
