describe('tips & tricks', function () {

  xdescribe('none of the tests here will execute', function () {

    it('should not execute - spec level', function () {
    });

    xit('not execute - test level', function () {
    });
  });

  describe('suite with one test selected', function () {

    iit('should execute only this test', function () {
    });

    it('should execute this one after removing iit', function () {
    });
  });
});

describe('test1',function(){
  xdescribe('none',function(){
    it();
    xit();
    describe('test11',function(){
      iit();
      it();

    })

  })
})
