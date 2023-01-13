import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:world_time/Services/World_time.dart';
class Loading extends StatefulWidget {
  @override
  _LoadingState createState() => _LoadingState();
}

class _LoadingState extends State<Loading> {


/*
//Asyno methods to receive server data

  void getTime() async{

    Response response= await get('https://worldtimeapi.org/api/timezone/Europe/London');
    await Future.delayed(Duration(seconds: 2), () {});
    Map data = jsonDecode(response.body);
    //print(response);

    // Get properties from data
    String dateTime = data['datetime'];
    String offset = data['utc_offset'].substring(1,3);
    // print(dateTime);
    // print(offset);

    // Create DateTime object
    DateTime now  = DateTime.parse(dateTime);
    now = now.add(Duration(hours: int.parse(offset)));
    print(now);
  }

 */
String time = 'loading';
void setUpWorldTime() async{
  World_time instance = World_time(location: 'Berlin',flag: 'germany.png',url: 'Europe/Berlin');
  await instance.getTime();
  Navigator.pushReplacementNamed(context, '/Home',arguments: {
    'location': instance.location,
    'flag': instance.flag,
    'time': instance.time
  });
  //print(instance.time);
  /*setState(() {
    time = instance.time;
  });*/
}
  @override
  void initState() {
    super.initState();
    setUpWorldTime();

  }



  /// Running async method on initiating class

/*

  //Async methods to receive server data
  void getData() async {
    String username = await Future.delayed(Duration(seconds: 3), () {
      return 'Prince';
    });

    await Future.delayed(Duration(seconds: 2), () {
      print('printed');
    });

    print('$username -  Printed');
  }

  @override
  void initState() {
    super.initState();
    getData();
    print('in inistate()');
  }
*/

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blue,
      body: Center(
        child: SpinKitCircle(
          color: Colors.white,
          size: 50.0,
        ),
      ),
    );
  }
}
