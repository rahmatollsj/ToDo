import 'package:flutter/cupertino.dart';
import 'package:todo/data/TaskList.dart';
import 'package:todo/models/RootPageModel.dart';

class RootPageViewModel extends ChangeNotifier {
  final RootPageModel _model = new RootPageModel();

  int getNbLists() => _model.lists.length;
  TaskList getList(int index) {
    if(_model.lists.length <= index || index < 0) return new TaskList(0, 0, "error");
    return _model.lists[index];
  }

  void setList(int index, int icon, String title) {
    if(_model.lists.length > index || index < 0) {
      _model.lists[index].icon = icon;
      _model.lists[index].title = title;
      notifyListeners();
    }
  }

  void removeList(int index) {
    if(_model.lists.length > index || index < 0) {
      _model.lists.removeAt(index);
      notifyListeners();
    }
  }

  void addList(int icon, String title) {
    _model.lists.add(new TaskList(_model.lists.length + 1, icon, title));
    notifyListeners();
  }
}