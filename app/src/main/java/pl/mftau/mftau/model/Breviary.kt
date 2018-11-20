package pl.mftau.mftau.model

data class Breviary(var html: String) {

    companion object {
        const val breviaryUrl = "http://skrzynkaintencji.pl/brewiarz/"
        val beginningValues = arrayOf(
                "<h2><a name=\"wezwanie\">Wezwanie</a></h2>",
                "<h2><a name=\"godzina_czytan\">Godzina Czytań</a></h2>",
                "<h2><a name=\"jutrznia\">Jutrznia</h2>",
                "<h2><a name=\"przedpoludniowa\">Modlitwa przedpołudniowa</a></h2>",
                "<h2><a name=\"poludniowa\">Modlitwa południowa</a></h2>",
                "<h2><a name=\"popoludniowa\">Modlitwa popołudniowa</a></h2>",
                "<h2><a name=\"nieszpory\">Nieszpory</a></h2>",
                "<h2><a name=\"kompleta\">Kompleta</a></h2>"
        )
        const val endValue = "<p></body></html></div>"
    }
}