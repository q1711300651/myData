var app = angular.module('myApp', []);

app.controller('MainCtrl', function($scope) {
  $scope.letters = ['A', 'B', 'C', 'D'];
  $scope.banners=[{photoPath:'https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png',url:'http://taobao.com'},{photoPath:'https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png',url:'http://taobao.com'}]
  $scope.letter = 'E';
  $scope.getType = function(item) {
    if ( angular.isDefined(item) ) {
      if ( item === null ) {
        return 'null';
      }
      return typeof item;
    }
    return 'undefined';
  };
});
