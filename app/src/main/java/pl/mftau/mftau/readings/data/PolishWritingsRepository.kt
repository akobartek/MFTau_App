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
        "Traktat o radości doskonałej"
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
                    22 to "A ci, którzy tego nie uczynią, będą musieli zdać rachunek w dzień sądu (por. Mt 12, 36) przed trybunałem Pana naszego Jezusa Chrystusa (por. Rz 14, 10).\n"
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
                        46 to "I miejmy w pogardzie i poniżeniu nasze ciała, bo wszyscy z własnej winy jesteśmy nędzni i zepsuci, cuchnący i robaki, jak mówi Pan przez proroka: Ja jestem robak, " +
                                "a nie człowiek, pośmiewisko ludzkie i wzgarda pospólstwa (Ps 21, 7).\n\n",
                        47 to "Nie pragnijmy nigdy górować nad innymi, lecz bądźmy raczej sługami i poddanymi wszelkiemu ludzkiemu stworzeniu ze względu na Boga (1 P 2, 13). ",
                        48 to "A na tych wszystkich i te wszystkie, które będą to czynić i wytrwają aż do końca, spocznie Duch Pański (por. Iz 11, 2) i uczyni w nich mieszkanie i miejsce pobytu (por. J 14, 23). ",
                        49 to "I będą synami Ojca niebieskiego (por. Mt 5, 45), którego dzieła czynią. ",
                        50 to "I są oblubieńcami, braćmi i matkami Pana naszego Jezusa Chrystusa (por. Mt 12, 50).\n\n",
                        51 to "Jesteśmy oblubieńcami, gdy dusza wierna łączy się w Duchu Świętym z Jezusem Chrystusem. ",
                        52 to "Jesteśmy prawdziwie braćmi gdy spełniamy wolę Jego Ojca, który jest w niebie (por. Mt 12, 50).\n\n",
                        53 to "Jesteśmy matkami, gdy nosimy Go w sercu i w ciele naszym (por. 1 Kor 6, 20) przez miłość i czyste i szczere sumienie, rodzimy go przez święte uczynki, które powinny przyświecać innym jako wzór (por. Mt 5, 16).\n\n",
                        54 to "O, jakże chwalebna i święta, i wielka to rzecz: mieć w niebie Ojca! ",
                        55 to "O, jak świętą jest rzeczą mieć pocieszyciela, tak pięknego i podziwu godnego oblubieńca! ",
                        56 to "O, jak świętą i cenną jest rzeczą mieć tak miłego, pokornego, darzącego pokojem, słodkiego i godnego miłości, i ponad wszystko upragnionego brata i syna, który życie swoje oddał za owce swoje (por. J 10, 15) i " +
                                "modlił się za nami do Ojca mówiąc: Ojcze święty, zachowaj w imię Twoje tych, których Mi dałeś (J 17, 11).\n\n",
                        57 to "Ojcze wszyscy, których Mi dałeś na świecie, Twoimi byli, i dałeś Mi ich (por. J 17, 6). ",
                        58 to "I słowa, które Mi dałeś, dałem im: a oni przyjęli i prawdziwie poznali, że od Ciebie wyszedłem i uwierzyli, że Ty Mnie posłałeś (J 17, 8). Proszę za nimi, a nie za światem (por. J 17, 17).\n\n",
                        59 to "I za nich samego siebie poświęcam w ofierze, aby zostali uświęceni w (J 17, 19) jedno, jak i my (J 17, 11) jesteśmy.\n\n",
                        60 to "I chcę Ojcze, aby i oni byli ze Mną tam, gdzie Ja jestem, żeby oglądali chwałę moją (J 17, 24) w królestwie Twoim (Mt 20, 21).\n\n",
                        61 to "Temu zaś, który tyle wycierpiał za nas, wyświadczył nam tyle dobra i wyświadczy w przyszłości, niech wszelkie stworzenie, które jest w niebie, na ziemi, " +
                                "w morzu i przepaściach, oddaje jako Bogu sławę, chwałę, cześć i błogosławieństwo (por. Ap 5, 13), ",
                        62 to "ponieważ On jest naszą mocą i siłą, On sam jest dobry, sam najwyższy, sam wszechmocny, godny podziwu, sam przesławny i święty, godny chwały i błogosławiony przez nieskończone wieki wieków. Amen.\n\n",
                        63 to "Ci wszyscy zaś, którzy nie trwają w pokucie i nie przyjmują ciała i Krwi Pana naszego Jezusa Chrystusa, ",
                        64 to "dopuszczają się występków i grzechów, i ulegają złej pożądliwości i złym pragnieniom, i nie zachowują tego co przyrzekli, ",
                        65 to "i służą cieleśnie światu cielesnymi pragnieniami i zabiegami świata, i troskami tego życia, ",
                        66 to "oszukani przez szatana, którego są synami i jego uczynki pełnią (por J 8, 41), są ślepi, ponieważ nie widzą prawdziwego Światła, Pana naszego Jezusa Chrystusa.\n\n",
                        67 to "Ci nie mają mądrości duchowej, ponieważ nie mają Syna Bożego, który jest prawdziwą Mądrością Ojca;O nich jest powiedziane: Mądrość ich została pochłonięta (por. Ps 106, 27).\n\n",
                        68 to "Widzą poznają, wiedzą i postępują źle, i świadomie gubią dusze.\n\n",
                        69 to "Patrzcie, ślepcy, zwiedzeni przez waszych nieprzyjaciół, to jest: przez ciało, świat i szatana, bo ciału słodko jest grzeszyć, a gorzko służyć Bogu, " +
                                "ponieważ wszelkie zło, występki i grzechy z serca ludzkiego wychodzą i pochodzą (por. Mk 7, 21. 23), jak mówi Pan w Ewangelii.\n\n",
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
                        88 to "A tych wszystkich i te wszystkie, którzy je życzliwie przyjmą, zrozumieją i odpis ich poślą innym, i jeśli wytrwają w tym aż do końca (por. Mt 10, 22; 24, 13), niech błogosławi Ojciec i Syn, i Duch Święty. Amen.\n"
                    )
                ).let { append(it) }
            }
        ),
        buildAnnotatedString {
            buildChapter(
                name = "I\nCiało Pańskie",
                text = "Pan Jezus powiedział swoim uczniom; Ja jestem drogą, prawdą i życiem. Nikt nie przychodzi do Ojca inaczej, jak tylko przez Mnie. " +
                        "Jeśli znalibyście Mnie, znalibyście z pewnością i mojego Ojca; i odtąd Go znacie i zobaczyliście Go. Mówi do Niego Filip: " +
                        "Panie, pokaż nam Ojca, a to nam wystarczy. Mówi mu Jezus: Tak długo jestem z wami, a nie poznaliście Mnie? Filipie, kto widzi Mnie, " +
                        "widzi i Ojca (J 14,6-9) mego. Ojciec mieszka w światłości niedostępnej (por. 1Tm 6,16) i Duchem jest Bóg (J 4,24), i Boga nikt nigdy " +
                        "nie widział (J 1,18). Dlatego tylko w duchu można Go oglądać, bo duch jest tym, który ożywia; ciało na nic się nie przyda (J 6,64).\n\n" +
                        "Ale i Syna w tym, w czym jest równy Ojcu, nikt nie może inaczej oglądać niż Ojca ani inaczej niż Ducha Świętego. " +
                        "Dlatego potępieni są wszyscy, którzy widzieli Pana Jezusa według człowieczeństwa, ale nie dostrzegli i nie uwierzyli " +
                        "według ducha i bóstwa, że jest on prawdziwym Synem Bożym. Tak samo potępieni są ci wszyscy, którzy widzą sakrament " +
                        "[Ciała Chrystusowego], dokonywany słowami Pana na ołtarzu przez ręce kapłana pod postacią chleba i wina, ale nie dostrzegają " +
                        "i nie wierzą według ducha i bóstwa, że jest to prawdziwie Najświętsze Ciało i Krew Pana naszego Jezusa Chrystusa. " +
                        "Poświadcza to sam Najwyższy słowami: To jest ciało moje i krew mego Nowego Przymierza [która za wielu będzie wylana] " +
                        "(por. Mk 14,22.24); i: Kto pożywa ciało moje i pije krew moją, ma życie wieczne (por J 6,55). Stąd Duch Pański przebywający " +
                        "w wiernych swoich, jest tym, który przyjmuje Najświętsze Ciało i Krew Pana. Wszyscy inni, którzy nie mają udziału w tym Duchu, " +
                        "a ośmielają się przyjmować je, sąd sobie jedzą i piją (por. 1Kor 11,29).\n" +
                        "Dlatego: Synowie ludzcy, dokąd będziecie twardego serca? (Ps 4,3). Dlaczego nie poznajecie prawdy i nie wierzycie w Syna Bożego? " +
                        "(por. J 9,35). Oto uniża się co dzień (por. Flp 2,8) jak wtedy, gdy z tronu królewskiego (por. Mdr 18,15) zstąpił do łona Dziewicy. " +
                        "Codziennie przychodzi do nas w pokornej postaci. Co dzień zstępuje z łona Ojca na ołtarz w rękach kapłana. I jak ukazał się " +
                        "świętym apostołom w rzeczywistym ciele, tak i teraz ukazuje się nam w świętym Chlebie. I jak oni swoim wzrokiem cielesnym widzieli " +
                        "tylko Jego Ciało, lecz wierzyli, że jest Bogiem, ponieważ oglądali Go oczyma ducha, tak i my, widząc chleb i wino oczyma cielesnymi, " +
                        "starajmy się dostrzegać i wierzmy mocno, że jest to jego żywe i prawdziwe Najświętsze Ciało i Krew. I w taki sposób Pan jest zawsze " +
                        "ze swymi wiernymi, jak sam mówi: Oto Ja jestem z wami aż do skończenia świata (por. Mt 28,20)."
            ).let { append(it) }
            buildChapter(
                name = "II\nZło samowoli",
                text = "Pan powiedział do Adama: Z każdego drzewa jedz; ale z drzewa wiadomości dobrego i złego nie jedz (por. Rdz 2,16-17). " +
                        "Z każdego drzewa rajskiego mógł jeść, ponieważ dopóki nie wykroczył przeciw posłuszeństwu, nie zgrzeszył. " +
                        "Ten bowiem jada z drzewa poznania dobra, kto przywłaszcza sobie swoją wolę i wynosi się z dobra, jakie Pan mówi i działa w nim. " +
                        "I tak z podszeptu szatana i z powodu przekroczenia przykazania stało się to owocem poznania złą. A za to trzeba ponieść karę."
            ).let { append(it) }
            buildChapter(
                name = "III\nPosłuszeństwo doskonałe",
                text = "Pan mówi w Ewangelii: Kto nie wyrzekłby się wszystkiego, co posiada, nie może być moim uczniem (por. Łk 14,33); " +
                        "i: Kto chciałby zachować swoje życie, straci je (Łk 9,24). Ten człowiek opuszcza wszystko, co ma, i traci swoje ciało, " +
                        "który siebie samego oddaje całkowicie pod posłuszeństwo w ręce swego przełożonego. I cokolwiek czyni i mówi, " +
                        "jeśli wie, że nie sprzeciwia się to jego woli, jest to prawdziwe posłuszeństwo, byleby to, co czyni, było dobre. " +
                        "I choćby podwładny widział coś lepszego i pożyteczniejszego dla swej duszy niż to, co nakazuje przełożony, " +
                        "niech dobrowolnie złoży Bogu z tego ofiarę i niech stara się spełnić życzenie przełożonego. " +
                        "To jest bowiem posłuszeństwo z miłości (1P 1,22), ponieważ miłe jest Bogu i bliźniemu.\n\n" +
                        "Jeśli przełożony wyda jakieś polecenie niezgodne z jego [podwładnego] sumieniem, wolno mu nie posłuchać, " +
                        "niech go jednak nie opuszcza. A jeśli z tego powodu będzie przez kogoś prześladowany, niech go dla Boga miłuje. " +
                        "Kto bowiem woli znosić prześladowanie niż odłączyć się od braci, ten trwa rzeczywiście " +
                        "w doskonałym posłuszeństwie, bo życie swoje oddaje za braci swoich (por. J 15,13).\n\n" +
                        "Jest bowiem wielu zakonników, którym się wydaje, że widzą lepsze rzeczy do zrobienia niż te, " +
                        "które nakazują przełożeni; ci oglądają się wstecz (por. Łk 9,62) i powracają do wymiotów własnej woli " +
                        "(por. Prz 26,11; 2P 2,22). Ci są mordercami i swoim złym przykładem gubią wiele dusz."
            ).let { append(it) }
            buildChapter(
                name = "IV\nNikt nie powinien przywłaszczać sobie przełożeństwa",
                text = "Nie przyszedłem, aby Mi służono, lecz aby służyć (por. Mt 20, 28) mówi Pan. Ci, którzy są postawieni nad innymi, " +
                        "tak powinni się chlubić z tego przełożeństwa, jakby zostali wyznaczeni do obowiązku umywania nóg braciom. " +
                        "I jeśli utrata przełożeństwa zasmuca ich bardziej, niż utrata obowiązku umywania nóg, " +
                        "tym więcej napełniają sobie trzosy zniebezpieczeństwem dla duszy (por. J 12,6)."
            ).let { append(it) }
            buildChapter(
                name = "V\nNikt nie powinien unosić się pychą, lecz niech chlubi się w Krzyżu Pańskim",
                text = "Człowieku zastanów się, do jak wysokiej godności podniósł cię Pan Bóg, bo stworzył cię i ukształtował według ciała " +
                        "na obraz umiłowanego Syna swego i na podobieństwo według ducha (por. Rdz 1,26). I wszystkie stworzenia, które są pod niebem, " +
                        "służą na swój sposób swemu Stwórcy, uznają Go i słuchają lepiej niż ty. I nawet złe duchy nie ukrzyżowały Go, " +
                        "lecz to ty wraz z nimi ukrzyżowałeś Go i krzyżujesz nadal przez upodobanie w wadach i grzechach.\n\n" +
                        "Z czego więc możesz się chlubić? Choćbyś bowiem był tak bystry i mądry, że posiadłbyś wszelką wiedzę (por. 1Kor 13,2) " +
                        "i umiał wyjaśniać wszystkie rodzaje języków (por. 1Kor 12, 10. 28) i badać wnikliwie tajemnice niebieskie, nie możesz się chlubić " +
                        "tym wszystkim; bo jeden szatan poznał rzeczy niebieskie i teraz zna sprawy ziemskie lepiej niż wszyscy ludzie, " +
                        "chociaż mógłby być jakiś człowiek, który otrzymałby od Pana szczególne poznanie najwyższej mądrości. Tak samo, " +
                        "gdybyś był piękniejszy i bogatszy od wszystkich i gdybyś nawet cuda czynił, tak że wyrzucałbyś złe duchy, wszystko to " +
                        "zwraca się przeciwko tobie i nie należy do ciebie, i nie możesz się tym wcale chlubić. Lecz w tym możemy się chlubić: " +
                        "w słabościach naszych (por. 2Kor 12,5) i w codziennym dźwiganiu świętego krzyża Pana naszego Jezusa Chrystusa (por. Łk 14,27)."
            ).let { append(it) }
            buildChapter(
                name = "VI\nNaśladowanie Pana",
                text = "Bracia, spoglądajmy na dobrego Pasterza, który dla zbawienia swych owiec wycierpiał mękę krzyżową. " +
                        "Owce Pana poszły za Nim w ucisku i prześladowaniu, w upokorzeniu i głodzie, w chorobie i doświadczeniu, " +
                        "i we wszystkich innych trudnościach; i w zamian za to otrzymały od Pana życie wieczne.\n\n" +
                        "Stąd wstyd dla nas, sług Bożych, że święci dokonywali wielkich dzieł, a my chcemy otrzymać chwałę i cześć, opowiadając o nich."
            ).let { append(it) }
            buildChapter(
                name = "VII\nZa wiedzą powinny iść dobre czyny",
                text = "Apostoł mówi: Litera zabija, a duch ożywia (2Kor 3,6). Litera zabija tych, którzy pragną poznać tylko same słowa, " +
                        "aby uchodzić za mądrzejszych od innych i zdobyć wielkie bogactwa, i rozdać je krewnym i przyjaciołom.\n\n" +
                        "I tych zakonników zabija litera, którzy nie chcą postępować według ducha Pisma Bożego, " +
                        "lecz pragną raczej poznawać tylko słowa i wyjaśniać je innym.\n\n" +
                        "I tych ożywia duch Pisma Bożego, którzy żadnej wiedzy, jaką posiadają i pragną posiąść, nie przypisują ciału, " +
                        "lecz słowem i przykładem odnoszą do Najwyższego Pana Boga, do którego należy wszelkie dobro."
            ).let { append(it) }
            buildChapter(
                name = "VIII\nNależy unikać grzechu zazdrości",
                text = "Apostoł mówi: Nikt nie może powiedzieć: Pan Jezus bez pomocy Ducha Świętego (1Kor 12, 3); i: Nie ma nikogo, " +
                        "kto by czynił dobro, nie ma ani jednego (Rz 3, 12). Ktokolwiek więc zazdrości bratu swemu dobra, jakie Pan mówi " +
                        "i czyni w nim, dopuszcza się grzechu bluźnierstwa, bo zazdrości samemu Najwyższemu, który mówi i czyni wszelkie dobro."
            ).let { append(it) }
            buildChapter(
                name = "IX\nMiłość",
                text = "Pan mówi: Miłujcie nieprzyjaciół waszych [czyńcie dobrze tym, którzy was nienawidzą i módlcie się za prześladujących " +
                        "i spotwarzających was] (Mt 5,44). Ten bowiem rzeczywiście kocha swego nieprzyjaciela, kto nie boleje nad doznawaną " +
                        "krzywdą, lecz dla miłości Bożej smuci się grzechem jego duszy. I czynem okazuje mu miłość."
            ).let { append(it) }
            buildChapter(
                name = "X\nUmartwienie ciała",
                text = "Wielu jest takich, którzy, grzesząc lub doznając krzywdy, składają winy na nieprzyjaciela lub bliźniego. Lecz nie tak jest: każdy bowiem " +
                        "ma w swej mocy nieprzyjaciela, mianowicie ciało, przez które grzeszy. Dlatego błogosławiony ten sługa (Mt 24,46), który mając " +
                        "w swej władzy takiego nieprzyjaciela, będzie zawsze trzymał go związanego i roztropnie miał się przed nim na baczności. " +
                        "Dopóki bowiem tak będzie postępował, żaden inny nieprzyjaciel, widzialny czy niewidzialny, nie będzie mógł mu szkodzić."
            ).let { append(it) }
            buildChapter(
                name = "XI\nNikt nie powinien się gorszyć grzechem bliźniego",
                text = "Słudze Bożemu tylko jedno nie powinno się podobać: grzech. I jakikolwiek grzech jakaś osoba popełniłaby " +
                        "i z tego powodu sługa Boży niepokoiłby się i gniewał, ale nie z miłości, gromadzi sobie winę (por. Rz 2,5). " +
                        "Ten sługa wiedzie życie prawe, wolne od przywiązań, który się nie denerwuje i nie gniewa na nikogo. " +
                        "I błogosławiony jest ten, który nic sobie nie zatrzymuje, oddając co jest cesarskiego, cesarzowi, a co jest Bożego, Bogu (Mt 22,21)."
            ).let { append(it) }
            buildChapter(
                name = "XII\nJak rozpoznać ducha Pańskiego",
                text = "Tak można poznać, czy sługa Boży ma ducha Pańskiego: jeśli jego ciało nie będzie wynosiło się pychą - " +
                        "bo ono zawsze jest przeciwne wszelkiemu dobru - z tego powodu, że Pan czyni przez niego jakieś dobro, " +
                        "lecz jeśli we własnych oczach uważałby się raczej za lichszego i mniejszego od wszystkich innych ludzi."
            ).let { append(it) }
            buildChapter(
                name = "XIII\nCierpliwość",
                text = "Błogosławieni pokój czyniący, bo nazwani będą synami Boga (Mt 5,9). Nie może poznać sługa Boży, " +
                        "ile ma cierpliwości i pokory, dopóki się wszystko dzieje po jego myśli. Gdy zaś przyjdzie czas, kiedy ci, " +
                        "którzy powinni postępować według jego woli, zaczną mu się sprzeciwiać; ile wtedy okaże cierpliwości i pokory, tyle jej ma, nie więcej."
            ).let { append(it) }
            buildChapter(
                name = "XIV\nUbóstwo ducha",
                text = "Błogosławieni ubodzy duchem, bo do nich należy królestwo niebieskie (Mt 5,3). Wielu jest takich, którzy oddając się gorliwie modlitwom " +
                        "i obowiązkom, nękają swe ciała licznymi postami i umartwieniami, lecz z powodu jednego tylko słowa, które zdaje się być krzywdą dla ich ciała, " +
                        "lub z powodu jakiejś rzeczy, której się ich pozbawia, wzburzają się i wpadają w gniew. Ci nie są ubodzy duchem. Kto bowiem jest rzeczywiście " +
                        "ubogi duchem, ten nienawidzi siebie samego (por. Łk 14,26) i kocha tych, którzy uderzają go w policzek (por. Mt 5,39)."
            ).let { append(it) }
            buildChapter(
                name = "XV\nPokój",
                text = "Błogosławieni pokój czyniący, bo nazwani będą synami Boga (Mt 5,9). Ci przynoszą naprawdę pokój, którzy wśród wszystkich cierpień, " +
                        "jakie ich spotykają na tym świecie, dla miłości Pana naszego Jezusa Chrystusa zachowują pokój duszy i ciała."
            ).let { append(it) }
            buildChapter(
                name = "XVI\nCzystość serca",
                text = "Błogosławieni czystego serca, bo oni będą oglądać Boga (Mt 5,8). Prawdziwie czystego serca są ci, którzy gardzą dobrami ziemskimi, " +
                        "szukają niebieskich i nie przestają nigdy czystym sercem i duszą uwielbiać i widzieć Pana, Boga żywego i prawdziwego."
            ).let { append(it) }
            buildChapter(
                name = "XVII\nPokorny sługa boży",
                text = "Błogosławiony jest ten sługa (Mt 24,46), który nie wynosi się z powodu dobra, jakie Pan mówi i czyni przez niego, bardziej niż z tego, " +
                        "jakie Pan mówi i czyni przez innego. Grzeszy ten człowiek, który chce więcej otrzymać od swego bliźniego niż dać z siebie Panu Bogu."
            ).let { append(it) }
            buildChapter(
                name = "XVIII\nMiłosierdzie wobec bliźniego",
                text = "Błogosławiony człowiek, który znosi swego bliźniego z jego ułomnościami, tak jak chciałby, aby jego znoszono, " +
                        "gdyby był w podobnym położeniu. Błogosławiony sługa, który Panu Bogu oddaje wszystkie dobra, kto bowiem cokolwiek zatrzymuje dla siebie, " +
                        "ukrywa wsobie pieniądze Pana Boga swego (por. Mt 25,18) i to, co sądził, że ma, będzie mu odjęte (por. Łk 8,18)."
            ).let { append(it) }
            buildChapter(
                name = "XIX\nDobry i pokorny zakonnik",
                text = "Błogosławiony sługa, który nie uważa się za lepszego, gdy go ludzie chwalą i wywyższają, niż wówczas, " +
                        "gdy go uważają za słabego, prostego i godnego pogardy; ponieważ człowiek jest tym tylko, czym jest w oczach Boga i niczym więcej.\n\n" +
                        "Biada temu zakonnikowi, który został wyniesiony do wysokiej godności i sam nie chce dobrowolnie ustąpić. " +
                        "A błogosławiony jest ten sługa (Mt 24,46), który został wyniesiony wysoko nie z własnej woli i pragnie być zawsze pod stopami innych."
            ).let { append(it) }
            buildChapter(
                name = "XX\nDobry i próżny zakonnik",
                text = "Błogosławiony ten zakonnik, który znajduje zadowolenie i radość tylko wnajświętszych słowach i dziełach Pana i pociąga przez nie ludzi do miłości Boga " +
                        "w radości i weselu (por. Ps 50,51). Biada temu zakonnikowi, który ma upodobanie w słowach płochych i próżnych i przez nie skłania ludzi do śmiechu."
            ).let { append(it) }
            buildChapter(
                name = "XXI\nPróżny i gadatliwy zakonnik",
                text = "Błogosławiony sługa, który gdy mówi, nie ujawnia wszystkich swoich spraw wnadziei nagrody i nie jest skory do mówienia (por. Prz 29,20), " +
                        "lecz przewiduje roztropnie, co powinien mówić lub opowiadać. Biada temu zakonnikowi, który dobra, jakich mu Pan udziela, chce ludziom okazywać słowami, " +
                        "by zdobyć uznanie, zamiast ukrywać je w sercu swoim (por. Łk 2,19.51), a innym ukazywać przez czyny.\nOtrzymuje on zapłatę swoją (por. Mt 6,2.5.16), a słuchacze odnoszą mało korzyści."
            ).let { append(it) }
            buildChapter(
                name = "XXII\nZnoszenie oskarżeń",
                text = "Błogosławiony sługa, który czyjeś uwagi, oskarżenia i upomnienia znosi tak cierpliwie, jakby pochodziły od niego samego. " +
                        "Błogosławiony sługa, który udzielane mu upomnienia przyjmuje spokojnie, słucha skromnie, pokornie wyznaje swą winę i chętnie wynagradza. " +
                        "Błogosławiony sługa, który nie jest skory do usprawiedliwiania się i znosi pokornie wstyd i naganę za winę, choćby się nawet jej nie dopuścił."
            ).let { append(it) }
            buildChapter(
                name = "XXIII\nPokora",
                text = "Błogosławiony sługa, który okazuje się równie pokorny wśród swych podwładnych, jak wśród swych panów. " +
                        "Błogosławiony sługa, który zawsze pozostaje pod rózgą karności. Sługą wiernym i roztropnym (por. Mt 24,45) jest ten, " +
                        "który nie zaniedbuje pokuty za wszystkie swoje przewinienia: wewnętrznie przez skruchę, a zewnętrznie przez wyznanie winy i uczynki wynagradzające."
            ).let { append(it) }
            buildChapter(
                name = "XXIV\nPrawdziwa miłość",
                text = "Błogosławiony sługa, który tak kochałby swego brata chorego, który nie może mu oddać przysługi, jak kocha zdrowego, który może mu pomóc."
            ).let { append(it) }
            buildChapter(
                name = "XXV\nPrawdziwa miłość",
                text = "Błogosławiony sługa, który tak kochałby i szanował swego brata będącego daleko od niego, " +
                        "jak gdyby był z nim i nie mówiłby w jego nieobecności tego, czego nie mógłby powiedzieć z miłością w jego obecności."
            ).let { append(it) }
            buildChapter(
                name = "XXVI\nSłudzy Boży powinni szanować duchownych",
                text = "Błogosławiony sługa, który ma zaufanie do duchownych uczciwie żyjących według zasad Kościoła rzymskiego. A biada tym, którzy nimi gardzą; " +
                        "choćby bowiem byli oni grzesznikami, jednak nikt nie powinien ich sądzić, bo Pan sam zastrzega sobie prawo sądu nad nimi. " +
                        "O ile bowiem większa jest ich posługa wobec Najświętszego Ciała i Krwi Pana naszego Jezusa Chrystusa, które oni sami przyjmują i sami tylko innym udzielają, " +
                        "o tyle większy grzech mają ci, którzy grzeszą przeciwko nim niż przeciwko wszystkim innym ludziom na tym świecie."
            ).let { append(it) }
            buildChapter(
                name = "XXVII\nCnoty, które usuwają wady",
                text = "Gdzie jest miłość i mądrość, tam nie ma ani bojaźni, ani niewiedzy. Gdzie jest cierpliwość i pokora, tam nie ma gniewu, ani zamętu. " +
                        "Gdzie jest ubóstwo z radością, tam nie ma ani chciwości, ani skąpstwa. Gdzie jest pokój i rozmyślanie, tam nie ma ani zatroskania, ani roztargnienia. " +
                        "Gdzie jest bojaźń Pańska, która strzeże domu swego (por. Łk 11,21), tam nieprzyjaciel nie ma możliwości wejścia. " +
                        "Gdzie jest miłosierdzie i delikatność, tam nie ma ani zbytku, ani zatwardziałości serca."
            ).let { append(it) }
            buildChapter(
                name = "XXVIII\nDobro należy ukrywać, aby go nie stracić",
                text = "Błogosławiony sługa, który w niebie gromadzi (por Mt 6,20) dobra, jakie Pan mu okazał i nie pragnie ujawniać ich ludziom dla zdobycia uznania, " +
                        "bo sam Najwyższy okaże jego czyny tym, którym zechce. Błogosławiony sługa, który tajemnice Pańskie zachowuje w sercu swoim (por. Łk 2,19.51).\n"
            ).let { append(it) }
        },
        buildAnnotatedString {
            buildChapter(
                name = "[Prolog]",
                array = arrayOf(
                    1 to "W imię Ojca i Syna, i Ducha Świętego.\n",
                    2 to "Oto jest sposób życia według Ewangelii Jezusa Chrystusa, o którego uznanie i zatwierdzenie prosił papieża brat Franciszek. I ten [papież] uznał go i zatwierdził dla niego i jego braci obecnych i przyszłych.\n",
                    3 to "Brat Franciszek i każdy, kto będzie zwierzchnikiem tego zakonu, ma przyrzec posłuszeństwo i szacunek papieżowi Innocentemu i jego następcom. ",
                    4 to "I wszyscy inni bracia mają obowiązek posłuszeństwa bratu Franciszkowi i jego następcom."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 1]\nBracia powinni żyć w posłuszeństwie, bez własności i w czystości",
                array = arrayOf(
                    1 to "Reguła i życie tych braci polega na tym, aby żyć w posłuszeństwie, w czystości i bez własności oraz zachowywać naukę i naśladować przykład Pana naszego Jezusa Chrystusa, który mówi: ",
                    2 to "Jeśli chcesz być doskonałym, idź, sprzedaj wszystko (por. Łk 18, 22), co masz i daj ubogim, a będziesz miał skarb w niebie; przyjdź i chodź za Mną (Mt 19, 21). ",
                    3 to "I: Jeśli kto chce iść za Mną, niech się zaprze samego siebie i weźmie krzyż swój, i naśladuje Mnie (Mt 16, 24). ",
                    4 to "Także: Jeśli kto chce przyjść do Mnie, a nie ma w nienawiści ojca i matki, i żony, i dzieci, i braci, i sióstr, jeszcze też i życia swego, nie może być Moim uczniem (por. Łk 14, 26.) ",
                    5 to "I każdy, kto opuściłby ojca albo matkę, braci albo siostry, żonę albo dzieci, domy albo pola dla Mnie, stokroć więcej otrzyma i posiądzie życie wieczne (por. Mt 19, 29; por. Mk 10, 29 i Łk 18, 29)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 2]\nPrzyjmowanie i ubiór braci",
                array = arrayOf(
                    1 to "Jeśli kto z Bożego natchnienia pragnie przyjąć ten sposób życia i przyjdzie do naszych braci, niech będzie przyjęty życzliwie. ",
                    2 to "Jeśli zaś okaże się wytrwały w pragnieniu przyjęcia naszego sposobu życia, niech bracia bardzo się strzegą, aby nie mieszać się do jego spraw doczesnych, lecz niech go jak najprędzej przedstawią swemu ministrowi. ",
                    3 to "Minister niech przyjmie go z prawdziwą dobrocią, doda zachęty i wytłumaczy mu dokładnie, na czym polega nasz sposób życia. ",
                    4 to "Potem kandydat, jeśli chce i może to uczynić zgodnie z sumieniem i bez przeszkód, sprzeda cały swój majątek i to wszystko postara się rozdać ubogim. ",
                    5 to "Bracia i ich ministrowie niech się strzegą, aby nie mieszać się wcale do jego spraw ",
                    6 to "i nie przyjmować od niego pieniędzy ani osobiście, ani przez zastępców. ",
                    7 to "Jeśli jednak bracia odczuwają brak rzeczy niezbędnych do życia, mogą z konieczności, jak inni ubodzy, przyjąć coś z wyjątkiem pieniędzy.\n\n",
                    8 to "Gdy [kandydat] powróci, minister da mu odzież na okres rocznej próby: dwie tuniki bez kaptura, sznur, spodnie i kaparon sięgający do pasa. ",
                    9 to "Po upływie roku i czasu próby niech będzie przyjęty pod posłuszeństwo. ",
                    10 to "Potem nie wolno mu przechodzić do innego zakonu ani „wałęsać się wbrew posłuszeństwu” według rozporządzenia papieża i Ewangelii. " +
                            "Bo nikt, kto rękę przykłada do pługa, a ogląda się wstecz, nie nadaje się do królestwa Bożego (Łk 9, 62). ",
                    11 to "Jeśli zaś przyjdzie ktoś, kto ma trudności w rozdaniu majątku, a ma dobrą wolę, wystarczy, że go opuści. ",
                    12 to "Nikogo nie wolno przyjmować wbrew praktyce i przepisom Kościoła świętego.\n\n",
                    13 to "Inni zaś bracia, którzy przyrzekli posłuszeństwo, niech mają jedną tunikę z kapturem i – jeśli zachodzi potrzeba – drugą bez kaptura, sznur i spodnie. ",
                    14 to "I wszyscy bracia niech noszą suknie ubogie i mogą je łatać, z błogosławieństwem Bożym, lichym płótnem lub innymi kawałkami; bo Pan mówi w Ewangelii: " +
                            "Ci, którzy są w kosztownej odzieży i w rozkoszach (Łk 7, 25) i w miękkie szaty się ubierają (Mt 11, 8), są w domach królewskich (Mt 11, 8; Łk 7, 25). ",
                    15 to "I choćby nazywano ich obłudnikami, niech jednak nie przestają czynić dobrze i niech nie poszukują drogich szat na tym świecie, aby mogli otrzymać szatę w królestwie niebieskim."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 3]\nBoskie oficjum i post",
                array = arrayOf(
                    1 to "Pan mówi: Ten rodzaj złych duchów może wyjść tylko przez post i modlitwę (Mk 9, 28); ",
                    2 to "i znowu: Gdy pościcie, nie bądźcie smutni jak obłudnicy (Mt 6, 16).\n\n",
                    3 to "Dlatego wszyscy bracia, zarówno klerycy jak nieklerycy, niech odmawiają zgodnie z zobowiązaniem oficjum boskie, uwielbienia i modlitwy. ",
                    4 to "Klerycy niech odmawiają oficjum i modlą się za żywych i zmarłych według zwyczaju przyjętego wśród duchowieństwa. ",
                    5 to "I dla wynagrodzenia za uchybienia i zaniedbania braci niech odmawiają co dzień Zmiłuj się nade mną, Boże (Ps 50) i Ojcze nasz. ",
                    6 to "Za zmarłych braci niech odmawiają Z głębokości (Ps 129) i Ojcze nasz. ",
                    7 to "I mogą mieć tylko księgi potrzebne do odmawiania oficjum. ",
                    8 to "I braciom nieklerykom wolno mieć psałterz, jeśli umieją czytać. ",
                    9 to "Ci zaś którzy nie umieją czytać, nie mogą mieć książek.\n\n",
                    10 to "Bracia nieklerycy niech odmawiają Wierzę w Boga i dwadzieścia cztery Ojcze nasz oraz Chwała Ojcu za jutrznię; za laudesy pięć; " +
                            "za prymę Wierzę w Boga i siedem Ojcze nasz oraz Chwała Ojcu; za tercję, sekstę i nonę po siedem; za nieszpory dwanaście; " +
                            "za kompletę Wierzę w Boga i siedem Ojcze nasz oraz Chwała Ojcu; za zmarłych siedem Ojcze nasz oraz Wieczny odpoczynek; " +
                            "i za uchybienia i zaniedbania braci co dzień trzy Ojcze nasz.\n\n",
                    11 to "I niech też wszyscy bracia poszczą od uroczystości Wszystkich Świętych do Bożego Narodzenia " +
                            "i od Objawienia Pańskiego, kiedy to Pan nasz Jezus Chrystus zaczął pościć, do Wielkanocy. ",
                    12 to "W innym zaś czasie według tego sposobu życia post obowiązuje ich tylko w piątki. ",
                    13 to "I wolno im według Ewangelii jeść wszystkie potrawy, jakie im podadzą (por. Łk 10, 8)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 4]\nStosunek między ministrami a innymi braćmi",
                array = arrayOf(
                    1 to "W imię Pańskie! ",
                    2 to "Wszyscy bracia, którzy są ustanowieni ministrami i sługami innych braci, niech rozmieszczają swoich braci, " +
                            "w prowincjach i w miejscach, w których będą, niech ich często odwiedzają, i w sposób duchowy napominają i umacniają. ",
                    3 to "A wszyscy inni moi umiłowani bracia niech będą im bardzo posłuszni we wszystkich sprawach dotyczących zbawienia duszy i nie sprzecznych z naszym sposobem życia.\n\n",
                    4 to "I niech odnoszą się do siebie, tak jak mówi Pan: Cokolwiek chcecie, aby wam ludzie czynili, i wy im czyńcie (Mt 7, 12) ",
                    5 to "oraz: „Nie czyń tego drugiemu, czego byś nie chciał, aby tobie czyniono”. ",
                    6 to "A ministrowie i słudzy niech pamiętają, że Pan mówi: nie przyszedłem, aby mi służono, lecz aby służyć (por. Mt 20, 28), " +
                            "i że troska o dusze braci została im powierzona. Jeśliby który z nich zginął z ich winy i przez ich zły przykład, " +
                            "będą musieli w dzień sądu zdać rachunek (por. Mt 12, 36) przed Panem Jezusem Chrystusem."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 5]\nPoprawianie braci błądzących",
                array = arrayOf(
                    1 to "Strzeżcie więc dusz waszych i dusz waszych braci, bo straszna to rzecz wpaść w ręce Boga żywego (Hbr 10, 31). ",
                    2 to "Jeśli jakiś minister wyda któremuś z braci polecenie sprzeczne z naszym sposobem życia lub sumieniem brata, " +
                            "ten nie jest obowiązany słuchać go; bo nie ma posłuszeństwa tam, gdzie kryje się wykroczenie lub grzech. ",
                    3 to "Niech jednak wszyscy bracia podlegający ministrom i sługom przyglądają się z uwagą i roztropnie postępowaniu ministrów i sług. ",
                    4 to "I jeśli zauważą, że któryś z nich postępuje nie według ducha i nie według naszego sposobu życia, " +
                            "lecz według ciała, gdyby po trzecim upomnieniu się nie poprawił, " +
                            "niech na kapitule Zielonych Świąt doniosą ministrowi i słudze całego braterstwa bez względu na jakiekolwiek jego protesty.\n\n",
                    5 to "Jeśli zaś między braćmi znalazłby się gdziekolwiek jakiś brat, chcący postępować " +
                            "według ciała, nie według ducha, niech mu współbracia zwrócą uwagę i upomną pokornie i z miłością. ",
                    6 to "Jeśli po trzecim upomnieniu nie chciałby się poprawić, niech go jak najprędzej odeślą " +
                            "do swego ministra i sługi lub dadzą mu znać. Minister i sługa niech postąpi z nim tak, jak mu się będzie najlepiej wobec Boga wydawało.\n\n",
                    7 to "I niech się strzegą wszyscy bracia , tak ministrowie i słudzy, jak i inni, aby się nie denerwowali ani gniewali " +
                            "z powodu czyjegoś grzechu lub złego [przykładu], bo szatan chce przez grzech jednego przywieść wielu do upadku. ",
                    8 to "Niech raczej, jak tylko mogą, duchowo wspomagają tego, który zgrzeszył, bo nie zdrowi, lecz chorzy potrzebują lekarza (por. Mt 9, 12; Mk 2, 17).\n\n",
                    9 to "Nie wolno również braciom piastować władzy ani panować nad nikim, zwłaszcza nad współbraćmi. ",
                    10 to "Tak bowiem mówi Pan w Ewangelii: Władcy panują nad narodami, a możni sprawują nad nimi władzę (Mt 20, 25). Nie tak będzie wśród braci (por. Mt 20, 26a); ",
                    11 to "i kto by chciał być większy, niech będzie ich ministrem (por. Mt 20, 26b) i sługą, ",
                    12 to "i kto wśród nich jest większy, niech stanie się jako mniejszy (por. Łk 22, 26).\n\n",
                    13 to "Niech żaden z braci źle nie mówi ani nie czyni drugiemu; ",
                    14 to "niech raczej przez miłość Ducha chętnie służą jedni drugim i będą sobie posłuszni (por. Ga 5, 13b). ",
                    15 to "I to jest prawdziwe i święte posłuszeństwo Pana naszego Jezusa Chrystusa. ",
                    16 to "I wszyscy bracia, ilekroć odstąpiliby od przykazań Pańskich i wałęsaliby się poza posłuszeństwem, " +
                            "niech wiedzą, że według słów proroka (Ps 118, 21) będą przeklęci, póki świadomie pozostaną w tym grzechu, poza posłuszeństwem. ",
                    17 to "I jeśli wytrwają w przykazaniach Pana, jak przyrzekli, zachowując Ewangelię i " +
                            "swój sposób życia, niech wiedzą, że żyją w prawdziwym posłuszeństwie, i niech będą błogosławieni przez Pana."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 6]\nZwracanie się braci do ministrów; żadnego brata nie wolno nazywać przeorem",
                array = arrayOf(
                    1 to "Bracia, w jakichkolwiek miejscach przebywają, jeśli nie mogą zachować naszego sposobu życia, niech zwrócą się jak najprędzej do swego ministra i przedstawią mu swoje trudności. ",
                    2 to "Minister niech stara się tak im zaradzić, jak by tego sobie samemu życzył, gdyby znalazł się w podobnym położeniu.\n\n",
                    3 to "I nikogo nie można nazywać przeorem, lecz wszyscy bez różnicy niech nazywają się braćmi mniejszymi. ",
                    4 to "I jeden drugiemu niech umywa nogi (por. J 13, 14)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 7]\nJak należy służyć i pracować",
                array = arrayOf(
                    1 to "Wszyscy bracia, w jakichkolwiek miejscach przebywają u kogoś, aby służyć albo pracować, nie mogą być rządcami ani urzędnikami, " +
                            "ani obejmować stanowisk kierowniczych w domach, gdzie służą. Niech nie przyjmują funkcji, która spowodowałaby zgorszenie lub wyrządziłaby szkodę ich duszy (por. Mk 8, 36). ",
                    2 to "Lecz niech będą mniejsi i poddani wszystkim, którzy przebywają w tym samym domu. ",
                    3 to "A bracia, którzy znają się na pracy, niech pracują wykonując ten sam zawód, którego się nauczyli, jeśli to nie szkodziłoby zbawieniu duszy i mogłoby być uczciwie wykonane. ",
                    4 to "Bo prorok mówi: Owoce prac twoich będziesz spożywał, szczęśliwy jesteś i dobrze ci będzie (Ps 127, 2); ",
                    5 to "i Apostoł: Kto nie chce pracować, niech nie je (por. 2 Tes 3, 10); ",
                    6 to "oraz: niech każdy pozostaje w takim zawodzie i obowiązku, w jakim został powołany (por. 1 Kor 7, 24). ",
                    7 to "Jako wynagrodzenie za pracę mogą przyjmować wszystko, co im potrzebne, z wyjątkiem pieniędzy. ",
                    8 to "A gdy zajdzie konieczność, niech idą po jałmużnę, jak inni ubodzy. ",
                    9 to "I wolno im mieć narzędzia żelazne i inne przydatne w ich rzemiośle.\n\n",
                    10 to "Wszyscy bracia „niech starają się gorliwie przykładać do jakiejś dobrej pracy”, bo napisane jest: „Zawsze czyń coś dobrego, aby diabeł zastał cię zajętym”. ",
                    11 to "I także: „Lenistwo jest nieprzyjacielem duszy”. ",
                    12 to "Dlatego słudzy Boży powinni zawsze oddawać się modlitwie lub jakiemuś dobremu zajęciu.\n\n",
                    13 to "Gdziekolwiek bracia będą przebywać, w pustelniach czy w innych miejscach, niech się strzegą, by nie przywłaszczali sobie żadnego pomieszczenia i nikomu nie bronili do niego dostępu. ",
                    14 to "I ktokolwiek przyszedłby do nich: przyjaciel czy wróg, złodziej czy łotr, niech go przyjmą uprzejmie. ",
                    15 to "I gdziekolwiek bracia są i w jakimkolwiek miejscu spotkają się, niech okazują sobie nawzajem poważanie i szacunek w duchu, z miłością, bez narzekania (por. 1 P 4, 9). ",
                    16 to "I niech strzegą się, by swym wyglądem nie robili wrażenia smutnych i posępnych obłudników; lecz niech okazują się radosnymi w Panu (por. Flp 4, 4) i pogodnymi, i życzliwymi."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 8]\nBracia nie powinni przyjmować pieniędzy",
                array = arrayOf(
                    1 to "Pan nakazuje w Ewangelii: Uważajcie, strzeżcie się wszelkiej złości i chciwości (por. Łk 12, 15); ",
                    2 to "i: miejcie się na baczności wobec kłopotów tego świata i trosk tego życia (por. Łk 21, 34).  \n\n",
                    3 to "Dlatego żaden z braci, gdziekolwiek jest i gdziekolwiek się udaje, nie może w żaden sposób brać ze sobą " +
                            "rzeczy mających wartość pieniężną lub pieniędzy, przyjmować lub kazać przyjmować, ani na zakup odzienia lub książek, " +
                            "ani jako wynagrodzenie za pracę, ani w żadnym innym wypadku, z wyjątkiem oczywistej potrzeby braci chorych; " +
                            "nie powinniśmy bowiem przypisywać większej użyteczności i znaczenia rzeczom mającym wartość pieniężną i pieniądzom niż kamieniom.",
                    4 to "I kto ich pożąda lub ceni je więcej niż kamienie, tego szatan stara się zaślepić. ",
                    5 to "Miejmy się więc na baczności my, którzy opuściliśmy wszystko (por. Mt 19, 27), abyśmy z powodu takiej drobnostki nie stracili królestwa niebieskiego. ",
                    6 to "I jeśli znajdziemy gdzieś pieniądze, nie dbajmy o nie więcej niż o pył, który depczemy stopami, bo to marność nad marnościami i wszystko marność (Syr 1, 2). ",
                    7 to "I jeśli, co nie daj, Boże, zdarzyłoby się, że jakiś brat zbiera lub ma rzeczy mające wartość pieniężną lub pieniądze, wyjąwszy wspomnianą potrzebę chorych, " +
                            "uważajmy go, wszyscy bracia, za fałszywego brata i odstępcę, i za złodzieja, i łotra, i za tego, który ma trzos (por. J 12, 6), o ile naprawdę nie nawróciłby się. ",
                    8 to "I w żadnym wypadku niech bracia nie przyjmują ani nie każą przyjmować, sami niech nie kwestują i nie każą kwestować ani rzeczy mających wartość pieniężną, " +
                            "ani pieniędzy dla jakichś domów lub miejsc. Nie powinni również towarzyszyć osobie kwestującej rzeczy mające wartość pieniężną lub pieniądze dla tych miejsc.\n\n",
                    9 to "Inne zaś posługi, które nie są sprzeczne z naszym sposobem życia, mogą bracia wykonywać gdziekolwiek z błogosławieństwem Bożym. ",
                    10 to "Jeśli jednak trędowaci znajdą się w oczywistej potrzebie, mogą bracia dla nich zbierać jałmużnę. ",
                    11 to "Niech się jednak bardzo wystrzegają pieniędzy. ",
                    12 to "Wszyscy bracia powinni również unikać wałęsania się dla niegodziwego zysku."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 9]\nJałmużna",
                array = arrayOf(
                    1 to "Wszyscy bracia niech się starają naśladować pokorę i ubóstwo Pana naszego Jezusa Chrystusa i niech pamiętają, " +
                            "że niczego innego nie powinniśmy mieć na tym świecie, tylko – jak mówi Apostoł: Zadowoleni jesteśmy z tego, że mamy pożywienie i ubranie (por. 1 Tm 6, 8). ",
                    2 to "I powinni się cieszyć, gdy przebywają wśród ludzi prostych i wzgardzonych, ubogich i słabych, chorych i trędowatych, i żebraków przy drogach.\n\n",
                    3 to "I gdy będzie potrzeba, niech idą po jałmużnę. ",
                    4 to "I nie powinni się wstydzić, niech raczej przypominają sobie, że Pan nasz Jezus Chrystus, Syn Boga żywego (por. J 11, 27), " +
                            "wszechmogącego, uczynił twarz swoją jak najtwardszą skałę (por. Iz 50, 7) i nie zawstydził się. ",
                    5 to "I był ubogi i bezdomny, i żył z jałmużny on sam i błogosławiona Dziewica, i uczniowie Jego. ",
                    6 to "I kiedy ludzie zawstydzaliby ich i nie chcieliby dać im jałmużny, bracia powinni Bogu za to dziękować! " +
                            "Bo za ten wstyd otrzymają wielkie uznanie przed trybunałem Pana naszego Jezusa Chrystusa. ",
                    7 to "I niech wiedzą, że wstyd staje się udziałem nie tych, którzy go doznają, lecz tych, którzy go sprawiają.\n\n",
                    8 to "I jałmużna jest dziedzictwem i prawem ubogich, które nabył dla nas Pan nasz Jezus Chrystus. ",
                    9 to "I bracia, którzy trudzą się zbieraniem ofiar, otrzymają wielką nagrodę i dają okazję do nagrody ofiarodawcom. " +
                            "Zginie bowiem wszystko, co ludzie zostawią na tym świecie, lecz za miłość i za złożone jałmużny otrzymają nagrodę od Pana.\n\n",
                    10 to "I z zaufaniem powinien jeden drugiemu wyjawiać swoje potrzeby, aby drugi mógł je zaspokoić i usłużyć [bratu]. ",
                    11 to "I jak matka kocha i karmi swego syna (por. 1 Tes 2, 7), tak każdy niech kocha i karmi swego brata; Pan użyczy mu do tego swej łaski. ",
                    12 to "I ten, który nie je, niech nie sądzi jedzącego (Rz 14, 3).\n\n",
                    13 to "I kiedykolwiek zajdzie konieczność, wolno wszystkim braciom wszędzie korzystać ze wszystkich pokarmów, które ludzie mogą jeść, " +
                            "jak Pan powiedział o Dawidzie, który jadł chleby pokładne (por. Mt 12, 4), których nie wolno było spożywać nikomu, tylko kapłanom (Mk 2, 26). ",
                    14 to "I niech mają w pamięci, co mówi Pan: Miejcie się na baczności, aby przypadkiem waszych serc" +
                            " nie obciążyło obżarstwo i pijaństwo, i troski tego życia, żeby nie przyszedł na was nagle ten dzień. ",
                    15 to "Spadnie on bowiem jak potrzask na wszystkich mieszkańców całej ziemi (por. Łk 21, 34-35). ",
                    16 to "Podobnie w razie oczywistej konieczności wolno wszystkim braciom tak korzystać z rzeczy potrzebnych, jak ich Pan natchnie, bo „gdy zachodzi konieczność, prawo nie obowiązuje”."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 10]\nBracia chorzy",
                array = arrayOf(
                    1 to "Gdy jakiś brat zachoruje, gdziekolwiek by był, niech go bracia nie opuszczają, nie wyznaczywszy jednego lub, jeśli potrzeba, kilku braci, " +
                            "którzy będą go tak obsługiwali, jak sami „chcieliby być obsłużeni”. ",
                    2 to "Lecz w razie ostatecznej konieczności mogą go zostawić jakiejś osobie, która powinna się nim opiekować w chorobie. ",
                    3 to "I proszę chorego brata, aby za wszystko składał dzięki Stwórcy; i żeby pragnął być takim, jakim chce go mieć Pan: " +
                            "czy to zdrowym, czy chorym, bo tych wszystkich, których Bóg przeznaczył do życia wiecznego (por. Dz 13, 48), " +
                            "przygotowuje przez ciosy doświadczeń i chorób, i przez ducha skruchy, jak mówi Pan: Ja poprawiam i karcę tych, których miłuję (por. Ap 3, 19). ",
                    4 to "I jeśli któryś [brat chory] denerwowałby się lub gniewał na Boga lub braci, albo natarczywie domagał się lekarstw, nazbyt pragnąc ratować ciało, " +
                            "które i tak wkrótce umrze i jest nieprzyjacielem duszy, czyni to pod wpływem złego i jest cielesny i zdaje się, jakby nie był spośród braci, bo bardziej kocha ciało niż duszę."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 11]\nBracia nie powinni przeklinać ani ubliżać, lecz miłować się wzajemnie",
                array = arrayOf(
                    1 to "I wszyscy bracia niech wystrzegają się słów obelżywych i sprzeczek (por. 2 Tm 2, 14), ",
                    2 to "niech starają się raczej, o ile Bóg da im łaskę, zachowywać milczenie. ",
                    3 to "Niech nie sprzeczają się ze sobą ani z innymi, lecz niech starają się odpowiadać pokornie mówiąc: sługą nieużytecznym jestem (por. Łk 17, 10). ",
                    4 to "I niech się nie gniewają, bo każdy, który gniewa się na brata swego, będzie winien sądu. Kto powiedziałby bratu swemu: bezbożniku, będzie winien Rady; kto by rzekł głupcze, będzie winien ognia piekielnego (Mt 5, 22). ",
                    5 to "I niech się kochają wzajemnie, jak mówi Pan: To jest moje przykazanie, abyście się wzajemnie miłowali, jak ja was umiłowałem (J 15, 12). ",
                    6 to "I niech czynem okazują miłość, jaką mają względem siebie, jak mówi Apostoł: Nie miłujmy słowem ani językiem, ale czynem i prawdą (1 J 3, 18). ",
                    7 to "I niech nikomu nie złorzeczą (por. Tt 3, 2); ",
                    8 to "niech nie szemrzą, nie ubliżają innym, ponieważ jest napisane: obmówcy i oszczercy są Bogu obmierzli (por. Rz 1, 29). ",
                    9 to "I niech będą uprzejmi, okazując wszelką łagodność względem wszystkich ludzi (por Tt 3, 2). ",
                    1 to "0Niech nie sądzą i nie potępiają. ",
                    1 to "1I, jak mówi Pan, niech nie roztrząsają nawet najmniejszych grzechów innych ludzi (por. Mt 7, 3; Łk 6, 41), ",
                    1 to "2a raczej własne grzechy niech rozważają w gorzkości duszy swej (por. Iz 38, 15). ",
                    1 to "3I niech usiłują wejść przez ciasną bramę (Łk 13, 24), bo Pan mówi: Ciasna jest brama i wąska droga, która wiedzie do życia, a mało jest tych, którzy ją znajdują (Mt 7, 14)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 12]\nNieczyste spojrzenia na kobiety i obcowanie z nimi",
                array = arrayOf(
                    1 to "Wszyscy bracia, gdziekolwiek są lub idą, niech się wystrzegają nieczystych spojrzeń na kobiety i obcowania z nimi. ",
                    2 to "I niech nikt nie wdaje się z nimi w rozmowę, ani sam nie idzie z nimi drogą, ani nie jada przy stole z jednej miski. ",
                    3 to "Kapłani, spowiadając je lub udzielając im rad duchownych, niech rozmawiają z nimi z godnością. ",
                    4 to "I żadna w ogóle kobieta nie może być przez jakiegokolwiek brata przyjęta pod posłuszeństwo, lecz otrzymawszy od niego radę duchowną, niech czyni pokutę, gdzie chce. ",
                    5 to "I wszyscy czuwajmy nad sobą bardzo i wszystkie nasze zmysły czystymi zachowujmy, bo Pan mówi: Kto pożądliwie patrzyłby na niewiastę, już w sercu swoim popełnił cudzołóstwo (Mt 5, 28). ",
                    6 to "I Apostoł: Czy nie wiecie, że członki wasze są świątynią Ducha Świętego? (1 Kor 6, 19); kto więc świątynię Bożą znieważy, zatraci go Bóg (1 Kor 3, 17)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 13]\nUnikanie rozpusty",
                array = arrayOf(
                    1 to "Jeśli który z braci za podszeptem szatańskim dopuściłby się grzechu rozpusty, należy zdjąć z niego habit, " +
                            "do którego stracił prawo przez swój niecny postępek – niech wszystko pozostawi – i wydalić go zupełnie z zakonu. ",
                    2 to "A potem niech pokutuje za swoje grzechy."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 14]\nJak bracia mają iść przez świat",
                array = arrayOf(
                    1 to "Gdy bracia idą przez świat, nie powinni niczego brać na drogę (por. Łk 9, 3): ani sakwy (por. Łk 10, 4), ani torby, ani chleba, ani pieniędzy (por. Łk 9, 3), ani laski (por. Mt 10, 10). ",
                    2 to "I gdy wejdą do któregokolwiek domu, niech najpierw mówią: Pokój temu domowi (por Łk 10, 5). ",
                    3 to "I w tym samym domu zostając, niech jedzą i piją to, co u nich jest (por. Łk 10, 7). ",
                    4 to "Niech nie przeciwstawiają się złu (por. Mt 5, 39), lecz jeśli ich kto uderzy w jeden policzek, niech nadstawią mu drugi (por. Mt 5, 39; Łk 6, 29). ",
                    5 to "I temu, kto bierze im płaszcz, niech nie bronią i sukni (por. Łk 6, 29). ",
                    6 to "Niech dają każdemu, kto ich prosi, a nie dopominają się zwrotu od tego, kto bierze ich własność (por. Łk 6, 29-30)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 15]\nBracia nie powinni jeździć konno",
                array = arrayOf(
                    1 to "Nakazuję wszystkim moim braciom zarówno klerykom, jak i nie-klerykom, będącym w drodze lub przebywającym w jakimś miejscu, " +
                            "aby nie trzymali żadnego zwierzęcia u siebie ani u innych, ani w jakiś inny sposób. ",
                    2 to "I jeśli nie zmusza ich do tego choroba lub ważna potrzeba, nie wolno im jeździć konno."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 16]\nUdający się do Saracenów i innych niewiernych",
                array = arrayOf(
                    1 to "Pan mówi: Oto ja was posyłam jak owce między wilki. ",
                    2 to "Bądźcie więc roztropni jak węże, a prości jak gołębie (Mt 10, 16). ",
                    3 to "Dlatego, gdyby którykolwiek brat chciał udać się między saracenów i innych niewiernych, niech idzie za pozwoleniem swego ministra i sługi. ",
                    4 to "A minister jeśli uzna ich za odpowiednich do wysłania, niech udzieli im pozwolenia nie sprzeciwia się. " +
                            "Będzie bowiem musiał zdać rachunek przed Panem (por. Łk 16, 2), jeśli niewłaściwie postąpiłby w tej lub innej sprawie.\n\n",
                    5 to "Bracia zaś, którzy udają się, mogą w dwojaki sposób duchownie wśród nich postępować. ",
                    6 to "Jeden sposób: nie wdawać się w kłótnie ani w spory, lecz być poddanymi wszelkiemu ludzkiemu " +
                            "stworzeniu ze względu na Boga (1 P 2, 13) i przyznawać się do wiary chrześcijańskiej. ",
                    7 to "Drugi sposób: gdyby widzieli, że tak się Panu podoba, niech głoszą słowo Boże, aby [ludzie] uwierzyli w Boga wszechmogącego, " +
                            "Ojca i Syna, i Ducha Świętego, Stworzyciela wszystkich rzeczy, w Syna Odkupiciela i Zbawiciela i aby przyjęli chrzest i zostali " +
                            "chrześcijanami, ponieważ kto nie odrodzi się z wody i z Ducha Świętego, nie może wejść do Królestwa Bożego (por. J 3, 5).\n\n",
                    8 to "Bracia mogą głosić tym i innym ludziom te i inne Bogu miłe prawdy, bo Pan mówi w Ewangelii: Każdego, kto Mnie wyzna wobec ludzi, i Ja wyznam wobec Ojca mojego, który jest w niebie (Mt 10, 32). ",
                    9 to "I: Kto będzie się wstydził Mnie i moich słów, tego i Syn Człowieczy będzie się wstydził, gdy przyjdzie w majestacie swoim i Ojca, i aniołów (por. Łk 9, 26).\n\n",
                    10 to "I wszyscy bracia, gdziekolwiek są, niech pamiętają, że oddali się i ofiarowali swoje ciała Panu Jezusowi Chrystusowi. ",
                    11 to "I z miłości do Niego powinni się wydawać nieprzyjaciołom tak widzialnym jak niewidzialnym, bo Pan mówi: Kto straciłby życie swoje dla Mnie, zachowa je (Łk 9, 24) na życie wieczne (Mt 25, 46). ",
                    12 to "Błogosławieni, którzy cierpią prześladowanie dla sprawiedliwości, bo do nich należy Królestwo niebieskie (Mt 5, 10). ",
                    13 to "Jeśli Mnie prześladują i was prześladować będą (J 15, 20). ",
                    14 to "I: jeśli prześladują was w jednym mieście, uciekajcie do drugiego (por. Mt 10, 23). ",
                    15 to "Błogosławieni jesteście (Mt 5, 11), gdy ludzie będą was nienawidzić (Łk 6, 22) i będą wam złorzeczyć i prześladować was (Mt 5, 11) " +
                            "i wyłączą was, i będą was znieważać i pomijać milczeniem wasze imię jako złe (Łk 6, 22) i gdy ze względu na Mnie kłamliwie będą mówić wszelkie zło przeciw wam (Mt 5, 11). ",
                    16 to "Cieszcie się i radujcie w owym dniu (Łk 6, 23), bo wielka jest zapłata wasza w niebie (por. Mt 5, 12). ",
                    17 to "I ja wam mówię, moim przyjaciołom, nie dajcie się im zastraszyć (por. Łk 12, 4) ",
                    18 to "i nie bójcie się tych, którzy zabijają ciało (Mt 10, 28) i potem nic więcej uczynić nie mogą (Łk 12, 4). ",
                    19 to "Uważajcie, żebyście się nie trwożyli (Mt 24, 6). ",
                    20 to "Bowiem dzięki cierpliwości waszej ocalicie wasze dusze (por. Łk 21, 19) ",
                    21 to "i kto wytrwa aż do końca, ten będzie zbawiony (Mt 10, 22; 24, 13)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 17]\nKaznodzieje",
                array = arrayOf(
                    1 to "Żaden z braci niech nie głosi kazań wbrew praktyce i zarządzeniom świętego Kościoła i bez pozwolenia swego ministra. ",
                    2 to "Minister zaś niech się strzeże, aby nie pozwalał nierozważnie. ",
                    3 to "Wszyscy jednak bracia niech głoszą kazania czynami. ",
                    4 to "I żaden minister lub kaznodzieja niech nie przywłaszcza sobie swej posługi wobec braci lub obowiązku kaznodziejskiego, " +
                            "lecz bez sprzeciwu niech złoży swój obowiązek w tej samej godzinie, w której otrzyma taki rozkaz.\n\n",
                    5 to "Dlatego przez miłość, którą jest Bóg (por. 1 J 4, 8. 16), błagam wszystkich moich braci kaznodziejów i tych, " +
                            "którzy się modlą, i tych, którzy pracują, tak kleryków, jak i niekleryków, aby się starali uniżać we wszystkim, ",
                    6 to "aby się nie chełpili ani nie cieszyli i nie wynosili się w duchu z powodu dobrych słów i uczynków ani nawet z żadnego dobra, " +
                            "jakie Bóg niekiedy czyni lub mówi i dokonuje w nich i przez nich, według tego co Pan mówi: Jednakże nie cieszcie się z tego, że duchy wam ulegają (Łk 10, 20). ",
                    7 to "I bądźmy mocno przekonani, że naszą własnością są tylko wady i grzechy. ",
                    8 to "I cieszmy się raczej wtedy, gdy wpadamy w rozmaite pokusy (por. Jk 1, 2) i gdy znosimy na tym " +
                            "świecie dla życia wiecznego różne przykrości lub utrapienia duchowe albo cielesne.\n\n",
                    9 to "Bracia, wystrzegajmy się więc wszyscy wszelkiej pychy i próżnej chwały, ",
                    10 to "i strzeżmy się mądrości tego świata i roztropności ciała (por. Rz 8, 6); ",
                    11 to "duch bowiem człowieka oddanego cielesności chce i bardzo stara się o znajomość słów [Bożych], lecz niewiele troszczy się o czyny, ",
                    12 to "i szuka nie religijności i świętości wewnętrznej ducha, lecz chce i pragnie religijności i świętości zewnętrznej, widocznej dla ludzi. ",
                    13 to "I o takich mówi Pan: Zaprawdę powiadam wam, otrzymali zapłatę swoją (Mt 6, 2). ",
                    14 to "Duch Pański natomiast domaga się, aby ciało było umartwione i wzgardzone, liche i odrzucone. ",
                    15 to "I stara się o pokorę i cierpliwość oraz czysty, szczery i prawdziwy pokój ducha. ",
                    16 to "I zawsze ponad wszystko pragnie bojaźni Bożej i mądrości Bożej, i miłości Bożej Ojca i Syna i Ducha Świętego.\n\n",
                    17 to "I wszelkie dobro odnośmy do Pana Boga najwyższego i uznawajmy za Jego własność, i dziękujmy za wszystko Temu, od którego pochodzi wszelkie dobro. ",
                    18 to "I on sam, najwyższy, jedyny i prawdziwy Bóg, niech ma, odbiera i otrzymuje wszelką cześć i uszanowanie, wszelkie uwielbienia i błogosławieństwa, " +
                            "wszelkie dziękczynienia i chwałę, On, który ma wszelkie dobro, który sam jest dobry (por. Łk 18, 19).\n\n",
                    19 to "I kiedy widzimy lub słyszymy, że [ludzie] źle mówią lub źle czynią albo bluźnią Bogu, " +
                            "my błogosławmy i dobrze czyńmy, i chwalmy Boga (por. Rz 12, 21), który jest błogosławiony na wieki (Rz 1, 25)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 18]\nZebranie ministrów",
                array = arrayOf(
                    1 to "Każdy minister może się spotykać z braćmi co roku, w uroczystość św. Michała Archanioła, gdzie im się podoba, aby omawiać sprawy Boże. ",
                    2 to "Wszyscy bowiem ministrowie krajów leżących za morzem lub za górami powinni się zbierać raz na trzy lata, a inni ministrowie raz na rok, na kapitułę " +
                            "w Zielone Święta w kościele Świętej Maryi w Porcjunkuli, jeśli minister i sługa całego braterstwa nie zarządzi inaczej."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 19]\nBracia mają żyć po katolicku",
                array = arrayOf(
                    1 to "Wszyscy bracia niech będą katolikami, niech żyją i mówią po katolicku. ",
                    2 to "Gdyby który słowem lub postępowaniem odstąpił od wiary i życia katolickiego i nie poprawił się, należy go z naszego braterstwa zupełnie usunąć. ",
                    3 to "I wszystkich duchownych i zakonników uważajmy za panów w sprawach dotyczących zbawienia duszy, a nie sprzeciwiających się zasadom naszego zakonu; i szanujmy w Panu ich stan, urząd i posługę."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 20]\nSpowiedź i przyjmowanie Ciała i Krwi Pana naszego Jezusa Chrystusa",
                array = arrayOf(
                    1 to "I bracia moi umiłowani, tak klerycy, jak nieklerycy, niech wyznają swoje grzechy przed kapłanami naszego zakonu. ",
                    2 to "A jeśli to niemożliwe, niech się spowiadają u innych roztropnych i katolickich kapłanów, dobrze wiedząc i pamiętając, że jeśli jakiś " +
                            "kapłan katolicki da im rozgrzeszenie i pokutę, otrzymają z pewnością rozgrzeszenie z tych grzechów, z których się wyspowiadali, jeśli tylko postarają się nałożoną pokutę wypełnić pokornie i wiernie.  \n\n",
                    3 to "Gdyby zaś nie mogli znaleźć wtedy kapłana, niech wyspowiadają się swemu bratu, jak mówi Jakub Apostoł: Wyznawajcie jedni drugim wasze grzechy (Jk 5, 16). ",
                    4 to "Niech jednak z tego powodu nie omieszkają zwrócić się do kapłana, bo tylko kapłani otrzymali władzę związywania i rozwiązywania. ",
                    5 to "I po takim żalu i spowiedzi niech przyjmą Ciało i Krew Pana naszego Jezusa Chrystusa z wielką pokorą i czcią pamiętając, co Pan mówi: Kto pożywa Ciało moje i pije Krew moją, ma życie wieczne (J 6, 55); ",
                    6 to "i: To czyńcie na moją pamiątkę (Łk 22, 19)."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 21]\nJak bracia mogą chwalić Boga i zachęcać do wielbienia Go",
                array = arrayOf(
                    1 to "I tę lub tego rodzaju zachętę i uwielbienie wszyscy bracia moi – kiedy zechcą – mogą głosić wśród jakichkolwiek ludzi z błogosławieństwem Bożym: ",
                    2 to "bójcie się i czcijcie, chwalcie i błogosławcie, dzięki czyńcie (1 Tes 5, 18) i uwielbiajcie Pana Boga wszechmogącego w Trójcy i Jedności, Ojca i Syna i Ducha Świętego, Stwórcę wszystkich rzeczy. ",
                    3 to "Czyńcie pokutę (Mt 3, 2), czyńcie godne owoce pokuty (por. Łk 3, 8), bo wkrótce umrzemy. ",
                    4 to "Dawajcie, a będzie wam dane (Łk 6, 38). ",
                    5 to "Odpuszczajcie a będzie wam odpuszczone (por. Łk 6, 37). ",
                    6 to "I jeśli nie odpuścicie ludziom ich grzechów (por. Mt 6, 14), Pan nie odpuści wam grzechów waszych (por. Mt 11, 26), wyznawajcie wszystkie wasze grzechy (por. Jk 5, 16) ",
                    7 to "Błogosławieni, którzy umierają w pokucie, bo będą w królestwie niebieskim. ",
                    8 to "Biada tym, którzy umierają bez pokuty, bo będą synami diabła (por. 1J 3, 10), pełniącymi jego uczynki (por. J 8, 41) i pójdą w ogień wieczny (por. Mt 18, 8; 25, 41). ",
                    9 to "Wystrzegajcie się i powstrzymujcie się od wszelkiego zła, i wytrwajcie w dobrym aż do końca."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 22]\nUpomnienie dla braci",
                array = arrayOf(
                    1 to "Zastanawiajmy się wszyscy bracia nad słowami Pana: Miłujcie nieprzyjaciół waszych i dobrze czyńcie tym, którzy was nienawidzą (por. Mt 5, 44), ",
                    2 to "bo Pan nasz Jezus Chrystus, w którego ślady powinniśmy wstępować (por. 1 P 2, 21), nazwał przyjacielem swego zdrajcę (por. Mt 26, 50) i oddał się dobrowolnie tym, którzy Go ukrzyżowali. ",
                    3 to "Naszymi więc nieprzyjaciółmi są ci wszyscy, którzy nas niesprawiedliwie dręczą i nękają, upokarzają i krzywdzą, zadają ból i cierpienie, męczarnie i śmierć. ",
                    4 to "Powinniśmy ich bardzo kochać, ponieważ dzięki temu, co nam czynią, otrzymamy życie wieczne.\n\n",
                    5 to "I miejmy w nienawiści nasze ciało z jego wadami i grzechami; bo życie cielesne chce diabeł pozbawić nas miłości Jezusa Chrystusa i życia wiecznego, i siebie samego ze wszystkimi zgubić w piekle; ",
                    6 to "bo my z własnej winy jesteśmy cuchnący, nędzni i przeciwni dobru, skłonni i chętni do złego, Pan bowiem tak mówi w Ewangelii: ",
                    7 to "Z serca pochodzą i wychodzą złe myśli, cudzołóstwa, nierząd, zabójstwa, kradzieże, chciwość, przewrotność, podstęp, wyuzdanie, złe spojrzenia, fałszywe świadectwa, przekleństwa, głupota (por. Mk 7, 21; Mt 15, 19). ",
                    8 to "Wszystko to zło z wewnątrz, z serca człowieka pochodzi (por Mk 7, 23) i to plami człowieka (por. Mt 15, 20).\n\n",
                    9 to "Teraz zaś, skoro porzuciliśmy świat, nic innego nie mamy czynić, tylko spełniać wolę Pana i Jemu samemu się podobać. ",
                    10 to "Strzeżmy się bardzo, abyśmy nie byli rolą przy drodze albo kamienistą, albo ciernistą, o której Pan mówi w Ewangelii: ",
                    11 to "Ziarnem jest słowo Boże (Łk 8, 11). ",
                    12 to "To zaś, które upadło koło drogi i zostało podeptane (por. Łk 8, 5), to są ci, którzy słuchają (Łk 8, 12) słowa i nie rozumieją (por. Mt 13, 19); ",
                    13 to "i natychmiast (por. Mk 4, 15) przychodzi diabeł (Łk 8, 12) i porywa (Mk 13, 19) to co zostało zasiane w ich sercach (Mk 4, 15) i zabiera słowo z ich serc, aby nie byli zbawieni przez wiarę (Łk 8, 12). ",
                    14 to "Ziarnem, które upadło na ziemię skalistą (por. Mt 13, 20) są ci, którzy, gdy posłyszą słowo, natychmiast z radością (Mk 4, 16) przyjmują (Łk 8, 13) je (Mk 4, 16). ",
                    15 to "Gdy zaś nastaje ucisk albo prześladowanie ze względu na słowo, od razu gorszą się (Mk 13, 21) i ci nie mają w sobie korzenia, ale są zmienni (por. Mk 4, 17), gdyż wierzą do czasu i w chwili próby odchodzą (Łk 8, 13). ",
                    16 to "To zaś, które padło między ciernie, oznacza tych (Łk 8, 14), którzy słuchają słowa Bożego (por. Mk 4, 18), a troska (Mt 13, 22) i kłopoty (Mk 4, 19) " +
                            "tego świata i ułuda bogactw (Mt 13, 22) oraz chciwość innych rzeczy wciskają się i zagłuszają słowo czyniąc je bezowocnym (Mk 4, 19). ",
                    17 to "To zaś, które w dobrej ziemi (Łk 8, 15) zostało posiane (Mt 13, 23) oznacza ludzi, którzy słuchając słowa sercem dobrym i szlachetnym (Łk 8, 15) rozumieją i (por. Mt 13, 23) zatrzymują, i przynoszą owoc w cierpliwości (Łk 8, 15). ",
                    18 to "I dlatego my, bracia, jak mówi Pan, zostawmy umarłym grzebanie swoich umarłych (por. Mt 8, 22).\n\n",
                    19 to " I bardzo wystrzegajmy się złości i przebiegłości szatana, który nie chce, aby człowiek miał umysł i serce skierowane do Pana. ",
                    20 to "I krążąc usiłuje pod pozorem jakiejś nagrody lub korzyści pociągnąć serce człowieka i zdusić w jego pamięci słowo i przykazania Pańskie, i chce omamić serce ludzkie sprawami i troskami świata i zamieszkać w nim, jak mówi Pan: ",
                    21 to "Kiedy duch nieczysty wyjdzie z człowieka, chodzi po miejscach suchych (Mt 12, 43) i bezwodnych i szuka spoczynku; a nie znajdując mówi: ",
                    22 to "Wrócę do domu mego, skąd wyszedłem (Łk 11, 24). ",
                    23 to "I przychodząc znajduje dom wolny, wymieciony i przyozdobiony (Mt 12, 44). ",
                    24 to "I idzie i bierze siedmiu innych, gorszych od siebie duchów i wszedłszy mieszkają tam; tak, że ostatnie rzeczy owego człowieka są gorsze od pierwszych (por. Łk 11, 26).  \n\n",
                    25 to "Stąd też, bracia, strzeżmy się wszyscy bardzo, abyśmy pod pozorem jakiejś nagrody, pracy czy korzyści nie zagubili lub nie odwrócili naszego umysłu i serca od Pana. ",
                    26 to "Lecz przez świętą miłość, którą jest Bóg (por. 1 J 4, 16), proszę wszystkich braci, tak ministrów, jak innych, aby usunąwszy wszystkie " +
                            "przeszkody i odrzuciwszy wszystkie troski i kłopoty, czystym sercem i czystym umysłem jak najlepiej służyli Panu Bogu, kochali Go, wielbili i czcili; bo tego Bóg ponad wszystko pragnie. ",
                    27 to "I przygotowujmy w sobie zawsze mieszkanie i miejsce pobytu (por. J 14, 23) Temu, który jest Panem Bogiem wszechmogącym, Ojcem i Synem, i Duchem Świętym, który mówi: Czuwajcie więc i " +
                            "módlcie się w każdym czasie, abyście byli godni uniknąć wszelkiego zła, które nadejdzie i stanąć przed Synem Człowieczym (por. Łk 21, 36). ",
                    28 to "A gdy staniecie do modlitwy (Mk 11, 25), mówcie (Łk 11, 2): Ojcze nasz, który jesteś w niebie (Mt 6, 9).",
                    29 to "I uwielbiajmy Go czystym sercem, bo trzeba zawsze modlić się i nie ustawać (Łk 18, 1); ",
                    30 to "gdyż Ojciec szuka takich czcicieli.\n\n",
                    31 to "Bóg jest Duchem i Jego czciciele powinni oddawać Mu cześć w duchu i prawdzie (por. J 4, 23-24). ",
                    32 to "I uciekajmy się do Niego jako do pasterza i biskupa dusz naszych (por. 1 P 2, 25), który mówi: Ja jestem dobrym pasterzem i pasę moje owce, i za owce moje oddaję me życie. ",
                    33 to "Wy wszyscy jesteście braćmi. ",
                    34 to "I nikogo na ziemi nie nazywajcie waszym ojcem, bo jeden jest wasz Ojciec, który jest w niebie. ",
                    35 to "Ani nie nazywajcie się nauczycielami; gdyż jeden jest wasz nauczyciel, ten w niebie (por. Mt 23, 8-10).\n",
                    36 to "Jeśli będziecie trwać we Mnie i moje słowa będą w was trwały, to stanie się wam wszystko, czegokolwiek zechcecie i o co poprosicie (J 15, 7). ",
                    37 to "Gdziekolwiek dwaj albo trzej zgromadzili się w imię moje, tam i Ja jestem wśród nich (Mt 18, 20). ",
                    38 to "Oto Ja jestem z wami aż do skończenia świata (Mt 28, 20). ",
                    39 to "Słowa, które wam powiedziałem, są duchem i życiem (J 6, 64). ",
                    40 to "Ja jestem drogą, prawdą i życiem (J 14, 6).\n\n",
                    41 to "Zachowujmy więc słowa, życie i naukę, i świętą Ewangelię Tego, który raczył modlić się za nas do swego Ojca i objawić nam Jego imię mówiąc: Ojcze, wsław Imię Twoje (J 12, 28a) i wsław Syna Twego, aby Syn Twój wsławił Ciebie (J 17, 1b). ",
                    42 to "Ojcze, objawiłem Imię Twoje ludziom, których mi dałeś (J 17, 6); gdyż słowa, które Mi dałeś, przekazałem im; a oni przyjęli i poznali, że od Ciebie wyszedłem i uwierzyli, że Ty mnie posłałeś. ",
                    43 to "Ja za nimi proszę, nie za światem, ",
                    44 to "lecz za tymi, których Mi dałeś, bo do Ciebie należą i wszystko, co moje, należy do Ciebie (J 17, 8-10). ",
                    45 to "Ojcze święty, zachowaj w Imię Twoje tych, których Mi dałeś, aby oni byli jedno jak i My (J 17, 11b). ",
                    46 to "To mówię na świecie, aby mieli w sobie radość. ",
                    47 to "Przekazałem im to, co mi powiedziałeś; a świat znienawidził ich, bo nie są ze świata, jak i Ja nie jestem ze świata.\n\n",
                    48 to "Nie proszę, abyś ich zabrał ze świata, lecz abyś zachował ich od złego (J 17, 13b-15). ",
                    49 to "Wsław ich w prawdzie. ",
                    50 to "Prawdą jest to, co mówisz. ",
                    51 to "Jak Ty Mnie posłałeś na świat, tak ja ich na świat posłałem. ",
                    52 to "I za nich samego siebie poświęcam w ofierze, aby zostali uświęceni dzięki prawdzie. ",
                    53 to "Nie tylko za nimi proszę, lecz także za tymi, którzy uwierzą we Mnie dzięki ich słowu (por. J 17, 17-20), aby osiągnęli jedność i żeby świat poznał, że Ty Mnie posłałeś i umiłowałeś ich tak, jak Mnie (J 17, 23). ",
                    54 to "I sprawię, że poznają Imię Twoje, aby miłość, którą Mnie ukochałeś, była w nich i Ja w nich (por. J 17, 26). ",
                    55 to "Ojcze, chcę, aby ci, których Mi dałeś, byli ze Mną tam, gdzie Ja jestem, aby oglądali chwałę Twoją (por. J 17, 24) w Twoim królestwie (Mt 20, 21). Amen."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 23]\nModlitwa – prośba i modlitwa dziękczynna",
                array = arrayOf(
                    1 to "Wszechmogący, najświętszy i najwyższy Boże, Ojcze święty (J 17, 21), i sprawiedliwy, Panie, królu nieba " +
                            "i ziemi (por. Mt 11, 25), dzięki Ci składamy z powodu Ciebie samego, że Twoją świętą wolą i przez " +
                            "jedynego Syna Twego stworzyłeś z Duchem Świętym wszystkie byty duchowe i cielesne, a nas uczyniłeś " +
                            "na obraz i podobieństwo (por. Rdz 1, 26) Twoje i umieściłeś w raju (por. Rdz 2, 15). ",
                    2 to "My zaś upadliśmy z własnej winy. ",
                    3 to "I dzięki Ci składamy, że jak stworzyłeś nas przez Syna Twego, tak przez świętą miłość Twoją, którą nas " +
                            "umiłowałeś (por. J 17, 26), sprawiłeś, że On, prawdziwy Bóg i prawdziwy Człowiek, narodził się z " +
                            "chwalebnej zawsze Dziewicy, błogosławionej, świętej Maryi i przez Jego krzyż i krew, i śmierć " +
                            "zechciałeś nas wykupić z niewoli. ",
                    4 to "I dzięki Ci składamy, że Syn Twój przyjdzie w chwale swego majestatu, aby strącić w ogień wieczny " +
                            "przeklętych, którzy nie czynili pokuty i nie poznali Ciebie, i aby powiedzieć wszystkim, którzy " +
                            "Ciebie poznali i uwielbili, i służyli Ci w pokucie: Pójdźcie, błogosławieni Ojca mego, weźcie " +
                            "królestwo, zgotowane wam od początku świata (por. Mt 25, 34).\n\n",
                    5 to "A ponieważ my, nędzni i grzeszni, wszyscy razem nie jesteśmy godni wymówić Twego Imienia, błagamy " +
                            "pokornie, aby Pan nasz Jezus Chrystus, Syn Twój umiłowany, który Tobie wielce podoba się (por. Mt 17, 5), " +
                            "składał Ci za wszystko dzięki wraz z Duchem Świętym Pocieszycielem, tak jak Tobie i Jemu się podoba, On, " +
                            "który Ci za wszystko wystarcza, przez którego tyle dla nas uczyniłeś. Alleluja.\n\n",
                    6 to "I chwalebną Matkę, Najświętszą Maryję zawsze Dziewicę, św. Michała, Gabriela i Rafała, i wszystkie chóry " +
                            "błogosławionych serafinów, cherubinów, tronów, panowań, zwierzchności, władz, mocy, aniołów, " +
                            "archaniołów, świętego Jana Chrzciciela, Jana Ewangelistę, Piotra, Pawła i świętych patriarchów, " +
                            "proroków, młodzianków, apostołów, ewangelistów, uczniów, męczenników, wyznawców, dziewice, " +
                            "świętych Eliasza i Henocha i wszystkich Świętych, którzy byli i będą, i są, przez miłość Twoją " +
                            "pokornie prosimy, aby, tak jak Tobie, się podoba, dzięki składali za wszystko Tobie, najwyższemu " +
                            "Bogu prawdziwemu, wiecznemu i żywemu, z Synem Twoim najmilszym, Panem naszym Jezusem Chrystusem i " +
                            "Duchem Świętym Pocieszycielem na wieki wieków. Amen. Alleluja (por. Ap 19, 34).\n\n",
                    7 to "A wszystkich, którzy pragną służyć Panu Bogu w świętym Kościele katolickim i apostolskim, i wszystkie " +
                            "następujące stany, kapłanów, diakonów, subdiakonów, akolitów, egzorcystów, lektorów, ostiariuszy i " +
                            "wszystkich duchownych, wszystkich zakonników i zakonnice, wszystkich nawróconych i maluczkich, " +
                            "ubogich i potrzebujących, królów i książąt, robotników i rolników, sługi i panów, wszystkie dziewice, " +
                            "wdowy i mężatki, ludzi świeckich, mężczyzn i kobiety, wszystkie dzieci, młodzież, młodych i starych, " +
                            "zdrowych i chorych, wszystkich małych i wielkich, i wszystkie ludy, plemiona, pokolenia i języki " +
                            "(por. Ap 7, 9), wszystkie narody i wszystkich ludzi na całej ziemi, którzy są i będą, my wszyscy " +
                            "bracia mniejsi, słudzy nieużyteczni (Łk 17, 10), pokornie prosimy i błagamy, abyśmy wszyscy wytrwali " +
                            "w prawdziwej wierze i pokucie, bo inaczej nikt nie może być zbawiony.\n\n",
                    8 to "Z całego serca, z całej duszy, z całego umysłu, z całej siły (por. Mk 12, 30), i mocy (por. Mk 12, 33), " +
                            "z całego umysłu, ze wszystkich sił (por. Łk 10, 27), całym wysiłkiem, całym uczuciem, całym wnętrzem, " +
                            "wszystkimi pragnieniami i całą wolą kochajmy wszyscy Pana Boga (por. Mk 12, 30), który dał i daje " +
                            "nam wszystkim całe ciało, całą duszę i całe życie, który nas stworzył, odkupił i zbawił nas tylko ze " +
                            "swego miłosierdzia (por. Tb 13, 5), który nam nieszczęsnym i nędznym, zepsutym i cuchnącym, " +
                            "niewdzięcznym i złym wyświadczył i wyświadcza wszelkie dobro. ",
                    9 to "Nie miejmy więc innych tęsknot, innych pragnień, innych przyjemności i radości oprócz Stwórcy i " +
                            "Odkupiciela, i Zbawiciela naszego, jedynego prawdziwego Boga, który jest pełnią dobra, wszelkim " +
                            "dobrem, który sam jeden jest dobry (por. Łk 18, 19), litościwy, łagodny, miły i słodki, który sam " +
                            "jeden jest święty, sprawiedliwy, prawdziwy, wzniosły i prawy, który sam jeden jest życzliwy, bez winy, " +
                            "czysty, od którego i przez którego, i w którym (por. Rz 11, 36) jest wszelkie przebaczenie, wszelka " +
                            "łaska, wszelka chwała dla wszystkich pokutników i sprawiedliwych, dla wszystkich błogosławionych, " +
                            "współweselących się w niebie. ",
                    10 to "0Niech więc nam nic nie przeszkadza, nic nie oddziela ani niepokoi;\n\n",
                    11 to "1Wszędzie, na każdym miejscu, o każdej godzinie i o każdej porze, codziennie i nieustannie wierzmy " +
                            "wszyscy szczerze i pokornie, nośmy w sercu i kochajmy, czcijmy, uwielbiajmy, służmy, chwalmy i " +
                            "błogosławmy, wychwalajmy i wywyższajmy, wysławiajmy i dzięki składajmy najwyższemu Bogu wiecznemu, " +
                            "Trójcy i Jedności, Ojcu i Synowi, i Duchowi Świętemu, Stwórcy wszystkich rzeczy i Zbawicielowi " +
                            "wszystkich w Niego wierzących, ufających i miłujących Go, Temu, który nie ma początku i końca, " +
                            "który jest niezmienny, niewidzialny, nieomylny, niewysłowiony, niepojęty, niezgłębiony (por. Rz 11, 33), " +
                            "błogosławiony, chwalebny, przesławny, wywyższony (por. Dn 3, 52), wzniosły, najwyższy, słodki, godny " +
                            "miłości, ukochany i cały pożądany ponad wszystko na wieki. Amen."
                )
            ).let { append(it) }
            buildChapter(
                name = "[Rozdział 24]\nZakończenie",
                array = arrayOf(
                    1 to "W imię Pańskie! Proszę wszystkich braci, aby nauczyli się tekstu i znaczenia tego, " +
                            "co jest napisane w tym sposobie życia dla zbawienia dusz naszych i aby to sobie często przywodzili na pamięć. ",
                    2 to "I błagam Boga, który jest wszechmogący i w Trójcy jedyny, aby błogosławił " +
                            "wszystkich nauczających, uczących się, zachowujących, pamiętających i spełniających " +
                            "to wszystko, co jest tu napisane dla zbawienia dusz naszych, ilekroć będą sobie to przypominać i wykonywać. ",
                    3 to "I całując im nogi, błagam wszystkich, aby ten [sposób życia] bardzo kochali, strzegli i przechowywali. ",
                    4 to "I w imieniu Boga wszechmogącego, i Ojca św., i na mocy posłuszeństwa ja, brat Franciszek, " +
                            "surowo nakazuję i zobowiązuję, aby z tego, co jest napisane w tym sposobie życia, " +
                            "nikt niczego nie ujmował ani niczego do tego pisma nie dodawał (por. Pwt 4, 2; 12, 32), i aby bracia nie mieli innej reguły. ",
                    5 to "Chwała Ojcu i Synowi, i Duchowi Świętemu, jak była na początku i teraz, i zawsze, i na wieki wieków. Amen.\n"
                )
            ).let { append(it) }
        },
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
                name = "[Rozdział 1]\nW imię Pańskie! Zaczyna się sposób życia braci mniejszych",
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
                        "Dano na Lateranie, 29 listopada, w ósmym roku naszego pontyfikatu. Kończy się zatwierdzenie Reguły Braci Mniejszych.\n"
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
                            "jak tylko mogę, wewnętrznie i zewnętrznie potwierdzam wam to najświętsze błogosławieństwo.\n"
                )
            }
        ),
        justifiedString(
            "Napisz, że błogosławię wszystkich moich braci, którzy są teraz w zakonie i będą wstępowali do niego aż do końca świata (…). " +
                    "Ponieważ z powodu słabości i bólu w chorobie nie mogę mówić, wyrażę mą wolę braciom moim krótko, w trzech zdaniach, mianowicie:\n" +
                    "aby na znak pamięci na moje błogosławieństwo i mój testament zawsze kochali się wzajemnie;\n" +
                    "aby zawsze kochali i dochowywali wierności naszej pani, świętemu ubóstwu\n" +
                    "i aby zawsze byli wierni i ulegli dostojnikom i wszystkim duchownym świętej matki Kościoła.\n"
        ),
        justifiedString(
            "Tenże (br. Leonard) opowiadał, że pewnego dnia u Świętej Maryi (Matki Boskiej Anielskiej) św. Franciszek zawołał brata Leona i powiedział: " +
                    "\"Bracie Leonie, pisz\". Ten odpowiedział: \"Jestem gotów\".\n\n\"Pisz - rzecze - czym jest prawdziwa radość. Przybywa posłaniec i mówi, że wszyscy profesorowie " +
                    "z Paryża wstąpili do zakonu; napisz, to nie prawdziwa radość. Że tak samo uczynili wszyscy prałaci z tamtej strony Alp, arcybiskupi i biskupi, " +
                    "również król Francji i Anglii; napisz, to nie prawdziwa radość. Tak samo, że moi bracia poszli do niewiernych i nawrócili wszystkich do wiary; " +
                    "że mam od Boga tak wielką łaskę, że uzdrawiam chorych i czynię wiele cudów; mówię ci, że w tym wszystkim nie kryje się prawdziwa radość.\n\n" +
                    "Lecz co to jest prawdziwa radość? Wracam z Perugii i późną nocą przychodzę tu, jest zima i słota, i tak zimno, że u dołu tuniki zwisają zmarznięte sople " +
                    "i ranią wciąż nogi, i krew płynie z tych ran. I cały zabłocony, zziębnięty i zlodowaciały przychodzę do bramy; i gdy długo pukałem i wołałem, " +
                    "przyszedł brat i pyta: \"Kto jest?\". Odpowiedziałem: Brat Franciszek. A ten mówi: \"Wynoś się; to nie jest pora stosowna do chodzenia; nie wejdziesz\". " +
                    "A gdy ja znowu nalegam, odpowiada: \"Wynoś się; ty jesteś [człowiek] prosty i niewykształcony. Jesteś teraz zupełnie zbyteczny. Jest nas tylu i takich, " +
                    "że nie potrzebujemy ciebie\". A ja znowu staję przy bramie i mówię: Na miłość Bożą, przyjmijcie mnie na tę noc. A on odpowiada: \"Nic z tego. Idź tam, " +
                    "gdzie są krzyżowcy i proś\".\n\nPowiadam ci: jeśli zachowam cierpliwość i nie rozgniewam się, na tym polega prawdziwa radość i prawdziwa cnota, i zbawienie duszy\".\n"
        )
    )
}