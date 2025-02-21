# Week 1

## Reflection 1

I've used several clean code principles in implementing my edit and delete feature. I used meaningful names for my functions and variables. I also wrote them clear enough to the point I can understand the code without any comments reminding me. I tried my best to apply the right object and data structure. Some errors have also been handled, although some others have not. For security principle, I used id as a way to authenticate a request to a certain object. However, I have not implemented enough entropy to the id attribute. To improve my code, I can implement a more holistic error handling and implement an id with more entropy.

## Reflection 2

1. I feel really satisfied after writing the unit-test. It gives me more confidence about my code. It depends on how many we need to cover our test sceanrios. Even having 100% coverage does not mean our code does not have bugs. There might be external dependencies that the test does not catch.

2. The new code will surely be unclean. The new code will reduce the code quality. It might create naming issues. Therefore we might need more comments to differentiate the two and this will create redundant comments. So in general, duplicate codes can really reduce the quality of our clean code. We can instead use a base class. With the availability of a base class, we could avoid using duplicate codes easier.

# Week 2

1. There were some issues with my code. I tried fixing some of those issues. I fixed "Unnecessary modifier 'public' on method 'delete'". I fixed "Unnecessary modifier 'public' on method 'create'" too. I also fixed "Unnecessary modifier 'public' on method 'create'". The code issues were pretty self-explanatory, so all I did was pinpoint the source of the issue and change it.

2. I think the current workflow implementation has met the definition of CI/CD. The workflows automatically run tests and build the project every time code is pushed. This is done by setting up our ci.yml to do it. The workflows automatically scan our code on every push for any issues. This is done by setting up our pmd.yml to do it. The workflows automatically deploy our web on every push. This is done by linking our GitHub Repository to Koyeb. 

 
