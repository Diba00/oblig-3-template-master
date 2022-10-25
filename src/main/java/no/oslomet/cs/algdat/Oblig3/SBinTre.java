package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {

        //kopiert fra kompendiet la til 'p = new Node<T>(verdi,null,null,p);'

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        //la til verdi,null,null,p fordi i konstuktøren har vi 4 parametere så fylte bare inn
        p = new Node<T>(verdi,null,null,q);                   // oppretter en ny node, med v og h lik null

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q


        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        Node<T> rotNode = rot; //oppretter rot node
        int antall = 0;

        if (verdi==null)return 0;

        while (rotNode != null) { //når p ikke er null

            int cmp = comp.compare(verdi, rotNode.verdi); //vi sammenligner verdien vi vil finne med verdiene som allerede finnes i treet
            if (verdi==rotNode.verdi)antall++; //om funnet øker vi antallet
            if (cmp < 0) rotNode = rotNode.venstre; //hvis den du sammenligner er mindre enn 0, går vi til den venstre i treet
            else if (cmp >= 0) rotNode = rotNode.høyre; //hvis den du sammenligner er større eller LIK enn  0, går vi til den høyre i treet

        }

        return antall; //returnerer antallet

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //inspirasjon fra 5.1.7 i kompendiet! kode 5.1.7 h)

        while (true)
        {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }
       //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        Node<T> f = p.forelder;

        if(f == null)
            return null; //hvis p ikke har en forelder (p er rotnoden) er neste null

        if(p == f.høyre)//Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
            return f;

        if(p == f.venstre && f.høyre == null) //Hvis p er venstre barn til sin forelder f, gjelder:
                                            // Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
            return f;

        return førstePostorden(f.høyre); //Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden
                                         // som kommer først i postorden i subtreet med f.høyre som rot.
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {

        Node<T> p = førstePostorden(rot); //Første noden i postorden rekkefølge

        while (p != null){ // så lenge p ikke er null

            oppgave.utførOppgave((p.verdi)); //lagrer verdien
            p=nestePostorden(p); //så går vi til neste node i postorden rekkefølge
        }

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        if (p.høyre!=null){ //sjekker om høyre ikke er null
            postordenRecursive(p.høyre,oppgave); //hvis den ikke er null kan vi gå videre og kalle på metoden
        }

        if (p.venstre!= null) { //sjekker om venstre ikke er null
            postordenRecursive(p.venstre, oppgave); //hvis den ikke er null kan vi gå videre og kalle på metoden
        }

        oppgave.utførOppgave(p.verdi); //om alt funker, ingen nullverdier og metoden er blitt kalt på lagrer vi så verdien p.verdi i oppgave



       // throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

} // ObligSBinTre
