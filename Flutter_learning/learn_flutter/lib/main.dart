import 'package:flutter/material.dart';
import 'package:learn_flutter/class2.dart';

/// for more fonts visit https://fonts.google.com/
// to add fonts download any suitable from above website and paste the extracted items into
// the fonts folder under myapp[project name](if does not exists then create the fonts folder)
// then go to pubspec.yaml and uncomment or change the fonts code and final results should look like this
// fonts:
//    - family: NerkoOne
//      fonts:
//        - asset: fonts/NerkoOne-Regular.ttf
void main() {
  runApp(MaterialApp(
    home: Home(),
  ));
}

/// List View /// Recycler Viewer
class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

/// list with card view and also card can be reused later
//1) open flutter outline from right vertical tabs and right click on card and chose extract
//2) after extraction there woud be no arguments passed inside return method by default so we will
// pass same arguments as of passed in method returning widget like in CardTemplete
//3) Now for class generated we don't have default constructor so we will create one and receive all the arguments there and rename their using values

class _HomeState extends State<Home> {
  List<Class2> names = [
    Class2(a: 'Prince', b: 'Bansal'),
    Class2(a: 'Bansal', b: 'Prince'),
    Class2(b: 'Bansal', a: 'Prince'),
  ];

  // Below code was used for alternate ref: 002a
/*
  Widget CardTemplete(name) {
    return SampleCard(name: name);

  }
*/

  //List view with cards
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        // .map is same as enhanced for loop
//        children: names.map((e) => CardTemplete(e)).toList(),
        //Above code was used as an alternate ref: 002a
        children: names.map((e) => SampleCard(name: e,delete: (){
          setState(() {
            names.remove(e);
            print("Deleted");
          });
        })).toList(),
      ),
    );
  }


/// List view with cards */*/*/*/*/*
/*
class _HomeState extends State<Home> {
  List<Class2> names = [
    Class2(a: 'Prince', b: 'Bansal'),
    Class2(a: 'Bansal', b: 'Prince'),
    Class2(b: 'Bansal', a: 'Prince'),
  ];

  Widget CardTemplete(name) {
    return Card(
      margin: EdgeInsets.fromLTRB(16.0, 16.0, 16.0, 0),
      child: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Center(child: Text(name.a,
              style: TextStyle(
                fontSize: 18.0,
                color: Colors.green,
              )
              ,),),
            SizedBox(height: 6.0,),
            Text(name.b,
              style: TextStyle(
                fontSize: 18.0,
                color: Colors.green,
              )
              , ),
          ],
        ),
      ),);

  }

  //List view with cards
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        // .map is same as enhanced for loop
        children: names.map((e) => CardTemplete(e)).toList(),
      ),
    );
  }
  */

  // Simple List view

  /*@override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        // .map is same as enhanced for loop
        children: names.map((e) => Text('${e.a} - ${e.b}')).toList(),
      ),
    );
  }*/

  // till here */*/*/*/*/*
}

class SampleCard extends StatelessWidget {
   final Class2 name;
   final Function delete;
  SampleCard( {this.name,this.delete});
  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.fromLTRB(16.0, 16.0, 16.0, 0),
      child: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Center(child: Text(name.a,
              style: TextStyle(
                fontSize: 18.0,
                color: Colors.green,
              )
              ,),),
            SizedBox(height: 6.0,),
            Text(name.b,
              style: TextStyle(
                fontSize: 18.0,
                color: Colors.green,
              )
              , ),
          SizedBox(height: 8.0),
          FlatButton.icon(
            onPressed: delete,
            label: Text('Delete.'),
            icon: Icon(Icons.delete),
          )],
        ),
      ),);
  }
}

/// Working With simple List
/*
class _HomeState extends State<Home> {
  List<String> names=  ['1','2','3','4'];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        // .map is same as enhanced for loop
        children: names.map((e) => Text(e)).toList(),
      ),
    );
  }
}
*/


/** 2 Types of States **/
// 2 types of widgets 1) Stateless and stateful
// Stateless can be used to set data which does not needs to be changed
// Stateful is used when we need to update a data

/// Auto Reload

///TYPE2: Stateful

/*
class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int count=0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.menu),
        title: Text(
          'testing',
        ),
        centerTitle: true,
        backgroundColor: Colors.green[600],
      ),
      body: Row(
        // Expanded is same as the match_parent and flex is same as weight according to java
        children: [
          Expanded(
            child: Image.asset('assets/image1.jpg'),
            flex: 4,
          ),
          Expanded(
            flex: 3,
            child: Container(
              padding: EdgeInsets.all(30.0),
              color: Colors.yellowAccent,
              child: Text('1st'),
            ),
          ),
          Expanded(
            flex: 2,
            child: Container(
              padding: EdgeInsets.all(20.0),
              color: Colors.lightBlue,
              child: Text('1st'),
            ),
          ),
          Expanded(
            flex: 1,
            child: Container(
              padding: EdgeInsets.all(10.0),
              color: Colors.green,
              child: Text('1st'),
            ),
          )
        ],
      )

      /// vertical and horizontal columns
*/
/*
      body: Column(//mainAxisAlignment is used as height and crossAxisAlignment is used as width
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [ Padding(padding: EdgeInsets.all(25),child: Text('Padding...'),),
                Container(color: Colors.pinkAccent[200],padding: EdgeInsets.fromLTRB(10.0,15,20.0,35.0),margin: EdgeInsets.symmetric(horizontal: 15,vertical: 25),
      child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.yellowAccent,),),
    Text("Yeah!",style: TextStyle(fontSize: 52.0,fontWeight: FontWeight.bold,letterSpacing: 2.0,fontFamily: 'NerkoOne',color: Colors.grey[600],),),
      Row(//mainAxisAlignment is used as width and crossAxisAlignment is used as vertical width
        children: [ Padding(padding: EdgeInsets.all(25),child: Text('Padding...'),),
          Container(color: Colors.pinkAccent[200],padding: EdgeInsets.fromLTRB(10.0,15,20.0,35.0),margin: EdgeInsets.symmetric(horizontal: 15,vertical: 25),
            child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.yellowAccent,),),
          Text("Yeah!",style: TextStyle(fontSize: 52.0,fontWeight: FontWeight.bold,letterSpacing: 2.0,fontFamily: 'NerkoOne',color: Colors.grey[600],),),],
      )
      ],

      )
*/ /*


      // Padding
      // body: Padding(padding: EdgeInsets.all(25),child: Text('Padding...'),)
*/
/*
    body: Container(color: Colors.pinkAccent[200],padding: EdgeInsets.fromLTRB(10.0,15,20.0,35.0),margin: EdgeInsets.symmetric(horizontal: 15,vertical: 25),
        child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.yellowAccent,))
*/ /*

      ///Divider
      //Divider(thickness: 2.0,height: 20.0,color: Colors.black87,),
*/
/*
      body: Center(
        //child: IconButton(onPressed: (){print('Icon-clicked: Message is printed in console');},icon: Icon(Icons.accessible_forward_outlined),),
         child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.pinkAccent,)
        // child: RaisedButton(onPressed: (){print('Button-clicked: Message is printed in console');},child: Text('Button '),color: Colors.pinkAccent,)
        ///ICONS
        //Icon(Icons.wifi_sharp, color: Colors.lightBlue, size: 150,  ),


        // to add images in local app add a directory named assets
        //go to pubspec.yaml and add assets: - assets/{imageName} or try uncommenting
        // * Also if we are having lots of assets then we can replace assets/{imageName} with assets/
          //child:Image.asset('assets/image1.jpg'),
          // OR
          // Image(image: AssetImage('assets/image1.jpg'),)
          //Image(image: NetworkImage('https://images-na.ssl-images-amazon.com/images/I/411KBawg7vL.jpg'),)
          // CircleAvatar(backgroundImage: AssetImage('assets/image1.jpg'),radius: 80.0,)
      )
 */ /*


      */
/*Text(
        "Yeah!",
        style: TextStyle(
          fontSize: 60.0,
          fontWeight: FontWeight.bold,
          letterSpacing: 2.0,
          fontFamily: 'NerkoOne',
          color: Colors.grey[600],
        ),
      ))*/ /*

      ,
      floatingActionButton: FloatingActionButton(
        child: Text('$count'),
        onPressed: (){
          setState(() {
            count++;
          });
        },
      ),
    );
  }
}
*/


///TYPE1 : Stateless
// Method below is used to enable auto hot reload and this also helps in reusing the code
// To start Auto reload we have to call below method in main() example void main(){runApp(MaterialApp(
//     home: Home(),)}
// And instead iof writing or adding widgets in void main method we add below
// After making changes press ctrl+S so that changes can take place
// -Also several methods can be created
/*
class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.menu),
        title: Text(
          'testing',
        ),
        centerTitle: true,
        backgroundColor: Colors.green[600],
      ),
      body: Row(
        // Expanded is same as the match_parent and flex is same as weight according to java
        children: [
          Expanded(
            child: Image.asset('assets/image1.jpg'),
            flex: 4,
          ),
          Expanded(
            flex: 3,
            child: Container(
              padding: EdgeInsets.all(30.0),
              color: Colors.yellowAccent,
              child: Text('1st'),
            ),
          ),
          Expanded(
            flex: 2,
            child: Container(
              padding: EdgeInsets.all(20.0),
              color: Colors.lightBlue,
              child: Text('1st'),
            ),
          ),
          Expanded(
            flex: 1,
            child: Container(
              padding: EdgeInsets.all(10.0),
              color: Colors.green,
              child: Text('1st'),
            ),
          )
        ],
      )

      /// vertical and horizontal columns
*/
/*
      body: Column(//mainAxisAlignment is used as height and crossAxisAlignment is used as width
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [ Padding(padding: EdgeInsets.all(25),child: Text('Padding...'),),
                Container(color: Colors.pinkAccent[200],padding: EdgeInsets.fromLTRB(10.0,15,20.0,35.0),margin: EdgeInsets.symmetric(horizontal: 15,vertical: 25),
      child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.yellowAccent,),),
    Text("Yeah!",style: TextStyle(fontSize: 52.0,fontWeight: FontWeight.bold,letterSpacing: 2.0,fontFamily: 'NerkoOne',color: Colors.grey[600],),),
      Row(//mainAxisAlignment is used as width and crossAxisAlignment is used as vertical width
        children: [ Padding(padding: EdgeInsets.all(25),child: Text('Padding...'),),
          Container(color: Colors.pinkAccent[200],padding: EdgeInsets.fromLTRB(10.0,15,20.0,35.0),margin: EdgeInsets.symmetric(horizontal: 15,vertical: 25),
            child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.yellowAccent,),),
          Text("Yeah!",style: TextStyle(fontSize: 52.0,fontWeight: FontWeight.bold,letterSpacing: 2.0,fontFamily: 'NerkoOne',color: Colors.grey[600],),),],
      )
      ],

      )
*/ /*


      // Padding
      // body: Padding(padding: EdgeInsets.all(25),child: Text('Padding...'),)
*/
/*
    body: Container(color: Colors.pinkAccent[200],padding: EdgeInsets.fromLTRB(10.0,15,20.0,35.0),margin: EdgeInsets.symmetric(horizontal: 15,vertical: 25),
        child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.yellowAccent,))
*/ /*

///Divider
      //Divider(thickness: 2.0,height: 20.0,color: Colors.black87,),
*/
/*
      body: Center(
        //child: IconButton(onPressed: (){print('Icon-clicked: Message is printed in console');},icon: Icon(Icons.accessible_forward_outlined),),
         child: RaisedButton.icon(onPressed: (){print('Button-clicked: Message is printed in console');}, icon: Icon(Icons.wifi_sharp),label: Text('Button '),color: Colors.pinkAccent,)
        // child: RaisedButton(onPressed: (){print('Button-clicked: Message is printed in console');},child: Text('Button '),color: Colors.pinkAccent,)
        ///ICONS
        //Icon(Icons.wifi_sharp, color: Colors.lightBlue, size: 150,  ),


        // to add images in local app add a directory named assets
        //go to pubspec.yaml and add assets: - assets/{imageName} or try uncommenting
        // * Also if we are having lots of assets then we can replace assets/{imageName} with assets/
          //child:Image.asset('assets/image1.jpg'),
          // OR
          // Image(image: AssetImage('assets/image1.jpg'),)
          //Image(image: NetworkImage('https://images-na.ssl-images-amazon.com/images/I/411KBawg7vL.jpg'),)
          // CircleAvatar(backgroundImage: AssetImage('assets/image1.jpg'),radius: 80.0,)
      )
 */ /*


      */
/*Text(
        "Yeah!",
        style: TextStyle(
          fontSize: 60.0,
          fontWeight: FontWeight.bold,
          letterSpacing: 2.0,
          fontFamily: 'NerkoOne',
          color: Colors.grey[600],
        ),
      ))*/ /*

      ,
      floatingActionButton: FloatingActionButton(
        child: Text('+'),
      ),
    );
  }
}
*/
