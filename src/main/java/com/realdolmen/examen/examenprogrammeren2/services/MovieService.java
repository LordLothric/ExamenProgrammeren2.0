package com.realdolmen.examen.examenprogrammeren2.services;

import com.realdolmen.examen.examenprogrammeren2.exceptions.NoQueryPossibleException;
import com.realdolmen.examen.examenprogrammeren2.repositories.MovieRepository;
import com.realdolmen.examen.examenprogrammeren2.domain.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieService {

    private MovieRepository movieRepository;
    private List<String> palindromeTitles;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.palindromeTitles = new ArrayList<>();
    }

     /**
     * Call the {@link MovieRepository} 
     * @throws NoQueryPossibleException when the repository can't handle the query
     * @return {@link List} resulting set of {@link Movie} 
     */
    public List<Movie> findAllMovies() throws NoQueryPossibleException {
        return movieRepository.find(QueryHelper.findAll());
    }
     /**
     * Call the {@link MovieRepository} 
     * @throws NoQueryPossibleException when the repository can't handle the query
     * @return {@link List} resulting set of {@link Movie} or null if the {@link Movie} was not found
     */
    public Movie findMovieById(int id) throws NoQueryPossibleException {
        List<Movie> movies = movieRepository.find(QueryHelper.findById(id));
        if (movies.isEmpty()) {
            return null;
        }
        return movies.get(0);
    }

     /** 
     * @return {@link List} resulting set of {@link String} or empty if no {@link Movie} was not found with a palindrome as title
     */
    public List<String> getAllPalindromeTitles() {
        findAllMoviesWithATitleWhoIsAPalindrome();
        return palindromeTitles;
    }

    //Deze functie gaat eerst alle movies ophalen uit de databank, daarna gaat hij checken of er een movie 
    //is die en een titel heeft dat een palindroom is en vervolgens aan de lijst toevoegen, private methode kan niet getest worden!!!
    private void findAllMoviesWithATitleWhoIsAPalindrome() {
        List<Movie> movies;
        try {
            movies = findAllMovies();
            for (Movie m : movies) {
                if (isAPalindrome(m.getTitle())) {
                    palindromeTitles.add(m.getTitle());
                }
            }
        } catch (NoQueryPossibleException ex) {
            //nothing to do here, maybe a message?
            System.out.println("Not added");
        }
    }

    //Ik gebruik protected zodat jullie deze afzonderlijk kunnen testen
    protected boolean isAPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        StringBuilder builder = new StringBuilder(s);
        return s.equals(builder.reverse().toString());
    }

}
