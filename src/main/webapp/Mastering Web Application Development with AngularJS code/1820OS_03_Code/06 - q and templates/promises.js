angular.module('promises', [])
  .controller('PromiseCtrl', function ($scope, $timeout) {

    $scope.name = $timeout(function () {
      return "World";
    }, 2000);

    $scope.getName = function () {
      return $timeout(function () {
        return "World";
      }, 2000);
    };

    $timeout(function () {
     $scope.name= "Worl0000d";
      return 0;
    }, 4000).then(function (value) {
        alert(value);
      });
  });