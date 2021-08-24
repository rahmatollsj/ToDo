import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:todo/ViewsModels/RootPageViewModel.dart';
import 'package:todo/data/TaskList.dart';
import 'package:todo/enums/PageTypes.dart';
import 'package:todo/views/HomePage.dart';
import 'package:todo/views/TasksPage.dart';
import '../listeners/RootPageListener.dart';

class RootPage extends StatefulWidget {
  RootPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _RootPageState createState() => _RootPageState();
}

class _RootPageState extends State<RootPage> implements RootPageListener {
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
  final _listTitleController = TextEditingController();
  PageTypes currentPage = PageTypes.Home;

  @override
  void initState() {
    super.initState();
    _viewModel.setListener(this);

    // Add the basic elements of the drawer.
    _drawerItems.addAll([ListTile(
      leading: Icon(Icons.home),
      title: Text('To Do'),
      onTap: () => _changePage(PageTypes.Home),
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

    for(int i = 0; i < _viewModel.getNbLists(); i++) {
      TaskList taskList = _viewModel.getList(i);
      _drawerItems.add(ListTile(
        leading: Icon(IconData(taskList.icon, fontFamily: 'MaterialIcons')),
        title: Text(taskList.title),
        onTap: () => _changePage(PageTypes.Tasks)
      ));
    }
  }

  @override
  Widget build(BuildContext context) {
    Widget body;

    switch(currentPage) {
      case PageTypes.Home:
        body = HomePage();
        break;
      case PageTypes.Tasks:
        body = TasksPage();
        break;
    }

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
      body: body
    );
  }

  Widget _buildAddListAlertDialog(BuildContext context) {
    return AlertDialog(
      title: Text("Add a new list"),
      content: SingleChildScrollView(
        physics: ScrollPhysics(),
        child: TextField(
          controller: _listTitleController,
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
            _listTitleController.clear();
          },
          child: const Text('Cancel'),
        ),
        TextButton(
          onPressed: () {
            Navigator.pop(context, 'Add');
            _viewModel.addList(Icons.view_list.codePoint, _listTitleController.text);
            _listTitleController.clear();
          },
          child: const Text('Add'),
        ),
      ],
    );
  }

  @override
  void onListAdded() {
    TaskList taskList = _viewModel.getList(_viewModel.getNbLists() - 1);
    setState(() {
      _drawerItems.add(ListTile(
          leading: Icon(IconData(taskList.icon, fontFamily: 'MaterialIcons')),
          title: Text(taskList.title),
          onTap: () => _changePage(PageTypes.Tasks)
      ));
      currentPage = PageTypes.Tasks;
    });
  }

  @override
  void onListRemoved(int index) {
    // TODO: implement onListRemoved
  }

  @override
  void onListUpdated(int index, int icon, String title) {
    // TODO: implement onListUpdated
  }

  void _changePage(PageTypes page) {
    setState(() {
      currentPage = page;
    });
    Navigator.pop(context);
  }
}