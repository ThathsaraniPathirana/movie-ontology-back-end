package ontologie.demo.ws;

public class MoviePOJO {

    private String actor;
    private String actress;
    private String director;
    private String editor;
    private String artDirector;
    private String costumeDesigner;
    private String writer;
    private String genre;
    private String country;
    private String award;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public MoviePOJO() {
    }

    public MoviePOJO(String actor, String actress, String director, String editor, String artDirector, String costumeDesigner, String writer, String genre, String country, String award, String name) {
        this.actor = actor;
        this.actress = actress;
        this.director = director;
        this.editor = editor;
        this.artDirector = artDirector;
        this.costumeDesigner = costumeDesigner;
        this.writer = writer;
        this.genre = genre;
        this.country = country;
        this.award = award;
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getArtDirector() {
        return artDirector;
    }

    public void setArtDirector(String artDirector) {
        this.artDirector = artDirector;
    }

    public String getCostumeDesigner() {
        return costumeDesigner;
    }

    public void setCostumeDesigner(String costumeDesigner) {
        this.costumeDesigner = costumeDesigner;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
