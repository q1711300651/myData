var app = angular.module('myApp', []);

app.controller('MainCtrl', function($scope) {
  $scope.users = [
    { firstName: 'Jo', lastName: 'Jordan', email: 'jo@jordan.com', sex:"Female",age:'100'},
    { firstName: '1', lastName: 'Jordan', email: 'jsso@jordan.com', sex:"Female",age:'100'},
    { firstName: 'Anne', lastName: 'Asher', email: 'anne@asher.com', sex:"Female",age:'110'},
    { firstName: 'Steve', lastName: 'Stone', email: 'steve@stone.com', sex:"Male",age:'200'},
    { firstName: 'Kev', lastName: 'King', email: 'kev@king.com', sex:"Male",age:'130'}
    ];
    if($scope.users.length>0){
      $scope.chosenUser=$scope.users[0];
    }
    $scope.getFullName = function(user) {
      return user.firstName + ' ' + user.lastName;
    };
});
