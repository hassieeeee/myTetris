import 'dart:core';

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'src/call_sample/data_channel_sample.dart';
import 'src/route_item.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const MyHomePage(title: 'WebRTC Drop Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  final String title;

  const MyHomePage({
    Key? key,
    required this.title,
  }) : super(key: key);
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<RouteItem> items = [];
  String _server = '0.0.0.0:8086';
  late SharedPreferences _prefs;

  @override
  initState() {
    super.initState();
    _initData();
  }


  _connectServer(){
    Navigator.of(context).push(
        MaterialPageRoute(
          builder: (context) {
            return DataChannelSample(host: _server);
          },
        ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return
       Scaffold(
          appBar: AppBar(
            title: Text('WebRTC Drop'),
          ),
          body: Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Text('Enter server address:',
                  style: TextStyle(
                    fontSize: 25,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                SizedBox(
                  height: 15,
                ),
                TextField(
                  onChanged: (String text) {
                    setState(() {
                      _server = text;
                    });
                  },
                  style: TextStyle(
                    fontSize: 25,
                  ),
                  decoration: InputDecoration(
                    hintText: _server,
                  ),
                  textAlign: TextAlign.center,
                ),
                SizedBox(
                  height: 20,
                ),
                SizedBox(
                  height: 60,
                  width: 160,
                  child: ElevatedButton(
                      onPressed: (){
                        _connectServer();
                      },
                      child: Text(
                        "Connect",
                        style: TextStyle(
                          fontSize: 25,
                        ),
                      ),
                  ),
                ),
              ],
            ),
          ),
      );
  }

  _initData() async {
    _prefs = await SharedPreferences.getInstance();
    setState(() {
      _server = _prefs.getString('server') ?? 'demo.cloudwebrtc.com';
    });
  }




}


