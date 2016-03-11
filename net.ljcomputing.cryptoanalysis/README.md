# Frequency Analysis

Frequency analysis counts the number of characters that occur within a given set of characters.

For example, suppose you wnat to know how many times the work "the" occurs in a sentence. The code looks something like this:

    ClassLoader classLoader = MainCounter.class.getClassLoader();
    File file = new File(classLoader.getResource("test.txt").getFile());
    String source = MainCounter.openFile(file);
    
    MainCounter counter = new MainCounter(3, source);
    Map<String, Integer> counts = counter.getLetterFrequency();
    
    counter.printResults(counts);

There are no dependencies for this project.

## License

<a href="http://www.apache.org/licenses/LICENSE-2.0"> Apache License</a>

## Author and Feedback

Author: <a href="willmorejg@gmail.com?subject=Status Reporter">James G Willmore</a>, <a href="http://ljcomputing.net/">LJ Computing</a>
