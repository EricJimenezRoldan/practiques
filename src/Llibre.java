public class Llibre {
    private int id;
    private String títol;
    private String autor;
    private int any;

    public Llibre() {}

    public Llibre(int id, String títol, String autor, int any) {
        this.id = id;
        this.títol = títol;
        this.autor = autor;
        this.any = any;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTítol() {
        return títol;
    }

    public void setTítol(String títol) {
        this.títol = títol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }
}