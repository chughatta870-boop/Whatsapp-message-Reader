# WhatsApp Awaz Reader

Ye Android app aane wale WhatsApp messages ka sender aur text pakar kar bulund awaz (Text-to-Speech) se parh kar sunati hai — taake anparh ya kam-parh admi bhi asani se samajh sake ke message kis ne aur kya bheja hai.

## Kaam kaise karta hai
1. `WhatsAppNotificationListener.kt` ek `NotificationListenerService` hai jo sirf `com.whatsapp` aur `com.whatsapp.w4b` (WhatsApp Business) ki notifications sunta hai.
2. Jab bhi WhatsApp notification aaye, sender ka naam aur message text nikal kar Android ke built-in TTS engine se bola jata hai.
3. `MainActivity.kt` user ko seedha "Notification Access" settings screen par le jata hai jahan is app ko permission dena hoti hai.

## Setup Steps — Sirf Mobile Se (PC/Laptop ki zaroorat nahi)

Ye project GitHub Actions se khud APK build karta hai. Bas ye steps follow karein:

1. **GitHub par naya repository banayein** (mobile browser se): github.com → "+" icon → "New repository" → naam dein (e.g. `whatsapp-awaz-reader`) → Public ya Private → **Create repository**.
2. Repository ke andar **"Add file" → "Upload files"** par tap karein.
3. Is zip ko phone me **extract** karein (koi bhi file manager app se, e.g. "Files" ya ZArchiver), phir poori `WhatsAppReader` folder ka andar wala saara content (folders + files) select kar ke upload kar dein. Sabse important: `.github/workflows/build.yml` folder bhi upload zaroor ho.
4. Upload ke baad **"Commit changes"** dabayein.
5. Ab GitHub repo ki **"Actions"** tab par jayein — "Build APK" workflow khud chalna shuru ho jayega (green dot/spinner dikhega).
6. 3-5 minute wait karein — jab tak green tick ✅ na aa jaye.
7. Us completed run par tap karein → neeche **"Artifacts"** section me **"WhatsAppAwazReader-debug-apk"** milega → download kar lein (zip format me aayega).
8. Downloaded zip ko phone me extract karein — andar `app-debug.apk` milega.
9. Us APK par tap kar ke install karein (agar "Unknown apps" wali warning aaye to **Settings → allow install from this source** kar dein — sirf ek dafa).
10. App open karein → "Notification Access On Karein" button dabayein → agli screen par **"WhatsApp Awaz Reader"** ko **ON** kar dein.
11. Bas — ab jab bhi WhatsApp par naya message aaye, phone khud bol kar bata dega.

## Zaroori Baatein
- WhatsApp ki notification settings me **"Show message content"** on honi chahiye, warna sirf "New message" bolega, poora text nahi.
- Agar phone lock hai aur "Hide sensitive content" wali setting WhatsApp me on hai, to bhi sirf naam ya khali text milega.
- Ye app poori tarah phone ke andar (locally) kaam karti hai — koi data internet par nahi jata.
- Google Play Store par publish karne ke liye "Notification Access" istemal karne wali apps ka strict privacy review hota hai — is liye pehle personal/family use ke liye APK banayein, phir Play Store submission ke waqt guidance lein.

## Aage kya customize ho sakta hai
- Sirf specific contacts ke messages parhna
- Urdu TTS voice better karne ke liye Google TTS "Urdu" language pack phone me install karwana
- Message ke sath ek chhota notification/vibration bhi add karna
