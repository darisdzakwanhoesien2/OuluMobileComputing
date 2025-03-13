These project are intended to implement these features: Microphone, Camera, Video Playback, Messages. The project directory structure are divided into (under OuluMobileComputing/app/src/main/java/com/example/mobilecomputingproject)

- folder: database
- folder: media
- folder: ui
-> MainActivity.kt

Under the (folder: database): 
-> Supporting feature of Message: 
1. Message.kt (as data model) that helps to define the data model (Room entity)
2. MessageDao.kt (Data Access Object) -> handles database operations (CRUD functions)
3. MessageViewModel.kt (ViewModel) -> that helps manages UI state and interacts with the database
4. AppDatabase.kt -> Room database setup for the app, ensuring that messages are stored persistently 

The splitting of these module (it's model, CRUD operations, UI stages and app setup) are intended to maintain it's modularity for future development

Under the (folder: media): 
1. AudioRecorder.kt: recording and playing audio components (with Jetpack Compose UI, using MediaRecorder and MediaPlayer)
2. CameraCapture.kt: capture an image using the device camera and display the captured image in the UI

Under the (folder: ui): 
1. MainScreen.kt: input messages, store them in a ViewModel, and display the list of messages

For the main page, MainActivity.kt aims to become the entry point of the app by extending ComponentActivity and setting up the UI using Jetpack Compose (central Kotlin command)

The other setup that needs to be added are 
- OuluMobileComputing/app/src/main/AndroidManifest.xml: Setup the permission for Microphone, Camera, Storage, and Internet, definiing the MainActivity as the launcher activity and it's aiming for initial setup  supports for Speech Recognition
-  build.gradle.kts: Setup dependencies needed for the features, such as (Jetpack Compose, Room Database, ExoPlayer for Video Playback, Camera & Audio Permissions, ViewModel & LiveData, Kotlin Coroutines)
Rerouting from: OuluMobileComputing/app/build.gradle.kts -> OuluMobileComputing/build.gradle.kts