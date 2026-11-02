import java.util.Comparator;

// Ordenamiento personalizado (Comparator): misiones ordenadas alfabeticamente por titulo.
// El orden natural (Comparable) de Mision es por dificultad.
public class ComparadorMisionPorTitulo implements Comparator<Mision> {

    @Override
    public int compare(Mision a, Mision b) {
        return a.getTitulo().compareToIgnoreCase(b.getTitulo());
    }
}
