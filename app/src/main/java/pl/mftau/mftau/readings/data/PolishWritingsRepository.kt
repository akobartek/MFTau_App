package pl.mftau.mftau.readings.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import pl.mftau.mftau.readings.domain.WritingsRepository

class PolishWritingsRepository : WritingsRepository() {
    override val writingsNames = arrayOf(
        "List do wiernych - redakcja I\n(Zachęta dla braci i sióstr pokutujących)",
        "List do wiernych - redakcja II",
        "Napomnienia",
        "Reguła niezatwierdzona",
        "Reguła zatwierdzona",
        "Testament",
        "Testament sieneński",
    )

    override fun buildWritingsBook(accentColor: Color) = arrayOf(
        buildAnnotatedString {
            buildChapter(
                name = "W imię Pana!\n\n[Rodział 1]\nO tych, którzy czynią pokutę",
                array = arrayOf(
                    1 to "Wszyscy, którzy miłują Pana z całego serca, z całej duszy i umysłu, z całej mocy (por,. Mk 12, 30) i miłują bliźnich swoich jak siebie samych (por. Mt 22, 39), ",
                    2 to "a mają w nienawiści ciała swoje z wadami i grzechami, ",
                    3 to "i przyjmują Ciało i Krew Pana naszego Jezusa Chrystusa, ",
                    4 to "i czynią godne owoce pokuty.\n\n",
                    5 to "O, jakże szczęśliwi i błogosławieni są oni i one, gdy takie rzeczy czynią i w nich trwają, ",
                    6 to "bo spocznie na nich Duch Pański (por. Iz 11, 2) i uczyni u nich mieszkanie i miejsce pobytu (por. J 14, 23), ",
                    7 to "i są synami Ojca niebieskiego (por. Mt 5, 45), którego dzieła czynią, i są oblubieńcami, braćmi i matkami Pana naszego Jezusa Chrystusa (por. Mt 12, 50).\n\n",
                    8 to "Oblubieńcami jesteśmy, kiedy dusza wierna łączy się w Duchu Świętym z Panem naszym Jezusem Chrystusem. ",
                    9 to "Braćmi dla Niego jesteśmy, kiedy spełniamy wolę Ojca, który jest w niebie (por. Mt 12, 50). ",
                    10 to "Matkami, gdy nosimy Go w sercu i w ciele naszym (por. 1 Kor 6, 20) przez miłość Boską oraz czyste " +
                            "i szczere sumienie; rodzimy Go przez święte uczynki, które powinny przyświecać innym jako wzór (por. Mt 5, 16). ",
                    11 to "O, jak chwalebna to rzecz mieć w niebie świętego i wielkiego Ojca! ",
                    12 to "O, jak świętą jest rzeczą mieć pocieszyciela, tak pięknego i podziwu godnego oblubieńca!\n\n",
                    13 to "O, jak świętą i cenną jest rzeczą mieć tak miłego, pokornego, darzącego pokojem, słodkiego, godnego miłości i ponad wszystko upragnionego brata " +
                            "i takiego syna: Pana naszego Jezusa Chrystusa, który życie oddał za owce swoje (por. J 10, 15) i modlił się do Ojca mówiąc: ",
                    14 to "Ojcze święty, zachowaj w imię twoje tych (J 17, 11) których Mi dałeś na świecie; Twoimi byli i dałeś Mi ich (por. J 17, 6). ",
                    15 to "I Słowa, które Mi dałeś, dałem im; a oni przyjęli i prawdziwie uwierzyli, że od Ciebie wyszedłem i poznali, że Ty Mnie posłałeś (por. J 17, 8).\n\n",
                    16 to "Proszę za nimi, a nie za światem (por. J 17, 9). ",
                    17 to "Pobłogosław i uświęć (por. J 17, 17), i za nich samego Siebie poświęcam w ofierze (J 17, 19).\n\n",
                    18 to "Nie tylko za nimi proszę, ale za tymi, którzy dzięki ich słowu uwierzą we Mnie (por. J 17, 20), aby byli poświęceni ku jedności (por. J 17, 23), jak i My (J 17, 11). ",
                    19 to "I chcę, Ojcze, aby i oni byli ze Mną tam, gdzie Ja jestem, żeby oglądali chwałę moją (por. J 17, 24) w królestwie Twoim (Mt 20, 21). Amen."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rodział 2]\nO tych, którzy nie czynią pokuty",
                array = arrayOf(
                    1 to "Wszyscy zaś oni i one, którzy nie trwają w pokucie ",
                    2 to "i nie przyjmują Ciała i Krwi Pana naszego Jezusa Chrystusa, ",
                    3 to "i dopuszczają się występków i grzechów, i którzy ulegają złej pożądliwości i złym pragnieniom swego ciała, ",
                    4 to "i nie zachowują tego, co przyrzekli Panu, ",
                    5 to "i służą cieleśnie światu cielesnymi pragnieniami i zabiegami świata, i troskami tego życia: ",
                    6 to "opanowani przez szatana, którego są synami i spełniają jego uczynki (por. J 8, 41), ",
                    7 to "są ślepi, ponieważ nie widzą prawdziwego Światła, Pana naszego Jezusa Chrystusa.\n\n",
                    8 to "Nie mają mądrości duchowej, ponieważ nie mają Syna Bożego, który jest prawdziwą Mądrością Ojca; ",
                    9 to "o nich jest powiedziane: Mądrość ich została pochłonięta (por. Ps 106, 27); i: Przeklęci, którzy odstępują od przykazań twoich (Ps 118, 21).\n\n",
                    10 to "Widzą i poznają, wiedzą i postępują źle, i sami świadomie gubią dusze.\n\n",
                    11 to "Patrzcie, ślepcy, zwiedzeni przez waszych nieprzyjaciół: przez ciało, świat i szatana; bo ciału słodko jest grzeszyć, a gorzko jest służyć Bogu; ",
                    12 to "ponieważ wszystkie występki i grzechy z serca ludzkiego wychodzą i pochodzą (por. Mk 7, 21. 23), jak mówi Pan w Ewangelii.\n\n",
                    13 to "I niczego nie macie w tym świecie ani w przyszłym.\n\n",
                    14 to "I uważacie, że długo będziecie posiadać marności tego świata, lecz łudzicie się, bo przyjdzie dzień i godzina, o których nie myślicie, nie wiecie i których nie znacie; ciało choruje, śmierć się zbliża i tak umiera gorzką śmiercią.\n\n",
                    15 to "I gdziekolwiek, kiedykolwiek i w jakikolwiek sposób umiera człowiek w śmiertelnym grzechu bez pokuty i zadośćuczynienia, jeśli może zadośćuczynić, a nie zadośćczyni, szatan porywa jego duszę z jego ciała z takim uciskiem i męką, o jakich nikt wiedzieć nie może, o ile tego nie doznaje.\n\n",
                    16 to "I wszystkie bogactwa i władza, i wiedza, i mądrość (por. 2Krn 1, 12), o których sądzili, że mają, zostały im odjęte (por. Łk 8, 18; Mk 4, 25).\n\n",
                    17 to "I pozostały dla krewnych i przyjaciół, a oni zabrali i podzielili jego majątek, a potem powiedzieli: „Niech będzie przeklęta jego dusza, bo mógł więcej nam dać i zgromadzić niż zgromadził”.\n\n",
                    18 to "Ciało jedzą robaki i tak zgubili ciało i duszę w tym krótkim życiu i pójdą do piekła, gdzie będą cierpieć męki bez końca.\n\n",
                    19 to "Tych wszystkich, do których dojdzie ten list, prosimy przez miłość, którą jest Bóg (por. 1 J 4, 16), aby te wyżej wspomniane wonne słowa Pana naszego Jezusa Chrystusa z miłości ku Bogu życzliwie przyjęli.\n\n",
                    20 to "A ci, którzy nie umieją czytać, niech starają się, aby im często czytano.\n\n",
                    21 to "I niech je zachowują, postępując święcie aż do końca, bo są duchem i życiem (J 6, 64).\n\n",
                    22 to "A ci, którzy tego nie uczynią, będą musieli zdać rachunek w dzień sądu (por. Mt 12, 36) przed trybunałem Pana naszego Jezusa Chrystusa (por. Rz 14, 10).\n\n"
                )
            ).let { append(it) }
        },
        justifiedString(
            buildAnnotatedString {
                append("W imię Pańskie – Ojca i Syna, i Ducha świętego. Amen.\n\n")
                processIndicesAndText(
                    arrayOf(
                        1 to "Wszystkim chrześcijanom – żyjącym w zakonie, duchownym i świeckim, mężczyznom i kobietom, " +
                                "wszystkim, którzy mieszkają na całym świecie, brat Franciszek, sługa ich i poddany, " +
                                "składa wyrazy czci i uszanowania oraz życzy prawdziwego pokoju z nieba i szczerej miłości w Panu.\n\n",
                        2 to "Będąc sługą wszystkich, mam obowiązek służyć wszystkim i udzielać wonnych słów mojego Pana.\n\n",
                        3 to "Zważywszy zatem, że z powodu choroby i słabości mego ciała nie mogę każdego z osobna odwiedzić osobiście, postanowiłem w tym liście i orędziu przekazać wam słowa Pana naszego Jezusa Chrystusa, który jest Słowem Ojca, i słowa Ducha Świętego, które są duchem i życiem (J 6, 64).\n\n",
                        4 to "To Słowo Ojca tak godne, tak święte i chwalebne zwiastował najwyższy Ojciec z nieba przez św. Gabriela, swego anioła, mające zstąpić do łona świętej i chwalebnej Dziewicy Maryi, z której to łona przyjęło prawdziwe ciało naszego człowieczeństwa i naszej ułomności.\n\n",
                        5 to "Które, będąc bogate (2 Kor 8, 9) ponad wszystko, zechciało wybrać na świecie ubóstwo wraz z Najświętszą Dziewicą, Matką swoją.\n\n",
                        6 to "Bliski męki święcił [Jezus} paschę ze swymi uczniami i wziąwszy chleb, dzięki czynił i błogosławił, i łamał mówiąc: bierzcie i jedzcie, to jest ciało moje (por. Mt 26, 26).\n\n",
                        7 to "A wziąwszy kielich (por. Mt 26, 27) powiedział: To jest krew moja Nowego Przymierza, która za was i za wielu będzie wylana na odpuszczenie grzechów (por. Mt 26, 28).\n\n",
                        8 to "Potem modlił się do Ojca mówiąc: Ojcze, jeśli to być może, niech odejdzie ode mnie ten kielich (por. Mt 26, 39).\n\n",
                        9 to "I stał się pot Jego jak krople krwi, spływającej na ziemię (Łk 22, 44).\n\n",
                        10 to "Poddał jednak swą wolę woli Ojca mówiąc: Ojcze, niech się dzieje wola Twoja (por. Mt 26, 42), nie jak Ja chcę, ale jak Ty (Mt 26, 39).\n\n",
                        11 to "Wola zaś Ojca była taka, aby Syn Jego błogosławiony i chwalebny, którego nam dał i który narodził się dla nas, ofiarował siebie samego przez własną krew jako ofiara i żertwa na ołtarzu krzyża ",
                        12 to "nie za siebie, przez którego stało się wszystko (por. J 1, 3), ale za nasze grzechy, ",
                        13 to "zostawiając nam przykład, abyśmy wstępowali w Jego ślady (por. 1 P 2, 21).\n\n",
                        14 to "I chcę, abyśmy wszyscy zostali przez Niego zbawieni i przyjmowali Go czystym sercem i czystym ciałem.\n\n",
                        15 to "Lecz niewielu jest takich, którzy pragną Go przyjmować i dostąpić przez Niego zbawienia, chociaż jarzmo Jego jest słodkie, a brzemię Jego lekkie (por. Mt 11, 30).\n\n",
                        16 to "Przeklęci są ci, którzy nie chcą skosztować jak słodki jest Pan (por. Ps 33, 9) i bardziej miłują ciemności niż światłość (por. J 3, 19), i nie chcą wypełniać przykazań Bożych.\n\n",
                        17 to "O nich to mówi prorok: Przeklęci, którzy odstępują od Twoich przykazań (Ps 118, 21).\n\n",
                        18 to "Lecz jak szczęśliwi i błogosławieni są ci, którzy miłują Boga i czynią tak, jak sam Pan mówi w Ewangelii: Będziesz miłował Pana Boga twego z całego serca i całym umysłem, a bliźniego twego jak samego siebie (por. Mt 22, 37. 39).\n\n",
                        19 to "Miłujmy więc Boga i uwielbiajmy Go czystym sercem i czystym umysłem, bo On, tego ponad wszystko pragnąc, powiedział: Prawdziwi czciciele będą czcić Ojca w duchu i prawdzie (J 4, 23).\n\n",
                        20 to "Wszyscy bowiem, którzy cześć Mu oddają, powinni Go czcić w duchu prawdy (por. J 4, 24).\n\n",
                        21 to "I wychwalajmy Go, i módlmy się w dzień i w nocy (Ps 31, 4) mówiąc: Ojcze nasz, który jesteś w niebie (Mt 6, 9), bo zawsze powinniśmy się modlić i nie ustawać (por. Łk 18, 1).\n\n",
                        22 to "Powinniśmy spowiadać się przed kapłanem ze wszystkich grzechów naszych i przyjmować od niego Ciało i Krew Pana naszego Jezusa Chrystusa.\n\n",
                        23 to "Kto nie spożywa Jego Ciała i nie pije Jego Krwi (por. J 6, 55. 57), nie może wejść do Królestwa Bożego (J 3, 5).\n\n",
                        24 to "Niech jednak spożywa i pije godnie, ponieważ kto niegodnie przyjmuje, sąd sobie spożywa i pije, nie rozpoznając Ciała Pańskiego (por. 1 Kor 11, 29), tj. nie odróżnia [od innych pokarmów].\n\n",
                        25 to "Wydawajmy również godne owoce pokuty (Łk 3, 8). ",
                        26 to "I kochajmy bliźnich jak siebie samych (por. Mt 22, 39). ",
                        27 to "A jeśli kto nie chce kochać ich tak jak siebie, niech im przynajmniej nie wyrządza zła, lecz niech im czyni dobrze. ",
                        28 to "Ci zaś, którzy otrzymali władzę sędziowską, niech sądzą z miłosierdziem, tak jak sami pragną otrzymać miłosierdzie od Pana.\n\n",
                        29 to "Sąd bowiem bez miłosierdzia będzie nad tymi, którzy miłosierdzia nie okażą (por. Jk 2, 13).\n\n",
                        30 to "Miejmy więc miłość i pokorę; i dawajmy jałmużnę, bo ona oczyszcza dusze z brudów grzechowych (por. Tb 4, 11; 12, 9).\n\n",
                        31 to "Ludzie bowiem tracą wszystko, co zostawiają na tym świecie, zabierają jednak ze sobą wynagrodzenie za miłość i jałmużny, jakich udzielali; otrzymają za nie od Pana nagrodę i odpowiednią zapłatę.\n\n",
                        32 to "Powinniśmy również pościć i powstrzymywać się od nałogów i grzechów (por. Syr 3, 32), i od nadużywania pokarmów i napoju, i być katolikami.\n\n",
                        33 to "Powinniśmy również nawiedzać często kościoły oraz szanować i czcić duchownych, nie tyle dla nich samych, bo mogą być grzesznikami, ile dla ich urzędu i posługi wobec Najświętszego Ciała i Krwi Chrystusa, które oni konsekrują na ołtarzu, sami przyjmują i innym udzielają.\n\n",
                        34 to "I bądźmy wszyscy mocno przekonani, że nikt nie może inaczej się zbawić, jak tylko przez święte słowa i Krew Pana naszego Jezusa Chrystusa, które duchowni głoszą, przekazują i udzielają. ",
                        35 to "I tylko oni powinni to wypełniać, a nikt inny. ",
                        36 to "Zwłaszcza zaś zakonnicy, którzy wyrzekli się świata, mają obowiązek czynić to, co większe i lepsze, ale i tamtych nie zaniedbywać (por. Łk 11, 42).\n\n",
                        37 to "Powinniśmy mieć w nienawiści ciała nasze z wadami i grzechami, bo Pan mówi w Ewangelii: wszelkie zło, występki i grzechy z serca pochodzą (por. Mt 15, 18-19; Mk 7, 23).\n\n",
                        38 to "Powinniśmy miłować naszych nieprzyjaciół i dobrze czynić tym, którzy, nas mają w nienawiści (por. Mt 5, 44; Łk 6, 27).\n\n",
                        39 to "Powinniśmy zachowywać przykazania i rady Pana naszego Jezusa Chrystusa. ",
                        40 to "Powinniśmy również wyrzec się siebie samych (por. Mt 16, 24) i poddać nasze ciała pod jarzmo służby i świętego posłuszeństwa, jak to każdy przyrzekł Panu.\n\n",
                        41 to "I żaden człowiek nie może być zobowiązany na mocy posłuszeństwa słuchać kogokolwiek w tym, co jest wykroczeniem lub grzechem.\n\n",
                        42 to "Ten zaś, który ma czuwać nad posłuszeństwem i który uchodzi za większego, niech będzie jako mniejszy (Łk 22, 26) i sługa innych braci. ",
                        43 to "A w stosunku do poszczególnych braci swoich niech okazuje miłosierdzie i niech je zachowa, tak, jakby sobie życzył w podobnym wypadku. ",
                        44 to "I niech się nie gniewa na brata z powodu jego występku, lecz nich go łagodnie upomina i znosi z wszelką cierpliwością i pokorą.\n\n",
                        45 to "Nie powinniśmy być mądrymi i roztropnymi według ciała (por. 1 Kor 1, 26), lecz raczej bądźmy prostymi, pokornymi i czystymi.\n\n",
                        46 to "I miejmy w pogardzie i poniżeniu nasze ciała, bo wszyscy z własnej winy jesteśmy nędzni i zepsuci, cuchnący i robaki, jak mówi Pan przez proroka: Ja jestem robak, a nie człowiek, pośmiewisko ludzkie i wzgarda pospólstwa (Ps 21, 7).\n\n",
                        47 to "Nie pragnijmy nigdy górować nad innymi, lecz bądźmy raczej sługami i poddanymi wszelkiemu ludzkiemu stworzeniu ze względu na Boga (1 P 2, 13). ",
                        48 to "A na tych wszystkich i te wszystkie, które będą to czynić i wytrwają aż do końca, spocznie Duch Pański (por. Iz 11, 2) i uczyni w nich mieszkanie i miejsce pobytu (por. J 14, 23). ",
                        49 to "I będą synami Ojca niebieskiego (por. Mt 5, 45), którego dzieła czynią. ",
                        50 to "I są oblubieńcami, braćmi i matkami Pana naszego Jezusa Chrystusa (por. Mt 12, 50).\n\n",
                        51 to "Jesteśmy oblubieńcami, gdy dusza wierna łączy się w Duchu Świętym z Jezusem Chrystusem. ",
                        52 to "Jesteśmy prawdziwie braćmi gdy spełniamy wolę Jego Ojca, który jest w niebie (por. Mt 12, 50).\n\n",
                        53 to "Jesteśmy matkami, gdy nosimy Go w sercu i w ciele naszym (por. 1 Kor 6, 20) przez miłość i czyste i szczere sumienie, rodzimy go przez święte uczynki, które powinny przyświecać innym jako wzór (por. Mt 5, 16).\n\n",
                        54 to "O, jakże chwalebna i święta, i wielka to rzecz: mieć w niebie Ojca! ",
                        55 to "O, jak świętą jest rzeczą mieć pocieszyciela, tak pięknego i podziwu godnego oblubieńca! ",
                        56 to "O, jak świętą i cenną jest rzeczą mieć tak miłego, pokornego, darzącego pokojem, słodkiego i godnego miłości, i ponad wszystko upragnionego brata i syna, który życie swoje oddał za owce swoje (por. J 10, 15) i modlił się za nami do Ojca mówiąc: Ojcze święty, zachowaj w imię Twoje tych, których Mi dałeś (J 17, 11).\n\n",
                        57 to "Ojcze wszyscy, których Mi dałeś na świecie, Twoimi byli, i dałeś Mi ich (por. J 17, 6). ",
                        58 to "I słowa, które Mi dałeś, dałem im: a oni przyjęli i prawdziwie poznali, że od Ciebie wyszedłem i uwierzyli, że Ty Mnie posłałeś (J 17, 8). Proszę za nimi, a nie za światem (por. J 17, 17).\n\n",
                        59 to "I za nich samego siebie poświęcam w ofierze, aby zostali uświęceni w (J 17, 19) jedno, jak i my (J 17, 11) jesteśmy.\n\n",
                        60 to "I chcę Ojcze, aby i oni byli ze Mną tam, gdzie Ja jestem, żeby oglądali chwałę moją (J 17, 24) w królestwie Twoim (Mt 20, 21).\n\n",
                        61 to "Temu zaś, który tyle wycierpiał za nas, wyświadczył nam tyle dobra i wyświadczy w przyszłości, niech wszelkie stworzenie, które jest w niebie, na ziemi, w morzu i przepaściach, oddaje jako Bogu sławę, chwałę, cześć i błogosławieństwo (por. Ap 5, 13), ",
                        62 to "ponieważ On jest naszą mocą i siłą, On sam jest dobry, sam najwyższy, sam wszechmocny, godny podziwu, sam przesławny i święty, godny chwały i błogosławiony przez nieskończone wieki wieków. Amen.\n\n",
                        63 to "Ci wszyscy zaś, którzy nie trwają w pokucie i nie przyjmują ciała i Krwi Pana naszego Jezusa Chrystusa, ",
                        64 to "dopuszczają się występków i grzechów, i ulegają złej pożądliwości i złym pragnieniom, i nie zachowują tego co przyrzekli, ",
                        65 to "i służą cieleśnie światu cielesnymi pragnieniami i zabiegami świata, i troskami tego życia, ",
                        66 to "oszukani przez szatana, którego są synami i jego uczynki pełnią (por J 8, 41), są ślepi, ponieważ nie widzą prawdziwego Światła, Pana naszego Jezusa Chrystusa.\n\n",
                        67 to "Ci nie mają mądrości duchowej, ponieważ nie mają Syna Bożego, który jest prawdziwą Mądrością Ojca;O nich jest powiedziane: Mądrość ich została pochłonięta (por. Ps 106, 27).\n\n",
                        68 to "Widzą poznają, wiedzą i postępują źle, i świadomie gubią dusze.\n\n",
                        69 to "Patrzcie, ślepcy, zwiedzeni przez waszych nieprzyjaciół, to jest: przez ciało, świat i szatana, bo ciału słodko jest grzeszyć, a gorzko służyć Bogu, ponieważ wszelkie zło, występki i grzechy z serca ludzkiego wychodzą i pochodzą (por. Mk 7, 21. 23), jak mówi Pan w Ewangelii.\n\n",
                        70 to "I niczego dobrego nie macie w tym świecie, ani w przyszłym. ",
                        71 to "Uważacie, że długo będziecie posiadać marności tego świata, lecz łudzicie się, bo przyjdzie dzień i godzina, o których nie myślicie, nie wiecie, i których nie znacie.\n\n",
                        72 to "Ciało choruje, śmierć się zbliża, przychodzą krewni i przyjaciele mówiąc: „Rozrządź tym, co twoje”. ",
                        73 to "A żona i dzieci jego, i krewni, i przyjaciele udają, że płaczą. ",
                        74 to "I patrząc na nich, i widząc, jak płaczą, ulega złemu wzruszeniu, zastanawia się i mówi: Oto duszę i ciało moje, i wszystko, co mam, składam w wasze ręce.\n\n",
                        75 to "Przeklęty jest naprawdę ten człowiek, który powierza i oddaje w takie ręce swoją duszę i ciało, i cały swój majątek. ",
                        76 to "Dlatego Pan mówi przez proroka: Przeklęty człowiek, który pokłada nadzieję w człowieku (Jr 17, 5). ",
                        77 to "I sprowadzają zaraz kapłana; kapłan mówi do niego: „Czy chcesz pokutować za wszystkie swoje grzechy?” ",
                        78 to "Odpowiada: „Chcę”. „Czy chcesz zadośćuczynić za popełnione grzechy i według możności ze swego majątku naprawić krzywdy, jakie wyrządziłeś ludziom?” ",
                        79 to "Odpowiada: „Nie”. A kapłan pyta: „Dlaczego nie?” ",
                        80 to "„Bo oddałem wszystko w ręce krewnych i przyjaciół”. ",
                        81 to "I zaczyna tracić mowę, i tak umiera ten nieszczęśliwy. ",
                        82 to "Lecz niech wiedzą wszyscy, że gdziekolwiek i w jakikolwiek sposób umarłby człowiek w grzechu śmiertelnym bez zadośćuczynienia, i może zadośćuczynić, a nie zadośćuczynił, szatan porywa jego duszę z jego ciała z takim uciskiem i męką, o jakich nikt wiedzieć nie może, o ile tego nie doznał.\n\n",
                        83 to "I wszystkie bogactwa i władza, i wiedza, o których sądził, że je posiada, zostaną mu odjęte (por. Mk 4, 25; Łk 8, 18). ",
                        84 to "I pozostawia krewnym i przyjaciołom, a ci zabiorą i podzielą jego majętność, a następnie powiedzą: „Niech będzie przeklęta jego dusza, bo mógł więcej nam dać i zgromadzić, niż zgromadził”.\n\n",
                        85 to "Ciało jedzą robaki; i tak gubi ciało i duszę w tym krótkim życiu i pójdzie do piekła, gdzie będzie cierpiał męki bez końca.\n\n",
                        86 to "W imię Ojca i Syna, i Ducha Świętego. Amen.\n\n",
                        87 to "Ja, brat Franciszek, sługa wasz najmniejszy, proszę was i zaklinam w miłości, którą jest Bóg (por. 1 J 4, 16) i z chęcią ucałowania waszych stóp, abyście te oraz inne słowa Pana naszego Jezusa Chrystusa przyjęli z pokorą i miłością i spełniali je, i zachowywali.\n\n",
                        88 to "A tych wszystkich i te wszystkie, którzy je życzliwie przyjmą, zrozumieją i odpis ich poślą innym, i jeśli wytrwają w tym aż do końca (por. Mt 10, 22; 24, 13), niech błogosławi Ojciec i Syn, i Duch Święty. Amen.\n\n"
                    )
                ).let { append(it) }
            }
        ),
        buildAnnotatedString { },
        buildAnnotatedString { },
        buildAnnotatedString {
            buildChapter(
                name = "[Bulla]",
                text = "Biskup Honoriusz, sługa sług Bożych, umiłowanym synom, bratu Franciszkowi " +
                        "i innym braciom z zakonu Braci Mniejszych pozdrowienie i apostolskie błogosławieństwo.\n\n" +
                        "Stolica Apostolska ma zwyczaj pozwalać na pobożne życzenia proszących i udzielać " +
                        "chętnie poparcia dla ich chwalebnych pragnień. Dlatego umiłowani w Panu synowie, " +
                        "skłaniając się do pobożnych próśb waszych, regułę waszego zakonu potwierdzoną " +
                        "przez niezapomnianej pamięci papieża Innocentego, naszego poprzednika, do tej [bulli] włączoną, " +
                        "powagą apostolską wam potwierdzamy i niniejszym pismem bierzemy w opiekę. Jest ona następująca:"
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 1]\nW imię Pańskie! Zaczyna się sposób życia braci mniejszych]",
                array = arrayOf(
                    1 to "Reguła i życie braci mniejszych polega na zachowaniu świętej Ewangelii Pana naszego " +
                            "Jezusa Chrystusa przez życie w posłuszeństwie, bez własności i w czystości. ",
                    2 to "Brat Franciszek przyrzeka posłuszeństwo i uszanowanie papieżowi Honoriuszowi i " +
                            "jego prawnym następcom, i Kościołowi Rzymskiemu. ",
                    3 to "A inni bracia mają obowiązek słuchać brata Franciszka i jego następców."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 2]\nKandydaci i sposób ich przyjmowania",
                array = arrayOf(
                    1 to "Tych, którzy przyjdą do naszych braci i pragnęliby przyjąć ten sposób życia, niech bracia odeślą " +
                            "do swoich ministrów prowincjalnych, bo im jedynie, a nie komu innemu przysługuje prawo przyjmowania braci. ",
                    2 to "Ministrowie niech ich sumiennie wybadają w zakresie wiary katolickiej i sakramentów Kościoła. ",
                    3 to "I jeśli w to wszystko wierzą i pragną to wiernie wyznawać i aż do końca pilnie zachowywać, ",
                    4 to "i jeśli nie mają żon albo, jeśli mają, wstąpiły już one do klasztoru lub z upoważnienia biskupa diecezjalnego " +
                            "dały im na to pozwolenie i same złożyły ślub czystości, i są w takim wieku, że nie mogą budzić podejrzeń – ",
                    5 to "niech im powiedzą słowa Ewangelii świętej (por. Mt 19, 21), aby poszli, sprzedali całe swoje mienie, i starali się rozdać to ubogim. ",
                    6 to "Jeśli nie mogą tego uczynić, wystarczy ich dobra wola. ",
                    7 to "I niech się strzegą bracia i ich ministrowie, aby się nie wtrącali do ich " +
                            "spraw doczesnych, żeby mogli nimi swobodnie rozporządzać, jak Pan ich natchnie. ",
                    8 to "Jeżeli jednak pytaliby o radę, to ministrowie mogą ich posłać do osób bogobojnych, za których radą rozdaliby swoje dobra ubogim. ",
                    9 to "Potem [ministrowie] dadzą im odzież na czas próby, to jest: dwie tuniki bez kaptura, sznur, spodnie i kaparon sięgający do pasa, ",
                    10 to "chyba, że tymże ministrom co innego niekiedy wyda się właściwszym według Boga. ",
                    11 to "Po upływie roku próby niech będą przyjęci pod posłuszeństwo, przyrzekając na zawsze zachowywać ten sposób życia i regułę. ",
                    12 to "I stosownie do rozporządzenia Ojca Świętego nie wolno im w żadnym wypadku wystąpić z tego zakonu, ",
                    13 to "bo według świętej Ewangelii nikt, kto przykłada rękę do pługa, a ogląda się wstecz, nie nadaje się do królestwa Bożego (Łk 9, 62).\n",
                    14 to "A ci, którzy przyrzekli już posłuszeństwo, niech mają jedną tunikę z kapturem i drugą – którzy chcieliby mieć – bez kaptura. ",
                    15 to "I w razie konieczności mogą nosić obuwie. ",
                    16 to "I niech wszyscy bracia noszą odzież pospolitą, i mogą ją z błogosławieństwem " +
                            "Bożym łatać zgrzebnym płótnem lub innymi kawałkami materiału.\n",
                    17 to "Upominam ich i przestrzegam, aby nie gardzili ludźmi i nie sądzili ich, gdy " +
                            "zobaczą ich ubranych w miękkie i barwne szaty i spożywających wyszukane " +
                            "potrawy i napoje, lecz niech każdy raczej siebie samego sądzi i sobą gardzi."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 3]\nOficjum Boskie i post, i jak bracia mają iść przez świat",
                array = arrayOf(
                    1 to "Klerycy niech odmawiają oficjum Boskie według przepisów świętego Kościoła Rzymskiego, za wyjątkiem psałterza. ",
                    2 to "Dlatego mogą mieć psałterze. ",
                    3 to "Bracia nie-klerycy niech odmawiają dwadzieścia cztery Ojcze nasz za jutrznię, " +
                            "pięć za laudesy, po siedem za prymę, tercję, sekstę i nonę, dwanaście za nieszpory, siedem za kompletę. ",
                    4 to "I niech modlą się za zmarłych.\n",
                    5 to "Niech poszczą od uroczystości Wszystkich Świętych do Bożego Narodzenia. ",
                    6 to "Co do świętego postu, który zaczyna się od Objawienia Pańskiego i trwa nieprzerwanie " +
                            "przez czterdzieści dni i który Pan uświęcił swoim świętym postem (por. Mt 4, 2), " +
                            "kto go dobrowolnie zachowa, niech będzie błogosławiony od Pana, a kto nie chce, nie jest do niego obowiązany. ",
                    7 to "Lecz inny post [czterdziestodniowy] przed Zmartwychwstaniem Pańskim niech bracia zachowują. ",
                    8 to "W innych zaś okresach obowiązani są do postu tylko w piątki. ",
                    9 to "W razie oczywistej potrzeby nie są jednak bracia obowiązani do postu cielesnego. ",
                    10 to "Radzę zaś moim braciom w Panu Jezusie Chrystusie, upominam ich i zachęcam, " +
                            "aby idąc przez świat nie wszczynali kłótni ani nie spierali się słowami (por. Tt 3, 2; 2 Tm 2, 14) i nie sądzili innych. ",
                    11 to "Lecz niech będą cisi, spokojni i skromni, łagodni i pokorni, rozmawiając uczciwie ze wszystkimi, jak należy. ",
                    12 to "I jeśli nie zmusza ich do tego oczywista konieczność lub choroba, nie powinni jeździć konno. ",
                    13 to "Do któregokolwiek domu wejdą, niech najpierw mówią: Pokój temu domowi (por. Łk 10, 5). ",
                    14 to "I zgodnie ze świętą Ewangelią mogą spożywać wszystkie potrawy, jaki im podadzą (por. Łk 10, 8)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 4]\nBracia nie powinni przyjmować pieniędzy",
                array = arrayOf(
                    1 to "Nakazuję stanowczo wszystkim braciom, aby żadnym sposobem nie przyjmowali pieniędzy " +
                            "lub rzeczy mających wartość pieniężną osobiście lub przez zastępcę. ",
                    2 to "Jednak o potrzeby chorych i odzież innych braci powinni się starać tylko ministrowie " +
                            "i kustosze – i to z największą troską – za pośrednictwem przyjaciół duchowych, " +
                            "w zależności od miejsc, pór roku i zimnych krajów, jak to uznają za konieczne, ",
                    3 to "zawsze z tym zastrzeżeniem, aby – jak powiedziano – nie przyjmowali " +
                            "pieniędzy lub rzeczy mających wartość pieniężną."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 5]\nSposób pracy",
                array = arrayOf(
                    1 to "Ci bracia, którym Pan dał łaskę, że mogą pracować, niech pracują wiernie i pobożnie, ",
                    2 to "tak żeby uniknąwszy lenistwa, nieprzyjaciela duszy, nie gasili ducha świętej " +
                            "modlitwy i pobożności, któremu powinny służyć wszystkie sprawy doczesne. ",
                    3 to "Jako wynagrodzenie za pracę mogą przyjmować rzeczy potrzebne do utrzymania siebie " +
                            "i swoich braci, z wyjątkiem pieniędzy lub rzeczy mających wartość pieniężną ",
                    4 to "i niech to czynią z pokorą, jak przystoi sługom Bożym i zwolennikom najświętszego ubóstwa."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 6]\nBracia nie powinni niczego nabywać na własność;\nzbieranie jałmużny i bracia chorzy",
                array = arrayOf(
                    1 to "Bracia niech niczego nie nabywają na własność: ani domu, ani ziemi, ani żadnej innej rzeczy. ",
                    2 to "I jako pielgrzymi i obcy (por. 1 P 2, 11) na tym świecie, służąc Panu w ubóstwie i pokorze, niech ufnie proszą o jałmużnę; ",
                    3 to "i nie powinni wstydzić się tego, bo Pan dla nas stał się ubogim na tym świecie (por. 2 Kor 8, 9).\n",
                    4 to "W tym jest dostojeństwo najwyższego ubóstwa, że ono ustanowiło was, braci moich " +
                            "najmilszych, dziedzicami i królami królestwa niebieskiego, uczyniło ubogimi w rzeczy doczesne, a uszlachetniło cnotami (por. Jk 2, 5). ",
                    5 to "Ono niech będzie cząstką waszą, która prowadzi do ziemi żyjących (por. Ps 141, 6). ",
                    6 to "Do niego, najmilsi bracia, całkowicie przylgnąwszy, niczego innego dla imienia Pana naszego Jezusa Chrystusa nie chciejcie nigdy na ziemi posiadać.\n",
                    7 to "I gdziekolwiek bracia przebywają lub spotkaliby się, niech odnoszą się do siebie jak członkowie rodziny. ",
                    8 to "I niech jeden drugiemu z zaufaniem wyjawia swoje potrzeby, jeśli bowiem matka karmi i kocha " +
                            "syna swego (por. 1 Tes 2, 7) cielesnego, o ileż troskliwiej powinien każdy kochać i karmić swego brata duchowego! ",
                    9 to "A jeśli który z nich zachoruje, inni bracia powinni tak mu usługiwać, jakby pragnęli, aby im służono (por. Mt 7, 12)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 7]\nNakładanie pokuty braciom grzeszącym",
                array = arrayOf(
                    1 to "Jeśli jacyś bracia za podszeptem nieprzyjaciela [duszy] dopuszczą się grzechu śmiertelnego " +
                            "i jeśli idzie o taki grzech, którego odpuszczenie, zgodnie z postanowieniem braci, wymaga zwrócenia " +
                            "się do ministrów prowincjalnych, wspomniani bracia obowiązani są zwrócić się do nich jak najprędzej, bez zwlekania. ",
                    2 to "Ministrowie zaś, jeśli są kapłanami, niech sami z miłosierdziem nałożą im pokutę; " +
                            "jeśli zaś nie są kapłanami, niech ją nadadzą za pośrednictwem innych kapłanów zakonu, jak wobec Boga uznają za lepsze. ",
                    3 to "I niech się strzegą, aby się nie gniewali i nie denerwowali z powodu czyjegoś " +
                            "grzechu, bo gniew i zdenerwowanie są i dla nich, i dla innych przeszkodą w miłości."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 8]\nWybór ministra generalnego tego braterstwa i kapituła w Zielone Święta",
                array = arrayOf(
                    1 to "Wszyscy bracia obowiązani są mieć zawsze jednego z braci tego zakonu za ministra " +
                            "generalnego i sługę całego braterstwa i mają ścisły obowiązek być mu posłuszni. ",
                    2 to "W razie jego śmierci ministrowie  prowincjalni i kustosze niech wybiorą następcę na kapitule w Zielone Święta. " +
                            "Na tę kapitułę mają się zawsze zbierać razem ministrowie prowincjalni w miejscu wyznaczonym przez ministra generalnego, ",
                    3 to "i to raz na trzy lata albo rzadziej lub częściej, w zależności od zarządzenia wspomnianego ministra. ",
                    4 to "I jeśli kiedy dla ogółu ministrów prowincjalnych i kustoszów stałoby się widoczne, " +
                            "że wspomniany minister nie jest odpowiedni do służby i ogólnego pożytku braci, " +
                            "obowiązani będą wspomniani bracia, którym powierzony jest wybór, w imię Pańskie wybrać sobie innego na kustosza. ",
                    5 to "Po kapitule Zielonych Świąt poszczególni ministrowie i kustosze, jeżeli zechcą i uznają " +
                            "to za wskazane, mogą w tym samym roku w swoich kustodiach raz jeden zwołać swych braci na kapitułę."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 9]\nKaznodzieje",
                array = arrayOf(
                    1 to "Bracia niech nie głoszą kazań w diecezji biskupa, który by im tego zabronił. ",
                    2 to "I żaden z braci niech się nigdy nie waży głosić kazań do ludu, dopóki go minister " +
                            "generalny tego braterstwa nie podda egzaminowi, nie zatwierdzi i nie powierzy mu obowiązku kaznodziejskiego. ",
                    3 to "Upominam również i zachęcam tych braci, aby ich słowa w kazaniach, jakie głoszą, były wypróbowane i czyste (por. Ps 11, 7; 17, 31) na pożytek i zbudowanie ludu. ",
                    4 to "Niech mówią mu o wadach i cnotach, o karze i chwale słowami zwięzłymi; bo słowo skrócone uczynił Pan na ziemi (por. Rz 9, 28)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 10]\nUpominanie i poprawianie braci",
                array = arrayOf(
                    1 to "Bracia, którzy są ministrami i sługami innych braci, niech odwiedzają i upominają swoich braci " +
                            "oraz pokornie i z miłością niech ich nakłaniają do poprawy, nie wydając im poleceń niezgodnych z ich sumieniem i naszą regułą. ",
                    2 to "Bracia zaś, którzy są podwładnymi, niech pamiętają, że dla Pana Boga wyrzekli się własnej woli. ",
                    3 to "Dlatego nakazuję im stanowczo, aby byli posłusznymi swoim ministrom we wszystkim " +
                            "co przyrzekli Panu zachowywać, a co nie sprzeciwia się ich sumieniu i regule naszej.\n",
                    4 to "I gdziekolwiek są bracia, którzy poznają i zrozumieją, że nie zdołają zachować reguły według ducha, powinni i mogą zwrócić się do swoich ministrów. ",
                    5 to "Ministrowie zaś niech ich przyjmą z miłością i dobrocią i niech im okażą tak wielką " +
                            "serdeczność, aby bracia mogli z nimi rozmawiać i postępować jak panowie ze swoimi sługami. ",
                    6 to "Tak bowiem powinno być, aby ministrowie byli sługami wszystkich braci. ",
                    7 to "Upominam zaś i zachęcam w Panu Jezusie Chrystusie, aby bracia wystrzegali się " +
                            "wszelkiej pychy, próżnej chwały, zazdrości, chciwości (por. Łk 12, 15), " +
                            "trosk i zabiegów tego świata (por. Mt 13, 22), obmowy i szemrania, i ci, którzy nie umieją czytać, niech się nie starają nauczyć, ",
                    8 to "lecz niech pamiętają, że nade wszystko powinni pragnąć posiąść Ducha Pańskiego wraz z Jego uświęcającym działaniem, ",
                    9 to "modlić się zawsze do Niego czystym sercem i mieć pokorę, cierpliwość w prześladowaniu i w chorobie, ",
                    10 to "i kochać tych, którzy nas prześladują, ganią i obwiniają, bo Pan mówi: " +
                            "Miłujcie waszych nieprzyjaciół i módlcie się za prześladujących i potwarzających was (por. Mt 5, 44). ",
                    11 to "Błogosławieni, którzy cierpią prześladowanie dla sprawiedliwości, bo do nich należy królestwo niebieskie (Mt 5, 10).",
                    12 to "Kto zaś wytrwa aż do końca, ten będzie zbawiony (Mt 10, 22)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 11]\nBracia nie powinni wchodzić do klasztorów mniszek",
                array = arrayOf(
                    1 to "Zakazuję stanowczo wszystkim braciom wdawać się w podejrzane znajomości i rozmowy z kobietami. ",
                    2 to "I niech nie wchodzą do klasztorów mniszek oprócz tych braci, którym Stolica Apostolska udzieliła specjalnego pozwolenia. ",
                    3 to "Niech też nie będą ojcami chrzestnymi mężczyzn lub kobiet, aby z tego powodu nie powstało zgorszenie wśród braci lub z powodu braci. "
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 12]\nCi, którzy udają się do saracenów i innych niewiernych",
                array = arrayOf(
                    1 to "Bracia, którzy za boskim natchnieniem zechcieliby udać się do saracenów i innych " +
                            "niewiernych, niech proszą swoich ministrów prowincjalnych o pozwolenie. ",
                    2 to "Ministrowie zaś niech udzielają pozwolenia tylko tym, których uznają za odpowiednich do wysłania. ",
                    3 to "Ponadto nakazuję ministrom na mocy posłuszeństwa prosić Ojca Świętego o jednego z kardynałów świętego " +
                            "Kościoła Rzymskiego, by kierował ty braterstwem, opiekował się nim i utrzymywał je w karności, ",
                    4 to "abyśmy zawsze poddani i położeni pod stopy tego świętego Kościoła, ugruntowani w wierze (por. Kol 1, 23) " +
                            "katolickiej, zachowywali ubóstwo i pokorę, i świętą Ewangelię Pana naszego Jezusa Chrystusa, jak to stanowczo przyrzekliśmy."
                )
            ).let { append(it) }

            buildChapter(
                name = "[c.d. bulli]",
                text = "Żadnemu więc człowiekowi nie wolno w ogóle naruszać tego dokumentu naszego potwierdzenia " +
                        "albo zuchwałym postępowaniem sprzeciwiać się mu. Gdyby jednak ktoś odważył się na to, " +
                        "niech wie, że ściągnie na siebie gniew Boga wszechmogącego i Jego Świętych Apostołów Piotra i Pawła.\n\n" +
                        "Dano na Lateranie, 29 listopada, w ósmym roku naszego pontyfikatu. Kończy się zatwierdzenie Reguły Braci Mniejszych.\n\n"
            ).let { append(it) }
        },
        justifiedString(
            buildAnnotatedString {
                append(
                    "Mnie, bratu Franciszkowi, Pan dał tak rozpocząć życie pokuty: gdy byłem w grzechach, widok trędowatych wydawał mi się bardzo przykry. " +
                            "I Pan sam wprowadził mnie między nich i okazywałem im miłosierdzie. " +
                            "I kiedy odchodziłem od nich, to, co wydawało mi się gorzkie, zmieniło mi się w słodycz duszy i ciała; " +
                            "i potem nie czekając długo, porzuciłem świat. I Pan dał mi w kościołach taką wiarę, że tak po prostu modliłem się i mówiłem: " +
                            "„Wielbimy Cię, Panie Jezu Chryste, [tu] i we wszystkich kościołach Twoich, " +
                            "które są na całym święcie i błogosławimy Tobie, że przez święty krzyż Twój odkupiłeś świat”.\n\n" +
                            "Potem dał mi Pan i daje tak wielkie zaufanie do kapłanów, którzy żyją według zasad świętego Kościoła Rzymskiego " +
                            "ze względu na ich godność kapłańską, że chociaż prześladowaliby mnie, chcę się do nich zwracać. " +
                            "I chociaż miałbym tak wielką mądrość jak Salomon, a spotkałbym bardzo biednych kapłanów tego świata, " +
                            "nie chcę wbrew ich woli nauczać w parafiach, w których oni przebywają. " +
                            "I tych, i wszystkich innych chcę się bać, kochać i szanować jako moich panów. " +
                            "I nie chcę dopatrywać się w nich grzechu, ponieważ rozpoznaję w nich Syna Bożego i są moimi panami. " +
                            "I postępuję tak, ponieważ na tym świecie nie widzę niczego wzrokiem cielesnym z Najwyższego Syna Bożego, " +
                            "tylko Jego Najświętsze Ciało i Najświętszą Krew, które oni przyjmują i oni tylko innym udzielają. " +
                            "I pragnę aby te najświętsze tajemnice były ponad wszystko czczone, uwielbiane i umieszczane w godnych miejscach. " +
                            "Gdzie tylko znajdę w miejscach nieodpowiednich napisane najświętsze imiona i słowa Jego, " +
                            "pragnę je zbierać i proszę, aby je zbierano i składano w odpowiednim miejscu. " +
                            "I wszystkich teologów i tych, którzy nam podają najświętsze słowa Boże, " +
                            "powinniśmy szanować i czcić jako tych, którzy dają nam ducha i życie "
                )
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("(por. J 6, 64)") }
                append(".\n\n")
                append(
                    "I gdy Pan zlecił mi troskę o braci, nikt mi nie wskazywał, co mam czynić, lecz sam Najwyższy objawił mi, że powinienem żyć " +
                            "według Ewangelii świętej. I ja kazałem to spisać w niewielu prostych słowach, i Ojciec św. potwierdził mi. " +
                            "A ci, którzy przychodzili przyjąć ten sposób życia, rozdawali ubogim wszystko, co mogli posiadać "
                )
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("(por. Tb 1, 3)") }
                append("; i zadowalali się jedną tuniką połataną od spodu i z wierzchu, sznurem i spodniami. I nie chcieliśmy mieć więcej.\n\n")
                append(
                    "Oficjum odmawialiśmy my, klerycy, jak inni duchowni; nieklerycy odmawiali: Ojcze nasz; " +
                            "i bardzo chętnie przebywaliśmy w Kościołach. I byliśmy niewykształceni i ulegli wszystkim. " +
                            "I ja pracowałem własnymi rękami i pragnę pracować; i chcę stanowczo, aby wszyscy inni bracia " +
                            "oddawali się pracy, co jest wyrazem uczciwości. Ci, którzy nie umieją, niech się nauczą, " +
                            "nie z powodu chciwości, aby otrzymać wynagrodzenie za pracę, lecz dla przykładu i zwalczania lenistwa. " +
                            "A kiedy nie dadzą nam zapłaty za pracę, udajmy się do stołu Pańskiego i prośmy o jałmużnę od drzwi do drzwi.\n\n"
                )
                append("Pan objawił mi, abyśmy używali pozdrowienia: niech Pan obdarzy was pokojem ")
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("(por. Lb 6, 26)") }
                append(
                    ". Niech bracia strzegą się, aby wcale nie przyjmowali kościołów, ubogich mieszkań i wszystkiego, " +
                            "co się dla nich buduje, jeśli się to nie zgadza ze świętym ubóstwem, które ślubowaliśmy w regule, " +
                            "goszcząc w nich zawsze jak obcy i pielgrzymi "
                )
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("(por. 1 P 2, 11)") }
                append(".\n\n")
                append(
                    "Nakazuję stanowczo na mocy posłuszeństwa wszystkim braciom, gdziekolwiek są, " +
                            "aby nie ważyli się ani osobiście, ani przez pośredników prosić w kurii rzymskiej " +
                            "o jakiekolwiek pisma [polecające] ani dla kościoła, ani dla żadnego innego miejsca, " +
                            "ani pod pozorem kaznodziejstwa, ani z powodu prześladowania cielesnego; lecz jeśli " +
                            "ich gdzieś nie przyjmą, niech się schronią do innego kraju, aby tam z błogosławieństwem " +
                            "Bożym czynić pokutę.\n\n"
                )
                append(
                    "I bardzo pragnę być posłusznym ministrowi generalnemu tego braterstwa i temu gwardianowi, " +
                            "którego on zechce mi wyznaczyć. I tak chcę być ujęty w jego rękach, żebym bez jego woli " +
                            "i wbrew posłuszeństwu nie mógł się poruszać ani cokolwiek czynić, gdyż on jest moim panem.\n\n"
                )
                append(
                    "I chociaż jestem człowiekiem prostym i chorym, pragnę jednak mieć zawsze kleryka, żeby mi odmawiał oficjum, jak przepisuje reguła. " +
                            "I wszyscy inni bracia powinni tak samo słuchać swoich gwardianów i odmawiać oficjum według reguły. " +
                            "A jeśli znaleźliby się bracia, którzy nie odmawialiby oficjum według reguły i chcieliby wprowadzić jakieś zmiany albo nie byliby katolikami, " +
                            "to wszyscy bracia, gdziekolwiek przebywają, spotkawszy któregoś z nich, obowiązani będą pod posłuszeństwem " +
                            "doprowadzić go do najbliższego kustosza tego miejsca, gdzie go znaleźli. A kustosz pod posłuszeństwem obowiązany jest " +
                            "strzec go pilnie we dnie i w nocy jak więźnia, aby nie mógł mu uciec, dopóki go we własnej osobie nie odda w ręce swego ministra. " +
                            "I minister pod posłuszeństwem obowiązany jest odesłać go przez takich braci, którzy będą go strzec we dnie i w nocy jak więźnia, " +
                            "dopóki nie przyprowadzą go do pana Ostii, który jest panem i opiekunem całego braterstwa, utrzymującym je w karności. " +
                            "I niech nie mówią bracia: „To jest inna reguła”, jest to bowiem przypomnienie, pouczenie, zachęta i mój testament, " +
                            "który ja, maluczki brat Franciszek, zostawiam wam, braciom moim umiłowanym, po to, " +
                            "abyśmy bardziej w duchu katolickim zachowywali regułę ślubowaną Panu.\n\n"
                )
                append(
                    "A ministrowi generalnemu i wszystkim innym ministrom i kustoszom zabraniam na mocy posłuszeństwa " +
                            "cokolwiek do tych słów dodawać lub ujmować. Niech to pismo zawsze mają przy sobie wraz z regułą. " +
                            "I na wszystkich kapitułach, które odprawiają, czytając regułę, niech przeczytają i te słowa. " +
                            "I wszystkim moim braciom klerykom i nie-klerykom zakazuję stanowczo na mocy posłuszeństwa wprowadzać " +
                            "wyjaśnienia do reguły i do tych słów; niech nie mówią: „Tak należy je rozumieć”. Lecz jak Pan " +
                            "dał mi prosto i jasno mówić i napisać regułę i te słowa, tak prosto i bez wyjaśnień rozumiejcie je " +
                            "i gorliwie zachowujcie aż do końca.\n\n"
                )
                append(
                    "I ktokolwiek to zachowa, niech w niebie będzie napełniony błogosławieństwem Ojca najwyższego, " +
                            "a na ziemi błogosławieństwem umiłowanego Syna Jego z Najświętszym Duchem Pocieszycielem " +
                            "i ze wszystkimi mocami niebios, i ze wszystkimi świętymi. I ja, brat Franciszek, najmniejszy wasz sługa, " +
                            "jak tylko mogę, wewnętrznie i zewnętrznie potwierdzam wam to najświętsze błogosławieństwo.\n\n"
                )
            }
        ),
        justifiedString(
            "Napisz, że błogosławię wszystkich moich braci, którzy są teraz w zakonie i będą wstępowali do niego aż do końca świata (…). " +
                    "Ponieważ z powodu słabości i bólu w chorobie nie mogę mówić, wyrażę mą wolę braciom moim krótko, w trzech zdaniach, mianowicie:\n" +
                    "aby na znak pamięci na moje błogosławieństwo i mój testament zawsze kochali się wzajemnie;\n" +
                    "aby zawsze kochali i dochowywali wierności naszej pani, świętemu ubóstwu\n" +
                    "i aby zawsze byli wierni i ulegli dostojnikom i wszystkim duchownym świętej matki Kościoła.\n\n"
        ),
    )
}