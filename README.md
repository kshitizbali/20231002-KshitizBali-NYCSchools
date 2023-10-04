# 20231002-KshitizBali-NYCSchools
A Demo app showcasing NYC schools and its details.

**Features**:
1. Fetches schools list in NYC.
2. Schools list has some info for each list item.
3. School detail screen which showcases school test details. 
4. We can access any school details by clicking on a school from the list on the main screen. FYI, some schools dont have details so the screen will display an appropriate message.
5. Pagination implementation using google's pagination library which works well with compose and room database. Since the API was returning pretty big response so it was needed. It loads 20 items per page.
6. Room DB also implemented to showcase offline access to users in case internet is not available. Currently the app will prefer internet api as the source of truth and only backs to Room DB when
   internet not available. This works for both schools list and detail screen data as it fetches from the db (if data present) when internet not available.

**Project composition**:
We make use of MVVM along with the clean architecture.
1. Data -> Which is the data classes where we handle the data.
2. DI -> Dependency injection implementation.
3. Domain -> Where we keep the data repositories and its implementation to provide the data as we like to the view.
4. Presentation -> Not to be confused with Presenter from MVP. This is just a name used for the view layer where we present/show
                   the data on the UI to the end user.

**Some Details**:
1. Project uses two api from city of New York api. One is used to get schools list and the other on is used for getting details of a particular school.
2. There was a delay added in the details screen to simulate the loading of data since the api actually loads very fast.
3. Custom icons and app icon used.
4. RoomDB implmentation
5. Pagination library from google used to paginate hundres (or even thousands) of list items showing 20 items per page.

**Some Improvements**:
1. Rely more on Room DB by updating the db only when new/updated data is available. We can use http headers for this with the api that supports it. This can decrease our API calls alot.
2. If we could get school image that would improve the overall presentation.
3. Animation to improve the UI even further. I personally wanted to use it but it was not high on my priority list at the end.

**Tools/Resources used**:

| Type              | Used                 | Comments                                                                          |
|-------------------|----------------------|-----------------------------------------------------------------------------------|
| Coding Language   | Kotlin               | There is a js file due to which the project shows over 80% in js on repo page. That js file contains all the cities in US to search for.                        |
| Architecture      | MVVM/Clean Archi     |  Clean architechture where models are the data clases, seperate viewmodel and the presentation (just a name used and not MVP) view layer  .                     |
| Network Library   | Retrofit             |                                                                                   |
| Unit Test         | JUnit/Espresso       |                                                                                   |
| Network-Concurrency|Kotlin Coroutines    |                                                                                   |
| UI                |Jetpack Compose       |                                                                                   |
| Dependency Inject.|Dagger 2, Hilt        |                                                                                   |
| Database          |Room                  |                                                                                   |
