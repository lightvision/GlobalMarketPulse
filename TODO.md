### TODO LIST - GLOBAL MARKET PULSE ###
    
    # 1. Configurare Inițială
    - [x] Creare proiect Android Studio - Global Market Pulse
        - [x] Selectare șablon "Bottom Navigation Activity"
        - [x] Configurare nume pachet (de stabilit: `ro.marven.globalmarketpulse` sau altă variantă)
        - [x] Selectare limbaj Java
        - [x] Activare View Binding
    - [x] Configurare nivel API
        - [x] Alegere nivel minim API (Android 5.0 - API 21 sau mai mare)
        - [x] Alegere nivel target API (Android 15.0 - API 35)
    - [ ] Configurare dependențe pentru API-urile utilizate
        - [ ] Integrare API Reddit pentru tendințe de piață
        - [ ] Integrare API Forex pentru cursuri valutare
        - [ ] Integrare multiple RSS feeds pentru știri economice
    - [x] Refactorizarea numelor generate automat
        - [x] Schimbare nume activități și clase după convențiile aplicației
            - [x] `MainActivity` → `MainActivity`
            - [x] `PlaceholderFragment` → `MarketTrendsFragment`
            - [x] `DashboardFragment` → `ExchangeRatesFragment`
            - [x] `NotificationsFragment` → `BusinessNewsFragment`
        - [x] Redenumire pachete pentru a reflecta structura aplicației
            - [x] `ro.marven.globalmarketpulse` → `ro.marven.globalmarketpulse`
            - [x] Mutare activități și fragmente în pachetele corespunzătoare (`ui.market_trends`, `ui.exchange_rates`, `ui.business_news`)
        - [x] Redenumire fișiere XML pentru layout-uri
            - [x] `activity_main.xml` → `activity_main.xml`
            - [x] `fragment_dashboard.xml` → `fragment_exchange_rates.xml`
            - [x] `fragment_notifications.xml` → `fragment_business_news.xml`
            - [x] `fragment_home.xml` → `fragment_market_trends.xml`
    - [x] Implementare suport pentru limba română și engleză
        - [x] Creare fișiere `strings.xml` pentru fiecare limbă (`res/values/strings.xml` și `res/values-ro/strings.xml`)
        - [x] Verificare traduceri și aplicare în UI
    - [ ] Configurare meniu cu opțiuni pentru Setări și Ieșire din aplicație
        - [x] Creare meniu în `menu_top.xml`
        - [ ] Implementare funcționalitate pentru deschiderea setărilor
        - [x] Implementare funcționalitate de ieșire din aplicație
    
    # 2. Bottom Navigation - Structura Principală
    - [x] Configurare Bottom Navigation View
    - [x] Adăugare secțiuni: Market Trends, Exchange Rates, Business News
    
    # 3. Ecran Acceptare Termeni și Condiții
    - [x] Creare UI pentru afișarea Termenilor și Condițiilor
        - [x] Creare layout XML
        - [x] Implementare buton Accept
        - [x] Implementare buton Refuz
    - [x] Implementare logică de acceptare la prima rulare
        - [x] Salvare stare acceptare în SharedPreferences
        - [x] Redeschidere ecran dacă utilizatorul nu acceptă și închide aplicația
    
    # 4. Ecran Market Trends
    - [ ] Creare UI pentru secțiunea de tendințe
        - [ ] Creare layout XML pentru lista de tendințe
        - [ ] Implementare ViewModel și LiveData
        - [ ] Implementare RecyclerView
    - [ ] Integrare API Reddit pentru identificarea tendințelor financiare
        - [ ] Conectare la API și preluare date
        - [ ] Parsare JSON și transformare în obiecte
        - [ ] Gestionare erori și fallback pentru API indisponibil
    - [ ] Afișare trending stocks și discuții relevante
        - [ ] Populare RecyclerView cu datele obținute
        - [ ] Implementare ViewHolder și Adapter
    - [ ] Implementare filtrare după popularitate sau categorie
        - [ ] Creare UI pentru filtrare
        - [ ] Implementare logică de actualizare listă după selecție
    - [ ] Afișare ProgressBar în timpul încărcării datelor asincron
    
    # 5. Ecran Exchange Rates
    - [ ] Creare UI pentru secțiunea Forex
        - [ ] Creare layout XML
        - [ ] Implementare RecyclerView
    - [ ] Selectare monedă de bază
        - [ ] Creare dropdown pentru selecție monedă
        - [ ] Salvare selecție utilizator în SharedPreferences
    - [ ] Afișare cursuri de schimb pentru monedele selectate
        - [ ] Integrare API Forex
        - [ ] Transformare și afișare date în listă
    - [ ] Implementare funcționalitate actualizare în timp real
        - [ ] Fetch automat la un interval definit
        - [ ] Afișare mesaj de actualizare
    - [ ] Afișare ProgressBar în timpul încărcării datelor asincron
    
    # 6. Ecran Business News
    - [ ] Creare UI pentru secțiunea de știri
        - [ ] Creare layout XML pentru lista de articole
        - [ ] Implementare RecyclerView
    - [ ] Afișare listă de articole din API-uri multiple (CNBC RSS, alți furnizori)
        - [ ] Integrare RSS feed API
        - [ ] Parsare și afișare date în listă
        - [ ] Implementare sistem de configurare JSON pentru mapare date RSS
        - [ ] Implementare sortare articole după timp
    - [ ] Navigație spre detalii articol
        - [ ] Creare ecran detalii articol
        - [ ] Afișare conținut complet
    - [ ] Afișare ProgressBar în timpul încărcării datelor asincron
    
    # 7. Ecran Settings
    - [ ] Creare UI pentru setări
        - [ ] Creare layout XML
        - [ ] Implementare liste de opțiuni
    - [ ] Implementare opțiuni de schimbare temă (light/dark mode)
        - [ ] Creare toggle pentru temă
        - [ ] Salvare preferință utilizator
    - [ ] Implementare opțiuni de notificări
        - [ ] Adăugare switch-uri pentru diverse notificări
        - [ ] Salvare setări în SharedPreferences