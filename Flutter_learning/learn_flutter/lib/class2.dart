class Class2 {
  String a;
  String b;

// Method1
  Class2({this.a, this.b});

  //triditional Method

  /*Class2({String a, String b}) {
    this.a = a;
    this.b = b;
  }*/
}

// IF calling using a traditional method then
//Class2 dynamic = Class2('Prince','Bansal');
// If using new ways
//Class2 _class2 = Class2(b: 'bansal', a: 'Prince');
// Plus point is that we can add arguments in any order
