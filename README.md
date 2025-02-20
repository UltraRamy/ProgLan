# ProgLan

## Reflection 1

I've used several clean code principles in implementing my edit and delete feature. I used meaningful names for my functions and variables. I also wrote them clear enough to the point I can understand the code without any comments reminding me. I tried my best to apply the right object and data structure. Some errors have also been handled, although some others have not. For security principle, I used id as a way to authenticate a request to a certain object. However, I have not implemented enough entropy to the id attribute. To improve my code, I can implement a more holistic error handling and implement an id with more entropy.

## Reflection 2
1. I feel really satisfied after writing the unit-test. It gives me more confidence about my code. It depends on how many we need to cover our test sceanrios. Even having 100% coverage does not mean our code does not have bugs. There might be external dependencies that the test does not catch.

2. The new code will surely be unclean. The new code will reduce the code quality. It might create naming issues. Therefore we might need more comments to differentiate the two and this will create redundant comments. So in general, duplicate codes can really reduce the quality of our clean code. We can instead use a base class. With the availability of a base class, we could avoid using duplicate codes easier. 