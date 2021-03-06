# Android-Study-Jams
### Problem Statement
Due to rising pandemic situations, social distancing has become the part and parcel of our daily life, we tend to avoid crowded places as to 
not get infected by the virus. In such case technology comes in handy where we can access all the necessary details within our fingertips and 
avoid going to the crowded places. Mobile application suits best in this scenario, as it becomes more convenient to use.

### Proposed Solution
“Book easy” helps to book/schedule appointment at places like grocery store, medical shops, supermarts, saloons and many more stores which tends 
to remain packed by consumers by making use of our application people will avoid crowding which will help us maintain a healthy environment as 
well as government norms and protocols will also be followed. In this application, the consumers/people have to book their appointments by enrolling 
into our system. People can track the appointments scheduled for the specific shop.

<img width="200" height="433" src="./Screenshots/Splash Screen.jpg"> <img width="200" height="433" src="./Screenshots/Login & SIgnup.jpg"> <img width="200" height="433" src="./Screenshots/Signup.jpg"> <br>
<img width="200" height="433" src="./Screenshots/Enter OTP.jpg"> <img width="200" height="433" src="./Screenshots/Dashboard.jpg"> <img width="200" height="433" src="./Screenshots/Navigation Drawer.jpg"> <br>
<img width="200" height="433" src="./Screenshots/List of Shops.jpg"> <img width="200" height="433" src="./Screenshots/Book Appointment 1.jpeg"> <img width="200" height="433" src="./Screenshots/Book Appointment 2.jpeg"> <br>
<img width="200" height="433" src="./Screenshots/Book Appointment 3.jpeg"> <img width="200" height="433" src="./Screenshots/Book Appointment 4.jpeg"> <img width="200" height="433" src="./Screenshots/Appointment Details.jpg"> <br>
<img width="200" height="433" src="./Screenshots/Shop Details.jpg">

<!-- ![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Splash%20Screen.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Login%20&%20SIgnup.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Signup.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Enter%20OTP.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Dashboard.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Navigation%20Drawer.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/List%20of%20Shops.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Book%20Appointment%201.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Book%20Appointment%202.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Book%20Appointment%203.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Book%20Appointment%204.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Appointment%20Details.jpg?raw=true)
![alt text](https://github.com/HeetGutka/Android-Study-Jams/blob/main/Screenshots/Shop%20Details.jpg?raw=true) -->

### Functionality & Concepts Used
The app has user-friendly interface, anyone can easily book appointments suitable to their schedule. Following are few android concepts used to achieve the functionality in app:
- `Layout`: Most of the activity in the app uses constraint layout which is easy to handle for different screen sizes also at some places linear layouts are used as well.
- `RecyclerView`: To represent the various shops that are available for appointments. Also, we have used recyclerview to display the appointments that a user has scheduled for a shop.
- `Simple & Easy Views Design`: Simple & Easy Views Design: Use of familiar audience EditText and interactive buttons made it easier for people to register or sign in without providing any detailed instructions pages. Apps also uses App Navigation to switch between different screens.
- `Live Data & Room Database`: We are also using LiveData to update & observe any changes in the appointments. To get all the data from the database or server we are making use of LiveData. For Room Database, we designed an UI to fetch various locations/cities where the registered shops are located.
- `Navigation Library`: For scheduling appointments we have made use of navigation library and databinding concepts. Once the appointment is successfully scheduled, it then can be stored in Firebase.

### Application Link & Future Scope
The app is currently in the testing phase with multiple local shops available around us. You can access the pre-release of our app from [releases](https://github.com/HeetGutka/Android-Study-Jams/releases/) section. Once the app is fully tested and functional with local shops, we plan to approach various businesses, and consultancies to allow this app idea and collaborate with them as well. Also we are planning to schedule appointments not only for the present day but also some 3-4 days in prior. 
We are also planning to add wishlist/favourite features so people can mark shops which they regularly wants to visit. People can also give ratings and 
reviews about shops they visit.
