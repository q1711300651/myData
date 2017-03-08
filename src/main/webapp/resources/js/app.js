'use strict';

var AngularSpringmvcMybatis = {};

var App = angular.module('AngularSpringmvcMybatis', ['AngularSpringmvcMybatis.filters', 'AngularSpringmvcMybatis.services', 'AngularSpringmvcMybatis.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/users', {
        templateUrl: 'users/layout',
        controller: UserController
    }).when('/test',{template:'test123'});
        // .when('/test123'),{templateUrl:'users/test.html',controller:TestController};

    $routeProvider.otherwise({redirectTo: '/users'});
}]);
