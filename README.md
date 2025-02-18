# OuluMobileComputing

# **ESG Sentiment & Topic Analysis Mobile App**  
*A Kotlin-based Android application for real-time ESG monitoring* 🌍📊  

## **📌 Project Overview**
This mobile application **analyzes news, social media, and company reports** to extract ESG-related sentiment and key topics using **Natural Language Processing (NLP) models**. The system provides **real-time insights** into sustainability trends, helping **corporations, investors, regulators, and journalists** monitor ESG-related discussions, including determines sentiment (Positive, Neutral, Negative), identifies trending ESG topics, and provides real-time alerts for high-risk ESG issues.

## **2️⃣ High-Level System Architecture**
The project is split into **three main components**:

1️⃣ **Mobile App (Frontend - Kotlin)**
Handles the **user interface (UI), interactions, and displaying ESG insights**.
   - Collects and visualizes ESG sentiment and topic analysis.
   - Implements various features: video playback, maps, camera, microphone, real-time ESG alerts.

2️⃣ **Backend Server (Kotlin)**
Manages **data storage, processing, and APIs**.
   - Handles sentiment analysis, topic extraction, and data storage.
   - Provides APIs for the mobile frontend.

3️⃣ **Data Processing & Alerts**
   - Uses WorkManager & SensorWorker for background ESG data fetching.
   - Sends push notifications for real-time ESG risk alerts.

### 📂 **Configuration & Build Files**
- **AndroidManifest.xml**: Defines app permissions and components.
- **build.gradle.kts**: Contains Gradle dependencies and configurations.
- **README.md**: Project documentation.
- **.gitignore**: Git ignore configuration.

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

## **7️⃣ Why This Project Matters**
🚀 **Real-World Impact**: Helps **companies, investors, and regulators** monitor ESG risks.  
📡 **AI-Powered Insights**: Uses **NLP models (Sentiment Analysis & BERTopic)**.  
🔍 **Real-Time Alerts**: Detects **ESG violations & trends** for immediate action.  
📱 **Feature-Rich App**: Integrates **maps, camera, microphone, video, and notifications**.  

Here’s the **consolidated version** of the key features and points allocation for the **Mobile Computing course project**:

---

## **✨ Key Features & Points Earned**
This project is part of the **Mobile Computing course**, where features contribute to the final grade. Below is a breakdown of the implemented features and their respective points.

### **🚀 Core Features**
| **Feature** | **Description** | **Points** |
|------------|----------------|------------|
| ✅ **Animated Splash Screen** | Enhances user experience with a visually engaging app launch | **5p** |
| ✅ **Camera Functionality** | Allows users to take pictures within the app (capture ESG-related images) | **10p** |
| ✅ **Microphone Recording & Playback** | Enables users to record & play audio within the app (record ESG discussion) | **10p** |
| ✅ **Maps SDK Integration** | Displays **ESG-related locations** on an interactive map | **5p** |
| ✅ **Video Playback** | Supports viewing ESG-related video reports (reports. news and educational content) | **5p** |
| ✅ **Runtime Permissions Handling** | Dynamically checks and grants permissions (notification, sensors, media access) | **5p** |
| ✅ **Using an API** | **Fetches real-time ESG content** (news & social data) | **5p** |
| ✅ **Add new entries to database** | Stores ESG-related discussions and user inputs | **5p** |

### **🌍 Advanced ESG-Specific Features (Core Project Scope)**
| **Feature** | **Description** | **Points** |
|------------|----------------|------------|
| ✅ **ESG Sentiment & Topic Modeling** | Uses NLP models (BERTopic/LDA) to analyze ESG discussions | **(Core Feature)** |
| ✅ **Background Sensor/Service** | Monitors real-time ESG sentiment & alerts users | **(Core Feature)** |
| ✅ **Push Notifications** | Sends ESG risk alerts when high-risk trends emerge | **(Core Feature)** |

---

### **🎯 Why These Features Matter**
- **Enhances user experience** with media-rich interactions (camera, audio, video).
- **Integrates real-time APIs** for ESG sentiment analysis.
- **Uses AI-powered insights** for ESG risk monitoring.
- **Enables user interactivity** via maps, notifications, and background services.

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


## **3️⃣ Features & File Structure**
Below is the project’s **high-level programming structure**, detailing how the files interact:

### **📱 Mobile App (Frontend)**
1️⃣ **User Interface (UI)**
   - `MainActivity.kt` handles navigation.
   - `ESGDashboard.kt` displays **real-time ESG sentiment and trending topics**.
   - `ESGTrends.kt` visualizes **key ESG discussions**.

2️⃣ **Permissions & Sensors**
   - `PermissionHelper.kt` Manages runtime permissions: ensures **permissions for camera, microphone, notifications**.
   - `SensorWorker.kt`  Runs background tasks (fetches **sensor data** & real-time ESG reports in the background)

3️⃣ **Data Fetching & Processing**
   - `ApiFetcher.kt` calls **backend APIs** to get ESG news, sentiment, and trending topics.
   - `NotificationHelper.kt` handles **push notifications** for ESG risk alerts.

4️⃣ **Media & Maps**
   - `VideoPlayerActivity.kt`: Plays **ESG-related videos (reports and news)**.
   - `CameraActivity.kt`: Captures **ESG-related photos**.
   - `AudioRecorderActivity.kt`: Records **audio recording/playback (ESG discussions, interviews)**.
   - `MapActivity.kt`: Displays **ESG-related locations**.

### **☁️ Backend Server**
1️⃣ **Sentiment Analysis** (`SentimentService.kt`)
   - Extracts **sentiment scores** (Positive, Neutral, Negative) from ESG content.

2️⃣ **Topic Extraction** (`TopicService.kt`)
   - Uses **BERTopic / LDA** to identify **trending ESG topics**.

3️⃣ **Data Storage** (`Database.kt`): 
   - Sets up Room Database for storing ESG data (**ESG sentiment scores & topic analysis**)

4️⃣ **Notification Service** (`NotificationService.kt`)
   - Sends **real-time ESG alerts** to users via push notifications.

- `UserDao.kt`: Manages user interactions and stored ESG discussions.
- `ESGEntry.kt`: Defines data model for ESG sentiment and topics.


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

✅ **Step 1: Implement Core Features**
- Develop **backend APIs** (Sentiment & Topic Extraction).
- Implement **UI & Frontend Components**.
- Integrate **Maps SDK, Camera, Audio, and Video Playback**.

✅ **Step 2: Testing & Debugging**
- Validate **background services (SensorWorker, NotificationHelper)**.
- Test **data fetching & API integration**.
- Ensure **permissions & media functionality** work correctly.

✅ **Step 4: Documentation & Final Submission**
- Write **detailed feature descriptions in README.md**.
- **Record a demo video** showing all features.
- Submit the final project.

---

## **Final Thoughts**
This project provides an **end-to-end ESG monitoring system** using AI-powered analysis, mobile computing, and real-time alerts. It combines **backend processing (Kotlin), real-time data fetching (APIs, sensors), interactive UI (Jetpack Compose), and notification-based risk alerts**.
