import 'package:flutter/material.dart';
import 'package:world_time/Services/World_time.dart';
class Choose_loaction extends StatefulWidget {
  @override
  _Choose_locationState createState() => _Choose_locationState();
}



// When starting a new activity and in new activity there is an appbar which automatically has back-button
class _Choose_locationState extends State<Choose_loaction> {

  List<World_time> location = [
    World_time(url: 'Europe/London',location: 'London',flag: 'uk.png'),
    World_time(url: 'Europe/Berlin',location: 'Athens',flag: 'greecn.png'),
    World_time(url: 'Africa/Cairo',location: 'Cairo',flag: ''),
    World_time(url: 'America/Chicago',location: 'Chicago',flag: ''),
    World_time(url: 'Africa/Nairobi',location: 'Nairobi',flag: ''),
    World_time(url: 'America/New_York',location: 'New York',flag: ''),
    World_time(url: 'Asia/Seoul',location: 'Seoul',flag: ''),
    World_time(url: 'Asia/Jakarta',location: 'Jakarta',flag: 'indonesia.png'),
  ];

  void updateTime(index)async{
  World_time instance = location[index];
  await instance.getTime();
  //navigate to back home screen
    //pop function destroy current screen and bring back to other screen
    Navigator.pop(context,{
      'location': instance.location,
      'flag': instance.flag,
      'time': instance.time
    });
  }
  @override
   Widget build(BuildContext context) {
    return Scaffold(backgroundColor: Colors.grey[200],
    appBar: AppBar(
      backgroundColor: Colors.blue[900],
      title: Text('Choose a Location'),
      centerTitle: true,
      elevation: 5,
    ),
    body: ListView.builder(
      itemCount: location.length,
      itemBuilder: (context,index){
        return Padding(
          padding: const EdgeInsets.symmetric(vertical: 1.0,horizontal: 4.0),
          child: Card(
            child: ListTile(
              onTap: (){updateTime(index);},
              title: Text(location[index].location),
              leading: CircleAvatar(
                backgroundImage: AssetImage('assets/img.jpg'),
              ),
            ),
          ),
        );
      },
    ),);
  }


}
