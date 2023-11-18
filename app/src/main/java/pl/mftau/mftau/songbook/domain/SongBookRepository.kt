package pl.mftau.mftau.songbook.domain

abstract class SongBookRepository {

    enum class Topic(val value: Int) {
        ALL(0), FAVOURITES(1), SAINT_FRANCIS(2), HOLY_SPIRIT(3),
        COMMUNION(4), WORSHIP(5), ADORATION(6), ATONEMENT(7),
        EASTER(8), ADVENT(9), MARY(10), GIFTS(11), KIDS(12), CAROLS(13)
    }

    protected abstract val songTitles: Array<String>
    protected abstract val songs: Array<String>
    protected abstract val chords: Array<String>
    protected abstract val topics: Map<Topic, List<Int>>

    /*
        mollChords = c, cis, d, dis, e, f, fis, g, gis, a, b, h
        durChords  = C, Cis, D, Dis, E, F, Fis, G, Gis, A, B, H
    */
    fun String.getTransposedChords(valueChange: Int): String =
        this.split("\n").joinToString("\n") { chordsLine ->
            chordsLine.split(" ").joinToString(" ") { chord ->
                if (chord.contains("cis"))
                    chord.replace("cis", if (valueChange == 1) "d" else "c")
                else if (chord.contains("dis"))
                    chord.replace("dis", if (valueChange == 1) "e" else "d")
                else if (chord.contains("fis"))
                    chord.replace("fis", if (valueChange == 1) "g" else "f")
                else if (chord.contains("gis"))
                    chord.replace("gis", if (valueChange == 1) "a" else "g")
                else if (chord.contains("c"))
                    chord.replace("c", if (valueChange == 1) "cis" else "h")
                else if (chord.contains("d"))
                    chord.replace("d", if (valueChange == 1) "dis" else "cis")
                else if (chord.contains("e"))
                    chord.replace("e", if (valueChange == 1) "f" else "dis")
                else if (chord.contains("f"))
                    chord.replace("f", if (valueChange == 1) "fis" else "e")
                else if (chord.contains("g"))
                    chord.replace("g", if (valueChange == 1) "gis" else "fis")
                else if (chord.contains("a"))
                    chord.replace("a", if (valueChange == 1) "b" else "gis")
                else if (chord.contains("b"))
                    chord.replace("b", if (valueChange == 1) "h" else "a")
                else if (chord.contains("h"))
                    chord.replace("h", if (valueChange == 1) "c" else "b")
                else if (chord.contains("Cis"))
                    chord.replace("Cis", if (valueChange == 1) "D" else "C")
                else if (chord.contains("Dis"))
                    chord.replace("Dis", if (valueChange == 1) "E" else "D")
                else if (chord.contains("Fis"))
                    chord.replace("Fis", if (valueChange == 1) "G" else "F")
                else if (chord.contains("Gis"))
                    chord.replace("Gis", if (valueChange == 1) "A" else "G")
                else if (chord.contains("C"))
                    chord.replace("C", if (valueChange == 1) "Cis" else "H")
                else if (chord.contains("D"))
                    chord.replace("D", if (valueChange == 1) "Dis" else "Cis")
                else if (chord.contains("E"))
                    chord.replace("E", if (valueChange == 1) "F" else "Dis")
                else if (chord.contains("F"))
                    chord.replace("F", if (valueChange == 1) "Fis" else "E")
                else if (chord.contains("G"))
                    chord.replace("G", if (valueChange == 1) "Gis" else "Fis")
                else if (chord.contains("A"))
                    chord.replace("A", if (valueChange == 1) "B" else "Gis")
                else if (chord.contains("B"))
                    chord.replace("B", if (valueChange == 1) "H" else "A")
                else if (chord.contains("H"))
                    chord.replace("H", if (valueChange == 1) "C" else "B")
                else ""
            }
        }
}