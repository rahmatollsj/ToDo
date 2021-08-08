import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:todo/ViewsModels/RootPageViewModel.dart';
import 'package:todo/data/TaskList.dart';

class RootPage extends StatefulWidget {
  RootPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _RootPageState createState() => _RootPageState();
}

class _RootPageState extends State<RootPage> {
  final RootPageViewModel _viewModel = new RootPageViewModel();
  final List<Widget> _drawerItems = [Container(
    height: 84,
    child: DrawerHeader(
      child: Text(
        'To Do',
        style: TextStyle(fontSize: 20),
      ),
    ),
  )];
  final listTitleController = TextEditingController();

  @override
  void initState() {
    super.initState();

    _viewModel.addListener(() {});

    // Add the basic elements of the drawer.
    _drawerItems.addAll([ListTile(
      leading: Icon(Icons.home),
      title: Text('To Do'),
      onTap: () => _changePage(0),
    ), ListTile(
        leading: Icon(Icons.add),
        title: Text('Add a new list'),
        onTap: () {
          Navigator.pop(context);
          showDialog(context: context, builder: (context) => _buildAddListAlertDialog(context));
        }
    ), Divider(
      height: 1,
      thickness: 1,
    )]);

    // Add user's lists.
    for(int i = 0; i < _viewModel.getNbLists(); i++) {
      TaskList taskList = _viewModel.getList(i);
      _drawerItems.add(ListTile(
            leading: Icon(IconData(taskList.icon, fontFamily: 'MaterialIcons')),
            title: Text(taskList.title),
            onTap: () => _changePage(i),
          ));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      drawer: Drawer(
        child: ListView.builder(
          padding: EdgeInsets.zero,
          itemCount: _drawerItems.length,
          itemBuilder: (context, index) {
            return _drawerItems[index];
          }
        )
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
          ]
        )
      )
    );
  }

  Widget _buildAddListAlertDialog(BuildContext context) {
    return AlertDialog(
      title: Text("Add a new list"),
      content: SingleChildScrollView(
        physics: ScrollPhysics(),
        child: TextField(
          controller: listTitleController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: "Enter list title"
          )
        ),
      ),
      actions: <Widget>[
        TextButton(
          onPressed: () {
            Navigator.pop(context, 'Cancel');
            listTitleController.clear();
          },
          child: const Text('Cancel'),
        ),
        TextButton(
          onPressed: () {
            Navigator.pop(context, 'Add');
            _viewModel.addList(Icons.view_list.codePoint, listTitleController.text);
            listTitleController.clear();
          },
          child: const Text('Add'),
        ),
      ],
    );
  }

  @override
  void dispose() {
    _viewModel.removeListener(() { });
    super.dispose();
  }

  void _changePage(int index) {

  }
}