import 'package:flutter/material.dart';

class Index extends StatefulWidget {
  @override
  _IndexState createState() => _IndexState();
}

class _IndexState extends State<Index> {
  List<String> components = [
    'App Bar:bottom','App Bar:top',
  ];

  _IndexState();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        // .map is same as enhanced for loop
//        children: names.map((e) => CardTemplete(e)).toList(),
        //Above code was used as an alternate ref: 002a
        children: components.map((e) => CardView(e);
        }).toList()),
      );

}

/*
class _IndexState extends State<Index> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Scaffold(
        body: SafeArea(
          child: Padding(
            padding: const EdgeInsets.fromLTRB(30, 10, 20, 0),
            child: Column(
              children: [
                TextFormField(
                  cursorColor: Theme.of(context).cursorColor,
                  initialValue: '',
                  maxLength: 20,
                  decoration: InputDecoration(
                    icon: Icon(Icons.favorite),
                    labelText: 'Label text',
                    labelStyle: TextStyle(
                      color: Color(0xFF6200EE),
                    ),
                    helperText: 'Helper text',
                    suffixIcon: Icon(
                      Icons.check_circle,
                    ),
                    enabledBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Color(0xFF6200EE)),
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
*/


class CardView extends StatelessWidget  {
  final String name;

  CardView({this.name});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.fromLTRB(16.0, 16.0, 16.0, 0),
      child: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Center(child: Text(name,
              style: TextStyle(
                fontSize: 18.0,
                color: Colors.green,
              )
              ,),),
            FlatButton.icon(
              label: Text('Delete.'),
              icon: Icon(Icons.delete),
            )
          ],
        ),
      ),);
  }
}