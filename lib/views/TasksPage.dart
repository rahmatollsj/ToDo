import 'package:flutter/cupertino.dart';

class TasksPage extends StatefulWidget {

  @override
  _TasksPageState createState() => _TasksPageState();
}

class _TasksPageState extends State<TasksPage> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text('Tasks')
      ],
    );
  }
}