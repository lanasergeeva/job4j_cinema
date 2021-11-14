package cinema.model;

public class Ticket {
    private int id;
    private int row;
    private int cell;
    private Movie movie;
    private User user;

    public Ticket(int row, int cell, Movie movie, User user) {
        this.row = row;
        this.cell = cell;
        this.movie = movie;
        this.user = user;
    }

    public Ticket(int id, int row, int cell, Movie movie, User user) {
        this.id = id;
        this.row = row;
        this.cell = cell;
        this.movie = movie;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
