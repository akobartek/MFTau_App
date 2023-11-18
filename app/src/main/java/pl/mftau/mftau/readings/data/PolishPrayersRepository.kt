package pl.mftau.mftau.readings.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import pl.mftau.mftau.readings.domain.PrayersRepository

class PolishPrayersRepository : PrayersRepository() {
    override val prayersNames = arrayOf(
        "Anioł Pański",
        "Antyfona: Święta Maryjo",
        "Błogosławieństwo dla brata Leona",
        "Bogu chwała",
        "Litania Loretańska",
        "Modlitwa franciszkańska",
        "Modlitwa odmówiona przed krucyfiksem",
        "Modlitwa pochwalna",
        "Modlitwy pochwalne odmawiane przy wszystkich godzinach",
        "Pieśń słoneczna",
        "Pozdrowienie Błogosławionej Maryi Dziewicy",
        "Pozdrowienie cnót",
        "Uwielbienie Boga Najwyższego",
        "Wielbimy Cię",
        "Wszechmogący, najświętszy (I)",
        "Wszechmogący, najświętszy (II)",
        "Wszechmogący, wiekuisty",
        "Wykład modlitwy Ojcze nasz",
        "Zachęta do uwielbienia Boga",
    )

    override fun buildPrayersBook(accentColor: Color) = arrayOf(
        buildAnnotatedString {
            withStyle(SpanStyle(color = accentColor)) { append("P. ") }
            append("Anioł Pański zwiastował Pannie Maryi.\n")
            withStyle(SpanStyle(color = accentColor)) { append("W. ") }
            append("I poczęła z Ducha Świętego.\n")
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("Zdrowaś Maryjo, łaski pełna, Pan z Tobą, błogosławionaś Ty między niewiastami, i błogosławiony owoc żywota Twojego, Jezus. ")
                    append("Święta Maryjo, Matko Boża, módl się za nami grzesznymi teraz i w godzinę śmierci naszej. Amen\n")
                }
            }
            withStyle(SpanStyle(color = accentColor)) { append("P. ") }
            append("Oto ja służebnica Pańska.\n")
            withStyle(SpanStyle(color = accentColor)) { append("W. ") }
            append("Niech mi się stanie według słowa Twego.\n\n")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("Zdrowaś Maryjo…\n\n") }
            withStyle(SpanStyle(color = accentColor)) { append("P. ") }
            append("A Słowo ciałem się stało.\n")
            withStyle(SpanStyle(color = accentColor)) { append("W. ") }
            append("I zamieszkało między nami.\n\n")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("Zdrowaś Maryjo…\n\n") }
            withStyle(SpanStyle(color = accentColor)) { append("P. ") }
            appendLine("Módl się za nami, Święta Boża Rodzicielko.")
            withStyle(SpanStyle(color = accentColor)) { append("W. ") }
            appendLine("Abyśmy się stali godnymi obietnic Chrystusowych.")
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                append(
                    "Módlmy się: Boże, przez zwiastowanie anielskie poznaliśmy wcielenie Chrystusa, Twojego Syna, " +
                            "prosimy Cię, wlej w nasze serca swoją łaskę, abyśmy przez Jego mękę i krzyż zostali doprowadzeni " +
                            "do chwały zmartwychwstania. Przez Chrystusa, Pana naszego."
                )
            }
            withStyle(SpanStyle(color = accentColor)) { append("W. ") }
            append("Amen.\n")
        },
        justifiedString(
            "Święta Maryjo, Dziewico, wśród niewiast na świecie nie urodziła się podobna Tobie, Córko i Służebnico " +
                    "najwyższego Króla, Ojca niebieskiego, Matko najświętszego Pana naszego Jezusa Chrystusa, Oblubienico " +
                    "Ducha Świętego: módl się za nami wraz ze św. Michałem Archaniołem i wszystkimi mocami " +
                    "nieba, i wszystkimi świętymi do Twego najświętszego, umiłowanego Syna, Pana i Mistrza.\nChwała Ojcu…\n"
        ),
        justifiedString(
            "Niech ci Pan błogosławi i niech cię strzeże; niech ci ukaże oblicze swoje i zmiłuje się nad tobą.\n" +
                    "Niech zwróci oblicze swoje ku tobie i niech cię obdarzy pokojem.\nPan niech cię błogosławi, bracie Leonie. Amen.\n"
        ),
        "Bogu chwała,\nwiekuista niech będzie chwała,\nhołd Maryi,\ncześć świętym,\npokój żyjącym,\n" +
                "wieczny odpoczynek zmarłym,\nzdrowie chorym,\ngrzesznikom szczera pokuta,\n" +
                "sprawiedliwym w dobrym wytrwanie,\nżeglarzom na morzu spokój,\npodróżującym pomyślna droga.\n",
        buildAnnotatedString {
            append("Kyrie eleison, Christe eleison, Kyrie eleison.\nChryste, usłysz nas. Chryste, wysłuchaj nas.\n\n")
            append("Ojcze z nieba, Boże,")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("   - zmiłuj się nad nami.\n") }
            append("Synu, Odkupicielu świata, Boże,\nDuchu Święty, Boże,\nŚwięta Trójco, Jedyny Boże,\n\n")
            append("Święta Maryjo,")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("   - módl się nad nami.\n") }
            append(
                "Święta Boża Rodzicielko,\nŚwięta Panno nad pannami,\nMatko Chrystusowa,\nMatko Kościoła,\n" +
                        "Matko miłosierdzia,\nMatko łaski Bożej,\nMatko nadziei,\nMatko nieskalana,\n" +
                        "Matko najczystsza,\nMatko dziewicza,\nMatko nienaruszona,\nMatko najmilsza,\n" +
                        "Matko przedziwna,\nMatko dobrej rady,\nMatko Stworzyciela,\nMatko Zbawiciela,\n" +
                        "Panno roztropna,\nPanno czcigodna,\nPanno wsławiona,\nPanno można,\n" +
                        "Panno łaskawa,\nPanno wierna,\nZwierciadło sprawiedliwości,\nStolico mądrości,\n" +
                        "Przyczyno naszej radości,\nPrzybytku Ducha Świętego,\nPrzybytku chwalebny,\n" +
                        "Przybytku sławny pobożności,\nRóżo duchowna,\nWieżo Dawidowa,\nWieżo z kości słoniowej,\n" +
                        "Domie złoty,\nArko przymierza,\nBramo niebieska,\nGwiazdo zaranna,\n" +
                        "Uzdrowienie chorych,\nUcieczko grzesznych,\nPociecho migrantów,\nPocieszycielko strapionych,\n" +
                        "Wspomożenie wiernych,\nKrólowo Aniołów,\nKrólowo Patriarchów,\nKrólowo Proroków,\n" +
                        "Królowo Apostołów,\nKrólowo Męczenników,\nKrólowo Wyznawców,\nKrólowo Dziewic,\n" +
                        "Królowo Wszystkich Świętych,\nKrólowo bez zmazy pierworodnej poczęta,\nKrólowo Wniebowzięta,\n" +
                        "Królowo Różańca świętego,\nKrólowo rodzin,\nKrólowo pokoju,\nKrólowo Polski,\n\n"
            )
            append("Baranku Boży, który gładzisz grzechy świata,\n")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("- przepuść nam, Panie.\n") }
            append("Baranku Boży, który gładzisz grzechy świata,\n")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("- wysłuchaj nas, Panie.\n") }
            append("Baranku Boży, który gładzisz grzechy świata,\n")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("- zmiłuj się nad nami.\n\n") }
            withStyle(SpanStyle(color = accentColor)) { append("P. ") }
            appendLine("Módl się za nami, Święta Boża Rodzicielko.")
            withStyle(SpanStyle(color = accentColor)) { append("W. ") }
            appendLine("Abyśmy się stali godnymi obietnic Chrystusowych.")
            withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                append(
                    "Módlmy się: Boże, przez zwiastowanie anielskie poznaliśmy wcielenie Chrystusa, Twojego Syna, " +
                            "prosimy Cię, wlej w nasze serca swoją łaskę, abyśmy przez Jego mękę i krzyż zostali doprowadzeni " +
                            "do chwały zmartwychwstania. Przez Chrystusa, Pana naszego."
                )
            }
            withStyle(SpanStyle(color = accentColor)) { append("W. ") }
            append("Amen.\n\n")
            append("Pod Twoją obronę…\n")
        },
        "O Panie, uczyń z nas narzędzia swojego pokoju,\nAbyśmy siali miłość tam, gdzie panuje nienawiść;\n" +
                "Wybaczenie tam, gdzie panuje krzywda;\nWiarę tam, gdzie panuje zwątpienie;\n" +
                "Nadzieję tam, gdzie panuje rozpacz;\nŚwiatło tam, gdzie panuje mrok;\n" +
                "Radość tam, gdzie panuje smutek.\nSpraw, abyśmy mogli nie tyle szukać pociechy,\n" +
                "Co pociechę dawać,\nNie tyle szukać zrozumienia, co rozumieć,\n" +
                "Nie tyle szukać miłości, co kochać.\nAlbowiem dając – otrzymujemy,\n" +
                "Wybaczając – zyskujemy przebaczenie,\nA umierając – rodzimy się do wiecznego życia. Amen.\n",
        "Najwyższy, chwalebny Boże, rozjaśnij ciemności mego serca i daj mi, Panie, prawdziwą wiarę, niezachwianą " +
                "nadzieję i doskonałą miłość, zrozumienie i poznanie, abym wypełniał Twoje święte i prawdziwe posłannictwo.\n",
        "Święty, święty, święty jest Pan Bóg wszechmogący, który jest i który był, i który ma przyjść.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                "Godzien, jesteś Panie, Boże nasz, otrzymać sławę, chwałę i cześć, i błogosławieństwo.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                "Godzien jest Baranek, który był zabity, otrzymać moc i bóstwo i mądrość, i siłę, i cześć, i chwalę, i błogosławieństwo.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\nBłogosławmy Ojca i Syna z Duchem Świętym.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\nBłogosławcie, wszystkie dzieła Pańskie, Pana.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                "Dajcie chwałę Bogu naszemu wszyscy słudzy Jego i którzy się Boga boicie, mali i wielcy.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                "Niech Go pełnego chwały chwalą niebo i ziemia.\nI chwalmy, i wywyższajmy Go na wieki.\n\n" +
                "I wszelkie stworzenie, które jest w niebie i na ziemi i które jest pod ziemią i morze, i co jest w nim.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\nChwała Ojcu i Synowi, i Duchowi Świętemu.\n" +
                "I chwalmy, i wywyższajmy Go na wieki.\n\nJaka była na początku i teraz,\n" +
                "i zawsze, i na wieki wieków. Amen.\nI chwalmy, i wywyższajmy Go na wieki.\n",
        justifiedString(
            buildAnnotatedString {
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    appendLine(
                        "\nZaczynają się modlitwy pochwalne, które zebrał święty Ojciec nasz Franciszek i odmawiał je przy wszystkich " +
                                "Godzinach [kanonicznych] dnia i nocy, i przed oficjum Błogosławionej Maryi Dziewicy, tak zaczynając: " +
                                "Najświętszy Ojcze nasz, który jesteś w niebie itd. oraz Chwała. Następnie należy od mawiać modlitwy pochwalne:\n"
                    )
                }
                processIndicesAndText(
                    arrayOf(
                        1 to "Święty, święty, święty, Pan Bóg wszechmogący, który jest i który był, i który ma przyjść (por. Ap 4,8):\n" +
                                "I chwalmy, i wywyższajmy Go na wieki.\n\n",
                        2 to "Godzien jesteś, Panie, Boże nasz, otrzymać sławę, chwałę i cześć, i błogosławieństwo (por. Ap 4,11):\n" +
                                "I chwalmy, i wywyższajmy Go na wieki.\n\n",
                        3 to "Godzien jest Baranek, który był zabity, otrzymać moc i bóstwo, i mądrość, i siłę, " +
                                "i cześć, i chwałę, i błogosławieństwo (Ap 5,12):\nI chwalmy, i wywyższajmy Go na wieki.\n\n",
                        4 to "Błogosławmy Ojca i Syna z Duchem Świętym:\nI chwalmy, i wywyższajmy Go na wieki.\n\n",
                        5 to "Błogosławcie, wszystkie dzieła Pańskie, Panu (Dn 3,57):\nI chwalmy, i wywyższajmy Go na wieki.\n\n",
                        6 to "Dajcie chwałę Bogu naszemu wszyscy słudzy Jego i którzy się Boga boicie, mali i wielcy (por. Ap 19,5):\n" +
                                "I chwalmy, i wywyższajmy Go na wieki.\n\n",
                        7 to "Niech Go pełnego chwały chwalą niebo i ziemia (por. Ps 68,35 – R):\nI chwalmy, i wywyższajmy Go na wieki.\n\n",
                        8 to "Wszelkie stworzenie, które jest w niebie i na ziemi, i które jest pod ziemią, " +
                                "i morze i co jest w nim (por. Ap 5,13):\nI chwalmy, i wywyższajmy Go na wieki.\n\n",
                        9 to "Chwała Ojcu i Synowi, i Duchowi Świętemu:\nI chwalmy, i wywyższajmy Go na wieki.\n\n",
                        10 to "Jak była na początku i teraz, i zawsze, i na wieki wieków. Amen.\nI chwalmy, i wywyższajmy Go na wieki.\n\n"
                    )
                ).let { append(it) }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)) {
                    appendLine("Modlitwa")
                }
                processIndicesAndText(
                    arrayOf(
                        11 to "Wszechmogący, najświętszy, najwyższy i największy Boże, wszelkie dobro, największe dobro, " +
                                "całe dobro, który sam jesteś dobry (por. Łk 18,19), Tobie pragniemy oddawać wszelką sławę, " +
                                "wszelką chwałę, wszelką wdzięczność, wszelką cześć, wszelkie błogosławieństwo i wszelkie dobra. " +
                                "Niech się stanie. Niech się stanie. Amen.\n"
                    )
                ).let { append(it) }
            }
        ),
        "Najwyższy, wszechmogący, dobry Panie,\nTwoja jest sława,\nchwała i cześć,\ni wszelkie błogosławieństwo.\n" +
                "Tobie jednemu, Najwyższy, one przystoją\ni żaden człowiek nie jest godny\nwymówić Twego Imienia\n" +
                "Pochwalony bądź, Panie mój,\nze wszystkimi Twymi stworzeniami,\nszczególnie z panem bratem słońcem,\n" +
                "przez które staje się dzień i nas przez nie oświecasz.\nI ono jest piękne i świecące wielkim blaskiem:\n" +
                "Twoim, Najwyższy jest wyobrażeniem.\nPochwalony bądź, Panie mój,\nprzez brata księżyc i gwiazdy,\n" +
                "ukształtowałeś je na niebie jasne\ni cenne, i piękne.\nPochwalony bądź, Panie mój,\n" +
                "przez brata wiatr i przez powietrze,\ni chmury, i pogodę, i każdy czas,\n" +
                "przez które Twoim stworzeniom\ndajesz utrzymanie.\nPochwalony bądź, Panie mój,\n" +
                "przez siostrę wodę,\nktóra jest bardzo pożyteczna i pokorna,\ni cenna, i czysta.\n" +
                "Pochwalony bądź, Panie mój,\nprzez brata ogień,\nktórym rozświetlasz noc:\n" +
                "i jest on piękny, i radosny,\ni krzepki, i mocny.\nPochwalony bądź, Panie mój,\n" +
                "przez siostrę naszą matkę ziemię\nktóra nas żywi i chowa,\nwydaje różne owoce\n" +
                "z barwnymi kwiatami i trawami.\nPochwalony bądź, Panie mój,\nprzez tych, którzy przebaczają dla Twej miłości\n" +
                "i znoszą słabości i prześladowania.\nBłogosławieni ci,\nktórzy je zniosą w pokoju,\n" +
                "ponieważ przez Ciebie, Najwyższy,\nbędą uwieńczeni.\nPochwalony bądź, Panie mój,\n" +
                "przez naszą siostrę śmierć cielesną,\nktórej żaden człowiek żywy uniknąć nie może.\n" +
                "Biada tym,\nktórzy umierają w grzechach śmiertelnych;\nBłogosławieni ci,\n" +
                "których śmierć zastanie w Twej najświętszej woli,\nalbowiem śmierć druga\nnie wyrządzi im krzywdy.\n" +
                "Chwalcie i błogosławcie mojego Pana,\ni dziękujcie Mu, i służcie\nz wielką pokorą.\n",
        "Bądź pozdrowiona, Pani, święta Królowo,\nświęta Boża Rodzicielko, Maryjo,\n" +
                "która jesteś Dziewicą, uczynioną Kościołem\ni wybraną przez najświętszego Ojca z nieba,\n" +
                "Ciebie On uświęcił z najświętszym,\numiłowanym Synem swoim\n" +
                "i Duchem Świętym, Pocieszycielem,\nw Tobie była i jest\n" +
                "wszelka pełnia łaski\ni wszelkie dobro.\n" +
                "Bądź pozdrowiona, Pałacu Jego,\nBądź pozdrowiona, Przybytku Jego,\n" +
                "Bądź pozdrowiona, Domu Jego.\nBądź pozdrowiona, Szato Jego,\n" +
                "Bądź pozdrowiona, Służebnico Jego,\nBądź pozdrowiona, Matko Jego.\n" +
                "I wy wszystkie święte cnoty,\nktóre Duch Święty\nswą łaską i oświeceniem\n" +
                "wlewa w serca wiernych,\nabyście z niewiernych\nuczyniły wiernych Bogu.\n",
        justifiedString(
            "Bądź pozdrowiona, Królowo Mądrości, Pan niech cię strzeże z twą siostrą, świętą, czystą Prostotą.\n\n" +
                    "Pani święte Ubóstwo, Pan niech cię strzeże z twą siostrą, świętą Pokorą.\n\n" +
                    "Pani święta Miłości, Pan niech cię strzeże z twą siostrą, świętym Posłuszeństwem.\n\n" +
                    "Wszystkie najświętsze cnoty, niech was strzeże Pan, od którego się wywodzicie i pochodzicie.\n\n" +
                    "Nie ma w ogóle na całym świecie żadnego człowieka, który mógłby jedną z was posiadać, jeśli wcześniej nie umarłby.\n\n" +
                    "Kto jedną posiada, a innym nie uchybia, posiada wszystkie." +
                    "I kto jednej uchybia, żadnej nie posiada i wszystkim uchybia.\n\n" +
                    "I każda zawstydza wady i grzechy.\n\n" +
                    "Święta Mądrość zawstydza szatana i całą jego przewrotność.\n\n" +
                    "Czysta i święta Prostota zawstydza całą mądrość tego świata i mądrość ciała.\n\n" +
                    "Święte Ubóstwo zawstydza pychę i skąpstwo, i troski tego świata.\n\n" +
                    "Święta Pokora zawstydza pychę i wszystkich ludzi, którzy są ze świata, podobnie i wszystko, co jest ze świata.\n\n" +
                    "Święta Miłość zawstydza wszystkie pokusy diabelskie i cielesne, i wszelką bojaźń cielesną.\n\n" +
                    "Święte Posłuszeństwo zawstydza wszelkie ludzkie " +
                    "i cielesne pożądania, i utrzymuje ciało w umartwieniu, aby było posłuszne duchowi i aby słuchało swego brata, " +
                    "i czyni człowieka poddanym i uległym wszystkim ludziom, i nie tylko samym ludziom, lecz także dzikim " +
                    "i okrutnym zwierzętom, aby mogły z nim czynić, co zechcą, na ile im Pan z wysoka pozwoli.\n",
            firstLineIndent = 0
        ),
        "Ty jesteś Święty Pan Bóg jedyny,\nktóry czynisz cuda.\nTy jesteś mocny,\nTy jesteś wielki,\n" +
                "Ty jesteś najwyższy,\nTy jesteś Królem wszechmogącym,\nOjcze święty,\nKrólu nieba i ziemi.\n" +
                "Ty jesteś w Trójcy jedyny\nPan Bóg nad bogami,\nTy jesteś dobro, wszelkie dobro, najwyższe dobro,\n" +
                "Pan Bóg żywy i prawdziwy.\nTy jesteś miłością, kochaniem;\nTy jesteś mądrością,\n" +
                "Ty jesteś pokorą,\nTy jesteś cierpliwością,\nTy jesteś pięknością,\n" +
                "Ty jesteś łaskawością;\nTy jesteś bezpieczeństwem,\nTy jesteś ukojeniem,\nTy jesteś radością,\n" +
                "Ty jesteś nadzieją naszą i weselem,\nTy jesteś sprawiedliwością,\nTy jesteś łagodnością,\n" +
                "Ty jesteś w pełni wszelkim bogactwem naszym.\nTy jesteś pięknością,\nTy jesteś łaskawością,\n" +
                "Ty jesteś opiekunem,\nTy jesteś stróżem i obrońcą naszym;\nTy jesteś mocą,\n" +
                "Ty jesteś orzeźwieniem.\nTy jesteś nadzieją naszą,\nTy jesteś wiarą naszą,\n" +
                "Ty jesteś miłością naszą,\nTy jesteś całą słodyczą naszą,\nTy jesteś wiecznym życiem naszym:\n" +
                "Wielkim i przedziwnym Panem,\nBogiem wszechmogącym, miłosiernym Zbawicielem.\n",
        "Wielbimy Cię,\nnajświętszy Panie Jezu Chryste,\ntu i we wszystkich kościołach Twoich, które są na całym świecie,\n" +
                "i błogosławimy Tobie,\nżeś przez święty krzyż Twój świat odkupić raczył.\n",
        justifiedString(
            "Wszechmogący, najświętszy i najwyższy Boże, Ojcze święty i sprawiedliwy, Panie, królu nieba i ziemi, " +
                    "dzięki Ci składamy z powodu Ciebie samego, że Twoją świętą wolą i przez jedynego Syna Twego stworzyłeś " +
                    "z Duchem Świętym wszystkie byty duchowe i cielesne, a nas uczyniłeś na obraz i podobieństwo Twoje i umieściłeś w raju.\n\n" +
                    "My zaś upadliśmy z własnej winy.\n\nI dzięki Ci składamy, że jak stworzyłeś nas przez " +
                    "Syna Twego, tak przez świętą miłość Twoją, którą nas umiłowałeś, sprawiłeś, że On, prawdziwy Bóg i prawdziwy " +
                    "Człowiek, narodził się z chwalebnej zawsze Dziewicy, błogosławionej, świętej Maryi i przez Jego " +
                    "krzyż i krew, i śmierć zechciałeś nas wykupić z niewoli.\n\nI dzięki Ci składamy, że Syn Twój przyjdzie w chwale " +
                    "swego majestatu, aby strącić w ogień wieczny przeklętych, którzy nie czynili pokuty i nie poznali Ciebie, " +
                    "i aby powiedzieć wszystkim, którzy Ciebie poznali i uwielbili, i służyli Ci w pokucie: Pójdźcie, błogosławieni " +
                    "Ojca mego, weźcie królestwo, zgotowane wam od początku świata.\n\n" +
                    "A ponieważ my, nędzni i grzeszni, wszyscy razem nie jesteśmy godni wymówić Twego Imienia, błagamy " +
                    "pokornie, aby Pan nasz Jezus Chrystus, Syn Twój umiłowany, który Tobie wielce podoba się, składał Ci " +
                    "za wszystko dzięki wraz z Duchem Świętym Pocieszycielem, tak jak Tobie i Jemu się podoba, On, który Ci " +
                    "za wszystko wystarcza, przez którego tyle dla nas uczyniłeś. Alleluja.\n"
        ),
        "Wszechmogący, najświętszy, najwyższy i największy Boże, " +
                "wszelkie dobro, największe dobro, całe dobro, " +
                "który sam jesteś dobry, Tobie pragniemy oddawać " +
                "wszelką sławę, wszelką chwałę, wszelką wdzięczność, " +
                "wszelką cześć, wszelkie błogosławieństwo i wszelkie dobra.\n" +
                "Niech się stanie. Niech się stanie. Amen.\n",
        "Wszechmogący, wiekuisty, sprawiedliwy i miłosierny Boże, daj nam nędznym czynić dla Ciebie to, " +
                "o czym wiemy, że tego chcesz i chcieć zawsze tego, co się Tobie podoba, abyśmy wewnętrznie oczyszczeni, " +
                "wewnętrznie oświeceni i rozpaleni ogniem Ducha Świętego, mogli iść śladami umiłowanego Syna Twego, " +
                "Pana naszego Jezusa Chrystusa i dojść do Ciebie, Najwyższy, jedynie dzięki Twej łasce, który żyjesz i królujesz, " +
                "i odbierasz hołd w doskonałej Trójcy i prostej Jedności, Bóg wszechmogący przez wszystkie wieki wieków. Amen.\n",
        justifiedString(
            "O Najświętszy Ojcze nasz: Stwórco, Odkupicielu, Pocieszycielu i Zbawicielu nasz.\n\n" +
                    "Który jesteś w niebie: w aniołach i w świętych. Oświecasz ich, aby Cię poznali, bo Ty, Panie jesteś światłością. " +
                    "Rozpalasz ich, aby Cię kochali, bo Ty, Panie, jesteś miłością. Zamieszkujesz w nich i napełniasz, " +
                    "aby ich uszczęśliwić, bo Ty, Panie, jesteś dobrem najwyższym, wiecznym, od którego pochodzi wszelkie " +
                    "dobro, bez którego nie ma żadnego dobra.\n\n" +
                    "Święć się Imię Twoje: niech zajaśnieje w nas poznanie Ciebie, abyśmy poznali, jaka jest " +
                    "szerokość Twoich dobrodziejstw, długość Twoich obietnic, wysokość majestatu i głębokość sądów.\n\n" +
                    "Przyjdź królestwo Twoje: abyś Ty królował w nas przez łaskę i doprowadził nas do Twego królestwa, " +
                    "gdzie będziemy oglądać Cię bez zasłon, miłować doskonale i w radosnym zjednoczeniu cieszyć się Tobą na wieki.\n\n" +
                    "Bądź wola Twoja jak w niebie, tak i na ziemi: abyśmy Cię kochali z całego serca, zawsze o Tobie myśląc; " +
                    "z całej duszy, zawsze za Tobą tęskniąc; całym umysłem, ku Tobie kierując wszystkie nasze intencje, szukając " +
                    "we wszystkim Twojej chwały i ze wszystkich naszych sił, obracając wszystkie nasze siły i władze duszy i ciała " +
                    "na służbę Twej miłości, a nie na co innego; i bliźnich naszych kochajmy jak samych siebie, wszystkich pociągając " +
                    "według sił do Twojej miłości, ciesząc się z dobra innych jak z własnego i współczując w nieszczęściu, i nikomu nie dając żadnego zgorszenia.\n" +
                    "Chleba naszego powszedniego: umiłowanego Syna Twego, Pana naszego Jezusa Chrystusa, daj nam dzisiaj: " +
                    "na pamiątkę i dla zrozumienia, i uczczenia miłości, którą nas darzył, oraz tego, co dla nas powiedział, uczynił i wycierpiał.\n\n" +
                    "I odpuść nam nasze winy: przez Twoje niewysłowione miłosierdzie, przez moc męki umiłowanego Syna " +
                    "Twego i przez zasługi, i wstawiennictwo Najświętszej Dziewicy i wszystkich wybranych Twoich.\n\n" +
                    "Jako i my odpuszczamy naszym winowajcom: a czego nie odpuszczamy w pełni, Ty, Panie, naucz nas " +
                    "w pełni odpuszczać, abyśmy dla Ciebie prawdziwie kochali nieprzyjaciół i modlili się za nich pobożnie " +
                    "do Ciebie, nikomu złem za złe nie oddając, i abyśmy starali się w Tobie wszystkim służyć pomocą.\n\n" +
                    "I nie wódź nas na pokuszenie: ukryte czy jawne, nagłe czy uporczywe.\n\n" +
                    "Ale nas zbaw ode złego: przeszłego, obecnego i przyszłego.\n\nChwała Ojcu…\n"
        ),
        "Bójcie się Pana i cześć mu oddajcie.\nGodzien jest Pan odebrać chwałę i cześć.\n" +
                "Wszyscy, którzy boicie się Pana, chwalcie Go.\nBądź pozdrowiona Maryjo, łaski pełna, Pan z Tobą.\n" +
                "Chwalcie Go niebo i ziemia.\nChwalcie, wszystkie rzeki, Pana.\nBłogosławcie, synowie Boży, Pana.\n" +
                "Oto dzień, który Pan uczynił; radujmy się w nim\ni weselmy.\n" +
                "Alleluja, alleluja, alleluja! Królu Izraela.\nWszelki duch niech chwali Pana.\n" +
                "Chwalcie Pana, bo jest dobry;\nwszyscy, którzy to czytacie, błogosławcie Pana.\n" +
                "Wszystkie stworzenia, błogosławcie Pana.\nWszystkie ptaki powietrzne, chwalcie Pana.\n" +
                "Wszystkie dzieci, chwalcie Pana.\nMłodzieńcy i panny, chwalcie Pana.\n" +
                "Godzien jest Baranek, który został zabity,\nodebrać sławę, chwałę i cześć.\n" +
                "Błogosławiona niech będzie Święta Trójca\ni nierozdzielna Jedność\n" +
                "Święty Michale Archaniele, broń nas w walce. Amen.\n",
    )
}