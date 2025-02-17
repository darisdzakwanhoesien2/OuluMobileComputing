# OuluMobileComputing

Here’s how you can structure your **README.md** based on your **project directory** and feature implementations:

---

# **ESG Sentiment & Topic Analysis Mobile App**  
*A Kotlin-based Android application for real-time ESG monitoring* 🌍📊  

## **📌 Project Overview**
This mobile application **analyzes news, social media, and company reports** to extract ESG-related sentiment and key topics using **Natural Language Processing (NLP) models**. The system provides **real-time insights** into sustainability trends, helping **corporations, investors, regulators, and journalists** monitor ESG-related discussions.

### **✨ Key Features & Points Earned**
This project is part of a **Mobile Computing course** where features contribute to the total grade.

| Feature | Description | Points |
|---------|------------|--------|
| ✅ **Animated Splash Screen** | Enhances user experience with a visually engaging app launch | **5p** |
| ✅ **Camera Functionality** | Allows users to take pictures within the app | **10p** |
| ✅ **Microphone Recording & Playback** | Enables users to record & play audio within the app | **10p** |
| ✅ **Maps SDK Integration** | Displays ESG-related locations on an interactive map | **5p** |
| ✅ **Video Playback** | Supports viewing ESG-related video reports | **5p** |
| ✅ **Runtime Permissions Handling** | Checks & grants permissions dynamically | **5p** |
| ✅ **Using an API** | Fetches real-time ESG news & social data | **5p** |
| ✅ **ESG Sentiment & Topic Modeling** | Uses NLP to extract sentiment and ESG topics | **(Core Feature)** |
| ✅ **Background Sensor/Service** | Monitors real-time ESG sentiment & alerts users | **(Core Feature)** |
| ✅ **Push Notifications** | Sends ESG risk alerts when high-risk trends emerge | **(Core Feature)** |

---

## **📂 Project Directory Structure**
The project consists of an **Android Mobile App (Kotlin, Jetpack Compose)** and a **Backend API (Kotlin-based server)**.

```
📂 OuluMobileComputing  # Root project directory
│── 📂 app
│   │── 📂 src/main/java/com/example/oulumobilecomputing
│   │   │── 📜 MainActivity.kt  # Handles navigation & permission checks
│   │   │── 📜 SplashScreenActivity.kt  # Animated splash screen (5p)
│   │   │── 📜 CameraActivity.kt  # Camera functionality (10p)
│   │   │── 📜 AudioRecorderActivity.kt  # Microphone recording/playback (10p)
│   │   │── 📜 MapActivity.kt  # Maps SDK integration (5p)
│   │   │── 📜 VideoPlayerActivity.kt  # Video playback functionality (5p)
│   │   │── 📜 PermissionHelper.kt  # Runtime permissions handling (5p)
│   │   │── 📜 ApiFetcher.kt  # Fetches real-time ESG news & social data (5p)
│   │   │── 📜 SensorWorker.kt  # Background service for monitoring sensors/API
│   │   │── 📜 NotificationHelper.kt  # ESG Risk Alerts via push notifications
│   │   │── 📂 data
│   │   │   │── 📜 Database.kt  # Room Database setup
│   │   │   │── 📜 UserDao.kt  # Handles user interactions & storage
│   │   │   │── 📜 ESGEntry.kt  # Data model for ESG entries
│   │   │── 📂 ui
│   │   │   │── 📜 ESGDashboard.kt  # Main UI for displaying ESG insights
│   │   │   │── 📜 ESGTrends.kt  # Trending ESG topics visualization
│   │   │   │── 📜 ESGSentiment.kt  # ESG sentiment score & tracking
│   │   │   │── 📜 ESGMap.kt  # Map visualization for ESG locations
│── 📂 app/src/main/res
│   │── 📂 drawable  # Icons, splash screen graphics
│   │── 📂 layout  # XML layout files for activities
│   │── 📂 raw  # Sample audio/video files for testing
│── 📂 backend
│   │── 📂 src
│   │   │── 📂 main/kotlin/com/example/oulumobilecomputing
│   │   │   │── 📜 BackendServer.kt  # REST API handling
│   │   │   │── 📜 SentimentService.kt  # ESG Sentiment analysis backend
│   │   │   │── 📜 TopicService.kt  # NLP-based topic extraction
│   │   │   │── 📜 MapService.kt  # Backend for Maps integration
│   │   │   │── 📜 NotificationService.kt  # Backend for push notifications
│── 📜 AndroidManifest.xml  # App permissions & configurations
│── 📜 build.gradle.kts  # Gradle dependencies & configurations
│── 📜 README.md  # Project documentation
│── 📜 .gitignore  # Git ignore config
```

---

## **🔧 Setup & Installation**
### **📱 Mobile App**
1️⃣ Clone the repository:
```sh
git clone https://github.com/your-repo/OuluMobileComputing.git
cd OuluMobileComputing
```
2️⃣ Open the project in **Android Studio**.  
3️⃣ Run the app on an emulator or connected device.

### **🖥️ Backend API**
1️⃣ Navigate to the backend directory:
```sh
cd backend
```
2️⃣ Start the Kotlin server:
```sh
./gradlew run
```

---

## **🚀 Features in Detail**
### **📸 Camera Integration**
- Allows users to **take photos** inside the app.  
- Stores images for ESG-related documentation.

### **🎙️ Audio Recording & Playback**
- Users can **record ESG-related discussions** and play them back.

### **🗺️ Maps SDK Integration**
- **Displays ESG-related locations** such as environmental protests, sustainability initiatives, or corporate reports.

### **📺 Video Playback**
- Plays **pre-recorded ESG news reports** inside the app.

### **📡 API Fetcher**
- Fetches **real-time ESG sentiment data** from news & social media.

### **🔔 ESG Risk Alerts**
- Sends **push notifications** for high-risk ESG events.

---

## **📊 Real-Time Insights with NLP**
This app **extracts sentiment and topics** from ESG-related data using **NLP techniques:**
- **Sentiment Analysis** → Classifies news & posts as **Positive, Negative, or Neutral**  
- **Topic Modeling (BERTopic, LDA)** → Identifies trending **ESG-related topics**  
- **Named Entity Recognition (NER)** → Extracts **company & industry names**

---

## **📜 License**
This project is open-source and follows the **MIT License**.

---

## **🙌 Contributors**
Developed by **[Your Name]** as part of the **Oulu Mobile Computing Project**.

---

### **🔗 Next Steps**
✅ Implement **real-time social media monitoring**  
✅ Expand **ESG alerts with AI-driven forecasting**  
✅ Improve **UI for ESG insights & interactive charts**

---

This **README.md** provides **clear project documentation**, making it **easy to understand** the **features, directory structure, and setup instructions**. 🚀 Let me know if you'd like further **refinements!** 💡
