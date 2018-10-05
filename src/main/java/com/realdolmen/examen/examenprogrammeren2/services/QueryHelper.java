
package com.realdolmen.examen.examenprogrammeren2.services;

/**
 *
 * @author SDOAX36 The QueryHelper class is a class where you can create complex
 * queries to use inside your repository, from the service layer
 */
public class QueryHelper {

    private static final String FIND_ALL = "SELECT * FROM movies";

    /**
     * @param id 
     * @return {@link String} "select * from movies where id= "
     */
    public static String findById(int id) {
        return "SELECT * FROM movies WHERE id = " + id;
    }

    /**
     * @param  genre 
     * @return {@link String} "SELECT * FROM movies WHERE genre = "
     */
    public static String findByGenre(String genre) {
        return "SELECT * FROM movies WHERE genre = " + genre;
    }

    /**
     * @param title
     * @return {@link String} "SELECT * FROM movies WHERE title like "
     */
    public static String findWhereTitleLike(String title) {
        return "SELECT * FROM movies  WHERE title like = %" + title + "%";
    }

    public static String findAll() {
        return FIND_ALL;
    }
}
