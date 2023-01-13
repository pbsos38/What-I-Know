import 'package:flutter/material.dart';
import 'package:world_time/Choose_loaction.dart';
import 'package:world_time/Loading.dart';
import 'package:world_time/Pages/Home.dart';

void main() {
  runApp(MaterialApp(
      //home: Home(),
    initialRoute: '/loading',
    /// Routes are the manifest file of android project
    routes: {
        '/': (Context) => Home(),
        '/Home': (Context) => Home(),
        '/location': (Context) => Choose_loaction(),
        '/loading': (Context) => Loading(),
    },
  ),
  );
}
