var adminProjects = angular.module('admin-projects', []);

adminProjects.controller('ProjectsListCtrl', function($scope,$log) {
  //controller's code go here
  $log.error('error11');
  $log.info('info11');
});

adminProjects.controller('ProjectsEditCtrl', function($scope) {
  //controller's code go here
});