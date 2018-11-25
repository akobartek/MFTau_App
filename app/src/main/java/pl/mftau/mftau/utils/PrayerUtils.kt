package pl.mftau.mftau.utils

object PrayerUtils {

    val prayerBook = arrayOf(
            "O Panie, uczyń z nas narzędzia swojego pokoju,\n" +
                    "Abyśmy siali miłość tam, gdzie panuje nienawiść;\n" +
                    "Wybaczenie tam, gdzie panuje krzywda;\n" +
                    "Wiarę tam, gdzie panuje zwątpienie;\n" +
                    "Nadzieję tam, gdzie panuje rozpacz;\n" +
                    "Światło tam, gdzie panuje mrok;\n" +
                    "Radość tan, gdzie panuje smutek.\n" +
                    "Spraw, abyśmy mogli nie tyle szukać pociechy,\n" +
                    "Co pociechę dawać,\n" +
                    "Nie tyle szukać zrozumienia, co rozumieć," +
                    "Nie tyle szukać miłości, co kochać.\n" +
                    "Albowiem dając – otrzymujemy,\n" +
                    "Wybaczając – zyskujemy przebaczenie,\n" +
                    "A umierając – rodzimy się do wiecznego życia. Amen.\n\n",
            "Wielbimy Cię, *\nnajświętszy Panie Jezu Chryste, *\ntu i we wszystkich kościołach Twoich, które są na całym świecie, *\n" +
                    "i błogosławimy Tobie, *\nżeś przez święty krzyż Twój świat odkupić raczył.\n\n",
            "Najwyższy, wszechmogący, dobry Panie,\n" +
                    "Twoja jest sława,\n" +
                    "chwała i cześć,\n" +
                    "i wszelkie błogosławieństwo.\n" +
                    "Tobie jednemu, Najwyższy, one przystoją\n" +
                    "i żaden człowiek nie jest godny\n" +
                    "wymówić Twego Imienia\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "ze wszystkimi Twymi stworzeniami,\n" +
                    "szczególnie z panem bratem słońcem,\n" +
                    "przez które staje się dzień i nas przez nie oświecasz.\n" +
                    "I ono jest piękne i świecące wielkim blaskiem:\n" +
                    "Twoim, Najwyższy jest wyobrażeniem.\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "przez brata księżyc i gwiazdy,\n" +
                    "ukształtowałeś je na niebie jasne\n" +
                    "i cenne, i piękne.\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "przez brata wiatr i przez powietrze,\n" +
                    "i chmury, i pogodę, i każdy czas,\n" +
                    "przez które Twoim stworzeniom\n" +
                    "dajesz utrzymanie.\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "przez siostrę wodę,\n" +
                    "która jest bardzo pożyteczna i pokorna,\n" +
                    "i cenna, i czysta.\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "przez brata ogień,\n" +
                    "którym rozświetlasz noc:\n" +
                    "i jest on piękny, i radosny,\n" +
                    "i krzepki, i mocny.\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "przez siostrę naszą matkę ziemię\n" +
                    "która nas żywi i chowa,\n" +
                    "wydaje różne owoce\n" +
                    "z barwnymi kwiatami i trawami.\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "przez tych, którzy przebaczają dla Twej miłości\n" +
                    "i znoszą słabości i prześladowania.\n" +
                    "Błogosławieni ci,\n" +
                    "którzy je zniosą w pokoju,\n" +
                    "ponieważ przez Ciebie, Najwyższy,\n" +
                    "będą uwieńczeni.\n" +
                    "Pochwalony bądź, Panie mój,\n" +
                    "przez naszą siostrę śmierć cielesną,\n" +
                    "której żaden człowiek żywy uniknąć nie może.\n" +
                    "Biada tym,\n" +
                    "którzy umierają w grzechach śmiertelnych;\n" +
                    "Błogosławieni ci,\n" +
                    "których śmierć zastanie w Twej najświętszej woli,\n" +
                    "albowiem śmierć druga\n" +
                    "nie wyrządzi im krzywdy.\n" +
                    "Chwalcie i błogosławcie mojego Pana,\n" +
                    "i dziękujcie Mu, i służcie\n" +
                    "z wielką pokorą.\n\n",
            "Ty jesteś Święty Pan Bóg jedyny,\n" +
                    "który czynisz cuda.\n" +
                    "Ty jesteś mocny,\n" +
                    "Ty jesteś wielki,\n" +
                    "Ty jesteś najwyższy,\n" +
                    "Ty jesteś Królem wszechmogącym,\n" +
                    "Ojcze święty,\n" +
                    "Królu nieba i ziemi.\n" +
                    "Ty jesteś w Trójcy jedyny\n" +
                    "Pan Bóg nad bogami,\n" +
                    "Ty jesteś dobro, wszelkie dobro, najwyższe dobro,\n" +
                    "Pan Bóg żywy i prawdziwy.\n" +
                    "Ty jesteś miłością, kochaniem;\n" +
                    "Ty jesteś mądrością,\n" +
                    "Ty jesteś pokorą,\n" +
                    "Ty jesteś cierpliwością,\n" +
                    "Ty jesteś pięknością,\n" +
                    "Ty jesteś łaskawością;\n" +
                    "Ty jesteś bezpieczeństwem,\n" +
                    "Ty jesteś ukojeniem,\n" +
                    "Ty jesteś radością,\n" +
                    "Ty jesteś nadzieją naszą i weselem,\n" +
                    "Ty jesteś sprawiedliwością,\n" +
                    "Ty jesteś łagodnością,\n" +
                    "Ty jesteś w pełni wszelkim bogactwem naszym.\n" +
                    "Ty jesteś pięknością,\n" +
                    "Ty jesteś łaskawością,\n" +
                    "Ty jesteś opiekunem,\n" +
                    "Ty jesteś stróżem i obrońcą naszym;\n" +
                    "Ty jesteś mocą,\n" +
                    "Ty jesteś orzeźwieniem.\n" +
                    "Ty jesteś nadzieją naszą,\n" +
                    "Ty jesteś wiarą naszą,\n" +
                    "Ty jesteś miłością naszą,\n" +
                    "Ty jesteś całą słodyczą naszą,\n" +
                    "Ty jesteś wiecznym życiem naszym:\n" +
                    "Wielkim i przedziwnym Panem,\n" +
                    "Bogiem wszechmogącym, miłosiernym Zbawicielem.\n\n",
            "Bądź pozdrowiona, Pani, święta Królowo,\n" +
                    "święta Boża Rodzicielko, Maryjo,\n" +
                    "która jesteś Dziewicą, uczynioną Kościołem\n" +
                    "i wybraną przez najświętszego Ojca z nieba,\n" +
                    "Ciebie On uświęcił z najświętszym,\n" +
                    "umiłowanym Synem swoim\n" +
                    "i Duchem Świętym, Pocieszycielem,\n" +
                    "w Tobie była i jest\n" +
                    "wszelka pełnia łaski\n" +
                    "i wszelkie dobro.\n" +
                    "Bądź pozdrowiona, Pałacu Jego,\n" +
                    "Bądź pozdrowiona, Przybytku Jego,\n" +
                    "Bądź pozdrowiona, Domu Jego.\n" +
                    "Bądź pozdrowiona, Szato Jego,\n" +
                    "Bądź pozdrowiona, Służebnico Jego,\n" +
                    "Bądź pozdrowiona, Matko Jego.\n" +
                    "I wy wszystkie święte cnoty,\n" +
                    "które Duch Święty\n" +
                    "swą łaską i oświeceniem\n" +
                    "wlewa w serca wiernych,\n" +
                    "abyście z niewiernych\n" +
                    "uczyniły wiernych Bogu.\n\n",
            "   Święta Maryjo, Dziewico, wśród niewiast na świecie nie urodziła się podobna Tobie, Córko i Służebnico " +
                    "najwyższego Króla, Ojca niebieskiego, Matko najświętszego Pana naszego Jezusa Chrystusa, Oblubienico " +
                    "Ducha Świętego: módl się za nami wraz ze św. Michałem Archaniołem i wszystkimi mocami " +
                    "nieba, i wszystkimi świętymi do Twego najświętszego, umiłowanego Syna, Pana i Mistrza. Chwała Ojcu. Jak było...\n\n",
            "Bójcie się Pana i cześć mu oddajcie.\n" +
                    "Godzien jest Pan odebrać chwałę i cześć.\n" +
                    "Wszyscy, którzy boicie się Pana, chwalcie Go.\n" +
                    "Bądź pozdrowiona Maryjo, łaski pełna, Pan z Tobą.\n" +
                    "Chwalcie Go niebo i ziemia.\n" +
                    "Chwalcie, wszystkie rzeki, Pana.\n" +
                    "Błogosławcie, synowie Boży, Pana.\n" +
                    "Oto dzień, który Pan uczynił; radujmy się w nim\n" +
                    "i weselmy.\n" +
                    "Alleluja, alleluja, alleluja! Królu Izraela.\n" +
                    "Wszelki duch niech chwali Pana.\n" +
                    "Chwalcie Pana, bo jest dobry;\n" +
                    "wszyscy, którzy to czytacie, błogosławcie Pana.\n" +
                    "Wszystkie stworzenia, błogosławcie Pana.\n" +
                    "Wszystkie ptaki powietrzne, chwalcie Pana.\n" +
                    "Wszystkie dzieci, chwalcie Pana.\n" +
                    "Młodzieńcy i panny, chwalcie Pana.\n" +
                    "Godzien jest Baranek, który został zabity,\n" +
                    "odebrać sławę, chwałę i cześć.\n" +
                    "Błogosławiona niech będzie Święta Trójca\n" +
                    "i nierozdzielna Jedność\n" +
                    "Święty Michale Archaniele, broń nas w walce. Amen.\n\n",
            "   Najwyższy, chwalebny Boże, rozjaśnij ciemności mego serca i daj mi, Panie, prawdziwą wiarę, niezachwianą " +
                    "nadzieję i doskonałą miłość, zrozumienie i poznanie, abym wypełniał Twoje święte i prawdziwe posłannictwo.\n\n",
            "   Wszechmogący, najświętszy i najwyższy Boże, Ojcze " +
                    "święty i sprawiedliwy, Panie, królu nieba i ziemi, " +
                    "dzięki Ci składamy z powodu Ciebie samego, że Twoją " +
                    "świętą wolą i przez jedynego Syna Twego stworzyłeś " +
                    "z Duchem Świętym wszystkie byty duchowe i cielesne, " +
                    "a nas uczyniłeś na obraz i podobieństwo Twoje i umieściłeś w raju.\n" +
                    "   My zaś upadliśmy z własnej winy.\n" +
                    "   I dzięki Ci składamy, że jak stworzyłeś nas przez " +
                    "Syna Twego, tak przez świętą miłość Twoją, którą nas " +
                    "umiłowałeś, sprawiłeś, że On, prawdziwy Bóg i prawdziwy " +
                    "Człowiek, narodził się z chwalebnej zawsze " +
                    "Dziewicy, błogosławionej, świętej Maryi i przez Jego " +
                    "krzyż i krew, i śmierć zechciałeś nas wykupić z niewoli.\n" +
                    "   I dzięki Ci składamy, że Syn Twój przyjdzie w chwale " +
                    "swego majestatu, aby strącić w ogień wieczny przeklętych, " +
                    "którzy nie czynili pokuty i nie poznali Ciebie, " +
                    "i aby powiedzieć wszystkim, którzy Ciebie poznali " +
                    "i uwielbili, i służyli Ci w pokucie: Pójdźcie, błogosławieni " +
                    "Ojca mego, weźcie królestwo, zgotowane wam od początku świata.\n" +
                    "   A ponieważ my, nędzni i grzeszni, wszyscy razem " +
                    "nie jesteśmy godni wymówić Twego Imienia, błagamy " +
                    "pokornie, aby Pan nasz Jezus Chrystus, Syn Twój " +
                    "umiłowany, który Tobie wielce podoba się, składał Ci " +
                    "za wszystko dzięki wraz z Duchem Świętym Pocieszycielem, " +
                    "tak jak Tobie i Jemu się podoba, On, który Ci " +
                    "za wszystko wystarcza, przez którego tyle dla nas uczyniłeś. Alleluja.\n\n",
            "   Wszechmogący, najświętszy, najwyższy i największy " +
                    "Boże, wszelkie dobro, największe dobro, całe dobro, " +
                    "który sam jesteś dobry, Tobie pragniemy oddawać " +
                    "wszelką sławę, wszelką chwałę, wszelką wdzięczność, " +
                    "wszelką cześć, wszelkie błogosławieństwo i wszelkie " +
                    "dobra. Niech się stanie. Niech się stanie. Amen.\n\n",
            "   Bądź pozdrowiona, Królowo Mądrości, Pan niech cię strzeże z twą siostrą, świętą, czystą Prostotą.\n" +
                    "   Pani święte Ubóstwo, Pan niech cię strzeże z twą siostrą, świętą Pokorą.\n" +
                    "   Pani święta Miłości, Pan niech cię strzeże z twą siostrą, świętym Posłuszeństwem.\n" +
                    "   Wszystkie najświętsze cnoty, niech was strzeże Pan, od którego się wywodzicie i pochodzicie.\n" +
                    "   Nie ma w ogóle na całym świecie żadnego człowieka, który mógłby jedną z was posiadać, jeśli wcześniej nie umarłby.\n" +
                    "   Kto jedną posiada, a innym nie uchybia, posiada wszystkie." +
                    "   I kto jednej uchybia, żadnej nie posiada i wszystkim uchybia.\n" +
                    "   I każda zawstydza wady i grzechy.\n" +
                    "   Święta Mądrość zawstydza szatana i całą jego przewrotność.\n" +
                    "   Czysta i święta Prostota zawstydza całą mądrość tego świata i mądrość ciała.\n" +
                    "   Święte Ubóstwo zawstydza pychę i skąpstwo, i troski tego świata.\n" +
                    "   Święta Pokora zawstydza pychę i wszystkich ludzi, którzy są ze świata, podobnie i wszystko, co jest ze świata.\n" +
                    "   Święta Miłość zawstydza wszystkie pokusy diabelskie i cielesne, i wszelką bojaźń cielesną.\n" +
                    "   Święte Posłuszeństwo zawstydza wszelkie ludzkie " +
                    "i cielesne pożądania, i utrzymuje ciało w umartwieniu, aby było posłuszne duchowi i aby słuchało swego brata, " +
                    "i czyni człowieka poddanym i uległym wszystkim ludziom, i nie tylko samym ludziom, lecz także dzikim " +
                    "i okrutnym zwierzętom, aby mogły z nim czynić, co zechcą, na ile im Pan z wysoka pozwoli.\n\n",
            "   O Najświętszy Ojcze nasz: Stwórco, Odkupicielu, Pocieszycielu i Zbawicielu nasz.\n" +
                    "   Który jesteś w niebie: w aniołach i w świętych. Oświecasz ich, aby Cię poznali, bo Ty, Panie jesteś " +
                    "światłością. Rozpalasz ich, aby Cię kochali, bo Ty, Panie, jesteś miłością. Zamieszkujesz w nich i napełniasz, " +
                    "aby ich uszczęśliwić, bo Ty, Panie, jesteś dobrem najwyższym, wiecznym, od którego pochodzi wszelkie " +
                    "dobro, bez którego nie ma żadnego dobra.\n" +
                    "   Święć się Imię Twoje: niech zajaśnieje w nas poznanie Ciebie, abyśmy poznali, jaka jest szerokość Twoich\n" +
                    "dobrodziejstw, długość Twoich obietnic, wysokość majestatu i głębokość sądów.\n" +
                    "   Przyjdź królestwo Twoje: abyś Ty królował w nas przez łaskę i doprowadził nas do Twego królestwa, " +
                    "gdzie będziemy oglądać Cię bez zasłon, miłować doskonale i w radosnym zjednoczeniu cieszyć się Tobą na wieki.\n" +
                    "   Bądź wola Twoja jak w niebie, tak i na ziemi: abyśmy Cię kochali z całego serca, zawsze o Tobie myśląc; " +
                    "z całej duszy, zawsze za Tobą tęskniąc; całym umysłem, ku Tobie kierując wszystkie nasze intencje, szukając " +
                    "we wszystkim Twojej chwały i ze wszystkich naszych sił, obracając wszystkie nasze siły i władze duszy i ciała " +
                    "na służbę Twej miłości, a nie na co innego; i bliźnich naszych kochajmy jak samych siebie, wszystkich pociągając " +
                    "według sił do Twojej miłości, ciesząc się z dobra innych jak z własnego i współczując w nieszczęściu, i nikomu nie dając żadnego zgorszenia.\n" +
                    "   Chleba naszego powszedniego: umiłowanego Syna Twego, Pana naszego Jezusa Chrystusa, daj nam dzisiaj: " +
                    "na pamiątkę i dla zrozumienia, i uczczenia miłości, którą nas darzył, oraz tego, co dla nas powiedział, uczynił i wycierpiał.\n" +
                    "   I odpuść nam nasze winy: przez Twoje niewysłowione miłosierdzie, przez moc męki umiłowanego Syna " +
                    "Twego i przez zasługi, i wstawiennictwo Najświętszej Dziewicy i wszystkich wybranych Twoich.\n" +
                    "   Jako i my odpuszczamy naszym winowajcom: a czego nie odpuszczamy w pełni, Ty, Panie, naucz nas " +
                    "w pełni odpuszczać, abyśmy dla Ciebie prawdziwie kochali nieprzyjaciół i modlili się za nich pobożnie " +
                    "do Ciebie, nikomu złem za złe nie oddając, i abyśmy starali się w Tobie wszystkim służyć pomocą." +
                    "   I nie wódź nas na pokuszenie: ukryte czy jawne, nagłe czy uporczywe.\n" +
                    "   Ale nas zbaw ode złego: przeszłego, obecnego i przyszłego.\nChwała Ojcu itd.\n\n",
            "   Wszechmogący, wiekuisty, sprawiedliwy i miłosierny Boże, daj nam nędznym czynić dla Ciebie to, " +
                    "o czym wiemy, że tego chcesz i chcieć zawsze tego, co się Tobie podoba, abyśmy wewnętrznie oczyszczeni, " +
                    "wewnętrznie oświeceni i rozpaleni ogniem Ducha Świętego, mogli iść śladami umiłowanego Syna Twego, " +
                    "Pana naszego Jezusa Chrystusa i dojść do Ciebie, Najwyższy, jedynie dzięki Twej łasce, który żyjesz i królujesz, " +
                    "i odbierasz hołd w doskonałej Trójcy i prostej Jedności, Bóg wszechmogący przez wszystkie wieki wieków. Amen.\n\n",
            "Niech ci Pan błogosławi i niech cię strzeże; niech ci ukaże oblicze swoje i zmiłuje się nad tobą.\n" +
                    "Niech zwróci oblicze swoje ku tobie i niech cię obdarzy pokojem.\nPan niech cię błogosławi, bracie Leonie. Amen.\n\n",
            "Bogu chwała,\nwiekuista niech będzie chwała,\nhołd Maryi,\ncześć świętym,\npokój żyjącym,\n" +
                    "wieczny odpoczynek zmarłym,\nzdrowie chorym,\ngrzesznikom szczera pokuta,\n" +
                    "sprawiedliwym w dobrym wytrwanie,\nżeglarzom na morzu spokój,\npodróżującym pomyślna droga.\n\n",
            "Święty, święty, święty jest Pan Bóg wszechmogący, który jest i który był, i który ma przyjść.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Godzien, jesteś Panie, Boże nasz, otrzymać sławę, chwałę i cześć, i błogosławieństwo.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Godzien jest Baranek, który był zabity, otrzymać moc i bóstwo i mądrość, i siłę, i cześć, i chwalę, i błogosławieństwo.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Błogosławmy Ojca i Syna z Duchem Świętym.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Błogosławcie, wszystkie dzieła Pańskie, Pana.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Dajcie chwałę Bogu naszemu wszyscy słudzy Jego i którzy się Boga boicie, mali i wielcy.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Niech Go pełnego chwały chwalą niebo i ziemia.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "I wszelkie stworzenie, które jest w niebie i na ziemi i które jest pod ziemią i morze, i co jest w nim.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Chwała Ojcu i Synowi, i Duchowi Świętemu.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n" +
                    "Jaka była na początku i teraz,\n" +
                    "i zawsze, i na wieki wieków. Amen.\n" +
                    "I chwalmy, i wywyższajmy Go na wieki.\n\n"
    )

    val prayerNames = arrayOf(
            "Modlitwa franciszkańska",
            "Wielbimy Cię",
            "Pieśń słoneczna",
            "Uwielbienie Boga Najwyższego",
            "Pozdrowienie Błogosławionej Maryi Dziewicy",
            "Antyfona: Święta Maryjo",
            "Zachęta do uwielbienia Boga",
            "Modlitwa odmówiona przed krucyfiksem",
            "Wszechmogący, najświętszy (I)",
            "Wszechmogący, najświętszy (II)",
            "Pozdrowienie cnót",
            "Wykład modlitwy Ojcze nasz",
            "Wszechmogący, wiekuisty",
            "Błogosławieństwo dla brata Leona",
            "Bogu chwała",
            "Modlitwa pochwalna"
    )
}