package pl.mftau.mftau.utils

object SongBookUtils {

    val songTitles = arrayOf(
        "1. ABBA OJCZE, TATUSIU", "2. ABBA OJCZE, TCHNIJ W NAS DUCHA", "3. ABY BYLI JEDNO",
        "4. ABYŚMY BYLI JEDNO", "5. ADONAI", "6. ALBOWIEM TAK BÓG UMIŁOWAŁ", "7. ALE FRANCESCO",
        "8. ALLELUJA (LIT.)", "9. ALLELUJA! CHWALCIE PANA U ŚWIĄTYNI BRAM",
        "10. ALLELUJA, ALLELUJA", "11. ALLELUJA, WZNIEŚ POD NIEBO GŁOS", "12. AMEN, AMEN, ALLELUJA",
        "13. AMEN, AMEN, BÓG MIŁOŚCIĄ JEST", "14. ANIELE MÓJ", "15. ANIOŁ RZEKŁ MARII",
        "16. ANIOŁA POKOJU ZEŚLIJ", "17. AVE MARYJA", "18. AŻ SPONAD MÓRZ",
        "19. BALLADA O ŚWIĘTYM FRANCISZKU", "20. BALLADA O WILKU Z GUBBIO",
        "21. BARANKU, DAJ MI ZMARTWYCHWSTANIE", "22. BARKA", "23. BARUH HASZEM HAMASZIJA",
        "24. BĄDŹ KRÓLOWĄ", "25. BĄDŹ MI LITOŚCIW", "26. BĄDŹ POZDROWIONY GOŚCIU NASZ",
        "27. BĘDĘ CHWALIŁ PANA", "28. BĘDĘ CIĘ SŁAWIŁ, O PANIE",
        "29. BĘDĘ ŚPIEWAŁ NA CZEŚĆ PANA (PSALM 6)", "30. BĘDĘ ŚPIEWAŁ TOBIE, MOCY MOJA",
        "31. BĘDĘ TWOJĄ ROSĄ IZRAELU", "32. BĘDZIEMY TAŃCZYĆ, BĘDZIEMY ŚPIEWAĆ", "33. BLISKO",
        "34. BLISKO, BLISKO", "35. BŁOGOSŁAW DUSZO MOJA PANA", "36. BŁOGOSŁAW PANA, IZRAELU",
        "37. BŁOGOSŁAWCIE PANA", "38. BŁOGOSŁAWIENI MIŁOSIERNI",
        "39. BŁOGOSŁAWIEŃSTWO ŚW. FRANCISZKA", "40. BŁOGOSŁAWIĘ CIĘ", "41. BŁOGOSŁAWIĘ IMIĘ TWOJE",
        "42. BŁOGOSŁAWIONA DZIEWICO", "43. BŁOGOSŁAWIONA JESTEŚ MARYJO", "44. BŁOGOSŁAWIONA WINA",
        "45. BŁOGOSŁAWIONY JEST NASZ PAN", "46. BŁOGOSŁAWIONY BĄDŹ, OJCZE",
        "47. BŁOGOSŁAWIONY KRZYŻU CHRYTUSA", "48. BO GÓRY MOGĄ USTĄPIĆ",
        "49. BO IMIĘ TWE TAK WSPANIAŁE JEST", "50. BO JAK ŚMIERĆ", "51. BO NIKT NIE MA Z NAS",
        "52. BOGU JEDYNEMU", "53. BOGU WYŚPIEWAM", "54. BONUM EST PRAESTOLARI", "55. BOŻE OJCZE",
        "56. BOŻE PEŁEN W NIEBIE CHWAŁY", "57. BOŻE WSZECHMOCNY", "58. BOŻE MÓJ, GDZIE JESTEŚ",
        "59. BOŻE, BOŻE MÓJ", "60. BOŻE, MÓJ BOŻE", "61. BOŻE, TWA ŁASKA NAD NAMI JEST",
        "62. BOŻY BARANKU", "63. BÓG DAŁ CZAS", "64. BÓG JEST MIŁOŚCIĄ I",
        "65. BÓG JEST MIŁOŚCIĄ II", "66. BÓG TAK UMIŁOWAŁ ŚWIAT", "67. BÓG UMIERAŁ",
        "68. BÓG ZASIEWA", "69. BÓG, MÓJ PAN", "70. BÓG, NASZ PAN", "71. BRACISZKOWIE SKRZYDLACI",
        "72. BYWAJĄ CHWILE", "73. CAŁA PIĘKNA JESTEŚ", "74. CAŁA ZIEMIO",
        "75. CAŁUJ MNIE POCAŁUNKAMI TWOICH UST", "76. CAŁYM SERCEM (PSALM 9)",
        "77. CHCEMY CIEBIE WIELBIĆ", "78. CHCĘ BYĆ TWOIM ZIARNEM", "79. CHCĘ PRZESTĄPIĆ JEGO PRÓG",
        "80. CHCĘ CIĘ UWIELBIAĆ", "81. CHCĘ ROZNOSIĆ TWÓJ CHLEB", "82. CHCĘ WIDZIEĆ CIĘ",
        "83. CHCĘ WYWYŻSZAĆ IMIĘ TWE", "84. CHLEBIE NAJCICHSZY",
        "85. CHLEBIE ŻYWY ŁAMANY DRZEWEM KRZYŻA", "86. CHOĆBY WASZE GRZECHY",
        "87. CHRYSTUS PAN, BOŻY SYN", "88. CHRYSTUS PAN KARMI NAS",
        "89. CHRYSTUS ZMARTWYCHWSTAN JEST", "90. CHWALCIE GO", "91. CHWALCIE PANA NARODY",
        "92. CHWALCIE PANA NIEBIOS", "93. CHWALĘ CIEBIE, PANIE", "94. CHWALĘ CIĘ, PANIE",
        "95. CHWALIĆ CHCĘ MEGO PANA", "96. CHWALMY GO", "97. CHWAŁA BARANKOWI",
        "98. CHWAŁA, KRÓL JEST GODZIEN CZCI", "99. CI, KTÓRZY JAHWE UFAJĄ",
        "100. CIEBIE, BOGA, WYSŁAWIAMY", "101. CIEBIE CAŁĄ DUSZĄ PRAGNĘ",
        "102. CIEBIE, PANIE, PRAGNĘ", "103. CIESZ SIĘ I WESEL", "104. CIESZCIE SIĘ",
        "105. CIESZĘ SIĘ - JEZUS ZBAWIŁ MNIE", "106. CO ZROBISZ, GDY ZOBACZYSZ JEZUSA?",
        "107. CONVERTE NOS", "108. CZAS ZBAWIENIA", "109. CZCIJMY JEZUSA",
        "110. CZEKAM NA CIEBIE DOBRY BOŻE", "111. CZEGO CHCESZ OD NAS, PANIE",
        "112. CZY MOŻE NIEWIASTA ZAPOMNIEĆ", "113. CZY WY WIECIE, ŻE JESTEŚCIE ŚWIĄTYNIĄ",
        "114. CZYŃCIE POKUTĘ", "115. DA PACEM DOMINE", "116. DAJ MI PIĆ",
        "117. DAJ MI POZOSTAĆ W TWOJEJ OBECNOŚCI", "118. DAJ MI SIŁĘ TWĄ",
        "119. DAJ MI TEGO CHLEBA PANIE", "120. DALEKO WĘDROWAŁEM SAM", "121. DE PROFUNDIS",
        "122. DLACZEGO KOCHASZ", "123. DŁUGO", "124. DO CHMUR, DO NIEBA", "125. DO CIEBIE, PANIE",
        "126. DO MNIE WRÓĆ", "127. DO SERCA JEZUSOWEGO", "128. DOBRY BÓG", "129. DOBRY JEST",
        "130. DOBRY PANIE", "131. DOBRY PASTERZU", "132. DOBRY PANIE, UCZYŃ MNIE",
        "133. DOSTALIŚMY TO", "134. DOTKNĄŁ MNIE DZIŚ PAN", "135. DOTKNIJ PANIE RANY MEJ",
        "136. DOTKNIJ, PANIE, MOICH OCZU", "137. DOTYK OGNIA", "138. DRZEWO KRZYŻA",
        "139. DUCH I OGIEŃ (Panie nie jestem godzien)", "140. DUCH PAŃSKI NADE MNĄ",
        "141. DUCHU MIŁOŚCI", "142. DUCHU POCIESZYCIELU", "143. DUCHU OGNIU, DUCHU ŻARZE",
        "144. DUCHU ŚWIĘTY DUSZO MEJ DUSZY", "145. DUCHU ŚWIĘTY POWIEJ WIATREM",
        "146. DUCHU ŚWIĘTY, BOŻE, PRZYJDŹ", "147. DUCHU ŚWIĘTY, DO NAS PRZYBĄDŹ",
        "148. DUCHU ŚWIĘTY, MIŁOŚCI MIŁOSIERNA", "149. DUCHU ŚWIĘTY, OGARNIJ MNIE",
        "150. DUCHU ŚWIĘTY, PRZYJDŹ", "151. DUCHU ŚWIĘTY, PRZYJDŹ W OGNIU SWYM",
        "152. DUCHU ŚWIĘTY, PRZYJDŹ, NAPEŁNIJ SERCE",
        "153. DUCHU ŚWIĘTY, PRZYJDŹ, DZIAŁAJ POŚRÓD NAS",
        "154. DUCHU ŚWIĘTY, PRZYJDŹ, OTWÓRZ SERCE ME", "155. DUCHU ŚWIĘTY, PRZYJDŹ, POCIESZYCIELU",
        "156. DUCHU ŚWIĘTY, STWORZYCIELU", "157. DUCHU ŚWIĘTY, TCHNIENIE OJCA",
        "158. DUCHU ŚWIĘTY, WOŁAM, PRZYJDŹ (I)", "159. DUCHU ŚWIĘTY, WOŁAM, PRZYJDŹ (II)",
        "160. DUCHU ŚWIĘTY, WYPEŁNIJ SWĄ ŚWIĄTYNIĘ", "161. DUSZO MA PANA CHWAL",
        "162. DZIELMY SIĘ WIARĄ JAK CHLEBEM", "163. DZIĘKI, JEZU", "164. DZIĘKI CI, PANIE",
        "165. DZIĘKUJEMY CI, OJCZE NASZ", "166. DZIĘKUJĘ BOGU DZIŚ", "167. DZIĘKUJMY JEZUSOWI",
        "168. DZIŚ JEST CZAS", "169. DZISIAJ TU, JUTRO TAM",
        "170. DZIŚ JEZUS ZAMIESZKAŁ W MYM SERCU", "171. DZIŚ KOŚCIELE", "172. ELI, ELI",
        "173. EMMANUEL", "174. FRANCISZEK I KLARA (BIEGALI RAZEM)", "175. FRATELLO SOLE",
        "176. GDY KLĘCZĘ PRZED TOBĄ", "177. GDY SCHODZIMY SIĘ", "178. GDY SERCE TWOJE",
        "179. GDY STOISZ PRZED PANEM", "180. GDY UCZNIÓW SWYCH",
        "181. GDY WPATRUJĘ SIĘ W TWĄ ŚWIĘTĄ TWARZ", "182. GDY ZOBACZYSZ CIEMNY OGRÓD",
        "183. GDYBY PAN NIE BYŁ PO NASZEJ STRONIE", "184. GDYBY WIARA TWA",
        "185. GDYBYM MÓWIŁ JĘZYKAMI LUDZI I ANIOŁÓW",
        "186. GDYBYŚ TU BYŁ (PAMIĘCI JACKA KULJEWICZA)", "187. GLORIA TIBI DOMINE",
        "188. GŁOSIMY JEZUSA MOC", "189. GŁOŚ IMIĘ PANA", "190. GODZIEN JESTEŚ",
        "191. GODZIEN, O GODZIEN", "192. GOLGOTO, GOLGOTO", "193. GÓRY MOGĄ USTĄPIĆ",
        "194. GROMADZISZ NAS I ŁĄCZYSZ NAS W JEDNO", "195. HAVA NAGILA", "196. HEJ, JEZU",
        "197. HOSANNA", "198. HOSANNA BARANKOWI", "199. HOSANNA, KRÓLOWI KRÓLÓW",
        "200. HYMN ETIOPSKI", "201. HYMN III TYSIĄCLECIA (OTWÓRZCIE BRAMY)", "202. HYMN O KRZYŻU",
        "203. HYMN O MIŁOŚCI", "204. HYMN WIELKANOCNY", "205. IDĘ PANIE, BIEGNĘ",
        "206. IDZIE MÓJ PAN", "207. IDZIEMY NA SYJON", "208. IN MANUS TUAS, PATER",
        "209. JA KOCHAM CIĘ, PANIE", "210. JA SIĘ MODLĘ", "211. JA ŚPIĘ",
        "212. JA WIERZĘ, ŻE TO JEZUS", "213. JA, PAN BÓG TWÓJ", "214. JAK DAWID",
        "215. JAK DOBRZE JEST DZIĘKOWAĆ CI, PANIE", "216. JAK FRANCISZEK",
        "217. JAK ŁANIA PRAGNIE WODY", "218. JAK OŻYWCZY DESZCZ",
        "219. JAK PIĘKNE JEST MIESZKANIE TWE", "220. JAK PTAKI NIEBIESKIE",
        "221. JAK WIELKA, O PANIE", "222. JAK WIELKI JEST BÓG",
        "223. JAK WIELKI JEST PAN, ALLELUJA", "224. JAKI BYŁ TEN DZIEŃ",
        "225. JAKŻE PÓJDZIEMY ZA TOBĄ", "226. JAM NIEGODZIEN", "227. JEDEN CHLEB",
        "228. JEDEN JEST TYLKO PAN", "229. JEDNA MIŁOŚĆ", "230. JEDYNIE W BOGU",
        "231. JEDYNY PAN, PRAWDZIWY BÓG", "232. JEGO KRWIĄ ZOSTAŁEM UZDROWIONY", "233. JEGO MIŁOŚĆ",
        "234. JERUZALEM, JERUZALEM", "235. JEST NA ŚWIECIE MIŁOŚĆ", "236. JESTEM KOCHANY",
        "237. JESTEM TU BY WIELBIĆ", "238. JESTEM TWÓJ NA WIEKI", "239. JESTEŚ BLISKO MNIE",
        "240. JESTEŚ KRÓLEM", "241. JESTEŚ DOBRY", "242. JESTEŚ PANIE WINNYM KRZEWEM",
        "243. JESTEŚ ŻYCIEM MYM", "244. JESTEŚCIE PRZYJACIÓŁMI", "245. JESTEŚMY LUDEM KRÓLA CHWAŁ",
        "246. JESTEŚMY PIĘKNI", "247. JESZCZE SIĘ KIEDYŚ ROZSMUCĘ",
        "248. JEŚLI NIE JA, JEŚLI NIE TY", "249. JEŚLI RADOŚĆ W SERCU CHCESZ MIEĆ",
        "250. JEŚLI PAN DOMU NIE ZBUDUJE", "251. JEZU, BARANKU ZRANIONY",
        "252. JEZU CHRYSTE, ARCYKAPŁANIE", "253. JEZU DAJ NAM POZNAĆ CIEBIE",
        "254. JEZU JESTEŚ KRÓLEM", "255. JEZU JESTEŚ TU", "256. JEZU POKAŻ MI MÓJ GRZECH",
        "257. JEZU, JEZU, OTO JESTEM", "258. JEZU, MÓJ JEZU", "259. JEZU, JEZU, WSPOMNIJ NA MNIE",
        "260. JEZU, TYŚ JEST ŚWIATŁOŚCIĄ", "261. JEZU, TYŚ WSZYSTKIM",
        "262. JEZUS CHRYSTUS JEST MOIM ZBAWIENIEM", "263. JEZUS CHRYSTUS MOIM PANEM JEST",
        "264. JEZUS CHRYSTUS PANEM JEST", "265. JEZUS CHRYSTUS PRZYSZEDŁ",
        "266. JEZUS CHRYSTUS SYN BOGA, ZBAWICIEL", "267. JEZUS CHRYSTUS TO PANÓW PAN",
        "268. JEZUS CHRYSTUS, BÓG I CZŁOWIEK", "269. JEZUS DAJE NAM ZBAWIENIE",
        "270. JEZUS DROGĄ", "271. JEZUS JEST TU", "272. JEZUS JEST Z NAMI TU",
        "273. JEZUS MĄ MIŁOŚCIĄ JEST", "274. JEZUS MÓJ DOBRY JEST",
        "275. JEZUS ODDAŁ ZA NAS ŻYCIE SWE", "276. JEZUS ZWYCIĘŻYŁ", "277. JEZUS, NAJWYŻSZE IMIĘ",
        "278. JEZUS, ŚWIĘTY NAMASZCZONY PAN", "279. JEZUSIE, SYNU DAWIDA",
        "280. JEŻELI TO TWÓJ GŁOS, MÓJ BOŻE", "281. JUŻ SIĘ NIE LĘKAJ",
        "282. JUŻ TERAZ WE MNIE KWITNĄ TWE OGRODY", "283. KAŻDEGO DNIA PRZY TOBIE TRWAĆ",
        "284. KAŻDY DZIEŃ JEST ZWYCIĘSTWEM", "285. KAŻDY SPRAGNIONY", "286. KAŻDY TWÓJ WYROK",
        "287. KAŻDY WSCHÓD SŁOŃCA", "288. KIEDY BRAKUJE SIŁ", "289. KIEDY CI SMUTNO",
        "290. KIEDY CICHNIE GWAR", "291. KIEDY JESTEM Z TOBĄ", "292. KIEDY NAD RÓWNINĄ",
        "293. KIEDY RANNE WSTAJĄ ZORZE", "294. KIEDYŚ WINO I CHLEB", "295. KIM JESTEŚ TY, PANIE",
        "296. KIMŻE JEST TA", "297. KOCHAĆ, TO ZNACZY POWSTAWAĆ", "298. KOCHAM",
        "299. KOCHAM TWOJĄ WOLĘ PANIE", "300. KOCHAM CIEBIE, JEZU",
        "301. KOCHAM, WIĘC NIE MUSZĘ SIĘ BAĆ", "302. KOGO, OPRÓCZ CIEBIE MAM",
        "303. KRÓL KRÓLÓW", "304. KRÓLOWO NIEBA, WESEL SIĘ", "305. KRZYŻ JEST ŹRÓDŁEM",
        "306. KRZYŻU CHRYSTUSA, BĄDŹŻE POCHWALONY", "307. KRZYŻU ŚWIĘTY NADE WSZYSTKO",
        "308. KTO NIE MIŁUJE", "309. KTO ODCZUWA PRAGNIENIE I", "310. KTO ODCZUWA PRAGNIENIE II",
        "311. KTO SPOŻYWA MOJE CIAŁO", "312. KTO ZAUFAŁ CI", "313. KTÓRZY UFAJĄ PANU",
        "314. KTÓŻ, JAK BÓG, BARANEK I LEW", "315. KU TOBIE PANIE", "316. LAUDATE DOMINUM",
        "317. LAUDATE, OMNES GENTES", "318. LAUDATO SII, O MI SIGNORE",
        "319. LAUDATO SII, SIGNIORE MIO", "320. LIST DO BOGA", "321. LITANIA CHRZCIELNA",
        "322. LITANIA POKUTNA", "323. LUD TWÓJ, PANIE", "324. LUDU KAPŁAŃSKI",
        "325. ŁASKA TWOJA PANIE", "326. ŁASKĄ JESTEŚMY ZBAWIENI", "327. MA DUSZA PRAGNIE BOGA",
        "328. MAGNIFICAT", "329. MARANATHA I", "330. MARANATHA II", "331. MARIO, PROSZĘ, SPRAW",
        "332. MARYJO WEŹ MNIE ZA RĘKĘ", "333. MARYJO, MATKO MOJEGO WEZWANIA",
        "334. MARYJO, NAUCZ MNIE MODLIĆ SIĘ", "335. MARYJO, ŚLICZNA PANI", "336. MATKA",
        "337. MARYJO, TYŚ NASZĄ NADZIEJĄ", "338. MATKO BOGA", "339. MATKO RANNYCH PACIERZY",
        "340. MATKO, JA WIELBIĘ CIĘ", "341. MATKO, KTÓRA NAS ZNASZ", "342. MEMU BOGU, KRÓLOWI",
        "343. MILCZĘ I KOCHAM", "344. MIŁOSIERDZIE PAŃSKIE", "345. MIŁOŚCI KRÓL",
        "346. MIŁOŚĆ DODA WAM SKRZYDEŁ", "347. MIŁOŚĆ NIEPRZYJACIÓŁ", "348. MIŁOŚĆ TWA",
        "349. MIŁOŚĆ TWOJA NAD GÓRAMI", "350. MIŁOŚĆ UKRZYŻOWANA", "351. MIŁUJCIE SIĘ WZAJEMNIE",
        "352. MIRIAM", "353. MISERICORDIAS DOMINI", "354. MNIEJSZY", "355. MODLITWA FRANCISZKAŃSKA",
        "356. MOCĄ SWĄ OBDARZ NAS", "357. MOGĘ MÓWIĆ, ŻE KOCHAM", "358. MOŻE DALEKO",
        "359. MÓJ JEZU, MÓJ ZBAWCO", "360. MÓJ JEZUS KRÓLEM KRÓLÓW JEST", "361. MÓJ MISTRZU",
        "362. MÓJ PAN JEST DOBRYM PASTERZEM", "363. MÓJ PAN MOCĄ MOJĄ JEST", "364. MÓJ PAN ŻYJE",
        "365. MÓJ ZBAWICIEL", "366. MÓW DO MNIE, PANIE", "367. MÓWIŁAŚ, WIEM", "368. MROK",
        "369. MY JESTEŚMY NA ZIEMI ŚWIATŁEM TWYM", "370. NA DRUGI BRZEG",
        "371. NA GOLGOCIE STOI KRZYŻ", "372. NA KRZYŻ PATRZĘ W SAN DAMIANO",
        "373. NA NOWO STWARZAJ MNIE", "374. NA WIEKI BĘDZIE TRWAĆ", "375. NA ZIEMIĘ PEŁNĄ KRZYWD",
        "376. NAD RZEKAMI BABILONU", "377. NAJPIERW ANIOŁ JEJ ZWIASTOWAŁ",
        "378. NAJWYŻSZEMU PANU CHWAŁA", "379. NAKARMIŁEŚ MNIE, PANIE", "380. NALEJMY OLIWY",
        "381. NASZ BÓG", "382. NASZ BÓG JEST POTĘŻNY W MOCY SWEJ",
        "383. NASZ PAN NA PEWNO PRZYJDZIE", "384. NAUCZ MNIE MIŁOŚCI KRZYŻA",
        "385. NIC NAS NIE ZDOŁA ODŁĄCZYĆ", "386. NIC NIE MUSISZ MÓWIĆ, NIC",
        "387. NIE BÓJ SIĘ, BO JA JESTEM Z TOBĄ", "388. NIE BÓJ SIĘ, NIE LĘKAJ SIĘ",
        "389. NIE BÓJCIE SIĘ ŻYĆ DLA MIŁOŚCI", "390. NIE CHCĘ WIĘCEJ",
        "391. NIE LĘKAJ SIĘ MÓJ SŁUGO", "392. NIE LĘKAJCIE SIĘ", "393. NIE LĘKAJCIE SIĘ (LEDNICA)",
        "394. NIE MA CIĘ, PANIE", "395. NIE MA INNEGO BOGA", "396. NIE MA W ŻADNYM INNYM ZBAWIENIA",
        "397. NIE MA WIĘKSZEJ MIŁOŚCI", "398. NIE MAM NIC",
        "399. NIE MAM NIC, CO MÓGŁBYM TOBIE DAĆ", "400. NIE MAM NIC, OPRÓCZ TWEJ MIŁOŚCI",
        "401. NIE MAMY JUŻ MIEJSCA STAŁEGO", "402. NIE NAM PANIE, NIE NAM",
        "403. NIE ODCHODŹ JESZCZE, OJCZE FRANCISZKU", "404. NIE PŁACZ, NIE PŁACZ MIRIAM",
        "405. NIE PŁACZ, TO TYLKO KRZYŻ", "406. NIE ROZBUDZAJCIE JEJ",
        "407. NIE ŚCIGAJ SIĘ Z MIŁOŚCIĄ", "408. NIE TEN BOGATY",
        "409. NIE UMIEM DZIĘKOWAĆ CI, PANIE", "410. NIE UMRĘ", "411. NIE ZDEJMĘ KRZYŻA",
        "412. NIEBIOSA, ROSĘ", "413. NIECH BĘDĄ PRZEPASANE", "414. NIECH BĘDZIE CHWAŁA",
        "415. NIECH CIĘ PAN BŁOGOSŁAWI I STRZEŻE", "416. NIECH CZYSTA WODA",
        "417. NIECH MOJE MILCZENIE", "418. NIECH NAS OGARNIE ŁASKA", "419. NIECH OBLICZE",
        "420. NIECH TWÓJ ŚWIĘTY DUCH", "421. NIECH ZAPŁONIE PŁOMIEŃ", "422. NIECHAJ CIĘ, PANIE",
        "423. NIECHAJ MIŁOŚĆ TWA", "424. NIECHAJ ZSTĄPI DUCH TWÓJ", "425. NIEGODNY",
        "426. NIEPOJĘTA ŁASKA", "427. NIEPOJĘTA TRÓJCO", "428. O HOSTIO ŚWIĘTA",
        "429. O JAHWE, BOŻE OJCÓW MOICH", "430. O JEZU, CICHY I POKORNY",
        "431. O MÓJ NAJSŁODSZY JEZU", "432. O JEZU, BOŻE UTAJONY", "433. O MÓJ PANIE, UCZYŃ MNIE",
        "434. O NAJWYŻSZY BOŻE NASZ", "435. O PANI, UFNOŚĆ NASZA",
        "436. O PANIE SZUKASZ DZIECI TWYCH", "437. O PANIE, NASZ PANIE",
        "438. O PANIE, TY NAM DAJESZ", "439. O PANIE, TYŚ MOIM PASTERZEM",
        "440. O PANIE, UCZYŃ Z NAS NARZĘDZIA TWOJEGO POKOJU", "441. O PIĘKNOŚCI NIESTWORZONA",
        "442. O PRZENAJŚWIĘTSZE OBLICZE JEZUSA", "443. O PRZYJDŹ, O PRZYJDŹ EMANUELU",
        "444. O TAK, TAK, TAK PANIE, MÓWIĘ TAK", "445. O TY, CO MIESZKASZ SAM",
        "446. O USŁYSZ MÓJ GŁOS", "447. O WYCHWALAJCIE GO WSZYSTKIE NARODY",
        "448. OBDARZ NAS, PANIE, POKOJEM", "449. OBJAWIONE SERCE BOGA", "450. OBLUBIENICA I DUCH",
        "451. OBLUBIENIEC CZEKA JUŻ", "452. OBLUBIEŃCEM MYM", "453. OBUDŹ MNIE",
        "454. OCEANY (exodus 15)", "455. OCIEŃ MNIE, PANIE", "456. OCZAROWAŁAŚ ME SERCE",
        "457. OCZEKUJĘ CIEBIE PANIE", "458. OCZYŚĆ SERCE ME", "459. ODDAJĘ CI",
        "460. ODDAJĘ CI ŻYCIE SWE", "461. ODDAJMY CZEŚĆ", "462. ODNÓW MNIE",
        "463. ODPOCZNIENIE W CIENIU SKRZYDEŁ TWYCH", "464. OFIARUJĘ PANIE CI",
        "465. OFIARUJĘ TOBIE, PANIE MÓJ", "466. OGŁASZAMY KRÓLESTWO BOŻE W NAS",
        "467. OJCZE CHWAŁA TOBIE", "468. OJCZE DAJ MI DUCHA", "469. OJCZE JEŚLI MOŻLIWE",
        "470. OJCZE FRANCISZKU", "471. OJCZE MÓJ", "472. OJCZE NASZ", "473. OJCZE TY KOCHASZ MNIE",
        "474. ON JEST MOIM ŻYCIEM", "475. ON SAM JEST SOLĄ SMAKOWITĄ", "476. ORŁY",
        "477. OŚWIEĆ ME SERCE", "478. OTO IDZIE MÓJ BÓG", "479. OTO IDZIE TEN",
        "480. OTO JEST DZIEŃ, KTÓRY DAŁ NAM PAN", "481. OTO JEST DZIEŃ", "482. OTO JESTEM",
        "483. OTO ON ŻYJE", "484. OTO PAN, BÓG, PRZYJDZIE", "485. OTO SĄ BARANKI MŁODE",
        "486. OTO STOJĘ U DRZWI", "487. OTWÓRZ ME OCZY", "488. OTWÓRZ ME OCZY, O PANIE",
        "489. OTWÓRZ OKNA", "490. OTWÓRZCIE SERCA", "491. PAMIĘTNIK APOSTOŁA",
        "492. PAN BLISKO JEST", "493. PAN DOBRY JEST", "494. PAN JEST MOCĄ",
        "495. PAN JEST PASTERZEM MOIM", "496. PAN JEST PASTERZEM MOIM (LAJ, LAJ)",
        "497. PAN MA WIELKĄ MOC", "498. PAN MNIE STRZEŻE", "499. PAN WIECZERNIK PRZYGOTOWAŁ",
        "500. PAN WOŁA", "501. PAN WYWYŻSZONY", "502. PAN ZMARTWYCHWSTAŁ",
        "503. PANIE DAJ MI ŁASKĘ WIERNEJ MODLITWY", "504. PANIE DOBRY JAK CHLEB",
        "505. PANIE JEŚLI CHCESZ", "506. PANIE JEZU CHRYSTE, SYNU BOŻY", "507. PANIE MÓJ",
        "508. PANIE MOJE SERCE MAŁE JEST", "509. PANIE MÓJ, CÓŻ CI ODDAĆ MOGĘ",
        "510. PANIE MÓJ, PROSZĘ WEJDŹ", "511. PANIE MÓJ, PRZYCHODZĘ DZIŚ",
        "512. PANIE MY TWOJE DZIECI", "513. PANIE NASZ, O PANIE NASZ", "514. PANIE OGIEŃ DAJ",
        "515. PANIE PROSZĘ ZABIERZ MNIE", "516. PANIE PRZEBACZ NAM", "517. PANIE PRZYJDŹ, CZEKAMY",
        "518. PANIE TWÓJ TRON", "519. PANIE TY ZNASZ MOJE IMIĘ", "520. PANIE WIELBIĘ CIĘ",
        "521. PANIE ZMIŁUJ SIĘ NAD NAMI", "522. PANIE, CO CHCESZ, ABYM CZYNIŁ",
        "523. PANIE, DAJ MI TWEJ WODY", "524. PANIE, GDY TONĘ", "525. PANIE, PANIE NASZ",
        "526. PANIE, POZOSTAŃ", "527. PANIE, PRZEPASZ MNIE", "528. PANIE, SPRAW, BY ME SERCE",
        "529. PANIE, ŚWIATŁO MIŁOŚCI", "530. PANIE, TYŚ JEST MOIM BOGIEM",
        "531. PANIE, WYSŁUCHAJ PROŚBY MEJ", "532. PANU NASZEMU PIEŚNI GRAJCIE",
        "533. PANU ŚPIEWAJ, PANA CHWAL", "534. PASCHALNY DESZCZ",
        "535. PATRZYSZ NA MNIE TAKIM WZROKIEM", "536. PER CRUCEM", "537. PER SORELLA",
        "538. PIEŚŃ MAŁEJ DUSZY", "539. PIEŚŃ TĘSKNOTY", "540. PŁOMIEŃ MIŁOŚCI",
        "541. POCHWAŁA CNÓT", "542. POCHWALONY BĄDŹ O PANIE", "543. PODNIEŚ MNIE, JEZU",
        "544. POGWARZ ZE MNĄ", "545. POKŁADAM W PANU UFNOŚĆ MĄ", "546. POKORNA SŁUŻEBNICO PANA",
        "547. POKÓJ I DOBRO", "548. POŁÓŻ MNIE JAK PIECZĘĆ", "549. POSŁUCHAJ CÓRKO I",
        "550. POSŁUCHAJ CÓRKO II", "551. POSYŁAM WAS", "552. POŚRÓD NOCY",
        "553. POWIEDZ MARYJO MATKO", "554. POWIEDZIAŁEŚ PANIE: „DOSYĆ LĘKU”",
        "555. POWIERZ PANU SWOJĄ DROGĘ", "556. POWIETRZEM MOIM", "557. POWOŁAŁ MNIE PAN",
        "558. POWSTAŃ I ŻYJ", "559. POZDRAWIAM CIEBIE MATKO", "560. POZDRAWIAM CIĘ MATKO",
        "561. POZDROWIENIE BŁOGOSŁAWIONEJ MARYI DZIEWICY", "562. POZOSTAŃ Z NAMI, PANIE",
        "563. POZWÓL MI PRZYJŚĆ DO CIEBIE", "564. PÓJDĘ Z WIARĄ", "565. PÓKI MEGO ŻYCIA",
        "566. PÓKI MEGO ŻYCIA, CHCĘ ŚPIEWAĆ", "567. PRAGNĘ", "568. PRAGNĘ ŚPIEWAĆ CI",
        "569. PRAGNIENIE", "570. PRAGNIJCIE POSIĄŚĆ DUCHA PAŃSKIEGO", "571. PRAWDA JEDYNA",
        "572. PROŚBA O DUCHA", "573. PROWADŹ, PANIE", "574. PRZED OBLICZEM PANA",
        "575. PRZED TRONEM TWYM STOIMY", "576. PRZED TWÓJ TRON", "577. PRZEMIEŃ MÓJ CZAS",
        "578. PRZEMIEŃ NAS, PANIE", "579. PRZEMIEŃ SERCE ME", "580. PRZEOGROMNA ZIEMIO",
        "581. PRZEPIĘKNY KRÓLU", "582. PRZYBĄDŹ PANIE, BO CZEKAMY", "583. PRZYBĄDŹ PŁOMIENIU",
        "584. PRZYBĄDŹ ŚWIĘTY", "585. PRZYBĄDŹ WSCHODZĄCE SŁOŃCE", "586. PRZYBĄDŹCIE TU",
        "587. PRZYCHODZISZ, PANIE, MIMO DRZWI ZAMKNIĘTYCH", "588. PRZYJACIEL KRZYŻA",
        "589. PRZYJACIELA MAM", "590. PRZYJACIELU, CHCĘ ZOSTAĆ Z TOBĄ", "591. PRZYJDŹ DO NAS CICHO",
        "592. PRZYJDŹ DUCHU KOŚCIOŁA", "593. PRZYJDŹ DUCHU ŚWIĘTY - SŁYSZ WOŁANIE",
        "594. PRZYJDŹ DUCHU ŚWIĘTY - STRUMIENIU ŻYCIA", "595. PRZYJDŹ DUCHU ŚWIĘTY, JA PRAGNĘ",
        "596. PRZYJDŹ DUCHU ŚWIĘTY, NAPEŁNIJ ME SERCE", "597. PRZYJDŹ DUCHU ŚWIĘTY, NAPEŁNIJ SERCA",
        "598. PRZYJDŹ I ROZPAL NAS", "599. PRZYJDŹ I ZAJMIJ MIEJSCE SWE", "600. PRZYJDŹ Z POKŁONEM",
        "601. PRZYJDŹ, DUCHU POKORY", "602. PRZYJDŹ, DUCHU, PRZYJDŹ",
        "603. PRZYJDŹ, PRZYJDŹ, ŚWIATŁOŚCI SERC", "604. PRZYJDŹCIE DO STOŁU MIŁOSIERDZIA",
        "605. PRZYJDŹMY TAM, GDZIE MIEJSCE NAJŚWIĘTSZE", "606. PRZYJDŹMY, POKŁOŃMY PRZED NIM SIĘ",
        "607. PRZYJMIJ BOŻE TE DARY", "608. PRZYJMIJ PANIE DZISIAJ NASZE DARY",
        "609. PRZYJMIJ PANIE, NASZE DARY", "610. PRZYMNÓŻ NAM WIARY I",
        "611. PRZYMNÓŻ NAM WIARY II", "612. PRZYSTĄPMY, CHWALMY GO",
        "613. PRZYTUL MNIE DO SWOICH RAN", "614. PRZYTUL MNIE, JEZU", "615. PRZYWOŁAJ MNIE PANIE",
        "616. PSALM 116", "617. PSALM 126", "618. PSALM 16", "619. PSALM 139", "620. PSALM 131",
        "621. PUSTYNIA JEST ŁASKĄ", "622. RADOŚĆ TCHNIJ W SERCE ME",
        "623. RADOŚNIE PANU HYMN ŚPIEWAJMY", "624. RATUJ, PANIE", "625. REGGAE", "626. REWOLUCJA",
        "627. ROZPIĘTY NA RAMIONACH", "628. RUAH I", "629. RUAH II",
        "630. RUSZAJ, RUSZAJ, RUSZAJ TAM", "631. RZEKA", "632. RZEKŁ PAN", "633. RZUĆ MNIE W MORZE",
        "634. SALVE REGINA", "635. SAMBA PROSTACZKA", "636. SCHOWAJ MNIE",
        "637. SEKWENCJA O DUCHU ŚWIĘTYM", "638. SERCA NASZE PEŁNE BOGA", "639. SERCE BOGA",
        "640. SERCE WIELKIE NAM DAJ", "641. SIOSTRO MA, OBLUBIENICO", "642. SKORO PANIE",
        "643. SKOSZTUJCIE I ZOBACZCIE", "644. SKOSZTUJCIE I ZOBACZCIE (P. BĘBENEK)",
        "645. SŁAWIĘ CIĘ", "646. SŁAWMY PANA", "647. SŁOWA JEGO", "648. SŁOWA TWYCH UST",
        "649. SŁOWO TWOJE LAMPĄ DLA MYCH STÓP", "650. SŁUCHAJ, IZRAELU I",
        "651. SŁUCHAJ, IZRAELU II", "652. SŁYSZĘ JUŻ ARMII PANA GŁOS",
        "653. SPOCZNIJ JEDYNIE W BOGU", "654. SPOCZNIJ NA NAS DUCHU PANA",
        "655. SPRAWIEDLIWI RADOŚNIE", "656. STAŃMY RADOŚNIE", "657. STOJĘ DZIŚ, MOJE SERCE",
        "658. STWÓRCA WSZYSTKICH RZECZY", "659. STWÓRZ, BOŻE, WE MNIE SERCE CZYSTE",
        "660. SURREXIT CHRISTUS", "661. SWOJEGO DUCHA", "662. SYN SŁUŻEBNICY",
        "663. SZCZĘŚLIWI, KTÓRYCH MOC JEST W PANU", "664. SZEDŁEM KIEDYŚ INNĄ DROGĄ",
        "665. SZEDŁEM KIEDYŚ ŚCIEŻYNĄ", "666. SZUKAJCIE WPIERW", "667. ŚLADY FRANCISZKA",
        "668. ŚPIEWA IZRAEL", "669. ŚPIEWAJ ALLELUJA PANU", "670. ŚPIEWAJ DZIŚ PANU",
        "671. ŚPIEWAJMY MU, DZIĘKUJMY MU", "672. ŚPIEWAJMY PANU", "673. ŚPIEWAM TOBIE MAGNIFICAT",
        "674. ŚWIATŁEM I ZBAWIENIEM MYM", "675. ŚWIATŁOŚĆ W CIEMNOŚCI ŚWIECI",
        "676. ŚWIĘTE IMIĘ JEZUS", "677. ŚWIĘTEMU BOGU ODDAJ CZEŚĆ",
        "678. ŚWIĘTY DUCHU, PRZYJDŹ I PROWADŹ MNIE", "679. ŚWIĘTY JEST NASZ PAN",
        "680. ŚWIĘTY JEST NASZ PAN WSZECHMOGĄCY", "681. ŚWIĘTY, ŚWIĘTY PAN",
        "682. ŚWIĘTY, ŚWIĘTY PAN BÓG ZASTĘPÓW", "683. ŚWIĘTY, ŚWIĘTY, ŚWIĘTY JEST NASZ BÓG",
        "684. TA KREW Z GRZECHÓW OBMYWA NAS", "685. TAJEMNY AKORD – ALLELUJA", "686. TAK JAK RZEKI",
        "687. TAK JAKOŚ DZIWNIE JEST NA ŚWIECIE", "688. TAK MNIE SKRUSZ",
        "689. TAK PRAGNĘ WIELBIĆ CIĘ", "690. TAK, TAK, TAK PANIE", "691. TCHNIJ MOC",
        "692. TEN, KTÓRY SŁOWEM STWARZA", "693. TERAZ TAK MÓWI PAN", "694. TĘSKNIĘ ZA TOBĄ, PANIE",
        "695. TO JEST BARANKA CIAŁO, JEGO KREW", "696. TO MÓJ PAN", "697. TO PRZYKAZANIE",
        "698. TOBIE CHÓR ANIOŁÓW", "699. TOBIE CHWAŁA", "700. TOBIE, PANIE, ZAUFAŁEM",
        "701. TRÓJCO ŚWIĘTA", "702. TWE DROGI", "703. TWOJE MIŁOWANIE",
        "704. TWOJE SŁOWO JEST LAMPĄ", "705. TWÓJ KRZYŻ", "706. TWÓJ PAN, TWÓJ BÓG",
        "707. TY JESTEŚ SKAŁĄ ZBAWIENIA MEGO", "708. TY FRANCISZKU", "709. TY JESTEŚ ŚWIĘTY",
        "710. TY PANIE", "711. TY PANIE DAŁEŚ ŻYCIE MI", "712. TY TYLKO MNIE POPROWADŹ",
        "713. TY WYZWOLIŁEŚ NAS, PANIE", "714. TY ZAPRASZASZ MNIE",
        "715. TYLKO W TWOIM MIŁOSIERDZIU", "716. TYLE DOBREGO", "717. TYŚ JAK SKAŁA",
        "718. TYŚ NASZĄ MOCĄ", "719. TYŚ W WIECZERNIKU CHLEBEM SIĘ STAŁ", "720. U CIEBIE, BOŻE",
        "721. UBI CARITAS", "722. UCISZ SIĘ", "723. UCZTA BARANKA",
        "724. UKAŻ, MI PANIE, SWĄ TWARZ", "725. UKAŻ, MI PANIE, SWĄ TWARZ (LEDNICA)",
        "726. UKOJENIE", "727. UKRZYŻOWANA MIŁOŚĆ", "728. UWIELBIA CIEBIE ZIEMIA",
        "729. UWIELBIAĆ CIEBIE CHCĘ", "730. UWIELBIAĆ MEGO PANA CHCĘ", "731. UWIELBIAJCIE PANA",
        "732. UWIELBIAJMY JEZUSA", "733. UWIELBIAJMY PANA", "734. UWIELBIAM CIĘ, BŁOGOSŁAWIĘ CIĘ",
        "735. UWIELBIAM CIĘ, TRÓJCO!", "736. UWIELBIAM IMIĘ TWOJE, PANIE",
        "737. UWIELBIAM TWOJE IMIĘ", "738. UWIELBIENIE BOGA NAJWYŻSZEGO",
        "739. VENITE, EXULTEMUS DOMINO", "740. VENI CREATOR SPIRITUS (O STWORZYCIELU)",
        "741. W CIEMNOŚCI IDZIEMY", "742. W CIENIU TWOICH RĄK", "743. W DOLINIE",
        "744. W DROGĘ Z NAMI", "745. W KRUSZYNIE CHLEBA", "746. W JASNY DZIEŃ",
        "747. W LEKKIM POWIEWIE", "748. W MOCY SŁOWA ZSTĘPUJESZ", "749. W NASZYCH CIEMNOŚCIACH",
        "750. W RYTMIE ŁASKI", "751. W SWOIM WIELKIM MIŁOSIERDZIU", "752. W TOBIE JEST ŚWIATŁO",
        "753. W TO WIERZĘ (CREDO)", "754. WCIĄŻ MNIE ZADZIWIASZ PANIE", "755. WEJDŹMY DO JEGO BRAM",
        "756. WESEL SIĘ KRÓLOWO MIŁA", "757. WESELE W KANIE", "758. WIDZĘ NOWE NIEBO I ZIEMIĘ",
        "759. WIELBIĆ MEGO PANA CHCĘ", "760. WIELBIĆ PANA CHCĘ",
        "761. WIELBIĘ CIĘ, CAŁE ŻYCIE CI ODDAJĘ", "762. WIELBIJ PANA, ŚPIEWAJ HYMN",
        "763. WIELBIJ, WIELBIJ GO", "764. WIELBIMY CIĘ", "765. WIELCE NALEŻY KOCHAĆ",
        "766. WIELE JEST SERC", "767. WIELKIE I DZIWNE SĄ DZIEŁA TWOJE", "768. WIEM, ŻE WIERZYSZ",
        "769. WIERZĘ W CIEBIE, PANIE", "770. WIĘCEJ MIŁOŚCI", "771. WITAJ POKARMIE",
        "772. WITAJ GWIAZDO MORZA", "773. WITAJ, NAJCZYSTSZA KRÓLOWO SERC", "774. WODY JORDANU",
        "775. WPROWADŹ MNIE W KOMNATY TWOJE", "776. WSPANIAŁY DAWCO MIŁOŚCI",
        "777. WSPANIAŁY JEST MAJESTAT TWÓJ", "778. WSTANĘ, PO MIEŚCIE CHODZIĆ BĘDĘ",
        "779. WSZECHMOGĄCY PANIE", "780. WSZYSTKIE MOJE TROSKI",
        "781. WSZYSTKIE NARODY KLASKAJCIE W DŁONIE", "782. WSZYSTKIE NASZE DZIENNE SPRAWY",
        "783. WSZYSTKO CO ŻYJE", "784. WSZYSTKO MOGĘ W TYM", "785. WSZYSTKO TOBIE",
        "786. WY JESTEŚCIE NA ZIEMI", "787. WY JESTEŚCIE SOLĄ ZIEMI", "788. WYCIĄGNIJ RĘCE",
        "789. WYDANI NA ŚMIERĆ", "790. WYKRZYKUJCIE BOGU", "791. WYKRZYKUJCIE NA CZEŚĆ PANA",
        "792. WYPŁYŃ NA GŁĘBIĘ", "793. WYSŁAWIAJCIE PANA", "794. WYSŁUCHAJCIE MEGO GŁOSU",
        "795. WYSTARCZY BYŚ BYŁ", "796. WYSTARCZY ISKRA", "797. WYWYŻSZONY",
        "798. WZNIEŚMY SWE RĘCE", "799. WZNOSZĘ RĘCE ME WZWYŻ", "800. WZYWAM CIĘ",
        "801. WZYWAM CIĘ, DUCHU, PRZYJDŹ", "802. Z BOGA JESTEM",
        "803. Z CAŁEGO SERCA CHCĘ CHWALIĆ PANA", "804. Z NIEBA SPŁYWA ŁASKA (Wołczyn 2009)",
        "805. Z GŁĘBOKOŚCI MÓRZ", "806. Z MIŁOŚCI UMARŁ PAN", "807. Z TOBĄ CIEMNOŚĆ",
        "808. Z WIELKĄ UFNOŚCIĄ", "809. ZA MNĄ CHODŹ", "810. ZABIERZ MNIE",
        "811. ZANIM POWIEM SŁOWO", "812. ZAPROWADŹ MNIE TAM", "813. ZAŚPIEWAĆ CHCĘ MIŁOŚCI PIEŚŃ",
        "814. ZAŚPIEWAJCIE SERCA NASZE", "815. ZAŚPIEWAM BOGU", "816. ZAUFAJ PANU",
        "817. ZAUFAŁEM PANU I JUŻ", "818. ZAWRÓĆMY DO PANA", "819. ZBAWCA, ON PORUSZA GÓRY",
        "820. ZBAWIENIE PRZYSZŁO PRZEZ KRZYŻ", "821. ZBAWIONY", "822. ZBLIŻA SIĘ DO NAS",
        "823. ZBLIŻAM SIĘ W POKORZE", "824. ZBUDUJ, OJCZE NASZ", "825. ZBUDŹ SIĘ, O ŚPIĄCY",
        "826. ZIEMIA", "827. ZBYTNIE TROSKI", "828. ZEŚLIJ DESZCZ", "829. ZJEDNOCZENI W DUCHU",
        "830. ZMARTWYCHWSTAŁ PAN", "831. ZMARTWYCHWSTAŁ PAN - PSALM 118",
        "832. ZMARTWYCHWSTAŁY PAN", "833. ZMIŁUJ SIĘ, BOŻE, NADE MNĄ", "834. ZNAK POKOJU",
        "835. ZOBACZCIE, JAK JEST PIĘKNIE", "836. ZOBACZCIE, JAK WIELKĄ MIŁOŚCIĄ",
        "837. ZOBACZCIE, JAK WIELKĄ MIŁOŚĆ", "838. ZOSTAŃ TU I ZE MNĄ SIĘ MÓDL",
        "839. ZWIASTOWANIE", "840. ZWIASTUNOM Z GÓR", "841. ŹRÓDŁA", "842. ŻADNA PIEŚŃ",
        "843. ŻYĆ W SPOŁECZNOŚCI Z TOBĄ", "844. BOZIU, BOZIU MÓJ", "845. BOŻA RADOŚĆ JAK RZEKA",
        "846. BÓG KOCHA MNIE", "847. GDY BOŻY DUCH", "848. GDY FRANEK BYŁ MAŁY",
        "849. GDY NA MORZU WIELKA BURZA", "850. GDY PAN JEZUS BYŁ MALUTKI", "851. IDZIE JEZUS",
        "852. JA I TY", "853. JA SPOTKAM JEZUSA", "854. JAK MI DOBRZE",
        "855. JAK ROZPOZNAĆ MAM CHRYSTUSA", "856. JESTEŚ RADOŚCIĄ MOJEGO ŻYCIA",
        "857. JEZUS DZIŚ PRZYSZEDŁ DO MNIE", "858. KRZYCZ ZE SZCZĘŚCIA",
        "859. LUDZIE, KTÓRYCH BÓG JEST PANEM", "860. MALEŃKIE MRÓWKI",
        "861. NA POCZĄTKU BÓG STWORZYŁ", "862. NIE CHCĘ CHODZIĆ W PIECHOCIE",
        "863. NIEBO JEST W SERCU MYM", "864. ODDAJ BRZEMIĘ JEZUSOWI",
        "865. PANIE JEZU, ZABIERZEMY CIĘ DO DOMU", "866. PANIE, TY JESTEŚ RADOŚCIĄ MĄ",
        "867. PIOSENECZKA", "868. PŁYNĄ STATKI Z BANANAMI", "869. PODAJ RĘKĘ SWEMU BRATU",
        "870. ŚWIĘTY UŚMIECHNIĘTY", "871. TATO (NIE BOJĘ SIĘ, GDY CIEMNO JEST)",
        "872. W MOIM SERCU MIESZKA BÓG", "873. A WCZORA Z WIECZORA", "874. ACH UBOGI ŻŁOBIE",
        "875. ANIOŁ PASTERZOM MÓWIŁ", "876. BÓG SIĘ RODZI", "877. BRACIA, PATRZCIE JENO",
        "878. CICHA NOC", "879. DO SZOPY, HEJ PASTERZE", "880. DZISIAJ W BETLEJEM WESOŁA NOWINA",
        "881. DZIŚ W STAJENCE", "882. GDY SIĘ CHRYSTUS RODZI", "883. GDY ŚLICZNA PANNA",
        "884. GORE GWIAZDA", "885. HEJ, W DZIEŃ NARODZENIA", "886. HOJKA",
        "887. JAKAŚ ŚWIATŁOŚĆ NAD BETLEJEM SIĘ ROZCHODZI", "888. JEST TAKI DZIEŃ",
        "889. JEZU ŚLICZNY KWIECIE", "890. JEZUS MALUSIEŃKI", "891. JEZUSA NARODZONEGO",
        "892. KOLĘDA DLA NIEOBECNYCH", "893. LULAJ, ŚPIJ MALEŃKI", "894. LULAJŻE, JEZUNIU",
        "895. MALEŃKI JEZU", "896. MARIO, CZY JUŻ WIESZ", "897. MĘDRCY ŚWIATA, MONARCHOWIE",
        "898. MIZERNA, CICHA STAJENKA LICHA", "899. NA TYM NIEBIE",
        "900. NIE BYŁO MIEJSCA DLA CIEBIE", "901. NOWY ROK BIEŻY", "902. O GWIAZDO BETLEJEMSKA",
        "903. OJ MALUŚKI, MALUŚKI", "904. PASTERZE MILI", "905. PÓJDŹMY WSZYSCY DO STAJENKI",
        "906. PRZYBIEŻELI DO BETLEJEM PASTERZE", "907. PRZYSTĄPMY DO SZOPY", "908. SKRZYPI WÓZ",
        "909. ŚWIEĆ, GWIAZDECZKO", "910. TRIUMFY KRÓLA NIEBIESKIEGO", "911. UCIEKALI",
        "912. W GRUDNIOWE NOCE, W ZIMOWE NOCE", "913. W ŻŁOBIE LEŻY, KTÓŻ POBIEŻY",
        "914. WESOŁĄ NOWINĘ, BRACIA SŁUCHAJCIE", "915. WŚRÓD NOCNEJ CISZY",
        "916. Z NARODZENIA PANA DZIEŃ DZIŚ WESOŁY"
    )

    val chords = arrayOf(
        "e D h\na H7\n\nh\nh A h\nh\nh A D\nH7",
        "G C G D\nG C G D G\n\nG C G F D\nG C G D G",
        "e7 a e7\ne7 a e7\n\nD h e\nD h e\nC G D\na H7",
        "D D7 e A\nD D7 e A A7\n\nG A\nfis h\nG g B A A7",
        "C F\nC F\nd G C G a\nF E\n\na F G C\na F G C\na F G C\nF E",
        "e C D\nC H7 e\ne C D\nC H7 e\nD G D\ne H7",
        "E A E\nE gis\nA E\nE H7\nE A E\nE gis\nA E\nE H7\n\nE H E\nA H E\nH E\nE H E\nE cis H E\n\nE A E\nE gis\nA E\nE H",
        "e D h e C D e\na D h e C D e\n\ne h C D e\ne D\nC D e\n\ne h\nh e\n\ne h\nC D e\ne D\nC D e",
        "D G D A\nD e7 G A\nD G D A\nD e7 G A\n\nG A h\nG A h",
        "a G F E a\n\na G a\nG C G a\nF G a\na G a",
        "D A\nG A\nD\nG A D h\nG A D D7\nG A D h\nG e A",
        "fis\nE cis fis\n\nE cis\nfis",
        "d C d\nF C\nd C d\nF A7 d\n\nF C F\ng A\nF C F\ng A7 d",
        "C G d7 G\nC G d7 G\ne7 h4 C7+ G4-3\ne7 h4 C7+ G4-3\na D a D",
        "G A h e\nG A h\nG A fis G\ne h A\n\nD G h A\nD G h A\nD G h A\nh G C D A (a h e)",
        "A E D A\nD E\nA E D A\nD F G A\n\nA E D A\n\n\nD E\n\nA E D A\n\n\nD E\n\n\nF G A",
        "h G\nA D Fis\n\nh G\ne A D Fis\nh G\ne A D Fis\nh G e A D Fis",
        "D\nh\nG\nA",
        "E a\nd E\nE a\nd E\n\na F G C E\n\nE a\nd F\nG E\na\nd F\nG E E7\nE a\nd F\nG E E7\n\nE a\nd F\nG E E7\na F G C E",
        "E H fis H\nE H A H\nfis cis fis cis\nA cis fis H\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nE H fis H\nE H A H\nfis cis fis cis\nA cis fis H\n\n\n\n\n\n\nE A\ncis fis\nH fis E\nA H\nE A\ncis fis\nH fis H E\n\n\n\n\n\n\nE H fis H\nE H A H\nfis cis fis cis\nA cis fis H\n\nE A\ncis fis\nH fis E\nA H\nE A\ncis fis\nH fis H E",
        "e a e D h C\na C a C D h e\n\nD\nD a\ne G\nD C",
        "C G7 a\nd F G G7\nC G C C7\n\nF G C A7\nd G C C7\nF G e a\nd G C",
        "d a7 d a7\nd a7 d a7",
        "e a h e a h7\nC D e H7\ne a h e a h7\nC D e H7\n\nG d7 a e\nC D C H7",
        "d B A A7 d\nd B A A7 d\nF d C A A7\nd B A A7 d",
        "D A h\nG E A\nD A h G A D",
        "E H7 E H7\nA H7 E (H7)\nA H E\nA H E H7",
        "e D h e\ne a C e\ne D h e\nD h a e\n\na G\na G\na G\na H7",
        "fis E fis\nfis E fis\nD E A fis\nfis E fis\n\nfis E\nA E\nfis E\nA E",
        "D G A G h\nG A7 D h\nD e A7 D",
        "e a7 D7\ne a7 D7\nC G\nC G H7 e\n\ne a7 D7\ne a7 D7\nC D G\nC D G H7 e",
        "d B C d\n\n\n\n\n\n\n\ng d\nC a d",
        "a C\nF2 F\n\n\na C F2 F G\nG\n\nF G d\na\nG\nF\nF G d\na\nG F",
        "a F\nd E a\na F\nd E",
        "E cis\nD A H\nE cis\nD A H\n\nA fis\nE\nA fis\nE\nA fis\nE\nA\nfis\nA\nfis H\n\n\n\n\n\n\n\n\n\n\n\n\nE\ncis\nD\nA H",
        "F C d C\nB C d C\nF C d\nB C d C (C7)\n\nF C d B C d C\nF C d B C d C\nF C d B C d C\nF C d B C d C",
        "d B C g\nd B C F\ng C d",
        "cis7 A2 E\nD A2 E\nH Fis\nA H Cis\n\n\n\n\n\n\nE H cis7 A\nE H Fis\n\ncis7 A2 E\nD A2 E\nH Fis\nA H Cis\n\n\n\n\n\n\nA H cis\nA E H A\nA H cis A H Cis\n\nA E fis7 D\nA E H",
        "G C G e\nC D G\ne H7 e/G H7 e\na e/C D\nD G e C\nG D G\nG C G e C D G D G",
        "G D e C\nG D C\nG D e C\nG D\nC\nG D e C\nG D e C\n\nG D\ne C\nG D\ne C\n\n\n\n\n\n\n\n\n\nG D e C\nG D e C",
        "E A E\nE A H7\ngis cis gis cis\nA fis H7\n\nE A E\nE A H7\ngis cis gis cis\nA fis E\n\nC D E\nC D E\nC D E\nfis H7",
        "G C\na D7\nh e a D\ne D h\ne a G\n\nG a h\ne C D\nC e\nG D7",
        "d a g d\nd g A7\nd F C d\nF g A7 d",
        "H7 e h\nh e\nD e\ne D G\nG a\na H7\n\nC D G\nC D e\nC D G\nC D C a",
        "A h A h\nfis G A\n\ncis7 D\ncis7 D\ncis7 D\nE A",
        "A D fis7\nA D E\nD E cis fis\nD E A\nD E cis fis\nD E\nA D fis\nA D E\nD E cis fis\nD E A\nD E cis fis\nD E\n\nE A D\nE A D\nA D A\nA D E A\nD E A",
        "d g d C d\nd g d a d\n\nd C a d",
        "C F7+\nC F7+\nd7 F\nG G7 C F7+",
        "A h D E",
        "D G D\ne7 A D\nD G D\ne7 A D\nh A\nD h A D",
        "D e\nG A7\nD\ne\nG A7 D",
        "G e a D\n\n\n\nH e\na D\nH e\na\nD\na D\na D",
        "cis H A\n\n\n\n\nfis A H\n\n\nA H cis",
        "a G d a G (a)",
        "C C/E d G a7 G4-3\n\na E a E\nC C/E d G a G C\nF2 C d C/E F G4\nF2 C d C/E f C G\nF2 C d C/E F G4\nF2 C d C/E F C",
        "D G A7 D\nG A7D\nfis\nG D A7\nD A D",
        "C a F G\nC a F G\nF C a f\nC G F C",
        "h e A D\nG e Fis",
        "D e G D",
        "e C\nG H7\ne C\nG H7\n\na e\na e\nC G H7",
        "E H A H\nE H A H\nfis cis H\nfis cis A H\n\nE H A\nE H A\nE H A\nE A H\n\nE H A H\ncis A fis",
        "d\ng\nC A7\nd\n\ng\nC A7\nd\n\ng\ng A\nB\nB A\n\ng\ng A\nB\nB A\n\ng A\ng A\ng C A\nB\nB A",
        "d C\nB A\n\n\n\n\n\n\nF C\nd B C\nF C\nd",
        "A D A fis h D E\nA D A E D E A",
        "A D A (F G)\n\n\n\n\nD/Fis E A2/Cis\n\nD E Cis7 fis\nD A h7 E4-3",
        "D G\nA D",
        "D fis\ne6 G h A\nD fis\ne6 G h A\n\nh A h\nG A\ne fis\nG A D G A",
        "g D\nD g\nD\n\n\nc D",
        "D A h fis\nD A h A\nD G A D\nh e7 Fis h",
        "e\nC D e",
        "G D\nC G D\nG D\nC G D H7 e D\n\nG\nD\nC G D\nC G D\nG D\nC G D H7 e (D)\n\n\n\n\n\n\n\n\n\n\n\n\n\nG D C",
        "D h\ne G A",
        "d F\ng d B F",
        "a D a C e a\na7 D e h D a\na D a C e a\na7 D e h D a\n\na C D e\nC D h e\na C D e\nC D h e",
        "D A\ne h\nD A\ne h\n\nD h\nD h\nG Fis\nD h\nD h\nG Fis",
        "cis A E H\ncis A E H\ncis A\nE H\ncis A E H",
        "G h\nG A D\nG h\nG A D\nD G A D G A\nD G A D G A",
        "G a C G\nG a C G\ne h C G\ne h C D\nG D\na G D\nG D\na G D\nG D\na G D\nG D\na G D G",
        "D G\nD h\nD G A A7\nD G D fis h\nG A D\nD G D h\nD G A A7\nD G D h\nG A D",
        "d C\nA7 d\nd C\nA7 d\ng C d\nC A7 d\ng d\nC A7 d\n\nC A d A\ng A7\nC g d A\nA",
        "e G D a\n\n\n\n\nC D e\nC D e\nC D G C\nC D a",
        "h A D G\nh A D G\nh A D G\nh A D G\n\nG A\nG A\n\n\n\n\n\n\n\n\n\n\n\n\nh G A",
        "E A H\nE A H\nE A H\nE A H\n\nE A H\nA E\nA H A\nE\nA H gis cis\nA H E",
        "G D\nC D\nG D\na e7\n\ne7 C G D",
        "d C B A\nB g a d\n\n\n\n\nd C B g A d",
        "e a D e\ne a D e",
        "A fis\nD h A\nA D A D\nA h\nD A E\nA fis\nh E A E\nA fis\nh E A",
        "e C H7\ne a H7\nC D a e\n\nG a e\nG e D e\nG a e\nG e D e H7",
        "d\nd C7 A7 d\ng B F\nd g C B F d A7 d",
        "h\nA\ne A h\n\n\nh A h\n\nh A h\n\nh A h\nh A h",
        "e a\nD\nC H7\n\ne\nC\nD\nH H7",
        "e\ne\nD h7\nC D e\n\ne\nD h7 C D e\ne\nD h7 C D e",
        "A cis\nD h E\n\nA fis\nfis D h E E7",
        "cis A E H\ncis A E H\ncis A E H\ngis A H\ncis A E H",
        "d C d\ng B C d\nd C d\nB C d\n\ng C d\nG A7\ng C d\nB A7",
        "C e F C\ne7 F G\nF C F e\ne F G\n\nF G e a\nF G C",
        "e D a e\nD e a e\n\n\n\ne H7 e a\nD h e\ne H7 e a\nD e",
        "F C G C F G\nF C G C\nF C a e\nF G C\na e\nF G",
        "D A G A",
        "e D e\ne a C D\nG C a G\ne a C D G H\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\ne D e\ne a C D\nG C a G\ne a C D G H\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\ne D e\ne a C D\nG C a G\ne a C D G H",
        "h e fis h\nD G A\nh e fis E\nfis G A h\n\nG A D\nG fis\nG A D\nG Fis7 h",
        "A7+ h7 (c cis)\ncis D7+\nA7+ h (c cis)\ncis D7+\n\nfis\ncis (c h)\nh cis\nD",
        "C G C G\na e a /x3",
        "fis E A E\nD E fis\n\nA E cis D\nA E\nA E D fis\nE cis fis",
        "G D\nD G\nG C G\ne C D G",
        "E H A H",
        "d d7 B g a\nF C\ng A d g A7 d",
        "e H7\nH7 e\nE a\nD a H7\n\n\n\n\n\na H7\na H7\n\n\n\n\n\n\n\n\n\n\ne H7\nH7 e\nE a\nD a H7",
        "C F G C F G\nG a\nG a\nG a\nG\nF G C",
        "a a7 d\nG7 C E\na d\na E7 a A7\nd a\nE7 a A7\nd a\nE7 a",
        "F C d B C2 d\nF C d B C2 d\nd g B C d C F\nF d C B g C F\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nF C d B C2 d\nF C d B C2 d\nd g B C d C F\nF d C B g C F",
        "G D\nC e\nG D C\nC D\nG e C\n1|C D e 2|C D G",
        "C G G C C7 F\nC G7 C",
        "d g B A\nF g B A\ng C\nF d\ng C d\n\nd g B A d\ng A d",
        "d G",
        "C G\nC F G\nd G\nF G\n\nC F G\nG F G C\nG C",
        "D A h7 G\nD A h7 G\nD A h7 G\nD A h7 G",
        "e D\ne D\na G\na H7\n\n\n\n\n\n\nG\ne\nC G D",
        "G G/H C D4-3\ne7 D/Fis G D\nG/H C D4-3\ne7 D/Fis D Dsus4\n\nG D/Fis\ne7 G/D D\nG/H C G/H\na7 G/H C D",
        "e D\nh e\ne D\nh e\n\nG D\ne h\ne h e",
        "d C d\na e a",
        "Cis A E H Fis\nfis Gis cis\nH7 a E\ncis Fis cis (A E H7)\n\n\n\n\n\n\n\n\n\n\n\nfis H\nD A\nfis H7 E\nfis H7 E",
        "E A E A\nH7 A H7 A\nE A E A\nH7 A H7 A\n\ncis D A H7 E\nA H7 E",
        "G e\nC D",
        "A E A E\nA E\nA E\n\ncis A E H\ncis A E H\ncis A E H\ncis A E",
        "e D C D e\nG D H7 e D C C/e",
        "a F C G\na F C E\ne G D\nF C D a",
        "e D e D a\ne D e C H7\ne D e D a\ne D e D e\n\na e\nH7\ne\na e\nFis H7",
        "A D E\nA D\nA D E\nA D (E)\n\nA\nDsus2 E A\nh E A\n\nD E D E fis (cis)\nD E D E fis E",
        "F g C\nF C F\n\nF B C\ng d\nF B F\ng C F",
        "D A h D/Fis G6 A4-3\ne fis G Asus4",
        "C F G7 a\nD7 G7 C\n\nC d G G7\nC d G",
        "D h G A\n\nh G h\nG h G h\nh G h\nG h G A7",
        "C F C\nF C G C\nC F C\nF C G C\nF C F C\nF C G C",
        "A E\ncis7 H\nA E\ncis7 H\nfis7 E H\nfis7 cis7 H Gis\n\ncis7 A E\nfis7 cis7 H Gis\ncis7 A E\nfis7 E H (Gis)",
        "D A\nh G A\nD A h G A\nD A h (G A)\nG e A",
        "D G A D G A\nD G A\nG h G A\n\nFis h G A\nFis h G A",
        "e a\nD H7\n\na H7\na H7\nC H7",
        "F B2 d7\na4 B\ng g7 C2 g7 C2\n\ng F\nC F\ng d\nC F",
        "g C B C\ng C B C\n\ng C\nB C\nC a B\ng C",
        "C G a e\nF C d F G G7",
        "e h\ne C D\ne h\nC D\n\nC D\nC D\nC D\nC H7",
        "e h\nC D\nG C\n1|a C H7 2|a C H7 e\n\nG h e\na H\nG E a\ne C H",
        "e h e D e\nC D e\ne h e D e\nC D e\ne D G D e\ne D G D C",
        "D h\ne A\nD h\ne A\n\nD h\nG e A",
        "e h7\ne D\nG D h7 e\n\ne h7 e D e\nD G h7 e",
        "D A D\nG D\nA h A\nG D A D\nG A h\nG A h\nA G A",
        "D G D\nh G h A\nD\n\nh G h G\nh G h G\nD G h A\nh",
        "D A Fis7 h7\ne7 G A4-3\nD A Fis7 h7\ne7 A7 D A",
        "D A D\nD G A\n\nG D\nG D\nG D A",
        "a G E\nF G a\nF G a",
        "h e A D\nG D\ne h",
        "E A H E\nE A H E\nE A H E\nE A D H\nE cis H\nA H\nE cis H\nA H\n\nG e7 D\nC D\nE cis H\nA H cis H\nA H cis",
        "C G\ne F\nC G\nF G\nF G C\nF G e\nF G a\nF G",
        "E A H A cis7\nfis7 H\nE A H A cis\nfis H E\n\nA7 E9 fis7 cis7\nD7 H",
        "A D A\nD E A\nA\nD A\nD E A\nD E A",
        "E A E\nE A E\n\ncis gis\nA E\nA E H7\n\nE A E\nE A E\n\ncis gis\nA E\nA E\nH7",
        "D G D G\nD G D G\nfis h\ne C A",
        "A\ne\nD A\n\nG D A\nG D A\nG D A\ne D E",
        "fis\nh E\nh D fis E\n\nfis D\nh D E\nfis D\nh D E",
        "G D A2/Cis h\nG D Asus4 A\nG h G A h\nG Asus4 D\n\nG D A h\nG D A h\nG D A h\nG D Asus4 A D",
        "C G\na7 F G\nC G\na7 F G\n\na7 e\nF G\na e\nF G",
        "D h G D\nD h G\nD A7 D",
        "a e F C\na D d7 1|E 2|a\n\na\nG C\nF a G\nC\nF a\nD d7 E",
        "C\nG C\nG\nG C\nC D4-3 G",
        "A E H A E H\nA E H A\n\nE cis\nA H4\nE cis A H H7",
        "D e\nA G D\nD\ne\nA G A D\nD e\nA G A D",
        "C F C F G\nC F G7\nF C F C\nF B G7\n\nC F G C",
        "C F C\nC G G7\nC C7 F C\nC G C\n\nC\nG\nF C\nC G C",
        "C d a E",
        "D C H7\ne A\ne A D H\ne C A\nD C H7\ne A\ne A D H\ne C F D7\n\nG h C D7\nG D C D7\nG G7 C A\nG D G D\ne a D G",
        "a G e a\n\n\nC G e a\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nC G e a\n\nd E d E\n\n\n\n\nF E\nC G C",
        "C G\nF G",
        "cis fis\na6 (H7) E cis\nA Gis\nfis H7\n\nE cis\nfis H7\nE cis\nfis H7 E",
        "C e F G e\nA g C e d G G7\nC e F G e\na G C e d G7 (C)\ne F C F d G C\ne F C F d G C",
        "G h C D\nG h C D\nG a D G\nC D G\n\nG h C D G h C D\nG a D G C D G",
        "D fis G A\nD fis G A\nG A D fis h\nG A D D7\nG A D D7\nG A D",
        "C d\nG C\na d\nD D7 G7\n\nC d\nG7 C\nC7 F\nC G7 C",
        "C e a\nF G\nC e a\nF G\nF C\nF C\nF C a\nF G C",
        "e D\nC e\ne D\nC e\n\nC D G C\nD G\nC D G C\nD e",
        "C e F\nC e a\nC e\nF B G\nC e F\nC e a\nC e F B G\nF G e a\nd7 G C (C7)",
        "e C a H7\n\n\n\n\ne C\na H7",
        "F a\nF a\nG F\n\nd a F G a (F)",
        "a E\nE7 a\nA7 d a\nE a\na E E7 a\n\n\n\n\n\n\n\n\n\n\n\na E a",
        "a C F C\nd C\nd E\na C\nF C\nd C\nd E\n\nF C E a\n\n\n\n\n\n\n\n\n\n\n\nF C E a\nF C E a",
        "d g C\nA F\nB g A\n\ng d C F C\ng d\nC F\nC g\nd\nC F C A\nd\n\nB F\nB F\ng\nA\nd",
        "E H A E A E",
        "D G\n\n\n\n\n\n\nD C\nG D\n\n\nG C2 G\nG C2 G\nG C2 G\nG C2 G (D)",
        "E cis7 gis A H7 E\nE cis7 gis A H7 E\nE A E H7\nE cis H7 E",
        "G C G D\nG C G D G\nD G\nD G\nG G7 C c\nG D G",
        "G D e\nC G C D\nG D e\nC G C D\nF C D\n\nG D C D /x3\nG",
        "a d G C\na C\nF C\nd a\nE E7\n\na d\nG C E\na d\na E a",
        "d C g A\nd F A\nd g A d\ng A d\n\nd a B\nF C\nd a B\nF A",
        "a G a\nG a\nG D e a\nC D e",
        "E a E\nE d E\na\na G d\nE a",
        "E A E",
        "E H7 E A H7\nA H7 E A H7 E\nA H7 E H7 cis\nfis H7 E",
        "C G a\nF a\nG C G a\nF G C\n\na7 G a7 F C\nF G a7 G C G C",
        "E D\nA H E\nE H A\nE H A\nE H\nfis H E\n\nE D\nA H E\nE D\nA H E\nE H A\nE H A\nE H\nfis H E",
        "e e7 A a C D e\nC D D7 C e\n\ne A\nD e7 D\na C\nC a C e",
        "d C a d\n\n\n\n\nd C a d\nd C a d\nd C a d\nd C a d\nd g C d\nd g C d\nF g C d\nF g C d C d",
        "d\nd g A\ng A d A A7 d A d",
        "D\nh\ne\nD A\nD (A)\nh\ne\nD A h\n\nD\nD A h\nD\nD A h\ne\ne\ne D\ne D\nA A7",
        "e D e\nC D h e\ne D e\nC D h e\n\ne D e\ne H7\ne D G a\nG D h e",
        "a F E a\n\n\n\n\n\n\n\n\na F G C\na F E a",
        "e\na e H7 e\ne a e D e H7 e\ne a e D a e H7 e",
        "e D a7 h",
        "A cis D A D E\nfis cis D A D E A",
        "D A D\nG D e D A7\nD A D\nD G A D",
        "G C D\nG C D\ne h\ne h\nfis cis\nC D (D7)",
        "e D G C H7\ne D G C H7\ne D e\ne D e\n\ne C H7\ne a C H7\ne G H7 C\nC H7 e",
        "D G A\n\n\nG A D",
        "D h G A\nD h\nG A\nD h G A\nD h\nG A\nD h G A",
        "a e\na e\nG a\n\n\n\n\nF G\nF G a",
        "a d d7\nG G7 C E\na d d7\na\nd E F\nd a E E7 (a)",
        "G C\nD7 G\nh7 C\nD D7 G\nH7 e\nH7 e\nF C\nD\n\nC D G\nH7 e\nF C\n1|D D7\n2|a D G",
        "A E fis A\nh E A2\nA E fis A\nD E A\nfis D A\nA E fis A\nD E A",
        "D G h A\nD G h A\ne fis e fis\ne fis G fis",
        "D h G A\n\nD e A\nD h G A\nD e A\n\nG\nA\nG\nA\n\nD h G\nD e A\n\nh G\nh G\nh G\nh\nG A",
        "C e\nF C\nF C G7",
        "C a\nF C G\nC a\nF C G\n\nF a\nF C G",
        "G e\nC D\nG e\nC D\n\nG (D)\ne (D)\nC D\n\n\n\n\n\n\n\nG\ne\nC D",
        "C G a\ne F f C G",
        "d B C a\nB F g A7",
        "a d a F G\na d F E\nC G a E\na d E a",
        "C D G e\nC D G e\nC D G e\nC D e D e\n\nC D G e\nC D G e\nC D G e\nC D e C e",
        "G C\nD G D G\nG C\nD G D G\n\ne C\nG D\ne C\nD G D G",
        "d g C d\nd C\na d",
        "a C D F G a (C D)\na C D F G a\n\nF G C D\nF G E7\nF G C D\na C D",
        "a e F G C\na e F G a\n\na e F G C\na e F G a",
        "fis h7 E A\n\n\nD E/x2",
        "d g d\nC a d\nd g d\nC a d\n\nF g A d\nC g A\nF g A d\nC g A",
        "h fis\nG D A",
        "e a (H7)\ne\ne a\ne\ne a\nH7 e\n\ne\na e\ne H7\ne",
        "G D e G7\nC G a D7\nG D e G7\nC G a D7\n\nG D e G7\nC D\nG D7\nG H7 e\nC G D C G",
        "C F C E7\na G\nF C E7\na G C\n\na E\na E\nF C\na G\na E\na E\nF C\na G",
        "E H fis A\nE H A\nE H fis A\nE H A\n\nE H\nE A\nE H\nE A\n\n\n\n\n\n\nH E A H\nH E A H",
        "E cis\nA fis A H\nE cis\nA H E\n\nH A E\nH A E\ngis cis\ngis cis\nfis A H",
        "h A\nE\nh A\nE\nG A E\nG A C Fis\nh e7 Fis\nD G A h\nh A\nA\nG Fis h G fis h",
        "D A h G\nD\nA\nh G",
        "cis H E\ncis H E\ncis H E\nfis A\ncis H E\ncis H E\ncis H E\nfis A\n\nE fis\nA E fis\nA\nE fis\nA fis\nE A",
        "D A\nh A fis\nG D\ne A D D7\n\nG D\nG D 1|D7 2|G",
        "E A H\nA\nE A H7 A\nE A H\nA\nE A H A\n\nE fis gis A\n\n\n\nE A H A",
        "G D G D\nG D h A\n\ne H e\nG a H\nE H E H\nG A H",
        "D G D\nG D\nG A D fis h\ne G e A\n\nD\nA\nG D A\nD\nA\nG D e A D",
        "E H cis A H\nE H cis A\nfis cis H\nfis cis H\nfis cis H\nfis cis H E",
        "e a\nH7 e C D\nG a\nD G H7 e",
        "C F G C\na F D G\nC F E a\nC G C\n\nF G C F G C\nE a\nF G",
        "E cis A H",
        "G A h\nG A D\nG A h\nG A\n\nG A D\nG A D\nG A h\nG A h\nG A D",
        "D h e A\n\nD h e A D A",
        "fis D\nA E\nfis D A E",
        "D A h fis\nG D/Fis e2 A\nD A h fis\nG D/Fis A D",
        "E cis A E\ncis A gis cis\nA fis H7\nE gis cis\nA H E cis\nA H cis\nA H E",
        "G D e C G a7 D\nG D C a C D G\nh D e C G D\nC D G C G",
        "d A7 B D D7\ng7 C7 F e7 A7",
        "G D e\nC D G\nG D e\nC D G (G7)",
        "e D e h e D G D\ne D e h e h D e",
        "a7 D a7\nF G C E\na7 D a7\nF G a7\n\nF G C\nF G a7",
        "a d G C\nF G C d E\na d G C\nF d a E7 a",
        "D A G A\nD A G A\nD A G A\nD A G D\nG A D G A D\nG fis h G\nA",
        "e h e\nC D G\na H7 e\nC D e\n\nG D C e\nC D G C\nG D H7 e\nD G H7 e\n\nG D C e\nC D G C\nG D H7 e\nD G H7 e",
        "e7 D h e7\na e D h e",
        "G\n\nF g\n\n\n\ng B C",
        "D h G A",
        "D G A\nh e A",
        "D G D A D\nD G A D G A\nD G A D\nD G A\nF C G D F C G A\nD G D A D",
        "D A\nh A",
        "d C a7 d\n\n\n\n\nF C a d",
        "A fis\nE D\nA fis\nE D\n\n\nA E\nfis D\n\n\n\n\n\n\nA E\nfis D fis\nE D",
        "D A D fis H7 e A\nD D7 G g D A D",
        "A H E\nA H E cis\nA H E cis\nA H E cis\nA H E",
        "D fis h D G\nA D",
        "G C\nG C\nG e a D\nG C\nG C\nG e a D\nG e a D",
        "G D a e G D a\n\nC G D\nC G e\nC G D\nC G e D",
        "d g\nC F A\nd g\nd A7 d\nd g\nC F A\nd g\nd A7 d",
        "D D7+ D7 D6\ne7 Asus2\nD D7+ D7 D6\ne7 Asus2 D D7\nG e7 fis h7\ne7 Asus2 D D7\nG e7 fis h7\ne7 Asus2 D",
        "E gis7 A E gis7 A\nE gis A E H E\nE gis7 A E gis7 A\nE gis A E H7 E\nA E\nA E\nA cis7\nA H7",
        "G D\nG D\na e\nG D H7\n\ne C a e D\na e G D (H7)",
        "e G D\ne G A7\ne G D h7\ne A D\n\nh e h e\nG A D",
        "a d7 G C7+\nF d6 E4 a\na d7 G C7+\nF d6 E a",
        "C F G\nF d G (C)",
        "D h A G",
        "D h\nG A7\nfis h\ne A7",
        "D fis7\nG e D/Fis G\nD fis7\nG e D/Fis G\nD G A\nD G A",
        "D G D G\nD A D A\nD G D G\nD A D",
        "D A h\nG D G A\nD A h G\nD A\n\nD A e h\nG D G 1|A 2|A h",
        "E A E D /x2 A\nE A E D\n1|E A E D A 2|E A E\ngis A H\ncis A\ncis A\ngis A\nFis A H",
        "D e D e\nD h e A\nD h e A D h\n\nG A D h\nG A D h\nG A D h\nG A D",
        "C G a G\n\nC G a G\n\nF C G\n\nF C G\nF C G\nF a G\nC G\na F G\nC G\na F G C",
        "C G F G /x2\nd F C G /x2",
        "A E0 h E\nA fis G E\nA A7 D\nh h7 E E7\n\nA cis\nD A\nfis H7\nfis\nH7 E E7",
        "D A\ne G D\nD e A\nh G A D",
        "A D E\nA D E\nfis D E\nD d A E\n\nfis D E fis D E\nfis D E D\nd",
        "h G A h",
        "e a D C H7\ne a H7\nH7\n\ne a\nD C H7\ne a D C H7\ne a\nD C H7\nH7\n\ne a D C H7\ne a\nD C H7\ne a\nD C H7\nH7\nH7\n\ne a\nD C H7\ne a\nD C H7\n\ne a\nH7 e H7\ne a\nH7\n\ne a\nH7 C H7\ne a\nH7",
        "C G C\nd G7 C\nC G C\nd G7 C\na F\ne G\na F\ne G\nF G C\nd G C",
        "a h e7",
        "d a\nF C\nd a d\nd a\nF C\nd a d (D D7)\n\ng6 C\nF d\ng6 A7\nd (D D7)",
        "D h7\nG A\nfis h7\ne A (D)",
        "A\nG D A",
        "E H A fis H\nE H A fis H\ngis cis\nA H\ngis cis\nA H\ncis gis A H\ncis gis A H\ncis gis A H\ncis gis A H",
        "e H7 e\ne H7",
        "F C d A F\nF B F C F\nF C d A d g A\nF B F C F\nF B F C7 F\nF B F C7 F\nF d A d A\nd C F C F\nF B F C F\nF B F C7 F\nF B F C7 F",
        "h fis\ne fis h\nh fis\ne fis h A\nD\nA\ne h7 A\nh",
        "h e7 Fis h\nh e7 Fis h\ne A h A7 D\nh e7 Fis h",
        "h A D fis cis\nh D cis fis\nD A Fis h e Fis\nh e Fis h\nD A Fis h e Fis\nh e Fis h",
        "fis h7 E A\nD h6 Cis fis\nfis h7 E A\nD h6 Cis fis",
        "A cis\nD h E\n\nA e C D\nG A\nA e C D\nfis E",
        "e A D H e\na D 1|H 2|a e",
        "d a g A4-3\nd a g A4-3\nF C B g C4-3\nF C B g A4-3",
        "E fis cis7\nA H\nE fis cis7\nA H",
        "d C F C F\nC d\n\nd C F C\nd C d",
        "e C G\nG D\ne C G\nG D\n\nC e D\nC D\nC e D\na7 D",
        "D7+ E fis\nD7+ E fis\nD7+ E fis\nD7+ E fis\n\nh fis A E\nh fis A E\nh fis A E\nD E fis",
        "e H e D\nG D e C a6 H\ne H e D\nG D e a H7 e",
        "C F C G a\nE a G C F G\nC F C G a\na F G C",
        "D\nh\nG\nA",
        "G a\nD e\nC D\nC a D D7\n\nG a\nD e\nC D\nC a D D7",
        "G D\nC G\ne h\nC D\nG D\nC G\ne h\nC D D7\n\nG D\nC G\ne h\nC D\nG D\nC G\ne C\nD",
        "e D e D e D\ne D e D e D e\n\n\n\n\ne D e e D e",
        "e\na e\ne\na\ne\ne\ne\na e\na C F d E",
        "E H cis\nA Fis H4-3\nE H7 cis E7\nA Fis E H\ngis Cis fis a E H E\n\ne a e\ne D e\ne H7 C\na Fis7 H7",
        "G h e C\nD G e C a\nC G\n\nG e\nh C D e\nC D e\n\n\n\n\n\n\n\n\n\n\n\n\n\nG e\nh C D e\nC D e",
        "d C\nd\nA7",
        "C a F G\nC a F G\nE a\nF G\nC a\nF G C a\nF G C",
        "e C G D7\nC a H7\ne a D7 G H7\ne C G D7\nC a H7\ne a H7 e",
        "D G A7 D",
        "A D A D\nA E\nA D A D\n1|A E A\n2|A E a\na d a d\na d a E A",
        "h e\nD e7 Fis7\n\nh e h A G A h\nh e h A G A h\nh e\ne h G A h",
        "D A h\nG D A\nfis h\nG D A",
        "g\ng D\nD\nD g F B\nc D\n\ng F\nEs D\ng F\nB D",
        "E gis\ncis A H\nE\ngis\ncis A H\n\nE\ngis\ncis A H",
        "a E7 a A7 d\nG G7 C (d G F C)\nE7 a A7\nd G C E7\nF C\nd E",
        "G h7 C D G\na D G E\nC A7 D D7\n\nG C G\nG H7 E\na D G E\na D G",
        "e D\nC H7 e\ne D\nC H7 e\n\ne\nC\nD\nG H",
        "h e\nA A7\nD Fis Fis7\nh e\nA A7\nD Fis\ne A\nD h\ne E7 A A7\n\nD e\nE7 A\nD G\nD A7 D",
        "e A7 D h\ne A7 D D7\ne A7 D h\ne A7 D\n\nD G A7 D\nG D G A7\nD G Fis h\nG D A7 D",
        "G C9 G\nG C D\nC D e\nC D G\n\n\n\n\n\n\nG C9 G\nC D G\nC D e C\nG C9 G",
        "E H\nH7 E\n\nA E\nfis H7 E E7\nA E\nfis H7 E",
        "d B\nC d\nd F C\nB C d C a d\n\nF d\nB C d\nF d\ng A7\nd B\ng B C",
        "e C D e C D e\ne C D e\nC D e\ne D h e C D e",
        "D h fis G\nD fis G D\ne D\ne D\nfis G D",
        "D G /x2\ne A\ne A\nD h G A",
        "cis H E A\ncis H E A\n\nA H E\nA H cis\nfis EA",
        "e h C D\nG a D\na e a D\ne C G",
        "E\nE fis\nE\nE fis\n\nE\nE fis E\nE fis\nfis E\n\nE\nE fis\nfis E\n\n\n\n\nE fis\nE fis",
        "C d G C e a\nd G C C7\nd G C e a\nd G C",
        "C D G\nC D G\nC D/x3\nG\nC D\nG e\nC D G\nC D G\ne\nC D C D\nC D G\nC D C D\nC D G\nC D\nG e\nC D /x3\nG e\nC D/x3 G",
        "fis h fis\nD E A\nh Cis fis\nh Cis fis\n\nfis cis\nh Cis\nfis cis\nh7 D fis\n\nE fis\nE fis\nD Cis\nD Cis\n\nE fis\nE fis\nD Cis\nD Cis\n\nE fis\nE fis\nD Cis\nD Cis",
        "C B a7 d F\ng d C2 B\n\nB7+ C/B\nB C/B B\nB7+ C/B\nB C/B B",
        "D G\nD G\ne A\ne A\n\nD G\nD G\ne A\nD G A7 D\n\nD G\nD G\nA G\nD G A7 D",
        "d A d C F C d A7 d",
        "a C G\nh A\n\n\n\n\n\nG a e\nG a e\n\n\n\n\n\n\n\na e\nG D",
        "D D4 D D4\nD D4 G H7\ne g A\nB A D G A\nD D4 D D4\nD D4 G H7\ne A fis H7\ne A D D7\n\nG H e\na a7 D\nC D h e\nC D G G7\nC D h e\nC D G\na (D7) D7 (G)",
        "e C D G\ne C D e",
        "G h\nC c\nG h a D G D\nG h\nC c\nG h a D G\nD\n\nG h G C c\nG D\nG h G C c\nG a D G\n\n\n\n\n\n\n\n\n\n\n\n\n\nC c G",
        "D h e A",
        "A E\nfis cis D\nD A D A\nD G h E\nA E\nfis cis D\nD A D A\nD G h E\n\nA fis D E\n\n\nE E7\nA fis D E",
        "E A H7",
        "d\nA7\nd\nd\nA7\nd\n\nd\nD7 g\nA7\nd\nd\nD7 g\nA7\nd",
        "d C B A7\nd g A7\ng C B A7\ng A7 d\n\ng A7 B A7\ng A7\ng A7 B A7\ng A7",
        "D C\nG D\n\nC G D\n\nD C\nG D",
        "D A7 D G\nD A7 1|D 2|D D7\nG D A7 D\nE A A7",
        "D A e h",
        "h A G7+ A\nG7+ A G A\n\n\n\nh A G h\nA G h\nh A G h A",
        "D D7+ G7+\nD D7+ G\n\n\n\n\n\n\n\n\nD\nD7+ G G7+\nD\nD7+ G\n\nD D7+ G7+\nD D7+ G",
        "e D h C a h e\ne D h C a h e\n\na7 h e",
        "C a d\nG C\nF C a\n1|d G C C7\n2|d G C",
        "D\nG A\nD\nG A\nD\nG A\nD\nG Fis\n\nh G\nD A",
        "e\na e\nG a\na e",
        "C G a e\nF C G G7\nC G a e\nF C G C\n\nC G C\nG a\nF G C a\nF G F G G7",
        "C D e7\nC D e7\n\na C\nH7\na C\nH7",
        "E H7 A E",
        "G C9 G\nG C9 G e\nC D G e\nC D G\n\nG C9 G\nG C9 G e\nC D G e\nC D G (G7)\n\nC D\nG e\nC D G",
        "e\ne\nD\nC H7\n\ne H7\nC H7\ne H7\nC H7\n\ne H7\nC H7\ne H7\nC H7\nC H7\ne H7\ne H7\nC H7",
        "e\na D e\na D7 e\nG\nH7\nC D A\n\ne\nC\na\nH7",
        "d B g A d B g C d\n\nd B\ng d\nF C F\nd A\nd C d",
        "e D\ne D\nC D e\n\nC D e",
        "e D G\na H7",
        "cis A E\ncis A E\nfis H\ncis A E\ncis A E\nfis H\n\ncis A\nE H\ncis A\nE H\n\n\n\n\n\n\ncis\nA\nE\nH",
        "A E H\ncis A\nE\nH cis",
        "A E\nfis D E\nA E\nfis D E\nA E fis D E",
        "d\nd\nF C A d\nd\nd\nF C A d\n\nd C g A\nd C g A\nd g A d\ng A d",
        "C G a7 F G\nC G a F G",
        "d B\nC F A7\nd B C F A7",
        "e C D\nC D e\n\nG h\nC D\nG h\nC D G\ne h e h\nC D G e",
        "d a\n\n\nF d a d",
        "e a7 D H7\ne D e a e",
        "G e\nC D h e\nC D h e\nC D D7\nC D h e\nC D h e\nC D h e\nC a D (D7)",
        "e C G H7\n\n\n\n\nC\nG\nC\nH7",
        "C F G /x2\na7 e F d G\nF G\nF G C\n\na7 e F C\nF d G",
        "e h C G\na H\n\nd E\na\nF G\nC\na\nG\nC\nH",
        "e D\na e D\ne D\na e\n\nD e D e\nC H7\nC H7",
        "D G A D G A\nG A D h\nG A D",
        "C G C G\nC G F C G\nC G C G\nG C G F C G\na G F\na G a\na G F a",
        "a E a C G C\nF G C E7 a E7 a E a",
        "D e\nG D\nD D7 G g\nD e A D",
        "C G a F",
        "D E A fis\nD E A A7\nD E A fis\nD E A\n\nD E A cis fis\nD E A cis fis\nD E Cis fis D\nA h E A",
        "G6 D/Fis\ne7 C2",
        "E H7\nA fis E H7\nGis cis\nA H7 E\n\nE A\nE H7\nE H7 E",
        "A A7 fis\nD E A A7\nD h cis fis\nh E7 A\n\nA A7\nG Fis h\nd A fis\nfis h E7 A",
        "e D H7\ne D H7\nC G a H7\na C a H7\n\nG D e\na e\nG D e\na e\na D e\na C H7",
        "a F\nG C E\na F\nG C E\n\na d\nG C E\na7 F\nG C E",
        "e H7\ne H7\ne H7\nC H7\ne H7\ne H7\ne H7\na H7\n\na H7\n\n\n\n\n\n\n\n\n\n\n\n\n\n\ne H7",
        "D e A7 D\nh e G A\nfis h e A7 D\nh C a7 E7 A7\nD A e h a H\ne A A7\nD A e h a H\ne A D",
        "d a g a\nd a g C\nF G F C\nd a B a d\n\nd a g a\nd a g C\nF G F C\nd a g a d",
        "C G C\ne F G\nF G C a\n1|d G C C7\n2|d G C",
        "A D\nfis E\nA D\nfis E\n\nD A\nfis E\nD A\nfis E",
        "C G a\nF f C G\nC G a\nF f C C7\n\nF G C C7\nF G C C7\nF G C e a\nd7 G C",
        "d C7 F B F d C\nd g C B C7\nF C B F C\nA d g C7 F",
        "C F C G\nC F D7 G\n\na d G\na G a E\na E a\nE a G C\na C E",
        "E A\nE H E E7\nA a E H7 E",
        "d g d\nd g d D7\ng A7 d A7 d",
        "D G A\nD G A\nFis h G D\nG C A\n\nD A G A\nD A G A\nD Fis h G\nD C A",
        "H7 e a C H7 e\nH7 e\nH7 a H7 e",
        "C G a F G\nF C d G (C)",
        "a E a C\nF G\na E a G a",
        "C\nG C\nF C\nG C\n\nF\ne\nd\nG",
        "E\ncis A\nfis7 cis\nfis H\n\n\n\n\n\nE\n\nA H\nfis7\ncis\nA cis H",
        "a e C D E\na7 D2 E\nC D e h C\nC D h E",
        "D G D G A\nfis h fis h\nG A D",
        "d\ng\nB A\nA d (B A)\n\nB F\nB F\nB A d",
        "e C\na h\ne C\na h\n\ne\nC h",
        "G a7\nd7 F G\nG a7\nd7 F G\n\nC\n\na7 F\na7 F G a7\nF d7 F\na7 F\na7 F G a7\nF\nd7\nF G",
        "d C F C d\nd C F\nF C d\nC F C d\n\nd g B\nB a d",
        "a C d a\na C E\n\nC\na\nC a\nE F G C E\n\nC\nE\nF\nE",
        "e\nC\nh\nC H7",
        "d a d a\nd a d a",
        "H7 e\nH7 C\nH7 C\nH7 e",
        "E D E D\nE D E\n\nE D",
        "D G h\nA G\n\nh G\nD A\nh G\nD A\nh G\nD A\nh G\nD A",
        "C G\n\n\n\nD F C G\nD F C G",
        "D G D G D h A\nFis h A D G D A D",
        "e H7 C e\nD H7 e a e\ne H7 C e\nD H7 e a e\n\ne a G A H7\ne\nC e H7 e",
        "E H A H\nE A E H E\n\nE H fis cis\nD A H H7\nE H fis cis\nD A H H7",
        "h D e fis h\nD A G D h e Fis7\n\nG A D h e fis h",
        "D A D\ne A D\nD A D\ne A D D7\n\nG D\nh A D",
        "D A h\nG A4-3\n\nD h47 G A\n\nh A D e Fis\nh A D e7 A\nh Asus4 D G A\nD G Asus4 G A\n\nD h e A (Fis)\nh G\nDsus2 A\nh G D A\nG Dsus2 h 1|A 2|A4-3\n\nD h A\nD G A\nFis h A\nD h G A",
        "G D a e",
        "C G F G\nC G F G\nC G F G C\nC G F G\nC G F\nG C",
        "e a7 D e\ne a7 D e\na7 C D\ne a7 D e\n\nG e a7 D e\ne a7 D e\n\n\n\n\n\n\n\n\n\n\n\ne a7 D e\ne a7 D e\na7 C D\ne a7 D e",
        "h7 E7 h7 E7\n\n\n\ne7 h e7 h\ne7 h D\nE7 Fis7\nh7 E9 h7 E9",
        "D fis\nG A\nh fis\nG D\nh fis\nG D",
        "d d7 g C\nF B g A7\nd d7 g C\nF g A7 d",
        "G C D\nG C D\nG C D\nG C D\nG C D",
        "a E\na C E\na E a E a",
        "fis D E fis\nh E fis",
        "a d\nG C\nF d\nE a",
        "G D C\nD\n\n\n\nG D C D\n\n\nG D C D G",
        "fis D\nE fis\nA E h fis\nA E h fis\nfis h fis h\n\nfis h fis h\nfis h E\nE D\nD E",
        "a\nF a\nF\na\nF\n1|a 2|a G\n\nC G a G C G F G",
        "h A2/Cis D\nA G\n\n\n\nG D A\nG D A\nG\nD A\nG\nA h (A2/Cis D A G)\n\n\n\n\n\n\n\nh | G | h A2/Cis\nG | D | D\nD | A | A\nA | e | e\nh | G | h A2/Cis\nG | D | D\nD | A | A\nA | e | e\nx2|x1|x2",
        "e D h e\na D G D\nG C\nD h\nC D e\nC D e\n\ne C D h\nC D e\nC D e\nC D e",
        "h e G D A\nFis G\nD A\nFis G\nD A\nFis\n\nh A G Fis\nD A G Fis\nh A G Fis\nh A h",
        "A H7 E\nA H7 cis7\nA H7 E\nA H7 Cis7\n\nA H7 E\nA H7 cis\nA H7 E\nA H7 E",
        "D A h G\nA\nD A h\nG e A\n\nD G A D G A\nD A D G A\nD A\nD G D A\nG A D",
        "A h G e\nA h G e\nA h G e",
        "D A\ne h A\nD A\ne h A",
        "a\nG a\nd\na\nF\nF a\nd\na\nd\nF G a\nF G a",
        "A E fis\nD A E\nA E fis\nD A E\n\nfis D E\nfis D E\nfis D E\nfis D E",
        "e a\nD H7\ne C\nD H7 e\n\na e\nH7 e",
        "C G a F C\nG C\n\n\n\na F C\na F C\nd E a G\nF C G C",
        "C G a\nC F C G G7\nC G a\nC F G C",
        "D F G\nD F G\n\nC G/x3 D\nC G/x2 D",
        "E fis H E",
        "D h\ne\nG D A h\ne\nG D",
        "a d E7 a\nG C\nd E7 a\nE H7 E7\n\na G d G C a7\nd F d E7 a",
        "C\ne a\nF C\nd G G7\nC\ne a7\nF C\nd G G7\nF G\ne a7\nF C\nd G G7\nF G\nE a\nF C\nd G G7\n\nF G\nE a\nF C\nd G G7",
        "C B C\nC B C\n\nF\nd C\nB g C",
        "C G C\nC F G\nF G e a\nC G7 C (C7)",
        "D A G A\nG h A\nG A Fis h\nG A",
        "E cis\nA E H\nA H E\nA H E E7\nH E Cis fis\nD H H7",
        "H7 e D\nC a7 H7\nG D\nC a7 H7",
        "E cis\nA H E",
        "a e a d E a\nG e a\n\nG\na\nF C\nd E a",
        "D h\nG A\nG A G A\n\nG A h\nG A G A\n\nG A D",
        "d\nF\nC B\nA",
        "G C D G\nD G e A h C D G\n\nG C D\nD G\ne A\nA D\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nG C D\nD G\ne A\nA D",
        "D\nA\nA7\nD D7\nG A D\nG A D\nD h A A7 D",
        "d g A\nd B A\nd g A\nB g A\n\nd C F C\nd C F C\ng A\nd C B\ng A",
        "C G F C G F C\nC G F C\nC G F G C",
        "D A h\nA D E A\nD G D e A D",
        "C D G D C D e\nC D G A G D\nG C Dsus2\nG C Dsus2\nG D C G D D7 G\n\ne C D e\nC e D e\nG D a C\nC D H7 e\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\ne C D e\nC e D e\nG D a C\nC D H7 e",
        "d C d a\nF C d\na F G d\nG d",
        "e A D h\ne A7 D D7\ne A7 D h\ne A7 G D",
        "D\nfis\nG A D\n\nA h\nG A\nA h\ne G A /x2\nD",
        "C a7 G\nC a7 G\nF C\nF C\nF C\na G",
        "fis h E cis\nfis h E\nfis D E cis\nfis E\nA fis h E\nA fis h E A",
        "e G\nD e D\ne G\nD e D\n\ne\nG\nD\ne D\n\n\n\n\n\n\n\n\n\n\n\ne G\nD e D\ne G\nD e D",
        "h G e Fis7\nh A D e Fis7 h",
        "D h\ne A\nD h\ne A D",
        "h A D G A D A\nG Fis h\nA D G A\nfis h G A D",
        "a E a F G C E\nF G C E a\nd a E a",
        "d g B C d\n\n\nd g\nB C A7 d\n\nF C g d\nF C g d\nF C G d F C G G7 d",
        "e H7 G D\nG e C H7 e G D\nG e C H7 a7 H7 e",
        "D G\nh A",
        "A cis D E fis D E\nA fis A D h E A\n\nh7 fis7 h fis\nh D fis7 E A",
        "d g\nC F\nd A7\nd\n\nd g C F\nd g A d\nd g C F\nA d",
        "E\ncis7 A\nH\nE cis7\nA H A H Cis\nA H E cis (H)\nA H E gis A E\n1|fis A 2|A H\nE",
        "C G a\nd C G\nC G a\nd C G\n\nF e F\nC G a\nF e F\nC G",
        "a e d a e a\na e d a e a\n\na d C e a\nd C e a",
        "A h E\nfis D E\nA cis D A\nh E A\n\nA h E\nh E H E\nA E cis\nE A E",
        "g B d\ng A7 d",
        "e C\nD e",
        "e D\nC H7",
        "e C H7 e\nC H7\nC H7\ne C H7 e\nC H7\nC H7\ne",
        "a G/H C G/H a\nF7+ D/Fis G\nE7 a e a\nF7+ d7 1|E7\n2|G7 C\nC e d7 G C\na e F2 G4",
        "C e\nF G\nC G\nC e\nF G C",
        "D G\nD G\nD e7 A\nD G D fis h\ne A\ne A\ne A A7 D",
        "A cis7\nD E\n\n\n\nfis E D A\nD E A\nfis E D A\n1|D E fis\n2|D E A",
        "D a7\nG9^6 D\n\nC2 e7 D2/Fis (h7)",
        "d C",
        "e D\na D e\ne D\na D\ne\n\ne\nC\nD\ne D",
        "D A7 D\nD A7 D\nD H7 e\nA7 D\nD A7 D",
        "C F7+\nC F7+\nC F7+\nC G\nF C",
        "G A7\nD h\nG A D D7\nD fis G A D",
        "C F C\nB C\nC F a\nC d a",
        "E\ncis\nA H\nE",
        "d C\na\n1|d\n2|d C\nF C\ng",
        "e D h C\nD G D e\n\ne D G h D G D\ne D G h\ne D e\ne D G\ne D e",
        "a D\nF\nE\n\na e\nF E\na G\nF E",
        "h A G D\nG D G D A\nh A G D\nG D G D A",
        "a E (F G C E)\na E (F G C E)\na e a e\nF d E4-3 a",
        "D G A D G A h\nG A D",
        "a7 F\nG a a7\nF G a7\n\nE a\nd a d a\nE a\nd a d a\nE a\nd F E",
        "d a F C\nd a d\n\nF C a\nd a d",
        "G C G D\nG C G D\nC D h e\nC D h e\nC D C D\n\nG D e a D\nG D e a D\nG D e a D\nG D e a D G",
        "e a7\nD G H7\ne a7\nC H7 C H7",
        "D A h\nfis G\nD A",
        "d C F\nC F A7 d\nd C F C B C d",
        "G e C D\n\n\nG e C D G\n\nG D C D G\nG D C D\nG D C D G\nG D C D G C G",
        "d a d a\nC G d a",
        "G D\ne7 C\n\n\n\nD\ne\nD C",
        "d g C F B g A d",
        "D G A D D4 G D\n\nh e A h D A D",
        "e H7 e h\nG a H7\ne h\n\na D e\na H7\nC a H7\na D\nC a H7",
        "A h A D\nh e D A\nFis h A D\nh G A D",
        "d B F C\ng d\n\nB g d\nC g d",
        "h G D  G A h\nG D A  G A h\n\n\n\nG D\nh A\nG D\nh A h",
        "F G C a\nF G C a\nF G C a\nF G a\n\na d a\nG e a\na d a\nG e a\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\na d a\nG e a\na d a\nG e a",
        "d C a (a7)\nF C a\nd B C a d G\nd B C a d",
        "d F g A\nd F g A\nd F G A\nd F A D\n\nD G\nA D\nG D\ne A\ng D\n1|e A D7\n2|e A G D",
        "d B\nC a d\n\nd C\na d\nd B\na d",
        "e D\ne D G\nG D e\n\nC\na G\nD e",
        "e A7 D h\ne A D D7",
        "e H7 e\ne a H7 e\ne a H7 e\ne a H7 e\ne a H7 e\ne a H7 e\na h e a e\ne a H7 e\ne a H7 e\ne a H7 e",
        "D A G D A\nD A G A D\nD h A G D A\nD h A D G A D",
        "e C G H7\na G a\nH7\ne C\nG H7 e a e (H7)\n\nC e\nC G\na e\nC H7",
        "C a G\ne G7 C\ne C7 F e\nd G7 C\n\nC C7 F C\ne d G7 C",
        "C E F\nF G C\nd a\nE\n\nC\nE F\nE\nC\nA F\nE",
        "G D\nC G\ne D\nC D\n\nC a\nC a\ne C\ne D\nC a\nC a\ne C\ne D",
        "h G D fis\nh G D\nh fis G\ne A D\n\nG A D\nG A D\nfis h G\n1|e A Fis\n2|e A D",
        "e D G\na H7 e\n\ne D G\na H7\ne D G\na H7",
        "G C /x2\nG D e C D\nG C /x2\nG D e C D\n\nG D e C D\nG D e C D",
        "e D\nC H7",
        "C e a F\nC e a F\nC e a F\nC e a F\n\nC e a F\nC e a F\nC e a F G\nC e a F",
        "D e A7 D\nh e A7 D\nD e A7 D\nh e A7 D\n\nD e A7 D\nh e A7 D",
        "d\nC a d\nd\nC a d D7\n\ng C d\ng C d D7\ng C d\ng d C d\nC d",
        "h e A h\nD e G Fis\nh e A h\nD e G h\n\nD A h G\ne A\nD A e h\nG h",
        "h E h E h A h Fis\nh E h E h fis h Fis",
        "D A\nh fis\nG D\ne A",
        "A9 C9 G\nA9 C9 G\nA9 C9 G\nh7 C9\n\nG h7 C9\ne h7 C9\nG h7 C9\ne h7 C9\n\n\n\n\n\n\n\ne h7 C9\ne h7 C9",
        "D fis G Dsus2\ne7 A7 D A7sus4\nD fis G Dsus2\ne7 A7 D D7\n\n1|G A7 2|e7 A7\nD fis h7\ne A7\n1|D D7 2|D A7sus4",
        "cis fis cis\nH A Gis\n\nfis Gis\nA Gis\nA cis\nfis Gis",
        "a d\na G a\n\nC G\nd a\nC G\nd a",
        "D G",
        "D A\nfis h\nD A\nfis G\n\n\n\n\n\nG7+ Asus4 fis G\n\n\nD A\nfis h\nD A\nfis G\ne7 fis\nAsus4 G\ne7 fis\nAsus4 G",
        "cis A H cis\ncis A H cis\ncis Fis A H",
        "D A fis cis",
        "G C D C\n\n\n\n\nG D\ne C\nG D e C\n\nG D C D G",
        "a d7 G C\nd a\n\na d a",
        "e D e\nG D e G D C\nC D e",
        "H A E\nH A E\nH A E\nH A D H\n\nE A\ncis H\nE A E\n\nH\nA cis H",
        "d C a d\nB C d\n\n\n\nF C d C\nF C d C\nF C a\nF C d A\nd g C A d",
        "cis H E\nE A\ncis H E\nE A\nfis E H\nH\nfis E H\nH\nA cis\nA\n\nA H E\nA H E\nA H E fis\nE A H\n\nA H E A H E\nA H E fis E A H",
        "E\nd F E\nE d F E\n\nA g\ng A",
        "C d7 G a\nC d7\n1|G C\n2|G C E\nE a F C E\nE a F G",
        "a C\nd E\na C\nF E\n\nF\ne\nd E",
        "e H7\ne a H7\ne H7 e\n\ne H7 e\nH7 e\na H7\ne H7 e\na H7",
        "a d a E\nE a d E C\nd E\n\nC G C d C G\nG d C G C G C",
        "e h7\nC H7\ne h7\nC H7\n\na h e\nC G\na h e\nC e\na h e\nC G\na h\ne H7",
        "Asus2 C Dsus2 Asus4\nDsus2 A\n\n\n\nA (E) fis7\nD A\n\n\nD A\nD Dsus2 A",
        "D A G\nD A G\nD A G D",
        "e h a e\nD A7 D H7\ne D a e\na7 h e",
        "D e A Fis Fis7\nh G Fis G A\nD e A Fis Fis7\nh G Fis G A h",
        "e H7\ne C D\nH7 e H7\na H7\n\ne H7\na\nC H7\ne H7\na\na\nC H7",
        "C G a F",
        "G D C D\n\n\nG C G\nG D G",
        "D G D A\nD G D A\nD D7 G g D A D",
        "e\nD\na C\nG D\n\nG D e\ne",
        "C a C\nC a C\nF7 a\nC a C\nd7 G C C7\nG7 C7 d7\nG e7\na d7 G\nG C",
        "e D\nh e\ne D\nh e",
        "D e\nA D\nD D7 G g\nD A D",
        "D A e7 h A A7\nD h e A\nG h A D",
        "e D a h\na D e\ne D a h\nC D e\n\ne D G D\na D e\ne D G D\na7 h e",
        "a F d E7",
        "h G D A",
        "D G\nA D\nh e\nA D",
        "e a\nD e\n\n\n\nG D\ne H7\n\n\nC D e",
        "d d7 B a7\n\n\n\n\nd F d G",
        "D h e A\nG A\nD A h\ne A D A h\ne A D",
        "E H7 cis\nA Fis H\nE H7 cis\nA H E\ngis cis\nA H7\nE H7 cis\nA H E",
        "G\nC e a\nC a\nD D7\nC a H7 E7\na D G e\na D G",
        "D\n\nG e A\nG D A\nG D A\nG A h\nG A D",
        "C G a e\nC G a E\nC G a e\nC G a E",
        "D h\nG e A\nD Fis h G\ne A D",
        "D e A7 D\nD e A7 D\nD G A D\ne G A",
        "D e7 D G\nD G e 1|G A4\n2|D\nD A G A D\nD G e A G A D\nD A G A D\nh e A G A D\n\n\n\n\n\n\n\n\n\n\n\nG h7\nC G\ne7 D/Fis\nG A\nG h7\nC G\ne7 D/Fis\nG A H\n\n\nE fis7 E A\nE A fis 1|A H4 2|E",
        "e a D",
        "H7 E gis\nA fis H H7\nE gis\ncis fis H E E7\n\nE7 A fis\nE cis\nfis H7 E (E7)",
        "H7 e a\nH7 e a e\na H7 e\na H7 e\nH7 e\n\nC a H7\nC a H7",
        "h A G e fis h\nh A G e fis h",
        "e D C D\na H7 e\ne D C D\na H7 e\n\ne D e D\nC D C H7\ne D e D\nC D C H7 e",
        "g C B C\na d C\nC g d C\ng7 B C\n\nB C d\ng B C\na C d\ng B C\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nB C d\ng B C\na C d\ng B C",
        "fis cis\nH A E\n\n\n\nH A\nE H\nfis A\nE H\n\n\n\n\n\n\n\n\nfis cis H A E\n\nfis\ncis H\n\n\n\n\nfis\nA H cis\n\n\n\n\n\n\nH A\nE H\nH A\nE H",
        "h G\nG A h\nh G A h\nh A D e Fis7\nh A D\ne Fis7",
        "E A E H\nA fis7 E A\nA gis7\nA fis7 E\n\nE A H\ncis fis7 gis\ngis7 cis fis7 gis\nfis7 H",
        "h G E4-3\nh G D A2/cis\nh G E4-3\nh G2 D A Asus4\n\nD G e7 Asus4\nD G e7 Asus4\n\n\n\n\n\nG D2/Fis\nG A D",
        "e C A7 C\ne C A7 C\na e (C A7 C)\n\n\n\n\nD e (C A7 C)\nD G D\ne (C A7 C)",
        "C C7 F\nC a B G7\nC C7 F f\nC D G7 C\n\nC C7 F D7\nG7 C F C\nC C7 F D7\nG G7 C",
        "C F G C F G\nC F G C\n\nC F C F G\nC G C F G C",
        "e H7 e\nH7 a H7\ne H7 a C\nH7 e\n\nH7 e\na H7\nC H7 e\na H7",
        "e a7 h7 e /x2\ne a7 h7 e\ne a7 h7 e",
        "A h\nD E\nA\nA A7 D\nD A E A",
        "a C G F a E\n\n\na d7 G C\nF d E E7",
        "e a7 H7 e\ne a7 H7 e",
        "D Dsus4 Dsus2 D\nD Dsus4 Dsus2\n\nD e7 Asus4 h4\nD2/Fis G9^6 Asus4 Dsus2\n\nh4 G9^6 D2/Fis A2/Cis\n\n\ne7 h4 Asus4\ne7 G9^6 G9/A A\n\n\n\n\n\n\n\ne7 D2/Fis Asus4",
        "e\na H7\ne\na H7 e\n\ne\na H7\ne\na H7 e\nH7",
        "(D) G e7\nh7 D2/Fis",
        "D A C G",
        "e Fis\ne Fis\ne Fis\nG Fis\n\nD e h\nD e Fis\n\nG D\nh A\ne h\nD Fis\n\nG D\nh A\ne h\nD Fis\n\nG D\nh A\ne h\nD Fis\n\ne Fis\ne Fis\ne Fis\nG Fis",
        "d a B a\nd C B g a d\n\nd C\nB a g a\n\nd\nC\nB C\nC\nB a d\n\nd\nC\nB a g a",
        "A7+ h7 cis7\nH7 E7 A7+\nA7+ h7 cis7\nH7 E7 A7+\n\nD7+\nE cis7 fis7\nh7 E A7+\nD7+\nE cis7 fis7\nh7\nD E4-3",
        "C e a F D G\nC e a F D G\n\nF G C\nF G a\nF G C\na G C",
        "e a D H7\ne a D H7\nG C D G\nH7 e H7\ne H7 e H7\nH7 e H7 e",
        "e a\ne H7\ne a\ne a H7 e\n\ne a\nD G H7\ne a\ne a H7 e",
        "C g\nC g\nF C\n1|F g \n2|F G\nF G C\nF G C\nF G C\nF C",
        "d A7 d B C F A\nd A d A7 d\n\nd B C F\nF B C F\nd B C d\nd B g A",
        "A cis\nD d A\n\n\n\nA cis\nD d A\n\n\n\nA E G D E\nA E G D E\nF G A\nF G A",
        "a d a\na d a\nF e d\na e a\n\nG a\nG a\nF e d\na\nd a e a",
        "e e7 C G D4-3\n\nD C D\nC a7 D\ne e7 C\nh7 D\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nD C D\nC a7 D\ne e7 C\nh7 D",
        "a d C F g C\nF g d C g B F\n\nd C F\ng d C\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nd C F\ng d C",
        "cis H A E\ncis H A H /x2\n\nE H A E",
        "e H7 e H7 e\nH7 e\n\na H7\na H7\na H7\na H7",
        "cis fis cis fis\ncis E fis Gis7\ncis fis cis fis\nE D cis",
        "G C G\na D7 G\n\nG C G\na D7 G",
        "E H gis cis\nA H E\nA H\nE cis\nA H E\nA H\nE cis\nA H E",
        "E fis gis fis",
        "g d g d\nB F c7 g\n\ng F\nF g\nB F\nF g",
        "E7\nH7\nE7\nA7 H7\nE7",
        "h A\ne Fis\nh A\nG Fis h",
        "D fis G A\nD fis G A\nD A h D\ne A D",
        "h7 fis7 h7 fis7\ne A7 D Fis7\n\nG D\nG D\ne h7\nC Fis7",
        "E7\nE7\nA7\nE7\nE\nE\nA7 H7\nE7",
        "A E H cis\nA E H cis A\nA E H cis\nA E H",
        "e h e h\nC H7\ne h e h\nC H7\n\ne C D h\nC H7 e H7\ne C D h\nC H7 e",
        "a G C\nd F E7\n\nd E7\nd E7\nF C\nd E7",
        "D A h fis G fis e A\nD A h fis G D e A D",
        "1|D 2|h 3|G e",
        "D a D a\nD a D a\n\nH7 C\nD G D\na e\nD C H7 e",
        "fis D A E\nfis D A E\nfis D A E\nD E fis\n\nfis D A E\nfis D A E\nfis D A E\nfis DA\nD E cis h\nD E fis\nD E cis h\nD E fis\n\nfis D D7+ A E\nfis D D7+ A E\nfis D D7+ A E\nfis D D7+ A\nD E cis h\nD E fis\nD E cis h\nD E fis",
        "D e\nA7 D\nh e\nG A7 D\n\nD G\nA7 D\nD e G A7 D",
        "F G C a",
        "C G F C\nF C D7 G7\nC G F C\nF C G C",
        "C\nG\na C G\nF C\nd G C\n\n(E) a E\na A7\nd C G\nC G\nC A7\nd G C",
        "e h7\nh7 e\ne D\nH7 e\nC H7\nH7 e",
        "h A h A\nh D Fis\nh A\nG D\nh A h",
        "C a F G\nC a F G\nC a F G\nF G C",
        "E\nE7\nA A7\nE\nH7 A\n\n\n\n\n\n\nE7\ncis\nH7 A",
        "C a F G C",
        "e a\nD h\ne a\nD h\n\ne a\nD h\ne a\nD h",
        "A D E A\nA D E7 A\nA D E A\nA D E A\n\nA D\nE A\nA D\nE A",
        "C G\nC G\na e\nF G",
        "G C D /x2\n\ne A\na D\ne H7\nG A\nC a D (C D G)",
        "E H7\ncis gis\nA E D H7\nE cis fis\nH7 E cis\nD H7 (E)",
        "A E\nfis D E\nA E\nfis D E",
        "d C d\nd C F D7\ng C F d\nB A7 d (D7)\n\ng C F d g A7\nd D7\ng C\nF (a7) d\nB a d",
        "E H E\nA E H /x2\nA H /x2\nE A H\nE A H\ncis A H\nfis A H",
        "C e F\nC e F\nC e\nF G",
        "C G\nG C C7\nF f C a\nF G C",
        "G e h\nC a D D7\nC h e A\nG e a D7 G (C G)",
        "D G\nA D\nh G\nA D",
        "G e\nG e\nC D\nG D\nG C D\ne C D\na H7 e\n\nC e C G D G",
        "A cis h fis\nfis E D\n\ncis Fis7 h d6\nH fis h7",
        "a d e a d e\na d e a d e\na e d e\na d e a d e\n\na e G D\na e d e",
        "d\nF\nC d\nd A7",
        "D\nh\nG e\nA A7\nD\nh\nG e A A7",
        "C F\nC F\nC a\nd7 G7 C C7\n\nF G C a\nd G7 C",
        "e C D e\n\ne a e\ne a e\ne a e C D",
        "e h a H\ne e7 h e\n\ne C7+\na H\ne C7+\na\nH\n\ne C7+\na H\ne C7+\na H",
        "E D\nA E\nE D\nA E",
        "c\nB c\nc B\nAs B c\nc As\ng B\ng B\ng B c",
        "e H7\ne D\na H7\ne H7\ne H7\ne D\na H7\na C H7 e\n\na H7 e D C\na H7 e\na H7 e D C\na H7",
        "a d G C\na d E a",
        "C a d G\nC a d G\nC a d G C C7\n\nF G C a\nd G C",
        "D h\nG A\nG h7 G e7 A",
        "Asus2 cis7 Hsus4\n\nfis7 cis7\nAsus2 Hsus4\n\nE E4 E E4\nHsus4 cis7 Asus2\nHsus4\nE E4 E E4 Hsus4\ncis7 Asus2 Hsus4\n\nfis7 cis7 Asus2 Hsus4",
        "G D CG D C\nG D C\nC e D",
        "G a G",
        "E A cis H\nE A cis H\nE A cis H\nE A cis H\ncis fis A H\ncis fis A H\ncis fis A H\ncis fis A H",
        "e D G a G\ne D G a G\ne D G a G\ne D G\n\nC G D e\nC G D e\nC G D e\nC G D e",
        "h e A Fis7\nh G Fis7 h Fis7",
        "e C D H7 e\ne C D H7 e\nG D C D G\nG D\nC H7 e (H7)\n\ne D\nG H7 e\nH7 e D\nC H7 e\n\ne C D H7 e\ne C D H7 e\nG D C D G\nG D\nC H7 e (H7)\n\nH7 e D\nG H7 e\nH7 e D\nC H7 e",
        "C a\nd G\na G\na d7 B\nC a\nd G C",
        "h A E\n\n\n\nD/Fis G A fis h\nG A h\nG A fis h\ne fis",
        "(e a H7) e a D G\ne a\nH7 e /a H7\ne a\nD G e\na H7 e E E7\n\na D\nG e\na H7 e E E7\na D\nG e\na H7 e /a H7",
        "D A e7 G\nD A e7 G\n\nD G h Asus4\n\n\n\n\n\n\n\n\n\ne7 G6 Asus2\n\n\ne7 G6 Asus4",
        "E fis A E\nE fis\nA E (fis E)\nfis H7 E cis\nfis H7 E\nfis H7 E cis\nfis H A E",
        "E cis A H /x2\nE A H7\n\n\n\nE gis A H E gis\nA H7 cis\nA H E",
        "G C D h\nC D G\nG C\nD h\nC D G G7\n\nC D\nG h e\nC D G G7",
        "G C D\nG C D D7\nH7 e D C\nG C D D7\n\nG C G D G C G D\nG H7 e C G D G",
        "C G\na F C\nG F C\n\n\n\n\n\n\n\n\n\nG C\nF C\nG C",
        "a D\na7 D\na7 D\na7 D G\n\ne h7 a\na D4-3\ne h7 a\na7 D4-3",
        "D G D\ne A\nD G D\ne A7 D\n\nD e A7\nD e A7\nG D\n1|A D D7\n2|A D",
        "D G D\nD A D",
        "D a\nD a\n\nC A\nC A",
        "E A E\nE A H\nE A E gis A H E\n\nE\nH7\nE H7 E",
        "fis cis fis D A E A\nh E A fis h Cis fis\n\nD cis\nD E A\nfis cis\nD A",
        "D A h G h E A\nD A h e A D",
        "G G/Fis\nh7 D A (D/Fis)\nG G/Fis\nh7 D A\n\nD D/Fis G /x2\nD D/Fis G A h\nA",
        "h A G D\nh A G D\nh A G e D\ne7 G A h\n\nh A G\ne G A h",
        "d C d C\nd C d C\nF C\ng d",
        "A D A D\nA D E\nfis cis\nD d\nA D A",
        "F G a7\nF G C\nF G e F\nd e7 a7\n\n\n\n\n\n\nF G\na7 G\nF G D\nF G\na7 G\n1|F G D\n2|F G A",
        "D h a\nD h a\nG A D\nG A\n\nD G\nD G\nA D\nG A",
        "h A D A\nh A h\nh A D A\nh A h\n\nh G A h\nh G A h",
        "E gis\nA H\nE gis A H\n\na7 D7\nG e\na7 H7\na7 D7 G e\na7 H7 a7 H7",
        "G C D\ne C D\ne C a e\na e H7\nC G a e\na e C D\nC G a e\nC D G",
        "d C d C d C d C\nd C d C d C d\nF G F A\nd C d C d C d\nF G\nF A\nd\nC d C d C d",
        "C F G C\ne F G\nF G\ne a\nC F G C",
        "G a C",
        "D h\ne A\nD h\ne A D",
        "C\nF G\nC G\nC F G\nC G\n\nC F G\na G\nC F G\na G",
        "G C D\nG C D\ne C\nA D\nG C D\nG C D\nG C D\na D",
        "h G fis\nh G fis\nD A e G fis\nh fis G fis h",
        "a e\nF d E\nC G\na F a\n\nF G C\nd G a\nd G C C7\nF E",
        "D G A D G A h\ne C e a G D\nD G A D G A h\nG D/Fis G A4-3 D",
        "A fis\nA E\nE fis E A\nfis h E\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nA fis\nA E\nE fis E A\nfis h E",
        "a F\nC E\na G C E\nE F E (a)",
        "G C D G\nD2/Fis C\ne D C\nG C D G\nD2/Fis C\ne D C\nG C D G (C D)\n\nG D C\nG D C D\nG D C a7 D G",
        "D G D A\nD G h A\nD G h A\nG A D\nF A D\nC G D\nF A D\nG A",
        "cis A\nfis H\ncis A\nfis H\n\nE A H E",
        "A cis fis\nD E A fis\nD E A fis\nD E",
        "G\ne\nC\nD D7\nG\ne\nC\nD D7\n\nG\ne\nC D D7\nG\ne\nC\nD D7",
        "a7 D7 a7 D7\nF G\nF G\na7 F E7 a7",
        "a F\na F\nd e F",
        "H e\ne D G C G D\nG D e h7 C\na6 H e a6 H e a6 H",
        "d\nC d\nd C d\nF C F\nF A7 d\nC d C d\n\ng6 d7\ng6 d\nC C7 F\ng7 B7 A7",
        "F C g d\nB F d B7+ C\ng B d",
        "a G F G\na G F e a\nF G C a F C\nF G C",
        "A cis H E\n\nA cis\nH E\nA H E\nA cis\nH E\nA H\n\nE H\nA H\ncis\nA H\nE H\nA H\ncis A H\n\n\n\n\n\n\n\n\nA cis H E\nA cis H E\nA H E\n\nE H\nA H\ncis\nA H\nE H\nA H\ncis A H",
        "h A D G\nh A D\nG A7sus4 fis7 h7 G\ne7 A7sus4 A7\nh A D G\nh A D\nG A7sus4 fis7 h7\nG e7 A7sus4 A7\n\nD A\nh fis7 G\nD/Fis e7 A7sus4\nD A\nh fis7 G\nD/Fis e7 A7sus4 A7\n\nh A D G\nh A D\nG A7sus4\nfis7 h7 G\ne7 A7sus4 A7\n\nG D/Fis7 G D/Fis7\nG D/Fis7 e7 A7sus4\nG D/Fis7 G D/Fis7\nG D/Fis7 C9 A7sus4 A7",
        "a G a",
        "G D G\ne C D G\ne C D\ne C D\n\nG C9 G D G C9 G D",
        "h E h\nh E h\nh E Fis\nh E h\nh E h\nh E Fis\n1|D E 2|A E",
        "D G D C D\nD G D C D\n\n\n\nC D\nC D\nC D\nC D",
        "D G\n\n\nD A G D\nG D A\nD A G D\nG D A D",
        "h E h G A D Fis\nh E h G A h",
        "E Gis7 cis A H\nH7 Gis7 cis A cis H\nE Gis7 cis A H\nH7 Gis7 cis A H E",
        "E H A\nE H A\nE H\nA H\nE H\nA H E",
        "G A7 D h G A7 D\n\n\nG A7 D",
        "G e C D",
        "D C2\nB C2 D",
        "d g C F A7\nd g A7 d\n\nd g C F A7\nd g d A\nd g A7 d",
        "e D a e\nG D a C D\nG D h e C D e",
        "G D\nD C\nD G\n\n\n\n\nC D G",
        "e C D e\ne C D G\na D G C\na D G H7\na D G C\ne D H7 e",
        "e C\nD e\ne C D e\ne C D e\ne C D e\ne C D e",
        "d a7 B C4-3\nd a7 B C4-3\nF C g F C\nF a7 d",
        "C G F G C\n\n\n\n\n\n\n\n\nF e\nG a\nF G C",
        "a G C\nF C d7 a\nG d C a\nE7 a d E a",
        "D G D/Fis\nD G A4-3\ne h A e D/Fis A\n\nD h A\nD h A\nG D E\nA E7 A",
        "e H7\nC H7\ne H7\nC H7\n\na\nH7\na H7\nC\nH7\na H7",
        "D G D (G A)\nD e A\ne A fis H7\ne G A7",
        "fis E\n\n\n\nfis E",
        "fis h fis\nh7 fis\nfis h fis\nh7 fis\n\nh fis\nh\nfis\nD Cis",
        "e\nG\nA\nC D e\n\ne D h e",
        "D A h D7\nG D E A\nD Fis h E\nG A D",
        "E\ncis\nA H\nE\n\nE cis\n\nA H E\n\nA H cis\n\n\nA H E",
        "D A\ne G D\nD e A\nh G A\n\n\n\n\n\n\n\n\n\n\n\nD A\ne G D\nD e A\nh G A",
        "E D A H7 E\n\n\n\n\n\n\nE D\nA H7",
        "cis A E H\n\n\ncis A E H",
        "C F G\na F G C\n\n\n\nC d G C\nC d G C",
        "D A7\ne D D7\nG A7 D A h\ne A7 D D7",
        "D G D\nh G A",
        "e D h e\ne D h e\ne D h e\ne D h e\n\nD G D e\ne D h e\nD G D e\ne D h e",
        "A\ng A\nA\ng A\nd\nC\nB A\n\nA\nB A\nA\nB A",
        "e\nD h C e\n\nD h\nC e\nD h\nC e\nD h\nC e\nC D",
        "e\nC D\ne\nC D\n\nC H7",
        "D A\nG A",
        "h A D G A\nD e D A\nG D e A\nD e D G A",
        "d\nF\nC a d\nd\nF\nC a d\n\nF C\ng d\nF C a d",
        "(E) D A\nD A\nD E fis E",
        "C e F G7\nC e F G7\n\nF G7 C e\nd G7 C a\nF C a\nd G7 C",
        "D fis h7\ne A D (e A)",
        "G C D7 G\nG7 C D D7\nG C D7 G\nC a D7 G",
        "D\ne A\ne A7\nD G D",
        "D A D\nA D A D\nA h A D\nA D A D\nG A7 D",
        "A H cis7 A H cis7\nA H cis7 fis7 H E\nA H cis7 A H cis7\nA H cis7 fis7 H E\n\nE A H cis7 A H cis7\nA H cis7 A H cis7\nA H cis7 E",
        "d C d\nd a C d\nd C d\nd a C d",
        "G C\n\n\n\n\n\n\nG D\n\nG C G D h C G",
        "e h e h\ne e7 D e\ne h e h\ne e7 G H\n\nE H A E\nA E A H\nE H A E\nA E A H (e)",
        "h A D\ne D C\nh A D\ne D C\nG A\n\nD A\nG\nh\nA G A\nD A\nG\nh\nA G A",
        "e H7\na H7\na H7\na H7\n\na\ne\na\ne\nC\nD\nh\ne H7",
        "E a G C F E\na G C d E",
        "C\nd\nF C (G)",
        "h A E h\nh A E h\nG D\nA h\nG D\nA h",
        "A A7+\nA7 Fis7\nh d\nE E7\nD A\nh fis D A\nE E7\n\nA E\nfis cis\nD A E (E7)\nA E\nfis cis\nD A E",
        "D\nh7 G\ng D\nD\nh7\nG g D\n\nG\nh7 B\na7 D\nG\nh7 B\nC D",
        "D A\nh fis\nA D A\nh fis",
        "E H cis\nA H\nE H cis\nA H\nfis cis H E\nfis cis H\nE H A\nE H A\ncis H A\nH E",
        "e D e\ne D h\ne D e\na h e\n\ne D e D h\ne D e a H7 e\ne D e D h\ne D e a H7 e",
        "h a\nh a\nh\n\nD C\nH7\nD C\nH7",
        "E H\ncis A\nE H E H\n\n\n\n\n\n\n\n\n\n\nE A E A\nE H E H",
        "e D\nC D\ne D\nC D\nC\nC D\ne\nC D\nC\nC D\ne",
        "E F\nd E (F)\n\na G\nG a\na G\nG a (F)",
        "A E\ncis\nH\nA E\ncis\nH (A H A H)\n\nE H\nA E\ncis H\nE H\nA E\ncis H\n\n\n\n\n\n\n\n\nA\nE H cis\nA\nE H cis",
        "a\nd a\nd C\nH7 E\n\nE7 a\nd E\na d\nE7 a",
        "D G A D\nG A C G D\nD G A D\nG e A D\n\nG A fis h\nG A fis h7\nG A Fis h\nE G A D",
        "G h e\nC D G D\nG h e G\nC D G D (D7)\n\nG H7 e G\nC D G D G H7 e G\nC D G (D)",
        "C G F G C\nC G F G C\nC a G a F a G\nC F a G a G C",
        "C G C a G C\nD G C a G a\nF C F C a\nF C F C",
        "e A C D\nC D G D h7 C\na C D9 G h C e\n\ne\nG C D\na e7\nC a7 H",
        "G C D\n\n\n\n\nG\nD\ne C G D\nG\nD\ne C G",
        "e D\ne D\nG D\nG D\n\n\n\n\n\nG C G\nD e D\nG C G\nD h e\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nG a\nG a",
        "e C\nG D\n\ne\nC G a7",
        "d\ng d\ng d\nB d g\nd g d",
        "d C\nB A7\n\nd C\nd\nB A",
        "d B\nF C\nd B F C\ng d\nF C\ng d F C\n\nd C F g A7",
        "G D C\nG D C\na D7\na D7\n\ne D e D\nC D C D\ne D e D\nC D C D",
        "d B\nAsus4 A7sus4 d (Asus4)\n\n\n\n\nd B\nAsus4 A7sus4 d (C)\n\nF C\ng7 d (C)\nF C\ng7 d (Asus4)",
        "D G\nD A\nG D h\nD A D\n\nD A G\nD A G\nD Fis7 h G\nD A D",
        "a d E7 a\na d E7 a\na F G C d G a\na F G C d E7 a",
        "D A h G D A7\n\nD A h D A D",
        "D G\nD A7\nD G\nD A7 D\nD G D A7\nD G D A7 D",
        "h Fis h Fis\nh A D A\nh Fis",
        "A fis D E\nA fis D E\n\nA Fis7 h\nh E7 A E7\nA Fis7 h\nh E E7 A\nA fis\nh E\nE7 A /D E A",
        "F B C a d\ng C F\nF7 B C a d\ng C F\nF7 B F\nF7 B C",
        "d\nC\ng\nA7\n\nF C\ng A7\nF C\ng A7",
        "d g d\nC F A7\nd g d\nC F A7\nd g d\nC F A7\nd g d\nC B C C7\n\nF C d A7\nF C d A7\nB C",
        "a G C\nd G C\nd E a\nG F E\n\nG C\nd G\nE a\nG d G",
        "D G D\nG D A D",
        "E\nA E\nE H7",
        "D h G A",
        "e\nH7 e\na e H7 e (E7)",
        "e C G A\n\n\n\n\ne C G A",
        "E fis H7 E",
        "E H7 E\nE H7 E\nE A H7 E\nE A H7 E",
        "D G\nD A\nD G\nD A D",
        "e G Dsus2 e\n\ne G A\nDsus2 e\n\n\ne C D A\ne C D\ne C D A\ne C A\n\ne C D A\ne C D",
        "E A\nE H7\nE A",
        "G e C D",
        "D e\nA D\nh e\nA D\n\nD e A D\nD e A D",
        "G D C G\nC D G\nG a7 D7 G e a7\nD G (D)",
        "C e F G\nC e F G C a\nF G C",
        "G e C D G",
        "a d\na E a\nE a\nE a\na d a E a",
        "D A",
        "D A7\nG h",
        "D\nA D\nD\ne A D\nD G D\nD e A D",
        "D fis h G A D",
        "E A H7 E\nE A\nH7 E",
        "D A\ne A D\nD H e\nA D",
        "E A H",
        "D h A D\nD h A D\nD h A D\nD h A D\nG A\nG A\n\nD G h A\nD G A D",
        "C F C\nF C G C",
        "D e A D h\ne A D h\ne Fis7 h\nG A D\n\nA D\ne A\ne A fis G\nA D",
        "C d G\n\n\n\na G a\nF E7",
        "D A D\nG A\nh7 D\nG D A\nh7 D\nG A\n\n\n\n\n\nG D A G D A\nG D h A fis\nh D\nG A",
        "D fis\nG A G A\nD fis G A\nG A G A\nD fis G A\nG A G A",
        "C G C e A\nd G C G C\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nC G C e A\nd G C G C",
        "D G\nD A\nG D\nA D",
        "C G C G C\nC F G D G\nC F C F C G\nC F C G\nC F G C",
        "C F\nC G\nC F\nC G\nd C\nd C\nd C A7 d G C",
        "G\nG\nD A D A\nD A D A D\nG G D G\nG D G",
        "G a D G\nC G C G\na D G e G D G",
        "D A\nA D\nD A\nA D\n\nD A\nG D\nh e\nA D",
        "D G D A7\nD G D A7\n\nA D\nA D\nD\nD G D A7 D",
        "D fis G A\nD fis G A\nFis h G\nD A D\n\nD G A D\nG A D A\nD G A D\ne A D",
        "C F G C A7 d G C\nC F G C A7 d G C\nG C G C\nC F G G7 C F C G C",
        "F C F d g C C7 F\nF C F d g C C7 F\nF g C B F\nd B b F g C F",
        "e a e H7\ne a e H7\n\ne a e a\ne a H7 e",
        "C G C\nC G C\nd C G\nC G C",
        "C F C F C\nF C G C",
        "D G D A\nG D E7 A\nD G D A\nG D E A\nD H7 e\nA D\nD H7 e\nA D",
        "D fis G D A\nD fis G D A\nD fis G D A\nD fis G D A\n\nG D G D\nG D e A\nG D G D\nG D e A D",
        "D G A h7 A\nD G A D\nD fis G A\nD fis G A\nD G A h A\nD G A",
        "a E a E\nd C d G C",
        "D G D A7 D\nD G D A7 D\nD G e A\nh G A7 D",
        "D A h7\nG A D\nD A h7\nG A D\nG D\nG A\nD A h7\nG A D\n\nD A\nG A D\nD A\nG A D",
        "D G D G\nD D7 G A A7\nD G D D7",
        "C d G G7 C\na d G G7 C\nC F d C\na d G G7 C",
        "C F G F G C\nC F G F G C\n\ne F G C\nF G C\ne F G a\nD G C",
        "a\nG F E\na\nG F E\nF\nG C G/H a\nd E\n\na\nG F E\na\nG F E\nF\nG C G/H a\nd E\n\na G F E\n\nF G\nC G/H a\nF\nG\na E\n\na\nG F E\na\nG F E\nF\nG C G/H a\nd E\na G F E7 a",
        "A D A\nh E7 A\nA A7 D Gis7\nA h E A\nE A h A E\nA A7 D Gis7\nA h E A",
        "e a e H7\ne C G H7\ne a e H7\ne C G H7 e",
        "C F G C\nC G F C",
        "d A\nA d\nd A\nA d\nF C\ng d\nd A\nA d",
        "E H7 B7 H7\nE A H E H7 E\n\n\n\n\n\n\n\nE H7 B7 H7\nE A H E H7 E",
        "E H E E7\nA H E\nE H E E7\nA H E\ncis gis\ncis gis\nfis H E E7\nA H E",
        "C G\nG C\n\nC G C",
        "E A E H E\nH E\nA H E\nA H A H E\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nE A E H E\nH E\nA H E\nA H A H E",
        "G C G C G\nG C G C G\na7 D7 G e\na7 D7 G (E7)\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nG C G C G\nG C G C G\na7 D7 G e\na7 D7 G (E7)",
        "C F C\nC F C\n\nC A7\nd G C F C G C",
        "A D\nA h E E7 A\nA D\nA h E E7 A\nA D E\nA E H7 E\nA D\nE D\nA E7 A",
        "G C D e /x2\nG D C G\nC D G",
        "a G C a\na G C a\na G C a\na G\n\nC F G\nF G C",
        "G C G C D G\nG C G C D G\nD A D D A D\nG e D H7 a7 D G\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nG C G C D G\nG C G C D G\nD A D D A D\nG e D H7 a7 D G",
        "a G a\nd E\na G a\nd E a\nG C\nd B E\na G a\nd E a\n\nC G a\nd G\nC G a\nd E a",
        "D e7\nA D\nh e7\nA A7\n\nD\ne7\nA\nD",
        "D A\nD G A D\nD A\nD G A D\nD e\nA D\nG e A D",
        "C d G C\na d G C\nC F G C\na d G C",
        "E H7 E E H7 E\nE H7 E E H7 E\nE cis fis H\nE cis fis H\nE H7 E",
        "D G A D\nD G A D\nD A\nD A\nD G A D"
    )

    val songs = arrayOf(
        "Ref. Abba Ojcze, Abba Ojcze, Tatusiu.\nAbba Ojcze, Ojcze mój.\n\n" +
                "1. Przytul mnie,\nNiech w ramionach Twych poczuję się bezpiecznie.\n" +
                "Spojrzyj na mnie,\nMoje serce przepełnione jest boleścią,\nOjcze mój!\n\n" +
                "2. Ufam Tobie,\nChciej wysłuchać mojej skargi i wołania.\nOdsuń kielich,\n" +
                "Lecz nie moja wola - Twoja niech się stanie,\nOjcze mój!\n\n" +
                "3. Ratuj mnie,\nWzmocnij swoją łaską słabą duszę moją.\nZabierz kamień,\n" +
                "Który wiąże mnie, obciąża moje serce,\nOjcze mój! Ojcze mój!\n\n",
        "1. Abba Ojcze, tchnij w nas Ducha, Jezu chwała Ci!\nAbba Ojcze, tchnij w nas Ducha, Jezu chwała Ci!\n\n" +
                "Ref. Chwała, Alleluja! Jezu chwała Ci!\nChwała, Alleluja! Jezu chwała Ci!\n\n" +
                "2. Ja dam wszystkim wodę życia, Jezu chwała Ci! /x2\n\n" +
                "3. Gdy mnie szukasz, to mnie znajdziesz, …Jezu chwała Ci! /x2\n\n" +
                "4. Tylko w ciszy Mnie usłyszysz, Jezu chwała Ci! /x2\n\n" +
                "5. Przyjdźcie dzieci, Jam jest Prawdą, Jezu chwała Ci! /x2\n\n" +
                "6. Jam wasz Pasterz, was powiodę, Jezu chwała Ci! /x2\n\n" +
                "7. Pokój Mój wam dzisiaj daję, Jezu chwała Ci! /x2\n\n" +
                "8. Jam jest Życiem, Zmartwychwstaniem, Jezu chwała Ci! /x2\n\n",
        "Ref. Aby byli jedno, muszą wydać życie.\nAby byli jedno, muszą życie dać.\n\n" +
                "1. Tajemnica wielka pociąga ich\ni w komunię pragną wejść.\n" +
                "Jak złamać ten lęk, co wrogiem jest:\nCzemu pragną i boją się.\n\n" +
                "2. Chcą się spotkać, zjednoczyć, chcą w miłość wejść.\nTak jak Ojciec, jak Duch i jak Syn.\n" +
                "Nie mogą, nie mogą ukrywać nic.\nCo ukryją, to zniszczy ich.\n\n" +
                "3. Co dla siebie zostawią, to stratą jest.\nCo ukryją - podzieli ich.\n" +
                "Nie słabość, nie słabość przeszkadza im.\nLecz brak wiary w spotkaniu serc.\n\n",
        "1. Abyśmy byli jedno, błagamy Cię, Ojcze nasz.\nAbyśmy byli jedno, błagamy Cię, Ojcze nasz.\n\n" +
                "Ref. Uczyń wszystkich nas\nnarzędziem Twego pokoju,\naby miłość Twa mieszkała w każdym z nas.\n\n" +
                "2. Zachowaj nas od złego, - błagamy Cię, Ojcze nasz. /2x\n\n" +
                "3. Uświęć nas w prawdzie, - błagamy Cię, Ojcze nasz. /2x\n\n",
        "Spojrzenie Twe, rozkoszy żar.\nPrzenikaj mnie, wytrwajmy tak.\n" +
                "Opromień mnie, zatańczyć chcę,\nporywaj wciąż, uwielbiam Cię.\n\n" +
                "Gdy łaskę dasz, mogę tańczyć jak wiatr.\nGdy łaskę dasz, mogę śpiewać i grać.\n" +
                "Gdy łaskę dasz, mogę wielbić co dnia,\nTwe cudne imię, Adonai.\n\n",
        "Albowiem tak Bóg umiłował świat,\nże Syna Jednorodzonego dał.\nBy każdy, kto w Niego wierzy,\n" +
                "nie zginął, lecz życie wieczne miał.\nLecz by mógł żyć na wieki,\nby mógł żyć na wieki. /x2\n\n",
        "1. Powiedz, co ci się stało dziś rano, Franciszku?\nCo za światło w twej duszy zabłysło i płonie tak?\n" +
                "Już nie kusi cię sława i złota blask,\njesteś wolny, twoim domem cały świat!\n" +
                "Teraz ptaki niebieskie śpiewają w twym sercu,\nteraz jaśniej świeci słońce, twój złoty brat.\n" +
                "Księżyc srebrem wypisuje poezję Boga,\nto dla ciebie, Franciszku, prawdziwy skarb.\n\n" +
                "Ref. Dla mej Pani Biedy śpiewam dziś.\nDla mej Pani Prawdy tańczę dziś,\nśpiewam jej radosny, wolny ptak,\n" +
                "ja, całego świata mały brat.\nAle, ooo. Ale Francesco. Ale, ooo. /x4\n\n" +
                "2. Teraz biegnij Franciszku i śpiewaj radośnie!\nJesteś bratem, który pokój zwiastuje nam.\n" +
                "Dzisiaj Twymi śladami piszemy historię,\nPowróć do nas, by śpiewać i tańczyć z nami.\n\n",
        "Ref. Alleluja, Alleluja, Alleluja\nAlleluja, Alleluja, Alleluja.\n\n" +
                "1. Każdy, kto pije tę wodę, znów będzie pragnął,\nKto zaś będzie pił wodę, którą Ja mu dam,\nNie będzie pragnął na wieki.\n\n" +
                "2. Jeśli ktoś jest spragniony, a wierzy we Mnie,\nNiech przyjdzie do mnie i pije! Alleluja!\n\n" +
                "3. Ciało moje jest prawdziwym pokarmem,\nKrew moja prawdziwym napojem.\nKto spożywa me Ciało i pije mą Krew,\nTrwa we Mnie, a Ja w nim.\n\n",
        "1. Alleluja! Chwalcie Pana u świątyni bram!\nChwalcie Go na niebiosach pośród gwiazd!\n" +
                "Chwalcie Go za wielkie dzieła Jego rąk,\nChwalcie Go, bo króluje pośród nas!\n\n" +
                "Ref. Wszystko, co żyje, niech chwali Go!\nAlleluja! /x2\n\n" +
                "2. Chwalcie Pana dźwiękiem rogu, chwalcie Go.\nChwalcie Go na cytrze i na harfie.\n" +
                "Chwalcie Go na bębnach, chwalcie tańcem.\nDźwięczcie struny, niech zaśpiewa flet.\n\n" +
                "3. Na cymbałach dźwięcznych chwalcie Pana,\nna cymbałach brzmiących chwalcie Go. /x2\n\n",
        "Ref. Alleluja, Alleluja, Alleluja, Alleluja!\n\n" +
                "1. Niech zabrzmi Panu chwała w niebiosach,\nna wysokościach cześć niech oddadzą.\nWielbijcie Pana duchy niebieskie,\nwielbijcie Pana Jego zastępy.\n\n" +
                "2. Słońce, księżycu, wielbijcie Pana,\ngwiazdy świecące, wielbijcie Pana.\n" +
                "Niebiosa niebios, wielbijcie Pana,\nwody podniebne, wielbijcie Pana.\n\n" +
                "3. Niech wszyscy wielbią Imię Pana,\nbo Jego Słowo stwarza wciąż wszystko.\n" +
                "Bo tylko Jego Imię jest wzniosłe,\nniechaj na wieki brzmi Jego chwała.\n\n" +
                "4. On daje siłę swemu ludowi,\nz prochu podnosi swoich przyjaciół.\n" +
                "Jest Bogiem bliskim dla Izraela,\nswoich wybranych On sam umacnia.\n\n" +
                "5. Niech zabrzmi chwała Ojcu, co stwarza,\nJego Synowi, który jest Panem.\nDuchowi, który w nas zamieszkuje\nprzez wszystkie wieki wieków. Amen.\n\n",
        "Alleluja, wznieś pod niebo głos,\nPan dał Chleba moc,\nAlleluja! /x2\nChrystus Pan Komunią dla nas, co dzień staje się.\n" +
                "On rozrywa grzechu pęta złe.\nCóż uczyni mi człowiek, kiedy ze mną Pan?\nBóg podnosi, podtrzymuje mnie.\n\n",
        "Ref. Alleluja, Alleluja,\nAmen, Amen, Alleluja!\n\n" +
                "1. Pokłońmy się przed Nim\n- On naszym Panem jest.\nPokłońmy się przed Nim\n" +
                "- On naszym Panem jest.\nPokłońmy się przed Nim\n- On naszym Panem, Amen, Amen, Alleluja!\n\n" +
                "2. Radujmy się bracia –\nRadujmy się siostry –\nRadujmy się wszyscy -\n\n" +
                "3. Posyła nam Ducha –\nPosyła nam Ducha –\nPosyła nam Ducha swego –\n\n" +
                "4. Chrystus do nas powróci –\nChrystus do nas powróci –\nOn do nas powróci –\n\n",
        "Ref. Amen, Amen, Bóg miłością jest,\nmoje serce tą miłością płonie.\nAmen, Amen. Jezus Panem jest,\nJego Duch wypełnia wnętrze me.\n\n" +
                "1. On otworzył serce, by napoić mnie,\nw cud zamienił przekleństwo tego świata.\n" +
                "Palcem pisał po ziemi, kamień wypadł z rąk.\n„Od tej chwili już nie grzesz” - do mnie rzekł.\n\n" +
                "2. Moje serce zaorał, swoją zrosił Krwią,\nby wydało owoc przedziwny.\n" +
                "„Bierzcie, jedzcie - powiedział - oto Ciało me”,\nabym ja tak samo czynić mógł.\n\n" +
                "3. On uwielbił Ojca, rozciągnął ręce swe\ni nad ziemię został wywyższony.\n" +
                "Nieskalany Baranek, chcę się dotknąć Go\ni na wieki spocząć, spocząć w Nim.\n\n",
        "Aniele mój, Ty zawsze przy mnie stój.\nAniele mój, Ty zawsze przy mnie stój.\n" +
                "Chroń mnie od złego dnia powszedniego,\nprowadź do domu Ojca mojego.\nAniele mój, aniele mój.\n\n",
        "1. Anioł rzekł Marii, Bóg zmienił Twe plany,\nurodzisz wnet Syna Jego.\n" +
                "Będzie On wielki i będzie nazwany\nJezus i Syn Najwyższego.\n\n" +
                "Ref. Oddaję Ci, Panie, me ręce,\npowierzam Ci Boże mój świat.\n" +
                "Przemieniaj codziennie me serce.\nAmen, od dwóch tysięcy lat.\n\n" +
                "2. Bóg rodzi miłość, gdy Go przyjmuję,\ngdy mówię TAK Jego woli.\n" +
                "Gdy Mu swe młode życie daruję,\nchoć to czasami tak boli.\n\n" +
                "3. Wybrałem Ciebie wczoraj, codziennie,\nna nowo chcę Cię wybierać.\n" +
                "Choć świat się zmienia, ja chcę niezmiennie\ndla Twojej woli umierać.\n\n",
        "Ref. Anioła pokoju ześlij Chryste nam,\nkiedy już nie starcza sił.\n" +
                "Anioła pokoju, niech uleczy z ran,\nniech ukoi każdy płacz.\n\n" +
                "1. Dodaj nam sił w walce ze złem,\nby nie ogarnął nas smutek i cień.\n" +
                "Twej łaski przecież wystarczy nam,\nAnioła pokoju nam daj.\n\n" +
                "2. Wznieśmy swe ręce i chwalmy Go,\nza Jego wielką miłość i wielką moc.\n" +
                "Niech Twoja radość ogarnia nas,\nA Twój Święty Anioł niech broni nas od zła.\n\n" +
                "Bridge:\nOtrzyj łzy ze wszystkich cierpiących twarzy,\nniech głodni wreszcie poznają smak chleba.\n" +
                "Niech przyjdzie, Ojcze, Twoje Królestwo,\nniech otworzą się dla nas Bramy Nieba.\n\n",
        "Ref. Ave Maryja\nAve, Ave, Ave Maryja.\n\n" +
                "Łaski pełna, Pan z Tobą.\nBłogosławiona jesteś między niewiastami.\n" +
                "Łaski pełna jesteś Maryjo.\nBłogosławiony owoc życia Twego.\n\n" +
                "Jezus, Jezus, Jezus.\nŚwięta Maryjo, Matko Boża,\n" +
                "módl się za nami grzesznymi.\nTeraz i w czasie naszego umierania.\nAmen.\n\n",
        "1. Aż sponad mórz i szczytów gór,\nTwa miłość spływa na mnie w dół.\nA ja otwieram serce me,\nniech miłość Twa uwolni mnie.\n\n" +
                "2. Szczęśliwy jestem w prawdzie Twej,\ndo Ciebie wznoszę ręce me.\nChcę zawsze śpiewać\no miłości Twojej Panie.\n\n" +
                "3. O miłości Twej zawsze śpiewać,\nchcę o miłości Twej zawsze śpiewać. /x2\n\n",
        "1. Bracie Franciszku, dziś wołam Ciebie,\nstań choć na chwilę, wysłuchać chciej.\n" +
                "Bracie Franciszku, bogaty Biedaku,\nposłuchaj gitary, na której Ci gram.\n\n" +
                "Ref. Aaa...\n\n" +
                "2. Święty Franciszku, asyski mocarzu,\nmalutki olbrzymie, siłaczu dobroci,\n" +
                "mój opiekunie, wysłuchaj mnie.\nTy potrafiłeś nie patrząc na siebie,\n" +
                "pocieszać zmartwionych, rozdawać swą miłość,\nrozdawać szczęście znękanym ludziom.\n" +
                "Tym co rzucali w Ciebie kamieniem,\nnie rozumiejąc, dlaczego tak czynisz\ni drwili sobie z Ciebie Franciszku, Franciszku.\n\n" +
                "3. Ty jednak dalej kroczyłeś swą drogą,\npaląc miłością i idąc w słońce.\n" +
                "A za Tobą Franciszku tysiące, tysiące.\nA jednym z nich jestem ja. Aaa...\n\n",
        "1. Dawno temu- tak rozpoczął pewien brat z zapałem\ntę historię, którą starsi jedynie pamiętali -\n" +
                "było miasto zwane Gubbio, bogate i wspaniałe,\na w nim ludzie, co nikogo oprócz siebie nie kochali.\n\n" +
                "2. Choć w dostatku żył każdy i żadna potrzeba\nnie spędzała nigdy snu z niczyich powiek.\n" +
                "Lecz na próżno żebrak prosił tutaj o kawałek chleba,\npróżno pytał o gospodę utrudzony wędrowiec.\n\n" +
                "3. Dnia pewnego powstał w mieście wielki płacz i trwoga\ni kazali zamknąć bramy rajcy wraz z burmistrzem.\n" +
                "Nagle Gubbio przypomniało sobie o istnieniu Boga,\no swych grzechach nagle przypomnieli sobie wszyscy.\n\n" +
                "4. Miasto było zewsząd lasami otoczone.\nW nich pojawił się wilk straszny, ogromna czarna bestia.\n" +
                "Zżerał owce, jagnięta, zżerał bydło i konie,\nW owym dniu zaś porwał kilku kupców spod bram miasta.\n\n" +
                "5. Właśnie wtedy, gdy nadziei nikt już nie miał na to,\nże odejdzie precz zły wilk, a z nim lęk, trwoga i rozpacz.\n" +
                "Poza mury, które wkoło otaczały miasto,\nwszedł święty Franciszek i bardzo się zatroskał.\n\n" +
                "6. Ulitował się nad ludźmi i tak oznajmił rajcom:\n„Pójdę w góry, by odnaleźć złego brata wilka”.\n" +
                "I poszedł tak, jak stał, mimo przestróg mieszkańców,\ngdy go znalazł, podniósł rękę i dotknął jego pyska\n\n" +
                "Ref. Bracie wilku, Pan Bóg\nstworzył taki piękny cały świat.\nBracie wilku, czemu w sercu swoim\nkryjesz jeszcze wciąż tyle zła?\n" +
                "Odmień życie, napraw krzywdy\nwyrządzone wokół wszystkim,\nulecz duszę swoją chorą z nienawiści!\n\n" +
                "7. Gdy tak święty Franciszek upominał złego wilka,\nten, o dziwo, spokorniał i ze wstydu przymknął oczy.\n" +
                "A od żalu wielkiego, spod powiek, łez kilka\nuroniło wilczysko na Biedaczyny stopy.\n\n" +
                "8. Kiedy wszyscy mieszkańcy zebrali się na rynku,\nby zobaczyć rozbójnika potulnego jak baranek,\n" +
                "wtedy święty Franciszek uniósł w górę rękę\ni wygłosił ludziom z Gubbio takie kazanie:\n\n" +
                "Ref. Bracia wilcy! Pan Bóg\nstworzył taki piękny cały świat.\nBracia wilcy, czemu serca wasze\n" +
                "kryją jeszcze wciąż tyle zła?\nProście Pana! On przebaczy\n" +
                "i zapomni grzechy wszystkim.\nOn uleczy wasze serca chore z nienawiści!\n\n" +
                "9. Nawrócili się mieszkańcy chciwi i obłudni,\nporuszeni aż do głębi kazaniem świętego.\n" +
                "A wilk do końca życia mieszkał w Gubbio razem z ludźmi,\nbo bardzo kochał dzieci i zabawę w chowanego.\n\n" +
                "10. „Ale to jeszcze nie koniec mojej opowieści” -\nstary brat pokiwał głową i pogłaskał siwą brodę –\n" +
                "„Spójrzcie, ile dzisiaj w świecie krzywd, łez i boleści,\nbo nie bratem, ale wilkiem człowiekowi bywa człowiek!”\n\n",
        "Ref. Baranku, Baranku, daj mi zmartwychwstanie.\nBaranku, Baranku, daj mi zmartwychwstanie.\n\n" +
                "1. Z Twego boku spływa na mnie\nNajczystsza rzeka Miłości.\nW Twoich ranach, w cichym ukryciu\nChcę mieszkać na wieki.\n\n" +
                "2. Jak długo mam błądzić zraniony\nTruchleć ze strachu.\nKiedy wlejesz we mnie odwagę\nNajczystszej ofiary?\n\n" +
                "3. Trwaj we mnie, trwaj we mnie, trwaj we mnie,\nNajczystszy Baranku.\nNiech oblicze mego Pana\nPochyla się zawsze nade mną.\n\n",
        "1. Pan kiedyś stanął nad brzegiem,\nSzukał ludzi gotowych pójść za Nim;\nBy łowić serca słów Bożych prawdą.\n\n" +
                "Ref. O Panie, to Ty na mnie spojrzałeś,\nTwoje usta dziś wyrzekły me imię.\n" +
                "Swoją barkę pozostawiam na brzegu,\nRazem z Tobą nowy zacznę dziś łów.\n\n" +
                "2. Jestem ubogim człowiekiem,\nMoim skarbem są ręce gotowe\nDo pracy z Tobą i czyste serce.\n\n" +
                "3. Ty, potrzebujesz mych dłoni,\nMego serca młodego zapałem\nMych kropli potu i samotności.\n\n" +
                "4. Dziś wypłyniemy już razem\nŁowić serca na morzach dusz ludzkich\nTwej prawdy siecią i słowem życia.\n\n",
        "Baruh haszem hamaszija Jeszua,\nBaruh haszem Adonai. /x4\n\nWysławiam Imię Mesjasza, Jezusa,\nwysławiam Imię Pana. /x4\n\n",
        "1. Bądź Królową moich czystych myśli.\nUchroń przed brudem me serce.\n" +
                "Nie pozwól kochać świat bez krzyża.\nBądź ze mną gdy czekam na niebo wieczne.\n\n" +
                "Ref. Matko łaski pełna, pod Twoją obronę nas weź.\nAby wiara nasza chwałą była Bogu.\n\n" +
                "2. Bądź Królową moich snów nadziei.\nUchroń przed fałszem mą duszę.\n" +
                "Nie pozwól w pieniądz jej zamienić.\nBądź ze mną, gdy czekam na niebo wieczne.\n\n" +
                "3. Bądź Królową moich dni i nocy.\nUchroń przed zdradą me czyny.\n" +
                "Nie pozwól w rozpacz je zamienić.\nBądź ze mną gdy czekam na niebo wieczne.\n\n",
        "1. Bądź mi litościw, Boże nieskończony,\nwedług wielkiego miłosierdzia Twego!\n" +
                "Według litości Twej niepoliczonej,\nchciej zmazać mnóstwo przewinienia mego.\n\n" +
                "2. Obmyj mnie z złości, obmyj tej godziny,\noczyść mnie z brudu, w którym mnie grzech trzyma.\n" +
                "Bo ja uznaję wielkość mojej winy\ni grzech mój zawsze przed mymi oczyma.\n\n" +
                "3. Odpuść przed Tobą grzech mój popełniony,\nboś przyrzekł, że ta kary ujdzie głowa,\n" +
                "którąć przyniesie grzesznik uniżony,\nby nie mówiono, że nie trzymasz słowa.\n\n" +
                "4. Chciej mi otworzyć usta moje, Panie,\nbym śpiewał chwałę Twojego Imienia.\n" +
                "Jeśli chcesz ofiar, wszak na nie mnie stanie,\nlecz nie tak miłe są całopalenia.\n\n" +
                "5. Ofiara Bogu - żalem zdjęta dusza.\nSerce skruszone i upokorzone,\n" +
                "to do litości najprędzej Go wzrusza,\nte dary miłe przed Nim położone.\n\n",
        "Bądź pozdrowiony Gościu nasz,\nw radosne progi nasze wejdź!\nMy zapalimy zamiast lamp, szczęśliwe ognie naszych serc.\n\n",
        "Będę chwalił Pana na wieki, chwalił Pana na wieki,\nbez końca me usta niech wielbią Go. /x2\n" +
                "Wysławiaj Pana ze mną\ni razem wywyższajmy Jego Imię.\n\n",
        "Ref. Będę Cię sławił, o Panie, z całego mego serca\nBo usłyszałeś, o Boże, słowa moich ust.\n" +
                "Będę śpiewał Ci pieśni, wobec aniołów,\nOddam Ci pokłon ku Twemu świętemu przybytkowi.\n\n" +
                "1. I będę dziękować Twojemu imieniu\nZa łaskę Twoją i wierność.\nBo wywyższyłeś ponad wszystko\nTwoje Imię i obietnicę.\n\n" +
                "2. Gdy Cię wzywałem, wysłuchałeś mnie,\nPomnożyłeś, o Panie, siłę mojej duszy.\n" +
                "Wszyscy królowie ziemi będą Ci dziękować,\nGdy usłyszą słowa ust Twoich.\n\n" +
                "3. Będą opiewać drogi Pańskie.\nPrawdziwie, chwała Pańska jest wielka.\n" +
                "Prawdziwie, Pan jest łaskawy dla pokornego,\nPyszałka zaś dostrzega z daleka.\n\n" +
                "4. Gdy chodzę wśród utrapienia\nTy, o Panie, zapewniasz mi życie\n" +
                "Wbrew gniewowi moich wrogów\nWyciągasz do mnie swą rękę.\n\n" +
                "5. Twoja prawica mnie wybawia,\nPan za mnie wszystkiego dokona..\n" +
                "Panie, na wieki trwa Twoja łaska,\nNie porzucaj dzieła rąk Twoich!\n\n" +
                "6. Będę Cię sławił z całego serca,\nBo usłyszałeś słowa ust moich\nBędę śpiewał Ci wobec aniołów\nWobec aniołów uwielbiać Cię będę!\n\n",
        "Ref. Będę śpiewał na cześć Pana,\nktóry dobrem nasyca mnie.\nBędę śpiewał na cześć Pana,\nOn nasyca dobrem życie me.\n\n" +
                "1. Prawo Pańskie jest doskonałe i pokrzepia duszę,\nświadectwo Pana niezawodne, uczy prostaczka mądrości.\n" +
                "Jego słuszne nakazy radują serce,\njaśnieje przykazanie Pana i olśniewa oczy.\n\n" +
                "2. Bojaźń Pana jest szczera i trwa na wieki,\nsądy Pana prawdziwe, a wszystkie razem słuszne.\n" +
                "Cenniejsze nad złoto, nad złoto najczystsze,\nsłodsze od miodu płynącego z plastra.\n\n",
        "Będę śpiewał Tobie, Mocy moja.\nTy, Panie, jesteś mą nadzieją,\nTobie ufam i bać się nie będę.\n\n",
        "Ref. Będę twoją rosą, Izraelu,\nbędę twoim słońcem, Izraelu.\nJa napoję i wysuszę,\ndam czas siewu i zbierania, Izraelu.\n\n" +
                "1. Będę twoją rosą, Izraelu,\nprzyjmij mnie w twe suche źródła.\nBędę twoją rzeką, Izraelu,\nwsłuchaj się w moje śpiewanie, Izraelu.\n\n" +
                "2. Będę twoim dniem, Izraelu,\na ty mnie wypełnisz sobą.\nbędę Twoją mocą, Izraelu,\nw smutny sen zawsze pójdę z Tobą, Izraelu.\n\n" +
                "3. Będę zawsze z Tobą, Izraelu,\nu bram wiernie będę czekał.\nBędę zawsze z Tobą, Izraelu,\ndla mnie nigdy nie jest za późno, Izraelu.\n\n",
        "Będziemy tańczyć, będziemy śpiewać,\nbędziemy klaskać w dłonie swe.\nKiedy nasz Pan stanie z nami twarzą w twarz,\nspotkamy się w nowym Jeruzalem.\n" +
                "Będziemy tańczyć, będziemy śpiewać,\nbędziemy klaskać w dłonie swe.\nKiedy nasz Pan w białych szatach stawi nas\nprzed Ojcem swym, przed Adonai.\n" +
                "Nadejdzie dzień, w którym wszystko spełni się,\nco dawno już objawił Bóg.\nWięc mocno trwaj, nigdy nie poddawaj się,\nnie poddawaj się.\n\n",
        "1. Wiem, że jest więcej,\ntęsknię nie tylko ja.\nTy także czekasz,\nby móc zobaczyć moją twarz.\nNiebo i ziemia w jedno połączą się\nGdy przyjdzie Twe królestwo\n\n" +
                "Ref. Blisko tak.\nNie jak w zagadce,\nnie jak w zwierciadle,\nlecz twarzą w twarz.\nPoznam Cię\nNie jak we śnie,\nlecz tak jak Ty mnie znasz\n\n" +
                "2. W porannym powiewie\npoczuję zapach Twój\nUsłyszę Twój szelest\nUtonę w zachwycie na widok Twój\nW naszym ogrodzie będziemy przechadzać się\nGdy przyjdzie Twe królestwo\n\n",
        "Blisko, blisko, blisko jesteś, Panie mój,\nblisko tak, że czuję tchnienie Twe.\nI przychodzisz do mnie, już dotykasz mnie,\nłaską przebaczenia karmisz moją duszę.\n\n",
        "Ref. Błogosław duszo moja Pana\nWszystko co we mnie Jego najświętsze imię.\nBłogosław duszo moja Pana,\nJego dobroci nigdy nie zapominaj. /x2\n\n" +
                "1. On odpuszcza wszystkie twoje grzechy,\nLeczy wszystkie twoje choroby.\nOkazuje Swoje miłosierdzie,\nOn ratuje Ciebie od zguby.\nOn napełnia twoje życie dobrem,\n" +
                "Aż odnowi się twoja młodość jak u orła.\nNie odpłaca według grzechów,\nNie chce wiecznie gniewać się.\nOn pamięta żeśmy prochem,\nOn najlepiej wie!\n\n" +
                "2. Tak jak ojciec nad swoimi dziećmi,\nPan lituje się nad pokornym.\nOn oddala od nas nasze winy,\nJak daleko wschód od zachodu.\nJak wysoko niebo jest nad ziemią,\n" +
                "Tak jest wielka dobroć dla ludzi którzy się Go boją.\nDni człowieka są jak trawa,\nkwitnie On jak polny kwiat.\nKiedy mocniej wiatr zawieje,\nZnika po nim ślad!\n" +
                "Błogosławcie Pana - wszyscy aniołowie!\nBłogosławcie Pana - wszyscy Jego ludzie!\nBłogosławcie Pana - wszystkie Jego działa!\nBłogosławcie Go!\n\n",
        "Ref. Błogosław Pana, Izraelu,\nchwal Go, wywyższaj na wieki.\nBłogosław Pana, Izraelu,\nze śmierci wydobył cię.\n\n" +
                "1. Wszystkie dzieła Pańskie,\tbłogosławcie Pana.\nChwalcie Go i wywyższajcie,\tbłogosławcie Pana.\nAniołowie Pańscy,\tbłogosławcie Pana.\nChwalcie Go niebiosa,\tbłogosławcie Pana.\n\n" +
                "2. Wszystkie wody podniebne,\nWszystkie potęgi,\nSłońce i księżycu,\nGwiazdy na niebie,\n\n" +
                "3. Kapłani Pańscy,\nWszyscy słudzy Pańscy,\nDuchy i dusze sprawiedliwych,\nŚwięci i pokornego serca,\n\n" +
                "4. Dusze i rosy,\nWszystkie wichry niebieskie,\nOgniu i żarze,\nChłodzie i upale,\n\n" +
                "5. Rosy i szrony,\nMrozy i zimna,\nŚniegi i lody,\nDnie oraz noce,\n\n" +
                "6. Światło i ciemności,\nBłyskawice i chmury,\nGóry i pagórki,\nWszelkie rośliny na ziemi,\n\n" +
                "7. Wieloryby i morskie stworzenia,\nWszelkie ptaki powietrzne,\nDzikie zwierzęta i trzody,\nSynowie ludzcy,\n\n",
        "1. Błogosławcie Pana wszystkie Jego dzieła,\nktóre by nie kwitły, gdyby nie cierpienie.\nBłogosławcie Pana.\n\n" +
                "2. Błogosławcie Pana wszystkie łzy i żale,\nkażda moja słabość i upokorzenie.\nBłogosławcie Pana.\n\n" +
                "3. Błogosławcie Pana wszystkie moje rany,\nna które Duch Święty staje się balsamem.\nBłogosławcie Pana.\n\n" +
                "4. Niech Cię błogosławią wszystkie me niemoce,\nból skrzętnie skrywany po bezsennej nocy.\nNiech Cię błogosławią.\n\n" +
                "5. Błogosławcie Pana, że mu ufam jeszcze,\nże mnie wciąż uzdrawia łaski swojej deszczem.\nBłogosławcie Pana.\n\n",
        "1. Wznoszę swe oczy ku górom, skąd\nprzyjdzie mi pomoc.\nPomoc od Pana, wszak Bogiem On,\nmiłosiernym jest.\n\n" +
                "2. Kiedy zbłądzimy sam szuka nas,\nby w swe ramiona wziąć.\nRany uleczyć Krwią swoich ran,\nnowe życie tchnąć!\n\n" +
                "Ref. Błogosławieni miłosierni,\nalbowiem oni, Miłosierdzia dostąpią.\n\n" +
                "3. Gdyby nam Pan nie odpuścił win,\nktóż ostać by się mógł.\nLecz on przebacza, przeto i my\nczyńmy jak nasz Bóg.\n\n" +
                "4. Pan Syna Krwią zmazał wszelki dług,\nSyn z grobu żywy wstał.\nPanem jest Jezus mówi w nas Duch,\nnich to widzi świat.\n\n" +
                "Bridge: Więc odrzuć lęk i wiernym bądź,\nswe troski w Panu złóż.\nI ufaj bo zmartwychwstał i wciąż, żyje Pan, Twój Bóg!\n\n" +
                "Ref. Błogosławieni miłosierni,\nalbowiem oni, Miłosierdzia dostąpią.\n\n",
        "Niech Pan Wam błogosławi\ni niech Was strzeże. /x2\nNiech Wam okaże oblicze Swoje\ni zmiłuje się nad Wami. /x2\n" +
                "Niech obróci ku Wam twarz Swoją\ni obdarzy pokojem.\nNiech Pan Wam błogosławi. Amen.\n\n",
        "1. Błogosławię Cię, kiedy ziemia wydaje plon\nGdy obfitość Panie zlewasz swą, błogosławię Cię.\nBłogosławię Cię, gdy pustynia w krąg otacza mnie\n" +
                "Choćbym nawet przez pustkowie szedł,\nbłogosławię Cię\nZa Twą łaskę, którą zsyłasz, ja chwalę Cię.\nChoćby ciemność mnie przykryła, ja powiem, że:\n\n" +
                "Ref. Panie błogosławię imię Twe,\nbłogosławię Cię\nPanie błogosławię imię Twe,\nbłogosławię święte imię Twe.\n\n" +
                "2. Błogosławię Cię, kiedy słońca blask oświeca mnie.\nTo, co dajesz mi najlepsze jest, błogosławię Cię.\nBłogosławię Cię, kiedy drogę moją znaczy cień.\n" +
                "Chociaż cierpię, pragnę chwalić Cię,\nbłogosławię Cię.\nZa Twą łaskę, którą zsyłasz, ja chwalę Cię.\nChoćby ciemność mnie przykryła, ja powiem, że:\n\n" +
                "Bridge: Ty dajesz z woli swej, zabierasz kiedy chcesz.\nW mym sercu zawsze chcę, błogosławić Cię. /x2\n\n",
        "1. Błogosławię Imię Twoje, Jezu,\nnigdy nie przestanę wielbić Cię.\nTyś jest Mesjasz, Zbawca,\nŚwięty Bóg Izraela.\n\n" +
                "2. Błogosławię Imię Twoje Panie.\nImię które daje życie mi.\nTyś jest Mesjasz, Zbawca,\nświęte jest Imię Twe.\n\n" +
                "3. Imię Twe jest ponad wszelkie imię.\nImię Twe przynosi wolność nam.\nZ niewoli wybawia narody,\njest życiem i zbawieniem mym.\n\n",
        "Ref. Błogosławiona Dziewico nad dziewicami,\nBogarodzico ze wszystkich matek wybrana,\nMatko Chrystusa i Matko ludzi.\nDaj nam Twego Syna,\ndaj nam Twego Syna.\n\n" +
                "1. Spośród wszystkich niewiast na świecie,\nPan Cię wybrał dla siebie,\nby na wieki rozbłysło na ziemi\nBoże światło - Twój Syn.\n\n" +
                "2. Niby źródło wody najczystszej\nspływa łaska na Ciebie.\nBóg ukochał Cię czułą miłością,\nZbawcę przez Ciebie dał.\n\n" +
                "3. W Twoim sercu nie ma przeszkody,\nby w nim miłość rozkwitła.\nBoża miłość sprawiła, że w Tobie\ncud Wcielenia się stał.\n\n" +
                "4. Cały wszechświat wpatruje się w Ciebie,\nwielbiąc Syna Twojego.\nDzięki Tobie, wśród ludu swojego\nchciał zamieszkać sam Bóg.\n\n" +
                "5. Pielgrzymujesz wraz z nami po świecie,\nbudzisz w sercach nadzieję.\nPośród krzyży i łez bądź nam Matką,\nSyna Twego nam daj.\n\n",
        "1. Błogosławiona jesteś Maryjo\ni wywyższona przez Pana.\nOn wielkich rzeczy w Tobie dokonał,\nNiepokalana.\n\n" +
                "2. Ty, któraś Słowo z wiarą przyjęła,\nby w Tobie Ciałem się stało.\nZrodziłaś światu Jutrzenkę Nową,\nPrzedwieczną Światłość.\n\n" +
                "3. Bądź nam obroną Bogarodzico,\ngdy się błąkamy w ciemności.\nDo światła wiary prowadź na nowo,\nDrogą świętości.\n\n",
        "Ref. Mój grzech dziś leży u mych stóp,\nMój grzech dziś zaczął służyć mi.\nŚmierć straciła oścień swój,\nPan złamał strzałę śmierci dziś.\nMój grzech dziś leży u mych stóp,\nDo mnie nie należy już.\n\n" +
                "1. Przypomina mi, że jest życie,\nA źródło tego życia nie bije we mnie.\nZnam miejsce w sercu Twym, miejsce wśród braci.\nNie płaczę już nad sobą, nad mymi ranami.\n\n" +
                "2. Przyjaźnię się ze słabością mą, Nie jestem skazany.\nPokora we mnie rodzi się, słodka zależność.\nBłogosławiona wina jest, skoro Ty\nMusiałeś zejść na ziemię, z miłości do mnie\n\n",
        "Ref. Błogosławiony jest nasz Pan,\nuwielbiony i wywyższony na wieki.\n\n" +
                "1. Aniołowie Pańscy błogosławcie Pana.\nUwielbiajcie Pana, wywyższajcie na wieki.\nCałe niebiosa błogosławcie Pana.\nChwalcie i wywyższajcie Go.\n\n" +
                "2. Wszystkie wody pod niebem błogosławcie Pana.\nUwielbiajcie Pana, wywyższajcie na wieki.\nWszystkie potęgi błogosławcie Pana.\nChwalcie i wywyższajcie Go.\n\n" +
                "3. Słońce i księżycu błogosławcie Pana.\nUwielbiajcie Pana, wywyższajcie na wieki.\nGwiazdy na niebie błogosławcie Pana.\nChwalcie i wywyższajcie Go.\n\n" +
                "4. Deszcze i rosy błogosławcie Pana.\nUwielbiajcie Pana, wywyższajcie na wieki.\nWszystkie wichry niebieskie błogosławcie Pana.\nChwalcie i wywyższajcie Go.\n\n" +
                "5. Ogniu i żarze błogosławcie Pana.\nUwielbiajcie Pana, wywyższajcie na wieki.\nUpale i chłodzie błogosławcie Pana.\nChwalcie i wywyższajcie Go.\n\n" +
                "6. Światło i ciemności błogosławcie Pana.\nUwielbiajcie Pana, wywyższajcie na wieki.\nNoce i dni błogosławcie Pana.\nChwalcie i wywyższajcie Go.\n\n",
        "1. Błogosławiony bądź, Ojcze,\nbo w nieskończonej miłości\ndałeś nam Syna Swojego\ni w mocy Ducha Świętego\n" +
                "przyjął On ciało z Maryi\nprzed dwoma tysiącami lat.\nOn stał się naszym Zbawieniem,\nwierny w miłości do końca\n" +
                "i naszą drogą do Ciebie,\nku nowej ziemi i niebu,\ngdzie w Twych otwartych ramionach\nstrudzony pielgrzym odpocznie.\n\n" +
                "Ref. Uwielbiony bądź, Ojcze nasz, Tobie chwała, cześć!\nPrzez Jezusa w Duchu Świętym, Tobie chwała, cześć!\n" +
                "Jedyny Bóg, jedyny Pan, jedyny Król.\nBłogosławimy Święte Imię Twe\ni wywyższamy Cię.\n\n" +
                "2. Pomóż nam wrócić do Ciebie,\nporzucić swe dawne drogi.\nA radość pośród narodów\nprzez pojednanie zagości\n" +
                "i wzniesie się pieśń pokoju\npod niebem całego świata.\nDaj, byśmy byli posłuszni\nnatchnieniom Twojego Ducha.\n" +
                "Karmieni Słowem Jezusa,\nnaśladowali Go z wiarą.\nStojąc u stóp Jego Krzyża,\npili ze źródła Twej łaski.\n\n" +
                "3. Mocą swojego Ducha\nwspomagaj Twój Święty Kościół,\nbyśmy głosili Chrystusa\nprzez wszystkie dni swego życia\n" +
                "i podążali ku Tobie,\ndo domu naszego Ojca.\nSpraw byśmy byli jednością,\nby świat uwierzył w Jezusa.\n" +
                "Niech wszyscy ludzie odkryją,\nże są Twoimi dziećmi.\nNiech dla każdego człowieka\ndzień każdy będzie źródłem łaski.\n\n",
        "1. Witaj, Krzyżu święty, witaj, Chlubo ziemi,\nNadziejo jedyna, która dajesz radość.\n\n" +
                "Ref. Błogosławiony krzyżu Chrystusa\nTy sam byłeś godzien nosić Króla Niebios\n" +
                "Znaku Bożej męki i triumfu Pana\nUsunąłeś ciemność, przywróciłeś radość\n\n" +
                "2. Znaku odkupienia i ratunku w trwodze\nNa Tobie zawisło życie, życie wszystkich ludzi.\n\n" +
                "3. Drzewem pokonani, Drzewem wyzwoleni,\nSławimy Cię dzisiaj, Drzewo życiodajne.\n\n" +
                "4. Drzewo godne sławy, wielki Skarbie świata\nŚpiewamy Ci hymny pełne uwielbienia.\n\n" +
                "5. Bogu Ojcu chwała, w Krzyżu Jego Syna,\nDuchowi miłości wspólne uwielbienie.\n\n" +
                "6. Drzewo triumfalne niechaj obdarzy ludzi\nPokojem wieczności, łaską i weselem.\n\n",
        "„Bo góry mogą ustąpić /x2\ni pagórki się zachwiać,\nale miłość Moja, miłość Moja,\nnigdy nie odstąpi od ciebie” - mówi Pan. /x2\n\n",
        "1. Jezus, Jezus, niech jasność Twa ogarnia mnie.\nJezus, Jezus, w blasku Twoim pragnę żyć.\n\n" +
                "Ref. Bo Imię Twe tak wspaniałe jest, tak potężne jest,\nwywyższam Cię.\nBo Imię Twe pełne blasku jest,\nwciąż uwalnia mnie, w Nim zbawienie jest.\n\n" +
                "2. Olśniewasz mnie, przenikasz mnie, Duch Twój wciąż odnawia mnie.\nPrzed obliczem Twym pragnę stanąć dziś, z bojaźnią wielbić Cię.\n\n",
        "Bo jak śmierć potężna jest miłość,\na zazdrość jej nieprzejednana jak Szeol.\nŻar jej to żar ognia,\n" +
                "Płomień Pański.\nWody wielkie nie zdołają ugasić miłości,\nnie zatopią, nie zatopią jej rzeki.\n\n",
        "Bo nikt nie ma z nas tego, co mamy razem,\nkażdy wnosi ze sobą to co ma (najlepszego).\n" +
                "Zatem, aby wszystko mieć,\npotrzebujemy siebie razem,\nbracie, siostro, ręka w rękę z nami idź!\n\n",
        "Ref. Bogu Jedynemu, Jezusowi, chwała i cześć.\nPanu Wszechmocnemu, Jezusowi,\nchwała, cześć na wieki.\n\n" +
                "1. On uczynił niebiosa,\nrozpostarł ziemi krąg.\nKróluje nad nami,\nwznieśmy więc serce swe.\n" +
                "Przyjdźmy przed Jego tron,\nśpiewając ze wszystkich sił,\nśpiewając ze wszystkich sił.\n\n" +
                "2. On jest Królem chwały,\nświat podnóżkiem jego stóp.\nOdziany w majestat\nwszedł Bóg, uniżmy się.\n" +
                "Podejdźmy w pokorze,\nby złożyć Jemu hołd,\nby złożyć Jemu hołd.\n\n",
        "1. Bogu wyśpiewam, Bogu na niebie,\nBogu wyśpiewam wdzięczności psalm.\nJahwe mi zagra na strunach światła\ni głos popłynie pod nieba dach.\n\n" +
                "Ref. Myślisz, że cisza moim domem jest,\nmówisz, że nie znam muzyki.\t\nZobacz, dla Pana dzisiaj śpiewam psalm,\nmój głos płynie do gwiazd, płynie do gwiazd.\n\n" +
                "2. Bogu wyśpiewam, Bogu na niebie,\nBogu wyśpiewam wdzięczności psalm.\nW dłoniach zaniosę radość, cierpienie,\nw gestach zaklęte słowa upojne patrz.\n\n" +
                "Ref. Słuchaj jak ręce moje wielbią dziś\nJahwe, Boga muzyki.\nCisza przemienia się w modlitwy cud,\nmój głos płynie do gwiazd, płynie do gwiazd.\n\n",
        "Bonum est praestolari cum silentio salutare Dei.\n\n",
        "Boże, Ojcze Miłosierny, naucz kochać nas.\nBoże, Ojcze, poślij Ducha, niech poucza nas.\n" +
                "Ojcze, wołam, prowadź mnie!\nW Twoich dłoniach już na wieki nie ulęknę się.\n" +
                "Boże mój, Panie mój, przytul mnie do swego serca.\nBoże mój, Panie mój, naucz kochać tak, jak Ty.\n" +
                "Boże mój, Panie mój, przytul mnie do swego serca.\nBoże mój, Panie mój, naucz kochać tak, jak Ty.\n\n",
        "1. Boże pełen w niebie chwały,\na na krzyżu pomarniały.\nGdzieś się skrywał i gdzieś bywał,\nżem Cię nigdy nie widywał,\nżem Cię nigdy nie widywał.\n\n" +
                "2. Wiem, że w moich klęsk czeluści,\nmoc mnie Twoja nie opuści.\nCzyli razem trwamy dzielnie,\nczy też każdy z nas oddzielnie,\nczy też każdy z nas oddzielnie.\n\n" +
                "3. Mów, co czynisz w tej godzinie,\nkiedy dusza moja ginie?\nCzy łzę ronisz potajemną,\nczy też giniesz razem ze mną. /x2\n\n",
        "1. Boże wszechmocny, w Trójcy niepojęty,\nCiebie wielbimy pokłonem i pieśnią,\nTy bowiem jesteś celem i pragnieniem\nnaszego ducha.\n\n" +
                "2. Stwórco wszystkiego,\nOjcze, Źródło życia,\nTy nas kształtujesz\n" +
                "na swe podobieństwo.\nUdziel nam wiary,\nktóra niech zasłuży\nna Twą nagrodę.\n\n" +
                "3. Z Ojca Zrodzony,\nblasku Jego chwały,\nzowiesz i czynisz\n" +
                "nas braćmi swoimi.\nTyś winoroślą,\nmy jej gałązkami,\nwięc daj nam życie.\n\n" +
                "4. Duchu płomienny,\nŚwiatło i Miłości,\nwładasz stworzeniem\n" +
                "z dobrocią i mocą.\nOdnów umysły,\nrozpal swoim żarem\nczłowiecze serca.\n\n" +
                "5. Gościu najmilszy,\nBoże jeden w Trójcy,\ndaj nam Cię kochać\n" +
                "i spełniać Twą wolę,\nbyśmy na wieki\nzjednoczeni z Tobą,\nwielbili Ciebie. /x3\n\n",
        "Boże mój, gdzie jesteś. Ciało me usycha.\nZmysły me umarły, pozwól znaleźć się już\n\n",
        "Boże, Boże mój, szukam Ciebie. /x2\n\nZa Tobą tęskni moja dusza i moje ciało,\njak zeschła ziemia, spragniona, bez wody. /x2\n\n",
        "Ref. Boże, mój Boże, dlaczego\nOpuściłeś mnie w moim utrapieniu.\nWołałem do Ciebie przez cały dzień,\nAle Ty zasłoniłeś swoje uszy!\n\n" +
                "1. Boże zbaw mnie w Imię Swoje,\nSwoją mocą broń mojej sprawy.\nBoże nakłoń swego ucha Na słowa ust moich.\n" +
                "Bo powstają przeciwko mnie pyszni,\nA wrogowie moi czyhają na me życie.\nNie mają Boga przed swymi oczami, Chcą mojej śmierci.\n\n" +
                "2. Ale oto Bóg mi dopomaga,\nPan sam podtrzymuje me życie.\nBędę sławił Twe Imię, bo jest dobre, Bo mnie ratuje.\n" +
                "Ja zaś jak oliwka, co kwitnie w domu Bożym,\nZaufam na wieki łaskawości Boga.\nChcę Cię wysławiać za to, coś mi uczynił, I polegać na Twym imieniu.\n\n",
        "Boże, Twa łaska nad nami jest,\nTwoja miłość przychodzi wciąż.\nDziałasz w mocy pośród nas,\nprzenikasz serca, gładzisz grzech\n\n" +
                "Ref. My chcemy więcej Ciebie, więcej łaski Twej.\nPragniemy więcej mocy, więcej miłości Twojej.\n" +
                "Pragniemy więcej Ciebie, więcej łaski Twej.\nPragniemy więcej mocy, więcej miłości Twojej.\n\n" +
                "Więcej Ciebie, więcej łaski.\nWięcej Ciebie, więcej łaski…\n\n",
        "Ref. Boży Baranku, Boży Baranku.\nDaj mi życie ukryte w Twoim ciele.\nDaj mi moc płynącą z Twego Ducha.\nAbym Cię uwielbiał.\n\n" +
                "1. Daj mi swe Ciało, abyś od dziś Ty żył we mnie.\nDaj mi swą Krew, by było we mnie Twoje życie.\nAbym Cię uwielbiał.\n\n" +
                "2. Niech Twój oddech wypełni moje wnętrze.\nNiech Twe słowa się staną źródłem mej nadziei.\nAbym umiał pójść za Tobą.\nAbym się nie lękał utracić życie. /x2\n\n" +
                "3. Chcę nasycić Twe pragnienia moją małą miłością.\nZrań me serce, abym kochał braci.\nAbym umiał pójść za Tobą.\nAbym się nie lękał utracić życie. /x2\n\n" +
                "4. Dotknij moich rąk, abym okazał miłość braciom.\nDotknij moich nóg, abym głosił Ciebie.\nDotknij mego czoła, abyś był jedyną moją Mądrością.\n" +
                "Abym umiał pójść za Tobą.\nAbym się nie lękał utracić życie. /x2\n\n",
        "1. Bóg dał czas na nadzieję,\nczas na siew i czas na żniwo,\nczas na taniec, na śpiew\ni na ciszę w kościołach widnych.\n" +
                "Bóg dał czas, żeby kochać,\nżeby domy budować na skale.\nOto droga, by iść, coraz dalej i dalej.\n\n" +
                "Ref. Mogę dzielić się wiatrem i chlebem,\ntyle wspólnych znajdować spraw.\nA na miłość pod Twoim niebem\ntyle miejsc.\n\n" +
                "2. Kiedy Bóg przyjdzie sądzić nasze czyny i naszą marność,\ndamy kłosy, a On spali plewy, oddzieli ziarno.\n" +
                "Będzie sądzić z miłości tej najprostszej i tej niełatwej.\nSądzić tylko z miłości, to aż nadto, aż nadto.\n\n",
        "Bóg jest Miłością, miejcie odwagę żyć dla Miłości.\nBóg jest Miłością, nie lękajcie się.\n\n",
        "Miłość mnie pochwyciła\nMiłość mnie ogarnęła\nMiłość mnie odmieniła\nMiłość to moja siła\n\nTy Panie moje serce znasz,\n" +
                "prowadzisz do świątyni bram.\nPrzygarniasz mnie, bo chcesz bym odkrył, że\nto Bóg Miłością jest! Miłością jest!\n\n",
        "Bóg tak umiłował świat,\nże Syna swego Jednorodzonego dał,\naby każdy, kto w Niego wierzy,\nnie zginął, lecz życie wieczne miał.\n\n",
        "1. Bóg umierał, cicho ciekła krew.\nGwoździe dłońmi obejmował - dusił się.\n" +
                "Bóg umierał i nie widział nikt,\njak po twarzy mu spływały, spływały łzy.\n\n" +
                "Ref. A ludzie na święto spieszyli się\ni słońcem czerwonym kończył się dzień.\n" +
                "Tak mijał odkupienia czas,\nHosanna, hosanna, wybaw nas.\n\n" +
                "2. Bóg umierał, głowa zwisła mu.\nNie miał siły, by ją podnieść - nabrać tchu.\n" +
                "Bóg umierał i nie widział nikt,\njak na ziemi krzepły krople, krople krwi.\n\n" +
                "3. Bóg umierał, ziemia trzęsła się,\nlecz nie drżały ludzkie serca, serca nie.\n" +
                "Bóg umierał, oczy zaszły mgłą,\ngdy oddawał Bogu duszę, duszę swą.\n\n",
        "Bóg zasiewa święte ziarno, Alleluja.\nJego słowa są mądrością, Alleluja.\nOtwórz swe oczy, Ludu Boży.\n" +
                "Otwórz swe oczy, mówi Bóg.\nOtwórz swe uszy, Ludu Króla.\nBóg już jest tu\n\n",
        "Bóg, mój Pan, daje mi moc.\nOn swą dłonią przygarnia mnie.\nŻyciu memu nadaje sens.\nZ Nim w swym sercu kroczyć chcę.\n\n",
        "Bóg, nasz Pan, jest dobry cały czas. /x2 \nWięc uwielbij Go. /x4 \n\n",
        "1. Do kogo mówisz, komu się uśmiechasz,\nświęty Franciszku.\nu ludzi nie ma, las szumi jak rzeka,\ntaszki świergocą i słychać Twój głos:\n\n" +
                "Ref. Braciszkowie skrzydlaci\nchwalcie Boga, chwalcie Boga,\nbo czym my stworzenia zdołamy odpłacić,\n" +
                "jeśli nie piosenką ubogą.\nZa słońce jasne, niebo czyste\ni łąki zieleń, i łąki zieleń. /x2\n\n" +
                "2. Żarnowce rozsiadły się na polanie,\ntworząc złocistą plamę.\nA Ciebie jakby tłum otaczał\nw katedrze wspaniałej\ni słychać Twój głos:\n\n" +
                "3. Coś, podniósłszy w ręce, tłumaczysz,\ngestami rąk zaciekawiasz.\nAż stukać przestały w drzewa dzięcioły,\nptaki umilkły\ni słychać Twój głos:\n\n" +
                "4. Umilkł święty, zabrzmiał świergot, jak dzwonki./x3\n\n",
        "1. Bywają chwile, gdy jestem sam,\nnie mam do kogo użalić się.\nPragnę odnaleźć drogę do Ciebie,\nbo wiem, że zawsze Ty kochasz mnie.\n\n" +
                "Ref. O Królowo, o Królowo, Matko ma,\nbądź tu przy mnie, bądź ze mną znów. /x2\n\n" +
                "2. Wszystko w mym życiu jest takie proste,\nbo Ty mi Matko pomagasz w nim.\nSą jednak chwile, gdy jest mi ciężko\ni jakże trudno samemu iść.\n\n" +
                "3. Miłość do Ciebie jest jak ta gwiazda,\nktórą zapalasz, gdy smutno mi.\nWierzę, że zawsze dodasz mi mocy,\nabym mógł z Tobą na zawsze być.\n\n",
        "Cała piękna jesteś, przyjaciółko moja\ni nie ma w Tobie skazy.\nKim jest ta, co wyłania się z pustyni\n" +
                "wśród słupów dymu,\nowiana wonią mirry i kadzidła i wszelkich wonności.\n\n",
        "Ref. Cała ziemio wołaj z radości na cześć Pana,\nraduj się, wesel się.\nCała ziemio wołaj z radości na cześć Pana.\nAlleluja, Alleluja.\n\n" +
                "1. Śpiewajcie Panu pieśń nową,\nalbowiem uczynił cuda.\nZwycięstwo Mu zgotowała Jego prawica\ni święte ramię Jego.\n\n" +
                "2. Pan okazał swoje zbawienie,\nna oczach pogan objawił swą sprawiedliwość.\nWspomniał na dobroć i na wierność swoją\ndla domu Izraela.\n\n" +
                "3. Ujrzały wszystkie krańce ziemi\nZbawienie Boga naszego.\nWołaj z radości na cześć Pana cała ziemio,\ncieszcie się, weselcie i grajcie.\n\n" +
                "4. Śpiewajcie Panu przy wtórze cytry,\nprzy wtórze cytry i przy dźwięku harfy.\nPrzy trąbach i przy głosie rogu,\nna oczach Pana, Króla, się radujcie.\n\n" +
                "5. Niech szumi morze i wszystko, co w nim żyje.\nKrąg ziemi i jego mieszkańcy,\nrzeki niech klaszczą w dłonie,\ngóry niech razem wołają z radości.\n\n",
        "1. Całuj mnie pocałunkami twoich ust,\nbo miłość twa słodsza niż wino.\n" +
                "Woń twych pachnideł, olejek rozlany w Twoje Imię.\nDlatego kochają cię dziewczęta!\n\n" +
                "Ref. O Ty, którego miłuje dusza moja,\no Ty, którego pragnie moje ciało.\nPokaż mi, gdzie pasiesz swe stada,\n" +
                "bym ja już nie musiała być przybłędą,\nbym ja już nie musiała być włóczęgą\ni nie błąkała się wśród stad innych pasterzy!\n\n" +
                "2. Zabierz mnie ze sobą, pobiegnijmy.\nPociągnij mnie Królu w swoje komnaty.\n" +
                "Bardziej niż wino Twa miłość nas upaja,\no jak cudownie, cudownie kochać Ciebie!\n\n" +
                "3. I jeśli tego nie wiesz, o najpiękniejsza z niewiast,\nto idź śladami moich owiec\n" +
                "i paś tam też swoje koźlęta.\nPrzy szałasach, szałasach moich pasterzy!\n\n",
        "Całym sercem będę Ciebie sławił Panie.\nBędę opowiadał wszystkie Twoje cuda. /x2\nBędę się cieszył i radował Tobą,\n" +
                "będę śpiewał Twemu Imieniu,\nNajwyższy, Twemu Imieniu Najwyższy. /x2\n\n",
        "Chcemy Ciebie wielbić, szukać Twojej twarzy,\nwcześnie o poranku i gdy noc kładzie cień.\nTobie chcemy śpiewać, wznieść niebiańską chwałę.\n" +
                "Dając nasze życie  byś je stale zmieniać mógł.\nNiech się wzniesie dziś Twa chwała, o Panie nasz.\nTo dla Ciebie nasze serca, otwarte są.\n\n",
        "Ref. Chcę być Twoim ziarnem, abyś mnie starł,\nChcę być Twoim chlebem, abyś łamał mnie,\nChcę być winnym gronem, abyś mnie wytłoczył,\n" +
                "Chcę być Twą oliwką, abyś zmiażdżył mnie!\nIm ja będę mniejszy, Ty będziesz większy,\nTaki mały będę kochał Ciebie!\n" +
                "Gdy ja będę niczym. Ty będziesz wszystkim,\nChcę miłością stać się w Twoim sercu!\nOdpocznij we mnie Umiłowany mój,\n" +
                "Moja miłości, moje wszystko!\nOdpocznij we mnie, mój Ukochany,\nWejdę w zmartwychwstanie nie spostrzegłszy śmierci.\n\n",
        "Chcę przestąpić Jego próg\nz dziękczynieniem w sercu mym\ni w przedsionki Pana wejść chwaląc Go!\n" +
                "Bo kolejny nadszedł dzień, który dał dla mnie Bóg.\nChcę śpiewać Mu, bo Pan radością mą.\nPan radością mą, Pan radością mą\n" +
                "chcę śpiewać Mu, bo Pan radością moją jest!\nPan radością mą, Pan radością mą\nchcę śpiewać Mu, bo Pan radością mą!\n\n",
        "Ref. Chcę Cię uwielbiać, Boże mój i Królu, na wieki\nI błogosławić Imię Twe!\nKażdego dnia będę Ciebie błogosławił, mój Boże,\nI rozpowiadał cuda Twe.\n" +
                "Wielki jest Pan, godzien jest wszelkiej chwały,\nKtóż pozna jak wspaniały jest.\nWszystkie narody, Panie, głoszą Twoją potęgę,\nGłoszą potężne czyny Twe.\n\n" +
                "1. Wszystkie narody, Panie, chwałę Ci oddają,\nGłoszą wspaniałą chwałę Twojego majestatu.\nNiechaj Cię wielbią Boże wszystkie dzieła Twoje\nI niechaj święci Twoi Cię błogosławią!\n\n" +
                "2. Niechaj narody mówią o Twoim Królestwie,\nNiech usta nasze z radością głoszą chwałę Pana.\nBóg jest łagodny, miłosierny i bardzo łaskawy,\nPan jest dobry dla wszystkich. Jemu chwała na wieki!\n\n" +
                "3. Pan podtrzymuje wszystkich, którzy upadają,\nOtwiera swą rękę, nasyca wszystko, co żyje.\nPan Bóg jest wierny we wszystkich swoich słowach,\nJego chwałę śpiewajmy, Boga błogosławmy!\n\n",
        "1. Skąd weźmiemy Panie chleb?\nObok nas wielki tłum\nOto Pascha zbliża się.\nUczyń znak, uczyń cud\n\n" +
                "Ref. Chcę roznosić Twój chleb Panie\nChcę roznosić Twój chleb\nChcę by życie mnożyło się\nOto ja, poślij mnie\n\n" +
                "2. Choć nie we mnie źródła są\nWidzę we mnie Twój cud\nChleb w mych rękach mnoży się\nBy nasycić ich głód\n\n" +
                "3. Twego chleba dawaj nam\nObjaw nam życie Swe\nTyś jest pokarm, napój nasz\nTyś jest nasz życia chleb\n\n",
        "1. Jeśli chcę tego, czego nie chcesz Ty\nZatrzymaj mnie i powiedz mi, czego pragniesz\n" +
                "Jeśli idę tam, gdzie nie idziesz Ty\nZatrzymaj mnie , nie pozwól dalej iść\n\n" +
                "Ref. Chcę widzieć Cię w życiu mym wywyższonego\nChcę widzieć Cię w życiu mym\n\n" +
                "2. Cokolwiek czynię dla swojej własnej chwały\nZatrzymaj mnie i zbadaj moje serce\n" +
                "Gdy oceniam innych bez miłości\nZatrzymaj mnie i zniszcz moją pychę\n\n" +
                "3. Gdy boję się o to, co pomyślą inni\nZatrzymaj mnie, ważne jest , co myślisz TY\n" +
                "Gdy troszczę się tylko o to, co jest moje\nZatrzymaj mnie i skrusz moje serce\n\n" +
                "Bridge:\nBo tylko Twoja jest chwała Twoja jest chwała\nI tylko Tobie się kłaniam Tobie się kłaniam\n\n",
        "Chcę wywyższać Imię Twe,\nchcę zaśpiewać Tobie chwałę.\nPanie, dziś raduję się,\nbo przyszedłeś, by mnie zbawić.\n\n" +
                "Z nieba zstąpiłeś i chcesz\nprowadzić mnie.\nNa krzyżu zmarłeś, by mój\nzapłacić dług.\n" +
                "Z grobu wstałeś i dziś\nnieba Królem jesteś Ty.\nChcę wywyższać Imię Twe\n\n",
        "Ref. Chlebie najcichszy,\notul mnie swym milczeniem.\nUkryj mnie w Twojej bieli,\nwchłoń moją ciemność.\n\n" +
                "1. Przemień mnie w siebie, bym, jak Ty, stał się chlebem\nPrzemień mnie w siebie, bym, jak Ty, stał się chlebem.\n" +
                "Pobłogosław mnie, połam, rozdaj łaknącym braciom.\nPobłogosław mnie, połam, rozdaj łaknącym braciom.\n\n" +
                "2. A ułomki chleba, które zostaną.\nA ułomki chleba, które zostaną.\n" +
                "Rozdaj tym, którzy nie wierzą w swój głód.\nRozdaj tym, którzy nie wierzą w swój głód.\n\n",
        "Chlebie Żywy łamany Drzewem Krzyża,\nuwielbiamy Cię, uwielbiamy Cię.\nKrwi Przenajświętsza, uzdrowienie naszych ran,\nuwielbiamy Cię, uwielbiamy Cię.\n" +
                "Baranku Boży chodzący dolinami serc,\nuwielbiamy Cię, uwielbiamy Cię.\nJezus to nasz Pan - uwielbiajmy Go!\n\n",
        "Choćby wasze grzechy były jak szkarłat,\nnad śnieg wybieleją, zapewniam was.\n\n",
        "Chrystus Pan, Boży Syn,\nZbawca nasz zgodził się wziąć mój grzech.\na mnie umrzeć chciał, ażebym znał mój ogrom win\n" +
                "i wiedział, że krew Jego zbawia,\noczyszcza i leczy.\nWywyższony bądź Jezu, Baranku mój,\n" +
                "Tyś jedyny Odkupiciel, Tyś mój Król.\nWywyższony bądź, Boży Baranku,\nprzed Twym tronem dziś najwyższy składam hołd.\n\n",
        "Ref. Chrystus Pan karmi nas\nSwoim świętym Ciałem,\nchwalmy Go na wieki.\n\n" +
                "1. Duchem całym wielbię Pana,\nBoga, Zbawcę Jedynego.\nBo w Nim samym odnajduję\nwszystką radość życia mego.\n\n" +
                "2. Wielbię, bo chciał wejrzeć z nieba,\nna swą sługę uniżoną.\nBy mnie odtąd wszyscy ludzie\nmogli zwać Błogosławioną.\n\n" +
                "3. Sprawił we mnie wielkie dzieła,\nw swej dobroci niepojętej.\nOn Wszechmocny, On Najwyższy,\nOn sam jeden, zawsze święty.\n\n" +
                "4. On, który przez pokolenia\npozostaje miłosierny,\nwobec tego, kto Mu służy\ni chce zostać jemu wierny.\n\n" +
                "5. On, który przez pokolenia\npozostaje miłosierny,\nwobec tego, kto Mu służy\ni chce zostać jemu wierny.\n\n" +
                "6. W mocy Jego odjąć władzę,\na wydźwignąć pokornego.\nWszystkich głodnych zaspokoić,\ngłodem wstrząsnąć bogatego.\n\n" +
                "7. On się ujął za swym ludem,\ndziećmi wiary Abrahama.\nPomny na swe miłosierdzie,\nobietnicy swej nie złamał.\n\n",
        "1. Chrystus Zmartwychwstan jest,\nnam na przykład dan jest,\niż mamy zmartwychpowstać,\nz Panem Bogiem królować. Alleluja.\n\n" +
                "2. Leżał trzy dni w grobie,\ndał bok przebić sobie.\nBok, ręce, nogi obie\nna zbawienie Tobie. Alleluja.\n\n" +
                "3. Trzy Maryje poszły,\ndrogie maści niosły.\nChciały Chrysta pomazać,\nJemu cześć i chwałę dać. Alleluja.\n\n" +
                "4. Gdy na drodze były,\ntak sobie mówiły:\n„Jest tam kamień niemały,\na któż nam go odwali?” Alleluja.\n\n" +
                "5. „Powiedz nam, Maryja,\ncoś w drodze widziała?\nWidziałam Go po męce,\ntrzymał chorągiew w ręce”. Alleluja.\n\n" +
                "6. Gdy nad grobem stały,\nrzekł im Anioł biały:\n„Nie bójcie się, Maryje,\nzmartwychwstał Pan i żyje!”. Alleluja.\n\n" +
                "7. „Jezusa szukacie?\nTu Go nie znajdziecie,\nwstał-ci z martwych, grób pusty,\noto złożone chusty”. Alleluja.\n\n" +
                "8. Łukasz z Kleofasem,\nobaj jednym czasem,\nszli do miasteczka Emaus,\nspotkał-ci ich Pan Jezus. Alleluja.\n\n" +
                "9. Bądźmy więc weseli,\njak w niebie Anieli.\nCzegośmy pożądali,\ntegośmy doczekali. Alleluja.\n\n",
        "Ref. Chwalę Cię, błogosławię Cię,\nPanie mój wyzwoliłeś mnie.\nCały świat Tobie śpiewa chwały pieśń\n(On Królem całej ziemi jest).\n\n" +
                "1. Aniołowie Pańscy, błogosławcie Go\n- (Chwalcie Go, błogosławcie Go).\nWody podniebne, błogosławcie Go\n" +
                "- (On Królem całej ziemi).\nNiebiosa niebios, błogosławcie Go.\nSłońce i księżycu, błogosławcie Go.\n\n" +
                "2. Gwiazdy na niebie błogosławcie Go.\nDeszcze i rosy, błogosławcie Go.\nWichry niebieskie, błogosławcie Go.\nOgniu i żarze, dziś błogosław Go.\n\n" +
                "3. Chłodzie i upale, błogosławcie Go\nRosy i szrony, błogosławcie Go\nMrozy i zimna, błogosławcie Go\nLody i śniegi, błogosławcie Go.\n\n" +
                "4. Noce i dni, błogosławcie Go.\nŚwiatło i ciemności, błogosławcie Go.\nGóry i pagórki, błogosławcie Go.\nWszystkie rośliny, błogosławcie Go.\n\n" +
                "5. Źródła wodne, błogosławcie Go,\nMorza i rzeki, błogosławcie Go.\nWszystkie zwierzęta błogosławcie Go\nSynowie ludzcy, błogosławcie Go.\n\n" +
                "6. Kapłani Pańscy, błogosławcie Go.\nWszyscy słudzy Pańscy, błogosławcie Go.\nIzraelu święty, dziś błogosław Go.\nKościele święty, dziś błogosław Go.\n\n" +
                "7. Bo On z otchłani ognia uratował nas.\nBo On od żaru śmierci zbawił wszystkich nas,\nBo On od śmierci wiecznej uratował nas.\nOn nas rozwiązał, Swego Syna dał.\n\n",
        "Ref. Chwalcie Pana narody,\nwysławiajcie Go ludy,\nbo Jego łaskawość nad nami.\n\n" +
                "1. Śpiewajcie Panu pieśń nową,\nNiech chwała dziś Panu zabrzmi,\nNiechaj Kościół się cieszy swym Stwórcą,\nJego Królestwo na wieki!\n\n" +
                "2. Niechaj chwalą Jego Imię wśród tańców,\nniechaj grają Mu na bębnie i cytrze,\nbo Król sobie nas upodobał,\nOn ozdobi pokornych zwycięstwem.\n\n" +
                "3. Niech weselą się święci wśród chwały,\nniechaj cieszą się na swoich miejscach,\nniech w ich ustach będzie Boża chwała,\na miecz obosieczny w ich ręku.\n\n",
        "Ref. Chwalcie Pana niebios,\nchwalcie Go na cytrze.\nChwalcie Króla świata,\nbo On Bogiem jest.\n\n" +
                "1. Chwal duszo moja Pana, mego Króla.\nChcę chwalić Pana, jak długo będę żył.\nChcę śpiewać memu Bogu, póki będę istniał.\nChcę Go wysławiać, śpiewać: Alleluja!\n\n" +
                "2. Szczęśliwy ten, któremu Pan jest Pomocą.\nKto ma nadzieję w Panu, Bogu swym.\nW Bogu, który stworzył niebo i ziemię.\nWszystko co żyje, śpiewa: Alleluja!\n\n" +
                "3. Pan Bóg króluje, wesel się ziemio!\nBóg twój Syjonie przez pokolenia.\nOn dał ci życie, On dał ci wszystko.\nŚpiewaj Mu, wysławiaj Go: Alleluja!\n\n",
        "Chwalę Ciebie, Panie, i uwielbiam\nWznoszę w górę swoje ręce, uwielbiając Imię Twe. /x2\n\nBo wielkiś Ty, wielkie dzieła czynisz dziś,\nnie dorówna Tobie nikt, nie dorówna Tobie nikt./x2\n\n",
        "Chwalę Cię, Panie, całym swoim sercem. /x2\nOpowiadam wszystkie cudowne Twe dzieła. /x2\nCieszyć się będę i radować w Tobie. /x2\n" +
                "Psalm będę śpiewać na cześć Twego Imienia.\nO Najwyższy, o, o, o, o... /x4\n\n",
        "Ref. Chwalić chcę mego Pana, uwielbiać Go będę,\nJemu me serce śpiewa nową pieśń.\nGłosić chcę chwałę Boga najwyższego,\nOn wlewa pokój w serce me.\n\n" +
                "Jemu chwała - chwała Bogu Ojcu,\nChrystusowi chwała, cześć!\nDuchu Świętym - cześć i uwielbienie,\nBogu w Trójcy chwała, cześć!\n\n",
        "1. Czy wiesz, że to już czas, by wielbić Pana tu,\nw miejscu gdzie przebywa Jego lud.\nDo Pana zbliżmy się, niech chwała Jemu brzmi.\nNiech będzie wywyższony, Boży Syn.\n\n" +
                "Ref. Chwalmy Go, chwalmy Go,\nchwalmy Go, Alleluja\n\n" +
                "2. Panu miła jest, modlitwa Jego ludu.\nPragnie uwielbienia z naszych serc.\nDo Pana zbliżmy się, niech chwała Jemu brzmi.\nNiech będzie wywyższony, Boży Syn.\n\n",
        "Ref. Chwała! Barankowi chwała, cześć!\nOn Królem jest! Baranek zabity.\nChwała! Barankowi chwała, cześć!\nOn Królem jest! Baranek Żyjący.\n\n" +
                "1. A oto wielki tłum,\nKtórego nikt nie mógł policzyć,\nStojący przed tronem\nI przed Barankiem.\n\n" +
                "2. Chwała, błogosławieństwo i mądrość,\nI cześć, i moc,\nPotęga Bogu naszemu\nI Barankowi\n\n" +
                "3. Pan mówi: „Ja jestem\nPoczątkiem i końcem\nZaprawdę przyjdę niebawem\nBiegnijcie wytrwale\n\n" +
                "4. Ci, przyodziani w białe szaty,\nKim są i skąd przybyli?\nTo ci, którzy w krwi Baranka\nJe wybielili.\n\n" +
                "5. Nie będę już łaknął, ni pragnął,\nNie porazi mnie słońce,\nBo paść mnie będzie Baranek,\nDa pić ze źródeł wód życia!\n\n",
        "Chwała, chwała, Król jest godzien czci!\nChwała, chwała,\nKról jest godzien czci! Kto jest tym Królem chwały?\n" +
                "Tym Królem Chrystus Pan,\nwywyższony ponad wszystko na ziemi jest,\nJego Imię głośno brzmi.\n\n",
        "Ci, którzy Jahwe ufają, są jak góra Syjon,\nco się nigdy nie porusza, ale trwa na wieki.\nGóry otaczają Jeruzalem, tak Jahwe otacza swój lud.\n" +
                "I teraz i na wieki. I teraz i na wieki.\nMódlmy się o pokój, pokój, pokój na całej ziemi\n(o wiarę, o miłość, o jedność).\n\n",
        "1. Ciebie, Boga, wysławiamy,\nTobie, Panu, wieczna chwała,\nCiebie, Ojca, niebios bramy,\nCiebie wielbi ziemia cała.\n\n" +
                "2. Tobie wszyscy Aniołowie,\nTobie Moce i Niebiosy,\nCheruby, Serafinowie,\nślą wieczystej pieśni głosy\n\n" +
                "3. Święty, Święty nad Świętymi,\nBóg Zastępów, Król łaskawy.\nPełne niebo z kręgiem ziemi,\nMajestatu Twojej sławy.\n\n" +
                "4. Apostołów Tobie rzesza,\nchór Proroków pełen chwały.\nTobie hołdy nieść pośpiesza,\nMęczenników orszak biały.\n\n" +
                "5. Ciebie poprzez okrąg ziemi,\nz głębi serca, ile zdoła.\nGłosy ludów zgodzonymi,\nwielbi święta pieśń Kościoła.\n\n" +
                "6. Niezmierzonej Ojca chwały,\nSyna Słowo wiekuiste.\nZ Duchem wszechświat wielbi cały,\nKrólem chwały Tyś, o Chryste.\n\n" +
                "7. Tyś Rodzica Syn z wiek wieka,\nby świat zbawić swoim zgonem.\nPrzyoblókłszy się w człowieka,\nnie wzgardziłeś Panny łonem.\n\n" +
                "8. Tyś pokruszył śmierci wrota,\nstarł jej oścień w męki dobie\nI rajskiego kraj żywota\notworzyłeś wiernym sobie.\n\n" +
                "9. Po prawicy siedzisz Boga,\nw chwale Ojca, Syn Jedyny,\nLecz gdy zagrzmi trąba sroga,\nprzyjdziesz sądzić ludzkie czyny.\n\n" +
                "10. Prosim, słudzy łask niegodni,\nwspomóż, obmyj grzech co plami,\nGdyś odkupił nas od zbrodni\ndrogiej Swojej Krwi strugami.\n\n" +
                "11. Ze Świętymi w blaskach mocy\nwiecznej chwały zlej nam zdroje,\nZbaw o Panie, lud sierocy,\nbłogosław dziedzictwo swoje.\n\n" +
                "12. Rządź je, broń po wszystkie lata,\nprowadź w niebios błogie bramy,\nMy w dzień każdy, Władco co świata,\nImię Twoje wysławiamy.\n\n" +
                "13. Po wiek wieków nie ustanie\npieśń, co sławi Twoje czyny,\nO, w dniu onym racz nas, Panie\nod wszelakiej ustrzec winy.\n\n" +
                "14. Zjaw Swą litość w życiu całym\ntym, co żebrzą Twej opieki:\nw Tobie, Panie zaufałem,\nnie zawstydzę się na wieki.\n\n",
        "1. Ciebie całą duszą pragnę\ni z tęsknotą oczekuję.\nJak spękana, zeschła ziemia,\nw czas posuchy wody łaknie.\n\n" +
                "Ref. Boże, jesteś moim Bogiem,\nCiebie z troską szukam.\nBoże jesteś moim Bogiem\nCiebie z troską szukam.\n\n" +
                "2. W Twej świątyni ujrzeć pragnę,\nTwą potęgę, moc i chwałę.\nBowiem Twoją miłość, Panie,\nbardziej cenię niźli życie.\n\n" +
                "3. Póki tylko istnieć będę,\npragnę Ciebie chwalić, Boże.\nDusza moja pełna szczęścia,\nbędzie śpiewać Ci z radością.\n\n" +
                "4. Jesteś mym wspomożycielem,\ncień Twych skrzydeł daje radość.\nCałym sercem lgnę do Ciebie,\nTwa prawica mnie prowadzi.\n\n",
        "Ciebie, Panie, pragnę, Ciebie, Panie, chcę.\nW sercu moim miejsce dla Ciebie jest,\nproszę wejdź, nie pukaj, drzwi otwarte są.\nCzuj się jak u siebie, bo tutaj jest Twój dom\n\n" +
                "Ref. I bądźmy razem,\naż po życia mego kres.\nJa i Ty, i Ty, i ja,\nniech spotkanie nasze całą wieczność trwa.\n\n",
        "Ciesz się i wesel Panno Maryjo.\nAlleluja, Alleluja, Alleluja!\nBo Ten któregoś zrodziła.\n" +
                "Alleluja, Alleluja, Alleluja!\nZmartwychwstał jak zapowiedział.\nAlleluja, Alleluja, Alleluja!\n\n",
        "Ref. Cieszcie się, weselcie się,\nśpiewajcie i grajcie Mu. /x2\n\n" +
                "1. Zagrajcie Panu nową pieśń,\nalbowiem cuda nam uczynił.\nOn zbawił nas, objawił się,\nOn wierny jest dla Izraela.\n\n" +
                "2. Rozraduj cała ziemio się,\nśpiewajcie Mu przy wtórze cytry.\nPrzy dźwięku harfy weselcie się wobec Boga,\nOn jest naszym Królem.\n\n" +
                "3. Zaklaszczcie rzeki w dłonie swe,\nniechaj góry wołają radośnie.\nPrzychodzi Pan, by sądzić nas,\nOn okaże swoją sprawiedliwość\n\n",
        "1. Cieszę się - Jezus zbawił mnie.\nCieszę się - Jezus zbawił mnie.\nCieszę się - Jezus zbawił mnie.\nŚpiewam chwała, Alleluja, Jezus zbawił mnie.\n\n" +
                "2. Kiedy byłem grzeszny - Jezus zbawił mnie. /x3\nŚpiewam chwała, Alleluja, Jezus zbawił mnie.\n\n" +
                "3. Chcę powiedzieć wszystkim - Jezus zbawił mnie. /x3\nŚpiewam chwała, Alleluja, Jezus zbawił mnie.\n\n" +
                "4. Będę go uwielbiać - Jezus zbawił mnie. /x3\nŚpiewam chwała, Alleluja, Jezus zbawił mnie.\nChwała - Jezus zbawił mnie.\n\n",
        "1. Co zrobisz, gdy zobaczysz Jezusa?\nCo zrobisz, gdy przestanie istnieć świat?\nCo zrobiłeś ze swoim życiem? Odpowiedzi przyjdzie czas.\n" +
                "Co zrobiłeś ze swoim życiem – odpowiedź kiedyś będziesz musiał dać.\n\n" +
                "2. Złóż je na krzyżu Jezusa, złóż je na krzyżu Jego miłości.\nTakiej miłości nie miał żaden człowiek, tylko On, tylko On.\n\n",
        "Converte nos, Deus.\nSalutaris noster.\nMiserere, miserere.\n\n(Przemień nas, Boże, zbawienie nasze,\nzmiłuj sie nad nami, zmiłuj sie nad nami.)\n\n",
        "1. Kroków naszych nikt pojąć nie zdoła,\nSłowa nasze zdumiewają świat,\nCzas to upragniony, czas to zbawienia.\nBo zmartwychwstał Pan!\n" +
                "Jesteśmy sługami przez wielką cierpliwość,\nWśród wielu utrapień i przeciwności,\nW chłostach więzienia i podczas rozruchów.\nBo zmartwychwstał Pan!\n\n" +
                "Ref. Jezus Panem naszym jest, Alleluja!\nJezus Panem naszym jest.\n\n" +
                "2. W trudach i postach i czuwaniach nocnych,\nPrzez wielkoduszność i przez łagodność,\nPrzez Ducha Świętego i miłość nieobłudną.\nBo zmartwychwstał Pan!\n" +
                "Przez głoszenie prawdy i przez moc Bożą,\nPrzez sprawiedliwość zaczepną i obronną,\nWśród oznak czci i oznak pohańbienia.\nBo zmartwychwstał Pan!\n\n" +
                "3. Przez dobrą sławę i przez zniesławienie,\nJakby oszuści, a jednak prawdomówni,\nNiby nieznani, a przecież dobrze znani.\nBo zmartwychwstał Pan!\n" +
                "Niby umarli, a oto żyjemy\nJakby karceni, lecz nie uśmiercani,\nJakby smutni, lecz zawsze radośni.\nBo zmartwychwstał Pan!\n\n" +
                "4. Jakby ubodzy, lecz wzbogacający,\nNiby nic nie mamy, a posiadamy wszystko,\nLud odkupiony, święci wybrani.\nBo zmartwychwstał Pan!\n\n",
        "Czcijmy Jezusa, czcijmy Go. /x4\nPowstał z martwych /x2\ni On żyje na wielki już.\n" +
                "Powstał z martwych, /x2\nwięc razem się radujmy,\nświętując zmartwychwstania ten dzień.\n\n",
        "1. Czekam na Ciebie dobry Boże,\nprzyjdź do mnie, Panie, pośpiesz się.\nNiechaj mi łaska Twa pomoże,\nchcę czystym sercem przyjąć Cię.\n" +
                "Przyjdź do mnie, Panie, mój dobry Boże,\nprzyjdź i nie spóźniaj się.\nPrzyjdź do mnie Panie, przyjdź z łaską swoją,\nprzyjdź i nie spóźniaj się.\n\n" +
                "2. Wśród licznych trosk i niepokojów,\nkiedy już sił nie starcza nam,\nkarmisz nas, Panie, swoim Ciałem,\nTyś nasza moc, nasz Bóg i Pan.\n" +
                "Przyjdź do nas Panie, nasz dobry Boże,\nprzyjdź i nie spóźniaj się.\nPrzyjdź do nas Panie, przyjdź z łaską swoją,\nprzyjdź i nie spóźniaj się\n\n",
        "1. Czego chcesz od nas Panie, za Twe hojne dary?\nCzego za dobrodziejstwa, którym nie masz miary?\nKościół Cię nie ogarnie, wszędy pełno Ciebie\ni w otchłaniach, i w morzu, na ziemi i w niebie.\n\n" +
                "2. Złota też wiem, nie pragniesz, bo to wszytko Twoje,\ncokolwiek na tym świecie człowiek mieni swoje.\nWdzięcznym Cię tedy sercem, Panie wyznawamy,\nbo nad to przystojniejszej ofiary nie mamy.\n\n" +
                "3. Tyś Pan wszystkiego świata. Tyś niebo zbudował\ni złotymi gwiazdami ślicznieś uhaftował.\nTyś fundament założył nieobeszłej ziemi\ni przykryłeś jej nagość zioły rozlicznemi.\n\n" +
                "4. Za Twoim rozkazaniem w brzegach morze stoi,\na zamierzonych granic przeskoczyć się boi.\nRzeki wód nieprzebranych wielką hojność mają,\nbiały dzień, a noc ciemna swoje czasy znają.\n\n" +
                "5. Tobie k’woli rozliczne kwiatki wiosna rodzi.\nTobie k’woli w kłosianym wieńcu lato chodzi.\nWino jesień i jabłka rozmaite dawa,\npo tym do gotowego gnuśna zima wstawa.\n\n" +
                "6. Z Twej łaski nocna rosa na mdłe zioła padnie,\na zagorzałe zboża deszcz ożywia snadnie.\nZ Twoich rąk wszelkie zwierzę patrza swej żywności,\na Ty każdego żywisz z Twej szczodrobliwości.\n\n" +
                "7. Bądź na wieki pochwalon, nieśmiertelny Panie!\nTwoja łaska, Twa dobroć nigdy nie ustanie.\nChowaj nas, póki raczysz, na tej niskiej ziemi,\njedno zawżdy niech będziem pod skrzydłami Twemi.\n\n",
        "Czy może niewiasta zapomnieć\no swoim niemowlęciu?\nTa, która kocha syna swego łona.\n" +
                "A nawet gdyby ona zapomniała,\nJa nie zapomnę o tobie.\nOto wyryłem cię na obu dłoniach. /x2\n\n",
        "1. Czy wy wiecie, że jesteście świątynią? /x3\nŚwiątynią, w której mieszka Święty Duch.\n\n" +
                "Ref. Pełni mocy, wdzięczności i chwały, /x3\nŚwiątynią, w której mieszka Święty Duch.\n\n" +
                "2. Tak my wiemy, że jesteśmy świątynią, /x3\nŚwiątynią, w której mieszka Święty Duch.\n\n",
        "1. Dawajcie, a będzie Wam dane,\nodpuszczajcie, a będzie Wam odpuszczone.\nDarujcie ludziom winy,\nby Ojciec Wasz niebieski,\nmógł i Wam przebaczyć. /x2\n\n" +
                "Ref. Czyńcie pokutę, bo wkrótce umrzemy,\nwytrwajcie w dobrym aż do końca.\n\n" +
                "2. Wstrzymajcie się od wszelkiego złego,\nwyznajcie Bogu Wasze winy.\nBłogosławieni wszyscy\nczyniący pokutę,\nbo będą w Królestwie niebieskim.\n\n",
        "Da pacem Domine.\n\n",
        "Ref. O Panie mój, ja wiem,\nŻe jesteś tym, który miał przyjść.\nDaj mi wody żywej,\nBym więcej już nie pragnął.\n\n" +
                "1. Daj mi pić, usłysz Słowo me,\nNiech ono wzbudzi wiarę w sercu twym,\nW prawdzie stań.\n\n" +
                "2. Pragnę wiary w sercu twym\nI ognia, który da ci łaskę iść\nW miłości mej.\n\n",
        "Daj mi pozostać w Twojej obecności,\nbym się radował tylko Tobą, Panie. /x2\nA to, co oczyściłeś łaską, niechaj wielbi Ciebie.\nNiech sławi serce Twój majestat pełen chwały. /x2\n\n",
        "1. Daj mi siłę Twą, abym stał się bezsilny,\nDaj mi swoją moc, abym stał się łagodny,\nDaj mi mądrość swą, abym stał się prosty,\nDaj bogactwo Twe, abym stał się ubogi.\n\n" +
                "2. Daj mi radość swą, bym potrafił się smucić,\nDaj sprawiedliwość Twą, bym potrafił nie walczyć,\nDaj mi słowa Twe, abym umiał zamilknąć,\nDaj mi życie Twe, abym umiał umierać.\n\n" +
                "Ref. Duchu Święty przyjdź,\nDuchu Święty przyjdź.\nNiech stanie się cud przemiany serca!\n\n",
        "Ref. Daj mi tego chleba Panie\nCo przemienia w raj me serce\nNapój mnie Krwią Przenajświętszą\nAbym już nie pragnął więcej.\n\n" +
                "1. Kto spragniony niech przyjdzie i pije,\nJa Jestem Wodą Życia,\nkto łaknący, niech się posili,\nJa Jestem Chlebem Życia\n\n" +
                "2. Kto utrudzony, niech przyjdzie i spocznie,\nJa jestem Orzeźwieniem,\nKto strapiony niech się pocieszy\nJa jestem Pocieszycielem.\n\n" +
                "3. Kto szuka drogi niech idzie za mną,\nJa Jestem Drogą Pewną,\nKto umiera, niech wierzy i ufa,\nJa Jestem Bramą do Nieba.\n\n",
        "1. Daleko wędrowałem sam\nnie miałem ani kropli wody.\nKtoś poił mnie i o mnie dbał,\nnajmniejszej nie poniosłem szkody.\n\n" +
                "Ref. Bóg jest jak ogień, Bóg jest jak wiatr,\nmocny jak morze, wielki jak świat,\nBóg jest jak Bóg! /x3\n\n" +
                "2. Złoczyńcy otoczyli mnie\ni starliby mnie bez przeszkody.\nObłok i góra skryły mnie,\nnajmniejszej nie poniosłem szkody.\n\n" +
                "3. I zapytałem siebie: „Kto\nochrania mnie od złego losu?”\nZabłysnął obłok, huknął grzmot,\nnie mogłem z siebie dobyć głosu.\n\n" +
                "4. I odczytałem pismo gwiazd,\nrękę, co tknęła mnie, poznałem.\nPojąłem skąd mam to, co mam\ni wtedy głos mój odzyskałem.\n\n",
        "Ref. W Swe ramiona mnie weź,\ny mocą Krzyża dodaj sił.\ny W Księgę Życia wpisz mnie,\ny do Swej chwały przyjąć chciej.\n\n" +
                "1. Z głębokości wznoszę głos do Ciebie,\nracz wysłuchać Panie prośby mej.\nNakłoń ku mnie ucho Twe łaskawie.\nUsłysz modły i błagania.\n\n" +
                "2. Jeśli grzechów nie zapomnisz, Panie,\nktóż przed gniewem Twym ostoi się?\nLecz ufamy, że przebaczysz winy,\nbyśmy kornie Ci służyli.\n\n" +
                "3. Całą ufność mą pokładam w Panu,\ndusza moja ufa Jego słowu.\nTęskniej czeka dusza moja Pana,\nniż jutrzenki nocne straże.\n\n" +
                "4. Tęskniej, niż jutrzenki nocne straże,\nniechaj Pana czeka Boży Lud.\nBo u Pana znajdzie zmiłowanie\ni obfite odkupienie.\n\n",
        "1. Dlaczego kochasz drzewa, Franciszku,\nlipę, buk, stary dąb i sosnę?\nOne przecież tylko szumią.\n„Kocham je, bo szumią dla Pana”.\n\n" +
                "2. Dlaczego kochasz ptaki, Franciszku,\nskowronki, słowiki, wróble?\nOne przecież tylko śpiewają.\n„Kocham je, bo śpiewają dla Pana”.\n\n" +
                "3. Dlaczego kochasz ludzi, Franciszku,\nbiednych, bogatych i zbójców?\nOni przecież tak bardzo nienawidzą.\n„Chcę ich kochać tak, jak mój Pan”.\n\n" +
                "4. Naucz mnie kochać, Franciszku,\njak Ty, drzewa, ptaki i ludzi jak Ty. /x2\nIdź bracie do San Damiano\ni patrz, patrz długo na Krzyż. /x2\n\n",
        "1. Wszędzie tylko słychać: „Kazanie się dłuży”.\nGość siedział godzinę, w dodatku nie w porę.\nList szedł jak żaba z żabą, powoli ze smutkiem,\nza długie szczęście, za długie rozstanie.\n\n" +
                "Ref. O moje życie długie - i stale za krótkie,\nO moje życie.\n\n" +
                "2. Piekielnie długi spacer, pod rękę ze sobą,\nza długo Pan Bóg milczy, za długo komunizm.\nNie spałem, bo sąsiad, bo sąsiad uparty,\nświęty obrazek przybijał do ściany.\n\n" +
                "Ref. O moje życie długie,\nA to, co na krótko, może być na zawsze.\n\n",
        "1. Gdy wiatr rozwiewa twoje włosy\ni wokół Ciebie ludzi brak.\nSerce z żalu i tęsknoty\ndo Boga leci i woła tak:\n\n" +
                "Ref. Do chmur, do nieba, aby być bliżej Ciebie,\naby ciągle Ci śpiewać. Alleluja. /x3\n\n" +
                "2. Więc proszę, usłuchaj mego głosu,\nprzygarnij, nakarm życia chlebem.\nPokaż mi drogę mego losu,\nbym dalej Panie wielbił Ciebie.\n\n",
        "Do Ciebie, Panie, woła dusza moja, tęskni ciało me.\nGdy nie ma Cię, moje serce ogarnia lęk,\nprzyjdź, dotknij mnie, rozraduj mnie.\n\n" +
                "Ref. Jezu, pragnę blisko Ciebie być\nJezu, w Twych ramionach się skryć.\nSłodycz Twą poznawać co dnia,\nprzy sercu Twym trwać.\n\n",
        "Do mnie wróć, zbawię Cię cała ziemio! /x2\nBo jam jest Pan i twój Bóg jedyny. /x2\n" +
                "Przyjdźcie tu! Zbawi was. Przyjdźcie do Pana. /x2\nBo On wasz Pan i wasz Bóg jedyny. /x2\n\n",
        "Jezu cichy i serca pokornego,\nUczyń serca nasze według serca Twego!\n" +
                "Ty, który pocieszasz serca strapione,\nWeź nasze - kamienne, a swoje – wcielone nam daj!\n\n",
        "Dobry Bóg – wykrzykuj to z radością!\nDobry Bóg – uwielbiaj Go!\nDobry Bóg – On zwątpić nie pozwoli!\n" +
                "Dobry Bóg – On prawdą jest!\n\nGdy Jego miłość ogarnia mnie,\nw sercu radość mam,\n" +
                "przed Nim tańczyć będę.\nOn dla mnie w sercu swym miejsce ma.\nCzeka wciąż, więc pobiegnę tam.\n\n",
        "Dobry jest, łaskawy jest, wszechmocny, miłosierny,\nwielki jest, Bóg z nami, Emmanuel.\nUfam mu, kocham go, uwielbiam i wywyższam,\nwierzę mu, w Nim moje życie ma sens.\n\n" +
                "Ref. Błogosławię Pana i uwielbiam Go.\nPokłon Mu oddaję ogłaszając to, że Jezus\n" +
                "Królem Chwały jest.\n\nPan jest Królem zmartwychwstałym!\nNiech Niebiosa głoszą chwałę!\n\n",
        "Ref. Dobry Panie, uczyń z nas narzędzia\nTwojego pokoju.\n\n" +
                "1. Spraw, abyśmy siali miłość\ntam, gdzie panuje nienawiść.\nDozwól nieść wybaczenie\ntam, gdzie panuje krzywda.\n\n" +
                "2. Spraw, abyśmy siali jedność tam, gdzie panuje zwątpienie.\nDozwól nieść nadzieję tam, gdzie panuje rozpacz.\n\n" +
                "3. Spraw, abyśmy nieśli światło tam, gdzie panują ciemności.\nDozwól wzbudzać radość tam, gdzie panuje smutek.\n\n" +
                "4. Spraw, abyśmy mogli nie tyle szukać pociechy, co pociechę dawać.\nNie tyle szukać zrozumienia, co innych rozumieć.\n\n" +
                "5. Spraw, abyśmy mogli nie tyle szukać miłości, co innych miłować.\nAlbowiem dając, otrzymujemy, wybaczając, zyskujemy przebaczenie.\n\n" +
                "6. A umierając, rodzimy się do nowego życia.\nPrzez Jezusa Chrystusa Pana naszego.\n\n",
        "Dobry Pasterzu, Jezu mój Panie,\nProwadź mnie.\n\n",
        "Ref. Dobry Panie, uczyń mnie\nnarzędziem Twego pokoju.\n\n" +
                "1. Pozwól nieść miłość, gdzie nienawiść,\nnieść zrozumienie, gdzie występek.\n\n" +
                "2. Pozwól nieść zgodę, gdzie niezgoda,\npozwól nieść wiarę, gdzie zwątpienie.\n\n" +
                "3. Pozwól nieść prawdę tam, gdzie błąd,\nnieść nadzieję tam, gdzie rozpacz.\n\n" +
                "4. Pozwól nieść radość tam, gdzie smutek,\npozwól nieść światło, gdzie ciemność.\n\n" +
                "5. Pozwól pocieszać i rozumieć,\npozwól nam kochać i wybaczać.\n" +
                "Albowiem dając otrzymujemy, wybaczając, zyskujemy przebaczenie.\n" +
                "A umierając, rodzimy się do nowego życia,\nprzez Jezusa Chrystusa, Pana naszego.\n\n",
        "Dostaliśmy, to na co nie zasłużyliśmy. /x2\n\nZ Twej łaski, Panie jesteśmy tu,\nby oddać się, by oddać się.\nDzięki swej łasce uwalniasz nas\ni leczysz wciąż, kształtujesz.\n\n",
        "Dotknął mnie dziś Pan\ni radość ogromną w sercu mam.\nZ tej radości chcę\n" +
                "śpiewać i klaskać w dłonie swe.\nWięc wszyscy razem chwalmy Go\nza to, że trzyma nas ręką swą.\n\n",
        "1. To co raną jest w mym sercu,\nPrzynoszę przed Twój tron.\nTo co trudne chcę dziś złożyć,\n" +
                "W objęciach Twoich rąk.\nDziś otwieram serce me,\nDotknij Panie uzdrów je, proszę.\n\n" +
                "Ref. Wyciągam do Ciebie ręce,\nDotknij Panie rany mej.\nWierzę, że otrzymam więcej,\nGdy proszę w Imię Twe.\n\n" +
                "2. Hojny jesteś w miłosierdziu, łaskawy, mój Bóg.\nMogę czerpać z Twego serca, miłości hojny zdrój.\nDziś otwieram serce me, dotknij Panie uzdrów je, proszę.\n\n",
        "Dotknij, Panie, moich oczu,\nabym przejrzał (abym przejrzał).\nDotknij, Panie, moich warg, abym przemówił (z uwielbieniem)\n" +
                "Dotknij, Panie mego serca i oczyść je, (oczyść je Panie)\nniech Twój Święty Duch dziś ogarnia mnie.\n\n",
        "Ref. Oto ja, poślij mnie. Dotknij ogniem moich warg.\nPowiedz, Panie, czego chcesz,\na moją rozkoszą będzie być posłusznym.\n\n" +
                "1. Daj mi Twego Ducha, abym mógł stać się chlebem.\nAbym stał się winem, abym gasił Twe pragnienie.\n\n" +
                "2. Nie chcę Ci już mówić o moich potrzebach.\nPanie, Ty wiesz wszystko, objaw mi Twe pragnienia\n\n",
        "Ref. Drzewo Krzyża, drzewo Krzyża,\nŹródło życia wiecznego.\n\n" +
                "1. Tyś wyrosło z wiary jak ziarnko,\nTyś jest owocem obumarcia,\nTyś jest owocem śmierci.\n\n" +
                "2. W Twych gałęziach znajdują schronienie wszystkie ptaki niebieskie.\nW twoim cieniu odpoczywam,\nw twych ramionach odnajduję pokój.\n\n" +
                "3. Ty wydajesz owoce, gdy nie ma na to czasu,\nTyś jest drzewem wiecznie zielonym.\nTy wskazujesz drogę memu sercu.\n\n" +
                "4. Na Tobie został wywyższony wąż miedziany,\nW Twych gałęziach uwikłał się baranek.\nTy wyrastasz na wzgórzu Moria.\n\n" +
                "5. Przez Ciebie dokonuje się sprawiedliwość,\nw Tobie schroniła się nadzieja świata.\nTwemu drewnu powierzam swe życie.\n\n" +
                "6. Twe ramiona porusza delikatny powiew Eliasza,\nTy stoisz nad wodą płynącą ze świątyni.\nDrzewo życia stojące w raju Boga.\n\n",
        "Ref. Panie, nie jestem godzien,\nabyś przyszedł do mnie,\nale powiedz tylko słowo i uzdrów duszę moją. /x2\n\n" +
                "1. W Twym chlebie żyje ukryty Duch,\nktóry nie może być spożyty.\nW Twym winie płynie ogień,\nktóry nie może być wypity.\n\n" +
                "2. Duch w Twoim chlebie, Ogień w Twym winie\nsą wielkimi cudami, jakie przyjmują nasze usta.\n" +
                "Pan zstąpił na Ziemię, czyniąc nas nowym stworzeniem.\nNiech na wzór Aniołów, Ogień i Duch nas wypełnią.\n\n" +
                "3. Ogień i Duch były w łonie, które Cię zrodziło Duch Ogień w chrzcie przychodzi,\njak w chlebie i kielichu, tak Ogień i Duch.\n" +
                "W Twym chlebie ginie łakomstwo, Twój kielich unicestwia śmierć, co nas pożera.\nPożywamy Cię, Panie, i pijemy nie tylko, by się nasycić, lecz żeby żyć przez Ciebie.\n\n" +
                "4. Me serce Panie jest pełne z nadmiaru Twych dobrodziejstw.\n" +
                "Zatrzymaj przeto łask swych strumień.\nZachowaj je w swoim skarbcu,\nBy mi ich kiedyś znowu udzielić.\n\n",
        "Ref. Duch Pański nade mną, namaścił i posłał mnie,\nbym głosił Ewangelię, niewidomym przejrzenie.\n\n" +
                "1. Oto Pan Bóg przychodzi z mocą,\ni Jego ramię dzierży władzę.\nOto z Nim idzie Jego nagroda,\na przed Nim Jego zapłata.\n\n" +
                "2. Pan Bóg zawsze prowadzić Cię będzie,\nnasyci duszę Twoją na pustkowiach.\nOdmłodzi Twoje kości, będziesz jak zroszony ogród\ni jako źródło wody, co się nie wyczerpie.\n\n" +
                "3. Rozleję wody po spragnionej glebie\ni zdroje po wyschniętej ziemi.\nPrzeleję Ducha mego na twoich potomków\ni błogosławieństwo moje na twe latorośle.\n\n" +
                "4. Słuchajcie, narody, Słowa Pańskiego,\ngłoście je na wyspach odległych i mówcie:\n„Ten, który rozproszył Izraela, znów go zgromadzi\ni będzie czuwał jak pasterz nad swym stadem”.\n\n",
        "Duchu miłości, wylewaj się na nas\nz przebitego serca Jezusa, Jezusa.\n\n",
        "Ref. Duchu Pocieszycielu,\nDuchu, wlej radość w serce me.\nDuchu, Dawco darów\nPrzyjdź, napełnij nas mocą Swą!\n\n" +
                "1. Niechaj spocznie na nas Duch Pana,\nDuch Mądrości, Rozumu i Męstwa.\nDuch posłany, by dać świadectwo\nNiechaj sprawi, aby Słowo żyło w nas!\n\n" +
                "2. Duchu Miłości Ojca i Syna,\nStworzycielu dający nam życie.\nSprawco dobra wszelkiego w stworzeniu,\nOżywczy Duchu, który działasz pośród nas!\n\n",
        "Ref. Duchu Ogniu, Duchu Żarze,\nDuchu Światło, Duchu Blasku,\nDuchu Wichrze i Pożarze,\nześlij płomień Twojej łaski! /x2\n\n" +
                "1. Chcesz, rozpalisz i rozognisz,\nserca wzniesiesz na wysokość.\nW ciemność rzucisz blask pochodni\ni rozproszysz grzechu mroki.\n\n" +
                "2. Naszą nicość odbudujesz w najpiękniejsze znów struktury,\ntchnieniem swoim świat przesnujesz w szeleszczących modlitw sznury.\n\n" +
                "3. Z mgieł konkretny kształt wywodzisz i z chaosu piękno ładu,\nTyś spokojem wśród niezgody, w bezradności Tyś jest radą.\n\n" +
                "4. Twe zbliżenia zaróżowi pulsem życia, wzrostu drżeniem,\nnarodzimy się na nowo, ciemność stanie się promieniem.\n\n",
        "Ref. O Duchu Święty, Duszo mej duszy,\nuwielbiam Cię!\nO Duchu Święty, Duszo mej duszy,\nuwielbiam Cię!\n\n" +
                "1. Przemień nasze napięcie w święte odprężenie.\nPrzemień nasz niepokój w kojącą ciszę.\n\n" +
                "2. Przemień nasze zatroskanie w spokojną ufność.\nPrzemień nasz lęk w nieugiętą wiarę.\n\n" +
                "3. Przemień naszą gorycz w słodycz Twej łaski.\nPrzemień mrok naszych serc w delikatne światło.\n\n" +
                "4. Przemień naszą obojętność w serdeczną życzliwość.\nPrzemień naszą noc w Twoje święte światło.\n\n" +
                "5. Przemień zimę naszych dusz w Twoją żywą wiosnę.\nPrzemień naszą pychę w świętą pokorę.\n\n" +
                "6. Rozpal w nas miłość, zgaś w nas zmysłowość.\nWypełnij naszą pustkę, wyprostuj nasze drogi.\n\n",
        "1. Duchu Święty, powiej wiatrem\ni z mocą na nas zstąp.\nBądź miłością w naszych sercach,\nświatłem ogarnij nas.\n\n" +
                "Ref. Daj nowe oczy, ześlij swój ogień\ni jedność z Tobą daj. /x2\n\n" +
                "2. Z czterech wiatrów przybądź Duchu, powiej mocą, ożyw nas.\nBądź wolnością w naszych sercach, życiem wypełnij nas.\n\n",
        "Ref. Duchu Święty, Boże, przyjdź,\nprzyjdź Duchu Światło,\nDuchu Ogniu przyjdź, przyjdź i rozpal nas.\n\n" +
                "1. Przyjdź Duchu Ojca, bądź naszym światłem,\nniech promienieje Twej chwały blask.\n\n" +
                "2. Świadku prawdziwy, umocnij nas,\nchcemy rozgłaszać: „Zmartwychwstał Pan!”.\n\n" +
                "3. Przyjdź Źródło Niebieskie, przyjdź Zdroju Życia,\nbądź nam uzdrowieniem, moc serca daj.\n\n" +
                "4. Duchu radości, rozraduj Kościół,\nniech płynie z serc naszych Baranka pieśń.\n\n" +
                "5. Ty daj nam doświadczyć miłości Ojca,\noblicze Chrystusa objawiaj nam.\n\n" +
                "6. Bądź nam tchnieniem życia, światła promieniem,\nprzez Ciebie jaśnieje Chrystusa Krzyż.\n\n",
        "Ref. Duchu Święty, do nas przybądź,\nogarnij nas swą mocą,\nprzytul nas do serca Twego.\nDotknij swą miłością, byśmy żyli dla Ciebie.\n" +
                "Daj nam doświadczyć Twojej mocy,\ndaj nam rozkochać się w Tobie,\nniech Twa miłość ogarnia nas.\n\n",
        "Ref. Duchu Święty, Miłości Miłosierna\nDuchu Święty Miłości nieskończona\nwylewaj się na nas\n\n" +
                "1. Delikatnie otwieraj moje dłonie\nCierpliwie prostuj moje palce\nBym wypuścił z nich samego siebie\nBym stał się wolny\n\n" +
                "2. Ty wiesz ile we mnie lęku\nTy znasz wszystkie moje rany\nPomóż mi wyjść naprzeciw Ciebie\nChoć jestem nagi\n\n" +
                "3. Nie chcę odmawiać Ci niczego\nBez wahania zrób ze mną co zechcesz\nChcę odpocząć na zawsze w Twojej woli\nPragnę odpocząć... \n\n",
        "Duchu Święty, ogarnij mnie,\notwórz oczy mego serca, napełnij miłością!\nDuchu Święty, ja kocham Cię!\nNiech Twa święta wola pełni się!\n\n",
        "Ref. Duchu Święty, przyjdź.\nDuchu Święty, przyjdź. /x2\n\n" +
                "1. Niech wiara zagości,\nniech nadzieja zagości,\nniech miłość zagości w nas. /x2\n\n" +
                "2. Niech życie zagości,\nniech wolność zagości,\nniech mądrość zagości w nas. /x2\n\n" +
                "3. Niech pokój zagości,\nniech szczęście zagości,\nniech radość zagości w nas. /x2\n\n",
        "Duchu Święty, przyjdź w ogniu swym. /x2\nDuchu Święty, przyjdź w ogniu swym. /x2\nO, Duchu Święty, rozpal ogień w nas. /x4\n\n",
        "Duchu Święty, przyjdź, napełnij serce me.\nJezu, przyjdź, wołam Cię,\nnapełnij serce me.\n\n",
        "Duchu Święty, przyjdź, działaj pośród nas.\nDuchu Święty, przyjdź, odnów radość w nas.\nDuchu, przyjdź w darach Twych, oczyść serce me.\n" +
                "Duchu, przyjdź z mocą swą, ja pragnę Cię.\nMądrość tchnij w serce me, proszę Cię,\nbardzo proszę.\n" +
                "Rozum tchnij w serce me, proszę Cię,\nbardzo proszę.\n\nMęstwo tchnij w serce me, proszę Cię,\nbardzo proszę.\n" +
                "Miłość tchnij w serce me, proszę Cię.\nJa kocham Cię, ja pragnę Cię,\nja proszę Cię, Duchu przyjdź.\n\n",
        "Duchu Święty, przyjdź, otwórz serce me,\nrozpal ducha i we mnie się módl!\nOświeć umysł mój, wyraź wnętrze me,\nnaucz Panie, jak modlić się!\n" +
                "Przenikasz i znasz Panie mnie.\nWiesz dobrze, co powiedzieć chcę.\nMiłujesz i prowadzisz mnie.\nWspieraj łaską w słabości mej!\n\n",
        "Duchu Święty przyjdź, Pocieszycielu przyjdź,\nOżywicielu serc!\nDuchu Święty przyjdź, Pocieszycielu przyjdź,\nOżywicielu naszych serc!\n\n" +
                "Pragnę z Tobą być i przez życie iść,\nwciąż na nowo doświadczając miłości Twej!\nPragnę z Tobą być i przez życie iść,\nŚwięty, Święty Duchu do nas przyjdź!\n\n",
        "Duchu Święty, Stworzycielu, ożywiaj moją modlitwę,\nożywiaj moje pragnienie pełnienia woli Ojca.\nDuchu Święty, Stworzycielu,\n" +
                "otwieraj moje oczy, by widziały,\notwieraj moje uszy, by słyszały, serce, aby czuło.\nDuchu Święty, Ożywicielu, przyjdź.\n\n",
        "1. Duchu Święty, Tchnienie Ojca, Dawco życia.\nDuchu Święty, Źródło wody żywej w nas.\n\n" +
                "Ref. Tak pragniemy Ciebie Panie,\njak spękana ziemia deszczu,\njak pustynia kropli wody, Duchu, przyjdź. /x2\n\n" +
                "2. Duchu Święty, Tyś Mądrością niezmierzoną.\nDuchu Święty, Ty oświetlasz drogi nam.\n\n" +
                "Ref. I prowadzisz nas do domu,\ngdzie mieszkanie przed wiekami\nprzygotował dla nas Ojciec,\nDuchu, przyjdź. /x2\n\n",
        "Duchu Święty, wołam, przyjdź,\nw sercach rozpal ogień swój.\nJedno uczyń z naszych serc,\nmiłość, radość, pokój wlej.\n\n",
        "Duchu Święty, wołam, przyjdź,\nbądź jak ogień w duszy mej,\nbądź jak ogień w ciele mym. Rozpal mnie. /x2\n\nWszechmogący Bóg jest pośród nas,\n" +
                "miłosierdzie Jego wielkie jest,\nokazuje dobroć swoją dziś\ndla tych, którzy chcą miłować Go.\n\n",
        "Ref. Duchu Święty,\nwypełnij swą świątynię - moje ciało,\nmieszkaj we mnie, cały jestem Twój.\n\n" +
                "1. Ty ocaliłeś od śmierci moje życie,\nPrzygarnąłeś mnie w miłości swojej.\nZostałem nabyty drogocenną krwią,\nNie chcę już należeć do siebie samego.\n\n" +
                "2. Ciało moje jest świątynią Twoją,\nSkładam w moim sercu małe ofiary.\nChcę czynić tylko to, co jest Tobie miłe\nI każdym oddechem wielbić Ciebie.\n\n" +
                "3. Wypełnij mnie Twą potęgą i mocą,\nWśród narodów będę chwalił Ciebie.\nPoza Tobą nie ma dla mnie dobra,\nTobie powierzam moje życie.\n\n",
        "Ref. Duszo ma, Pana chwal, oddaj Bogu cześć,\nŚwiętemu śpiewaj pieśń.\nZ mocą wywyższaj go, duszo ma,\nUwielbiam, Boże, Cię\n\n" +
                "1. Nowy dzień wraz ze wschodem słońca,\nZnów nadszedł czas, by Ci śpiewać pieśń.\nCokolwiek jest już za mną i to wszystko, co przede mną\nWiem, będę śpiewać, gdy nadejdzie zmrok.\n\n" +
                "2. Bogaty w miłość, gniew oddalasz, Panie,\nTwe serce miłe, wielkie Imię Twe.\nZe względu na Twą dobroć będę śpiewać Tobie pieśni,\nWiele powodów, by uwielbiać Cię.\n\n" +
                "3. Przyjdzie dzień, gdy bez sił zostanę,\nNadejdzie czas mego końca tu.\nDusza ma będzie już na zawsze Cię uwielbiać,\nW wieczności z Tobą piękna zabrzmi pieśń.\n\n",
        "Ref. Dzielmy się wiarą jak chlebem.\nDajmy świadectwo nadziei.\nBóg ofiarował nam siebie\ni my mamy się Nim z ludźmi dzielić\n\n" +
                "1. Chleba tego nie zabraknie,\nrozmnoży się podczas łamania.\nPotrzeba tylko rąk naszych\ni gotowości dawania.\n\n" +
                "2. Nikt nie zapala lampy,\nby potem ją schować pod korcem.\nSkoro Bóg nas światłem natchnął,\ntrzeba z tym światłem iść w drogę.\n\n" +
                "3. Odrzućmy zwątpienie i trwogę\ni choć świat się śmieje z proroków.\nMusimy świadczyć odważnie,\nw służbie ludziom i Bogu. \n\n",
        "1. Dzięki, Jezu, dzięki Jezu, dzięki Ci, że kochasz mnie.\nDzięki, Jezu, dzięki, Jezu,\ndzięki Ci, o dzięki Ci, że kochasz mnie.\n\n" +
                "2. Tam na Golgocie umarłeś za mnie.\n\n" +
                "3. Zmartwychwstałeś, żyjesz we mnie.\n\n" +
                "4. Wkrótce przyjdziesz, stąd mnie weźmiesz.\n\n" +
                "5. Alleluja, Alleluja.\n\n",
        "Ref. Dzięki Ci, Panie, za Ciało Twe i Krew.\nZa Dary nieskończone, wielbimy Cię. /x2\n\n" +
                "1. Chwalimy Cię Wszechmocny\nza dary Twe nieskończone,\nZa Ciało i Twoją Krew.\n" +
                "Przebacz w swojej dobroci tym,\nco Ciebie niegodni,\nkiedy do Twego stołu się zbliżamy.\n\n" +
                "2. Przebacz - żeś hojny i wierny,\nuwolnij z więzów grzechu,\nbyśmy się odmienili\nprzez tajemnice najświętsze.\n\n" +
                "3. Niechaj przestworem spłynie z gwiazd anioł Twój miły,\noczyści i uleczy nasze serca i ciała.\n" +
                "Powiedzie, za sprawą tajemnicy na same szczyty nieba,\na tu na ziemi ratuje obrona Twoja potężna.\n\n" +
                "4. Spojrzyj łaskawie Stwórco, na nas znikomych i słabych,\nocal Dobry Pasterzu, Owce na swojej łące.\n" +
                "Tyś życie nam przywrócił wbrew nieprzyjacielowi\ni wzmacniasz już na zawsze, siebie dając nam Panie.\n\n" +
                "5. Spraw to, Ojcze Wszechmocny w dobroci swej niezmiernej,\n" +
                "byśmy się stali jedno,\nz Tobą, Chrystusem i Duchem,\nTy, coś w Trójcy jedyny.\n\n",
        "1. Dziękujemy Ci, Ojcze nasz,\nza święte nasze powołanie,\nktóre nam poznać dałeś\nprzez Ojca naszego Franciszka.\nTobie chwała na wieki.\n\n" +
                "2. Dziękujemy Ci, Ojcze nasz,\nza miłość seraficką,\nktórą rozlewasz w sercach naszych\ndla zasług Ojca naszego Franciszka.\nTobie chwała na wieki.\n\n" +
                "3. Dziękujemy Ci, Ojcze nasz,\nza święty skarb ubóstwa,\nktórym nas ubogaciłeś\nprzez Ojca naszego Franciszka.\nTobie chwała na wieki.\n\n" +
                "4. Dziękujemy Ci, Ojcze nasz,\nza radość doskonałą,\nktórej nauczył nas w cierpieniu\nŚwięty nasz Ojciec Franciszek.\nTobie chwała na wieki.\n\n" +
                "5. Dziękujemy Ci, Ojcze nasz,\nza braci i siostry nasze w świecie,\nktórzy Ci świadectwo dają\nw Imię Ojca naszego Franciszka.\nTobie chwała na wieki.\n\n" +
                "6. Dziękujemy Ci, Ojcze nasz,\nza świętą więź braterstwa,\nktóra złączyła trzy Zakony\nŚwiętego Ojca naszego Franciszka.\nTobie chwała na wieki. Amen.\n\n",
        "Ref. Dziękuję Bogu dziś, że nas zjednoczyć chciał,\nw modlitwie mojej radość brzmi. /x2\n\n" +
                "1. Jestem pewien, że ten sam,\nco rozpoczął w nas swe dzieło,\nprzyjdzie je dopełnić w dniu Chrystusa Pana.\n\n" +
                "2. Ta miłość, którą Pan\nchciał napełnić moje serce,\njest miłością zaszczepioną w Duchu Świętym.\n\n" +
                "3. Tak pragnę, aby więź\nmiędzy nami mogła wzrastać,\nbyśmy czystym sercem mogli czekać Pana.\n\n" +
                "4. Niech Jezus da nam wzrost\ni napełni wszelkim dobrem,\nby modlitwa nasza była miła Bogu.\n\n",
        "Dziękujmy Jezusowi za wszystko, co nam dał.\nZa Jego śmierć na krzyżu, przez którą zbawił nas.\nZa to, że wśród nas przebywa,\njak obiecał w słowie swym.\n" +
                "Wznieśmy ręce w stronę nieba i chwalmy Go.\nUwielbiajmy Go, chwalmy Go!\nWznieśmy ręce w stronę nieba i chwalmy Go. /x2\n\n",
        "1. Dziś jest czas, by oddać Bogu chwałę\nw tej świątyni, którą my jesteśmy sami.\nWięc otwórz serce swe i zacznij wielbić Go,\nJego chwała wnet wypełni miejsce to.\n\n" +
                "Ref. Chwała, cześć, chwalmy Go! /x2\n\n" +
                "2. Bóg przebywa w chwale swego ludu,\nOn pragnie, aby wzywać Jego Imię.\nWięc otwórz serce swe i zacznij wielbić Go,\nJego chwała wnet wypełni miejsce to.\n\n",
        "1. Zapytałem raz Jezusa, czy nienawiść w sercu ma\ndo ludzkości, co skazała Go na Krzyż.\n" +
                "O miłości lepiej mów, nie mam czasu, bywaj zdrów,\ntyle jeszcze do zrobienia, muszę iść.\n\n" +
                "Ref. Dzisiaj tu, jutro tam,\nkażda radość krótko trwa.\nDobrze z sobą było nam,\nwięc śpiewajmy dzisiaj tu, a jutro tam.\n\n" +
                "2. Kiedy Adam raj opuszczał z jednym jabłkiem w dłoni swej,\nzapytałem go, co dalej będzie z nim.\n" +
                "Będę orał, sadził, siał oraz modlił się co dnia,\nnie mam czasu do stracenia, muszę iść.\n\n" +
                "3. Byłem razem z Waszyngtonem, gdy na chłodzie z armią stał\ni spytałem w imię czego cierpią tak?\n" +
                "Jeśli człowiek rację ma, nawet życie za nią da,\nchoćby wiedział, że odejdzie tak, czy siak.\n\n",
        "1. Dziś Jezus zamieszkał w mym sercu,\nzszedł z wysokiego tronu Nieba.\nWielki Pan, wszechrzeczy Stwórca,\nprzyszedł do mnie w postaci Chleba.\n\n" +
                "Ref. O Wiekuisty Boże w piersi mej zamknięty,\na z Tobą mam niebo całe i z Aniołami śpiewam Ci:\nŚwięty, Święty, Święty.\n\n" +
                "2. Nie z Serafinem łączysz się o Boże,\nale z człowiekiem mizernym,\nktóry bez Ciebie nic uczynić nie może,\na Tyś dla niego zawsze miłosierny.\n\n",
        "1. Jesteśmy ludem nabytym cenną Krwią.\nW radości Bogu oddajemy cześć.\n" +
                "Gdy głosimy Bożą wielkość, majestat Jego i moc,\nto niszczymy moc szatana na ziemi tej.\n" +
                "Uwielbiajmy Jego chwałę dzień po dniu.\nW radości Bogu oddawajmy cześć.\n" +
                "Gdy głosimy Jego wierność i miłosierdzia dar,\nto niszczymy moc szatana na ziemi tej.\n\n" +
                "Ref. Dziś Kościele żyjącego Boga wstań!\nBóg Królem całej ziemi jest.\nŻadne moce, ni zwierzchności, nie wzruszą nas nigdy już.\n" +
                "Gdy stajemy w jedności, nic nas już nie rozdzieli,\nbo stajemy razem, gdzie nasz Bóg.\n\n" +
                "2. Ogłaszamy mocom zła na świecie tym:\n„Wasz czas już kończy się.\nNasz Bóg dał nam zwycięstwo i my, Jego Kościół,\n" +
                "dziś niszczymy moc szatana na ziemi tej”.\nZatem, cały ludu Pana, teraz wstań!\nUwierz mocy słowa, które daje Bóg.\n" +
                "Bo szatan jest pokonany i zastępy jego też,\nkiedy Kościół przyjmuje Bożą moc.\n\n",
        "Ref. Eli, Eli, lema sabachthani.\nEli, Eli, lema sabachthani. /x4\n\n" +
                "1. Boże mój wołam przez dzień, Boże mój wołam i nocą,\nTy mi nie odpowiadasz i nie zaznaję spokoju.\n" +
                "Przecież Ty mieszkasz w świątyni, Tobie ufali przodkowie,\ndo Ciebie wołali, zostali zbawieni i nie doznali wstydu.\n\n" +
                "2. Ja zaś jestem jak robak, nie człowiek, wyśmiany, wzgardzony.\nSzydzą ze mnie ci, którzy mnie znają i potrząsają głowami.\n" +
                "Rozlany jestem jak woda, jak wosk topnieje me serce\nMoje gardło jest jak skorupa, kładziesz mnie w prochu śmierci!\n\n" +
                "3. Sfora psów mnie dopadła, przebili mi ręce i nogi.\nPoliczyć mogę wszystkie moje kości, oni patrzą na moją nagość.\n" +
                "Moje szaty dzielą między siebie, los rzucają o mą suknię.\nOjcze, Abba, nie stój daleko, Tatusiu, proszę, ocal mi życie!\n\n" +
                "Będziesz głosił swoim braciom wybawienie, Synku mój.\nOni zobaczą i uwierzą Twojemu świadectwu.\n" +
                "Ubogi będzie nakarmiony, biedak będzie wysłuchany,\nsmutny będzie pocieszony, cichy posiądzie ziemię,\n" +
                "cierpiący jest na drodze do Królestwa,\nśpiący w ziemi powstanie do życia.\n\n" +
                "Bo tak uczynił Pan!\nPan tak uczynił! Pan tak uczynił! Pan tak uczynił! /x2\n\n",
        "1. Zza horyzontu wielka światłość wciela się w historię\ni zwyciężając mroki czasu staje się Pamiątką.\n" +
                "Oświecając nasze życie jasno nam objawia,\nże tylko poszukując Prawdy, życie ma sens.\n\n" +
                "Ref. I my z Nim, objęci Jego światłem,\nzłączeni Jego krzyżem, śpiewamy jednym głosem,\nEmmanuel, Emmanuel, Emmanuel.\nEmmanuel, Emmanuel.\n\n" +
                "2. Ten wielki dar samego Boga, to Chrystus - Syn Jego -\ni cała ludzkość odnowiona i w Nim wybawiona.\n" +
                "Prawdziwy Bóg, prawdziwy Człowiek, On jest Chlebem Życia\ni wszystkim ludziom - swoim braciom - On daje się.\n\n" +
                "3. Śmierć pokonana, wygrało życie, Pascha w całym świecie,\npowiew wiatru w każdym sercu - Duch Zmartwychwstałego.\n" +
                "To On prowadzi poprzez wieki Kościół - swą Wybrankę -\npod czujnym okiem Maryi Panny, wspólnotę serc.\n\n" +
                "4. I oto my, jako dłużnicy historii, stuleci,\nwielu istnień dla miłości, świętych, co wierzyli,\n" +
                "ludzi, co za wielką cenę uczyli nas latać,\ntych, co wieki przemieniali, jak Chrystus Pan.\n\n" +
                "5. Nadeszła już godzina wiosny, czas łaski na przemianę.\nNie czekaj jutra, dziś jest darem, przyjmij to z zapałem.\n" +
                "Powroty szczere, słowa nowe, serca nawrócenie.\nChrystus niesie każdemu z ludzi nowości czas.\n\n",
        "1. Biegali razem, trzymając się za ręce,\nFranciszek i Klara znali swe serca.\nPiękny był Asyż i słońce za pan brat,\nlecz pewnego dnia Franciszek mówi tak:\n\n" +
                "Ref. „Klaro, musimy rozstać się,\nbo ludzie, ludzie mówią o nas źle,\nwięc żegnaj Klaro, musimy rozstać się,\nbo ludzie, ludzie mówią o nas bardzo źle”.\n\n" +
                "2. Wielki smutek w oczach Klary, w oczach Klary szczere łzy,\n" +
                "wszak Ona i On to jedno.\n„Franciszku nie odchodź, kiedy wrócisz znów?\nKiedy wrócisz znów?”\n\n" +
                "3. Franciszek odchodzi, a Klara pyta:\n„Kiedy przyjdziesz znów?”\n„Gdy rozkwitną róże”... I stał się cud.\nRóże całego Asyżu płonęły czerwienią.\n\n" +
                "Ref. Panie, nie rozstaniemy się, bo po to dałeś miłość,\naby wielbić Imię /x2 Twe.\n\n",
        "1. Jesteś radosny, cieszy się twe serce,\ngdy się w nim rodzi uczucie miłości.\nJesteś szczęśliwy, bo pewność masz jedną,\n" +
                "że jesteś częścią niezmiernego życia.\nCo swoją hojność rozsiewa wokół nas,\nbo miłość Pana jest wierna i bez dna.\n\n" +
                "2. On dał nam niebo i przepiękne gwiazdy,\nw dzień złote słońce, w nocy srebrny księżyc.\nOwoce, zioła i kwiaty na ziemi,\n" +
                "ogień, powietrze, wiatr i czystą wodę,\nco źródłem życia jest dla Jego stworzeń.\nBo miłość Pana jest wielka i bez dna.\n\n",
        "1. Gdy klęczę przed Tobą,\nmodlę się i składam hołd,\nweź ten dzień, uczyń go swym\ni we mnie miłość wznieć.\n\n" +
                "Ref. Ave Maria, gratia plena.\nDominus tecum, benedicta tu.\n\n" +
                "2. Wszystko Tobie daję,\nkażdy sen i każdą myśl.\nMatko Boga, Matko moja,\nwznieś je przed Pana tron\n\n" +
                "3. Gdy klęczę przed Tobą,\nwidzę Twą radosną twarz.\nKażda myśl, każde słowo,\nniech spocznie w dłoniach Twych.\n\n",
        "Gdy schodzimy się, niech Święty Duch w nas działa.\nGdy schodzimy się, by wielbić Imię Twe.\n" +
                "Wiemy dobrze, że gdy serca są otwarte,\nBłogosławisz mocą swą.\nBłogosławisz, boś obiecał to,\nBłogosławisz mocą swą.\n\n",
        "1. Gdy serce twoje napełni się trwogą\ni ciężar ogromny, przygniata do ziemi.\n" +
                "Gdy nogi nie chcą, lub iść już nie mogą,\nto pomyśl, że jest ktoś kto wszystko to zmieni.\n\n" +
                "Ref. To Matka ukoić, może twe serce.\nPrzytulić tak mocno, jak tuli Jezusa.\n" +
                "Wziąć wszystko co twoje, we własna Swe ręce\ni zanieść ten skarb, do Syna Chrystusa.\n\n" +
                "2. Lecz musisz się zdobyć, na trud jeszcze jeden,\nby poprzez wędrówkę, w upale i znoju,\n" +
                "zobaczyć twarz Matki, promienną i jasną.\nBy tam się z Nią spotkać i zaznać pokoju.\n\n",
        "1. Gdy stoisz przed Panem, ucisz się,\nbo Święty jest wśród nas.\nPrzed Panem kolana skłoń,\nz bojaźnią i ze czcią.\n" +
                "Nie znajdziesz grzechu w Nim,\ngdyż Bóg nasz święty jest.\nGdy stoisz przed Panem, ucisz się,\nbo Święty jest wśród nas.\n\n" +
                "2. Gdy trwam w uwielbieniu, wtedy Pan\njaśnieje w życiu mym,\nzapala w nim święty żar, wypełnia pięknem swym.\n" +
                "Przejmuje lękiem mnie widok światłości tej,\ngdy trwam w uwielbieniu, wtedy Pan\njaśnieje w życiu mym.\n\n" +
                "3. Stoimy przed Panem, Jego moc\nwypełnia miejsce to.\nOn przyszedł oczyszczać, leczyć nas, udzielać łaski swej.\n" +
                "Wszystko możliwe jest,jeśli wierzymy Mu,\nstoimy przed Panem, Jego moc\nwypełnia miejsce to.\n\n",
        "1. Gdy uczniów swych posyłał Pan,\nby nieśli wieść radosną,\nżegnając ich, dał swoją moc\ni mówił do nich z miłością:\n\n" +
                "Ref. „Nie warto na drogę tę\nsandałów i płaszcza zabierać.\n(Nie, nie, nie) Nie trzeba nam srebra brać,\no dach nad głową zabiegać”.\n\n" +
                "2. Nowinę tę głosili więc, po czterech stronach świata.\nBogaci tak, nie mając nic, bo miłość jest tak bogata:\n\n" +
                "3. Gdy ukończyli żniwo swe, w ostatnią podróż ruszyli.\nPatrzyli w niebo na Ojca dom i tak z ufnością śpiewali:\n\n",
        "Gdy wpatruję się w Twą świętą twarz,\ngdy rozmyślam o miłości Twej,\nwtedy to, co wokół mnie,\n" +
                "niknie w cień światłości Twej.\nGdy dotykasz Panie serca mego,\ngdy pokłony składam u Twych stóp,\n" +
                "wtedy to, co wokół mnie, niknie w cień światłości Twej.\nUwielbiam Cię, uwielbiam Cię,\nbo mego życia sens, to wielbić Cię. /x2\n\n",
        "1. Gdy zobaczysz ciemny ogród, pogrążony w cichym śnie,\nto zobaczysz tam człowieka, który właśnie modli się.\n" +
                "Czy poznajesz Jego twarz? Tak niedawno wołał cię.\nCzy usłyszysz Jego słowa, których teraz boisz się?\n\n" +
                "Ref. Nie, nie chcę umierać!\nLecz Twoją wolę spełnię Panie. /x2\n\n" +
                "2. Potem ujrzysz tam też uczniów, którzy za nim wiernie szli.\nOni teraz nie są przy Nim, strach ogarnął wszystkich ich.\n" +
                "Więc zobaczysz znów człowieka, tak niedawno wołał Cię.\nW nocnej ciszy znów usłyszysz przeraźliwy, głośny krzyk.\n\n",
        "1. Gdyby Pan nie był po naszej stronie\nGdy ludzie przeciw nam powstali połknęliby nas żywcem.\nGdyby Pan nie był po naszej stronie.\n\n" +
                "Ref. Niech to już przyzna Izrael. /x4\n\n" +
                "2. Gdyby Pan nie był po naszej stronie,\nzatopiłaby nas woda, przelałyby się nad nami strumienie.\n" +
                "Błogosławiony Pan, który nas nie wydał na pastwę ich zębów.\n\n" +
                "3. Gdyby Pan nie był po naszej stronie,\nDusze nasze jak ptak, nie uszłyby niewoli.\nJezus by za nas nie umarł na Krzyżu.\n\n",
        "Gdyby wiara twa była wielka jak gorczycy ziarno,\nte słowa mówi ci Pan. /x2\nI z taką wiarą rzekłbyś do góry:\n„Przesuń się, przesuń się”. /x2\n\n" +
                "A góra posłusznie przesunie się,\nprzesunie się, przesunie się.\nW Imię Jezusa przesunie się,\nprzesunie się, przesunie się.\n\n" +
                "I chromy stanie na nogi swe, na nogi swe, na nogi swe.\nkto łzy leje, otrze je, otrze je, otrze je.\n\n" +
                "Chory odzyska zdrowie swe, a zdrowie swe, zdrowie swe.\nI kto jest smutny, uśmiechnie się, uśmiechnie się, uśmiechnie się.\n\n" +
                "Spływa, spływa, spływa Duch Święty. /x4\n\n" +
                "Duch Święty swą mocą dotyka mnie, dotyka mnie, dotyka mnie.\nOd czubka głowy, po stopy me, po stopy me, po stopy me.\n\n" +
                "Duch Święty swą mocą dotyka cię, dotyka cię, dotyka cię.\nOd czubka głowy po stopy twe, po stopy twe, po stopy twe.\n\n",
        "1. Gdybym mówił językami ludzi i aniołów,\na miłości bym nie miał,\na miłości bym nie miał,\nstałbym się jak miedź brzęcząca,\n" +
                "albo cymbał brzmiący,\na miłości bym nie miał,\na miłości bym nie miał,\n\n" +
                "Ref. Byłbym niczym, byłbym niczym.\n\n" +
                "2. Gdybym miał dar prorokowania i znał wszystkie tajemnice,\n" +
                "a miłości bym nie miał, /x2\ngdybym miał taką wiarę, iż bym góry przenosił,\na miłości bym nie miał, /x2\n\n" +
                "3. Gdybym rozdał na jałmużnę całą swą majętność,\n" +
                "a miłości bym nie miał, /x2\ngdybym wydał moje ciało na spalenie w ogniu,\na miłości bym nie miał. /x2\n\n" +
                "Ref. Byłbym niczym, byłbym niczym.\nAmen, Amen.\n\n",
        "Z głębokości wołam do Ciebie, Panie,\nUsłysz mój głos,\nNakłoń swego ucha na me błaganie!\n\n" +
                "Gdybyś tu był, Panie, nasz brat by nie umarł,\nGdybyś tu był!\nOto zasnął ten, którego Ty umiłowałeś,\n" +
                "Tak późno przychodzisz dziś!\nKamień wielki jak śmierć oddziela nas\nI tylko Ty masz władzę go odsunąć /x2\n\n" +
                "(Bo Tyś jest Mesjasz, Pan).\nTyś naszym życiem!\nTyś zmartwychwstaniem!\n" +
                "Biegnijmy i my\nBiegnijmy umrzeć z Nim!\nBiegnijmy umrzeć z Nim!\n\n",
        "Gloria, gloria Tibi Domine.\n\n",
        "1. Głosimy Jezusa moc - nadchodzi Pan nasz i Bóg.\nOn Panem niebios i gwiazd,\nto miejsce dał nam nasz Król.\n" +
                "Głosimy Jezusa moc - On Panem ziem i mórz.\nWznosimy dla Niego tron,\nbo miejsce to dał nam nasz Król.\n\n" +
                "Ref. Jezus Panem, niebios Królem,\nwszystko Jego jest.\nJezus Panem, cały wszechświat,\nwszystko Jego, wszystko Jego jest.\n" +
                "Wielbimy Cię - Alleluja.\nChwalimy Cię - Alleluja.\nWielbimy Cię - Alleluja.\nChwalimy Cię. /x2\n\n" +
                "2. Jezus jest pośród nas, otwórzmy serca Mu,\nniech ciemne moce drżą,\nbo miejsce to dał nam nasz Król.\n" +
                "Chwały Jezusa blask, Wszechmocny Pan nasz i Bóg.\nGłosimy miłość i moc,\nbo miejsce to dał nam nasz Król.\n\n",
        "1. Głoś Imię Pana, Króla Wszechmocy i Chwały.\nZłącz się z chórami niebios, zastępów wspaniałych.\n" +
                "Harfa i róg niech zagra: „Któż jest jak Bóg!”\nBy krańce ziemi słyszały. /x2\n\n" +
                "2. Głoś Imię Pana, On twoim losem kieruje.\nNa skrzydłach niesie, jak orzeł się opiekuje.\n" +
                "Daj Mu swą dłoń, w przygodzie zawołaj doń.\nOn cię od zła zachowuje.\n\n" +
                "3. Głoś Imię Pana, razem ze wszystkim, co żyje.\nOn twoim Światłem, Jemu niech serce twe bije.\n" +
                "On Życiem twym, wdzięczności śpiewaj Mu hymn,\nteraz i na wieki. Amen.\n\n",
        "1. Godzien jesteś, /x2 Panie i Boże nasz.\nGodzien jesteś przyjąć chwałę, chwałę, cześć i moc.\nPonieważ Ty wszystko stworzyłeś w swej mocy,\n" +
                "wszystko stworzyłeś Ty sam.\nI z woli Twojej zostało stworzone.\nGodzien jesteś wziąć cześć.\n\n" +
                "2. Godzien jesteś, /x2 Zbawco, Baranku nasz.\nGodzien jesteś przyjąć chwałę, chwałę, mądrość, moc.\n" +
                "Ponieważ zostałeś zabity za lud Twój, wszystkich zbawiłeś\n" +
                "Ty sam. I przez Krew Twoją nabyłeś ich Bogu.\nGodzien jesteś wziąć cześć.\n\n" +
                "3. Godzien jesteś, /x2 Panie i Boże nasz.\nGodzien jesteś, godzien jesteś, Zbawco, Baranku nasz.\n" +
                "Odebrać dziś chwałę i mądrość, i wdzięczność,\nbłogosławieństwo i moc. Bóg nasz niech przyjmie\npotęgę na wieki. Amen. Amen.\n\n",
        "1. Godzien, o godzien jest Bóg\nsiedzący na tronie.\nBaranek wśród chwał\nprzyjąć wdzięczność, siłę i moc.\nBłogosławieństwo i cześć.\n\n" +
                "Ref. On Panem jest od wieków na wieki. /x3\nAmen.\n\n" +
                "2. Będziemy niezmiennie wciąż trwać\nw miłości i prawdzie,\noddając mu hołd,\nmiłość, wdzięczność, siłę i moc.\nBłogosławieństwo i cześć.\n\n",
        "1. Golgoto, Golgoto, Golgoto.\nW tej ciszy przebywam wciąż rad.\nW tej ciszy daleki jest świat.\n" +
                "Ty koisz mój ból, usuwasz mój strach,\ngdy widzę Cię, Zbawco, przez łzy.\n\n" +
                "Ref. To nie gwoździe Cię przybiły, lecz mój grzech.\nTo nie ludzie Cię skrzywdzili, lecz mój grzech.\n" +
                "To nie gwoździe Cię trzymały, lecz mój grzech,\nchoć tak dawno to się stało, widziałeś mnie.\n\n" +
                "2. Golgoto, Golgoto, Golgoto.\nJa widzę Cię, Zbawco mój, tam.\nTak wiele masz sińców i ran.\n" +
                "Miłości Twej moc zawiodła Cię tam,\nuwolnić mnie z grzechów i win.\n\n",
        "Ref. Góry mogą ustąpić i pagórki się zachwiać,\nLecz Twa miłość nie odstąpi ode mnie.\n" +
                "Od miłości Twojej nic nie zdoła mnie odłączyć,\nW Twoim sercu pragnę żyć na wieki.\n\n" +
                "1. Małżonkiem twoim jest twój Stworzyciel,\nktóremu na imię Pan Zastępów.\nOdkupicielem twoim - Święty Izraela -\nNazywają Go Bogiem całej ziemi.\n\n" +
                "2. Utrapienie, ucisk, głód czy nagość\nNie wyrwą nigdy nam miłości Twojej.\nAni śmierć, ani życie, ani nic, co będzie,\nNie zdoła nas odłączyć od Ciebie.\n\n",
        "Gromadzisz nas i łączysz nas w jedno,\nby wielbić święte Imię Twe. /x2\nKiedy słowo Twe wypełnia się w nas,\nto stajemy się Twą rodziną. /x2\n\n",
        "1. HAVA NAGILA /x3 V’NISM’ CHAN /x2\nHAVA N’RAN’NA NA /x3 V’NISM’ CHAN /x2\nURU, URU ACHIM\n" +
                "URU ACHIM BELEV SAMEACH /x4\nURU ACHIM /x2 BELEV SAMEACH\n\n" +
                "2. Przyjdźcie, radośnie /x3 wielbić Go /x2\nPrzyjdźcie, radujmy się /x3, wielbijmy Go /x2\n" +
                "Bracia, bracia wstańcie!\nWywyższajmy Jego Imię /x4\nDzisiaj tu /x2 radosnym sercem.\n\n",
        "1. Hej, Jezu, Królem Tyś (Hej, Jezu, Królem Tyś).\nŻycie Twe uwalnia mnie (życie Twe uwalnia mnie).\n" +
                "Będę sławić cały dzień (będę sławić cały dzień),\ndoskonałe drogi Twe (doskonałe drogi Twe).\n\n" +
                "2. Hej, Jezu, Panem Tyś.\nChcę słuchać Twoich słów.\nTwe królestwo ujrzeć chcę,\nwola Twa niech spełni się.\n\n" +
                "3. Chwała Barankowi, cześć.\nWeź mnie do ziemi swej.\nZwyciężymy w Imię Twe,\nogłosimy rządy Twe.\n\n" +
                "4. Hej, o hej, o Lwie Judy,\njak potężny jesteś Ty.\nHej, o hej, o Lwie Judy,\njak wspaniałym jesteś Ty.\n\n",
        "1. Hosanna, hosanna, hosanna na niebiosach. /x2\nSławić chcemy Cię wciąż, z radością i czcią.\n" +
                "Wywyższony bądź, Boże nasz,\nhosanna niechaj ciągle brzmi.\n\n" +
                "2. Chwała, chwała, Królowi Królów chwała, cześć. /x2\nSławić chcemy Cię wciąż,z radością i czcią.\n" +
                "Wywyższony bądź, Boże nasz,\nKrólowi Królów chwała cześć.\n\n",
        "1. Niech słaby powie: „Mam moc”,\nbiedny wyzna: „Wszystko mam”,\nŚlepy mówi: „Widzę znów”,\nwe mnie to uczynił Bóg.\n\n" +
                "Ref. Hosanna, hosanna Barankowi, co siebie dał.\nHosanna, hosanna, Jezus zmarł i zmartwychwstał.\n\n" +
                "2. Wejdę w rzekę, gdzie grzechy me\nZmywasz, Zbawco, swoją krwią.\nZ niebios miłość wylewa się,\nłaską swą ogarnij mnie.\n\n" +
                "3. Z wód głębokich podniosę się\nWprost w ramiona, Panie, Twe\nI zaśpiewam nową pieśń,\nJezus Król uwolnił mnie.\n\n",
        "Hosanna! (Hosanna!), Hosanna! (Hosanna!)\nKrólowi Królów część. /x2\nChwalmy Pana, śpiew niech brzmi.\nZmartwychwstał Król i żyje dziś.\n" +
                "Chwalmy Go, najwyższy On,\ni godzien wszelkiej czci. /x2\n\nWielbimy! (Wielbimy!), Wielbimy! (Wielbimy!),\nWielbimy Panie Cię.\n" +
                "Kochamy! (Kochamy!), Kochamy! (Kochamy!)\nKochamy Panie Cię.\nChwalmy Pana, śpiew niech brzmi.\n" +
                "Zmartwychwstał Król i żyje dziś.\nChwalmy Go, najwyższy On,\ni godzien wszelkiej czci. /x2\n\n",
        "Ref. Chleb niebiański dał nam Pan,\nna życie wieczne.\n\n" +
                "1. Wzięliśmy i jedliśmy Chleb Błogosławiony,\nCiało Pana i Krew drogocenną.\nChleb przemienił Pan,\nKielich zbawczy, Napój orzeźwiający.\n\n" +
                "2. Przyjąwszy Święty Chleb będziemy wysławiali Boga,\nktóry na całej ziemi uczynił wielkie rzeczy.\n" +
                "Radujcie się sprawiedliwi w Panu,\nprzyjmując Ciało i Krew Chrystusa.\n\n" +
                "3. Dziś spożywaliśmy Chleb Aniołów.\nDziś oglądaliśmy naszego Pana, Jezusa Chrystusa.\n" +
                "Dziś wychwalaliśmy węgiel żarzący się na ołtarzu,\nw cieniu którego śpiewają Serafiny.\n\n" +
                "4. Dziś usłyszeliśmy wielki i słodki głos:\n„To Ciało spali ciernie grzechów\n" +
                "i oświeci dusze ludzi\nprzyjmujących Boski Pokarm”.\n\n",
        "1. Pańska jest ziemia i co jest na ziemi,\njej długi okrąg z mieszkańcami swymi.\nOn ją na morzach utrzymuje stale\ni miękkie wody chciał dać za grunt skale.\n\n" +
                "Ref. Otwórzcie bramy, co nietknione stały.\nBramy wieczyste! Bo idzie Król chwały.\nKtóż ten Król chwały? I kto jest ten mężny?\n" +
                "Pan mocny w boju i Bóg nasz potężny.\nOtwórzcie bramy, co nietknione stały.\nBramy wieczyste, bo idzie Król chwały!\n" +
                "Któż ten Król chwały? Pan o cnoty dbały.\nOn w te drzwi wnidzie, on jest Królem chwały.\n\n" +
                "2. Któż na Twą górę może wstąpić, Panie?\nAlbo na miejscu poświęconym stanie?\n" +
                "Ten, który krzywdą rąk swych nie oszpeci,\nten, co ma serce czyste, Bożych dzieci.\n\n" +
                "3. Kto dba o duszę, nie przysiągł kłamliwie,\nz Pańskiej litości pójdzie mu szczęśliwie.\n" +
                "Oto jest rodzaj i taka rachuba,\ntych, co chcą znaleźć twarz Boga Jakuba.\n\n",
        "1. Drzewo Krzyża surowe,\nTyś ciało Chrystusowe dźwigało.\nMarii Panny lamenty i bok święty rozcięty widziało\n\n" +
                "2. Na Ciebie Krew i Woda\ni łaska Chrystusowa spłynęły.\nNa Tobie Kościół święty\ni wiary fundamenty stanęły.\n\n" +
                "3. Potężnym majestatem\nwysoko ponad światem górujesz.\nPrzemija postać świata,\na Ty po wszystkie lata królujesz.\n\n",
        "Ref. Połóż mnie jak pieczęć na twoim sercu,\njak pieczęć na twoim ramieniu.\nBo jak śmierć potężna jest miłość,\na zazdrość jej nieprzejednana.\n" +
                "Żar jej, to żar ognia, płomień Pański.\nWody wielkie nie zdołają jej ugasić.\nNie zatopią jej rzeki\nJeśli ktoś ją odda za srebrniki, pogardzą nim tylko.\n\n" +
                "1. Gdybym mówił językami ludzi i aniołów,\na miłości bym nie miał.\nStałbym się jak miedź brzęcząca,\nAlbo cymbał brzmiący.\n" +
                "Gdybym też miał dar prorokowania i znał\nwszystkie tajemnice.\nI posiadł wszelką wiedzę.\nI wszelką możliwą wiarę, tak iżbym góry przenosił.\n" +
                "A miłości bym nie miał, byłbym niczym.\n\n" +
                "2. I gdybym rozdał na jałmużnę całą majętność moją,\na ciało wystawił na spalenie,\nlecz miłości bym nie miał, nic bym nie zyskał.\n" +
                "Miłość cierpliwa jest, łaskawa jest, miłość nie zazdrości,\nnie szuka poklasku, nie unosi się pychą,\n" +
                "nie dopuszcza się bezwstydu,\nnie szuka swego, nie unosi się gniewem, nie pamięta złego.\n\n",
        "Ref. Alleluja! Jezus Panem jest!\nOn zmartwychwstał, żyje, stoi pośród nas.\nAlleluja! Radujmy się!\nUwielbiajmy Pana, który Królem jest.\n\n" +
                "1. Na świętej uczcie Baranka,\nodziani w szaty zbawienia,\nprzebywszy Morze Czerwone,\nku czci Chrystusa śpiewajmy.\n\n" +
                "2. Bo Jego Ciało nas karmi,\nna krzyżu za nas wydane,\na Jego krwią napojeni\nżyjemy odtąd dla Boga.\n\n" +
                "3. Gdy Noc Paschalna nadeszła,\nMściciela miecz nas ominął\ni wtedy Pan nas wyzwolił,\nspod władzy księcia ciemności.\n\n" +
                "4. Już Chrystus Paschą jest naszą\ni szczerej prawdy zaczynem,\nniewinnym Bożym Barankiem,\nco siebie złożył w ofierze.\n\n" +
                "5. O jakże cenna ofiara,\nprzez którą piekło jest starte,\na lud wychodzi z niewoli,\nby pełnię życia odzyskać.\n\n" +
                "6. Już Chrystus grób swój porzucił,\nzwycięski wraca z otchłani,\na gdy uwięził szatana,\notworzył niebo dla wszystkich.\n\n" +
                "7. O stań się Jezu dla duszy,\nradością Paschy wieczystej\ni nas, wskrzeszonych Twą mocą,\ndo swego przyłącz orszaku.\n\n" +
                "8. Niech Ciebie, Panie promienny,\npowstały z martwych po męce,\ni Twego Ojca, i Ducha\nwysławia rzesza zbawionych. Amen.\n\n",
        "1. Kiedy rano słońce dotyka mnie\nswym promieniem, którym rozdziera noc,\ntak ze śmierci wolno znów budzę się.\nZrzucam z oczu sen, ściągam z serca mrok,\n" +
                "ja po swoim życiu rozglądam się,\nnie sprzątałem tutaj przez wiele lat.\nGdy za sobą cicho zamykam drzwi,\nmyślę, co mi dziś przygotował świat.\n\n" +
                "Ref. Tak przez życie biegnę, potykam się,\nszukam wciąż miłości, znajduję śmierć.\nNoszę w sercu pustkę i w oczach łzy,\nwiem, że ulgę możesz dać tylko Ty.\n\n" +
                "2. Chcę inaczej już rozpoczynać dzień,\nchcę dotykać sercem obietnic Twych.\nW Twoim słowie szukać co rano chcę\nTwej Miłości, tylko w Niej pragnę żyć.\n\n" +
                "Ref. Idę, Panie, biegnę, doganiam Cię,\nzbliża mnie modlitwa, oddala grzech.\nDam Ci w sercu miejsce, tam wprowadź się,\nz Tobą w sercu Panie chcę skończyć bieg.\n\n",
        "Idzie mój Pan, idzie mój Pan.\nOn teraz biegnie, by spotkać mnie. /x2\nMija góry, łąki, lasy, by komunii stał się cud.\nOn chce chlebem nas nakarmić, by nasycić życia głód.\n\n",
        "Ref. Idziemy na Syjon, na Syjon, na Syjon.\nIdziemy na Syjon, do raju, na Syjon.\n\n" +
                "1. Pan jest moim Pasterzem,\nniczego mi nie braknie.\nPozwala mi leżeć\nna zielonych pastwiskach.\n\n" +
                "2. Choćbym chodził w ciemnościach\nzła się nie ulęknę\nPrzez ciemną dolinę\nja do nieba pobiegnę.\n\n",
        "In manus tuas, Pater, commendo spiritum meum.\nIn manus tuas, Pater, commendo spiritum meum.\n\n",
        "Ja kocham Cię, Panie, wznoszę głos,\nuwielbiać Cię z całej duszy chcę.\nRozraduj się dźwiękiem modlitw mych,\npragnę miłą pieśnią być w uszach Twych.\n\n",
        "Ja się modlę, jakbym darł palcami ziemię\ni wygrzebywał z ziemi kamień za kamieniem.\nJa się modlę, jakbym darł palcami ziemię,\n" +
                "ale jałowe, puste moje pole.\nI chociaż mam, co chciałem, czuję opuszczenie,\njak gdyby chleba brakło na mym stole.\n\n",
        "Ref. Ja śpię, lecz moje serce czuwa\nDo drzwi miły mój puka.\nMój miły jest mój, a ja jestem jego,\nOn jest cały mój, a ja cała jego.\n\n" +
                "1. Oto stoję u twych drzwi ma ukochana,\nJeśli mnie usłyszysz, otwórz, abym wszedł,\nBędziemy razem wieczerzać do rana.\nO, najpiękniejsza z niewiast, ponad życie miłuję Cię.\n\n" +
                "2. Nie zbudźcie ze snu mej ukochanej,\nNie rozbudzajcie jej póki nie zechce sama,\nUcałować mych ust, pocałunkami swymi.\nNie zbudźcie jej ze snu, póki nie zbudzi się sama.\n\n",
        "1. Ja wierzę, że to Jezus, wierzę, że On Synem Boga jest.\nOn zmarł i powstał, aby żyć, i za cenę śmierci życie dać.\n\n" +
                "Ref. Wierzę, że jest tu teraz (On tu teraz jest),\nstoi pośród nas (stoi pośród nas),\nma moc nas teraz uzdrawiać (uzdrowienia moc),\nma przebaczenia dar.\n\n" +
                "2. Ja wierzę Tobie, Panie, wierzę, że Tyś Synem Boga jest.\nZmarłeś i powstałeś, aby żyć, i za cenę śmierci życie dać.\n\n" +
                "Ref. Wierzę, że tutaj jesteś (Ty jesteś tu),\nstoisz pośród nas (stoisz pośród nas).\nMasz moc nas teraz uzdrawiać (uzdrowienia moc),\nmasz przebaczenia dar.\n\n",
        "Ref. Ja, Pan Bóg twój, ująłem cię za prawicę,\nmówiąc do ciebie: „Nie lękaj się,\nprzychodzę ci z pomocą”.\nNie bój się robaczku, Jakubie, nieboraku Izraelu.\n" +
                "Ja cię wspomagam, wyrocznia Pana\ni Ja jestem twym Odkupicielem.\nOdkupicielem, Ja Pan, Odkupicielem, Ja Pan.\n\n",
        "1. Jak Dawid, kiedy przed Bogiem był,\nbędę tańczyć ze wszystkich sił\nprzed Panem, Królem mym.\n" +
                "Jak Miriam grała na bębnie swym,\nbędę klaskać ze wszystkich sił\nprzed Panem, Królem mym.\n\n" +
                "Ref. Pokłońmy się przed Bogiem,\noddajmy Jemu cześć!\nWielbijmy Imię Jego,\nśpiewajmy Jemu pieśń!\n\n" +
                "2. Jak Judejczycy przed bitwą swą,\nzawierzmy Panu i chwalmy Go,\npobity będzie wróg.\n" +
                "Jak Jozue wznieśmy bojową pieśń,\noddajmy Panu chwałę i cześć,\na wróg ucieknie wnet.\n\n",
        "Jak dobrze jest dziękować Ci Panie,\ni śpiewać psalm Twojemu Imieniu.\nI opowiadać rano Twoje miłosierdzie,\n" +
                "a w nocy wierność Twoją,\nprzy dziesięciostrunnej harfie\ni lutni, i dźwięcznej cytrze.\n\n",
        "1. Jak Franciszek ubogi, nasz brat,\nwyruszamy dzisiaj w świat.\nEwangelią żyć pragniemy,\nprzykład ludziom dawać chcemy.\n" +
                "Idąc w góry i doliny,\nśpiewać chcemy Bogu pieśń,\nbraciom, siostrom dawać radość,\nbo Jezus z nami jest.\n\n" +
                "Ref. My z Chrystusem z San Damiano\nwyruszamy dzisiaj w świat.\nI ty chodź, bracie, z nami,\ndo nieba wydeptać szlak. /x2\n\n" +
                "2. Jak Franciszek ubogi, nasz brat, klaskać w dłonie chcemy w takt,\ndo melodii braci ptaków, polnych ziół i traw, i maków.\n" +
                "My jesteśmy heroldami Tego, który stworzył świat.\nPana niebios kuglarzami, zdobywamy dla Niego świat.\n\n" +
                "3. Jak Franciszek ubogi, nasz brat, wyruszamy dzisiaj w świat.\nRazem chcemy dojść do celu, do nieba, gdzie świętych wielu.\n" +
                "Wielka radość więc z nas bije, gdy spotkamy polny kwiat,\nbo my wiemy, że to wszystko, to miłości Bożej znak.\n\n",
        "Jak łania pragnie wody ze strumieni,\nmoja dusza pragnie Cię.\nTylko Ty jesteś moim pragnieniem,\nzawsze chcę uwielbiać Cię.\n" +
                "Tylko Ty jesteś mocą mą. Twoja wola, wolą mą.\nTylko Ty jesteś moim pragnieniem,\nzawsze chcę uwielbiać Cię.\n\n",
        "Jak ożywczy deszcz, Duchu Święty, przyjdź,\ndotknij naszych serc, rozpal ognia żar.\nDziś przenikaj nas, tchnieniem mocy swej,\nbądź nam światłem dnia i nadzieją serc.\n\n",
        "1. Jak piękne jest mieszkanie Twe,\no Wszechmogący,\nbo dusza ma, za Tobą tęskni wciąż.\n" +
                "Tu serce me szczęśliwe jest w Twej Obecności,\nwięc śpiewam Ci w cieniu skrzydeł Twych.\n\n" +
                "Ref. Lepszy jeden dzień w Przedsionkach Twych,\nlepszy jeden dzień w Mieszkaniu Twym,\n" +
                "lepszy jeden dzień w Przedsionkach Twych,\nniż tysiąc innych.\n\n" +
                "2. O, pozwól mi oglądać dziś Twoje\nPiękno i znaleźć Cię w miejscu chwały Twej. /x2\n\n" +
                "3. Me serce woła wciąż do Ciebie, Boże mój.\nTwój Duch jest wodą duszy mej.\nSmakować Ciebie chcę. Przyjdź, znowu pragnę Cię.\n" +
                "Podchodzę blisko tak,\npodchodzę blisko tak.\n\n",
        "1. Spójrz na ptaki niebieskie,\nktóre daje nasz Bóg.\nCzyż nie jesteś czymś więcej od nich tu.\n\n" +
                "2. Nie troszcz się o swe życie,\nco jeść będziesz, co pić.\nWie twój Ojciec najlepszy, co dać ci.\n\n" +
                "3. Szukaj wpierw Królestwa,\nktórym jest Jezus, Bóg.\nA otrzymasz zapłatę za swój trud.\n\n",
        "1. Jak wielka, o Panie, zachodzi przemiana,\ngdy przed Twym obliczem uginam kolana.\n" +
                "Jak wielkie z mej duszy spadają ciężary,\npustynia na ogród przemienia się wiary.\n\n" +
                "Ref. Klękamy, a wszystko ucicha dokoła.\nStajemy, a zorza otacza nam czoła.\n\n" +
                "2. Klękamy w słabości i pełni boleści,\nstajemy by z mocą ogłaszać Twe wieści.\n" +
                "Więc po co się sami i innych krzywdzimy.\nI zamiast być mocni, się stale martwimy.\n\n" +
                "3. Wzdychamy, że troski nas ciężar przygniata.\nGdy mamy modlitwę, co w niebo ulata.\n" +
                "Wyrabiając moc, odwagę i radość.\nBo Ty naszym prośbom uczynić chcesz zadość.\n\n",
        "1. To On, nasz Pan i Król, odziany w majestat Swój,\nZiemio, raduj się, Ziemio, raduj się.\n" +
                "Okrywa światłość Go, ucieka ciemność i zło\ni drży na Jego głos, i drży na Jego głos.\n\n" +
                "Ref. Jak wielki jest Bóg.\nJak wielki jest Bóg.\nJak wielki jest nasz Bóg.\n\n" +
                "2. On czas w swej dłoni ma, od wieków na wieki trwa.\nPoczątkiem i Końcem On, Początkiem i Końcem On.\n" +
                "Jedyny w Trójcy Bóg, Ojciec, Syn i Duch,\nBarankiem jest i Lwem, Barankiem jest i Lwem.\n\n" +
                "Bridge:\nNa wieki godzien jest,\nprzyjąć chwałę moc i cześć.\nOgłaszać chcę jak wielki jest nasz Bóg.\n\n",
        "Jak wielki jest Pan, Alleluja.\nWielki jest Pan, Alleluja! Wielki jest Pan, Alleluja!\n\n",
        "1. Późno już otwiera się noc,\nsen podchodzi do drzwi na palcach jak kot.\nNadchodzi czas ucieczki na aut.\nGdy kolejny mój dzień wspomnieniem się stał.\n\n" +
                "Ref. Jaki był ten dzień, co darował, co wziął.\nCzy mnie wyniósł pod niebo, czy rzucił na dno.\nJaki był ten dzień, czy coś zmienił, czy nie.\nCzy był tylko nadzieją na dobre i złe.\n\n" +
                "2. Łagodny zmrok przesłania mi twarz,\njakby przeczuł, że chcę być sobą choć raz.\nNie uskarżę się, że mam to, co mam,\nże przegrałem znów wszystko i zostałem tu sam.\n\n",
        "1. Jakże pójdziemy za Tobą, Chrystusie,\ngdy Ty nie chodzisz naszymi drogami,\na Twoje myśli tak różne od naszych,\nmiłość tak inna od naszej miłości.\n\n" +
                "2. Serca samotne błąkają się w mroku,\nszukając ścieżki wiodącej ku światłu.\nWięc nie zostawiaj nas samych w ciemności,\nprzyjdź nam z pomocą, pielgrzymom tej ziemi.\n\n" +
                "3. Daj nam Cię poznać po chleba łamaniu,\na wtedy pokój wypełni nam dusze.\nPozostań z nami i nakarm swym Ciałem,\nobdarz radością spotkania w wieczności.\n\n" +
                "4. Panie i Mistrzu, idący wraz z nami\npo drogach świata, Ty serc naszych szukasz.\nWielbimy Ciebie i Ojca wraz z Duchem,\nTrójcę Jedyną sławimy na zawsze.\n\n",
        "Ref. Jam niegodzien, Panie, tego coś mi dał,\nTyś mnie tak biednego umiłować chciał.\n" +
                "Nic nie mam swojego, wszystko z ręki Twej,\nale mnie samego przyjąć chciej.\n\n" +
                "1. Jestem jak pył ziemi, który niesie wiatr,\njakby padający z nieba w ziemię grad.\n" +
                "Jestem jak rozbity przez wichurę dzban,\ngdy nie stoisz przy mnie, Ty, mój Bóg i Pan.\n\n" +
                "2. Przeciw moim wrogom, Ty mi dajesz moc.\nZ Tobą mi nie straszna nawet śmierć i noc.\n" +
                "Czuję, żeś tu blisko, żeś mi pomóc chciał,\nchoć nie jestem godzien tego, coś mi dał.\n\n",
        "1. Jeden chleb, co zmienia się w Chrystusa Ciało,\nz wielu ziaren pszenicznych się rodzi.\nJedno wino, co się Krwią Chrystusa stało,\nz soku wielu winnych gron pochodzi.\n\n" +
                "Ref. Jak ten chleb, co złączył złote ziarna,\ntak niech miłość łączy nas ofiarna.\nJak ten kielich łączy kropel wiele,\ntak nas Chryste, w swoim złącz Kościele.\n\n" +
                "2. Pasterzu, zgromadź w jednej swej owczarni,\nzabłąkane owce, które giną.\nW jeden Kościół zbierz na nowo i przygarnij,\nbyśmy jedną stali się rodziną.\n\n" +
                "3. Na ramiona swoje weź o Panie,\ntych, co sami wrócić już nie mogą!\nNiechaj zjednoczenia cud się stanie,\nprowadź nas ku niebu wspólną drogą\n\n",
        "Jeden jest tylko Pan, tutaj jest Jego dom.\nKlęknijcie wszyscy, którzy tu wchodzicie\ni razem chwalmy Go.\n\n" +
                "Miejsce to wybrał Pan, aby wysłuchać nas.\nŚpiewajmy wszyscy słudzy Jego domu,\nBogu oddajmy cześć.\n\n",
        "1. Jedna miłość, jedno niebo, jeden Jezus, jeden Bóg.\nJedna łaska, jedna radość, jeden Kościół, jeden Duch.\n\n" +
                "Ref. Przyjdźmy wszyscy przed Jego tron,\nchwałę oddać Mu.\nNiech króluje nad nami\nBoży Syn. /x3\n\n" +
                "2. Nasze życie w Twoich rękach. Co chcesz możesz zrobić z nim.\nW Twoich ranach nasze zdrowie. Nasza wolność w Twojej krwi.\n\n",
        "Ref. Jedynie w Bogu spokój znajduje dusza ma\nW Nim zbawienie udziałem moim jest.\n\n" +
                "1. Tylko On Opoką i Zbawieniem mym,\nOn moją Twierdzą, Skałą, nie zachwieję się.\n\n" +
                "2. Jedynie w Bogu szukaj spokoju duszo ma,\nMoja nadzieja – nie ulęknę się!\n\n" +
                "3. Jemu zaufaj – On ucieczką Twą –\nPrzed Nim serce dziś wylewaj swe.\n\n" +
                "4. W Bogu Zbawienie, chwała. On Opoką mą,\nW Nim ucieczka, tam schronienia szukaj duszo ma.\n\n",
        "Jedyny Pan, prawdziwy Bóg.\nWszystko co mam, oddaję Mu.\nOn jedynym Panem jest,\nSz’ma Izrael! /x2\n\n",
        "Ref. Jego krwią zostałem uzdrowiony,\nJego krew obmyła rany me.\nJego ranach odnajduję zdrowie,\nW Jego Krzyżu życie moje jest.\n\n" +
                "1. Z głębin śmierci wyrwałeś moją duszę,\nZ błota grzechu wyprowadziłeś mnie.\nW Tobie Panie całe moje szczęście,\nKrwią Twoich ran uzdrowiłeś mnie.\n\n" +
                "2. Okazujesz, Panie, Twoje miłosierdzie,\nRozpraszasz moje ciemności, oświecasz drogę.\n" +
                "Kruszysz kajdany, wyprowadzasz mnie na wolność,\nChcę głosić Twe dzieła całym życiem swym\n\n",
        "Jego miłość zakrywa grzech,\nOna nie pamięta złego,\nJego krew największy dar dla każdego.\n" +
                "Jego imię potężna moc,\nJezus drogą, prawdą, życiem.\nEmmanuel, Bóg pośród nas, Odkupiciel.\n\n" +
                "Ref. Bóg w łasce swej do nas zniżył się.\nPozostawił chwałę nieba,\nJezus wycierpiał każdy trud,\nżebyś żył i już się nie bał.\n\n" +
                "Ref. 2 Ooo…\nJezus królem królów jest.\nOoo…\nNasza wolność tylko w nim. (x2)\n\n" +
                "Bridge.\nTylko Tobie należy się chwała.\nTylko Tobie należy się cześć.\nTylko Tobie będziemy się kłaniać\nJezu!\n\n",
        "Ref. Jeruzalem, Jeruzalem to miasto,\nna które czekamy. /x2\nZe względu na licznych przyjaciół,\n" +
                "ze względu na wielu mych braci,\nbędę się modlił o dobro dla ciebie\ni wołał: „Pokój z Tobą”.\n\n" +
                "1. Jest radość we mnie, ktoś mi rzekł:\n„Chodźmy do domu Pana”.\nJuż stoją stopy u twych brame\n- święte Jeruzalem.\n\n" +
                "2. I ród za rodem śpiewa,\nby wielbić Imię Pana.\nTeż wielbią, Boże, tron Twój\ni święte Jeruzalem.\n\n" +
                "3. A proście o dar, by Pan sam\nkochał to swoje miasto.\nNiech rośnie pokój, gdzie twój mur,\nświęte Jeruzalem.\n\n",
        "1. Jest na świecie miłość,\nktórej Imię srebrzystym promieniem objęło ziemię.\n" +
                "Gdy ci będzie smutno,\nImię to wypowiedz, a przy tobie Ona zjawi się.\n\n" +
                "Ref. Matka z radością poda dłoń,\ngdy powiesz: „Mario,\nw Tobie ufność mam (w Tobie ufność mam).\n" +
                "Kiedy jesteś przy mnie, znika ból i w oczach łzy,\npragnę z Tobą być, Ty dodaj sił”.\n\n" +
                "2. Jest w Niej tyle ciepła,\na w spojrzeniu Jej dobroć i troska matczyna gości.\n" +
                "Otwórz serce swoje, schowaj Ją głęboko\ni jak dziecko powiedz: „Kocham Cię”.\n\n",
        "Ref. Jestem kochany z moim grzechem,\njestem kochany z mą słabością.\nZa darmo ukochał mnie Pan,\numarł za mnie i zmartwychwstał, abym żył.\n\n" +
                "1. Byłem umarły w moich grzechach,\nŻyjąc według sposobu tego świata,\nWedług ducha, który działa w synach buntu.\n" +
                "Postępując według żądz mego ciała,\nSpełniałem zachcianki myśli zdrożnych.\nZ natury zasłużyłem na gniew,\n" +
                "A Bóg przez wielką swą miłość,\nZ Chrystusem przywrócił mnie do życia. /x2\n\n" +
                "2. Umiłował mnie, gdy byłem umarły,\nWskrzesił z martwych i posadził w niebiosach .\nNic ode mnie, lecz wszystko darem Boga,\nNie z uczynków, abym się nie chlubił.\n" +
                "Będąc obcym w społeczności Izraela,\nBez udziału w przymierzach obietnicy,\nPrzez krew Pana stałem się bliski\nI zbawiony poprzez Jego łaskę. /x2\n\n",
        "1. Światłością swą Ty rozjaśniasz ciemności.\nUlecz mój wzrok, abym mógł,\nwidzieć Twą twarz i spojrzenie miłości,\nw którym roztapiasz mój strach.\n\n" +
                "Ref. Jestem tu by wielbić, jestem tu by chwalić,\njestem tu by wyznać, Tyś mój Bóg.\n" +
                "Jesteś tak cudowny, jesteś tak wspaniały,\njesteś godzien przyjąć chwałę, cześć.\n\n" +
                "2. Król mój i Pan wywyższony na wieki,\nTobie należy się cześć.\nW pokorze Swej, Ty przyszedłeś na ziemię,\nzanieść me grzechy na Krzyż.\n\n" +
                "3. To miłość Twa sprawiła, że\nz mych grzechów Tyś uwolnił mnie. /x5\n\n",
        "Ref. Jestem Twój, na wieki Twój.\nNikt i nic nie rozerwie już więzów miłości Twej.\nJesteś mój, na wieki mój.\nTwoja miłość złączyła nas, Twój Krzyż.\n\n" +
                "1. I nic więcej już nie potrzeba mi,\nniczego już nie pragnę, gdy przy mnie jesteś Ty.\nTwoja łaska wzmacnia mnie,\n" +
                "słowo Twe oczyszcza mnie.\nJesteś Drogą, Prawdą, Życiem mym na zawsze.\n\n" +
                "2. Imię Twoje, Panie, jest jak miód,\ndotyk Twój rozpala ogień w sercu mym.\nTwoja krew obmyła mnie,\n" +
                "Twoja śmierć zmazała grzech.\nDziś w światłości z Tobą mogę żyć na zawsze.\n\n",
        "Jesteś blisko mnie,\ntęsknie za Duchem Twym.\nKocham kroki Twe\ni wiem, jak pukasz do drzwi.\n" +
                "Przychodzisz jak ciepły wiatr.\nOtwieram się i czuję znów, że:\nTwoja miłość jak ciepły deszcz,\nTwoja miłość jak morze gwiazd za dnia.\n" +
                "Twoja miłość sprawia, że\nnieskończenie Dobry,\nŚwięty Duch ogarnia mnie.\n\n",
        "Jesteś Królem, jesteś Królem, Królem jest Bóg.\nPodnieśmy wszyscy nasze serca,\npodnieśmy wszyscy nasze dłonie,\nstawajmy przed obliczem Pana, wielbiąc Go.\n\n",
        "1. Zawsze wierny byłeś mi,\nkiedy zawodziłem ja.\nNiepojęty w Miłości swej,\ndobrze jest z Tobą być.\n" +
                "Darowałeś życie mi,\nzabrałeś winę mą i grzech.\nNikt nie kocha tak jak Ty\ndałeś życie swe.\n\n" +
                "Ref. Bo jesteś dobry.\nDla mnie zawsze Twoja łaska,\nwiecznie trwa.\nBo jesteś dobry.\nDla mnie zawsze Twoja łaska,\nna wieki trwa.\n\n" +
                "2. Niepojęty jesteś Ty,\njednak sługą stałeś się.\nSłyszysz każdy płacz i szept,\nMiłosierny mój Pan.\n" +
                "Przed świętością Twoją drżę,\nspraw bym czyste serce miał\nWobec świata chcę kochać Cię,\ndawać miłość Twą \n\n",
        "1. Jesteś Panie winnym krzewem,\na my Jego latorośle.\nMoc czerpiemy tylko z Ciebie,\nTy w nas żyjesz swą Miłością.\n\n" +
                "Ref. Panie, Panie,\nnaucz nas w miłości Twojej trwać. /x2\n\n" +
                "2. Cóż trwałego poza Tobą, człowiek zdziała sam ze siebie,\nkto potrafi własne dobro oblec w kształty nieśmiertelne.\n\n" +
                "3. Ty przyjaźnią nas ogarniasz, tak wspaniałą i szeroką,\nże wyzwalasz pełną radość w sercu, które Ciebie kocha.\n\n" +
                "4. Wejdź o Jezu, w nasze życie głębią łaski, mocą słowa,\nbyśmy mogli owoc przynieść, cześć i chwałę Ojcu oddać.\n\n",
        "Jesteś życiem mym, więc każdy ruch\nrobię w Tobie Jezu.\nTy pozwalasz wciąż oddychać mi.\n" +
                "Jesteś drogą mą, więc każdy krok\nstawiam w Tobie, Jezu.\nTy pozwalasz wciąż oddychać mi.\n\n" +
                "Ref. Fale Twojej łaski, Panie,\ngdzie nie spojrzę, tam wciąż widzę Cię.\nTwa miłość mnie uwiodła,\nJezu, jakże to możliwe jest!\nNa, na, na.\n\n",
        "Ref. Jesteście przyjaciółmi, powiedział Pan,\njeżeli czynicie, co przykazałem wam.\n\n" +
                "1. Nie mogę sam iść przez życie,\nbo to droga donikąd.\nNie mogę żyć już tylko dla siebie,\nTy wzywasz mnie do miłości.\n\n" +
                "2. Nie żyjmy tylko dla siebie i bądźmy darem dla drugich.\nTo przykazanie zostawił nam Chrystus, abyśmy żyli wiecznie.\n\n",
        "Jest jedno Ciało, jest jeden Pan,\njednoczy nas w Duchu, byśmy razem szli.\nUsta głoszą chwałę Mu, w ręku słowa, Jego miecz.\n" +
                "W moc odziani tak idziemy, zdobywając ziemię tę.\n\nJesteśmy ludem Króla Chwał.\nJego świętym narodem.\n" +
                "Wybranym pokoleniem, by objawiać Jego cześć.\nJesteśmy ludem Króla Chwał,\nJego świętym narodem.\nWielbijmy Jezusa, On jest Królem całej ziemi tej.\n\n",
        "Jesteśmy piękni Twoim pięknem, Panie.\nJesteśmy piękni Twoim pięknem, Panie.\nTy otwierasz nasze oczy, na piękno Twoje, Panie.\n" +
                "Ty otwierasz nasze oczy, Panie.\nTy otwierasz nasze oczy, na piękno Twoje, Panie.\nTy otwierasz nasze oczy, Panie, na Twoje piękno\n\n",
        "1. Jeszcze się kiedyś rozsmucę,\njeszcze do Ciebie powrócę, Chrystusie.\nJeszcze tak strasznie zapłaczę,\nże przez łzy Ciebie zobaczę, Chrystusie.\n\n" +
                "2. I z taką wielką żałobą,\nbędę się żalił przed Tobą, Chrystusie,\nże duch mój przed Tobą klęknie\ni wtedy serce mi pęknie, Chrystusie.\n\n",
        "Ref. Jeśli nie ja, jeśli nie ty,\ndziś nie będziemy Jezusa czcić.\nJeśli dziś nasz nie zabrzmi śpiew,\nto kamienie wzniosą swój krzyk.\n\n" +
                "1. Błogosławię Cię, Panie i Boże mój.\nOdbierz królewską cześć,\nboś Ty godzien, by sławić Cię.\n\n" +
                "2. Okazujesz swą moc, błogosławisz mój dzień.\nPrzyjmij dziękczynną pieśń, boś Ty godzien, by sławić Cię.\n\n",
        "1. Jeśli radość w sercu chcesz mieć.\nRadość, która wiecznie ma trwać.\nPrzed tron Boga przyjdź i głoś Jego chwałę.\n" +
                "Przed tron Boga przyjdź i głoś Jego chwałę i moc.\nGłoś chwałę i moc, głoś chwałę i moc.\n\n" +
                "2. Jeśli pokój…\n\n3. Jeśli miłość…\n\n4. Jeśli ufność…\n\n",
        "Jeśli Pan domu nie zbuduje,\nNa próżno trudzą się ci, którzy go wznoszą.\nJeśli Pan miasta nie ustrzeże,\n" +
                "Strażnik czuwa daremnie.\n\nChwalcie Pana wszystkie narody,\nWysławiajcie Go wszystkie ludy,\n" +
                "Bo Jego łaska nad nami potężna,\nA wierność Pańska trwa na wieki.\nA wierność Pańska trwa na wieki.\n\nDaremnym jest dla was wstawać przed świtem,\n" +
                "Wysiadywać do późna dla was,\nKtórzy jecie chleb, zapracowany ciężko.\nDaje on to we śnie tym, których miłuje.\n\n",
        "Jezu, Baranku zraniony moim grzechem,\nuwielbiam Cię, najczulszy Synu Ojca.\nBłogosławię Cię, umiłowany Panie, kocham Cię.\n\n",
        "Ref. Jezu Chryste, Arcykapłanie\nspójrz na naszych braci\nPanie spraw, by aniołowie ziemi byli Tobie wierni.\n\n" +
                "1. Niech będą Aniołami czystości,\nKtóre Twoją miłość stawiają ponad inną.\nNiech będą Aniołami miłości,\nKtóra rezygnuje ze szczęścia ludzkiej rodziny.\n\n" +
                "2. Niech będą Aniołami światła,\nKtóre w sercach zapalają wiarę.\nNiech będą Aniołami ofiary,\nKtóra spala ich samych dla dobra braci.\n\n" +
                "3. Niech będą Aniołami rady,\nKtóre niosą ludziom pociechę w bólu.\nNiech będą Aniołami męstwa,\nKtóre wzmacniają w walce.\n\n" +
                "4. Niech będą Aniołami łaski,\nKtóre dusze oczyszczają i podnoszą.\nNiech będą Aniołami pokoju,\nKtóre przy śmierci otwierają bramy niebios.\n\n",
        "Jezu, daj nam poznać Ciebie,\npozwól widzieć twarzą w twarz.\nDotknij, ulecz, trzymaj, prowadź,\nw Tobie zawsze pozwól trwać.\n\n",
        "Jezu, jesteś Królem, oddajemy Ci cześć.\nStoisz tu pośród dzieci twych,\nwielbimy majestat Twój.\nI wielbiąc Ciebie, wznosimy tron.\n" +
                "I wielbiąc Ciebie, wznosimy tron.\nI wielbiąc Ciebie, wznosimy tron.\nPrzyjdź Jezu i króluj nam.\n\n",
        "Jezu, jesteś tu, świat odszedł w cień.\nMam tylko Ciebie, moje życie to Ty.\nKażdy dzień Twoim darem, nie przestanę wielbić Cię.\nChwała, chwała, Jezu, wielbię Cię! /x2\n\n",
        "Jezu pokaż mi mój grzech.\nBym mógł zobaczyć Twą twarz.\n\n",
        "Jezu, Jezu,\noto jestem przed obliczem Twym.\nJezu, Jezu,\nTobie daję całe życie me. /x3\n\n",
        "Jezu, mój Jezu, dziś do Ciebie mówić chcę.\nTy mi dajesz swojego Ducha, uwielbiam Cię.\n\n",
        "1. Jeśli mnie nie podtrzymasz Panie, to umrę.\nWspomnij na mnie ze swego Królestwa.\n" +
                "Bo chcę uciec z miejsca, gdzie mnie postawiłeś.\nWcale nie jest mi łatwo oddać życie.\n\n" +
                "Ref. Jezu, Jezu, wspomnij na mnie.\nJezu, Jezu, wspomnij na mnie.\n\n" +
                "2. Podtrzymaj moje mdlejące ramiona.\nNie pozwól mi zejść z Drzewa życia.\nTu, wysoko, mi pokaż ziemię obietnicy.\nTu przed śmiercią niech się rozraduję.\n\n",
        "Jezu, Tyś jest światłością mej duszy,\nniech ciemność ma nie przemawia do mnie już.\nJezu, Tyś jest światłością mej duszy,\ndaj mi moc przyjąć dziś miłość Twą.\n\n",
        "Jezu, Tyś wszystkim dla mnie jest,\njak żyć bez miłości Twej?\nJezu, otwórz me oczy,\nbym ujrzeć mógł Twej miłości cud.\n" +
                "Przyjdź i napełnij nas mocą Bożą co dnia.\nTyś pokoju Księciem, Lekarzem dusz, jesteś Światłem,\nco w ciemności nigdy nie gaśnie.\n\n",
        "Ref. Jezus Chrystus jest moim Zbawieniem.\nIdąc z Nim nie lękam się,\nbo w moim sercu gości pewność,\nże mój Pan dziś ze mną jest. /x2\n\n" +
                "1. Wychwalam Cię Panie, mój Boże,\nbo byłem daleko od Ciebie.\nTeraz znów Cię odnalazłem.\nW Twoim ręku jest życie me.\n\n" +
                "Biegnijcie z radością do źródeł,\ndo źródeł Bożego Zbawienia.\nKażdego dnia chwalcie Pana,\nJego Imienia wzywajcie wciąż.\n\n" +
                "2. Ogłoście całemu ludowi,\nwielkie dzieła Bożej miłości.\nI z radością wspominajcie,\njak jest wielki i święty Pan.\n\n" +
                "3. Śpiewajcie o Stwórcy wszechrzeczy,\nniech świat cały pozna Boga.\nTrwaj w radości, o Syjonie,\ngdyż Zbawiciel przy Tobie jest.\n\n",
        "Jezus Chrystus moim Panem jest, Alleluja! /x2\nOn kocha mnie, On kocha mnie, Alleluja. /x2\n\n",
        "Jezus Chrystus Panem jest, Król to królów, panów Pan.\nCała ziemia jego jest, po najdalszy świata kres\nJezus, Jezus!\n" +
                "królów Król, królów Król!\nJezus, Jezus!\nŚwiata Pan, Świata Pan!\nŚwiata Pan!\n\n",
        "Jezus Chrystus przyszedł na ziemię.\nPrzyszedł po to, by zbawić ciebie.\nI właśnie On, przez moc swojej Krwi,\n" +
                "zbawienie dał, byś miał je i ty!\nCzy cieszysz się? Tak, wciąż cieszę się!\nAlleluja, amen! Alleluja, amen!\n\n",
        "Jezus Chrystus, Syn Boga, Zbawiciel.\nJezus Chrystus, Syn Boga, Zbawiciel.\n\n",
        "Jezus Chrystus to Panów Pan, całej ziemi Król. /x2\nJedyny Pan, Władca narodów, jedyny Pan i Bóg.\n" +
                "W innym Imieniu nie ma zbawienia,\nbo Jezus to Panów Pan.\nJezus to Panów Pan. /x2\nAlleluja. /x2\n\n",
        "Jezus Chrystus, Bóg i człowiek,\nOdkupiciel, wczoraj dziś na wieki.\n\n",
        "1. Jezus daje nam zbawienie,\nJezus daje pokój nam.\nJemu składam dziękczynienie,\nchwałę z serca mego dam.\n\n" +
                "Ref. Jezus Siłą mą, Jezus Pieśnią mego życia.\nKrólem wiecznym On, niepojęty w mocy swej.\n" +
                "W Nim znalazłem to, czego szukałem do dzisiaj.\nSam mi podał dłoń, bym zwyciężał w każdy dzień.\n\n" +
                "2. W Jego ranach uzdrowienie, w Jego śmierci życia dar.\nJego śmierć to oczyszczenie, Jego życie chwałą nam.\n\n",
        "1. Kładę życie u Twych stóp. Potrzebuję byś tu był.\nJesteś zawsze kiedy braknie sił.\nSzukam Cię gdy jest mi źle.\n" +
                "Pragnę więcej łaski Twej.\nChcę Ci oddać całe serce swe.\n\n" +
                "Ref. Jezus Drogą,\njesteś Panem i dla Ciebie żyję. /x4\n\n" +
                "2. Twa obecność wieczna jest. Miłość trwa, nie skończy się.\nUwielbienie Tobie pragnę wznieść.\n" +
                "Źródłem życia mego Pan. Wczoraj, dzisiaj, taki sam.\nBogiem nieskończonym jesteś Ty.\n\n" +
                "3. Miłością Swą dziś obdarzasz mnie,\nja wierzę chociaż nie widziałem Cię.\nZ Tobą pragnę żyć. /x4\n\n",
        "1. Jezus jest tu, Jezus jest tu.\nwznieśmy ręce, wielbiąc Jego Imię, Jezus jest tu!\n\n" +
                "2. Pan jest wśród nas, Pan jest wśród nas.\nO wznieśmy ręce, wielbiąc Jego Imię, Pan jest wśród nas.\n\n" +
                "3. Bóg kocha nas, Bóg kocha nas.\nO wznieśmy ręce, wielbiąc Jego Imię, Bóg kocha nas.\n\n",
        "Jezus jest z nami tu, /x3\na Jego moc działa dziś, by uwolnić Cię.\nA Jego moc działa dziś, by uzdrowić Cię.\n" +
                "A Jego moc działa dziś, by zbawić Cię.\nJezus jest z nami tu. /x2\n\n",
        "Jezus mą miłością jest, Jezus moją miłością jest\nPanie, kocham Cię.\n\n",
        "Jezus mój /x2 dobry jest. /x2\nDobry jest /x2 Jezus mój. /x2\nDoradca, Boży Syn, dobry jest, dobry jest.\nJezus mój /x2 wspaniały jest. /x2\n" +
                "Wspaniały jest /x2 Jezus mój. /x2\nKsiążę Pokoju, Wszechmogący Bóg.\nWspaniały jest, wspaniały jest, wspaniały jest.\n\n",
        "Jezus oddał za nas życie swe, Jezus kocha mnie. /x2\n\n" +
                "Ref. Kochajmy Pana, niech żyje w nas,\nZbawiciel świata po wieczny czas.\nKochajmy Pana, a serca swe,\noddajmy Jemu, gdyż kocha je, kocha je.\n\n" +
                "1. Jezus zgładził pierworodny grzech, Jezus, Jego krew. /x2\n\n" +
                "2. Jezus Swą miłość ofiarował nam, Jezus, dobroci Pan. /x2\n\n",
        "Jezus zwyciężył, to wykonało się.\nSzatan pokonany, Jezus złamał śmierci moc.\nJezus jest Panem, o Alleluja,\npo wieczne czasy, Królem królów jest.\n" +
                "Jezus jest Panem, Jezus jest Panem,\nJezus jest Panem, Jezus jest Panem.\nTylko Jezus jest Panem, Jezus jest Panem.\nOn jest Panem ziemi tej.\n\n",
        "Jezus, Najwyższe Imię,\nnasz Zbawiciel, Książę Pokoju.\nEmmanuel, Bóg jest z nami,\nOdkupiciel, Słowo żywota.\n" +
                "Święty Bóg, Mesjasz prawdziwy,\njedyny Ojca Syn umiłowany.\nZgładził grzech, Baranek na wieki,\nkrólów Król i panów Pan.\n\n",
        "Jezus, Jezus,\nŚwięty namaszczony Pan, Jezus.\nJezus, Jezus,\nżyje wywyższony Pan, Jezus.\nTwe Imię jak miód na ustach mych,\n" +
                "Twój Duch jest jak woda duszy mej.\nTwe Słowo jak lampa u mych stóp,\nJezu, ja kocham Cię, ja kocham. /x2\n\n",
        "1. Rabbuni, zlituj się.\nRabbuni, przejrzeć chcę.\nJa słyszę, że przechodzisz,\nRabbuni, uzdrów mnie, a pójdę Twą drogą!\n\n" +
                "Ref. Jezusie, Synu Dawida, ulituj się nade mną.\nJezusie, Synu Dawida, ulituj się nade mną.\n\n" +
                "2. Patrz, zrywam z siebie płaszcz, Odkrywam rany me.\nTy, Panie, wszystko wiesz. Jezu, ulituj się, Rabbuni, abym przejrzał!\n\n",
        "Ref. Jeżeli to Twój głos, mój Boże,\njeśli to do mnie wołasz donośnie, bym szedł,\njeśli to na mnie czekają łany zboża,\noto jestem, poślij mnie.\n\n" +
                "1. Do ludzi obojętnych na pracę bez nagrody\n- oto jestem, poślij mnie.\n" +
                "Przywracać zdrowie chorym, dać światło niewidomym\n- oto jestem, poślij mnie.\n\n" +
                "2. Jak owce między wilki, na wartę z Panem świata\n- oto jestem, poślij mnie.\n" +
                "Na żniwo Twojej łaski, by plony zebrać spore\n- oto jestem, poślij mnie.\n\n" +
                "3. Nieść Ciebie wszystkim ludziom, czy w porę, czy nie w porę\n- oto jestem, poślij mnie.\n" +
                "Głosić po całej ziemi, dobrej nowiny słowo,\nżeś Zmartwychwstał - poślij mnie.\n\n",
        "Już się nie lękaj, porzuć zmartwienia.\nBogu zaufaj, nic ci nie grozi.\nJuż się nie lękaj, porzuć zmartwienia.\nBóg Miłością jest.\n\n" +
                "(Nada te turbe, nada te espante; quien a Dios tiene nada le falta.\nNada te turbe, nada te espante: sólo Dios basta.)\n\n",
        "Już teraz we mnie kwitną Twe ogrody,\njuż teraz we mnie Twe królestwo jest.\n\n",
        "Każdego dnia przy Tobie trwać, poznawać Ciebie. /x2\nPragnę bliżej Ciebie być. /x2\nPoznawać Ciebie.\nJeszcze bardziej kochać Cię.\n" +
                "Jeszcze dłużej wielbić Cię.\nRozkochać się.\nKocham Cię, Kocham Cię. /x3\n\n",
        "Każdy dzień jest zwycięstwem.\nKażdy dzień jest zwycięstwem.\nKażdy dzień jest zwycięstwem,\njeśli w ręce Pana zechcesz oddać go.\n\n",
        "Każdy spragniony i słaby też\nniech przyjdzie do Źródła, serce obmyje swe.\nNiech ból i cierpienie zanurzy Pan\n" +
                "w swym miłosierdziu, niech głębia woła w nas.\nPanie Jezu, przyjdź!\nDuchu Święty, przyjdź!\n\n",
        "1. Każdy Twój wyrok przyjmę twardy,\nprzed mocą Twoją się ukorzę.\nAle chroń mnie Panie od pogardy,\nprzed nienawiścią strzeż mnie, Boże.\n\n" +
                "2. Wszak Tyś jest niezmierzone dobro,\nktórego nie wyrażą słowa.\nWięc mnie od nienawiści obroń\ni od pogardy mnie zachowaj.\n\n" +
                "3. Co postanowisz, niech się ziści,\nniechaj się wola Twoja stanie.\nAle zbaw mnie od nienawiści,\nocal mnie od pogardy, Panie.\n\n",
        "1. My, którzy wszystko liczymy,\nCiebie mamy za darmo.\nDajesz nam siebie w całości\ni jesteś taki rozrzutny.\n\n" +
                "Ref. Każdy wschód słońca Ciebie zapowiada,\nnie pozwól nam przespać poranka. /x2\n\n" +
                "2. Nie dość, że do nas przyszedłeś,\nco dzień dajesz nam siebie.\nZostałeś z nami w tym Chlebie,\nktóry bierzemy do ręki.\n\n" +
                "3. Dałeś nam siebie za darmo,\nTwoja hojność zdumiewa.\nNaucz nas liczyć dni nasze,\nniech człowiek już nie umiera.\n\n" +
                "4. Ty jesteś pełnią życia,\ndaj nam Twojego chleba.\nDaj nam oddychać wiecznością,\nTwój oddech ożywia i wspiera\n\n" +
                "5. Przyjdź Panie, przyjdź nareszcie,\njak przyszedłeś już kiedyś.\nA co dzień nowy wschód słońca,\nniech Ciebie nam zapowiada.\n\n",
        "Kiedy brakuje sił, na piękne słowa, czy gesty,\nstaję przed Tobą i\npo prostu dla Ciebie jestem. /x2\nI trwam przed Tobą wiernie trwam.\n" +
                "Pieśnią wysławiam Cię znów,\nchoć moje słowa nie płyną z ust.\nUwielbiam Ciebie całym sercem mym,\nkażdą myślą mą chwałę oddaję Ci.\n\n",
        "1. Kiedy ci smutno i nic nie wychodzi, mów:\n„Amen”, jak Maryja. /x2\n„Amen, widocznie Bóg tak chce”.\n\n" +
                "Ref. Tak, jak Maryja (i Józef),\nwypełniaj wolę Boga (jedyną),\ni zanieś tam swój uśmiech (i serce),\ngdzie często płyną łzy.\n\n" +
                "2. Gdy z ciebie szydzą i kiedy się śmieją, mów:\n\n3. Kiedy w twym sercu nic więcej, prócz bólu, mów:\n\n",
        "Kiedy cichnie gwar, wszystko kończy się,\nwtedy szukam Cię.\nChcę Ci przynieść coś, co wartościowe jest,\nco ucieszy Cię.\n" +
                "Chcę dać Ci więcej niż pieśń, bo wiem,\nże śpiew nie jest tym,\nczego ode mnie chcesz.\nTy patrzysz głębiej niż my, mimo pozoru spraw,\n" +
                "Ty patrzysz w duszę mą.\nPowracam znowu, by wielbić Ciebie,\nby wywyższać Cię, by wywyższać Ciebie wciąż.\n" +
                "Przepraszam Ciebie za moje grzechy,\nchcę wywyższać Cię, chcę wywyższać Ciebie wciąż.\n\n",
        "Kiedy jestem z Tobą nie cieszy ziemia mnie. /x2\nKtóż piękniejszy jak Ty, Panie mój, kocham Cię. /x2\n\n",
        "1. Kiedy nad równiną wschodził jasny słońca blask,\na Franciszek ścierał z oczu resztki snu,\n" +
                "wtedy dlań piękniejszym wydał się cały świat,\ngdy modlitwą sławił Boga tak, jak mógł.\n\n" +
                "Ref. Hej Franciszku, poprowadź nas,\nradośnie przez życie chcemy iść.\nDla nas jesteś słońcem, bratem w każdy czas,\n" +
                "boś swym życiem świętym\npodbił cały świat, cały świat. /x2\n\n",
        "1. Kiedy ranne wstają zorze,\nTobie ziemia, Tobie morze.\nTobie śpiewa żywioł wszelki,\nbądź pochwalon, Boże wielki!\n\n" +
                "2. A człowiek, który bez miary, obsypany Twymi dary.\nCoś go stworzył i ocalił, a czemuż by Cię nie chwalił?\n\n" +
                "3. Ledwie oczy przetrzeć zdołam, wnet do mego Pana wołam.\nDo mego Boga na niebie i szukam Go koło siebie.\n\n" +
                "4. Wielu snem śmierci upadli, co się wczoraj spać pokładli.\nMy się jeszcze obudzili, byśmy Cię, Boże, chwalili.\n\n" +
                "5. Boże w Trójcy niepojęty, Ojcze, Synu, Duchu Święty.\nTobie chwałę oddajemy, niech dla Ciebie dziś żyjemy.\n\n",
        "Ref. Kiedyś wino i chleb,\nteraz Ciało i Krew.\nMożesz wierzyć lub nie,\nto naprawdę dzieje się.\n\n" +
                "1. Ciągle czekasz na cud, niespokojny twój duch.\nA ja przypomnę, że w ciszy i przy blasku świec,\ncud największy dzieje się.\n\n" +
                "2. Wypatrujesz co dnia, czekasz na jakiś znak.\nA ja przypomnę, że w ciszy i przy blasku świec,\ncud największy dzieje się.\n\n",
        "Kim jesteś Ty, Panie, a kim jestem ja,\nkim jesteś Ty, a kim ja. Kim Ty, a kim ja.\n\n",
        "Ref. Kimże jest ta, która świeci z wysoka jak zorza.\npiękna jak księżyc, jaśniejąca jak słońce,\ngroźna jak zbrojne zastępy.\n\n" +
                "1. Piękna jesteś przyjaciółko moja,\nwdzięczna jak Jeruzalem, groźna jak zbrojne zastępy.\nOdwróć ode mnie twe oczy, bo mnie niepokoją.\n" +
                "Wiele jest królowych, nałożnic i dziewcząt,\nlecz jedna jest moja gołąbka, moja nieskalana.\nJedyna swej matki, wybrana swej rodzicielki.\n\n" +
                "2. Włosy twoje jak stado kóz falujące na górach Gileadu,\nskroń twoja jak okrawek granatu,\nskryta za twoją zasłoną.\n" +
                "Jak piękne są Twoje stopy w sandałach,\nlinia twych bioder jak kolia, łono twe - czasza okrągła.\nUsta twoje jak wino,\njak wino o najwspanialszym smaku!\n\n" +
                "3. Oto przede mną winnica moja,\noto przede mną moja własna winnica.\nTysiąc syklów Salomonie dla ciebie,\ndwieście stróżom jej owocu.\n" +
                "O Ty, która mieszkasz w ogrodach,\ndaj mi usłyszeć twój głos.\n\nO Ty, która mieszkasz w ogrodach,\ndaj mi usłyszeć twój głos!\n\n" +
                "Biegnij mój miły, bądź podobny do gazeli\nlub do młodego jelenia na górach wśród drzew.\n" +
                "Biegnij mój miły, bądź podobny do gazeli\nlub do młodego jelenia,\nna górach wśród balsamowych drzew.\n\n",
        "1. Ciągle zaczynam od nowa,\nchoć czasem w drodze upadam,\nwciąż jednak słyszę te słowa:\n„Kochać, to znaczy powstawać”.\n" +
                "Chciałem Ci w chwilach uniesień,\nżycie poświęcić bez reszty,\nspójrz, moje ręce są puste,\n" +
                "stoję ubogi ja, grzesznik.\nPrzyjm jednak małość mą, Panie,\nweź serce me, jakie jest.\n\n" +
                "2. Jestem jak dziecko bezradny, póki mnie ktoś nie podniesie,\nznów wraca uśmiech na twarzy, gdy mnie Twa miłość rozgrzeszy.\n" +
                "Wiem, że wystarczy Ci Panie dobra, choć słaba ma wola,\nz Tobą mój duch nie ustanie, z Tobą wszystkiemu podołam.\n" +
                "Szukam codziennie Twej twarzy, wracam w tę noc pod Twój dach.\n\n" +
                "3. Teraz już wiem, jak Cię kochać, przyjm moje „teraz”,\no Panie, dziś rozpoczynam od nowa, bo kochać, to znaczy powstawać.\nKochać, to znaczy powstawać.\n\n",
        "1. Kiedy odwracam się od Ciebie,\ngdy celowo nie chcę słyszeć Twoich słów,\ngłuchnie moje serce.\n" +
                "Stoisz cierpliwie obok mnie\ni słowa swe powtarzasz do mnie znów i znów,\ntak długo, aż usłyszę je.\n\n" +
                "Ref. Kocham, kocham, kocham, kocham Ciebie, Panie mój,\nśpiewa moje serce.\n" +
                "Kocham, kocham, kocham, kocham Ciebie, Panie mój\nw nieskończoność.\n\n" +
                "2. Twój głos tak delikatnie brzmi,\nsłyszę go w swoim wnętrzu nawet wtedy, gdy\nmówisz tak łagodnie.\n" +
                "Kiedy wydaje mi się, że\ndaleki jesteś zbyt od zwykłych zmartwień mych,\nTy jesteś jednak obok mnie.\n\n" +
                "3. Przed Tobą znów ukrywam wzrok,\nzawiodłem Cię tak łatwo.\nI kiedy myślę o tym, że opuścić mogłeś mnie,\na w zamian dajesz mi Twe słowo, które leczy mnie.\n\n",
        "1. Kocham Twoją wolę Panie,\njeszcze bardziej drzazgi małe,\nktórych nie potrafię przyjąć sercem.\n" +
                "Moje łzy na rzęsie przyjmij\nręce złożone złącz na zawsze,\nniech życie moje wielbić Ciebie zacznie.\n\n" +
                "Ref. Znów wstaję i biegnę do Ciebie,\nkolana zdarte krwawią jeszcze.\nAch Panie, płacze serce moje,\nwszak ranić Twej miłości nie chcę.\n\n" +
                "2. Znajdź mnie Boże w nocy serca,\nw labiryncie myśli moich,\nktórych nie mam sił już związać w uśmiech.\n" +
                "Pomnij na nieufność moją,\npomóż mi uprzątnąć wnętrze,\nCzy odpocząć możesz Panie we mnie?\n\n" +
                "Ref. Znów wstaję i biegnę do Ciebie,\nnaciskam klamkę wszak z mojej strony.\nAch Panie tak wierzyłem,\nże przyjdziesz, biło serce moje.\n\n" +
                "3. W niepewności mnie zostawiasz,\npotem nagle wznosisz serce,\naż na szczyt milczenia tajemnicy.\n" +
                "Potem nagle zbijasz z tropu\ni wyciskasz z serca wszystko,\njestem coraz słabszy, choć tak blisko.\n\n" +
                "Ref. Znów wstawać mi każesz Chryste,\no Boże sił już braknie mi.\nPrzecież kochasz swe stworzenie,\nwięc prowadź mnie na szczyt.\n\n",
        "Kocham Ciebie, Jezu,\nwznoszę ręce, dając cześć.\nCałym światem jesteś mi,\nuwielbiam Twoje Imię, Panie (mój).\n\n",
        "1. Kocham, więc nie muszę się bać.\nZabierz mój strach. /x3\nJezus mówi Ci, że miłość ta, zabiera strach. /x3\n\n" +
                "Ref. Jezus (drogą), Jezus (prawdą), Jezus (życiem).\n\n" +
                "2. Jezus daje Ci miłość swą, nie lękaj się. /x3\nJezus mówi Ci, że miłość ta, zabiera strach. /x3\n\n",
        "Kogo oprócz Ciebie mam, kto by kochał mnie jak Ty.\nKto mi nowe życie dał i zaufał mi.\nTylko Ty, Największy Pan,\n" +
                "ponad wszystko wywyższony.\nW Twoim sercu życie mam,\nchcę z radością śpiewać Ci. /x2\n" +
                "Biegnę, by przed Twym świętym tronem\nradować się.\nCieszę się, bo Twoja święta miłość\nwypełnia mnie.\n\n",
        "Król królów, Pan panów, chwała, Alleluja. /x2\nJezus, Książę Pokoju, chwała, Alleluja. /x2\n\n",
        "Królowo Nieba, wesel się,\nAlleluja, Alleluja!\nBo Ten, któregoś nosiła, Maryjo,\nAlleluja, Alleluja.\n" +
                "Zmartwychpowstał, jak powiedział,\nAlleluja, Alleluja./x2\nBłagaj za nami Boga,\nAlleluja, Alleluja,\n" +
                "Alleluja, Alleluja.\nZmartwychpowstał, jak powiedział,\nAlleluja, Alleluja./x2\n\n",
        "1. Krzyż jest źródłem mego zbawienia.\nJezus obmył mnie swoją Krwią,\nOn uleczy moje zranienia.\nKrzyż Jezusa nadzieją mą.\n" +
                "Me szaty jaśnieją jak śnieg,\nObmyła je Baranka Krew,\nOna gładzi wszelki grzech,\nBaranka Krew.\n\n" +
                "2. Krzyż jest drogą mego zbawienia,\nKażde cierpienie oczyszcza mnie.\nTrudność w drodze, upokorzenie\n" +
                "Błogosławieństwem w Krzyżu jest.\nOdważnie więc śpiewam tę pieśń.\nZnam drogę, bym wolnym mógł być:\n" +
                "Trzeba umrzeć, aby żyć!\nTrzeba życie dać!\n\n" +
                "3. Krzyż jest znakiem mego zbawienia.\nMiłość Boża rozświetla świat,\nTą miłością pragnę świecić.\n" +
                "Swoje życie w ofierze dać.\nChcę milczeć, gdy spotka mnie zło\nI w sercu Jezusa się skryć.\n" +
                "Chcę miłością w świecie być!\nChcę miłością być!\n\n",
        "1. Krzyżu Chrystusa, bądźże pochwalony,\nna wieczne czasy bądźże pozdrowiony.\nGdzie Bóg, Król świata całego,\ndokonał życia swojego.\n\n" +
                "2. Krzyżu Chrystusa, bądźże pochwalony,\nna wieczne czasy bądźże pozdrowiony.\nTa sama Krew cię skropiła,\nktóra nas z grzechów obmyła.\n\n" +
                "3. Krzyżu Chrystusa, bądźże pochwalony,\nna wieczne czasy bądźże pozdrowiony.\nZ ciebie moc płynie i męstwo,\nw Tobie jest nasze zwycięstwo.\n\n",
        "1. Krzyżu święty nade wszystko,\ndrzewo przenajszlachetniejsze.\nW żadnym lesie takie nie jest,\n" +
                "Jedno, na którym sam Bóg jest.\nSłodkie drzewo, słodkie gwoździe\nrozkoszny owoc nosiło.\n\n" +
                "2. Skłoń gałązki, drzewo święte,\nulżyj członkom tak rozpiętym,\nodmień teraz oną srogość,\n" +
                "którąś miało z przyrodzenia.\nSpuść lekuchno i cichuchno ciało Króla niebieskiego.\n\n" +
                "3. Tyś samo było dostojne\nnosić światowe Zbawienie,\nprzez cię przewóz jest naprawion\n" +
                "światu, który był zagubion.\nKtóry święta Krew polała, co z Baranka wypływała.\n\n" +
                "4. Niesłychanać to jest dobroć,\nza kogo na krzyżu umrzeć.\nKtóż to może dzisiaj zdziałać,\n" +
                "za kogo swoją duszę dać?\nSam to Pan Jezus wykonał, bo nas wiernie umiłował.\n\n",
        "Kto nie miłuje, ten nie zna Boga,\nkto trwa w miłości, przebywa w Bogu.\nKto nie miłuje, ten nie zna Boga,\nBóg miłością jest.\n\n",
        "Kto odczuwa pragnienie, niech przyjdzie, kto chce.\nNiech wody życia darmo zaczerpnie. /x2\n\nJam Alfa i Omega, Pierwszy i Ostatni,\n" +
                "Początek i Koniec.\nJam Alfa i Omega, Pierwszy i Ostatni,\nPoczątek i Koniec.\n\n",
        "Kto odczuwa pragnienie niech przyjdzie, kto chce.\nNiech wody życia darmo zaczerpnie.\n\n",
        "Kto spożywa Moje Ciało i Krew Moją pije,\nTrwa we Mnie, a Ja w nim, będzie żył na wieki.\n" +
                "Ja jestem Chlebem Żywym, który zstąpił z nieba.\nJeśli kto ten Chleb spożywa, będzie żył na wieki\n\n",
        "Kto zaufał Ci, ten uwierzył,\nże nie zawstydzi go nic.\nMoc Twego ramienia jest skałą schronienia,\nkiedy skrada się wróg.\n\n",
        "Ref. Którzy ufają Panu są jak góra Syjon,\nNieporuszona, trwająca przez wieki.\n\n" +
                "1. Upodobał sobie tych, którzy cześć Mu oddają,\nKtórzy ufają Jego dobroci.\n\n" +
                "2. Ufałem Panu nawet w wielkim ucisku,\nOn mi zawsze przychodzi z pomocą.\n\n" +
                "3. Odzyskują siły Ci, którzy Panu ufają,\nOtrzymują skrzydła jak orły.\n\n" +
                "4. Na obu swoich dłoniach wyrył me Imię,\nMiłuje mnie odwieczną miłością.\n\n" +
                "5. W krzyżu Pana moja moc jest i siła,\nNie zginie ten, kto się krzyża uchwycił.\n\n",
        "Któż, jak Bóg, Baranek i Lew\ndziś na tronie swym?\nOceany wznoszą krzyk i góry kłaniają się\nprzed Bogiem, Królem chwał.\n\n" +
                "Chwalmy Pana od świtu aż po zmierzch.\nChwały wznieśmy pieśń.\nChwalmy Pana i niechaj każdy z nas.\nJemu dziś pokłoni się.\n\n",
        "Ku Tobie, Panie, wznoszę moją duszę,\nnikt bowiem nie doznaje wstydu, kto ufa Ci.\nDaj mi poznać drogi Twoje, Panie.\nWiedź mnie ścieżką prawdy i pouczaj.\n\n" +
                "Ref. Bo Ty jesteś moim Zbawcą, Panem Jedynym.\nTy dajesz zawsze miłość, zawsze nadzieję.\nTy jesteś Panem dobrym, Panem łaskawym,\nw Tobie pokładam ufność mą.\n\n",
        "Laudate Dominum, laudate Dominum,\nomnes gentes, Alleluja!\nLaudate Dominum, laudate Dominum,\nomnes gentes, Alleluja!\n(Wychwalajcie Pana wszystkie narody.)\n\n",
        "Laudate, omnes gentes,\nlaudate Dominum.\nLaudate, omnes gentes,\nlaudate Dominum.\n\n",
        "Ref. Laudato sii, o mi Signore,\nLaudato sii, o mi Signore.\nLaudato sii, o mi Signore,\nLaudato sii, o mi Signore.\n\n" +
                "1. Niech Cię wysławia to, co stworzyłeś.\nSłońce na niebie i księżyc wśród nocy.\nGwiazdy świecące i wiatry gwałtowne.\nŻywioły wodne i ogień gorący.\n\n" +
                "2. I siostra nasza matka ziemia,\nta, co nas żywi i utrzymuje.\nSłodkie owoce, kwiaty i zioła\ni szczyty górskie i morskie głębie.\n\n" +
                "3. Trzeba nam chwalić Ciebie, o Boże.\nTrzeba nam śpiewać o Tobie, Boże,\nbo to jest sensem naszego życia.\nNiech całe ono będzie piosenką.\n\n",
        "Ref. Laudato Sii, Signiore mio,\nLaudato Sii, Signiore mio,\nLaudato Sii, Signiore mio,\nLaudato Sii, Signiore mio,\n\n" +
                "1. Niech Cię chwali brat nasz, słońce,\nswoim ciepłem, swoim światłem,\nby wśród mroków naszej drogi,\nznaleźć Ciebie było łatwiej.\n\n" +
                "2. Niech Cię sławią księżyc, gwiazdy,\nniezliczone nasze siostry,\nco mrugają do nas z nieba,\nby rozproszyć ciemność nocy.\n\n" +
                "3. Niech Cię chwali matka Ziemia,\nuprawiana w pocie czoła,\nktóra rodzi złote zboża,\nbarwne kwiaty oraz zioła.\n\n" +
                "4. Bądź wsławiony przez cierpienie\ni odwagę przebaczenia.\nNiech Twój pokój zstąpi na nas,\ngdy nas życia zmierzch ocienia.\n\n" +
                "5. Niech Cię chwali nasza siostra,\nśmierć, co przyjdzie niespodzianie\ni zabierze nas do Ciebie,\ngdzie trwa wieczne miłowanie\n\n" +
                "6. Niech wychwala Ciebie Panie,\nmiłość chłopca i dziewczyny.\nA niewinność czystych dzieci,\nniech nam będzie wzorem czynów.\n\n" +
                "7. Tobie śpiewać chcemy, Boże,\nCiebie chwalić z całym światem.\nCałym sercem dzięki składać,\nboś nam Panem jest i Bratem.\n\n",
        "1. Drogi Boże piszę chociaż kilka słów\ninnym razem napiszę więcej.\nNa początku życzę Ci wszystkiego dobrego\ni pozdrawiam Cię najgoręcej.\n" +
                "Tak się jakoś złożyło, że nie miałem okazji\npodziękować za list coś mi przysłał.\nMiałem wiele pracy, miałem wiele nauki,\ntakże piszę dopiero dzisiaj.\n\n" +
                "Ref. U mnie wszystko jak dawniej,\ntylko jeden samobójca więcej,\ntylko jedna znów rodzina rozbita,\ntylko życie pędzi coraz prędzej.\n" +
                "Gdzieś obok rozbił się samolot,\ntrochę dalej trzęsła się ziemia.\nKiedy patrzę na to wszystko,\ntak jak dziś.\n\n" +
                "2. Tak w ogóle to przepraszam Cię bardzo,\nza to, że tak długo milczałem,\nlecz dopiero dzisiaj zaczynam rozumieć.\nBiblię, którą mi przysłałeś.\n" +
                "Wczoraj odszedł ode mnie przyjaciel,\nz którym tak wiele mnie łączyło,\nlecz dopiero dzisiaj zaczynam doceniać\nczym jest życie i prawdziwa miłość.\n\n" +
                "Ref. U mnie wszystko jak dawniej,\ntylko jeden samobójca więcej,\ntylko jedna znów rodzina rozbita,\ntylko życie pędzi coraz prędzej.\n" +
                "Gdzieś obok rozbił się samolot,\ntrochę dalej trzęsła się ziemia.\nKiedy patrzę na to wszystko,\ntak jak dziś.\n" +
                "U mnie wszystko jak dawniej,\ntylko świat jest mniej kolorowy,\ntylko życie pędzi coraz prędzej,\ntylko ludzie szybciej tracą głowy.\n" +
                "Gdzieś obok rozbił się samolot,\ntrochę dalej trzęsła się ziemia.\nKiedy patrzę na to wszystko,\ntak jak dziś.\n\n",
        "O Panie, zmiłuj się, (o Panie), \tzmiłuj się nad nami.\nO Panie, zmiłuj się, (o Panie),\nO Chryste, zmiłuj się, (o Chryste),\nO Chryste, zmiłuj się, (o Chryste),\n" +
                "O Panie, zmiłuj się, (o Panie),\nO Panie, zmiłuj się, (o Panie),\nŚwięta Maryjo, Matko Boża \tmódl się za nami.\nŚwięci Michale, Gabrielu i Rafale –\n" +
                "Święci Aniołowie Boży -\nŚwięty Janie Chrzcicielu -\nŚwięci Józefie, Piotrze i Pawle -\nŚwięty Andrzeju i Święty Janie -\n" +
                "Święty Wojciechu i Stanisławie -\nŚwięty Grzegorzu i Augustynie -\nŚwięty Tomaszu i Benedykcie -\nŚwięty Franciszku i Dominiku -\n" +
                "Święta Tereso od Jezusa -\nŚwięty Janie od Krzyża -\nŚwięta Teresko i Święty Pawle -\nWszyscy Święci i Święte Boże -\n" +
                "Bądź nam miłościw \twybaw nas Panie.\nOd każdego grzechu i śmierci wiecznej -\nPrzez Twoje wcielenie, śmierć i zmartwychwstanie -\nPrzez Zesłanie Ducha Świętego -\n" +
                "Prosimy Ciebie my, grzesznicy \twysłuchaj nas Panie.\nByś wybranych tych odrodził przez chrzest Święty –\n" +
                "O Panie, zmiłuj się, (o Panie), \tzmiłuj się nad nami.\nO Panie, zmiłuj się, (o Panie),\n\n",
        "Jezu cichy, zhańbiony, z szat obdarty,\nSamotny, pobity wisisz na Krzyżu,\nPrzebodli nogi, przebodli ręce\nRozpięty na krzyżu\nPrzebodli Ci serce\n" +
                "A ja gorycz mych czynów Ci podaję\nA ja ręce ocieram z Krwi Twojej\nA ja rany me przed Tobą ukrywam\n" +
                "Od straty jednej sekundy mego życia\t-wybaw nas Panie.\nOd myśli, że jestem trochę lepszy, od pychy -\n" +
                "Od myśli, spojrzeń, uczynków nieczystych -\nOd podniesionego głosu, od słów niepotrzebnych -\n" +
                "Od niewoli pieniądza, od braku ubóstwa -\nOd tchórzliwej zgody na kłamstwo i kradzież -\n" +
                "Od życia bez modlitwy i Eucharystii -\nOd zmęczenia, które nie pozwoli się modlić –\n" +
                "Od złego słowa o mym przyjacielu -\nOd złego słowa o mym wrogu -\nOd złego słowa o Kościele, mej Matce –\n" +
                "Od niemówienia prawdy, która boli -\nOd mojego odejścia od braci i sióstr -\nOd utraty wiary, nadziei, miłości -\n" +
                "Od grzechu przeciwko Duchowi Świętemu –\nOd nieprzyjmowania prawdy o mnie -\nOd tego, bym Cię przestał uwielbiać –\nOd tego, bym Cię przestał uwielbiać –\n\n",
        "Ref. Lud Twój Panie, lud pielgrzymi\nprosi, byś był światłem.\nByś na drodze do królestwa\nwzmacniał serca swoim Ciałem.\nZostań, zostań wśród nas, o Panie.\n\n" +
                "1. Chlebie życia, Tyś sam jest naszą siłą\ni czynisz trudną drogę tak bezpieczną.\nJeśli siły na tym szlaku w nas osłabną,\nręka Twoja niech obdarza wciąż nadzieją.\n\n" +
                "2. Twoja Krew niechaj jest napojem mocy\ni kieruje zapał kroków w Twoje ślady.\nJeśli radość gaśnie w sercach od słabości,\nTwój głos sprawi, że obudzi się w nas świeżość.\n\n" +
                "3. Twoje Ciało, o Jezu, rodzi Kościół,\nbo uczysz, żeśmy braćmi w pyle drogi.\nJeśli krzywdy niszczą światło Twej miłości,\nz Twego serca płynie nowe przebaczenie.\n\n",
        "Ref. Ludu kapłański, ludu królewski,\nZgromadzenie Święte, Ludu Boży,\nśpiewaj twemu Panu.\n\n" +
                "1. Tobie śpiewamy, o Synu umiłowany Ojca.\nUwielbiamy Cię, Mądrości przedwieczna,\nŻywe Słowo Boga.\n\n" +
                "2. Tobie śpiewamy, jedyny Synu Maryi Panny.\nUwielbiamy Cię, Chrystusie, nasz Bracie,\nTyś nas przyszedł zbawić.\n\n" +
                "3. Tobie śpiewamy, Mesjaszu przyjęty przez ubogich.\nUwielbiamy Cię, o Chryste, nasz Królu\ncichy i pokorny.\n\n" +
                "4. Tobie śpiewamy, jedyny Pośredniku ludzkości.\nUwielbiamy Cię, o Prawdo i Życie,\nDrogo nas wiodąca.\n\n" +
                "5. Tobie śpiewamy, Arcykapłanie w Nowym Przymierzu.\nUwielbiamy Cię, o Boże pokoju,\nprzez Krew Twego Krzyża.\n\n" +
                "6. Tobie śpiewamy, Ożywcze Źródło dające łaskę.\nUwielbiamy Cię, bo gasisz pragnienie.\nŻywa Wodo, Chryste.\n\n" +
                "7. Tobie śpiewamy, o Winny Szczepie przez Ojca dany.\nUwielbiamy Cię, o Krzewie ożywczy,\nmy, Twe latorośle.\n\n" +
                "8. Tobie śpiewamy, prawdziwa Manno, dająca życie.\nUwielbiamy Cię, o Chlebie żyjący,\nktóryś zstąpił z nieba.\n\n" +
                "9. Tobie śpiewamy, Pasterzu wiodący do Królestwa.\nUwielbiamy Cię, bo w jedno zgromadzasz\nwszystkie owce Twoje.\n\n",
        "Ref. Łaska Twoja Panie,\njest wielkim darem, jest wielkim darem.\n\n" +
                "1. Widziałem życie, widziałem śmierć. /x3\nTy pokonałeś!\n\n" +
                "2. Jest we mnie życie, jest we mnie śmierć. /x3\nTy pokonałeś!\n\n",
        "Łaską jesteśmy zbawieni, z łaski możemy tu stać.\nŁaską usprawiedliwieni, i przez Baranka Krew.\nWzywasz nas Panie do siebie,\n" +
                "przed Twój w niebie Tron.\nMy łaską obdarzeni,\nTobie składamy hołd.\nTobie składamy hołd.\n\n",
        "1. Ma dusza pragnie Boga,\nchcę spocząć w Nim,\nz tęsknoty cała drży.\nWstań, duszo moja,\noto Oblubieniec,\noto Pan, Bóg twój.\n\n" +
                "2. Ty dasz mi pokój serca,\nPanie mój,\nbo Tyś zbawieniem mym.\nTak, w Tobie znajdzie\npokój dusza moja\ni uciszy się.\n\n",
        "Magnificat /x3 anima mea dominum.\nMagnificat /x3 anima mea.\n\n",
        "Ref. Maranatha, przyjdź Jezu, Panie,\nw swej chwale do nas zejdź!\nMaranatha, usłysz wołanie,\ngdy się wypełnią wieki!/x2\n\n" +
                "1. Jak rosy chłód na spragniony grunt,\njak dobry chleb na głodnego dłoń.\n\n" +
                "2. W Dziecięciu Ty objawiasz się nam,\nprzez wichru szum dajesz poznać się.\n\n" +
                "3. W Kościele Twym, gdzie dajesz się nam,\nprzez Ciała, Krwi tajemniczą moc\n\n" +
                "4. Gdy miłość swą okażemy w krąg,\nprzychodzisz już dziś na ziemię swą.\n\n",
        "Ref. Maranatha, przyjdź Panie, przyjdź\nJezu, Maranatha! /x2\n\n" +
                "1. I usłyszałem, jak gdyby głos donośny z nieba,\ntłum wielki śpiewał: „Alleluja”.\nZbawienie, moc i chwała,\nzbawienie, moc i chwała od Boga!\n\n" +
                "2. Dwudziestu czterech starców i czworo zwierząt przed tronem,\npokłon oddało Mu, Alleluja!\nI wyszedł głos od tronu,\nwszyscy Jego słudzy chwalcie Go!\n\n" +
                "3. I usłyszałem głos, jakby huk potężnych gromów,\nGłos, który w niebie grzmiał: „Alleluja”.\nBo zakrólował Pan Bóg nasz,\nbo zakrólował Pan Bóg nasz wszechmocny!\n\n" +
                "4. Oto nadeszły chwalebne gody Baranka,\nmałżonka czeka już, Alleluja!\nBłogosławieni, którzy\nwezwani są na ucztę Baranka!\n\n",
        "Mario, proszę, spraw,\naby życie mi nie było obojętne.\nAbym zawsze kochał to, co piękne,\npozostawił ciepły ślad na czyjejś ręce.\n\n",
        "W moim sercu wyrósł Krzyż,\nktóry życie daje mi.\nOn przyprowadził mnie do Ciebie\no Panie, tylko Ty,\nmożesz odbudować mój dom.\n\n" +
                "Maryjo, weź mnie za rękę,\npodprowadź do Twego Syna.\nPrzyciśnij mnie do drewna,\nniech spłynie Krew i mnie oczyści.\n\n",
        "1. Maryjo, Matko mojego wezwania,\nchcę i pragnę, idąc za Twym słowem,\nczynić wszystko, co powie mi Jezus,\n" +
                "miłować Jego wolę.\nA wszystko inne, ze względu na niego samego.\n\n" +
                "Ref. Bo tak jest z tymi, którzy z Ducha\nnarodzili się.\nNikt nie wie, dokąd pójdą za wolą Twą.\n\n" +
                "2. Słuchać słowa całym sercem\ni w Duchu Świętym je wypełniać.\nAmen. Amen. Amen.\n\n",
        "1. Maryjo, naucz mnie modlić się tak, jak Ty,\nbo ufać chcę, jak Ty.\nZawierzyć Ci serce moje,\n" +
                "napełnić się Twym pokojem.\nDaj, bym miał, nie pragnąc, mieć.\nDaj mi Twej miłości chcieć.\n\n" +
                "2. Maryjo, naucz mnie skupiać wciąż wolę mą,\nna Twoim wiernym „fiat”.\nGdy wola ma się zachwieje,\n" +
                "umocnij we mnie nadzieję.\nBłagam Cię, sił mi brak,\nosłoń mój życia szlak.\n\n",
        "1. Maryjo, śliczna Pani, Matko Boga i ludzi na ziemi.\nTyś świata Królową, Tyś Gwiazdą na niebie,\nTy nas wiedziesz przed Jezusa wieczny tron.\n\n" +
                "Ref. Maryja, Ave Maryja,\nu Boga nam wybłagaj zdroje łask,\nby świat lepszy był, by w miłości żył,\no Maryjo, miej w opiece dzieci swe!\n\n" +
                "2. Maryjo, śliczna Pani, świat dziś czuje na swych ustach gorzkie łzy.\n" +
                "W sercu ból, smutek, żal, a w oczach wciąż strach,\nusłysz Pani, błaganie, pomóż nam.\n\n",
        "1. Kiedy masz chwile smutne w Twym życiu,\npowierz się Matce.\nKiedy rozpacz rozdziela Twe serce,\nofiaruj się Jej.\n\n" +
                "Ref. Matce, która pod krzyżem stała.\nMatce, która się z Synem żegnała,\nktóra uczyć Cię będzie pokory,\ncierpienie znieść ci pomoże.\n\n" +
                "2. Kiedy zgubisz swą drogę do Boga,\npowierz się Matce.\nGdy w Twym sercu znów gości trwoga,\nofiaruj się Jej.\n\n" +
                "3. Kiedy wyruszysz w daleką wędrówkę,\npowierz się Matce.\nGdy na szlaku zaskoczy Cię burza,\nofiaruj się Jej.\n\n",
        "1. Gdy popatrzysz dookoła, gdy otworzysz swoje oczy,\nwówczas dojrzysz tylko smutek,\nktóry nasze życie mroczy.\n" +
                "Dni tak szare, niepogodne, bez uśmiechu i radości,\ntylko smutek sam pozostał,\nktóry w sercach naszych gości.\n" +
                "Lecz nadzieja jest w Maryi,\nktóra sama, choć stroskana,\nzawsze cieszy i rozdaje łaski, które płyną od Pana.\n\n" +
                "Ref. Maryjo, Tyś naszą nadzieją,\nMaryjo, Ty radość nam dasz!\nSpraw, Mario, niech serca się śmieją,\nMaryjo, daj na szare dni pogodną twarz!\n\n" +
                "2. Wejdź, Maryjo, w nasze życie, w nasze serca i zagrody,\ndaj na szare dni codzienne,\ntrochę szczęścia i pogody.\n" +
                "Schyl się, Matko, nad ludzkością, co pokoju jest spragniona\ni jak tulisz swego Syna,\ntak nas wszystkich weź w ramiona.\n" +
                "Przynieś, Matko, na swych rękach\ntę największą radość świata.\nPrzynieś, Matko, nam Jezusa w nasze młode, trudne lata.\n\n",
        "Ref. Matko Boga, Królowo świata,\nMatko ludzi, módl się za nami.\nMatko Boga, Królowo świata,\nMatko ludzi, prowadź nas.\n\n" +
                "1. Maryjo, Ty w mój każdy dzień,\nprowadź mnie, kieruj mną, ze mną bądź.\nChciej, o Pani, dziś modlitwy przyjąć te,\nza siostrę, brata i za mnie też.\n\n" +
                "2. Maryjo, każdy człowiek powie Ci,\nprowadź mnie, kieruj mną, ze mną bądź.\nChciej, o Pani, dziś modlitwy przyjąć te,\nza biednych, samotnych i głodnych też.\n\n" +
                "3. Maryjo, chory człowiek powie Ci,\nprowadź mnie, kieruj mną, ze mną bądź.\nChciej o Pani dziś modlitwy przyjąć te,\nby każdy wytrwał w cierpieniu swym.\n\n" +
                "4. Maryjo, mąż i żona powie Ci,\nprowadź mnie, kieruj mną, ze mną bądź.\nChciej, o Pani, dziś modlitwy przyjąć te,\nza miłość w rodzinie, za zgodę w niej.\n\n" +
                "5. Maryjo, małe dziecko powie Ci,\nprowadź mnie, kieruj mną, ze mną bądź.\nChciej, o Pani, dziś modlitwy przyjąć te,\nza każde nowe życie, by mogło żyć.\n\n" +
                "6. Maryjo, cały świat dziś mówi Ci,\nprowadź mnie, kieruj mną, ze mną bądź!\nChciej, o Pani, dziś modlitwy przyjąć te,\nniech pokój i miłość na świecie zawsze trwa.\n\n",
        "1. Matko rannych pacierzy,\nMatko polskich ołtarzy.\nGdy o świtaniu oczy otwieram,\nna Panią świtu Ciebie wybieram.\n\n" +
                "2. Matko polskiego słońca,\npołudniem na polskiej drodze.\nKiedy na drodze pot z czoła ocieram,\nna Panią skwaru ciebie wybieram.\n\n" +
                "Ref. Matko mojego krzyża,\nzawsze bądź jego ozdobą.\nCodziennie dusza do Pana się zbliża,\ni dojdę, dojdę bo idę z Tobą.\n\n" +
                "3. Matko polskich zachodów,\nwietrze wieczornych łąk.\nGdy się zmęczony o laskę wspieram,\nna Panią znoju ciebie wybieram.\n\n" +
                "4. Królowo Polski i chwili,\nNagrodo gasnących dni.\nKiedy u kresu na sen się zbieram,\nna pół sennych marzeń Ciebie wybieram.\n\n",
        "1. Bądź pozdrowiona łaski pełna, Maryjo Matko ma.\nPrzyjmij dzięki, Matko moja, bo wielka dobroć Twa.\n\n" +
                "Ref. Matko, ja wielbię Cię, Matko ja kocham Cię,\no Matko, spójrz, pobłogosław wszystkie dzieci swe.\n" +
                "Matko, ja wielbię Cię, Matko, ja kocham Cię,\no Matko, spójrz, pobłogosław mnie.\n\n" +
                "2. Dziś do Ciebie przychodzimy, Ty witasz dzieci swe.\nBiednym, smutnym, zrozpaczonym, Ty dajesz łaski swe.\n\n",
        "Ref. Matko, która nas znasz,\nz dziećmi Twymi bądź.\nNa drogach nam nadzieją świeć\nz Synem Twym z nami idź.\n\n" +
                "1. Z wszystkich niewiast wybrana –\n przyjdź i drogę wskaż.\nCórko ludu Bożego –\n" +
                " do Syna Twego nas prowadź.\nSłużebnico pokorna –\n pokój światu daj.\n\n" +
                "2. Królowo ognisk rodzinnych –\nDziewico, wzorze prostoty -\nOblubienico cieśli –\n\n" +
                "3. Królowo narodu naszego –\nUciśnionych nadziejo –\nŚwiatło łaknących prawdy –\n\n" +
                "4. Pociecho ludu biednego -\nPani wszelkiej radości –\nMatko tkliwego serca –\n\n" +
                "5. Dziewico, Matko Chrystusa -\nDziewico, Matko Kościoła –\nDziewico, Matko ludzi –\n\n" +
                "6. Matko, przez Syna nam dana –\nMatko, która nas słuchasz -\nTy nas zawsze rozumiesz –\n\n" +
                "7. Dziewico z nami idąca –\nHistorii świata promieniu –\nPośredniczko najlepsza -\n\n" +
                "8. Matko ludzi bezdomnych -\nMatko prześladowanych –\nMatko ludzi wzgardzonych –\n\n" +
                "9. Matko ludzi cierpiących –\nMatko z sercem przeszytym -\nU stóp krzyża stojąca –\n\n" +
                "10. Matko płacząca nad nami –\nMatko przed karą chroniąca –\nMatko nas przyjmująca –\n\n",
        "Alleluja, Alleluja, Alleluja, Amen.\nMemu Bogu, Królowi, będę śpiewał tę pieśń,\nteraz, zawsze, na wieki, Amen.\nAlleluja, Alleluja, Alleluja, Amen.\n\n",
        "1. Odebrałeś mi każde słowo i gest\nMogę tylko patrzeć i kochać Cię\nWięc milczę i kocham\nPatrzę i kocham\nWidzę i kocham Cię\n\n" +
                "2. Odebrałeś mi każde słowo i gest\nMogę tylko patrzeć i pragnąć Cię\nWięc milczę i pragnę\nPatrzę i pragnę\nWidzę i pragnę Cię\n\n" +
                "3. Odebrałeś mi każde słowo i gest\nMogę tylko patrzeć i wielbić Cię\nWięc milczę i wielbię\nPatrzę i wielbię\nWidzę i wielbię Cię\n\n" +
                "4. Więc milczę i kocham\nPatrzę i pragnę\nWidzę i wielbię Cię\n\n",
        "Miłosierdzie Pańskie wyśpiewywać na wieki./x2\nDla Ciebie Jezus na krzyżu rozpięty.\n" +
                "Nie rozpaczaj grzeszniku, Ty możesz być święty.\nJezu, ufam Tobie. /x3\n\n",
        "Niezwykły jest, Najwyższy jest, Wspaniały Jezus.\nCudowny jest, Potężny jest, Bóg Żywy Jezus. /x2\n\n" +
                "Ref. Stworzenie całe czci Imię to.\nA pieśń radości brzmi, z wszystkich stron.\nAniołów chór ogłasza, że Jezus Królem jest.\n\n",
        "Miłość doda wam skrzydeł,\nuniesie duszę do Boga.\nGdy jak ziarnko piasku stanę się mały,\nJezus podniesie mnie.\n\n",
        "1. Miłujcie waszych nieprzyjaciół\nI módlcie się za tych, którzy was prześladują.\nTak będziecie synami Ojca waszego,\nKtóry jest w niebie.\n\n" +
                "Ref. Ponieważ On sprawia,\nŻe słońce Jego wschodzi nad złymi i nad dobrymi\nI On zsyła deszcz\nNa sprawiedliwych i niesprawiedliwych.\n\n" +
                "2. Jeśli bowiem miłujecie tych, którzy was miłują,\nCóż za nagrodę mieć będziecie?\nCzyż i celnicy tak nie czynią?\n\n" +
                "3. I jeśli pozdrawiacie tylko swych braci,\nCóż szczególnego czynicie?\nCzyż i poganie tego nie czynią?\n" +
                "Bądźcie więc święci, jak Bóg jest święty!\nBądźcie więc święci, jak Bóg jest święty!\n\n",
        "Miłość Twa od najwyższych gór wyższa jest.\nWielka jest wierność Twa, do nieba sięga wzwyż.\n" +
                "Miłość Twa głębsza, niż ocean bez dna.\nWielka jest wierność Twa, gdy do mnie zniżasz się.\n\n",
        "Miłość Twoja nad górami,\nponad szczyty wznosi się.\nJak to jest, że jesteś, /x3\nz nami i w niebie nie brakuje Cię.\n" +
                "Panie, jeśli zechcesz\nprzyjąć mnie taką, jaką jestem,\njestem gotowa już dziś.\nJeśli rozkażesz, odrzucę wszystko,\n" +
                "co nie z Ciebie,\nby za Tobą w prawdzie, by za Tobą w prawdzie\nby za Tobą w prawdzie iść\n" +
                "Bo z Tobą, Panie, i przez Ciebie\nwszystko, wszystko dzieje się.\nTakże życie moje,\n" +
                "niech w Twoim trwa imieniu.\nTy mi pozwoliłeś, /x3\nna nowo narodzić się.\nTy mi pozwoliłeś, /x3\n\n",
        "1. Nie jestem tu ze względu na człowieka.\nNie jestem tu, by uciec przed światem.\nTo Pan powołał mnie,\nTo Pan powołał mnie.\n\n" +
                "Ref. Jego Słowo mnie pokrzepia,\nPrzeprowadza mnie przez ciasną bramę.\nPan powiedział, bądź barankiem,\nBowiem miłość ukrzyżowana jest.\n\n" +
                "2. Macie być solą dla ziemi,\nMacie być światłem dla świata.\nCieszcie się, gdy wam robią krzywdę,\nJesteście wtedy błogosławieni.\n\n" +
                "3. Oporu złemu nie stawiajcie,\nNadstawcie drugi policzek.\nNie brońcie swego imienia,\nJesteście wtedy błogosławieni.\n\n" +
                "4. Nie brońcie swego majątku,\nPoddajcie wszystko bez walki.\nDajcie darmo temu, kto was prosi,\nJesteście wtedy błogosławieni\n\n",
        "Ref. Miłujcie się wzajemnie,\ntak, jak Ja was umiłowałem. /x2\n\n" +
                "1. Gdybym mówił językami ludzi i aniołów,\na miłości bym nie miał,\nstałbym się jak miedź brzęcząca,\nalbo cymbał brzmiący.\n\n" +
                "2. Gdybym też miał dar prorokowania i znał wszystkie tajemnice\ni posiadał wszelką wiedzę.\n" +
                "I wiarę miał tak wielką, iżbym góry przenosił,\na miłości bym nie miał, byłbym niczym.\n\n" +
                "3. I gdybym rozdał na jałmużnę całą majętność moją,\na ciało wystawił na spalenie,\nlecz miłości bym nie miał,\nnic bym nie zyskał.\n\n" +
                "4. Miłość cierpliwa jest i łaskawa,\nmiłość nie zazdrości i nie szuka uznania.\nNie unosi się pychą\ni nie szuka swego.\n\n" +
                "5. Miłość nie unosi się gniewem,\nnie pamięta złego.\nNie cieszy się z niesprawiedliwości,\nlecz weseli się z prawdy.\n\n" +
                "6. Miłość wszystko znosi,\nmiłość wszystkiemu wierzy,\nwe wszystkim pokłada nadzieję,\nmiłość wszystko przetrzyma.\n\n" +
                "7. Miłość nigdy nie ustaje,\nnie jest jak proroctwa, które się skończą.\nTeraz więc trwają: wiara, nadzieja i miłość,\nz nich największa jest miłość.\n\n",
        "Ref. Pomódl się Miriam, aby Twój Syn żył we mnie.\nPomódl się, by Jezus we mnie żył.\n" +
                "Gdzie Ty jesteś, zstępuje Duch Święty,\ngdzie Ty jesteś, niebo staje się.\n\n" +
                "1. Miriam, Tyś jest bramą do nieba,\nmoim niebem jest Twój Syn.\nWeź mnie, weź mnie do swego łona,\nbym bóstwem Jezusa zajaśniał jak Ty.\n\n" +
                "2. Gdybym umarł, Jezus żyłby we mnie,\ngdybym umarł, odpocząłbym.\nPrzyspiesz, przyspiesz moją śmierć,\npragnę umrzeć, aby żyć.\n\n",
        "Misericordias Domini in aeternum cantabo.\n\n(O miłosierdziu Pana zawsze będę śpiewać.)\n\n",
        "1. Co małe i wzgardzone jest\nW twych oczach wielką wartość ma\nTę drogę przecież ty sam\nprzeszedłeś, by naprawić świat.\n" +
                "Tylko nieliczni, których wzrok pokornie czyta twoje ślady.\nZnajdują Boga, kiedy mrok chce zgubić ludzi w pysze swej.\n\n" +
                "Ref. Franciszku naucz nas,\npodążać drogą pokory./x2\n\n" +
                "2. To miłość co uniża się, by sobą karmić ciągle nas.\nW twym sercu wypaliła to, co wielkie w oczach ludzi jest.\n" +
                "I odtąd zmienił się twój smak. Co gorzkie słodkim stało się.\nKażdy na Ziemi to twój brat, bo w niebie czeka Ojciec nasz.\n\n" +
                "Bridge:\nTy pragnąłeś, aby wszyscy bracia\nbyli najmniejszymi spośród ludzi.\nCo w ubóstwie i pokorze Pana\nodnajdują pełną radość życia.\n" +
                "Żłób w Betlejem i krzyż na Kalwarii zapisałeś w sercach braci łzami.\nAby mogli w swym powszednim chlebie dostrzec ukrytego Pana z nami.\n\n",
        "1. Panie, uczyń mnie Twoim narzędziem,\nuczyń mnie narzędziem Twego pokoju.\nGdzie nienawiść, abym umiał nieść miłość,\ntam gdzie krzywda, bym wnosił przebaczenie.\n" +
                "Gdzie zwątpienie, abym umiał nieść wiarę,\ngdzie niezgoda, bym budował jedność.\nTam, gdzie błąd, abym prawdę umiał nieść,\nzrozpaczonym ukazywał nadzieję. /x2\n\n" +
                "Ref. O Mistrzu daj mi proszę, wielkie serce,\naby było kroplą rosy dla świata.\nGłosem prawdy i nadziei, szczęśliwym porankiem\n" +
                "dla dnia każdego człowieka.\nZ ostatnimi świata kroczyć chcę przez życie,\nradość znajdować w ubóstwie,\nznajdować w ubóstwie. /x2\n\n" +
                "2. Panie, uczyń mnie Twoją pieśnią,\nuczyń mnie pieśnią Twego pokoju.\nAbym smutnym mógł rozdawać radość,\ngdzie ciemność, bym wnosił Twoje światło.\n" +
                "Albowiem dając, uczy się kochać życie,\ntylko służąc, uczy się żyć w radości.\nPrzebaczając, znajduje przebaczenie,\na umierając, rodzi się do wieczności. /x2\n\n",
        "Mocą swą obdarz nas, Duchu Święty, przyjdź.\nŚwiatło prawdy w nas wlej,Miłości tchnienie daj.\nPrzybądź do naszych serc i wypełnij je.\n" +
                "Oświeć nasz każdy dzień. Tyś naszym Bogiem jest.\n\nJesteś naszym światłem, otwierasz oczy nam.\n" +
                "Tyś Wyzwolicielem, co zrywa więzy zła.\nTyś Pocieszycielem, ocierasz bólu łzę,\ndajesz moc wytrwania i dar jedności Twej.\n\n",
        "1. Mogę mówić, że kocham wszystkich,\nmogę mówić, że kocham Boga,\nlecz nie mogę już Tobie mówić, że kocham Cię.\n" +
                "Nic już nie mam, nic nie posiadam,\nnawet miłość nie jest moja\ni nie mogę już Tobie mówić, że kocham Cię,\ndlaczego?\n\n" +
                "Ref. Klaro, Klaro, nie! Mając Ciebie,\nbyłbym większy niż sam król.\nLecz Ty to wiesz, że bogactwa\njuż nie znaczą dla mnie nic.\n\n" +
                "2. I dlaczego twarz trędowatych\nmogę co dzień dotykać czule,\na nie mogę już tak dotykać Twej. Dlaczego?\nTy wybrałeś Panią Biedę,\n" +
                "która może jest piękniejsza,\nwszystko dałeś jej, mnie nie dając nic. Dlaczego?\nDlaczego?\n\n" +
                "Ref. Klaro, Klaro, nie! Mając Ciebie,\nbyłbym większy niż sam król.\n" +
                "Lecz Ty to wiesz, że bogactwa\njuż nie znaczą dla mnie nic.\nTaka miłość nie jest dla nas już.\n\n",
        "Może daleko jesteś od Niego,\nmoże zgubiłeś ścieżki Jego ślad.\nMoże w samotną ruszyłeś drogę\ni nie wiesz, jak Bóg ciebie zna.\n" +
                "Nie martw się, Bóg ciebie kocha, On zawsze z Tobą jest.\nPodnieś swój zmęczony wzrok i popatrz przed siebie.\nZacznij od nowa, zacznij jeszcze raz. /x3\n\n",
        "Mój Jezu, mój Zbawco,\nktóż jest tak wielki, jak Ty?\nPrzez wszystkie dni wysławiać chcę\nwspaniałe dzieła Twoich rąk.\n" +
                "Mój Panie, obrońco,\nźródło mych natchnień i sił.\nNiech cały świat, wszystko, co jest,\nzawsze wielbi Imię Twe.\n\n" +
                "Ref. Krzycz na cześć Pana, rozraduj się w nim.\nOgłaszaj wszędzie, że On Panem jest.\nGóry ustąpią na dźwięk Jego słów,\ngdy przemówi Stwórca ziem.\n" +
                "Patrzę z podziwem na dzieła Twych rąk,\nzawsze chcę kochać Cię, przy Tobie być.\nCo może równać się z tym, co u Ciebie mam.\n\n",
        "Ref. Mój Jezus Królem królów jest.\nMój Jezus władać będzie wciąż.\nKrólestwo Jego wiecznie trwa.\nOn zbawił duszę mą i teraz we mnie mieszka już.\n\n" +
                "1. On dał mi całkiem nowe życie,\nuczynił mnie dzieckiem światłości.\nDziedzictwem swym obdarzył mnie,\nbo umiłował mnie mój Ojciec Bóg.\n\n" +
                "2. On wziął na siebie moje winy,\nOn wyrwał mnie z królestwa ciemności,\nchoroby wszystkie wziął na Siebie\ni już nie muszę się niczego bać.\n\n",
        "1. On szedł w spiekocie dnia\ni w szarym pyle dróg.\nA idąc uczył kochać i przebaczać.\n" +
                "On z celnikami jadł,\nOn nie znał, kto to wróg,\npochylał się nad tymi, którzy płaczą.\n\n" +
                "Ref. Mój Mistrzu, przede mną droga,\nktórą przebyć muszę tak, jak Ty.\nMój Mistrzu, wokoło ludzie,\nktórych kochać trzeba tak, jak Ty.\n" +
                "Mój Mistrzu, niełatwo cudzy ciężar\nwziąć w ramiona tak, jak Ty.\nMój Mistrzu, poniosę wszystko,\njeśli będziesz ze mną zawsze Ty.\n\n" +
                "2. On przyjął wdowi grosz\ni Magdaleny łzy,\nbo wiedział co to kochać i przebaczać.\n" +
                "I późną nocą On\ndo Nikodema rzekł,\nże prawdy trzeba pragnąć, trzeba szukać.\n\n" +
                "3. Idziemy w skwarze dnia\nw szarym pyle dróg,\na On nas uczy kochać i przebaczać.\n" +
                "I z celnikami siąść,\nzapomnieć, kto to wróg,\npochylać się nad tymi, którzy płaczą.\n\n",
        "Ref. Mój Pan jest Dobrym Pasterzem,\nNiczego nie brak mi.\nOn Bramą jest. Prowadzi mnie,\nNa Krzyżu swe życie dał.\n\n" +
                "1. Prowadzisz mnie nad wody zdrój,\nTwa Miłość schronieniem mym.\nChociażbym szedł w ciemności swej,\nTyś ze mną, nie lękam się!\n\n" +
                "2. Obfitość Twa zastawia stół,\nMych wrogów zawstydzasz dziś.\nW strapieniu mym pocieszasz Ty,\nOlejkiem namaszczasz mnie!\n\n",
        "Mój Pan mocą moją jest,\nPieśnią i Zbawieniem, On Bogiem mym. /x2\n\n" +
                "Ref. Dla Ciebie otwieram serce me. /x3\n\nMój Pan Królem był i jest,\nna zawsze i na wieki. Amen. /x2\n\n",
        "Mój Pan żyje, niech będzie wywyższony\ni błogosławiony Bóg zbawienia mego. /x2\nBędę śpiewał Ci, wielbił Imię Twe\nwśród narodów i głosił chwałę Twą.\n\n",
        "Mój Zbawiciel, On bardzo kocha mnie.\nJa nie wiem, czemu miłością darzy mnie\nOn mi nowe życie dzisiaj ofiarować chce.\n" +
                "Będę, będę mieszkał razem z Panem mym.\nBędę, będę mieszkał razem z Panem mym.\n\n",
        "Mów do mnie, Panie, chcę słyszeć Cię.\nPrzyjąć od Ciebie, co masz dla mnie.\nNie chcę się chować, lecz w Tobie skryć.\nW cieniu Twym, Panie, chcę iść.\n\n" +
                "Ref. Święty, potężny jesteś Panie nasz,\nprzed Tobą dziś możemy stać,\ndzięki łasce, nie dzięki nam samym.\n\n",
        "1. Mówiłaś: „Wiem, nie jestem dzieckiem,\nwiem, co jest dobre w moim życiu, a co nie”.\n" +
                "I dopadł Cię przeklęty ogień\ni ciągle pali, ciągle pali, niszczy cię.\n\n" +
                "2. Mówiłaś, Bóg jest gdzieś daleko,\nna pewno nie wie, co w mym życiu dzieje się.\n" +
                "Nie chciałaś wierzyć mi, że tamtej nocy,\nwłaśnie dla ciebie Boży Syn narodził się.\n\n" +
                "Ref. I wciąż cię kocha, kocha, kocha, kocha,\nkocha, kocha nieprzytomnie.\nI wciąż cię kocha, kocha, kocha, kocha,\nkocha, kocha tak, jak nikt.\n\n" +
                "3. Mówiłaś: „Ja mam czas, nie umieram,\nżycie jest super, gdy coś dzieje się”.\n" +
                "Gdy jesteś sam, długo sam, to już nie wierzysz,\nże jest gdzieś ktoś, kto naprawdę kocha Cię.\n\n",
        "1. Mrok ogarnął całą ziemię, na ulicy cisza.\nTylko twoje kroki słychać, twój płacz i jęk.\n\n" +
                "Ref. On przyszedł po to, by ci pomóc\nz upadku twego wyjść,\nbyś nigdy więcej już nie błądził,\nbyś znalazł drogę w życiu tym.\n\n" +
                "2. Jesteś przecież jeszcze młody, wiele możesz zdziałać,\ntylko życie tracisz swoje w różny sposób.\n\n",
        "My jesteśmy na ziemi światłem Twym.\nMy jesteśmy na ziemi światłem Twym.\nAby ludzie widzieli dobre czyny w nas\ni chwalili Ojca, który w niebie jest. /x2\n\n",
        "1. Tak jest mało czasu, mało dni,\nserce bije tylko kilka chwil.\nNiespokojne czeka wierci się,\nkiedy w końcu Ty przytulisz je.\n" +
                "Tak jest mało czasu mało dni,\nserce bije tylko kilka chwil.\nNie wiem czy Cię poznam, ale wiem,\nże na pewno Ty rozpoznasz mnie.\n\n" +
                "Ref. Zabierzesz mnie na drugi brzeg,\nza tobą będę do nieba biegł. /x2\n\n" +
                "2. Nie jest wcale ciężko kiedy wiem, że na końcu drogi spotkam Cię.\nChociaż było tyle trudnych dni, codziennie bliżej nieba – warto żyć!\n" +
                "Tak jest mało czasu, mało dni, serce bije tylko kilka chwil.\nNie wiem czy Cię poznam, ale wiem, że na pewno Ty rozpoznasz mnie.\n\n" +
                "3. Mijają godziny, mija czas, szukam Cię na niebie pośród gwiazd.\nNie wiem czy Cię poznam, ale wiem, że na pewno Ty rozpoznasz mnie.\n\n",
        "1. Na Golgocie stoi Krzyż,\nciało już zdjęte, zbliż się, zbliż.\nPopatrz, Jezus u Twych stóp,\nczeka na Niego pusty grób.\n\n" +
                "2. Gdy go bili, byłeś tam,\nkiedy umierał, został sam.\nO mój Jezu, drogę wskaż,\nktórą pójdę jeszcze raz.\n\n" +
                "Ref. Widzę wstającą tęczę,\nnadziei dobrej znak.\nJeszcze nie jest za późno,\nby powiedzieć Ci, o Panie, Panie, tak.\n\n" +
                "3. Nikt nie cierpiał tak, jak Ty,\nbowiem Ty cierpisz wszystkie dni.\nNasze grzechy gwoźdźmi są,\nw Twoich świętych ranach tkwią.\n\n",
        "Ref. Na Krzyż patrzę w San Damiano,\nPanie, chcę być dzieckiem Twym.\nNa Krzyż patrzę w San Damiano,\nPanie, uczyń mnie narzędziem Twym.\n\n" +
                "1. Abym miłość niósł, gdzie jej brak,\nbym przebaczenie niósł wzgardzonym w krąg.\n" +
                "Bym ludziom pokój dał, bym umiał prawdą żyć\ni był Twoim głosem, Twoim wzrokiem, Twoją nadzieją.\n\n" +
                "2. Abym radość niósł smutnym dniom,\nabym światło niósł w grzechu mrok.\n" +
                "Bym ludziom wiarę dał, bym sprawiedliwość dał\ni był Twoją wolą, Twoim oddechem, Twoją pokorą.\n\n" +
                "3. Abym bratem był słońca dróg,\nabym z wiatrem grał pośród pól.\n" +
                "Bym gwiazdkę śniegu mógł każdemu dziecku dać,\no Tobie mówić, że jesteś Życiem, w Tobie się spalić.\n\n",
        "Ref. Na nowo stwarzaj mnie,\nNiechaj z Ducha zrodzę się.\n\n" +
                "1. To Ty jesteś Ożywicielem,\nTchnij nowe życie w moje martwe ciało.\nTo Ty jesteś Odnowicielem,\nSpraw, by me serce żywe się stało.\n\n" +
                "2. To Ty jesteś Miłości Płomieniem,\nPrzyjdź i rozpal przygaszone serce.\nTo Ty jesteś Ożywczym Strumieniem,\nNiech z boku Chrystusa nowe życie czerpię.\n\n" +
                "3. To Ty jesteś duszy oczyszczeniem,\nNiech me rany obmywa Krew z drzewa życia.\nTo Ty jesteś moim Odpocznieniem,\nNiech w ramionach Krzyża Twój powiew mnie porusza.\n\n" +
                "4. To Ty jesteś moim Pragnieniem,\nNiechaj me serce Twojej woli szuka.\nTo Ty jesteś moim spełnieniem,\nDaj mi dziś Panie narodzić się z Ducha.\n\n",
        "Na wieki będzie trwać chwała Boża\nbo raduje się Pan, że uczynił świat.\nŚpiewać chcę memu Bogu po wszystkie dni,\nsławić, co stworzył On i wysławiać Go.\n\n",
        "1. Na ziemię pełną krzywd, potoków łez,\nw ten czas, gdy zatracono życia sens,\nPanie, przyjdź, wołamy Ciebie, wysłuchaj nas.\nPanie, przyjdź, Boże przyjdź, prowadź nas.\n\n" +
                "2. Spraw, aby zapomniano słowo „wróg”,\nby nie profanowano słowa „brat”.\nPanie, przyjdź, wołamy Ciebie, wysłuchaj nas,\nPanie, przyjdź, Boże przyjdź, prowadź nas.\n\n" +
                "Ref. Panie, przyjdź, Boże przyjdź\ni z serc naszych wyrwij zło.\nUtwórz z nich, o utwórz z nich miłości krąg.\n\n" +
                "3. Ja wierzę, że nadejdzie taki dzień,\nże człowiek przezwycięży w sobie zło,\nże na oczyszczoną ziemię przyjdzie Bóg,\nprzyjdzie Bóg i wyzwoli wierny lud.\n\n",
        "Ref. Nad rzekami Babilonu siedzieliśmy,\nPłacząc rzewnie na wspomnienie Syjonu.\nNa topolach tamtej krainy,\nZawiesiliśmy nasze harfy i cytry.\n\n" +
                "1. Ci którzy nas uprowadzili,\nŻądali od nas pieśni,\nŻądali pieśni radości,\nCi którzy nas uciskali.\n\n" +
                "2. Jakże możemy śpiewać pieśń Pańską\nW obcej krainie?\nO Jeruzalem jeśli zapomnę o tobie,\nNiech uschnie moja prawica,\n" +
                "Niech język mój przyschnie do gardła,\nJeśli nie będę pamiętał o tobie,\nJeśli nie postawię Jeruzalem e\nPonad największą moją radość.\n\n",
        "1. Najpierw Anioł jej zwiastował,\nże się stanie Matką Słowa,\npotem Bóg z Niej ciało wziął.\n" +
                "Gdy nadeszła zaś godzina,\ngłos konającego Syna,\nMatką mą (On) uczynił Ją.\n\n" +
                "Ref. Jak to dobrze w Matce Boga,\ntakże swoją Matkę mieć,\nprzez otwarte Jej ramiona\nwprost w ramiona Ojca biec.\n\n" +
                "2. Ona zawsze przy mnie blisko,\nmogę Jej powierzyć wszystko.\nW Niej jest wiary mej ostoja.\n" +
                "Ona uczy sercem patrzeć,\nbym szedł za Jezusem zawsze.\nJego Matka - Matka moja.\n\n",
        "Ref. Najwyższemu Panu chwała, Alleluja, Alleluja!\n\n" +
                "1. Bądź uwielbiony, Panie,\nza Twoje do nas przybycie,\nza Ciało, którym nas karmisz,\nza Kościół, którym jesteśmy!\nAlleluja!\n\n" +
                "2. Dzięki składamy za wszechświat\nnigdy nieogarniony,\nza ziemię, nasze mieszkanie,\nza światło, wodę i życie.\nAlleluja!\n\n" +
                "3. Bądź uwielbiony nasz Boże\nza twórczą pracę człowieka,\nza miłość, która nas łączy,\nza piękno, dobro i prawdę.\nAlleluja!\n\n" +
                "4. Dzięki składamy, Panie,\nza dzieło naszego zbawienia,\nza to, że stałeś się Ciałem,\nza Twoje życie, naukę i cuda.\nAlleluja!\n\n" +
                "5. Bądź uwielbiony, Panie,\nza Krzyż, na którym skonałeś,\nza Zmartwychwstanie Twoje,\nza Ducha, którego nam dałeś.\nAlleluja!\n\n" +
                "6. Bądź uwielbiony, Boże,\nza każdy grzech przebaczony,\nza ludzi, wśród których żyjemy,\nza ludzi, których spotkamy.\nAlleluja!\n\n" +
                "7. Dzięki składamy za dobra\nod Ciebie otrzymane,\nza naszą dzisiejszą ofiarę,\nza słowa nam przekazane.\nAlleluja!\n\n" +
                "8. Bądź uwielbiony, Boże,\nnasz Stworzycielu.\nBądź uwielbiony, Jezu, nasz Panie.\nBądź uwielbiony Duchu, nasza miłości.\nAlleluja!\n\n",
        "Nakarmiłeś mnie, Panie,\nnakarmiłeś mnie.\nOchroniłeś moje Sanktuarium, /x2\n\nTyś, Jezu, pokarm żywy mój. /x2\n\n",
        "Nalejmy oliwy do naszych serc\ni wyjdźmy na spotkanie Pana.\n\n",
        "1. Wodę zamieniłeś w wino,\nślepi przy Tobie znów widzą.\nKtóż jest jak Ty? Tylko Ty.\n" +
                "Blaskiem rozświetlasz ciemności,\nczłowiek powstaje z marności.\nKtóż jest jak Ty? Tylko Ty.\n\n" +
                "Ref. Nasz Bóg jest wielki, nasz Bóg jest silny.\nBoże nikt inny nie równa się z Tobą.\n" +
                "Nasz Bóg uzdrawia, jest wszechmogący.\nNasz Bóg, Nasz Bóg.\n\n" +
                "2. Blaskiem rozświetlasz ciemności,\nczłowiek powstaje z marności.\nKtóż jest jak Ty? Tylko Ty.\n\n" +
                "Bridge:\nJeśli nasz Bóg jest przy nas,\njuż nic nas nie zatrzyma.\nJeśli nasz Bóg jest z nami,\nktóż jest przeciwko nam? /x2\n\n",
        "Nasz Bóg jest potężny w mocy swej.\nKróluje nad nami dziś.\nZ nieba rządzi w mocy,\nMiłości, mądrości swej.\n\n",
        "Nasz Pan na pewno przyjdzie,\nczekaj na Niego, gdyż On nie spóźni się.\nNasz Pan na pewno przyjdzie,\ni nic Mu nie przeszkodzi.\nAlleluja. /x8\n\n",
        "Ref. Naucz mnie miłości krzyża,\nNaucz mnie miłości krzyża,\nWciąż umierać razem z Tobą - Baranku.\n" +
                "Naucz mnie miłości krzyża,\nNaucz mnie miłości krzyża,\nWe krwi Twojej płukać szaty - Baranku.\n\n" +
                "1. Spójrz Panie na słabe serce moje,\nKtóre pragnie rozdawać Twą miłość.\nNie tylko przyjaciołom, a nade wszystko wrogom,\nTy, ukryty w mym wnętrzu, kochaj.\n\n" +
                "2. Pełen miłości patrzysz z drzewa krzyża,\nRozciągając szeroko ramiona.\nNiech każde cierpienie przybliża mnie do Ciebie\nNaucz mnie ofiary, Baranku milczący.\n\n",
        "Nic nas nie zdoła odłączyć od Ciebie,\nMiłości Twej nigdy nie zgaśnie płomień.\n\n",
        "Nic nie musisz mówić, nic,\nodpocznij we mnie, czuj się bezpiecznie. /x2\nPozwól kochać się, miłość pragnie Ciebie. /x2\n\n",
        "Ref. Nie bój się, bo Ja jestem z tobą! /x3\nMówi Pan.\n\nOdkupiłem cię, dałem ci Imię Swe,\nMoim jesteś więc już,\n" +
                "nie utoniesz, gdy będziesz po wodzie szedł,\nogień nie strawi cię.\nNie! Nie! Nie utoniesz! Nie! Nie! Nie spłoniesz!\nBo Ja jestem z tobą, mówi Pan.\n\n",
        "Nie bój się, nie lękaj się\njuż się więcej nie zawstydzisz.\nNie bój się, nie lękaj się\ntwym Zbawicielem Święty Izraela.\n\n",
        "Nie bójcie się żyć dla miłości,\ndla tej miłości warto żyć.\n\n",
        "Nie chcę więcej, by\nżyciem moim rządził grzech co dnia.\nCo złe, z mego serca wyrzuć dziś,\npragnę uwielbiać Imię Twe.\n" +
                "Prowadź mnie co dnia wciąż wyżej i wyżej.\nProwadź mnie, Panie mój, wyżej wciąż.\n" +
                "Prowadź mnie z dnia na dzień, wciąż wyżej i wyżej,\nchcę kiedyś zobaczyć Twoją twarz. /x2\n\n",
        "Ref. Nie lękaj się, mój sługo, bo ja Ciebie wykupiłem,\nwezwałem Cię po imieniu, tyś jest moim na zawsze.\n" +
                "Gdy pójdziesz przez wody - ja tam będę z Tobą,\nGdy pójdziesz przez rzeki - nie zatopią Ciebie.\n\n" +
                "1. Gdy pójdziesz przez ogień, nie spalisz się\ni nie strawi Cię płomień.\nAlbowiem ja jestem Pan, Bóg Twój.\nŚwięty Twój Zbawca.\n\n" +
                "2. Nie bój się robaczku, Jakubie,\nnieboraku Izraelu.\nAlbowiem ja, Pan, mówię Ci: „Nie lękaj się,\nprzychodzę Ci z pomocą”.\n\n" +
                "3. Czyż może niewiasta zapomnieć o dziecku?\nTa, która kocha syna swego łona?\nA nawet gdyby ona zapomniała,\nJa nie zapomnę o Tobie!\n\n",
        "Ref. Nie lękajcie się, Ja jestem z wami.\nNie lękajcie się, ja jestem z wami.\nNie lękajcie się, Bóg jest miłością.\nNie lękajcie się, trwajcie mocni w wierze.\n\n" +
                "1. Ty jesteś skałą Zbawienia,\njedyną naszą ostoją.\nPrzychodzimy do Ciebie po Światło,\nulecz nasze serca, zmartwychwstać daj.\n\n" +
                "2. Pomóż nam wytrwać przy Tobie,\nbyć wiernym w wierze przez życia czas.\nTylko w Tobie cała nasza nadzieja,\nmiłosierdziem swoim uzdrawiaj nas.\n\n" +
                "3. Przekażcie światu mój Ogień\nPokoju i Miłosierdzia.\nNieście wszystkim orędzie nadziei,\nMoje światło niech świeci wśród was.\n\n",
        "Ref. Nie lękajcie się, nie lękajcie się,\notwórzcie drzwi Chrystusowi.\n\n" +
                "1. Twoja droga, Panie mój,\nwzywa i zaprasza,\nchoć za oknem ciemno jest.\nSłyszę Twe wołanie,\n" +
                "więc wychodzę z domu\ni spotykam braci.\nWyruszamy Twoją drogą,\nnikt już nie jest sam.\n\n" +
                "2. Chociaż droga trudna jest,\nserce cię prowadzi.\nChrystus zajaśnieje nam\nwe wschodzącym słońcu.\n" +
                "Weźmie nas za rękę,\npowiedzie do domu,\ntam, gdzie czeka na nas Ojciec,\nMiłujący Bóg.\n\n" +
                "3. Słońce, które wzejdzie tu,\nZbawcę zapowiada.\nMy idziemy pewniej już,\npatrząc w oczy Pana.\n" +
                "On jest naszą Drogą,\nOn jest naszą Prawdą,\nOn jest naszym Życiem,\nMiłujący Bóg.\n\n",
        "Ref. Nie ma Cię, Panie, nie ma Cię\nW moim życiu, ciemno jest!\nRana ma krwawi, boli mnie,\nDaj odpowiedź, nie ma Cię!\n\n" +
                "1. To jest to miejsce, nie uciekaj stąd,\nTo jest brama, przez którą możesz przejść,\nTo jest droga, którą możesz pójść.\n\n" +
                "2. Tu, w tym cierpieniu właśnie skrył się Pan,\nJest tak blisko, że nie czujesz Go!\nJest tak blisko, że nie słyszysz Go!\n\n" +
                "3. Serce spotka serce, rana spotka ranę,\nChoć nie czujesz, to właśnie tu jest Bóg,\nChoć nie czujesz, to wiernie przy nim trwaj!\n\n" +
                "Ref. Znajdziesz Go, wkrótce znajdziesz Go!\nW twoim życiu Światłość jest.\nRana twa wieńcem chwały jest,\nW niej spotkanie dokonuje się.\n\n",
        "Nie ma innego boga, który by był jak Bóg nasz.\nWielki jest Pan,wspaniałe dzieła Jego.\nWielki jest Bóg, nasz Pan.\n\n",
        "Nie ma w żadnym innym zbawienia,\ngdyż nie dano ludziom pod niebem\nżadnego innego imienia,\nw którym moglibyśmy być zbawieni.\n" +
                "Ja jestem Drogą, Prawdą i Życiem,\nnikt nie przychodzi do Ojca, inaczej\njak tylko przeze mnie.\n\n",
        "Nie ma większej miłości, nie ma większej miłości,\njak oddać swoje życie za przyjaciół swych.\n\n",
        "1. Nie mam nic, co bym mógł Ci, Panie, dać.\nNie mam sił, by przed Tobą, Panie, stać.\n" +
                "Puste ręce przynoszę przed Twój w niebie tron.\nManną z nieba nakarm duszę mą. /x2\n\n" +
                "2. Dotknij łaską swej mocy duszę mą,\noddal małość, co rani miłość Twą.\n" +
                "Pełne dłonie daj przynieść przed Twój święty tron.\nManną z nieba nakarm duszę mą. /x2\n\n",
        "Nie mam nic, co mógłbym Tobie dać.\nI nie wiem jak iść, żeby już nie gubić się. /x3\n" +
                "Więc serce swe oddaję Ci, nie pragnę nic, tylko łaski (Twej). /x4\n" +
                "Miłością swą zatrzymam czas, niech będzie tak, byśmy mogli trwać.\n" +
                "W nadziei, że Ty jesteś tu. Przychodzisz, by nam dodać sił.\n\n",
        "1. Nie mam nic, oprócz Twej miłości, Panie Jezu,\noprócz niej, nie mam nic.\nNie mam nic, oprócz Twej dobroci, Panie Jezu,\nnie mam prawie nic.\n\n" +
                "Ref. Jak liść jesienią musi z drzewa spaść,\njak śnieg, co wiosną z ziemi spłynie.\nTak ginę i ja, lecz Ty każesz trwać\nw Tobie, Panie. Chcę Ci serce dać.\n\n" +
                "2. Twoja moc wypełniła moją duszę, Panie,\nTwoja święta moc.\nTwoja dłoń odnalazła moją rękę w tłumie,\nby mnie wieść przez noc.\n\n",
        "Nie mamy już miejsca stałego\nwśród wszystkich zakątków tej ziemi.\nSzukamy wciąż miejsca stałego, wciąż do nowego dążymy.\n" +
                "Ale przyjdzie taki dzień, gdy zapuka do ciebie Ktoś.\nWówczas powie ci: „Odnalazłeś swój dom”,\nDom spotkania Miłości.\n" +
                "Będzie to piękne spotkanie, spotkanie, którego nie zna nikt.\nBędzie to przebywanie na zawsze i po wieczne dni.\n" +
                "Jasność stanie przed tobą i ciemność na zawsze przeminie.\nPan wyjdzie na to spotkanie w jedynej dla ciebie godzinie.\n\n",
        "Ref. Nie nam Panie, nie nam\nlecz Twemu Imieniu daj chwałę i cześć,\nNie nam, Panie, nie nam,\nlecz Twemu Imieniu daj cześć!\n\n" +
                "1. Święty, Święty, Święty Wszechmogący Bóg,\nktóry jest, który był i który ma przyjść.\nI chwalmy i wywyższajmy Go na wieki!\n" +
                "Błogosławmy Ojca i Syna z Duchem Świętym,\nbłogosławcie wszystkie dzieła Pańskie Panu.\nI chwalmy i wywyższajmy Go na wieki!\n" +
                "Niech chwalą Go, pełnego chwały\ni niebo i ziemia, i wszelkie ich stworzenia.\nI chwalmy i wywyższajmy Go na wieki!\n" +
                "O Panie nasz i Boże, Ty godzien jesteś\notrzymać sławę, chwałę, cześć i błogosławieństwo.\nI chwalmy i wywyższajmy Go na wieki!\n\n" +
                "2. Ty jesteś święty Panie nasz i Boże,\na Twój majestat nad wszystkim coś stworzył.\nI chwalmy i wywyższajmy Go na wieki!\n" +
                "Oddajcie Panu chwałę, wszyscy słudzy Jego,\nbojący się Boga, mali i wielcy.\nI chwalmy i wywyższajmy Go na wieki!\n" +
                "Godzien jest Baranek, który był zabity,\notrzymać wszelką chwałę i błogosławieństwo.\nI chwalmy i wywyższajmy Go na wieki!\n" +
                "Błogosławcie Pana wszystkie\ndzieła pańskie.\nI chwalmy i wywyższajmy Go na wieki!\n\n" +
                "3. Chwała Ojcu i Synowi i Duchowi Świętemu,\njak było na początku, teraz, zawsze i na wieki wieków. Amen. /x4\n(I chwalmy i wywyższajmy Go na wieki!)\n\n",
        "1. Nie odchodź jeszcze, Ojcze Franciszku,\noczaruj świat, poeto Boży.\nStań przed złym wilkiem, cieniem Chrystusa bądź,\npokaż, jak narzędziem Ojca być.\n\n" +
                "Ref. Boże, Ojcze nasz, Boże, Ojcze nasz,\nniepojęty jest dar miłości Twej.\nBoże, Ojcze nasz, Boże, Ojcze nasz,\nnaucz mnie wciąż narzędziem jej być.\n\n" +
                "2. Nie odchodź jeszcze, Ojcze Franciszku,\npocałuj twarz trędowatego.\nBiednemu oddaj serce, bogatym światło,\naby Miłość kochał cały świat.\n\n" +
                "3. Nie odchodź jeszcze, Ojcze Franciszku,\npomóż zrozumieć ludziom Boga.\nUsłyszeć krzyk milczenia, zobaczyć mrok,\nnaucz, jak dobrem przezwyciężać zło.\n\n",
        "Ref. Nie płacz, nie płacz Miriam\nNie płacz, nie płacz Miriam\nAle weź śpiące ciało i je w moim sercu złóż\nNiech śpi, niech śpi niech odpocznie we mnie już\n\n" +
                "1. Spójrz Miriam oto miejsce w pobliżu\nJestem tu i czekam\nNiech wonność twych modlitw wypełni\n" +
                "Przestrzeń w moim sercu\nWypełnij te grotę, gdzie lękam się zajrzeć\nNikt dotąd nie odważył się tu wejść\n\n" +
                "2. Obumarłe, bezbronne i samotne\nZiarno we mnie złóż\nZroś je i użyźnij łzami\n" +
                "Twoich czułych modlitw\nOczekuj oczekuj milczeniem okryta\nOczekuj, choć nie czeka już nikt\n\n",
        "Ref. Nie płacz, to tylko Krzyż,\nprzecież tak trzeba.\nNie drżyj, to tylko Miłość,\njak rana w przylepce chleba.\n\n" +
                "1. I ty, jak Judasz,\nwciąż zdradzasz Pana.\nI tylko ten głos:\n„Przyjacielu, po coś przyszedł?”\n\n" +
                "2. Widzisz ten policzek,\nna nim twoja ręka.\nI tylko to spojrzenie:\n„Czemu mnie bijesz?”\n\n" +
                "3. Spójrz, to Golgota,\nnie odwracaj wzroku.\nNa szczycie są trzy krzyże,\na w środku Chrystus Pan.\n\n",
        "Ref. Nie rozbudzajcie jej,\nNie budźcie jej ze snu,\nPóki sama przyjść nie zechce!\nUkochana ma!\n" +
                "Nie rozbudzajcie jej,\nNie budźcie jej ze snu,\nPóki sama przyjść nie zechce!\nUkochana ma!\n\n" +
                "1. Biegnij, pociągnij mnie za sobą,\nwprowadź mnie w komnaty Twe,\nCieszyć się będziemy sobą\ni sławić Miłość Twą\n\n" +
                "2. Nie znam siebie prowadź mnie,\nPokaż mi drogi Twoje!\nSzukam Ciebie miły mój,\nPochwyciłam Cię i już nie puszczę!\n\n" +
                "3. Jam Twoja, miły mój,\nA Ty jesteś mój,\nRozpala mnie miłości żar\nNic nie zdoła go ugasić!\n\n" +
                "4. Pod drzewem obudziłem Cię,\nPoczęła Cię tam Matka Twoja,\nTam poczęła cię ta która,\nPorodziła cię!\n\n",
        "Nie ścigaj się z miłością - zaufaj jej.\nNiech będzie twoim gościem, ty posuń się,\na ona, gdy urośnie - usunie lęk.\nDo siostry świętości zaprosi Cię.\n" +
                "Nie lękajcie się, /x3\nBóg jest miłością.\nNie lękajcie się, /x3\nBóg jest miłością. Bóg jest miłością.\n\n",
        "1. Nie ten bogaty, co kapie od złota,\nani ten, który zagarnął pół świata.\nCzymże jest złoto? Tylko grudką błota,\nsą inne skarby, niech ku nim duch wzlata.\n\n" +
                "2. Na jednej z ulic Asyżu tłum ludzi\notacza kołem nędznego żebraka.\nU jednych litość, u drugich śmiech budzi.\nCóż to za człowiek, skądże nędza taka?\n\n" +
                "3. Gruby wór szary ciało jego kryje.\nPowróz na biodrach, jego bose nogi.\nGłowa odkryta, choć żar słońca bije.\nCzyliż kto może być bardziej ubogi?\n\n" +
                "4. Ale, o dziwo, ten żebrak wesoły.\nOn nie narzeka, ani się też smuci.\nOn ciągle sobie pieśń pobożną nuci.\nOn ciągle sobie pieśń pobożną nuci.\n\n" +
                "5. Gdy ktoś nad nędzą Jego się lituje,\nOn nań z uśmiechem zwraca oczy swoje.\nI mówi, że się ubogim nie czuje.\nWoła w zachwycie: „Bóg mój, wszystko moje”.\n\n" +
                "6. I choć sam biedny, lecz wielu wzbogaca.\nTworzy zakony, zakłada klasztory.\nW nich kwitnie miłość, modlitwa i praca.\nW nich znajdzie pomoc ubogi i chory.\n\n" +
                "7. O wielki Święty, o ziemi ozdobo!\nTwe Imię w sercach naszych jest wyryte.\nSpraw, byśmy idąc w ubóstwie za Tobą,\nznaleźli skarby Boże przeobfite.\n\n",
        "1. Nie umiem dziękować Ci, Panie,\nbo małe są moje słowa.\nZechciej przyjąć moje milczenie\ni naucz mnie życiem dziękować./x2\n\n" +
                "2. Naucz milczeć w zachwycie, gdy dajesz,\nnaucz kochać goręcej, gdy bierzesz.\nNaucz ufać miłości Twej, Panie,\nchoćby wszyscy przestali Ci wierzyć.\n\n" +
                "3. Naucz patrzeć w Twe oczy, mój Boże,\nnaucz Krzyż Twój całować i nosić.\nNaucz słuchać Cię sercem pokornym,\nI otwierać swe serce na oścież.\n\n",
        "1. Bądź uwielbiony, Boże mój,\nNadziejo ma!\nPrzy Tobie już nie lękam się,\nbo miłość Twa sprawi, że:\n\n" +
                "Ref. Nie umrę, ale będę żyć,\nBo Bogiem mym Chrystus Pan!\nJak On powstanę z martwych i\nWejdę do Jego bram.\n\n" +
                "2. Bądź zawsze ze mną, Boże mój,\nMiłości ma!\nBym innym głosił Imię Twe,\nbo z nimi wraz:\n\n" +
                "3. Jezu, przemień w siebie mnie,\nOstojo ma!\nDaj mi na wieki sławić Cię,\nby słyszał świat o tym, że:\n\n",
        "1. Nie zdejmę krzyża z mojej ściany,\nza żadne skarby świata.\nBo na nim Jezus ukochany,\ngrzeszników z niebem brata.\n\n" +
                "Ref. Nie zdejmę Krzyża z mego serca,\nchoćby mi umrzeć trzeba.\nChoćby mi groził kat, morderca,\nbo Krzyż, to klucz do nieba.\n\n" +
                "2. Nie zdejmę Krzyża z mojej duszy,\nnie wyrwę go z sumienia.\nBo Krzyż szatana wniwecz kruszy,\nbo Krzyż, to znak zbawienia.\n\n" +
                "Ref. A gdy zobaczę w poniewierce\nJezusa Krzyż i ranę,\nktóra otwiera Jego Serce,\nw obronie Krzyża stanę.\n\n",
        "1. Niebiosa, rosę spuśćcie nam z góry,\nSprawiedliwego wylejcie chmury!\nO wstrzymaj, wstrzymaj Twoje zagniewanie\ni grzechów naszych zapomnij już Panie!\n\n" +
                "2. Niebiosa, rosę...\nGrzech nas oszpecił i w nędznej postaci\nstoim przed Tobą, jakby trędowaci.\n\n" +
                "3. Niebiosa, rosę...\nO spojrzyj, spojrzyj na lud Twój znękany\ni ześlij Tego, co ma być zesłany!\n\n" +
                "4. Niebiosa, rosę…\nPociesz się ludu, pociesz w twej niedoli,\njuż się przybliża kres twojej niewoli!\n\n" +
                "5. Niebiosa, rosę...\nRoztwórz się ziemio i z łona twojego\nwydaj nam, wydaj już, Zbawcę naszego!\n\n",
        "Ref. Niech będą przepasane biodra wasze\ni zapalone pochodnie. /x2\n\n" +
                "1. Bądźcie podobni do ludzi,\nktórzy czekają na Pana,\nkiedy z uczty weselnej powróci,\n" +
                "aby Mu zaraz otworzyć,\ngdy przyjdzie i zakołacze.\n\n" +
                "2. Wtedy szczęśliwi ci słudzy,\nktórych Pan czujnych zastanie.\nSwoje biodra rzemieniem przepasze,\n" +
                "każe im zasiąść do stołu,\nobchodząc, sam będzie służył.\n\n" +
                "3. Gdyby gospodarz to wiedział,\nkiedy złodziej nadejdzie,\nnie pozwoliłby wkraść się do domu.\n" +
                "W niespodziewanej godzinie\nCzłowieczy Syn do nas przyjdzie.\n\n",
        "Niech będzie chwała i cześć, i uwielbienie.\nChwała i cześć Jezusowi. Chwała.\nNiech będzie chwała, tak, Jemu chwała i cześć.\n\n",
        "Niech Cię Pan błogosławi i strzeże.\nNiechaj Pan daje pokój Ci.\nOn miłością napełnia serce twe, zawsze z Tobą jest.\n\n",
        "1. Tęsknotę, Panie, wlej nam w duszę,\ndo ziemi czystej i zielonej.\nFranciszkowego ducha tchnij\nw człowiecze serca zagonione.\n\n" +
                "Ref. Niech czysta woda i zieleń łąk,\nniech błękit nieba i ptaków lot,\nto wszystko, coś Panie stworzył mocą swoich rąk,\nniech głosi chwałę Twą.\n\n" +
                "2. Od nas zależy piękno ziemi,\nod nas zależy każdy dzień.\nPotrzeba kogoś, kto serca zmieni,\nkto śniegom wróci dawną biel\n\n" +
                "3. Miłości Bożej Piewco ubogi,\nratować ziemię przyszedł czas.\nSercom ludzkim tak bardzo drogi,\nświęty Franciszku, prowadź nas.\n\n",
        "Niech moje milczenie strzeże ofiary,\nktórą składam Tobie.\nNiech cierpienie moje będzie zakryte.\n\n",
        "Niech nas ogarnie łaska, Panie, Twa.\nDuch Twój Święty niech dotknie nas.\n\n",
        "Niech oblicze Twe, Panie mój,\nzajaśnieje nad sługą twym.\nNiech przenika mnie jego blask.\n\n",
        "1. Niech Twój Święty Duch\ndziś przenika mnie\ni niech zawsze już\ngości w duszy mej.\n\n" +
                "Ref. I niech spadnie deszcz\nbłogosławieństw Twych.\nOjcze, obmyj mnie,\nDuchu Święty, przyjdź.\n\n" +
                "2. Duchu Święty, przyjdź, działaj z mocą w nas.\nDuchu Święty, przyjdź, przemień smutku czas.\n\n",
        "1. Przejdziemy świat, a w sercach żar,\nmodlitwą będzie nasz każdy krok.\nNadzieja rośnie, dzień już świta,\nto rozbrzmiewa nowy śpiew.\n" +
                "To ten sam blask rozjaśnia świat,\njuż od dwóch tysięcy lat.\nSerca tęsknią, cierpią, pragną\nprzebudzenia jeszcze raz.\n\n" +
                "Ref. Niech zapłonie płomień,\nniech w ciemności płonie.\nZmienia noc na jasny dzień,\n" +
                "niech śpiew się wzmaga\ni niech miłość wzrasta.\nOgniu, płoń!\n\n" +
                "2. Za prawdą idź, w miłości mów.\nJezusa Imię to nasza moc.\nBy chronić dzieci, upadłych podnieść,\nnapełnić naród pieśnią swą.\n" +
                "Niech ten sam blask rozjaśnia świat,\nniech dalej trwa tysiące lat.\nSerca tęsknią, cierpią, pragną\nprzebudzenia jeszcze raz.\n\n",
        "Niechaj Cię, Panie, wielbią wszystkie Twoje dzieła,\ni święci Twoi niech Cię błogosławią!\n" +
                "Niech mówią o chwale, Twojego Królestwa\ni niech głoszą Twoją potęgę /x2\n\n",
        "Niechaj miłość Twa, jak potężna fala,\nspłynie tu przez łaski Twej zdrój.\nChryste, dotknij mnie.\n\n",
        "Niechaj zstąpi Duch Twój i odnowi ziemię,\nżyciodajny spłynie deszcz na spragnione serce.\n" +
                "Odnów mnie i uświęć mnie,\nuwielbienia niech popłynie pieśń. /x2\n\n" +
                "Ref. Chwała Jezusowi, który za mnie życie dał.\nChwała temu, który pierwszy umiłował mnie.\nJezus, tylko Jezus Panem jest.\n\n",
        "Ref. Niegodny jestem Panie przyjąć Cię do mej duszy,\nlecz niech to wyznanie, niech Cię żal mój wzruszy.\n" +
                "Pociesz mnie, przebaczeniem daruj mi winy, zbrodnie\ni uświęć swym wejrzeniem, bym Cię przyjął godnie.\n\n" +
                "1. Dzieci niegodne tak wielkiej ofiary\nz Twego stołu dary biorą.\nRęce do Ojca podnoszą po dary\ni z ufnością, i z pokorą.\n" +
                "Panem Tyś naszym i Ojcem na wieki,\nchociaż słabi pobłądzimy.\nPrzecież spod Twojej nie ujdziem opieki,\ngdy Cię pośród nas czujemy.\n\n" +
                "2. My za Chrystusem idziemy przykładem, czy w nieszczęściu, czy w niedoli.\nOn nam pokazał i wzory, i szlaki, jak żyć według Ojca woli.\n" +
                "Boży Baranku, co gładzisz grzech świata, aby lud Twój żył bezpiecznie.\nPrzywróć nam wiarę i miłość do brata, daj nam pokój, pokój wieczny.\n\n",
        "Ref. Niepojęta łaska Jego,\nwciąż dosięga nas,\ngdy podnosi, przemienia serca\ni uwalnia nas.\n\nPrawda ta.\n\n" +
                "1. On jest ojcem sierot,\nopiekunem wdów, jest dobry Bóg.\nDom swój daje wszystkim opuszczonym.\nZsyła deszcz obfity\n" +
                "na to co spragnione i wyczerpane jest.\nDobry jest dla biednych,\nw łasce swej nasz Bóg\ndo nas uniżył się.\n\n" +
                "2. On zna każde serce,\nnie odwraca się, gdy wzywamy Go.\nKocha nas pomimo tego,\nże tak niewiele możemy mu dać.\n\n",
        "Ref. Niepojęta Trójco, niewysłowiona!\nWsłuchany w Ojca przedwieczny Syn,\nŚwięty Duch, Pocieszyciel,\nPokoju Dar!\n\n" +
                "1. Chwała i śpiew Tobie, Trójco niepojęta,\nwielbimy Cię hymnem, pieśnią i muzyką.\n" +
                "Radości bez końca uciesz się darami,\ntrud nasz jest tańcem przed Tobą, Najwyższy!\n\n" +
                "2. Miłości przedziwna, odpuść zło, nieprawość,\nprzybądź z odpocznieniem, nadzieją i mocą.\n" +
                "Jedności cudowna przynieś ciałom zdrowie,\nchorym bądź lekarstwem, ukojeniem serc.\n\n" +
                "3. Niechaj w Twej opiece wznoszą się kościoły,\nz troską strzeż pokoju, pojednaj walczących.\n" +
                "Świętych Zamieszkanie, spójrz na wszystkie domy,\ngdzie chleb dzielą z głodnym, bo Ty mieszkasz w nim.\n\n" +
                "4. Wspominamy braci, którzy powiedzieli:\n„Pomnijcie na nas w waszej modlitwie!”\n" +
                "Wejrzyj na nich, Panie, Ojcze miłosierdzia,\nwedług bogatego skarbca miłości i łaski.\n\n" +
                "5. Przez modlitwy Matki, co Pana powiła,\nJózefa, co z lękiem Skarb brał w swoje ręce,\n" +
                "męczenników, sprawiedliwych, którzy w Tobie żyli,\nwysłuchaj modlitwy, przyjmij dziękczynienia.\n\n" +
                "6. Ojcze, Synu, Najpiękniejszy Duchu,\nTrójjedyna Miłości, Boże wszechdobroci,\n" +
                "chwała Tobie, śpiew, nasze uwielbienia,\npo wszystkie wieki i czasy bez końca.\n\n",
        "Ref. O, Hostio Święta, zdroju Bożej słodyczy\nTy dajesz duszy mojej moc.\n\n" +
                "1. O, Tyś Wszechmocny,\nktóryś przybrał ciało z Dziewicy.\nPrzychodzisz do serca mego utajony\nI nie dosięga Cię zmysłów moc.\n\n" +
                "2. Me serce jest Ci mieszkaniem, O Królu Wiecznej Chwały.\nPanuj w mym sercu i króluj w Nim, Jako w pałacu.\n" +
                "O wielki, niepojęty Boże, Któryś raczył tak się uniżyć.\nOddaję Ci cześć!\n\n",
        "Ref. O Jahwe, Boże Ojców moich,\nO Jahwe, Boże mój troskliwy.\nBądź uwielbiony, Panie mój,\nZa łaskę, którą dajesz mi.\n\n" +
                "1. Pragnę Panie, abyś wydobył\nZ mego wnętrza wonny olejek,\nJak uczyniłeś to w krzewie gorejącym,\nKtóry płonie i się nie spala.\n" +
                "Poślij mnie do Egiptu,\nBy do końca obumarł we mnie stary człowiek,\nAby spełnił swoje posłannictwo,\nPrzez które objawisz ku nam swoją miłość.\n\n" +
                "2. Niechaj mądrość, którą zdobyłem u faraona,\nUstąpi we mnie Twojej mądrości.\nNiechaj lata spędzone na pustyni.\nWydadzą owoc posłuszeństwa z miłości.\n" +
                "Niechaj spełni się Twoje pragnienie,\nNiech wola Twoja przeze mnie się dokona.\nChcę obumrzeć jak ziarno dla Ciebie,\nAbyś Ty sam plon wyprowadził.\n\n" +
                "3. Za łaskę rozmawiania z Tobą,\nZa łaskę bycia z Tobą twarzą w twarz.\nBądź uwielbiony, Panie mój.\nBądź uwielbiony, Panie mój.\n\n",
        "O Jezu, cichy i pokorny,\nuczyń serce me, według serca Twego.\n\n",
        "O mój najsłodszy Jezu,\nJeśli Twa miłość zabić mnie ma,\nNiechże ta chwila teraz\nTo szczęście mi da.\n\n",
        "Ref. O Jezu, Boże utajony, serce moje Cię czuje.\nChoć kryją Ciebie zasłony, Ty wiesz, że Cię kocham.\n\n" +
                "1. Miłosierdzie Pana chcę śpiewać na wieki,\nWyśpiewywać je będę przed wszystkim ludem.\n" +
                "Bo ono jest największym przymiotem Boga.\nJest nieustannym cudem.\n\n" +
                "2. Wytryskujesz z Troistości Bożej,\nLecz z jednego miłosnego łona.\n" +
                "Ze źródła miłosierdzia Twego płynie wszelkie szczęście\nI życie.\n\n" +
                "3. Przez otwarte serce ukrzyżowanego Syna,\nŹródła miłosierdzia Boga otwarte są dla nas.\n" +
                "Nie dla aniołów, Cherubinów, Serafinów,\nLecz dla człowieka grzesznego.\n\n",
        "O mój Panie, uczyń mnie\nnarzędziem Twojego pokoju. /x2\n\nSpraw, o mój Panie, bym niósł ludziom miłość,\ngdzie jej brakuje.\n" +
                "Niósł ludziom światło, gdzie ciemność panuje,\noświetlał drogę innym.\nAlbowiem dając, otrzymujemy,\n" +
                "przebaczając, zyskujemy przebaczenie.\nRodzimy się do życia, do życia, przez Chrystusa,\nPana naszego.\n\n",
        "O Najwyższy Boże nasz, Przechwalebny Stwórco,\nracz rozjaśnić ciemność serca mego, Panie.\nDaj prawdziwą wiarę, wytrwałą nadzieję,\n" +
                "doskonałą miłość oraz zrozumienie.\nAbym mógł wypełnić, Panie, Twoją świętą wolę.\nAbym mógł wypełnić Twoją nieomylną wolę.\n\n",
        "O Pani, ufność nasza w modlitwy Twej obronie.\nChroń nas, chroń nas, Królowo Pokoju.\n\n",
        "1. Panie, szukasz dzieci Twych,\nbo Miłość - Imię Twe.\nTy rozproszone złączyć chcesz,\ndać udział w łaskach swych.\n\n" +
                "Ref. Panie, Panie złącz w Twym Kościele,\nrozdzielonych braci.\nZłącz wszystkich nas w miłości Twej.\n\n" +
                "2. Tyś zbawił świat przez swoją śmierć,\nbo Miłość - Imię Twe.\nO, zechciej włączyć wszystkich nas,\nw mistyczne Ciało swe.\n\n" +
                "3. Ty zaspokajasz wszelki głód,\nbo Miłość - Imię Twe.\nO, uczyń znowu z chlebem cud\ni nakarm dzieci swe.\n\n" +
                "4. Wszak obiecałeś pokój dać,\nbo Miłość - Imię Twe.\nRacz w serca żar miłości wlać,\nKrólestwo rozszerz swe.\n\n",
        "Ref. O Panie, nasz Panie, przedziwne Twe Imię\ni ponad niebiosa majestat Twój. /x2\n\n" +
                "1. Kimże jest człowiek, że o nim pamiętasz.\nKim syn człowieczy, że wciąż jesteś z nim.\n" +
                "Przyozdobiłeś go czcią i swą chwałą.\nBoskim swym życiem dzielisz się z nim.\n\n" +
                "2. Dałeś mu władzę nad dziełami swych rąk.\nI wszystko rzuciłeś pod jego stopy.\n" +
                "Owce i bydło, i dzikie zwierzęta.\nI wszystko, co płynie szlakami do mórz.\n\n",
        "1. Panie, Ty nam dajesz Ciało swe i Krew. /x2\nDo Ciebie więc idziemy wciąż, radosną nucąc pieśń.\n\n" +
                "Ref. Ty jesteś Bogiem wiernym, na wieczny czas. /x2\n\n" +
                "2. Przez dar Twojego Chleba, dobry Boże nasz. /x2\nRodzinę bratnią czynisz z nas, miłować uczysz nas.\n\n" +
                "3. To Twoje miłowanie dało wolność nam. /x2\nChwalimy święte Imię Boga, jak uczyłeś nas.\n\n" +
                "4. Tyś nam wyznaczył drogę wiodącą na Twój szlak. /x2\nIdziemy więc, by radość nieść tam, gdzie jej światu brak.\n\n",
        "1. Panie, Tyś moim Pasterzem,\ntak dobrym, że nic mi nie braknie.\nDo źródeł wód żywych mnie wiedziesz,\nprostymi ścieżkami prowadzisz.\n\n" +
                "Ref. Pasterzem moim jest Pan\ni nie brak mi niczego. /x2\n\n" +
                "2. Choć idę przez ciemną dolinę,\nniczego nie muszę się trwożyć,\nbo Pasterz mój zawsze jest przy mnie,\nw obronie mej stanąć gotowy.\n\n" +
                "3. Do stołu swojego zaprasza,\nna oczach mych wrogów to czyni.\nOlejkiem mą głowę namaszcza,\na kielich napełnia obficie.\n\n" +
                "4. Twa łaska i dobroć podążą\nw ślad za mną, po dzień mój ostatni.\nAż dotrę, o Panie, do domu,\nby z Tobą zamieszkać na zawsze.\n\n",
        "Ref. O Panie, uczyń uczyń z nas\nnarzędzia Twojego pokoju.\n\n" +
                "1. Abyśmy siali miłość, tam gdzie panuje nienawiść.\nWybaczenie, tam, gdzie panuje krzywda.\nWiare, tam gdzie panuje zwątpienie.\n" +
                "Nadzieję, tam gdzie panuje rozpacz.\nŚwiatło, tam gdzie panuje mrok.\nRadość, tam gdzie panuje smutek.\n\n" +
                "2. Spraw, abyśmy mogli, spraw, abyśmy mogli.\nNie tyle szukać pociechy,\nCo ją dawać;\n" +
                "Nie szukać zrozumienia, lecz rozumieć;\nNie tyle szukać miłości, ale kochać /x2\n\n" +
                "3. Albowiem dając - otrzymujemy;\nGdy wybaczamy – wybacza się nam;\nGdy umieramy, rodzimy się\nGdy umieramy, zaczynamy żyć. /x2\n\n",
        "O Piękności niestworzona, kto Ciebie raz poznał,\nten nic innego pokochać nie może.\n" +
                "Czuję, że tonę w Nim jako jedno ziarenko piasku\nw bezdennym oceanie.\n" +
                "Czuję, że nie ma ani jednej kropli krwi we mnie,\nktóra by nie płonęła miłością ku Tobie.\nMiłosierdzie Boże. /x3\n\n",
        "O Przenajświętsze oblicze Jezusa, zasłoń nas. /x2\nGdy modlę się, wpatrując się w Twą twarz,\ndotykasz mnie jasnością Twoich chwał.\n" +
                "Przy Tobie chcę na wieki być, w miłości Twojej trwać.\nO Panie, proszę spraw, bym w obliczu Twoim\nschronienie zawsze miał.\n\n",
        "1. O przyjdź, o przyjdź, Emanuelu,\nz niewoli wyrwij Izraela;\nza grzech wygnanie cierpi lud.\nJednorodzony Synu Boży!\n\n" +
                "Ref. Rozraduj się, rozraduj się,\nbo przyjdzie twój Emmanuel!\n\n" +
                "2. O przyjdź, Mądrości, Słowo Boże,\nnad wszystkim obejmij rządy Twe.\nNaucz nas roztropności, przyjdź,\nścieżkami życia prowadź nas.\n\n" +
                "3. O przyjdź, o przyjdź, Różdżko Jessego,\nz rąk nieprzyjaciół wyzwól nas,\nz przepaści i otchłani zła\nz czeluści śmierci wyrwij nas.\n\n" +
                "4. O przyjdź, o przyjdź, o Wschodzie Słońca,\ni oświeć ziemię Światłem Twym,\nniech przezwycięży nocne mgły\nrozproszy ciemność nocy mej.\n\n" +
                "5. O przyjdź, o przyjdź, Kluczu Dawida,\nniebo otworzyć w mocy Twej jest;\nbezpieczną drogą prowadź wzwyż\nna zawsze zamknij piekła drzwi.\n\n" +
                "6. O przyjdź, o przyjdź, o święty Adonaj,\ncoś Twemu ludowi Prawo dał\nna górze Synaj, najwyższej z gór,\nniech Słowo Twe na nowo stworzy nas.\n\n" +
                "7. O przyjdź, o przyjdź, o Królu królów,\nprzyjdź Zbawicielu, przyjdź i ocal świat,\nuwolnij nas z niewoli zła –\ni Twoją bliskość odczuć nam daj.\n\n",
        "O tak, tak, tak Panie, mówię tak, Twemu Słowu,\nO tak, tak, tak Panie, mówię tak Twojej Woli.\n" +
                "O tak, tak, tak Panie, mówię tak Twym natchnieniom.\nO tak, tak, tak tak Panie, mówię tak Twemu prawu.\n" +
                "Jesteś mym pasterzem, uczysz mnie jak tutaj żyć.\nTwoje napomnienia chronią mnie,\nstrzegą mnie, dają życie mi.\no - alleluja, alleluja /x2\n\n",
        "1. Ty, co mieszkasz sam\nw moim sercu na dnie.\nO Ty, co mieszkasz sam\n" +
                "w moim sercu na dnie.\nNiech Twój usłyszę głos\nw moim sercu na dnie. /x2\n\n" +
                "2. Ty, co mieszkasz sam\nw moim sercu na dnie./x2\nDaj Twą radością żyć\nw moim sercu na dnie. /x2\n\n" +
                "3. Ty, co mieszkasz sam\nw moim sercu na dnie. /x2\nDaj, bym się w Tobie skrył\nw moim sercu na dnie. /x2\n\n",
        "O, usłysz mój głos, /x2\nPanie mój, wołam Cię.\nO, usłysz mój głos, /x2\nprzyjdź i wysłuchaj mnie.\n\n",
        "O wychwalajcie Go wszystkie narody\ni wysławiajcie Go wszystkie ludy.\nJego łaskawość nad nami potężna,\n" +
                "a Jego wierność trwa na wieki.\nO Alleluja, Alleluja. /x4\n\n",
        "Obdarz nas, Panie, pokojem,\nbo my wierzymy w Twoją moc.\nPanie, udziel nam Ducha swojego, Ducha pokoju.\n\n",
        "Jezus - objawione serce Boga,\nnajczulsze, zranione dla mnie.\n\n",
        "Oblubienica i Duch wołają: „Przyjdź”,\na ten, kto słyszy, niech powie: „Przyjdź”.\nKto jest spragniony, niech przyjdzie i pije,\n" +
                "niech wody życia za darmo zaczerpnie.\nAlleluja, wielbimy Cię! /x4\n\n",
        "Oblubieniec czeka już, pierzmy szaty.\nBlisko dzień wesela.\nNieproszonych gości tłum stanie u bram,\nktóre Pan otwiera.\n\n" +
                "Ref. Jezus Oblubieńcem naszym jest,\na Kościół świętą panną, którą umiłował.\n" +
                "Jezus Oblubieńcem naszym jest,\na Kościół świętą panną, którą zdobył swoją Krwią.\n\n",
        "Ref. Oblubieńcem mym na ziemi\njest tylko Jezus opuszczony. /x2\nOblubieńcem moim jest Jezus,\n" +
                "oprócz Niego nie mam Boga.\nOpuszczony, opuszczony.\n\n" +
                "1. Bo w nim jest wszystko,\ncałe niebo z Trójcą.\nTak w Nim jest wszystko,\ncała ziemia z ludzkością.\n\n" +
                "2. Dlatego wszystko, co jest Jego,\njest także moje.\nA Jego jest cierpienie,\nwięc ono jest moje.\n\n" +
                "3. I tak przez resztę mego życia,\nzanurzony w cierpieniu,\nw bólu, samotności,\nw opuszczeniu, rozłące,\n" +
                "wygnaniu, odrzuceniu,\nśmierci w tym wszystkim,\nczym On jest,\nbo On jest cierpieniem.\n\n",
        "1. Obudź mnie do wiary Matko\nObudź mnie bym nie zasnął /x2\nCały Tobie się oddaję\n" +
                "Zawsze Twoim dzieckiem pragnę być\nW Twoich dłoniach me schronienie\nW Twym spojrzeniu pocieszenie Matko! /x2\n\n" +
                "Ref. Cały Twój Maryjo! Cały Twój na wieki!\n\n" +
                "2. Obudź mnie do wiary Matko\nObudź mnie bym nie zasnął /x2\nCały Tobie się oddaję\n" +
                "Zawsze Twoim dzieckiem pragnę być\nTyś potężną jest Królową\nMiażdżysz węża Twoją stopą Matko! /x2\n\n" +
                "3. Obudź nas do wiary Matko\nObudź naród by nie zasnął /x2\nW Twoje ręce go oddaje!\n" +
                "Chcemy dziećmi Twymi zawsze być!\nPrzecież Polski Tyś Królową!\nZwyciężymy z Twą pomocą Matko! /x2\n\n",
        "1. Znad oceanów do mnie wołasz\nGdzie każdy krok niepewny jest\nTy jesteś tam gdzie niewiadoma\nTam znajdę grunt dla wiary mej\n\n" +
                "Ref. I będę wzywać Imię Twe\nI ponad fale patrzeć chcę\nGdy burzą się\nDla duszy pokój w Tobie jest\nGdzie pójdziesz Ty\nJa pójdę też\n\n" +
                "2. Mój strach utonie w Twojej łasce\nBo ręka Twa prowadzi mnie\nI Ty mnie nigdy nie zawiodłeś\nWiem teraz też nie zawiedziesz mnie\n\n" +
                "Bridge.\nDuchu prowadź mnie\nGdzie wiara nie ma granic\nDaj mi chodzić nad wodami\nGdziekolwiek mnie zabierzesz\n" +
                "Prowadź głębiej niż\nPójść mogą moje stopy\nMoja wiara się umocni\nW Twej obecności Boże /x5\n\n",
        "Ocień mnie, Panie, swym milczeniem,\nOcień mnie swą miłością.\nChcę w Twoim cieniu\nTeż stać się cieniem.\n" +
                "Aby Twój Duch przepełnił mnie.\nAby Twój Duch przepełnił mnie.\n\n" +
                "Ref. Bliskością swą,\nBliskością swą,\nBliskością swą,\nBliskością swą.\n\n",
        "1. Oczarowałaś me serce, siostro ma, Oblubienico,\nOczarowałaś me serce.\nJednym paciorkiem Twych naszyjników,\n" +
                "Jednym spojrzeniem Twych oczu,\nZachwyciłaś mą duszę.\nBo słodki jest Twój głos, a twarz pełna wdzięku.\n\n" +
                "Ref. Wprowadź mnie królu w Twe komnaty,\nCieszyć się będziemy i weselić Tobą.\nO Ty, którego miłuje dusza moja,\nWskaż mi, gdzie pasiesz Twe stada.\n\n" +
                "2. Jak piękna jest miłość Twoja, siostro ma, Oblubienico,\nJak piękna jest miłość Twa.\nSłodsza od wina,\n" +
                "A zapach Twoich szat,\nNad wszystkie balsamy.\nMiodem najświeższym ociekają wargi Twe.\n\n" +
                "3. Ogrodem zamkniętym jesteś, siostro ma, Oblubienico,\nOgrodem zamkniętym.\nŹródłem zapieczętowanym.\n" +
                "Jak lilia pośród cierni,\nTak Ty pośród dziewcząt.\nO, jak piękna jesteś przyjaciółko moja, jakże piękna.\n\n",
        "1. Nie chcę zbyt wysoko sięgać Panie,\npatrzeć tam gdzie pycha lśni i chwała.\nChociaż tak wyniosłe ich mieszkanie,\nchoć zazdrości godny świata pałac.\n\n" +
                "Ref. Oczekuję Ciebie Panie,\njesteś mym oczekiwaniem.\nUspokajasz serce moje,\nuciszeniem jesteś i pokojem mym.\n\n" +
                "2. Nie chcę, by się wywyższało serce.\nWszystko czego pragnę to Twe progi.\nW ciszy na Twym progu siedzieć będę.\nCieszyć się, że jesteś moim Bogiem.\n\n",
        "1. Oczyść serce me, chcę jak złoto lśnić\ni jak czyste srebro.\nOczyść serce me,\nchcę czystego złota blaskiem lśnić.\n\n" +
                "Ref. Zstąp, Ogniu zstąp, przyjdź, oczyść serce me.\nPragnę być święty, Tobie oddany Panie.\n" +
                "Chcę zawsze być święty,\nTobie mój Mistrzu, na zawsze oddany.\nGotów, by służyć Ci.\n\n" +
                "2. Oczyść serce me, oczyść Swoją Krwią,\nspraw, bym mógł być święty.\nOczyść serce me\nz najtajniejszych grzechów, oczyść je.\n\n",
        "Oddaję Ci całą chwałę i cześć.\nOddaję Ci całe serce swe. /x2\nŚwięty, Święty, Święty Wszechmogący Jezus. /x2\n\n",
        "Ref. Oddaję Ci życie swe,\nTy, Panie, wiesz, jakie jest.\nZa wszystko przepraszam Cię,\nco było grzechem i złem.\n\n" +
                "1. Oczyść, Panie duszę mą,\nzapomnij mi moje winy.\nObmyj mnie Twą świętą krwią,\npodaruj mi nowe życie.\n\n" +
                "Ref. Daj Ducha świętego mi,\nby odtąd prowadził mnie.\nRozpalił dziś w sercu mym\nognisko miłości Twej.\n\n" +
                "2. Przyjdź, zamieszkaj w domu mym,\nz Twą łaską i miłosierdziem.\nOtocz mnie ramieniem swym,\nbo ja.\n\n" +
                "Ref. Oddaję ci życie swe,\nTy Panie, wiesz, jakie jest.\nZa wszystko przepraszam Cię,\nco było grzechem i złem.\n\n" +
                "3. Wybawiłeś duszę mą,\numarłeś za moje winy.\nJezu jesteś Panem mym,\nZbawicielem mym.\n\n" +
                "Ref. Oddaję ci życie swe,\nTy Panie, wiesz, jakie jest.\nZa wszystko przepraszam Cię,\nco było grzechem i złem.\n\n",
        "Oddajmy cześć, oddajmy cześć.\nWiecznemu Panu chwał, wiecznemu Panu chwał.\nPokłońmy się, pokłońmy się,\nTemu, który, Temu, który\n" +
                "rozpostarł niebiosa, rozpostarł niebiosa\ni utwierdził ziemi krąg, i utwierdził ziemi krąg.\nJego chwała, Jego chwała\n" +
                "wznosi się ponad szczyty gór, ponad szczyty gór.\nJego łaska, Jego łaska\nnad nami jest, a niebo jego tron.\nBóg to nasz Pan! I tylko On!\n\n",
        "1. Uczyń mnie gałązką w krzewie winnym,\nduchem płonącym jak ognisty krzew.\nNiech liście me nie zwiędną nigdy,\noczyść mnie i pozwól w sobie trwać.\n\n" +
                "Ref. Odnów mnie daj przynosić dobry owoc,\nwyzwól mnie i nowe życie daj.\nZasadź mnie nad płynącą żywą wodą,\nuświęć mnie i naucz w sobie trwać.\n\n" +
                "2. Ty którego miłuje dusza moja,\nwskaż mi gdzie pasiesz stada swe.\nKu źródłom wody żywej mnie poprowadź,\nbym nie błąkał się szukając Cię.\n\n",
        "1. Odpocznienie w cieniu skrzydeł Twych,\npokój poznam poprzez miłość Twą.\nChoć upadnę, nie będę martwił się,\ngdy schronienie znajdę w cieniu skrzydeł Twych.\n\n" +
                "Ref. Znajdę w cieniu, znajdę w cieniu\nZnajdę w cieniu skrzydeł Twych. /x2\n\n" +
                "2. W Twoim cieniu me schronienie jest,\nTwa osłona nie zawiedzie mnie.\nWobec strzał ja nie muszę trwożyć się,\ngdy schronienie znajdę w cieniu skrzydeł Twych.\n\n",
        "Ofiaruję, Panie, Ci cały mój świat,\ncałe moje życie.\nOfiaruję to, co mam, więcej już, Panie,\ndać nie umiem.\n\n" +
                "Ref. Weź, o Panie, duszę mą,\nweź, o Panie, serce me.\nWeź, o Panie, wszystko co mam.\nPrzecież, Panie, ja kocham Cię. /x2\n\n",
        "Ofiaruję Tobie, Panie mój,\ncałe życie me, cały jestem Twój, aż na wieki.\nOto moje serce, przecież wiesz,\nTyś Miłością mą jedyną jest.\n\n",
        "Ogłaszamy Królestwo Boże w nas. /x2\nWśród nas jest. /x2\n\nGłusi znów słyszą, niewidomi widzą,\n" +
                "zmarli powstają, by żyć.\nUbogim głosimy tę Dobrą Nowinę:\nJezus jest Królem. Amen!\n\n",
        "1. Ojcze, chwała Tobie,\nSwe życie składam Tobie, kocham Ciebie.\n\n" +
                "2. Jezu…\n\n3. Duchu…\n\n4. Trójco…\n\n",
        "Ojcze, daj mi Ducha, /x2\nbym z wdzięcznością\nprzyjął każde upokorzenie\ni przeciwności,\nktóre przychodzą do mnie.\n\n",
        "1. Nocą Ogród Oliwny,\nśpią twardo wszyscy uczniowie.\nCzuwa w bólu ogromnym\ni cierpi za nas Bóg - Człowiek.\n\n" +
                "Ref. Ojcze, jeśli możliwe, oddal ode mnie ten kielich.\nOjcze, jeżeli trzeba, chcę Twoją wolę wypełnić.\n\n" +
                "2. Nocą klęczę przed Tobą,\nucichły auta, tramwaje.\nWszystko w życiu zawodzi,\njedynie Ty pozostajesz.\n\n" +
                "3. Nocą patrzę przez okno\nna skrawek nieba chmurnego.\nTyle w życiu cierpienia,\nlecz nie śmiem pytać: dlaczego?\n\n",
        "1. Ojcze Franciszku,\ndlaczego tak smutno patrzysz z fresków?\nCzy nie cieszą cię już bracia ptaki\ni wilk u twoich stóp leżący?\n" +
                "Ojcze Franciszku,\ndlaczego tyle troski w twych oczach\ni skąd ta plama na twoim policzku?\nCzy to łza, czy kawałek farby odpadł?\n" +
                "I czy jeszcze grywasz czasami,\ndo wtóru z wiatrem, na patykach?\nCzy śpiewasz jeszcze „Hymn Stworzenia”?\ndawno cię nie było słychać.\n\n" +
                "Ref. Hej, Franciszku, przyjdź znów do nas,\nzejdź z obrazów, kościelnych fresków.\nTak, jak kiedyś biegaj po drogach.\nZnów miłości nikt nie kocha.\n" +
                "Hej, Franciszku, przyjdź znów do nas,\nswe ślady zostaw w pyle drogi.\nBo gdzieś zginęła Ewangelia,\nswym życiem pomóż nam ją odtworzyć.\n\n" +
                "2. Ojcze Franciszku,\npełnym troski wzrokiem patrzysz na mnie.\nCzy pragniesz mi znów opowiedzieć,\no tym, co znaczy doskonałość?\n" +
                "A choć jeszcze się zdarza czasami,\nże ktoś przemawia do drzew i ptaków,\nto jednak dziś nam trzeba ciebie,\nby głosić światu pokój i dobro.\n\n",
        "1. Ojcze mój, ja bardzo pragnę Twoim dzieckiem być.\nOjcze mój, Ty wszystko sprawisz we mnie, jeśli chcesz.\n\n" +
                "Ref. Gdy odchodzę,\nw swej dobroci w domu czekasz na mnie wciąż.\nKiedy wracam, już z daleka widzę Ciebie, Ojcze mój.\n\n" +
                "2. Nic nie muszę czynić, abyś Boże Ojcze kochał mnie.\nKochasz mnie, gdyż dzieckiem swoim uczyniłeś mnie.\n\n",
        "1. Ojcze nasz, któryś w niebie jest,\nniech święci się zawsze Imię Twe.\nNiech Królestwo Twe przyjdzie lada dzień,\nprosimy Cię, dziś prosimy Cię.\n\n" +
                "2. A powszedniego chleba racz nam dać,\nna pokuszenie nigdy nie wódź nas.\nA Ty z nami bądź, wspieraj nas i chroń,\nprosimy Cię, dziś prosimy Cię.\n\n" +
                "3. Za czyny złe darować karę chciej,\na wola Twa niech zawsze spełni się.\nTym, co winią nas, przebaczamy też,\nprosimy Cię, dziś prosimy Cię.\n\n",
        "Ojcze, Ty kochasz mnie,\nnie dlatego, że jestem dobry.\nAle dlatego, że jestem dzieckiem,\nTwoim dzieckiem.\n\n",
        "On jest moim życiem, On jest moją siłą,\nOn jest mą nadzieją, Jezus, mój Pan. /x2\nWielbić Cię chcę życiem mym,\n" +
                "wielbić Cię chcę pieśnią mą.\nZe wszystkich mych sił uwielbiać Cię chcę,\nmoja nadzieja, to Ty.\n\n",
        "On sam jest solą smakowitą\ni chlebem, Barankiem weselnym.\nOn studnią najlepszego wina,\nco przynosi radość.\n\n",
        "Ci, co zaufali Panu – odzyskują siły,\notrzymują skrzydła jak orły, biegną bez zmęczenia!\n\n",
        "Ref. Oświeć me serce Twe słowa dają życie wieczne.\nPokaż, co jest zakryte przede mną.\n\n" +
                "1. Twoje prawo miłości jest doskonałe,\nŚwiadectwo Twego Ducha uczy mnie mądrości.\nTwe nakazy radują moje serce,\nBlask Krzyża Twego olśniewa me oczy.\n\n" +
                "2. Usłyszałem Panie Twe wezwanie\nI pragnę pójść Twoją drogą.\nKto jednak widzi swoje błędy?\nOczyść mnie z błędów ukrytych przede mną.\n\n" +
                "3. Błagam Cię Panie, uchroń mnie od pychy,\nBy nie panowała nade mną.\nWtedy będę bezpieczny,\nModlić się będę moją słabością.\n\n",
        "Oto idzie mój Bóg, oto idzie mój Król.\nOto Zbawiciel mój, a Imię Jego Jezus. /x2\nTęsknię za Tobą Panie.\nWiem, że przyjdziesz niebawem,\n" +
                "z głębi serca dziś wołam: „Maranatha”.\nTęsknię za Tobą Panie.\nWiem, że przyjdziesz niebawem,\nz głębi serca dziś wołam: „Maranatha”.\n\n",
        "Oto idzie Ten, który zbawienie przynosi nam.\nOto idzie Zbawiciel świata, Mesjasz i Pan.\nOto idzie, a Kościół woła:\n" +
                "Maranatha, maranatha, maranatha.\nMaranatha, niech się otworzą niebiosa już dziś.\n" +
                "Maranatha, wołamy, Panie Jezu, dziś.\nMaranatha, wołamy wszyscy.\nMaranatha, maranatha, maranatha.\n\n",
        "Ref. Oto jest dzień, który dał nam Pan!\nWeselmy się i radujmy się w nim! Alleluja, Alleluja!\n\n" +
                "1. Ty, który mieszkasz w chmurze pełnej ognia,\nTy, który siedzisz na tronie z szafirów,\n" +
                "istot tajemnych otoczony chórem,\nBoże chwały wielkiej.\n\n" +
                "2. Ty, co zamykasz cały wszechświat w dłoni,\nczynisz z obłoków posłuszne rydwany.\n" +
                "Jako swych posłów Ty kierujesz wichry,\nBoże Stworzycielu.\n\n" +
                "3. Ty, któryś mówił w krzaku gorejącym,\nOjcze narodu dawnego przymierza.\n" +
                "Lud prowadziłeś przez czerwone wody,\nBoże Ocalenia.\n\n" +
                "4. Panie kapłanów, królów i proroków,\nzłota świątynia przybytkiem Twym była.\n" +
                "Tyś jednak wybrał dom swój w naszych sercach,\nBoże Miłosierny.\n\n" +
                "5. Aby pojednać grzesznych ludzi z Tobą,\ndałeś nam Syna, co stał się Człowiekiem.\n" +
                "W Nim okazałeś głębię swej miłości,\nBoże Odkupienia.\n\n" +
                "6. Przyjął On mękę, śmierć i pogrzebanie.\nKrwi swej purpurą przywrócił nam życie.\n" +
                "Dnia zaś trzeciego powstał z mroków grobu,\nBoże Zmartwychwstania.\n\n" +
                "7. Tobie Najwyższy, pełen majestatu,\nrazem z Twym Synem i Duchem płomiennym.\n" +
                "Sława niech będzie, cześć i dziękczynienie,\nBoże zawsze wierny.\n\n",
        "Oto jest dzień (oto jest dzień),\nktóry dał nam Pan (który dał nam Pan).\nWeselmy się (weselmy się)\ni radujmy się nim (i radujmy się nim).\n" +
                "Oto jest dzień, który dał nam Pan,\nweselmy się i radujmy się nim.\nOto jest dzień, oto jest dzień, który dał nam Pan.\n\n",
        "Ref. Oto JESTEM, wyciągam ręce Swe\nOto JESTEM, przyjdź do Mnie\nOto JESTEM, otwieram serce Me\nWe Mnie ukojenie znajdzie dusza twa.\n\n" +
                "1. Znam twój grzech, ból i strach, rany twe\nMiłość Ma leczy cię: - „Otwórz się”.\n" +
                "Oto stoję u drzwi:\n„Usłysz słowo Me\nWieczerzać dziś z tobą chcę!”\n\n" +
                "2. Twoje Imię jest na dłoniach Mych\nI na skrzydłach orlich niosę cię.\n" +
                "Szkarłat win twoich widzę.\nZ miłością tulę cię,\nNad śnieg wybielę szaty twe.\n\n" +
                "3. Z dołu śmierci wydobyłem cię,\nWykupiłem, więc nie lękaj się!\n" +
                "Nową pieśń w usta wkładam.\nUtwierdzam stopy twe\nJa Pan, Twój Bóg. Przebudź się!\n\n",
        "Oto On żyje, Chrystus Pan zmartwychwstał.\nŚmierci więzy zerwał On.\nTak, On żyje, zmartwychpowstał On\n\n",
        "Oto Pan, Bóg, przyjdzie,\nz rzeszą świętych k’nam przybędzie.\nWielka światłość w dzień ów będzie.\nAlleluja, Alleluja!\n\n",
        "Ref. Oto są baranki młode,\noto ci, co zawołali Alleluja!\nDopiero przyszli do zdrojów,\nświatłością się napełnili,\nAlleluja, Alleluja!\n\n" +
                "1. Na Baranka Pańskich godach,\nw szat świątecznych czystej bieli.\nPo krwawego morza wodach\nnieśmy Panu pieśń weseli.\n\n" +
                "2. W swej miłości wiekuistej.\nOn nas swoją Krwią częstuje.\nNam też Ciało swe przeczyste\nChrystus, kapłan, ofiaruje.\n\n" +
                "3. Na drzwi świętą Krwią skropione,\nAnioł mściciel z lękiem wziera.\nPędzi morze rozdzielone.\nwrogów w nurtach swych pożera.\n\n" +
                "4. Już nam Paschą Tyś, o Chryste,\nWielkanocną też ofiarą.\nTyś Przaśniki nasze czyste\ndla dusz prostych z szczerą wiarą.\n\n" +
                "5. ofiaro niebios święta,\nTy moc piekła pokonywasz.\nZrywasz ciężkie śmierci pęta,\nwieniec życia nam zdobywasz.\n\n" +
                "6. Chrystus piekło pogromiwszy,\nSwój zwycięski znak roztacza.\nNiebo ludziom otworzywszy,\nKróla mroków w więzy wtłacza.\n\n" +
                "7. Byś nam wiecznie, Jezu drogi,\nWielkanocną był radością.\nStrzeż od grzechu śmierci srogiej\nodrodzonych Twą miłością.\n\n" +
                "8. Chwała Ojcu i Synowi,\nktóry z martwych żywy wstaje.\nI Świętemu też Duchowi,\nniech na wieki nie ustaje.\n\n",
        "Oto stoję u drzwi i kołaczę./x2\nJeśli ktoś posłyszy mój głos i drzwi otworzy,\nwejdę do niego i będę z nim wieczerzał,\na on ze Mną, a on ze Mną.\n\n",
        "Otwórz me oczy, chcę widzieć Jezusa\ni być bliżej Niego i kochać goręcej.\nOtwórz me uszy i naucz mnie słuchać,\notwórz me oczy, chcę widzieć Jezusa.\n\n",
        "Otwórz me oczy, o Panie.\nOtwórz me oczy i serce,\nchcę widzieć Ciebie. /x2\n\nWywyższonego widzieć chcę,\n" +
                "ujrzeć Ciebie w blasku Twej chwały.\nWylej swą miłość i moc, gdy śpiewam:\nŚwięty, święty, święty, /x2\nchcę widzieć Ciebie.\n\n",
        "Otwórz okna swego serca i patrz.\nOtwórz okna swego serca i czyń. /x2\nTak niewiele może ktoś chcieć.\n" +
                "Tak niewiele możesz mu dać.\nTak niewiele trzeba nam mieć,\nby stać się jak Jezus!\n\n",
        "1. Otwórzcie serca, tchnienie daje Bóg,\nniech Jego życie rośnie w naszej duszy.\nI niech nowy lud narodzi się z wód,\n" +
                "Chrztem świętym na życie umocniony!\nOtwórzmy serca, tchnienie daje Duch,\nOn jest oddechem naszej duszy, nam zostawiony!\n\n" +
                "2. Wydajcie ciała na Ognia żar,\ntym ogniem płonie serce starej ziemi.\nZwycięstwa znak, Jezusa znak\n" +
                "nadzieją dusze wam napełni.\nWydajmy ciała na Ognia żar,\nJezusa spełni się pragnienie, nasze zbawienie.\n\n" +
                "3. Jak żyzna rola, przyjmij Ducha siew,\nniech On przemieni każde twe cierpienie,\nbo krzywda i ból to znak, że Bóg twój,\n" +
                "wraz z tobą, dopełnia odkupienie.\nJak żyzna rola, ten przyjmijmy siew,\nna służbę Panu dajmy siebie w żywym Kościele.\n\n" +
                "4. Zobaczcie, w sercach mieszka Boży Gość!\nTo On w modlitwę zmienia twe pragnienie.\nChoć smutek, choć noc, On daje wam moc,\n" +
                "by Bogu oddawać uwielbienie.\nPrzystańmy, w sercach słychać Ducha głos,\nw ciszy rozlega się wołanie: „Ojcze, nasz Panie!”\n\n",
        "1. Wracałem już z połowu, a sieci chude z głodu,\nten Człowiek stał na brzegu, wypłynąć kazał znów.\n" +
                "Gdy zarzuciłem sieci, wsłuchany w Jego Słowa,\nja nie wiem w jaki sposób, lecz ryb tam było w bród.\n\n" +
                "Ref. Ach, kim Ty jesteś Człowieku,\nże mocne są Twe Słowa,\nże moc jest w Twoich dłoniach,\nże idziesz w Imię Boga?\n\n" +
                "2. Poszedłem wtedy za Nim, wesele było w Kanie,\ntam z dzbanów pełnych wody dał wino parze młodej.\n" +
                "Niejeden raz, gdy szliśmy, gdzie ślepiec o grosz błagał,\nOn wtedy jednym słowem ślepemu wracał wzrok.\n\n" +
                "3. Widziałem też tłum głodnych, jak o kęs chleba łkał.\nNajedli się do syta, On pięć bochenków dał.\n" +
                "I szedłem też po wodzie, tak jak szedł do mnie On,\nzwątpiłem, zacząłem tonąć, On podał mi swą dłoń.\n\n" +
                "4. Gdy w Paschę jadł wieczerzę, powiedział łamiąc chleb,\nże to jest Jego Ciało, a wino to Jego Krew.\n" +
                "A potem się Go zaparłem. Widziałem jak dźwigał krzyż.\nTak gorzko, gorzko płakałem i On wybaczył mi.\n\n" +
                "5. Widziałem Go dnia trzeciego i w wieczerniku też był,\na kiedy wstępował w niebo, otuchy nam dodał i sił.\n" +
                "A potem coś się stało: szum z nieba i ognia błysk.\nI odtąd mnie ludzie pytają, pytają mnie ludzie do dziś.\n\n",
        "Pan blisko jest, oczekuj Go.\nPan blisko jest, w Nim serca moc.\n\n",
        "Pan dobry jest, Pan dobry jest. /x2\nBłogosławiony mąż, który ufa mu.\nPan dobry jest, Pan dobry jest. /x2\nSkosztuj i zobacz, że Pan dobry jest.\n\n",
        "Pan jest mocą swojego ludu, pieśnią moją jest Pan.\nMoja tarcza i moja moc.\nOn jest mym Bogiem, nie jestem sam.\nW Nim moja siła, nie jestem sam.\n\n",
        "Pan jest Pasterzem moim, niczego mi nie braknie.\nNa niwach zielonych pasie mnie,\nnad wody spokojne prowadzi mnie. /x2\n\n",
        "Pan jest pasterzem moim,\nniczego mi nie braknie, na zielonych niwach pasie mnie.\nNad spokojne wody mnie prowadzi\n" +
                "i duszę mą pokrzepia,\ni wiedzie mnie ścieżkami sprawiedliwości swojej\n\n" +
                "Ref. Choćbym nawet szedł ciemną doliną,\nzła się nie ulęknę, bo Ty jesteś ze mną.\nLaska Twoja i kij Twój mnie pocieszają.\n\n",
        "Pan ma wielką moc, Pan ma wielką moc\nw Nim cała nasza siła, Alleluja, Alleluja.\nCała nasza siła, Alleluja.\n\n",
        "Ref. Pan mnie strzeże, Pan mnie strzeże.\nCzuwa nade mną Bóg, On jest moim cieniem. /x2\nCzuwa nade mną Bóg, /x3\nMój Bóg.\n\n" +
                "1. Chcę z Tobą chodzić\npo wszystkich mych drogach.\nProwadź mnie w mocy swej,\nabym nie upadł.\n\n" +
                "2. Nie boję się,\nwchodzę w noc ciemną bez lęku.\nDobrze to wiem,\nTy jesteś ze mną.\n\n" +
                "3. Biegnę do Ciebie,\naby być z Tobą.\nChcę Ciebie widzieć,\naby być sobą.\n\n" +
                "4. Jak Heli, o Panie, z Tobą chodzić chciał,\ntak z Tobą chodzić chcę.\nJak Paweł wszystkie kroki Tobie dał,\ntak ja oddaję kroki swe.\n\n",
        "Ref. Pan Wieczernik przygotował, swój zaprasza lud.\nDla nas wszystkich dom otworzył i zastawił stół.\n\n" +
                "1. Przyjdźcie z ulic i opłotków, bowiem mija czas.\nPrzyjdźcie chorzy i ubodzy, Pan uzdrowi was.\n\n" +
                "2. Każdy człowiek w domu Pańskim swoje miejsce ma.\nNiech nikogo w nim nie braknie, uczta Pańska trwa.\n\n" +
                "3. Przystępujmy z dziękczynieniem, pożywajmy chleb.\nPijmy napój nieśmiertelnych, aby życie mieć.\n\n" +
                "4. Zakosztujcie i poznajcie tej Wieczerzy smak.\nZ obfitości boskich darów bierzcie pełnię łask.\n\n" +
                "5. Kto się z Panem tu spotyka, tworzy jedno z Nim.\nBóg z miłością go nazywa przyjacielem swym.\n\n" +
                "6. Nakarmieni Chlebem Żywym, wznieśmy Panu pieśń.\nBóg żyjący i prawdziwy strzeże naszych serc.\n\n",
        "Ref. Pan woła: „Pokochaj Mnie.\nPójdź za mną, pokochaj Mnie.\nJam Prawda, pokochaj Mnie.\nZwyciężysz, pokochaj Mnie”.\n\n" +
                "1. Na rynku świata tłum się przeplata,\nmijają lata, Tyś ciągle z dala.\nDlaczego stoisz? Patrz, życie płynie.\nCzego się boisz? Z Panem nie zginiesz.\n\n" +
                "2. Idę za Tobą w przepastne knieje,\ncałą osobą, chociaż wiatr wieje.\nA kiedy tonę i z drogi zbaczam,\ngdy wstydem płonę, On mi przebacza.\n\n" +
                "3. Jeszcze dogłębnie Cię nie pojmuję,\nlecz żyć bez Ciebie już nie próbuję.\nZnalazłem tego, co skarbem świata,\na miłość Jego nas wszystkich brata.\n\n",
        "Pan wywyższony,\nnasz Król wywyższony wśród chwał,\nuwielbiajmy Go.\nPan wywyższony, nasz Król wywyższony,\noddajmy Jemu cześć.\n" +
                "Królem jest Pan, Jego łaska na wieki trwa.\nNiebo i ziemia radują się Jego imieniem.\nPan wywyższony, nasz Król wywyższony\nwśród chwał.\n\n",
        "Ref. Pan zmartwychwstał, bracia radujmy się!\nPokonał grzech, uwolnił nas od śmierci.\nW Krzyżu zbawienie jest,\nJest to droga, którą trzeba nam iść.\n\n" +
                "1. Niewiasto czemu płaczesz?\n- Zabrano Pana, nie wiem, gdzie On jest.\nMario, kogo szukasz?\n- Rabbuni, mój Rabbuni!\n\n" +
                "2. Witajcie, pokój Wam!\n- Panie, pokaż ręce swe i bok.\nPokój wam, nie bójcie się!\n- Pan mój i Bóg mój!\n\n" +
                "3. czym rozprawiacie w drodze\n-o Jezusie, który miał wybawić nas\nDlaczego nie wierzycie Pismu?\n- Czy serce nie pałało w nas, gdy z nami był?\n\n" +
                "4. Zarzućcie sieci, gdzie wam powiem\n- Pragnę słuchać tego, co mówisz mi.\nCzy kochasz mnie bardziej niż inni?\n- Tak Panie, Ty wiesz, że kocham Cię.\n\n" +
                "5. Weźmijcie Ducha Świętego!\n-Panie, z nami zawsze bądź.\nJestem z wami po wszystkie czasy\n-Alleluja, Alleluja!\n\n",
        "Ref. Panie, daj mi łaskę wiernej modlitwy,\nAbym nie opuścił Cię w godzinie Krzyża\n\n" +
                "1. Smutna jest moja dusza, aż do śmierci\nPrzybył z nieba Anioł, aby mnie umacniać.\n\n" +
                "2. Moje serce pogrążone jest w udręce.\nKrwawy pot spływa na ziemię.\n\n" +
                "3. Nie zasłoniłem swojej twarzy przed opluciem.\nNie otworzyłem ust, gdy szydzili ze mnie.\n\n" +
                "4. Moje dłonie i stopy przybite do krzyża.\nOstra włócznia przeszyła mi serce.\n\n" +
                "5. Smutna jest moja dusza, aż do śmierci.\nZostańcie tu i czuwajcie ze mną.\n\n",
        "Ref. Panie dobry jak chleb,\nbądź uwielbiony od swego Kościoła.\nBo Tyś do końca nas umiłował,\ndo końca nas umiłował.\n\n" +
                "1. Tyś na pustkowiu chleb rozmnożył Panie,\nbyśmy do nieba w drodze nie ustali.\n" +
                "Tyś stał się manną wędrowców przez ziemię\ndla tych, co dotąd przy Tobie wytrwali.\n\n" +
                "2. Ziarna zbierzemy, odrzucimy chwasty,\nbo łan dojrzewa, pachnie świeżym chlebem.\n" +
                "Niech ziemia nasza stanie się ołtarzem,\na Chleb Komunią dla spragnionych Ciebie.\n\n" +
                "3. Ty nas nazwałeś swymi przyjaciółmi,\njeśli spełnimy, co nam przykazałeś.\n" +
                "Cóż my bez Ciebie, Panie uczynimy?\nTyś naszym życiem i oczekiwaniem.\n\n" +
                "4. Tyś za nas życie swe oddał na Krzyżu,\na w znaku chleba w świątyniach zostałeś.\n" +
                "I dla nas zawsze masz otwarte serce,\nbo Ty do końca nas umiłowałeś.\n\n",
        "Panie, jeśli chcesz, możesz mnie uzdrowić.\nPanie, jeśli chcesz, uzdrów mnie.\n\n",
        "Panie, Jezu Chryste, Synu Boży,\nzmiłuj się nade mną.\n\n",
        "Panie mój, wiesz, że Cię kocham,\nTy wszystko wiesz, miłuję Cię!\n\n",
        "Panie, moje serce małe jest i oczy me nie są wyniosłe.\nNie gonię za tym, co jest wielkie,\nAlbo co przerasta moje siły.\n" +
                "Wprowadziłem ład i pokój do mojej duszy.\nJak niemowlę u swej matki,\nTak we mnie jest dusza moja,\nMe serce małym stało się.\n\n",
        "Ref. Panie mój, cóż Ci oddać mogę\nza bezmiar niezliczonych łask?\nW każdy dzień sławić będę Cię,\nwielbić Cię. Alleluja! /x2\n\n" +
                "1. Ty jak Ojciec wiedziesz mnie i chronisz mnie od łez.\nTy znasz wszystkie kroki moje próżny więc mój lęk.\n\n" +
                "2. Gdy wyznaję nędzę swą, jak Ojciec słuchasz mnie.\nGdy lęk śmierci mnie ogarnia, Ty umacniasz mnie.\n\n" +
                "3. Byłem słabym dzieckiem Twym, Tyś mnie w ramiona wziął.\nGdy ubogim byłem Panie, Tyś wzbogacił mnie.\n\n" +
                "4. Błogosławię Imię Twe, i sławię dobroć Twą.\nWiem, że dojdę drogą Twoją w święty Ojca dom.\n\n",
        "1. Panie mój, /x2 proszę wejdź, /x2\nproszę ucisz moje serce,\npokój wlej.\n" +
                "Cicho tak, /x2 pukasz w drzwi. /x2\nCzy usłyszę dziś Twój głos? Pomóż mi!\n\n" +
                "2. Zostań tu, /x2 rozgość się, /x2\nja usiądę u Twych stóp,\nbędę słuchał Cię.\n" +
                "Słowo Twe, /x2 prawdą jest, /x2\nwięc pozostań jak długo tylko chcesz.\n\n",
        "Panie mój, przychodzę dziś,\nserce me skruszone przyjm.\nSkłaniam się przed świętym tronem Twym.\nWznoszę ręce moje wzwyż, miłość mą wyznaję Ci.\n" +
                "Uwielbiam Ciebie w Duchu\nuwielbiam w prawdzie Cię!\nŻycie me oddaję Tobie, uświęć je.\n\n",
        "Panie, my Twoje dzieci,\nwybrane w Imieniu Twym.\nJedno serce i cel nas gromadzi,\nby wielbić Cię i śpiewać hymn.\n\n" +
                "Tylko Ty Panie jesteś Fundamentem,\nnie musimy się bać.\nGdy nadejdą burze, my będziemy trwać,\nBo Ty zbawiłeś nas. /x2\n\n",
        "Panie nasz, o Panie nasz,\njak przedziwne jest Twe Imię./x2\n\nI nie ma w żadnym zbawienia,\n" +
                "gdyż nie dano ludziom pod niebem.\nŻadnego innego imienia,\nw którym trzeba dać się zbawić.\n\n",
        "Panie, ogień daj, wypal smutek i żal,\nwypal myśli natrętne, niechciane,\nwypal to, co winno być zapomniane.\nPanie, ogień daj.\n\n",
        "Ref. Zabierz mnie tam, gdzie miejsce najświętsze.\nZabierz mnie przez Baranka Krew.\n" +
                "Zabierz mnie tam, gdzie miejsce najświętsze,\nogień włóż, dotknij ust,\notom jest.\n\n" +
                "Panie, proszę, zabierz mnie\ntam, gdzie jest Twój tron.\nZabierz mnie od tłumu ludzi,\nprzed oblicze Twe.\n" +
                "Chcę być, Panie, blisko Ciebie,\npatrzeć w Twoją twarz.\nŚwiat zostawić gdzieś daleko,\nw Twych objęciach trwać.\n\n",
        "1. Panie, przebacz nam,\nOjcze, zapomnij nam.\nZapomnij nam nasze winy,\nprzywołaj kiedy zbłądzimy,\nOjcze, zapomnij nam.\n\n" +
                "2. Panie, przyjmij nas,\nOjcze, przygarnij nas.\nI w swej ojcowskiej miłości,\nku naszej schyl się słabości,\nOjcze, przygarnij nas.\n\n",
        "Panie, przyjdź, czekamy, to Twój czas.\nJezu, przyjdź i ulecz nas.\nDuchu, przyjdź, napełnij nas.\nKochamy Cię, kochamy Cię,\n" +
                "kochamy Cię, Panie nasz.\n\nPanie przyjdź, ja czekam, to Twój czas.\nJezu przyjdź i ulecz mnie.\n" +
                "Duchu przyjdź, napełnij mnie,\nJa kocham Cię, ja kocham Cię,\nJa kocham Cię, Panie mój.\n\n",
        "Panie, Twój tron wznosi się\nnad wszystkie ziemie świata,\njesteś Najwyższy, Panie mój, Królu mój! /x2\nWywyższamy Cię, /x3 Boże nasz! /x2\n\n",
        "Panie Ty znasz moje imię,\nukryte jest w Tobie. /x2\nObjawiaj swoją miłość,\nktórą zachowałeś dla mnie. /x2\n\n",
        "Panie, wielbię Cię\ni chcę z dnia na dzień\nbliżej Ciebie być, z Tobą zawsze żyć.\nPanie, wielbię Cię.\n\n",
        "Panie, zmiłuj się nad nami.\nPanie, zmiłuj się, Panie, zmiłuj się\nnad nami. /x2\n\nChryste, zmiłuj się nad nami.\n" +
                "Chryste, zmiłuj się, Chryste, zmiłuj się\nnad nami. /x2\n\nTo jest Baranek, który oddał swoje życie\n" +
                "Miłość wcielona, ratująca swoje dzieci.\n\nBaranku bez zmazy, który gładzisz nasze grzechy.\nWlej pokój w serca, racz przemienić nasze życie.\n\n",
        "Ref. Panie, co chcesz, co chcesz, abym czynił?\nIdź i odbuduj Mój Kościół! /x2\n\n" +
                "1. Wszechmogący, wiekuisty, sprawiedliwy Boże,\ndaj nam czynić dla Ciebie to,\n" +
                "o czym wiemy, że Ty tego chcesz\ni chcieć zawsze tego,\nco Tobie się podoba.\n\n" +
                "2. Oczyść, oświeć nas i rozpal ogniem Ducha swego,\nbyśmy mogli iść za Twym Synem,\n" +
                "Jezusem Chrystusem\ni dojść do Ciebie\njedynie dzięki łasce.\n\n",
        "Ref. Panie, daj mi Twej wody,\nZaprowadź mnie nad strumień,\nAbym mógł popłynąć.\n\n" +
                "1. Pokaż mi Twoje serce, Panie,\nPokaż mi Twój bok, mój Królu,\nAby stamtąd popłynęła woda,\nWoda dająca życie.\n\n" +
                "2. Poprowadź mnie dalej\nTam, gdzie woda jest głębsza, niż cały mój grzech\n" +
                "Poprowadź mnie ręką anioła\nI pokaż mi miejsce, gdzie zobaczę dobre owoce.\n\n" +
                "3. Daj mi teraz stanąć w zatoce,\nAbym pomógł Ci w cierpieniu Krzyża.\nPomóż mi odnaleźć radość i szczęście\nW tym, co zostawiłeś dla soli.\n\n",
        "Panie, gdy tonę Ty ocal mnie.\nWierzę Ci, ufam, że\nKrwią zapłaciłeś bym Twoim był.\nOto więc jestem Twym.\n\n",
        "Panie, Panie nasz, dokąd pójdziemy?\nTy masz słowa życia wiecznego.\nTyś jest Chrystus, Tyś jest Chrystus,\nSyn Boga wiecznie żywego!\n\n",
        "Panie, pozostań, ma się ku wieczorowi.\nDzień się już nachylił.\n\n",
        "Ref. Panie, przepasz mnie\nI poprowadź, gdzie ja nie chcę pójść.\nPanie, o Panie, przepasz mnie. /x2\n\n" +
                "Czy miłujesz mnie więcej, aniżeli ci?\nPanie, Ty wiesz, Ty wiesz, że Cię kocham.\nCzy miłujesz mnie więcej, aniżeli ci?\n" +
                "Panie, Ty wiesz, Ty wiesz, że Cię kocham.\nCzy miłujesz mnie więcej, aniżeli ci?\nPanie, Ty wiesz, że ja miłuję Cię.\n\n",
        "Ref. Panie, spraw, by me serce\nStało się sercem Twoim. /x2\n\nBym był cichy i pokorny jak Ty\nAbym znalazł ukojenie dla duszy mojej.\n\n",
        "1. Panie, światło miłości Twej świeci,\npośród wszelkiej ciemności świeci.\nOświeć nas, Jezu, Światłości świata.\n" +
                "Wyzwól prawdę, którą przynosisz.\nOświeć mnie, oświeć mnie.\n\n" +
                "Ref. Świeć, Jezu, świeć, chwałą Ojca napełnij ziemię.\nPłoń, Duchu, płoń, w sercach ogień złóż.\n" +
                "Płyń, Rzeko, płyń, zalej łaską narody całe.\nŚlij słowo Twe, światłość niech stanie się.\n\n" +
                "2. Panie, wchodzę w Twą obecność,\ncień ustąpił przed Twoim blaskiem.\nDzięki krwi Twojej żyję w światłości.\n" +
                "Badaj mnie, wypal, pochłoń ciemności.\nOświeć mnie, oświeć mnie.\n\n" +
                "3. Widzę Twoją królewską jasność,\nktóra twarze nam rozjaśnia.\nPotem prowadzi od chwały do chwały,\n" +
                "życiem naszym o Tobie opowie.\nOświeć mnie, oświeć mnie.\n\n",
        "Panie, tyś jest moim Bogiem.\nPanie, tyś jest moim Bogiem.\nPanie, tyś jest moim Bogiem,\nJezu. /x2\n\n",
        "Panie, wysłuchaj prośby mej, błogosław duszę mą.\nPomocną podaj dłoń, bym nie pogrążył się,\nw godzinie złej ze mną bądź.\n\n",
        "Panu naszemu pieśni grajcie,\nwysławiajcie Jego święte Imię.\nAlleluja, Alleluja, Alleluja, Alleluja. /x2\n\n",
        "Ref. Panu śpiewaj, Pana chwal, Alleluja!\nJego chwała, Jego czas, Alleluja.\n" +
                "Więc wyśpiewać dzisiaj chcę całą radość w duszy mej,\na na ustach moich pieśń, Alleluja!\n\n" +
                "1. Pan jest Mocą i Pasterzem, słyszę Jego głos.\nOn powiedział mi: „Weź ziemię, pracuj,\n" +
                "bądź szczęśliwy, z mocą moją radę sobie dasz.\nSłuchaj nauk Mych, w sercu radość miej”.\n\n" +
                "2. Pana chwali moja dusza, sławiąc Jego moc,\nprawda Jego słów jest moją nadzieją.\n" +
                "Więc nie umrę, lecz żyć będę,\nTak powiedział Pan, jeśli tylko chcę, szczęśliwy mogę być.\n\n",
        "Na krzyżu poślubił mnie Pan,\nKrzyż jest słodkim łożem miłości.\nTam słucham Jego milczenia,\n" +
                "Cisza rozdarła zasłonę w świątyni.\nOblicze Przedwiecznej Miłości,\nw pełni się wtedy odsłoniło.\n" +
                "Spadł deszcz, Paschalny spadł deszcz,\nmoje szaty znów są śnieżnobiałe.\nMyślałam, że to może ogrodnik,\n" +
                "bo w ogrodzie wezwał mnie po imieniu.\nMam pierścień, na palcu mam pierścień,\nOjciec mi go tam nałożył.\n" +
                "Chcę tańczyć, z radości chcę tańczyć,\nmoje serce dziś omdlewa z miłości.\n" +
                "Zmartwychwstał (Alleluja), Prawdziwie zmartwychwstał (Alleluja).\nPocieszyciel mi to dziś powiedział,\n" +
                "Alleluja (Alleluja), Alleluja (Alleluja),\nMoje serce dziś omdlewa z miłości./x2\n\n",
        "1. Patrzysz na mnie takim wzrokiem,\nże z tego wszystkiego, aż płakać mi się chce.\n" +
                "Patrzysz na mnie takim wzrokiem,\nże bezczelnie proszę o jeszcze.\n\n" +
                "Ref. W Twoim spojrzeniu słońca,\nw Twoim tchnieniu wiatru.\nW Twojej tęsknocie deszczu, czuję miłość Twoją, Panie.\n" +
                "W moim smutku i radości,\nw moim zwątpieniu i pewności.\nCzuję, czuję miłość Twoją, Jahwe.\n\n" +
                "2. Patrzysz na mnie z taką wiarą,\nz jaką nigdy nikt nie odważył spojrzeć się.\n" +
                "Patrzysz na mnie, choć nie mogę\nodwzajemnić spojrzenia Twego, bo boję się, chociaż wiem, że...\n\n" +
                "3. Panie co oznacza, to czym mnie doświadczasz?\nTwoje spojrzenie jest dla mnie oczyszczeniem.\n" +
                "Zapraszam Cię, Panie, do swojego życia,\nzłap mnie, Panie, za rękę i porwij w otchłań Twojej miłości.\n\n",
        "Per crucem et passionem tuam, libera nos Domine,\nlibera nos Domine, libera nos Domine, Domine./x2\n" +
                "Per sanctam resurrectionem tuam, libera nos Domine,\nlibera nos Domine, libera nos Domine, Domine.\n\n",
        "1. Per sorella piogia Ti ringraziamo.\n\nRef. Per sorella poverta grazie rendiamo!\n\n" +
                "2. Per sorella pane... (chleb) castita... (czystość) umilta... (pokora).\n\n" +
                "3. Za brata deszcz - dzięki Ci, Panie.\n\n" +
                "4. Za siostrę ubóstwo - dzięki Ci składamy (chleb… czystość… pokorę...).\n\n",
        "Ref. Zamknij mnie w swoich ramionach\nTaką małą, jak jestem.\nUtul mnie pieśnią Twego serca\nTaką małą, jak jestem.\n\n" +
                "1. Schowaj w spojrzeniu Twych oczu\nTaką małą, jak jestem.\nPrzybliż do moich Twe usta,\nTchnij we mnie znów swoją miłość.\n\n" +
                "2. Chcę pierwsza wybiec ku Tobie,\nGdy będziesz zdążał do Emaus.\nI z Twojej dłoni wziąć chleb\nZłamany cierpieniem za mnie.\n\n" +
                "3. Rozpoznać smak swego życia,\nBiorąc do ust Twoje Ciało.\nI w swoim sercu Cię zmieścić,\nOtulić Cię swoim życiem.\n\n",
        "Niech Twój rozbłyśnie dzień,\nniech nadejdzie Twe zmiłowanie.\nNiech wreszcie wstanie Twój dzień,\nocalenie przyniesie nam.\n\n",
        "Ref. Niechaj płomień Twej miłości spali mnie,\nniechaj spłonę dzisiaj w Nim.\n\n" +
                "1. Podążam Twoim śladem. Oto widzę:\njeszcze drżą gałązki, które poruszyłeś.\n" +
                "Widzę Twój cień, jeszcze żarzą się węgle z ognia,\nktóry rozpaliłeś, byłeś tu przede mną.\n\n" +
                "2. Czuję Twą obecność, lecz nie mogę Ciebie dojrzeć, Panie, tylko Cię odczuwam.\n" +
                "Wszystko stworzenie mówi do mnie, że byłeś tutaj przed chwilą.\n" +
                "3. Blisko stał mój Pan, słyszę jeszcze delikatne bicie Jego serca.\n" +
                "Proszę, daj mi się dotknąć, tęsknię Panie do spotkania z Tobą twarzą w twarz.\n\n" +
                "4. Mówisz tak delikatnie, że tylko jak milczę, mogę usłyszeć Twoje słowa.\n" +
                "Błagam, przebij moje serce, abym umarł, abym umarł z miłości do Ciebie.\n\n",
        "1. Pani święte Ubóstwo - Pan niech Cię strzeże!\nOblubienico świętych - Pan niech Cię strzeże!\n" +
                "Bo Ty zawstydzasz chciwość - Pan niech Cię strzeże!\nI troski tego świata - Pan niech Cię strzeże!\n\n" +
                "Ref. Wszystkie najświętsze cnoty,\nniechaj strzeże was.\nW nas niech strzeże Pan,\nod Niego pochodzicie!\n\n" +
                "2. Pani, święta Miłości - Pan niech Cię strzeże!\nTy gasisz pożądanie - Pan niech Cię strzeże!\n" +
                "Strach od nas odsuwasz - Pan niech Cię strzeże!\nI pokusy ciała - Pan niech Cię strzeże!\n\n" +
                "3. Pani, święta Mądrości -\nTy jesteś Królową -\nZawstydzasz Szatana -\nI jego przewrotność -\n\n" +
                "4. Pani święta Prostoto -\nPani święta i czysta -\nZawstydzasz mądrość ciała -\nI mądrość tego świata -\n\n" +
                "5. Pani, święta Pokoro -\nTy zawstydzasz pychę -\nWszystko, co jest ze świata -\nI wszystkich ludzi -\n\n" +
                "6. Święte Posłuszeństwo -\nTy poskramiasz cielesność -\nCzynisz człowieka sługą -\nWszystkich jego braci -\n\n" +
                "7. Kto jedną posiada – Pan niech was strzeże!\nPosiada i wszystkie - Pan niech was strzeże!\n" +
                "A kto jedną z was gardzi - Pan niech was strzeże!\nJakby nie miał żadnej - Pan niech was strzeże!\n\n",
        "Ref. Pochwalony bądź, o Panie,\nprzez świat cały, który jest Twym dziełem.\nPochwalony bądź, o Panie,\nprzez świat cały, który jest Twym dziełem.\n\n" +
                "1. Najwyższy Ojcze, dobry mój Panie.\nTwoja jest sława, cześć i chwała.\nBłogosławieństwo Tobie przystoi,\nczłowiek niegodzien wymawiać Imię Twoje.\n\n" +
                "2. Pochwalony bądź, o Panie,\nze wszystkimi Twymi stworzeniami.\nZ bratem słońcem, które dzień daje,\nTwoim, Najwyższy jest wyobrażeniem.\n\n" +
                "3. Pochwalony bądź, o Panie,\nprzez brata księżyc, siostry gwiazdy.\nBrata wiatr i czyste powietrze,\nprzez siostrę wodę i krzepki ogień.\n\n" +
                "4. Pochwalony bądź, o Panie,\nprzez siostrę naszą, Matkę Ziemię.\nOna nas żywi i utrzymuje,\nrodzi owoce, kwiaty i zioła.\n\n" +
                "5. Pochwalony bądź, o Panie\nprzez tych, co sobie wybaczają.\nWszelkie słabości znoszą w pokoju,\nw Tobie, Najwyższy, będą uwieńczeni.\n\n" +
                "6. Pochwalony bądź, o Panie,\nprzez siostrę naszą, śmierć cielesną.\nOna się zjawia przed człowiekiem,\numknąć jej nigdy nikt nie zdoła.\n\n" +
                "7. Błogosławiony, kto pełni Twą wolę,\ndzięki Ci składa, Tobie służy.\nChwalimy Ciebie, Najwyższy Ojcze,\nz wielką pokorą Tobie śpiewamy.\n\n",
        "Podnieś mnie, Jezu i prowadź do Ojca. /x2\nZanurz mnie w wodzie Jego miłosierdzia.\nAmen.\nAmen.\n\n",
        "1. Pogwarz ze mną święty Franciszku,\npogwarz tak, jak z bratem Jałowcem.\nChcę zapytać Ciebie o wszystko,\no czym myślę w bezsenne noce.\n\n" +
                "Ref. Jest wszystko, czym umysł się chlubi,\nzdobycze nauki i postęp.\nI cuda techniki wspaniałe,\n" +
                "lecz ciągle łzy płyną nieoschłe,\nbo gdzieś się Twój sekret zagubił\nradości doskonałej. /x2\n\n" +
                "2. Chcę o świata spytać problemy\ni o życie pełne konfliktów.\nLudzkość idzie jak we mgle ciemnej\nna drodze, co zda się donikąd.\n\n",
        "Ref. Pokładam w Panu ufność mą,\nzawsze ufam Jego słowu.\n\n" +
                "1. Z głębokości wznoszę głos do Ciebie,\nracz wysłuchać, Panie, prośby mej.\nNakłoń ku mnie ucho Twe łaskawie.\nUsłysz prośby i błagania.\n\n" +
                "2. Jeśli grzechów nie zapomnisz,\nPanie, któż przed gniewem Twym ostoi się?\nLecz ufamy, że przebaczysz winy,\nbyśmy kornie Ci służyli.\n\n" +
                "3. Całą ufność mą pokładam w Panu,\ndusza moja ufa Jego słowu.\nTęskniej czeka dusza moja Pana,\nniż jutrzenki nocne straże.\n\n" +
                "4. Tęskniej niż jutrzenki nocne straże,\nniechaj Pana czeka Boży Lud.\nBo u Pana znajdzie zmiłowanie\ni obfite odkupienie.\n\n",
        "Pokorna Służebnico Pana,\nłamiąca strzały nieprzyjaciela,\nmiażdżąca głowę węża. /x2\n\n" +
                "Bądź zawsze przy mnie,\nabym stał się pokornym sługą,\nna wzór mego Mistrza i Pana.\n\n",
        "Ref. Pokój i dobro, pokój wam,\nPokój i dobro, pokój wam. /x2\n\n" +
                "1. Niesiemy wam wieść radosną,\nfranciszkańską nucąc pieśń.\nI powtarzając od wieków hasło:\n„Pokój i dobro, pokój wam”.\n\n" +
                "2. Niesiemy wam wieść radosną,\nfranciszkańską nucąc pieśń.\nI powtarzając od wieków hasło:\n„Pokój i dobro, pokój wam”.\n\n",
        "Połóż mnie jak pieczęć na swym sercu,\njak pieczęć na swoim ramieniu.\nBo jak śmierć potężna jest miłość,\na zazdrość jej nieprzejednana jak Szeol.\n" +
                "Żar jej, to żar ognia, płomień Pański.\nŻar jej, to żar ognia, płomień Pański.\nWody wielkie nie zdołają ugasić Miłości,\n" +
                "nie zatopią jej rzeki.\nBo jak śmierć potężna jest Miłość,\na zazdrość jej nieprzejednana jak Szeol.\n\n",
        "Posłuchaj, córko, spójrz, nakłoń ucha,\nzapomnij o swym ludzie, o domu Twego Ojca.\nKról pragnie Twego piękna,\nOn twoim Panem, oddaj Mu pokłon.\n\n",
        "Ref. Posłuchaj Córko, spójrz i nakłoń ucha,\nZapomnij o swym ludzie, o domu\nswego ojca.\nKról pragnie twego piękna,\nOn twoim Panem, pokłon mu złóż. /x2\n\n" +
                "1. Wzywa cię najpiękniejszy z synów ludzkich,\nWdzięk się rozlał na jego wargach.\nSprawiedliwość i miłość Jego berłem,\nBłogosławieństwo i moc.\n\n" +
                "2. Oddaj Mu całe swoje życie\nZaufaj Mu, On ciebie wywyższy\nJego dom niech stanie się twoim domem\nJego wola, twą wolą.\n\n" +
                "3. Zbliż się do Niego ze swoimi darami,\nKrólowie świata szukać będą twych względów.\nWejdź do Królestwa pełna chwały,\nW złotogłów odziana.\n\n" +
                "4. W szacie wzorzystej zbliż się do swego Króla,\nDzisiaj jest dzień twoich zaślubin.\nZbliż się w radości i uniesieniu,\nDo królewskiego pałacu.\n\n",
        "1. Posyłam was na pracę bez nagrody,\nna ciężki, twardy i niewdzięczny trud.\nNiezrozumienie, drwiny i obmowy,\nposyłam was przydawać do mych trzód.\n\n" +
                "Ref. Tak, jak Ojciec Mój posłał Mnie,\ntak i Ja was ślę. /x2\n\n" +
                "2. Posyłam was opatrzyć ciężkie rany,\npomagać słabym, ich ciężary nieść,\npocieszać smutnych, wspomnieć zapomnianych,\nposyłam was radosną głosić wieść.\n\n" +
                "3. Posyłam was na krańce tego świata,\ngdzie w samotności serce nieraz łka.\nOpuścić każę ojca, matkę, brata,\nposyłam was na drogi mojej szlak.\n\n" +
                "4. Posyłam was do serc z zawiści twardych,\ndo oczu ślepych, zaciśniętych rąk.\nGdzie trzeba serc gorących i ofiarnych,\nposyłam was na całej ziemi krąg.\n\n",
        "Ref. Znam źródło bijące w mym wnętrzu,\nŹródło, które tryska i płynie.\nChoć się dobywa wśród nocy,\nTryska wśród nocy.\n\n" +
                "1. Wiem, gdzie swe wody ma ten zdrój kryniczny,\nUkryty w głębi tajemnic i wieczny\n- choć się dobywa wśród nocy.\n" +
                "Początku jego nie znam, bo go nie ma,\nLecz wiem, że każdy byt swą mocą trzyma\n- choć się dobywa wśród nocy.\n\n" +
                "2. Wiem, że nie może być nic piękniejszego,\nŻe wszelka światłość pochodzi od niego\n- choć się dobywa wśród nocy.\n" +
                "Blask jego światła nigdy nie zachodzi,\nBo wszelka światłość od niego pochodzi\n- choć się dobywa wśród nocy.\n\n" +
                "3. To wieczne źródło znalazło ukrycie\nW Chlebie Żywota, aby dać nam życie\n- choć się dobywa wśród nocy.\n" +
                "To jest źródło, którego pożądam,\nTo jest Chleb Życia, który tu oglądam\n- choć się dobywa wśród nocy.\n\n",
        "Ref. Powiedz Maryjo, Matko,\nStojąc pod Krzyżem Twego Syna.\nPowiedz o Jego Miłości,\nNaucz nas wiernie przy Nim trwać.\n\n" +
                "1. Nie ma On wdzięku ani też blasku,\naby na Niego popatrzeć,\nani wyglądu,\nby się nam podobał.\nWzgardzony i odepchnięty przez ludzi,\n" +
                "Mąż boleści, oswojony z cierpieniem,\njak ktoś, przed kim się twarze zakrywa,\nwzgardzony tak, iż mamy Go za nic.\n\n" +
                "2. Lecz On się obarczył naszym cierpieniem,\nOn dźwiga nasze boleści,\nwszyscy Go za skazańca uznali,\nchłostanego przez Boga i zdeptanego.\n" +
                "Lecz On jest przebity za grzechy ludzi,\nzdruzgotany za wszystkich winy.\nSpadła Nań chłosta zbawienna dla nas,\na w Jego ranach jest nasze zdrowie.\n\n" +
                "3. Wszyscy pobłądzili jak owce,\nkażdy obrócił się ku własnej drodze,\na Pan zwalił na Niego\nwiny nas wszystkich.\n" +
                "Dręczono Go, lecz sam się dał gnębić,\nnawet nie otworzył ust swoich.\nJak baranek na rzeź prowadzony,\n" +
                "jak owca niema wobec strzygących ją,\ntak On nie otworzył swoich ust\n\n",
        "1. Powiedziałeś Panie: „Dosyć lęku”.\nPowiedziałeś: „Kochaj, w tym jest sens”.\nPokazałeś dziwny świat miłości,\nw który teraz ponad wszystko wierzyć chcę.\n\n" +
                "Ref. Śpiewajmy Panu tę pieśń jedności,\nw której kochać chcemy wszystkich tak, jak On.\nChcemy żyć w prawdzie, wierze i miłości,\nchcemy stworzyć w Bożym świecie wspólny dom!/x2\n\n" +
                "2. Przemieniamy z Tobą ziemię w niebo,\nw białą szatę stroisz cały świat.\nPo ogrodach leżą kopy śniegu,\nto nie zima, anioł zgubił pióra swe.\n\n" +
                "3. I nadziei dałeś całe morze,\ntej nadziei, która każe iść.\nZbłękitniałe oczy Twej miłości,\ndają duszy wielką radość, kochać chcę.\n\n",
        "Ref. Powierz Panu swoją drogę i zaufaj Mu,\nOn sam będzie działał w twoim życiu.\n\n" +
                "1. Miej ufność w Panu i raduj się,\nOn spełni pragnienia twego serca.\nBóg wszechmogący, potężny Panów Pan,\nRaduj się i śpiewaj Jemu pieśni.\n\n" +
                "2. Zrzuć swą troskę na Niego,\nOn cię podtrzyma swoją mocną ręką.\nI nigdy nie dopuści do tego,\nByś się zachwiał na swojej drodze.\n\n" +
                "3. Gdy patrzę na dzieło palców Twoich,\nKsiężyc i gwiazdy, któreś Ty utwierdził.\nCzym jest człowiek, że o nim pamiętasz,\nCzym jest, że troszczysz się o niego.\n\n",
        "Powietrzem moim jest /x2\nobecność Twoja święta, w sercu mym.\nPowszednim chlebem mym /x2\nTwe żywe słowo, dane mi dziś.\n\n" +
                "Ref. Bez Ciebie nie umiem już żyć.\nDla Ciebie me serce chce bić.\n\n",
        "1. Zdobyty przez Ciebie Panie\nuznaję wszystko za śmieci.\nChcę poznać moc zmartwychwstania,\nupodobnić się do Ciebie w śmierci.\n\n" +
                "Ref. Powołał mnie Pan bez żadnej zasługi mej,\nlecz z łaski swojej i miłosierdzia. /x2\n\n" +
                "2. Chcę głosić Twe Słowo, Panie\nw mądrości Twojego Krzyża.\nStwórz we mnie więc serce czyste\ni odnów we mnie moc ducha.\n\n" +
                "3. Królowi, Oblubieńcowi\nwszelka chwała i miłość naszych serc. /x2\n\n",
        "Powstań i żyj, chociaż wokół mrok.\nPowstań i żyj, dobro wielką ma moc.\n" +
                "Powstań i żyj, choć upadłeś nie raz.\nJezus doda Ci sił, On zmartwychwstał byś żył. /x2\n\n" +
                "Ile trzeba łez, aby wrócić do Ciebie.\nJak daleko oddalić się, by usłyszeć Twój szept.\n" +
                "Jak bardzo żałować, aby pękło to serce kamienne.\nIle trzeba łez, aby wrócić do Ciebie.\n\n",
        "1. Pozdrawiam Ciebie Matko, mistrzyni moich dróg.\nPozdrawiam Ciebie Pani, nadziejo moich dróg.\n" +
                "Pozdrawiam Cię, Królowo, w swe dłonie weź nasz los.\nI wspieraj byśmy zawsze nieśli miłości ton.\n\n" +
                "Ref. Ave Maryja, woła cały świat.\nAve Maryja, Matko Boga i nas. /x2\n\n" +
                "2. Niech w sercach naszych zabrzmi,\ndla Ciebie „Ave” pieśń.\nNiech maj trwa dla nas zawsze,\nw nim wzrasta Twoja cześć.\n\n" +
                "3. Niech nigdy nie ustanie,\npozdrowień „Ave” moc.\nTy hymnem naszym stań się,\npragnieniem naszym bądź.\n\n",
        "1. Pozdrawiam Cię, Matko Syna Bożego.\nMatko módl się za nami, byśmy Słowo głosili\nChrystusa Ukrzyżowanego.\nNie w mądrości słowa, ale zgodnie z krzyżem.\n\n" +
                "Ref. Za nami wstawiaj się Maryjo,\nBy nie został zniweczony Krzyż.\nZa nami wstawiaj się Maryjo,\n" +
                "Aby został wywyższony Jezus Chrystus, Twój Syn.\nJezus Chrystus, nasz Pan.\n\n" +
                "2. Krzyż jest głupstwem dla tych, co są ze świata,\nBo świat szuka mądrości, bo świat chce ujrzeć znaki.\n" +
                "My głosimy Ukrzyżowanego, On zgorszeniem dla pogan,\nOn jest dla nas mądrością.\n\n" +
                "3. Módl się z nami, byśmy niczego nie znali,\nJak tylko Jezusa Ukrzyżowanego.\n" +
                "Aby nasze głoszenie nie uwodziło mądrością,\nAby ukazało Świętego Ducha i moc!\n\n",
        "1. Bądź pozdrowiona, Pani Święta,\nKrólowo Najświętsza, Boża Rodzicielko.\nMaryjo, zawsze Dziewico,\nwybrana przez Ojca, i Syna i Ducha.\n\n" +
                "Ref. Bądź pozdrowiona, Jego Pałacu,\nJego Przybytku.\nBądź pozdrowiona, Jego Mieszkanie,\nMatko Boga, Maryjo.\n\n" +
                "2. Bądźcie pozdrowione wy wszystkie święte cnoty,\nktóre Duch Święty swą łaską i oświeceniem\n" +
                "wlewa w serca ludzi, aby z niewiernych\nstali się wierni Bogu samemu.\n\n" +
                "Ref. Bądź pozdrowiona, Jego Szato,\nJego Służebnico.\nBądź pozdrowiona, Jego Matko,\nMaryjo, zawsze Dziewico.\n\n",
        "Pozostań z nami, Panie, bo dzień już się nachylił.\nPozostań z nami, Panie, bo zmrok ziemię okrywa.\n\n",
        "1. Pozwól mi przyjść do Ciebie,\ntakim zwyczajnym, jakim jestem.\nPozwól mi przyjść do Ciebie,\njednym łaskawym Twoim gestem.\n\n" +
                "2. Ty jesteś Mario taka czysta, jak łza grzesznika gdy żałuje.\nTy jesteś Mario taka dobra, to moje ludzkie serce czuje.\n\n" +
                "3. Więc się ośmielam przyjść do Ciebie, tak, jak przed matką dziecko staje.\nWięc się ośmielam paść przed Tobą i całą nędzę Ci wyznaję.\n\n" +
                "4. Patrzysz łaskawym na mnie wzrokiem, uczysz miłować, uczysz wiary.\nPatrzysz łaskawym na mnie sercem, uczysz wdzięczności i ofiary.\n\n" +
                "5. Zanim odejdę od stóp Twoich, pozwól, że skończę na pytaniu,\nktóre dotyczy mego życia: „Co znaczy trwać w ofiarowaniu?”\n\n",
        "1. Czy Ci uwierzę, gdy powiesz mi,\nŻe w Twojej dłoni mój każdy dzień.\nCzy Twego Słowa uchwycę się,\nBy codziennie wierzyć w nie?\n\n" +
                "Ref. Więc ja pójdę z wiarą,\nnawet gdy nie widzę już.\nChoć wiem, jestem słaby,\nWciąż ufam woli Twej!\n\n" +
                "2. Pomóż pokonać najgłębszy lęk,\nZawsze tak wierny byłeś mi.\nJednym swym tchnieniem odnawiasz mnie,\nTwoja łaska podnosi mnie.\n\n" +
                "Bridge:\nChoć złamany, lecz widzę Twoją twarz.\nPrzemówiłeś, wylałeś łaskę swą.\n\n",
        "1. Póki mego życia, Panu śpiewać chcę,\ngrać memu Bogu, póki życia starczy mi.\nNiech miła będzie Ci, Panie, moja pieśń,\nbędę radował się w Panu, Bogu mym.\n\n" +
                "Ref. Błogosław duszo moja Pana,\nAlleluja, Alleluja.\nBłogosław duszo moja Pana,\nAlleluja. /x2\n\n" +
                "2. Bogu memu ufam, On prowadzi mnie,\njak Pasterz owce swoje, Pan Bóg wiedzie nas.\n" +
                "Głosić chcę, o Panie miłosierdzie Twe,\nniech wszystkie ludy ujrzą chwały Twojej blask.\n\n",
        "Ref. Póki mego życia, chcę śpiewać memu Panu\ni grać mojemu Bogu, póki będę żył.\n\n" +
                "1. Boże mój jesteś bardzo wielki,\nodziany we wspaniałość i majestat.\nŚwiatłem okryty jak płaszczem,\nrozpostarłeś niebo jak namiot.\n\n" +
                "2. Wzniosłeś swe komnaty nad wodami.\nRydwanem Twoim obłoki.\nPrzechadzasz się na skrzydłach wiatru.\nZiemia jest pełna Twych sworzeń.\n\n" +
                "3. Niech miła Ci będzie pieśń moja.\nBędę radował się w Tobie.\nNiech chwała Twoja trwa na wieki.\nTy jesteś radością mego życia.\n\n",
        "Ref. Ojcze, w Twoje ręce\nPowierzam Ducha mego!\n\n" +
                "1. Całuj mnie pocałunkami Twoich ust,\nJak słodka Twoja miłość jest.\nPociągnij mnie biegnijmy,\nCałuj mnie, pragnę Ciebie.\n" +
                "Napełnij mnie Twoim Słowem,\nŁoże nasze z zieleni.\nChora jestem z miłości,\nCałuj mnie, pragnę Ciebie.\n\n" +
                "2. Powstań wietrze z północy,\nPowstań wietrze z południa,\nWietrze wiej przez mój ogród,\nBo omdlewam z miłości.\n" +
                "Życie ze mnie odeszło,\nMiły mój się oddalił.\nJam jest mego miłego,\nA On jest cały mój.\n\n" +
                "3. Na cóż budzić ze snu\nMoją umiłowaną.\nPóki nie zechce sama,\nPóki nie zechce sama. /x2\n\n",
        "Pragnę śpiewać Ci najpiękniejszą pieśń,\nz Aniołami tak pragnę śpiewać Ci. /x2\n\nŚwięty, Święty, Święty jest Pan,\n" +
                "Zastępów Pan. /x2\n\nCała ziemia jest pełna Jego chwały. /x4\n\n",
        "1. Spoglądasz w zieloną dłoń dębu,\njak mocno chwyta niebo.\nSpragnione Ciebie, Franciszku,\nw słowie wypowiedzianym.\n" +
                "Pamiętasz pierwsze spotkanie,\nprzy małej wiecznej lampce?\n„Co mam czynić Panie?”\n„Odbuduj Mój obraz na skale”.\n\n" +
                "Ref. Przychodzi w ciszy tu i pyta po cichu:\n„Jak usłyszeć ciszę, gdy wszystko wokół krzyczy?”\n\n" +
                "2. Spoglądasz w szarość kamienia,\njak mocny tworzy fundament.\nSpragniony Ciebie, Franciszku,\nw świętości ran Twych pięciu.\n" +
                "Nazywasz trąd bratem swoim,\nbo wiedzie Cię do miłości.\nOdbuduj mały kościółek,\nogniem z krzyża z San Damiano.\n\n" +
                "3. Spoglądasz bez wahania,\nw szeroką drogę prawdy.\nSpragniony Ciebie, Franciszku,\npodążam Twoim śladem.\n" +
                "Bo słyszą głos na dnie duszy:\n„Błogosławieni, pójdźcie za mną”.\nW pragnieniu mego Franciszka\ni ja, niegodny, pragnę.\n\n",
        "Pragnijcie posiąść Ducha Pańskiego\nwraz z Jego uświęcającym działaniem,\nwraz z wiarą i miłością, nadzieją i mocą.\n\n",
        "1. Prawda jedyna, słowa Jezusa z Nazaretu,\nże swego Syna posłał z nieba Bóg na świat.\n" +
                "Aby niewinnie cierpiąc, zmarł za nasze grzechy\ni w pohańbieniu przyjął winy wszystkich nas.\n\n" +
                "Ref. Dzięki Ci, Boże mój, za ten Krzyż,\nktóry Jezus cierpiał za mnie, Jezus cierpiał za mnie.\n\n" +
                "2. Uwierz w Jezusa, przecież On za ciebie umarł\ni z miłości do nas przyszedł z nieba na ten świat.\n" +
                "Błogosławiony ten, kto wierzy, choć nie widział,\nzaufaj dziś Bogu, a na wieki będziesz żyć.\n\n",
        "1. Gdy tracę Ciebie, słabnie miłość.\nGdy tracę Ciebie, blednie radość.\nGdy tracę Ciebie, znika pokój.\nGinę bez Ciebie, błagam więc Duchu:\n\n" +
                "Ref. Stwórz mą wiarę na nowo,\nniech wróci Twa miłość.\nStwórz mą wiarę na nowo, niech wróci Twa radość.\n" +
                "Stwórz mą wiarę na nowo, niech wróci Twój pokój.\nOdnów życie w mym sercu na dnie.\n\n" +
                "2. Franciszku, ucz nas kochać w Duchu.\nFranciszku, ukaż Jego radość.\nFranciszku, wprowadź w Jego pokój.\nNaucz nas prosić, wołaj dziś z nami.\n\n",
        "Ref. Prowadź, Panie, mnie nad strumień.\nProwadź, abym mógł popłynąć.\n\nUkaż mi, Panie, Twoje serce.\nUkaż mi, Panie, Twój bok.\n" +
                "Daj, aby popłynęła woda.\nProwadź mnie ręką anioła.\nPanie, poprowadź mnie dalej.\nTam, gdzie woda jest głębsza niż mój grzech.\n" +
                "Tam, gdzie rosną cudowne owoce.\nUzdrów mnie, Panie, Twoim Duchem.\nPanie, poprowadź mnie dalej.\nDaj mi też stanąć w zatoce.\n" +
                "W tym, co zostawiłeś dla soli.\nDaj odpocząć w zatoce.\nDaj odpocząć w Twym Krzyżu.\n\n",
        "1. Przed obliczem Pana uniżmy się. /x2\nPan sam wywyższy nas, Jego jest ziemia i czas,\nPan sam wywyższy nas.\n\n" +
                "2. Wszystkie swoje troski oddajmy Mu. /x2\nPan sam wywyższy nas, Jego jest ziemia i czas,\nPan sam wywyższy nas.\n\n" +
                "3. Z pokorą stań przed Panem swym. /x2\nPan sam wywyższy nas, Jego jest ziemia i czas,\nPan sam wywyższy nas.\n\n",
        "Przed tronem Twym stoimy,\nwpatrzeni w Twej miłości blask.\nDo Ciebie, Panie, podobni\nstajemy się, widząc Twą twarz!\n\n" +
                "Ref. Chwała Twa wypełnia nas,\nobecności Twojej blask,\ngdy wielbimy Ciebie, wiem, jesteś tu.\n\n" +
                "Bridge. Chwała, cześć, mądrość, moc,\nbłogosławieństwo,na wieki, na wieki! /x4\n\n",
        "1. Przed Twój tron, Panie nasz, idą ludzie, niosąc dar,\nChleb i wino na ofiarę.\n" +
                "Chciej wypełnić święty czas,\nprzyjmij dary, przyjmij nas i zbawienie nam ofiaruj.\n\n" +
                "Ref. Panie nasz pobłogosław\nstrudzonej pracy ludzkich rąk.\nPanie nasz, usłysz nasze głosy,\n" +
                "nie odwracaj się, wszak my niesiemy Tobie dary\ni ofiara niechaj ta nas ocali.\n\n" +
                "2. Każdy niesie to, co ma, każdy chce swą kroplę wlać.\nKażdy pragnie Twej ofiary.\n" +
                "Każdy niesie troski swe,\nwszyscy wierzą, Panie, że nie odwrócisz swojej twarzy.\n\n",
        "1. Czas goni, jak piasek w klepsydrze,\nprzechodzi, jak przez ciasną bramę.\nMa szansę przekroczyć nadzieję,\n" +
                "a zdaję się wciąż zwykłym piaskiem.\nPrzemija bez chwili wahania,\ngromadząc godziny bezcenne.\n" +
                "Nikogo nie słucha prócz Ciebie,\nTyś Królem czasu.\nBogiem wiecznym, Bogiem wiecznym,\nBogiem wiecznym.\n\n" +
                "Ref. Przemień mój czas w zaczyn Słowa Bożego.\nPrzemień mój czas w sól Twojego Istnienia.\nPrzemień mój czas w złoto Twego Majestatu.\n" +
                "W Światło Twego Oblicza.\n\nA piasek obróci się w miłość.\nA piasek obróci się w miłość.\n\n" +
                "2. Oddaję poranne westchnienia,\nsekundy pomiędzy krokami.\nPowierzam godziny zmagania,\n" +
                "minuty pomiędzy wierszami.\nPrzemieniaj momenty zamyśleń\nw przebłyski życiowej mądrości\n" +
                "I kieruj me stopy ku braciom,\na ręce niech im\nbłogosławią, błogosławią,\nbłogosławią.\n\n",
        "Ref. Przemień nas, Panie,\nuczyń cud jak dawniej w Kanie.\nPrzemień nas, Panie, woda niech się winem stanie.\n\n" +
                "1. Przed Tobą, Panie, wszelkie me pragnienie,\nnapój mnie sobą tak, jak kiedyś w niebie.\n" +
                "Daj nam Panie siebie, daj nam Twego wina.\nprowadź przez życie mocą Twego syna.\n\n" +
                "2. Woda, to stare przymierze, wino, to nowy testament,\nwoda przydaje się wierze, wino, to wiary fundament.\n" +
                "Woda potrzebna do życia, wino na ucztę się bierze,\nwoda, to nasza codzienność, wino smak daje wierze.\n\n" +
                "3. Wino upaja tak, jak woda męczy\ni znaczy miłość od lat już tysięcy.\n" +
                "Wodo, wodo, nie czekaj, wodo, wodo, nie zwlekaj,\nnapełnij miłością każdego człowieka.\n\n" +
                "4. Przemień nas, Panie, tak, jak kiedyś w Kanie,\nzmieniłeś wodę na Matki wołanie.\n" +
                "Zmieniłeś wodę, co znużenie znaczy,\nw cudowne wino, które nas jednoczy.\n\n",
        "Przemień serce me, uczyń szczerym je.\nPrzemień serce me,\npragnę być jak Ty./x2\n\n" +
                "Tyś jest Garncarzem, jam gliną jest.\nSkrusz mnie, ukształtuj, o to modlę się.\n\n",
        "1. Przeogromna ziemio wyszłaś z Bożych rąk,\nwykrzykuj radośnie, śpiewaj Mu i graj.\n" +
                "Świetlisty księżycu, niebo pełne gwiazd\ni ogromne słońce przed Panem swym tańcz.\n\n" +
                "Ref. O Najwyższy nasz,\nna dłonie wzniesione me spójrz.\nNiech płynie chwała, jak kadzideł dym.\n\n" +
                "2. Niech morze swym szumem Pana niebios czci,\npotoki niech nucą, tafla jezior lśni.\n" +
                "A szczytów majestat sięgający chmur,\nniech kornie przyklęknie, psalmy nucąc Mu.\n\n" +
                "3. Sam długo błądziłem, szukając Twych dróg,\npatrzyłem na piękno, na Twych palców cud.\n" +
                "Aż wreszcie odkryłem, żeś Ty stworzył świat,\nże ludzie to owce, a Tyś Pasterz nasz.\n\n",
        "Ref. Przepiękny Królu Wszechświata,\nKtóry królujesz z drzewa Krzyża,\nPełen wdzięku, Królu mój.\n\n" +
                "1. Choć Królem jesteś, patrzysz na mnie\n- pył leżący na ziemi.\nNa mnie - trawę, która rano zielona wieczorem usycha.\n" +
                "Na mnie - ziarnko piasku, krople wody w oceanie.\nKochasz mnie, o Panie, Ty kochasz mnie.\n\n" +
                "2. Choć Królem jesteś, patrzysz na mnie\n- proch unoszony wiatrem\nNa mnie - cień, który trwa przez moment, a po chwili znika.\n" +
                "Na mnie - płatek śniegu, najmniejszą gwiazdę na niebie.\nKochasz mnie, o Panie, Ty kochasz mnie.\n\n",
        "1. Przybądź, Panie, bo czekamy,\nTwego przyjścia wyglądamy,\nbo źle nam żyć bez Ciebie.\n\n" +
                "Ref. Gotujmy drogę Panu, prostujmy ścieżki Jego.\nPrzemieńmy swoje życie, odwróćmy się od złego.\n\n" +
                "2. Noc minęła, dzień jest blisko,\nw jasnym świetle czyńmy wszystko,\nze snu już powstać pora.\n\n" +
                "3. Pan jest blisko, między nami,\nw tłumie ludzi gdzieś wmieszany,\nczy umiem Go zobaczyć?\n\n" +
                "4. Pan jest blisko i też czeka,\nczy przyjść może do człowieka,\nczy kocham Go prawdziwie\n\n",
        "Ref. Przybądź Płomieniu, niech węgle naszych serc.\nRozpali ogień miłości Twej.\nZjednocz nas w Krzyżu, on bramą Pana jest,\nPrzez nią wejdziemy, by życie mieć.\n\n" +
                "1. Wejdźmy do świątyni jego ciała\n- przez przebity bok.\nW niej dziedzictwo nasze: skarb wody i krwi\n- obmyjmy się w nich.\n" +
                "Ta brama jest tak wąska, że aby przejść\n- trzeba dzieckiem być.\nI stracić swoje życie\n- by znaleźć je.\n\n" +
                "2. Na Krzyżu z Jego cierpienia, z Jego „Pragnę”\n- narodził Kościół się.\nZostawił nam swoje ciało, abyśmy trwali w Nim\n- uwielbiajmy Je.\n" +
                "Chleba okruch i kropla wina, to tak niewiele\n- a starczy na pokarm dusz.\nUsta moje Krwią skropione\n- Świątyni drzwi.\n\n",
        "1. Języku ognia, przyjdź i płoń w naszych sercach;\nDuchu miłości, przemieniaj nas.\n" +
                "Swe pocieszenie nam daj i nim wypełniaj;\nDuchu nadziei, umacniaj nas.\n\n" +
                "Ref. Przybądź Święty, niepojęty\nz nieba na nas tchnięty Duchu!\nRozpal świętą, niepojętą,\n" +
                "z nieba na nas tchniętą miłość!\nNią ogarnij nas! Amen!\nNią ogarnij nas! Amen!\n\n" +
                "2. Obłoku jasny, ochroń nas przed gniewem Złego;\ndaj soli smak i światłu blask.\n" +
                "Nie daj się ukryć miastu Boga przedwiecznego;\nosłaniaj je, strzeż jego bram.\n\n" +
                "3. Pocieszycielu, radość swą ześlij z nieba,\nwszak Dobry ją obiecał nam.\n" +
                "Przerwij milczenie w imię Syna - Słowa - Chleba;\nnajświętsze Tchnienie, wołaj w nas!\n\n",
        "Przybądź, Wschodzące Słońce,\nspojrzyj w otchłań ciemną. /x2\nA potem w Siebie przemień to, co jest moją nędzą.\n\n",
        "1. Przybądźcie tu z najdalszych stron gromady,\nnękane głodem i pragnieniem serc.\n" +
                "Odrzućcie smutek, niech nastanie radość.\nBóg w nasze ręce złożył ciało swe./x2\n\n" +
                "2. Weselnym winem już się woda staje.\nNa górach Chrystus Pan rozmnaża chleb.\n" +
                "Już winny krzew obfity owoc daje,\nBóg w nasze ręce złożył ciało swe.\n\n" +
                "3. Otwarte jak otwarta w boku rana,\nmiłości pełne, co nie kończy się.\n" +
                "Jest Słowo Ciałem i pokarmem dla nas,\nBóg w nasze ręce złożył ciało swe.\n\n",
        "Przychodzisz Panie mimo drzwi zamkniętych,\nJezu zmartwychwstały ze śladami męki.\n" +
                "Ty jesteś z nami, poślij do nas Ducha,\nPanie nasz i Boże, uzdrów nasze życie.\n\n",
        "Ref. O Panie mój, o Panie mój,\nPrzyjacielem Twego Krzyża pragnę być\nO Panie mój, o Panie mój,\nBramą ciasną, drogą wąską prowadź mnie!\n\n" +
                "1. Być przyjacielem Krzyża to znaczy:\nChcieć być świętym wolą mocną\nPrzez zwycięską łaskę Ducha Świętego.\n" +
                "To także znaczy:\nIść za Jezusem uniżonym,\nKtóry oddał za nas życie\nW uściskach Krzyża świętego.\n\n" +
                "2. Być przyjacielem Krzyża to znaczy:\nzaprzeć się samego siebie,\nszukać chwały w ubóstwie.\nUniżeniu i upokorzeniach.\n" +
                "To także znaczy:\nNie uskarżać się,\nNie usprawiedliwiać się\nI nie bronić swojego imienia!\n\n" +
                "3. Być przyjacielem Krzyża to znaczy:\nBrać z zapałem swój Krzyż,\nPodnieść z radością swój Krzyż,\nNieść z odwagą na ramionach swój Krzyż!\n" +
                "Swój Krzyż to znaczy:\nPrzyjąć taki jak daje Bóg,\nZ jego grubością i długością,\nSzerokością i głębią!\n\n" +
                "4. Być przyjacielem Krzyża to znaczy:\nNaśladować Jezusa,\nTak jak On nieść Krzyż,\nW taki sposób jak On nieść Krzyż!\n" +
                "Jak nieść swój Krzyż?\nZ miłością i z pokorą,\nZ wdzięcznością, bez szemrania,\nDobrowolnie, z wiarą i bez pychy\n\n",
        "Przyjaciela mam, co pociesza mnie,\ngdy o jego ramię oprę się.\nW nim nadzieję mam, uleciał strach,\n" +
                "On najbliżej jest, zawsze troszczy się.\nKrólów Król, z nami Bóg,\nkrólów Król, z nami Bóg.\nJezus, Jezus...\n\n",
        "1. Przyjacielu, chcę zostać z Tobą, przy Tobie chcę być.\nI nie trzeba byś mówił coś, wystarczy byś był.\n\n" +
                "Ref. Bo nie ma większej miłości niż ta,\ngdy ktoś życie oddaje, bym ja mógł żyć.\n\n" +
                "2. Chcę być z Tobą, gdy jest mi dobrze i kiedy mi źle.\nPrzyjacielu, otwieram przed Tobą serce swe.\n\n",
        "Przyjdź do nas cicho, jak baranek\nbezbronny, łagodny, biały Panie.\nI zmiłuj się, /x2 i pokój nam daj. /x2\n\n",
        "Ref. Przyjdź, Duchu Święty.\nPrzyjdź, Duchu Kościoła.\nPrzyjdź do Oblubienicy.\nPrzyjdź, wołamy Cię!\n\n" +
                "1. Duchu Święty, Panie nasz i Boże - wołamy, przyjdź!\nPrzyjdź, Duchu Święty!\nDuchu Święty, który pochodzisz od Ojca i Syna, przyjdź!\n" +
                "Duchu Święty, Obietnico Chrystusa - wołamy, przyjdź!\nDuchu Święty, Oblubieńcze Maryi - wołamy, przyjdź!\n\n" +
                "2. Duchu Święty, Darze Pięćdziesiątnicy - wołamy, przyjdź!\nDuchu Święty, Boże i Ożywicielu - wołamy, przyjdź!\n" +
                "Duchu Święty, Duchu Prawdy, Parakletos - wołamy, przyjdź!\nDuchu Święty, Odnowicielu naszych serc - wołamy, przyjdź!\n\n" +
                "3. Duchu Święty, Boże, Pocieszycielu - wołamy, przyjdź!\nDuchu Święty, Boże, Dawco łaski - wołamy, przyjdź!\n" +
                "Duchu Święty, Boże, Nauczycielu - wołamy, przyjdź!\nDuchu Święty, który przekonujesz świat o grzechu, sprawiedliwości i o sądzie.\n\n" +
                "4. Duchu Święty, działający w Chrystusie - wołamy, przyjdź!\nDuchu Święty, działający w Kościele - wołamy, przyjdź!\n" +
                "Duchu Święty, działający poprzez Słowo - wołamy, przyjdź!\nDuchu Święty, działający w naszym sercu - wołamy, przyjdź!\n\n",
        "Przyjdź, Duchu Święty - słysz wołanie,\nPrzyjdź, Duchu Święty - słysz wołanie,\nPrzyjdź, Duchu Święty - słysz, wzywamy Cię.\n" +
                "Przyjdź, Duchu Święty - słysz wołanie,\nsłysz wołanie, słysz, wzywamy Cię.\nPrzyjdź, obdarz nas - zbuduj Kościół swój.\n" +
                "Przyjdź, z prawdą swą - oddaj Panu cześć,\nPrzyjdź, prowadź nas - drogę wskaż,\nprzyjdź, z nami bądź.\n\n",
        "Przyjdź, Duchu Święty - strumieniu życia.\nPrzyjdź, Duchu Święty - podmuchu ognia.\nPrzyjdź, Duchu Święty - tchnienie miłości.\nPrzyjdź, na nas zstąp.\n\n",
        "1. Przyjdź, Duchu Święty, ja pragnę,\no to dziś błagam Cię.\nPrzyjdź w swojej mocy i sile,\nradością napełnij mnie.\n\n" +
                "2. Przyjdź jako mądrość do dzieci,\nprzyjdź jak ślepemu wzrok.\nPrzyjdź jako moc w mej słabości,\nweź wszystko, co moje jest.\n\n" +
                "3. Przyjdź jako źródło w pustyni,\nmocą swą do naszych dusz.\nO, niech Twa moc uzdrowienia\ndotknie, uleczy mnie już.\n\n",
        "Przyjdź Duchu Święty, napełnij me serce miłością,\nulecz mnie skruchą, obdarz nadzieją,\nTwoją napełnij mądrością!\n\n",
        "Ref. Przyjdź, Duchu Święty,\nnapełnij serca swych wiernych\ni zapal w nas swój ogień,\nogień miłości Twej.\n\n" +
                "1. Niech zstąpi Duch Twój,\nna nowo będziemy stworzeni.\nTo Ty sam odnowisz\noblicze ziemi Twej.\n\n" +
                "2. Nie moc, nie siła,\nlecz Duch mój na was spocznie.\nOn sam dopełni dzieła,\ntak mówi Zastępów Pan.\n\n" +
                "3. Pan jest Duchem,\ngdzie Duch Boży, tam jest wolność.\nBędziemy Cię wielbić Panie,\nna wieki wieków. Amen!\n\n",
        "Ref. Duchu Święty /x4\n\n" +
                "1. Przyjdź i rozpal nas, i ulecz nas, miłość nam daj!\n\n" +
                "2. Przyjdź, napełnij nas, rozraduj nas, miłość nam daj!\n\n" +
                "3. Przyjdź i utul nas, rozkochaj nas, miłość nam daj!\n\n",
        "Przyjdź i zajmij miejsce Swe na tronie naszych serc\nprzyjdź i zajmij miejsce Swe\n\nRef. Ciebie pragnie dusza moja\nW suchej ziemi pragnę Cię\n\n",
        "Przyjdź z pokłonem Ludu Boży,\nprzyjdź ze śpiewem ludu święty.\nSław Jezusa, swego Zbawcę,\nwspaniałego Króla chwały\n\n",
        "1. Przyjdź, Duchu pokory,\nprzyjdź, Duchu prostoty,\nprzyjdź, Duchu ubóstwa,\nradości doskonałej.\n\n" +
                "Ref. Duchu wiary, miłości, nadziei,\nDuchu rady, męstwa i pobożności,\nrozumu, umiejętności,\nmądrości, Bożej bojaźni\nwierności płynącej z Krzyża.\n\n" +
                "2. Przyjdź, Duchu czystości,\nDuchu posłuszeństwa.\nPrzyjdź, Duchu pokoju,\nmiłości braterskiej.\n\n" +
                "3. Przyjdź, Duchu małości,\nDuchu uwielbienia,\nDuchu nawrócenia,\nradosnej pokuty.\n\n" +
                "4. Przyjdź, Duchu wspólnoty,\nDuchu apostolstwa.\nPrzyjdź, Duchu Kościoła,\nPrzyjdź, Duchu Święty!\n\n",
        "Przyjdź, Duchu, przyjdź, serce me spragnione.\nPrzyjdź, Ogniu, przyjdź, serce me zgaszone.\nWeź, proszę, weź to, co mnie zniewala,\n" +
                "co rani i kochać miłości nie pozwala,\nw nas działaj!\nDuch Święty nade mną spoczywa,\n" +
                "namaścił mnie Pan.\nPosłał, abym niósł nowinę, nie muszę się bać!\nJuż bać, już bać, już bać!\n\n",
        "Przyjdź, przyjdź, światłości serc.\nPrzyjdź i zamieszkaj w nas,\nprzeniknij nas.\nPrzyjdź i napełnij zdrojem swoich łask.\nPrzyjdź, Duchu Święty, przyjdź.\n\n",
        "Przyjdźcie do stołu Miłosierdzia,\ngdzie ciało i krew Pana jest.\nWszyscy spragnieni przybądźcie,\nnakarmić dusze swe. /x2\n" +
                "Przyjdźcie, bo Pan nas zaprasza,\nwyciąga przebitą dłoń.\nSyćmy się ciałem Chrystusa,\nbo przez nie nam życie dał On.\n\n",
        "Przyjdźmy tam, gdzie miejsce najświętsze,\nprzez Baranka Krew wejdźmy tam.\nPrzyjdźmy Doń, śpiewaniem Go wielbiąc,\n" +
                "wywyższając Boży tron. /x2\nWznosząc ręce swe, wielbiąc Króla Chwał.\nChwała Ci Jezu!\nChwała Ci Jezu!\n\n",
        "Przyjdźmy, pokłońmy przed Nim się.\n\nUklęknijmy przed Bogiem Stwórcą naszym. /x2\nGdyż On ma wszelką moc,\n" +
                "a myśmy owcami pastwisk Jego.\nOraz trzodą Jego rąk,\noraz trzodą Jego rąk.\n\n",
        "1. Przyjmij, Boże, te dary z rąk Twego kapłana,\nten zadatek miłości Chrystusa i Pana.\n" +
                "Ten Chleb czysty i wino, które Ci składamy,\nna pamiątkę Chrystusa, gdy je poświęcamy.\n\n" +
                "2. Przyjmij, Boże, te dary, prosim Cię z łzami,\na dla Krwi Syna Twego, zmiłuj się nad nami.\n" +
                "Niech wonność tej ofiary przed tron Twój się wzniesie,\na nam wiecznej chwały nagrodę przyniesie.\n\n",
        "1. Przyjmij, Panie, dzisiaj nasze dary,\npobłogosław wino to i chleb.\nWszystko, co czynimy, wszystko, co śpiewamy\nprzyjmij w darze o dobry Boże nasz. /x2\n\n" +
                "2. Daj nam dziś Twoimi być uczniami,\nbo zyskuje, kto przy Tobie trwa.\nA Ty, karmiąc nas swoim ciałem, życie dajesz,\ntym, co dla świata zmarli już. /x2\n\n",
        "1. Przyjmij Panie, nasze dary,\nktóre tutaj przynosimy.\nPrzyjmij Panie, nas samych,\ngdy się tutaj gromadzimy.\n\n" +
                "2. Przynosimy chleb i wino,\nby się Ciałem i Krwią stały.\nWejrzyj, Panie, w nasze serca,\nulecz wszystkie nasze rany.\n\n" +
                "3. Przyjmij Panie, naszą słabość,\nnatchnij mocą Swego Ducha.\nOdnów, Panie, w nas miłość,\nniechaj zawsze Cię kochamy.\n\n",
        "Ref. Przymnóż nam wiary, przymnóż nam wiary\nPrzymnóż nam wiary Panie nasz. /x2\n\n" +
                "1. Ojcze Przyjacielu, wierzymy Ci\nOjcze Miłosierny, wierzymy Ci\nBoże Ojcze w Trójcy, wierzymy Ci\nOjcze Wszechmogący, wierzymy Ci\n\n" +
                "2. Jezu, Synu Boży, wierzymy Ci\nZbawicielu świata, wierzymy Ci\nŻywe Słowo Boga, wierzymy Ci\nJezu, Synu Maryi, wierzymy Ci\n\n" +
                "3. Duchu Święty, Boże, wierzymy Ci\nDuchu, życie wieczne, wierzymy Ci\nSłodki Gościu duszy, wierzymy Ci\nDuchu Miłości, wierzymy Ci\n\n" +
                "Bridge. Wierzę w Boga Ojca,\nwierzę w Jezusa Chrystusa,\nwierzę w Ducha Świętego,\nw święty Kościół.\n" +
                "W świętych obcowanie,\ngrzechów odpuszczenie,\nciała zmartwychwstanie,\nżywot wieczny. Amen.\n\n" +
                "Ref. (po modulacji)\nPrzymnóż nam wiary, przymnóż nam wiary\nPrzymnóż nam wiary Panie nasz. /x2\n\n",
        "Przymnóż nam wiary. /x3\n\n",
        "Przystąpmy, chwalmy Go! Oto Syn Boży, miłości dar!\nOn życie Swoje dał nam, bo umiłował nas!\n" +
                "Wznosimy chwały pieśń, jako ofiarę składamy Ci,\nbo radość, której nie da nikt, Ty Królu dajesz nam!\n\n" +
                "Ref. Przystąpmy chwalmy Go, sławmy Go,\nbo On Królem jest!\nPieśnią chwały uwielbiajmy Go! /x2\n\n",
        "Przytul mnie do Swoich ran,\nz których płynie miłość i to wystarczy.\nTylko mocno przytul mnie,\nNie musisz już zmieniać nic,\nMój ból miesza się z Twym bólem.\n\n" +
                "Ref. Tylko mocno przytul mnie,\nTylko mocno kochaj mnie.\n\n",
        "Przytul mnie, Jezu, tak mocno do siebie,\nukryj głęboko na dnie Twego serca! /x2\nPrzytul mnie!\n\n",
        "1. Przywołaj mnie, Panie, przywołaj mnie Panie\nna pustynię.\nI niech mój głód Ciebie i moja tęsknota\nPrzywoła Ciebie.\n\n" +
                "Ref. Mów do mego serca, mów do mego serca,\nTy znasz Jego ukryte doliny.\nTam gdzie usychają piękne winnice,\nTy wiesz, gdzie otworzyć ukryte źródła.\n\n" +
                "2. Niech we mnie umilkną pragnienia\ni myśli, abyś mógł mówić Ty.\nNiech słowa miłości i przebaczenia\nprzenikną duszę mą.\n\n",
        "Ref. Czym się Panu odpłacę,\nza wszystko, co mi wyświadczył.\nPodniosę kielich zbawienia i\nwezwę imienia Pana.\n\n" +
                "1. Miłuję Pana albowiem usłyszał\ngłos mego błagania.\nBo skłonił ku mnie swe ucho,\nw dniu, w którym wołałem.\n\n" +
                "2. Oplotły mnie więzy śmierci †\ndosięgły mnie pęta Otchłani,*\nogarnął mnie strach i udręka.\nAle wezwałem imienia Pana*\nPanie, ratuj moje życie.\n\n" +
                "3. Pan jest łaskawy i sprawiedliwy *\nBóg nasz jest miłosierny.\nPan strzeże ludzi prostego serca,*\nbyłem w niedoli, a On mnie wybawił.\n\n" +
                "4. Wróć, duszo moja, do swego spokoju,*\nbo Pan dobro ci wyświadczył.\nUchronił bowiem moją duszę od śmierci,*\noczy od łez, nogi od upadku.\n\n" +
                "5. Ufność miałem wtedy, gdy mówiłem,*\njestem w wielkim ucisku.\nI zalękniony wołałem,*\nkażdy człowiek jest kłamcą.\n\n" +
                "6. Czym się Panu odpłacę,*\nza wszystko co mi wyświadczył.\nPodniosę kielich zbawienia*\ni wezwę imienia Pana.\n\n" +
                "7. Panie, jestem Twoim sługą,*\nTwym sługą, synem Twojej służebnicy.\nTyś rozerwał moje kajdany †\nTobie złożę ofiarę pochwalną*\ni wezwę imienia Pana.\n\n" +
                "8. Wypełnię me śluby dla Pana*\nprzed całym jego ludem.\nW dziedzińcach Pańskiego domu*\nw pośrodku ciebie, Jeruzalem.\n\n" +
                "9. Wypełnię me śluby dla Pana*\nprzed całym Jego ludem.\nCenna jest w oczach Pana*\nśmierć Jego wyznawców.\n\n",
        "1. Gdy Pan odmienił los Syjonu,\nwydawało się nam, że śnimy!\nUsta nasze były pełne śmiechu,\na język śpiewał z radości!\n\n" +
                "Ref. Mówiono wtedy między narodami:\n„Wielkie rzeczy im Pan uczynił!”\nPan uczynił nam wielkie rzeczy\ni radość nas ogarnęła!\n\n" +
                "2. Ci, którzy we łzach sieją,\nżąć będą w radości;\nchoć idą i płaczą, niosąc ziarno na zasiew,\npowrócą z radością, niosąc swoje snopy.\n\n" +
                "Ref. Powiedzą wtedy…\n\nOoo… /x2\n\n" +
                "Bridge: Odmień, Panie, nasz los!\nZamień suche doliny w strumienie rwące!\nOdmień, Panie, nasz los!\nNiech nawiedzi nas z wysoka wschodzące Słońce!\n" +
                "Odmień, Panie, nasz los!\nLament zamień w taniec! Rozpal znów, co gasnące!\nOdmień, Panie, nasz los!\nW Twoich rękach nasz los – odmień go!\n\n" +
                "Ref. A zawsze mówić będą między narodami:\n„Wielkie rzeczy im Pan uczynił!”\nPan uczynił nam wielkie rzeczy\ni radość nas ogarnęła!\n\n" +
                "Niech mówią przeto między narodami:\n„Wielkie rzeczy im Pan uczynił!”\nPan uczynił nam wielkie rzeczy\ni radość nas ogarnęła!\n\n",
        "1. Miałem Pana zawsze,\nprzed oczami swymi zawsze miałem Pana.\nGdyż On stoi po mojej prawicy, abym się nie zachwiał.\n" +
                "I dlatego cieszy się, cieszy się me serce i raduje język.\nTakże ciało moje\nspoczywa w nadziei, spoczywa w nadziei.\n\n" +
                "2. Że nie zostawisz\nmej duszy w otchłani, Panie, nie zostawisz.\nAni nie dasz świętemu Twemu ulec skażeniu.\n" +
                "Dajesz mi poznać wszystkie drogi życia, dajesz mi poznać.\nI napełniasz mnie radością\nswoją przed obliczem Twoim.\n\n",
        "Ref. Jahwe, ja wiem, jesteś tu,\nprzy mym boku dzień po dniu.\nOchraniasz mnie od zła\ni prowadzisz mnie drogą odwieczną.\n\n" +
                "1. Ty przenikasz mnie Panie i znasz,\nczy siedzę, czy stoję, zawsze wiesz.\n" +
                "Twoja dłoń jest nade mną, odsuwa śmierci cień,\nchroni mnie przed złem.\n\n" +
                "2. Gdzie przed Twoją miłością uciec mam,\ngdybym wstąpił do nieba będziesz tam.\n" +
                "Czy na skrzydłach jutrzenki, czy na krańcu mórz,\ntam bym znalazł Cię.\n\n" +
                "3. To Ty sam utworzyłeś mnie,\nmoje serce i myśli wszystkie znasz.\nZanim słońce ujrzałem,\nTy utkałeś mnie w łonie matki mej.\n\n" +
                "4. Jak wspaniałe są dzieła Twoich rąk.\nJak głębokie są, Panie, myśli Twe.\n" +
                "Gdybym wszystkie policzył, a więcej ich niż gwiazd,\nznów bym znalazł Cię.\n\n",
        "1. Panie moje serce się nie pyszni,\ni oczy moje nie są wyniosłe.\nNie gonię za tym, co jest wielkie,\nani co, przerasta me siły.\n\n" +
                "Ref. Złóż w Panu całą nadzieję,\nodtąd już, aż na wieki. /2x\n\n" +
                "2. Przeciwnie wprowadziłem ład\ni spokój do mojej duszy.\nJak niemowlę u swej matki,\ntak we mnie jest moja dusza\n" +
                "Odtąd już /x3\naż na wieki…\n\n",
        "1. Posłuchaj ludu słowa, które daję ci,\nAbyś zrozumiał, że nadzieja w Panu jest,\nAbyś wierny był, gdy przyjdą ciemności.\n" +
                "Bo On uczynił cud, gdyś przez pustynię szedł,\nOtworzył morze potem obłok ognia dał,\nOn wydobył ze skały strumienie.\n\n" +
                "Ref. Pustynia i ciemna noc jest łaską.\nPustynia i ciemna noc jest łaską.\nJest łaską.\n\n" +
                "2. Lecz ty szemrałeś i nie uwierzyłeś Mu,\nCzy Bóg potrafi na pustyni nakryć stół,\nChleb z nieba nie przekonał ciebie.\n" +
                "Gdy cię zabijał, zaczynałeś szukać Go,\nW ciemności zrozumiałeś, że On skałą jest.\nPrzestań wątpić, Bóg umarł z miłości.\n\n",
        "1. Radość tchnij w serce me, bym Cię chwalił.\nRadość tchnij, proszę, w serce me.\nRadość tchnij w serce me, bym Cię chwalił,\nbym Cię zawsze chwalił, Panie mój.\n\n" +
                "Ref. Śpiewaj Hosanna, śpiewaj Hosanna,\nśpiewaj Panu, który Królem jest.\nŚpiewaj Hosanna, śpiewaj Hosanna,\nśpiewaj Panu cały dzień.\n\n" +
                "2. Pokój tchnij w serce me, bym odpoczął.\nPokój tchnij proszę w serce me.\nPokój tchnij w serce me, bym odpoczął,\nbym w spoczynku także chwalił Cię.\n\n" +
                "3. Miłość tchnij w serce me, abym służył.\nMiłość tchnij proszę w serce me.\nMiłość tchnij w serce me, abym służył.\nbym swą służbą także chwalił Cię.\n\n" +
                "4. Wiarę tchnij w serce me, bym się modlił.\nWiarę tchnij proszę w serce me.\nWiarę tchnij w serce me, bym się modlił,\nbym modlitwą także chwalił Cię.\n\n",
        "Ref. Radośnie Panu hymn śpiewajmy, Alleluja.\nI dobroć Jego wysławiajmy, Alleluja.\n\n" +
                "1. Wychwalajcie ze mną dzieła Bożej mocy, Alleluja.\nWychwalajcie Pana nad wszystko na wieki, Alleluja.\n\n" +
                "2. Wychwalajcie Pana Aniołowie Pańscy,\nWychwalajcie Pana Zastępy niebieskie,\n\n" +
                "3. Wychwalajcie Pana słońce i księżycu,\nWychwalajcie Pana wszystkie gwiazdy nieba,\n\n" +
                "4. Wychwalajcie Pana cztery pory roku,\nWychwalajcie Pana pogody i słoty,\n\n" +
                "5. Wychwalajcie Pana rzeki, oceany,\nWychwalajcie Pana pagórki i góry,\n\n" +
                "6. Wychwalajcie Pana rośliny, zwierzęta,\nNiech wychwala Pana cały rodzaj ludzki,\n\n" +
                "7. Wychwalajcie Pana ludzie sprawiedliwi,\nWychwalajcie Pana pokorni i święci,\n\n" +
                "8. Wychwalajcie Pana kapłani Kościoła,\nNiech cały lud Boży sławi Jego dobroć,\n\n" +
                "9. Wychwalajmy Ojca wraz z Synem i Duchem,\nWychwalajmy Pana Stwórcę nieba, ziemi,\n\n" +
                "10. Wychwalajmy Boga, który rządzi światem,\nWychwalajmy Boga nad wszystko na wieki.\n\n",
        "Ref. Ratuj, Panie, i powstrzymaj burzę,\nNie daj mi zginąć pośród fal ogromnych.\nObudź się, bo już tracę siły,\nRatuj mnie, podaj swoją rękę.\n\n" +
                "1. Dlaczego zwątpiłeś? Jakże ci brak wiary.\nOdwagi! Ja jestem, nie bój się\nUwierz tylko, ufaj swemu Bogu,\nBo jeszcze wysławiać Go będziesz.\n\n" +
                "2. Aniołowie moi na rękach cię noszą,\nByś swej stopy nie uraził o kamień.\nCzuwam nad twym życiem dniem i nocą,\nJestem cieniem przy twoim boku.\n\n",
        "Chwalmy Pana w rytmie reggae, Alleluja. /x2\nProwadź nas Boże, prowadź nas,\nprowadź przez życie całe (hej!).\n\n",
        "W krąg, przez cały świat, Duch mocą swą wieje.\nW krąg, przez cały świat, jak rzekł prorok,\ntak dzieje się.\n" +
                "W krąg, przez cały świat, kroczy potężna rewolucja.\nBoża chwała wznosi się tak, jak szum potężnych wód.\nI w sercu mym też Duch mocą swą wieje.\n" +
                "I w sercu mym też, jak rzekł.\nCzy w sercu twym też Duch mocą wieje?\nCzy w sercu twym też, jak rzekł?\n\n",
        "Ref. Rozpięty na ramionach, jak sokół na niebie.\nChrystusie, Synu Boga, spójrz, proszę, na ziemię.\n\n" +
                "1. Na ruchliwe ulice, zabieganych ludzi,\ngdy noc się już kończy, a ranek się budzi.\n" +
                "Uśmiechnij się przyjaźnie, z wysokiego krzyża,\ndo ciężko pracujących, których głód poniża.\n\n" +
                "2. Pociesz zrozpaczonych, zrozum głodujących,\nmodlących się wysłuchaj, wybacz umierającym.\n" +
                "Spójrz cierpienia, sokole, na wszechświat, na ziemię,\nna cichy ciemny kościół, dziecko wzywające Ciebie.\n\n" +
                "3. A gdy będziesz nas sądził, Boskie Miłosierdzie,\nprosimy, Twoje dzieci, nie sądź na miarę siebie.\n\n",
        "Ruah, Ruah, Ruah.\nNie siłą, nie mocą naszą, lecz mocą Ducha Świętego.\n\n",
        "Ruah, Ruah,\nRuah, Ruah\n\nRef. Ruah, Ruah,\nRuah, Ruah.\n\n" +
                "1. Ożywcze tchnienie Ojca - do nas przyjdź\nDawco darów mnogich - do nas przyjdź\n" +
                "Ukojenie naszych serc - do nas przyjdź\nTy jesteś Oblubieńcem naszych dusz\nwielbimy Ciebie\n\n" +
                "2. Duchu przybrania za synów - do nas przyjdź\nUosobiona miłości - do nas przyjdź\n" +
                "Oczyszczenie naszych serc - do nas przyjdź\nTy jesteś Oblubieńcem naszych dusz\nwielbimy Ciebie\n\n" +
                "Duchu Święty, Duchu Święty przyjdź\nDuchu Święty, Duchu Święty przyjdź\nDuchu Święty, Duchu Święty jesteś\nDuchu Święty, Duchu Święty jesteś – Ruah\n\n",
        "Ref. Ruszaj, ruszaj, ruszaj tam,\ngdzie Ziemię Obiecaną daje ci Pan.\nRuszaj, ruszaj, ruszaj tam,\ngdzie Ziemia Obiecana jest.\n\n" +
                "1. Wyruszył kiedyś tam\nAbram ze swego Ur Chaldejskiego,\nbo wierzył, bo wierzył, bo wierzył, że\nPan powiedział tak\ni usłyszał:\n\n" +
                "2. Już czekasz tyle lat, by raz chociaż przeżyć wraz z Bogiem chwilę.\n" +
                "Czy wierzysz, czy wierzysz, czy wierzysz, że będzie taki czas\ni usłyszysz:\n\n" +
                "3. I wątpisz w życia sens, masz dość świata, ludzi, samego siebie.\n" +
                "Czy wierzysz, czy wierzysz, czy wierzysz, że będzie taki czas\ni usłyszysz:\n\n" +
                "4. Więc szukaj Boga wciąż, niech On będzie szansą dla ciebie wielką\n" +
                "i uwierz, i uwierz, i uwierz, że będzie taki czas,\ngdy usłyszysz:\n\n",
        "1. Dobro wypełnia tej rzeki bieg\nKażdy mój smutek w jej źródle topi się\nOcean łaski –głębszy niż strach\nNiech się rozlewa, rośnie\n\n" +
                "2. W środku tej rzeki moc objawia się\n" +
                "Z Bożego serca wciąż wylewa się\nNiebieska łaska na nas spływa w dół\nNiech się rozlewa, rośnie\n" +
                "Wzbierają wody, wzbierają rzeki\nZdrój wody Twej wylewa się /x2\n\n" +
                "Ref. Ta rzeka daje nam życie /x4\n\n" +
                "Bridge. Otwieraj więźniom drzwi. Wypuszczaj wolno ich.\nNiech tryska moc! /x2 Niech we mnie budzi się.\n" +
                "Nic nie zatrzyma mnie. Z radości tańczyć chcę.\nNiech tryska moc! /x2 Niech we mnie budzi się!\n\n",
        "Rzekł Pan: „Stało się, Jam Alfa i Omega,\nPoczątek i Koniec.\nJa łaknącemu dam darmo pić\n" +
                "ze źródła wody życia”.\nPana daje mi pić, /x3\nze źródła wody życia. Ty dajesz mi pić.\n\n",
        "1. Teraz widzę, że jesteś inny.\nJesteś inny, niż zawsze myślałem.\nJesteś Bogiem o miękkim sercu,\nWszechmogącym, lecz słabym z Miłości.\n\n" +
                "Ref. Dlatego weź mnie i rzuć mnie w morze\nDlatego weź mnie i rzuć w ich morze.\n\n" +
                "2. Chcę być dla nich światłem w ciemności,\nChcę zanosić Twoją obecność.\nPragnę zostać ich przyjacielem,\nPrzyjacielem grzeszników, celników.\n\n" +
                "3. Już nie będę lękać się ich śmierci,\nGwałtu, kłamstwa, zabójstwa, chciwości.\nPragnę Ciebie wśród nich uobecniać,\nNie zamilknę, lecz będę wśród nich.\n\n" +
                "4. Proszę, wylej miłość miłosierną,\nCzułość, która dotknie ich serc.\nWysłuchaj ich przeze mnie, wysłuchaj,\nNiechaj Ciebie we mnie dotkną się.\n\n" +
                "5. Teraz widzę, że jesteś inny,\nJesteś inny, niż zawsze myślałem.\nJesteś Bogiem o miękkim sercu,\nWszechmogącym, lecz słabym z Miłości.\n\n",
        "Ref. Witaj Królowo, nasza Pani.\nWitaj Maryjo, Matko Miłosierdzia, witaj.\n\n" +
                "1. Życie, słodyczy i nasza nadziejo,\ndo Ciebie wołamy wygnańcy, synowie Ewy.\n\n" +
                "2. Do Ciebie wołamy jęcząc i płacząc\nna tym łez padole.\nTy, która orędujesz za nami zwróć\n" +
                "na swe dzieci oczy Swoje, zawsze\npełne miłosierdzia.\n\n" +
                "3. I po tym wygnaniu okaż nam Jezusa,\nbłogosławiony owoc Twojego Żywota.\nMatko łaskawa i litościwa, o słodka Panno Maryjo!\n\n",
        "1. Małe ludzkie serce mam,\npełne pragnień uśpionych od lat.\nJego bicie budzi mnie,\ngdy nie wierzę, że zamknę w nim świat.\n\n" +
                "Ref. Biorę mą bezradność\nw swoje dłonie,\nprzed Twe Boskie serce kładę ją.\nChcę być bratem\n" +
                "Twoich wszystkich stworzeń,\npomnóż moją miłość,\nby kochała mocą Twą.\n\n" +
                "2. Chcę mym sercem objąć świat,\nzniszczyć w nim przyjaźnią resztki zła.\nSpójrzcie w moje oczy, czy\nwidać w nich Franciszka, wiary skarb.\n\n",
        "1. Schowaj mnie pod skrzydła swe,\nukryj mnie w silnej dłoni swej.\n\n" +
                "Ref. Kiedy fale mórz chcą porwać mnie,\nz Tobą wzniosę się, podniesiesz mnie!\nPanie, Królem Tyś spienionych wód,\nJa ufam Ci, Ty jesteś Bóg!\n\n" +
                "2. Odpocznę dziś w ramionach Twych\nDusza ma w Tobie będzie trwać.\n\n",
        "1. Przybądź Duchu Święty,\nspuść z niebiosów wzięty\nświatła Twego strumień.\nPrzyjdź Ojcze ubogich,\ndawco darów mnogich,\nprzyjdź Światłości sumień!\n\n" +
                "2. O najmilszy z gości,\nsłodka serc radości,\nsłodkie orzeźwienie.\nW pracy Tyś ochłodą,\nw skwarze żywą wodą,\nw płaczu utuleniem.\n\n" +
                "3. Światłości najświętsza,\nserc wierzących wnętrza,\npoddaj swej potędze.\nBez Twojego tchnienia,\ncóż jest wśród stworzenia,\njeno cierń i nędze.\n\n" +
                "4. Obmyj, co nie święte,\noschłym wlej zachętę,\nulecz serca ranę.\nNagnij, co jest harde,\nrozgrzej serca twarde,\nprowadź zabłąkane.\n\n" +
                "5. Daj Twoim wierzącym,\nTobie ufającym,\nsiedmiorakie dary.\nDaj zasługę męstwa,\ndaj wieniec zwycięstwa,\ndaj szczęście bez miary.\n\n",
        "1. Serca nasze pełne Boga,\nusta pełne Jego chwały.\nBóg sam siebie dał na pokarm,\nw Eucharystii cały.\n\n" +
                "Ref. Bóg jest naszej drogi celem,\nBóg jest naszym zmartwychwstaniem.\nNaszym Przyjacielem drogim,\nnaszym zaufaniem.\n\n" +
                "2. Połamany chleb pokoju,\nTwym mieszkaniem, Panie, jestem.\nTy przygarniasz mnie do siebie,\nswoim Boskim gestem.\n\n" +
                "3. Tajemnicą mnie przenikasz\ni okrywasz swoim cieniem.\nZ głębi nocy błysk nadziei\ntli się Twym Imieniem.\n\n",
        "Ref. Serce Boga, otwarte dla nas,\nSerce Boga, przebite na Krzyżu.\nTylko Ty jesteś Bramą,\nTylko Ty dajesz mi życie /x2\n\n" +
                "1. Wprowadzasz mnie do Twego wnętrza,\nDajesz żyć Twoim życiem.\nOddycham Twoim oddechem,\nŻyję serca Twego biciem.\n\n" +
                "2. Tu, w ciszy oddycham pokojem,\nTwój Duch mnie otula miłością.\nTo Ty jesteś życie moje,\nTo w Tobie żyję miłością.\n\n",
        "Ref. Serce wielkie nam daj, zdolne objąć świat.\nPanie, serce nam daj, mężne w walce ze złem.\n\n" +
                "1. Zwleczmy z siebie uczynki starych ludzi,\nzniszczmy wszystko, co budzi Boży gniew.\n" +
                "Wdziejmy biel nowych szat w Chrystusie Panu,\nNowy człowiek powstanie w każdym z nas.\n\n" +
                "2. Nowi ludzie w historię wpiszą miłość,\nwskażą drogi odnowy ludzkich serc.\n" +
                "Nowi ludzie przeżyją własne życie,\ntworząc wspólnym wysiłkiem nowy świat.\n\n" +
                "3. Nowi ludzie przyniosą ziemi pokój,\nw znaku wiary jednocząc cały świat.\n" +
                "Nowi ludzie przyniosą ziemi wolność,\nprawda ludzi wyzwoli, niszcząc zło.\n\n",
        "1. Siostro ma, Oblubienico,\noczarowałaś me serce spojrzeniem,\njedynym patrzeniem Twych oczu,\npaciorkiem swych naszyjników - Miłość Twa.\n\n" +
                "2. Siostro ma, Oblubienico,\nileż słodsza Twa miłość od wina.\nJesteś ogrodem zamkniętym,\nźródłem zapieczętowanym - Miłość Twa.\n\n" +
                "Ref. Wejdźcie do mego ogrodu, pijcie wino młode.\nJedzcie i pijcie najdrożsi, spożywajcie plastry miodu.\nCicho! Mój Miły nadchodzi, /x2\notwórz mu swoje serce.\n\n" +
                "3. Powstań wietrze północny,\nnadleć wichrze wiejący z południa.\nWiej poprzez mój ogród,\nniech popłyną jego wonności –\nMiłość Twa.\n\n" +
                "4. Wejdź do mego ogrodu,\nmiły mój, umiłowany.\nDla Ciebie jego owoce,\ndla Ciebie kadzidła, balsamy - Miłość Twa.\n\n",
        "Ref. Skoro Panie dałeś mi ten lud,\nKtóry ma gorące serce, twardy kark,\nProwadź mnie, bym potrafił go paść,\nPrzez mą słabość objaw swoją moc.\n\n" +
                "1. Daj mi braci, którzy podniosą moje ręce,\nSwoją ofiarą wspomogą mego ducha,\n" +
                "Bym wpatrywał się w niebo oczekując,\naż zwyciężysz w nas.\nBrak miłości, brak jedności - wszelki grzech.\n\n" +
                "2. Daj z odwagą spoglądać w naszą przyszłość,\nBez zwątpienia wsłuchiwać się w Twe serce,\n" +
                "Bym z radością oglądał Twe zwycięstwo w naszych sercach\nPoprzez wiarę bym oglądał to, co chcesz nam dać.\n\n" +
                "3. Moje serce uczyń szerokim na mych braci,\nKtórych rana jest zbyt wielka i boląca,\n" +
                "Niech me serce będzie czułe, delikatne moje czyny,\nBym nie sprawiał im większego bólu, lecz koił go.\n\n" +
                "4. Boski Lekarzu w którym mam nadzieję,\nUlecz rany zadane przez mój grzech,\n" +
                "Niechaj Duch twój sprawi, byśmy żyli znów w jedności,\nBy zakwitła w nas Twa miłość - Panie, proszę Cię!\n\n",
        "Ref. Skosztujcie i zobaczcie, jak dobry jest Pan!\n\n" +
                "1. Będę Panu w każdej porze\nśpiewał pieśń wdzięczności.\nNa mych ustach chwała Jego\nnieustannie gości.\n\n" +
                "2. W Panu cała chluba moja,\ncieszcie się pokorni.\nWspólnie ze mną chwalcie Pana,\nsławmy Imię Jego.\n\n" +
                "3. Kiedym tęsknie szukał Pana,\nraczył mnie wysłuchać.\nI ze wszelkiej trwogi mojej\nraczył mnie wyzwolić.\n\n" +
                "4. Cieszcie się widokiem Jego,\nOn was nie zawiedzie.\nPan usłyszał głos biednego,\nwyrwał go z ucisków.\n\n" +
                "5. Tych co Pana się lękają,\nanioł Pański broni.\nBy ich wyrwać z ręki wroga\nSzańcem ich osłoni\n\n" +
                "6. Więc skosztujcie i zobaczcie,\njaki Pan jest dobry.\nKto do Niego się ucieknie,\nten błogosławiony.\n\n" +
                "7. Z czcią i lękiem służcie Panu,\nŚwięty Ludu Boży.\nBo nie znają niedostatku\nludzie bogobojni.\n\n" +
                "8. W nędzy znajdą się bogacze,\nbędą łaknąć chleba.\nCi zaś, co szukają Pana,\nobfitować będą.\n\n",
        "Ref. Skosztujcie i zobaczcie, jak dobry jest Pan,\nskosztujcie i zobaczcie, jak dobry jest Pan.\n\n" +
                "1. Będę błogosławił Pana po wieczne czasy,\nJego chwała będzie zawsze na moich ustach.\n" +
                "Dusza moja chlubi się Panem,\nniech słyszą to pokorni i niech się weselą.\n\n" +
                "2. Uwielbiajcie razem ze mną Pana,\nwspólnie wywyższajmy Jego Imię.\n" +
                "Szukałem pomocy u Pana, a On mnie wysłuchał\ni uwolnił od wszelkiej trwogi.\n\n" +
                "3. Spójrzcie na Niego, a rozpromienicie się radością,\noblicza wasze nie zapłoną wstydem.\n" +
                "Oto biedak zawołał i Pan go usłyszał\ni wybawił ze wszystkich ucisków.\n\n" +
                "4. Anioł Pana otacza szańcem bogobojnych,\naby ocalić tych, którzy w Niego wierzą.\n" +
                "Skosztujcie i zobaczcie, jak Pan jest dobry,\nszczęśliwy człowiek, który się do Niego ucieka.\n\n" +
                "5. Bójcie się Pana wszyscy Jego święci,\ngdyż bogobojni nie zaznają biedy.\n" +
                "Bogacze zubożeli i zaznali głodu,\nszukającym Pana niczego nie zabraknie.\n\n" +
                "6. Pan słyszy wołających o pomoc\ni ratuje ich od wszelkiej udręki.\n" +
                "Pan jest blisko ludzi skruszonych w sercu\ni wybawia złamanych na duchu.\n\n" +
                "7. Wiele nieszczęść spada na sprawiedliwego,\nlecz ze wszystkich Pan go wybawia.\n" +
                "On czuwa nad każdą jego kością,\nani jedna z nich nie zostanie złamana.\n\n",
        "1. Sławię Cię, sławię Cię, zawsze chcę przy Tobie być.\nDziękować Ci pragnę, bo właśnie Ty kochasz mnie.\n\n" +
                "Ref. Wyrwałeś mnie ze śmierci, odmieniłeś życieme.\nZmazałeś moje winy, przebaczyłeś mi mój grzech.\n" +
                "Wyrwałeś mnie ze śmierci, odmieniłeś życie me.\nOd dzisiaj, już na zawsze, będę śpiewać Tobie pieśń.\n\n" +
                "2. Kocham Cię, kocham Cię, przyjmij dziś miłość mą.\nRaduję się w Tobie, bo właśnie Ty kochasz mnie.\n\n",
        "Ref. Sławmy Pana, wzywajmy Jego Imienia,\nbo On Bogiem jest.\n\n" +
                "1. Śpiewajmy Mu i z serca grajmy Panu,\nrozpowiadajmy co uczynił nam.\nSzczyćmy się więc Jego Imieniem,\nszukajmy Go - On żyje pośród nas.\n\n" +
                "2. Rozmyślam wciąż o Panu, Jego dziełach,\nwe wszystkim szukam Jego Oblicza.\nPamiętam też o cudach, które zdziałał,\no Jego znakach, sądach Jego ust.\n\n" +
                "3. On Jahwe jest, On Bogiem naszym jest,\nza Jego sprawą wszystko stało się.\nNa wieki pamięta o przymierzu,\no obietnicy dla potomstwa Abrahama.\n\n" +
                "4. Ustanowił dla nas swoje prawo,\nwziął w opiekę nieboraka Izraela.\nNie pozwolił nas uciskać, bronił nas,\ngdy byliśmy słabi - wspomógł mocą swą.\n\n" +
                "5. Bóg posłał wtedy sługę Mojżesza,\nAarona posłał razem z nim.\nSwoją mocą potwierdzał ich słowa,\npoprzez plagi uwolnił wreszcie nas.\n\n" +
                "6. Dał nam chmurę, która nas chroniła,\nogień, aby pośród nocy świecił nam.\nDał nam mięsa, dał nam chleb z nieba,\nrozdarł skałę, wypłynęła woda z niej.\n\n" +
                "7. Przez Józefa uratował Pan swój lud,\nposłał Słowo, które dało mu świadectwo.\nUstanowił go nad całym swoim ludem,\nWładcą nad posiadłością swą.\n\n" +
                "8. Według myśli swej pouczał dostojników,\nstarszyznę ludu uczył zaś mądrości.\nRodzinę swą przywołał do Egiptu,\nPan rozmnożył lud - nienawidzili nas.\n\n" +
                "9. Tak wypełnił Pan swoje obietnice,\nw swoim Synu przymierze nowe dał.\nZ Jego serca popłynęła woda życia,\nCiało Swe na pokarm pozostawił nam.\n\n" +
                "10. Śpiewajmy Mu i z serca grajmy Panu,\nrozpowiadajmy co uczynił nam.\nSzczyćmy się więc Jego Imieniem,\nszukajmy Go - On żyje pośród nas.\n\n",
        "Słowa Jego są słodyczą\ni cały pełen jest powabu.\nTaki jest miły mój, oto przyjaciel mój,\nCórki Jeruzalem!\n\n",
        "Ref. Słowa, Słowa Twych ust\nSą pokarmem dla duszy mej!\n\n" +
                "1. Słowo Pana jest niezmienne jak niebiosa\n– jest pokarmem dla duszy mej.\nSłowo Pana, które trwa na wieki – jest…\n" +
                "Słowo Pana wypróbowane jest w ogniu – jest…\nSłowo Pana jest nieodwołalne i sprawiedliwe – jest..\n\n" +
                "2. Słowo Pana, to jest miecz Ducha -\nSłowo Pana jest żywe i skuteczne -\nSłowo Pana jest jak miecz obosieczny -\nSłowo Pana jest jak młot kruszący skałę -\n\n" +
                "3. Poprzez Słowo Twoje daj mi zrozumienie -\nPoprzez Słowo Twoje udziel mi życia -\nWypełnij na ziemi Swoje Słowo -\nW Bogu uwielbiajmy Jego Słowo! -\n\n" +
                "4. Słowo Pana jest bardzo blisko ciebie –\nW Twoich ustach, sercu abyś je wypełnił –\nSłowo Pana jest dla serca ukojeniem –\nSłowo Pana pokrzepia strudzonego –\n\n",
        "Słowo Twoje jest lampą dla mych stóp\ni światłem drogi mej. /x2\nGorejącym ogniem jest\nwe mnie, Panie, Słowo Twe,\n" +
                "ogień, który mnie trawi.\nBędę sławić Imię Twe,\nwśród narodów wielbić Cię\ni obwieszczać Słowo Twe.\n\n",
        "Słuchaj, Izraelu, tylko Jahwe jest Bogiem twym.\nKochaj Pana Boga swego całym swym sercem.\nKochaj Pana Boga swego całą swą duszą.\n" +
                "Kochaj Pana Boga swego całym umysłem.\nKochaj Pana Boga swego całym swym życiem.\n\n",
        "Ref. Słuchaj, Izraelu, Pan jest naszym Bogiem,\nPan jest naszym Bogiem, Panem jedynym.\n\n" +
                "1. Będziesz więc miłował\nPana, Boga twojego,\nz całego swego serca, z całej duszy swojej,\nze wszystkich swych sił.\n\n" +
                "2. Pozostaną w twym sercu te słowa, które Ja ci dziś nakazuję,\nwpoisz je twoim synom, będziesz o nich mówił\n" +
                "przebywając w domu, w czasie podróży,\nkładąc się spać i wstając ze snu.\n\n" +
                "3. Przywiążesz je do twojej ręki jako znak.\nOne ci będą ozdobą między oczami.\nWypisz je na odrzwiach swojego domu\ni na twoich bramach.\n\n",
        "Słyszę już armii Pana głos.\nSłyszę już armii Pana głos.\nSłyszę armii głos, słyszę Pana głos.\nTo idzie armia Pana /x3,\nuwielbiając Go.\n\n",
        "Spocznij jedynie w Bogu, duszo moja.\nOd niego moja nadzieja.\nPrzed nim serce me otworzę.\nOn usłyszy mnie.\n\n",
        "Spocznij na nas Duchu Pana,\nDuchu mądrości i rozumu.\nDuchu poznania i bojaźni Bożej,\nDuchu miłości i mocy.\n\n",
        "Ref. Sprawiedliwi radośnie, wołajcie na cześć Pana.\nGrajcie Mu na harfie o dziesięciu strunach.\n\n" +
                "1. Sławcie Pana na cytrze, na harfie.\nGrajcie na harfie o dziesięciu strunach.\n" +
                "Śpiewajcie Panu z radością pieśń nową.\nPełnym głosem śpiewajcie mu wdzięcznie.\n\n" +
                "2. Bo każde słowo Pana jest prawem,\na jego dzieło godne zaufania.\nOn prawo i sprawiedliwość miłuje,\nziemia jest pełna jego łaski.\n\n" +
                "3. Pan udaremnia zamiary narodów,\nw niwecz obraca ludów zamysły.\nZamiary Pana trwają na wieki.\nZamysły Jego przez pokolenia.\n\n" +
                "4. Dusza nasza oczekuje Pana,\nOn jest naszą pomocą i tarczą.\nRaduje się w nim nasze serce.\nUfamy jego świętemu imieniu.\n\n",
        "Stańmy radośnie i mówmy każdemu,\nże Jezus Królem jest.\nStańmy radośnie i mówmy każdemu,\nże Jezus Królem jest.\n" +
                "Stańmy radośnie i mówmy każdemu,\nże Jezus Królem jest.\nŻe Jezus Chrystus Królem królów jest.\nOn Królem jest.\n\n",
        "Stoję dziś, moje serce, ręce tak podnoszę\nw podziwie do Tego, który wszystko dał.\n" +
                "Stoję dziś, moją duszę, Panie, Tobie składam.\nJa chcę być jak Ty.\n\n",
        "1. Stwórca wszystkich rzeczy, Malarz firmamentu,\nTo, co najcenniejsze Panie dałeś mi.\n" +
                "Chór Zastępów śpiewa o tym, jak wielki jesteś Ty.\nMiłość Twa wypełnia całą duszę mą.\n\n" +
                "Ref. Panie mój, tylko Ciebie wielbić chcę.\nTwa miłość całkiem odmieniła mnie.\n" +
                "Panie mój, tylko Ciebie wielbić chcę\nza to, kim naprawdę jesteś Ty.\n\n" +
                "2. Stwórca wszystkich rzeczy, Malarz firmamentu,\nprzyjdzie dzień, gdy ujrzę Ciebie twarzą w twarz.\n" +
                "Od dnia, gdy Cię spotkałem, gdy poznałem miłość Twą,\nPanie, moje serce coraz bliżej Ciebie jest.\n\n",
        "Ref. Stwórz, Boże, we mnie serce czyste\ni odnów we mnie moc ducha.\n\n" +
                "1. Zmiłuj się nade mną, Boże, w łaskawości swojej,\nw ogromie swej litości zgładź nieprawość moją.\n" +
                "Obmyj mnie zupełnie z mojej winy\ni oczyść mnie z grzechu mojego.\n\n" +
                "2. Uznaję bowiem nieprawość moją,\na grzech mój jest zawsze przede mną.\n" +
                "Odwróć swe oblicze od moich grzechów\ni zmaż wszystkie moje przewinienia.\n\n" +
                "3. Przeciwko Tobie samemu zgrzeszyłem\ni uczyniłem, co złe jest przed Tobą,\n" +
                "Pokrop mnie hizopem, a stanę się czysty,\nobmyj mnie, a nad śnieg wybieleję.\n\n" +
                "4. Oto zrodzony jestem w przewinieniu\ni jako grzesznika poczęła mnie matka.\n" +
                "Nie odrzucaj mnie od swego oblicza\nnie odbieraj mi świętego ducha swego.\n\n" +
                "5. Przywróć mi radość Twojego zbawienia\ni wzmocnij mnie duchem ofiarnym.\n" +
                "Okaż Syjonowi łaskę w Twej dobroci,\nodbuduj mury Jeruzalem.\n\n",
        "Ref. Ooo, Surrexit Christus, Alleluia!\nOoo, Cantate Domino, Alleluia!\n\n" +
                "1. Wszystkie dzieła Pańskie - wychwalajcie Pana.\nAniołowie Pańscy - błogosławcie Pana.\n\n" +
                "2. Słońce i księżycu\nNiebo oraz chmury\n\n3. Gwiazdy nieba\nOgniu i żarze\n\n" +
                "4. Góry i pagórki\nMorza i rzeki\n\n5. Dni i noce\nŚwiatło i ciemności\n\n" +
                "6. Dzikie zwierzęta\nPtaki podniebne\n\n7. Królowie ziemscy\nWszystkie narody\n\n" +
                "8. Chłopcy i dziewczęta\nStarcy i dzieci\n\n9. Niech Imię Pana wszyscy wychwalają,\nbo tylko Jego Imię jest wzniosłe.\n\n" +
                "10. Oto pieśń pochwalna wszystkich Jego świętych,\nLudu Pana bliski śpiewaj Mu na chwałę.\n11. Amen, amen, amen, amen\n\n",
        "Swojego Ducha, Panie, wylej na nas dziś. /x3\n\n",
        "Ref. O Panie, jam Twój sługa,\nJam Twój sługa, syn Twojej służebnicy.\n\n" +
                "1. Oto Mój sługa, którego wybrałem,\nUmiłowany Mój,W którym moje serce ma upodobanie.\n" +
                "Położę Ducha Mojego na Nim,\nA On zaniesie prawo narodom.\n\n" +
                "2. Nie będzie się spierał, ani krzyczał\nI nikt nie usłyszy na ulicach Jeg o głosu.\n" +
                "Trzciny zgniecionej nie złamie,\nAni knota tlejącego nie dogasi, aż zwycięsko sąd przeprowadzi.\n\n" +
                "3. Zwróć się ku mnie i zmiłuj się nade mną,\nUdziel swej siły słudze Twojemu,\n" +
                "I ocal syna Twej służebnicy.\nUczyń dla mnie znak – Zapowiedź pomyślności.\n\n" +
                "4. Drogocenną jest w oczach Pana\nŚmierć Jego czcicieli.O Panie,\njam Twój sługa, jam Twój sługa\nSyn Twojej służebnicy.\n\n",
        "Ref. Szczęśliwi, których moc jest w Panu jedynym,\nktórzy zachowują ufność w sercu swym.\nZ mocy w moc wzrastać będą,\nujrzą Boga na Syjonie.\n\n" +
                "1. Jak miłe są przybytki Twoje, Panie zastępów,\ndusza moja pragnie i tęskni do przedsieni Twych.\n" +
                "Moje serce i ciało radośnie wołają\ndo Boga żywego.\nNawet wróbel dom sobie znajduje\n" +
                "i jaskółka gniazdo, gdzie złoży swe pisklęta.\nPrzy ołtarzach Twoich, Panie zastępów,\nszczęśliwi, którzy mieszkają w Twym domu.\n\n" +
                "2. Zaiste, jeden dzień w przybytkach Twoich Panie\nlepszy, niż innych tysiące.\nWolę stać w progu domu mojego Boga,\nniż mieszkać w namiotach grzeszników.\n" +
                "Bo Pan Bóg jest słońcem i tarczą,\nPan hojnie darzy łaską i chwałą.\nNie odmawia dobrodziejstw czyniącym dobrze,\nszczęśliwy, Panie, który ufa Tobie.\n\n",
        "1. Szedłem kiedyś inną drogą,\nczas powoli mijał mi,\nlecz spotkałem na niej kogoś,\nkto zachwycił sobą mnie.\n\n" +
                "Ref. Pan powiedział: „Ty pójdź za Mną,\nprzez zwyczajne, szare dni.\nNie bój się, Ja będę z tobą, więc nie będzie smutno ci”.\n\n" +
                "2. Powołałeś mnie jak Piotra od zwyczajnych ludzkich spraw,\ntrudna będzie moja droga, lecz nie będę na niej sam.\n\n" +
                "3. Chcę więc wszystko pozostawić, Twoim wiernym uczniem być.\nWszystkich kochać Twą miłością, Panie, proszę, dodaj sił.\n\n",
        "Szedłem kiedyś ścieżyną przez las,\nobok mnie kroczył Jezus i nikt nie widział nas.\n" +
                "W czasie tej wędrówki Pan powiedział mi, że:\nbardzo, bardzo mocno kocha mnie.\n\n",
        "Szukajcie wpierw Królestwa Bożego\ni Jego sprawiedliwości.\nA wszystko inne będzie wam przydane.\nAlleluja, Alleluja.\n\n",
        "Ref. Wciąż przede mną na rosie\nFranciszka ślady bose,\ntak niedawno obok mego życia przeszedł.\n" +
                "Tańczył, chociaż nic nie miał, i klaskała w takt ziemia,\ni tak pragnął, abym w radość jego wierzył.\n\n" +
                "1. Raz się w nocy przyśniła\nFranciszkowi ta miłość,\nmiłość przez „M” duże pisana.\n" +
                "Nie namyślał się długo,\nkto jest Panem, kto sługą,\ndla niej wszystko rzucił od zaraz.\n\n" +
                "2. Niespokojny Franciszek\nznalazł w sobie tę ciszę,\nw której serce modlitwą biło.\n" +
                "A gdy Krzyż w drodze spotkał,\nto uklęknął bez słowa\ni do krzyża przybił swą miłość.\n\n" +
                "3. A gdy zakon się tworzył,\nEwangelię przełożył\nbraciom w dobre dłonie i serca.\n" +
                "Pokój wokół rozsiewał,\na świat cały mu śpiewał,\nszedł do nieba z Bożym szaleństwem.\n\n",
        "Śpiewa Izrael swemu Panu dziś,\nŚpiewa Izrael swemu Panu dziś.\nŚpiewa, śpiewa Izrael,\n" +
                "Śpiewa, śpiewa Izrael,\nŚpiewa, śpiewa Izrael,\nŚpiewa swemu Panu dziś.\n\n",
        "Śpiewaj Alleluja Panu!\nŚpiewaj Alleluja Panu!\nŚpiewaj Alleluja!\nŚpiewaj Alleluja!\nŚpiewaj Alleluja Panu!\n\n",
        "Śpiewaj dziś Panu, głoś Jego chwałę.\nŚpiewaj dziś Panu, wywyższaj Go.\nNiebo i ziemia się przed Nim skłonią.\nPanu dziś oddaj cześć.\n\n",
        "1. Śpiewajmy mu, dziękujmy mu.\nJego miłość przetrwa wiecznie.\nOn dobry jest, On jest Panem mym,\nJego miłość przetrwa wiecznie.\n" +
                "Wznieś głos, wznieś głos,\nŚpiewajmy mu, dziękujmy mu,\nJego miłość przetrwa wiecznie.\n" +
                "Za życie, które daje nam,\nJego miłość przetrwa wiecznie.\nWznieś głos, wznieś głos!\n\n" +
                "Ref. Na zawsze Bóg jest wierny,\nNa zawsze mocny jest.\nNa zawsze Bóg jest z nami, na zawsze!\n\n" +
                "2. Od rana, aż po nocy kres,\nJego miłość przetrwa wiecznie.\nOpieką swą otacza nas,\n" +
                "Jego miłość przetrwa wiecznie.\nWznieś głos, wznieś głos.\n\n",
        "Śpiewajmy Panu, na wieki Jego chwała trwa.\nŚpiewajmy Panu, z radością klaszczmy w dłonie swe.\n" +
                "Śpiewajmy Panu, bo On jest żywy pośród nas.\nŚpiewajmy Panu, Alleluja.\n\n",
        "Ref. Śpiewam Tobie, śpiewam Panie, śpiewam\nImieniu Twemu.\nŚpiewam Tobie, śpiewam Panie, śpiewam\nImieniu Twemu.\n\n" +
                "1. Wielbi ma dusza Pana,\nRaduje się duch mój w Bogu.\nOn wejrzał na mą małość,\nPan dotknął sługę swoją.\n\n" +
                "2. Pokolenia sławić mnie będą,\nWielkie rzeczy mi Pan uczynił.\nŚwięte jest Imię Jego,\nChwalmy Najwyższego.\n\n" +
                "3. Darzy miłosierdziem ludzi,\nPokolenia żyjące w bojaźni.\nOkazuje moc swojej ręki,\nRozprasza pyszniących się w sercach.\n\n" +
                "4. Pan strąca władców z tronu,\nWywyższa pokornych, ubogich.\nGłodnych nasyca dobrami,\nBogatych odprawia z niczym.\n\n" +
                "5. Ujął się za sługą swoim\nIzraelem, swoim dziedzictwem.\nPamiętając o swym miłosierdziu,\nObietnicy dla Abrahama.\n\n",
        "Ref. Światłem i zbawieniem mym,\nDrogą, Życiem, Prawdą jest Pan.\nŁaską swą prowadzi mnie,\nw Jego ręku jest mój los.\n\n" +
                "1. Pan moim światłem i zbawieniem moim, *\nkogo miałbym się lękać?\nPan obrońcą mego życia,*\nprzed kim miałbym czuć trwogę?\n\n" +
                "2. Nawet gdy wrogowie staną przeciw mnie obozem, *\nmoje serce nie poczuje strachu.\nChoćby napadnięto mnie zbrojnie,*\nnawet wtedy ufność swą zachowam.\n\n" +
                "3. O jedno tylko Pana proszę i o to zabiegam, +\nżebym mógł zawsze przebywać w jego domu, *\n" +
                "po wszystkie dni mego życia,\nAbym kosztował słodyczy Pana,*\nstale się radował Jego świątynią.\n\n" +
                "4. W namiocie swoim mnie ukryje *\nw chwili nieszczęścia.\nSchowa w głębi przybytku, *\nna skałę mnie wydźwignie.\n\n" +
                "5. Teraz wysoko podnoszę głowę nad nieprzyjaciół, *\nktórzy mnie osaczają.\nZłożę w Jego przybytku radosne ofiary, *\nzaśpiewam i zagram psalm Panu.\n\n" +
                "6. Usłysz Panie, kiedy głośno wołam,*\nzmiłuj się nade mną i mnie wysłuchaj.\n" +
                "O Tobie mówi serce moje: +\n„Szukaj Jego oblicza”. *\nBędę szukał oblicza Twego, Panie.\n\n" +
                "7. Ty jesteś moją pomocą, więc mnie nie odrzucaj *\ni nie opuszczaj mnie, Boże, mój Zbawco.\n" +
                "Choćby mnie opuścili ojciec mój i matka, *\nto jednak Pan mnie przygarnie.\n\n" +
                "8. Wierzę, że będę oglądał dobra Pana *\nw krainie żyjących.\nOczekuj Pana, bądź mężny, *\nnabierz odwagi i oczekuj Pana.\n\n",
        "Światłość w ciemności świeci\ni ciemność jej nie ogarnia.\nŚwiatłość w ciemności świeci\ni ciemność jej nie ogarnia.\n\n",
        "Ref. Święte Imię - Jezus /x2\n\nJest na ustach mych i w sercu mym,\nw mocy Ducha uwielbiam Cię.\n" +
                "Nie ma w innym zbawienia,\ngdyż nie dano nam, ludziom,\ninnego Imienia, w Nim zbawienie jest.\n\n",
        "Świętemu Bogu oddaj cześć,\ndziękczynną serca śpiewaj pieśń.\nJezusa, Syna swego, Bóg darował nam.\n" +
                "W Nim słaby mocnym staje się,\nbiedny niechaj wyzna, że bogatym jest,\ngdyż wszystko dał nam Bóg. (Dał Bóg).\n\n",
        "Święty Duchu, przyjdź\ni prowadź mnie tam, gdzie Boży Tron.\nŚwięty Duchu, spraw,\nniech z moich ust popłynie dziś uwielbienia pieśń.\n\n" +
                "Ref. Wołam: przyjdź i wypełnij mnie,\nniech wiatr Twój uświęci duszę mą.\nWołam: przyjdź i przenikaj mnie,\nniech ogień Twój rozpali mnie, oczyszcza serce me.\n\n",
        "1. Święty jest nasz Pan, o Święty Pan Zastępów.\nPrawy jest i słuszny każdy Jego czyn.\n" +
                "Fałsz nie wyjdzie z Jego ust i Jego droga prawdą jest,\nNiebiosa z ziemią głoszą Panu cześć.\n\n" +
                "Ref. Święty, o Święty zawsze był i jest,\nbędzie wciąż ten sam.\nChoć grzeszny cały świat,\n" +
                "On czysty wciąż, niewinny jest,\nświęte Imię Twe.\n\n" +
                "2. Godzien jest nasz Pan, o godzien Pan Zastępów.\nJego dobroć ciągle płynie poprzez świat.\n" +
                "Sprawiedliwością jest nasz Pan, lecz miłosierny będzie nam,\nNiebiosa z ziemią głoszą Panu cześć.\n\n" +
                "Ref. Godzien, o godzien zawsze był i jest,\ngodne Imię Twe.\n\n",
        "Święty, Święty,\nŚwięty jest nasz Pan Wszechmogący. /x2\nOn był i jest, i przyjdzie znów. /x2\nWywyższam Cię, Tobie śpiewam pieśń.\n" +
                "Wywyższam Cię, wielbię Imię Twe.\nWznoszę mój głos, by dać Ci chwałę,\nbo jesteś godzien wszelkiej czci.\n\n",
        "Święty, święty, święty, święty, święty Pan.\nŚwięty, święty, święty, święty Bóg i Król.\nOtwieram serce me, podnoszę ręce swe,\nKrólu, wejdź i rozgość się.\n\n",
        "1. Święty, Święty, Święty, Święty.\nŚwięty, Święty Pan Bóg Zastępów.\nDziś wznosimy nasze ręce, uwielbiając miłość Twą.\nŚwięty, Święty, Święty, Święty.\n\n" +
                "2. Dobry Ojcze, Dobry Ojcze.\nJak jest dobrze być Twym dzieckiem, Dobry Ojcze.\n" +
                "Dziś wznosimy nasze ręce, uwielbiając miłość Twą.\nDobry Ojcze, Dobry Ojcze.\n\n" +
                "3. Drogi Jezu, Drogi Jezu.\nSwoją Krwią nas odkupiłeś, Drogi Jezu.\n" +
                "Dziś wznosimy nasze ręce, uwielbiając miłość Twą.\nDrogi Jezu, Drogi Jezu.\n\n" +
                "4. Duchu Święty, Duchu Święty.\nPrzyjdź, napełnij nas na nowo, Duchu Święty.\n" +
                "Dziś wznosimy nasze ręce, uwielbiając miłość Twą.\nDuchu Święty, Duchu Święty.\n\n",
        "1. Święty, Święty, Święty jest nasz Bóg.\nŚwięty jest nasz Bóg wszechmogący, /x2\nktóry był, który jest i który przychodzi.\nŚwięty, Święty, Święty jest nasz Bóg.\n\n" +
                "2. Godzien, godzien, godzien jest Baranek.\nGodzien jest Baranek zabity,\nktóry był, który jest i który przychodzi.\nGodzien, godzien, godzien jest Baranek.\n\n" +
                "3. Jezus, Jezus, Jezus Panem jest.\nJezus Bogiem jest Wszechmogącym,\nktóry był, który jest i który przychodzi.\nJezus, Jezus, Jezus Panem jest.\n\n",
        "Ta krew z grzechów obmywa nas.\nTa krew czyni nas bielszym od śniegu.\nTa krew z grzechów obmywa nas.\nTo jest Baranka święta krew.\n\n",
        "1. Tajemny akord kiedyś brzmiał,\nPan cieszył się, gdy Dawid grał.\nAle muzyki dziś\ntak nikt nie czuje.\n" +
                "Kwarta i kwinta, tak to szło.\nRaz wyżej w dur, raz niżej w mol.\nNieszczęsny król ułożył Alleluja.\n\n" +
                "Ref. Alleluja…\n\n" +
                "2. Na wiarę nic nie chciałeś brać,\nlecz sprawił to księżyca blask,\nże piękność jej na zawsze\ncię podbiła.\n" +
                "Kuchenne krzesło tronem twym,\nostrzegła cię, już nie masz sił\ni z gardła ci wydarła: Alleluja.\n\n" +
                "3. Dlaczego mi zarzucasz wciąż,\nże nadaremno wzywam Go.\nJa przecież nawet nie znam\nGo z imienia.\n" +
                "Jest w każdym słowie światła błysk.\nNieważne, czy usłyszysz dziś,\nnajświętsze, czy nieczyste: Alleluja.\n\n" +
                "4. Tak się starałem ale cóż,\ndotykam tylko, zamiast czuć.\nLecz mówię prawdę, nie chcę\nwas oszukać.\n" +
                "I chociaż wszystko poszło źle,\nprzed Panem pieśni wstawię się,\nna ustach mając tylko: Alleluja.\n\n",
        "1. Tak jak rzeki, spieszące do mórz,\ntak jak wiatru szybki bieg.\n\n" +
                "Ref. Serce me, śpiewaj Panu pieśń.\nOn sam przyjdzie dziś, by twym szczęściem być.\n\n" +
                "2. Kiedy Bogu, swój powierzysz los,\npierzchnie wszelki smutku cień.\n\n" +
                "3. Szczęściem moim jest Chrystus, mój Bóg.\nJemu składam serca dar.\n\n",
        "1. Tak jakoś dziwnie jest na świecie,\nczasami nie wiem, gdzie jest sens.\nKtoś pytał mnie, czy wiem, co znaczy nadzieja,\nczy taki wyraz jeszcze jest.\n\n" +
                "Ref. Wiem Panie mój, że bardzo kochasz mnie,\nja chcę być Twój i grać Tobie reggae. /x2\n\n" +
                "2. Też w życiu różne mam marzenia,\ntak chciałbym jakieś szczęście mieć.\nLudzie o sobie myślą wciąż do znudzenia,\nja chciałbym z Tobą czegoś chcieć.\n\n" +
                "3. Pozwól mi Panie tak uwierzyć,\nże będzie w sercu wiecznie maj.\nPozwól mi życie swoje tak mocno przeżyć,\nże kiedyś tam już tylko raj.\n\n",
        "Tak mnie skrusz, tak mnie złam,\ntak mnie wypal Panie.\nByś został tylko Ty, byś został tylko Ty,\nna zawsze Ty.\n\n",
        "Tak pragnę wielbić Cię, dobry Panie mój.\nTak pragnę wielbić Cię, bo Ty dajesz mi\npatrzeć w swoją twarz i rozpoznać wolę Twą.\nTak pragnę wielbić Cię.\n" +
                "Ptaki na niebie nucą Ci swą pieśń,\nramiona polnych drzew niosą Tobie cześć,\na ja śpiewam Ci, pragnę ręce swe do Ciebie wznieść.\n\n",
        "1. Jezus jest mym Panem.\nJezus znalazł mnie.\nOn mnie wyrwał z tego świata,\nbym Mu służyć mógł.\n\n" +
                "Ref. Tak, tak, tak, Panie mój,\ndzięki, dzięki Ci.\n\n" +
                "2. On mi podał rękę\ni wybaczył to,\nco mnie wiodło na bezdroża\ni przepaści dno.\n\n" +
                "3. Widzę piękno świata,\nczuję miłość tą,\nktórą Jezus mnie obdarza,\nwidząc słabość mą.\n\n" +
                "4. A jeżeli pragniesz,\nuwolnionym być.\nDołącz głos swój do mojego,\nśpiewaj ze mną pieśń.\n\n",
        "Ref. Tchnij moc, tchnij miłość i przenikaj życieme. /x2\n\n" +
                "1. Bo całym sercem swym oddaję Ci cześć,\nbo każdą myślą swą oddaję Ci cześć\ni z całej siły swej oddaję Ci cześć, o Panie mój.\n\n" +
                "2. Bo całym sercem swym uwielbiać Cię chcę,\nbo każdą myślą swą uwielbiać Cię chcę\ni z całej siły swej uwielbiać Cię chcę, o Panie mój.\n\n" +
                "3. Bo całym sercem chcę odnaleźć Twą twarz,\nbo każdą myślą chcę odnaleźć Twą twarz\ni z całej siły chcę odnaleźć Twą twarz, o Panie mój.\n\n",
        "Ref. Ten, który Słowem stwarza niebiosa,\nswojego Syna zesłał nam.\n\n" +
                "1. Wielbi dusza moja Pana\ni raduje się duch mój w Bogu, Zbawicielu moim,\nbo wejrzał na uniżenie swojej Służebnicy.\n" +
                "Oto bowiem odtąd+\nbłogosławić mnie będą wszystkie pokolenia.\n\n" +
                "2. Gdyż wielkie rzeczy uczynił mi wszechmocny,\na Jego Imię jest święte.\n" +
                "Jego miłosierdzie z pokolenia na pokolenie,\nnad tymi, którzy się Go boją.\n\n" +
                "3. Okazał moc Swego ramienia,\nrozproszył pyszniących się zamysłami serc swoich.\n" +
                "Strącił władców z tronu,\na wywyższył pokornych.\n\n" +
                "4. Głodnych nasycił dobrami,\na bogatych z niczym odprawił.\n" +
                "Ujął się za swoim sługą, Izraelem,\npomny na swe miłosierdzie.\n\n" +
                "5. Jak obiecał naszym ojcom,\nAbrahamowi i jego potomstwu na wieki.\n" +
                "Chwała Ojcu i Synowi i Duchowi Świętemu,\njak była na początku, teraz i zawsze, i na wieki wieków. Amen.\n\n",
        "Teraz tak mówi Pan,\nktóry Cię stworzył Jakubie,\nktóry Cię ukształtował,\nIzraelu.\n\nNie bój się, bo Cię wykupiłem,\n" +
                "nazwałem Cię twoim imieniem,\nmoim jesteś.\n\nGdy pójdziesz przez wodę, będę z Tobą,\na gdy przez rzeki, nie zaleją Cię.\n" +
                "Gdy pójdziesz przez ogień, to nie spłoniesz,\na płomień nie spali Cię.\n\nBo ja Pan jestem, Twoim Bogiem, jak święty izraelski,\n" +
                "Twym odkupicielem. /x2\n\nSypią się, sypią się, bramy piekielne sypią się,\nkiedy dzieci posłuszne Ojcu, razem modlą się. /x2\n\n",
        "1. Tęsknię za Tobą, Panie,\nchcę zatańczyć miłość, ptaków śpiew.\nTyś największe me pragnienie,\nz Tobą mogę po bezkresach biec.\n" +
                "Tyś największe me pragnienie,\nz Tobą mogę po bezkresach,\nz Tobą mogę po bezkresach,\nz Tobą mogę po bezkresach biec.\n\n" +
                "2. Kiedy mówisz do mnie, Panie,\nw Twoje oczy kiedy patrzę się,\nmoje życie tańczy taniec,\ntakie proste i radosne jest.\n" +
                "Moje życie tańczy taniec,\ntakie proste i radosne,\ntakie proste i radosne,\ntakie proste i radosne jest.\n\n",
        "Ref. To jest Baranka Ciało, Jego Krew,\nChleb, który dał za życie świata,\nWinne grono zmiażdżył, poi mnie,\nwe Krwi zdrojach płuczę szaty swe.\n" +
                "To jest Baranka Ciało, Jego Krew,\nChleb, który dał za życie świata,\nZiarno pęcznieje, otchłań budzi się,\nPascha Pana staje się.\n\n" +
                "1. W Nim odkupienie win - uzdrowienie ran,\nChleb z nieba dany nam, karmi nas.\n" +
                "Tyś krzewem winnym jest, spragnionym wody dasz,\nPanie, wypełnij serce me!\n\n" +
                "2. Tyś żywym znakiem jest, śmierci mego Pana,\nChlebie, co dajesz życie wieczne.\n" +
                "Pokarmie słodki nasz daj zaspokoić głód,\nNiech dusza zgłodniała się nasyci.\n\n" +
                "3. Krew Twoja dana nam, Przymierzem z nami jest,\nPokarmem duszy na życie wieczne.\n" +
                "Malutka kropla Krwi, otwiera wnętrza drzwi,\nPanie, naucz mnie umierać!\n\n",
        "To mój Pan, wiele mi uczynił, On moim Bogiem.\nTo mój Pan, wiele mi uczynił, On mnie uzdrowił.\n\n",
        "To przykazanie Ja dziś daję wam,\nbyście się miłowali, jak Ja miłuję was.\nByście się miłowali, jak Ja miłuję was.\n\n" +
                "Ref. A wtedy wszyscy poznają, żeście moi,\ngdy miłość wzajemną mieć będziecie. /x2\n\n",
        "Tobie chór aniołów śpiewa nową pieśń,\nChwała Barankowi. /x2\nAlleluja, Alleluja, Alleluja! Chwała i cześć.\n\n",
        "Ref. Tobie chwała, chwała. /x2\n\nWszechmogący, najświętszy,\nnajwyższy, Boże nasz. /x2\n\n" +
                "Bridge. Tobie pragniemy oddawać\nwszelką sławę, wszelką chwałę,\nwszelką wdzięczność.\n" +
                "Błogosławieństwo, wszelką cześć i dobra.\nNiech się stanie, niech się stanie.\n\nAmen, amen, amen. /x2\n\n",
        "Tobie, Panie, zaufałem. To Twój Duch prowadzi mnie.\nTobie, Panie, zaufałem.\nNie zawstydzę się. Na wieki.\n\n",
        "1. Duchu Święty do nas przyjdź.\nDotknij w sercach naszych ran.\nUzdrów je miłością swą.\nPokój w serca nasze wlej. /x2\n\n" +
                "2. Królu życia do nas przyjdź. przyjdź.\nNiech Twój Krzyż jaśnieje w nas.\nŁaską swoją ulecz nas.\nOgień niech zapłonie dziś. /x2\n\n" +
                "3. Ojcze, Stwórco świata,\nNiech Twój obraz żyje w nas.\nPrzyjdź i ześlij Ducha nam.\nNiech umocni życie w nas. /x2\n\n",
        "Twe drogi są najlepsze,\nnajlepsze dla mnie są.\nTy, Panie, wiesz najlepiej,\nco dla mnie dobre jest.\n" +
                "I zawsze troszczysz się,\nbo bardzo kochasz mnie.\nI zawsze troszczysz się,\nbo bardzo kochasz mnie.\n\n",
        "1. Moje wędrowanie - Panie mój.\nMoje upadanie - Panie mój.\nMoje powstawanie - Panie mój.\nTwoje miłowanie.\n\n" +
                "Ref. Jesteś dla nas, spragnionych, jak rosa poranna.\nTobie śpiewamy radosne: Hosanna.\n" +
                "Jesteś dla nas, wędrowców, jak zdrój czystej wody.\nDuszy wytchnienie, niebiańskie ogrody.\n\n" +
                "2. Nasze wędrowanie - Panie nasz.\nNasze upadanie - Panie nasz.\nNasze powstawanie - Panie nasz.\nTwoje miłowanie.\n\n" +
                "Ref. Jesteś dla nas, żeglarzy, jak gwiazda zaranna.\nTobie śpiewamy radosne: Hosanna.\nJesteś dla nas, pielgrzymów, jak burzy ustanie.\n" +
                "Jesteś Drogą, co nam przez Ojca jest dana.\nJesteś dla nas spragnionych jak rosa poranna.\nTobie śpiewamy radosne: Hosanna.\n" +
                "Jesteś dla nas, wędrowców, jak zdrój czystej wody.\nDuszy wytchnienie, niebiańskie ogrody.\n\n",
        "Twoje słowo jest lampą dla moich stóp\ni światłem na mojej ścieżce.\n\n",
        "1. Twój Krzyż, Jego ramiona mnie oplatają.\nTwój Krzyż prosty drewniany, a jakże wielki.\n" +
                "W Jego wnętrzu mała monstrancja, Twoje Ciało.\nI pięć stróżek krwi błogosławi ziemię.\nNaucz mnie umierać.\n\n" +
                "Ref. Duchu przyjdź, Duchu przyjdź,\nO Duchu Święty przyjdź!\nDuchu przyjdź, Duchu przyjdź,\nO Duchu Święty przyjdź!\n\n" +
                "2. Pewnego dnia objęły mnie ramiona krzyża,\nPrzytuliły mnie mocno do Twojego serca.\n" +
                "I ujrzałem w każdym upadku moim płomień miłosierdzia,\nA w każdym powstaniu zwycięstwo nadziei,\nŻe jedna kropla Krwi Twojej.\n\n" +
                "Ref. Spadając na mą pustynię,\nZrodzi szaleństwo zielonej miłości.\nSpadając na mą pustynię,\nZrodzi szaleństwo zielonej miłości.\n\n",
        "Twój Pan, twój Bóg (…), który w Tobie jest (…)\njest wielki (…), jest wielki (…).\nSpotkałem Go (…), zmartwychwstały Pan (…),\n" +
                "łaskawy i wierny, wszechmocny Bóg.\nI w duchu twym (…) On panować chce (…),\nna zawsze i wszędzie. Amen.\n\n",
        "Ty jesteś skałą zbawienia mego,\nw Tobie ma siła i moc.\nTyś mą nadzieją, mym natchnieniem,\ndo Ciebie wznoszę mój głos.\n" +
                "Panie wierzę Ci, tak wierzę Ci,\nbo Twa miłość wierna jest.\nTyś pomocą mą w potrzebach mych.\nPanie, przy Tobie chcę być.\n\n",
        "1. Ty, Franciszku, w tym życiu widziałeś Boga\nwe wszystkim, dla Ciebie bratem\nbył księżyc, a siostrą gwiazdy.\n" +
                "Tyś zrozumiał,\nże kochać, to znaczy służyć bliźniemu,\nwięc porzuciłeś ziemskie radości dla niego\n\n" +
                "Ref. Znalazłeś radość, życia sens,\nznalazłeś pokój w sercu swym,\nbo pokochałeś z wszystkich sił swego brata.\n" +
                "Umiałeś oddać chleba kęs\ntam, gdzie nienawiść, miłość wnieść,\nbo pokochałeś z wszystkich sił swego brata.\n\n" +
                "2. Szli za Tobą szaleńcy pragnący żyć dla drugich,\nTy ich uczyłeś jak dawać, nie mając nic.\n" +
                "Swą prostotą podbili serca ówczesnych ludzi,\nNiosąc im radość i szczęście, nie mając nic.\n\n" +
                "Ref. Znaleźli radość, życia sens,\nznaleźli pokój w sercach swych,\nbo pokochali z wszystkich sił swego brata.\n" +
                "Umieli oddać chleba kęs\ntam, gdzie nienawiść, miłość wnieść,\nbo pokochali z wszystkich sił swego brata.\n\n" +
                "3. I my także pragniemy iść Twoim śladem, Franciszku,\nzrozumieć sens tego życia, odnaleźć się.\n" +
                "Ref. Znajdziemy radość, życia sens,\nznajdziemy pokój w sercach swych,\nbo pokochamy z wszystkich sił swego brata. /x2\n\n",
        "Ref. Ty jesteś Święty, Panie nasz, który czynisz cuda,\nTy jesteś Święty, Boże nasz. /x2\n\n" +
                "1. Ty jesteś mocny, Ty jesteś wielki,\nTy jesteś najwyższy, Ty jesteś Królem.\n" +
                "Ty jesteś w Trójcy, Bóg nad Bogami,\nTy jesteś dobro, wszelkie dobro.\n\n" +
                "2. Ty jesteś miłością, Ty jesteś kochaniem,\nTy jesteś mądrością, Ty jesteś pokorą.\n" +
                "Ty jesteś cierpliwością, Ty jesteś pięknością,\nTy jesteś łaskawością, bezpieczeństwem!\n\n" +
                "Bridge. Ty jesteś wiarą naszą,\nTy jesteś nadzieją naszą,\nTy jesteś miłością naszą,\nwiecznym życiem naszym.\n\n",
        "1. Ty wskazałeś drogę do miłości, Ty Panie.\nTy zmieniłeś świat swym zmartwychwstaniem,\nTy Panie, Panie.\n" +
                "Tylko Ty jeden wiesz, co w mym sercu dzieje się,\nTylko Ty, Panie, tylko Ty.\n" +
                "Tylko Ty jeden wiesz, co w mym sercu dzieje się,\nTylko Ty, Panie, tylko Ty.\n\n" +
                "2. Ty oddałeś życie za nas wszystkich, Ty Panie.\nI dlatego jesteś tak mi bliski,\nTy Panie, Panie.\n" +
                "U Twych stóp cały świat, Ty wiesz, co czeka nas,\nTylko Ty, Panie, Tylko Ty. /x2\n\n" +
                "3. W moim sercu jesteś w dzień i w nocy, Ty Panie.\nChoć mym oczom jesteś niewidoczny,\nTy Panie, Panie.\n" +
                "Tylko Ty jeden wiesz, co w mym sercu dzieje się,\nTylko Ty możesz pomóc mi. /x2\n\n",
        "Ty, Panie, dałeś życie mi i łaską swą obdarzasz mnie.\nTy pokonałeś zło, Ty pokonałeś grzech i śmierć.\n" +
                "Ty darzysz mnie miłością swą,\nprostujesz każdą ścieżkę złą.\nTy pokochałeś mnie, Ty, Panie, kochasz, kochasz mnie.\n" +
                "Jezu mój, chcę wywyższać Imię Twe.\nBoś Ty dobrym Bogiem jest,\nboś wspaniałym Panem jest.\n\n",
        "1. Gdy drogi pomyli los zły i oczy mgłą zasnuje.\nMiej w sobie tę ufność, nie lękaj się.\n" +
                "A kiedy gniew świat ci przysłoni\ni zazdrość jak chwast zakiełkuje.\nMiej w sobie tę ufność, nie lękaj się.\n\n" +
                "Ref. Ty tylko mnie poprowadź,\nTobie powierzam mą drogę.\nTy tylko mnie poprowadź, Panie mój.\n\n" +
                "2. Poprowadź, jak jego prowadzisz, przez drogi najprostsze z możliwych.\nI pokaż mi jedną, tę jedną z nich.\n" +
                "A kiedy już głos Twój usłyszę i karmić się będę nim co dzień.\nMiej w sobie tę ufność, nie lękaj się.\n\n",
        "1. Ty wyzwoliłeś nas, Panie,\nz kajdan i samych siebie,\na Chrystus, stając się bratem\nnauczył nas wołać do Ciebie.\n\n" +
                "Ref. Abba Ojcze! Abba Ojcze!\nAbba Ojcze! Abba Ojcze!\n\n" +
                "2. Bo Kościół, jak drzewo życia, w wieczności zapuszcza korzenie,\nprzenika naszą codzienność i pokazuje nam Ciebie.\n\n" +
                "3. Bóg hojnym dawcą jest życia, On wyswobodził nas ze śmierci\ni przygarniając do Siebie, uczynił swoimi dziećmi.\n\n" +
                "4. Wszyscy jesteśmy braćmi, jesteśmy jedną rodziną,\ntej prawdy nic już nie zaćmi i teraz jest jej godzina.\n\n",
        "Ty zapraszasz mnie jak co dzień do siebie,\nzapalasz słońce i robisz śniadanie, i uczysz,\njak blisko stąd do miłości.\n" +
                "Potem wkładam ręce w Twoje dłonie,\nTy mnie wiedziesz do ludzi szczęśliwych, do ludzi\nszczęśliwych i do radości.\n" +
                "Dalej biegnę za Tobą z ufnością,\ncoraz bliżej i bliżej do Ciebie, do Ciebie,\ndo ludzi i do jasności.\n" +
                "Ty poprowadź, drogę pokaż w ciemnościach,\nTy poprowadź, drogę pokaż w ciemnościach, do Ciebie,\ndo ludzi i do miłości.\n" +
                "Nie ma mnie bez Ciebie, nie ma mnie. /x2\nLa, la...\nNie ma mnie bez Ciebie, nie ma mnie.\n\n",
        "Ref. Tylko w Twoim miłosierdziu,\nPanie mój, nadzieja ma.\nTylko w Twoim miłosierdziu,\nPanie mój, nadzieja ma.\n\n" +
                "1. Chcę Cię wywyższać, Boże mój, Królu,\ni błogosławić Imię Twe na zawsze i na wieki.\n" +
                "Każdego dnia będę Cię błogosławił\ni na wieki wysławiał Twe Imię.\n\n" +
                "2. Wielki jest Pan i godzien wielkiej chwały,\na wielkość Jego niezgłębiona.\n" +
                "Pokolenie pokoleniu głosi Twoje dzieła\ni zwiastuje Twoje potężne czyny.\n\n" +
                "3. Głoszą wspaniałą chwałę Twego majestatu\ni rozpowiadają Twe cuda.\n" +
                "I mówią o potędze Twoich dzieł straszliwych,\ni opowiadają Twą wielkość.\n\n" +
                "4. Przekazują pamięć o Twej wielkiej dobroci\ni radują się Twą sprawiedliwością.\n" +
                "Pan jest łagodny i miłosierny, nieskory do gniewu\ni bardzo łaskawy.\n\n" +
                "5. Pan jest dobry dla wszystkich\ni Jego miłosierdzie ogarnia wszystkie Jego dzieła.\n" +
                "Niechaj Cię wielbią, Panie, wszystkie dzieła Twoje\ni święci Twoi niech Cię błogosławią!\n\n" +
                "6. Niech mówią o chwale Twojego królestwa\ni niech głoszą Twoją potęgę.\n" +
                "Aby oznajmić synom ludzkim Twoją potęgę\ni wspaniałość chwały Twego królestwa.\n\n" +
                "7. Królestwo Twoje królestwem wszystkich wieków,\nTwoje panowanie trwa przez wszystkie pokolenia.\n" +
                "Pan jest wierny we wszystkich swych słowach\ni we wszystkich swoich dziełach święty.\n\n" +
                "8. Pan podtrzymuje wszystkich, którzy padają,\npodnosi wszystkich zgnębionych.\n" +
                "Oczy wszystkich oczekują Ciebie,\nTy zaś dajesz im pokarm we właściwym czasie.\n\n" +
                "9. Ty otwierasz swą rękę i wszystko, co żyje,\nnasycasz do woli.\n" +
                "Pan jest sprawiedliwy na wszystkich swych drogach\ni łaskawy we wszystkich swoich dziełach.\n\n" +
                "10. Pan jest blisko wszystkich, którzy Go wzywają,\nwszystkich wzywających Go szczerze.\n" +
                "Spełnia wolę tych, którzy się Go boją,\nusłyszy ich wołanie i przyjdzie im z pomocą.\n\n" +
                "11. Pan strzeże wszystkich, którzy Go miłują,\na wytępi wszystkich występnych.\n" +
                "Niech usta moje głoszą chwałę Pana,\nby wszelkie ciało wielbiło Jego święte Imię.\n\n",
        "1. Tyle dobrego zawdzięczam Tobie, Panie.\nWszystko co mam, od Ciebie przecież jest.\n" +
                "I to, że jestem, że życie wciąż poznaję,\ndziś tymi słowy wyrazić wszystko chcę:\n\n" +
                "Ref. Za każdy dzień, za nocy mrok,\nza radość mą, szczęśliwy rok,\nnawet za chmurne, deszczowe dni,\nza wszystko, Panie, dziękuję Ci. /2x\n\n" +
                "2. Gdy mnie uczono, że jesteś Boże w niebie.\nGdy poznawałem, co dobre jest, co złe.\n" +
                "W dziecięcych słowach mówiłem: kocham Ciebie\ni powtarzałem w modlitwie słowa te:\n\n" +
                "3. Więc przyjm, o Boże, mych modłów dziękczynienie,\nbo jakże często wdzięczności w modłach brak.\n" +
                "Mądrością życia jest widzieć sens cierpienia,\ndlatego, Boże, śpiewamy Tobie tak:\nNie ma mnie bez Ciebie, nie ma mnie.\n\n",
        "Tyś jak skała, Tyś jak wzgórze,\nPanie nasz, Boże nasz.\nTyś jak wiatr w swej naturze, Tyś jest stwórcą wszystkich nas.\n" +
                "Światłem swym rozświetlasz drogę, która prosto wiedzie nas.\n" +
                "Tam, gdzie źródło Twej miłości, gdzie radośnie płynie czas.\nŁamdarej, łamdało. /x2\n\n",
        "1. Gdzie mamy znaleźć naszą przystań, Jeśli nie w Tobie.\nDokąd nas powiodą nasze nogi, Jeśli nie do Ciebie.\n\n" +
                "Ref. Tyś naszą mocą i pieśnią, Ogniu nieugaszony.\nKto zdoła Cię ugasić. Stanąć przeciw Tobie.\n\n" +
                "2. Dokąd mamy pójść, wędrowcy, jeśli nie do Twego Królestwa.\nChwalcie Pana i Mu śpiewajcie, wszystkie narody.\n\n" +
                "3. Utwierdź Kościół - Matkę naszą. Rozszerz Twoje Królestwo.\nTwoja chwała ponad niebiosa, powiedz jedno słowo.\n\n",
        "1. Tyś w wieczerniku Chlebem się stał,\nTyś nam Krew swoją dał.\nTy chciałeś z nami jedno być, jednego Ojca czcić.\n\n" +
                "Ref. Więc połącz wszystkich w jedno Ciało,\nPanie nasz!\nWlej w serca ludzi miłość trwałą, Boże nasz!\n\n" +
                "2. Pożywać chcemy Ciało Twe, Krew Twoją chcemy pić.\nW miłości spalać serca swe, na wieki z Tobą żyć.\n\n",
        "Ref. U Ciebie, Boże, schronienie me,\nw cieniu Twych skrzydeł ukryć mnie chciej.\n\n" +
                "1. W Tobie, Panie, ucieczka moja,\nniech wstydu nie zaznam na wieki.\nWyzwól mnie i ratuj w swej sprawiedliwości,\nnakłoń ku mnie swe ucho i ześlij ocalenie.\n\n" +
                "2. Boże, wyrwij mnie z rąk niegodziwca,\nz ucisku złoczyńcy i ciemiężyciela.\nBo Ty, Boże, jesteś moją nadzieją,\nPanie, Tobie ufam od młodości.\n\n" +
                "3. Moi wrogowie o mnie rozprawiają,\nCo, co czyhają na me życie, odbywają narady.\nBoże, nie stój z daleka ode mnie,\nBoże, pośpiesz mi z pomocą!\n\n" +
                "4. Bądź dla mnie skałą schronienia\ni zamkiem warownym, aby mnie ocalić.\nBo Ty jesteś moją opoką i twierdzą,\nTy, mój Boże, jesteś moją nadzieją.\n\n",
        "Ubi caritas et amor,\nUbi caritas, Deus ibi est.\nTam, gdzie miłość jest i dobroć,\nTam, gdzie miłość jest, tam mieszka Bóg.\n\n",
        "Jak wzburzone morze, myśli w mojej głowie\nNie wiem , jak mam stać wśród tych fal\nWidzę Cię przez mgłę, stoisz tak spokojnie\n" +
                "Wołam Cię: Nie pozwól mi spaść\n\nUcisz mnie. Powiedz coś\nJednym słowem swym masz moc\nZakończyć sztorm\n" +
                "Uchwyć mnie. Ukryj w myśli swej\nZniwecz każdą ciemną myśl\nŚwiatłem swym\n\nPatrzę w Twoje oczy, groźne i łagodne\n" +
                "Nic nie może przerazić Cię\nIdę w Twoją stronę, a Ty idziesz do mnie\nWznosisz dłoń i uśmiechasz się\n\nUcisz się. Ja jestem.\n" +
                "W zaufaniu Twym jest moc,\nw ciszy twej\nNie bój się. Przecież jesteś mój\nMożesz ufać mi i iść\nMimo burz\n\n" +
                "Ucisz się. Ja jestem.\nW zaufaniu Twym jest moc,\nw ciszy twej\nNie bój się. Mam cię w rękach swych.\nMożesz ufać mi i iść\n" +
                "Będę tu\nWokół wielka cisza, gdzie są przeciwnicy\nUstąpiły burze i wiatr\nTu na szklanym morzu w tęczy Twojej chwały\nStoję i nie muszę się bać\n\n",
        "Ref. Oto Boży Baranek swym Ciałem karmi nas,\nPoi Krwią pełną Boskiej słodyczy,\n" +
                "Kto spożywa to Ciało i pije Jego Krew\nW domu Ojca zamieszka na wieki.\n\n" +
                "1. Spożywajmy Ciało za nas włócznią ranione,\nKtóre jest bramą do Królestwa światła\n" +
                "I choć przebite bezwstydnie na krzyżu\nSzczelnie przed złem nas osłania.\n\n" +
                "2. Krwią Jego naznaczmy drzwi serc naszych,\nby anioł śmierci nas ominął.\n" +
                "We Krwi tej zanurzmy nasze dusze,\na wzburzone fale życia nas nie pochłoną.\n\n" +
                "3. Ciało nieskazitelne, które nigdy się nie starzeje\ni wypełnia duchem Bożego dzięcięctwa;\n" +
                "W skromności swej skryte pod postacią chleba,\nw szaleństwie miłości całe nam oddane.\n\n" +
                "4. Krew Jego jest morzem miłosierdzia,\nktóre niech nigdy nie rozstąpi się przed nami.\n" +
                "Niech nas obmyje i uświęci,\nwiecznemu szczęściu przysposobi.\n\n" +
                "5. Pokarm, co jedzącego w siebie przemienia,\nNapój , który gasi pragnienie je wzbudzając;\n" +
                "Uczta o stole zastawionym obficie\nMiłość darmo dana, chcąca w nas zamieszkać\n\n",
        "Ukaż mi, Panie, swą twarz,\ndaj mi usłyszeć Twój głos.\nNaucz mnie szukać Ciebie,\nwskaż mi cień Twego oblicza.\n\n",
        "Ukaż mi, Panie, swą twarz,\ndaj mi usłyszeć swój głos.\nBo słodki jest Twój głos\ni twarz pełna wdzięku.\nUkaż mi, Panie, swą twarz.\n\n",
        "Tylko w Bogu moje jest zbawienie.\nW Nim jedynie duszy ukojenie.\nMoja chwała i skała,\nw Nim pokładam mą nadzieję.\n\n" +
                "Ufaj Mu, Kościele, w każdym czasie.\nWylewajcie przed Nim serca wasze.\nLudzie lżejsi niż tchnienie.\nTylko w Bogu jest zbawienie.\n\n" +
                "Ref. Sprawiedliwy jest nasz Pan.\nKażdy zbierze to, co siał.\nKto uwierzy nie zawiedzie się.\n" +
                "Przychodzimy przed Twój tron.\nChcemy widzieć Twoją moc\ni doświadczyć, Panie, łaski Twej. /x2\n\n",
        "1. Ukrzyżowana Miłość pochwyciła mnie,\nCzekała tam gdzie czaiła się śmierć.\nO nie, już nie chcę kochać mniej,\nGdzie Ty jesteś - nie lękam się!\n\n" +
                "Ref. Nie śmierć - lecz życie,\nNie koniec - lecz początek\nnie rozpacz - lecz szczęście,\nmoje serce zjednoczone z Tobą.\n\n" +
                "2. Zaślubiny już dokonują się,\nW swych ramionach Ty sam przenosisz mnie.\nJednoczymy się w uścisku ukrzyżowanym,\nCałując nawzajem nasze święte rany.\n\n",
        "1. Uwielbia Ciebie ziemia szachownicą pól,\nuwielbia Ciebie Panie, cały świat.\nTęczą kolorów, szumem borów, krajobrazem gór,\n" +
                "jesiennym liściem, co na ziemię spadł.\nPochwalony bądź, Panie nasz,\nuwielbiony bądź, Ojcze nasz.\n\n" +
                "2. Szepty strumienia, czas istnienia, każdy nowy dzień,\ntęsknota, by dogonić szczęścia bieg.\n" +
                "Słowa zbyt małe wobec całej pełni serca drżeń,\nna wieki sławią, Panie, Imię Twe.\n\n" +
                "3. Ku Twojej chwale biegnie stale nieustanny czas\ni nowe życie ciągle rodzi się.\n" +
                "I choć stworzony, niezmierzony pozostaje świat,\ncałą potęgą sławi Imię Twe.\n\n",
        "1. Uwielbiać Ciebie chcę,\nwśród zgiełku fal spienionych groźnie.\nWezwij głośno mnie, chcę słyszeć Twój głos.\n\n" +
                "Ref. Chcę widzieć Cię,\njak patrzysz na mnie czułym wzrokiem.\nSłyszeć Cię, jak wzywasz moje imię,\n" +
                "wyciągasz dłoń, posłusznie milkną wszystkie burze.\nWzywasz mnie by iść, do Ciebie iść po wodzie. /x2\n\n" +
                "2. Uwielbiać Ciebie chcę,\nwśród czyhających niebezpieczeństw.\nPrzede mną, Panie, stań, chcę widzieć Twą twarz.\n\n",
        "Uwielbiać mego Pana chcę,\nwyśpiewać Jemu nową pieśń.\nOn ukojeniem i zbawieniem duszy mej.\nTylko Jemu służyć chcę.\n" +
                "Alleluja! Uwielbiaj duszo ma.\nWywyższaj Pana, wywyższaj Go.\nAlleluja! Uwielbiaj duszo ma.\nOn godzien wszelkiej chwały jest.\n\n",
        "Uwielbiajcie Pana ludzkich serc bijące dzwony.\nPadnij na kolana przed Nim ludu utrudzony.\n" +
                "On osuszy twoje łzy, On ratunkiem będzie ci,\nu Jego stóp padnie wróg, bo On Bóg niezwyciężony.\n" +
                "Niepojęty w swej mądrości,\nŚwięty, Święty, Bóg miłości.\nŚpiewaj Panu ziemio,\nchwalcie wszystkie świata strony.\n\n",
        "Uwielbiajmy Jezusa, bo kocha nas jak nikt.\nUwielbiajmy Jezusa, bo kocha nas jak nikt.\nUwielbiajmy Jezusa, bo kocha nas.\n" +
                "Uwielbiajmy Jezusa. bo kocha nas.\nUwielbiajmy Jezusa, bo kocha nas jak nikt.\n\n",
        "Ref. Uwielbiajmy Pana! /x4\n\n" +
                "1. Wychwalam Ciebie, Panie, bo Twój gniew się uśmierzył.\nBędę miał ufność w Tobie, nie ulęknę się,\n" +
                "bo pieśnią moją jest Jahwe. On stał się dla mnie zbawieniem.\nUwielbiajmy Pana. /x2\n\n" +
                "2. Pijcie wodę ze zdrojów zbawienia,\nchwalcie Go i wzywajcie Jego Imienia.\n" +
                "Wspaniałe jest Jego Imię, on uczynił wielkie rzeczy.\nUwielbiajmy Pana. /x2\n\n" +
                "3. Krzycz i wołaj z radości, mieszkanko Syjonu,\nbo wielki jest pośród ciebie Święty Izraela,\n" +
                "albowiem Jahwe jest wielki.Jego łaska nad nami na wieki.\nUwielbiajmy Pana. /x2\n\n",
        "Uwielbiam Cię, błogosławię Cię,\nSwym Duchem ogarnij mnie\nUwielbiam Cię, chcę widzieć twoją świętą twarz.\nI w miłość Twoją wtopić się.\n\n",
        "1. Uwielbiam Cię,\nPanie Boże, mój Ojcze!\nTobie chwała, cześć i moc!\nWielbię Cię! Pragnę głosić Twą chwałę!\nUwielbiam święte Imię Twe!\n\n" +
                "Ref. Uwielbiam Cię Panie! Wielbić chcę Ciebie!\nNiech popłynie z serca chwały pieśń!\n" +
                "Uwielbiam Cię, Tyś jedynym jest Bogiem!\nPragnę dzisiaj wielbić Cię!\n\n" +
                "2. Uwielbiam Cię,\nPanie Jezu, mój Zbawco!\nTobie chwała, cześć i moc!\nWielbię Cię! Pragnę głosić Twą chwałę!\nUwielbiam święte Imię Twe!\n\n" +
                "3. Uwielbiam Cię,\nDuchu Święty, mój Panie!\nTobie chwała, cześć i moc!\nWielbię Cię! Pragnę głosić Twą chwałę!\nUwielbiam święte Imię Twe!\n\n",
        "Uwielbiam Imię Twoje, Panie.\nWywyższam Cię i daję Ci hołd.\nW przedsionku chwały Twej staję,\nz radością śpiewam Ci pieśń:\n" +
                "„O Panie Jezu, chcę wyznać, że\nJa kocham Ciebie, Ty zmieniasz mnie.\nChcę Ci dziękować dziś ze wszystkich sił.\nDajesz mi siebie, bym na wieki żył”.\n\n",
        "Uwielbiam Twoje Imię, uwielbiam Twoje Serce.\nUwielbiam Święte Rany zadane w krwawej męce.\n" +
                "Uwielbiam Twoją drogę i z Krzyża siedem słów.\nSławię Twoją Miłość, o Zbawco mój.\n\n",
        "1. Ty jesteś święty, najwyższy Boże,\nTy jesteś mocny, Ty jesteś wielki.\nTyś jest Najwyższy, Ty Wszechmogący,\nTy Ojcze Święty, Królu Nieba.\n\n" +
                "2. Ty jesteś Trójcą, jedyny Panie,\nTy jesteś dobro, wszelkie dobro.\nTyś jest miłością, Ty jesteś prawdą,\nTyś jest pokorą, Ty jesteś nadzieją.\n\n" +
                "3. Ty jesteś pięknem, Tyś jest pokojem,\nTy bezpieczeństwem, chwałą i radością.\nTyś jest nadzieją, sprawiedliwością,\nTyś jest umiarem i wszelkim bogactwem.\n\n" +
                "4. Ty jesteś Stróżem, Tyś łagodnością,\nTyś jest obroną, Ty jesteś mocą.\nTyś jest miłością, wiarą, nadzieją,\nTy jesteś całą naszą słodkością.\n\n" +
                "5. Ty jesteś życiem i wieczną chwałą,\no wielki Panie, przedziwny Boże.\nO wszechmogący, o Stworzycielu,\no miłosierny Zbawicielu.\n\n",
        "Venite, exultemus Domino,\nvenite adoremus.\nVenite, exultemus Domino,\nvenite adoremus.\n\n(Przyjdźcie, radośnie śpiewajmy Panu,\nprzyjdźcie, uwielbiajmy.)\n\n",
        "1. Stworzycielu, Duchu przyjdź,\nnawiedź dusz wiernych Tobie krąg.\nNiebieską łaskę zesłać racz\nsercom, co dziełem są Twych rąk.\n\n" +
                "2. Pocieszycielem jesteś zwan\ni Najwyższego Boga dar.\nTyś namaszczeniem naszych dusz,\nzdrój żywy, miłość, ognia żar.\n\n" +
                "3. Ty darzysz łaską siedemkroć,\nbo moc z prawicy Ojca masz.\nPrzez Ojca obiecany nam,\nmową wzbogacasz język nasz.\n\n" +
                "4. Światłem rozjaśnij naszą myśl,\nw serca nam miłość świętą wlej.\nI wątłą słabość naszych ciał,\npokrzep stałością mocy swej.\n\n" +
                "5. Nieprzyjaciela odpędź w dal\ni Twym pokojem obdarz wraz.\nNiech w drodze za przewodem Twym,\nminiemy zło, co kusi nas.\n\n" +
                "6. Daj nam przez Ciebie Ojca znać,\ndaj, by i Syn poznany był.\nI Ciebie, jedno Tchnienie Dwóch,\nniech wyznajemy z wszystkich sił.\n\n" +
                "7. Niech Bogu Ojcu chwała brzmi,\nSynowi, który zmartwychwstał.\nI Temu, co pociesza nas,\nniech hołd wieczystych płynie chwał.\nAmen.\n\n",
        "W ciemności idziemy, w ciemności,\ndo źródła Twojego życia.\nTylko pragnienie jest światłem,\ntylko pragnienie jest światłem.\n\n",
        "W cieniu Twoich rąk, ukryj proszę mnie.\nGdy boję się, gdy wokół mrok,\nbądź światłem, bądź nadziei dniem.\nWszystkim o czym śnię, głosem w sercu mym,\n" +
                "jak ręka, która trzyma mnie.\nNad brzegiem nocy, brzegiem dni,\nbądź jak skrzydła dwa, kiedy braknie sił.\n\n" +
                "Chwyć mnie i nieś,\nniech niebo bliżej będzie.\nTak bardzo chcę, w ramionach skryć się Twych.\n\n",
        "Gdy staniesz u wrót kwitnącej doliny,\nw zapachu mięty i gorzkich ziół.\nNie szukaj słów płochych jak sarny,\nani czułych spojrzeń. /x2\n" +
                "Uwięziła je mgła.\nRaczej próbuj pamiętać o tych,\nnad przepaścią wsłuchaj się w ich lęk,\non znieczula, choć łka.\n\n",
        "1. W drogę z nami wyrusz Panie,\nnam nie wolno w miejscu stać.\nGdy zbłądzimy, podaj rękę,\nGdy upadniemy, pomóż wstać.\n\n" +
                "Ref. I do serca swego prowadź, prowadź nas! /x2\n\n" +
                "2. Zabierz smutek, przywróć radość,\nosłabionym dodaj sił,\nbyśmy innym nieść pomogli\nciężar krzyża przez ten świat.\n\n" +
                "3. Poprzez piachy, ostów kolce,\nz nami idź do niebios bram.\nPo pustyniach zabłąkanym,\nwody swej zaczerpnąć daj.\n\n" +
                "4. Gdy do Pana odejdziemy,\nniech nie płacze po nas nikt,\nbo my przecież z Nim być chcemy,\nw Jego chwale wiecznie żyć.\n\n",
        "1. W kruszynie chleba Panie jesteś,\nschowany wielki Ty, Pan.\nPrzychodzisz do nas już przez wieki,\nby ofiarować siebie nam.\n\n" +
                "2. W tych kroplach wina Twoja Krew,\nprzelana za nas Panie mój.\nTak wiele jej na Krzyżu było,\nby odkupić nas od zła.\n\n" +
                "3. Ty Panie, Miłością jesteś.\nTy Panie, kochasz nas.\nI proszę Ciebie by tak było,\nwciąż przez wiele lat.\n\n",
        "1. W jasny dzień pewnego dnia,\nspotkałem Ciebie, Panie.\nNa skraju lasu, w szumie traw,\nsłyszałem Twoje Imię.\n" +
                "Nim promień słońca padł mi w twarz,\npowstało to pytanie.\nPragnienie we mnie zrodziło się,\nby szukać Ciebie wciąż.\n\n" +
                "Ref. Więc zostań ze mną, Panie mój,\nna chwilę jeszcze jedną.\nI pomóż mi zrozumieć sens życia mego.\n" +
                "Więc zostań ze mną, Panie mój\ni ukaż Swe oblicze.\nNiech słodycz, Jezu, Twoich słów,\nprzemieni moje życie.\n\n" +
                "2. Tak wielu ludzi nie zna Cię,\nchoć pragnie Ciebie poznać.\nBrakuje ciągle uczniów Twych,\nby głosić Ewangelię.\n" +
                "A ja wciąż błądzę, nie widzę dróg,\nnie słyszę Twego głosu.\nGdy wołasz do mnie, Panie mój,\npójdź za mną łowić dusze.\n\n" +
                "3. Biegnę ścieżką pośród pól,\nszeleszczą wokół zboża.\nPrzede mną kroczy stwórca, Pan,\nrozsiewa miłość swoją.\n" +
                "Ta ścieżka mnie prowadzi tam,\ngdzie Pan mój na mnie czeka.\nJak bardzo Boże pragnę być,\nw objęciach Twego Syna.\n\n",
        "W lekkim powiewie przychodzisz do mnie, Panie. /x2\nNie przez wicher ogromny i nie przez ogień,\n" +
                "ale w lekkim powiewie przychodzisz do mnie,\nale w lekkim powiewie nawiedzasz duszę mą.\n\n",
        "W mocy Słowa zstępujesz,\npłomieniem ognia serce obejmujesz.\nJak można wątpić, że jesteś? /x2\n\n",
        "W naszych ciemnościach,\no Panie, rozpal ogień, który nigdy nie zagaśnie.\nW naszych ciemnościach,\no Panie, rozpal ogień, który nigdy nie zagaśnie.\n\n",
        "Ref. Miłosierdzie Twoje, Panie,\njest jak życiodajny deszcz,\nw miłosierdziu Twoim, Panie, dotknąć nas chcesz.\n" +
                "Miłosierdzie Twoje, Panie, większe niż grzech,\nnie pokona go, nie zniszczy sama śmierć!\nZatańczmy w rytmie łaski, zatańczmy w rytmie łaski.\n\n" +
                "1. Życie jest piękne i każdy to wie,\nlecz trudno przejść przez nie, nie brudząc się.\n" +
                "Ty, mimo wszystko, miłujesz wciąż nas,\nmiłości Twojej to czas.\n\n" +
                "2. Widzę w Twych oczach wciąż miłości żar,\nnie ma w nich gniewu, wyrzutów i kar.\n" +
                "Ty przebaczenie darować nam chcesz,\nBogiem miłości się zwiesz.\n\n" +
                "3. Ty, jak baranek pędzony na rzeź,\nbity, męczony, nasz grzech chciałeś nieść.\n" +
                "Miłość na Krzyżu przytrzymała Cię,\nMiłość, co usuwa lęk.\n\n",
        "W swoim wielkim miłosierdziu\nBóg nas zrodził do nadziei /x2\ndo wielkiej nadziei\n\n",
        "W Tobie jest światło, każdy mrok rozjaśni.\nW Tobie jest życie, ono śmierć zwycięża.\nUfam Tobie, Miłosierny,\nJezu, wybaw nas!\n\n",
        "Intro\n\n1. Nasz Ojcze nieskończony,\nwszystko stworzyłeś Ty,\nWszechmogący.\nŚwiętego Ducha mocą,\nPoczęty został Syn\nJezus, nasz Zbawca.\n\n" +
                "Ref. Tak, ja wierzę w Boga Ojca,\nw Syna, którym Jezus jest.\nWierzę też w Świętego Ducha,\nBóg jest w osobach trzech.\n" +
                "Wierzę także w Zmartwychwstanie,\nże powstaniemy znów.\nWyznaję to, wierzę w imię Jezus.\n\n" +
                "2. Nasz Sędzio, nasz Obrońco,\ncierpiałeś za nasz grzech,\na nam przebaczono.\n" +
                "Ty zszedłeś do ciemności\ni w chwale wzniosłeś się.\nZająłeś w niebie tron.\n\n" +
                "Bridge: Wierzę, że Bóg jest!\nWierzę, że pokonał śmierć!\nWierzę, że to Chrystus Panem jest./x2\n\n" +
                "Ref. Wierzę, że jest życie wieczne\nŻe na ziemię przyszedł Bóg\nWierzę we wspólnotę świętych\n" +
                "I święty Kościół Twój\nWierzę w nasze zmartwychwstanie\nGdy Jezus przyjdzie znów\nWyznaję to, wierzę w imię Jezus.\n\n",
        "1. Niepojęty jesteś, wielki i święty Boże.\nTyle łaski dla nas masz.\nWidzisz każde serce, wszystko czynić możesz, więc\nprzemień Panie nas.\n" +
                "Daj nam serce nowe, napój nas wodą życia,\nniech obmyje z oczu łzy.\nCiągle nas zadziwiasz, ciągle nas zachwycasz.\nDzięki Panie, dzięki Ci.\n\n" +
                "Ref. Wciąż mnie zadziwiasz Panie,\nmiłością swoją dla mnie tak,\nbliskim i jak serca cichy rytm.\n" +
                "Wciąż mnie zadziwiasz sobą,\ncodziennie i na nowo jak,\nwciąż ten sam, lecz zawsze inny świt.\n\n" +
                "2. Tak łagodnie wołasz, by stanąć w Krzyża cieniu.\nSpojrzeć w górę poza mrok.\nChociaż noc dokoła,\n" +
                "wszystko może zmienić,\njeden zadziwiony krok.\n\nWciąż mnie zadziwiasz, siłą swej miłości Panie,\n" +
                "bezmiarem łaski, co podnosi mnie.\nWciąż mnie zadziwiasz, siłą swej miłości Panie.\nTy jesteś ciszą, dla zmęczonych serc.\n\n",
        "Wejdźmy do Jego bram z dziękczynieniem,\nu Jego tronu oddajmy cześć.\nWejdźmy do Jego bram z uwielbieniem,\nradosną Panu śpiewajmy pieśń.\n" +
                "Rozraduj się w Nim, swym Stworzycielu.\nRozraduj się w Nim, światłości swej.\nRozraduj się w Nim, swym Zbawicielu.\nRozraduj się w Nim, wywyższać Go chciej.\n\n",
        "1. Wesel się Królowo miła,\nbo Ten, któregoś zrodziła.\nZmartwychwstał Pan nad Panami,\nmódl się do Niego za nami.\n\n" +
                "Ref. Alleluja, Alleluja!\n\n" +
                "2. Ciesz się i wesel się w Niebie,\nproś Go za nami w potrzebie.\nByśmy się też tam dostali\ni na wiek wieków śpiewali.\n\n",
        "Przyjdź do nas, Panie,\nna wesele w Kanie.\nPrzyjdź, Panie, to jest Twój czas!\nPrzemień wodę w wino,\n" +
                "uczyń nas rodziną.\nPanie Jezu, uczyń cuda w nas.\nLaj laj laj!\n\n",
        "1. Widzę nowe niebo i ziemię!\nMiasto święte Jeruzalem!\nOto dzisiaj zstępuje do nas z nieba!\nJego miłość na nas wylewa się!\n\n" +
                "Ref. Na skale zbudował swój Kościół\nI bramy otchłani nie przemogą go.\nNa skale zbudował swój Kościół,\nBarankowi cześć, chwała i moc.\n\n" +
                "2. Oto przybytek Boga z ludźmi,\nJest z nami Pan nasz, Emanuel.\nŁez, śmierci, żałoby już odtąd nie będzie,\nGdy mądrość Krzyża napoi Cię.\n\n",
        "1. Wielbić mego Pana chcę,bo On godzien chwały jest,\nOn uwalnia mnie sam od wrogów mych.\n\n" +
                "2. Mój Pan żyje, Opoką moją jest\ni niechaj będzie wywyższony mój Zbawiciel.\n" +
                "Mój Pan żyje, Opoką moją jest\ni niechaj będzie wywyższony Zbawca mój, Zbawca mój.\n\n",
        "Wielbić Pana chcę, radosną śpiewać pieśń.\nWielbić Pana chcę, On Źródłem życia jest.\n\n",
        "Wielbię Cię, całe życie Ci oddaję,\nwielbię Cię, choć uczynki moje małe.\nWielbię Cię noszę w sercu Twoje znamię,\nwielbię Cię, Ty znasz imię me na pamięć.\n" +
                "Wielbię Cię jesteś bliżej niż ja sobie,\nwielbię Cię dałeś wszystko w Swoim Słowie.\nWielbię Cię, całym sercem, duszą, ciałem,\nwielbię Cię, oto jestem dla Twej chwały.\n\n",
        "Wielbij Pana, śpiewaj hymn.\nNiech żyje wywyższony Król.\nUwielbiaj Go, najwyższy Pan.\n" +
                "On godzien chwały jest.\nWielbij Go, najwyższy Pan.\nOn godzien chwały jest.\n\n",
        "1. Wielbij, wielbij Go, wielbij pieśnią swą.\nWielbij, wielbij Go, wielbij cały dzień.\n\n" +
                "Ref. Bowiem Pan jest godzien,\ngodzien przyjąć chwały pieśń. /x2\n\n" +
                "2. Wielbij, wielbij Go, wielbij sercem swym,\nwielbij, wielbij Go, wielbij z wszystkich sił.\n\n" +
                "3. Wielbij, wielbij Go, wielbij życiem swym.\nWielbij, wielbij Go, Imię Pana głoś.\n\n",
        "Wielbimy Cię, Najświętszy Panie, Jezu Chryste,\ntu i we wszystkich kościołach Twoich,\n" +
                "które są na całym świecie, i wysławiamy Cię,\nże przez Krzyż i mękę swoją odkupiłeś świat.\n\n",
        "Wielce należy kochać miłość Tego,\nktóry nas wielce umiłował.\n\n",
        "Ref. Wiele jest serc, które czekają na Ewangelię,\nwiele jest serc, które czekają wciąż.\n\n" +
                "1. Napełnij serce swoje tym kosztownym nasieniem,\na zobaczysz, że Bóg poprowadzi cię do ludzi.\nA zobaczysz, że Bóg poprowadzi cię do ludzi.\n\n" +
                "2. Sam zobaczysz, że Bóg poprowadzi cię do ludzi,\nktórych będziesz mógł zaprowadzić do Chrystusa.\nKtórych będziesz mógł zaprowadzić do Chrystusa.\n\n",
        "1. Wielkie i dziwne są dzieła Twoje,\nPanie, Boże Wszechmogący ,\ni sprawiedliwe są drogi Twoje, Królu narodów.\n\n" +
                "2. Któż by się nie bał Ciebie, o Boże,\ni nie uwielbił Twego imienia,\ngdyż sprawiedliwe są sądy Twoje, Królu narodów.\n\n" +
                "3. Toteż przyjdą wszystkie narody\ni oddadzą Tobie pokłon,\ngdyż sprawiedliwe są sądy Twoje, Królu narodów.\n\n",
        "1. Wiem, że wierzysz w miłość,\nchoć mówisz nie ma, nie ma jej.\nWierzyć chcesz, że jest.\n" +
                "Wiem, że kochasz dom,\nchoć serce po sercu gubisz to.\nWiem, że chcesz go mieć.\n\n" +
                "Ref. Jutro jest pełne nadziei,\nnie myśl, że niczego nie zmienisz.\nCzy pamiętasz, obudź się, chodź,\nskończyła się już noc.\n\n" +
                "2. Widziałeś, jak umieram,\nwidzisz, jak zmieniam się.\nZ całych sił trzeba chcieć,\n" +
                "w miłość wierzysz i dom,\nchoć serce po sercu gubisz to.\nWierzyć chcesz, że jest.\n\n",
        "1. Wierzę w Ciebie, Panie, coś mnie obmył z win.\nWierzę, że człowiekiem stał się Boży Syn.\nMiłość Ci kazała Krzyż na plecy brać.\n" +
                "W tabernakulum zostałeś, aby z nami trwać.\nJesteś przewodnikiem nam do wieczności bram.\nTam przygarniesz nas do siebie.\n\n" +
                "2. Tyś jest moim życiem, boś Ty żywy Bóg.\nTyś jest moją drogą, najpiękniejszą z dróg.\nTyś jest moją Prawdą, co oświeca mnie.\n" +
                "Boś odwiecznym Synem Ojca,który wszystko wie.\nNic mnie nie zatrwoży już wśród najcięższych burz.\nBo Ty, Panie, jesteś ze mną.\n\n" +
                "3. Tyś jest moją siłą, w Tobie moja moc.\nTyś jest mym ratunkiem w najburzliwszą noc.\nTyś jest mym ratunkiem, gdy zagraża toń.\n" +
                "Moją słabą ludzką rękę ujmij w swoją dłoń.\nZ tobą przejdę poprzez świat, w ciągu życia lat,\ni nic złego mnie nie spotka.\n\n" +
                "4. Tobie, Boże, miłość, wiarę swoją dam.\nW Tobie, Synu Boży, ufność swoją mam.\nDuchu Święty, Boże, w serce moje zstąp.\n" +
                "I miłości Bożej ziarno rzuć w me serce, w głąb.\nDuszy mojej rozpal żar, siedmioraki dar,\ndaj mi stać się Bożą rolą.\n\n",
        "Więcej miłości, więcej mocy,\nWięcej Ciebie w życiu mym. /x2\nBo całym sercem swoim uwielbiam Ciebie.\n" +
                "Całą duszą swoją uwielbiam Ciebie.\nCałym życiem swoim uwielbiam Ciebie.\nTyś Panem mym jest, Panem moim jest.\n\n",
        "1. Witaj, Pokarmie, w którym niezmierzony,\nnieba i ziemie Twórca jest zamkniony.\nWitaj Napoju zupełnie gaszący,\numysł pragnący.\n\n" +
                "2. Witaj Krynico wszystkiego dobrego,\ngdy bowiem w sobie masz Boga samego.\nZnasz ludziom wszystkie jego wszechmocności,\nniesiesz godności.\n\n" +
                "3. Witaj z niebiosów Manno padająca,\nrozkoszny w sercu naszym smak czyniąca.\nWszystko na świecie, co jedno smakuje,\nw tym się najduje.\n\n" +
                "4. Witaj rozkoszne z ogrodu rajskiego,\nDrzewo owocu pełne żywiącego.\nKto Cię skosztuje, śmierci się nie boi,\nchoć nad nim stoi.\n\n" +
                "5. Witaj jedyna serc ludzkich Radości,\nwitaj strapionych wszelka łaskawości.\nCiebie dziś moje łzy słodkie szukają,\nk’Tobie wołają.\n\n",
        "1. Witaj, Gwiazdo Morza - chwalmy na wieki Go!\nWielka Matko Boga - chwalmy na wieki Go!\nPanno zawsze czysta - chwalmy na wieki Go!\n" +
                "Bramo niebios błoga - chwalmy na wieki Go!\nTy, coś Gabriela -\nSłowem przywitana -\nUtwierdź nas w pokoju -\nOdmień Ewy miano -\n\n" +
                "Ref. Módl się za nami Matko,\nByśmy trwali przy Twoim Synu\nI chwalili na wieki Go.\n\n" +
                "2. Winnych wyzwól z więzów -\nŚlepym powróć blaski -\nUproś wszelkie łaski -\nOddal nasze nędze -\n" +
                "Okaż, żeś jest Matką -\nWzrusz modłami swymi -\nTego, co Twym Synem -\nZechciał być na ziemi –\n\n" +
                "3. Dziewico sławna -\nI pokory wzorze -\nWyzwolonym z winy -\nDaj nam żyć w pokorze -\n" +
                "Daj wieść życie czyste -\nDrogę ściel bezpieczną -\nWidzieć daj Jezusa -\nMieć w Nim radość wieczną –\n\n" +
                "4. Bogu Ojcu chwała -\nChrystusowi pienie -\nObu z Duchem Świętym -\nJedno uwielbienie –\n\n",
        "1. Witaj, Najczystsza Królowo serc,\nEwo depcząca szatana i grzech.\nPowtórz w nas noc Betlejemską.\nMatko Jezusa, Maryjo!\n\n" +
                "2. Witaj, Niewiasto w koronie z gwiazd,\nPsalmie zwycięstwa nad śmiercią i złem.\nPowtórz w nas dzień Zmartwychwstania,\nMatko Jezusa, Maryjo!\n\n" +
                "3. Witaj, Promienna Jutrzenko dnia,\nPani cudownej przemiany i łask.\nPowtórz w nas cud Galilejski,\nMatko Jezusa, Maryjo!\n\n",
        "Ref. Wody Jordanu weselcie się,\nnasz Zbawiciel przychodzi.\nWoda Życia, Zdrój na pustyni.\n\n" +
                "1. Jeśli ktoś jest spragniony,\na wierzy we Mnie,\nniech przyjdzie do mnie i pije.\nStrumienie wody żywej popłyną z jego wnętrza.\n\n" +
                "2. Kto będzie pił wodę, którą Ja mu dam,\nnie będzie pragnął na wieki.\n" +
                "Lecz woda, którą Ja mu dam, stanie się w nim źródłem wody\nwytryskującej ku życiu wiecznemu.\n\n" +
                "3. Bo rozleję wody po spragnionej glebie\ni zdroje po wyschniętej ziemi.\n" +
                "Przeleję Ducha mego na twoje plemię\ni błogosławieństwo moje na twoich potomków.\n\n" +
                "4. Pan cię zawsze prowadzić będzie,\nnasyci duszę twoją na pustkowiach.\n" +
                "Odmłodzi twoje kości tak, że będziesz jak zroszony ogród\ni jak źródło wody, co się nie wyczerpie.\n\n",
        "Ref. Wprowadź mnie w komnaty Twoje\nPobiegnijmy, pociągnij mnie za sobą\nCieszyć się sobą będziemy\nI nad wino sławić miłość Twą.\n\n" +
                "1. O ty, którego miłuje dusza moja,\nwskaż, gdzie pasiesz swoje stada.\nAbym się nie błąkała wśród stad Twych towarzyszy.\n" +
                "Jeśli tego nie wiesz, o najpiękniejsza z niewiast,\npójdź za śladami trzód\nI paś koźlęta twe, przy szałasach pasterzy.\n\n" +
                "2. O, jak piękny jesteś miły mój,\no jakże jesteś uroczy\nZ radością wejdę w łoże Twe, ukryte w zieleni.\n" +
                "O, jak piękna jesteś przyjaciółko ma,\noczy twe jak gołębice.\n\n" +
                "3. Oczarowałaś me serce jednym spojrzeniem twych oczu.\nPragnę spocząć w Twoim cieniu,\nowoce Twe są słodyczą mej duszy.\n" +
                "Wprowadź mnie do domu wina, okryj mnie swoją miłością,\nPosil mnie i pokrzep,\n" +
                "bo jestem chora z miłości.\nObudź mnie kochany mój, pragnę z całego serca!\n\n",
        "Wspaniały Dawco miłości,\nskładamy na Twoim stole\nwszystko, co mamy, wszystko, co mamy,\nchoć i tak to od wieków jest Twoje.\n\n",
        "Ref. Wspaniały jest majestat Twój. /x2\nPiękne jest Twoje oblicze, o Panie.\nWspaniały jest majestat Twój.\n\n" +
                "1. Będę Cię wielbił Panie i Boże (o mój Jezu).\nPo wieczne czasy wysławiał Cię będę (o mój Jezu).\n" +
                "Bo Twoja chwała wciąż nad nami (ciągle jest).\nWspaniały jest majestat Twój (z nami jest).\n\n" +
                "2. Jedyną drogą mnie prowadzisz,\nnie będę już więcej kroczył po bezdrożach.\n" +
                "Właściwą ścieżkę ukazujesz mi.\nWspaniały jest majestat Twój.\n\n" +
                "3. Małżonka moja śpiewać dla Ciebie będzie, o Panie.\nI cały mój dom, i cały mój dom, i cały mój dom,\n" +
                "radował się będzie, się będzie radował.\nWspaniały jest majestat Twój.\n\n",
        "Ref. Wstanę, po mieście chodzić będę,\nwśród ulic i placów.\nKochanka mej duszy szukać będę,\nwśród ulic i placów.\n\n" +
                "1. Cicho! Ukochany mój! Oto On, oto nadchodzi,\nBiegnie przez góry, skacze po pagórkach,\n" +
                "Podobny do gazeli.\nPowstań ma przyjaciółko, Piękna ma i pójdź.\n\n" +
                "2. Oto stoi za mym murem, patrzy przez okno, zagląda przez kraty.\n" +
                "Miły mój odzywa się, On do mnie mówi.\nPowstań ma przyjaciółko, Piękna ma i pójdź.\n\n" +
                "3. Minęła już zima, deszcz ustał, na ziemi widać kwiaty.\n" +
                "Winne krzewy już pachną, trzeba przyciąć winnicę.\nPowstań ma przyjaciółko, Piękna ma i pójdź.\n\n" +
                "4. Gołąbko ma ukryta w szczelinach przepaści, W zagłębieniach skały,\n" +
                "Słodki jest twój głos, jesteś pełna wdzięku.\nPowstań ma przyjaciółko, Piękna ma i pójdź.\n\n",
        "Ref. Wszechmogący Panie,\nNajwyższy Tyś jest.\nTwoja cześć i chwała,\nsława i błogosławieństwo.\n\n" +
                "1. Bądź pochwalony Panie z Twymi stworzeniami,\nszczególnie z bratemsłońcem, z którymdzień się staje.\n" +
                "Ono jest piękne i świecące blaskiem,\nTwoim, Najwyższy, jest wyobrażeniem.\n\n" +
                "2. Przez brata księżyc pochwalon bądź Panie.\nPrzez brata wiatr, powietrze i chmury.\n" +
                "Przez każdy czas i poprzez pogodę,\nprzez które Twym stworzeniom dajesz utrzymanie.\n\n" +
                "3. Przez siostrę wodę pochwalon bądź Panie,\nktóra jest czysta pokorna i cenna.\n" +
                "Przez brata ogień, radosny i piękny,\nkrzepki i mocny, którym noc rozświetlasz.\n\n" +
                "4. Przez siostrę ziemię pochwalon bądź Panie,\nktóra jest matką, co żywi i chowa.\n" +
                "Bądź pochwalony przez wybaczających,\nznoszących słabość i prześladowanych.\n\n" +
                "5. Przez siostrę śmierć pochwalon bądź Panie,\nktórej nikt z żywych uniknąć nie zdoła.\n" +
                "Błogosławieni żyjący w Twej woli,\nbowiem śmierć druga im nie wyrządzi krzywdy.\n\n",
        "1. Wszystkie moje troski i kłopoty,\nw Twoje ręce składam, Panie mój.\nBardzo cieszę się, że właśnie nas wybrałeś,\nbyśmy idąc, nieśli Imię Twe.\n\n" +
                "2. Zawsze chciałem zostać Apostołem,\nbyło to pragnienie w sercu mym.\nGdy odejdziesz, to napiszemy Ewangelię,\naby Imię Twe znał cały świat.\n\n",
        "Wszystkie narody klaskajcie w dłonie!\nWykrzykujcie Bogu radosnym głosem,\nbo Pan Najwyższy\njest Królem nad całą Ziemią! /x2\n\n" +
                "Ref. Śpiewajcie wszystkie narody,\nśpiewajcie radosnym głosem,\nśpiewajcie Królowi!\n\n" +
                "Bridge. Bo Bóg króluje nad narodami,\nBóg zasiada na swym świętym tronie.\nGdyż Bóg jest Królem całej ziemi,\nwięc hymn śpiewajcie, śpiewajcie.\n\n",
        "1. Wszystkie nasze dzienne sprawy\nprzyjm litośnie Boże prawy,\na gdy będziem zasypiali,\nniech Cię nawet sen nasz chwali\n\n" +
                "2. Twoje oczy obrócone,\ndzień i noc patrzą w tę stronę,\ngdzie niedołężność człowieka,\nTwojego ratunku czeka.\n\n" +
                "3. Odwracaj nocne przygody,\nod wszelakiej broń nas szkody.\nMiej nas zawsze w swojej pieczy\nStróżu i Sędzio człowieczy.\n\n" +
                "4. A gdy już niebo osiądziem,\nTobie wspólnie śpiewać będziem.\nBoże w Trójcy niepojęty,\nŚwięty, na wiek wieków Święty.\n\n" +
                "5. Najświętsza Matko droga,\nproś za nami w niebie Boga.\nProś i czuwaj wciąż nad nami,\nze świętymi aniołami.\n\n" +
                "6. A za dzień Ci dziękujemy,\no szczęśliwą noc prosimy.\nByś nam zawsze błogosławił,\na po śmierci duszę zbawił.\n\n",
        "Wszystko, co żyje, niech uwielbia Cię.\nTobie, Panie, najwyższy tron, nad całym dziełem Twym.\nTu, teraz, wszędzie, dokąd poślesz nas.\n" +
                "Tobie, Panie, najwyższy tron. Niech usłyszy świat.\nJesteś naszym Wodzem zbrojnym w światłości miecz.\nKruszysz siły ciemności, do walki prowadząc nas.\n" +
                "Dzięki Krwi Chrystusa, w zwycięstwie będziemy trwać.\nTobie, Panie, najwyższy tron,\nniech wszystko odda Ci hołd.\n\n",
        "Wszystko mogę w Tym, który mnie umacnia. /x2\nJezus, On mnie umacnia. /x2\n\nRozlewaj swoją chwałę, potęgę i moc.\nNa miasto Twoje świętę, potęgę i moc.\n\n",
        "1. Wszystko Tobie oddać pragnę\ni dla Ciebie tylko żyć.\nChcę Cię, Jezu, kochać wiernie,\ndzieckiem Twoim zawsze być!\n\n" +
                "Ref. Serce moje weź, niech Twą śpiewa cześć.\nSerce moje, duszę moją, Panie Jezu, weź!\n\n" +
                "2. Wszystko Tobie oddać pragnę\nod najmłodszych moich lat.\nPomóż, Jezu, by mnie nie zwiódł\npokusami swymi świat.\n\n" +
                "3. Wszystko Tobie oddać pragnę,\nw duszy czuję święty żar.\nTo Ty dajesz dziecku swemu\nTwojej łaski Boży dar.\n\n",
        "Wy jesteście na ziemi światłem mym.\nWy jesteście na ziemi światłem mym.\nAby ludzie widzieli dobre czyny w was\ni chwalili Ojca, który w niebie jest. /x2\n\n",
        "Wy jesteście solą ziemi.\nWy jesteście światłem świata.\n\n",
        "Wyciągnij ręce, bym dał Ci żar.\nOn będzie światłem na drodze twej.\nCzyny miłości do braci mych\nCię poprowadzą przez krzyża szczyt.\n\n" +
                "Ref. Tam Mnie odnajdziesz, spotkasz w swym krzyżu,\nDla innych ludzi światłem staniesz się.\n" +
                "Doświadczysz łaski, przemiany serca,\nJeśli na innych sercem spojrzysz swym.\n\n",
        "1. Zewsząd znosimy cierpienia,\nLecz nie poddajemy się zwątpieniu.\nŻyjemy w niedostatku, żyjemy w ubóstwie,\nLecz dalecy dziś jesteśmy od rozpaczy.\n" +
                "Znosimy prześladowania,\nLecz nie czujemy się samotni.\nPowalają nas na ziemię, a my, my nie giniemy.\n\n" +
                "Ref. Jesteśmy wydani, wydani na śmierć,\nAby życie Jezusa w nas się objawiło.\nJesteśmy wydani, wydani na śmierć.\nAby życie Jezusa w nas się objawiło.\n\n" +
                "2. Nosimy nieustannie w naszym ciele\nKonanie Jezusa, Jego śmierć\nAby życie Jezusa\nObjawiło się w naszym ciele.\n" +
                "Ciągle bowiem jesteśmy wydawani\nNa śmierć z powodu Jezusa.\nAby życie Jezusa objawiło się w naszym,\nŚmiertelnym ciele!\n\n" +
                "3. Tak więc działa w nas śmierć,\nAby życie mogło z nas wytrysnąć\nPrzeto cieszę się\nOwym duchem wiary i mówię:\n" +
                "„Jestem przekonany, że Ten,\nKtóry wskrzesił Jezusa,\nZ Jezusem przywróci życie\nTakże mi!”\n\n",
        "Ref. Wykrzykujcie Bogu, wykrzykujcie Królowi,\nklaszczcie w dłonie i hymny śpiewajcie. /x2\n\n" +
                "1. Pan Najwyższy, straszliwy\n- jest Królem całej ziemi.\nOn poddaje nam narody\n- jest Królem całej ziemi.\n" +
                "Ludy rzuca pod stopy\n- jest Królem całej ziemi,\nBóg Abrahama.\n\n" +
                "2. Wybrał dla nas dziedzictwo, -\nBo nas bardzo miłuje. -\nŚpiewajcie Mu, śpiewajcie.-\n\n" +
                "3. On zasiada na tronie,-\nMożni świata hołd mu złożą, -\nBo do Niego należą-\n\n",
        "Ref. Wykrzykujcie na cześć Pana\nwszystkie ziemie.\nWśród okrzyków i radości\nwysławiajcie Go!\n\n" +
                "1. Wiedzcie, że Pan jest Bogiem,\nOn sam stworzył nas.\nJesteśmy jego ludem.\njesteśmy jego własnością.\n\n" +
                "2. W jego bramy wstępujcie z dziękczynieniem,\nwśród hymnów w Jego przedsionki.\nBłogosławcie Imię Jego.\nChwalcie Pana dziś.\n\n" +
                "3. Albowiem dobry jest Pan,\nłaskawość jego na wieki.\nAlbowiem dobry jest Pan.\nJego wierność przez pokolenia.\n\n",
        "Nie bój się, wypłyń na głębię,\njest przy tobie Chrystus.\n\n",
        "Wysławiajcie Pana, ooo,\nWysławiajcie Pana, ooo.\nŚpiewaj Panu cała ziemio,\nAlleluja, Alleluja.\n\n",
        "1. Wysłuchajcie mego głosu,\nczyńcie to, co nakazuję,\na będziecie ludem moim, przez cały czas.\n" +
                "Gdy na Moje zawołanie\nnie odpowie żaden z was,\nwtedy swoją twarz odwrócę na długi czas.\n\n" +
                "Ref. Jeśli nie wysłuchacie Mnie,\nw ukryciu płakał będę.\nDo niewoli poślę was, trzodo ma.\n\n" +
                "2. Dziś odrzućcie wasze złości,\nobrzydliwość w sercach waszych.\nZawróć do Mnie Izraelu, mówi Pan.\n" +
                "Twoje złe postępowanie,\nbrak bojaźni i pokuty\nugodziły w serce Moje, ludu Mój.\n\n" +
                "3. Słowa me poczytujecie,\njako przedmiot drwiny waszej.\nBrak wam w nich upodobania, brak też czci.\n" +
                "Jeśli szczerze naprawicie\nwasze drogi, czyny złe,\nsprawię, że znów powrócicie na ziemie swe.\n\n",
        "Wystarczy byś był, Jezu, nic więcej,\ntylko byś był, nic więcej,\nby Twoje skrzydła otuliły mnie.\n\n",
        "1. Wystarczy iskra, by rozpalić wielki ogień.\nOd jego żaru już wokoło wszystko płonie.\n\n" +
                "Ref. I tak z miłością Bożą jest,\ngdy raz doświadczysz jej.\nRozgłaszać chcesz każdemu ją,\nkażdemu dać ją chcesz.\n\n" +
                "2. Cudownie wiosną jest, gdy kwitną wszystkie drzewa.\nPtak nuci swoją pieśń, kwiat płatki rozpościera.\n\n" +
                "Ref. I tak z miłością Bożą jest,\ngdy raz doświadczysz jej.\nChcesz śpiewać jej wiosenną treść,\nkażdemu dać ją chcesz.\n\n" +
                "3. Chcę przyjacielu mój, byś w Bogu znalazł szczęście.\nNa Niego zawsze licz, nieważne, gdzie twe miejsce.\n" +
                "Ref. Chcę krzyczeć to z wierzchołków gór,\nchcę, aby poznał świat.\nMiłości Pan dziś do mnie wszedł,\nkażdemu chcę Go dać.\n\n",
        "Wywyższony, zawsze będziesz wywyższony,\nJesteś godzien honoru i czci (honoru).\nZawsze będę stać przed Tobą, by Cię wielbić\ni chwalić przez wszystkie moje dni.\n\n",
        "Wznieśmy swe ręce, bo nadchodzi Król.\nSkłońmy się Mu, chwały wznosząc pieśń.\nNiech ku Jego chwale uwielbienie brzmi.\nOczyszczone, uświęcone, bo On królów Król.\n\n",
        "Wznoszę ręce me wzwyż,\nz moich ust chwała brzmi,\nz sercem pełnym wdzięczności\nbłogosławić Cię chcę.\n" +
                "Błogosławić Cię chcę,\nbłogosławić Cię chcę,\nz sercem pełnym wdzięczności\nbłogosławić Cię chcę.\n\n",
        "Wzywam Cię, przyjdź, umocnij mnie,\noddal mroku cień, jasny rozpal dzień.\nTwój jest czas i świat, Pełnio Boskich Prawd.\n" +
                "Tyś natchnieniem mym i przebudzeniem,\nDuchu Święty (przyjdź).\n\n",
        "1. Wzywam Cię, Duchu, przyjdź,\nczekam wciąż byś dotknął nas.\nWołam Cię, Panie, przyjdź,\nJezu, Zbawco, do dzieci Twych.\n\n" +
                "Ref. Jak spragniona ziemia rosy dusza ma,\ntylko Ty możesz wypełnić serca głód,\nserca głód.\n\n" +
                "2. Głębio mórz, Potęgo gór,\nBoże mój, nie mogę bez Twej miłości żyć. Nie chcę bez Ciebie żyć.\n\n",
        "Z Boga jestem i do Boga\nwracam, bo w Nim jest mój dom.\nJak miłości kropla,\ndo miłości morza\n\n",
        "1. Z całego serca chcę chwalić Pana\nW radzie sprawiedliwych i na zgromadzeniu.\nWielkie są dzieła Pańskie.\nMogą ich doświadczyć, którzy je miłują.\n" +
                "Majestat i wspaniałość - Jego działanie.\nA sprawiedliwość Jego przetrwa na zawsze.\nZapewnił pamięć swoim cudom,\nPan jest miłosierny i łaskawy!\n\n" +
                "Ref. Alleluja, Alleluja, Alleluja, Alleluja!\n\n" +
                "2. Dał pokarm tym, którzy się Go boją.\nPamiętać będzie wiecznie o swoim przymierzu.\nLudowi pokazał potęgę dzieł swoich,\nOddając im posiadłości pogan.\n" +
                "Dzieła rąk Jego to wierność i sprawiedliwość.\nWszystkie przykazania Jego są trwałe,\nUstalone na wieki, na zawsze,\nNadane ze słusznością i mocą!\n\n" +
                "3. Zesłał odkupienie swojemu ludowi,\nUstanowił na wieki swoje przymierze.\nA Imię Jego jest święte i lęk wzbudza,\nBojaźń Pańska początkiem mądrości.\n" +
                "Wspaniała zapłata dla wszystkich,\nCo według niej postępują.\nSprawiedliwość Jego trwać będzie na zawsze,\nNa zawsze, na wieki!\n\n",
        "1. Kroczymy w ciemności, w labiryncie grzechu,\nIdziemy na oślep, potykamy się,\nCzekając na impuls dostrzegamy światło,\nIskra życia dotyka i rozpala nas.\n\n" +
                "Ref. Z nieba spływa łaska niczym ognisty blask,\nPrzenika do serca, napełnia miłością,\n" +
                "Naznaczeni pieczęcią, wolni od lęku,\nPorwani przez Ducha, posłani, by głosić.\n\n" +
                "2. Nasze wnętrze płonie, ogarnięte żarem,\nNatchnione uczuciem, namaszczeniem Twym,\n" +
                "Przekraczamy granice niemocy i zła,\nOtrzymawszy dary niesiemy je światu.\n\n" +
                "3. Zrodzeni przez Ducha, aby tworzyć Kościół,\nW przestrzeni Boga, czerpiemy ze źródła,\n" +
                "Zapaleni światłem, szczęśliwi heroldzi,\nŚwiadczący o Prawdzie i Dobrej Nowinie.\n\n",
        "1. Z głębokości mórz, aż po szczyty gór,\nstworzenie objawia Majestat Twój.\n" +
                "Od kolorów jesieni po wiosenny nów.\nWszystko śpiewa Ci pieśń pełną cudownych słów,\nogłaszając.\n\n" +
                "Ref. Niepojęty, niezmierzony,\ngwiazdy stworzyłeś na niebie\ni każdą z nich znasz.\nWspaniałym Bogiem Tyś jest.\n" +
                "Wszechmogący, nieskończony,\nw zachwycie klękamy przed Tobą,\nby wyznać to, że\nwspaniałym Bogiem Tyś jest.\n\n" +
                "2. Kto uwalnia błyskawic moc w czasie burz.\nKto maluje kolory porannych zórz.\n" +
                "Słońce stworzył na niebie i dał mu Swą moc.\nOrzeźwienie przynosi w gwieździstą noc.\nTylko Ty!\n\n" +
                "Ref. Niepojęty, niezmierzony,\ngwiazdy stworzyłeś na niebie\ni każdą z nich znasz.\nWspaniałym Bogiem Tyś jest.\n" +
                "Zawsze dobry, miłujący,\nznasz moje serce i mimo to,\nwciąż kochasz mnie.\nWspaniałym Bogiem Tyś jest.\n\n",
        "Ref. Z miłości umarł Pan\nPrzez Krzyż zbawienie dał\nTam Ojciec, Syn i Duch\nZ rąk śmierci wyrwał mnie!\n\n" +
                "1. Wyspy, posłuchajcie Mnie,\nludy najdalsze, uważajcie!\nPan powołał Mnie, od łona Matki\nwspomniał moje imię.\n" +
                "Ostrym mieczem uczynił me usta,\nw cieniu swojej ręki Mnie ukrył.\nStrzałę zaostrzoną uczynił ze mnie,\nUtaił mnie w swoim kołczanie.\n\n" +
                "2. Rzekł mi Pan: „Tyś Sługą moim, w tobie się rozsławię”.\nJa zaś mówiłem: „Na darmo, na nic zużyłem moje siły”.\n" +
                "Lecz moje prawo jest u Pana, moja nagroda jest u Boga mego,\nWsławiłem się w oczach Pana, Bóg mój stał się moją siłą.\n\n" +
                "3. Pan ukształtował mnie od urodzenia na swego Sługę,\nAbym nawrócił do Niego Jakuba, zgromadził Mu Izraela.\n" +
                "To zbyt mało, że gromadzisz wybranych, Sprowadzasz ocalałych z Izraela.\nBędziesz światłem dla pogan, by zbawienie me dotarło do krańców ziemi.\n\n" +
                "4. Królowie zobaczą cię i powstaną, książęta padną na swe twarze,\nPrzez wzgląd na Pana, który jest wierny, na Świętego, który Cię wybrał.\n" +
                "Tak mówi Pan do wzgardzonego, do budzącego odrazę pogan,\nDo niewolnika przemożnych, Sługi Jahwe, do Chrystusa Jezusa.\n\n",
        "Z Tobą ciemność nie będzie ciemna wokół mnie,\na noc, tak jak dzień, zajaśnieje.\n\n",
        "Z wielką ufnością i pokojem,\nmoje serce w Tobie odpoczywa,\njak dłoń w dłoni przyjaciela.\n\n",
        "Mogę iść, dokąd tylko chcę, ale Ktoś woła mnie.\nMogę siąść, wszystko jedno gdzie, ale Ktoś woła mnie:\nZa mną chodź, nie oglądaj się już wstecz,\n" +
                "Za mną chodź - wołam cię.\nZa mną chodź - nowe życie daję ci,\nZa mną chodź - kocham cię.\n\n",
        "1. Franciszku, jakżeś Ty odgadł,\nże Bóg w nieskończoność rozciągnięty,\nmieszka cichutko pod korzeniem dębu,\ndziś już spod prawa wyjęty.\n" +
                "Albo kto Ci powiedział,\nkiedyś ty po górach chodził, że Ty i kwiaty,\nże was Bóg urodził.\n\n" +
                "Ref. Zabierz mnie, pójdziemy razem,\nja się chwycę twego sznura,\nzdejmę buty, żeby lepiej czuć kamienie.\n" +
                "Zabierz mnie, pójdziemy razem,\npochodzimy sobie po górach.\nHej, lepiej czuć kamienie.\n\n" +
                "2. Dziś zaplątałeś się\nw las atomowych rakiet,\nwięc może potrafisz powiedzieć ludziom,\nże z ich sumieniem coś „na bakier”.\n" +
                "Patrzę, bo tak Ci ładnie w tym worku,\ntylko zastanawiam się,\ndlaczego Bóg od ośmiu długich wieków,\nnie dał Ci butów i worka lepszego.\n\n",
        "1. Nie ma za trudnych spraw,\nnie ma zbyt łatwych też dla Ciebie, Panie.\nTy przecież wszystko wiesz,\n" +
                "każdą potrzebę znasz,\nsłyszysz mój śmiech i płacz.\nDla Ciebie, Panie, to przecież ważne jest.\n\n" +
                "Ref. Ja wiem, że wszystko jest możliwe,\nże nie ma rzeczy bez znaczenia, dlatego modlę\nsię do Ciebie, dobry Panie.\n" +
                "I wiem, że zanim powiem słowo,\nTy, Boże, znasz już całe zdanie.\nUwielbiam Cię,Twoja wola niech się stanie.\n\n" +
                "2. Nie zaskoczę Cię, wiesz o mnie wszystko,\nwiesz, co było, znasz moją przyszłość.\nNiepojęte, że Ty, Panie, kochasz mnie, kochasz mnie.\n\n",
        "Zaprowadź mnie tam skąd powrotu nie ma\ngdzie ustaje wiara, spełnia się nadzieja\n" +
                "gdzie światłością pachnie każdy skrawek nieba,\nbo ją na swój obraz miłość wylewa.\n\n",
        "Zaśpiewać chcę miłości pieśń,\nmemu zbawcy Jezusowi.\nZa dzieła Twe wychwalam Cię\nkochany zbawco drogi Jezu.\n" +
                "Raduję się bo nazwałeś mnie swym,\nTylko z Tobą pragnę być.\nW twych ramionach się skryć,\n" +
                "w twych ramionach się skryć.\nWięc przytul mnie i ukryj mnie,\nw twych ramionach dziś.\n\n",
        "Ref. Zaśpiewajcie serca nasze:\nOto Matka Pana.\nDziś bierzemy ją do siebie:\nOto matka nasza.\n\n" +
                "1. Oblubienico\nDucha Świętego,\nBiegnijmy razem do Pana,\nOblubieniec na nas czeka.\n\n" +
                "2. Spójrzmy wszyscy na Króla w koronie,\nKtórą dała Mu Jego Matka.\nOto jest dzień naszych zaślubin,\nOto radość Jego serca.\n\n" +
                "3. Obrazie Bożej miłości,\nMario, Matko Kościoła.\nGrajcie struny weselne, Ręka\nPańska spoczęła na nas.\n\n",
        "Ref. Zaśpiewam Bogu w moim cierpieniu.\nZaśpiewam Panu w mym utrapieniu.\nJemu zaufam!\n\n" +
                "1. Ani cierpienie, ani choroby, ani prześladowania\nNie odłączą mnie od Boga mego Zbawcy.\n" +
                "Ani utrata majątku, ani zysk, ani nawet śmierć\nNie odłączą mnie od Jego miłości.\n\n" +
                "2. Moje cierpienie jest skarbem, drogocenną zdobyczą\nOfiarowaną wraz z Chrystusem za braci.\n" +
                "Jest ono także darem bratniej miłości,\nOfiarą z siebie dla innych.\n\n" +
                "3. To cierpienie jest moim przejściem do krainy życia,\nW której spotkam mego Pana, mego Zbawcę, mego Króla.\n" +
                "W tym cierpieniu razem z Chrystusem doświadczę zmartwychwstania,\nDzięki Jego powstaniu do życia!\n\n",
        "1. Jak się nie bać, powiedz jak,\nkiedy w strachu żyje świat.\nZaufaj Panu już dziś.\nJak uwierzyć, powiedz mi,\n" +
                "kiedy już nie wierzę w nic.\nZaufaj Panu już dziś.\nJak mam kochać, powiedz jak,\nkiedy rani mnie mój brat.\n" +
                "Zaufaj Panu już dziś.\nJak być dobrym, powiedz mi,\nkiedy świat jest taki zły.\nZaufaj Panu już dziś.\n\n" +
                "Ref. Zaufaj Panu już dziś!\nZaufaj Panu już dziś!\n\n" +
                "2. Jak mam walczyć, powiedz jak,\nkiedy silnej woli brak.\nZaufaj Panu już dziś.\nJak pokonać własny grzech,\n" +
                "kiedy pokus tyle jest.\nZaufaj Panu już dziś.\nJak się cieszyć, powiedz mi,\nkiedy płyną gorzkie łzy.\n" +
                "Zaufaj Panu już dziś.\nJak do ładu z sobą dojść,\nkiedy siebie mam już dość.\nZaufaj Panu już dziś.\n\n" +
                "3. Jak nie zbłądzić, powiedz mi,\nkiedy nie wiem dokąd iść.\nZaufaj Panu już dziś.\n" +
                "Jak nadzieję w sercu mieć,\nkiedy wszystko wali się.\nZaufaj Panu już dziś.\n\n",
        "Zaufałem Panu i już,\ni niczego nie muszę się lękać.\nZaufałem Panu i już,\ni niczego nie muszę się trwożyć. /x2\n" +
                "Wierzę mu,\nbo On ma moc\nprzezwyciężyć\nw moim sercu zło.\nWierzę mu,\nbo On ma właśnie taką\nmoc.\n\n",
        "Ref. Chodźcie, zawróćmy do Pana,\nOn nas rozszarpał, On nas też uleczy!\n\n" +
                "1. Zranił i opatrzył nasze rany.\nPo dwóch dniach wskrzesi nas do życia.\nTrzeciego dnia podniesie nas\ni będziemy żyli przed Jego obliczem.\n\n" +
                "2. Starajmy się więc poznać usilnie Pana.\nŻe Go znajdziemy pewnie jak zorza.\nPrzyjdzie do nas jak deszcz.\nJak późny deszcz, co zrasza ziemię.\n\n" +
                "3. Co ci mam uczynić Efraimie?\nCo ci mam uczynić Judo?\nWszak wasza miłość jest jak obłok poranny\ni jak rosa, która szybko znika.\n\n" +
                "4. Zabijałem ich słowami ust moich\ni moje prawo wzeszło jak światłość.\nGdyż miłości chcę a nie ofiary.\nPoznania Boga a nie całopaleń.\n\n",
        "1. Jesteśmy Twymi dziećmi,\npotrzebujemy Ciebie,\nMiłości, która trwa.\nDobroci nieskończonej,\nłaski i nadziei,\nna wybawienie.\n\n" +
                "Ref. Zbawca, On porusza góry,\na Jego moc zbawia świat,\nJego moc zbawia świat!\n" +
                "Odwieczny, Autor odkupienia.\nOn z grobu powstał by żyć,\nz grobu powstał by żyć!\n\n" +
                "2. Weź mnie jakim jestem,\nz mym grzechem i mym lękiem,\nwypełnij życie me.\nJa Tobie się oddaję,\ncałą moją wiarę,\noddaję Tobie.\n\n" +
                "Bridge: Chcę być światłem,\naby poznał świat, Ciebie!\nWielki Królu,\nZbawicielu nasz, Jezu!\n\n",
        "1. Zbawienie przyszło przez Krzyż,\nogromna to tajemnica.\nKażde cierpienie ma sens,\nprowadzi do pełni życia.\n\n" +
                "Ref. Jeżeli chcesz mnie naśladować,\nto weź swój Krzyż na każdy dzień.\nI chodź ze mną zbawiać świat,\nKolejny już wiek.\n\n" +
                "2. Codzienność wiedzie przez Krzyż,\nwiększy im kochasz goręcej.\nNie musisz ginąć już dziś,\nlecz ukrzyżować swe serce.\n\n" +
                "3. Każde spojrzenie na Krzyż,\nniech niepokojem zagości.\nBo wszystko w życiu, to nic,\nwobec tak wielkiej miłości.\n\n",
        "1. Ten, którego kochają - zostanie zbawiony,\nchoć kocha się dlatego, że się nie rozumie.\n" +
                "Niekiedy tylko ogarnia zdumienie,\njakby się księżyc świntuch rozebrał do naga.\n\n" +
                "Ref. Czas już poza czasem, słowo ponad słowem,\ngwiazda, co przez okno chce Cię stuknąć w głowę.\n" +
                "Łotr na trzech gwoździach za nas powieszony,\nten, którego kochają, zostanie zbawiony.\n\n" +
                "2. Ten, którego kochają, zostaje zbawiony.\nIle razy błądziłeś - ale ktoś Cię kochał.\n" +
                "Czekał w oknie, bo oddech pozostał na szybie.\nIle razy grzeszyłeś, łza Cię uzdrowiła.\n\n",
        "1. Zbliża się do nas, idzie polami,\njak słońce złocista, jak biel przeczysta.\nW ciemnościach nocy puka do serc naszych,\nnasza Pani wieczysta.\n\n" +
                "Ref. Gwiazdo śliczna, wspaniała.\nO, nasza Maryjo. Do Ciebie się uciekamy,\nTyś nam pomocą jedyną.\n\n" +
                "2. Idzie w sukience,\njak w perle szacownej.\nNad nami swe błogosławione ręce,\nwszystkich ludzi o miłość prosi.\n\n" +
                "3. A kiedy rozpacz wejdzie w nasze serca,\nłaskami obdarzy i smutek rozwieje.\nKroczy cichutko z nami po drodze\ni cała ziemia Jej śpiewa.\n\n",
        "1. Zbliżam się w pokorze i niskości swej.\nWielbię Twój Majestat, skryty w Hostii tej.\n" +
                "Tobie dziś w ofierze serce daję swe.\nO utwierdzaj w wierze, Jezu, dzieci Twe.\n\n" +
                "2. Mylą się, o Boże, w Tobie wzrok i smak.\nKto się im poddaje, temu wiary brak.\n" +
                "Ja jedynie wierzyć Twej nauce chcę,\nże w postaci chleba utaiłeś się.\n\n" +
                "3. Bóstwo swe na Krzyżu skryłeś wobec nas.\nTu ukryte z bóstwem człowieczeństwo wraz.\n" +
                "Lecz w Oboje wierząc, wiem, że dojdę tam,\ngdzieś przygarnął łotra: do Twych niebios bram.\n\n" +
                "4. Jak niewierny Tomasz, Twych nie szukam ran,\nlecz wyznaję z wiarą, żeś mój Bóg i Pan.\n" +
                "Pomóż wierze mojej, Jezu, łaską swą.\nOżyw mą nadzieję, rozpal miłość mą.\n\n" +
                "5. Ty, coś upamiętnił śmierci Bożej czas,\nChlebie żywy życiem swym darzący nas.\n" +
                "Spraw, bym dla swej duszy życie z Ciebie brał,\nbym nad wszelką słodycz Ciebie poznać chciał.\n\n" +
                "6. Ty, co jak Pelikan Krwią swą karmisz lud,\nprzywróć mi niewinność, oddal grzechów brud.\n" +
                "Oczyść mnie Krwią swoją, która wszystkich nas,\njedną kroplą może obmyć z win i zmaz.\n\n" +
                "7. Pod zasłoną teraz, Jezu widzę Cię,\nniech pragnienie serca kiedyś spełni się.\n" +
                "Bym oblicze Twoje tam oglądać mógł,\ngdzie wybranym miejsce przygotował Bóg.\n\n",
        "Zbuduj, Ojcze nasz, nowe Jeruzalem.\nbuduj, Ojcze nasz, nowe Jeruzalem.\n" +
                "Zwołaj, Panie, narody swe, niechaj w jedno połączą się.\nZbuduj nowe Jeruzalem, nowe Jeruzalem.\n\n",
        "Ref. Zbudź się, o śpiący\ni powstań z martwych,\na zajaśnieje ci Chrystus.\n\n" +
                "1. Pan moim światłem i zbawieniem moim,\nkogo miałbym się lękać?\nPan obrońcą mego życia,\nprzed kim miałbym czuć trwogę?\n\n" +
                "2. Usłysz Panie, kiedy głośno wołam,\nzmiłuj się nade mną i mnie wysłuchaj.\nO Tobie mówi serce moje:\nszukaj Jego oblicza.\n\n" +
                "3. Nie zakrywaj przede mną swojej twarzy,\nnie odtrącaj w gniewie Twego sługi.\nTy jesteś moją pomocą, więc mnie nie odrzucaj\ni nie opuszczaj mnie, Boże, mój Zbawco.\n\n" +
                "4. Będę szukał oblicza Twego, Panie,\nnie zakrywaj przede mną swojej twarzy.\nWierzę, że będę oglądał Twoją chwałę,\nw krainie żyjących.\n\n",
        "1. Ziemia, którą mi dajesz, nie jest fikcją, ani bajką.\nWolność, którą mam w Tobie, jest prawdziwa.\n" +
                "Wszystkie góry na drodze, muszą, muszą ustąpić,\nbo wiara góry przenosi, a ja wierzę Tobie.\n\n" +
                "Ref. Będę tańczył przed twoim tronem\ni oddam Tobie chwałę\ni nic już nie zamknie mi ust.\n" +
                "Żaden mur i żadna ściana,\nnajwiększa nawet tama\njuż nie, nie zatrzyma mnie już.\n\n" +
                "2. Bo większy, większy jest we mnie\nTen, który mnie umacnia.\nŻaden goliat nie może z nim równać się.\n\n",
        "1. Nie troszczcie się zbytnio o swe życie,\nO to co macie jeść, co macie pić,\nAni o swoje ciało,\nCzym przyodziać je – tak mówi Pan.\n" +
                "Przypatrzcie się ptakom w powietrzu,\nNie pracują, nie przędą, ani żną,\nOjciec zaś niebieski wciąż je żywi,\nKtóż ważniejszy jest, one czy WY?\n\n" +
                "Ref. Gdzie jest Twój skarb, tam i serce jest twe.\nSzukaj Królestwa, a wszystko da Pan,\n" +
                "Ojciec niebieski o potrzebach twych wie,\nJemu zaufaj – nie zawiedziesz się.\n\n" +
                "2. Któż z was przy całej swojej trosce,\nDoda choć chwilę do swych dni.\nNie troszczcie się więc, co będzie jutro,\nDosyć ma ten dzień biedy swej.\n" +
                "Przypatrzcie się liliom na polu,\nJeśli je tak pięknie stworzył Bóg,\nTym bardziej da wszystko, co potrzeba\nTym, za których Syn życie dał.\n\n" +
                "3. Nie gromadź sobie skarbów na ziemi,\nZ lękiem nie zbieraj, aby żyć.\nRozdaj braciom wszystko, co posiadasz,\nDawaj z serca, nie zabraknie ci!\n" +
                "Co ważniejsze jest: dać wiele, czy dać wszystko?\nNajpierw daj serce – spotkaj się,\nJeśli Mu zaufasz – Bóg zaradzi,\nOn Panem jest, nic nie wymknie się!\n\n" +
                "Ref. Jednego brakuje ci. Chcę, abyś wolny był.\nUczyń to! Z ufnością za mną chodź!\n\n",
        "Ześlij deszcz, ześlij deszcz.\nOtwórz nam bramy do Nieba.\n\n" +
                "Przelewają się przeze mnie rzeki Twojej łaski,\nłaska po łasce, fala za falą /x3\n\n",
        "1. Zjednoczeni w Duchu, zjednoczeni w Nim,\nzjednoczeni w Duchu, zjednoczeni w Nim.\nMódlmy się, aby jedność była odnowiona w nas,\n" +
                "aby świat cały poznał uczniów Chrystusa w nas,\naby świat cały ujrzał miłość w nas.\n\n" +
                "2. Wraz z innymi pójdziemy, podaj mi swoją dłoń,\nwraz z innymi pójdziemy, podaj mi swoją dłoń.\nWszystkim głosić będziemy tę radosną dla nas wieść,\n" +
                "że nasz Pan kroczy z nami, że nasz Pan z nami jest,\nże nasz Pan zawsze z nami wszędzie jest.\n\n" +
                "3. Chcemy razem pracować, chcemy złączyć swój trud,\nchcemy razem pracować, chcemy złączyć swój trud.\n" +
                "By nie stracić godności, którą Pan obdarzył nas,\naby strzec ludzką dumę, aby strzec dumę swą,\nżeby człowiek zachował godność swą.\n\n" +
                "4. Niechaj chwała brzmi Ojcu, który stworzył ten świat,\nniechaj chwała brzmi Ojcu, który stworzył ten świat.\n" +
                "Chwała też Chrystusowi, który Synem Jego jest\ni Duchowi też chwała, który jednoczy nas,\ni Duchowi też, który łączy nas.\n\n",
        "Ref. Zmartwychwstał Pan, Zmartwychwstał Pan,\nZmartwychwstał Pan, Alleluja!\n\n" +
                "1. O śmierci, gdzie jesteś, o śmierci,\ngdzie jest moja śmierć,\ngdzie jest jej zwycięstwo?\n\n" +
                "2. Radujmy się, radujmy się bracia,\njeśli dzisiaj się miłujemy,\nto dlatego, że On zmartwychwstał.\n\n" +
                "3. Dzięki niech będą Ojcu,\nktóry nas prowadzi do Królestwa,\ngdzie się miłością żyje.\n\n" +
                "4. Jeśli w Nim umieramy,\nz Nim też żyć będziemy,\nz Nim śpiewać będziemy,\nAlleluja!\n\n",
        "Ref. Zmartwychwstał Pan i żyje dziś,\nblaskiem jaśnieje noc.\nNie umrę, nie, lecz będę żył, Bóg okazał swą moc.\n" +
                "Krzyż to jest brama Pana,\njeśli chcesz, przez nią wejdź.\nZbliżmy się do ołtarza, Bogu oddajmy cześć!\n\n" +
                "1. Dzięki składajmy Mu, bo wieczna jest Jego łaska.\nZ grobu powstał dziś Pan, a noc jest pełna blasku.\n" +
                "Chcę dziękować Mu i chcę Go dziś błogosławić.\nJezus, mój Pan i Bóg, On przyszedł, aby nas zbawić!\n\n" +
                "2. Lepiej się uciec do Pana, niż zaufać książętom.\nPan - moja moc i pieśń, podtrzymał, gdy mnie popchnięto.\n" +
                "Już nie będę się bać, cóż może zrobić mi śmierć.\nNie, nie lękam się i śpiewam chwały pieśń!\n\n" +
                "3. Odrzucony Pan stał się kamieniem węgielnym.\nPan wysłuchał mnie, On jest zbawieniem mym.\n" +
                "Cudem staje się noc, gdy w dzień jest przemieniona.\nTańczmy dla Niego dziś, prawica Pańska wzniesiona!\n\n",
        "Ref. Zmartwychwstały Pan królestwo objął dziś.\nWszechmogący Wódz okazał swoją moc.\n" +
                "Zakwitł Krzyż i karmi, chroni mnie.\nJezus, Pan, rozdaje życie swe.\n\n" +
                "1. Milczący Baranek, Baranek zabity.\nWybawia dziś nas z niewoli tego świata.\n" +
                "Hańbą okrył śmierć, nasza Pascha,\nOn wywiódł dziś nas ze śmierci do życia.\n\n" +
                "2. W Ablu zabity, w Izaaku związany,\nwzgardzony w Jakubie, w Józefie sprzedany.\n" +
                "Zrodzony z Maryi, przepięknej owieczki,\nzłożony w ofierze, powstaje dziś z martwych.\n\n" +
                "3. Zstępuje z nieba w nasze cierpienia,\nby wyprowadzić nas z ciemności do światła.\n" +
                "Wzięty ze stada, zawieszony na drzewie,\nnaznacza nas duchem i własną krwią.\n\n",
        "1. Zmiłuj się, Boże, nade mną\nw swojej łaskawości\nw ogromie swego miłosierdzia\nwymaż moją nieprawość!\n" +
                "Obmyj mnie zupełnie z mojej winy\ni oczyść mnie z grzechu mojego!\nUznaję bowiem moją nieprawość\nA grzech mój jest zawsze przede mną.\n\n" +
                "Ref. Przyjdźcie do mnie wszyscy nie lękajcie się,\nJa was pokrzepię!\nChoćby wasze grzechy były jak szkarłat,\nnad śnieg wybieleją.\n\n" +
                "2. Tylko przeciw Tobie zgrzeszyłem\ni uczyniłem co złe jest przed Tobą,\nOdwróć swe oblicze od moich grzechów\ni wymaż wszystkie moje przewinienia!\n\n" +
                "3. Nie odrzucaj mnie od swego oblicza\ni nie odbieraj mi świętego Ducha swego!\nPrzywróć mi radość z twojego zbawienia\ni wzmocnij mnie duchem ochoczym!\n\n",
        "Jezu, Mistrzu nasz i Panie,\nswym pokojem obdarz nas.\nNiech się cud odnowy stanie\nw każdym sercu, w każdym z nas.\n\n" +
                "Ref. Choć tyle żalu w nas\ni gniew uśpiony trwa.\nPrzekażmy sobie znak pokoju,\nprzekażmy sobie znak.\n\n",
        "1. Zobaczcie, jak jest pięknie przebywać razem z braćmi.\nZobaczcie, jak jest pięknie przebywać razem z braćmi.\n" +
                "Jak jest pięknie przebywać razem z braćmi.\nJak jest pięknie przebywać razem z braćmi.\n\n" +
                "2. Jest to jak olej, co spływa na głowę Aarona.\nTak, jak olej na głowę Aarona.\n\n" +
                "3. Jest to jak rosa Hermonu, na górach Syjonu.\nTak, jak rosa na górach Syjonu.\n\n" +
                "4. Tam Jahwe, Pan, udzielił swego błogosławieństwa.\nTam udzielił swego błogosławieństwa.\n\n" +
                "5. Tam Jahwe podarował życie, aż na wieki.\nPodarował życie, aż na wieki.\n\n",
        "1. Zobaczcie, jak wielką miłością obdarzył nas Ojciec.\n\n" +
                "Ref. Śpiewajmy naszemu Bogu, Alleluja.\n\n" +
                "2. On nazwał nas swymi dziećmi i rzeczywiście nimi jesteśmy.\n\n" +
                "3. Wiemy, że gdy się objawi, będziemy do Niego podobni.\n\n" +
                "4. Będziemy widzieć Pana takiego, jakim jest.\n\n" +
                "5. Każdy, kto ma w Nim nadzieję, podobnie jak On, jest Święty.\n\n",
        "Zobaczcie, jak wielką miłość\nOjciec ofiarował nam.\nZobaczcie, jak wielką miłość\n" +
                "Ojciec ofiarował nam.\nByśmy dziećmi Boga mogli być.\nByśmy dziećmi Boga mogli być.\n\n",
        "Zostań tu i ze mną się módl!\nRazem czuwajmy!\nRazem czuwajmy!\n\nNie bój się, nie lękaj się, Bóg sam wystarczy, Bóg sam wystarczy.\n\n",
        "1. Mario, czemu pobladłaś, Tyś można, jak inna żadna,\nbo poczniesz Króla nad króle, a nazwą Go Emanuel.\n\n" +
                "Ref. Nie trwóż się Mario - Lilijo,\nDzieciątko Święte powijesz.\nSiankiem Mu żłóbek wyścielesz,\nnim błyśnie gwiazda w Betlejem.\n" +
                "Radować wszyscy się będą, muzyką chwalić, kolędą,\nBić będą niskie pokłony, przed Twoim,\nJednorodzonym, Jednorodzonym.\n\n" +
                "2. Nadzieje ludów spełnione, bo Ciałem się stanie Słowo.\nNiech będzie błogosławiony żywota Twojego Owoc.\n\n",
        "Zwiastunom z gór, stopom ich cześć, bo niosą nam,\nradosną wieść, tę wieść.\nPokoju dar, wśród życia dróg przychodzi już,\n" +
                "władać Bóg, Królem Bóg.\nKrólem Bóg, Królem Bóg,\nKrólem Bóg, Królem Bóg.\n\n",
        "Ref. Wszystkie moje źródła w tobie są,\nWszystkie moje źródła w tobie są.\nWszystkie moje źródła w tobie są,\nJeruzalem.\n\n" +
                "1. Budowla Twoja na górach wznosi się,\nWspaniałe rzeczy o Tobie głoszą dziś.\nNa wzgórzach Twoich narodziłem się,\nPan cię miłuje, życie oddał ci.\n\n" +
                "2. Tańcząc, śpiewajmy naszemu Panu dziś,\nŹródło otworzył w zranionym sercu swym.\nBiegnijmy, biegnijmy, by dać pochwycić się,\nAlleluja, Alleluja, Barankowi cześć.\n\n",
        "1. Nie ukochałem mego krzyża,\nNie rozpoznałem perły.\nI wciąż szukałem mego życia,\nLecz doświadczałem śmierci.\n" +
                "Aż pokonując groźne fale,\nUjrzałem krzyża drewno.\nTy wytyczyłeś moją drogę\nMiłości ścieżką pewną.\n\n" +
                "Ref. Żadna pieśń nie wypowie, Co czuje moje serce.\nLiche drewno ratuje moje życie,\nBłogosławiony Krzyż, moja Arka.\n\n" +
                "2. Skłoniłem głowę w Twych ramionach\nI w Tobie odpoczywam.\nZe skały płynie woda życia\nI rany me obmywa.\n" +
                "Gdy tak jesteśmy połączeni,\nMam w sobie wszechświat cały.\nNiebo i ziemia, i anieli,\nśpiewają pieśni chwały.\n\n",
        "Ref. Żyć w społeczności z Tobą,\nTo coś tak pięknego,\nŻe serce w ogniu staje,\nA dusza miłością płonie.\n\n" +
                "1. Wszyscy spragnieni, dziś przyjdźcie do wody.\nPrzyjdźcie, choć nie macie pieniędzy,\n" +
                "Kupujcie spożywajcie, dalejże kupujcie,\nBez płacenia za wino i chleb.\n\n" +
                "2. Czemu wydajecie pieniądze na to,\nCo nie jest z nieba chlebem,\nA waszą pracę na to co nie syci.\nSłuchajcie, a żyć będziecie.\n\n" +
                "3. Dusza wasza zakosztuje rozkoszy,\nJeśli nakłonicie swego ucha.\nPrzyjdźcie do Pana, słuchajcie Go pilnie,\nA dusza wasza żyć będzie.\n\n",
        "Boziu, Boziu mój, ja robaczek Twój,\nja robaczek nieboraczek, a Ty, Boziu mój.\n\n",
        "1. Boża radość jak rzeka,\nBoża radość jak rzeka,\nBoża radość wypełnia duszę mą (Alleluja)! /x2\n\n" +
                "2. Boża miłość...\n\n3. Boży pokój...\n\n4. Boża radość jak rzeka, Boża miłość jak rzeka,\nBoży pokój wypełnia duszę mą.\n\n",
        "Bóg kocha mnie takiego, jakim jestem,\nraduje się każdym moim gestem.\nAlleluja! Boża radość mnie rozpiera.\n\n",
        "1. Gdy Boży Duch wypełnia mnie,\njak Dawid śpiewać chcę. /x2\nJak Dawid, jak Dawid, jak Dawid śpiewać chcę. /x2\n\n" +
                "2. Gdy Boży Duch wypełnia mnie, jak Dawid klaskać chcę. /x2\n\n" +
                "3. Gdy Boży Duch wypełnia mnie, jak Dawid wielbić chcę. /x2\n\n" +
                "4. Gdy Boży Duch wypełnia mnie, jak Dawid skakać chcę. /x2\n\n",
        "Gdy Franek był mały, chciał rycerzem być. /x2\nTata kupił mu konia, miecz i psa. /x2\nPojechał na wojnę i przegrał ją. /x2\n" +
                "Wtedy zrozumiał, że królem jest Bóg. /x2\n\nRef. Zostań rycerzem Jezusa Chrystusa. /x4\n\n",
        "Gdy na morzu wielka burza,\nJezus ze mną w łodzi jest. /x2\nOn za rękę trzyma mnie, łódka nie kołysze się.\n" +
                "Gdy na morzu wielka burza, Jezus ze mną w łodzi jest.\n\n",
        "Gdy Pan Jezus był malutki, nigdy nie próżnował.\nZ Matką Bożą i z Józefem chętnie wciąż pracował.\n" +
                "Tak piłował, tak heblował i wełenkę zwijał,\nnosił wodę, rąbał drzewo i gwoździe przybijał.\n\n",
        "1. Idzie Jezus, spójrz jak kroczy On po wodzie,\npodnosi cię i pomaga ci wstać.\nIdzie Jezus, On jest Panem tych spienionych fal.\nIdzie Jezus, On prowadzi mnie.\n\n" +
                "2. Idzie Jezus, ... On kieruje mną.\n\n3. Idzie Jezus, ... On kocha mnie.\n\n4. Idzie Jezus, ... On pociesza mnie.\n\n" +
                "5. Idzie Jezus, ... On umacnia mnie.\n\n6. Idzie Jezus, ... On uświęca mnie.\n\n7. Idzie Jezus, ... On uwalnia mnie.\n\n" +
                "8. Idzie Jezus, ... On przenika mnie.\n\n9. Idzie Jezus, ... On zbawia mnie.\n\n",
        "Ja i ty, to razem my /x2\n\nRef. Razem z ojcem, Razem z synem\nRazem z duchem, Razem my /x2\n\n" +
                "Bridge.\nJesteśmy razem - jednego Ojca mamy\nJesteśmy razem - On jest naszym bratem\nJesteśmy razem - w jednym Duchu razem, razem\n" +
                "Im bliżej Niego - tym bardziej razem, razem, razem\nDuchu Święty wołam przyjdź – a przyjdź Duchu Święty\nDuchu Święty wołam przyjdź – (i rozpal nas)\n\n",
        "1. Ja spotkam Jezusa pewnego dnia,\nja spotkam Jezusa twarzą w twarz.\nJa spotkam Jezusa twarzą w twarz.\n\n" +
                "2. Ja spotkam Maryję...\n\n3. Ja spotkam Franciszka...\n\n4. Spotkamy się razem...\n\n",
        "Jak mi dobrze, że jesteś tu, Panie. /x2\n\nRef. Trzymaj mnie mocno dziś,\nzawsze chcę przy Tobie być, Panie.\n\n" +
                "1. Twoja miłość wypełnia mnie, o mój Panie.\n\n2. Twoja radość jest w sercu mym, o mój Panie.\n\n",
        "Ref. Jak rozpoznać mam Chrystusa,\ngdzie Go szukać mam.\nPomóż mi, bym Go odnalazł\ni już nie był sam.\n\n" +
                "1. W modlitwie, w modlitwie mój Chrystus jest.\nW modlitwie, w modlitwie On czeka cię.\n\n" +
                "2. W Kościele...\n\n3. W radości...\n\n4. W miłości...\n\n5. W pokoju...\n\n6. W cierpieniu...\n\n7. W człowieku...\n\n",
        "Jesteś radością mojego życia,\no, o, o Panie mój. /x2\nTy jesteś moim Panem /x3\nna zawsze jesteś Panem mym.\n\n",
        "Jezus dziś przyszedł do mnie,\nwziął mnie w ramiona swoje, rzekł: „Jesteś mój\nJa zaś bratem twoim jestem, daj Mi dłoń!”\n\n",
        "Ref. Krzycz ze szczęścia, /x2 bo Pan ukochał nas.\n\n1. Śpiewaj Panu...\n\n. Chwalmy Pana...\n\n" +
                "3. Klaszczmy w dłonie...\n\n4. Wznieśmy ręce...\n\n5. Alleluja...\n\n",
        "Ludzie, ludzie, których Bóg jest Panem,\nszczęśliwi są. /x2\nBędę szukał Jego wciąż,\nsłużył Mu do końca swoich dni.\nZaufałem memu Bogu, On prowadzi mnie./x2\n\n",
        "1. Maleńkie mrówki kocha Bóg, od zguby chroni je.\nA jeśli mrówki kocha Bóg, to kocha także mnie.\n" +
                "On kocha mnie, On kocha mnie, ja wiem, On kocha mnie.\nBo jeśli mrówki kocha Bóg, to kocha także mnie.\n\n" +
                "2. Wróbelki małe kocha Bóg, od zguby chroni je.\nA jeśli ptaszki kocha Bóg, to kocha także mnie.\n" +
                "On kocha mnie, On kocha mnie, ja wiem On kocha mnie.\nBo jeśli ptaszki kocha Bóg, to kocha także mnie.\n\n",
        "Na początku Bóg stworzył niebo, ziemię i światło.\n\nPorozdzielał toń wód i sklepienie niebieskie,\n" +
                "dał rośliny, zwierzęta, błyszczące planety.\nPotem był człowiek.\nPierwszy dzień pracował, drugi dzień,\n" +
                "trzeci dzień pracował, czwarty dzień,\npiąty dzień pracował, szósty dzień,\nw siódmym odpoczywał.\n\n",
        "Nie chcę chodzić w piechocie,\njeździć w kawalerii, strzelać z artylerii.\nNie chcę latać samolotem,\n" +
                "jestem w Pana armii.\nJestem w Pana armii /x2\nNie chcę latać samolotem, jestem w Pana armii.\n\n",
        "Ref. O, o, o, - niebo jest w sercu mym. /x2\n\n" +
                "1. Królestwo Boga tutaj jest - niebo jest w sercu mym!\nMajestat Jego pośród nas - niebo jest w sercu mym!\n" +
                "On radość swą przynosi nam - niebo jest w sercu mym!\nOgarnia nas świętości blask - niebo jest w sercu mym!\n\n" +
                "2. Pan za mnie zmarł, bym życie miał -\nTo życie, które wiecznie trwa -\n" +
                "W Chrystusie cała ufność ma -\nDziedzictwem moim Chrystus, Pan –\n\n" +
                "3. Świątynią Bóg uczynił nas –\nWęgielny Kamień, Chrystus, Pan –\n" +
                "Gdy wróci, z sobą weźmie nas –\nOblubienica woła „Przyjdź!” –\n\n",
        "1. Oddaj brzemię Jezusowi, pomoże ci. /x2\nWyżej, /x7 Jezus coraz wyżej.\nWyżej, /x7 Jezus coraz wyżej, kocham Go.\n\n" +
                "2. Oddaj brzemię Jezusowi, pomoże ci. /x2\nNiżej, /x7 szatan coraz niżej.\nNiżej, /x7 szatan coraz niżej, zdepczę go.\n\n",
        "Panie Jezu, zabierzemy Cię do domu.\nPanie Jezu, nie oddamy Cię nikomu.\nDniem i nocą, Panie Jezu będziesz z nami,\nbo gorąco, Panie Jezu, Cię kochamy. /x2\n\n",
        "Panie, Ty jesteś radością mą,\nJezu, Ty rozweselasz mnie.\nPan uwalnia jeńców, Pan przywraca wzrok niewidomym,\n" +
                "Pan podnosi pochylonych, Pan miłuje sprawiedliwych,\nPan strzeże przychodniów. Chroni sierotę i wdowę,\nPan króluje na wieki, Bóg przez pokolenia, Alleluja.\n\n",
        "1. Weź do ręki swą gitarę,\nwydaj z siebie dźwięków parę.\nProste chwyty, proste słowa,\n" +
                "proste Bogu się podoba.\nTo na cześć i chwałę Nieba,\nAnioł, jako żywy, śpiewa!\n" +
                "Ref. Hej, la, la, la! Hej, la, la, la!\nTo dla Ciebie, Dobry Boże pioseneczka ta!\n\n" +
                "2. Jedni grają dla oklasków\ni dla innych ziemi blasków.\nMy na chwałę Nieba gramy\n" +
                "i najwięcej z tego mamy.\nTo na cześć i chwałę Nieba,\nAnioł, jako żywy, śpiewa!\n\n" +
                "3. Dałeś nam się cieszyć Boże,\nz Tobą smutno być nie może.\nPowiedz jak dziękować mamy –\n" +
                "Tobie gramy i śpiewamy.\nTo na cześć i chwałę Nieba,\nAnioł, jako żywy, śpiewa!\n\n",
        "Płyną statki z bananami w siną dal,\nŁabidu daj daj, łabidu daj,\na każdy ładowacz śpiewa tak:\n" +
                "łabidu daj daj, łabidu daj.\nPodaj, podaj, podaj mi, podaj bananów kosz. /x2\n\n",
        "Ref. Podaj rękę swemu bratu,\nniechaj nikt nie czuje się sam.\nChrystus przyszedł pokój dać światu\ni ten pokój zostawia nam.\n\n" +
                "1. Jesteś, Panie, Chlebem życia,\njako pokarm dajesz się nam.\nI zaprawdę ten, kto wierzy w Ciebie,\nżycie wieczne ma. /x2\n\n" +
                "2. Chcemy, Panie, naszym braciom\nTwoją miłość i pokój dać.\nAby wszyscy ludzie poznawali\nuczniów Twoich w nas. /x2\n\n",
        "Ref. Taki duży, taki mały może świętym być.\nTaki gruby, taki chudy może świętym być.\nTaki ja i taki Ty, może świętym być. /x2\n\n" +
                "1. Święty kocha Boga, życia mu nie szkoda,\nkocha bliźniego jak siebie samego. /x2\n\n" +
                "2. Kto się nawróci, ten się nie smuci, każdy święty chodzi uśmiechnięty.\nTylko nawrócona jest zadowolona, każda święta chodzi uśmiechnięta.\n\n" +
                "3. Nic nie potrzebuje, zawsze się raduje,\nbo święta załoga kocha tylko Boga. /x2\n\n" +
                "4. Gdzie można dzisiaj świętych zobaczyć?\nSą między nami w szkole i w pracy. /x2\n\n",
        "Ref. Nie boję się, gdy ciemno jest,\nOjciec za rękę prowadzi mnie. /x2\n\n" +
                "1. Dziękuję Ci, Tato, za wszystko co robisz,\nże bawisz się ze mną, na rękach mnie nosisz.\n" +
                "Dziękuję Ci, Tato, i wiem to na pewno,\nprzez cały dzień czuwasz nade mną.\n\n" +
                "2. Czasem się martwię, czegoś nie umiem,\nTy mnie pocieszasz i mnie rozumiesz.\n" +
                "Śmieję się głośno, kiedy żartujesz, bardzo Cię kocham i potrzebuję.\n\n" +
                "3. Nasz Ojciec mieszka w niebie, kocha mnie i Ciebie.\nOn nas kocha, kocha mnie i Ciebie.\n\n" +
                "4. Sanki są w zimie, rower jest w lato.\nMama, to nie jest to samo, co Tato.\n\n",
        "W moim sercu mieszka Bóg,\na Jego flagą radość jest, /x3\nPodniosę flagę wysoko, wysoko. /x2\n" +
                "Niech pozna, pozna cały świat. /x2\nNiech wszyscy ludzie zobaczą, zobaczą, /x2\nże Pan mieszka w sercu mym.\n\n",
        "1. A wczora z wieczora, a wczora z wieczora,\nz niebieskiego dwora, z niebieskiego dwora.\n" +
                "Przyszła nam nowina, przyszła nam nowina:\nPanna rodzi Syna, Panna rodzi Syna.\n\n" +
                "2. Boga prawdziwego,\nnieogarnionego.\nZa wyrokiem Boskim,\nw Betlejem żydowskim.\n\n" +
                "3. Pastuszkowie mali\nw polu wtenczas spali.\nGdy Anioł z północy\nświatłość z nieba toczy\n\n" +
                "4. Chwałę oznajmując,\nszopę pokazując.\nChwałę Boga tego,\ndziś nam zrodzonego.\n\n" +
                "5. „Tam Panna Dzieciątko,\nmiłe Niemowlątko,\nuwija w pieluszki,\npośpieszcie pastuszki!”\n\n" +
                "6. Natychmiast pastuszy\nśpieszą z całej duszy,\nweseli bez miary,\nniosą z sobą dary.\n\n" +
                "7. Mądrości druhowie,\nz daleka królowie,\npragną widzieć swego\nStwórcę przedwiecznego.\n\n" +
                "8. Dziś Mu pokłon dają\nw ciele oglądają.\nKażdy się dziwuje,\nże Bóg nas miłuje.\n\n" +
                "9. I my też pośpieszmy,\nJezusa ucieszmy,\nze serca darami:\nmodlitwą, cnotami.\n\n" +
                "10. Jezu najmilejszy,\nze wszech najwdzięczniejszy,\nzmiłuj się nad nami,\ngrzesznymi sługami.\n\n",
        "1. Ach ubogi żłobie,\ncóż ja widzę w tobie?\nDroższy widok niż ma niebo,\nw maleńkiej osobie./x2\n\n" +
                "2. Zbawicielu drogi,\njakżeś to ubogi.\nOpuściłeś śliczne niebo,\nobrałeś barłogi. /x2\n\n" +
                "3. Czyżeś nie mógł Sobie,\nw największej ozdobie,\nobrać pałacu drogiego,\nnie w tym leżeć żłobie? /x2\n\n" +
                "4. Gdy na świat przybywasz,\ngrzechy z niego zmywasz.\nA na zmycie tej sprośności,\ngorzkie łzy wylewasz. /x2\n\n" +
                "5. Na twarz upadamy,\nczołem uderzamy,\nwitając Cię w tej stajence,\nmiędzy bydlętami. /x2\n\n" +
                "6. Zmiłuj się nad nami,\nobmyj z grzechów łzami.\nPrzyjmij serca, te skruszone,\nktóre Ci składamy. /x2\n\n",
        "1. Anioł pasterzom mówił:\nChrystus się wam narodził\nw Betlejem, nie bardzo podłym mieście,\nnarodził się w ubóstwie,\nPan wszego stworzenia.\n\n" +
                "2. Chcąc się dowiedzieć tego\nposelstwa wesołego,\nbieżeli do Betlejem skwapliwie.\nZnaleźli Dziecię w żłobie,\nMaryję z Józefem.\n\n" +
                "3. Taki Pan chwały wielkiej\nuniżył się Wysoki.\nPałacu kosztownego żadnego\nnie miał zbudowanego,\nPan wszego stworzenia.\n\n" +
                "4. O dziwne narodziny,\nnigdy nie wysłowione.\nPoczęła Panna Syna w czystości,\nporodziła w całości,\npanieństwa swojego.\n\n" +
                "5. Już się ono spełniło,\nco pod figurą było:\nAarona różdżka ona zielona\nstała się nam kwitnąca\ni owoc rodząca.\n\n" +
                "6. Słuchajcież Boga Ojca,\njako wam Go zaleca:\nTen ci jest Syn najmilszy, jedyny,\nw raju wam obiecany,\nTego wy słuchajcie.\n\n" +
                "7. Bogu bądź cześć i chwała,\nktóra byś nie ustała.\nJako Ojcu, tak i Jego Synowi,\ni Świętemu Duchowi,\nw Trójcy jedynemu.\n\n",
        "1. Bóg się rodzi, moc truchleje,\nPan niebiosów obnażony!\nOgień krzepnie, blask ciemnieje,\nma granice nieskończony.\n" +
                "Wzgardzony, okryty chwałą,\nśmiertelny, Król nad wiekami.\nA Słowo Ciałem się stało i mieszkało między nami.\n\n" +
                "2. Cóż masz Niebo, nad ziemiany?\nBóg porzucił szczęście Swoje.\nWszedł między lud ukochany,\n" +
                "dzieląc z nim trudy i znoje.\nNiemało cierpiał, niemało,\nżeśmy byli winni sami.\nA Słowo…\n\n" +
                "3. W nędznej szopie urodzony,\nżłób Mu za kolebkę dano!\nCóż jest, czym był otoczony?\n" +
                "Bydło, pasterze i siano.\nUbodzy, was to spotkało,\nwitać Go przed bogaczami.\nA Słowo…\n\n" +
                "4. Podnieś rękę, Boże Dziecię,\nbłogosław ojczyznę miłą.\nW dobrych radach, w dobrym bycie,\n" +
                "wspieraj jej siłę swą siłą.\nDom nasz i majętność całą,\ni wszystkie wioski z miastami!\nA Słowo…\n\n",
        "1. Bracia, patrzcie jeno, jak niebo goreje!\nZnać, że coś dziwnego, w Betlejem się dzieje.\nRzućmy budy, warty, stada,\n" +
                "niechaj nimi Pan Bóg włada.\nA(a) my(y) do Betlejem, a my do Betlejem,\ndo Betlejem.\n\n" +
                "2. Patrzcie, jak tam gwiazda światłem swoim miga,\npewnie dla uczczenia Pana swego ściga.\nKrokiem śmiałym i wesołym,\n" +
                "śpieszmy i uderzmy czołem.\nPrze(e)d Pa(a)nem w Betlejem, przed Panem w Betlejem,\nw Betlejem.\n\n" +
                "3. Wszakże powiedziałem, że cuda ujrzymy:\nDziecię, Boga świata, w żłobie zobaczymy.\nPatrzcie, jak biednie okryte,\n" +
                "w żłóbku Panię znakomite.\nW szo(o)pie(e) przy Betlejem, w szopie przy Betlejem,\nprzy Betlejem.\n\n" +
                "4. Jak prorok powiedział: Panna zrodzi Syna,\ndla ludu całego szczęśliwa nowina.\nNam zaś radość w tej tu chwili,\n" +
                "gdyśmy Pana zobaczyli.\nW szo(o)pie(e) przy Betlejem, w szopie przy Betlejem,\nprzy Betlejem.\n\n" +
                "5. Obchodząc pamiątkę odwiedzin pasterzy,\nkażdy czciciel Boga, co w Chrystusa wierzy.\nNiech się cieszy i raduje,\n" +
                "że Zbawcę swego znajduje.\nW szo(o)pie(e) przy Betlejem, w szopie przy Betlejem,\nprzy Betlejem.\n\n",
        "1. Cicha noc, święta noc! Pokój niesie ludziom wszem.\nA u żłóbka Matka Święta, czuwa sama uśmiechnięta,\nnad Dzieciątka snem, nad Dzieciątka snem.\n\n" +
                "2. Cicha noc, święta noc! Pastuszkowie od swych trzód\nbiegną wielce zadziwieni, za anielskim głosem pieni,\ngdzie się spełnił cud, gdzie się spełnił cud.\n\n" +
                "3. Cicha noc, święta noc! Narodzony Boży Syn,\nPan wielkiego majestatu, niesie dziś całemu światu\nodkupienie win, odkupienie win.\n\n" +
                "4. Cicha noc, święta noc! Jaki w tobie dzisiaj cud.\nW Betlejem Dziecina święta, wznosi w górę swe rączęta,\nbłogosławi lud, błogosławi lud.\n\n",
        "1. Do szopy, hej pasterze,\ndo szopy, bo tam cud!\nSyn Boży w żłobie leży,\nby zbawić ludzki ród.\n\n" +
                "Ref. Śpiewajcie Aniołowie,\npasterze grajcie Mu.\nKłaniajcie się Królowie,\nnie budźcie Go ze snu!\n\n" +
                "2. Padnijmy na kolana,\nto Dziecię to nasz Bóg.\nWitajmy swego Pana,\nwdzięczności złóżmy dług.\n\n" +
                "3. Boże niepojęty, kto\npojmie miłość Twą?\nNa sianie wśród bydlęty,\nmasz tron i służbę swą.\n\n" +
                "4. Jezuniu mój najsłodszy,\nTobie oddaję się.\nO skarbie mój najdroższy,\nracz wziąć na własność mnie.\n\n",
        "1. Dzisiaj w Betlejem /x2 wesoła nowina,\nże Panna czysta /x2 porodziła Syna.\n\n" +
                "Ref. Chrystus się rodzi, nas oswobodzi,\nAnieli grają, Króle witają.\nPasterze śpiewają, bydlęta klękają,\ncuda, cuda ogłaszają. /x2\n\n" +
                "2. Maryja Panna /x2 Dzieciątko piastuje\ni Józef święty /x2 Ono pielęgnuje.\n\n" +
                "3. Choć w stajeneczce /x2 Panna Syna rodzi.\nPrzecież On wkrótce /x2 ludzi oswobodzi.\n\n" +
                "4. I trzej Królowie /x2 od wschodu przybyli.\nI dary Panu /x2 kosztowne złożyli.\n\n",
        "1. Dziś w stajence mały Jezus się narodził\ni pobożnie swoje małe rączki złożył.\nChociaż jest maleńki, błogosławi już\nwszystkim, którzy zaśpiewali Mu.\n\n" +
                "Ref. Zaśpiewajmy kolędę Jezusowi dziś,\nniech kolęduje z nami cała ziemia.\nZaśpiewajmy kolędę Jezusowi dziś,\nniech kolęduje z nami cały świat.\n\n" +
                "2. W takt kolędy wieją wiatry, szumią drzewa,\ncały świat kolędy Jezusowi śpiewa.\nNiech kolędy nuta mocno w świecie brzmi,\nmaleńkiemu Jezusowi dziś.\n\n" +
                "3. In exelsis Deo - śpiewajmy do Pana,\na śpiewając - Gloria - zegnijmy kolana.\nWielka radość dzisiaj ogarnęła nas,\nzaśpiewajmy Bogu jeszcze raz.\n\n",
        "1. Gdy się Chrystus rodzi i na świat przychodzi,\nciemna noc w jasności promienistej brodzi.\n" +
                "Aniołowie się radują, pod niebiosy wyśpiewują!\nGloria, Gloria, Gloria in excelsis Deo. /x2\n\n" +
                "2. Mówią do pasterzy, którzy trzód swych strzegli,\naby do Betlejem czym prędzej pobiegli.\n" +
                "Bo się narodził Zbawiciel, wszego świata Odkupiciel!\nGloria, Gloria, Gloria in excelsis Deo. /x2\n\n" +
                "3. O niebieskie duchy i posłowie nieba,\npowiedzcież wyraźniej, co nam czynić trzeba.\n" +
                "Bo my nic nie pojmujemy, ledwo ze strachu żyjemy!\nGloria, Gloria, Gloria in excelsis Deo. /x2\n\n" +
                "4. Idźcież do Betlejem, gdzie Dziecię zrodzone,\nw pieluszki powite, w żłobie położone.\n" +
                "Oddajcie mu pokłon Boski, On osłodzi wasze troski!\nGloria, Gloria, Gloria in excelsis Deo. /x2\n\n",
        "1. Gdy śliczna Panna Syna kołysała,\nz wielkim weselem tak Jemu śpiewała:\nLili lili laj, moje Dzieciąteczko,\nlili lili laj, śliczne Paniąteczko.\n\n" +
                "2. Wszystko stworzenie, śpiewaj Panu swemu,\npomóż w radości wielkiej sercu memu.\n" +
                "Lili lili laj, wielki Królewicu,\nlili lili laj, niebieski Dziedzicu.\n\n" +
                "3. Cicho wietrzyku, Cicho południowy,\nCicho powiewaj, jeśli Panicz goły\n" +
                "Lili lili laj, mój wonny Kwiateczku,\nlili lili laj, w ubogim żłóbeczku.\n\n" +
                "4. Sypcie się z nieba, śliczni Aniołowie\nŚpiewajcie Panu, niebiescy duchowie\n" +
                "Lili lili laj, mój wonny Kwiateczku,\nlili lili laj, w ubogim żłóbeczku.\n\n" +
                "5. Nic mi nie mówisz, o kochanie moje!\nPrzecież pojmuję w sercu słowa Twoje!\n" +
                "Lili lili laj, o Boże wcielony,\nlili lili laj, nigdy nie zmierzony.\n\n",
        "1. Gore gwiazda Jezusowi w obłoku, w obłoku,\nJózef z Panną asystują przy boku, przy boku.\n\n" +
                "Ref. Hejże ino dyna, dyna, narodził się Bóg Dziecina,\nw Betlejem, w Betlejem. /x2\n\n" +
                "2. Wół i osioł w parze, służą przy żłobie, przy żłobie,\nhuczą, buczą delikatnej osobie, osobie.\n\n" +
                "3. Pastuszkowie z podarunki przybiegli, przybiegli,\nw koło szopę o północy oblegli, oblegli.\n\n" +
                "4. Anioł Pański sam ogłosił te dziwy,\nktórych oni nie słyszeli jak żywi, jak żywi.\n\n" +
                "5. Anioł Pański kuranciki wycina, wycina,\nstąd pociecha dla człowieka jedyna, jedyna.\n\n" +
                "6. Już Maryja Jezuleńka powiła, powiła,\nstąd wesele i pociecha nam miła, nam miła.\n\n",
        "1. Hej, w dzień narodzenia Syna Jedynego,\nOjca Przedwiecznego, Boga Prawdziwego.\nWesoło śpiewajmy, chwałę Bogu dajmy.\nHej, kolęda, kolęda! /x2\n\n" +
                "2. Panna porodziła niebieskie Dzieciątko,\nw żłobie położyła małe Pacholątko.\nPasterze śpiewają, na multankach grają.\nHej, kolęda! Kolęda! /x2\n\n" +
                "3. Skoro pastuszkowie o tym usłyszeli,\nzaraz do Betlejem czym prędzej bieżeli.\nWitając Dzieciątko, małe Pacholątko.\nHej, kolęda! Kolęda! /x2\n\n" +
                "4. Kuba nieboraczek nierychło przybieżał,\nśpieszno bardzo było, wszystkiego odbieżał.\nPanu nie miał co dać, kazali mu śpiewać.\nHej, kolęda! Kolęda! /x2\n\n" +
                "5. Dobył tak wdzięcznego głosu baraniego,\nże się Józef stary przestraszył od niego.\nJuż uciekać myśli, ale drudzy przyszli.\nHej, kolęda! Kolęda! /x2\n\n" +
                "6. Mówi mu Staruszek: „Nie śpiewaj tak pięknie,\nbo się głosu twego Dzieciątko przelęknie.\nLepiej Mu zagrajcie, Panu chwałę dajcie”.\nHej, kolęda! Kolęda! /x2\n\n",
        "1. Hojka, hojka, zieleni się trowka, zieleni się trowka,\nsiedzom sobie pastuszkowie, jedzom kaszym z gorka./x2\n\n" +
                "2. Przyszeł do nich janioł i tak im powiedział,\nże się Jezusek narodził, co by każdy wiedział.\n\n" +
                "3. Łoni się go zlynkli, na kolana klynkli,\nwsiecki łyżki i ważyszki wypadły im z rynki.\n\n" +
                "4. Wypadły im z rynki, jak się dowiedzieli\ni z darami do Betlejym wsiecy pobieleli.\n\n",
        "1. Jakaś światłość nad Betlejem się rozchodzi,\nw środku nocy przerażony świat się budzi.\n" +
                "Dzisiaj Chrystus tutaj właśnie się narodził,\ndo nas przyszedł, bo ukochał wszystkich ludzi.\n\n" +
                "Ref. Teraz śpij, Dziecino mała,\nteraz śpij, Dziecino miła.\nZiemia bogactw Ci nie dała,\nbo bez Ciebie biedną była.\n\n" +
                "2. Już pasterze, prości ludzie, biegną z dala,\noddać pokłon Tobie Panu nad panami.\n" +
                "Mówią sobie: „Jakaż łaska nas spotkała,\nże Bóg przyszedł, że chce zostać razem z nami”.\n\n" +
                "3. Z wschodnich krain przyszli jeszcze Trzej Królowie,\nkażdy myślał: „Coś wielkiego dziś zobaczę”.\n" +
                "Lecz zdziwili się ci wielcy monarchowie,\nże ich król jest malusieńki i że płacze.\n\n" +
                "Ref. Nie płacz już, Dziecino mała,\nnie płacz już, Dziecino miła.\nZiemia tym Cię zasmuciła,\nże tak mocno w grzech utkwiła.\n\n",
        "1. Jest taki dzień, bardzo ciepły, choć grudniowy.\nDzień, zwykły dzień, w którym gasną wszelkie spory.\n" +
                "Jest taki dzień, w którym radość wita wszystkich.\nDzień, który już każdy z nas zna od kołyski.\n\n" +
                "Ref. Niebo ziemi, niebu ziemia,\nwszyscy wszystkim ślą życzenia.\nDrzewa ptakom, ptaki drzewom,\ntchnienie wiatru płatkom śniegu.\n\n" +
                "2. Jest taki dzień, tylko jeden, raz do roku.\nDzień, zwykły dzień, który liczy się od zmroku.\n" +
                "Jest taki dzień, gdy jesteśmy wszyscy razem.\nDzień, piękny dzień, dziś nam rok go niesie w darze.\n\n" +
                "Ref. Niebo ziemi, niebu ziemia,\nwszyscy wszystkim ślą życzenia.\nA gdy wszyscy usną wreszcie,\nnoc igliwia zapach niesie.\n\n",
        "1. Jezu śliczny kwiecie,\nzjawiony na świecie!\n czemuż się w zimnie rodzisz,\n" +
                "ciężki mróz na się przywodzisz,\nnie na ciepłem lecie!\nnie na ciepłem lecie!\n\n" +
                "2. Jezu niepojęty, czemu nie z panięty.\nNie w pałacuś jest złożyny, w lichej szopie narodzony\ni między bydlęty! /x2\n\n" +
                "3. Niewinny Baranku, drżysz na gołem sianku.\nCzem nie w złotej kolebeczce, Czem nie w złotej kolebeczce,\nniewinny Baranku. /x2\n\n" +
                "4. Śliczna jak lilija, panienka Maryja.\nCała piękna jako róża, nie szuka pańskiego łoża,\nw żłobeczku powija. /x2\n\n" +
                "5. Anioł z nieba budzi najprzód prostych ludzi:\n„Pastuszkowie, prędzej wstajcie, w szopie Pana przywitajcie,\nco się dla was trudzi”. /x2\n\n" +
                "6. Pastuszkowie mali prędko się zebrali.\nTo z muzyką, to z pieśniami, to z różnemi ofiarami,\nPanu cześć dawali. /x2\n\n" +
                "7. Jezu kochany, nam z nieba zesłany.\nPrzez Twe święte Narodzenie, daj szczęśliwe powodzenie,\nżywot pożądany. /x2\n\n",
        "1. Jezus malusienki, leży wśród stajenki.\nPłacze z zimna, nie dała Mu Matula sukienki. /x2\n\n" +
                "2. Bo uboga była, rąbek z głowy zdjęła,\nw który Dziecię owinąwszy, siankiem Je okryła. /x2\n\n" +
                "3. Nie ma kolebeczki, ani poduszeczki,\nwe żłobie Mu położyła siana pod główeczki. /x2\n\n" +
                "4. Dziecina się kwili, Matusieńka lili,\nw nóżki zimno, żłóbek twardy, stajenka się chyli. /x2\n\n" +
                "5. Matusia truchleje, serdeczne łzy leje,\no mój Synu, wola Twoja, nie moja się dzieje. /x2\n\n",
        "1. Jezusa narodzonego wszyscy witajmy,\nJemu po kolędzie dary wzajem oddajmy.\nOddajmy wesoło, skłaniajmy swe czoło,\nskłaniajmy swe czoło Panu naszemu.\n\n" +
                "2. Oddajmy za złoto wiarę, czyniąc wyznanie,\nże to Bóg i człowiek prawy leży na sianie.\n" +
                "Oddajmy wesoło, skłaniajmy swe czoło,\nskłaniajmy swe czoło Panu naszemu.\n\n" +
                "3. Oddajmy też za kadzidło Panu nadzieję,\nże Go będziem widzieć w niebie, mówiąc to śmiele.\n" +
                "Oddajmy wesoło, skłaniajmy swe czoło,\nskłaniajmy swe czoło Panu naszemu.\n\n" +
                "4. Oddajmy za mirrę miłość na dowód tego,\nże Go nad wszystko kochamy, z serca całego.\n" +
                "Oddajmy wesoło, skłaniajmy swe czoło,\nskłaniajmy swe czoło Panu naszemu.\n\n" +
                "5. Przyjmij Jezu, na kolędę te nasze dary,\nprzyjmij serca, dusze nasze za swe ofiary.\n" +
                "Byśmy kiedyś w niebie, posiąść mogli Ciebie,\nposiąść mogli Ciebie na wieki wieków.\n\n",
        "1. A nadzieja znów wstąpi w nas,\nnieobecnych pojawią się cienie.\nUwierzymy kolejny raz\nw jeszcze jedno Boże Narodzenie.\n" +
                "I choć przygasł świąteczny gwar,\nbo zabrakło znów czyjegoś głosu.\nPrzyjdź tu do nas i z nami trwaj\nwbrew tak zwanej ironii losu.\n\n" +
                "Ref. Przyjdź na świat, by wyrównać rachunki strat,\nżeby zająć wśród nas puste miejsce przy stole.\n" +
                "Jeszcze raz pozwól cieszyć się dzieckiem w nas\ni zapomnieć, że są puste miejsca przy stole.\n\n" +
                "2. Daj nam wiarę, że to ma sens,\nże nie trzeba żałować przyjaciół,\nże gdziekolwiek są, dobrze im jest,\nbo są z nami, choć w innej postaci.\n" +
                "I przekonaj, że tak ma być,\nże po głosach tych wciąż drży powietrze,\nże odeszli po to, by żyć\ni tym razem będą żyć wiecznie.\n\n",
        "1. Lulaj, śpij maleńki, w cieple małej stajenki –\noczka zmruż, jutro znowu dzień cały\nbędą świat oglądały - wielki świat.\n\n" +
                "2. Lulaj, popatrz, gwiazda, która dotąd tak jasna –\nzgasła już, cicho jest w stajeneczce.\nNawet śpią przy żłóbeczku - osioł, wół.\n\n" +
                "3. Lulaj, śpij maleńki, w cieple małej stajenki –\nzaśnij wnet za stajenką, śnieg biały\njuż okrywa świat cały - biały w krąg.\n\n" +
                "4. Lulaj, śpij maleńki, w cieple małej stajenki –\nzaśnij już, chustą swą cię otulę,\ndo swej piersi przytulę - cicho sza.\n\n",
        "1. Lulajże Jezuniu, moja perełko,\nlulaj ulubione me pieścidełko.\nLulajże, Jezuniu, lulajże, lulaj,\na Ty Go, Matulu, w płaczu utulaj.\n\n" +
                "2. Zamknijże znużone płaczem powieczki,\nutulże zemdlone łkaniem usteczki.\nLulajże, Jezuniu, lulajże, lulaj,\na Ty Go, Matulu, w płaczu utulaj.\n\n" +
                "3. Lulajże, piękniuchny nasz Aniołeczku,\nlulajże, wdzięczniuchny świata Kwiateczku.\nLulajże Jezuniu, lulajże, lulaj,\na Ty Go, Matulu, w płaczu utulaj.\n\n",
        "1. Maleńki Jezu, przychodzisz zbawić świat,\nbo na nim ciągle tak wiele jeszcze zła.\n\n" +
                "Ref. Maleńki Jezu, zostań tu,\nzamieszkaj w każdym z nas.\nNiech w naszych sercach żyje Bóg,\nna zawsze w każdy czas.\n\n" +
                "2. Maleńki Jezu, w Betlejem rodzisz się,\nby wszystkim ludziom otworzyć serce swe.\n\n" +
                "3. Maleńkie Dziecię, przychodzisz do nas,\nbo dobrym uczynkiem Ty pragniesz zwyciężyć zło.\n\n",
        "Mario czy już wiesz,\nKim okaże się twój syn, twój mały chłopiec?\nMario czy ty wiesz,\nŻe te stópki dwie po wodzie będą kroczyć?\n" +
                "Czy ty wiesz,\nŻe ta mała dłoń powstrzyma wielki wiatr?\nŻe dałaś życie temu, kto tobie życie da?\n\nMario czy ty wiesz,\n" +
                "Że pewnego dnia przywróci wzrok ślepemu?\nMario czy już wiesz\nŻe twój synek ma wybawić nas od złego?\nCzy ty wiesz\n" +
                "że twój chłopiec już niebiańskie ścieżki zna\nŻe patrząc w jego oczy - najświętszą widzisz twarz\n\nMario, mario\n\n" +
                "Ślepemu wzrok, głuchemu dźwięk,\nZmarłemu życia dar.\nChromemu krok,\nNiememu pieśń,\nPrzynosi Chrystus Pan.\n\n" +
                "Mario czy ty wiesz\nKim naprawdę jest maleńki twój bohater?\nMario czy ty wiesz,\nże pewnego dnia On rządzić będzie światem?\n" +
                "Czy ty wiesz,\nże to właśnie on pokona grzech i śmierć?\nDziecko śpiące w twych ramionach, na imię ma\n\"Jam jest\"!\n\n",
        "1. Mędrcy świata, monarchowie,\ngdzie spiesznie dążycie?\nPowiedzcież nam Trzej Królowie,\nchcecie widzieć Dziecię?\n" +
                "Ono w żłobie nie ma tronu i berła nie dzierży,\na proroctwo Jego zgonu\njuż się w świecie szerzy. /x2\n\n" +
                "2. Mędrcy świata, złość okrutna\nDziecię prześladuje.\nWieść okropna, wieść to smutna,\nHerod spisek knuje.\n" +
                "Nic monarchów nie odstrasza,\ndo Betlejem spieszą.\nGwiazda Zbawcę im ogłasza,\nnadzieją się cieszą. /x2\n\n" +
                "3. Przed Maryją stoją społem,\nniosą Panu dary.\nPrzed Jezusem biją czołem,\nskładają ofiary.\n" +
                "Trzykroć szczęśliwi Królowie,\nktóż wam nie zazdrości?\nCóż my damy, kto nam powie,\npałając z miłości. /x2\n\n",
        "1. Mizerna, cicha stajenka licha,\npełna niebieskiej chwały.\nOto leżący, przed nami śpiący\nw promieniach Jezus mały.\n\n" +
                "2. Nad Nim Anieli w locie stanęli i pochyleni klęczą.\nZ włosy złotymi, z skrzydły białymi, pod malowaną tęczą.\n\n" +
                "3. Wielkie zdziwienie, wszystkie stworzenie, cały świat oniemiały.\nMądrość Mądrości, Światłość Światłości, Bóg człowiek tu wcielony.\n\n" +
                "4. I oto mnodzy ludzie ubodzy, chcący oglądać Pana.\nPełni natchnienia, pełni zdziwienia, upadli na kolana.\n\n" +
                "5. Oto Maryja, czysta Lilija, przy Niej staruszek drżący.\nStoją przed nami, przed pastuszkami, tacy uśmiechający.\n\n" +
                "6. Długo czekali, długo wzdychali, aż niebo rozgorzało.\nPiekło zawarte, niebo rozdarte, Słowo się Ciałem stało.\n\n" +
                "7. Hej, ludzie prości, Bóg z nami gości, skończony czas niedoli.\nOn daje siebie, chwała na Niebie, mir ludziom dobrej woli.\n\n",
        "1. Na tym niebie gwiazdeczka świeci,\nbardzo się dziwią starzy i dzieci.\nCo tam wysoko po niebie świeci,\nto chyba janioł po niebie leci.\n\n" +
                "2. Janioł leci, a za nim gwiazda,\nnarodził się największy gazda.\nBędzie posoł łowce po świecie,\ndo Betlejemu zaraz pójdzieci.\n\n" +
                "3. Tak jak postali se zaśpiewali,\nkolęda, kolęda pójdziemy dalej.\nPoszli po świecie, rozpowiadali,\no narodzeniu kolędę dali.\n\n" +
                "4. I się nie martwcie, że nic nie mocie,\npiszczołki weźcie i mu zogrojcie.\nCzego byśmy wam tu życzyli,\nbyście wolę Bożą pełnili.\n\n",
        "1. Nie było miejsca dla Ciebie,\nw Betlejem w żadnej gospodzie.\nI narodziłeś się Jezu,\nw stajni, w ubóstwie i chłodzie.\n" +
                "Nie było miejsca, choć zszedłeś,\njako Zbawiciel na ziemię,\nby wyrwać z czarta niewoli\nnieszczęsne Adama plemię.\n\n" +
                "2. Nie było miejsca, choć chciałeś\nludzkość przytulić do łona\ni podać z krzyża grzesznikom\nzbawcze, skrwawione ramiona.\n" +
                "Nie było miejsca, choć szedłeś\nogień miłości zapalić\ni przez swą mękę najdroższą,\nświat od zagłady ocalić.\n\n" +
                "3. Gdy liszki mają swe jamy\ni ptaszki swoje gniazdeczka,\ndla Ciebie brakło gospody,\nTyś musiał szukać żłóbeczka.\n" +
                "A dzisiaj, czemu wśród ludzi\ntyle łez, jęków, katuszy?\nBo nie ma miejsca dla Ciebie\nw niejednej człowieczej duszy!\n\n" +
                "4. Nie było miejsca, choć chciałeś\nWszystkim otworzyć swe serce\nI kres położyć miłośnie\nludzkiej nędzy, poniewierce\n" +
                "A dzisiaj, czemu wśród ludzi\ntyle łez, jęków, katuszy?\nBo nie ma miejsca dla Ciebie\nw niejednej człowieczej duszy!\n\n",
        "1. Nowy Rok bieży, w jasełkach leży, a kto, kto?\nDzieciątko małe, dajcie Mu chwałę na ziemi./x2\n\n" +
                "2. Leży Dzieciątko jako jagniątko, a gdzie, gdzie?\nW Betlejem mieście, tam się pośpieszcie, znajdziecie. /x2\n\n" +
                "3. Jak Go poznamy, gdy Go nie znamy, Jezusa?\nPodło uwity, nie w aksamity, ubogo. /x2\n\n" +
                "4. Wół, osioł ziewa, parą zagrzewa, a jakoż?\nKlęcząc, padając, chwałę oddając przy żłobie. /x2\n\n" +
                "5. Anieli grają, wdzięcznie śpiewają, a jak, jak?\nNiech chwała będzie zawsze i wszędzie Dzieciątku. /x2\n\n" +
                "6. Królowie jadą z wielką gromadą, a skąd, skąd?\nOd wschodu słońca, szukają końca zbawienia./x2\n\n" +
                "7. Skarb otwierają, dary dawają, a komu?\nWielcy królowie, możni panowie Dzieciątku. /x2\n\n" +
                "8. Pójdźcie, kapłani, do tejże stajni, a proście.\nNiech w rękach waszych, a ustach naszych, Bóg rośnie! /x2\n\n" +
                "9. Pójdź miła młodzi, Jezus się rodzi w stajence.\nByś wzięła, prosi, rączki podnosi dziecięce./x2\n\n" +
                "10. Pójdźcie, panienki, do tej stajenki, klęknijcie!\nWam Oblubieniec da rajski wieniec, dziękujcie! /x2\n\n" +
                "11. W organy grajcie, dziatki śpiewajcie, hej, hej, hej!\nWdzięcznymi głosy, aż pod niebiosy wsławiajcie. /x2\n\n",
        "1. O gwiazdo Betlejemska,\nzaświeć na niebie mym.\nTak szukam Cię wśród nocy,\ntęsknię za światłem Twym.\n" +
                "Zaprowadź do stajenki,\nleży tam Boży Syn,\nBóg - człowiek z Panny świętej,\ndany na okup win.\n\n" +
                "2. O nie masz Go już w szopce,\nnie masz Go w żłóbku tam!\nWięc gdzie pójdziemy Chryste?\nGdzie się ukryłeś nam?\n" +
                "Pójdziemy przed ołtarze,\nwzniecić miłości żar\ni hołd Ci niski oddać:\nto jest nasz wszystek dar.\n\n" +
                "3. Ja nie wiem, o mój Panie,\nktóryś miał w żłobie tron.\nCzy dusza moja biedna,\nmilsza Ci jest, niż on.\n" +
                "Ulituj się nade mną,\nbłagać Cię kornie śmiem,\ngdyś stajnią nie pogardził,\nnie gardź i sercem mym.\n\n",
        "1. Oj, Maluśki, Maluśki, Maluśki, jako rękawicka.\nAlboli tyz jakoby, jakoby kawałecek smycka.\n\n" +
                "Ref. Śpiewajcie i grajcie mu, małemu, małemu.\n\n" +
                "2. Cy nie lepiej by Tobie, by Tobie siedzieć było w niebie.\nWsak Twój Tatuś kochany, kochany nie wyganiał Ciebie.\n\n" +
                "3. Tam wciornaska wygoda, wygoda, a tu bieda wsędzie.\nTa Ci teraz dokuca, dokuca, ta i potem będzie.\n\n" +
                "4. Tam Ty miałeś pościółkę, pościółkę i mietkie piernatki.\nTu na to Twej nie stanie, nie stanie ubozuchnej Matki.\n\n" +
                "5. Tam kukiołki jadałeś, jadałeś z carnuską i miodem.\nTu się tylko zasilać, zasilać musis samym głodem.\n\n" +
                "6. Tam pijałeś ceć jakie, ceć jakie słodkie małmazje.\nTu się Twoja gębusia, gębusia łez gorskich napije.\n\n" +
                "7. Tam Ci zawse słuzyły, słuzyły prześlicne janioły.\nA tu lezys sam jeden, sam jeden jako palec goły.\n\n" +
                "8. Hej, co się więc takiego, takiego Tobie, Panie stało.\nZeś się na ten kiepski świat, kiepski świat przychodzić zachciało.\n\n",
        "1. Pasterze mili, coście widzieli?\nWidzieliśmy Maleńkiego\nJezusa narodzonego,\nSyna Bożego, Syna Bożego.\n\n" +
                "2. Co za pałac miał, gdzie gospodą stał?\nSzopa bydłu przyzwoita\ni to jeszcze źle pokryta,\npałacem była, pałacem była.\n\n" +
                "3. Jakie łóżeczko, miał Paniąteczko?\nMarmur twardy, żłób kamienny,\nna tym Depozyt zbawienny,\nspoczywał łożu, spoczywał łożu.\n\n" +
                "4. Co za obicie miało to Dziecię?\nWisząc spod strzech pajęczyna.\nBoga i Maryi Syna,\nobiciem była, obiciem była.\n\n" +
                "5. W jakiej odzieży Pan nieba leży?\nZa purpurę, perły drogie,\nustroiła Go w ubogie,\npieluszki nędza, pieluszki nędza.\n\n" +
                "6. Czyli w gospodach? Czy spał w swobodach?\nNa barłogu, ostrym sianie\ndelikatne spało Panię,\na nie w łabędziach, a nie w łabędziach.\n\n" +
                "7. Kto asystował? Kto Go pilnował?\nWół i osioł przyklękali,\nparą swą Go ogrzewali,\ndworzanie Jego, dworzanie Jego.\n\n" +
                "8. Jakie kapele nuciły trele?\nAniołowie Mu śpiewali,\nmy na dudkach przygrywali,\nskoczno wesoło, skoczno wesoło.\n\n" +
                "9. Kto więc śpieszył, by Dziecię cieszył?\nJózef święty z Panieneczką\nmelodyjną swą piosneczką\nDziecię cieszyli, Dziecię cieszyli.\n\n" +
                "10. Jakieście dary dali, ofiary?\nSercaśmy własne oddali,\na odchodząc poklękali,\nczołem Mu bili, czołem Mu bili.\n\n",
        "1. Pójdźmy wszyscy do stajenki,\ndo Jezusa i Panienki.\nPowitajmy Maleńkiego\ni Maryję, Matkę Jego. /x2\n\n" +
                "2. Witaj Jezu ukochany,\nod patriarchów czekany.\nOd proroków ogłoszony,\nod narodów upragniony. /x2\n\n" +
                "3. Witaj Dzieciąteczko w żłobie,\nwyznajemy Boga w Tobie.\nCoś się narodził tej nocy,\nbyś nas wyrwał z czarta mocy. /x2\n\n" +
                "4. Witaj, Jezu nam zjawiony,\nwitaj dwakroć narodzony.\nRaz z Ojca przed wieków wiekiem,\na teraz z Matki człowiekiem. /x2\n\n" +
                "5. Kto to słyszał takie dziwy?\nTyś człowiek i Bóg prawdziwy.\nTy łączysz w Boskiej Osobie,\ndwie natury różne sobie. /x2\n\n" +
                "6. Tyś świat stworzył, a świat Ciebie\nnie poznał, mając wśród siebie.\nIdziesz dla jego zbawienia,\nOn Ci odmawia schronienia. /x2\n\n" +
                "7. Za to u świata ubogich,\nale w oczach Twoich drogich.\nPastuszków, którzy czuwali,\nwzywasz, by cię przywitali. /x2\n\n",
        "1. Przybieżeli do Betlejem pasterze,\ngrając skocznie Dzieciąteczku na lirze.\n\n" +
                "Ref. Chwała na wysokości,\nChwała na wysokości, a pokój na ziemi. /x2\n\n" +
                "2. Oddawali swe ukłony w pokorze.\nTobie z serca ochotnego, o Boże.\n\n" +
                "3. Anioł Pański sam ogłosił te dziwy,\nktórych oni nie słyszeli jak żywi.\n\n" +
                "4. Dziwili się napowietrznej muzyce\ni myśleli co to będzie za Dziecię?\n\n" +
                "5. Oto Mu się wół i osioł kłaniają,\nTrzej Królowie podarunki oddają.\n\n" +
                "6. I Anieli gromadami pilnują,\nPanna czysta wraz z Józefem piastują.\n\n" +
                "7. Poznali Go Mesyjaszem być prawym,\nnarodzonym dzisiaj Panem łaskawym.\n\n" +
                "8. My go także Bogiem, Zbawcą już znamy\ni z całego serca wszyscy kochamy.\n\n",
        "1. Przystąpmy do szopy, uściskajmy stopy\nJezusa maleńkiego,\nktóry swoje Bóstwo wydał na ubóstwo\ndla zbawienia naszego.\nZawitaj, Zbawco narodzony\n" +
                "z przeczystej Panienki.\nGdzie berło, gdzie Twoje korony,\ngdzie berło, gdzie Twoje korony,\nJezu malusieńki.\n\n" +
                "2. Ten, co wszechświat dzierży,\nw żłobie dzisiaj leży, ludzkiej pomocy czeka.\nJezus, Bóg wcielony, w żłobie wyniszczony\n" +
                "dla zbawienia człowieka.\nO Boże, bądźże pochwalony\nza Twe narodzenie!\n" +
                "Racz zbawić ludzki ród zgubiony,\nracz zbawić ludzki ród zgubiony,\ndaj duszy zbawienie.\n\n",
        "1. Skrzypi wóz, wielki mróz, wielki mróz na ziemi. /x2\nTrzej królowie jadą, złoto, mirrę kładą.\nHej, kolęda, kolęda. /x2\n\n" +
                "2. A komóż takiemu, Dzieciątku małemu. /x2\nCóż to za dzieciątko, musi być Paniątko. Hej, kolęda…\n\n" +
                "3. Pasterze na lirze na skrzypeczkach grali. /x2\nW tę stronę do szopy prędko przybiegali. Hej, kolęda…\n\n" +
                "4. To Jezus maluśki, to Dzieciątko krasne. /x2\nCichutkie, malutkie, jak słoneczko jasne. Hej, kolęda…\n\n" +
                "5. Pójdę do niego poproszę od Niego. /x2\nDaj nam Boże Dziecię pokój na tym świecie. Hej, kolęda…\n\n",
        "1. Zaprowadź mnie prosto do Betlejem,\nzaprowadź mnie, gdzie Bóg narodził się.\nZaprowadź mnie, nie mogę spóźnić się,\nnie mogę spóźnić się!\n\n" +
                "Ref. Świeć gwiazdeczko mała świeć,\ndo Jezusa prowadź mnie.\n\n" +
                "2. Narodził się Bóg, stąpił na Ziemię.\nNarodził się, by uratować mnie.\nNarodził się i nie zostawił mnie.\nI nie zostawił mnie!\n\n" +
                "3. Czekają tam, Józef i Maryja.\nŚpiewają nam, śpiewają gloriia.\nTo gloriia, święta historiia.\nŚwięta historiia!\n\n",
        "1. Triumfy Króla niebieskiego,\nzstąpiły z nieba wysokiego.\nPobudziły pasterzów, dobytku swego stróżów.\nŚpiewaniem, śpiewaniem, śpiewaniem./x2\n\n" +
                "2. Chwała bądź Bogu w wysokości,\na ludziom pokój na niskości.\nNarodził się Zbawiciel, dusz ludzkich Odkupiciel.\nNa ziemi, na ziemi, na ziemi./x2\n\n" +
                "3. Zrodziła Maryja Dziewica\nwiecznego Boga bez rodzica.\nBy nas z piekła wybawił, a w niebieskich postawił.\nPałacach, pałacach, pałacach./x2\n\n" +
                "4. Pasterze w podziwieniu stają,\ntriumfu przyczynę badają.\nCo się nowego dzieje, że tak światłość jaśnieje.\nNie wiedząc, nie wiedząc, nie wiedząc./x2\n\n" +
                "5. Że to Bóg, gdy się dowiedzieli,\nswej trzody w polu odbieżeli.\nSpiesząc na przywitanie do Betlejemskiej stajni.\nDzieciątka, dzieciątka, dzieciątka./x2\n\n" +
                "6. Niebieskim światłem oświeceni,\npokornie przed nim uniżeni.\nBogiem Go być prawdziwym z serca afektem żywym.\nWyznają, wyznają, wyznają./x2\n\n" +
                "7. I które mieli z sobą dary\nDzieciątku dają na ofiary:\n„Przyjmij, o Narodzony, nas i dar przyniesiony.\nZ ochotą, z ochotą, z ochotą”./x2\n\n",
        "1. Tej nocy Anioł przyszedł do Józefa:\n„Zbudźcie dzieciątko, co tak słodko śpi.\nJudea dziś to niebezpieczna strefa\ni policzone małych chłopców dni”.\n" +
                "Namiestnik Herod ciągle drży w obawie,\nże chociaż władzę dzierży jego dłoń\ni rzymski cesarz patrzy nań łaskawie,\nto w rączkach dziecka może kryć się broń.\n\n" +
                "Ref. Uciekali, uciekali, uciekali,\nna osiołku, przez pustyni żar.\nJak najdalej, jak najdalej, jak najdalej,\nukryć Dziecię, największy ich skarb.\n\n" +
                "2. Ciężko ojczyznę wspominać w pustyni,\nmieliśmy jakoś zapewniony byt.\nChoć czasem patrol mijał drzwi świątyni,\nzawsze się modlić mógł pobożny Żyd.\n" +
                "Lecz może trzeba szlakiem iść wygnania\nTak, jak przodkowie nasi kiedyś szli.\nMoże nie tu jest Ziemia Obiecana,\nmoże gdzie indziej będzie rósł nasz Syn.\n\n" +
                "3. Jeszcze nie wiemy co nam przeznaczone,\nlecz choć przeczucia czasem dręczą złe.\nOjciec Niebieski weźmie nas w obronę\ni znowu Anioł zjawi się we śnie.\n" +
                "Piasek przysypał szlaki do Betlejem,\nzabłądzić łatwo, trudny każdy krok.\nTak mało mamy, tylko tę nadzieję,\nktóra rozjaśnia nam codzienny mrok.\n\n",
        "1. W grudniowe noce, w zimowe noce\nDzieciątko Boże z zimna dygoce.\nIdzie przez pola pokryte szronem,\nwiatr mu wydzwania piosnki znajome.\n\n" +
                "Ref. Hej, kolęda, kolęda.\nHej, kolędy to czas.\nKto ogrzeje rączęta?\nKto schronienie mu da?\n\n" +
                "2. Tyle tych domów i okien tyle,\nmoże Go schronią choćby przez chwilę.\nMoże przygarną do serca ludzie,\nmoże nakarmią, nim dalej pójdzie.\n\n" +
                "3. Świeczki się jarzą w świerkach zielonych\ni złote gwiazdy wieńczą korony.\nBo zewsząd dzwoni kolęda święta,\nlecz o Nim, o Nim nikt nie pamięta.\n\n",
        "1. W żłobie leży, któż pobieży\nkolędować Małemu,\nJezusowi Chrystusowi\ndziś nam narodzonemu.\n" +
                "Pastuszkowie przybywajcie,\nJemu wdzięcznie przygrywajcie,\njako Panu naszemu./x2\n\n" +
                "2. My zaś sami z piosneczkami,\nza wami pospieszymy,\na tak tego Maleńkiego,\nniech wszyscy zobaczymy.\n" +
                "Jak ubogo narodzony,\npłacze w stajni położony,\nwięc go dziś ucieszymy./x2\n\n" +
                "3. Naprzód tedy niechaj wszędy\nzabrzmi świat w wesołości,\nże posłany jest nam dany\nEmanuel w niskości.\n" +
                "Jego tedy przywitajmy,\nz aniołami zaśpiewajmy\n„Chwała na wysokości”./x2\n\n" +
                "4. Witaj Panie, cóż się stanie,\nże rozkosze niebieskie\nopuściłeś, a zstąpiłeś\nna te niskości ziemskie?\n" +
                "„Miłość moja to sprawiła,\nby człowieka wywyższyła\npod nieba empirejskie”./x2\n\n",
        "1. Wesołą nowinę, bracia słuchajcie,\nniebieską Dziecinę ze mną witajcie.\n" +
                "Jak miła ta nowina! Mów, gdzie jest Ta Dziecina?\nByśmy tam pobieżeli i ujrzeli.\n\n" +
                "2. Bogu chwałę wznoszą na wysokości,\npokój ludziom głoszą duchy światłości.\n" +
                "Jak miła ta nowina! Mów, gdzie jest Ta Dziecina?\nByśmy tam pobieżeli i ujrzeli.\n\n" +
                "3. Panna wam powiła Boskie Dzieciątko,\npokłonem uczciła to Niemowlątko.\n" +
                "Jak miła ta nowina! Mów, gdzie jest Ta Dziecina?\nByśmy tam pobieżeli i ujrzeli.\n\n" +
                "4. Którego zrodziła, Bogiem uznała\ni Panna, jak była, Panną została.\n" +
                "Jak miła ta nowina! Mów, gdzie jest Ta Dziecina?\nByśmy tam pobieżeli i ujrzeli.\n\n" +
                "5. Królowie ze wschodu już to poznali\ni w judzkim narodzie szukać jechali.\n" +
                "Jak miła ta nowina! Mów, gdzie jest Ta Dziecina?\nByśmy tam pobieżeli i ujrzeli.\n\n" +
                "6. Gwiazda najśliczniejsza ich oświeciła,\ndo szopy w Betlejem zaprowadziła.\n" +
                "Jak miła ta nowina! Mów, gdzie jest Ta Dziecina?\nByśmy tam pobieżeli i ujrzeli.\n\n",
        "1. Wśród nocnej ciszy głos się rozchodzi:\nwstańcie pasterze, Bóg się wam rodzi!\n" +
                "Czem prędzej się wybierajcie,\ndo Betlejem pospieszajcie\nprzywitać Pana. /x2\n\n" +
                "2. Poszli, znaleźli Dzieciątko w żłobie,\nz wszystkimi znaki danymi sobie.\n" +
                "Jako Bogu cześć Mu dali,\na witając zawołali\nz wielkiej radości. /x2\n\n" +
                "3. Ach witaj Zbawco z dawna żądany,\ntyle tysięcy lat wyglądany.\n" +
                "Na ciebie króle, prorocy,\nCzekali, a tyś tej nocy\nnam się objawił. /x2\n\n" +
                "4. I my czekamy na Ciebie, Pana,\na skoro przyjdziesz na głos kapłana.\n" +
                "Padniemy na twarz przed Tobą,\nWierząc, żeś jest pod osłoną\nchleba i wina. /x2\n\n",
        "1. Z narodzenia Pana dzień dziś wesoły,\nwyśpiewują chwałę Bogu żywioły.\nRadość ludzi wszędzie słynie,\n" +
                "Anioł budzi przy dolinie,\nPasterzów, co paśli pod borem woły./x2\n\n" +
                "2. Wypada wśród nocy ogień z obłoku,\ndumają pasterze w takim widoku.\nKażdy pyta: „Co się dzieje?\n" +
                "Czy nie świta? Czy nie dnieje?\nSkąd ta łuna bije, tak miła oku?”/x2\n\n" +
                "3. Ale gdy anielskie głosy słyszeli, zaraz\ndo Betlejem prosto bieżeli.\n" +
                "Tam witali w żłobie Pana,\npoklękali na kolana\ni oddali dary, co z sobą wzięli./x2\n\n"
    )
}