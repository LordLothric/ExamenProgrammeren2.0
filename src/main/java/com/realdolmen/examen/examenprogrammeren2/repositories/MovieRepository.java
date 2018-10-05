/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.examen.examenprogrammeren2.repositories;

import com.realdolmen.examen.examenprogrammeren2.exceptions.NoQueryPossibleException;
import com.realdolmen.examen.examenprogrammeren2.domain.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDOAX36
 */
public class MovieRepository {

    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/movie_db?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String ID = "id";
    private static final String GENRE = "genre";
    private static final String TITLE = "title";

    public MovieRepository() {
        this(URL);
    }

    //only for adding a test url
    protected MovieRepository(String url) {
        URL = url;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    public List<Movie> find(String query) throws NoQueryPossibleException {
        List<Movie> listToFill = null;
        try (Connection connection = createConnection()) {
            PreparedStatement pstatement = connection.prepareStatement(query);
            ResultSet resultSet = pstatement.executeQuery();
            listToFill = new ArrayList<>();
            while (resultSet.next()) {
                listToFill.add(createMovie(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoQueryPossibleException();
        }
        return listToFill;
    }


    private Movie createMovie(ResultSet resultSet) throws NoQueryPossibleException {
        Movie movie = null;
        try {
            movie = new Movie();
            movie.setId(resultSet.getInt(ID));
            movie.setTitle(resultSet.getString(TITLE));
            movie.setGenre(resultSet.getString(GENRE));
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new NoQueryPossibleException();
        }
        return movie;
    }
}
