package com.clickaudioproject;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import netscape.javascript.JSObject;


public class Main extends Application {
    private ArrayList<Icon> icons;
    private int rows;
    private int cols;
    private FrequencyList frequencyList;
    private HBox listDisplay;
    private TextToSpeech textToSpeech = new TextToSpeech();
    private Text frequencyTitle = new Text("FREQUENCY LIST");
    private String commandInput;

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    JavaBridge bridge = new JavaBridge();


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Click Audio Project");
        String[] files = {"1000_Hours_Outside_.png", "My_Doge_Puzzle_Game_.png", "5-0_Radio_Pro_Police_Scanner_.png", "My_Macros+_Diet_and_Calories_.png", "75_Hard_.png", "NBA_Live_Games_and_Scores_.png", "8_Ball_Pool_tm_.png", "Necrophonic_.png", "ADP_Mobile_Solutions_.png", "Netflix_.png", "AI_Chatbot_Open_Chat_AI_.png", "NightCap_Camera_.png", "AI_Mirror_AI_Art_Photo_Editor_.png", "Nike_Shoes,_Apparel,_Stories_.png", "AI_Plant_Identifier-NatureID_.png", "Noom_Healthy_Weight_Loss_.png", "AMC_Theatres_Movies_and_More_.png", "NordVPN_VPN_Fast_and_Secure_.png", "ASCCP_Management_Guidelines_.png", "OfferUp-Buy._Sell._Letgo._.png", "A_Dance_of_Fire_and_Ice_.png", "Okta_Verify_.png", "Ableton_Note_.png", "OverDrive_2.6_.png", "AdBlock_.png", "PDG_PROmote_2021-2023_.png", "Adobe_Acrobat_Reader_Edit_PDF_.png", "PROmote-Army_Study_Guide_.png", "Affirm_Buy_now,_pay_over_time_.png", "PUBG_MOBILE_.png", "Airbnb_.png", "PackPoint_Premium_Packing_List_.png", "Alibaba.com_B2B_Trade_App_.png", "Panda_Express_.png", "AllTrails_Hike,_Bike_and_Run_.png", "Pandora_Music_and_Podcasts_.png", "Alpha_Cop-Case_Law_Guide_.png", "Papers,_Please_.png", "Amazon_Prime_Video_.png", "Paprika_Recipe_Manager_3_.png", "Amazon_Shopping_.png", "Paramount+_.png", "American_Airlines_.png", "ParkMobile-Find_Parking_.png", "Among_Us_.png", "Parking_Jam_3D_.png", "Ancestry_Family_History_and_DNA_.png", "PayPal-Send,_Shop,_Manage_.png", "Angry_Birds_Dream_Blast_.png", "Peacock_TV_Stream_TV_and_Movies_.png", "AnkiMobile_Flashcards_.png", "PeakFinder_.png", "Arcadia-Arcade_Watch_Games_.png", "Peloton_Fitness_and_Workouts_.png", "Audible_Audio_Entertainment_.png", "PhotoPills_.png", "AutoSleep_Track_Sleep_on_Watch_.png", "Photoleap_AI_Art_Photo_Editor_.png", "BET+_.png", "Photomath_.png", "BIGO_LIVE-Live_Stream,_Go_Live_.png", "Picsart_AI_Photo_Editor_.png", "BLK-Dating_for_Black_singles_.png", "PictureThis-Plant_Identifier_.png", "Babbel-Language_Learning_.png", "Pinterest_.png", "Bank_of_America_Mobile_Banking_.png", "Plague_Inc._.png", "BetterMe_Health_Coaching_.png", "Planet_Fitness_Workouts_.png", "PlantIn_Plant_Identifier_.png", "BimmerLink_for_BMW_and_MINI_.png", "Plant_Parent_Plant_Care_Guide_.png", "BitLife-Life_Simulator_.png", "Plenty_of_Fish_Dating_.png", "Block_Blast_Adventure_Master_.png", "Pocket_City_2_.png", "Blood_Type_Diet_reg_.png", "Pocket_GM_3_Football_Sim_.png", "Booking.com_Hotels_and_Travel_.png", "Pokemon_GO_.png", "Brain_Test_Tricky_Puzzles_.png", "Poshmark_Buy_and_Sell_Fashion_.png", "Bridge_Race_.png", "Pou_.png", "Broadcastify_Pro_.png", "Pro_Camera_by_Moment_.png", "Brotato_Premium_.png", "Procreate_Pocket_.png", "Bumble_Dating_App_and_Friends_.png", "Puzzles_and_Survival_.png", "CE5_Contact_.png", "Quantumult_X_.png", "Call_of_Duty_reg_Mobile_.png", "QuickBend_Conduit_Bending_.png", "Calm_.png", "Canva_Design,_Photo_and_Video_.png", "QuickBooks_Self-Employed_.png", "CapCut-Video_Editor_.png", "RFS-Real_Flight_Simulator_.png", "Capital_One_Mobile_.png", "RadarOmega_.png", "Capital_One_Shopping_.png", "RadarScope_.png", "Cash_App_.png", "Raya_.png", "Catan_Classic_.png", "Rebel_Inc._.png", "Character_AI-Chat_Ask_Create_.png", "Red_s_First_Flight_.png", "Chase_Mobile_reg_Bank_and_Invest_.png", "Reddit_.png", "ChatBox-AI_Chatbot_Assistant_.png", "Ring-Always_Home_.png", "ChatGPT_.png", "Rise_of_Kingdoms_.png", "ChatOn-AI_Chat_Bot_Assistant_.png", "Road_to_Hana_Maui_GyPSy_Guide_.png", "Chat_with_Ask_AI_.png", "Roadside_America_.png", "Chegg_Study-Homework_Helper_.png", "Roblox_.png", "Chess-Play_and_Learn_.png", "Rocket_Money-Bills_and_Budgets_.png", "Chick-fil-A_.png", "RollerCoaster_Tycoon_reg_Classic_.png", "Chime-Mobile_Banking_.png", "SHEIN-Shopping_Online_.png", "Chipotle-Fresh_Food_Fast_.png", "SHOWTIME_.png", "ChirpOMatic_Bird_Song_ID-USA_.png", "STARZ_.png", "Chispa_Dating_App_for_Latinos_.png", "Sam_s_Club_.png", "Clash_of_Clans_.png", "Schwab_Mobile_.png", "Clime_NOAA_Weather_Radar_Live_.png", "Screen_Mirroring+_App_.png", "Cloud_Baby_Monitor_.png", "Screen_Mirroring+_for_Fire_TV_.png", "Coin_Master_.png", "Screen_Mirroring_+_for_Roku_.png", "Collect_Em_All_Clear_the_Dots_.png", "Shadow_Fight_2_Special_Edition_.png", "Costco_.png", "Shadowrocket_.png", "Couch_to_5K_reg-Run_training_.png", "Shazam_Music_Discovery_.png", "Cricut_Design_Space_.png", "Shop_All_your_favorite_brands_.png", "Crunchyroll_.png", "DairyBar_.png", "Discord-Chat,_Talk_and_Hangout_.png", "Slow_Shutter_Cam_.png", "Disney+_.png", "Smart_Cleaner_Clean_Up_Storage_.png", "Domino_s_Pizza_USA_.png", "Snapchat_.png", "DoorDash-Dasher_.png", "Solocator-GPS_Field_Camera_.png", "DoorDash-Food_Delivery_.png", "SoundCloud_Discover_New_Music_.png", "Dropbox_Cloud_Files_Storage_.png", "Southwest_Airlines_.png", "Dumb_Ways_to_Die_.png", "Splice-Video_Editor_and_Maker_.png", "Dunkin_.png", "SpongeBob_SquarePants_.png", "Duo_Mobile_.png", "Spotify-Music_and_Podcasts_.png", "Duolingo-Language_Lessons_.png", "Star_Walk_2_The_Night_Sky_Map_.png", "ESPN_Live_Sports_and_Scores_.png", "Starbucks_.png", "ETA-GPS_and_maps_driving_times_.png", "Stardew_Valley_.png", "Eatventure_.png", "Stash-Rule_Based_Proxy_.png", "Endling_.png", "Stormshot_.png", "EpocCam_Webcamera_for_Computer_.png", "Strava_Run,_Ride,_Hike_.png", "Etsy_Custom_and_Creative_Goods_.png", "Streaks_.png", "Expedia_Hotels,_Flights_and_Car_.png", "Stylebook_.png", "Exploding_Kittens_reg_.png", "Subway_reg_.png", "ExpressVPN-VPN_Fast_and_Secure_.png", "Sun_Seeker-Tracker_and_Compass_.png", "FL_Studio_Mobile_.png", "T-Mobile_.png", "FaceApp_Perfect_Face_Editor_.png", "TIDAL_Music_HiFi,_Ad-free_.png", "Facebook_.png", "TV_Cast_Pro_for_Fire_TV_.png", "Feeld_Meet_Couples_and_Singles_.png", "TV_Mirror+_for_Chromecast_.png", "Fetch_Have_Fun,_Save_Money_.png", "TV_Remote-Universal_Control_.png", "Fill_The_Fridge_.png", "Taco_Bell_Fast_Food_and_Delivery_.png", "Fishdom_.png", "Target_.png", "Fitbit_Health_and_Fitness_.png", "Teen_Titans_Go_Figure_.png", "Flick_Home_Run_.png", "Telegram_Messenger_.png", "Temu_Shop_Like_a_Billionaire_.png", "Fly_Delta_.png", "Terraria_.png", "Forest_Focus_for_Productivity_.png", "Tetris_reg_.png", "Free_Tone-Calling_and_Texting_.png", "TextNow_Call_+_Text_Unlimited_.png", "Funimation_.png", "Text_Me-Phone_Call_+_Texting_.png", "GameChanger_.png", "The_75_Soft_Challenge_.png", "Garten_of_Banban_2_.png", "The_Golf_Tracer_.png", "Garten_of_Banban_3_.png", "The_Home_Depot_.png", "Geometry_Dash_Lite_.png", "The_New_York_Times_.png", "Gmail-Email_by_Google_.png", "The_Past_Within_.png", "Goblin_Tools_.png", "The_Roku_App_Official_.png", "Going_Balls_.png", "The_Wall_Street_Journal._.png", "GoodNovel_and_Books_Web_Novels_.png", "The_Wonder_Weeks_.png", "Google_.png", "The_Zeus_Network_.png", "Google_Authenticator_.png", "Themify-Widget_and_Icon_Themes_.png", "Google_Calendar_Get_Organized_.png", "Things_3_.png", "Google_Chrome_.png", "Threema._The_Secure_Messenger_.png", "Google_Docs_Sync,_Edit,_Share_.png", "Ticket_to_Ride-Train_Game_.png", "Google_Drive_.png", "Ticketmaster_Buy,_Sell_Tickets_.png", "Google_Earth_.png", "TikTok_.png", "Google_Maps_.png", "Tinder-Dating._Meet_Friends_.png", "Google_Meet_.png", "Tiny_Wings_.png", "Google_Photos_.png", "Tomb_of_the_Mask_.png", "Google_Sheets_.png", "TonalEnergy_Tuner_and_Metronome_.png", "Google_Translate_.png", "Toon_Blast_.png", "Grammarly-Keyboard_and_Editor_.png", "Top_War_Battle_Game_.png", "Grand_Theft_Auto_III_.png", "Townscaper_.png", "Grindr-Gay_Dating_and_Chat_.png", "Traffic_Run_.png", "GroupMe_.png", "Triple_Match_3D_.png", "HappyCow-Vegan_Food_Near_You_.png", "Trivia_Crack_No_Ads_.png", "Hay_Day_.png", "Tubi-Watch_Movies_and_TV_Shows_.png", "Headspace_Mindful_Meditation_.png", "Tuner_.png", "Headway_Daily_Book_Summaries_.png", "TurboScan_tm_Pro_PDF_scanner_.png", "Hear_My_Baby_Heartbeat_App_.png", "Turo-Find_your_drive_.png", "HeartWatch_Heart_Rate_Tracker_.png", "Twisted_Tangle_.png", "Hily_Dating_App._Chat_and_Flirt_.png", "Twitch_Live_Game_Streaming_.png", "Hinge_Dating_App_Meet_People_.png", "Twitter_.png", "Hitman_Sniper_.png", "Two_Dots_Brain_Puzzle_Games_.png", "Hopper_Flights,_Hotels_and_Cars_.png", "Uber-Request_a_ride_.png", "HotSchedules_.png", "Uber_Eats_Food_Delivery_.png", "Hulu_Watch_TV_shows_and_movies_.png", "Ultimate_Custom_Night_.png", "INKHUNTER_PRO_Tattoos_try_on_.png", "United_Airlines_.png", "Upside_Cash_Back-Gas_and_Food_.png", "Impulse-Brain_Training_.png", "VPN-Super_Unlimited_Proxy_.png", "Incredibox_.png", "VSCO_Photo_and_Video_Editor_.png", "Indeed_Job_Search_.png", "Vector_Full_Parkour_Run_.png", "Instacart_Grocery_delivery_.png", "Venmo_.png", "Instagram_.png", "ViX-Stream_Shows,_Sports,_News_.png", "Intuit_Credit_Karma_.png", "Viki_Asian_Drama,_Movies_and_TV_.png", "Vrbo_Vacation_Rentals_.png", "Joi-Live_Stream_and_Video_Chat_.png", "WEBTOON_Comics_.png", "Walkout_Song_DJ_.png", "Klarna_Shop_now._Pay_later._.png", "Walmart_Shopping_and_Savings_.png", "Koala_Sampler_.png", "Waterlogue_Photo_to_Painting_.png", "Life360_Find_Friends_and_Family_.png", "Wayfair-Shop_All_Things_Home_.png", "Lightning_Link_Casino_Slots_.png", "Waze_Navigation_and_Live_Traffic_.png", "Lightroom_Photo_and_Video_Editor_.png", "WhatsApp_Messenger_.png", "LinkedIn_Network_and_Job_Finder_.png", "White_Noise_.png", "LiveATC_Air_Radio_.png", "Widgetable_Lock_Screen_Widget_.png", "Lose_It-Calorie_Counter_.png", "Wingspan_The_Board_Game_.png", "Lowriders_Comeback_2_Cruising_.png", "Wizz-Expand_Your_World_.png", "Lyft_.png", "Words_With_Friends_2_Word_Game_.png", "MLB_.png", "Wordscapes_.png", "MLB_Ballpark_.png", "WorkOutDoors_.png", "MONOPOLY_GO_.png", "Wyze-Make_Your_Home_Smarter_.png", "Magic_Tiles_3_Piano_Game_.png", "Xfinity_.png", "Match_Dating_and_Relationships_.png", "Yahtzee_reg_with_Buddies_Dice_.png", "Max_Stream_HBO,_TV,_and_Movies_.png", "Yelp_Food,_Delivery_and_Reviews_.png", "McDonald_s_.png", "YouTube_Music_.png", "Merge_Dragons_.png", "YouTube_Watch,_Listen,_Stream_.png", "Merge_Mansion_.png", "Your_First_Financial_.png", "Messenger_.png", "Yuka-Food_and_Cosmetic_scanner_.png", "Microsoft_365_Office_.png", "Zelle_.png", "Microsoft_Authenticator_.png", "Zillow_Real_Estate_and_Rentals_.png", "Microsoft_Edge_Web_Browser_.png", "Zombieville_USA_2_.png", "Microsoft_Outlook_.png", "Zoom-One_Platform_to_Connect_.png", "Microsoft_Teams_.png", "b-hyve_pro_.png", "MilGPS_.png", "bBrowser_.png", "Minecraft_.png", "discovery+_Stream_TV_Shows_.png", "Mobile_Passport_Control_.png", "e-Sword_LT_Bible_Study_to_Go_.png", "Monash_University_FODMAP_diet_.png", "eBay_Marketplace_Shop_and_Sell_.png", "Monument_Valley_.png", "ecMobile_.png", "Musi-Simple_Music_Streaming_.png", "iHeart_.png", "MyChart_.png", "iReal_Pro_.png", "MyFitnessPal_Calorie_Counter_.png", "iScanner_PDF_Scanner_App_.png", "MyFlo_.png", "onX_Hunt_GPS_Hunting_Maps_.png", "My_Bath_and_Body_Works_.png"};
        String[] apps = {"1000 Hours Outside", "My Doge Puzzle Game", "5-0 Radio Pro Police Scanner", "My Macros+ Diet and Calories", "75 Hard", "NBA Live Games and Scores", "8 Ball Pool tm", "Necrophonic", "ADP Mobile Solutions", "Netflix", "AI Chatbot Open Chat AI", "NightCap Camera", "AI Mirror AI Art Photo Editor", "Nike Shoes, Apparel, Stories", "AI Plant Identifier-NatureID", "Noom Healthy Weight Loss", "AMC Theatres Movies and More", "NordVPN VPN Fast and Secure", "ASCCP Management Guidelines", "OfferUp-Buy. Sell. Letgo.", "A Dance of Fire and Ice", "Okta Verify", "Ableton Note", "OverDrive 2.6", "AdBlock", "PDG PROmote 2021-2023", "Adobe Acrobat Reader Edit PDF", "PROmote-Army Study Guide", "Affirm Buy now, pay over time", "PUBG MOBILE", "Airbnb", "PackPoint Premium Packing List", "Alibaba.com B2B Trade App", "Panda Express", "AllTrails Hike, Bike and Run", "Pandora Music and Podcasts", "Alpha Cop-Case Law Guide", "Papers, Please", "Amazon Prime Video", "Paprika Recipe Manager 3", "Amazon Shopping", "Paramount+", "American Airlines", "ParkMobile-Find Parking", "Among Us", "Parking Jam 3D", "Ancestry Family History and DNA", "PayPal-Send, Shop, Manage", "Angry Birds Dream Blast", "Peacock TV Stream TV and Movies", "AnkiMobile Flashcards", "PeakFinder", "Arcadia-Arcade Watch Games", "Peloton Fitness and Workouts", "Audible Audio Entertainment", "PhotoPills", "AutoSleep Track Sleep on Watch", "Photoleap AI Art Photo Editor", "BET+", "Photomath", "BIGO LIVE-Live Stream, Go Live", "Picsart AI Photo Editor", "BLK-Dating for Black singles", "PictureThis-Plant Identifier", "Babbel-Language Learning", "Pinterest", "Bank of America Mobile Banking", "Plague Inc.", "BetterMe Health Coaching", "Planet Fitness Workouts", "PlantIn Plant Identifier", "BimmerLink for BMW and MINI", "Plant Parent Plant Care Guide", "BitLife-Life Simulator", "Plenty of Fish Dating", "Block Blast Adventure Master", "Pocket City 2", "Blood Type Diet reg", "Pocket GM 3 Football Sim", "Booking.com Hotels and Travel", "Pokemon GO", "Brain Test Tricky Puzzles", "Poshmark Buy and Sell Fashion", "Bridge Race", "Pou", "Broadcastify Pro", "Pro Camera by Moment", "Brotato Premium", "Procreate Pocket", "Bumble Dating App and Friends", "Puzzles and Survival", "CE5 Contact", "Quantumult X", "Call of Duty reg Mobile", "QuickBend Conduit Bending", "Calm", "Canva Design, Photo and Video", "QuickBooks Self-Employed", "CapCut-Video Editor", "RFS-Real Flight Simulator", "Capital One Mobile", "RadarOmega", "Capital One Shopping", "RadarScope", "Cash App", "Raya", "Catan Classic", "Rebel Inc.", "Character AI-Chat Ask Create", "Red s First Flight", "Chase Mobile reg Bank and Invest", "Reddit", "ChatBox-AI Chatbot Assistant", "Ring-Always Home", "ChatGPT", "Rise of Kingdoms", "ChatOn-AI Chat Bot Assistant", "Road to Hana Maui GyPSy Guide", "Chat with Ask AI", "Roadside America", "Chegg Study-Homework Helper", "Roblox", "Chess-Play and Learn", "Rocket Money-Bills and Budgets", "Chick-fil-A", "RollerCoaster Tycoon reg Classic", "Chime-Mobile Banking", "SHEIN-Shopping Online", "Chipotle-Fresh Food Fast", "SHOWTIME", "ChirpOMatic Bird Song ID-USA", "STARZ", "Chispa Dating App for Latinos", "Sam s Club", "Clash of Clans", "Schwab Mobile", "Clime NOAA Weather Radar Live", "Screen Mirroring+ App", "Cloud Baby Monitor", "Screen Mirroring+ for Fire TV", "Coin Master", "Screen Mirroring + for Roku", "Collect Em All Clear the Dots", "Shadow Fight 2 Special Edition", "Costco", "Shadowrocket", "Couch to 5K reg-Run training", "Shazam Music Discovery", "Cricut Design Space", "Shop All your favorite brands", "Crunchyroll", "DairyBar", "Discord-Chat, Talk and Hangout", "Slow Shutter Cam", "Disney+", "Smart Cleaner Clean Up Storage", "Domino s Pizza USA", "Snapchat", "DoorDash-Dasher", "Solocator-GPS Field Camera", "DoorDash-Food Delivery", "SoundCloud Discover New Music", "Dropbox Cloud Files Storage", "Southwest Airlines", "Dumb Ways to Die", "Splice-Video Editor and Maker", "Dunkin", "SpongeBob SquarePants", "Duo Mobile", "Spotify-Music and Podcasts", "Duolingo-Language Lessons", "Star Walk 2 The Night Sky Map", "ESPN Live Sports and Scores", "Starbucks", "ETA-GPS and maps driving times", "Stardew Valley", "Eatventure", "Stash-Rule Based Proxy", "Endling", "Stormshot", "EpocCam Webcamera for Computer", "Strava Run, Ride, Hike", "Etsy Custom and Creative Goods", "Streaks", "Expedia Hotels, Flights and Car", "Stylebook", "Exploding Kittens reg", "Subway reg", "ExpressVPN-VPN Fast and Secure", "Sun Seeker-Tracker and Compass", "FL Studio Mobile", "T-Mobile", "FaceApp Perfect Face Editor", "TIDAL Music HiFi, Ad-free", "Facebook", "TV Cast Pro for Fire TV", "Feeld Meet Couples and Singles", "TV Mirror+ for Chromecast", "Fetch Have Fun, Save Money", "TV Remote-Universal Control", "Fill The Fridge", "Taco Bell Fast Food and Delivery", "Fishdom", "Target", "Fitbit Health and Fitness", "Teen Titans Go Figure", "Flick Home Run", "Telegram Messenger", "Temu Shop Like a Billionaire", "Fly Delta", "Terraria", "Forest Focus for Productivity", "Tetris reg", "Free Tone-Calling and Texting", "TextNow Call + Text Unlimited", "Funimation", "Text Me-Phone Call + Texting", "GameChanger", "The 75 Soft Challenge", "Garten of Banban 2", "The Golf Tracer", "Garten of Banban 3", "The Home Depot", "Geometry Dash Lite", "The New York Times", "Gmail-Email by Google", "The Past Within", "Goblin Tools", "The Roku App Official", "Going Balls", "The Wall Street Journal.", "GoodNovel and Books Web Novels", "The Wonder Weeks", "Google", "The Zeus Network", "Google Authenticator", "Themify-Widget and Icon Themes", "Google Calendar Get Organized", "Things 3", "Google Chrome", "Threema. The Secure Messenger", "Google Docs Sync, Edit, Share", "Ticket to Ride-Train Game", "Google Drive", "Ticketmaster Buy, Sell Tickets", "Google Earth", "TikTok", "Google Maps", "Tinder-Dating. Meet Friends", "Google Meet", "Tiny Wings", "Google Photos", "Tomb of the Mask", "Google Sheets", "TonalEnergy Tuner and Metronome", "Google Translate", "Toon Blast", "Grammarly-Keyboard and Editor", "Top War Battle Game", "Grand Theft Auto III", "Townscaper", "Grindr-Gay Dating and Chat", "Traffic Run", "GroupMe", "Triple Match 3D", "HappyCow-Vegan Food Near You", "Trivia Crack No Ads", "Hay Day", "Tubi-Watch Movies and TV Shows", "Headspace Mindful Meditation", "Tuner", "Headway Daily Book Summaries", "TurboScan tm Pro PDF scanner", "Hear My Baby Heartbeat App", "Turo-Find your drive", "HeartWatch Heart Rate Tracker", "Twisted Tangle", "Hily Dating App. Chat and Flirt", "Twitch Live Game Streaming", "Hinge Dating App Meet People", "Twitter", "Hitman Sniper", "Two Dots Brain Puzzle Games", "Hopper Flights, Hotels and Cars", "Uber-Request a ride", "HotSchedules", "Uber Eats Food Delivery", "Hulu Watch TV shows and movies", "Ultimate Custom Night", "INKHUNTER PRO Tattoos try on", "United Airlines", "Upside Cash Back-Gas and Food", "Impulse-Brain Training", "VPN-Super Unlimited Proxy", "Incredibox", "VSCO Photo and Video Editor", "Indeed Job Search", "Vector Full Parkour Run", "Instacart Grocery delivery", "Venmo", "Instagram", "ViX-Stream Shows, Sports, News", "Intuit Credit Karma", "Viki Asian Drama, Movies and TV", "Vrbo Vacation Rentals", "Joi-Live Stream and Video Chat", "WEBTOON Comics", "Walkout Song DJ", "Klarna Shop now. Pay later.", "Walmart Shopping and Savings", "Koala Sampler", "Waterlogue Photo to Painting", "Life360 Find Friends and Family", "Wayfair-Shop All Things Home", "Lightning Link Casino Slots", "Waze Navigation and Live Traffic", "Lightroom Photo and Video Editor", "WhatsApp Messenger", "LinkedIn Network and Job Finder", "White Noise", "LiveATC Air Radio", "Widgetable Lock Screen Widget", "Lose It-Calorie Counter", "Wingspan The Board Game", "Lowriders Comeback 2 Cruising", "Wizz-Expand Your World", "Lyft", "Words With Friends 2 Word Game", "MLB", "Wordscapes", "MLB Ballpark", "WorkOutDoors", "MONOPOLY GO", "Wyze-Make Your Home Smarter", "Magic Tiles 3 Piano Game", "Xfinity", "Match Dating and Relationships", "Yahtzee reg with Buddies Dice", "Max Stream HBO, TV, and Movies", "Yelp Food, Delivery and Reviews", "McDonald s", "YouTube Music", "Merge Dragons", "YouTube Watch, Listen, Stream", "Merge Mansion", "Your First Financial", "Messenger", "Yuka-Food and Cosmetic scanner", "Microsoft 365 Office", "Zelle", "Microsoft Authenticator", "Zillow Real Estate and Rentals", "Microsoft Edge Web Browser", "Zombieville USA 2", "Microsoft Outlook", "Zoom-One Platform to Connect", "Microsoft Teams", "b-hyve pro", "MilGPS", "bBrowser", "Minecraft", "discovery+ Stream TV Shows", "Mobile Passport Control", "e-Sword LT Bible Study to Go", "Monash University FODMAP diet", "eBay Marketplace Shop and Sell", "Monument Valley", "ecMobile", "Musi-Simple Music Streaming", "iHeart", "MyChart", "iReal Pro", "MyFitnessPal Calorie Counter", "iScanner PDF Scanner App", "MyFlo", "onX Hunt GPS Hunting Maps", "My Bath and Body Works"};

        rows = 12;
        cols = 300 / 11;
        icons = new ArrayList<>();
        listDisplay = new HBox();


        // Create Icons and add them to icon ArrayList
        for (int i = 0; i < files.length; i++) {
            String filePathImage = "file:src/main/java/com/clickaudioproject/images/" + files[i];
            Icon newIcon = new Icon(files[i], apps[i], filePathImage, textToSpeech);
            icons.add(newIcon);
        }

        frequencyList = new FrequencyList(icons);

        // Creat GridPane to display all icons
        GridPane desktop = new GridPane();
        desktop.setPadding(new Insets(10));
        desktop.setHgap(10);
        desktop.setVgap(10);

        int iconIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (iconIndex < icons.size()) {
                    Icon currentIcon = icons.get(iconIndex);
                    desktop.add(currentIcon.displayIcon(), col, row);
                    iconIndex++;
                } else {
                    break;
                }
            }
        }

        File f = new File("C:\\Users\\jtaya\\Desktop\\CMPT 481\\ClickAudioProject\\src\\main\\java\\com\\clickaudioproject\\speech_to_text.html");
        webEngine.load(f.toURI().toString());
//


        webEngine.loadContent("<html><body><script>" +
                "function startSpeechToText() {" +
                "       console.log('Speech to text started');" + // Corrected the console.log statement

                "}" +
                "</script><textarea id='transcript'></textarea><button onclick='startSpeechToText()'>Start Speech to Text</button></body></html>");

        // Wait for the web page to fully load
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) ->
        {
            JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("java", bridge);
            webEngine.executeScript("console.log = function(message)\n" +
                    "{\n" +
                    "    java.log(message);\n" +
                    "};");


        });

        // Voice Input
        TextField commandField = new TextField();
        commandField.setPromptText("Enter Command");
        Button commandButton = new Button("Enter");
        commandButton.setOnAction(event -> {
            commandInput = commandField.getText();
            System.out.println("Text entered: " + commandInput);

            String command = firstWord(commandInput);
            for (Icon icon: icons) {
                icon.commandBoard(command, commandInput);
            }

            frequencyList.frequencyCommandBoard(command, commandInput);
            displayFrequency();


        });

        Button SpeechRecognizerButton = new Button("Speech Recognition Window");
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("New Window");
        StackPane secondaryLayout = new StackPane(webView, new Button("Close"));
        secondaryLayout.setOnMouseClicked(e -> secondaryStage.close()); // Close window when clicked
        secondaryStage.setScene(new Scene(secondaryLayout, 200, 100));
        VBox test = new VBox();
        test.getChildren().add(webView);
        Scene recogScene = new Scene(test, 1000, 500);
        secondaryStage.setScene(recogScene);

        SpeechRecognizerButton.setOnAction(event -> {
            secondaryStage.show();
        });





        HBox comandBox = new HBox();
        comandBox.getChildren().addAll(commandField, commandButton, SpeechRecognizerButton);

        VBox window = new VBox();
        listDisplay.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        window.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            displayFrequency();
        });
        window.getChildren().add(comandBox);

        window.getChildren().addAll(desktop, listDisplay);


        Scene scene = new Scene(window, 1450, 750);
//        Scene scene = new Scene(window, 1450, 750);

        keyboardCalls(frequencyList.topFiveIcons,scene);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void displayFrequency() {
        frequencyList.display();
        listDisplay.getChildren().clear();
        listDisplay.getChildren().addAll(frequencyList.display().getChildren());
        listDisplay.getChildren().add(frequencyTitle);
    }
    // Java class to be used as a bridge for communication between JavaFX and JavaScript
//    public class JavaBridge {
//        // Method to receive recognized text from JavaScript
//        public void receiveRecognizedText(String text) {
//            System.out.println("Recognized Text: " + text);
//            // Handle the recognized text in your JavaFX application
//        }
//    }

    private void keyboardCalls(ArrayList<Icon> topIcons, Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DIGIT1) {
                textToSpeech.sayText(topIcons.get(0).getAppName());
            } else if (event.getCode() == KeyCode.DIGIT2) {
                textToSpeech.sayText(topIcons.get(1).getAppName());
            } else if (event.getCode() == KeyCode.DIGIT3) {
                textToSpeech.sayText(topIcons.get(2).getAppName());
            } else if (event.getCode() == KeyCode.DIGIT4) {
                textToSpeech.sayText(topIcons.get(3).getAppName());
            } else if (event.getCode() == KeyCode.DIGIT5) {
                textToSpeech.sayText(topIcons.get(4).getAppName());
            }
        });
    }

    public String firstWord(String input) {
        String[] words = input.split("\\s+");
        String firstWord = words[0];
        StringBuilder remainingWords = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            remainingWords.append(words[i]);
            if (i < words.length - 1) {
                remainingWords.append(" "); // Add space between words
            }
        }
        commandInput = remainingWords.toString().toLowerCase();
        System.out.println("Remaining words: " + commandInput);
        return firstWord;
    }



    public static void main(String[] args) {
        launch(args);
    }
}