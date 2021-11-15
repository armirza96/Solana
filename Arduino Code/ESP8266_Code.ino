#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "coen390-19e55-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "secret"
#define WIFI_SSID "your wifi ssid"
#define WIFI_PASSWORD "your wifi password"

float uv_index;

void setup() {
  //Initializes the serial connection at 9600 get sensor data from arduino.
   Serial.begin(9600);

   delay(1000);
  
  //Connecting to wifi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }
  
  //Connecting to our realtime database
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH); 
}

void loop() {

 bool Sr =false;
 
  while(Serial.available()){
    
        //Receiving sensor data from arduino through serial
        uv_index=Serial.parseFloat(); //Assign the received float to uv_index
        Sr=true;    
    }
  delay(1000);

//store UV Index value as a float in Firebase 
  if(Sr==true){  
  Firebase.setFloat("UV Index",uv_index);
  //handling errors
  if (Firebase.failed()) {    
      return;
  }
}
delay (1000);

}
