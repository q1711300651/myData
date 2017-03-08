angular.module('async', [])
  .factory('asyncGreeter', function ($timeout, $log) {
    return {
      say:function (name, timeout) {
        $timeout(function(){
          $log.info("Hello, " + name + "!");
        });
      }
    };
  });





angular.module('app',[])
    .factory('testTimeout',function($scope,$log,$timeout){
      return {
        say:function(name,timeOut){
          $timeout(function(){
            $log.info('test'+name);
          });


        }
      }

    });