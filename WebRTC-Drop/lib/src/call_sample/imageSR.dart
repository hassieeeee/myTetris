import 'dart:io';
import 'dart:typed_data';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:image_gallery_saver/image_gallery_saver.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Image? _image;
  Image? _image2;
  XFile? _xfiles;

  void _uploadPicture(Image? image) {
    setState(() {
      _image = image;
    });
  }

  void _setBinary() async{
    Uint8List byte = await _xfiles!.readAsBytes();
    setState(() {
      if(byte != null) {
        _image2 = Image.memory(byte);
      }
    });
  }

  Future _saveImage() async {
    if(_xfiles != null) {
      Uint8List _buffer = await _xfiles!.readAsBytes();
      final result = await ImageGallerySaver.saveImage(_buffer);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Container(
                width: 300,
                height: 300,
                child: _image != null
                    ? _image
                    : Container(
                  color: Colors.grey,
                )),
            SizedBox(
              height: 10,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () async {
                    Image? image = await getPictureImage_mobile();
                    _uploadPicture(image);
                  },
                  child: Text("画像取得"),
                  style: ElevatedButton.styleFrom(
                    primary: Colors.orange,
                    onPrimary: Colors.white,
                  ),
                ),
                SizedBox(
                  width: 10,
                ),
                ElevatedButton(
                  onPressed: () async {
                    _setBinary();
                  },
                  child: Text("アップロード"),
                  style: ElevatedButton.styleFrom(
                    primary: Colors.blue,
                    onPrimary: Colors.white,
                  ),
                ),
                SizedBox(
                  width: 10,
                ),
                ElevatedButton(
                  onPressed: () async {
                    bool answer = await showDialog(
                        context: context,
                        builder: (_) {
                          return AlertDialogSample();
                        }
                    );
                    if(answer){
                      _saveImage();
                    }
                  },
                  child: Text("保存"),
                  style: ElevatedButton.styleFrom(
                    primary: Colors.red,
                    onPrimary: Colors.white,
                  ),
                ),
              ],
            ),
            Container(
                width: 200,
                height: 200,
                child: _image2 != null
                    ? _image2
                    : Container(
                  color: Colors.grey,
                )),
          ],
        ),
      ),
    );
  }

  /* モバイルアプリ用画像取得 */
  Future<Image?> getPictureImage_mobile() async {
    Image? image;
    ImagePicker picker = ImagePicker();
    XFile? xfile = await picker.pickImage(source: ImageSource.gallery);
    setState(() {
      _xfiles = xfile;
    });
    if (xfile != null) {
      File file = File(xfile.path);
      image = Image.file(file);
    }
    return image;
  }
}

class AlertDialogSample extends StatelessWidget {
  const AlertDialogSample({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text('画像を保存しますか？'),
      content: Text('こうかいしませんね？'),
      actions: <Widget>[
        GestureDetector(
          child: Text('いいえ'),
          onTap: () {
            Navigator.of(context).pop(false);
          },
        ),
        GestureDetector(
          child: Text('はい'),
          onTap: () {
            Navigator.of(context).pop(true);
          },
        )
      ],
    );
  }
}