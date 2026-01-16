package com.example.myapplication.debug

import com.example.myapplication.data.modules.CustomerType
import com.example.myapplication.ui.component.MenuItem

/* --------------------------------------------- Variabili Globali ------------------------------------------*/
val textDescription = """Sto facendo il refactoring del file dei componenti (era 1570 righe di codice tra codice puro e gli import) in:
- Utilities --> Dove metto le funzioni tipo per stabilire di che colore devono essere i componenti in base al loro tipo (Ele, CDZ o ALLARME) 
- Cards --> Dove vanno i componenti cliccabili che poi mostrano tutti i dati 
- Lists --> Dove metto tutte le liste di altri componenti che ho creato perchè ce ne sono diverse . Ad esempio quella che abbiamo fatto insieme, 
non so se ti ricordi, che metteva la lettera iniziale dei cognomi, poi tutti i clienti con quella lettera e poi di nuovo un altra lettera dentro un 
cerchio per dividere in ordine alfabetico i clienti 
- Buttons --> Dove metto tutti i vari bottoni
- DropDownMenu --> Dove metto tutti i menu a tendina
- Tables --> Dove metto la tabella
- GeneralComponent --> Tutti i componenti rimasti, quindi gli avatar (sono i cerchi con la lettera dentro), la TopAppBar (la barra sopra che indica 
in che sezione si è e quali impostazioni si possono cambiare per quella schermata), le Label (Sono solo 2 e sono quelle che nel video c'è la parte verde 
che indica la specifica e la parte verde chiaro che indica tipo il cognome), l'AlertDialog (il menu a comparsa tipo popup)
"""

var itemsList = List<String>(10, {item -> "item $item"})

var item_num = listOf<Pair<String,Int>>(
    Pair<String,Int>("item", 10),
    Pair<String,Int>("item", 5),
    Pair<String,Int>("item", 3),
    Pair<String,Int>("item", 4),
    Pair<String,Int>("item", 1),
)

var customers = listOf(
    "Alessandro", "Andrea", "Anna", "Alessia", "Amelia", "Antonio",
    "Barbara", "Bruno", "Beatrice", "Bianca", "Benedetto", "Bernardo",
    "Carlo", "Chiara", "Cristina", "Camilla", "Cesare", "Claudia",
    "Davide", "Daniela", "Diego", "Dario", "Diana", "Domenico",
    "Elena", "Emanuele", "Erica", "Elisa", "Enrico", "Eugenio",
    "Francesco", "Federica", "Fabio", "Flavio", "Francesca", "Franco",
    "Gabriele", "Giulia", "Giovanni", "Gabriella", "Giancarlo", "Giorgio",
    "Hanna", "Hugo", "Helena",
    "Ivan", "Isabella", "Irene", "Ilaria", "Ivo", "Ivana",
    "Jacopo", "Jasmine", "Julia", "Jasmine", "Jeremy", "Jessica",
    "Kevin", "Katia", "Kyle", "Katia", "Kenneth", "Kristen",
    "Luca", "Laura", "Leonardo", "Liliana", "Lorenzo", "Lucia",
    "Marco", "Martina", "Matteo", "Marina", "Mario", "Marisa",
    "Nicola", "Nadia", "Nina", "Nadine", "Nicolò", "Noemi",
    "Omar", "Olivia", "Ottavia", "Olimpia", "Oreste", "Ottavio",
    "Paolo", "Paola", "Pietro", "Patrizia", "Piero", "Pietro",
    "Quentin", "Quinn", "Quirino",
    "Riccardo", "Roberta", "Rocco", "Raffaella", "Raimondo", "Rebecca",
    "Simone", "Sofia", "Stefano", "Sabrina", "Salvatore", "Samantha",
    "Tommaso", "Tiziana", "Tobias", "Tania", "Teodoro", "Teresa",
    "Umberto", "Ursula", "Ugo", "Ubaldo", "Umberto", "Ursula",
    "Valentina", "Vincenzo", "Veronica", "Valeria", "Vincenzo", "Virginia",
    "William", "Wendy", "Walter", "Walter", "Wendy", "Wilhelm",
    "Xander", "Xenia", "Xiomara", "Xanthe", "Xenia", "Ximena",
    "Ylenia", "Yves", "Yolanda", "Yara", "Yves", "Yolanda",
    "Zeno", "Zoe", "Zoran", "Zaira", "Zeno", "Zita"
)

val letters = customers.map { it.get(0) }.distinct()

data class Prodotto(
    val nome: String,
    val modello: String,
    var quantita: Int,
    val prezzo : Double,
    val iva : Int,
    val unitaMisura: String,
    val tipo : String
)

var prodotti = listOf<Prodotto>(
    Prodotto("Interruttore", "Bticino", 10, 20.0, 22, "u", "ELE"),
    Prodotto("Interruttore", "Vimar", 50, 20.0, 10, "u","ELE"),
    Prodotto("Deviatore", "Bticino", 10, 20.0, 22, "m","ELE"),
    Prodotto("Calma", "Bticino", 10, 20.0, 22, "cm","ELE"),
    // ----------------------------------------------------
    // CATEGORIA 1: ELETTRONICA E TECNOLOGIA (Prodotti 1-30)
    // ----------------------------------------------------
    Prodotto("Smartphone", "Modello X12", 50, 799.99, 22, "Pz","ELE"),
    Prodotto("Laptop Gaming", "Aurora R5", 25, 1599.50, 22, "Pz","ELE"),
    Prodotto("Smartwatch", "Geo 3", 150, 199.00, 22, "Pz","ELE"),
    Prodotto("Cuffie Wireless", "SoundBlast 5", 300, 89.90, 22, "Pz","ELE"),
    Prodotto("Power Bank", "Energy 20k", 400, 35.50, 22, "Pz","ALA"),
    Prodotto("Tastiera Meccanica", "K-Raptor", 80, 119.99, 22, "Pz","ALA"),
    Prodotto("Mouse Ottico", "M-Comfort", 600, 12.50, 22, "Pz","ALA"),
    Prodotto("Monitor 27'' 4K", "ViewUltra", 30, 450.00, 22, "Pz","ALA"),
    Prodotto("Webcam Full HD", "CamPro", 220, 55.99, 22, "Pz","ALA"),
    Prodotto("Scheda SD 128GB", "Flash XL", 500, 29.99, 22, "Pz","ELE"),
    Prodotto("Router Wi-Fi 6", "SpeedNet 1000", 75, 95.00, 22, "Pz","ELE"),
    Prodotto("Altoparlante Bluetooth", "BassCube", 180, 65.00, 22, "Pz","ELE"),
    Prodotto("Stampante Laser B/N", "PrintFast 200", 40, 240.00, 22, "Pz","ELE"),
    Prodotto("Cartuccia Toner Nera", "T-2000", 120, 45.90, 22, "Pz","ELE"),
    Prodotto("NAS 2 Bay", "Storage Pro", 15, 320.00, 22, "Pz","CDZ"),
    Prodotto("Cavo HDMI 2m", "HD-Connect", 700, 8.50, 22, "Pz","CDZ"),
    Prodotto("Adattatore USB-C", "MultiPort", 350, 19.99, 22, "Pz","CDZ"),
    Prodotto("Fotocamera Mirrorless", "Alpha Z", 10, 850.00, 22, "Pz","CDZ"),
    Prodotto("Obiettivo 50mm", "Prime Lens", 18, 290.00, 22, "Pz","CDZ"),
    Prodotto("Drone Compact", "FlyMini", 5, 499.00, 22, "Pz","ALA"),
    Prodotto("Smart TV 55'' OLED", "Vision 55", 8, 1199.00, 22, "Pz","ALA"),
    Prodotto("Soundbar", "AudioWave", 35, 149.99, 22, "Pz","ALA"),
    Prodotto("Lettore Blu-Ray", "DiscPlay 4", 12, 85.00, 22, "Pz","ALA"),
    Prodotto("Joystick PC", "GamePad Pro", 90, 25.00, 22, "Pz","ALA"),
    Prodotto("Lampada LED Smart", "Light IQ", 280, 15.00, 22, "Pz","CDZ"),
    Prodotto("Termostato Intelligente", "ClimaHome", 60, 99.00, 22, "Pz","CDZ"),
    Prodotto("Presa Smart Wi-Fi", "PlugON", 450, 18.00, 22, "Pz","NONE"),
    Prodotto("Mini PC Desktop", "Cube Mini 4", 20, 399.00, 22, "Pz","CDZ"),
    Prodotto("Ventola Raffreddamento PC", "Cooler Max", 100, 22.00, 22, "Pz", "CDZ"),
    Prodotto("Hard Disk Esterno 2TB", "StoreSafe", 140, 75.90, 22, "Pz","CDZ"),
)

var materialList: List<String> = prodotti.map { prodotto -> prodotto.nome}

var suggerimenti = listOf("Suggerimento 1", "Suggerimento 2", "Suggerimento 3", "Ciao")

data class Intervento(
    val cognomeNome: String,
    val indirizzo: String,
    val comune: String,
    val cap: String,
    val provincia: String,
    val prezzo: Float,
    val data: String,
    val oraI: String,
    val oraF: String,
    val tipo: String
)

var interventi = listOf(
    Intervento("Rossi Mario",    "Via Garibaldi 12",      "Milano",  "20121", "MI",  85.50f, "14/12/2025", "09:00", "10:30", "ELE"),
    Intervento("Bianchi Anna",   "Piazza Duomo 3",       "Milano",  "20122", "MI", 120.00f, "15/12/2025", "11:00", "12:15", "ALA"),
    Intervento("Verdi Luca",     "Corso Italia 45",      "Torino",  "10121", "TO",  60.75f, "16/12/2025", "08:30", "09:15", "CDZ"),
    Intervento("Ferrari Laura",  "Via Roma 7",           "Genova",  "16121", "GE",  95.20f, "17/12/2025", "13:00", "14:45", "NONE"),
    Intervento("Esposito Marco", "Viale Europa 10",      "Napoli",  "80121", "NA", 150.00f, "18/12/2025", "15:30", "17:00", "ELE"),
    Intervento("Romano Silvia",  "Via Po 29",            "Torino",  "10124", "TO",  72.30f, "19/12/2025", "09:15", "10:00", "ALA"),
    Intervento("Ricci Paolo",    "Via Mazzini 2",        "Bologna", "40121", "BO",  45.00f, "20/12/2025", "10:30", "11:00", "CDZ"),
    Intervento("Gallo Elisa",    "Piazza San Marco 1",   "Venezia", "30124", "VE", 110.40f, "21/12/2025", "14:00", "15:30", "ELE"),
    Intervento("Moretti Fabio",  "Via Larga 8",          "Firenze", "50123", "FI",  55.99f, "22/12/2025", "08:00", "08:45", "NONE"),
    Intervento("Conti Laura",    "Via Sant'Angelo 6",    "Bari",    "70121", "BA", 130.00f, "23/12/2025", "16:00", "17:30", "ALA")
)

val tipi = interventi.map { intervento -> intervento.tipo }.distinct()

data class Pagamenti(
    val cliente: String,
    val indirizzo: String,
    val prezzo: String,
    val data: String,
    val fattura: String
)

var pagamenti = listOf(
    Pagamenti("Mario Rossi", "Via Roma 1, Milano", "€120.00", "05/02/2025", fattura = "1"),
    Pagamenti("Giulia Bianchi", "Corso Garibaldi 12, Torino", "€85.50", "12/03/2025", fattura = "2"),
    Pagamenti("Luca Verdi", "Piazza Duomo 3, Milano", "€200.00", "20/01/2025", fattura = "3"),
    Pagamenti("Sara Neri", "Via Dante 7, Firenze", "€45.00", "08/04/2025", fattura = "4"),
    Pagamenti("Paolo Romano", "Viale Europa 20, Napoli", "€99.99", "17/02/2025", fattura = "5"),
    Pagamenti("Elena Russo", "Via Giulia 5, Roma", "€60.00", "01/01/2025", fattura = "6"),
    Pagamenti("Andrea Ferri", "Via Manzoni 10, Bologna", "€150.00", "23/05/2025", fattura = "7"),
    Pagamenti("Francesca Sala", "Via Po 18, Torino", "€30.00", "14/06/2025", fattura = "8"),
    Pagamenti("Matteo Longo", "Corso Venezia 25, Milano", "€250.00", "30/03/2025", fattura = "9"),
    Pagamenti("Valentina Greco", "Via San Marco 8, Venezia", "€75.00", "09/07/2025", fattura = "10"),
    Pagamenti("Roberto Fontana", "Viale Mazzini 2, Genova", "€1.100.00", "28/02/2025", fattura = "11"),
    Pagamenti("Chiara Conte", "Piazza San Carlo 4, Torino", "€40.00", "06/08/2025", fattura = "12"),
    Pagamenti("Stefano Moretti", "Via Cavour 14, Roma", "€95.00", "11/09/2025", fattura = "13"),
    Pagamenti("Marta De Luca", "Via S. Lucia 9, Napoli", "€55.00", "19/10/2025", fattura = "14"),
    Pagamenti("Giorgio Martini", "Corso Italia 6, Firenze", "€180.00", "04/11/2025", fattura = "15"),
    Pagamenti("Laura Rinaldi", "Via Nettuno 11, Bari", "€65.00", "15/12/2025", fattura = "16"),
    Pagamenti("Nicola Caputo", "Piazza Maggiore 1, Bologna", "€130.00", "02/05/2025", fattura = "17"),
    Pagamenti("Elisa Romano", "Via Verdi 22, Parma", "€49.90", "21/06/2025", fattura = "18"),
    Pagamenti("Alessio Galli", "Via Larga 16, Milano", "€210.00", "27/07/2025", fattura = "19"),
    Pagamenti("Federica Pini", "Viale Regina Margherita 30, Roma", "€70.00", "13/08/2025", fattura = "20")
)

data class Appuntamento(
    val indirizzo: String,
    val cliente: String,
    val tipo: String,
    val descrizione: String
)

var appuntamenti = listOf(
    Appuntamento("Via Garibaldi 12, Milano", "Rossi Mario", "ELE",  "Sostituzione contatore elettrico, controllo cablaggio"),
    Appuntamento("Piazza Duomo 3, Milano",   "Bianchi Anna", "ALA",  "Installazione antenna TV sul tetto"),
    Appuntamento("Corso Italia 45, Torino",  "Verdi Luca",   "CDZ",  "Manutenzione condizionatore split, ricarica gas"),
    Appuntamento("Via Roma 7, Genova",       "Ferrari Laura","NONE", "Ispezione generale, nessun intervento previsto"),
    Appuntamento("Viale Europa 10, Napoli",  "Esposito Marco","ELE", "Riparazione quadro elettrico, sostituzione fusibili"),
    Appuntamento("Via Po 29, Torino",        "Romano Silvia","ALA",  "Regolazione antenna satellitare"),
    Appuntamento("Via Mazzini 2, Bologna",   "Ricci Paolo",  "CDZ",  "Pulizia filtri e controllo compressore"),
    Appuntamento("Piazza San Marco 1, Venezia","Gallo Elisa","ELE",  "Verifica impianto e sostituzione presa"),
    Appuntamento("Via Larga 8, Firenze",     "Moretti Fabio","NONE", "Consulenza e preventivo"),
    Appuntamento("Via Sant'Angelo 6, Bari",  "Conti Laura",  "ALA",  "Montaggio antenna per ricezione digitale")
)

val categorie_fatture = listOf("Ristorante","Fornitore","Macchina")

val venditori = listOf("Venditore1", "Venditore2", "Venditore3" )

data class Scadenze(
    val fornitore: String,
    val categoria: String,
    val prezzo: Float,
    val data: String
)

var scadenze = listOf(
    Scadenze("Pizzeria Bella", "Ristorante", 45.50f, "15/01/2024"),
    Scadenze("Forniture S.p.A.", "fornitore", 120.00f, "20/01/2024"),
    Scadenze("Autocarrozzeria Rossi", "macchina", 350.75f, "22/01/2024"),
    Scadenze("Trattoria Da Mario", "Ristorante", 80.00f, "05/02/2024"),
    Scadenze("Fornitore Alimentare SRL", "fornitore", 210.30f, "10/02/2024"),
    Scadenze("Lavaggio Auto Milano", "macchina", 60.00f, "12/02/2024"),
    Scadenze("Osteria del Porto", "Ristorante", 95.20f, "18/02/2024"),
    Scadenze("Elettronica & Co.", "fornitore", 499.99f, "01/03/2024"),
    Scadenze("Carrozzeria Latina", "macchina", 275.40f, "05/03/2024"),
    Scadenze("Ristorante Il Girasole", "Ristorante", 120.00f, "10/03/2024"),
    Scadenze("Forniture Cucina SRL", "fornitore", 330.10f, "15/03/2024"),
    Scadenze("Officina Meccanica Bianchi", "macchina", 420.00f, "20/03/2024"),
    Scadenze("Trattoria La Nonna", "Ristorante", 68.75f, "25/03/2024"),
    Scadenze("Fornitore Bevande SPA", "fornitore", 150.25f, "01/04/2024"),
    Scadenze("Centro Revisioni Roma", "macchina", 89.90f, "05/04/2024"),
    Scadenze("Pasticceria Dolce Vita", "Ristorante", 34.60f, "10/04/2024"),
    Scadenze("Forniture Gelato SRL", "fornitore", 245.00f, "15/04/2024"),
    Scadenze("Gomme & Co.", "macchina", 210.50f, "20/04/2024"),
    Scadenze("Ristorante La Scala", "Ristorante", 132.40f, "25/04/2024"),
    Scadenze("Fornitore Packaging", "fornitore", 77.80f, "30/04/2024")
)

data class Provenienze(
    val fornitore: String,
    val quantita: String,
    val numeroBolla: String?, // Nullable
    val fattura: String?,     // Nullable
    val data: String         // formato 10/10/1990
)

var provenienze = listOf(
    Provenienze(fornitore = "Acciai Italia", quantita = "2000 kg", numeroBolla = "BO-2024-001", fattura = null, data = "01/03/2024"),
    Provenienze(fornitore = "Elettronica 2000", quantita = "15 unità", numeroBolla = null, fattura = "FA-445566", data = "12/04/2024"),
    Provenienze(fornitore = "Packaging Sud", quantita = "500 scatole", numeroBolla = "B-889", fattura = "F-990", data = "05/02/2024"),
    // Caso 1: Solo Bolla presente
    Provenienze(fornitore = "Mario Rossi S.r.l.", quantita = "150 kg", numeroBolla = "BO-2023-001", fattura = null, data = "20/11/2023"),
    Provenienze(fornitore = "Logistica Nord", quantita = "12 bancali", numeroBolla = "BL-9928", fattura = null, data = "15/06/2024"),
    Provenienze(fornitore = "Ferramenta Veneta", quantita = "500 viti", numeroBolla = "BOL-12/A", fattura = null, data = "02/01/2024"),

    // Caso 2: Solo Fattura presente
    Provenienze(fornitore = "Tech Solution SPA", quantita = "5 tablet", numeroBolla = null, fattura = "FT-2024-05", data = "10/05/2024"),
    Provenienze(fornitore = "Energia Pura", quantita = "1200 kWh", numeroBolla = null, fattura = "FAT-88921", data = "30/09/2024"),
    Provenienze(fornitore = "Consulenze IT", quantita = "40 ore", numeroBolla = null, fattura = "2023/450", data = "18/12/2023"),

    // Caso 3: Entrambi presenti
    Provenienze(fornitore = "Agricola Bio", quantita = "20 litri", numeroBolla = "B-772", fattura = "F-2024-B", data = "07/03/2024"),
    Provenienze(fornitore = "Edilizia Moderna", quantita = "5 mc sabbia", numeroBolla = "BO-551", fattura = "FA-112", data = "22/08/2024"),
    Provenienze(fornitore = "Packaging Italia", quantita = "1000 scatole", numeroBolla = "BL-009", fattura = "FT-990", data = "11/07/2024"),

    // Caso 4: Fornitore estero (misto)
    Provenienze(fornitore = "Global Export Ltd", quantita = "2 container", numeroBolla = "EXP-7712", fattura = "INV-2024-X1", data = "29/05/2024")
)

var fatture = provenienze.filter{ it.fattura != null }
var listaFatture : List<CardItem> = fatture.map{ f -> CardItem( name = f.fattura.toString(), type = f.data)}.distinct()
var bolle = provenienze.filter{ it.numeroBolla != null}
var listaBolle : List<CardItem> = bolle.map{ b -> CardItem(name = b.numeroBolla.toString(), type = b.data)}.distinct()

data class Cliente(
    val tipo : String, // P o A (privato o azienda)
    val nome : String,
    val CF : String,
    val cognome : String?,
    val email : String,
    val telefono : String,
    val indirizzo : String,
    val citta : String,
    val comune : String,
    val provincia : String,
    val cap : String,
    val riferimento : String,
    val dataNascita : String?,
    val luogoNascita : String?,
    val codiceUnivoco : String?,
    val ragioneSociale : String?,
    val partitaIVA : String?,
)

val listaClienti = listOf(
    // Esempio Tipo P - Privato
    Cliente(
        tipo = CustomerType.Private.toString(),
        nome = "Mario",
        cognome = "Rossi", // Obbligatorio per P
        CF = "RSSMRA80A01H501U",
        dataNascita = "01/01/1980", // Obbligatorio per P
        luogoNascita = "Roma", // Obbligatorio per P
        indirizzo = "Via Roma 12",
        citta = "Roma",
        comune = "Roma",
        provincia = "RM",
        cap = "00100",
        riferimento = "Privato standard",
        codiceUnivoco = null,
        ragioneSociale = null,
        partitaIVA = null,
        email = "mariorossi80@gmail.com",
        telefono = "+39 347 1234567"
    ),

    // Esempio Tipo A - Azienda
    Cliente(
        tipo = CustomerType.Company.toString(),
        nome = "Luca", // Rappresentante o referente
        cognome = null,
        CF = "01234567890", // Spesso coincide con la P.IVA
        dataNascita = null,
        luogoNascita = null,
        indirizzo = "Via delle Industrie 50",
        citta = "Milano",
        comune = "Milano",
        provincia = "MI",
        cap = "20100",
        riferimento = "Ufficio acquisti",
        codiceUnivoco = "SUBM70N", // Obbligatorio per A
        ragioneSociale = "Tech Solutions S.R.L.", // Obbligatorio per A
        partitaIVA = "01234567890", // Obbligatorio per A
        email = "amministrazione@techsolutions.it",
        telefono = "02 9876543"
    )
)

var address = appuntamenti.map{a -> a.indirizzo}

var tipi_menu = tipi.map{tipo -> MenuItem(tipo, {})}
var categorie_menu = prodotti.map{cat -> MenuItem(cat.nome, {})}

var categorie_prodotti = prodotti.map{cat -> cat.nome}

var cantieri = List<String>(10, {item -> "Cantiere $item"})

var cantieri_menu = cantieri.map { c -> MenuItem(c, {}) }

data class Cantiere(
    val indirizzo : String,
    val dataInizio : String,
    val dataFine : String? = "/"
)

val listaCantieri = listOf(
    Cantiere(
        indirizzo = "Via Roma 10, Roma",
        dataInizio = "01/01/2024",
        dataFine = "30/06/2024"
    ),
    Cantiere(
        indirizzo = "Corso Vittorio Emanuele 50, Milano",
        dataInizio = "15/02/2024",
        dataFine = "15/12/2024"
    ),
    Cantiere(
        indirizzo = "Via Garibaldi 5, Torino",
        dataInizio = "10/03/2024",
    ),
    Cantiere(
        indirizzo = "Piazza Grande 1, Bologna",
        dataInizio = "01/05/2024",
        dataFine = "01/11/2024"
    ),
    Cantiere(
        indirizzo = "Via Dante 12, Firenze",
        dataInizio = "20/06/2024"
    )
)

data class CardItem(
    val name : String,
    val type : String
)

var customersType = customers.map { item -> CardItem(name = item, type = "NONE") }
var addressType = address.map {item -> CardItem(name = item, type = "NONE")}
var invoicesType = fatture.map{item -> CardItem(name = item.fattura.toString(), type = "NONE")}
var materialsType = prodotti.map{item -> CardItem(name = item.nome, type = item.tipo)}
var bubblesType = bolle.map{item -> CardItem(name = item.numeroBolla.toString(), type = "NONE")}
