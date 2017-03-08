angular.module('filtersPerf', [])

  .filter('logUppercase', function(uppercaseFilter){
    return function(input) {
      console.log('Calling uppercase on: '+input);
      alert(uppercaseFilter("0ddd00"));
      return uppercaseFilter(input);
    };
  });
