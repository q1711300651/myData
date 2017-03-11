'use strict';

/**
 * UserController
 */
var TestController = function($scope, $http) {

    $scope.test=function(){
        $http.get('users/test').success(function(){
            console.log("test...");
        });
    }

};