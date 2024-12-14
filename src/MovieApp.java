
import java.util.Arrays;
import java.util.Scanner;

public class MovieApp {

    Movie[] movies = new Movie[0];

    public void press() {
        boolean isRun = true;
        while (isRun) {
            showMenu();
            System.out.println("Press the movie button: ");
            int num = new Scanner(System.in).nextInt();

            switch (num) {
                case 0:
                    isRun = false;
                    break;
                case 1:
                    inputMovies();
                    break;
                case 2:
                    displayMovies(movies);
                    break;
                case 3:
                    addMovies();
                    break;
                case 4:
                    findStatistics();
                    break;
                case 5:
                    searchMovie();
                    break;
                case 6:
                    updateRating();
                    break;
                case 7:
                    deleteMovie();
                    break;
                case 8:
                    sortMovie();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;

            }
            if (!isRun) break;
        }

    }

    public static void showMenu() {
        System.out.println("""
                Press 1: to input movies
                Press 2: to display movies
                Press 3: to add movies
                Press 4: to find statistics
                Press 5: to search movie
                Press 6: to update ratings
                Press 7: to delete movies
                Press 8: to sort movies
                Press 0: to exit
                """
        );
    }

    private void inputMovies() {
        movies = new Movie[3];
        for (int i = 0; i < movies.length; i++) {
            System.out.println("Enter the movie name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Enter the movie rating: ");
            double rating = new Scanner(System.in).nextDouble();
            movies[i] = new Movie(name, rating);
        }
        System.out.println("Added successfully!!");
    }

    private void displayMovies(Movie[] movies) {
        for (Movie movie : movies) {
            System.out.println(movie.getName() + ": " + movie.getRating());
        }
    }

    private void addMovies() {
        System.out.println("Enter the number of movies that you want to add: ");
        int num = new Scanner(System.in).nextInt();
        int length = movies.length;
        Movie[] newMovies = new Movie[num + length];
        System.arraycopy(movies, 0, newMovies, 0, movies.length);

        for (int i = 0; i < num; i++) {
            System.out.println("Enter the movie name " + (i + 1) + ": ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Enter the movie rating "+(i+1)+": ");
            double rating = new Scanner(System.in).nextDouble();
            newMovies[length + i] = new Movie(name, rating);
        }
        movies = newMovies;
        System.out.println("Added successfully!!");
    }

    private void findStatistics() {
        double sum = 0.0;
        Movie minimum = movies[0];
        Movie maximum = movies[0];
        for (Movie movie : movies) {
            sum += movie.getRating();
            if (movie.getRating() < minimum.getRating()) {
                minimum = movie;
            }
            if (movie.getRating() > maximum.getRating()) {
                maximum = movie;
            }
        }
        System.out.println("The average rating of the movie is " + sum / movies.length);
        System.out.println("The minimum rating of the movie is: " + minimum.getName() + " " + minimum.getRating());
        System.out.println("The maximum rating of the movie is: " + maximum.getName() + " " + maximum.getRating());
        System.out.println();
    }

    private int getMovieId(String name) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].getName().equals(name) || movies[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    private void searchMovie() {
        System.out.println("Enter the movie name to search: ");
        String name = new Scanner(System.in).nextLine();
        int id = getMovieId(name);
        if (id >= 0) {
            System.out.println("Movie found: " + movies[id].getName() + " " + movies[id].getRating());
            System.out.println();
        } else {
            System.out.println("Movie not found");
        }
        System.out.println();
    }


    private void updateRating() {
        System.out.println("Enter the movie name to update: ");
        String name = new Scanner(System.in).nextLine();
        int id = getMovieId(name);
        if (id >= 0) {
            System.out.println("Enter the new movie rating: ");
            double rating = new Scanner(System.in).nextDouble();
            movies[id].setRating(rating);
            System.out.println("Movie updated successfully!!");
        } else {
            System.out.println("Movie not found");
        }
        System.out.println();
    }

    private void deleteMovie() {
        System.out.println("Enter the movie name to delete: ");
        String name = new Scanner(System.in).nextLine();
        int id = getMovieId(name);
        if (id >= 0) {
            movies[id] = null;
            int length = movies.length;
            Movie[] newMovies = new Movie[length - 1];
            System.arraycopy(movies, 0, newMovies, 0, id);
            System.arraycopy(movies, id + 1, newMovies, id, length - id - 1);
            movies = newMovies;
            System.out.println("Movie deleted successfully!");
        } else {
            System.out.println("Movie not found");
        }
        System.out.println();
    }

    private void sortMovie() {
        double[] ratings = new double[movies.length];
        for (int i = 0; i < movies.length; i++) {
            ratings[i] = movies[i].getRating();
        }
        bubbleSort(ratings);
        Movie[] newMovies = new Movie[movies.length];
        for (int i = 0; i < movies.length; i++) {
            for (Movie movie : movies) {
                if (ratings[i] == movie.getRating()) {
                    newMovies[i] = movie;
                }
            }
        }
        movies = newMovies;
        System.out.println("Movie sorted successfully! Press 2 to see the result!");
        System.out.println();
    }
    private void bubbleSort(double[] ratings) {
        for (int i = 0; i < ratings.length - 1; i++) {
            for (int j = 0; j < ratings.length - i - 1; j++) {
                if (ratings[j] > ratings[j + 1]) {
                    double temp = ratings[j];
                    ratings[j] = ratings[j + 1];
                    ratings[j + 1] = temp;
                }
            }
        }
    }


}
