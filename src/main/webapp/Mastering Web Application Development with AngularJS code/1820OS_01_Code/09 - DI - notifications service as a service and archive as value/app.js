angular.module('notificationsApp', [])
  .controller('NotificationsCtrl',NotificationsCtrl)
    .service('notificationsService', NotificationsService)
  .value('notificationsArchive', new NotificationsArchive())
    .config(['$routeProvider','$locationProvider', function ($routeProvider,$locationProvider) {
        $routeProvider.when('/test',{
        templateUrl:'test.html'
    });
}]);
;
