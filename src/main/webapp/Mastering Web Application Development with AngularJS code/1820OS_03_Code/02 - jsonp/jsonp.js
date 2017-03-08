angular.module('jsonp', [])
  .controller('JSONPCtrl', function ($scope, $http) {

    $scope.jsonpGreet = function () {

      $http
        .jsonp('http://angularjs.org/greet.php?callback=JSON_CALLBACK', {
          params:{
            name:'World'
          }
        })
        .success(function (data) {
          $scope.greeting = data;
        }).error(function(data, status, headers, config){
          throw new Error('Something went wrong...'+status);
        });

    };
  });


var 小青年 = [{name:'test1'},{name:'test2'}];
for (var m in 小青年){
  document.write("i = " + m + "..................小青年[i] = " + 小青年[m].name + "<br>");
}

