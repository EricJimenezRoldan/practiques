import java.io.*;

class PR132persona implements Serializable {
    private String Nom;
    private String Cognom;
    private int Edat;

    public PR132persona(String Nom, String Cognom, int Edat) {
        this.Nom = Nom;
        this.Cognom = Cognom;
        this.Edat = Edat;
    }

    // Métodos getter para acceder a los atributos
    public String getNom() {
        return Nom;
    }

    public String getCognom() {
        return Cognom;
    }

    public int getEdat() {
        return Edat;
    }
}







