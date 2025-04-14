import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "testtest";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select " +
                    "course_name, " +
                    "COUNT(*) / (MAX(month) - MIN(month) + 1)  AS coefficient " +
                    "from (SELECT " +
                    "course_name, " +
                    "MONTH(subscription_date) month " +
                    "FROM PurchaseList " +
                    "WHERE subscription_date >= '2018-01-01' AND " +
                    "subscription_date < '2019-12-31') t " +
                    "GROUP BY course_name;");

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String coefficient = resultSet.getString("coefficient");

                System.out.println("Курс: " + courseName + " (коэфф. - " + coefficient + ").");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
