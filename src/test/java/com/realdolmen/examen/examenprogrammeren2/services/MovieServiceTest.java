package com.realdolmen.examen.examenprogrammeren2.services;

import com.realdolmen.examen.examenprogrammeren2.exceptions.NoQueryPossibleException;
import com.realdolmen.examen.examenprogrammeren2.services.MovieService;
import com.realdolmen.examen.examenprogrammeren2.repositories.MovieRepository;
import com.realdolmen.examen.examenprogrammeren2.domain.Movie;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    //TODO
    //Opdracht 3 Unit testen met Mockito : Er is al een deel van de service test opgesteld. Alle gegevens die je nodig hebt staan al ingevuld.
    //25 : zorg ervoor dat Mockito gebruikt kan worden, over heel de klasse.
    //26 : annoteer alle methoden met de juiste annotaties, en private attributen (waar nodig), zodat ze aanzien worden als test methoden. Boven sommige methoden staan tips, bekijk ze goed. 
    //27 : tracht alle methoden die hieronder beschreven zijn in te vullen zodat ze slagen. Tips kan je vinden in de methoden zelf.

    private MovieService movieService;
    private List<Movie> movies;
    private Movie movieObjectToTest;
    @Mock
    private MovieRepository movieRepository;

    //TODO
    @Before
    public void init() {
        movieService = new MovieService(movieRepository);
        movies = new ArrayList<>();
        movieObjectToTest = new Movie(1, "comedy", "Ace ventura");
    }

    //TODO maak een test die nagaat of MovieService de juiste methode opvraagt bij MovieRepository, 
    //zorg voor de duidelijke structuur van een test methode
    @Test
    public void findAllMoviesTest() throws Exception {
        when(movieRepository.find(QueryHelper.findAll())).thenReturn(movies);
        List<Movie> resulterino = movieService.findAllMovies();
        assertTrue(resulterino.isEmpty());
        assertEquals(resulterino, movies);
        verify(movieRepository, times(1)).find(QueryHelper.findAll());
        verifyNoMoreInteractions(movieRepository);
    }

    //TODO maak een test die nagaat of de MovieService de juiste methode opvraagt bij MovieRepository
    //Zorg dat MovieRepository een NoQueryPossibleException gooit
    @Test(expected = NoQueryPossibleException.class)
    public void findAllMoviesTestNoQueryPossibleExceptionThrow() throws Exception {
        when(movieRepository.find(QueryHelper.findAll())).thenThrow(NoQueryPossibleException.class);
        List<Movie> resulterino = movieService.findAllMovies();
        verify(movieRepository, times(1)).find(QueryHelper.findAll());
        verifyNoMoreInteractions(movieRepository);
    }

    //TODO maak een test die nagaat of de MovieService de juiste methode opvraagt bij MovieRepository
    //Bekijk de code van MovieRepository.findMovieById() en zorg dat de juiste movie teruggegeven wordt
    @Test
    public void findMovieByIdTest() throws Exception {
        movies.add(movieObjectToTest);
        when(movieRepository.find(QueryHelper.findById(1))).thenReturn(movies);
        Movie resulterino = movieService.findMovieById(1);
        assertEquals(resulterino, movieObjectToTest);
        verify(movieRepository, times(1)).find(QueryHelper.findById(1));
        verifyNoMoreInteractions(movieRepository);
    }

    //TODO Maak een test die ervoor zorgt dat het resultaat van de getAllPalindromeTitles() één of meerdere resultaten kan weergegeven.
    //onthou dat in de methode getAllPalindromeTitles een andere private methode wordt aangesproken die op zijn beurt nog een andere methode van de MovieService aanspreekt
    //zorg dat je er zeker rekening mee houdt dat ook hier ergens MovieRepository iets gevraagd zal worden.
    //vb van movies met palindrome titles = "boob", "aka","dad","ROTOR"
    //Voeg met andere woorden een of meerdere movies toe die een titel hebben dat een palindrome voorsteld
    @Test
    public void getAllPalindromeTitlesTestTitleAddedToList() throws NoQueryPossibleException {
        movies.add(new Movie(2, "saippuakivikauppias", "genre"));
        movies.add(new Movie(3, "title", "genre"));
        movies.add(new Movie(4, "aabbaa", "genre2"));
        when(movieRepository.find(QueryHelper.findAll())).thenReturn(movies);
        List<String> resulterino = movieService.getAllPalindromeTitles();
        assertNotEquals(movies.size(), resulterino.size());
        assertEquals(resulterino.get(0), "saippuakivikauppias");
        assertEquals(resulterino.get(1), "aabbaa");
        assertFalse(resulterino.contains("title"));
        verify(movieRepository, times(1)).find(QueryHelper.findAll());
        verifyNoMoreInteractions(movieRepository);
    }

    //TODO test de methode getAllPalindromeTitles, waarbij de MovieRepository methode die wordt opgeroepen een NoQueryPossibleException gooit
    //kijk goed naar de methodes in MovieService, kijk wat er gebeurd en zorg dat je resultaat net is zoals er verwacht wordt
    //Onthou hierbij dat private methoden niet getest worden, maar de publieke methode 
    @Test
    public void getAllPalindromeTitlesTestNoQueryPossibleExceptionThrownAndCatchedTitleNotAdded() throws NoQueryPossibleException {
        when(movieRepository.find(QueryHelper.findAll())).thenThrow(NoQueryPossibleException.class);
        List<String> resulterino = movieService.getAllPalindromeTitles();
        assertTrue(resulterino.isEmpty());
        verify(movieRepository, times(1)).find(QueryHelper.findAll());
        verifyNoMoreInteractions(movieRepository);
    }

    //TODO test the isAPalindrome method gebruik hiervoor "saippuakivikauppias"
    //nice to know, this is the longest palindrome according to the guiness book of records
    @Test
    public void isAPalinDromeTestTrue() {
        String stringToTest = "saippuakivikauppias";
        assertTrue(movieService.isAPalindrome(stringToTest));
    }

}
