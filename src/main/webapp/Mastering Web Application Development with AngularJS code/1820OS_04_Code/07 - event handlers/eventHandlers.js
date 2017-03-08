angular.module('eventHandlers', [])

  .controller('EventhandlersCtrl', function ($scope) {

    $scope.items = ['foo', 'bar', 'baz'];

    $scope.readPosition = function (item, $event,test) {
      console.log(item + ' was clicked at: ' + $event.toLocaleString() +$event.clientX+ ',' + $event.clientY);
    };
  });