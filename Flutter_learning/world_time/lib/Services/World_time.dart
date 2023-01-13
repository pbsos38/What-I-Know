import 'package:http/http.dart';
import 'dart:convert';
import 'package:intl/intl.dart';

class World_time {
  String location; //Location name for UI
  String time; //the time in that location
  String flag; // url to assert flag icon
  String url; //Location url for api endpoints

  World_time({this.location, this.url, this.flag});

//Asyno methods to receive server data

  //This void is replaced by future<void> so that we can keep control waiting after calling this function from some other class/method
  Future<void> getTime() async {
    try{
      Response response =
          await get('https://worldtimeapi.org/api/timezone/$url');
      await Future.delayed(Duration(seconds: 2), () {});
      print(response.body);
      Map data = jsonDecode(response.body);

      // Get properties from data
      String dateTime = data['datetime'];
      String offset = data['utc_offset'].substring(1, 3);
      // print(dateTime);
      // print(offset);

      // Create DateTime object
      DateTime now = DateTime.parse(dateTime);
      now = now.add(Duration(hours: int.parse(offset)));
      //Set time Property
      time = DateFormat.jm().format(now);
    }
    catch(e){time = 'got error';}
  }
}
