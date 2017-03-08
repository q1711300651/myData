describe('hello World test', function () {

  var greeter;
  beforeEach(function () {
    greeter = new Greeter();
  });

  it('should say hello to the World9', function () {
    expect(greeter.say('World')).toEqual('Hello, World!');
  });
});