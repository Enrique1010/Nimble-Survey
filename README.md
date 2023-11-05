# Nimble-Survey
App to browse between interesting surveys. Made with android jetpack compose.

# Application ScreenShots
- Login/Forgot Password/Splash

![image](https://github.com/Enrique1010/Nimble-Survey-Challenge/assets/42783065/5b5e2920-7a8e-4c19-b530-a0c04cc95a5c)

- Loading/Home/Details

![image](https://github.com/Enrique1010/Nimble-Survey-Challenge/assets/42783065/87498449-1236-46b9-b6bf-aeb81a212057)

- Profile/Logout/Home

![image](https://github.com/Enrique1010/Nimble-Survey-Challenge/assets/42783065/53c89b19-b8ec-44f9-8df1-30b5f0d09ac1)



# Challenge Objectives (✓ = Completed)

- Implement the login authentication screen. ✓
- Implement the OAuth authentication including the storage of access tokens. ✓
- Implement the automatic usage of refresh tokens to keep the user logged in using the OAuth API. ✓
- On the home screen, each survey card must display the following info:
  - Cover image (background) ✓
  - Name (in bold) ✓
  - Description ✓
- There must be 2 actions:
  - Horizontal scroll through the surveys. ✓
  - A button “Take Survey” should take the user to the survey detail screen. ✓
  - The list of surveys must be fetched when opening the application. ✓
  - Show a loading animation when fetching the list of surveys. ✓
  - The navigation indicator list (bullets) must be dynamic and based on the API response. ✓
- Authentication: implement the forgot password authentication screen. ✓
- Home Screen:
  - Implement the caching of surveys onto the device. ✓
  - Implement a pull-to-refresh action to update the list of surveys. Show a loading animation when refreshing the list of surveys. ✓

# Non-native libraries used

- [Retrofit 2](https://square.github.io/retrofit/) To better handle API calls.
- [Coil](https://coil-kt.github.io/coil/compose/) Provides an AsyncImage component to handle images from URLs.
- [Google Accompanist permissions](https://google.github.io/accompanist/permissions/)  To better handle android permissions.

# Test Cases

- Testing Room database - tested all methods to read/write/delete from database.
- Testing Utils method to get format date.

# Known Issues / Suggestions
- Image Quality: to appease the poor quality of the images received, a blur effect was slightly implemented.
- Notification Permission: if notification permission is not granted, notifications will not appear when you try to reset the password. 
In order for them to appear again with the app in use, they must be granted from settings.
- Pull to refresh: it is not recommended to use "pull to refresh" on screens that use horizontal gestures or on screens that do not have vertical scrolling, as it may affect the user experience or the screen behavior.

# Used Devices for testing

- Pixel 7 - Android 14 (Physical)
- One Plus 7T - Android 11 (Physical)
- Xiaomi Poco X3 Pro - Android 12 (Physical)
- Samsung Galaxy S21 FE - Android 12 (Physical)
- Pixel 7 - Android 13 (Emulator)
- Pixel 4 - Android 11 (Emulator)
- Pixel XL - Android 10 (Emulator)
- Nexus 4 - Android 9 (Emulator)
- Pixel 4a - Android 11 (Emulator)

# Video App Demo

- [Application Video Demonstration]([https://drive.google.com/file/d/1TnpVVZm5oZAsKtQGHQzPOiPqIpRHArQa/view?usp=share_link](https://drive.google.com/file/d/1DzRyzBWYdwWkWbmvMyE2Ydfmi34Ft8po/view?usp=sharing)https://drive.google.com/file/d/1DzRyzBWYdwWkWbmvMyE2Ydfmi34Ft8po/view?usp=sharing)

## License

Open Source
