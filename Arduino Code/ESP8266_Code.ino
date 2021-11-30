#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "coen390-19e55-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "secret"
#define WIFI_SSID "your wifi ssid"
#define WIFI_PASSWORD "your wifi password"

String values;

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
        values=Serial.readString(); //Assign the received float to uv_index
        Sr=true;
    }
  delay(1000);
  int firstCommaIndex = values.indexOf(',');
  int secondCommaIndex = values.indexOf(',', firstCommaIndex+1);
  int thirdCommaIndex = values.indexOf(',', secondCommaIndex + 1);
  String po = values.substring(0, firstCommaIndex);
  String uv = values.substring(firstCommaIndex+1, secondCommaIndex);
  String en = values.substring(secondCommaIndex+1);

  float power = po.toFloat();
  float uvIndex = uv.toFloat();
  float energy = en.toFloat();

//store UV Index value as a float in Firebase 
  if(Sr==true){ 
  Firebase.setFloat("Power", power); 
  Firebase.setFloat("UV Index",uvIndex);
  Firebase.setFloat("Energy", energy);
  //handling errors
  if (Firebase.failed()) {
      return;
  }
}
delay (1000);

}
