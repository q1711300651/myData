'use strict';

var AngularSpringmvcMybatis = {};

var App = angular.module('AngularSpringmvcMybatis', ['AngularSpringmvcMybatis.filters', 'AngularSpringmvcMybatis.services', 'AngularSpringmvcMybatis.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider','$locationProvider', function ($routeProvider,$locationProvider) {
    $routeProvider.when('/users', {
        templateUrl: 'users/layout',
        controller: UserController
    }).when('/test',{
        templateUrl:'users/test',
        controller:UserController
    }).when('/login',{
        templateUrl:'/resources/test1.html',
        controller:UserController
    });
        // .when('/test123'),{templateUrl:'users/test.html',controller:TestController};

    $routeProvider.otherwise({redirectTo: '/users'});
    // $locationProvider.html5Mode(true);
}]);
