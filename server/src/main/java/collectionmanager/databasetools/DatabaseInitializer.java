package collectionmanager.databasetools;

import collectionmanager.CollectionInitializer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class DatabaseInitializer implements CollectionInitializer {

    private DatabaseChecker checker;
    private Connection connection;

    private static final String CREATE_MAIN_TABLE = "CREATE TABLE MUSICBANDS (" +
                        "id INTEGER NOT NULL," +
                        "name VARCHAR NOT NULL," +
                        "coordinate_X INTEGER NOT NULL," +
                        "coordinate_Y DOUBLE NOT NULL," +
                        "creation_Date DATE NOT NULL," +
                        "number_Of_Participants INTEGER NOT NULL," +
                        "singles_Count INTEGER," +
                        "music_Genre VARCHAR," +
                        "label VARCHAR" +
                        "owner VARCHAR" +
                        ")";
    private static final String CREATE_ID_SEQUENCE = "CREATE SEQUENCE MUSICBAND_IDS";
    private static final String CREATE_INIT_DATE_TABLE = "CREATE TABLE MUSICBANDS_INITIALIZATION_DATE (initialization_Date DATE NOT NULL)";
    private static final String SET_INIT_DATE = "INSERT INTO MUSICBANDS_INITIALIZATION_DATE VALUES(?)";

    public DatabaseInitializer(DatabaseChecker checker, Connection connection) {
        this.checker = checker;
        this.connection = connection;
    }

    @Override
    public void init() throws SQLException {
        if (!checker.checkMainTable()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MAIN_TABLE);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(CREATE_ID_SEQUENCE);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(CREATE_INIT_DATE_TABLE);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SET_INIT_DATE);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();
        }
    }

}
