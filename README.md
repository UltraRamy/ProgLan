Link: https://just-ilysa-ultramy-539d170c.koyeb.app/

<details>
# Week 1

## Reflection 1

I've used several clean code principles in implementing my edit and delete feature. I used meaningful names for my functions and variables. I also wrote them clear enough to the point I can understand the code without any comments reminding me. I tried my best to apply the right object and data structure. Some errors have also been handled, although some others have not. For security principle, I used id as a way to authenticate a request to a certain object. However, I have not implemented enough entropy to the id attribute. To improve my code, I can implement a more holistic error handling and implement an id with more entropy.

## Reflection 2

1. I feel really satisfied after writing the unit-test. It gives me more confidence about my code. It depends on how many we need to cover our test sceanrios. Even having 100% coverage does not mean our code does not have bugs. There might be external dependencies that the test does not catch.

2. The new code will surely be unclean. The new code will reduce the code quality. It might create naming issues. Therefore we might need more comments to differentiate the two and this will create redundant comments. So in general, duplicate codes can really reduce the quality of our clean code. We can instead use a base class. With the availability of a base class, we could avoid using duplicate codes easier.

# Week 2

1. There were some issues with my code. I tried fixing some of those issues. I fixed "Unnecessary modifier 'public' on method 'delete'". I fixed "Unnecessary modifier 'public' on method 'create'" too. I also fixed "Unnecessary modifier 'public' on method 'create'". The code issues were pretty self-explanatory, so all I did was pinpoint the source of the issue and change it.

2. I think the current workflow implementation has met the definition of CI/CD. The workflows automatically run tests and build the project every time code is pushed. This is done by setting up our ci.yml to do it. The workflows automatically scan our code on every push for any issues. This is done by setting up our pmd.yml to do it. The workflows automatically deploy our web on every push. This is done by linking our GitHub Repository to Koyeb. 

# Week 3

1. I applied SRP, OCP, LSP, ISP, and DIP. For SRP, I seperated a base service interface into two interfaces, one is for write and the other one is for read. For OCP, I made a BaseService so that any new entity with similar behavior to product or car can extend it easily. For LSP, I made sure that every function in the interface if implemented in the Impl file. For ISP, I seperated the extended the base service to Car and Product. For DIP, I made sure that my code relies on abstractions such as interfaces.

2. Implementing SOLID principle makes my code more maintainable and readable. I can figure out what file do what easily. It is also more structued. The biggest effect I felt is it is way easier to progress now. With the base service, I can extend classes with similar behavior easily.

3. It makes the code less readable. It also makes the code hard to maintain. Files are also harder to locate. It is also harder to progress. For example, I might need to do some redundant coding to make a service without base service.

# Week 4

1. TDD flow is useful for me. I do think it takes a little longer than making it normally. It is also really hard since we are making the tests first. Usually, I make the code first and then the test. But, I think I can think about my program more holistically. So, I think overall it is useful.
   
2. I have made a lot of unit tests in this tutorial. I think my unit test already cover happy and unhappy paths, although I think there might be holes here and there. My tests are also repeatable. Some of my assertions are loose, I might need to work on my error throwing and edge cases like null next time. I think my unit test is not the Fastest because there are some repeated tests of a feature/function. But, I try to keep it as few as I can. Next time, I will try to brainstorm harder in the early stages so that I do not have to add another test for the same function midway and therefore making my unit test more compact.
</details>
