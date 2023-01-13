import 'package:flutter/material.dart';
import 'package:flutter_store/MaterialIO/index.dart';

void main() {
  runApp(MaterialApp(
      initialRoute: '/',
      routes: {
        '/': (Context) => Index(),
        '/Home': (Context) => Index(),
      },
  ));
  }