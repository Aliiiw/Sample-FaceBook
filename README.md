# Sample FaceBook

Sample FaceBook is a Jetpack Compose Android sample that recreates a Facebook-like mobile UI using local mock data and bundled image assets. The app includes a scrolling feed, bottom navigation, a notification list, a navigation drawer with shortcuts, and detail pages for selected feed items.

The actual Android project is stored in the `FaceBookSample/` directory.

## Features

- Native Android app written in Kotlin
- Jetpack Compose UI
- Facebook-like feed layout
- Bottom navigation with Home, Notifications, and Menu actions
- Navigation drawer opened from the bottom bar
- Drawer profile row and shortcut grid
- Home feed with vertical dessert posts and horizontal fruit rows
- Clickable feed items that open detail pages
- Notification screen with mock people/activity data
- Deep link pattern for detail screens
- Local mock data for desserts, fruits, people, and shortcuts
- Large bundled drawable asset set for profile photos, fruit photos, dessert photos, and shortcut icons
- Material theme starter files

## Project Structure

```text
.
+-- README.md
+-- FaceBookSample/
+   +-- build.gradle
+   +-- settings.gradle
+   +-- gradlew
+   +-- gradle/
+   +-- app/
+       +-- build.gradle
+       +-- src/main/
+           +-- AndroidManifest.xml
+           +-- java/com/alirahimi/facebooksample/
+           |   +-- MainActivity.kt
+           |   +-- data/
+           |   +-- navigation/
+           |   +-- screens/
+           |   +-- ui/theme/
+           +-- res/drawable/
+           +-- res/values/
+```

## Main Screens

| Screen | Route | Description |
| --- | --- | --- |
| Home | `home` | Main feed with dessert posts and horizontal fruit cards. |
| Notifications | `notification` | Activity list generated from local `Person` data. |
| Detail | `detail/{itemId}` | Full item page for a selected dessert or fruit. |
| Drawer | Scaffold drawer | Profile row, random shortcut row, and all-shortcuts grid. |

The detail screen also declares this deep link pattern:

```text
https://www.fblikeapp.com/{itemId}
```

## Main Components

| File | Purpose |
| --- | --- |
| `MainActivity.kt` | App entry point, theme setup, scaffold, drawer, bottom bar, and navigation host. |
| `screens/HomeScreen.kt` | Builds the mixed feed from dessert and fruit lists. |
| `screens/NotificationScreen.kt` | Displays mock notifications using local person data. |
| `screens/ItemDetailScreen.kt` | Shows image, title, origin, and description for selected items. |
| `screens/NavigationDrawer.kt` | Drawer UI with profile, random shortcuts, and shortcut grid. |
| `screens/Destination.kt` | Sealed route definitions for home, notifications, and detail. |
| `navigation/FacebookBottomNavigation.kt` | Bottom navigation bar with Home, Notifications, and Menu. |
| `data/ListItem.kt` | Shared base model, id generator, item lookup, and random item helper. |
| `data/Dessert.kt` | Dessert mock content and image references. |
| `data/Fruit.kt` | Fruit mock content and image references. |
| `data/Person.kt` | Mock people and notification messages. |
| `data/Shortcuts.kt` | Drawer shortcut models and icon tint values. |

## Data Model

The app is fully local and does not call a backend. It uses static lists inside Kotlin files:

| Data source | Count | Used for |
| --- | ---: | --- |
| `Dessert.getAllDesserts()` | 20 | Feed posts and detail pages |
| `Fruit.getAllFruits()` | 20 | Horizontal cards and detail pages |
| `Person.getPeople()` | 20 | Notifications and random drawer items |
| `Shortcut.getShortcuts()` | 14 | Drawer shortcut grid |

`ListItem.getUUID()` assigns incremental ids across the mock objects. `getItem(id)` searches desserts and fruits for the detail screen.

## Feed Behavior

`HomeScreen` renders the feed in pages of five items:

1. Five dessert posts are rendered vertically.
2. A horizontal row of five fruit cards is inserted.
3. The pattern repeats until the local lists are exhausted.
4. Tapping a dessert or fruit navigates to `detail/{itemId}`.

## Drawer Content

The drawer includes:

- A profile row using the local `user.jpeg` asset and the label `Aliiiw`
- A `Your Shortcuts` horizontal row using randomly selected desserts, fruits, and people
- An `All Shortcuts` adaptive grid with icons such as Marketplace, Memories, Videos, Friends, Groups, Saved, Pages, Reels, News, Dating, Event, Gaming, and Shop

## Tech Stack

- Kotlin
- Android
- Jetpack Compose
- Compose Material
- Compose Navigation
- Gradle wrapper
- Android Gradle Plugin `7.3.0`
- Kotlin Android plugin `1.6.10`
- Compose UI `1.1.1`
- Compose compiler extension `1.1.1`

## Android Configuration

| Setting | Value |
| --- | --- |
| Namespace | `com.alirahimi.facebooksample` |
| Application ID | `com.alirahimi.facebooksample` |
| Min SDK | `21` |
| Target SDK | `32` |
| Compile SDK | `32` |
| Version | `1.0` |
| App label | `FaceBookSample` |
| Main activity | `com.alirahimi.facebooksample.MainActivity` |

## Assets

The app ships with many local drawable resources:

- `p1.jpeg` to `p20.jpeg` for people/profile content
- `f1_*.jpeg` to `f20_*.jpeg` for fruit content
- `d1_*.jpeg` to `d20_*.jpeg` for dessert content
- shortcut icons such as `ic_marketplace`, `ic_people`, `ic_reels`, `ic_saved`, and more
- `user.jpeg` for the drawer profile image

## How to Run

Open this folder in Android Studio:

```text
FaceBookSample/
```

Then sync Gradle and run the `app` configuration on an emulator or Android device.

From the command line, once Android SDK is configured:

```bash
cd FaceBookSample
./gradlew :app:assembleDebug
```

To compile Kotlin only:

```bash
cd FaceBookSample
./gradlew :app:compileDebugKotlin
```

If Gradle cannot find the Android SDK, set `ANDROID_HOME` or create `FaceBookSample/local.properties`:

```properties
sdk.dir=/path/to/Android/Sdk
```

## Current Build Note

A Gradle compile check was attempted with:

```bash
./gradlew :app:compileDebugKotlin
```

The environment could run Gradle, but Android SDK was not configured:

```text
SDK location not found. Define a valid SDK location with an ANDROID_HOME environment variable or by setting the sdk.dir path in local.properties.
```

This is an environment configuration issue.

## Notes

- This is a UI sample project with local mock data.
- There is no authentication, backend, persistence, or real social network integration.
- Drawer shortcuts and notification messages are static/random local content.
- `NotificationScreen` messages are generated when the `Person` list is initialized.
- The default Android starter tests are still present and do not validate the Compose UI.
- Some files contain unused imports from earlier experimentation.

## Possible Improvements

- Add UI tests for navigation, feed rendering, and drawer behavior
- Add stable ids instead of generating ids from a mutable companion counter
- Add app-specific theme colors instead of the default purple/teal starter palette
- Clean up unused imports
- Add state restoration for selected bottom navigation destination
- Add fake reactions/comments/share actions to make the feed more complete
- Move mock data to JSON or a local data source layer
- Add accessibility-friendly content descriptions for images and icons
