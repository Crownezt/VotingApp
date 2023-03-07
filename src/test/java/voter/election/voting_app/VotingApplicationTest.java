package voter.election.voting_app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class VotingApplicationTest {

    @Test
    void contextLoads() {
    }

    @Test
    void testDatabaseConnection() {
        DataSource dataSource =
                new DriverManagerDataSource("jdbc:mysql://localhost/", "Ademi","Tochukwu@123");
        try{
            Connection connection = dataSource.getConnection();
            assertThat(connection).isNotNull();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}