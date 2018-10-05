package com.realdolmen.examen.examenprogrammeren2.facade;


import com.realdolmen.examen.examenprogrammeren2.exceptions.NoQueryPossibleException;
import com.realdolmen.examen.examenprogrammeren2.domain.Movie;
import com.realdolmen.examen.examenprogrammeren2.repositories.MovieRepository;
import com.realdolmen.examen.examenprogrammeren2.services.MovieService;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieFacade {

    private static MovieService movieService;

    private static void startApp() {
        movieService = new MovieService(new MovieRepository());
        System.out.println("Welkom, wat wilt u doen?");
        System.out.println("1. Alle film titles bekijken?");
        System.out.println("2. Zoek een film op id?");
        System.out.println("3. Bekijk de lijst van films met een palindroom title?");
        System.out.println("4. Close app");
        Scanner in = new Scanner(System.in);
        System.out.print("Uw keuze : ");
        int choice = in.nextInt();
        appChoice(choice);
    }

    private static void goOn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Nieuwe keuze? Y/N ");
        String choice = in.next();
        
        switch (choice.charAt(0)) {
            case 'y':
            case 'Y':
                startApp();
                break;
            default: System.out.println("Goodbye!!");
        }
    }

    private static void appChoice(int i) {
        switch (i) {
            case 1:
                showAllMovies();
                break;
            case 2:
                showMovieById();
                break;
            case 3:
                showPalindrome();
                break;
            default:
                System.out.println("Goodbye");
                ;

        }
    }

    private static void showAllMovies() {
        System.out.println("Lijst met alle films : ");
        try {
            List<Movie> movies = movieService.findAllMovies();
            movies.forEach(m -> System.out.println(m.toString()));
            goOn();
        } catch (NoQueryPossibleException ex) {
            System.err.println("Er is een probleem opgetreden, probeer opnieuw aub");
            startApp();
        }
    }

    private static void showMovieById() {
        Scanner in = new Scanner(System.in);
        System.out.println("Geef het id van de film : ");
        try {
            int id = in.nextInt();
            Movie movie = movieService.findMovieById(id);
            System.out.println(movie.toString());
            goOn();
        } catch (Exception ex) {
            System.err.println("Er is een probleem opgetreden, probeer opnieuw aub");
            startApp();
        }
    }

    private static void showPalindrome() {

        try {
            System.out.println("Alle titles die een palindrome zijn");
            movieService.getAllPalindromeTitles().forEach(s -> System.out.println(s));
            goOn();
        } catch (Exception ex) {
            System.err.println("Er is een probleem opgetreden, probeer opnieuw aub");
            startApp();
        }
    }

    public static void main(String[] args) {
        startApp();
    }

}
