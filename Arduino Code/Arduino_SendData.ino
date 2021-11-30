
#include <Servo.h>
#include <Wire.h>
#include <Adafruit_INA219.h>


Adafruit_INA219 ina219;
Servo servo;

//Hardware pin definitions
int UVOUT = A0; //Output from the sensor
int REF_3V3 = A1; //3.3V power on the Arduino board
float uvIntensity = 0;
float outputVoltage = 0;
int leftResistor = A2;;
int rightResistor = A3;
int tolerance = 2;
int pos = 90;
String values;
unsigned long previousMillis = 0;
unsigned long interval = 100;
const int chipSelect = 10;
float shuntvoltage = 0;
float busvoltage = 0;
float current_mA = 0;
float loadvoltage = 0;
float energy = 0;
float power = 0;

void setup()
{
  Serial.begin(9600);
  servo.attach(9);
  ina219.begin();
  ina219.setCalibration_16V_400mA();
  pinMode(UVOUT, INPUT);
  pinMode(REF_3V3, INPUT);
  pinMode(leftResistor, INPUT);
  pinMode(rightResistor, INPUT);
  servo.write(pos);
  delay(2000);
}
 
void loop()
{
  //UV Sensor Calculations
  int uvLevel = averageAnalogRead(UVOUT);
  int refLevel = averageAnalogRead(REF_3V3);
  float outputVoltage = 3.3 / refLevel * uvLevel;
  uvIntensity = mapfloat(outputVoltage, 1, 2.8, 0.0, 15.0); //Convert the voltage to a UV intensity level

  //Removing previously buffered serial data
  Serial.flush();
  delay(1000);
  values = (Power() + "," + UVstring() + "," + Energy());
  //Send sensors data to serial (sent sensors data to ESP8266)

  Serial.print(values);
  delay(2000);

  //Servo controls
  int leftPhoto = analogRead(leftResistor);
  int rightPhoto = analogRead(rightResistor);

  if((abs(leftPhoto - rightPhoto) <= tolerance) || (abs(rightPhoto - leftPhoto) <= tolerance)) {
    //do nothing if the difference between values is within the tolerance limit
  } else {
    if(leftPhoto > rightPhoto)
    {
      pos = --pos;
    }
    if(leftPhoto < rightPhoto) 
    {
      pos = ++pos;
    }
  }
 
  if(pos > 170) { pos = 170; } // reset to 180 if it goes higher
  if(pos < 0) { pos = 0; } // reset to 0 if it goes lower

  servo.write(pos); // write the position to servo
  delay(50);

  //INA219 Measurements
  ina219values();
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval)
  {
    previousMillis = currentMillis;
    ina219values(); 
  }

}
 
//Takes an average of readings on a given pin
//Returns the average
int averageAnalogRead(int pinToRead)
{
  byte numberOfReadings = 8;
  unsigned int runningValue = 0; 
 
  for(int x = 0 ; x < numberOfReadings ; x++)
    runningValue += analogRead(pinToRead);
  runningValue /= numberOfReadings;
 
  return(runningValue);
}
 
float mapfloat(float x, float in_min, float in_max, float out_min, float out_max)
{
  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}

String UVstring()
{
  return String(uvIntensity); 
}

String Power()
{
  return String(power);
}

String Energy()
{
  return String(energy); 
}

void ina219values() {
  shuntvoltage = abs(ina219.getShuntVoltage_mV());
  busvoltage = abs(ina219.getBusVoltage_V());
  current_mA = (loadvoltage200)/5;
  loadvoltage = busvoltage + (shuntvoltage / 1000);
  power = (loadvoltage current_mA);
  energy = (energy + loadvoltage * current_mA / 3600);
}
