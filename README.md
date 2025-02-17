# OuluMobileComputing

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
