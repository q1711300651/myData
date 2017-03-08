angular.module('repeatOverObjectApp', [])

  .controller('RepeatOverObjectCtrl', function ($scope) {

    $scope.dataAsObj = {
      foo : 'foo value1',
      bar : 'bar value2',
      baz : 'baz value3'
    };
  });