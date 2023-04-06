##Overview
This document provides detailed specifications for the [ChipseaCloudV3SDK Android]. This SDK provides the [Function Introduction] function, supporting [Operating System and Language Environment], etc.

##System requirements
Development tool: Android Studio
Integration method: manually importing SDK
Android version support: minSdkVersion 18

##Usage Guidelines
1. Import SDK related files.
After downloading the SDK, extract the sample code
Add /app/libs/cloud_v3_lib.jar to the project

2. Interface Call Description

```
1： Call interface description:
1.	BIAWorkerFactory：
1) . Call BIAWorkerFactory. createWorker (context) ---- to create a BIAWorker class;

2.	BIAWorker：

1).BIAWorker.start(mac?=null, RoleInfo curRole, byte[] broadcastData?=null, BIACallback); --- Start measuring function.
Parameter description: 
mac: the current device mac address, which can be empty. The SDK will automatically match according to the protocol.
curRole: current user information, RoleInfo type.
BroadcastData: When searching for devices, the broadcast byte array obtained can be empty.
BIACallback: callback interface
2) BIAWorker. stop () --- Stop measuring function/stop binding device function.
3) BIAWorker. getAddress() --- to obtain the MAC address function of the device being measured.
4) BIAWorker. getRSSI() --- to obtain the RSSI value function of the device being measured.
5) BIAWorker. getState() --- to obtain the current working state function.
Return value description: 
    0  After the initial creation and call of stop(), the status of the worker changes to IDLE
    1  After calling start(), the worker status changes to DOING
    2  When all measurements are completed, the worker status changes to DONE

6) BIAWorker. getversionCode() --- get the sdk version.
7) BIAWorker.bindDevice(BIACallback); Bind device functions
Parameter description: BIACallback: callback interface

3.  BIACallback:
1) . onResult (int type, WeightEntity data) --- to return the measurement result function.
Return value description: 
    type:  0  is an unstable result (process data).
           1  Stable results (locked data).
    data:  weight data,  WeightEntity type.
2) . onDeviceInfo (String name,String mac, String manufacturer, String model,
                                     String version) --- "returns the device broadcast name, mac, manufacturer,model and version function searched during binding.
3) . onState (int state, String msg) --- to return the working state of the current worker.
 Return value description:
  state 
    0 -- msg Bluetooth is not turned on
    1 -- - msg location permission is not opened
    2 -- - msg report data
    3 -- - msg distribution number
    4 --- msg is measuring weight
    5 --- msg is measuring fat
    7 --- msg Bluetooth is off
    8 --- msg Bluetooth is on
    9 -- - msg search device
    10 -- - msg discovers gatt service
    11 -- - msg gatt connection closed
    12 -- - msg is connecting
    13 -- - msg connected
    14 -- msg Wait for connection
    15 --- msg in calculation
    16 --- msg waiting for weighing
    17 -- - msg positioning status has changed
    18 -- - msg measurement completed
    19 -- - msg search timeout
    20 ----msg mac is not match
    21 ----msg packageName is not match
    

4. UserInfo data attribute definition format:


"height"//Int * user height
"sex"//Int * User gender 1==male, 0==female
"age"//Int * user age


5. Measurement result data WeightEntity data attribute definition format:

“weight”
“scaleWeight” //The weight data displayed on the scale
“score”   //body score
“bmi”
“axunge”
“muscleWeight”
“viscera”
“bodyLevel"   //Body type
“metabolism”
“water”
“bone”
“protein”
“thinWeight”
“body_age”
“weight_time"   //Measure the time
```
Please refer to Demo for details


## version history
| Version |                         Description                          |
| :-----: | :----------------------------------------------------------: |
| 1.0.3 | add model、version、manufacturer information|



