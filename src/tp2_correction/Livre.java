package tp2_correction;

import java.util.ArrayList;
public class Livre {
    public String l_title;
    public int l_year;
    public double l_price;
    public String ISBN;
    public String subject;
    public ArrayList<Auteur> auteurs;
    public Livre(String l_title,int l_year,double l_price,String ISBN){
        this.l_title=l_title;
        this.l_year=l_year;
        this.l_price=l_price;
        this.ISBN=ISBN;
        this.auteurs = new ArrayList<Auteur>();
    }
    public Livre(String l_title,int l_year,double l_price,String ISBN, String subject){
        this.l_title=l_title;
        this.l_year=l_year;
        this.l_price=l_price;
        this.ISBN=ISBN;
        this.auteurs = new ArrayList<Auteur>();
        this.subject = subject;
    }
    public void add_auteur(Auteur a){
        this.auteurs.add(a);
    }
}
